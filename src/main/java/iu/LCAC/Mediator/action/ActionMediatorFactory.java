package iu.LCAC.Mediator.action;

public class ActionMediatorFactory {

  private static ActionMediator actionMediator;

  public static ActionMediator create(String authorYear) {
    if (actionMediator == null) {
      actionMediator = new ActionMediator(authorYear);
    }
    return actionMediator;
  }
}
