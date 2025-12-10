package iu.LCAC.Member.action.Abstract;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.MemberIntrfc;
import iu.LCAC.Utils.PropertyManager_v5;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.*;

public abstract class AbstActionMember implements MemberIntrfc {

  protected ActionMediator actionMediator;
  protected CHolderMediator cholderMediator;

  private String actionName = "action_name";
  public JMenuItem menuItem; // JMenuItem connected to this action;
  public PropertyManager_v5 propManager;

  private String shortName;

  public AbstActionMember(String action_name, String short_name) {
    this.shortName = short_name;
    this.actionName = action_name;
  }

  public String getMemberName() {
    return this.actionName;
  }

  public JMenuItem getMenuItem() {

    if (menuItem == null) {
      menuItem = new JMenuItem(this.shortName);
      menuItem.setActionCommand(
          getMemberName()); // 結びつけるアクションの名前。表示名とは独立しており、ActionListenerがアクションの種類を特定するのに使う。
      menuItem.addActionListener(actionMediator);
      setAcceleratorKeyStroke();
    }
    return menuItem;
  }

  /**
   * e.g. this.LinkedMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
   * InputEvent.CTRL_DOWN_MASK)); e.g.
   * this.LinkedMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
   * InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK)); e.g.
   * this.LinkedMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
   * InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK + InputEvent.ALT_DOWN_MASK));
   */
  protected abstract void setAcceleratorKeyStroke();

  public abstract void perform(ActionEvent action_event);

  protected PropertyManager_v5 createPropertyManager(String property_file_path) {
    //System.out.println("A property file '" + property_file_path + "' is about to load.");
    Path setting_file_path = Paths.get(property_file_path);

    if (propManager == null) {
      //System.out.println("    The instance of ProperManager is null. Create it.");
      propManager = new PropertyManager_v5(new File(property_file_path));
      propManager.writeoutProperties();
    }else{
      //System.out.println("    The instance of ProperManager is not null. Use it.");
    }

    //System.out.println("    Properties file '" + setting_file_path.toString() + "' was loaded.");

    return propManager;
  }

  public static String[] getActionCommandAndArgs(ActionEvent actionEvent, boolean verbose) {

    String action_command = actionEvent.getActionCommand();

    String[] splitActionCommand = action_command.split(" ");

    // 最初の要素を実際のアクション名として使用
    String actualActionCommand = splitActionCommand[0];

    // 2番目以降の要素を引数として保持
    String[] arguments = new String[splitActionCommand.length - 1];
    System.arraycopy(splitActionCommand, 1, arguments, 0, arguments.length);

    // デバッグ用出力
    if (verbose) {
      System.out.println("Split Action Event String @AbstActionMember.java");
      System.out.println("    Actual Action command: " + actualActionCommand);
      if (arguments.length > 0) {
        System.out.println("    Arguments: " + String.join(", ", arguments));
      }
    }

    return splitActionCommand;
  }
}
