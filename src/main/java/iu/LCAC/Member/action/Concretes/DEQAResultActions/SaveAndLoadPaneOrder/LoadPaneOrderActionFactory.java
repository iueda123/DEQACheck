package iu.LCAC.Member.action.Concretes.DEQAResultActions.SaveAndLoadPaneOrder;

import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.action.Abstract.AbstActionMemberFactory;

public class LoadPaneOrderActionFactory extends AbstActionMemberFactory {

    private AbstActionMember action;

    @Override
    protected AbstActionMember createInstance(String action_name, String short_name, String... args) {
        if (action == null) {
            action = new LoadPaneOrderAction(action_name, short_name, args[0]);
        }
        return action;
    }
}
