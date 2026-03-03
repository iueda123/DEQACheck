package iu.SwingStyle.LCCA.Startup.RsltComparator_v13;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;

/**
 * AuthorYear と バージョン(DE_v10, QA_v9 等)を選択するモーダルダイアログ。
 * Phase 1: ./data/ フォルダをスキャンし AuthorYear を JList で表示
 * Phase 2: 選択した AuthorYear 内のバージョンフォルダを JList で表示
 */
public class SelectionDialog extends JDialog {

    private String selectedAuthorYear = null;
    private String selectedVersion = null;
    private boolean confirmed = false;

    private final DefaultListModel<String> authorYearModel = new DefaultListModel<>();
    private final JList<String> authorYearList = new JList<>(authorYearModel);

    private final DefaultListModel<String> versionModel = new DefaultListModel<>();
    private final JList<String> versionList = new JList<>(versionModel);

    private final JButton okButton = new JButton("OK");
    private final JButton cancelButton = new JButton("Cancel");

    public SelectionDialog() {
        super((Frame) null, "RsltComparator - Select AuthorYear and Version", true);
        initUI();
        scanAuthorYears();
    }

    private void initUI() {
        setLayout(new BorderLayout(8, 8));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Phase 1: AuthorYear list
        JPanel leftPanel = new JPanel(new BorderLayout(4, 4));
        leftPanel.setBorder(BorderFactory.createTitledBorder("AuthorYear"));
        authorYearList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        authorYearList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                onAuthorYearSelected();
            }
        });
        leftPanel.add(new JScrollPane(authorYearList), BorderLayout.CENTER);

        // Phase 2: Version list
        JPanel rightPanel = new JPanel(new BorderLayout(4, 4));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Version"));
        versionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rightPanel.add(new JScrollPane(versionList), BorderLayout.CENTER);

        // Split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(250);
        add(splitPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        okButton.addActionListener(e -> onOk());
        cancelButton.addActionListener(e -> onCancel());
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(600, 500);
        setLocationRelativeTo(null);
    }

    private void scanAuthorYears() {
        File dataDir = new File("./data");
        if (!dataDir.exists() || !dataDir.isDirectory()) {
            JOptionPane.showMessageDialog(this,
                    "./data/ フォルダが見つかりません。\n作業ディレクトリを確認してください。",
                    "エラー", JOptionPane.ERROR_MESSAGE);
            return;
        }
        File[] dirs = dataDir.listFiles(File::isDirectory);
        if (dirs == null) return;
        Arrays.sort(dirs, (a, b) -> a.getName().compareToIgnoreCase(b.getName()));
        for (File dir : dirs) {
            String name = dir.getName();
            if (!name.equals("settings")) {
                authorYearModel.addElement(name);
            }
        }
    }

    private void onAuthorYearSelected() {
        versionModel.clear();
        String ay = authorYearList.getSelectedValue();
        if (ay == null) return;

        File ayDir = new File("./data/" + ay);
        if (!ayDir.exists() || !ayDir.isDirectory()) return;

        File[] dirs = ayDir.listFiles(File::isDirectory);
        if (dirs == null) return;
        Arrays.sort(dirs, (a, b) -> a.getName().compareToIgnoreCase(b.getName()));
        for (File dir : dirs) {
            String name = dir.getName();
            // DE_* および QA_* パターンにマッチするフォルダのみ表示
            if (name.startsWith("DE_") || name.startsWith("QA_")) {
                // json/ サブディレクトリが存在するか確認
                File jsonDir = new File(dir, "json");
                if (jsonDir.exists() && jsonDir.isDirectory()) {
                    versionModel.addElement(name);
                }
            }
        }
    }

    private void onOk() {
        selectedAuthorYear = authorYearList.getSelectedValue();
        selectedVersion = versionList.getSelectedValue();

        if (selectedAuthorYear == null || selectedVersion == null) {
            JOptionPane.showMessageDialog(this,
                    "AuthorYear と Version の両方を選択してください。",
                    "選択エラー", JOptionPane.WARNING_MESSAGE);
            return;
        }
        confirmed = true;
        dispose();
    }

    private void onCancel() {
        confirmed = false;
        dispose();
    }

    public String getSelectedAuthorYear() {
        return selectedAuthorYear;
    }

    public String getSelectedVersion() {
        return selectedVersion;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
