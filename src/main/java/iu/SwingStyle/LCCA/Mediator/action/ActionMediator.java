package iu.SwingStyle.LCCA.Mediator.action;

import iu.SwingStyle.LCCA.Mediator.MediatorIntrfc;
import iu.SwingStyle.LCCA.Mediator.MemberFactoryLoader;
import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;
import iu.SwingStyle.LCCA.Member.MemberIntrfc;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMember;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMemberFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.CoreActions.Save_and_Load.LoadAcceleratorSettingsActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.CoreActions.Save_and_Load.SaveAcceleratorSettingsActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.CoreActions.SetAcceleratorOnNextClick.SetAcceleratorOnNextClickActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.ConfidenceChecker.CheckConfidenceRatingOfDESectionsActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.Sample.change_west_color.ChangeWestColorActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.Sample.change_center_color.ChangeCenterColorActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.Sample.change_east_color.ChangeEastColorActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.Sample.change_text_of_button_panel.ChangeTextOfButtonPanelActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.Sample.initialize_textfield_panel.InitializeTextFieldPanelActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.Sample.change_text_of_textfield.ChangeTextOfTextFieldWithArgActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.Sample.run_a_bash_script.RunABashScriptActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.Init_All_Section.Initialize_All_TabPanes_ActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.SaveAndLoadPaneOrder.SavePaneOrderActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.SaveAndLoadPaneOrder.LoadPaneOrderActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.SaveAndLoadNotePaneTexts.SaveNotePaneTextsActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.SaveAndLoadNotePaneTexts.LoadNotePaneTextsActionFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;

public class ActionMediator implements ActionListener, MediatorIntrfc {

    public HashMap<String, MemberIntrfc> memberMap = new HashMap<>();

    public ActionMediator(String authorYear) {
        this.createMembers(authorYear);
    }

    /* *** Variables and Methods for AcceleratorSetupMode *** */

    static boolean SetupAcceleratorMode = false;

    public void enableAcceleratorSetupMode() {
        SetupAcceleratorMode = true;
    }

    public void disableAcceleratorSetupMode() {
        SetupAcceleratorMode = false;
    }

    @Override
    public void actionPerformed(ActionEvent action_event) {
        String action_command = action_event.getActionCommand();
        System.out.println(
                "==== ActionMediator has called an action_command: \"" + action_command + "\" ====");

        String[] splitActionEventString = AbstActionMember.getActionCommandAndArgs(action_event, true);

        // 最初の要素を実際のアクション名として使用
        String actualActionCommand = splitActionEventString[0];

        if (SetupAcceleratorMode) {
            System.out.println("AcceleratorSetupMode has turned ON.");
            // System.out.print("ただいま呼び出されたActionのショートカットを書き換えます。");
            // System.out.print("ただいま呼び出されたActionの名前は '" + actualActionCommand + "' です。");
            ((AbstActionMember) memberMap.get("set_acceleration_on_next_click")).perform(action_event);
        } else {
            AbstActionMember called_action = (AbstActionMember) memberMap.get(actualActionCommand);
            called_action.perform(action_event);
        }
    }

    @Override
    public void createMembers() {
        createMembers("");
    }

