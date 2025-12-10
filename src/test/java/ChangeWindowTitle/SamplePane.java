package ChangeWindowTitle;

import javax.swing.*;
import java.awt.*;

public class SamplePane extends JPanel {

    public SamplePane(ChangeWindowTitle parent) {
        setLayout(new FlowLayout());

        JButton button = new JButton("タイトルを変更");
        button.setFont(new Font("Sans-Serif", Font.PLAIN, 16));

        button.addActionListener(e -> {
            parent.incrementClickCount();

            // getParent()を繰り返し呼び出してJFrameを探す
            Container container = SamplePane.this.getParent();
            while (container != null && !(container instanceof JFrame)) {
                container = container.getParent();
            }
            if (container instanceof JFrame) {
                JFrame frame = (JFrame) container;
                frame.setTitle("タイトル変更 " + parent.getClickCount() + " 回目");
            }
        });

        add(button);
    }

}
