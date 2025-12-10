package iu.LCAC.Startup.DebugStarter01;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Concretes.MainWindow.MainWindowHolder;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuBarCreator {

  ActionMediator actionMediator;
  CHolderMediator cHolderMediator;

  public MenuBarCreator(ActionMediator actionMediator, CHolderMediator cHolderMediator) {

    this.actionMediator = actionMediator;
    this.cHolderMediator = cHolderMediator;
  }

  public void addMenuBarToMainFrame() {
    ((MainWindowHolder) cHolderMediator.getInstanceOfAMember("main_window_holder"))
        .getMainWindow()
        .setJMenuBar(createMenuBar());
  }

  /** Starter（全Panelへのアクセス権を持つ）を介して、各種アクションを生成。 メニューバーを生成し、生成したアクションを登録する。 */
  private JMenuBar createMenuBar() {

    JMenuBar menuBar = new JMenuBar();

    /* *** main_menu_1 *** */
    JMenu main_menu_1 = new JMenu("main_menu_1 (F)");
    main_menu_1.setMnemonic(KeyEvent.VK_F); // ムネモニックの登録
    menuBar.add(main_menu_1);

    /* *** children of main_menu_1 *** */
    JMenuItem menu_item1_1 =
        actionMediator.getInstanceOfAMember("change_color_of_west").getMenuItem();
    main_menu_1.add(menu_item1_1);

    JMenuItem menu_item1_2 =
        actionMediator.getInstanceOfAMember("change_color_of_center").getMenuItem();
    main_menu_1.add(menu_item1_2);

    JMenuItem menu_item1_3 =
        actionMediator.getInstanceOfAMember("change_color_of_east").getMenuItem();
    main_menu_1.add(menu_item1_3);

    JMenuItem menu_item1_4 = actionMediator.getInstanceOfAMember("change_text").getMenuItem();
    main_menu_1.add(menu_item1_4);

    JMenuItem menu_item1_5 =
        actionMediator.getInstanceOfAMember("initialize_sample_text_field").getMenuItem();
    main_menu_1.add(menu_item1_5);

    JMenuItem menu_item1_6 =
        actionMediator.getInstanceOfAMember("change_text_of_textfield").getMenuItem();
    main_menu_1.add(menu_item1_6);

    /* *** main_menu_2 *** */
    JMenu main_menu_2 = new JMenu("Other (O)");
    main_menu_2.setMnemonic(KeyEvent.VK_O); // ムネモニックの登録
    menuBar.add(main_menu_2);

    /* *** children of main_menu_2 *** */
    JMenuItem menu_item2_1 =
        actionMediator.getInstanceOfAMember("load_accelerator_settings").getMenuItem();
    main_menu_2.add(menu_item2_1);

    JMenuItem menu_item2_2 =
        actionMediator.getInstanceOfAMember("save_accelerator_settings").getMenuItem();
    main_menu_2.add(menu_item2_2);

    JMenuItem menu_item2_3 =
        actionMediator.getInstanceOfAMember("set_acceleration_on_next_click").getMenuItem();
    main_menu_2.add(menu_item2_3);

    return menuBar;
  }
}
