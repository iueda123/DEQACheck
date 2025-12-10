package iu.LCAC.Member.action.Concretes.CoreActions.SetAcceleratorOnNextClick;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class SetAcceleratorOnNextClickAction extends AbstActionMember {

  public SetAcceleratorOnNextClickAction(String action_name, String short_name) {
    super(action_name, short_name);
  }

  @Override
  protected void setAcceleratorKeyStroke() {
    this.getMenuItem()
        .setAccelerator(
            KeyStroke.getKeyStroke(
                KeyEvent.VK_A,
                InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK + InputEvent.ALT_DOWN_MASK));
  }

  AcceleratorSetupWindow ARSWindow = null;

  @Override
  public void perform(ActionEvent action_event) {
    this.actionMediator.enableAcceleratorSetupMode();

    ARSWindow = AcceleratorSetupWindowFactory.create(this.actionMediator);
    if (ARSWindow.isVisible() == false) {
      System.out.println("Accelerator Setup Window is invisible. Show it.");
      // accelerator_setup_window.getContentPane().add(new
      // TextField(action_event.getActionCommand()));
      // accelerator_setup_window.setLocationRelativeTo(null);
      ARSWindow.setVisible(true);
    } else {
      System.out.println("Accelerator Setup Window is already visible.");
      String update_target_action_name = action_event.getActionCommand();
      ARSWindow.setNewMessage(update_target_action_name + "に対する新しいショートカットを入力してください。");
      ARSWindow.setUpdateTargetActionName(update_target_action_name);
      ARSWindow.requestFocus();
    }
  }

  @Override
  public void setCHolderMediator(CHolderMediator cHolderMediator) {
    this.cholderMediator = cHolderMediator;
  }

  @Override
  public void setActionMediator(ActionMediator actionMediator) {
    this.actionMediator = actionMediator;
  }

  @Override
  public void initialize() {}

  @Override
  public void doWorkAsMember() {}
}
