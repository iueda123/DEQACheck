package iu.SwingStyle.LCCA.Startup.RsltComparator_v13;

import iu.SwingStyle.LCCA.Mediator.RsltComparator.RCActionMediator;
import iu.SwingStyle.LCCA.Mediator.RsltComparator.RCActionMediatorFactory;
import iu.SwingStyle.LCCA.Mediator.RsltComparator.RCCHolderMediator;
import iu.SwingStyle.LCCA.Mediator.RsltComparator.RCCHolderMediatorFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.MainWindow.MainWindowHolder;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.File;
import java.util.Enumeration;

/**
 * RsltComparator のエントリーポイント。
 * 起動時に SelectionDialog で AuthorYear と Version を選択し、
 * AI出力を横並びで比較表示するメイン画面を構築する。
 */
public class Starter {

    public static void main(String[] args) {

        /* **** フォントサイズを1.25倍に設定 **** */
        setUIFont(new FontUIResource("SansSerif", Font.PLAIN, 15));

        /* **** ベースディレクトリ調整: share_package 配下で実行する想定を補助 **** */
        String baseDir = System.getProperty("LCCA_BASE_DIR", ".");
        File dataDir = new File(baseDir, "data");
        if (!dataDir.exists()) {
            File shareDataDir = new File("share_package/data");
            if (shareDataDir.exists()) {
                System.setProperty("LCCA_BASE_DIR", "share_package");
            }
        }

        /* **** AuthorYear と Version を決定 **** */
        String authorYear;
        String version;

        if (args.length >= 2) {
            /* コマンドライン引数で両方指定された場合（例: SummaryView経由）はダイアログをスキップ */
            authorYear = args[0];
            version = args[1];
            System.out.println("Selected (from args): " + authorYear + " / " + version);
        } else {
            /* 通常起動: SelectionDialog で AuthorYear と Version を選択 */
            SelectionDialog dialog = new SelectionDialog();
            dialog.setVisible(true);

            if (!dialog.isConfirmed()) {
                System.out.println("Selection cancelled. Exiting.");
                System.exit(0);
            }

            authorYear = dialog.getSelectedAuthorYear();
            version = dialog.getSelectedVersion();
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
