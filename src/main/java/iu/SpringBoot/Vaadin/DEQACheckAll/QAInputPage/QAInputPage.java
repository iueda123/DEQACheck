package iu.SpringBoot.Vaadin.DEQACheckAll.QAInputPage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexWrap;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Route("qa-input")
@PageTitle("QA Input v9")
@RolesAllowed({"USER", "GUEST"})
public class QAInputPage extends VerticalLayout {

    private static final String DATA_PATH = "share_package/data";
    private static final String TEMPLATE_PATH = "share_package/templates/QA_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v9_with_criteria.json";
    private static final DateTimeFormatter TS_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final ObjectMapper objectMapper = new ObjectMapper();
    private JsonNode templateRoot;

    private final Map<String, com.vaadin.flow.component.HasValue<?, String>> inputFields = new LinkedHashMap<>();

    private ComboBox<String> authorYearCombo;
    private ComboBox<String> existingFileCombo;
    private Button saveButton;
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

        Button createButton = new Button("[+]", e -> createNewReport());
        createButton.setTooltipText("Create a QA report");
        createButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button loadButton = new Button("Load/Edit", e -> loadSelectedFile());

        saveButton = new Button("Save", e -> saveCurrentFile());
        saveButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        saveButton.setEnabled(false);

        Button reloadButton = new Button("Reload AuthorYear list", e -> authorYearCombo.setItems(getAuthorYearList()));

        Div lineBreak1 = new Div();
        lineBreak1.getStyle().set("flex-basis", "100%").set("height", "0");

        Div lineBreak2 = new Div();
        lineBreak2.getStyle().set("flex-basis", "100%").set("height", "0");

        FlexLayout controls = new FlexLayout(authorYearCombo, reloadButton, lineBreak1, existingFileCombo, createButton, lineBreak2, loadButton, saveButton);
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
        saveButton.setEnabled(false);
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
            saveButton.setEnabled(true);
        } catch (IOException e) {
            Notification.show("Failed to create file: " + e.getMessage(), 4000, Notification.Position.BOTTOM_START);
        }
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
            saveButton.setEnabled(true);
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
            card.getStyle()
                    .set("border", "1px solid #e0e0e0")
                    .set("border-radius", "6px")
                    .set("padding", "10px")
                    .set("margin-bottom", "10px");

            card.add(new H4(formatFieldName(entry.getKey())));

            if (isAssessmentItem(itemNode)) {
                FormLayout form = new FormLayout();
                TextField answer = new TextField("Answer");
                TextField confidence = new TextField("Confidence Rating");
                TextArea reason = new TextArea("Reason");
                TextArea supporting = new TextArea("Supporting Text");
                TextField location = new TextField("Location");

                answer.setWidthFull();
                confidence.setWidthFull();
                location.setWidthFull();
                reason.setWidthFull();
                supporting.setWidthFull();
                reason.setHeight("120px");
                supporting.setHeight("120px");

                form.add(answer, confidence, location, reason, supporting);
                form.setResponsiveSteps(
                        new FormLayout.ResponsiveStep("0", 1),
                        new FormLayout.ResponsiveStep("800px", 2)
                );

                inputFields.put(itemKey + ".answer", answer);
                inputFields.put(itemKey + ".confidence_rating", confidence);
                inputFields.put(itemKey + ".reason", reason);
                inputFields.put(itemKey + ".supporting_text", supporting);
                inputFields.put(itemKey + ".location", location);

                card.add(form);
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

    private String getCurrentUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username == null ? "user" : username;
    }
}
