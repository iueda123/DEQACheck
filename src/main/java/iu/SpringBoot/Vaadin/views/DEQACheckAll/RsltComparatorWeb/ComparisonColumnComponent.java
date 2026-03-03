package iu.SpringBoot.Vaadin.views.DEQACheckAll.RsltComparatorWeb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * One AI column in the side-by-side comparison view.
 * Displays answer/detail/confidence/supporting_text/location fields
 * for a single JSON file (one AI source) at a specific section/subsection.
 */
public class ComparisonColumnComponent extends VerticalLayout {

    private final TextArea tAreaAnswer = new TextArea("Answer");
    private final TextArea tAreaDetail = new TextArea("Detail");
    private final TextArea tAreaConfidence = new TextArea("Confidence");
    private final TextArea tAreaSupportingText = new TextArea("Supp. Text");
    private final TextArea tAreaLocation = new TextArea("Location");

    private final Path jsonFilePath;
    private final String jsonFileName;
    private final String sectionName;
    private final String subSectionName;
    private final String aiName;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private JsonObject jsonObject;
    private boolean isObjectValue = false;
    private boolean usesReasonKey = false;

    public ComparisonColumnComponent(
            Path jsonFolderPath,
            String jsonFileName,
            String sectionName,
            String subSectionName) {

        this.jsonFilePath = jsonFolderPath.resolve(jsonFileName);
        this.jsonFileName = jsonFileName;
        this.sectionName = sectionName;
        this.subSectionName = subSectionName;
        this.aiName = extractAiName(jsonFileName);

        setSpacing(false);
        setPadding(false);
        setMinWidth("400px");
        setWidth("100%");
        getStyle().set("border", "1px solid var(--lumo-contrast-20pct)")
                  .set("border-radius", "var(--lumo-border-radius-m)")
                  .set("padding", "var(--lumo-space-s)")
                  .set("flex", "1 1 400px");

        // Header: AI name label with color
        Span aiLabel = new Span(aiName);
        aiLabel.getStyle()
                .set("background-color", getCssColorForAi(aiName))
                .set("color", "white")
                .set("font-weight", "bold")
                .set("padding", "4px 8px")
                .set("border-radius", "4px")
                .set("text-align", "center")
                .set("display", "block")
                .set("width", "100%")
                .set("box-sizing", "border-box");
        add(aiLabel);

        // Buttons
        Button saveBtn = new Button("Save", e -> saveJson());
        saveBtn.addThemeVariants(ButtonVariant.LUMO_SMALL);
        Button loadBtn = new Button("Load", e -> loadJson());
        loadBtn.addThemeVariants(ButtonVariant.LUMO_SMALL);
        Button ctohBtn = new Button("CtoH", e -> copyToHuman());
        ctohBtn.addThemeVariants(ButtonVariant.LUMO_SMALL);
        ctohBtn.getStyle().set("color", "var(--lumo-primary-color)");

        HorizontalLayout buttons = new HorizontalLayout(saveBtn, loadBtn, ctohBtn);
        buttons.setSpacing(true);
        buttons.setPadding(false);
        add(buttons);

        // Fields
        configureTextArea(tAreaAnswer, "180px");
        configureTextArea(tAreaDetail, "140px");
        configureTextArea(tAreaConfidence, "60px");
        configureTextArea(tAreaSupportingText, "140px");
        configureTextArea(tAreaLocation, "100px");

        add(tAreaAnswer, tAreaDetail, tAreaConfidence, tAreaSupportingText, tAreaLocation);

        // File info
        Span fileInfo = new Span(jsonFileName);
        fileInfo.getStyle()
                .set("font-size", "var(--lumo-font-size-xxs)")
                .set("color", "var(--lumo-secondary-text-color)")
                .set("overflow", "hidden")
                .set("text-overflow", "ellipsis")
                .set("white-space", "nowrap")
                .set("max-width", "100%");
        fileInfo.setTitle(jsonFileName);
        add(fileInfo);

        // Load JSON data
        loadJson();
    }

    private void configureTextArea(TextArea ta, String height) {
        ta.setWidthFull();
        ta.setHeight(height);
        ta.getStyle().set("font-size", "var(--lumo-font-size-s)");
    }

