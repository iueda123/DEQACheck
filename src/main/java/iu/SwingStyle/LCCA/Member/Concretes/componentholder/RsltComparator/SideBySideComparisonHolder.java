package iu.SwingStyle.LCCA.Member.Concretes.componentholder.RsltComparator;

import com.google.gson.Gson;
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
 * RsltComparator の核心クラス。
 * 指定された authorYear と version の JSON を横並びで比較表示する。
 *
 * 動的にセクション/サブセクション構造を発見し、
 * セクションタブ → サブセクションタブ → AI別横並びカラム
 * の3層構造を構築する。
 * プロンプトガイドのテキストを各サブセクションの上に表示する。
 * human JSONが無い場合はテンプレートから生成する。
 */
public class SideBySideComparisonHolder extends AbstCHolderMember implements SubTabsHolderItrfc {

    public static final String staticMemberName = "side_by_side_comparison_holder";

    private final String authorYear;
    private final String version;
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JTabbedPane sectionTabPane = new JTabbedPane();

    private final ArrayList<ManagerOfSubTabBasePane> allManagers = new ArrayList<>();

    // セクション構造: sectionName -> List<subSectionName>
    private final LinkedHashMap<String, List<String>> sectionStructure = new LinkedHashMap<>();
    // JSONファイル一覧
    private File[] jsonFiles;
    private String jsonFolderPathStr;

    // プロンプトガイド: headingId (e.g. "si-1") -> prompt text block
    private final LinkedHashMap<String, String> promptMap = new LinkedHashMap<>();

    public SideBySideComparisonHolder(String cholderName, String shortName, String authorYear, String version) {
        super(cholderName, shortName);
        this.authorYear = authorYear;
        this.version = version;

        // JSONフォルダのパス構築
        Path jsonFolderPath = Paths.get("./data", authorYear, version, "json");
        this.jsonFolderPathStr = jsonFolderPath.toString();
        File jsonDir = jsonFolderPath.toFile();

        if (!jsonDir.exists() || !jsonDir.isDirectory()) {
            JOptionPane.showMessageDialog(null,
                    "json/ フォルダが見つかりません。\n" + jsonFolderPath,
                    "エラー", JOptionPane.ERROR_MESSAGE);
            panel.add(new JLabel("No JSON folder found."), BorderLayout.CENTER);
            return;
        }

        // JSONファイル一覧取得
        jsonFiles = jsonDir.listFiles((dir, name) -> name.endsWith(".json"));
        if (jsonFiles == null) jsonFiles = new File[0];

        // human JSONが無ければテンプレートから生成
        ensureHumanJsonExists(jsonDir);

        // 再スキャン
        jsonFiles = jsonDir.listFiles((dir, name) -> name.endsWith(".json"));
        if (jsonFiles == null || jsonFiles.length == 0) {
            JOptionPane.showMessageDialog(null,
                    "json/ フォルダ内にJSONファイルが見つかりません。\n" + jsonFolderPath,
                    "エラー", JOptionPane.ERROR_MESSAGE);
            panel.add(new JLabel("No JSON files found."), BorderLayout.CENTER);
            return;
        }

        // human を先頭にソート
        Arrays.sort(jsonFiles, (f1, f2) -> {
            boolean f1Human = f1.getName().toLowerCase().contains("_by_human");
            boolean f2Human = f2.getName().toLowerCase().contains("_by_human");
            if (f1Human != f2Human) return Boolean.compare(f2Human, f1Human);
            return f1.getName().compareToIgnoreCase(f2.getName());
        });

        // 動的セクション発見: 最初のJSONファイルを読んで構造を解析
        discoverSectionStructure(jsonFiles[0]);

        // プロンプトガイドを読み込み
        loadPromptGuide();

        // UI構築
        buildUI();
    }

    // =========================================================================
    // Human JSON template generation
    // =========================================================================

    /**
     * human JSONが存在しなければテンプレートから生成する。
     */
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

        // テンプレートを探す
        File templateFile = findTemplate();
        if (templateFile == null || !templateFile.exists()) {
            System.err.println("Template not found for version: " + version);
            return;
        }

