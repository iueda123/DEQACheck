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
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import iu.SpringBoot.Vaadin.views.DEQACheckAll.Utils.ExternalJarLauncher;
import iu.SpringBoot.Vaadin.views.MainView;

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

@PageTitle("Result Overview DE v13")
@Route("summary-view-4")
@StyleSheet("./styles/summary-table.css")
@RolesAllowed("ADMIN")
public class SummaryView_DEv13 extends VerticalLayout {

    private static final String DATA_FOLDER_NAME = "share_package/data";
    private static final String JAR_WORKING_DIR = "share_package";
    private static final String RSLT_COMPARATOR_JAR = "share_package/jar/RsltComparator-v20260107-V2.jar";
    private static final Pattern SOURCE_PATTERN = Pattern.compile("_by_([^_]+)_", Pattern.CASE_INSENSITIVE);
    private static final String[][] RCI_KEYS = {
            {"rci1_using_msad"},
            {"rci2_using_epd"},
            {"rci3_epd_name"},
            {"rci4_phase"},
            {"rci5_n"},
            {"rci6_sex"},
            {"rci7_age"}
    };

    private final ObjectMapper mapper = new ObjectMapper();
    private final List<SummaryRow> rows = new ArrayList<>();
    private List<SummaryRow> filteredRows = new ArrayList<>();
    private Div scrollWrapper;
    private ComboBox<String> sourceFilter;
    private ComboBox<String> columnCopySelect;

