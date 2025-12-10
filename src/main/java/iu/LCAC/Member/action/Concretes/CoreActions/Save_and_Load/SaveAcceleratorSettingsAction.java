package iu.LCAC.Member.action.Concretes.CoreActions.Save_and_Load;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Utils.PropertyManager_v5;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Set;
import javax.swing.*;

public class SaveAcceleratorSettingsAction extends AbstActionMember {

  static final String accelerator_setting_file_path_str =
      "settings/ActionControlledComponentFramework/accelerator.prop";

  public SaveAcceleratorSettingsAction(String action_name, String short_name) {
    super(action_name, short_name);
  }

  @Override
  protected void setAcceleratorKeyStroke() {
    this.getMenuItem()
        .setAccelerator(
            KeyStroke.getKeyStroke(
                KeyEvent.VK_S,
                InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK + InputEvent.ALT_DOWN_MASK));
  }

  @Override
  public void perform(ActionEvent action_event) {
    System.out.println("perform() in " + this.getClass().toString() + " was called.");

    Set<String> action_commands = this.actionMediator.memberMap.keySet();

    PropertyManager_v5 prop_manager = createPropertyManager(accelerator_setting_file_path_str);

    AbstActionMember action = null;
    String accelerator = "";
    for (String action_command : action_commands) {
      System.out.print(action_command);
      action = this.actionMediator.getInstanceOfAMember(action_command);
      JMenuItem menu_item = action.getMenuItem();
      // KeyStroke ket_stroke = menu_item.getAccelerator();
      if (menu_item == null) {
        System.out.println(
            "    Can't get a JMenu item about '"
                + action_command
                + "' from the current ActionMediator instance.'");
      } else if (menu_item.getAccelerator() == null) {
        System.out.println(
            "    Can't get a key stroke info about '"
                + action_command
                + "' from the current ActionMediator instance.'");
      } else {
        accelerator = action.getMenuItem().getAccelerator().toString();
        System.out.println("    " + accelerator);
        prop_manager.setProperty(action_command, accelerator);
      }
    }

    prop_manager.writeoutProperties();
  }

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
