package iu.LCAC.Member;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;

public interface MemberIntrfc {

  public abstract void setCHolderMediator(CHolderMediator cHolderMediator);

  public abstract void setActionMediator(ActionMediator actionMediator);

  public abstract void initialize();

  public abstract void doWorkAsMember();

  public abstract String getMemberName();
}
