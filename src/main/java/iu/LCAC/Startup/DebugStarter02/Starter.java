package iu.LCAC.Startup.DebugStarter02;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.action.ActionMediatorFactory;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediatorFactory;
import iu.LCAC.Member.componentholder.Concretes.MainWindow.MainWindowHolder;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

public class Starter {
  /**
   * ショートカット制御機構を備えたメニューバー付きSwingFrameWork
   *
   * <p>TODO: - adjustable border area - status area - terminal - logging
   *
   * @param args
   */
  public static void main(String[] args) {

    /* **** フォントサイズを1.25倍に設定 **** */
    setUIFont(new FontUIResource("SansSerif", Font.PLAIN, 15)); // 標準12pt → 15pt (1.25倍)

    /* **** 新しい ActionMediator を作る **** */
    ActionMediator actionMediator = ActionMediatorFactory.create("authorYear");
    // Actionの生成と登録はここで完了している。

    /* **** CHolderMediator を作る **** */
    CHolderMediator cholderMediator = CHolderMediatorFactory.create("authorYear");
    // Componentの生成と登録はここで完了している。

    /* **** Component-holders と Actions を連携させる **** */
    actionMediator.registerCHolderMediatorToEachMember(cholderMediator);
    cholderMediator.registerActionMediatorToEachMember(actionMediator);

    /* **** Widowを作る **** */

    /* **** 各種コンポーネントをMainWindowに埋め込む **** */
    BasePaneCreator basePaneCreator = new BasePaneCreator(cholderMediator);
    basePaneCreator.addBasePaneToMainFrame();

    /* **** メニューバー を作り、MainWindowにはめ込む **** */
    MenuBarCreator menuBarCreator = new MenuBarCreator(actionMediator, cholderMediator);
    menuBarCreator.addMenuBarToMainFrame();

    /* **** Command Executor を作り MainWindowにはめ込む **** */
    CmdExecutorCreator cmdExecutorCreator = new CmdExecutorCreator(actionMediator, cholderMediator);
    cmdExecutorCreator.addCmdExecutorToMainFrame();

    /* **** Status Area を作り MainWindowにはめ込む **** */
    StatusPanelCreator statusPanelCreator = new StatusPanelCreator(actionMediator, cholderMediator);
    statusPanelCreator.addStatusAreaToMainFrame();

    /* **** 表示 **** */
    ((MainWindowHolder) cholderMediator.getInstanceOfAMember("main_window_holder"))
        .displayAndInitialize();

    /* **** 表示後の初期化 **** */
    cholderMediator.postInitializeEachMember();
  }

  private static void setUIFont(FontUIResource font) {
    Enumeration<Object> keys = UIManager.getDefaults().keys();
    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = UIManager.get(key);
      if (value instanceof FontUIResource) {
        UIManager.put(key, font);
      }
    }
  }
}
