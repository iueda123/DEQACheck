package iu.LCAC.Member.action.Concretes.DEQAResultActions.SaveAndLoadPaneOrder;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.LCAC.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.One_DEQAResult_Pane_Abs;
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

        loadPaneOrder("DESI", "./settings/" + authorYear + "/PaneOrder/" + "study_identification_of_de" + ".prop");
        loadPaneOrder("DESC", "./settings/" + authorYear + "/PaneOrder/" + "study_characteristics_of_de" + ".prop");
        loadPaneOrder("DERCI", "./settings/" + authorYear + "/PaneOrder/" + "reference_cohort_and_imaging_of_de" + ".prop");
        loadPaneOrder("DENM", "./settings/" + authorYear + "/PaneOrder/" + "normative_modeling_of_de" + ".prop");
        loadPaneOrder("DECAA", "./settings/" + authorYear + "/PaneOrder/" + "clinical_application_and_analysis_of_de" + ".prop");
        loadPaneOrder("DEGN", "./settings/" + authorYear + "/PaneOrder/" + "general_notes_of_de" + ".prop");

        loadPaneOrder("QACM", "./settings/" + authorYear + "/PaneOrder/" + "common_part_of_qa" + ".prop");
        loadPaneOrder("QANM", "./settings/" + authorYear + "/PaneOrder/" + "normative_modeling_part_of_qa" + ".prop");
        loadPaneOrder("QACR", "./settings/" + authorYear + "/PaneOrder/" + "clinical_research_part_of_qa" + ".prop");

        //loadPaneOrder("QASI", "./settings/" + authorYear + "/PaneOrder/" + "study_identification_of_qa" + ".prop");
        //loadPaneOrder("QA1_v6", "./settings/" + authorYear + "/PaneOrder/" + "quality_assessment_1_v6" + ".prop");
        //loadPaneOrder("QA2_v6", "./settings/" + authorYear + "/PaneOrder/" + "quality_assessment_2_v6" + ".prop");
        //loadPaneOrder("QAAC", "./settings/" + authorYear + "/PaneOrder/" + "additional_comments" + ".prop");

    }

    /**
     * Loads pane order for a specific section keyed by member name.
     */
    private void loadPaneOrder(String member_name_key_word, String prop_file_path_str) {
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
        //System.out.println("----- Load pane order of '" + sectionName + "' section -----");

        propManager = createPropertyManager(prop_file_path_str);
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