    public SummaryView_DEv13() {
        getStyle().set("padding", "var(--lumo-space-m)");
        setSpacing(true);
        setWidthFull();

        add(new H2("DE_v13 (human/gemini/claude/codex) 一覧"));

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
                mergeRow(authorYear, jsonFile);
            }
        }

        if (rows.isEmpty()) {
            add(new Paragraph("DE_v13 の JSON が見つかりません。"));
            return;
        }
        filteredRows = new ArrayList<>(rows);

        HorizontalLayout controlLayout = new HorizontalLayout();
        controlLayout.setAlignItems(Alignment.BASELINE);
        controlLayout.setSpacing(true);

        Button reloadButton = new Button("リロード", e -> UI.getCurrent().getPage().reload());
        controlLayout.add(reloadButton);

        sourceFilter = new ComboBox<>("Source Filter");
        sourceFilter.setItems("all", "human", "gemini", "claude", "codex");
        sourceFilter.setValue("human");
        sourceFilter.addValueChangeListener(e -> {
            applySourceFilter();
            rebuildTable();
        });
        controlLayout.add(sourceFilter);

        Button sortButton = new Button("SORT", e -> {
            rows.sort(Comparator
                    .comparing((SummaryRow r) -> r.authorYear.toLowerCase(Locale.ROOT))
                    .thenComparing(r -> r.source.toLowerCase(Locale.ROOT))
                    .thenComparing(r -> r.fileName.toLowerCase(Locale.ROOT)));
            applySourceFilter();
            rebuildTable();
        });
        sortButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        controlLayout.add(sortButton);

        columnCopySelect = new ComboBox<>("Column Copy");
        columnCopySelect.setItems(
                "AuthorYear",
                "RCI1", "RCI2", "RCI3", "RCI4", "RCI5", "RCI6", "RCI7"
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
            for (SummaryRow row : filteredRows) {
                values.add(normalizeNewlines(getColumnValue(row, column)));
            }
            String joined = String.join("\n", values);
            UI.getCurrent().getPage().executeJs("navigator.clipboard.writeText($0)", joined);
            Notification.show("コピーしました: " + column);
        });
        controlLayout.add(copyButton);

        add(controlLayout);

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

    private void mergeRow(String authorYear, Path jsonFile) {
        try {
            JsonNode root = mapper.readTree(jsonFile.toFile());
            String fileName = jsonFile.getFileName().toString();
            String source = extractSource(fileName);

            SummaryRow row = getOrCreateRow(authorYear, source);
            row.fileName = fileName;

            List<String> rciValues = new ArrayList<>();
            List<String> rawRciValues = new ArrayList<>();

            for (int i = 0; i < RCI_KEYS.length; i++) {
                String[] keys = RCI_KEYS[i];
                String key = keys[0];
                if ("rci5_n".equals(key)) {
                    rciValues.add(extractModelValue(root, keys, "reference_cohort_info_part", ModelValueType.N));
                } else if ("rci6_sex".equals(key)) {
                    rciValues.add(extractModelValue(root, keys, "reference_cohort_info_part", ModelValueType.SEX));
                } else if ("rci7_age".equals(key)) {
                    rciValues.add(extractModelValue(root, keys, "reference_cohort_info_part", ModelValueType.AGE));
                } else {
                    rciValues.add(extractValue(root, keys, "reference_cohort_info_part"));
                }
                rawRciValues.add(extractRawValue(root, keys, "reference_cohort_info_part"));
            }

            mergeValues(row.rciValues, rciValues, true);
            mergeValues(row.rawRciValues, rawRciValues, true);
        } catch (IOException e) {
            add(new Paragraph("JSON 読み取り失敗: " + jsonFile + " - " + e.getMessage()));
        }
    }

    private SummaryRow getOrCreateRow(String authorYear, String source) {
        for (SummaryRow row : rows) {
            if (row.authorYear.equals(authorYear) && row.source.equalsIgnoreCase(source)) {
                return row;
            }
        }
        SummaryRow row = new SummaryRow();
        row.authorYear = authorYear;
        row.source = source;
        row.rciValues = createEmptyValues(RCI_KEYS.length);
        row.rawRciValues = createEmptyValues(RCI_KEYS.length);
        rows.add(row);
        return row;
    }

    private List<String> createEmptyValues(int size) {
        List<String> values = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            values.add("");
        }
        return values;
    }

    private void mergeValues(List<String> target, List<String> source, boolean overwrite) {
        if (target == null || source == null) return;
        int size = Math.min(target.size(), source.size());
        for (int i = 0; i < size; i++) {
            String incoming = source.get(i);
            if (incoming == null || incoming.isBlank()) {
                continue;
            }
            if (overwrite || target.get(i) == null || target.get(i).isBlank()) {
                target.set(i, incoming);
            }
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
        if (node == null) {
            return "";
        }
        if (node.isObject() && node.has("answer")) {
            node = parseStringifiedJson(node.get("answer"));
        }
        if (node == null || node.isNull()) return "";
        if (node.isTextual()) return node.asText();
        return node.toString();
    }

    private String extractRawValue(JsonNode root, String[] keys, String sectionKey) {
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
        if (node == null || node.isNull()) return "";
        return node.toString();
    }

    private String extractKeywordsValue(JsonNode root, String[] keys, String sectionKey) {
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
        if (node == null || node.isNull()) return "";
        if (node.isObject() && node.has("answer")) {
            node = parseStringifiedJson(node.get("answer"));
        }
        if (node == null || node.isNull()) return "";
        if (node.isArray()) {
            List<String> parts = new ArrayList<>();
            for (JsonNode elem : node) {
                if (elem == null || elem.isNull()) continue;
                if (elem.isTextual()) {
                    parts.add(elem.asText());
                } else {
                    parts.add(elem.toString());
                }
            }
            return String.join(", ", parts);
        }
        if (node.isTextual()) return node.asText();
        return node.toString();
    }

    private String extractKeywordDescriptionValue(JsonNode root, String[] keys, String sectionKey) {
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
        if (node == null || node.isNull()) return "";
        if (node.isObject() && node.has("answer")) {
            node = parseStringifiedJson(node.get("answer"));
        }
        if (node == null || node.isNull()) return "";
        if (node.isArray()) {
            List<String> parts = new ArrayList<>();
            for (JsonNode elem : node) {
                if (elem == null || elem.isNull()) continue;
                if (elem.isObject()) {
                    if (elem.fields().hasNext()) {
                        var entry = elem.fields().next();
                        String key = entry.getKey();
                        String val = entry.getValue() != null ? entry.getValue().asText("") : "";
                        if (!key.isEmpty() && !val.isEmpty()) {
                            parts.add(key + " (" + val + ")");
                        } else if (!key.isEmpty()) {
                            parts.add(key);
                        } else if (!val.isEmpty()) {
                            parts.add(val);
                        }
                    }
                } else if (elem.isTextual()) {
                    parts.add(elem.asText());
                } else {
                    parts.add(elem.toString());
                }
            }
            return String.join(", ", parts);
        }
        if (node.isTextual()) return node.asText();
        return node.toString();
    }

    private String extractMinorCategoryList(JsonNode root, String[] keys, String sectionKey) {
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
        if (node == null || node.isNull()) return "";
        if (node.isObject() && node.has("answer")) {
            node = parseStringifiedJson(node.get("answer"));
        }
        if (node == null || node.isNull()) return "";
        if (node.isArray()) {
            List<String> parts = new ArrayList<>();
            for (JsonNode elem : node) {
                if (elem == null || elem.isNull()) continue;
                if (elem.isObject() && elem.has("minor-category")) {
                    String minor = elem.get("minor-category").asText("");
                    if (!minor.isBlank()) {
                        parts.add(minor);
                    }
                } else if (elem.isTextual()) {
                    parts.add(elem.asText());
                } else {
                    parts.add(elem.toString());
                }
            }
            return String.join("; ", parts);
        }
        if (node.isTextual()) return node.asText();
        return node.toString();
    }

    private String extractModelPhaseValue(JsonNode root, String[] keys, String sectionKey) {
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
        if (node == null || node.isNull()) return "";
        if (node.isObject() && node.has("answer")) {
            node = parseStringifiedJson(node.get("answer"));
        }
        if (node == null || node.isNull()) return "";
        if (node.isObject()) {
            List<String> lines = new ArrayList<>();
            node.fields().forEachRemaining(entry -> {
                JsonNode modelNode = entry.getValue();
                if (modelNode == null || modelNode.isNull()) return;
                String modelName = entry.getKey();
                if (modelNode.isObject() && modelNode.has("model-name")) {
                    modelName = modelNode.get("model-name").asText(modelName);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(modelName);
                List<String> parts = new ArrayList<>();
                if (modelNode.isObject()) {
                    String overall = formatPhase(modelNode.get("overall-phase"));
                    String train = formatPhase(modelNode.get("train-phase"));
                    if (!overall.isBlank()) {
                        parts.add("overall-phase: " + overall);
                    }
                    if (!train.isBlank()) {
                        parts.add("train-phase: " + train);
                    }
                }
                if (!parts.isEmpty()) {
                    sb.append(" (").append(String.join("; ", parts)).append(")");
                }
                lines.add(sb.toString());
            });
            return String.join("; ", lines);
        }
        if (node.isTextual()) return node.asText();
        return node.toString();
    }

    private String formatPhase(JsonNode phaseNode) {
        if (phaseNode == null || phaseNode.isNull()) return "";
        if (!phaseNode.isObject()) {
            return phaseNode.asText(phaseNode.toString());
        }
        if (phaseNode.has("female_n") || phaseNode.has("male_n")
                || phaseNode.has("female_pct") || phaseNode.has("male_pct")) {
            return formatSexPhase(phaseNode);
        }
        String[] orderedKeys = new String[]{
                "mean", "sd", "median", "iqr_l", "iqr_u", "min", "max"
        };
        List<String> parts = new ArrayList<>();
        for (String key : orderedKeys) {
            JsonNode v = phaseNode.get(key);
            if (v == null || v.isNull()) continue;
            String text = v.asText(v.toString());
            if (text.isBlank()) continue;
            parts.add(key + " " + text);
        }
        if (!parts.isEmpty()) {
            return String.join(", ", parts);
        }
        return phaseNode.toString();
    }

    private String formatSexPhase(JsonNode phaseNode) {
        String fN = textOrEmpty(phaseNode.get("female_n"));
        String fPct = textOrEmpty(phaseNode.get("female_pct"));
        String mN = textOrEmpty(phaseNode.get("male_n"));
        String mPct = textOrEmpty(phaseNode.get("male_pct"));

        String fPart = buildSexPart("F", fN, fPct);
        String mPart = buildSexPart("M", mN, mPct);

        List<String> parts = new ArrayList<>();
        if (!fPart.isBlank()) parts.add(fPart);
        if (!mPart.isBlank()) parts.add(mPart);
        if (!parts.isEmpty()) {
            return String.join(", ", parts);
        }
        return phaseNode.toString();
    }

    private String buildSexPart(String label, String n, String pct) {
        String nText = n == null ? "" : n.trim();
        String pText = pct == null ? "" : pct.trim();
        boolean hasN = !nText.isEmpty();
        boolean hasP = !pText.isEmpty();
        if (!hasN && !hasP) return "";
        if (!hasN) {
            return label + " (" + pText + ")";
        }
        if (!hasP) {
            return label + " " + nText + " (NR)";
        }
        return label + " " + nText + " (" + pText + ")";
    }

    private String textOrEmpty(JsonNode node) {
        if (node == null || node.isNull()) return "";
        return node.asText(node.toString());
    }

    private enum ModelValueType {
        N,
        SEX,
        AGE
    }

    private String extractModelValue(JsonNode root, String[] keys, String sectionKey, ModelValueType type) {
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
        if (node == null || node.isNull()) return "";
        if (node.isObject() && node.has("answer")) {
            node = parseStringifiedJson(node.get("answer"));
        }
        if (node == null || node.isNull()) return "";
        if (node.isObject()) {
            List<String> lines = new ArrayList<>();
            List<String> modelKeys = new ArrayList<>();
            node.fieldNames().forEachRemaining(modelKeys::add);
            modelKeys.sort(String::compareTo);
            for (String modelKey : modelKeys) {
                JsonNode modelNode = node.get(modelKey);
                if (modelNode == null || modelNode.isNull()) continue;
                String modelName = modelKey;
                if (modelNode.isObject() && modelNode.has("model-name")) {
                    modelName = modelNode.get("model-name").asText(modelName);
                }
                String detail = "";
                if (type == ModelValueType.N) {
                    detail = textOrEmpty(modelNode.get("n"));
                    if (!detail.isBlank()) {
                        detail = "n " + detail;
                    }
                } else if (type == ModelValueType.SEX) {
                    detail = formatSexPhase(modelNode);
                } else if (type == ModelValueType.AGE) {
                    detail = formatPhase(modelNode);
                }
                if (detail.isBlank()) {
                    lines.add(modelName);
                } else {
                    lines.add(modelName + " (" + detail + ")");
                }
            }
            return String.join("; ", lines);
        }
        if (node.isTextual()) return node.asText();
        return node.toString();
    }

    private String extractSource(String fileName) {
        Matcher m = SOURCE_PATTERN.matcher(fileName);
        if (m.find()) {
            return m.group(1).toLowerCase(Locale.ROOT);
        }
        return "unknown";
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
        appendHeaderCellWithTooltip(trHead, "RCI1",
                "reference_cohort_info_part/rci1_using_msad");
        appendHeaderCellWithTooltip(trHead, "RCI2",
                "reference_cohort_info_part/rci2_using_epd");
        appendHeaderCellWithTooltip(trHead, "RCI3",
                "reference_cohort_info_part/rci3_epd_name");
        appendHeaderCellWithTooltip(trHead, "RCI4",
                "reference_cohort_info_part/rci4_phase");
        appendHeaderCellWithTooltip(trHead, "RCI5",
                "reference_cohort_info_part/rci5_n");
        appendHeaderCellWithTooltip(trHead, "RCI6",
                "reference_cohort_info_part/rci6_sex");
        appendHeaderCellWithTooltip(trHead, "RCI7",
                "reference_cohort_info_part/rci7_age");
        thead.appendChild(trHead);
        table.appendChild(thead);

        Element tbody = new Element("tbody");
        boolean even = false;
        for (SummaryRow row : filteredRows) {
            Element tr = new Element("tr");
            if (even) {
                tr.setAttribute("style", "background: var(--lumo-contrast-5pct);");
            }
            appendLauncherCell(tr, row.authorYear);
            appendCell(tr, getValue(row.rciValues, 0), getValue(row.rawRciValues, 0));
            appendCell(tr, getValue(row.rciValues, 1), getValue(row.rawRciValues, 1));
            appendCell(tr, getValue(row.rciValues, 2), getValue(row.rawRciValues, 2));
            appendCell(tr, getValue(row.rciValues, 3), getValue(row.rawRciValues, 3));
            appendCell(tr, getValue(row.rciValues, 4), getValue(row.rawRciValues, 4));
            appendCell(tr, getValue(row.rciValues, 5), getValue(row.rawRciValues, 5));
            appendCell(tr, getValue(row.rciValues, 6), getValue(row.rawRciValues, 6));
            tbody.appendChild(tr);
            even = !even;
        }
        table.appendChild(tbody);
        scrollWrapper.getElement().appendChild(table);
    }

    private String getValue(List<String> list, int idx) {
        if (list == null || idx < 0 || idx >= list.size()) return "";
        return list.get(idx);
    }

    private String getColumnValue(SummaryRow row, String column) {
        if (row == null || column == null) return "";
        switch (column) {
            case "AuthorYear":
                return row.authorYear;
            case "RCI1":
                return getValue(row.rciValues, 0);
            case "RCI2":
                return getValue(row.rciValues, 1);
            case "RCI3":
                return getValue(row.rciValues, 2);
            case "RCI4":
                return getValue(row.rciValues, 3);
            case "RCI5":
                return getValue(row.rciValues, 4);
            case "RCI6":
                return getValue(row.rciValues, 5);
            case "RCI7":
                return getValue(row.rciValues, 6);
            default:
                return "";
        }
    }

    private void appendHeaderCell(Element tr, String text) {
        Element th = new Element("th");
        th.setAttribute("style", "position: sticky; top: 0; z-index: 1; text-align: center; background: var(--lumo-contrast-10pct); border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m); white-space: pre-line;");
        th.setText(text == null ? "" : text);
        tr.appendChild(th);
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
        String displayText = normalizeNewlines(text);
        td.setText(displayText);
        tr.appendChild(td);
    }

    private String normalizeNewlines(String text) {
        if (text == null) return "";
        return text.replace("\r\n", " ").replace("\r", " ").replace("\n", " ");
    }

    private void appendLauncherCell(Element tr, String authorYear) {
        Element td = new Element("td");
        td.setAttribute("style", "position: sticky; left: 0; z-index: 1; background: inherit; border-bottom: 1px solid var(--lumo-contrast-10pct); padding: var(--lumo-space-xs) var(--lumo-space-s);");

        Button launcher = new Button(normalizeNewlines(authorYear));
        launcher.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        launcher.addClickListener(e -> {
            boolean ok = ExternalJarLauncher.launch(RSLT_COMPARATOR_JAR, JAR_WORKING_DIR, authorYear, "DE_v13");
            if (ok) {
                Notification.show("起動要求を送信しました");
            } else {
                Notification.show("起動に失敗しました（ログを確認してください）");
            }
        });

        td.appendChild(launcher.getElement());
        tr.appendChild(td);
    }

    private static class SummaryRow {
        String authorYear;
        String source;
        String fileName;
        List<String> rciValues;
        List<String> rawRciValues;
    }
}
