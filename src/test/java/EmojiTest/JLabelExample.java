package EmojiTest;

import javax.swing.*;
import java.awt.*;

//https://stackoverflow.com/questions/29028072/unicode-characters-in-app-doesnt-show-correctly/29028982#29028982
public class JLabelExample extends JFrame {

    public JLabelExample() {
        // ウィンドウの設定
        setTitle("JLabel Example");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Unicodeエスケープシーケンス (📂)
        String folderEmoji = "フォルダ\uD83D\uDCC2";

        String heart = new String(Character.toChars(10084));

        // JLabelにフォルダの絵文字を設定
        JLabel label = new JLabel(folderEmoji);

        // フォントを設定して絵文字が見やすいようにする
        label.setFont(new Font("SansSerif", Font.PLAIN, 48));

        // ラベルをフレームに追加
        add(label, BorderLayout.CENTER);

        // ウィンドウを表示
        setVisible(true);
    }

    public static void main(String[] args) {
        // SwingのUIを作成して表示
        SwingUtilities.invokeLater(() -> new JLabelExample());
    }
}
