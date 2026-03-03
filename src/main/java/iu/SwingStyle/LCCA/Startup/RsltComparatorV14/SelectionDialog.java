package iu.SwingStyle.LCCA.Startup.RsltComparatorV14;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;

/**
 * DE_v14 専用: AuthorYear のみ選択するモーダルダイアログ。
 * ./data/<AuthorYear>/DE_v14/json が存在するものだけを表示する。
 */
public class SelectionDialog extends JDialog {

    private String selectedAuthorYear = null;
    private boolean confirmed = false;

    private final DefaultListModel<String> authorYearModel = new DefaultListModel<>();
    private final JList<String> authorYearList = new JList<>(authorYearModel);

    private final JButton okButton = new JButton("OK");
    private final JButton cancelButton = new JButton("Cancel");

    public SelectionDialog() {
        super((Frame) null, "RsltComparatorV14 - Select AuthorYear", true);
        initUI();
        scanAuthorYears();
    }

    private void initUI() {
        setLayout(new BorderLayout(8, 8));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(4, 4));
        panel.setBorder(BorderFactory.createTitledBorder("AuthorYear"));
        authorYearList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(new JScrollPane(authorYearList), BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        okButton.addActionListener(e -> onOk());
        cancelButton.addActionListener(e -> onCancel());
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(500, 450);
        setLocationRelativeTo(null);
    }

    private void scanAuthorYears() {
        String baseDir = System.getProperty("LCCA_BASE_DIR", ".");
        File dataDir = new File(baseDir, "data");
        if (!dataDir.exists() || !dataDir.isDirectory()) {
            JOptionPane.showMessageDialog(this,
                    dataDir.getPath() + " が見つかりません。\n作業ディレクトリを確認してください。",
                    "エラー", JOptionPane.ERROR_MESSAGE);
            return;
        }
        File[] dirs = dataDir.listFiles(File::isDirectory);
        if (dirs == null) return;
        Arrays.sort(dirs, (a, b) -> a.getName().compareToIgnoreCase(b.getName()));
        for (File dir : dirs) {
            String name = dir.getName();
            if (name.equals("settings")) continue;
            File jsonDir = new File(dir, "DE_v14/json");
            if (jsonDir.exists() && jsonDir.isDirectory()) {
                authorYearModel.addElement(name);
            }
        }
    }

    private void onOk() {
        selectedAuthorYear = authorYearList.getSelectedValue();
        if (selectedAuthorYear == null) {
            JOptionPane.showMessageDialog(this,
                    "AuthorYear を選択してください。",
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

    public boolean isConfirmed() {
        return confirmed;
    }
}
