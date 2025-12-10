package iu.LCAC.Utils.JsonManagerWithConflictSafe;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestComponentForJsonManagerwithConflictSafe extends JPanel implements JsonManagerCallback {

    private final JFrame frame = new JFrame("Over View of Json");

    private final JTextField textField_1 = new JTextField();
    private final JTextField textField_2 = new JTextField();
    private final JLabel statusLbl = new JLabel("No file");

    private final JsonManagerWithConflictSafe jsonManagerWithConflictSafe;

    public TestComponentForJsonManagerwithConflictSafe() {

        // 基本的な使い方
        Path json_file_path = Paths.get("/home/iu/Downloads/test.json");
        this.jsonManagerWithConflictSafe = new JsonManagerWithConflictSafe(json_file_path.toFile(), this);

        // Center Area
        Box centerBase = Box.createVerticalBox();
        Box centerSub1 = Box.createHorizontalBox();
        centerSub1.add(new JLabel("Key1"));
        centerSub1.add(textField_1);
        centerBase.add(centerSub1);
        Box centerSub2 = Box.createHorizontalBox();
        centerSub2.add(new JLabel("Key2"));
        centerSub2.add(textField_2);
        centerBase.add(centerSub2);
        frame.add(centerBase, BorderLayout.CENTER);

        // North Area
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton openBtn = new JButton("Open");
        top.add(openBtn);
        JButton saveBtn = new JButton("Save");
        top.add(saveBtn);
        JButton saveAsBtn = new JButton("Save As");
        top.add(saveAsBtn);
        top.add(new JSeparator(SwingConstants.VERTICAL));
        top.add(statusLbl);
        frame.add(top, BorderLayout.NORTH);

        // Actions
        openBtn.addActionListener(e -> doOpen());
        saveBtn.addActionListener(e -> doSave(false));
        //saveBtn.setEnabled(false);
        saveAsBtn.addActionListener(e -> doSaveAs());
        //saveAsBtn.setEnabled(false);

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });

        this.actionAfterSuccessfullyOpeningJson(this.jsonManagerWithConflictSafe);
    }

    private void doSave(boolean forceOverWrite) {
        boolean rslt_1 = this.jsonManagerWithConflictSafe.setValue("/test/key1", textField_1.getText());
        //if(rslt_1) System.out.println("The value of textField1 was successfully set.");
        boolean rslt_2 = this.jsonManagerWithConflictSafe.setValue("/test/key2", textField_2.getText());
        //if(rslt_2) System.out.println("The value of textField2 was successfully set.");
        this.jsonManagerWithConflictSafe.doSave(forceOverWrite);
    }

    private void doSaveAs() {
        boolean rslt_1 = this.jsonManagerWithConflictSafe.setValue("/test/key1", textField_1.getText());
        //if(rslt_1) System.out.println("The value of textField1 was successfully set.");
        boolean rslt_2 = this.jsonManagerWithConflictSafe.setValue("/test/key2", textField_2.getText());
        //if(rslt_2) System.out.println("The value of textField2 was successfully set.");
        this.jsonManagerWithConflictSafe.doSaveAs();
    }

    public void doOpen() {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("Text files", "txt", "md", "log", "csv", "java", "json"));
        if (fc.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
            this.jsonManagerWithConflictSafe.openJson(fc.getSelectedFile());
        }
    }

    private void setStatus(String s) {
        statusLbl.setText(s);
    }

    @Override
    public Component getFrame() {
        return frame;
        //return this;
    }

    @Override
    public void actionAfterSuccessfullyOpeningJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        //System.out.println("Successfully open JSON from " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
        String value1 = jsonManagerWithConflictSafe.getValue("/test/key1");
        //System.out.println("value1: " + value1);
        textField_1.setText(value1);
        String value2 = jsonManagerWithConflictSafe.getValue("/test/key2");
        //System.out.println("value2: " + value2);
        textField_2.setText(value2);

        long loadedMtime = jsonManagerWithConflictSafe.getLastModifiedTime();

        setStatus("Opened: " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath() + " (mtime=" + JsonManagerWithConflictSafe.formatTimeInJST(loadedMtime) + ")");
    }

    @Override
    public void actionAfterFailingToOpenJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        System.err.println("Failed to open JSON from " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }

    @Override
    public void actionAfterSuccessfullySavingJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        //System.out.println("Successfully saved JSON to " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
        long loadedMtime = jsonManagerWithConflictSafe.getLastModifiedTime();
        setStatus("The values were saved \n" +
                " at " + JsonManagerWithConflictSafe.formatTimeInJST(loadedMtime) + "\n" +
                " to " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }

    @Override
    public void actionAfterFailingToSaveJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        System.err.println("Failed to save JSON to " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }

    @Override
    public void actionAfterSuccessfullyReloadingJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        //System.out.println("Successfully loaded JSON from " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
        String value1 = jsonManagerWithConflictSafe.getValue("/test/key1");
        //System.out.println("value1: " + value1);
        textField_1.setText(value1);
        String value2 = jsonManagerWithConflictSafe.getValue("/test/key2");
        //System.out.println("value2: " + value2);

        textField_2.setText(value2);
        long loadedMtime = jsonManagerWithConflictSafe.getLastModifiedTime();
        setStatus("Reloaded from disk at " + JsonManagerWithConflictSafe.formatTimeInJST(loadedMtime));
    }

    @Override
    public void actionAfterFailingToReloadJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        System.err.println("Failed to reload JSON from " + jsonManagerWithConflictSafe.getJsonFile().getAbsolutePath());
    }


    public static void main(String[] args) {

        TestComponentForJsonManagerwithConflictSafe compWithReloadFunc = new TestComponentForJsonManagerwithConflictSafe();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                compWithReloadFunc.getFrame().setVisible(true);
            }
        });
    }
}
