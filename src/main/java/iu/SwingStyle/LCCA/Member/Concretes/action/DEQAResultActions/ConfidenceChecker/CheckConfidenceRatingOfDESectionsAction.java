package iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.ConfidenceChecker;

import iu.SwingStyle.LCCA.Mediator.action.ActionMediator;
import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMember;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.*;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.DEQAResultPane.One_ACRSL_Style_Pane;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.DEQAResultPane.One_ADCSL_Style_Pane;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.DEQAResultPane.One_DEQAResult_Pane_Abs;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.SubTabsHolderItrfc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class CheckConfidenceRatingOfDESectionsAction extends AbstActionMember {

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

    String authorYear;

    final int KEEP_CURRENT_STATUS_LABEL = 1;
    final int OVERWRITE = 2;
    final int DO_NOTHING = 3;

    public CheckConfidenceRatingOfDESectionsAction(String action_name, String short_name, String authorYear) {
        super(action_name, short_name);
        this.authorYear = authorYear;
    }

    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem()
                .setAccelerator(
                        KeyStroke.getKeyStroke(
                                KeyEvent.VK_C,
                                InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
    }

    @Override
    public void perform(ActionEvent action_event) {
        //System.out.println("");
        //System.out.println("perform() in " + this.getClass().toString() + " was called.");


        int mode = JOptionPane.showOptionDialog(
                null,
                "Confidence Ratingに基づくStatus Label更新",
                "DEパネルについてStatus Labelを更新しますか？",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"既存ラベルは保持しつつ更新", "既存ラベルは破棄して上書き更新", "キャンセル"},
                "キャンセル") + 1; // 1: KEEP_CURRENT_STATUS_LABEL, 2: OVERWRITE, 3: DO_NOTHING (閉じた場合は0→default扱い)

        SubTabsHolderItrfc subTabsHolder;
        if (cholderMediator != null && actionMediator != null) {
            for (String subTabsHolderName : subTabsHolderNames) {

                subTabsHolder = (SubTabsHolderItrfc) cholderMediator.getInstanceOfAMember(subTabsHolderName);

                ArrayList<ManagerOfSubTabBasePane> arrayListOfManagerOfSubTabBasePane = subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane();
                for (ManagerOfSubTabBasePane managerOfSubTabBasePane : arrayListOfManagerOfSubTabBasePane) {

                    int count_of_high = 0;
                    int count_of_medium = 0;
                    int count_of_low = 0;
                    int count_of_unknown = 0;
                    boolean hasRelevantPane = false;
                    ArrayList<One_DEQAResult_Pane_Abs> arrayOf_one_deqaResult_pane = managerOfSubTabBasePane.getDeqaPaneArray();
                    for (One_DEQAResult_Pane_Abs one_deqaResult_pane : arrayOf_one_deqaResult_pane) {
                        if (!one_deqaResult_pane.getJsonName().toLowerCase().contains("human")) {
                            String confidence_rate_of_non_human = "";
                            if (one_deqaResult_pane instanceof One_ACRSL_Style_Pane) {
                                confidence_rate_of_non_human = ((One_ACRSL_Style_Pane) one_deqaResult_pane).gettFiled_ConfidenceRating().getText();
                            } else if (one_deqaResult_pane instanceof One_ADCSL_Style_Pane) {
                                confidence_rate_of_non_human = ((One_ADCSL_Style_Pane) one_deqaResult_pane).gettFiled_ConfidenceRating().getText();
                            }
                            confidence_rate_of_non_human = confidence_rate_of_non_human.toLowerCase();
                            switch (confidence_rate_of_non_human) {
                                case "high":
                                    count_of_high++;
                                    break;
                                case "medium":
                                    count_of_medium++;
                                    break;
                                case "low":
                                    count_of_low++;
                                    break;
                                default:
                                    count_of_unknown++;
                            }
                        }

                        if (one_deqaResult_pane instanceof One_ACRSL_Style_Pane || one_deqaResult_pane instanceof One_ADCSL_Style_Pane) {
                            hasRelevantPane = true;
                        } else {
                            //managerOfSubTabBasePane.getNotePane().setStatusText("");
                        }

                    }

                    if (hasRelevantPane) {
                        String statusSummary = Integer.toString(count_of_high)
                                + Integer.toString(count_of_medium)
                                + Integer.toString(count_of_low)
                                + Integer.toString(count_of_unknown);
                        switch (mode) {
                            case KEEP_CURRENT_STATUS_LABEL:
                                String current_label = managerOfSubTabBasePane.getNotePane().getStatusText();
                                if (current_label.equals("")) {
                                    managerOfSubTabBasePane.getNotePane().setStatusText(statusSummary);
                                }
                                break;
                            case OVERWRITE:
                                managerOfSubTabBasePane.getNotePane().setStatusText(statusSummary);
                                break;
                            case DO_NOTHING:
                                //Do nothing
                                break;
                            default:
                                //Do nothing
                        }
                    }
                }
            }
            // Save once after all updates
            actionMediator.getInstanceOfAMember("save_note_pane").perform(action_event);

        } else {
            System.err.println("cholderMediator or actionMediator is null @" + this.getClass());
        }


    }


    @Override
    public void setCHolderMediator(CHolderMediator cHolderMediator) {
        this.cholderMediator = cHolderMediator;
    }

    @Override
    public void setActionMediator(ActionMediator actionMediator) {
        super.actionMediator = actionMediator;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void doWorkAsMember() {
    }

}
