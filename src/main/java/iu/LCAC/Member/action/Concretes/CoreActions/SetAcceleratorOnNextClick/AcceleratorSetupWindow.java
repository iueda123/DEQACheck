package iu.LCAC.Member.action.Concretes.CoreActions.SetAcceleratorOnNextClick;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Member.action.Abstract.AbstActionMember;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class AcceleratorSetupWindow extends JFrame {

  static Box BaseVBox = Box.createVerticalBox();
  static final String message_1 = "更新したいActionをメニューから選択してください。";
  static final String message_2 = "希望するショートカットを入力してください。";
  static JLabel label_1 = new JLabel(message_1);
  static JTextField ShortcutTextField = new JTextField("");
  static JButton OkButton;
  static JButton CancelButton;
  static Box OkAndCancelHBox = Box.createHorizontalBox();

  final ActionMediator acController;
  private String UpdateTargetActionName;
  int NewModifier = 0;
  int NewKeyCode = 0;

  public AcceleratorSetupWindow(ActionMediator acController) throws HeadlessException {
    this.acController = acController;

    this.setTitle("Set accelerator on next click: ");

    BaseVBox.add(label_1);
    BaseVBox.add(ShortcutTextField);
    OkAndCancelHBox.add(Box.createHorizontalGlue());
    OkAndCancelHBox.add(createOkButton());
    OkAndCancelHBox.add(createCancelButton());
    BaseVBox.add(OkAndCancelHBox);

    this.getContentPane().add(BaseVBox);

    this.ShortcutTextField.setEditable(false);
    this.addKeyListener(new ShortCutKeyListener());

    this.pack();
  }

  public void setNewMessage(String message) {
    this.label_1.setText(message);
    this.pack();
    this.validate();
  }

  public void resetAndCloseWindow() {
    this.ShortcutTextField.setText("");
    this.label_1.setText(message_1);
    acController.disableAcceleratorSetupMode();
    AcceleratorSetupWindow.this.dispose();
    System.out.println("AcceleratorSetupMode has turned OFF.");
  }

  public JButton createCancelButton() {
    if (CancelButton == null) {
      CancelButton = new JButton("CANCEL");
      CancelButton.addActionListener(
          new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

              // Processes for Closing
              resetAndCloseWindow();
            }
          });
    }
    return CancelButton;
  }

  public JButton createOkButton() {
    if (OkButton == null) {
      OkButton = new JButton("OK");
      OkButton.addActionListener(
          new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
              System.out.println("OK Button was pushed!");

              // Update ShortCut Key
              System.out.println("Update shortcut of " + UpdateTargetActionName + ".");
              AbstActionMember update_target_action =
                  acController.getInstanceOfAMember(UpdateTargetActionName);
              update_target_action
                  .getMenuItem()
                  .setAccelerator(KeyStroke.getKeyStroke(NewKeyCode, NewModifier));

              // Processes for Closing
              resetAndCloseWindow();
            }
          });
    }
    return OkButton;
  }

  public void setUpdateTargetActionName(String update_target_action_name) {
    this.UpdateTargetActionName = update_target_action_name;
  }

  private class ShortCutKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
      ShortcutTextField.setText(keyEvent.paramString());
      ShortcutTextField.setToolTipText(
          keyEvent.getKeyChar() + String.valueOf(keyEvent.getModifiersEx()));
      NewKeyCode = keyEvent.getKeyCode();
      NewModifier = keyEvent.getModifiers();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {}
  }
}
