package iu.SpringBoot.Vaadin.views.DEQACheckAll.DESummaryPage.SummaryView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import iu.SpringBoot.Vaadin.views.MainView;
import iu.SwingStyle.LCCA.Startup.RsltComparator_v14.Starter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import jakarta.annotation.security.RolesAllowed;

@PageTitle("Result Overview DE v14")
@Route("summary-view-6")
@StyleSheet("./styles/summary-table.css")
@RolesAllowed("ADMIN")
public class SummaryView_DEv14 extends VerticalLayout {

    private static final String DATA_FOLDER_NAME = "share_package/data";
    private static final Pattern SOURCE_PATTERN = Pattern.compile("_by_([^_]+)_", Pattern.CASE_INSENSITIVE);

    private final ObjectMapper mapper = new ObjectMapper();
    private final List<DisorderRow> rows = new ArrayList<>();
    private List<DisorderRow> filteredRows = new ArrayList<>();
    private Div scrollWrapper;
    private ComboBox<String> sourceFilter;
    private ComboBox<String> columnCopySelect;
    private Paragraph saveMessagePara;

    public SummaryView_DEv14() {
        getStyle().set("padding", "var(--lumo-space-m)");
        setSpacing(true);
        setWidthFull();

        add(new H2("DE_v14 (human/gemini/claude/codex/opus/sonnet) 一覧"));

        Path base = Paths.get(System.getProperty("user.dir"), DATA_FOLDER_NAME);
        if (!Files.exists(base) || !Files.isDirectory(base)) {
            add(new Paragraph("Data ディレクトリが見つかりません: " + base.toAbsolutePath()));
            return;
        }

        List<Path> authorYearDirs;
        try {
            authorYearDirs = Files.list(base)
                    .filter(Files::isDirectory)
                    .filter(p -> {
                        String name = p.getFileName().toString();
                        return !name.equalsIgnoreCase("Someone20XX");
                    })
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
            Path jsonDir = authorYearDir.resolve("DE_v14").resolve("json");

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
                                    && name.contains("de_v14_")
                                    && (name.contains("human") || name.contains("gemini")
                                    || name.contains("claude") || name.contains("codex")
                                    || name.contains("opus") || name.contains("sonnet"));
                        })
                        .sorted(Comparator.comparing(p -> p.getFileName().toString().toLowerCase(Locale.ROOT)))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                add(new Paragraph(authorYear + ": JSON ファイルの取得でエラー - " + e.getMessage()));
                continue;
            }

            for (Path jsonFile : jsonFiles) {
                parseFile(authorYear, jsonFile);
            }
        }

        if (rows.isEmpty()) {
            add(new Paragraph("DE_v14 の JSON が見つかりません。"));
            return;
        }
        filteredRows = new ArrayList<>(rows);

        HorizontalLayout controlLayout = new HorizontalLayout();
        controlLayout.setAlignItems(Alignment.BASELINE);
        controlLayout.setSpacing(true);

        Button reloadButton = new Button("リロード", e -> UI.getCurrent().getPage().reload());
        controlLayout.add(reloadButton);

        sourceFilter = new ComboBox<>("Source Filter");
        sourceFilter.setItems("all", "human", "gemini", "claude", "codex", "opus", "sonnet");
        sourceFilter.setValue("human");
        sourceFilter.addValueChangeListener(e -> {
            applySourceFilter();
            rebuildTable();
        });
        controlLayout.add(sourceFilter);

        Button sortButton = new Button("SORT", e -> {
            rows.sort(Comparator
                    .comparing((DisorderRow r) -> r.authorYear.toLowerCase(Locale.ROOT))
                    .thenComparing(r -> r.source.toLowerCase(Locale.ROOT))
                    .thenComparing(r -> r.disorderName.toLowerCase(Locale.ROOT)));
            applySourceFilter();
            rebuildTable();
        });
        sortButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        controlLayout.add(sortButton);

        columnCopySelect = new ComboBox<>("Column Copy");
        columnCopySelect.setItems(
                "AuthorYear", "Source", "Disorder", "Dataset",
                "N", "female_n", "female_pct", "male_n", "male_pct",
                "age_mean", "age_sd", "age_min", "age_max"
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
            for (DisorderRow row : filteredRows) {
                values.add(normalizeNewlines(getColumnValue(row, column)));
            }
            String joined = String.join("\n", values);
            UI.getCurrent().getPage().executeJs("navigator.clipboard.writeText($0)", joined);
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

    private void parseFile(String authorYear, Path jsonFile) {
        try {
            JsonNode root = mapper.readTree(jsonFile.toFile());
            String source = extractSource(jsonFile.getFileName().toString());
            String note = loadNote(authorYear);

            JsonNode disorders = root.get("disorders");
            if (disorders == null || !disorders.isArray()) {
                return;
            }

            for (JsonNode disorder : disorders) {
                if (disorder == null || disorder.isNull() || !disorder.isObject()) {
                    continue;
                }
                if (!disorder.fields().hasNext()) {
                    continue; // skip empty objects
                }

                DisorderRow row = new DisorderRow();
                row.authorYear = authorYear;
                row.source = source;

                // disorder-name
                JsonNode nameNode = disorder.get("disorder-name");
                if (nameNode != null) {
                    if (nameNode.isTextual()) {
                        row.disorderName = nameNode.asText();
                    } else if (nameNode.isObject() && nameNode.has("answer")) {
                        row.disorderName = textOrEmpty(nameNode.get("answer"));
                    }
                }

                // dataset-of-origin
                JsonNode datasetNode = disorder.get("dataset-of-origin");
                if (datasetNode != null) {
                    if (datasetNode.isTextual()) {
                        datasetNode = parseStringifiedJson(datasetNode);
                    }
                    if (datasetNode.isObject() && datasetNode.has("answer")) {
                        datasetNode = parseStringifiedJson(datasetNode.get("answer"));
                    }
                    if (datasetNode != null && datasetNode.isArray()) {
                        List<String> parts = new ArrayList<>();
                        for (JsonNode elem : datasetNode) {
                            if (elem != null && !elem.isNull()) {
                                parts.add(elem.isTextual() ? elem.asText() : elem.toString());
                            }
                        }
                        row.datasets = String.join(", ", parts);
                    } else if (datasetNode != null) {
                        row.datasets = datasetNode.asText();
                    }
                }

                // sex
                extractSex(disorder, row);

                // age
                extractAge(disorder, row);

                row.note = note;
                rows.add(row);
            }
        } catch (IOException e) {
            add(new Paragraph("JSON 読み取り失敗: " + jsonFile + " - " + e.getMessage()));
        }
    }

    private void extractSex(JsonNode disorder, DisorderRow row) {
        JsonNode sexNode = disorder.get("sex");
        if (sexNode == null || sexNode.isNull()) return;

        // handle plain string (possibly stringified JSON)
        if (sexNode.isTextual()) {
            sexNode = parseStringifiedJson(sexNode);
        }

        // handle {"answer": ...}
        if (sexNode != null && sexNode.isObject() && sexNode.has("answer")) {
            sexNode = parseStringifiedJson(sexNode.get("answer"));
        }

        if (sexNode == null || sexNode.isNull() || !sexNode.isObject()) return;

        row.femaleN = textOrEmpty(sexNode.get("female_n"));
        row.femalePct = textOrEmpty(sexNode.get("female_pct"));
        row.maleN = textOrEmpty(sexNode.get("male_n"));
        row.malePct = textOrEmpty(sexNode.get("male_pct"));
        row.n = computeN(row.femaleN, row.maleN);
    }

    private void extractAge(JsonNode disorder, DisorderRow row) {
        JsonNode ageNode = disorder.get("age");
        if (ageNode == null || ageNode.isNull()) return;

        if (ageNode.isTextual()) {
            ageNode = parseStringifiedJson(ageNode);
        }

        if (ageNode.isObject() && ageNode.has("answer")) {
            ageNode = parseStringifiedJson(ageNode.get("answer"));
        }

        if (ageNode == null || ageNode.isNull()) return;

        if (ageNode.isTextual()) {
            // "NR" or similar plain-string case
            String text = ageNode.asText();
            row.ageMean = text;
            row.ageSd = text;
            row.ageMin = text;
            row.ageMax = text;
        } else if (ageNode.isObject()) {
            row.ageMean = textOrEmpty(ageNode.get("mean"));
            row.ageSd = textOrEmpty(ageNode.get("sd"));
            row.ageMin = textOrEmpty(ageNode.get("min"));
            row.ageMax = textOrEmpty(ageNode.get("max"));
        }
    }

    private String computeN(String femaleN, String maleN) {
        if (femaleN == null || femaleN.isBlank() || maleN == null || maleN.isBlank()) return "";
        try {
            long f = Long.parseLong(femaleN.trim());
            long m = Long.parseLong(maleN.trim());
            return String.valueOf(f + m);
        } catch (NumberFormatException e) {
            return "";
        }
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

    private String extractSource(String fileName) {
        Matcher m = SOURCE_PATTERN.matcher(fileName);
        if (m.find()) {
            return m.group(1).toLowerCase(Locale.ROOT);
        }
        return "unknown";
    }

    private String textOrEmpty(JsonNode node) {
        if (node == null || node.isNull()) return "";
        return node.asText(node.toString());
    }

    private String normalizeNewlines(String text) {
        if (text == null) return "";
        return text.replace("\r\n", " ").replace("\r", " ").replace("\n", " ");
    }

    private String getColumnValue(DisorderRow row, String column) {
        if (row == null || column == null) return "";
        switch (column) {
            case "AuthorYear":  return row.authorYear;
            case "Source":      return row.source;
            case "Disorder":    return row.disorderName;
            case "Dataset":     return row.datasets;
            case "N":           return row.n;
            case "female_n":    return row.femaleN;
            case "female_pct":  return row.femalePct;
            case "male_n":      return row.maleN;
            case "male_pct":    return row.malePct;
            case "age_mean":    return row.ageMean;
            case "age_sd":      return row.ageSd;
            case "age_min":     return row.ageMin;
            case "age_max":     return row.ageMax;
            default:            return "";
        }
    }

    private void rebuildTable() {
        scrollWrapper.getElement().removeAllChildren();

        Element table = new Element("table");
        table.setAttribute("style", "border-collapse: collapse; width: max-content; min-width: 100%; font-size: var(--lumo-font-size-m);");
        table.setAttribute("class", "summary-table");

        Element thead = new Element("thead");
        Element trHead = new Element("tr");
        appendFirstHeaderCellWithTooltip(trHead, "AuthorYear",
                "JSON filename-derived author year");
        appendHeaderCellWithTooltip(trHead, "Source",
                "AI source (human/gemini/claude/codex/opus/sonnet)");
        appendHeaderCellWithTooltip(trHead, "Disorder",
                "disorders[]/disorder-name/answer");
        appendHeaderCellWithTooltip(trHead, "Dataset",
                "disorders[]/dataset-of-origin/answer");
        appendHeaderCellWithTooltip(trHead, "N",
                "female_n + male_n");
        appendHeaderCellWithTooltip(trHead, "female_n",
                "disorders[]/sex/answer/female_n");
        appendHeaderCellWithTooltip(trHead, "female_pct",
                "disorders[]/sex/answer/female_pct");
        appendHeaderCellWithTooltip(trHead, "male_n",
                "disorders[]/sex/answer/male_n");
        appendHeaderCellWithTooltip(trHead, "male_pct",
                "disorders[]/sex/answer/male_pct");
        appendHeaderCellWithTooltip(trHead, "age_mean",
                "disorders[]/age/answer/mean");
        appendHeaderCellWithTooltip(trHead, "age_sd",
                "disorders[]/age/answer/sd");
        appendHeaderCellWithTooltip(trHead, "age_min",
                "disorders[]/age/answer/min");
        appendHeaderCellWithTooltip(trHead, "age_max",
                "disorders[]/age/answer/max");
        appendHeaderCellWithTooltip(trHead, "Note",
                "Note");
        thead.appendChild(trHead);
        table.appendChild(thead);

        Element tbody = new Element("tbody");
        boolean even = false;
        for (DisorderRow row : filteredRows) {
            Element tr = new Element("tr");
            if (even) {
                tr.setAttribute("style", "background: var(--lumo-contrast-5pct);");
            }
            appendLauncherCell(tr, row.authorYear);
            appendCell(tr, row.source, "");
            appendCell(tr, row.disorderName, "");
            appendCell(tr, row.datasets, "");
            appendCell(tr, row.n, "");
            appendCell(tr, row.femaleN, "");
            appendCell(tr, row.femalePct, "");
            appendCell(tr, row.maleN, "");
            appendCell(tr, row.malePct, "");
            appendCell(tr, row.ageMean, "");
            appendCell(tr, row.ageSd, "");
            appendCell(tr, row.ageMin, "");
            appendCell(tr, row.ageMax, "");
            appendNoteCell(tr, row);
            tbody.appendChild(tr);
            even = !even;
        }
        table.appendChild(tbody);
        scrollWrapper.getElement().appendChild(table);
    }

    private void appendHeaderCellWithTooltip(Element tr, String text, String tooltip) {
        Element th = new Element("th");
        th.setAttribute("style", "position: sticky; top: 0; z-index: 1; text-align: center; background: var(--lumo-contrast-10pct); border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m); white-space: pre-line;");
        if (tooltip != null && !tooltip.isBlank()) {
            th.setAttribute("title", tooltip);
        }
        th.setText(text == null ? "" : text);
        tr.appendChild(th);
    }

    private void appendFirstHeaderCellWithTooltip(Element tr, String text, String tooltip) {
        Element th = new Element("th");
        th.setAttribute("style", "position: sticky; top: 0; left: 0; z-index: 2; text-align: center; background: var(--lumo-contrast-10pct); border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m); white-space: pre-line;");
        if (tooltip != null && !tooltip.isBlank()) {
            th.setAttribute("title", tooltip);
        }
        th.setText(text == null ? "" : text);
        tr.appendChild(th);
    }

    private void appendCell(Element tr, String text, String rawJson) {
        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-10pct); padding: var(--lumo-space-xs) var(--lumo-space-s); white-space: pre-wrap; word-break: break-word;");
        if (rawJson != null && !rawJson.isBlank()) {
            td.setAttribute("title", rawJson);
        }
        td.setText(normalizeNewlines(text));
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
                List<String> command = new ArrayList<>();
                command.add(javaBin);
                command.add("-Djava.awt.headless=false");
                command.add("-cp");
                command.add(classpath);
                command.add(Starter.class.getName());
                command.add(authorYear);
                ProcessBuilder pb = new ProcessBuilder(command);
                pb.directory(Paths.get(System.getProperty("user.dir")).toFile());
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

    private void appendNoteCell(Element tr, DisorderRow row) {
        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-10pct); padding: var(--lumo-space-xs) var(--lumo-space-s); min-width: 220px; vertical-align: top;");

        TextArea textArea = new TextArea();
        textArea.setValue(row.note != null ? row.note : "");
        textArea.setWidth("200px");
        textArea.getStyle().set("font-size", "var(--lumo-font-size-s)");

        Button saveButton = new Button("保存", e -> {
            String content = textArea.getValue();
            Path notePath = Paths.get(System.getProperty("user.dir"), DATA_FOLDER_NAME,
                    row.authorYear, "DE_v14", "note", "summary-view-6.txt");
            try {
                Files.createDirectories(notePath.getParent());
                Files.writeString(notePath, content);
                for (DisorderRow r : rows) {
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
                authorYear, "DE_v14", "note", "summary-view-6.txt");
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
        saveMessagePara.getStyle().remove("display");
        saveMessagePara.setVisible(true);
        saveMessagePara.getElement().executeJs("setTimeout(() => { this.style.display = 'none'; }, 10000)");
    }

    private static class DisorderRow {
        String authorYear = "";
        String source = "";
        String disorderName = "";
        String datasets = "";
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
