package iu.LCCA.Mediator.action;

import iu.LCCA.Mediator.MediatorIntrfc;
import iu.LCCA.Mediator.MemberFactoryLoader;
import iu.LCCA.Mediator.componentholder.CHolderMediator;
import iu.LCCA.Member.MemberIntrfc;
import iu.LCCA.Member.action.Abstract.AbstActionMember;
import iu.LCCA.Member.action.Abstract.AbstActionMemberFactory;
import iu.LCCA.Member.action.Concretes.CoreActions.Save_and_Load.LoadAcceleratorSettingsActionFactory;
import iu.LCCA.Member.action.Concretes.CoreActions.Save_and_Load.SaveAcceleratorSettingsActionFactory;
import iu.LCCA.Member.action.Concretes.CoreActions.SetAcceleratorOnNextClick.SetAcceleratorOnNextClickActionFactory;
import iu.LCCA.Member.action.Concretes.DEQAResultActions.ConfidenceChecker.CheckConfidenceRatingOfDESectionsActionFactory;
import iu.LCCA.Member.action.Concretes.Sample.change_west_color.ChangeWestColorActionFactory;
import iu.LCCA.Member.action.Concretes.Sample.change_center_color.ChangeCenterColorActionFactory;
import iu.LCCA.Member.action.Concretes.Sample.change_east_color.ChangeEastColorActionFactory;
import iu.LCCA.Member.action.Concretes.Sample.change_text_of_button_panel.ChangeTextOfButtonPanelActionFactory;
import iu.LCCA.Member.action.Concretes.Sample.initialize_textfield_panel.InitializeTextFieldPanelActionFactory;
import iu.LCCA.Member.action.Concretes.Sample.change_text_of_textfield.ChangeTextOfTextFieldWithArgActionFactory;
import iu.LCCA.Member.action.Concretes.Sample.run_a_bash_script.RunABashScriptActionFactory;
import iu.LCCA.Member.action.Concretes.DEQAResultActions.Init_All_Section.Initialize_All_TabPanes_ActionFactory;
import iu.LCCA.Member.action.Concretes.DEQAResultActions.SaveAndLoadPaneOrder.SavePaneOrderActionFactory;
import iu.LCCA.Member.action.Concretes.DEQAResultActions.SaveAndLoadPaneOrder.LoadPaneOrderActionFactory;
import iu.LCCA.Member.action.Concretes.DEQAResultActions.SaveAndLoadNotePaneTexts.SaveNotePaneTextsActionFactory;
import iu.LCCA.Member.action.Concretes.DEQAResultActions.SaveAndLoadNotePaneTexts.LoadNotePaneTextsActionFactory;

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

        /* **** Initialization **** */

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                Initialize_All_TabPanes_ActionFactory.class.getName(),
                AbstActionMemberFactory.class);
        AbstActionMember ititialize_All_TabPanes_Action =
                actionMemberFactory.createAction("initialize_all_tabpanes", "Init All");
        ititialize_All_TabPanes_Action.setActionMediator(this);
        ititialize_All_TabPanes_Action.initialize();
        registerMemberToMap(ititialize_All_TabPanes_Action);


        // Save & Load DEResultPane Order

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                SavePaneOrderActionFactory.class.getName(),
                AbstActionMemberFactory.class);
        AbstActionMember savePaneOrderAction =
                actionMemberFactory.createAction("save_pane_order", "save pane order", args[0]);
        savePaneOrderAction.setActionMediator(this);
        savePaneOrderAction.initialize();
        registerMemberToMap(savePaneOrderAction);

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                LoadPaneOrderActionFactory.class.getName(),
                AbstActionMemberFactory.class);
        AbstActionMember loadPaneOrderAction =
                actionMemberFactory.createAction("load_pane_order", "load pane order", args[0]);
        loadPaneOrderAction.setActionMediator(this);
        loadPaneOrderAction.initialize();
        registerMemberToMap(loadPaneOrderAction);


        // Save & Load NotePane Texts

        actionMemberFactory = MemberFactoryLoader.loadFactory(
                SaveNotePaneTextsActionFactory.class.getName(),
                AbstActionMemberFactory.class);
        AbstActionMember saveNotePaneAction =
                actionMemberFactory.createAction("save_note_pane", "save note pane texts", args[0]);
        saveNotePaneAction.setActionMediator(this);
        saveNotePaneAction.initialize();
        registerMemberToMap(saveNotePaneAction);


        actionMemberFactory = MemberFactoryLoader.loadFactory(
                LoadNotePaneTextsActionFactory.class.getName(),
                AbstActionMemberFactory.class);
        AbstActionMember loadNotePaneAction =
                actionMemberFactory.createAction("load_note_pane", "load note pane texts", args[0]);
        loadNotePaneAction.setActionMediator(this);
        loadNotePaneAction.initialize();
        registerMemberToMap(loadNotePaneAction);


        /* **** Special Action **** */
        actionMemberFactory = MemberFactoryLoader.loadFactory(
                CheckConfidenceRatingOfDESectionsActionFactory.class.getName(),
                AbstActionMemberFactory.class);
        AbstActionMember summarizeConfidenceRatingAction =
                actionMemberFactory.createAction("check_confidence_rating_of_de_sections", "check confidence rating of de", args[0]);
        summarizeConfidenceRatingAction.setActionMediator(this);
        summarizeConfidenceRatingAction.initialize();
        registerMemberToMap(summarizeConfidenceRatingAction);



        /* **** Processing **** */

        /*
        actionMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.action.Concretes.DEQAResultActions.ConvertJson.ConvertJson2MarkdownActionFactory",
                        AbstActionMemberFactory.class);
        AbstActionMember convertJson2MarkdownAction =
                actionMemberFactory.createAction("convert_json_2_markdown", "Convert JSON 2 Markdown");
        convertJson2MarkdownAction.setActionMediator(this);
        convertJson2MarkdownAction.initialize();
        registerMemberToMap(convertJson2MarkdownAction);
        */
        /*
        actionMemberFactory =
                MemberFactoryLoader.loadFactory(
                        "iu.LCAC.Member.action.Concretes.DEQAResultActions.ConvertJson.ConvertJson2TsvActionFactory",
                        AbstActionMemberFactory.class);
        AbstActionMember convertJson2TsvAction =
                actionMemberFactory.createAction("convert_json_2_tsv", "Convert JSON 2 TSV");
        convertJson2TsvAction.setActionMediator(this);
        convertJson2TsvAction.initialize();
        registerMemberToMap(convertJson2TsvAction);
        */
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
