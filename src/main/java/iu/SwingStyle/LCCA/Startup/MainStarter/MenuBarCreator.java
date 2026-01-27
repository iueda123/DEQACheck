package iu.SwingStyle.LCCA.Startup.MainStarter;

import iu.SwingStyle.LCCA.Mediator.action.ActionMediator;
import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.MainWindow.MainWindowHolder;

import java.awt.event.KeyEvent;
import javax.swing.*;

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

    /**
     * Starter（全Panelへのアクセス権を持つ）を介して、各種アクションを生成。 メニューバーを生成し、生成したアクションを登録する。
     */
    private JMenuBar createMenuBar() {

        JMenuBar menuBar = new JMenuBar();

        /* *** main_menu_1 *** */
        JMenu main_menu_1 = new JMenu("main_menu_1 (F)");
        main_menu_1.setMnemonic(KeyEvent.VK_F); // ムネモニックの登録
        menuBar.add(main_menu_1);

        /* *** children of main_menu_1 *** */
        JMenuItem menu_item1_1 = actionMediator.getInstanceOfAMember("change_color_of_west").getMenuItem();
        //main_menu_1.add(menu_item1_1);

        JMenuItem menu_item1_2 = actionMediator.getInstanceOfAMember("change_color_of_center").getMenuItem();
        //main_menu_1.add(menu_item1_2);

        //JMenuItem menu_item1_3 = actionMediator.getInstanceOfAMember("change_color_of_east").getMenuItem();
        //main_menu_1.add(menu_item1_3);
        JMenuItem menu_item1_3 = actionMediator.getInstanceOfAMember("save_note_pane").getMenuItem();
        main_menu_1.add(menu_item1_3);

        //JMenuItem menu_item1_4 = actionMediator.getInstanceOfAMember("change_text").getMenuItem();
        JMenuItem menu_item1_4 = actionMediator.getInstanceOfAMember("load_note_pane").getMenuItem();
        main_menu_1.add(menu_item1_4);

        //JMenuItem menu_item1_5 = actionMediator.getInstanceOfAMember("change_text_of_textfield").getMenuItem();
        JMenuItem menu_item1_5 = actionMediator.getInstanceOfAMember("save_pane_order").getMenuItem();
        main_menu_1.add(menu_item1_5);

        //JMenuItem menu_item1_6 = actionMediator.getInstanceOfAMember("initialize_sample_text_field").getMenuItem();
        JMenuItem menu_item1_6 = actionMediator.getInstanceOfAMember("load_pane_order").getMenuItem();
        main_menu_1.add(menu_item1_6);

        main_menu_1.addSeparator();

        JMenuItem menu_item1_7 = actionMediator.getInstanceOfAMember("initialize_all_tabpanes").getMenuItem();
        main_menu_1.add(menu_item1_7);

        main_menu_1.addSeparator();

        JMenuItem menu_item1_8 = actionMediator.getInstanceOfAMember("check_confidence_rating_of_de_sections").getMenuItem();
        main_menu_1.add(menu_item1_8);

        //JMenuItem menu_item1_8 = actionMediator.getInstanceOfAMember("convert_json_2_markdown").getMenuItem();
        //main_menu_1.add(menu_item1_8);

        //JMenuItem menu_item1_9 = actionMediator.getInstanceOfAMember("convert_json_2_tsv").getMenuItem();
        //main_menu_1.add(menu_item1_9);

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
