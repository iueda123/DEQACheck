package iu.SwingStyle.LCCA.Member.Concretes.componentholder.RsltComparator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import iu.SwingStyle.LCCA.Mediator.action.ActionMediator;
import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.DEQAResultPane.ComparisonColumnPane;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.DEQAResultPane.One_DEQAResult_Pane_Abs;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.SubTabsHolderItrfc;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * DE_v14 専用 RsltComparator。
 * clinical_cohort_part の構造に合わせてタブを構築する。
 */
public class SideBySideComparisonHolderV14 extends AbstCHolderMember implements SubTabsHolderItrfc {

    public static final String staticMemberName = "side_by_side_comparison_holder";

    private static final String SECTION_NAME = "disorders";
    private static final String PAPER_SECTION = "paper_id";

    private final String authorYear;
    private final String version;
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JTabbedPane sectionTabPane = new JTabbedPane();
    private final ArrayList<ManagerOfSubTabBasePane> allManagers = new ArrayList<>();

    private File[] jsonFiles;
    private String jsonFolderPathStr;
    private final LinkedHashMap<String, String> promptMap = new LinkedHashMap<>();

    public SideBySideComparisonHolderV14(String cholderName, String shortName, String authorYear, String version) {
        super(cholderName, shortName);
        this.authorYear = authorYear;
        this.version = version;

        String baseDir = System.getProperty("LCCA_BASE_DIR", ".");
        Path jsonFolderPath = Paths.get(baseDir, "data", authorYear, version, "json");
        this.jsonFolderPathStr = jsonFolderPath.toString();
        File jsonDir = jsonFolderPath.toFile();

        if (!jsonDir.exists() || !jsonDir.isDirectory()) {
            JOptionPane.showMessageDialog(null,
                    "json/ フォルダが見つかりません。\n" + jsonFolderPath,
                    "エラー", JOptionPane.ERROR_MESSAGE);
            panel.add(new JLabel("No JSON folder found."), BorderLayout.CENTER);
            return;
        }

        jsonFiles = jsonDir.listFiles((dir, name) -> name.endsWith(".json"));
        if (jsonFiles == null) jsonFiles = new File[0];

        ensureHumanJsonExists(jsonDir);

        jsonFiles = jsonDir.listFiles((dir, name) -> name.endsWith(".json"));
        if (jsonFiles == null || jsonFiles.length == 0) {
            JOptionPane.showMessageDialog(null,
                    "json/ フォルダ内にJSONファイルが見つかりません。\n" + jsonFolderPath,
                    "エラー", JOptionPane.ERROR_MESSAGE);
            panel.add(new JLabel("No JSON files found."), BorderLayout.CENTER);
            return;
        }

        Arrays.sort(jsonFiles, (f1, f2) -> {
            boolean f1Human = f1.getName().toLowerCase().contains("_by_human");
            boolean f2Human = f2.getName().toLowerCase().contains("_by_human");
            if (f1Human != f2Human) return Boolean.compare(f2Human, f1Human);
            return f1.getName().compareToIgnoreCase(f2.getName());
        });

        loadPromptGuide();
        buildUI();
    }

