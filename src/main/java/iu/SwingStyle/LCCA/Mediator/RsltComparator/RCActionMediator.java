package iu.SwingStyle.LCCA.Mediator.RsltComparator;

import iu.SwingStyle.LCCA.Mediator.MemberFactoryLoader;
import iu.SwingStyle.LCCA.Mediator.action.ActionMediator;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMember;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMemberFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.RsltComparator.RCSaveAllActionFactory;
import iu.SwingStyle.LCCA.Member.Concretes.action.RsltComparator.RCLoadAllActionFactory;

import java.awt.event.ActionEvent;

/**
 * RsltComparator 用の ActionMediator。
 * ActionMediator を継承し、createMembers をオーバーライドして
 * Save All / Load All アクションのみを生成する。
 */
public class RCActionMediator extends ActionMediator {

    public RCActionMediator(String authorYear) {
        super(authorYear);
    }

    @Override
    public void createMembers(String... args) {
        AbstActionMemberFactory factory;

        // RCSaveAllAction
        factory = MemberFactoryLoader.loadFactory(
                RCSaveAllActionFactory.class.getName(),
                AbstActionMemberFactory.class);
        AbstActionMember saveAll = factory.createAction("rc_save_all", "Save All", args);
        saveAll.setActionMediator(this);
        saveAll.initialize();
        memberMap.put(saveAll.getMemberName(), saveAll);

        // RCLoadAllAction
        factory = MemberFactoryLoader.loadFactory(
                RCLoadAllActionFactory.class.getName(),
                AbstActionMemberFactory.class);
        AbstActionMember loadAll = factory.createAction("rc_load_all", "Load All", args);
        loadAll.setActionMediator(this);
        loadAll.initialize();
        memberMap.put(loadAll.getMemberName(), loadAll);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        System.out.println("==== RCActionMediator: \"" + actionCommand + "\" ====");

        String[] split = AbstActionMember.getActionCommandAndArgs(actionEvent, false);
        String actualCommand = split[0];

        AbstActionMember action = (AbstActionMember) memberMap.get(actualCommand);
        if (action != null) {
            action.perform(actionEvent);
        } else {
            System.err.println("Action '" + actualCommand + "' not found in RCActionMediator.");
        }
    }
}
