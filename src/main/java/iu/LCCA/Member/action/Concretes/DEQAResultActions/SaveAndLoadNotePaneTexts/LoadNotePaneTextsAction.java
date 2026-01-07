package iu.LCCA.Member.action.Concretes.DEQAResultActions.SaveAndLoadNotePaneTexts;

import iu.LCCA.Mediator.action.ActionMediator;
import iu.LCCA.Mediator.componentholder.CHolderMediator;
import iu.LCCA.Member.action.Abstract.AbstActionMember;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.NotePane;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.SubTabsHolderItrfc;

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
        for (NotePaneSection section : NotePaneSection.deSections()) {
            loadNotePaneTexts(section);
        }

        //loadNotePaneTexts("QACM", "./data/" + authorYear + "/NotePane/" + "common_part_of_qa" + ".prop");
        //loadNotePaneTexts("QANM", "./data/" + authorYear + "/NotePane/" + "normative_modeling_part_of_qa" + ".prop");
        //loadNotePaneTexts("QACR", "./data/" + authorYear + "/NotePane/" + "clinical_research_part_of_qa" + ".prop");
        //loadNotePaneTexts("QASI", "./data/" + authorYear + "/NotePane/" + "study_identification_of_qa" + ".prop");
        //loadNotePaneTexts("QA1_v6", "./data/" + authorYear + "/NotePane/" + "quality_assessment_1_v6" + ".prop");
        //loadNotePaneTexts("QA2_v6", "./data/" + authorYear + "/NotePane/" + "quality_assessment_2_v6" + ".prop");
        //loadNotePaneTexts("QAAC", "./data/" + authorYear + "/NotePane/" + "additional_comments" + ".prop");
    }

    /**
     * REFERENCE COHORT AND IMAGING
     */
    private void loadNotePaneTexts(NotePaneSection section) {

        SubTabsHolderItrfc subTabsHolder = section.resolveSubTabsHolder(this.cholderMediator);

        String sectionName = subTabsHolder.getSectionName();
        //System.out.println("----- Load texts on NotePanes of '" + sectionName + "' section -----");

        propManager = createPropertyManager(section.buildPropPath(authorYear));
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