    private void ensureHumanJsonExists(File jsonDir) {
        boolean humanExists = false;
        if (jsonFiles != null) {
            for (File f : jsonFiles) {
                if (f.getName().toLowerCase().contains("_by_human")) {
                    humanExists = true;
                    break;
                }
            }
        }
        if (humanExists) return;

        File templateFile = findTemplate();
        if (templateFile == null || !templateFile.exists()) {
            System.err.println("Template not found for version: " + version);
            return;
        }

        String humanJsonName = generateHumanJsonName();
        File humanJsonFile = new File(jsonDir, humanJsonName);

        try {
            Files.copy(templateFile.toPath(), humanJsonFile.toPath());
            System.out.println("Generated human JSON from template: " + humanJsonFile.getName());
        } catch (Exception e) {
            System.err.println("Failed to generate human JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private File findTemplate() {
        String baseDir = System.getProperty("LCCA_BASE_DIR", ".");
        Path templatesDir = Paths.get(baseDir, "templates");
        String templateName = "DE_v14_Author20XX_by_Someone_YYYYmmddHHMMSS.json";
        return templatesDir.resolve(templateName).toFile();
    }

    private String generateHumanJsonName() {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return "DE_v14_" + authorYear + "_by_human_" + timestamp + ".json";
    }

    private void loadPromptGuide() {
        String baseDir = System.getProperty("LCCA_BASE_DIR", ".");
        Path guidePath = Paths.get(baseDir, "prompts", "DE_Guide_v14.md");
        if (!guidePath.toFile().exists()) {
            System.err.println("Prompt guide not found: " + guidePath);
            return;
        }

        try {
            List<String> lines = Files.readAllLines(guidePath, StandardCharsets.UTF_8);
            parseGuide(lines);
        } catch (Exception e) {
            System.err.println("Failed to load prompt guide: " + guidePath);
            e.printStackTrace();
        }
    }

    private void parseGuide(List<String> lines) {
        String currentHeadingId = null;
        StringBuilder currentBlock = new StringBuilder();

        for (String line : lines) {
            if (line.matches("^#{2,4}\\s+.*")) {
                if (currentHeadingId != null) {
                    promptMap.put(currentHeadingId, currentBlock.toString().trim());
                }
                currentHeadingId = extractHeadingId(line);
                currentBlock = new StringBuilder();
            } else {
                currentBlock.append(line).append("\n");
            }
        }
        if (currentHeadingId != null) {
            promptMap.put(currentHeadingId, currentBlock.toString().trim());
        }
    }

    private static String extractHeadingId(String headingLine) {
        String trimmed = headingLine.replaceAll("^#+\\s+", "").trim();
        int dotIndex = trimmed.indexOf('.');
        if (dotIndex > 0) {
            return trimmed.substring(0, dotIndex).trim().toLowerCase();
        }
        return trimmed.toLowerCase();
    }

    private void buildUI() {
        JPanel paperPanel = createPaperIdPanel();
        sectionTabPane.addTab("PAPER", paperPanel);

        JPanel disordersPanel = createDisordersPanel();
        sectionTabPane.addTab("DISORDERS", disordersPanel);
        panel.add(sectionTabPane, BorderLayout.CENTER);
    }

    private JPanel createDisordersPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(0, 4));

        Map<File, Map<String, Integer>> indexMap = buildDisorderIndexMap();
        List<String> disorderNames = getAllDisorderNamesOrdered(indexMap);

        if (disorderNames.isEmpty()) {
            mainPanel.add(new JLabel("No disorders found."), BorderLayout.CENTER);
            return mainPanel;
        }

        JTabbedPane disorderTabPane = new JTabbedPane();
        for (int i = 0; i < disorderNames.size(); i++) {
            String name = disorderNames.get(i);
            String disorderLabel = name + " (" + (i + 1) + ")";

            Map<File, Integer> perFileIndex = new LinkedHashMap<>();
            for (File f : jsonFiles) {
                Map<String, Integer> fileMap = indexMap.get(f);
                if (fileMap != null && fileMap.containsKey(name)) {
                    perFileIndex.put(f, fileMap.get(name));
                }
            }

            JPanel disorderPanel = new JPanel(new BorderLayout(0, 4));
            JTabbedPane fieldTabs = new JTabbedPane();
            fieldTabs.addTab("disorder-name",     createFieldPanel(perFileIndex, name, "disorder-name",     "d-1"));
            fieldTabs.addTab("dataset-of-origin", createFieldPanel(perFileIndex, name, "dataset-of-origin", "d-2"));
            fieldTabs.addTab("age",               createFieldPanel(perFileIndex, name, "age",               "d-3"));
            fieldTabs.addTab("sex",               createFieldPanel(perFileIndex, name, "sex",               "d-4"));

            disorderPanel.add(fieldTabs, BorderLayout.CENTER);
            disorderTabPane.addTab(disorderLabel, disorderPanel);
        }

        mainPanel.add(disorderTabPane, BorderLayout.CENTER);
        return mainPanel;
    }

