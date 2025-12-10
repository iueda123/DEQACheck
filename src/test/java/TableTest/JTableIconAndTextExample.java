package TableTest;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JTableIconAndTextExample extends JFrame {

    public JTableIconAndTextExample() {
        // ウィンドウの設定
        setTitle("JTable Icon and Text Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // テーブルモデルを作成 (列名を指定)
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Item");

        // JTableを作成
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // アイコンの読み込み
        Icon folderIcon = UIManager.getIcon("FileView.directoryIcon");

        // データを追加 (アイコンとテキスト)
        tableModel.addRow(new Object[]{"Folder 1"});
        tableModel.addRow(new Object[]{"Folder 2"});
        tableModel.addRow(new Object[]{"Folder 3"});

        // カスタムレンダラーを設定
        table.getColumnModel().getColumn(0).setCellRenderer(new IconAndTextRenderer(folderIcon));

        // ウィンドウを表示
        setVisible(true);
    }

    // カスタムレンダラークラス (アイコンとテキストを表示)
    class IconAndTextRenderer extends DefaultTableCellRenderer {
        private Icon icon;

        public IconAndTextRenderer(Icon icon) {
            this.icon = icon;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // JLabelとしてセルを表示
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // テキストとアイコンを設定
            label.setIcon(icon);
            label.setText((String) value);

            // アイコンとテキストの間に余白を設定
            label.setIconTextGap(10);

            return label;
        }
    }

    public static void main(String[] args) {
        // SwingのUIを作成して表示
        SwingUtilities.invokeLater(() -> new JTableIconAndTextExample());
    }
}
