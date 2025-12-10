package ToggleTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleTextAreaExample extends JFrame {

    private JTextArea textArea;  // 3段目に表示するテキストエリア
    private JPanel textAreaPanel; // テキストエリアを含むパネル
    private boolean isTextAreaVisible = true; // テキストエリアが表示されているかどうか

    public ToggleTextAreaExample() {
        // ウィンドウの設定
        setTitle("Toggle TextArea Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // メインのパネルを作成
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel, BorderLayout.CENTER);

        // 1段目: JLabel
        JLabel label = new JLabel("This is the first line (JLabel)");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(label);

        // 2段目: JButton
        JButton toggleButton = new JButton("Toggle Text Area");
        toggleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(toggleButton);

        // 3段目: JTextArea (最初は表示されている)
        textArea = new JTextArea(5, 30);
        textArea.setText("This is the third line (JTextArea)");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // JTextAreaをスクロール可能にするためにJScrollPaneに追加
        JScrollPane scrollPane = new JScrollPane(textArea);

        // テキストエリアを含むパネル
        textAreaPanel = new JPanel();
        textAreaPanel.add(scrollPane);
        mainPanel.add(textAreaPanel);

        // ボタンのアクションリスナー
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleTextAreaVisibility();  // テキストエリアの表示/非表示を切り替える
            }
        });

        // ウィンドウを表示
        setVisible(true);
    }

    // テキストエリアの表示/非表示を切り替えるメソッド
    private void toggleTextAreaVisibility() {
        isTextAreaVisible = !isTextAreaVisible;
        textAreaPanel.setVisible(isTextAreaVisible);
        // レイアウトの再調整
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        // SwingのUIを作成して表示
        SwingUtilities.invokeLater(() -> new ToggleTextAreaExample());
    }
}