    // =========================================================================
    // JSON I/O
    // =========================================================================

    public void loadJson() {
        if (!Files.exists(jsonFilePath)) {
            Notification.show("File not found: " + jsonFileName, 3000, Notification.Position.BOTTOM_START);
            return;
        }
        try (Reader reader = new InputStreamReader(
                Files.newInputStream(jsonFilePath), StandardCharsets.UTF_8)) {
            JsonElement element = JsonParser.parseReader(reader);
            if (element.isJsonObject()) {
                jsonObject = element.getAsJsonObject();
            } else {
                jsonObject = new JsonObject();
            }
            populateFields();
        } catch (Exception e) {
            Notification.show("Failed to load: " + jsonFileName + " - " + e.getMessage(),
                    5000, Notification.Position.BOTTOM_START);
            e.printStackTrace();
        }
    }

    public void saveJson() {
        if (jsonObject == null) {
            Notification.show("No JSON loaded for " + jsonFileName, 3000, Notification.Position.BOTTOM_START);
            return;
        }

        String directPath = sectionName + "/" + subSectionName;

        if (isObjectValue) {
            saveFieldValue(directPath + "/answer", tAreaAnswer.getValue());
            String detailKey = usesReasonKey ? "reason" : "detail";
            setJsonValue(directPath + "/" + detailKey, tAreaDetail.getValue());
            setJsonValue(directPath + "/confidence_rating", tAreaConfidence.getValue());
            setJsonValue(directPath + "/supporting_text", tAreaSupportingText.getValue());
            setJsonValue(directPath + "/location", tAreaLocation.getValue());
        } else {
            setJsonValue(directPath, tAreaAnswer.getValue());
        }

        // Write to disk
        try (Writer writer = new OutputStreamWriter(
                Files.newOutputStream(jsonFilePath), StandardCharsets.UTF_8)) {
            gson.toJson(jsonObject, writer);
        } catch (Exception e) {
            Notification.show("Failed to save: " + jsonFileName + " - " + e.getMessage(),
                    5000, Notification.Position.BOTTOM_START);
            e.printStackTrace();
        }
    }

    /**
     * Save answer field, handling JSON array/object values.
     */
    private void saveFieldValue(String path, String textValue) {
        String trimmed = textValue.trim();
        if ((trimmed.startsWith("[") && trimmed.endsWith("]")) ||
                (trimmed.startsWith("{") && trimmed.endsWith("}"))) {
            try {
                JsonElement parsed = JsonParser.parseString(trimmed);
                String[] pathParts = path.split("/");
                JsonObject current = jsonObject;
                for (int i = 0; i < pathParts.length - 1; i++) {
                    JsonElement next = current.get(pathParts[i]);
                    if (next != null && next.isJsonObject()) {
                        current = next.getAsJsonObject();
                    } else {
                        setJsonValue(path, textValue);
                        return;
                    }
                }
                current.add(pathParts[pathParts.length - 1], parsed);
                return;
            } catch (Exception e) {
                // Not valid JSON, fall through to string save
            }
        }
        setJsonValue(path, textValue);
    }

    /**
     * Set a string value at a slash-delimited path in the JsonObject.
     */
    private void setJsonValue(String pathKey, String value) {
        String[] keys = pathKey.split("/");
        JsonObject current = jsonObject;

        for (int i = 0; i < keys.length - 1; i++) {
            String key = keys[i];
            if (key.isEmpty()) continue;
            if (!current.has(key)) {
                current.add(key, new JsonObject());
            }
            JsonElement element = current.get(key);
            if (element.isJsonObject()) {
                current = element.getAsJsonObject();
            } else {
                JsonObject newObj = new JsonObject();
                current.add(key, newObj);
                current = newObj;
            }
        }

        String lastKey = keys[keys.length - 1];
        if (!lastKey.isEmpty()) {
            current.addProperty(lastKey, value);
        }
    }

    /**
     * Get a string value at a slash-delimited path from the JsonObject.
     */
    private String getJsonValue(String pathKey) {
        String[] keys = pathKey.split("/");
        JsonElement current = jsonObject;

        for (String key : keys) {
            if (key.isEmpty()) continue;
            if (current == null || !current.isJsonObject()) return null;
            current = current.getAsJsonObject().get(key);
        }

        if (current == null) return null;
        if (current.isJsonPrimitive()) return current.getAsString();
        if (current.isJsonArray() || current.isJsonObject()) return gson.toJson(current);
        return null;
    }

