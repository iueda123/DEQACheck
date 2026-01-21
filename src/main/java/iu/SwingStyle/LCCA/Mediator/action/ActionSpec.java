package iu.SwingStyle.LCCA.Mediator.action;

import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMemberFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.ConfidenceChecker.CheckConfidenceRatingOfDESectionsAction;
import iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.ConfidenceChecker.CheckConfidenceRatingOfDESectionsActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.Init_All_Section.Initialize_All_TabPanes_Action;
import iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.Init_All_Section.Initialize_All_TabPanes_ActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.SaveAndLoadNotePaneTexts.LoadNotePaneTextsAction;
import iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.SaveAndLoadNotePaneTexts.LoadNotePaneTextsActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.SaveAndLoadNotePaneTexts.SaveNotePaneTextsAction;
import iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.SaveAndLoadNotePaneTexts.SaveNotePaneTextsActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.SaveAndLoadPaneOrder.LoadPaneOrderAction;
import iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.SaveAndLoadPaneOrder.LoadPaneOrderActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.SaveAndLoadPaneOrder.SavePaneOrderAction;
import iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.SaveAndLoadPaneOrder.SavePaneOrderActionFactory;

public enum ActionSpec {

    // Initialization
    INITIALIZE_ALL_TABPANES(
            Initialize_All_TabPanes_ActionFactory.class, Initialize_All_TabPanes_Action.staticMemberName,
            "Init All"),

    // Save & Load DEResultPane Order
    SAVE_PANE_ORDER(
            SavePaneOrderActionFactory.class, SavePaneOrderAction.staticMemberName,
            "save pane order"),

    LOAD_PANE_ORDER(
            LoadPaneOrderActionFactory.class, LoadPaneOrderAction.staticMemberName,
            "load pane order"),

    // Save & Load NotePane Texts
    SAVE_NOTE_PANE(
            SaveNotePaneTextsActionFactory.class, SaveNotePaneTextsAction.staticMemberName,
            "save note pane texts"),

    LOAD_NOTE_PANE(
            LoadNotePaneTextsActionFactory.class, LoadNotePaneTextsAction.staticMemberName,
            "load note pane texts"),

    // Special Action
    CHECK_CONFIDENCE_RATING_OF_DE_SECTIONS(
            CheckConfidenceRatingOfDESectionsActionFactory.class, CheckConfidenceRatingOfDESectionsAction.staticMemberName,
            "check confidence rating of de");

    public final Class<? extends AbstActionMemberFactory> factoryClass;
    public final String memberName;
    public final String description;

    ActionSpec(
            Class<? extends AbstActionMemberFactory> factoryClass,
            String memberName,
            String description) {
        this.factoryClass = factoryClass;
        this.memberName = memberName;
        this.description = description;
    }
}