    private String getDisorderNameAt(File jsonFile, int index) {
        JsonElement el = getElementAtPath(jsonFile, SECTION_NAME + "/" + index + "/disorder-name/answer");
        if (el != null && el.isJsonPrimitive()) {
            String v = el.getAsString();
            if (v != null && !v.isEmpty()) return v;
        }
        el = getElementAtPath(jsonFile, SECTION_NAME + "/" + index + "/disorder-name");
        if (el != null && el.isJsonPrimitive()) {
            String v = el.getAsString();
            if (v != null && !v.isEmpty()) return v;
        }
        return null;
    }

    private Map<File, Map<String, Integer>> buildDisorderIndexMap() {
        Map<File, Map<String, Integer>> result = new LinkedHashMap<>();
        for (File jsonFile : jsonFiles) {
            Map<String, Integer> nameToIndex = new LinkedHashMap<>();
            JsonArray arr = getArrayAtPath(jsonFile, SECTION_NAME);
            if (arr != null) {
                for (int i = 0; i < arr.size(); i++) {
                    String name = getDisorderNameAt(jsonFile, i);
                    if (name != null && !name.isEmpty() && !nameToIndex.containsKey(name)) {
                        nameToIndex.put(name, i);
                    }
                }
            }
            result.put(jsonFile, nameToIndex);
        }
        return result;
    }

    private List<String> getAllDisorderNamesOrdered(Map<File, Map<String, Integer>> indexMap) {
        List<String> ordered = new ArrayList<>();
        for (Map<String, Integer> nameToIndex : indexMap.values()) {
            for (String name : nameToIndex.keySet()) {
                if (!ordered.contains(name)) {
                    ordered.add(name);
                }
            }
        }
        return ordered;
    }

    private JPanel createFieldPanel(Map<File, Integer> perFileIndex, String disorderName, String fieldKey, String headingId) {
        JPanel mainPanel = new JPanel(new BorderLayout(0, 4));
        String promptText = promptMap.get(headingId);
        if (promptText != null && !promptText.isEmpty()) {
            mainPanel.add(createPromptPane(promptText), BorderLayout.NORTH);
        }

        JPanel columnsPanel = createColumnsPanelForDisorderField(perFileIndex, disorderName, fieldKey);
        JScrollPane columnsScrollPane = new JScrollPane(columnsPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        columnsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(columnsScrollPane, BorderLayout.CENTER);
        return mainPanel;
    }

    private JPanel createColumnsPanelForDisorderField(Map<File, Integer> perFileIndex, String disorderName, String fieldKey) {
        JPanel columnsPanel = new JPanel(new GridLayout(1, jsonFiles.length, 4, 0));

        ManagerOfSubTabBasePane manager = new ManagerOfSubTabBasePane(
                "RC", fieldKey, SECTION_NAME, fieldKey, sectionTabPane);
        manager.registerSubTabsHolder(this);
        allManagers.add(manager);

        for (File jsonFile : jsonFiles) {
            Integer idx = perFileIndex.get(jsonFile);
            String subSectionPath = (idx != null)
                    ? idx + "/" + fieldKey
                    : Integer.MAX_VALUE + "/" + fieldKey;
            ComparisonColumnPane columnPane = new ComparisonColumnPane(
                    jsonFolderPathStr,
                    jsonFile.getName(),
                    SECTION_NAME,
                    subSectionPath,
                    disorderName);
            manager.addToTheDePaneArray(columnPane);
            columnsPanel.add(columnPane);
        }

        return columnsPanel;
    }

    private JPanel createPaperIdPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(0, 4));
        String promptText = promptMap.get("p-1");
        if (promptText != null && !promptText.isEmpty()) {
            mainPanel.add(createPromptPane(promptText), BorderLayout.NORTH);
        }
        JPanel columnsPanel = createColumnsPanelForPath(PAPER_SECTION, "", "paper_id", null);
        JScrollPane columnsScrollPane = new JScrollPane(columnsPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        columnsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(columnsScrollPane, BorderLayout.CENTER);
        return mainPanel;
    }

