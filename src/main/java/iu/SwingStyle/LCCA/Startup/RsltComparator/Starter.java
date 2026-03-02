package iu.SwingStyle.LCCA.Startup.RsltComparator;

import iu.SwingStyle.LCCA.Mediator.RsltComparator.RCActionMediator;
import iu.SwingStyle.LCCA.Mediator.RsltComparator.RCActionMediatorFactory;
import iu.SwingStyle.LCCA.Mediator.RsltComparator.RCCHolderMediator;
import iu.SwingStyle.LCCA.Mediator.RsltComparator.RCCHolderMediatorFactory;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.MainWindow.MainWindowHolder;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
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

        /* **** SelectionDialog で AuthorYear と Version を選択 **** */
        SelectionDialog dialog = new SelectionDialog();
        dialog.setVisible(true);

        if (!dialog.isConfirmed()) {
            System.out.println("Selection cancelled. Exiting.");
            System.exit(0);
        }

        String authorYear = dialog.getSelectedAuthorYear();
        String version = dialog.getSelectedVersion();
        System.out.println("Selected: " + authorYear + " / " + version);

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
