package iu.SpringBoot.Vaadin.views.DEQACheckAll.QAResultPage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.StreamResource;
import iu.SpringBoot.Vaadin.views.MainView;
import jakarta.annotation.security.RolesAllowed;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * AuthorYear と PromptName を選択すると、
 * すべての Reviewer の QA 結果を一覧テーブルで表示するページ。
 * 行: QA項目、列: Reviewer
 * <p>
 * ダウンロード機能:
 * - JSONファイル一式をZIPで提供: <AuthorYear>_<PromptName>_JSONS.zip
 * - サマリーテーブルをTSVで提供: <AuthorYear>_<PromptName>_Summary.tsv
 * - 保存先: ./tmp/QA_Results/
 */
@Route("qa-result-table")
@PageTitle("QA Result Comparison (per Reviewer)")
@RolesAllowed({"USER", "GUEST"})
public class QAResultTablePage extends VerticalLayout {

    private static final String DATA_PATH = "share_package/data";
    private static final Path DOWNLOAD_DIR = Paths.get("tmp/QA_Results");

    // QA_v9 の項目リスト
    private static final String[] QA_V9_ITEMS = {
            "cm1_research_objectives",
            "nm1_selection_criteria_reference_cohort",
            "nm2_handling_of_covariates_reference_cohort",
            "nm3_data_sources_reference_cohort",
            "nm4_image_acquisition_protocol",
            "nm5_data_preprocessing",
            "nm6_internal_data_validation_reference_cohort",
            "nm7_external_data_validation_reference_cohort",
            "nm8_normative_modeling_approach",
            "nm9_model_performance_reference_cohort",
            "nm10_characteristics_reference_cohort_each_partition",
            "nm11_reproducibility",
            "cr1_selection_criteria_clinical_cohort",
            "cr2_handling_of_clinical_covariates",
            "cr3_data_sources_clinical_cohort",
            "cr4_clinical_characteristics_clinical_cohort",
            "cr5_clinical_assessment_measures",
            "cr6_interpretation_of_deviation_scores"
    };

    // 項目の短縮名
    private static final String[] QA_V9_SHORT_NAMES = {
            "CM1", "NM1", "NM2", "NM3", "NM4", "NM5", "NM6", "NM7", "NM8", "NM9", "NM10", "NM11",
            "CR1", "CR2", "CR3", "CR4", "CR5", "CR6"
    };

    private ComboBox<String> authorYearCombo;
    private ComboBox<String> promptNameCombo;
    private Div resultContainer;
    private Button downloadButton;
    private Div downloadLinksContainer;

    // 現在表示中のデータを保持
    private List<ReviewerData> currentDataList;
    private List<Path> currentJsonFiles;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public QAResultTablePage() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        add(new H2("QA Result Comparison (All Reviewers)"));

        RouterLink backLink = new RouterLink("< Back to Main", MainView.class);
        add(backLink);

        createDropdowns();

        resultContainer = new Div();
        resultContainer.setWidthFull();
        resultContainer.getStyle()
                .set("overflow-y", "auto")
                .set("overflow-x", "auto")
                .set("margin-top", "20px")
                .set("padding", "10px")
                .set("border", "1px solid #e0e0e0")
                .set("border-radius", "5px")
                .set("background-color", "#fafafa")
                .set("flex-grow", "1");
        add(resultContainer);

