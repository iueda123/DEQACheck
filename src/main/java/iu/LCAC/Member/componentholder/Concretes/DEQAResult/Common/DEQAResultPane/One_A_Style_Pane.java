package iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane;

import iu.LCAC.Utils.ColorChangeableTextArea;
import iu.LCAC.Utils.JsonManagerWithConflictSafe.JsonManagerWithConflictSafe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class One_A_Style_Pane extends One_DEQAResult_Pane_Abs {

    private ColorChangeableTextArea tArea_Answer = new ColorChangeableTextArea("Answer");


    private final String tooltipForAnswer = "This is the answer.";


    public One_A_Style_Pane(String jsonFolderPathStr, String jsonName, String sectionName, String subSectionName) {

        super(jsonFolderPathStr, jsonName, sectionName, subSectionName);

        // BashScriptを呼び出すActionMemberを利用すべく
        // CHolderMediator や ActionMediator を このクラス内に保持したいと
        // このコンストラクタを作った。しかし、どうやって CHolderMediatorやActionMediatorを
        // nullじゃない状態で持ってくるかの良い方法を思いつけず。
        //　ひとまずこのコンストラクタを残しておく。

        // Answer Sub Sub Section
        tArea_Answer.setLineWrap(true);
        tArea_Answer.setWrapStyleWord(true);
        tArea_Answer.setToolTipText(tooltipForAnswer);

        setLayout(new BorderLayout());

        /* Setup North Area */
        Box northBox = Box.createVerticalBox();

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
        add(the1stBaseOfNorth, BorderLayout.NORTH);

        /* Setup Center Area */
        JPanel the1stBaseOfCenter = new JPanel(new BorderLayout());
        the1stBaseOfCenter.setPreferredSize(new Dimension(800, 150));
        JScrollPane scrollPane_Answer = new JScrollPane(tArea_Answer);
        scrollPane_Answer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_Answer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        the1stBaseOfCenter.add(scrollPane_Answer, BorderLayout.CENTER);

        northBox.add(the1stBaseOfCenter);

        add(the1stBaseOfCenter, BorderLayout.CENTER);

        //add(northBox, BorderLayout.NORTH);

        /* Setup Center Area */

        /* Finalization */
        setBorder(BorderFactory.createEtchedBorder());
        //setBorder(BorderFactory.createTitledBorder(jsonName));
        // BoxLayoutで適切にスクロールするために、固定の高さを設定
        setPreferredSize(new Dimension(800, 200));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        // 全フィールドの初期化が完了した後にJsonManagerを初期化
        initializeJsonManager();
    }


    @Override
    public void saveJson() {
        String answerText = tArea_Answer.getText();
        //System.out.println("answerText: " + answerText);
        jsonManager.setValue(sectionName + "/" + subSectionName, answerText);
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

        ArrayList<One_DEQAResult_Pane_Abs> deqaPanes = managerOfSubTabBasePane.getDeqaPaneArray();
        for (One_DEQAResult_Pane_Abs one_deqaResult_pane_abs : deqaPanes) {

            //「human」というキーワードを含むJSONに相当するDEQAResultパネルに値を複製
            if (one_deqaResult_pane_abs.getJsonName().toLowerCase().contains("human")) {

                ((One_A_Style_Pane) one_deqaResult_pane_abs).loadJson(); //これがないと他のSubSectionPanelで変更が加えられた後に一度loadボタンを押さなくてはならなくなる。

                ((One_A_Style_Pane) one_deqaResult_pane_abs).gettArea_Answer().setText(answerOfThisPanel);
            }
        }
    }

    public ColorChangeableTextArea gettArea_Answer() {
        return tArea_Answer;
    }

    @Override
    public void resetBackgroundColorOfTAreasTFields() {
        tArea_Answer.resetBackgroundColor();
        tArea_Answer.updateDefaultValue();
    }

    @Override
    public boolean isUpdated() {
        if (tArea_Answer.isUpdated()) {
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

        String answer = jsonManagerWithConflictSafe.getValue(sectionName + "/" + subSectionName + "/answer");
        if (answer != null) tArea_Answer.setText(answer);

        // update jsonNameLabel
        jsonNameLabel.setText(jsonName);

        resetBackgroundColorOfTAreasTFields();

        //System.out.println("Successfully open JSON from " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }

    @Override
    public void actionAfterFailingToOpenJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        System.err.println("Failed to open JSON from " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }

    @Override
    public void actionAfterSuccessfullySavingJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        //System.out.println("Successfully saved JSON to " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
        resetBackgroundColorOfTAreasTFields();
    }

    @Override
    public void actionAfterFailingToSaveJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        System.err.println("Failed to save JSON to " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }

    @Override
    public void actionAfterSuccessfullyReloadingJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        String answer = jsonManagerWithConflictSafe.getValue(sectionName + "/" + subSectionName);
        //System.out.println("answer: " + answer);
        if (answer != null) tArea_Answer.setText(answer);
        resetBackgroundColorOfTAreasTFields();
        //System.out.println("Successfully loaded JSON from " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }

    @Override
    public void actionAfterFailingToReloadJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        System.err.println("Failed to reload JSON from " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }
}
