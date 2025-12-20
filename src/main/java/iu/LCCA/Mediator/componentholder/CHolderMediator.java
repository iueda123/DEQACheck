package iu.LCCA.Mediator.componentholder;

import iu.LCCA.Mediator.MediatorIntrfc;
import iu.LCCA.Mediator.MemberFactoryLoader;
import iu.LCCA.Mediator.action.ActionMediator;
import iu.LCCA.Member.MemberIntrfc;
import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMemberFactory;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.WithNotebookLMPane.WithNotebookLMPanelHolderFactory;
import iu.LCCA.Member.componentholder.Concretes.MainWindow.MainWindowHolderFactory;
import iu.LCCA.Member.componentholder.Concretes.StatusPanel.StatusPanelHolderFactory;
import iu.LCCA.Member.componentholder.Concretes.Sample.ButtonPanel.ButtonPanelHolderFactory;
import iu.LCCA.Member.componentholder.Concretes.Sample.RunBashPanel.RunBashPanelHolderFactory;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DESI.DESI_SubTabsHolderFactory;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DESC.DESC_SubTabsHolderFactory;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DERCI.DERCI_SubTabsHolderFactory;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DENM.DENM_SubTabsHolderFactory;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DECAA.DECAA_SubTabsHolderFactory;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DEGN.DEGN_SubTabsHolderFactory;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Explanation.ExplanationPanelHolderFactory;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.QAResult_v7.QACM.QACM_SubTabsHolderFactory;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.QAResult_v7.QANM.QANM_SubTabsHolderFactory;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.QAResult_v7.QACR.QACR_SubTabsHolderFactory;
import iu.LCCA.Member.componentholder.Concretes.Sample.CheckboxPanel.CheckboxPanelHolderFactory;
import iu.LCCA.Member.componentholder.Concretes.Sample.TextField.TextFieldPanelHolderFactory;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.SummaryPane.SummaryPaneHolderFactory;

import java.util.*;

/**
 * メインフレームに対する各種操作を一手に引き受けるクラスのためのアブストラクトクラス。
 *
 * <p>もう一つの役割は、 AbstActionMember Class を継承したクラスが呼び出されたときに、一旦Eventを引き受けて、 もしショートカット再設定モードに入っているならば、
 * 呼び出されたAction_A Classのサブクラスのperform()を抑制するという機能。
 */
public class CHolderMediator implements MediatorIntrfc {

    // これは文字列からComponentのインスタンスを取り出すために必要
    private final HashMap<String, MemberIntrfc> memberMap = new HashMap<>();

    public CHolderMediator(String authorYear) {
        createMembers(authorYear);
    }

    @Override
    public void createMembers() {
        createMembers("");
    }


