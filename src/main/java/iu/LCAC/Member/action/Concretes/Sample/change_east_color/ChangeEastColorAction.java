package iu.LCAC.Member.action.Concretes.Sample.change_east_color;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Concretes.Sample.ButtonPanel.ButtonPanelHolder;
import iu.LCAC.Member.componentholder.Concretes.Sample.CheckboxPanel.CheckboxPanelHolder;
import iu.LCAC.Member.componentholder.Concretes.Sample.TextField.TextFieldPanelHolder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class ChangeEastColorAction extends AbstActionMember {

  public ChangeEastColorAction(String action_name, String short_name) {
    super(action_name, short_name);
  }

  @Override
  protected void setAcceleratorKeyStroke() {
    this.getMenuItem()
        .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
  }

  @Override
  public void perform(ActionEvent action_event) {
    System.out.println("Action_ChangeEastColor.perform() has been called!");

    if (this.cholderMediator == null) {
      System.err.println("CHolderMediator is null!" + "@" + this.getClass().toString());
    } else {

      // WEST
      ButtonPanelHolder button_panel_holder =
          (ButtonPanelHolder) this.cholderMediator.getInstanceOfAMember("button_panel_holder");
      if (button_panel_holder != null) {
        button_panel_holder.getBaseComponent().setBackground(Color.WHITE);
      } else {
        System.err.println("button_panel is null.");
      }

      // CENTER
      CheckboxPanelHolder checkbox_panel_holder =
          (CheckboxPanelHolder) this.cholderMediator.getInstanceOfAMember("checkbox_panel_holder");
      if (checkbox_panel_holder != null) {
        checkbox_panel_holder.getBaseComponent().setBackground(Color.WHITE);
      } else {
        System.err.println("checkbox_panel is null.");
      }

      // EAST
      TextFieldPanelHolder textfield_panel_holder =
          (TextFieldPanelHolder)
              this.cholderMediator.getInstanceOfAMember("text_field_panel_holder");
      if (textfield_panel_holder != null) {
        textfield_panel_holder.getBaseComponent().setBackground(Color.BLUE);
      } else {
        System.err.println("textfield_panel is null.");
      }
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
