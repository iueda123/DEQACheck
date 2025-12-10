package ScriptRunnnerAppDemo.v0_0_0;

import javax.swing.*;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class Commands extends JFrame implements ActionListener {

    private JPanel CommandsPanel;

    private File WorkingDirectory;

    private String Command1;

    private String Command2;

    private String Command3;

    private File OutputFile;


    private JTextField textField;
    private JButton saveButton;

    public Commands() {
        setTitle("JSON Read/Write App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());

        textField = new JTextField(20);
        saveButton = new JButton("Save");

        saveButton.addActionListener(this);

        add(textField);
        add(saveButton);

        // Load data from JSON file on startup
        loadDataFromJson();

        setVisible(true);
    }

    private void loadDataFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File("data.json");
            if (file.exists()) {
                JsonNode root = mapper.readTree(file);
                String value = root.get("value").asText();
                textField.setText(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDataToJson() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.put("value", textField.getText());

        try {
            mapper.writeValue(new File("data.json"), root);
            JOptionPane.showMessageDialog(this, "Data saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while saving data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            saveDataToJson();
        }
    }


    private static void createAndShowGUI() {
        JFrame mainFrame = new JFrame("Commands");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        // ボタンを作成
        JButton saveButton = new JButton("Save");

        //mainPanel.add(new JTextField("a"), BorderLayout.CENTER);

        // コマンドをパネルに配置
        Box command1_panel = Box.createHorizontalBox();
        command1_panel.add(new JLabel("Command 1"));
        command1_panel.add(new JScrollPane(new JTextArea("")));
        mainPanel.add(command1_panel);

        mainPanel.add(Box.createVerticalStrut(5));

        Box command2_panel = Box.createHorizontalBox();
        command2_panel.add(new JLabel("Command 2"));
        command2_panel.add(new JScrollPane(new JTextArea("")));
        mainPanel.add(command2_panel);


        // ボタンをパネルに配置
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);

        // メインパネルにコンポーネントを配置
        //mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel);

        mainFrame.getContentPane().add(mainPanel);

        mainFrame.setSize(400, 300);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
}

