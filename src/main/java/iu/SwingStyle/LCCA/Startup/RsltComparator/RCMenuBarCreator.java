package iu.SwingStyle.LCCA.Startup.RsltComparator;

import iu.SwingStyle.LCCA.Mediator.RsltComparator.RCActionMediator;
import iu.SwingStyle.LCCA.Mediator.RsltComparator.RCCHolderMediator;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.MainWindow.MainWindowHolder;

import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * RsltComparator 用の MenuBarCreator。
 * Save All / Load All アクションをメニューバーに登録する。
 */
public class RCMenuBarCreator {

    private final RCActionMediator actionMediator;
    private final RCCHolderMediator cHolderMediator;

    public RCMenuBarCreator(RCActionMediator actionMediator, RCCHolderMediator cHolderMediator) {
        this.actionMediator = actionMediator;
        this.cHolderMediator = cHolderMediator;
    }

    public void addMenuBarToMainFrame() {
        ((MainWindowHolder) cHolderMediator.getInstanceOfAMember("main_window_holder"))
                .getMainWindow()
                .setJMenuBar(createMenuBar());
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        /* *** File メニュー *** */
        JMenu fileMenu = new JMenu("File (F)");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        JMenuItem saveAll = actionMediator.getInstanceOfAMember("rc_save_all").getMenuItem();
        fileMenu.add(saveAll);

        JMenuItem loadAll = actionMediator.getInstanceOfAMember("rc_load_all").getMenuItem();
        fileMenu.add(loadAll);

        return menuBar;
    }
}
