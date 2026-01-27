package iu.SwingStyle.LCCA.Member;

import iu.SwingStyle.LCCA.Mediator.action.ActionMediator;
import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;

public interface MemberIntrfc {

    public abstract void setCHolderMediator(CHolderMediator cHolderMediator);

    public abstract void setActionMediator(ActionMediator actionMediator);

    public abstract void initialize();

    public abstract void doWorkAsMember();

    public abstract String getMemberName();
}