    private JScrollPane createPromptPane(String promptText) {
        JTextArea promptArea = new JTextArea(promptText);
        promptArea.setEditable(false);
        promptArea.setLineWrap(true);
        promptArea.setWrapStyleWord(true);
        promptArea.setBackground(new Color(255, 255, 230));
        promptArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
        promptArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Prompt"),
                BorderFactory.createEmptyBorder(4, 4, 4, 4)));
        JScrollPane promptScrollPane = new JScrollPane(promptArea);
        promptScrollPane.setPreferredSize(new Dimension(0, 150));
        promptScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        return promptScrollPane;
    }

    private JPanel createColumnsPanelForPath(String sectionName, String subSectionPath, String displayName, JTabbedPane parentTabPane) {
        JPanel columnsPanel = new JPanel(new GridLayout(1, jsonFiles.length, 4, 0));

        JTabbedPane tabPane = parentTabPane != null ? parentTabPane : sectionTabPane;
        ManagerOfSubTabBasePane manager = new ManagerOfSubTabBasePane(
                "RC", displayName, sectionName, subSectionPath, tabPane);
        manager.registerSubTabsHolder(this);
        allManagers.add(manager);

        for (File jsonFile : jsonFiles) {
            ComparisonColumnPane columnPane = new ComparisonColumnPane(
                    jsonFolderPathStr,
                    jsonFile.getName(),
                    sectionName,
                    subSectionPath);
            manager.addToTheDePaneArray(columnPane);
            columnsPanel.add(columnPane);
        }

        return columnsPanel;
    }

    private JsonArray getArrayAtPath(File jsonFile, String path) {
        JsonElement element = getElementAtPath(jsonFile, path);
        if (element != null && element.isJsonArray()) {
            return element.getAsJsonArray();
        }
        return null;
    }

    private JsonElement getElementAtPath(File jsonFile, String path) {
        try (InputStreamReader reader = new InputStreamReader(
                Files.newInputStream(jsonFile.toPath()), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            JsonObject root = gson.fromJson(reader, JsonObject.class);
            String[] keys = path.split("/");
            JsonElement current = root;
            for (String key : keys) {
                if (key.isEmpty()) continue;
                if (current == null || current.isJsonNull()) return null;
                if (current.isJsonObject()) {
                    current = current.getAsJsonObject().get(key);
                } else if (current.isJsonArray()) {
                    if (!key.matches("\\d+")) return null;
                    int idx = Integer.parseInt(key);
                    JsonArray arr = current.getAsJsonArray();
                    if (idx < 0 || idx >= arr.size()) return null;
                    current = arr.get(idx);
                } else {
                    return null;
                }
            }
            return current;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public JComponent getBaseComponent() {
        return panel;
    }

    @Override
    public void postInitialize() {
        for (ManagerOfSubTabBasePane manager : allManagers) {
            for (One_DEQAResult_Pane_Abs pane : manager.getDeqaPaneArray()) {
                pane.loadJson();
            }
        }
    }

    @Override
    public void setCHolderMediator(CHolderMediator cHolderMediator) {
        this.cholderMediator = cHolderMediator;
    }

    @Override
    public void setActionMediator(ActionMediator actionMediator) {
        this.actionMediator = actionMediator;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void doWorkAsMember() {
    }

    @Override
    public String getSectionName() {
        return SECTION_NAME;
    }

    @Override
    public ArrayList<ManagerOfSubTabBasePane> getArrayList_of_ManagerOfSubTabBasePane() {
        return allManagers;
    }

    @Override
    public One_DEQAResult_Pane_Abs getTheFirstJsonPanel() {
        if (allManagers.isEmpty()) return null;
        ManagerOfSubTabBasePane manager = allManagers.get(0);
        if (manager.getDeqaPaneArray().isEmpty()) return null;
        return manager.getDeqaPaneArray().get(0);
    }

    @Override
    public String getAuthorYear() {
        return authorYear;
    }
}
