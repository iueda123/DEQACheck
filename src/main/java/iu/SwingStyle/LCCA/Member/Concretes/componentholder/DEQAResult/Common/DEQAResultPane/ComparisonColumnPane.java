package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.DEQAResultPane;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import iu.SwingStyle.LCCA.Utils.ColorChangeableTextArea;
import iu.SwingStyle.LCCA.Utils.JsonManagerWithConflictSafe.JsonManagerWithConflictSafe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * 横並び比較表示用の1カラムパネル。
 * One_DEQAResult_Pane_Abs を継承し、1つのAIの出力を表示する。
 * ヘッダ: AI名（ファイル名から抽出: claude, codex, gemini, human）
 * ボディ: answer, detail/reason, confidence_rating, supporting_text, location
 * フッタ: json name label
 */
public class ComparisonColumnPane extends One_DEQAResult_Pane_Abs {

    private static final String MISSING_INDEX_PREFIX = Integer.MAX_VALUE + "/";

    private final ColorChangeableTextArea tArea_Answer = new ColorChangeableTextArea("");
    private final ColorChangeableTextArea tArea_Detail = new ColorChangeableTextArea("");
    private final ColorChangeableTextArea tArea_ConfidenceRating = new ColorChangeableTextArea("");
    private final ColorChangeableTextArea tArea_SupportingText = new ColorChangeableTextArea("");
    private final ColorChangeableTextArea tArea_Location = new ColorChangeableTextArea("");
    private final String aiName;

    /** Whether the JSON subsection value is an object (ADCSL) vs simple string */
    private boolean isObjectValue = false;
    /** Whether "reason" key is used instead of "detail" (QA_v9, DE_v10_1 style) */
    private boolean usesReasonKey = false;
    private final String desiredDisorderName;

    public ComparisonColumnPane(
            String jsonFolderPathStr,
            String jsonName,
            String sectionName,
            String subSectionName) {
        this(jsonFolderPathStr, jsonName, sectionName, subSectionName, null);
    }

