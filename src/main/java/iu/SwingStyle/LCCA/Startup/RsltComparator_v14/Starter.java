package iu.SwingStyle.LCCA.Startup.RsltComparator_v14;

import iu.SwingStyle.LCCA.Mediator.RsltComparator.RCActionMediator;
import iu.SwingStyle.LCCA.Mediator.RsltComparator.RCActionMediatorFactory;
import iu.SwingStyle.LCCA.Mediator.RsltComparator.RCCHolderMediator;
import iu.SwingStyle.LCCA.Mediator.RsltComparator.RCCHolderMediatorFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.MainWindow.MainWindowHolder;
import iu.SwingStyle.LCCA.Startup.RsltComparator_v13.RCBasePaneCreator;
import iu.SwingStyle.LCCA.Startup.RsltComparator_v13.RCMenuBarCreator;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.File;
import java.util.Enumeration;

/**
 * RsltComparatorV14 のエントリーポイント。
 * AuthorYear を選択し、DE_v14 を固定で読み込む。
 */
public class Starter {

    public static void main(String[] args) {

        /* **** フォントサイズを1.25倍に設定 **** */
        setUIFont(new FontUIResource("SansSerif", Font.PLAIN, 15));

        /* **** AuthorYear と Version を決定 **** */
        String authorYear;
        String version = "DE_v14";

        // ベースディレクトリ調整: share_package 配下で実行する想定を補助
        String baseDir = System.getProperty("LCCA_BASE_DIR", ".");
        File dataDir = new File(baseDir, "data");
        if (!dataDir.exists()) {
            File shareDataDir = new File("share_package/data");
            if (shareDataDir.exists()) {
                System.setProperty("LCCA_BASE_DIR", "share_package");
            }
        }

        if (args.length >= 1) {
            authorYear = args[0];
            System.out.println("Selected (from args): " + authorYear + " / " + version);
        } else {
            SelectionDialog dialog = new SelectionDialog();
            dialog.setVisible(true);

            if (!dialog.isConfirmed()) {
                System.out.println("Selection cancelled. Exiting.");
                System.exit(0);
            }

            authorYear = dialog.getSelectedAuthorYear();
            System.out.println("Selected: " + authorYear + " / " + version);
        }

        /* **** ActionMediator を作る **** */
        RCActionMediator actionMediator = RCActionMediatorFactory.create(authorYear);

        /* **** CHolderMediator を作る **** */
        RCCHolderMediator cholderMediator = RCCHolderMediatorFactory.create(authorYear, version);

        /* **** Component-holders と Actions を連携させる **** */
        actionMediator.registerCHolderMediatorToEachMember(cholderMediator);
        cholderMediator.registerActionMediatorToEachMember(actionMediator);

        /* **** BasePaneをMainWindowにはめ込む **** */
        RCBasePaneCreator basePaneCreator = new RCBasePaneCreator(cholderMediator);
        basePaneCreator.addBasePaneToMainFrame();

        /* **** メニューバーをMainWindowにはめ込む **** */
        RCMenuBarCreator menuBarCreator = new RCMenuBarCreator(actionMediator, cholderMediator);
        menuBarCreator.addMenuBarToMainFrame();

        /* **** 表示 **** */
        MainWindowHolder mainWindowHolder = (MainWindowHolder)
                cholderMediator.getInstanceOfAMember("main_window_holder");
        mainWindowHolder.displayAndInitialize();

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
