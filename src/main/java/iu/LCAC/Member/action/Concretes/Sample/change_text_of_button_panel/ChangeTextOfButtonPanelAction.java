package iu.LCAC.Member.action.Concretes.Sample.change_text_of_button_panel;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Member.componentholder.Concretes.Sample.ButtonPanel.ButtonPanelHolder;
import iu.LCAC.Utils.PropertyManager_v5;
import java.awt.event.ActionEvent;

public class ChangeTextOfButtonPanelAction extends AbstActionMember {

  static final String SettingPropertyFilePath =
      "./settings/ActionControlledComponentFramework/settings.prop";

  public ChangeTextOfButtonPanelAction(String action_name, String short_name) {
    super(action_name, short_name);
  }

  @Override
  protected void setAcceleratorKeyStroke() {}

  @Override
  public void perform(ActionEvent action_event) {
    System.out.println("perform() in " + this.getClass().toString() + " was called.");

    // Load Properties
    PropertyManager_v5 prop_manager = createPropertyManager(SettingPropertyFilePath);

    // Preparation of Components
    ButtonPanelHolder button_panel_holder =
        (ButtonPanelHolder) this.cholderMediator.getInstanceOfAMember("button_panel_holder");

    // Initialization Core
    if (button_panel_holder != null) {
      button_panel_holder.setTextToTheButton(prop_manager.getValueOrCreateNew("default_text"));
    } else {
      System.err.println("button_panel is null.");
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
