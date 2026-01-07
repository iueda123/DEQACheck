package iu.LCCA.Member.action.Concretes.DEQAResultActions.SaveAndLoadNotePaneTexts;

import iu.LCCA.Mediator.action.ActionMediator;
import iu.LCCA.Mediator.componentholder.CHolderMediator;
import iu.LCCA.Member.action.Abstract.AbstActionMember;
import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.NotePane;
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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class LoadNotePaneTextsAction extends AbstActionMember {

    String authorYear = "Someone20XX";

    public LoadNotePaneTextsAction(String action_name, String short_name, String authorYear) {
        super(action_name, short_name);
        this.authorYear = authorYear;
    }

    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem()
                .setAccelerator(
                        KeyStroke.getKeyStroke(
                                KeyEvent.VK_L,
                                InputEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void perform(ActionEvent action_event) {
        //System.out.println("");
        //System.out.println("perform() in " + this.getClass().toString() + " was called.");

        // 全セクションに拡張可能
        loadNotePaneTexts("DESI", "./settings/" + authorYear + "/NotePane/" + "study_identification_of_de" + ".prop");
        loadNotePaneTexts("DESC", "./settings/" + authorYear + "/NotePane/" + "study_characteristics_of_de" + ".prop");
        loadNotePaneTexts("DERCI", "./settings/" + authorYear + "/NotePane/" + "reference_cohort_and_imaging_of_de" + ".prop");
        loadNotePaneTexts("DENM", "./settings/" + authorYear + "/NotePane/" + "normative_modeling_of_de" + ".prop");
        loadNotePaneTexts("DECAA", "./settings/" + authorYear + "/NotePane/" + "clinical_application_and_analysis_of_de" + ".prop");
        loadNotePaneTexts("DEGN", "./settings/" + authorYear + "/NotePane/" + "general_notes_of_de" + ".prop");

        //loadNotePaneTexts("QACM", "./settings/" + authorYear + "/NotePane/" + "common_part_of_qa" + ".prop");
        //loadNotePaneTexts("QANM", "./settings/" + authorYear + "/NotePane/" + "normative_modeling_part_of_qa" + ".prop");
        //loadNotePaneTexts("QACR", "./settings/" + authorYear + "/NotePane/" + "clinical_research_part_of_qa" + ".prop");
        //loadNotePaneTexts("QASI", "./settings/" + authorYear + "/NotePane/" + "study_identification_of_qa" + ".prop");
        //loadNotePaneTexts("QA1_v6", "./settings/" + authorYear + "/NotePane/" + "quality_assessment_1_v6" + ".prop");
        //loadNotePaneTexts("QA2_v6", "./settings/" + authorYear + "/NotePane/" + "quality_assessment_2_v6" + ".prop");
        //loadNotePaneTexts("QAAC", "./settings/" + authorYear + "/NotePane/" + "additional_comments" + ".prop");
    }

    /**
     * REFERENCE COHORT AND IMAGING
     */
    private void loadNotePaneTexts(String member_name_key_word, String prop_file_path_str) {

        AbstCHolderMember member = null;
        SubTabsHolderItrfc subTabsHolder = null;
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
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_DECAA");
                subTabsHolder = (DECAA_SubTabsHolder) member;
                break;
            case "DEGN":
                member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_DEGN");
                subTabsHolder = (DEGN_SubTabsHolder) member;
                break;
            //case "QACM":
            //     member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QACM");
            //     subTabsHolder = (QACM_SubTabsHolder) member;
            //     break;
            //case "QANM":
            //     member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QANM");
            //     subTabsHolder = (QANM_SubTabsHolder) member;
            //     break;
            //case "QACR":
            //     member = this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QACR");
            //     subTabsHolder = (QACR_SubTabsHolder) member;
            //     break;
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
        }

        String sectionName = subTabsHolder.getSectionName();
        //System.out.println("----- Load texts on NotePanes of '" + sectionName + "' section -----");

        propManager = createPropertyManager(prop_file_path_str);
        //System.out.println("Properties file '" + deresultpane_order_setting_file_path_str + "' was loaded.");
        //propManager.listUpProperty();

        String loaded_text = "";
        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane()) {
            String subSectionName = managerOfSubTabBasePane.getSubSectionName();
            //System.out.println("Now registering NotePane of  '" + subSectionName + "'");
            NotePane notePane = managerOfSubTabBasePane.getNotePane();

            for (String property_name : propManager.stringPropertyNames()) {
                loaded_text = propManager.getValueOrCreateNew(property_name);
                if (property_name.equals(subSectionName + "." + "status")) {
                    notePane.setStatusText(loaded_text);
                } else if (property_name.equals(subSectionName + "." + "note")) {
                    notePane.setNoteText(loaded_text);
                }
            }
            notePane.resetBackgroundColors();
            notePane.updateDefaultValues();
            notePane.updateTabTitle();
        }
        propManager = null; //Propの外部更新時のため（毎回新しいPropを呼ぶため）dispose
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
