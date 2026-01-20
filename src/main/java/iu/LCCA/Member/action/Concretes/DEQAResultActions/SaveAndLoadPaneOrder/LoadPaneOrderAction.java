package iu.LCCA.Member.action.Concretes.DEQAResultActions.SaveAndLoadPaneOrder;

import iu.LCCA.Mediator.action.ActionMediator;
import iu.LCCA.Mediator.componentholder.CHolderMediator;
import iu.LCCA.Member.action.Abstract.AbstActionMember;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.One_DEQAResult_Pane_Abs;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.SubTabsHolderItrfc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.*;

public class LoadPaneOrderAction extends AbstActionMember {

    String authorYear = "Someone20XX";

    public LoadPaneOrderAction(String action_name, String short_name, String authorYear) {
        super(action_name, short_name);
        this.authorYear = authorYear;
    }

    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem()
                .setAccelerator(
                        KeyStroke.getKeyStroke(
                                KeyEvent.VK_L,
                                InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
    }

    @Override
    public void perform(ActionEvent action_event) {
        //System.out.println("");
        //System.out.println("perform() in " + this.getClass().toString() + " was called.");

        for (PaneOrderSection section : PaneOrderSection.deSections()) {
            loadPaneOrder(section);
        }
    }

    /**
     * Loads pane order for a specific section keyed by member name.
     */
    private void loadPaneOrder(PaneOrderSection section) {
        SubTabsHolderItrfc subTabsHolder = section.resolveSubTabsHolder(this.cholderMediator);

        String sectionName = subTabsHolder.getSectionName();
        //System.out.println("----- Load pane order of '" + sectionName + "' section -----");

        propManager = createPropertyManager(section.buildPropPath(authorYear));
        //System.out.println("Properties file '" + deresultpane_order_setting_file_path_str + "' was loaded.");
        //propManager.listUpProperty();

        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : subTabsHolder.getArrayList_of_ManagerOfSubTabBasePane()) {
            String subSectionName = managerOfSubTabBasePane.getSubSectionName();

            JPanel subSectionPanel = managerOfSubTabBasePane.getBasePaneForDEQAResultPanes();
            Component[] components = subSectionPanel.getComponents();

            // リストに変換して任意の順序付け
            ArrayList<Component> currentComponentArray = new ArrayList<>();
            Collections.addAll(currentComponentArray, components);

            String loaded_order = "";
            for (String property_name : propManager.stringPropertyNames()) {
                loaded_order = (String) propManager.getValueOrCreateNew(property_name);
                //System.out.println(property_name + " -> " + loaded_order);
                if (property_name.equals(subSectionName)) break;
            }
            //System.out.println("Loaded Order of " + subSectionName + ": " + loaded_order);
            ArrayList<String> newlyOrderedJsonNameArray = splitToArrayList(loaded_order);

            //並び替え
            ArrayList<Component> newlyOrderedComponents = new ArrayList<>();
            for (String orderedJsonName : newlyOrderedJsonNameArray) {
                for (Component comp : currentComponentArray) {
                    One_DEQAResult_Pane_Abs oneDEResultPane = (One_DEQAResult_Pane_Abs) comp;
                    String jsonName_of_checking_comp = oneDEResultPane.getJsonName();
                    //System.out.println("  Now checking '" + jsonName_of_checking_comp + "'");
                    if (orderedJsonName.equals(jsonName_of_checking_comp)) {
                        //System.out.println("    This was added to newlyOrderedComponents!");
                        newlyOrderedComponents.add(comp);
                        break;
                    }
                }
                //System.out.println(" ");
            }

            //差分からPropertyに定義されていないComponent (One_DEResultPane) を把握
            ArrayList<Component> undefinedInPropComponents = new ArrayList<>(currentComponentArray);
            undefinedInPropComponents.removeAll(newlyOrderedComponents);

            //newlyOrderedComponents の後ろに undefinedInPropComponents を結合
            newlyOrderedComponents.addAll(undefinedInPropComponents);

            // パネルをクリアして再配置
            subSectionPanel.removeAll();
            for (Component comp : newlyOrderedComponents) {
                subSectionPanel.add(comp);
            }
            subSectionPanel.revalidate();
            subSectionPanel.repaint();
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

    public static ArrayList<String> splitToArrayList(String input) {
        if (input == null || input.isEmpty()) {
            return new ArrayList<>();
        }
        // ";" で分割して ArrayList に変換
        return new ArrayList<>(Arrays.asList(input.split(";")));
    }
}
