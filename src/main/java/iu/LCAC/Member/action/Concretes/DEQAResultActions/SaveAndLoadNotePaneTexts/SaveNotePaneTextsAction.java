package iu.LCAC.Member.action.Concretes.DEQAResultActions.SaveAndLoadNotePaneTexts;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.NotePane;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.SubTabsHolderItrfc;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DECAA.DECAA_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DEGN.DEGN_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DENM.DENM_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DERCI.DERCI_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DESC.DESC_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DESI.DESI_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v7.QACM.QACM_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v7.QACR.QACR_SubTabsHolder;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.QAResult_v7.QANM.QANM_SubTabsHolder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SaveNotePaneTextsAction extends AbstActionMember {

    String authorYear = "Someone20XX";

    public SaveNotePaneTextsAction(String action_name, String short_name, String authorYear) {
        super(action_name, short_name);
        this.authorYear = authorYear;
    }

    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem()
                .setAccelerator(
                        KeyStroke.getKeyStroke(
                                KeyEvent.VK_S,
                                InputEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println("perform() in " + this.getClass().toString() + " was called.");

        // 全セクションに拡張可能
        saveNotePaneState("DESI", "./settings/" + authorYear + "/NotePane/" + "study_identification_of_de" + ".prop");
        saveNotePaneState("DESC", "./settings/" + authorYear + "/NotePane/" + "study_characteristics_of_de" + ".prop");
        saveNotePaneState("DERCI", "./settings/" + authorYear + "/NotePane/" + "reference_cohort_and_imaging_of_de" + ".prop");
        saveNotePaneState("DENM", "./settings/" + authorYear + "/NotePane/" + "normative_modeling_of_de" + ".prop");
        saveNotePaneState("DECAA", "./settings/" + authorYear + "/NotePane/" + "clinical_application_and_analysis_of_de" + ".prop");
        saveNotePaneState("DEGN", "./settings/" + authorYear + "/NotePane/" + "general_notes_of_de" + ".prop");

        saveNotePaneState("QACM", "./settings/" + authorYear + "/NotePane/" + "common_part_of_qa" + ".prop");
        saveNotePaneState("QANM", "./settings/" + authorYear + "/NotePane/" + "normative_modeling_part_of_qa" + ".prop");
        saveNotePaneState("QACR", "./settings/" + authorYear + "/NotePane/" + "clinical_research_part_of_qa" + ".prop");

    }

    private void saveNotePaneState(String member_name_key_word, String prop_file_path_str) {
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
            default:
                System.err.println("未知のSection指定です" + "@" + this.getClass());
        }

        String sectionName = subTabsHolder.getSectionName();
        System.out.println("----- Save texts on NotePanes of '" + sectionName + "' section -----");

        // 全タブ（SubSectionに相当）配置されているコンポーネントの順番を把握し、propertyへ書き込む
        propManager = createPropertyManager(prop_file_path_str);
        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane()) {
            String subSectionName = managerOfSubTabBasePane.getSubSectionName();
            //System.out.println("Now registering NotePane of  '" + subSectionName + "'");
            NotePane notePane = managerOfSubTabBasePane.getNotePane();
            propManager.setProperty(subSectionName + ".status", notePane.getStatusText());
            propManager.setProperty(subSectionName + ".note", notePane.getNoteText());
            notePane.resetBackgroundColors();
            notePane.updateDefaultValues();
            notePane.updateTabTitle();
        }
        propManager.writeoutProperties();
        propManager = null;
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

}