    @Override
    public void createMembers(String... authorYears) {

        //------------------------------------------
        /* **** Core **** */

        AbstCHolderMemberFactory chMemberFactory;
        // AbstCHolderMember mainWindowHolder = new
        // MainWindowHolderFactory().create("main_window_holder");
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        MainWindowHolderFactory.class.getName(),
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember mainWindowHolder =
                chMemberFactory.createCHolder("main_window_holder", "main window", authorYears[0]);
        mainWindowHolder.setCHolderMediator(this);
        mainWindowHolder.initialize();
        registerMemberToMap(mainWindowHolder);

        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        StatusPanelHolderFactory.class.getName(),
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember statusPanelHolder =
                chMemberFactory.createCHolder("status_panel_holder", "Status Panel Holder", authorYears[0]);
        statusPanelHolder.setCHolderMediator(this);
        statusPanelHolder.initialize();
        registerMemberToMap(statusPanelHolder);

        //------------------------------------------
        /* **** Samples **** */

        /* **** ButtonPanel **** */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        ButtonPanelHolderFactory.class.getName(),
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember buttonPanelHolder =
                chMemberFactory.createCHolder("button_panel_holder", "Button Panel Holder", authorYears[0]);
        buttonPanelHolder.setCHolderMediator(this);
        buttonPanelHolder.initialize();
        registerMemberToMap(buttonPanelHolder);

        /* **** CheckboxPanel **** */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        CheckboxPanelHolderFactory.class.getName(),
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember checkboxPanelHolder =
                chMemberFactory.createCHolder("checkbox_panel_holder", "Checkbox Panel Holder", authorYears[0]);
        checkboxPanelHolder.setCHolderMediator(this);
        checkboxPanelHolder.initialize();
        registerMemberToMap(checkboxPanelHolder);

        /* **** TextField **** */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        TextFieldPanelHolderFactory.class.getName(),
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember textFieldPanelHolder =
                chMemberFactory.createCHolder("text_field_panel_holder", "Checkbox Panel Holder", authorYears[0]);
        textFieldPanelHolder.setCHolderMediator(this);
        textFieldPanelHolder.initialize();
        registerMemberToMap(textFieldPanelHolder);

        /* **** RunBashPanel **** */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        RunBashPanelHolderFactory.class.getName(),
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember runBashPanelHolder =
                chMemberFactory.createCHolder("run_bash_panel_holder", "Run Bash Panel Holder", authorYears[0]);
        runBashPanelHolder.setCHolderMediator(this);
        runBashPanelHolder.initialize();
        registerMemberToMap(runBashPanelHolder);

        //------------------------------------------

        /* 1. Study Identification of DE */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        DESI_SubTabsHolderFactory.class.getName(),
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_DESI =
                chMemberFactory.createCHolder("sub_tabs_holder_DESI", "sub_tabs_holder_DESI", authorYears[0]);
        subTabsHoldFactory_DESI.setCHolderMediator(this);
        subTabsHoldFactory_DESI.initialize();
        registerMemberToMap(subTabsHoldFactory_DESI);

        /* 2. Study Characteristics */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        DESC_SubTabsHolderFactory.class.getName(),
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_DESC =
                chMemberFactory.createCHolder("sub_tabs_holder_DESC", "sub_tabs_holder_DESC", authorYears[0]);
        subTabsHoldFactory_DESC.setCHolderMediator(this);
        subTabsHoldFactory_DESC.initialize();
        registerMemberToMap(subTabsHoldFactory_DESC);

        /* 3. Reference Cohort and Imaging */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        DERCI_SubTabsHolderFactory.class.getName(),
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_RCI =
                chMemberFactory.createCHolder("sub_tabs_holder_DERCI", "sub_tabs_holder_DERCI", authorYears[0]);
        subTabsHoldFactory_RCI.setCHolderMediator(this);
        subTabsHoldFactory_RCI.initialize();
        registerMemberToMap(subTabsHoldFactory_RCI);

        /* 4. Normative Modeling */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        DENM_SubTabsHolderFactory.class.getName(),
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_DENM =
                chMemberFactory.createCHolder("sub_tabs_holder_DENM", "sub_tabs_holder_DENM", authorYears[0]);
        subTabsHoldFactory_DENM.setCHolderMediator(this);
        subTabsHoldFactory_DENM.initialize();
        registerMemberToMap(subTabsHoldFactory_DENM);


        /* 5. Clinical Application and Analysis */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        DECAA_SubTabsHolderFactory.class.getName(),
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_DECAA =
                chMemberFactory.createCHolder("sub_tabs_holder_DECAA", "sub_tabs_holder_DECAA", authorYears[0]);
        subTabsHoldFactory_DECAA.setCHolderMediator(this);
        subTabsHoldFactory_DECAA.initialize();
        registerMemberToMap(subTabsHoldFactory_DECAA);

        /* 6. General Notes of DE */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        DEGN_SubTabsHolderFactory.class.getName(),
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_DEGN =
                chMemberFactory.createCHolder("sub_tabs_holder_DEGN", "sub_tabs_holder_DEGN", authorYears[0]);
        subTabsHoldFactory_DEGN.setCHolderMediator(this);
        subTabsHoldFactory_DEGN.initialize();
        registerMemberToMap(subTabsHoldFactory_DEGN);

        //------------------------------------------

        /* Explanation Text Field */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        ExplanationPanelHolderFactory.class.getName(),
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember explanationPanelHolderFactory =
                chMemberFactory.createCHolder("explanation_panel_holder", "explanation panel holder", authorYears[0]);
        explanationPanelHolderFactory.setCHolderMediator(this);
        explanationPanelHolderFactory.initialize();
        registerMemberToMap(explanationPanelHolderFactory);

        //------------------------------------------

        /* 7. Common Part of QA */
        chMemberFactory =
                MemberFactoryLoader.loadFactory(
                        QACM_SubTabsHolderFactory.class.getName(),
                        AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_QACM =
                chMemberFactory.createCHolder("sub_tabs_holder_QACM", "sub_tabs_holder_QADM", authorYears[0]);
        subTabsHoldFactory_QACM.setCHolderMediator(this);
        subTabsHoldFactory_QACM.initialize();
        registerMemberToMap(subTabsHoldFactory_QACM);

        /* 8. Normative Modeling Part of QA */
        chMemberFactory = MemberFactoryLoader.loadFactory(QANM_SubTabsHolderFactory.class.getName(), AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_QANM =
                chMemberFactory.createCHolder("sub_tabs_holder_QANM", "sub_tabs_holder_QANM", authorYears[0]);
        subTabsHoldFactory_QANM.setCHolderMediator(this);
        subTabsHoldFactory_QANM.initialize();
        registerMemberToMap(subTabsHoldFactory_QANM);

        /* 9. Clinical Research Part of QA */
        chMemberFactory = MemberFactoryLoader.loadFactory(QACR_SubTabsHolderFactory.class.getName(), AbstCHolderMemberFactory.class);
        AbstCHolderMember subTabsHoldFactory_QACR =
                chMemberFactory.createCHolder("sub_tabs_holder_QACR", "sub_tabs_holder_QACR", authorYears[0]);
        subTabsHoldFactory_QACR.setCHolderMediator(this);
        subTabsHoldFactory_QACR.initialize();
        registerMemberToMap(subTabsHoldFactory_QACR);

        //------------------------------------------

        /* 98. Notebook LM Pane */
        chMemberFactory = MemberFactoryLoader.loadFactory(
                WithNotebookLMPanelHolderFactory.class.getName(),
                AbstCHolderMemberFactory.class);
        AbstCHolderMember withNotebookLmPaneHoldFactory =
                chMemberFactory.createCHolder("with_notebook_lm_pane_holder", "With NotebookLM Pane Holder", authorYears[0]);
        withNotebookLmPaneHoldFactory.setCHolderMediator(this);
        withNotebookLmPaneHoldFactory.initialize();
        registerMemberToMap(withNotebookLmPaneHoldFactory);


        /* 99. Summary Pane */
        chMemberFactory = MemberFactoryLoader.loadFactory(
                SummaryPaneHolderFactory.class.getName(),
                AbstCHolderMemberFactory.class);
        AbstCHolderMember summaryPaneHoldFactory =
                chMemberFactory.createCHolder("summary_pane_holder", "summary pane holder", authorYears[0]);
        summaryPaneHoldFactory.setCHolderMediator(this);
        summaryPaneHoldFactory.initialize();
        registerMemberToMap(summaryPaneHoldFactory);

    }

    private void registerMemberToMap(AbstCHolderMember member) {
        memberMap.put(member.getMemberName(), member);
    }

    @Override
    public void requestFromMember() {
    }

    @Override
    public HashMap<String, MemberIntrfc> getMemberMap() {
        return memberMap;
    }

    public AbstCHolderMember getInstanceOfAMember(String member_name) {
        AbstCHolderMember aMember = (AbstCHolderMember) memberMap.get(member_name);
        if (aMember == null) {
            System.err.println("CHolderMember '" + member_name + "' が見つかりません。member呼び出し名やその紐付けを確認してください。");
            return null;
        } else {
            return aMember;
        }
    }

    public void registerActionMediatorToEachMember(ActionMediator actionMediator) {
        List<String> keys = new ArrayList<>(memberMap.keySet());
        Collections.sort(keys);
        for (String key : keys) {
            memberMap.get(key).setActionMediator(actionMediator);
        }
    }

    public void postInitializeEachMember() {
        //アルファベット順に初期化
        List<String> keys = new ArrayList<>(memberMap.keySet());
        Collections.sort(keys);
        for (String key : keys) {
            ((AbstCHolderMember) memberMap.get(key)).postInitialize();
        }
    }
}
