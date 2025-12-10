import javax.swing.*;
import java.awt.*;

public class ScrollBarTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ScrollBar Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            // 縦にJLabelを並べるパネル
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // 10個のJLabelを追加（各高さ100）
            for (int i = 0; i < 10; i++) {
                JLabel label = new JLabel("Label " + (i + 1));
                label.setPreferredSize(new Dimension(500, 100));
                label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                label.setOpaque(true);
                label.setBackground(i % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                label.setAlignmentX(Component.LEFT_ALIGNMENT);
                panel.add(label);
            }

            // パネル全体のPreferredSizeを設定（10個 × 100 = 1000）
            panel.setPreferredSize(new Dimension(500, 1000));

            // JScrollPaneでラップ
            JScrollPane scrollPane = new JScrollPane(panel,
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);

            frame.add(scrollPane);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
