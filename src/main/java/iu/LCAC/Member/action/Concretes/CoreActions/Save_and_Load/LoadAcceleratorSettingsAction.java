package iu.LCAC.Member.action.Concretes.CoreActions.Save_and_Load;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import iu.LCAC.Utils.PropertyManager_v5;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;
import javax.swing.*;

public class LoadAcceleratorSettingsAction extends AbstActionMember {

  static final String accelerator_setting_file_path_str =
      "settings/ActionControlledComponentFramework/accelerator.prop";

  public LoadAcceleratorSettingsAction(String action_name, String short_name) {
    super(action_name, short_name);
  }

  @Override
  protected void setAcceleratorKeyStroke() {
    this.getMenuItem()
        .setAccelerator(
            KeyStroke.getKeyStroke(
                KeyEvent.VK_L,
                InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK + InputEvent.ALT_DOWN_MASK));
  }

  @Override
  public void perform(ActionEvent action_event) {
    System.out.println("perform() in " + this.getClass().toString() + " was called.");

    Path accelerator_setting_file_path = Paths.get(accelerator_setting_file_path_str);

    PropertyManager_v5 prop_manager =
        createPropertyManager(accelerator_setting_file_path.toString());

    prop_manager.listUpProperty();

    System.out.println(
        "Properties file '"
            + accelerator_setting_file_path.toAbsolutePath().toString()
            + "' was loaded.");

    Set<String> property_names = prop_manager.stringPropertyNames();

    String loaded_accelerator = "";
    AbstActionMember action = null;
    KeyStroke loaded_accelerator_key_stroke = null;
    System.out.println("================================");
    System.out.println("property_name -> accelerator key");
    System.out.println("--------------------------------");
    for (String property_name : property_names) {
      loaded_accelerator = (String) prop_manager.getValueOrCreateNew(property_name);

      System.out.println(property_name + " -> " + loaded_accelerator);

      action = this.actionMediator.getInstanceOfAMember(property_name);
      System.out.println("action_name: " + action.getMemberName());

      loaded_accelerator_key_stroke = KeyStroke.getKeyStroke(loaded_accelerator);
      // System.out.println("loaded_accelerator_key_stroke: " + loaded_accelerator_key_stroke);
      // System.out.println("loaded_accelerator_key_stroke: " +
      // loaded_accelerator_key_stroke.getModifiers());

      try {
        action.getMenuItem().setAccelerator(loaded_accelerator_key_stroke);
      } catch (NullPointerException npe) {
        npe.printStackTrace();
        if (action == null) {
          // Propertyファイルに記述されていない、もしくは記述されているが、Actionのインスタンスが存在しない。
          System.err.println();
          System.err.println("An error was thrown in 'Action_LoadAcceleratorSetting.java'");
          System.err.println(
              "An action object could not get from 'Contoller.ActionCommandAndActionMap'. \n"
                  + "Please check if the Starter's 'ActionCommandAndActionMap' holds an instance corresponding to the action "
                  + "'"
                  + property_name
                  + "'.");
          System.err.println();
          System.err.println("The loaded property_name: " + property_name);
          System.err.println(
              "The loaded accelerator key stroke: "
                  + loaded_accelerator_key_stroke
                  + " ("
                  + loaded_accelerator_key_stroke.getModifiers()
                  + ").");
          System.err.println();
          System.err.println("==== Actions holded by Starter ====");
          Iterator it = this.actionMediator.getMemberMap().keySet().iterator();
          String ActionCommand = "";
          while (it.hasNext()) {
            ActionCommand = (String) it.next();
            System.err.println("ActionCommandName:" + ActionCommand);
            System.err.println(
                "    ---- Instance: " + this.actionMediator.getMemberMap().get(ActionCommand));
          }
          System.err.println("======================================");
          System.err.println();
          System.err.println(
              "You can create new actions under 'iu.Actions' and 'iu.MenuBar'. Please check them.");
          System.err.println();
        }
      }
    }
    System.out.println("================================");

    /*
    Set<String> action_commands = Starter.ActionCommandAndActionMap.keySet();
    AbstActionMember action = null;
    String accelerator = "";
    for(String action_command: action_commands ){
        action = Starter.ActionCommandAndActionMap.get(action_command);
        System.out.println(action_command);
        accelerator = action.LinkedMenuItem.getAccelerator().toString();
        System.out.println(accelerator);
        accelerator_combinations.setProperty(action_command, accelerator);
    }
    */

  }

  @Override
  public void setCHolderMediator(CHolderMediator cHolderMediator) {
    this.cholderMediator = cHolderMediator;
  }

  @Override
  public void setActionMediator(ActionMediator actionMediator) {
    super.actionMediator = actionMediator;
  }

  @Override
  public void initialize() {}

  @Override
  public void doWorkAsMember() {}
}
