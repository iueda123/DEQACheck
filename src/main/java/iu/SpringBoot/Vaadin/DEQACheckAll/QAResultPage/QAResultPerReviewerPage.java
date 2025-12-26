package iu.SpringBoot.Vaadin.DEQACheckAll.QAResultPage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import iu.SpringBoot.Vaadin.views.MainView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import jakarta.annotation.security.RolesAllowed;

@Route("qa-results")
@PageTitle("QA Result Per Reviewer Page")
@RolesAllowed({"USER", "GUEST"})
public class QAResultPerReviewerPage extends VerticalLayout {

    private static final String DATA_PATH = "share_package/data";

    private ComboBox<String> authorYearCombo;
    private ComboBox<String> promptNameCombo;
    private ComboBox<String> reviewerNameCombo;
    private Div resultContainer;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public QAResultPerReviewerPage() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        // Title
        add(new H2("QA Summary per Reviewer"));

        // Back to MainView link
        RouterLink backLink = new RouterLink("< Back to Main", MainView.class);
        add(backLink);

        // Create dropdowns
        createDropdowns();

        // Create result container with scrolling
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

        // Set this layout to expand
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
        promptNameCombo.addValueChangeListener(e -> {
            if (e.getValue() != null) {
                updateReviewerNameOptions();
            }
        });

        // ReviewerName dropdown
        reviewerNameCombo = new ComboBox<>("ReviewerName");
        reviewerNameCombo.setWidth("150px");
        reviewerNameCombo.addValueChangeListener(e -> updateDisplay());

        // Reload button
        Button reloadButton = new Button("Reload", e -> {
            authorYearCombo.setItems(getAuthorYearList());
            updatePromptNameOptions();
            updateDisplay();
        });

