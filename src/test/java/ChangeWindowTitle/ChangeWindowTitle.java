package ChangeWindowTitle;

import javax.swing.*;
import java.awt.*;

public class ChangeWindowTitle {
    private int clickCount = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChangeWindowTitle().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("初期タイトル");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        SamplePane samplePane = new SamplePane(this);
        frame.add(samplePane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void incrementClickCount() {
        clickCount++;
    }

    public int getClickCount() {
        return clickCount;
    }


}
