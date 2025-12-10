package TableTest;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JTableExample extends JFrame {

    public JTableExample() {
        // ウィンドウの設定
        setTitle("JTable Example");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // テーブルモデルを作成 (列名を指定)
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Icon");  // アイコンを表示する列

        // JTableを作成
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // 書き込むUnicodeエスケープシーケンス (📂)
        String folderEmoji = "\uD83D\uDCC2フォルダ";

        // テーブルにデータ（絵文字）を追加
        tableModel.addRow(new Object[]{folderEmoji});

        // テーブルのフォントを変更 (絵文字がサポートされているフォント)
        //table.setFont(new Font("Dialog", Font.PLAIN, 18));

        // カスタムセルレンダラーを設定
        table.getColumnModel().getColumn(0).setCellRenderer(new EmojiRenderer());

        // ウィンドウを表示
        setVisible(true);
    }

    public static void main(String[] args) {
        // SwingのUIを作成して表示
        SwingUtilities.invokeLater(() -> {
            new JTableExample();
        });
    }

    // カスタムレンダラークラス
    class EmojiRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            // デフォルトのレンダラー動作を継承
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // 値がString型ならカスタムフォントを設定して絵文字を表示
            if (value instanceof String) {
                setFont(new Font("Dialog", Font.PLAIN, 18)); // Unicode対応のフォントを設定
                setText((String) value);  // セルのテキストとして絵文字を設定
            }

            return this;
        }
    }
}