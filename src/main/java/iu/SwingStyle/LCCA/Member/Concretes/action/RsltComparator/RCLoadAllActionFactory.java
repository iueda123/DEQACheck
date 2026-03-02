package iu.SwingStyle.LCCA.Member.Concretes.action.RsltComparator;

import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMember;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMemberFactory;

public class RCLoadAllActionFactory extends AbstActionMemberFactory {

    private AbstActionMember action;

    @Override
    protected AbstActionMember createInstance(String actionName, String shortName, String... args) {
        if (action == null) {
            action = new RCLoadAllAction(actionName, shortName);
        }
        return action;
    }
}
