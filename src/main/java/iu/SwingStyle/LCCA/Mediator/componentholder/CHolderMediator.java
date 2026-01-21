package iu.SwingStyle.LCCA.Mediator.componentholder;

import iu.SwingStyle.LCCA.Mediator.MediatorIntrfc;
import iu.SwingStyle.LCCA.Mediator.MemberFactoryLoader;
import iu.SwingStyle.LCCA.Mediator.action.ActionMediator;
import iu.SwingStyle.LCCA.Member.MemberIntrfc;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMemberFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v11.DEDC.DE_DC_SubTabsHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v12.DENM2.DE_NM2_SubTabsHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.WithNotebookLMPane.WithNotebookLMPanelHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.MainWindow.MainWindowHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.StatusPanel.StatusPanelHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.Sample.ButtonPanel.ButtonPanelHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.Sample.RunBashPanel.RunBashPanelHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DESI.DESI_SubTabsHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DESC.DESC_SubTabsHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DERCI.DERCI_SubTabsHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DENM.DENM_SubTabsHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DECAA.DECAA_SubTabsHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DEGN.DEGN_SubTabsHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Explanation.ExplanationPanelHolderFactory;
//import iu.LCCA.Member.componentholder.Concretes.DEQAResult.QAResult_v7.QACM.QACM_SubTabsHolderFactory;
//import iu.LCCA.Member.componentholder.Concretes.DEQAResult.QAResult_v7.QANM.QANM_SubTabsHolderFactory;
//import iu.LCCA.Member.componentholder.Concretes.DEQAResult.QAResult_v7.QACR.QACR_SubTabsHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.Sample.CheckboxPanel.CheckboxPanelHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.Sample.TextField.TextFieldPanelHolderFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.SummaryPane.SummaryPaneHolderFactory;

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

        /* **** DEQACheck **** */
        for (CHolderSpec spec : CHolderSpec.values()) {
            chMemberFactory = MemberFactoryLoader.loadFactory(
                    spec.factoryClass.getName(),
                    AbstCHolderMemberFactory.class);
            AbstCHolderMember member = chMemberFactory.createCHolder(spec.memberName, spec.description, authorYears);
            member.setCHolderMediator(this);
            member.initialize();
            registerMemberToMap(member);
        }

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
