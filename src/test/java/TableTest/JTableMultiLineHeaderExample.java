package TableTest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class JTableMultiLineHeaderExample extends JFrame {

    public JTableMultiLineHeaderExample() {
        // ウィンドウの設定
        setTitle("JTable Multi-line Header Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // テーブルモデルを作成 (列名を指定)
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("First Name<br>Last Name");
        tableModel.addColumn("Age<br>(years)");

        // データを追加
        tableModel.addRow(new Object[]{"1", "Alice", 25});
        tableModel.addRow(new Object[]{"2", "Bob", 30});
        tableModel.addRow(new Object[]{"3", "Carol", 28});
        tableModel.addRow(new Object[]{"4", "Dave", 35});

        // JTableを作成
        JTable table = new JTable(tableModel);

        // カラムの見出しをHTML形式に変更
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(1).setHeaderValue("<html>First Name<br>Last Name</html>");
        columnModel.getColumn(2).setHeaderValue("<html>Age<br>(years)</html>");

        // カスタムレンダラーでヘッダーの高さを調整
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new MultiLineHeaderRenderer());
        header.setPreferredSize(new Dimension(header.getWidth(), 50));  // 高さを50ピクセルに設定

        // JTableHeaderの設定
        header.setReorderingAllowed(false);  // カラムの並べ替えを無効化
        header.setResizingAllowed(true);  // サイズ変更は有効

        // JScrollPaneにJTableを追加
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        // ウィンドウを表示
        setVisible(true);
    }

    // カスタムレンダラークラス
    public class MultiLineHeaderRenderer extends JLabel implements TableCellRenderer {
        public MultiLineHeaderRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
            setFont(new Font("Dialog", Font.PLAIN, 12));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            return this;
        }
    }

    public static void main(String[] args) {
        // SwingのUIを作成して表示
        SwingUtilities.invokeLater(() -> new JTableMultiLineHeaderExample());
    }
}

