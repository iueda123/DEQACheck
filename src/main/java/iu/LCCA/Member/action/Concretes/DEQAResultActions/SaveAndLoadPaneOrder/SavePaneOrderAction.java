package iu.LCCA.Member.action.Concretes.DEQAResultActions.SaveAndLoadPaneOrder;

import iu.LCCA.Mediator.action.ActionMediator;
import iu.LCCA.Mediator.componentholder.CHolderMediator;
import iu.LCCA.Member.action.Abstract.AbstActionMember;
import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.One_DEQAResult_Pane_Abs;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.SubTabsHolderItrfc;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DECAA.DECAA_SubTabsHolder;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DEGN.DEGN_SubTabsHolder;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DENM.DENM_SubTabsHolder;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DERCI.DERCI_SubTabsHolder;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DESC.DESC_SubTabsHolder;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DESI.DESI_SubTabsHolder;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.QAResult_v7.QACM.QACM_SubTabsHolder;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.QAResult_v7.QACR.QACR_SubTabsHolder;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.QAResult_v7.QANM.QANM_SubTabsHolder;
import iu.LCCA.Member.componentholder.Concretes.StatusPanel.StatusPanelHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SavePaneOrderAction extends AbstActionMember {

    String authorYear = "Someone20XX";

    public SavePaneOrderAction(String action_name, String short_name, String authorYear) {
        super(action_name, short_name);
        this.authorYear = authorYear;
    }

    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem()
                .setAccelerator(
                        KeyStroke.getKeyStroke(
                                KeyEvent.VK_S,
                                InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println("perform() in " + this.getClass().toString() + " was called.");

        // 全セクションに拡張可能
        savePaneOrder("SI", "./settings/" + authorYear + "/PaneOrder/" + "study_identification_of_de" + ".prop");
        savePaneOrder("SC", "./settings/" + authorYear + "/PaneOrder/" + "study_characteristics_of_de" + ".prop");
        savePaneOrder("RCAI", "./settings/" + authorYear + "/PaneOrder/" + "reference_cohort_and_imaging_of_de" + ".prop");
        savePaneOrder("NM", "./settings/" + authorYear + "/PaneOrder/" + "normative_modeling_of_de" + ".prop");
        savePaneOrder("CAAA", "./settings/" + authorYear + "/PaneOrder/" + "clinical_application_and_analysis_of_de" + ".prop");
        savePaneOrder("GN", "./settings/" + authorYear + "/PaneOrder/" + "general_notes_of_de" + ".prop");

        savePaneOrder("QACM", "./settings/" + authorYear + "/PaneOrder/" + "common_part_of_qa" + ".prop");
        savePaneOrder("QANM", "./settings/" + authorYear + "/PaneOrder/" + "normative_modeling_part_of_qa" + ".prop");
        savePaneOrder("QACR", "./settings/" + authorYear + "/PaneOrder/" + "clinical_research_part_of_qa" + ".prop");

        //savePaneOrder("QASI", "./settings/" + authorYear + "/PaneOrder/" + "study_identification_of_qa" + ".prop");
        //savePaneOrder("QA1_v6", "./settings/" + authorYear + "/PaneOrder/" + "quality_assessment_1_v6" + ".prop");
        //savePaneOrder("QA2_v6", "./settings/" + authorYear + "/PaneOrder/" + "quality_assessment_2_v6" + ".prop");
        //savePaneOrder("QAAC", "./settings/" + authorYear + "/PaneOrder/" + "additional_comments" + ".prop");
    }

    private void savePaneOrder(String member_name_key_word, String prop_file_path_str) {
        AbstCHolderMember member;
        SubTabsHolderItrfc subTabsHolder;
        switch (member_name_key_word) {
            case "DESI":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_DESI");
                subTabsHolder = (DESI_SubTabsHolder) member;
                break;
            case "DESC":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_DESC");
                subTabsHolder = (DESC_SubTabsHolder) member;
                break;
            case "DERCI":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_DERCI");
                subTabsHolder = (DERCI_SubTabsHolder) member;
                break;
            case "DENM":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_DENM");
                subTabsHolder = (DENM_SubTabsHolder) member;
                break;
            case "DECAA":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_dECAA");
                subTabsHolder = (DECAA_SubTabsHolder) member;
                break;
            case "DEGN":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_DEGN");
                subTabsHolder = (DEGN_SubTabsHolder) member;
                break;
            case "QACM":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QACM");
                subTabsHolder = (QACM_SubTabsHolder) member;
                break;
            case "QANM":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QANM");
                subTabsHolder = (QANM_SubTabsHolder) member;
                break;
            case "QACR":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QACR");
                subTabsHolder = (QACR_SubTabsHolder) member;
                break;
            //case "QASI":
            //    member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QASI");
            //    subTabsHolder = (QASI_SubTabsHolder) member;
            //    break;
            //case "QA1_v6":
            //    member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QA1_v6");
            //    subTabsHolder = (QA1_SubTabsHolder) member;
            //    break;
            //case "QA2_v6":
            //    member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QA2_v6");
            //    subTabsHolder = (QA2_SubTabsHolder) member;
            //    break;
            //case "QAAC":
            //    member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QAAC");
            //    subTabsHolder = (QAAC_SubTabsHolder) member;
            //    break;
            default:
                System.err.println("未知のSection指定です" + "@" + this.getClass());
                return;
        }

        String sectionName = subTabsHolder.getSectionName();
        System.out.println("----- Save pane order of '" + sectionName + "' section -----");

        // 全タブ（SubSectionに相当）配置されているコンポーネントの順番を把握し、propertyへ書き込む
        propManager = createPropertyManager(prop_file_path_str);
        ArrayList<String> arrayList_PanelOrder = new ArrayList<>();
        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane()) {
            String subSectionName = managerOfSubTabBasePane.getSubSectionName();
            //System.out.println("subSectionName: " + subSectionName);
            JPanel subSectionPanel = managerOfSubTabBasePane.getBasePaneForDEQAResultPanes();
            Component[] components = subSectionPanel.getComponents();
            for (int i = 0; i < components.length; i++) {
                Object component = components[i];
                if (component instanceof One_DEQAResult_Pane_Abs) {
                    One_DEQAResult_Pane_Abs oneDEResultPane = (One_DEQAResult_Pane_Abs) component;
                    String jsonName = oneDEResultPane.getJsonName();
                    //System.out.println("  DEResultPane No. " + i + ": " + jsonName);
                    arrayList_PanelOrder.add(jsonName);
                }
            }
            propManager.setProperty(subSectionName, joinWithSemicolon(arrayList_PanelOrder));
            arrayList_PanelOrder.clear();
        }
        boolean property_save_result = propManager.writeoutProperties();
        propManager = null;

        //保存が完了したことをフィードバック
        member = this.cholderMediator.getInstanceOfAMember("status_panel_holder");
        StatusPanelHolder statusPanelHolder = (StatusPanelHolder) member;
        if (property_save_result) {
            statusPanelHolder.showAMessageForWhile("The current panel order was saved.", 5000);
        } else {
            statusPanelHolder.showAMessageForWhile("★Saving the current panel order failed★", 5000);
        }
    }


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


    public static String joinWithSemicolon(ArrayList<String> list) {
        return String.join(";", list);
    }

}