        // human JSON ファイル名を生成
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

    /**
     * バージョンに応じたテンプレートファイルを見つける。
     */
    private File findTemplate() {
        Path templatesDir = Paths.get("./templates");
        Map<String, String> templateNames = new LinkedHashMap<>();
        templateNames.put("DE_v14", "DE_v14_Author20XX_by_Someone_YYYYmmddHHMMSS.json");
        templateNames.put("DE_v13", "DE_v13_Author20XX_by_Someone_YYYYmmddHHMMSS.json");
        templateNames.put("DE_v12", "DE_v12_Author20XX_by_Someone_YYYYmmddHHMMSS.json");
        templateNames.put("DE_v11", "DE_v11_Author20XX_by_Someone_YYYYmmddHHMMSS.json");
        templateNames.put("DE_v10_1", "DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v10_1.json");
        templateNames.put("QA_v9", "Author20XX_by_Someone_YYYYmmddHHMMSS_for_QA_v9.json");

        String templateName = templateNames.get(version);
        if (templateName != null) {
            return templatesDir.resolve(templateName).toFile();
        }
        return null;
    }

    /**
     * human JSON のファイル名を生成する。
     */
    private String generateHumanJsonName() {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        switch (version) {
            case "DE_v14":
                return "DE_v14_" + authorYear + "_by_human_" + timestamp + ".json";
            case "DE_v13":
                return "DE_v13_" + authorYear + "_by_human_" + timestamp + ".json";
            case "DE_v12":
                return "DE_v12_" + authorYear + "_by_human_" + timestamp + ".json";
            case "DE_v11":
                return "DE_v11_" + authorYear + "_by_human_" + timestamp + ".json";
            case "DE_v10_1":
                return "DE_" + authorYear + "_by_human_" + timestamp + "_for_v10_1.json";
            case "QA_v9":
                return authorYear + "_by_human_" + timestamp + "_for_QA_v9.json";
            default:
                return version + "_" + authorYear + "_by_human_" + timestamp + ".json";
        }
    }

    // =========================================================================
    // Prompt guide loading and parsing
    // =========================================================================