    public ComparisonColumnPane(
            String jsonFolderPathStr,
            String jsonName,
            String sectionName,
            String subSectionName,
            String desiredDisorderName) {

        super(jsonFolderPathStr, jsonName, sectionName, subSectionName);

        this.aiName = extractAiName(jsonName);
        this.desiredDisorderName = desiredDisorderName;

        // Configure text areas
        configureTextArea(tArea_Answer);
        configureTextArea(tArea_Detail);
        configureTextArea(tArea_SupportingText);
        configureTextArea(tArea_Location);
        tArea_ConfidenceRating.setLineWrap(true);
        tArea_ConfidenceRating.setWrapStyleWord(true);

        setLayout(new BorderLayout(2, 2));

        /* Header: AI name + buttons */
        Box headerBox = Box.createVerticalBox();

        JLabel aiLabel = new JLabel(aiName, SwingConstants.CENTER);
        aiLabel.setFont(aiLabel.getFont().deriveFont(Font.BOLD, 14f));
        aiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        aiLabel.setOpaque(true);
        aiLabel.setBackground(getColorForAi(aiName));
        aiLabel.setForeground(Color.WHITE);
        JPanel aiLabelPanel = new JPanel(new BorderLayout());
        aiLabelPanel.add(aiLabel, BorderLayout.CENTER);
        aiLabelPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        headerBox.add(aiLabelPanel);

        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(loadButton);
        buttonBox.add(saveButton);
        buttonBox.add(openJsonFileButton);
        buttonBox.add(openJsonFolderButton);
        buttonBox.add(copyToTheHumanPanelButton);
        buttonBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        headerBox.add(buttonBox);

        add(headerBox, BorderLayout.NORTH);

        /* Center: all fields in a vertical layout inside a scroll pane */
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));

        fieldsPanel.add(createLabeledField("Answer", tArea_Answer, 4));
        fieldsPanel.add(createLabeledField("Detail", tArea_Detail, 3));
        fieldsPanel.add(createLabeledField("Confidence", tArea_ConfidenceRating, 1));
        fieldsPanel.add(createLabeledField("Supp. Text", tArea_SupportingText, 3));
        fieldsPanel.add(createLabeledField("Location", tArea_Location, 2));

        JScrollPane scrollPane = new JScrollPane(fieldsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        /* Footer: json name label */
        jsonNameLabel.setFont(jsonNameLabel.getFont().deriveFont(Font.PLAIN, 10f));
        jsonNameLabel.setToolTipText(jsonName);
        add(jsonNameLabel, BorderLayout.SOUTH);

        setBorder(BorderFactory.createEtchedBorder());

        // JsonManager init
        initializeJsonManager();
    }

    private void configureTextArea(ColorChangeableTextArea ta) {
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
    }

    /**
     * Create a labeled field panel: label on top, scrollable text area below.
     */
    private JPanel createLabeledField(String label, JTextArea textArea, int rows) {
        JPanel panel = new JPanel(new BorderLayout(0, 1));
        JLabel lbl = new JLabel(label);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD, 11f));
        lbl.setForeground(new Color(80, 80, 80));
        panel.add(lbl, BorderLayout.NORTH);

        textArea.setRows(rows);
        JScrollPane sp = new JScrollPane(textArea);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(sp, BorderLayout.CENTER);

        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return panel;
    }

    /**
     * JSONファイル名からAI名を抽出する。
     * 例: DE_v12_Baldwin2022_by_claude_20260120174027.json -> claude
     */
    private static String extractAiName(String jsonFileName) {
        String nameWithoutExt = jsonFileName.replaceAll("\\.json$", "");
        int byIndex = nameWithoutExt.indexOf("_by_");
        if (byIndex < 0) return jsonFileName;
        String afterBy = nameWithoutExt.substring(byIndex + 4);
        int underscoreIndex = afterBy.indexOf('_');
        if (underscoreIndex < 0) return afterBy;
        return afterBy.substring(0, underscoreIndex);
    }

    private static Color getColorForAi(String aiName) {
        switch (aiName.toLowerCase()) {
            case "claude":
                return new Color(200, 100, 50);
            case "codex":
                return new Color(50, 130, 50);
            case "gemini":
                return new Color(50, 100, 200);
            case "human":
                return new Color(130, 50, 150);
            default:
                return new Color(100, 100, 100);
        }
    }

    @Override
    public void saveJson() {
        String effectiveSubSection = subSectionName;
        if (isMissingDisorderSlot()) {
            int createdIndex = ensureDisorderSlot();
            if (createdIndex < 0) {
                JOptionPane.showMessageDialog(
                        SwingUtilities.getWindowAncestor(this),
                        "disorder-name が未設定のため自動追加できませんでした。",
                        "保存できません",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            String fieldKey = extractFieldKey(subSectionName);
            effectiveSubSection = createdIndex + "/" + fieldKey;
        }

        final boolean objectValue = isObjectValue;
        final boolean reasonKey = usesReasonKey;
        final String answerText = tArea_Answer.getText();
        final String detailText = tArea_Detail.getText();
        final String confidenceText = tArea_ConfidenceRating.getText();
        final String supportingText = tArea_SupportingText.getText();
        final String locationText = tArea_Location.getText();

        // Determine if this is an object-value subsection
        String answerPath = sectionName + "/" + effectiveSubSection + "/answer";
        String directPath = sectionName + "/" + effectiveSubSection;

        jsonManager.doSaveAsync(false, () -> {
            if (objectValue) {
                // Save answer - may be JSON array/object or simple string
                saveFieldValue(answerPath, answerText);
                // Save detail or reason
                String detailKey = reasonKey ? "reason" : "detail";
                jsonManager.setValue(sectionName + "/" + subSectionName + "/" + detailKey, detailText);
                jsonManager.setValue(sectionName + "/" + subSectionName + "/confidence_rating", confidenceText);
                jsonManager.setValue(sectionName + "/" + subSectionName + "/supporting_text", supportingText);
                jsonManager.setValue(sectionName + "/" + subSectionName + "/location", locationText);
            } else {
                // Simple string value
                jsonManager.setValue(directPath, answerText);
            }
        });
    }

    /**
     * Save the answer field value, handling JSON arrays/objects.
     */
    private void saveFieldValue(String path, String textValue) {
        String trimmed = textValue.trim();
        // Try to parse as JSON array or object if it looks like one
        if ((trimmed.startsWith("[") && trimmed.endsWith("]")) ||
                (trimmed.startsWith("{") && trimmed.endsWith("}"))) {
            try {
                JsonElement parsed = JsonParser.parseString(trimmed);
                // Navigate to the parent object and set directly
                String[] pathParts = path.split("/");
                com.google.gson.JsonObject current = jsonManager.getJsonObject();
                for (int i = 0; i < pathParts.length - 1; i++) {
                    JsonElement next = current.get(pathParts[i]);
                    if (next != null && next.isJsonObject()) {
                        current = next.getAsJsonObject();
                    } else {
                        // Fall back to string save
                        jsonManager.setValue(path, textValue);
                        return;
                    }
                }
                current.add(pathParts[pathParts.length - 1], parsed);
                return;
            } catch (Exception e) {
                // Not valid JSON, fall through to string save
            }
        }
        jsonManager.setValue(path, textValue);
    }

    @Override
    public void loadJson() {
        jsonManager.reloadFromDisk();
    }

    @Override
    public void copyToTheHumanDEQAResultPane() {
        if (managerOfSubTabBasePane == null) return;

        for (One_DEQAResult_Pane_Abs pane : managerOfSubTabBasePane.getDeqaPaneArray()) {
            if (pane.getJsonName().toLowerCase().contains("human")) {
                if (pane instanceof ComparisonColumnPane) {
                    ComparisonColumnPane humanPane = (ComparisonColumnPane) pane;
                    humanPane.loadJson();
                    humanPane.tArea_Answer.setText(tArea_Answer.getText());
                    humanPane.tArea_Detail.setText(tArea_Detail.getText());
                    humanPane.tArea_ConfidenceRating.setText(tArea_ConfidenceRating.getText());
                    humanPane.tArea_SupportingText.setText(tArea_SupportingText.getText());
                    humanPane.tArea_Location.setText(tArea_Location.getText());
                }
            }
        }
    }

    @Override
    protected void resetBackgroundColorOfTAreasTFields() {
        tArea_Answer.resetBackgroundColor();
        tArea_Answer.updateDefaultValue();
        tArea_Detail.resetBackgroundColor();
        tArea_Detail.updateDefaultValue();
        tArea_ConfidenceRating.resetBackgroundColor();
        tArea_ConfidenceRating.updateDefaultValue();
        tArea_SupportingText.resetBackgroundColor();
        tArea_SupportingText.updateDefaultValue();
        tArea_Location.resetBackgroundColor();
        tArea_Location.updateDefaultValue();
    }

    @Override
    public boolean isUpdated() {
        return tArea_Answer.isUpdated() || tArea_Detail.isUpdated()
                || tArea_ConfidenceRating.isUpdated()
                || tArea_SupportingText.isUpdated() || tArea_Location.isUpdated();
    }

    @Override
    public Component getFrame() {
        return SwingUtilities.getWindowAncestor(this);
    }

    @Override
    public void actionAfterSuccessfullyOpeningJson(JsonManagerWithConflictSafe jm) {
        populateFieldsFromJson(jm);
        jsonNameLabel.setText(jsonName);
        resetBackgroundColorOfTAreasTFields();
    }

    @Override
    public void actionAfterFailingToOpenJson(JsonManagerWithConflictSafe jm) {
        System.err.println("Failed to open JSON: " + jm.getJsonFile().getAbsolutePath());
    }

    @Override
    public void actionAfterSuccessfullySavingJson(JsonManagerWithConflictSafe jm) {
        resetBackgroundColorOfTAreasTFields();
    }

    @Override
    public void actionAfterFailingToSaveJson(JsonManagerWithConflictSafe jm) {
        System.err.println("Failed to save JSON: " + jm.getJsonFile().getAbsolutePath());
    }

    @Override
    public void actionAfterSuccessfullyReloadingJson(JsonManagerWithConflictSafe jm) {
        populateFieldsFromJson(jm);
        resetBackgroundColorOfTAreasTFields();
    }

    @Override
    public void actionAfterFailingToReloadJson(JsonManagerWithConflictSafe jm) {
        System.err.println("Failed to reload JSON: " + jm.getJsonFile().getAbsolutePath());
    }

    /**
     * Populate all fields from the JSON manager.
     * Detects whether the value is a simple string or an ADCSL-style object.
     */
    private void populateFieldsFromJson(JsonManagerWithConflictSafe jm) {
        String basePath = sectionName + "/" + subSectionName;

        // Try to read as ADCSL object first (answer sub-key)
        String answer = jm.getValueAsString(basePath + "/answer");
        if (answer != null) {
            isObjectValue = true;
            tArea_Answer.setText(answer);

            // Try "detail" first, then "reason"
            String detail = jm.getValueAsString(basePath + "/detail");
            if (detail != null) {
                usesReasonKey = false;
                tArea_Detail.setText(detail);
            } else {
                String reason = jm.getValueAsString(basePath + "/reason");
                if (reason != null) {
                    usesReasonKey = true;
                    tArea_Detail.setText(reason);
                }
            }

            String cr = jm.getValueAsString(basePath + "/confidence_rating");
            if (cr != null) tArea_ConfidenceRating.setText(cr);

            String st = jm.getValueAsString(basePath + "/supporting_text");
            if (st != null) tArea_SupportingText.setText(st);

            String loc = jm.getValueAsString(basePath + "/location");
            if (loc != null) tArea_Location.setText(loc);
        } else {
            // Simple string value
            isObjectValue = false;
            String value = jm.getValueAsString(basePath);
            if (value != null) {
                tArea_Answer.setText(value);
            }
        }
    }

    public ColorChangeableTextArea gettArea_Answer() {
        return tArea_Answer;
    }

    public String getAiName() {
        return aiName;
    }

    private boolean isMissingDisorderSlot() {
        return subSectionName != null && subSectionName.startsWith(MISSING_INDEX_PREFIX);
    }

    private String extractFieldKey(String subSectionPath) {
        if (subSectionPath == null) return "";
        int slash = subSectionPath.indexOf('/');
        if (slash < 0 || slash == subSectionPath.length() - 1) return "";
        return subSectionPath.substring(slash + 1);
    }

    private int ensureDisorderSlot() {
        if (!"disorders".equals(sectionName)) {
            return -1;
        }
        if (desiredDisorderName == null || desiredDisorderName.isBlank()) {
            return -1;
        }
        com.google.gson.JsonObject root = jsonManager.getJsonObject();
        if (root == null) {
            return -1;
        }
        com.google.gson.JsonElement element = root.get(sectionName);
        com.google.gson.JsonArray arr;
        if (element == null || !element.isJsonArray()) {
            arr = new com.google.gson.JsonArray();
            root.add(sectionName, arr);
        } else {
            arr = element.getAsJsonArray();
        }

        for (int i = 0; i < arr.size(); i++) {
            com.google.gson.JsonElement e = arr.get(i);
            if (e != null && e.isJsonObject()) {
                String name = readDisorderName(e.getAsJsonObject());
                if (desiredDisorderName.equals(name)) {
                    return i;
                }
            }
        }

        com.google.gson.JsonObject disorder = new com.google.gson.JsonObject();
        com.google.gson.JsonObject nameObj = new com.google.gson.JsonObject();
        nameObj.addProperty("answer", desiredDisorderName);
        disorder.add("disorder-name", nameObj);
        arr.add(disorder);
        return arr.size() - 1;
    }

    private String readDisorderName(com.google.gson.JsonObject disorder) {
        if (disorder == null) return null;
        com.google.gson.JsonElement nameEl = disorder.get("disorder-name");
        if (nameEl == null || nameEl.isJsonNull()) return null;
        if (nameEl.isJsonPrimitive()) {
            return nameEl.getAsString();
        }
        if (nameEl.isJsonObject()) {
            com.google.gson.JsonElement ans = nameEl.getAsJsonObject().get("answer");
            if (ans != null && ans.isJsonPrimitive()) {
                return ans.getAsString();
            }
        }
        return null;
    }
}
