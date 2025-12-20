package iu.LCCA.Member.action.Concretes.Sample.change_center_color;

import iu.LCCA.Mediator.action.ActionMediator;
import iu.LCCA.Mediator.componentholder.CHolderMediator;
import iu.LCCA.Member.action.Abstract.AbstActionMember;
import iu.LCCA.Member.componentholder.Concretes.Sample.ButtonPanel.ButtonPanelHolder;
import iu.LCCA.Member.componentholder.Concretes.Sample.CheckboxPanel.CheckboxPanelHolder;
import iu.LCCA.Member.componentholder.Concretes.Sample.TextField.TextFieldPanelHolder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class ChangeCenterColorAction extends AbstActionMember {

  public ChangeCenterColorAction(String action_name, String short_name) {
    super(action_name, short_name);
  }

  @Override
  protected void setAcceleratorKeyStroke() {
    this.getMenuItem()
        .setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
  }

  @Override
  public void perform(ActionEvent action_event) {
    System.out.println("Action_ChangeCenterColor.perform() has been called!");
    if (this.cholderMediator == null) {
      System.err.println("Component Starter is null!" + "@" + this.getClass().toString());
    } else {

      // WEST
      ButtonPanelHolder buttonPanelHolder =
          (ButtonPanelHolder) this.cholderMediator.getInstanceOfAMember("button_panel_holder");
      if (buttonPanelHolder != null) {
        buttonPanelHolder.getBaseComponent().setBackground(Color.WHITE);
      } else {
        System.err.println("button_panel is null.");
      }

      // CENTER
      CheckboxPanelHolder checkboxPanelHolder =
          (CheckboxPanelHolder) this.cholderMediator.getInstanceOfAMember("checkbox_panel_holder");
      if (checkboxPanelHolder != null) {
        checkboxPanelHolder.getBaseComponent().setBackground(Color.BLUE);
      } else {
        System.err.println("checkbox_panel is null.");
      }

      // EAST
      TextFieldPanelHolder textfieldPanelHolder =
          (TextFieldPanelHolder)
              this.cholderMediator.getInstanceOfAMember("text_field_panel_holder");
      if (textfieldPanelHolder != null) {
        textfieldPanelHolder.getBaseComponent().setBackground(Color.WHITE);
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
