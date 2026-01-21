package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.SummaryPane;

import iu.SwingStyle.LCCA.Mediator.action.ActionMediator;
import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.*;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.DEQAResultPane.*;
import iu.SwingStyle.LCCA.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.*;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.SubTabsHolderItrfc;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.SummaryPane.units.SummaryBoxGroupPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.TreeMap;

public class SummaryPaneHolder extends AbstCHolderMember {

    public static final String staticMemberName = "summary_pane_holder";

    String[] subTabsHolderNames = {
            "sub_tabs_holder_DESI",
            "sub_tabs_holder_DESC",
            "sub_tabs_holder_DERCI",
            "sub_tabs_holder_DENM",
            "sub_tabs_holder_DECAA",
            "sub_tabs_holder_DEGN"//,
            //"sub_tabs_holder_QACM",
            //"sub_tabs_holder_QANM",
            //"sub_tabs_holder_QACR"
    };

    JPanel basePane = new JPanel();
    Box baseOfNorth = Box.createHorizontalBox();
    Box baseOfCenter = Box.createVerticalBox();

    Box box_NM = Box.createVerticalBox();
    private TreeMap<String, SummaryBoxGroupPane> summaryBoxGroupPaneTreeMap = new TreeMap<>();

    JButton checkProgressButton = new JButton("check progress");

    JButton runScriptButton = new JButton("Run A Script");

    SubTabsHolderItrfc subTabsHolder;


    public SummaryPaneHolder(String cholder_name, String short_name) {
        super(cholder_name, short_name);


        baseOfNorth.add(checkProgressButton);
        checkProgressButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkProgress();
            }
        });

        //baseOfNorth.add(runScriptButton);
        runScriptButton.addActionListener(
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (actionMediator != null) {

                            // 引数なし
                            //actionMediator.getInstanceOfAMember("run_a_bash_script").perform(actionEvent);

                            // 何かしらの文字列を引数として渡すには以下のようにしてActionEventを作成
                            AbstActionMember abstActionMember = actionMediator.getInstanceOfAMember("run_a_bash_script");
                            ActionEvent customEvent_with_ActionNameAndArgs = new ActionEvent(
                                    this,
                                    ActionEvent.ACTION_PERFORMED,
                                    "DummyActionName RunBashPanelHolderから渡した引数1 " + "RunBashPanelHolderから渡した引数2 "

                                    // AbstrActionMember#getActionCommandAndArgs()の自分が決めた仕様で、
                                    // ActionEventオブジェクトに格納された文字列の１つ目の要素はアクション名、
                                    // ２つ目以降の要素は引数扱い。
                                    // なので ここでは１つ目を DummyActionName としている。

                            );
                            abstActionMember.perform(customEvent_with_ActionNameAndArgs);

                        } else {
                            System.err.println("Action Starter is null! @ " + this.getClass().toString());
                        }
                    }
                });

        baseOfCenter.add(box_NM);

        // Finalization
        basePane.setLayout(new BorderLayout());
        basePane.add(baseOfNorth, BorderLayout.NORTH);
        basePane.add(baseOfCenter, BorderLayout.CENTER);

    }

    private void checkProgress() {
        for (String subTabsHolderName : subTabsHolderNames) {

            if (cholderMediator != null) {
                subTabsHolder = (SubTabsHolderItrfc) cholderMediator.getInstanceOfAMember(subTabsHolderName);

                ArrayList<ManagerOfSubTabBasePane> arrayListOfManagerOfSubTabBasePane = subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane();
                for (ManagerOfSubTabBasePane managerOfSubTabBasePane : arrayListOfManagerOfSubTabBasePane) {
                    String subSectionName = managerOfSubTabBasePane.getSubSectionName();

                    //System.out.println("subSectionName: " + subSectionName );

                    ArrayList<One_DEQAResult_Pane_Abs> arrayOf_one_deqaResult_pane = managerOfSubTabBasePane.getDeqaPaneArray();
                    for (One_DEQAResult_Pane_Abs one_deqaResult_pane : arrayOf_one_deqaResult_pane) {
                        if (one_deqaResult_pane.getJsonName().toLowerCase().contains("human")) {

                            String value = "";
                            if (one_deqaResult_pane instanceof One_A_Style_Pane) {
                                value = ((One_A_Style_Pane) one_deqaResult_pane).gettArea_Answer().getText();
                            } else if (one_deqaResult_pane instanceof One_ASL_Style_Pane) {
                                value = ((One_ASL_Style_Pane) one_deqaResult_pane).gettArea_Answer().getText();
                            } else if (one_deqaResult_pane instanceof One_ACRSL_Style_Pane) {
                                value = ((One_ACRSL_Style_Pane) one_deqaResult_pane).gettArea_Answer().getText();
                            } else if (one_deqaResult_pane instanceof One_ADCSL_Style_Pane) {
                                value = ((One_ADCSL_Style_Pane) one_deqaResult_pane).gettArea_Answer().getText();
                            }

                            //System.out.println("    value: " + value );

                            // SummaryBoxUnit summaryBoxUnit = null;
                            if (value.equals("")) {
                                summaryBoxGroupPaneTreeMap.get(subTabsHolderName).setStatus(subSectionName, "□", "");
                            } else {
                                summaryBoxGroupPaneTreeMap.get(subTabsHolderName).setStatus(subSectionName, "■", value);
                            }
                        }
                    }
                }
            }
        }

    }


    @Override
    public void postInitialize() {

        for (String memberName : subTabsHolderNames) {

            if (cholderMediator != null) {
                ArrayList<ManagerOfSubTabBasePane> arrayListOfManagerOfSubTabBasePane = ((SubTabsHolderItrfc) cholderMediator.getInstanceOfAMember(memberName)).getArrayList_of_ManagerOfSubTabBasePane();

                SummaryBoxGroupPane summaryBoxGroupPane = new SummaryBoxGroupPane(arrayListOfManagerOfSubTabBasePane);
                summaryBoxGroupPaneTreeMap.put(memberName, summaryBoxGroupPane);
                baseOfCenter.add(summaryBoxGroupPane);
            }

        }

        // checkProgress() is now called from Initialize_All_TabPanes_Action after all data is loaded
        //checkProgress();
        // ToDo: ここで checkProgress() を読んでも正しいAnswerの記入状況を反映しない。原因は恐らく summaryPanelHolder の postInitialize() が最後ではないことが原因。
        //  どうやったら summaryPaneHolderのpostInitialize() が最終とできるのか？ "summary_pane_holder" を "99_summary_pane_holder" とかにすると良いのかもしれない。

    }

    @Override
    public JComponent getBaseComponent() {
        return this.basePane;
    }

    public void setTextToTheButton(String text) {
        this.runScriptButton.setText(text);
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
}
