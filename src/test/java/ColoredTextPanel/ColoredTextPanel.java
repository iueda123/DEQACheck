package ColoredTextPanel;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class ColoredTextPanel {
    public static void main(String[] args) {
        // JFrameの作成
        JFrame frame = new JFrame("Colored Text Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // JTextPaneの作成
        JTextPane textPane = new JTextPane();
        StyledDocument doc = textPane.getStyledDocument();

        // スタイルの作成
        SimpleAttributeSet red = new SimpleAttributeSet();
        StyleConstants.setForeground(red, Color.RED);

        SimpleAttributeSet blue = new SimpleAttributeSet();
        StyleConstants.setForeground(blue, Color.BLUE);

        // テキストの挿入
        try {
            doc.insertString(doc.getLength(), "This text is red.\n", red);
            doc.insertString(doc.getLength(), "This text is blue.", blue);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        // JScrollPaneにJTextPaneを追加
        JScrollPane scrollPane = new JScrollPane(textPane);
        frame.add(scrollPane);

        // JFrameの表示
        frame.setVisible(true);
    }
}
