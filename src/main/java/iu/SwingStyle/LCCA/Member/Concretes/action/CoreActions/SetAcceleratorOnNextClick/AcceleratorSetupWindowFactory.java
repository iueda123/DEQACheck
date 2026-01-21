package iu.SwingStyle.LCCA.Member.Concretes.action.CoreActions.SetAcceleratorOnNextClick;

import iu.SwingStyle.LCCA.Mediator.action.ActionMediator;

public class AcceleratorSetupWindowFactory {

  static AcceleratorSetupWindow ARSWindow;

  public static AcceleratorSetupWindow create(ActionMediator controller) {
    if (ARSWindow == null) {
      ARSWindow = new AcceleratorSetupWindow(controller);
    }
    return ARSWindow;
  }
}