        setFlexGrow(1, resultContainer);
    }

    private void createDropdowns() {
        // AuthorYear dropdown
        authorYearCombo = new ComboBox<>("AuthorYear");
        authorYearCombo.setWidth("200px");
        authorYearCombo.setItems(getAuthorYearList());
        authorYearCombo.addValueChangeListener(e -> {
            if (e.getValue() != null) {
                updatePromptNameOptions();
            }
        });

        // PromptName dropdown
        promptNameCombo = new ComboBox<>("PromptName");
        promptNameCombo.setWidth("150px");
        promptNameCombo.addValueChangeListener(e -> updateDisplay());

        // Reload button
        Button reloadButton = new Button("Reload", e -> {
            authorYearCombo.setItems(getAuthorYearList());
            updatePromptNameOptions();
            updateDisplay();
        });

        // Download button
        downloadButton = new Button("Download", e -> prepareDownload());
        downloadButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        downloadButton.setEnabled(false);

        // Download links container
        downloadLinksContainer = new Div();
        downloadLinksContainer.getStyle()
                .set("display", "flex")
                .set("gap", "15px")
                .set("align-items", "center")
                .set("flex-wrap", "wrap");

        HorizontalLayout dropdownLayout = new HorizontalLayout(
                authorYearCombo, promptNameCombo, reloadButton, downloadButton
        );
        dropdownLayout.setAlignItems(Alignment.END);
        add(dropdownLayout);
        add(downloadLinksContainer);
    }

    private List<String> getAuthorYearList() {
        List<String> result = new ArrayList<>();
        Path dataDir = Paths.get(DATA_PATH);

        if (Files.exists(dataDir) && Files.isDirectory(dataDir)) {
            try (Stream<Path> stream = Files.list(dataDir)) {
                result = stream
                        .filter(Files::isDirectory)
                        .map(p -> p.getFileName().toString())
                        .filter(name -> !name.isEmpty() && Character.isUpperCase(name.charAt(0)))
                        .sorted()
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private void updatePromptNameOptions() {
        String authorYear = authorYearCombo.getValue();
        if (authorYear == null) return;

        List<String> promptNames = new ArrayList<>();
        Path authorDir = Paths.get(DATA_PATH, authorYear);

        if (Files.exists(authorDir) && Files.isDirectory(authorDir)) {
            try (Stream<Path> stream = Files.list(authorDir)) {
                promptNames = stream
                        .filter(Files::isDirectory)
                        .map(p -> p.getFileName().toString())
                        .filter(name -> name.startsWith("QA"))
                        .sorted()
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        promptNameCombo.setItems(promptNames);
        if (!promptNames.isEmpty()) {
            if (promptNames.contains("QA_v9")) {
                promptNameCombo.setValue("QA_v9");
            } else if (promptNames.contains("QA")) {
                promptNameCombo.setValue("QA");
            } else {
                promptNameCombo.setValue(promptNames.get(0));
            }
        }
    }

    private void updateDisplay() {
        resultContainer.removeAll();
        downloadLinksContainer.removeAll();
        downloadButton.setEnabled(false);
        currentDataList = null;
        currentJsonFiles = null;

        String authorYear = authorYearCombo.getValue();
        String promptName = promptNameCombo.getValue();

        if (authorYear == null || promptName == null) {
            Div placeholder = new Div("AuthorYear と PromptName を選択してください。");
            placeholder.getStyle()
                    .set("color", "#666")
                    .set("font-style", "italic")
                    .set("padding", "20px");
            resultContainer.add(placeholder);
            return;
        }

        // すべてのReviewerのデータを収集
        CollectedData collected = collectAllReviewerDataWithFiles(authorYear, promptName);
        currentDataList = collected.dataList;
        currentJsonFiles = collected.jsonFiles;

        if (currentDataList.isEmpty()) {
            Div notFoundDiv = new Div("該当するJSONファイルが見つかりません。");
            notFoundDiv.getStyle().set("color", "#ffc107").set("padding", "20px");
            resultContainer.add(notFoundDiv);
            return;
        }

        // テーブル作成
        resultContainer.add(createComparisonTable(currentDataList, authorYear));

        // ダウンロードボタンを有効化
        downloadButton.setEnabled(true);
    }

    private CollectedData collectAllReviewerDataWithFiles(String authorYear, String promptName) {
        List<ReviewerData> dataList = new ArrayList<>();
        List<Path> jsonFiles = new ArrayList<>();
        Path jsonDir = Paths.get(DATA_PATH, authorYear, promptName, "json");

        if (!Files.exists(jsonDir) || !Files.isDirectory(jsonDir)) {
            return new CollectedData(dataList, jsonFiles);
        }

        try (Stream<Path> stream = Files.list(jsonDir)) {
            List<Path> files = stream
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().endsWith(".json"))
                    .sorted(Comparator.comparing(p -> p.getFileName().toString()))
                    .collect(Collectors.toList());

            for (Path jsonFile : files) {
                String fileName = jsonFile.getFileName().toString();
                String reviewerName = extractReviewerName(fileName);

                if (reviewerName != null && !reviewerName.isEmpty()) {
                    JsonNode root = objectMapper.readTree(jsonFile.toFile());
                    ReviewerData data = new ReviewerData(reviewerName, fileName);
                    extractAnswers(root, data);
                    dataList.add(data);
                    jsonFiles.add(jsonFile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new CollectedData(dataList, jsonFiles);
    }

    private String extractReviewerName(String fileName) {
        String lowerName = fileName.toLowerCase();
        int byIndex = lowerName.indexOf("_by_");
        if (byIndex >= 0) {
            String afterBy = fileName.substring(byIndex + 4);
            int nextUnderscore = afterBy.indexOf("_");
            if (nextUnderscore > 0) {
                return afterBy.substring(0, nextUnderscore);
            }
        }
        return null;
    }

    private void extractAnswers(JsonNode root, ReviewerData data) {
        String[] parts = {"common_part", "normative_modeling_part", "clinical_research_part"};

        for (String partName : parts) {
            if (root.has(partName)) {
                JsonNode part = root.get(partName);
                Iterator<Map.Entry<String, JsonNode>> items = part.fields();
                while (items.hasNext()) {
                    Map.Entry<String, JsonNode> item = items.next();
                    String itemKey = item.getKey();
                    JsonNode itemData = item.getValue();

                    if (itemData.isObject() && itemData.has("answer")) {
                        String answer = itemData.get("answer").asText("");
                        data.answers.put(itemKey, answer);
                    }
                }
            }
        }
    }

    private void prepareDownload() {
        String authorYear = authorYearCombo.getValue();
        String promptName = promptNameCombo.getValue();

        if (authorYear == null || promptName == null || currentDataList == null || currentDataList.isEmpty()) {
            Notification.show("ダウンロードするデータがありません。", 3000, Notification.Position.BOTTOM_START);
            return;
        }

        try {
            // ダウンロードディレクトリを作成
            Files.createDirectories(DOWNLOAD_DIR);

            String baseName = authorYear + "_" + promptName;

            // ZIPファイル生成
            byte[] zipBytes = createJsonZip(currentJsonFiles, baseName);
            String zipFileName = baseName + "_JSONS.zip";
            Path zipPath = DOWNLOAD_DIR.resolve(zipFileName);
            Files.write(zipPath, zipBytes);

            // TSVファイル生成
            byte[] tsvBytes = createSummaryTsv(currentDataList, authorYear, promptName);
            String tsvFileName = baseName + "_Summary.tsv";
            Path tsvPath = DOWNLOAD_DIR.resolve(tsvFileName);
            Files.write(tsvPath, tsvBytes);

            // ダウンロードリンクを表示
            downloadLinksContainer.removeAll();

            Paragraph statusLabel = new Paragraph("ダウンロードリンク:");
            statusLabel.getStyle().set("margin", "0").set("font-weight", "bold");
            downloadLinksContainer.add(statusLabel);

            // ZIP download link
            StreamResource zipResource = new StreamResource(zipFileName, () -> new ByteArrayInputStream(zipBytes));
            zipResource.setContentType("application/zip");
            Anchor zipAnchor = new Anchor(zipResource, zipFileName);
            zipAnchor.getElement().setAttribute("download", true);
            zipAnchor.getStyle()
                    .set("color", "#1976d2")
                    .set("text-decoration", "none")
                    .set("padding", "4px 8px")
                    .set("background-color", "#e3f2fd")
                    .set("border-radius", "4px");
            downloadLinksContainer.add(zipAnchor);

            // TSV download link
            StreamResource tsvResource = new StreamResource(tsvFileName, () -> new ByteArrayInputStream(tsvBytes));
            tsvResource.setContentType("text/tab-separated-values; charset=utf-8");
            Anchor tsvAnchor = new Anchor(tsvResource, tsvFileName);
            tsvAnchor.getElement().setAttribute("download", true);
            tsvAnchor.getStyle()
                    .set("color", "#1976d2")
                    .set("text-decoration", "none")
                    .set("padding", "4px 8px")
                    .set("background-color", "#e3f2fd")
                    .set("border-radius", "4px");
            downloadLinksContainer.add(tsvAnchor);

            Notification.show("ダウンロードファイルを生成しました。", 3000, Notification.Position.BOTTOM_START);

        } catch (IOException e) {
            Notification.show("ファイル生成に失敗しました: " + e.getMessage(), 5000, Notification.Position.BOTTOM_START);
            e.printStackTrace();
        }
    }

    /**
     * JSONファイル一式をZIP圧縮する。
     * ./tmp/QA_Results/<baseName>_JSONS.zip に保存される。
     */
    private byte[] createJsonZip(List<Path> jsonFiles, String baseName) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (ZipOutputStream zos = new ZipOutputStream(baos)) {
            String folderName = baseName + "_JSONS";

            for (Path jsonFile : jsonFiles) {
                String entryName = folderName + "/" + jsonFile.getFileName().toString();
                ZipEntry zipEntry = new ZipEntry(entryName);
                zos.putNextEntry(zipEntry);
                Files.copy(jsonFile, zos);
                zos.closeEntry();
            }
        }

        return baos.toByteArray();
    }

    /**
     * サマリーテーブルをTSV形式で生成する。
     * ./tmp/QA_Results/<baseName>_Summary.tsv に保存される。
     */
    private byte[] createSummaryTsv(List<ReviewerData> dataList, String authorYear, String promptName) {
        StringBuilder sb = new StringBuilder();

        // ヘッダー行: AuthorYear, PromptName情報
        sb.append("# AuthorYear: ").append(authorYear).append("\n");
        sb.append("# PromptName: ").append(promptName).append("\n");
        sb.append("\n");

        // テーブルヘッダー
        sb.append("Item");
        for (ReviewerData data : dataList) {
            sb.append("\t").append(data.reviewerName);
        }
        sb.append("\n");

        // データ行
        for (int i = 0; i < QA_V9_ITEMS.length; i++) {
            String itemKey = QA_V9_ITEMS[i];
            String shortName = QA_V9_SHORT_NAMES[i];

            sb.append(shortName);
            for (ReviewerData data : dataList) {
                String answer = data.answers.getOrDefault(itemKey, "");
                sb.append("\t").append(getAnswerShort(answer));
            }
            sb.append("\n");
        }

        // サマリー行
        sb.append("\n");
        sb.append("# Summary per Reviewer\n");
        sb.append("Reviewer\tYes\tNo\tPartial\tNA\n");

        for (ReviewerData data : dataList) {
            int yesCount = 0, noCount = 0, partialCount = 0, naCount = 0;

            for (String itemKey : QA_V9_ITEMS) {
                String answer = data.answers.getOrDefault(itemKey, "").toLowerCase();
                switch (answer) {
                    case "yes":
                        yesCount++;
                        break;
                    case "no":
                        noCount++;
                        break;
                    case "partial":
                        partialCount++;
                        break;
                    case "na":
                        naCount++;
                        break;
                }
            }

            sb.append(data.reviewerName)
                    .append("\t").append(yesCount)
                    .append("\t").append(noCount)
                    .append("\t").append(partialCount)
                    .append("\t").append(naCount)
                    .append("\n");
        }

        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    private Div createComparisonTable(List<ReviewerData> dataList, String authorYear) {
        Div container = new Div();
        container.getStyle().set("overflow-x", "auto");

        // タイトル
        Div titleDiv = new Div("AuthorYear: " + authorYear);
        titleDiv.getStyle()
                .set("font-weight", "bold")
                .set("font-size", "16px")
                .set("margin-bottom", "10px");
        container.add(titleDiv);

        Element table = new Element("table");
        table.getStyle()
                .set("border-collapse", "collapse")
                .set("font-size", "12px")
                .set("background-color", "white");

        // ヘッダー行
        Element thead = new Element("thead");
        Element headerRow = new Element("tr");

        // 項目名列
        Element thItem = new Element("th");
        thItem.setText("Item");
        thItem.getStyle()
                .set("border", "1px solid #ddd")
                .set("padding", "8px")
                .set("background-color", "#1565c0")
                .set("color", "white")
                .set("position", "sticky")
                .set("left", "0")
                .set("z-index", "2")
                .set("min-width", "80px");
        headerRow.appendChild(thItem);

        // 各Reviewerの列ヘッダー
        for (ReviewerData data : dataList) {
            Element th = new Element("th");
            th.setText(data.reviewerName);
            th.setAttribute("title", data.fileName);
            th.getStyle()
                    .set("border", "1px solid #ddd")
                    .set("padding", "6px")
                    .set("background-color", "#1565c0")
                    .set("color", "white")
                    .set("min-width", "60px")
                    .set("text-align", "center")
                    .set("cursor", "help");
            headerRow.appendChild(th);
        }

        thead.appendChild(headerRow);
        table.appendChild(thead);

        // データ行
        Element tbody = new Element("tbody");
        int rowIndex = 0;

        for (int i = 0; i < QA_V9_ITEMS.length; i++) {
            String itemKey = QA_V9_ITEMS[i];
            String shortName = QA_V9_SHORT_NAMES[i];

            Element tr = new Element("tr");
            String rowBgColor = (rowIndex % 2 == 0) ? "#ffffff" : "#f8f9fa";

            // 項目名セル
            Element tdItem = new Element("td");
            tdItem.setText(shortName);
            tdItem.setAttribute("title", itemKey);
            tdItem.getStyle()
                    .set("border", "1px solid #ddd")
                    .set("padding", "6px")
                    .set("background-color", rowBgColor)
                    .set("font-weight", "bold")
                    .set("position", "sticky")
                    .set("left", "0")
                    .set("z-index", "1")
                    .set("cursor", "help");
            tr.appendChild(tdItem);

            // 各Reviewerの回答セル
            for (ReviewerData data : dataList) {
                String answer = data.answers.getOrDefault(itemKey, "");
                Element td = new Element("td");
                td.setText(getAnswerShort(answer));
                td.getStyle()
                        .set("border", "1px solid #ddd")
                        .set("padding", "4px")
                        .set("text-align", "center")
                        .set("background-color", getAnswerBgColor(answer))
                        .set("color", "white")
                        .set("font-weight", "bold")
                        .set("font-size", "11px");
                tr.appendChild(td);
            }

            tbody.appendChild(tr);
            rowIndex++;
        }

        table.appendChild(tbody);
        container.getElement().appendChild(table);

        // サマリー行を追加
        container.add(createSummarySection(dataList));

        // 凡例を追加
        container.add(createLegend());

        return container;
    }

    private Div createSummarySection(List<ReviewerData> dataList) {
        Div summaryDiv = new Div();
        summaryDiv.getStyle()
                .set("margin-top", "20px")
                .set("padding", "15px")
                .set("background-color", "#fff")
                .set("border", "1px solid #ddd")
                .set("border-radius", "5px");

        summaryDiv.add(new Span("Summary per Reviewer:"));
        summaryDiv.getElement().appendChild(new Element("br"));
        summaryDiv.getElement().appendChild(new Element("br"));

        for (ReviewerData data : dataList) {
            int yesCount = 0, noCount = 0, partialCount = 0, naCount = 0;

            for (String itemKey : QA_V9_ITEMS) {
                String answer = data.answers.getOrDefault(itemKey, "").toLowerCase();
                switch (answer) {
                    case "yes":
                        yesCount++;
                        break;
                    case "no":
                        noCount++;
                        break;
                    case "partial":
                        partialCount++;
                        break;
                    case "na":
                        naCount++;
                        break;
                }
            }

            Div reviewerSummary = new Div();
            reviewerSummary.getStyle()
                    .set("display", "inline-flex")
                    .set("gap", "10px")
                    .set("margin-right", "30px")
                    .set("margin-bottom", "10px")
                    .set("align-items", "center");

            Span nameSpan = new Span(data.reviewerName + ": ");
            nameSpan.getStyle().set("font-weight", "bold").set("min-width", "80px");
            reviewerSummary.add(nameSpan);

            reviewerSummary.add(createBadge("Y", yesCount, "#28a745"));
            reviewerSummary.add(createBadge("N", noCount, "#dc3545"));
            reviewerSummary.add(createBadge("P", partialCount, "#fd7e14"));
            reviewerSummary.add(createBadge("NA", naCount, "#6c757d"));

            summaryDiv.add(reviewerSummary);
        }

        return summaryDiv;
    }

    private Div createBadge(String label, int count, String bgColor) {
        Div badge = new Div();
        badge.getStyle()
                .set("display", "inline-flex")
                .set("align-items", "center")
                .set("gap", "3px");

        Span labelSpan = new Span(label + ":");
        labelSpan.getStyle().set("font-size", "11px");

        Span countSpan = new Span(String.valueOf(count));
        countSpan.getStyle()
                .set("background-color", bgColor)
                .set("color", "white")
                .set("padding", "1px 5px")
                .set("border-radius", "8px")
                .set("font-size", "10px")
                .set("font-weight", "bold")
                .set("min-width", "18px")
                .set("text-align", "center");

        badge.add(labelSpan, countSpan);
        return badge;
    }

    private Div createLegend() {
        Div legend = new Div();
        legend.getStyle()
                .set("margin-top", "15px")
                .set("padding", "10px")
                .set("display", "flex")
                .set("gap", "15px")
                .set("flex-wrap", "wrap")
                .set("align-items", "center");

        legend.add(new Span("凡例: "));
        legend.add(createLegendItem("Y", "Yes", "#28a745"));
        legend.add(createLegendItem("N", "No", "#dc3545"));
        legend.add(createLegendItem("P", "Partial", "#fd7e14"));
        legend.add(createLegendItem("NA", "NA", "#6c757d"));
        legend.add(createLegendItem("-", "未回答", "#dee2e6"));

        return legend;
    }

    private Div createLegendItem(String shortText, String fullText, String bgColor) {
        Div item = new Div();
        item.getStyle()
                .set("display", "inline-flex")
                .set("align-items", "center")
                .set("gap", "4px");

        Span badge = new Span(shortText);
        badge.getStyle()
                .set("background-color", bgColor)
                .set("color", "white")
                .set("padding", "2px 6px")
                .set("border-radius", "3px")
                .set("font-size", "11px")
                .set("font-weight", "bold");

        Span label = new Span("= " + fullText);
        label.getStyle().set("font-size", "12px");

        item.add(badge, label);
        return item;
    }

    private String getAnswerShort(String answer) {
        if (answer == null || answer.isEmpty()) return "-";
        switch (answer.toLowerCase()) {
            case "yes":
                return "Y";
            case "no":
                return "N";
            case "partial":
                return "P";
            case "na":
                return "NA";
            default:
                return "-";
        }
    }

    private String getAnswerBgColor(String answer) {
        if (answer == null || answer.isEmpty()) return "#dee2e6";
        switch (answer.toLowerCase()) {
            case "yes":
                return "#28a745";
            case "no":
                return "#dc3545";
            case "partial":
                return "#fd7e14";
            case "na":
                return "#6c757d";
            default:
                return "#dee2e6";
        }
    }

    // Reviewer ごとのデータを保持するクラス
    private static class ReviewerData {
        String reviewerName;
        String fileName;
        Map<String, String> answers = new LinkedHashMap<>();

        ReviewerData(String reviewerName, String fileName) {
            this.reviewerName = reviewerName;
            this.fileName = fileName;
        }
    }

    // データ収集結果を保持するクラス
    private static class CollectedData {
        List<ReviewerData> dataList;
        List<Path> jsonFiles;

        CollectedData(List<ReviewerData> dataList, List<Path> jsonFiles) {
            this.dataList = dataList;
            this.jsonFiles = jsonFiles;
        }
    }
}
