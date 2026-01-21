package iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.Init_All_Section;

import iu.SwingStyle.LCCA.Mediator.action.ActionMediator;
import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMember;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.DEQAResultPane.One_DEQAResult_Pane_Abs;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DECAA.DECAA_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DEGN.DEGN_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DENM.DENM_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DERCI.DERCI_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DESC.DESC_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DESI.DESI_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.QAResult_v7.QACM.QACM_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.QAResult_v7.QACR.QACR_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.QAResult_v7.QANM.QANM_SubTabsHolder;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Initialize_All_TabPanes_Action extends AbstActionMember {

    public Initialize_All_TabPanes_Action(String action_name, String short_name) {
        super(action_name, short_name);
    }

    @Override
    protected void setAcceleratorKeyStroke() {
    }

    @Override
    public void perform(ActionEvent action_event) {
        System.out.println("perform() in " + this.getClass().toString() + " was called.");

        /* 1. Study Identification of DE */
        DESI_SubTabsHolder subTabsHolder_DESI = (DESI_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_DESI");
        if (subTabsHolder_DESI != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_DESI.getArrayList_of_ManagerOfSubTabBasePane();
            for (ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane) {
                for (One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDeqaPaneArray()) {
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("sub_tabs_holder_DESI is null.");
        }

        /* 2. Study Characteristics  */
        DESC_SubTabsHolder subTabsHolder_DESC = (DESC_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_DESC");
        if (subTabsHolder_DESC != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_DESC.getArrayList_of_ManagerOfSubTabBasePane();
            for (ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane) {
                for (One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDeqaPaneArray()) {
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("sub_tabs_holder_DESC is null.");
        }

        /* 3. Reference Cohort and Imaging */
        DERCI_SubTabsHolder subTabsHolder_RCAI = (DERCI_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_DERCI");
        if (subTabsHolder_RCAI != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_RCAI.getArrayList_of_ManagerOfSubTabBasePane();
            for (ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane) {
                for (One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDeqaPaneArray()) {
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("sub_tabs_holder_RCAI is null.");
        }

        /* 4. Normative Modeling */
        DENM_SubTabsHolder subTabsHolder_DENM = (DENM_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_DENM");
        if (subTabsHolder_DENM != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_DENM.getArrayList_of_ManagerOfSubTabBasePane();
            for (ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane) {
                for (One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDeqaPaneArray()) {
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("sub_tabs_holder_DENM is null.");
        }

        /* 5. Clinical Application and Analysis */
        DECAA_SubTabsHolder subTabsHolder_DECAA = (DECAA_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_DECAA");
        if (subTabsHolder_DECAA != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_DECAA.getArrayList_of_ManagerOfSubTabBasePane();
            for (ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane) {
                for (One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDeqaPaneArray()) {
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("sub_tabs_holder_DECAA is null.");
        }

        /* 6. General Notes of DE */
        DEGN_SubTabsHolder subTabsHolder_DEGN = (DEGN_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_DEGN");
        if (subTabsHolder_DEGN != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_DEGN.getArrayList_of_ManagerOfSubTabBasePane();
            for (ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane) {
                for (One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDeqaPaneArray()) {
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("sub_tabs_holder_DEGN is null.");
        }

        //--------------------------

        /* 7. Common Part of QA */
        QACM_SubTabsHolder subTabsHolder_QACM = (QACM_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QACM");
        if (subTabsHolder_QACM != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_QACM.getArrayList_of_ManagerOfSubTabBasePane();
            for (ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane) {
                for (One_DEQAResult_Pane_Abs deqa_result_pane : managerOfSubTabBasePane.getDeqaPaneArray()) {
                    deqa_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("subTabsHolder_QACM is null.");
        }

        /* 8. Normative Modeling Part of QA */
        QANM_SubTabsHolder subTabsHolder_QANM = (QANM_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QANM");
        if (subTabsHolder_QANM != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_QANM.getArrayList_of_ManagerOfSubTabBasePane();
            for (ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane) {
                for (One_DEQAResult_Pane_Abs deqa_result_pane : managerOfSubTabBasePane.getDeqaPaneArray()) {
                    deqa_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("subTabsHolder_QANM is null.");
        }

        /* 9. Clinical Research Part of QA */
        QACR_SubTabsHolder subTabsHolder_QACR = (QACR_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QACR");
        if (subTabsHolder_QACR != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_QACR.getArrayList_of_ManagerOfSubTabBasePane();
            for (ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane) {
                for (One_DEQAResult_Pane_Abs deqa_result_pane : managerOfSubTabBasePane.getDeqaPaneArray()) {
                    deqa_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("subTabsHolder_QACR is null.");
        }

        /* 99 Update summary pane after all data is loaded */
        //if (this.cholderMediator != null) {
        Object summaryPaneHolder = this.cholderMediator.getInstanceOfAMember("summary_pane_holder");
        if (summaryPaneHolder != null) {
            try {
                summaryPaneHolder.getClass().getMethod("checkProgress").invoke(summaryPaneHolder);
            } catch (Exception e) {
                System.err.println("Failed to call checkProgress(): " + e.getMessage());
            }
        }
        //}

        /* 7. Study Identification of QA */
        /*
        QASI_SubTabsHolder subTabsHolder_QASI = (QASI_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QASI");
        if (subTabsHolder_QASI != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_QASI.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDeqaPaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("subTabsHolder_QASI is null.");
        }
        */


        /* 8. Quality Assessment Part 1 (v6) */
        /*
        QA1_SubTabsHolder subTabsHolder_QA1 = (QA1_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QA1_v6");
        if (subTabsHolder_QA1 != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_QA1.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDeqaPaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("subTabsHolder_QA1 is null.");
        }
        */

        /* 9. Quality Assessment Part 2 (v6) */
        /*
        QA2_SubTabsHolder subTabsHolder_QA2 = (QA2_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QA2_v6");
        if (subTabsHolder_QA2 != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_QA2.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDeqaPaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("subTabsHolder_QA2 is null.");
        }
        */

        /* 10. Additional Comment of QA */
        /*
        QAAC_SubTabsHolder subTabsHolder_QAAC = (QAAC_SubTabsHolder) this.cholderMediator.getInstanceOfAMember("sub_tabs_holder_QAAC");
        if (subTabsHolder_QAAC != null) {
            ArrayList<ManagerOfSubTabBasePane> managersOfSubTabBasePane = subTabsHolder_QAAC.getArrayList_of_ManagerOfSubTabBasePane();
            for(ManagerOfSubTabBasePane managerOfSubTabBasePane : managersOfSubTabBasePane){
                for(One_DEQAResult_Pane_Abs de_result_pane : managerOfSubTabBasePane.getDeqaPaneArray()){
                    de_result_pane.loadJson();
                }
            }
        } else {
            System.err.println("subTabsHolder_QAAC is null.");
        }
        */
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
