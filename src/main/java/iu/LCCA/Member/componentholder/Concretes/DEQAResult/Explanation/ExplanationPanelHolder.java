package iu.LCCA.Member.componentholder.Concretes.DEQAResult.Explanation;

import iu.LCCA.Mediator.action.ActionMediator;
import iu.LCCA.Mediator.componentholder.CHolderMediator;
import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.SubTabsHolderItrfc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ExplanationPanelHolder extends AbstCHolderMember {

    //private Path guideFilePath_of_DE = Paths.get("./settings/Guides/DE_Guide_v9_jp.md");
    //private Path guideFilePath_of_DE = Paths.get("./settings/Guides/DE_Guide_v10.md");
    private Path guideFilePath_of_DE = Paths.get("./settings/Guides/DE_Guide_v10_1.md");
    private Path guideFilePath_of_QA = Paths.get("./settings/Guides/QA_Guide_v7_2.md");

    /**
     * sub_tabs_holder_name と対応するガイドファイルのペア定義
     */
    enum SubTabsHolderConfig {
        DESI("sub_tabs_holder_DESI", "./settings/Guides/DE_Guide_v10_1.md"),
        DESC("sub_tabs_holder_DESC", "./settings/Guides/DE_Guide_v10_1.md"),
        DERCI("sub_tabs_holder_DERCI", "./settings/Guides/DE_Guide_v10_1.md"),
        DENM("sub_tabs_holder_DENM", "./settings/Guides/DE_Guide_v10_1.md"),
        DECAA("sub_tabs_holder_DECAA", "./settings/Guides/DE_Guide_v10_1.md"),
        DEGN("sub_tabs_holder_DEGN", "./settings/Guides/DE_Guide_v10_1.md");
        //QACM("sub_tabs_holder_QACM", "./settings/Guides/QA_Guide_v7_2.md"),
        //QANM("sub_tabs_holder_QANM", "./settings/Guides/QA_Guide_v7_2.md"),
        //QACR("sub_tabs_holder_QACR", "./settings/Guides/QA_Guide_v7_2.md");

        private final String holderName;
        private final String guideFilePath;

        SubTabsHolderConfig(String holderName, String guideFilePath) {
            this.holderName = holderName;
            this.guideFilePath = guideFilePath;
        }

        public String getHolderName() {
            return holderName;
        }

        public String getGuideFilePath() {
            return guideFilePath;
        }
    }

    JPanel basePanel = new JPanel(new BorderLayout());
    JTabbedPane tabbedPane = new JTabbedPane();
    JTextArea explanationTextArea_for_DE = new JTextArea("DE EXPLANATION");
    JTextArea explanationTextArea_for_QA = new JTextArea("QA EXPLANATION");

    public ExplanationPanelHolder(String cholder_name, String short_name) {
        super(cholder_name, short_name);

        // DE
        JScrollPane scrollPane_for_DE = new JScrollPane(explanationTextArea_for_DE);
        explanationTextArea_for_DE.setEditable(false);
        scrollPane_for_DE.setPreferredSize(new Dimension(500, 9000));
        scrollPane_for_DE.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_for_DE.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tabbedPane.add(guideFilePath_of_DE.toFile().getName(), scrollPane_for_DE);

        // QA
        JScrollPane scrollPane_for_QA = new JScrollPane(explanationTextArea_for_QA);
        explanationTextArea_for_QA.setEditable(false);
        scrollPane_for_QA.setPreferredSize(new Dimension(500, 9000));
        scrollPane_for_QA.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_for_QA.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tabbedPane.add(guideFilePath_of_QA.toFile().getName(), scrollPane_for_QA);

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
        // DE Guide
        try {
            StringBuilder content = new StringBuilder();
            try (java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(
                            new java.io.FileInputStream(guideFilePath_of_DE.toFile()),
                            java.nio.charset.StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            }
            explanationTextArea_for_DE.setText(content.toString());
            explanationTextArea_for_DE.setCaretPosition(0);
        } catch (java.io.IOException e) {
            explanationTextArea_for_DE.setText("Error loading guide file: " + e.getMessage());
            e.printStackTrace();
        }

        // QA Guide
        try {
            StringBuilder content = new StringBuilder();
            try (java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(
                            new java.io.FileInputStream(guideFilePath_of_QA.toFile()),
                            java.nio.charset.StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            }
            explanationTextArea_for_QA.setText(content.toString());
            explanationTextArea_for_QA.setCaretPosition(0);
        } catch (java.io.IOException e) {
            explanationTextArea_for_QA.setText("Error loading guide file: " + e.getMessage());
            e.printStackTrace();
        }


        /* **** Add the explanation to theNotePane of each sub panel **** */

        for (SubTabsHolderConfig config : SubTabsHolderConfig.values()) {
            String sub_tabs_holder_name = config.getHolderName();
            //QACR_SubTabsHolder subTabsHolder_QACR = (QACR_SubTabsHolder) this.cholderMediator.getInstanceOfAMember(sub_tabs_holder_name);
            SubTabsHolderItrfc subTabsHolder = (SubTabsHolderItrfc) this.cholderMediator.getInstanceOfAMember(sub_tabs_holder_name);
            if (subTabsHolder != null) {
                ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane();
                for (ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane) {
                    String sectionType = managerOfSubTabBasePane.getSectionType();
                    String subSectionName = managerOfSubTabBasePane.getSubSectionName();
                    String subsetExplanation = getSubsetOfExplanation(sectionType, subSectionName);
                    managerOfSubTabBasePane.addExplanationToNotePane(subsetExplanation);
                }
            } else {
                System.err.print("Can't find '" + sub_tabs_holder_name + "'. @" + this.getClass() + ". ");
                System.err.println("This is an error regarding adding a subset of explanation to each NotePane.");
            }
        }

    }

    private String getSubsetOfExplanation(String sectionType, String subSectionName) {
        String DEorQA = sectionType;
        String subsetOfExplanation = "";
        if (DEorQA.equals("DE")) {
            // guideFilePath_of_DE が指し示すファイルから、subSectionName に関する部分を抜き出し、subsetOfExplanation に格納
            subsetOfExplanation = extractSectionFromGuide(guideFilePath_of_DE, subSectionName);

        } else if (DEorQA.equals("QA")) {
            // guideFilePath_of_QA が指し示すファイルから、subSectionName に関する部分を抜き出し、subsetOfExplanation に格納
            subsetOfExplanation = extractSectionFromGuide(guideFilePath_of_QA, subSectionName);
        } else {
            // Unknown Section
            System.err.println("Unknown section name was specified: " + DEorQA + ".");
            System.err.println("Please check the code: @" + this.getClass());
        }
        return subsetOfExplanation;
    }



    private String extractSectionFromGuide(Path guideFilePath, String subSectionName) {
        // 方針: 先頭の英字 + 数字から見出しキー "#### <UPPER>-<num>." を生成して、次の同レベル見出し(####)直前までを抽出
        //
        // <例>
        // Json 内の subSectionName                              | Guide MDD 内の 見出し
        // "rci1_dataset_name"                         | "#### RCI-1. Dataset Name"
        // "cr1_clear_definition_of_target_population" | "#### CR-1. Clear Definition of Target Population"
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
        // subSectionName: 先頭の英字ブロック + 数字 から見出しキーを構築
        // 例) rci1_dataset_name -> #### RCI-1.
        //     cr7_handling_of_confounding_variables -> #### CR-7.
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("^([a-z]+)(\\d+)_.*");
        java.util.regex.Matcher m = p.matcher(subSectionName.toLowerCase());
        if (!m.matches()) {
            return null;
        }
        String letters = m.group(1).toUpperCase();
        String number = m.group(2);
        return "#### " + letters + "-" + number + ".";
    }

    public void setText(String text) {
        explanationTextArea_for_DE.setText(text);
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
