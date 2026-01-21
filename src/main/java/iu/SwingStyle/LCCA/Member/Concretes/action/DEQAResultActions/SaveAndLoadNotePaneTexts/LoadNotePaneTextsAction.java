package iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.SaveAndLoadNotePaneTexts;

import iu.SwingStyle.LCCA.Mediator.action.ActionMediator;
import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMember;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.NotePane;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.SubTabsHolderItrfc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class LoadNotePaneTextsAction extends AbstActionMember {

    public static final String staticMemberName = "load_note_pane";

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
