package iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.SaveAndLoadPaneOrder;

import iu.SwingStyle.LCCA.Mediator.action.ActionMediator;
import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMember;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.DEQAResultPane.One_DEQAResult_Pane_Abs;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.SubTabsHolderItrfc;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.StatusPanel.StatusPanelHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SavePaneOrderAction extends AbstActionMember {

    public static final String staticMemberName = "save_pane_order";

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
        for (PaneOrderSection section : PaneOrderSection.saveTargets()) {
            savePaneOrder(section);
        }

    }

    private void savePaneOrder(PaneOrderSection section) {
        SubTabsHolderItrfc subTabsHolder = section.resolveSubTabsHolder(this.cholderMediator);

        String sectionName = subTabsHolder.getSectionName();
        System.out.println("----- Save pane order of '" + sectionName + "' section -----");

        // 全タブ（SubSectionに相当）配置されているコンポーネントの順番を把握し、propertyへ書き込む
        propManager = createPropertyManager(section.buildPropPath(authorYear));
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
        StatusPanelHolder statusPanelHolder = (StatusPanelHolder) this.cholderMediator.getInstanceOfAMember("status_panel_holder");
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