    // =========================================================================
    // Field population
    // =========================================================================

    private void populateFields() {
        String basePath = sectionName + "/" + subSectionName;

        // Try ADCSL-style first (object with answer sub-key)
        String answer = getJsonValue(basePath + "/answer");
        if (answer != null) {
            isObjectValue = true;
            tAreaAnswer.setValue(answer);

            // Try "detail" first, then "reason"
            String detail = getJsonValue(basePath + "/detail");
            if (detail != null) {
                usesReasonKey = false;
                tAreaDetail.setValue(detail);
            } else {
                String reason = getJsonValue(basePath + "/reason");
                if (reason != null) {
                    usesReasonKey = true;
                    tAreaDetail.setValue(reason);
                } else {
                    tAreaDetail.setValue("");
                }
            }

            String cr = getJsonValue(basePath + "/confidence_rating");
            tAreaConfidence.setValue(cr != null ? cr : "");

            String st = getJsonValue(basePath + "/supporting_text");
            tAreaSupportingText.setValue(st != null ? st : "");

            String loc = getJsonValue(basePath + "/location");
            tAreaLocation.setValue(loc != null ? loc : "");
        } else {
            // Simple string value
            isObjectValue = false;
            String value = getJsonValue(basePath);
            tAreaAnswer.setValue(value != null ? value : "");
            tAreaDetail.setValue("");
            tAreaConfidence.setValue("");
            tAreaSupportingText.setValue("");
            tAreaLocation.setValue("");
        }
    }

    // =========================================================================
    // Copy to Human
    // =========================================================================

    private void copyToHuman() {
        if (getParent().isEmpty()) return;
        var parent = getParent().get();
        if (!(parent instanceof HorizontalLayout)) return;

        HorizontalLayout columnsLayout = (HorizontalLayout) parent;
        for (var child : columnsLayout.getChildren().toArray()) {
            if (child instanceof ComparisonColumnComponent) {
                ComparisonColumnComponent other = (ComparisonColumnComponent) child;
                if (other.getAiName().equalsIgnoreCase("human")) {
                    other.loadJson(); // Reload human's JSON first
                    other.tAreaAnswer.setValue(this.tAreaAnswer.getValue());
                    other.tAreaDetail.setValue(this.tAreaDetail.getValue());
                    other.tAreaConfidence.setValue(this.tAreaConfidence.getValue());
                    other.tAreaSupportingText.setValue(this.tAreaSupportingText.getValue());
                    other.tAreaLocation.setValue(this.tAreaLocation.getValue());
                    // Ensure human column knows it's ADCSL if source was
                    other.isObjectValue = this.isObjectValue;
                    other.usesReasonKey = this.usesReasonKey;
                    Notification.show("Copied to Human column", 2000, Notification.Position.BOTTOM_START);
                    return;
                }
            }
        }
        Notification.show("Human column not found", 3000, Notification.Position.BOTTOM_START);
    }

    // =========================================================================
    // Utility
    // =========================================================================

    static String extractAiName(String jsonFileName) {
        String nameWithoutExt = jsonFileName.replaceAll("\\.json$", "");
        int byIndex = nameWithoutExt.indexOf("_by_");
        if (byIndex < 0) return jsonFileName;
        String afterBy = nameWithoutExt.substring(byIndex + 4);
        int underscoreIndex = afterBy.indexOf('_');
        if (underscoreIndex < 0) return afterBy;
        return afterBy.substring(0, underscoreIndex);
    }

    private static String getCssColorForAi(String aiName) {
        switch (aiName.toLowerCase()) {
            case "claude":  return "rgb(200, 100, 50)";
            case "codex":   return "rgb(50, 130, 50)";
            case "gemini":  return "rgb(50, 100, 200)";
            case "human":   return "rgb(130, 50, 150)";
            default:        return "rgb(100, 100, 100)";
        }
    }

    public String getAiName() {
        return aiName;
    }

    public String getJsonFileName() {
        return jsonFileName;
    }
}
