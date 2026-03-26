package iu.SpringBoot.Vaadin.views.DEQACheckAll.DESummaryPage.SummaryView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import iu.SpringBoot.Vaadin.views.MainView;
import iu.SwingStyle.LCCA.Startup.RsltComparator_v13.Starter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import jakarta.annotation.security.RolesAllowed;

@PageTitle("Result Overview DE v13 (Model Rows)")
@Route("summary-view-5")
@StyleSheet("./styles/summary-table.css")
@RolesAllowed("ADMIN")
public class SummaryView_DEv13_2 extends VerticalLayout {

    private static final String DATA_FOLDER_NAME = "share_package/data";
    private static final String VERSION_NAME = "DE_v13";
    private static final String SECTION_KEY = "reference_cohort_info_part";
    private static final Pattern SOURCE_PATTERN = Pattern.compile("_by_([^_]+)_", Pattern.CASE_INSENSITIVE);

    private final ObjectMapper mapper = new ObjectMapper();
    private final List<ModelRow> rows = new ArrayList<>();
    private List<ModelRow> filteredRows = new ArrayList<>();
    private Div scrollWrapper;
    private ComboBox<String> sourceFilter;
    private ComboBox<String> columnCopySelect;
    private Paragraph saveMessagePara;

    public SummaryView_DEv13_2() {
        getStyle().set("padding", "var(--lumo-space-m)");
        setSpacing(true);
        setWidthFull();

        add(new H2("DE_v13 (model-based) 一覧"));

        Path base = Paths.get(System.getProperty("user.dir"), DATA_FOLDER_NAME);
        if (!Files.exists(base) || !Files.isDirectory(base)) {
            add(new Paragraph("Data ディレクトリが見つかりません: " + base.toAbsolutePath()));
            return;
        }

        List<Path> authorYearDirs;
        try {
            authorYearDirs = Files.list(base)
                    .filter(Files::isDirectory)
                    .filter(p -> !p.getFileName().toString().equalsIgnoreCase("Someone20XX"))
                    .sorted(Comparator.comparing(p -> p.getFileName().toString().toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            add(new Paragraph("サブフォルダの走査でエラー: " + e.getMessage()));
            return;
        }

        if (authorYearDirs.isEmpty()) {
            add(new Paragraph("Dataディレクトリ配下にフォルダがありません。"));
            return;
        }

        for (Path authorYearDir : authorYearDirs) {
            String authorYear = authorYearDir.getFileName().toString();
            Path jsonDir = authorYearDir.resolve("DE_v13").resolve("json");
            if (!Files.exists(jsonDir) || !Files.isDirectory(jsonDir)) {
                continue;
            }
            List<Path> jsonFiles;
            try {
                jsonFiles = Files.list(jsonDir)
                        .filter(Files::isRegularFile)
                        .filter(p -> {
                            String name = p.getFileName().toString().toLowerCase(Locale.ROOT);
                            return name.endsWith(".json")
                                    && name.contains("de_v13_")
                                    && (name.contains("human") || name.contains("gemini")
                                    || name.contains("claude") || name.contains("codex"));
                        })
                        .sorted(Comparator.comparing(p -> p.getFileName().toString().toLowerCase(Locale.ROOT)))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                add(new Paragraph(authorYear + ": JSON ファイルの取得でエラー - " + e.getMessage()));
                continue;
            }

            for (Path jsonFile : jsonFiles) {
                mergeRows(authorYear, jsonFile);
            }
        }

        if (rows.isEmpty()) {
            add(new Paragraph("DE_v13 の JSON が見つかりません。"));
            return;
        }

        rows.sort(Comparator
                .comparing((ModelRow r) -> r.authorYear.toLowerCase(Locale.ROOT))
                .thenComparing(r -> r.modelName.toLowerCase(Locale.ROOT)));
        filteredRows = new ArrayList<>(rows);

        HorizontalLayout controlLayout = new HorizontalLayout();
        controlLayout.setAlignItems(Alignment.BASELINE);
        controlLayout.setSpacing(true);

        Button reloadButton = new Button("リロード", e -> getUI().ifPresent(ui -> ui.getPage().reload()));
        controlLayout.add(reloadButton);

        sourceFilter = new ComboBox<>("Source Filter");
        sourceFilter.setItems("all", "human", "gemini", "claude", "codex");
        sourceFilter.setValue("human");
        sourceFilter.addValueChangeListener(e -> {
            applySourceFilter();
            rebuildTable();
        });
        controlLayout.add(sourceFilter);

        columnCopySelect = new ComboBox<>("Column Copy");
        columnCopySelect.setItems(
                "AuthorYear", "ModelName", "Phase", "n",
                "female_n", "female_pct", "male_n", "male_pct",
                "age_mean", "age_sd", "age_min", "age_max", "Note"
        );
        columnCopySelect.setValue("AuthorYear");
        controlLayout.add(columnCopySelect);

        Button copyButton = new Button("Copy Column", e -> {
            String column = columnCopySelect.getValue();
            if (column == null || column.isBlank()) {
                Notification.show("列を選択してください");
                return;
            }
            List<String> values = new ArrayList<>();
            for (ModelRow row : filteredRows) {
                values.add(getColumnValue(row, column));
            }
            String joined = String.join("\n", values);
            getUI().ifPresent(ui -> ui.getPage().executeJs("navigator.clipboard.writeText($0)", joined));
            Notification.show("コピーしました: " + column);
        });
        controlLayout.add(copyButton);

        add(controlLayout);

        saveMessagePara = new Paragraph();
        saveMessagePara.getStyle().set("color", "var(--lumo-success-color)");
        saveMessagePara.getStyle().set("font-weight", "bold");
        saveMessagePara.getStyle().set("margin", "0");
        saveMessagePara.setVisible(false);
        add(saveMessagePara);

        scrollWrapper = new Div();
        scrollWrapper.getStyle().set("max-height", "70vh");
        scrollWrapper.getStyle().set("overflow", "auto");
        scrollWrapper.getStyle().set("width", "100%");
        scrollWrapper.getStyle().set("max-width", "100%");
        scrollWrapper.getStyle().set("border", "1px solid var(--lumo-contrast-20pct)");

        applySourceFilter();
        rebuildTable();
        add(scrollWrapper);

        add(new RouterLink("メインページへ戻る", MainView.class));
    }

    private void mergeRows(String authorYear, Path jsonFile) {
        try {
            JsonNode root = mapper.readTree(jsonFile.toFile());
            String phase = extractValue(root, new String[]{"rci4_phase"}, SECTION_KEY);
            String source = extractSource(jsonFile.getFileName().toString());

            Map<String, ModelRow> modelRows = new LinkedHashMap<>();
            mergeModelN(modelRows, root);
            mergeModelSex(modelRows, root);
            mergeModelAge(modelRows, root);

            String note = loadNote(authorYear);
            for (ModelRow row : modelRows.values()) {
                row.authorYear = authorYear;
                row.phase = phase;
                row.source = source;
                row.note = note;
                rows.add(row);
            }
        } catch (IOException e) {
            add(new Paragraph("JSON 読み取り失敗: " + jsonFile + " - " + e.getMessage()));
        }
    }

    private void mergeModelN(Map<String, ModelRow> modelRows, JsonNode root) {
        JsonNode node = extractAnswerNode(root, new String[]{"rci5_n"}, SECTION_KEY);
        if (node == null || node.isNull()) return;
        if (node.isObject()) {
            node.fields().forEachRemaining(entry -> {
                String modelKey = entry.getKey();
                JsonNode modelNode = entry.getValue();
                ModelRow row = modelRows.computeIfAbsent(modelKey, k -> new ModelRow());
                row.modelName = extractModelName(modelKey, modelNode);
                row.n = textOrEmpty(modelNode.get("n"));
            });
        }
    }

    private void mergeModelSex(Map<String, ModelRow> modelRows, JsonNode root) {
        JsonNode node = extractAnswerNode(root, new String[]{"rci6_sex"}, SECTION_KEY);
        if (node == null || node.isNull()) return;
        if (node.isObject()) {
            node.fields().forEachRemaining(entry -> {
                String modelKey = entry.getKey();
                JsonNode modelNode = entry.getValue();
                ModelRow row = modelRows.computeIfAbsent(modelKey, k -> new ModelRow());
                if (row.modelName == null || row.modelName.isBlank()) {
                    row.modelName = extractModelName(modelKey, modelNode);
                }
                row.femaleN = textOrEmpty(modelNode.get("female_n"));
                row.femalePct = textOrEmpty(modelNode.get("female_pct"));
                row.maleN = textOrEmpty(modelNode.get("male_n"));
                row.malePct = textOrEmpty(modelNode.get("male_pct"));
            });
        }
    }

    private void mergeModelAge(Map<String, ModelRow> modelRows, JsonNode root) {
        JsonNode node = extractAnswerNode(root, new String[]{"rci7_age"}, SECTION_KEY);
        if (node == null || node.isNull()) return;
        if (node.isObject()) {
            node.fields().forEachRemaining(entry -> {
                String modelKey = entry.getKey();
                JsonNode modelNode = entry.getValue();
                ModelRow row = modelRows.computeIfAbsent(modelKey, k -> new ModelRow());
                if (row.modelName == null || row.modelName.isBlank()) {
                    row.modelName = extractModelName(modelKey, modelNode);
                }
                row.ageMean = textOrEmpty(modelNode.get("mean"));
                row.ageSd = textOrEmpty(modelNode.get("sd"));
                row.ageMin = textOrEmpty(modelNode.get("min"));
                row.ageMax = textOrEmpty(modelNode.get("max"));
            });
        }
    }

    private String extractModelName(String modelKey, JsonNode modelNode) {
        if (modelNode != null && modelNode.isObject() && modelNode.has("model-name")) {
            String name = modelNode.get("model-name").asText("");
            if (!name.isBlank()) return name;
        }
        return modelKey == null ? "" : modelKey;
    }

    private JsonNode extractAnswerNode(JsonNode root, String[] keys, String sectionKey) {
        JsonNode node = null;
        for (String key : keys) {
            node = root.get(key);
            if (node != null) break;
        }
        if (node == null && sectionKey != null) {
            JsonNode section = root.get(sectionKey);
            if (section != null && section.isObject()) {
                for (String key : keys) {
                    node = section.get(key);
                    if (node != null) break;
                }
            }
        }
        if (node == null || node.isNull()) return null;
        if (node.isObject() && node.has("answer")) {
            return parseStringifiedJson(node.get("answer"));
        }
        return node;
    }

    private JsonNode parseStringifiedJson(JsonNode node) {
        if (node == null || !node.isTextual()) return node;
        String text = node.asText();
        if (text == null || text.isBlank()) return node;
        String trimmed = text.trim();
        if ((trimmed.startsWith("[") && trimmed.endsWith("]"))
                || (trimmed.startsWith("{") && trimmed.endsWith("}"))) {
            try {
                return mapper.readTree(text);
            } catch (IOException e) {
                return node;
            }
        }
        return node;
    }

    private String extractValue(JsonNode root, String[] keys, String sectionKey) {
        JsonNode node = null;
        for (String key : keys) {
            node = root.get(key);
            if (node != null) break;
        }
        if (node == null && sectionKey != null) {
            JsonNode section = root.get(sectionKey);
            if (section != null && section.isObject()) {
                for (String key : keys) {
                    node = section.get(key);
                    if (node != null) break;
                }
            }
        }
        if (node == null) return "";
        if (node.isObject() && node.has("answer")) {
            node = parseStringifiedJson(node.get("answer"));
        }
        if (node == null || node.isNull()) return "";
        if (node.isTextual()) return node.asText();
        return node.toString();
    }

    private String textOrEmpty(JsonNode node) {
        if (node == null || node.isNull()) return "";
        return node.asText(node.toString());
    }

    private String extractSource(String fileName) {
        Matcher m = SOURCE_PATTERN.matcher(fileName);
        if (m.find()) {
            return m.group(1).toLowerCase(Locale.ROOT);
        }
        return "unknown";
    }

    private void applySourceFilter() {
        if (sourceFilter == null) {
            filteredRows = new ArrayList<>(rows);
            return;
        }
        String selected = sourceFilter.getValue();
        if (selected == null || "all".equalsIgnoreCase(selected)) {
            filteredRows = new ArrayList<>(rows);
            return;
        }
        String needle = selected.toLowerCase(Locale.ROOT);
        filteredRows = rows.stream()
                .filter(r -> r.source != null && r.source.equalsIgnoreCase(needle))
                .collect(Collectors.toList());
    }

    private String getColumnValue(ModelRow row, String column) {
        if (row == null || column == null) return "";
        switch (column) {
            case "AuthorYear":
                return row.authorYear;
            case "ModelName":
                return row.modelName;
            case "Phase":
                return row.phase;
            case "n":
                return row.n;
            case "female_n":
                return row.femaleN;
            case "female_pct":
                return row.femalePct;
            case "male_n":
                return row.maleN;
            case "male_pct":
                return row.malePct;
            case "age_mean":
                return row.ageMean;
            case "age_sd":
                return row.ageSd;
            case "age_min":
                return row.ageMin;
            case "age_max":
                return row.ageMax;
            case "Note":
                return row.note;
            default:
                return "";
        }
    }

    private void rebuildTable() {
        scrollWrapper.getElement().removeAllChildren();

        Element table = new Element("table");
        table.setAttribute("style", "border-collapse: collapse; width: max-content; min-width: 100%; font-size: var(--lumo-font-size-m);");
        table.setAttribute("class", "summary-table");

        Element thead = new Element("thead");
        Element trHead = new Element("tr");
        appendFirstHeaderCell(trHead, "AuthorYear");
        appendHeaderCell(trHead, "ModelName");
        appendHeaderCell(trHead, "Phase");
        appendHeaderCell(trHead, "n");
        appendHeaderCell(trHead, "female_n");
        appendHeaderCell(trHead, "female_pct");
        appendHeaderCell(trHead, "male_n");
        appendHeaderCell(trHead, "male_pct");
        appendHeaderCell(trHead, "age_mean");
        appendHeaderCell(trHead, "age_sd");
        appendHeaderCell(trHead, "age_min");
        appendHeaderCell(trHead, "age_max");
        appendHeaderCell(trHead, "Note");
        thead.appendChild(trHead);
        table.appendChild(thead);

        Element tbody = new Element("tbody");
        boolean even = false;
        for (ModelRow row : filteredRows) {
            Element tr = new Element("tr");
            if (even) {
                tr.setAttribute("style", "background: var(--lumo-contrast-5pct);");
            }
            appendLauncherCell(tr, row.authorYear);
            appendCell(tr, row.modelName);
            appendCell(tr, row.phase);
            appendCell(tr, row.n);
            appendCell(tr, row.femaleN);
            appendCell(tr, row.femalePct);
            appendCell(tr, row.maleN);
            appendCell(tr, row.malePct);
            appendCell(tr, row.ageMean);
            appendCell(tr, row.ageSd);
            appendCell(tr, row.ageMin);
            appendCell(tr, row.ageMax);
            appendNoteCell(tr, row);
            tbody.appendChild(tr);
            even = !even;
        }
        table.appendChild(tbody);
        scrollWrapper.getElement().appendChild(table);
    }

    private void appendHeaderCell(Element tr, String text) {
        Element th = new Element("th");
        th.setAttribute("style", "position: sticky; top: 0; z-index: 1; text-align: center; background: var(--lumo-contrast-10pct); border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m); white-space: pre-line;");
        th.setText(text == null ? "" : text);
        tr.appendChild(th);
    }

    private void appendFirstHeaderCell(Element tr, String text) {
        Element th = new Element("th");
        th.setAttribute("style", "position: sticky; top: 0; left: 0; z-index: 2; text-align: center; background: var(--lumo-contrast-10pct); border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m); white-space: pre-line;");
        th.setText(text == null ? "" : text);
        tr.appendChild(th);
    }

    private void appendCell(Element tr, String text) {
        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-10pct); padding: var(--lumo-space-xs) var(--lumo-space-s); white-space: pre-wrap; word-break: break-word;");
        td.setText(text == null ? "" : text);
        tr.appendChild(td);
    }

    private void appendLauncherCell(Element tr, String authorYear) {
        Element td = new Element("td");
        td.setAttribute("style", "position: sticky; left: 0; z-index: 1; background: inherit; border-bottom: 1px solid var(--lumo-contrast-10pct); padding: var(--lumo-space-xs) var(--lumo-space-s);");

        Button launcher = new Button(normalizeNewlines(authorYear));
        launcher.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        launcher.addClickListener(e -> {
            try {
                String javaBin = Paths.get(System.getProperty("java.home"), "bin", "java").toString();
                String classpath = System.getProperty("java.class.path");
                Path sharePackageDir = resolveSharePackageDir();
                List<String> command = new ArrayList<>();
                command.add(javaBin);
                command.add("-Djava.awt.headless=false");
                command.add("-DLCCA_BASE_DIR=" + sharePackageDir.toAbsolutePath());
                command.add("-cp");
                command.add(classpath);
                command.add(Starter.class.getName());
                command.add(authorYear);
                command.add(VERSION_NAME);
                ProcessBuilder pb = new ProcessBuilder(command);
                pb.directory(sharePackageDir.toFile());
                pb.inheritIO();
                pb.start();
                Notification.show("起動要求を送信しました");
            } catch (Exception ex) {
                ex.printStackTrace();
                Notification.show("起動に失敗しました（ログを確認してください）");
            }
        });

        td.appendChild(launcher.getElement());
        tr.appendChild(td);
    }

    private void appendNoteCell(Element tr, ModelRow row) {
        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-10pct); padding: var(--lumo-space-xs) var(--lumo-space-s); min-width: 220px; vertical-align: top;");

        TextArea textArea = new TextArea();
        textArea.setValue(row.note != null ? row.note : "");
        textArea.setWidth("200px");
        textArea.getStyle().set("font-size", "var(--lumo-font-size-s)");

        Button saveButton = new Button("保存", e -> {
            String content = textArea.getValue();
            Path notePath = Paths.get(System.getProperty("user.dir"), DATA_FOLDER_NAME,
                    row.authorYear, "DE_v13", "note", "summary-view-5.txt");
            try {
                Files.createDirectories(notePath.getParent());
                Files.writeString(notePath, content);
                for (ModelRow r : rows) {
                    if (r.authorYear.equals(row.authorYear)) {
                        r.note = content;
                    }
                }
                showSaveMessage("保存しました → " + notePath.toAbsolutePath());
            } catch (IOException ex) {
                showSaveMessage("保存に失敗しました: " + ex.getMessage());
            }
        });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SMALL);