    /**
     * プロンプトガイドのMarkdownを読み込んで、サブセクション毎のテキストを抽出する。
     */
    private void loadPromptGuide() {
        String guideFileName = deriveGuideFileName();
        if (guideFileName == null) return;

        Path guidePath = Paths.get("./prompts", guideFileName);
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

    /**
     * バージョン名からガイドファイル名を導出する。
     * DE_v12 -> DE_Guide_v12.md, QA_v9 -> QA_Guide_v9.md
     */
    private String deriveGuideFileName() {
        if (version.startsWith("DE_")) return "DE_Guide_" + version.substring(3) + ".md";
        if (version.startsWith("QA_")) return "QA_Guide_" + version.substring(3) + ".md";
        return null;
    }

    /**
     * Markdownガイドを行単位で解析し、見出しID毎のテキストブロックを promptMap に格納する。
     */
    private void parseGuide(List<String> lines) {
        String currentHeadingId = null;
        StringBuilder currentBlock = new StringBuilder();

        for (String line : lines) {
            // ## or ### or #### heading
            if (line.matches("^#{2,4}\\s+.*")) {
                // Save previous block
                if (currentHeadingId != null) {
                    promptMap.put(currentHeadingId, currentBlock.toString().trim());
                }
                // Extract heading ID from line like "#### SI-1. Study ID"
                currentHeadingId = extractHeadingId(line);
                currentBlock = new StringBuilder();
                currentBlock.append(line).append("\n");
            } else if (currentHeadingId != null) {
                currentBlock.append(line).append("\n");
            }
        }
        // Save last block
        if (currentHeadingId != null) {
            promptMap.put(currentHeadingId, currentBlock.toString().trim());
        }
    }

    /**
     * 見出し行からIDを抽出する。
     * "#### SI-1. Study ID" -> "si-1"
     * "### RCI-1. Using MSAD or Not" -> "rci-1"
     */
    private static String extractHeadingId(String headingLine) {
        String trimmed = headingLine.replaceAll("^#+\\s+", "").trim();
        int dotIndex = trimmed.indexOf('.');
        if (dotIndex > 0) {
            return trimmed.substring(0, dotIndex).trim().toLowerCase();
        }
        return trimmed.toLowerCase();
    }

    /**
     * サブセクションキーからプロンプトテキストを取得する。
     * si_1_study_id -> si-1, rci1_using_msad -> rci-1
     */
    String getPromptForSubSection(String subSectionKey) {
        String headingId = subSectionKeyToHeadingId(subSectionKey);
        return promptMap.get(headingId);
    }

    /**
     * サブセクションキーをガイドの見出しIDに変換する。
     * si_1_study_id -> si-1
     * nm2_1_modeling_methods -> nm2-1
     * rci1_using_msad -> rci-1
     */
    static String subSectionKeyToHeadingId(String subSectionKey) {
        String[] parts = subSectionKey.split("_");
        String prefix = parts[0];

        // 2番目のパートが数字なら prefix-number
        if (parts.length >= 2 && parts[1].matches("\\d+")) {
            return prefix.toLowerCase() + "-" + parts[1];
        }

        // prefix自体の末尾に数字がある場合 (rci1, caa7, nm10)
        StringBuilder alpha = new StringBuilder();
        StringBuilder digits = new StringBuilder();
        for (char c : prefix.toCharArray()) {
            if (Character.isDigit(c) && alpha.length() > 0) {
                digits.append(c);
            } else {
                alpha.append(c);
            }
        }
        if (digits.length() > 0) {
            return alpha.toString().toLowerCase() + "-" + digits.toString();
        }

        return prefix.toLowerCase();
    }

    // =========================================================================
    // Section structure discovery
    // =========================================================================

    /**
     * JSONファイルを読んでセクション/サブセクション構造を動的に発見する。
     */
    private void discoverSectionStructure(File firstJsonFile) {
        try (InputStreamReader reader = new InputStreamReader(
                Files.newInputStream(firstJsonFile.toPath()), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            JsonObject root = gson.fromJson(reader, JsonObject.class);

            for (String sectionKey : root.keySet()) {
                JsonElement sectionElement = root.get(sectionKey);
                List<String> subSections = new ArrayList<>();

                if (sectionElement.isJsonObject()) {
                    JsonObject sectionObj = sectionElement.getAsJsonObject();
                    for (String subKey : sectionObj.keySet()) {
                        subSections.add(subKey);
                    }
                }
                // セクション内にサブセクションがない場合（直接値のセクション）はスキップ
                if (!subSections.isEmpty()) {
                    sectionStructure.put(sectionKey, subSections);
                }
            }

        } catch (Exception e) {
            System.err.println("Failed to discover section structure from: " + firstJsonFile.getAbsolutePath());
            e.printStackTrace();
        }
    }

    // =========================================================================
    // UI construction
    // =========================================================================

    /**
     * 発見したセクション構造に基づいてUIを構築する。
     */
    private void buildUI() {
        for (Map.Entry<String, List<String>> entry : sectionStructure.entrySet()) {
            String sectionName = entry.getKey();
            List<String> subSections = entry.getValue();

            // セクション用のサブタブペイン
            JTabbedPane subSectionTabPane = new JTabbedPane();

            for (String subSectionName : subSections) {
                // メインパネル: 上にプロンプト、下にカラム群
                JPanel mainPanel = new JPanel(new BorderLayout(0, 4));

                // プロンプトテキスト表示
                String promptText = getPromptForSubSection(subSectionName);
                if (promptText != null && !promptText.isEmpty()) {
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
                    mainPanel.add(promptScrollPane, BorderLayout.NORTH);
                }

                // カラム群パネル
                JPanel columnsPanel = createColumnsPanel(sectionName, subSectionName, subSectionTabPane);
                JScrollPane columnsScrollPane = new JScrollPane(columnsPanel,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                columnsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
                mainPanel.add(columnsScrollPane, BorderLayout.CENTER);

                subSectionTabPane.addTab(formatSubSectionTabName(subSectionName), mainPanel);
            }

            sectionTabPane.addTab(formatSectionTabName(sectionName), subSectionTabPane);
        }

        panel.add(sectionTabPane, BorderLayout.CENTER);
    }

    /**
     * 1つの subsection に対して、全JSONファイルの横並びカラムを作成する。
     */
    private JPanel createColumnsPanel(String sectionName, String subSectionName, JTabbedPane parentTabPane) {
        JPanel columnsPanel = new JPanel(new GridLayout(1, jsonFiles.length, 4, 0));

        // ManagerOfSubTabBasePane を作成（既存パターンに合わせる）
        ManagerOfSubTabBasePane manager = new ManagerOfSubTabBasePane(
                "RC", formatSubSectionTabName(subSectionName),
                sectionName, subSectionName, parentTabPane);
        manager.registerSubTabsHolder(this);
        allManagers.add(manager);

        for (File jsonFile : jsonFiles) {
            ComparisonColumnPane columnPane = new ComparisonColumnPane(
                    jsonFolderPathStr,
                    jsonFile.getName(),
                    sectionName,
                    subSectionName);
            manager.addToTheDePaneArray(columnPane);
            columnsPanel.add(columnPane);
        }

        return columnsPanel;
    }

    /**
     * セクション名をタブ表示用にフォーマットする。
     */
    private String formatSectionTabName(String sectionName) {
        Map<String, String> abbreviations = new LinkedHashMap<>();
        abbreviations.put("study_identification_part", "SI");
        abbreviations.put("study_characteristics_part", "SC");
        abbreviations.put("reference_cohort_and_imaging_part", "RCI");
        abbreviations.put("reference_cohort_info_part", "RCI");
        abbreviations.put("normative_modeling_part", "NM");
        abbreviations.put("normative_modeling_2nd_part", "NM2");
        abbreviations.put("clinical_application_and_analysis_part", "CAA");
        abbreviations.put("general_note_part", "GN");
        abbreviations.put("common_part", "CM");
        abbreviations.put("clinical_research_part", "CR");
        abbreviations.put("dataset_characteristics_part", "DC");

        String abbr = abbreviations.get(sectionName);
        if (abbr != null) return abbr;

        // フォールバック: _ で分割して頭文字を大文字結合
        String[] parts = sectionName.split("_");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty() && !part.equals("part")) {
                sb.append(Character.toUpperCase(part.charAt(0)));
            }
        }
        return sb.length() > 0 ? sb.toString() : sectionName;
    }

    /**
     * サブセクション名をタブ表示用にフォーマットする。
     */
    private String formatSubSectionTabName(String subSectionName) {
        String[] parts = subSectionName.split("_");
        if (parts.length >= 2) {
            String prefix = parts[0].toUpperCase();
            // 2番目が数字ならそれをくっつける
            if (parts[1].matches("\\d+")) {
                return prefix + parts[1];
            }
            // prefix 自体に数字が含まれている場合 (例: rci1)
            return prefix;
        }
        return subSectionName.toUpperCase();
    }

    // --- SubTabsHolderItrfc implementation ---

    @Override
    public String getSectionName() {
        if (sectionStructure.isEmpty()) return "";
        return sectionStructure.keySet().iterator().next();
    }

    @Override
    public ArrayList<ManagerOfSubTabBasePane> getArrayList_of_ManagerOfSubTabBasePane() {
        return allManagers;
    }

    @Override
    public One_DEQAResult_Pane_Abs getTheFirstJsonPanel() {
        if (allManagers.isEmpty()) return null;
        ArrayList<One_DEQAResult_Pane_Abs> panes = allManagers.get(0).getDeqaPaneArray();
        if (panes.isEmpty()) return null;
        return panes.get(0);
    }

    @Override
    public String getAuthorYear() {
        return authorYear;
    }

    // --- AbstCHolderMember implementation ---

    @Override
    public JComponent getBaseComponent() {
        return panel;
    }

    @Override
    public void postInitialize() {
        // 全パネルにJSONを読み込ませる
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

    public String getVersion() {
        return version;
    }
}
