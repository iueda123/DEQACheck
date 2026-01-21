package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.DEQAResultPane;

import iu.SwingStyle.LCCA.Utils.ColorChangeableTextArea;
import iu.SwingStyle.LCCA.Utils.JsonManagerWithConflictSafe.JsonManagerWithConflictSafe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class One_ASL_Style_Pane extends One_DEQAResult_Pane_Abs {

    ColorChangeableTextArea tArea_Answer = new ColorChangeableTextArea("Answer");
    private final String tooltipForAnswer = "Answer";

    //ColorChangeableTextField tFiled_ConfidenceRating = new ColorChangeableTextField("Confidence Rating");
    //private final String tooltipForConfidenceRating = "Confidence Rating";

    //ColorChangeableTextField tField_NegativeAnswerCategory = new ColorChangeableTextField("Negative Answer Category");
    //private final String tooltipForNegativeAnswerCategory = "Negative Answer Category";

    //ColorChangeableTextArea tArea_Reason = new ColorChangeableTextArea("Reason");
    //private final String tooltipForReason = "Reason";

    ColorChangeableTextArea tArea_SupportingText = new ColorChangeableTextArea("Before Loading");
    private final String tooltipForSupportingText = "Supporting Text";

    ColorChangeableTextArea tArea_Location = new ColorChangeableTextArea("Before Loading");
    private final String tooltip_Location = "Source file, page, or/and line of the information.";

    public One_ASL_Style_Pane(String jsonFolderPathStr, String jsonName, String sectionName, String subSectionName) {
        super(jsonFolderPathStr, jsonName, sectionName, subSectionName);

        // Answer Sub Sub Section
        tArea_Answer.setLineWrap(true);
        tArea_Answer.setWrapStyleWord(true);
        tArea_Answer.setToolTipText(tooltipForAnswer);

        // Confidence Rating Sub Sub Section
        //tFiled_ConfidenceRating.setToolTipText(tooltipForConfidenceRating);

        // Negative Answer Category Sub SubSection
        //tField_NegativeAnswerCategory.setToolTipText(tooltipForNegativeAnswerCategory);

        // Reason Sub Sub Section
        //tArea_Reason.setLineWrap(true);
        //tArea_Reason.setWrapStyleWord(true);
        //tArea_Reason.setToolTipText(tooltipForReason);

        // Supporting Text Sub Sub Section
        tArea_SupportingText.setLineWrap(true);
        tArea_SupportingText.setWrapStyleWord(true);
        tArea_SupportingText.setToolTipText(tooltipForSupportingText);

        // Supporting Location Sub Sub Section
        tArea_Location.setLineWrap(true);
        tArea_Location.setWrapStyleWord(true);
        tArea_Location.setToolTipText(tooltip_Location);

        setLayout(new BorderLayout());

        /* Setup North Area */
        Box baseOfNorth = Box.createVerticalBox();

        // The 1st Box of North
        Box the1stBaseOfNorth = Box.createHorizontalBox();
        the1stBaseOfNorth.add(Box.createHorizontalGlue());
        the1stBaseOfNorth.add(jsonNameLabel);
        the1stBaseOfNorth.add(Box.createHorizontalGlue());
        the1stBaseOfNorth.add(loadButton);
        the1stBaseOfNorth.add(saveButton);
        the1stBaseOfNorth.add(openJsonFileButton);
        the1stBaseOfNorth.add(openJsonFolderButton);
        the1stBaseOfNorth.add(copyToTheHumanPanelButton);
        //the1stBaseOfNorth.add(jsonFileNameEditButton);
        the1stBaseOfNorth.add(new PanelMoverPane());
        the1stBaseOfNorth.setPreferredSize(new Dimension(800, 34));
        baseOfNorth.add(the1stBaseOfNorth);

        // The 2nd Box of North
        Box the2ndBaseOfNorth = Box.createHorizontalBox();
        //the2ndBaseOfNorth.add(tFiled_ConfidenceRating);
        //the2ndBaseOfNorth.add(tField_NegativeAnswerCategory);
        the2ndBaseOfNorth.setPreferredSize(new Dimension(800, 33));
        baseOfNorth.add(the2ndBaseOfNorth);

        // The 3rd Box of North
        Box the3rdBaseOfNorth = Box.createHorizontalBox();
        JScrollPane scrollPane_Answer = new JScrollPane(tArea_Answer);
        scrollPane_Answer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_Answer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        the3rdBaseOfNorth.add(scrollPane_Answer);
        the3rdBaseOfNorth.setPreferredSize(new Dimension(800, 75));
        baseOfNorth.add(the3rdBaseOfNorth);

        // The 4th Box of North
        Box the4thBaseOfNorth = Box.createHorizontalBox();

        //JScrollPane scrollPane_Reason = new JScrollPane(tArea_Reason);
        //scrollPane_Reason.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //scrollPane_Reason.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JScrollPane scrollPane_SupportingText = new JScrollPane(tArea_SupportingText);
        scrollPane_SupportingText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_SupportingText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JScrollPane scrollPane_PageLine = new JScrollPane(tArea_Location);
        scrollPane_PageLine.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_PageLine.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        //the4thBaseOfNorth.add(scrollPane_Reason);
        the4thBaseOfNorth.add(scrollPane_SupportingText);
        the4thBaseOfNorth.add(scrollPane_PageLine);

        the4thBaseOfNorth.setPreferredSize(new Dimension(800, 200));
        baseOfNorth.add(the4thBaseOfNorth);

        // The 5th Box
        //Box the5thBaseOfNorth = Box.createHorizontalBox();
        //the5thBaseOfNorth.add(new JLabel("あ"));
        //the5thBaseOfNorth.setPreferredSize(new Dimension(800, 33));
        //baseOfNorth.add(the5thBaseOfNorth);

        // Finalization of North
        add(baseOfNorth, BorderLayout.NORTH);

        /* South Area */
        Box southBox = Box.createHorizontalBox();
        southBox.add(new JLabel(" "));

        //SouthSubPane2
        //Box southSubBox2 = Box.createHorizontalBox();
        //southSubBox2.add(new JLabel(" "));
        //southBox.add(southSubBox2);

        add(southBox, BorderLayout.SOUTH);

        /* Finalization */
        setBorder(BorderFactory.createEtchedBorder());
        //setBorder(BorderFactory.createTitledBorder("A ACNRSL Panel"));
        // BoxLayoutで適切にスクロールするために、固定の高さを設定
        setPreferredSize(new Dimension(800, 375));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 375));

        // 全フィールドの初期化が完了した後にJsonManagerを初期化
        initializeJsonManager();
    }

    public ColorChangeableTextArea gettArea_Answer() {
        return tArea_Answer;
    }

    //public ColorChangeableTextField gettFiled_ConfidenceRating() {
    //    return tFiled_ConfidenceRating;
    //}

    //public ColorChangeableTextField gettField_NegativeAnswerCategory() {
    //    return tField_NegativeAnswerCategory;
    //}

    //public ColorChangeableTextArea gettArea_Reason() {
    //    return tArea_Reason;
    //}

    public ColorChangeableTextArea gettArea_SupportingText() {
        return tArea_SupportingText;
    }

    public ColorChangeableTextArea gettArea_Location() {
        return tArea_Location;
    }

    @Override
    public void saveJson() {

        //initializeJsonManager();

        String answerText = tArea_Answer.getText();
        String supportingText = tArea_SupportingText.getText();
        String pageLineText = tArea_Location.getText();

        // JSONへ書き込み
        jsonManager.setValue(sectionName + "/" + subSectionName + "/answer", answerText);
        jsonManager.setValue(sectionName + "/" + subSectionName + "/supporting_text", supportingText);
        jsonManager.setValue(sectionName + "/" + subSectionName + "/location", pageLineText);

        jsonManager.doSave(false);

    }


    @Override
    public void loadJson() {
        jsonManager.reloadFromDisk();
    }

    @Override
    public void copyToTheHumanDEQAResultPane() {
        // このDEQAResultパネルにおける値を取得
        String answerOfThisPanel = tArea_Answer.getText();
        String supportingTextOfThisPanel = tArea_SupportingText.getText();
        String locationOfThisPanel = tArea_Location.getText();

        ArrayList<One_DEQAResult_Pane_Abs> deqaPanes = managerOfSubTabBasePane.getDeqaPaneArray();
        for (One_DEQAResult_Pane_Abs one_deqaResult_pane_abs : deqaPanes) {

            //「human」というキーワードを含むJSONに相当するDEQAResultパネルに値を複製
            if (one_deqaResult_pane_abs.getJsonName().toLowerCase().contains("human")) {

                ((One_ASL_Style_Pane) one_deqaResult_pane_abs).loadJson(); //これがないと他のSubSectionPanelで変更が加えられた後に一度loadボタンを押さなくてはならなくなる。

                ((One_ASL_Style_Pane) one_deqaResult_pane_abs).gettArea_Answer().setText(answerOfThisPanel);
                ((One_ASL_Style_Pane) one_deqaResult_pane_abs).gettArea_SupportingText().setText(supportingTextOfThisPanel);
                ((One_ASL_Style_Pane) one_deqaResult_pane_abs).gettArea_Location().setText(locationOfThisPanel);
            }
        }
    }

    @Override
    public void resetBackgroundColorOfTAreasTFields() {
        //tField_jsonName.resetBackgroundColor();
        //tField_jsonName.updateDefaultValue();
        //tField_NegativeAnswerCategory.resetBackgroundColor();
        //tField_NegativeAnswerCategory.updateDefaultValue();
        //tFiled_ConfidenceRating.resetBackgroundColor();
        //tFiled_ConfidenceRating.updateDefaultValue();
        tArea_Answer.resetBackgroundColor();
        tArea_Answer.updateDefaultValue();
        //tArea_Reason.resetBackgroundColor();
        //tArea_Reason.updateDefaultValue();
        tArea_SupportingText.resetBackgroundColor();
        tArea_SupportingText.updateDefaultValue();
        tArea_Location.resetBackgroundColor();
        tArea_Location.updateDefaultValue();
    }

    @Override
    public boolean isUpdated() {
            if (tArea_Answer.isUpdated()) {
                return true;
            } else if (tArea_SupportingText.isUpdated()) {
                return true;
            } else if (tArea_Location.isUpdated()) {
                return true;
            } else {
                return false;
            }
    }


    @Override
    public Component getFrame() {
        return null;
    }

    @Override
    public void actionAfterSuccessfullyOpeningJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        String answer = jsonManagerWithConflictSafe.getValueAsString(sectionName + "/" + subSectionName + "/answer");
        //String confidenceRating = jsonManagerWithConflictSafe.getValue(sectionName + "/" + subSectionName + "/confidence_rating");
        //String negativeAnswerCategory = jsonManagerWithConflictSafe.getValue(sectionName + "/" + subSectionName + "/negative_answer_category");
        //String reason = jsonManagerWithConflictSafe.getValue(sectionName + "/" + subSectionName + "/reason");
        String supportingText = jsonManagerWithConflictSafe.getValueAsString(sectionName + "/" + subSectionName + "/supporting_text");
        String pageLine = jsonManagerWithConflictSafe.getValueAsString(sectionName + "/" + subSectionName + "/location");

        // 各フィールドに値を設定
        if (answer != null) tArea_Answer.setText(answer);
        //if (confidenceRating != null) tFiled_ConfidenceRating.setText(confidenceRating);
        //if (negativeAnswerCategory != null) tField_NegativeAnswerCategory.setText(negativeAnswerCategory);
        //if (reason != null) tArea_Reason.setText(reason);
        if (supportingText != null) tArea_SupportingText.setText(supportingText);
        if (pageLine != null) tArea_Location.setText(pageLine);

        // update jsonNameLabel
        jsonNameLabel.setText(jsonName);

        resetBackgroundColorOfTAreasTFields();

        //System.out.println("Successfully open JSON from " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }

    @Override
    public void actionAfterSuccessfullyReloadingJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        actionAfterSuccessfullyOpeningJson(jsonManagerWithConflictSafe);
    }

    @Override
    public void actionAfterFailingToOpenJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        System.err.println("Failed to open JSON from " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }

    @Override
    public void actionAfterSuccessfullySavingJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        System.out.println("Successfully saved JSON to " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
        resetBackgroundColorOfTAreasTFields();
    }

    @Override
    public void actionAfterFailingToSaveJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        System.err.println("Failed to save JSON to " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }


    @Override
    public void actionAfterFailingToReloadJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        System.err.println("Failed to reload JSON from " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }
}
