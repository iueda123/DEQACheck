package iu.SwingStyle.LCCA.Startup.DebugStarter01;

import iu.SwingStyle.LCCA.Mediator.action.ActionMediator;
import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.MainWindow.MainWindowHolder;
import iu.SwingStyle.LCCA.Utils.AutoCompleteCommandBox;

import javax.swing.*;
import java.awt.*;

public class CmdExecutorCreator {
  private final CHolderMediator cholderMediator;
  private final ActionMediator actionMediator;

  public CmdExecutorCreator(ActionMediator actionMediator, CHolderMediator cholderMediator) {
    this.actionMediator = actionMediator;
    this.cholderMediator = cholderMediator;
  }

  public void addCmdExecutorToMainFrame() {
    ((MainWindowHolder) cholderMediator.getInstanceOfAMember("main_window_holder"))
        .addPanelToSouth(createCmdExecutor());
  }

  private JPanel createCmdExecutor() {
    JPanel basePane = new JPanel(new BorderLayout());
    basePane.setBackground(Color.ORANGE);

    String[] commands = actionMediator.getMemberMap().keySet().toArray(new String[0]);
    AutoCompleteCommandBox cmdBox = new AutoCompleteCommandBox(commands);
    cmdBox.setEnterKeyListener(actionMediator);
    basePane.add(cmdBox, BorderLayout.CENTER);

    return basePane;
  }
}
