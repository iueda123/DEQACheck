package iu.SwingStyle.LCCA.Member.Concretes.action.DEQAResultActions.SaveAndLoadPaneOrder;

import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMember;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMemberFactory;

public class SavePaneOrderActionFactory extends AbstActionMemberFactory {

    private AbstActionMember action;

    @Override
    protected AbstActionMember createInstance(String action_name, String short_name, String... args) {
        if (action == null) {
            action = new SavePaneOrderAction(action_name, short_name, args[0]);
        }
        return action;
    }
}
