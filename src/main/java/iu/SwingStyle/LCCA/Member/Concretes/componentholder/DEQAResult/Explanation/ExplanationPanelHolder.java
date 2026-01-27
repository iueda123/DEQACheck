package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Explanation;

import iu.SwingStyle.LCCA.Mediator.action.ActionMediator;
import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.SubTabsHolderConfig;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.SubTabsHolderItrfc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ExplanationPanelHolder extends AbstCHolderMember {

    public static final String staticMemberName = "explanation_panel_holder";

    /**
     * 表示するガイドファイルのパス一覧
     */
    private final String[] guideFilePaths;

    JPanel basePanel = new JPanel(new BorderLayout());
    JTabbedPane tabbedPane = new JTabbedPane();

    /**
     * 各ガイドファイルに対応するJTextAreaの配列
     */
    JTextArea[] explanationTextAreas;

    public ExplanationPanelHolder(String cholder_name, String short_name) {
        super(cholder_name, short_name);

        this.guideFilePaths = buildGuideFilePathsFromConfig();

        // ガイドファイルの数に応じてJTextAreaを作成
        explanationTextAreas = new JTextArea[guideFilePaths.length];

        // ガイドファイルをループしてタブを生成
        for (int i = 0; i < guideFilePaths.length; i++) {
            String guideFilePath = guideFilePaths[i];
            Path guidePath = Paths.get(guideFilePath);

            // JTextAreaを作成
            explanationTextAreas[i] = new JTextArea("Loading " + guidePath.getFileName() + "...");
            explanationTextAreas[i].setEditable(false);

            // JScrollPaneを作成
            JScrollPane scrollPane = new JScrollPane(explanationTextAreas[i]);
            scrollPane.setPreferredSize(new Dimension(500, 9000));
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            // タブに追加（ファイル名をタブ名として使用）
            tabbedPane.add(guidePath.getFileName().toString(), scrollPane);
        }

        // Finalize
        basePanel.add(tabbedPane, BorderLayout.EAST);
    }


    @Override
    public JComponent getBaseComponent() {
        return this.basePanel;
    }

    @Override
    public void postInitialize() {

        /* ** 説明文を書き込む ** */
        // ガイドファイルをループして内容を読み込む
        for (int i = 0; i < guideFilePaths.length; i++) {
            String guideFilePath = guideFilePaths[i];
            Path guidePath = Paths.get(guideFilePath);

            try {
                StringBuilder content = new StringBuilder();
                try (java.io.BufferedReader reader = new java.io.BufferedReader(
                        new java.io.InputStreamReader(
                                new java.io.FileInputStream(guidePath.toFile()),
                                java.nio.charset.StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                }
                explanationTextAreas[i].setText(content.toString());
                explanationTextAreas[i].setCaretPosition(0);
            } catch (java.io.IOException e) {
                explanationTextAreas[i].setText("Error loading guide file: " + e.getMessage());
                e.printStackTrace();
            }
        }


        /* **** Add the explanation to theNotePane of each sub panel **** */
        for (SubTabsHolderConfig config : SubTabsHolderConfig.values()) {
            String sub_tabs_holder_name = config.getHolderName();
            SubTabsHolderItrfc subTabsHolder = (SubTabsHolderItrfc) this.cholderMediator.getInstanceOfAMember(sub_tabs_holder_name);
            if (subTabsHolder != null) {
                ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane();
                for (ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane) {
                    String subSectionName = managerOfSubTabBasePane.getSubSectionName();

                    // sub_tabs_holder_name からガイドファイルを特定して説明を抽出

                    String subsetExplanation = getSubsetOfExplanation(sub_tabs_holder_name, subSectionName);
                    managerOfSubTabBasePane.addExplanationToNotePane(subsetExplanation);
                }
            } else {
                System.err.print("Can't find '" + sub_tabs_holder_name + "'. @" + this.getClass() + ". ");
                System.err.println("This is an error regarding adding a subset of explanation to each NotePane.");
            }
        }

    }

    private String[] buildGuideFilePathsFromConfig() {
        // Preserve declaration order while removing duplicates
        LinkedHashSet<String> uniquePaths = new LinkedHashSet<>();
        for (SubTabsHolderConfig config : SubTabsHolderConfig.values()) {
            uniquePaths.add(config.getGuideFilePath());
        }
        return uniquePaths.toArray(new String[0]);
    }

    /**
     * sub_tabs_holder_name からガイドファイルを特定し、該当セクションの説明を抽出する
     *
     * @param sub_tabs_holder_name 例: "sub_tabs_holder_DESI"
     * @param subSectionName 例: "si1_first_author", "rci2_dataset_name"
     * @return 抽出された説明文
     */
    private String getSubsetOfExplanation(String sub_tabs_holder_name, String subSectionName) {
        // sub_tabs_holder_name から対応するガイドファイルパスを取得
        SubTabsHolderConfig config = SubTabsHolderConfig.fromHolderName(sub_tabs_holder_name);

        if (config == null) {
            System.err.println("Unknown sub_tabs_holder_name: " + sub_tabs_holder_name);
            System.err.println("Cannot determine guide file. @" + this.getClass());
            return "Error: Unable to determine guide file for holder: " + sub_tabs_holder_name;
        }

        // 対応するガイドファイルから説明を抽出
        Path guideFilePath = Paths.get(config.getGuideFilePath());
        return extractSectionFromGuide(guideFilePath, subSectionName);
    }


    private String extractSectionFromGuide(Path guideFilePath, String subSectionName) {
        // 方針: 先頭の英字 + 数字から見出しキー "#### <UPPER>-<num>." を生成して、次の同レベル見出し(####)直前までを抽出
        //
        // <例>
        // Json 内の subSectionName                              | Guide MDD 内の 見出し
        // "rci1_dataset_name"                         | "#### RCI-1. Dataset Name"
        // "cr1_clear_definition_of_target_population" | "#### CR-1. Clear Definition of Target Population"
        // "nm2_1_modeling_method"                     | "#### NM2-1. Modeling Method"
        //

        // 次の同レベル見出し、または より上位の階層 直前までを抽出するように対応

        if (subSectionName == null || subSectionName.isEmpty()) {
            return "";
        }

        String headingKey = buildHeadingKey(subSectionName);
        if (headingKey == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        boolean capturing = false;

        try (java.io.BufferedReader reader = new java.io.BufferedReader(
                new java.io.InputStreamReader(
                        new java.io.FileInputStream(guideFilePath.toFile()),
                        java.nio.charset.StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (!capturing) {
                    if (trimmed.startsWith(headingKey)) {
                        capturing = true;
                        sb.append(line).append("\n");
                    }
                } else {
                    // 見出しが現れたら、同レベル(####)または上位レベル(###/##/#)で終了
                    if (trimmed.startsWith("#")) {
                        int level = 0;
                        while (level < trimmed.length() && trimmed.charAt(level) == '#') {
                            level++;
                        }
                        // 先頭見出しレベル(通常は4)を算出
                        int startLevel = 0;
                        while (startLevel < headingKey.length() && headingKey.charAt(startLevel) == '#') {
                            startLevel++;
                        }
                        // 同レベルまたは上位レベルの見出しを検出したら終了
                        // ただし、開始見出し行そのもの(headingKeyで始まる)は除外
                        if (level <= startLevel && !trimmed.startsWith(headingKey)) {
                            break;
                        }
                    }
                    sb.append(line).append("\n");
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return "Error while reading guide: " + e.getMessage();
        }

        String result = sb.toString().trim();
        if (result.isEmpty()) {
            return "Explanation not found for: " + subSectionName + "\n(Key: " + headingKey + ")";
        }
        return result;
    }

    private String buildHeadingKey(String subSectionName) {
        // subSectionName: 見出しキーを構築
        // 対応形式:
        //   形式1: rci1_dataset_name        -> #### RCI-1.
        //   形式2: nm2_1_modeling_method    -> #### NM2-1.
        //   形式3: dc_1_datasets_using...   -> #### DC-1.
        //   形式4: nm_2_1_modeling_methods  -> #### NM2-1.
        if (subSectionName == null || subSectionName.isEmpty()) {
            return null;
        }

        String[] tokens = subSectionName.toLowerCase().split("_");
        if (tokens.length == 0) {
            return null;
        }

        String letters = null;
        String primaryNumber = null;
        String secondaryNumber = null;

        // 形式1/2: tokens[0] が "rci1" や "nm2" のように文字+数字が結合している場合
        java.util.regex.Matcher combinedMatcher = java.util.regex.Pattern
                .compile("^([a-z]+)(\\d+)$")
                .matcher(tokens[0]);

        if (combinedMatcher.matches()) {
            letters = combinedMatcher.group(1).toUpperCase();
            primaryNumber = combinedMatcher.group(2);

            // 次のトークンが数字なら secondaryNumber として使用 (nm2_1_... 形式)
            if (tokens.length > 1 && tokens[1].matches("\\d+")) {
                secondaryNumber = tokens[1];
            }
        } else if (tokens[0].matches("^[a-z]+$") && tokens.length > 1 && tokens[1].matches("\\d+")) {
            // 形式3/4: tokens[0] が文字のみ、tokens[1] が数字 (dc_1_... や nm_2_1_... 形式)
            letters = tokens[0].toUpperCase();
            primaryNumber = tokens[1];

            // 次のトークンが数字なら secondaryNumber として使用 (nm_2_1_... 形式)
            if (tokens.length > 2 && tokens[2].matches("\\d+")) {
                secondaryNumber = tokens[2];
            }
        } else {
            return null;
        }

        // 見出しキーを構築
        StringBuilder headingKey = new StringBuilder("#### ").append(letters);
        if (secondaryNumber != null) {
            // nm2_1_... や nm_2_1_... → "#### NM2-1."
            headingKey.append(primaryNumber).append("-").append(secondaryNumber).append(".");
        } else {
            // rci1_... や dc_1_... → "#### RCI-1." や "#### DC-1."
            headingKey.append("-").append(primaryNumber).append(".");
        }
        return headingKey.toString();
    }

    /**
     * 最初のタブ（index 0）のテキストを設定する
     *
     * @param text 設定するテキスト
     */
    public void setText(String text) {
        if (explanationTextAreas != null && explanationTextAreas.length > 0) {
            explanationTextAreas[0].setText(text);
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

    /**
     * コンポーネントのプロパティが変更された時に呼び出される。 コンポーネントのプロパティが変更されるときとは、 フォント、前景色、背景色が変更されたとき、 コンポーネント生成時などである。
     *
     * <p>起動時に行わせたい処理を書き込むと良いのだと思う。 TODO:起動時に2回呼び出されるバグ？あり。大きな問題はないので一旦放置。なんか気持ち悪い。
     */
    private class SamplePropertyChangeListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
            System.out.println("Property of SampleTextField has changed.");

            if (actionMediator != null) {
                System.out.println("Action Starter is not null! @" + this.getClass().toString());
                actionMediator
                        .getInstanceOfAMember("initialize_sample_text_field")
                        .perform(new ActionEvent(this, 0, "Property of SampleTextField has changed."));
            } else {
                System.err.println("Action Starter is null!" + "@" + this.getClass().toString());
            }
        }
    }


}