        td.appendChild(textArea.getElement());
        td.appendChild(saveButton.getElement());
        tr.appendChild(td);
    }

    private String loadNote(String authorYear) {
        Path notePath = Paths.get(System.getProperty("user.dir"), DATA_FOLDER_NAME,
                authorYear, "DE_v13", "note", "summary-view-5.txt");
        if (Files.exists(notePath)) {
            try {
                return Files.readString(notePath);
            } catch (IOException e) {
                return "";
            }
        }
        return "";
    }

    private void showSaveMessage(String message) {
        saveMessagePara.setText(message);
        saveMessagePara.setVisible(true);
        saveMessagePara.getElement().executeJs("setTimeout(() => { this.style.display = 'none'; }, 10000)");
    }

    private String normalizeNewlines(String text) {
        if (text == null) return "";
        return text.replace("\r\n", " ").replace("\r", " ").replace("\n", " ");
    }

    private Path resolveSharePackageDir() {
        return Paths.get(System.getProperty("user.dir"), "share_package").toAbsolutePath().normalize();
    }

    private static class ModelRow {
        String authorYear = "";
        String modelName = "";
        String phase = "";
        String source = "";
        String n = "";
        String femaleN = "";
        String femalePct = "";
        String maleN = "";
        String malePct = "";
        String ageMean = "";
        String ageSd = "";
        String ageMin = "";
        String ageMax = "";
        String note = "";
    }
}
