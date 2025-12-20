package iu.LCCA.Member.action.Concretes.DEQAResultActions.ConfidenceChecker;

import iu.LCCA.Member.action.Abstract.AbstActionMember;
import iu.LCCA.Member.action.Abstract.AbstActionMemberFactory;

public class CheckConfidenceRatingOfDESectionsActionFactory extends AbstActionMemberFactory {

    private AbstActionMember action;

    @Override
    protected AbstActionMember createInstance(String action_name, String short_name, String... args) {
        if (action == null) {
            action = new CheckConfidenceRatingOfDESectionsAction(action_name, short_name, args[0]);
        }
        return action;
    }
}
