package iu.LCAC.Startup.DebugStarter01;

import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Concretes.MainWindow.MainWindowHolder;

import javax.swing.*;
import java.awt.*;

public class BasePaneCreator {

  CHolderMediator cHolderMediator;

  public BasePaneCreator(CHolderMediator cHolderMediator) {
    this.cHolderMediator = cHolderMediator;
  }

  public void addBasePaneToMainFrame() {
    /* **** 土台パネルをメインパネルに埋め込む **** */
    // このとき各サブパネルに登録されているPropertyChangeListenerにシグナルが送られる。
    ((MainWindowHolder) cHolderMediator.getInstanceOfAMember("main_window_holder"))
        .addPanelToCenter(createBasePane());
  }

  private JPanel createBasePane() {
    JPanel basePane = new JPanel(new BorderLayout());

    // Factory を介して各パネル（Panel_Aのサブクラス）を生成し、配置。

    /* **** Component を配置する **** */
    basePane.add(
        (cHolderMediator.getInstanceOfAMember("button_panel_holder")).getBaseComponent(),
        BorderLayout.WEST);
    basePane.add(
        (cHolderMediator.getInstanceOfAMember("checkbox_panel_holder")).getBaseComponent(),
        BorderLayout.CENTER);
    basePane.add(
        (cHolderMediator.getInstanceOfAMember("text_field_panel_holder")).getBaseComponent(),
        BorderLayout.EAST);
    basePane.add(
            (cHolderMediator.getInstanceOfAMember("run_bash_panel_holder")).getBaseComponent(),
            BorderLayout.SOUTH);

    return basePane;
  }
}