    @Override
    public void createMembers(String... args) {
        /* *** children of main_menu_1 *** */
        AbstActionMemberFactory actionMemberFactory;

        /* **** Core Actions **** */

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                LoadAcceleratorSettingsActionFactory.class.getName(),
                AbstActionMemberFactory.class);
        AbstActionMember loadAcceleratorSettingsAction =
                actionMemberFactory.createAction("load_accelerator_settings", "Load Accelerator");
        loadAcceleratorSettingsAction.setActionMediator(this);
        loadAcceleratorSettingsAction.initialize();
        registerMemberToMap(loadAcceleratorSettingsAction);

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                SaveAcceleratorSettingsActionFactory.class.getName(),
                AbstActionMemberFactory.class);
        AbstActionMember saveAcceleratorSettingsAction =
                actionMemberFactory.createAction("save_accelerator_settings", "Save Accelerator");
        saveAcceleratorSettingsAction.setActionMediator(this);
        saveAcceleratorSettingsAction.initialize();
        registerMemberToMap(saveAcceleratorSettingsAction);

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                SetAcceleratorOnNextClickActionFactory.class.getName(),
                AbstActionMemberFactory.class);
        AbstActionMember setAcceleratorOnNextClickAction =
                actionMemberFactory.createAction("set_acceleration_on_next_click", "Setup Accelerator");
        setAcceleratorOnNextClickAction.setActionMediator(this);
        setAcceleratorOnNextClickAction.initialize();
        registerMemberToMap(setAcceleratorOnNextClickAction);


        /* **** Sample Actions **** */

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                ChangeWestColorActionFactory.class.getName(),
                AbstActionMemberFactory.class);
        AbstActionMember changeWestColorAction =
                actionMemberFactory.createAction("change_color_of_west", "blue west");
        // changeWestColorAction.setActionListenerToMenuItem((ActionListener) this);
        changeWestColorAction.setActionMediator(this);
        changeWestColorAction.initialize();
        registerMemberToMap(changeWestColorAction);

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                ChangeCenterColorActionFactory.class.getName(),
                AbstActionMemberFactory.class);
        AbstActionMember changeCenterColorAction =
                actionMemberFactory.createAction("change_color_of_center", "blue center");
        changeCenterColorAction.setActionMediator(this);
        changeCenterColorAction.initialize();
        registerMemberToMap(changeCenterColorAction);

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                ChangeEastColorActionFactory.class.getName(),
                AbstActionMemberFactory.class);
        AbstActionMember changeEastColorAction =
                actionMemberFactory.createAction("change_color_of_east", "blue east");
        changeEastColorAction.setActionMediator(this);
        changeEastColorAction.initialize();
        registerMemberToMap(changeEastColorAction);

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                ChangeTextOfButtonPanelActionFactory.class.getName(),
                AbstActionMemberFactory.class);
        AbstActionMember changeTextOfButtonPanelAction =
                actionMemberFactory.createAction("change_text", "change text");
        changeTextOfButtonPanelAction.setActionMediator(this);
        changeTextOfButtonPanelAction.initialize();
        registerMemberToMap(changeTextOfButtonPanelAction);

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                InitializeTextFieldPanelActionFactory.class.getName(),
                AbstActionMemberFactory.class);
        AbstActionMember initializeTextFieldPanelAction =
                actionMemberFactory.createAction("initialize_sample_text_field", "initialize textfield");
        initializeTextFieldPanelAction.setActionMediator(this);
        initializeTextFieldPanelAction.initialize();
        registerMemberToMap(initializeTextFieldPanelAction);

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                ChangeTextOfTextFieldWithArgActionFactory.class.getName(),
                AbstActionMemberFactory.class);
        AbstActionMember changeTextOfTextFieldAction =
                actionMemberFactory.createAction("change_text_of_textfield", "Change Text");
        changeTextOfTextFieldAction.setActionMediator(this);
        changeTextOfTextFieldAction.initialize();
        registerMemberToMap(changeTextOfTextFieldAction);

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                RunABashScriptActionFactory.class.getName(),
                AbstActionMemberFactory.class);
        AbstActionMember runABashScriptAction =
                actionMemberFactory.createAction("run_a_bash_script", "Run a bash script");
        runABashScriptAction.setActionMediator(this);
        runABashScriptAction.initialize();
        registerMemberToMap(runABashScriptAction);


        /* **** DEQACheck **** */
        for (ActionSpec spec : ActionSpec.values()) {
            actionMemberFactory =
                    MemberFactoryLoader.loadFactory(
                            spec.factoryClass.getName(),
                            AbstActionMemberFactory.class);
            AbstActionMember action = actionMemberFactory.createAction(spec.memberName, spec.description, args);
            action.setActionMediator(this);
            action.initialize();
            registerMemberToMap(action);
        }


    }

    private void registerMemberToMap(AbstActionMember action) {
        memberMap.put(action.getMemberName(), action);
    }

    @Override
    public void requestFromMember() {
    }

    @Override
    public HashMap<String, MemberIntrfc> getMemberMap() {
        return memberMap;
    }

    public AbstActionMember getInstanceOfAMember(String member_name) {
        return (AbstActionMember) memberMap.get(member_name);
    }

    public void registerCHolderMediatorToEachMember(CHolderMediator cHolderMediator) {
        Iterator<String> it = memberMap.keySet().iterator();
        String key = null;
        while (it.hasNext()) {
            key = it.next();
            memberMap.get(key).setCHolderMediator(cHolderMediator);
        }
    }
}
