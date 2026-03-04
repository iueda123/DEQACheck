package iu.SwingStyle.LCCA.Startup.RsltComparator_v13;

import iu.SwingStyle.LCCA.Mediator.RsltComparator.RCActionMediator;
import iu.SwingStyle.LCCA.Mediator.RsltComparator.RCCHolderMediator;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.MainWindow.MainWindowHolder;

import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        fileMenu.addSeparator();

        JMenuItem openMaterials = new JMenuItem("Open materials/non-optimized");
        openMaterials.addActionListener(e -> {
            String authorYear = cHolderMediator.getAuthorYearStr();
            String baseDir = System.getProperty("LCCA_BASE_DIR", ".");
            Path path = Paths.get(baseDir, "data", authorYear, "materials", "non-optimized");
            openPath(path, "materials/non-optimized");
        });
        fileMenu.add(openMaterials);

        JMenuItem openGuide = new JMenuItem("Open DE_Guide_v14.md");
        openGuide.addActionListener(e -> {
            String baseDir = System.getProperty("LCCA_BASE_DIR", ".");
            Path path = Paths.get(baseDir, "prompts", "DE_Guide_v14.md");
            openPath(path, "DE_Guide_v14.md");
        });
        fileMenu.add(openGuide);

        return menuBar;
    }

    private void openPath(Path path, String label) {
        try {
            if (!Files.exists(path)) {
                JOptionPane.showMessageDialog(
                        null,
                        "見つかりません: " + path.toAbsolutePath(),
                        label,
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            if (!Desktop.isDesktopSupported()) {
                JOptionPane.showMessageDialog(
                        null,
                        "このシステムではファイルを開く機能がサポートされていません。",
                        label,
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            Desktop.getDesktop().open(path.toFile());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "ファイルを開く際にエラーが発生しました: " + ex.getMessage(),
                    label,
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
