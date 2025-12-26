package iu.SpringBoot.Vaadin.DEQACheckAll.QAInputPage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexWrap;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import iu.SpringBoot.Vaadin.views.MainView;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route("qa-input")
@PageTitle("QA Input v9")
@RolesAllowed({"USER", "GUEST"})
public class QAInputPage extends VerticalLayout {

    private static final String DATA_PATH = "share_package/data";
    private static final String TEMPLATE_PATH = "share_package/templates/QA_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v9_with_criteria.json";
    private static final DateTimeFormatter TS_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final Map<String, String> ITEM_DESCRIPTIONS = buildItemDescriptions();

    private final ObjectMapper objectMapper = new ObjectMapper();
    private JsonNode templateRoot;

    private final Map<String, com.vaadin.flow.component.HasValue<?, String>> inputFields = new LinkedHashMap<>();

    private ComboBox<String> authorYearCombo;
    private ComboBox<String> existingFileCombo;
    private Button saveButton;
    private final List<Button> cardSaveButtons = new ArrayList<>();
    private Path currentFile;
    private VerticalLayout formContainer;

    public QAInputPage() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        templateRoot = loadTemplate();

        add(new H2("QA v9 Input"));
        add(new RouterLink("< Back to Main", MainView.class));

        createControls();
        createFormArea();
    }

    private void createControls() {
        authorYearCombo = new ComboBox<>("AuthorYear (share_package/data/*)");
        authorYearCombo.setItems(getAuthorYearList());
        authorYearCombo.setWidth("220px");
        authorYearCombo.addValueChangeListener(e -> updateExistingFiles());

        existingFileCombo = new ComboBox<>("Select a QA (v9) report or create a new report");
        existingFileCombo.setWidth("480px");

        Button createButton = new Button("Create New", e -> showCreateConfirm());
        createButton.setTooltipText("Create a QA report");
        createButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button loadButton = new Button("Load/Edit", e -> loadSelectedFile());
        Button deleteButton = new Button("Delete", e -> showDeleteConfirm());
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);

        saveButton = new Button("Save", e -> saveCurrentFile());
        saveButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        // 初期状態では無効（ファイル選択後に有効化）

        Button reloadButton = new Button("Reload AuthorYear list", e -> authorYearCombo.setItems(getAuthorYearList()));

        Div lineBreak1 = new Div();
        lineBreak1.getStyle().set("flex-basis", "100%").set("height", "0");

        Div lineBreak2 = new Div();
        lineBreak2.getStyle().set("flex-basis", "100%").set("height", "0");

        FlexLayout controls = new FlexLayout(authorYearCombo, reloadButton, lineBreak1, existingFileCombo, createButton, lineBreak2, loadButton, saveButton, deleteButton);
        controls.setWidthFull();
        controls.setFlexWrap(FlexWrap.WRAP);
        controls.setAlignItems(Alignment.END);
        controls.getStyle().set("gap", "8px");
        add(controls);
    }

    private void createFormArea() {
        formContainer = new VerticalLayout();
        formContainer.setWidthFull();
        formContainer.setSpacing(true);
        formContainer.setPadding(false);

        if (templateRoot == null) {
            formContainer.add(new Div("Template not found: " + TEMPLATE_PATH));
        } else {
            buildFormFromTemplate(templateRoot);
        }
        add(formContainer);
        setFlexGrow(1, formContainer);
    }

    private JsonNode loadTemplate() {
        Path templatePath = Paths.get(TEMPLATE_PATH);
        if (!Files.exists(templatePath)) {
            Notification.show("Template file not found: " + TEMPLATE_PATH, 5000, Notification.Position.MIDDLE);
            return null;
        }
        try {
            return objectMapper.readTree(templatePath.toFile());
        } catch (IOException e) {
            Notification.show("Failed to read template: " + e.getMessage(), 5000, Notification.Position.MIDDLE);
            return null;
        }
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
                Notification.show("Failed to list data folders: " + e.getMessage(), 4000, Notification.Position.BOTTOM_START);
            }
        }
        return result;
    }

    private void updateExistingFiles() {
        existingFileCombo.clear();
        existingFileCombo.setItems(new ArrayList<>());
        currentFile = null;
        setSaveButtonsEnabled(false);
        clearForm();

        String authorYear = authorYearCombo.getValue();
        if (authorYear == null || authorYear.isEmpty()) {
            return;
        }

        String username = getCurrentUsername();
        Path jsonDir = Paths.get(DATA_PATH, authorYear, "QA_v9", "json");
        if (!Files.exists(jsonDir)) {
            return;
        }

        try (Stream<Path> stream = Files.list(jsonDir)) {
            List<String> files = stream
                    .filter(Files::isRegularFile)
                    .map(p -> p.getFileName().toString())
                    .filter(name -> name.endsWith(".json") && name.toLowerCase().contains(("by_" + username).toLowerCase()))
                    .sorted()
                    .collect(Collectors.toList());
            existingFileCombo.setItems(files);
            if (!files.isEmpty()) {
                existingFileCombo.setValue(files.get(0));
            }
        } catch (IOException e) {
            Notification.show("Failed to list QA files: " + e.getMessage(), 4000, Notification.Position.BOTTOM_START);
        }
    }

    private void createNewReport() {
        if (templateRoot == null) {
            Notification.show("Template not available. Cannot create report.", 4000, Notification.Position.BOTTOM_START);
            return;
        }
        String authorYear = authorYearCombo.getValue();
        if (authorYear == null || authorYear.isEmpty()) {
            Notification.show("Select AuthorYear first.", 3000, Notification.Position.BOTTOM_START);
            return;
        }

        String username = getCurrentUsername();
        String timestamp = LocalDateTime.now().format(TS_FORMAT);

        Path targetDir = Paths.get(DATA_PATH, authorYear, "QA_v9", "json");
        try {
            Files.createDirectories(targetDir);
        } catch (IOException e) {
            Notification.show("Failed to create directory: " + e.getMessage(), 4000, Notification.Position.BOTTOM_START);
            return;
        }

        String newFileName = String.format("QA_%s_by_%s_%s_for_v9.json", authorYear, username, timestamp);
        Path targetPath = targetDir.resolve(newFileName);
        try {
            Files.copy(Paths.get(TEMPLATE_PATH), targetPath, StandardCopyOption.REPLACE_EXISTING);
            Notification.show("Created: " + newFileName, 3000, Notification.Position.BOTTOM_START);
            updateExistingFiles();
            existingFileCombo.setValue(newFileName);
            currentFile = targetPath;
            populateForm(templateRoot);
            setSaveButtonsEnabled(true);
        } catch (IOException e) {
            Notification.show("Failed to create file: " + e.getMessage(), 4000, Notification.Position.BOTTOM_START);
        }
    }

    private void deleteSelectedFile() {
        String authorYear = authorYearCombo.getValue();
        String fileName = existingFileCombo.getValue();
        if (authorYear == null || authorYear.isEmpty()) {
            Notification.show("Select AuthorYear first.", 3000, Notification.Position.BOTTOM_START);
            return;
        }
        if (fileName == null || fileName.isEmpty()) {
            Notification.show("Select a file to delete.", 3000, Notification.Position.BOTTOM_START);
            return;
        }

        Path filePath = Paths.get(DATA_PATH, authorYear, "QA_v9", "json", fileName);
        if (!Files.exists(filePath)) {
            Notification.show("File not found: " + fileName, 4000, Notification.Position.BOTTOM_START);
            return;
        }

        try {
            Files.delete(filePath);
            Notification.show("Deleted: " + fileName, 3000, Notification.Position.BOTTOM_START);
            currentFile = null;
            clearForm();
            updateExistingFiles();
        } catch (IOException e) {
            Notification.show("Failed to delete file: " + e.getMessage(), 4000, Notification.Position.BOTTOM_START);
        }
    }

    private void showCreateConfirm() {
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(true);
        dialog.setCloseOnOutsideClick(true);

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(true);
        layout.setSpacing(true);
        layout.add(new Paragraph("Create a new QA report from template?"));

        Button confirm = new Button("Create", event -> {
            dialog.close();
            createNewReport();
        });
        confirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button cancel = new Button("Cancel", event -> dialog.close());

        HorizontalLayout buttons = new HorizontalLayout(confirm, cancel);
        buttons.setSpacing(true);

        layout.add(buttons);
        dialog.add(layout);
        dialog.open();
    }

    private void showDeleteConfirm() {
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(true);
        dialog.setCloseOnOutsideClick(true);

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(true);
        layout.setSpacing(true);
        layout.add(new Paragraph("Delete the selected QA report? This cannot be undone."));

        Button confirm = new Button("Delete", event -> {
            dialog.close();
            deleteSelectedFile();
        });
        confirm.addThemeVariants(ButtonVariant.LUMO_ERROR);

        Button cancel = new Button("Cancel", event -> dialog.close());

        HorizontalLayout buttons = new HorizontalLayout(confirm, cancel);
        buttons.setSpacing(true);

        layout.add(buttons);
        dialog.add(layout);
        dialog.open();
    }

    private void loadSelectedFile() {
        String authorYear = authorYearCombo.getValue();
        String fileName = existingFileCombo.getValue();
        if (authorYear == null || authorYear.isEmpty()) {
            Notification.show("Select AuthorYear first.", 3000, Notification.Position.BOTTOM_START);
            return;
        }
        if (fileName == null || fileName.isEmpty()) {
            Notification.show("Select a file to load.", 3000, Notification.Position.BOTTOM_START);
            return;
        }

        Path filePath = Paths.get(DATA_PATH, authorYear, "QA_v9", "json", fileName);
        if (!Files.exists(filePath)) {
            Notification.show("File not found: " + fileName, 4000, Notification.Position.BOTTOM_START);
            return;
        }
        try {
            JsonNode node = objectMapper.readTree(filePath.toFile());
            populateForm(node);
            currentFile = filePath;
            setSaveButtonsEnabled(true);
            Notification.show("Loaded: " + fileName, 2000, Notification.Position.BOTTOM_START);
        } catch (IOException e) {
            Notification.show("Failed to load file: " + e.getMessage(), 4000, Notification.Position.BOTTOM_START);
        }
    }

    private void saveCurrentFile() {
        if (currentFile == null) {
            Notification.show("No file selected to save.", 3000, Notification.Position.BOTTOM_START);
            return;
        }
        if (templateRoot == null) {
            Notification.show("Template not available. Cannot save.", 4000, Notification.Position.BOTTOM_START);
            return;
        }

        JsonNode newContent = buildNodeFromTemplate(templateRoot, "");
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(currentFile.toFile(), newContent);
            Notification.show("Saved: " + currentFile.getFileName(), 2500, Notification.Position.BOTTOM_START);
        } catch (IOException e) {
            Notification.show("Failed to save file: " + e.getMessage(), 4000, Notification.Position.BOTTOM_START);
        }
    }

    private void buildFormFromTemplate(JsonNode root) {
        formContainer.removeAll();
        inputFields.clear();
        cardSaveButtons.clear();

        // Instruction Part
        Div instructionBox = new Div();
        instructionBox.getStyle()
                .set("background", "#f5f5f5")
                .set("border", "1px solid #ddd")
                .set("border-radius", "6px")
                .set("padding", "12px 16px")
                .set("margin-bottom", "16px");
        instructionBox.add(new H4("お願い"));
        Div instructionList = new Div();
        instructionList.getStyle().set("line-height", "1.6");
        instructionList.add(new Paragraph("・Answer欄、Confidence Rating欄は必須です。"));
        instructionList.add(new Paragraph("・Reason欄については判断根拠の記述を推奨します。初期状態で「[ ] Research hypotheses or ...」といった文字列が書かれていると思いますが、該当する場合は「[○] Research hypotheses or ...」という風に記述してください。また評価中で気になった点をReason欄に書いても良いです。"));
        instructionList.add(new Paragraph("・Study Identificationの各欄、Supporting Text欄、Location欄などは空欄でも構いません。"));
        instructionBox.add(instructionList);
        formContainer.add(instructionBox);

        // Study Identification Part
        if (root.has("study_identification_part")) {
            formContainer.add(new H3("Study Identification"));
            addFlatFieldsToLayout(formContainer, "study_identification_part", root.get("study_identification_part"));
        }

        if (root.has("common_part")) {
            formContainer.add(new H3("Common Part (CM)"));
            addAssessmentGroup("common_part", root.get("common_part"));
        }

        if (root.has("normative_modeling_part")) {
            formContainer.add(new H3("Normative Modeling Part (NM)"));
            addAssessmentGroup("normative_modeling_part", root.get("normative_modeling_part"));
        }

        if (root.has("clinical_research_part")) {
            formContainer.add(new H3("Clinical Research Part (CR)"));
            addAssessmentGroup("clinical_research_part", root.get("clinical_research_part"));
        }
    }

    private void addFlatFieldsToLayout(VerticalLayout target, String prefix, JsonNode node) {
        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            String key = prefix + "." + entry.getKey();
            TextArea area = new TextArea(formatFieldName(entry.getKey()));
            area.setWidthFull();
            area.setHeight("80px");
            inputFields.put(key, area);
            target.add(area);
        }
    }

    private void addAssessmentGroup(String prefix, JsonNode node) {
        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            JsonNode itemNode = entry.getValue();
            String itemKey = prefix + "." + entry.getKey();

            Div card = new Div();
            card.setWidthFull();
            card.getStyle()
                    .set("border", "1px solid #e0e0e0")
                    .set("border-radius", "6px")
                    .set("padding", "10px")
                    .set("margin-bottom", "10px");

            card.add(new H4(formatFieldName(entry.getKey())));
            String desc = ITEM_DESCRIPTIONS.get(entry.getKey());
            if (desc != null && !desc.isEmpty()) {
                Paragraph helper = new Paragraph(desc);
                helper.getStyle()
                        .set("font-size", "12px")
                        .set("color", "#555")
                        .set("margin-top", "0")
                        .set("margin-bottom", "8px");
                card.add(helper);
            }

            if (isAssessmentItem(itemNode)) {
                ComboBox<String> answer = new ComboBox<>("Answer");
                answer.setItems("Yes", "Partial", "No", "NA");
                answer.setAllowCustomValue(false);

                ComboBox<String> confidence = new ComboBox<>("Confidence Rating");
                confidence.setItems("High", "Medium", "Low");
                confidence.setAllowCustomValue(false);

                TextArea reason = new TextArea("Reason");
                TextArea supporting = new TextArea("Supporting Text");
                ComboBox<String> location = new ComboBox<>("Location");
                location.setItems(
                    "Paper.pdf.md: Abstract",
                    "Paper.pdf.md: Methods",
                    "Paper.pdf.md: Results",
                    "Paper.pdf.md: Discussion",
                    "Supp.pdf.md: Table",
                    "Supp.pdf.md: Figure"
                );
                location.setAllowCustomValue(true);
                location.addCustomValueSetListener(ev -> location.setValue(ev.getDetail()));

                answer.setHelperText("Choose Yes / Partial / No / NA per QA_Guide_v9.");
                confidence.setHelperText("High / Medium / Low per evidence strength.");
                reason.setHelperText("Explain which criteria are satisfied or missing (see guide).");
                supporting.setHelperText("Quote the evidence supporting the judgment.");
                location.setHelperText("File and section, e.g., Paper.pdf.md: Methods");

                answer.setWidthFull();
                confidence.setWidthFull();
                location.setWidthFull();
                reason.setWidthFull();
                supporting.setWidthFull();
                reason.setHeight("550px");
                supporting.setHeight("120px");

                VerticalLayout leftCol = new VerticalLayout(answer, confidence, supporting, location);
                leftCol.setPadding(false);
                leftCol.setSpacing(true);
                leftCol.setWidthFull();

                VerticalLayout rightCol = new VerticalLayout(reason);
                rightCol.setPadding(false);
                rightCol.setSpacing(true);
                rightCol.setWidthFull();

                HorizontalLayout twoCol = new HorizontalLayout(leftCol, rightCol);
                twoCol.setWidthFull();
                twoCol.setSpacing(true);
                twoCol.setFlexGrow(1, leftCol, rightCol);
                twoCol.getStyle().set("align-items", "stretch");

                inputFields.put(itemKey + ".answer", answer);
                inputFields.put(itemKey + ".confidence_rating", confidence);
                inputFields.put(itemKey + ".reason", reason);
                inputFields.put(itemKey + ".supporting_text", supporting);
                inputFields.put(itemKey + ".location", location);

                card.add(twoCol);

                // 各カードにSaveボタンを追加
                Button cardSave = new Button("Save", ev -> saveCurrentFile());
                cardSave.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                cardSave.setEnabled(false);
                cardSaveButtons.add(cardSave);
                card.add(cardSave);
            } else if (itemNode.isObject()) {
                VerticalLayout nested = new VerticalLayout();
                nested.setPadding(false);
                nested.setSpacing(false);
                addFlatFieldsToLayout(nested, itemKey, itemNode);
                card.add(nested);
            }
            formContainer.add(card);
        }
    }

    private boolean isAssessmentItem(JsonNode node) {
        return node.isObject()
                && node.has("answer")
                && node.has("confidence_rating")
                && node.has("reason")
                && node.has("supporting_text")
                && node.has("location");
    }

    private void populateForm(JsonNode data) {
        inputFields.forEach((key, field) -> {
            String value = getValueByPath(data, key);
            field.setValue(value == null ? "" : value);
        });
    }

    private void clearForm() {
        inputFields.values().forEach(f -> f.setValue(""));
    }

    private String getValueByPath(JsonNode root, String path) {
        if (root == null || path == null) return "";
        String[] parts = path.split("\\.");
        JsonNode current = root;
        for (String part : parts) {
            if (current == null) return "";
            current = current.get(part);
        }
        if (current != null && current.isValueNode()) {
            return current.asText("");
        }
        return "";
    }

    private JsonNode buildNodeFromTemplate(JsonNode templateNode, String prefix) {
        if (templateNode.isObject()) {
            ObjectNode obj = objectMapper.createObjectNode();
            Iterator<Map.Entry<String, JsonNode>> fields = templateNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String key = entry.getKey();
                JsonNode childTemplate = entry.getValue();
                String newPrefix = prefix.isEmpty() ? key : prefix + "." + key;

                if (isAssessmentItem(childTemplate)) {
                    ObjectNode item = objectMapper.createObjectNode();
                    item.put("answer", getInputValue(newPrefix + ".answer"));
                    item.put("confidence_rating", getInputValue(newPrefix + ".confidence_rating"));
                    item.put("reason", getInputValue(newPrefix + ".reason"));
                    item.put("supporting_text", getInputValue(newPrefix + ".supporting_text"));
                    item.put("location", getInputValue(newPrefix + ".location"));
                    obj.set(key, item);
                } else if (childTemplate.isObject()) {
                    obj.set(key, buildNodeFromTemplate(childTemplate, newPrefix));
                } else {
                    obj.put(key, getInputValue(newPrefix));
                }
            }
            return obj;
        }
        return objectMapper.getNodeFactory().textNode(getInputValue(prefix));
    }

    private String getInputValue(String key) {
        com.vaadin.flow.component.HasValue<?, String> field = inputFields.get(key);
        if (field == null || field.getValue() == null) return "";
        return field.getValue().trim();
    }

    private String formatFieldName(String key) {
        return key.replace("_", " ").toUpperCase();
    }

    private void setSaveButtonsEnabled(boolean enabled) {
        saveButton.setEnabled(enabled);
        for (Button btn : cardSaveButtons) {
            btn.setEnabled(enabled);
        }
    }

    private String getCurrentUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username == null ? "user" : username;
    }

    private static Map<String, String> buildItemDescriptions() {
        Map<String, String> map = new HashMap<>();
        map.put("cm1_research_objectives", "Research questions/hypotheses are stated and the purpose of applying normative modeling (diagnostic support, personalized medicine, disease understanding) is described.");
        map.put("nm1_selection_criteria_reference_cohort", "NEW model: eligibility of reference cohort (cite open datasets or list inclusion/exclusion), image quality/missing data standards, final N and exclusions. EXISTING model: cite original cohort; if transfer/recalibration, describe local eligibility; report quality standards and final N with exclusions.");
        map.put("nm2_handling_of_covariates_reference_cohort", "Covariates such as age/sex are considered; for multi-site data, describe site-effect handling (site covariate, ComBat, hierarchical modeling, etc.).");
        map.put("nm3_data_sources_reference_cohort", "NEW model: specify data source (database/study name or collection sites/time period). EXISTING model: specify dataset/study name or cite the original publication.");
        map.put("nm4_image_acquisition_protocol", "PRIMARY data: modality/subtype (e.g., T1, DTI, fMRI), acquisition parameters (TR/TE/resolution), equipment specs (manufacturer, field strength). SECONDARY data: answer NA.");
        map.put("nm5_data_preprocessing", "If preprocessing is performed: name software (FreeSurfer/FSL/SPM), steps or pipeline (e.g., recon-all, fMRIPrep), and QC. If only preprocessed data used: NA.");
        map.put("nm6_internal_data_validation_reference_cohort", "NEW model: data partitioning for internal validation (hold-out, K-fold, LOOCV) using held-out samples. EXISTING model: NA.");
        map.put("nm7_external_data_validation_reference_cohort", "NEW model: apply model to an independent external healthy-control dataset. EXISTING model: NA.");
        map.put("nm8_normative_modeling_approach", "NEW model: model type (linear regression, GPR, hierarchical Bayes, etc.), key settings (kernels/priors/smoothing), and software/libraries. EXISTING model: NA.");
        map.put("nm9_model_performance_reference_cohort", "NEW model: quantitative fit/calibration metrics for controls (R^2, predicted vs observed correlation, error, interval coverage). EXISTING model: NA.");
        map.put("nm10_characteristics_reference_cohort_each_partition", "NEW model with partitioning: sample sizes per split and demographics (age/sex) per partition. If no partitioning or EXISTING model: NA.");
        map.put("nm11_reproducibility", "NEW model: software/library versions; availability of code, trained models, data, and access conditions. EXISTING model: source of normative model (citation/repository/version).");
        map.put("cr1_selection_criteria_clinical_cohort", "PRIMARY data: patient eligibility/diagnostic criteria, image quality/missing data standards, final N and exclusions. SECONDARY data: cite original publication plus any extra criteria; report quality standards and final N with exclusions.");
        map.put("cr2_handling_of_clinical_covariates", "Consider clinical covariates beyond demographics (medication, illness duration, symptom severity, comorbidities) in deviation-score analyses.");
        map.put("cr3_data_sources_clinical_cohort", "PRIMARY data: collection sites/time period. SECONDARY data: database/study name or citation of original publication.");
        map.put("cr4_clinical_characteristics_clinical_cohort", "Report sample sizes and key demographics (age/sex) plus clinical characteristics (medication status, symptom severity, illness duration).");
        map.put("cr5_clinical_assessment_measures", "Specify clinical measures (scales/diagnostic criteria with version) and assessment procedures (structured interview, self-report, clinician-rated).");
        map.put("cr6_interpretation_of_deviation_scores", "Explain meaning/direction of deviation scores; if categorizing (extreme/atypical), give thresholds (e.g., |Z|>1.96); discuss clinical implications and limitations.");
        return map;
    }
}
