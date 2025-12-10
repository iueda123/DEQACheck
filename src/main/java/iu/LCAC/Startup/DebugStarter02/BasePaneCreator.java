package iu.LCAC.Startup.DebugStarter02;

import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Concretes.MainWindow.MainWindowHolder;
import iu.LCAC.Utils.CollapsiblePanel;
import iu.LCAC.Utils.VerticalTextTabbedPane;

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
    //basePane.add(
    //    (cHolderMediator.getInstanceOfAMember("button_panel_holder")).getBaseComponent(),
    //    BorderLayout.WEST);

    VerticalTextTabbedPane tabbedPane = new VerticalTextTabbedPane(JTabbedPane.LEFT);

   tabbedPane.add("4. NM", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_NM")).getBaseComponent());
    tabbedPane.setToolTipTextAt(0, "Normative Modeling");


   JComponent explanationPanelHolder = (cHolderMediator.getInstanceOfAMember("explanation_panel_holder").getBaseComponent());

    return new CollapsiblePanel(tabbedPane, explanationPanelHolder, new JLabel("West"), new JLabel("South"), new JLabel("North"));

  }
}
