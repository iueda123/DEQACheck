package ScriptRunnnerAppDemo.v0_0_0;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EditPropButton extends JButton {

    private static final String SETTINGS_FILE_PATH = "./settings/ScriptRunnerApp/settings.prop";
    private String propFilePath;

    private static JTextArea textArea = new JTextArea();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame mainFrame = new JFrame("Settings Editor");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // ボタンを作成
        //JButton openButton = new JButton("Open");

        // ボタンをパネルに配置
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new EditPropButton(SETTINGS_FILE_PATH, mainFrame));

        // メインパネルにコンポーネントを配置
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        mainFrame.getContentPane().add(mainPanel);

        mainFrame.setSize(400, 300);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }


    public EditPropButton(String propFilePath, JFrame mainFrame) {
        super("edit prop");
        this.propFilePath = propFilePath;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // settings.propファイルの内容を読み込んでJTextAreaに表示
                try {
                    String content = readSettingsFile();
                    textArea.setText(content);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainFrame, "Error reading settings.prop file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                createEditorWindow(mainFrame);
            }
        });
    }

    private void createEditorWindow(JFrame parentFrame) {
        JFrame editorFrame = new JFrame("Editor");
        editorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ウィンドウを閉じるときのアクションリスナー
        editorFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // 親フレームをアクティブにする
                parentFrame.requestFocus();
            }
        });

        JScrollPane scrollPane = new JScrollPane(textArea);

        Box vbox = Box.createVerticalBox();
        vbox.add(scrollPane);

        Box buttonPanel = Box.createHorizontalBox();
        // Saveボタンのアクションリスナー
        JButton saveButton = new JButton("Save & Close");
        buttonPanel.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 編集内容をsettings.propファイルに保存
                try {
                    String editedContent = textArea.getText();
                    saveSettingsFile(editedContent);
                    JOptionPane.showMessageDialog(parentFrame, "Settings saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(parentFrame, "Error saving settings.prop file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                editorFrame.dispose();
            }
        });

        // Cancelボタンのアクションリスナー
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ウィンドウを閉じる
                editorFrame.dispose();
            }
        });

        vbox.add(buttonPanel);

        editorFrame.getContentPane().add(vbox);

        editorFrame.setSize(300, 200);
        editorFrame.setLocationRelativeTo(parentFrame);
        editorFrame.setVisible(true);
    }

    private String readSettingsFile() throws IOException {
        // settings.propファイルの内容を読み込んで返す
        Path filePath = Paths.get(propFilePath);
        return Files.readString(filePath, StandardCharsets.UTF_8);
    }

    private void saveSettingsFile(String content) throws IOException {
        // settings.propファイルに編集内容を保存
        Path filePath = Paths.get(propFilePath);
        Files.writeString(filePath, content, StandardCharsets.UTF_8);
    }
}