        HorizontalLayout dropdownLayout = new HorizontalLayout(
            authorYearCombo, promptNameCombo, reviewerNameCombo, reloadButton
        );
        dropdownLayout.setAlignItems(Alignment.END);
        add(dropdownLayout);
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
            // Prefer "QA" if exists, otherwise the first one
            if (promptNames.contains("QA")) {
                promptNameCombo.setValue("QA");
            } else {
                promptNameCombo.setValue(promptNames.get(0));
            }
        }
    }

    private void updateReviewerNameOptions() {
        String authorYear = authorYearCombo.getValue();
        String promptName = promptNameCombo.getValue();
        if (authorYear == null || promptName == null) {
            reviewerNameCombo.setItems(new ArrayList<>());
            return;
        }

        List<String> reviewerNames = new ArrayList<>();
        Path jsonDir = Paths.get(DATA_PATH, authorYear, promptName, "json");

        if (Files.exists(jsonDir) && Files.isDirectory(jsonDir)) {
            try (Stream<Path> stream = Files.list(jsonDir)) {
                reviewerNames = stream
                    .filter(Files::isRegularFile)
                    .map(p -> p.getFileName().toString())
                    .filter(name -> name.endsWith(".json"))
                    .map(this::extractReviewerName)
                    .filter(name -> name != null && !name.isEmpty())
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        reviewerNameCombo.setItems(reviewerNames);
        if (!reviewerNames.isEmpty()) {
            reviewerNameCombo.setValue(reviewerNames.get(0));
        }
    }

    private String extractReviewerName(String fileName) {
        // ファイル名パターン: <AuthorYear>_by_<ReviewerName>_<timestamp>_for_QA_v9.json
        // または: *_by_<ReviewerName>_*.json
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

    private void updateDisplay() {
        resultContainer.removeAll();

        String authorYear = authorYearCombo.getValue();
        String promptName = promptNameCombo.getValue();
        String reviewerName = reviewerNameCombo.getValue();

        if (authorYear == null || promptName == null || reviewerName == null) {
            Div placeholder = new Div("Please select AuthorYear, PromptName, and ReviewerName to view QA results.");
            placeholder.getStyle()
                .set("color", "#666")
                .set("font-style", "italic")
                .set("padding", "20px");
            resultContainer.add(placeholder);
            return;
        }

        // Find matching JSON file
        Path jsonDir = Paths.get(DATA_PATH, authorYear, promptName, "json");
        if (!Files.exists(jsonDir) || !Files.isDirectory(jsonDir)) {
            Div errorDiv = new Div("JSON folder not found: " + jsonDir);
            errorDiv.getStyle().set("color", "#dc3545");
            resultContainer.add(errorDiv);
            return;
        }

        try (Stream<Path> stream = Files.list(jsonDir)) {
            Optional<Path> matchingFile = stream
                .filter(p -> p.getFileName().toString().endsWith(".json"))
                .filter(p -> p.getFileName().toString().toLowerCase().contains(reviewerName.toLowerCase()))
                .findFirst();

            if (matchingFile.isPresent()) {
                displayJsonContent(matchingFile.get());
            } else {
                Div notFoundDiv = new Div("No JSON file found for reviewer: " + reviewerName + " in " + jsonDir);
                notFoundDiv.getStyle().set("color", "#ffc107");
                resultContainer.add(notFoundDiv);
            }
        } catch (IOException e) {
            Div errorDiv = new Div("Error reading directory: " + e.getMessage());
            errorDiv.getStyle().set("color", "#dc3545");
            resultContainer.add(errorDiv);
        }
    }

    private void displayJsonContent(Path jsonPath) {
        try {
            JsonNode root = objectMapper.readTree(jsonPath.toFile());

            // File info header
            Div fileInfoDiv = new Div();
            fileInfoDiv.getStyle()
                .set("background-color", "#e3f2fd")
                .set("padding", "10px")
                .set("border-radius", "5px")
                .set("margin-bottom", "15px");

            Span fileLabel = new Span("File: ");
            fileLabel.getStyle().set("font-weight", "bold");
            Span fileName = new Span(jsonPath.getFileName().toString());
            fileName.getStyle().set("font-family", "monospace");
            fileInfoDiv.add(fileLabel, fileName);
            resultContainer.add(fileInfoDiv);

            // Detect QA_v8 format or older format
            boolean isQAv8 = root.has("study_identification_part") || root.has("common_part") || root.has("normative_modeling_part");

            if (isQAv8) {
                // QA_v8 format
                displayQAv8Content(root);
            } else {
                // Older format (assessment_items_group_a/b)
                displayOlderFormatContent(root);
            }

        } catch (IOException e) {
            Div errorDiv = new Div("Error reading JSON: " + e.getMessage());
            errorDiv.getStyle().set("color", "#dc3545");
            resultContainer.add(errorDiv);
        }
    }

    private void displayQAv8Content(JsonNode root) {
        // Summary section for QA_v8
        resultContainer.add(createSummarySectionQAv8(root));

        // Study Identification Part
        if (root.has("study_identification_part")) {
            H3 header = new H3("Study Identification");
            header.getStyle().set("margin-top", "20px").set("color", "#1565c0");
            resultContainer.add(header);
            resultContainer.add(createStudyIdTable(root.get("study_identification_part")));
        }

        // Common Part
        if (root.has("common_part")) {
            H3 header = new H3("Common Part (CM)");
            header.getStyle().set("margin-top", "20px").set("color", "#1565c0");
            resultContainer.add(header);
            resultContainer.add(createAssessmentTableQAv8(root.get("common_part")));
        }

        // Normative Modeling Part
        if (root.has("normative_modeling_part")) {
            H3 header = new H3("Normative Modeling Part (NM)");
            header.getStyle().set("margin-top", "20px").set("color", "#1565c0");
            resultContainer.add(header);
            resultContainer.add(createAssessmentTableQAv8(root.get("normative_modeling_part")));
        }

        // Clinical Research Part
        if (root.has("clinical_research_part")) {
            H3 header = new H3("Clinical Research Part (CR)");
            header.getStyle().set("margin-top", "20px").set("color", "#1565c0");
            resultContainer.add(header);
            resultContainer.add(createAssessmentTableQAv8(root.get("clinical_research_part")));
        }
    }

    private Div createAssessmentTableQAv8(JsonNode node) {
        Div container = new Div();
        container.getStyle().set("overflow-x", "auto");

        Element table = new Element("table");
        table.getStyle()
            .set("border-collapse", "collapse")
            .set("width", "100%")
            .set("margin-bottom", "20px")
            .set("font-size", "13px")
            .set("background-color", "white");

        // Header row - QA_v8 format (no Category column)
        Element thead = new Element("thead");
        Element headerRow = new Element("tr");
        String[] headers = {"#", "Item", "Answer", "Confidence", "Reason", "Supporting Text", "Location"};
        String[] widths = {"30px", "200px", "70px", "90px", "300px", "350px", "150px"};

        for (int i = 0; i < headers.length; i++) {
            Element th = new Element("th");
            th.setText(headers[i]);
            th.getStyle()
                .set("border", "1px solid #ddd")
                .set("padding", "10px")
                .set("text-align", "left")
                .set("background-color", "#1565c0")
                .set("color", "white")
                .set("position", "sticky")
                .set("top", "0")
                .set("min-width", widths[i])
                .set("font-weight", "600");
            headerRow.appendChild(th);
        }
        thead.appendChild(headerRow);
        table.appendChild(thead);

        // Body rows
        Element tbody = new Element("tbody");
        Iterator<Map.Entry<String, JsonNode>> items = node.fields();
        int rowIndex = 0;
        while (items.hasNext()) {
            Map.Entry<String, JsonNode> item = items.next();
            JsonNode itemData = item.getValue();

            // Skip if not an object (simple key-value pairs)
            if (!itemData.isObject()) continue;

            Element tr = new Element("tr");
            String bgColor = (rowIndex % 2 == 0) ? "#ffffff" : "#f8f9fa";

            // Row number
            addTableCell(tr, String.valueOf(rowIndex + 1), bgColor, false, "30px", "center");

            // Item name
            addTableCell(tr, formatFieldName(item.getKey()), bgColor, true, "200px", "left");

            // Answer with color coding
            String answer = getFieldValue(itemData, "answer");
            String answerBgColor = getAnswerBgColor(answer);
            addTableCellWithBgColor(tr, answer, answerBgColor);

            // Confidence
            String confidence = getFieldValue(itemData, "confidence_rating");
            String confidenceColor = getConfidenceColor(confidence);
            addTableCellWithTextColor(tr, confidence, bgColor, confidenceColor);

            // Reason
            addTableCell(tr, getFieldValue(itemData, "reason"), bgColor, false, "300px", "left");

            // Supporting Text
            addTableCell(tr, getFieldValue(itemData, "supporting_text"), bgColor, false, "350px", "left");

            // Location
            addTableCell(tr, getFieldValue(itemData, "location"), bgColor, false, "150px", "left");

            tbody.appendChild(tr);
            rowIndex++;
        }
        table.appendChild(tbody);

        container.getElement().appendChild(table);
        return container;
    }

    private void displayOlderFormatContent(JsonNode root) {
        // Summary section - count Yes/No/Partial/NA
        if (root.has("assessment_items_group_a") || root.has("assessment_items_group_b")) {
            resultContainer.add(createSummarySection(root));
        }

        // Study Identification
        if (root.has("study_identification")) {
            H3 header = new H3("Study Identification");
            header.getStyle().set("margin-top", "20px").set("color", "#1565c0");
            resultContainer.add(header);
            resultContainer.add(createKeyValueTable(root.get("study_identification")));
        }

        // Assessment Items Group A
        if (root.has("assessment_items_group_a")) {
            H3 header = new H3("Assessment Items - Group A (Clarity/Validity)");
            header.getStyle().set("margin-top", "20px").set("color", "#1565c0");
            resultContainer.add(header);
            resultContainer.add(createAssessmentTable(root.get("assessment_items_group_a")));
        }

        // Assessment Items Group B
        if (root.has("assessment_items_group_b")) {
            H3 header = new H3("Assessment Items - Group B (Technical Details)");
            header.getStyle().set("margin-top", "20px").set("color", "#1565c0");
            resultContainer.add(header);
            resultContainer.add(createAssessmentTable(root.get("assessment_items_group_b")));
        }

        // Additional Comments
        if (root.has("additional_comments")) {
            H3 header = new H3("Additional Comments");
            header.getStyle().set("margin-top", "20px").set("color", "#1565c0");
            resultContainer.add(header);
            resultContainer.add(createKeyValueTable(root.get("additional_comments")));
        }
    }

    private Div createStudyIdTable(JsonNode node) {
        Div container = new Div();
        container.getStyle().set("overflow-x", "auto");

        Element table = new Element("table");
        table.getStyle()
            .set("border-collapse", "collapse")
            .set("width", "100%")
            .set("margin-bottom", "20px")
            .set("background-color", "white");

        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            Element tr = new Element("tr");

            // Extract field name (e.g., "si1_study_id" -> "SI1 Study Id")
            String fieldKey = field.getKey();
            String displayName = formatFieldName(fieldKey);

            Element th = new Element("th");
            th.setText(displayName);
            th.getStyle()
                .set("border", "1px solid #ddd")
                .set("padding", "10px")
                .set("text-align", "left")
                .set("background-color", "#f8f9fa")
                .set("width", "200px")
                .set("vertical-align", "top")
                .set("font-weight", "600");

            Element td = new Element("td");
            td.setText(field.getValue().asText());
            td.getStyle()
                .set("border", "1px solid #ddd")
                .set("padding", "10px")
                .set("word-wrap", "break-word")
                .set("white-space", "pre-wrap");

            tr.appendChild(th);
            tr.appendChild(td);
            table.appendChild(tr);
        }

        container.getElement().appendChild(table);
        return container;
    }

    private Div createSummarySectionQAv8(JsonNode root) {
        int yesCount = 0, noCount = 0, partialCount = 0, naCount = 0;

        // Count from all parts
        String[] parts = {"common_part", "normative_modeling_part", "clinical_research_part"};
        for (String partName : parts) {
            if (root.has(partName)) {
                JsonNode part = root.get(partName);
                Iterator<Map.Entry<String, JsonNode>> items = part.fields();
                while (items.hasNext()) {
                    JsonNode itemData = items.next().getValue();
                    if (itemData.isObject()) {
                        String answer = getFieldValue(itemData, "answer").toLowerCase();
                        switch (answer) {
                            case "yes": yesCount++; break;
                            case "no": noCount++; break;
                            case "partial": partialCount++; break;
                            case "na": naCount++; break;
                        }
                    }
                }
            }
        }

        int total = yesCount + noCount + partialCount + naCount;

        // Create summary div
        Div summaryDiv = new Div();
        summaryDiv.getStyle()
            .set("display", "flex")
            .set("gap", "20px")
            .set("padding", "15px")
            .set("background-color", "#fff")
            .set("border", "1px solid #ddd")
            .set("border-radius", "5px")
            .set("margin-bottom", "15px")
            .set("flex-wrap", "wrap");

        // Summary title
        Div titleDiv = new Div("Summary: ");
        titleDiv.getStyle().set("font-weight", "bold").set("margin-right", "10px");
        summaryDiv.add(titleDiv);

        // Yes badge
        summaryDiv.add(createBadge("Yes", yesCount, "#28a745"));
        // No badge
        summaryDiv.add(createBadge("No", noCount, "#dc3545"));
        // Partial badge
        summaryDiv.add(createBadge("Partial", partialCount, "#ffc107"));
        // NA badge
        summaryDiv.add(createBadge("NA", naCount, "#6c757d"));
        // Total
        summaryDiv.add(createBadge("Total", total, "#17a2b8"));

        return summaryDiv;
    }

    private Div createSummarySection(JsonNode root) {
        int yesCount = 0, noCount = 0, partialCount = 0, naCount = 0;

        // Count from Group A
        if (root.has("assessment_items_group_a")) {
            JsonNode groupA = root.get("assessment_items_group_a");
            Iterator<Map.Entry<String, JsonNode>> items = groupA.fields();
            while (items.hasNext()) {
                String answer = getFieldValue(items.next().getValue(), "answer").toLowerCase();
                switch (answer) {
                    case "yes": yesCount++; break;
                    case "no": noCount++; break;
                    case "partial": partialCount++; break;
                    case "na": naCount++; break;
                }
            }
        }

        // Count from Group B
        if (root.has("assessment_items_group_b")) {
            JsonNode groupB = root.get("assessment_items_group_b");
            Iterator<Map.Entry<String, JsonNode>> items = groupB.fields();
            while (items.hasNext()) {
                String answer = getFieldValue(items.next().getValue(), "answer").toLowerCase();
                switch (answer) {
                    case "yes": yesCount++; break;
                    case "no": noCount++; break;
                    case "partial": partialCount++; break;
                    case "na": naCount++; break;
                }
            }
        }

        int total = yesCount + noCount + partialCount + naCount;

        // Create summary div
        Div summaryDiv = new Div();
        summaryDiv.getStyle()
            .set("display", "flex")
            .set("gap", "20px")
            .set("padding", "15px")
            .set("background-color", "#fff")
            .set("border", "1px solid #ddd")
            .set("border-radius", "5px")
            .set("margin-bottom", "15px")
            .set("flex-wrap", "wrap");

        // Summary title
        Div titleDiv = new Div("Summary: ");
        titleDiv.getStyle().set("font-weight", "bold").set("margin-right", "10px");
        summaryDiv.add(titleDiv);

        // Yes badge
        summaryDiv.add(createBadge("Yes", yesCount, "#28a745"));
        // No badge
        summaryDiv.add(createBadge("No", noCount, "#dc3545"));
        // Partial badge
        summaryDiv.add(createBadge("Partial", partialCount, "#ffc107"));
        // NA badge
        summaryDiv.add(createBadge("NA", naCount, "#6c757d"));
        // Total
        summaryDiv.add(createBadge("Total", total, "#17a2b8"));

        return summaryDiv;
    }

    private Div createBadge(String label, int count, String color) {
        Div badge = new Div();
        badge.getStyle()
            .set("display", "inline-flex")
            .set("align-items", "center")
            .set("gap", "5px");

        Span labelSpan = new Span(label + ": ");
        Span countSpan = new Span(String.valueOf(count));
        countSpan.getStyle()
            .set("background-color", color)
            .set("color", "white")
            .set("padding", "2px 8px")
            .set("border-radius", "10px")
            .set("font-weight", "bold")
            .set("min-width", "24px")
            .set("text-align", "center");

        badge.add(labelSpan, countSpan);
        return badge;
    }

    private Div createKeyValueTable(JsonNode node) {
        Div container = new Div();
        container.getStyle().set("overflow-x", "auto");

        Element table = new Element("table");
        table.getStyle()
            .set("border-collapse", "collapse")
            .set("width", "100%")
            .set("margin-bottom", "20px")
            .set("background-color", "white");

        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            Element tr = new Element("tr");

            Element th = new Element("th");
            th.setText(formatFieldName(field.getKey()));
            th.getStyle()
                .set("border", "1px solid #ddd")
                .set("padding", "10px")
                .set("text-align", "left")
                .set("background-color", "#f8f9fa")
                .set("width", "200px")
                .set("vertical-align", "top")
                .set("font-weight", "600");

            Element td = new Element("td");
            td.setText(field.getValue().asText());
            td.getStyle()
                .set("border", "1px solid #ddd")
                .set("padding", "10px")
                .set("word-wrap", "break-word")
                .set("white-space", "pre-wrap");

            tr.appendChild(th);
            tr.appendChild(td);
            table.appendChild(tr);
        }

        container.getElement().appendChild(table);
        return container;
    }

    private Div createAssessmentTable(JsonNode node) {
        Div container = new Div();
        container.getStyle().set("overflow-x", "auto");

        Element table = new Element("table");
        table.getStyle()
            .set("border-collapse", "collapse")
            .set("width", "100%")
            .set("margin-bottom", "20px")
            .set("font-size", "13px")
            .set("background-color", "white");

        // Header row
        Element thead = new Element("thead");
        Element headerRow = new Element("tr");
        String[] headers = {"#", "Item", "Answer", "Confidence", "Category", "Reason", "Supporting Text", "Location"};
        String[] widths = {"30px", "180px", "70px", "90px", "100px", "250px", "300px", "120px"};

        for (int i = 0; i < headers.length; i++) {
            Element th = new Element("th");
            th.setText(headers[i]);
            th.getStyle()
                .set("border", "1px solid #ddd")
                .set("padding", "10px")
                .set("text-align", "left")
                .set("background-color", "#1565c0")
                .set("color", "white")
                .set("position", "sticky")
                .set("top", "0")
                .set("min-width", widths[i])
                .set("font-weight", "600");
            headerRow.appendChild(th);
        }
        thead.appendChild(headerRow);
        table.appendChild(thead);

        // Body rows
        Element tbody = new Element("tbody");
        Iterator<Map.Entry<String, JsonNode>> items = node.fields();
        int rowIndex = 0;
        while (items.hasNext()) {
            Map.Entry<String, JsonNode> item = items.next();
            JsonNode itemData = item.getValue();

            Element tr = new Element("tr");
            String bgColor = (rowIndex % 2 == 0) ? "#ffffff" : "#f8f9fa";

            // Row number
            addTableCell(tr, String.valueOf(rowIndex + 1), bgColor, false, "30px", "center");

            // Item name
            addTableCell(tr, formatFieldName(item.getKey()), bgColor, true, "180px", "left");

            // Answer with color coding
            String answer = getFieldValue(itemData, "answer");
            String answerBgColor = getAnswerBgColor(answer);
            addTableCellWithBgColor(tr, answer, answerBgColor);

            // Confidence
            String confidence = getFieldValue(itemData, "confidence_rating");
            String confidenceColor = getConfidenceColor(confidence);
            addTableCellWithTextColor(tr, confidence, bgColor, confidenceColor);

            // Category
            addTableCell(tr, getFieldValue(itemData, "negative_answer_category"), bgColor, false, "100px", "left");

            // Reason
            addTableCell(tr, getFieldValue(itemData, "reason"), bgColor, false, "250px", "left");

            // Supporting Text
            addTableCell(tr, getFieldValue(itemData, "supporting_text"), bgColor, false, "300px", "left");

            // Location
            addTableCell(tr, getFieldValue(itemData, "location"), bgColor, false, "120px", "left");

            tbody.appendChild(tr);
            rowIndex++;
        }
        table.appendChild(tbody);

        container.getElement().appendChild(table);
        return container;
    }

    private void addTableCell(Element tr, String text, String bgColor, boolean bold, String width, String align) {
        Element td = new Element("td");
        td.setText(text != null ? text : "");
        td.getStyle()
            .set("border", "1px solid #ddd")
            .set("padding", "8px")
            .set("background-color", bgColor)
            .set("vertical-align", "top")
            .set("word-wrap", "break-word")
            .set("white-space", "pre-wrap")
            .set("text-align", align);
        if (bold) {
            td.getStyle().set("font-weight", "600");
        }
        if (width != null) {
            td.getStyle().set("min-width", width);
        }
        tr.appendChild(td);
    }

    private void addTableCellWithBgColor(Element tr, String text, String bgColor) {
        Element td = new Element("td");
        td.setText(text != null ? text : "");
        td.getStyle()
            .set("border", "1px solid #ddd")
            .set("padding", "8px")
            .set("background-color", bgColor)
            .set("vertical-align", "top")
            .set("font-weight", "bold")
            .set("text-align", "center")
            .set("color", "white");
        tr.appendChild(td);
    }

    private void addTableCellWithTextColor(Element tr, String text, String bgColor, String textColor) {
        Element td = new Element("td");
        td.setText(text != null ? text : "");
        td.getStyle()
            .set("border", "1px solid #ddd")
            .set("padding", "8px")
            .set("background-color", bgColor)
            .set("vertical-align", "top")
            .set("font-weight", "600")
            .set("text-align", "center")
            .set("color", textColor);
        tr.appendChild(td);
    }

    private String getAnswerBgColor(String answer) {
        if (answer == null) return "#6c757d";
        switch (answer.toLowerCase()) {
            case "yes": return "#28a745";      // Green
            case "no": return "#dc3545";       // Red
            case "partial": return "#fd7e14";  // Orange
            case "na": return "#6c757d";       // Gray
            default: return "#6c757d";         // Gray
        }
    }

    private String getConfidenceColor(String confidence) {
        if (confidence == null) return "#000000";
        switch (confidence.toLowerCase()) {
            case "high": return "#28a745";     // Green
            case "medium": return "#ffc107";   // Yellow
            case "low": return "#dc3545";      // Red
            default: return "#000000";         // Black
        }
    }

    private String getFieldValue(JsonNode node, String fieldName) {
        if (node == null || !node.has(fieldName)) return "";
        JsonNode field = node.get(fieldName);
        return field.isNull() ? "" : field.asText();
    }

    private String formatFieldName(String fieldName) {
        // Convert snake_case to Title Case
        return Arrays.stream(fieldName.split("_"))
            .map(word -> word.isEmpty() ? "" :
                Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase())
            .collect(Collectors.joining(" "));
    }
}
