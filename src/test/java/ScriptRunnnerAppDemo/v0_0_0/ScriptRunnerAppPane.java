package ScriptRunnnerAppDemo.v0_0_0;

//-*- mode:java; encoding:utf8n; coding:utf-8 -*-
// vim:set fileencoding=utf-8:
//http://terai.xrea.jp/Swing/SwingWorker.html


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.List;

public class ScriptRunnerAppPane extends JPanel {

    private final AnimatedLabel animatedLabel = new AnimatedLabel();
    private static String bashScriptFilePath = "./settings/ScriptRunnerApp/sample_script.sh";
    private static String logFilePath = "settings/ScriptRunnerApp/sample_script.txt";
    private static String propFilePath = "settings/ScriptRunnerApp/settings.prop";

    private static final JFrame mainFrame = new JFrame("ScriptRunnerApp");
    private final JTextArea textArea = new JTextArea();
    private final JPanel progressBarPanel = new JPanel(new BorderLayout());
    private final JButton runButton = new JButton(new RunAction());
    private final JButton cancelButton = new JButton(new CancelAction());
    private JButton editPropButton;
    private SwingWorker<String, String> worker;


    private static Process currentProcess;

    public ScriptRunnerAppPane() {
        super(new BorderLayout(5, 5));

        textArea.setEditable(false);
        add(new JScrollPane(textArea));


        Box hbox = Box.createHorizontalBox();

        animatedLabel.addMouseListener(new TextAreaOpener());
        hbox.add(animatedLabel);

        hbox.add(Box.createHorizontalGlue());

        hbox.add(runButton);

        hbox.add(Box.createHorizontalStrut(2));

        hbox.add(cancelButton);

        hbox.add(Box.createHorizontalStrut(2));

        editPropButton = new EditPropButton(propFilePath, mainFrame);
        hbox.add(editPropButton);

        add(hbox, BorderLayout.NORTH);


        add(progressBarPanel, BorderLayout.SOUTH);

        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setPreferredSize(new Dimension(320, 180));
    }


    public static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Box hbox = Box.createHorizontalBox();
        hbox.add(new ScriptRunnerAppPane());

        mainFrame.getContentPane().add(hbox);

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static JProgressBar progressBar = new JProgressBar(0, 100);

    class RunAction extends AbstractAction {

        public RunAction() {
            super("run");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            System.out.println("actionPerformed() is EDT?: " + EventQueue.isDispatchThread());
            runButton.setEnabled(false);
            cancelButton.setEnabled(true);

            animatedLabel.startAnimation();

            progressBarPanel.removeAll();
            progressBarPanel.add(progressBar);
            progressBarPanel.revalidate();
            progressBar.setIndeterminate(true);

            /*
             * SwingWokerはジェネリクスの二つのパラメータを使用できる．
             * これらのパラメータは，非同期に実行しているスレッドからEDTに情報を引き渡すときに使う．
             * execute()の実行により，処理が開始される．
             *
             * 第 1 パラメータ	doInBackground メソッドの返り値の型
             * 第 2 パラメータ	publish メソッドの引数の型
             *
             * http://kevin3sei.blog95.fc2.com/blog-entry-208.html より
             */
            BashProcessor worker = new BashProcessor();
            worker.execute();
        }
    }

    class BashProcessor extends SwingWorker {

        public BashProcessor() {
            this.addPropertyChangeListener(new ProgressListener(progressBar));
        }

        @Override
        public String doInBackground() {
            System.out.println("doInBackground() is EDT?: " + EventQueue.isDispatchThread());

            // TODO: Load Prop
            //loadCommands();
            //


            publish("------------------------------");

            setProgress(50); //setProgress()はSwingWorkerクラスのメソッド

            int return_num = executeBashScript(bashScriptFilePath, logFilePath);

            publish(String.valueOf(return_num));
            setProgress(100); //setProgress()はSwingWorkerクラスのメソッド

            publish("------------------------------");

            return "Done";
        }


        /**
         * process()は
         * doInBackground()と並行に，EDT上で実行させるメソッド．
         * doInBackground()内で，publish()が呼び出された際に実行される．
         * 引数は，Listクラスで，ジェネリスクはSwingWorkerクラスの第 2 パラメータ．
         * <p>
         * EDT=Swingへの操作が可能なスレッド。
         *
         * @param chunks
         */
        @Override
        protected void process(List chunks) {
            System.out.println("process() is EDT?: " + EventQueue.isDispatchThread());
            for (Object message : chunks) {
                appendLineToTextArea("process: " + (String) message);
            }
        }

        @Override
        public void done() {
            System.out.println("done() is EDT?: " + EventQueue.isDispatchThread());
            animatedLabel.stopAnimation();
            runButton.setEnabled(true);
            cancelButton.setEnabled(false);
            progressBarPanel.remove(progressBar);
            progressBarPanel.revalidate();
            String text = null;
            if (isCancelled()) {
                text = "Cancelled";
            } else {
                try {
                    text = (String) super.get();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    text = "Exception";
                }
            }
            System.out.println(text);
            appendLineToTextArea(text);
        }
    }

    private static int executeBashScript(String scriptPath, String outputPath) {
        try {
            // Bash スクリプトを実行する ProcessBuilder を作成
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", scriptPath);
            processBuilder.redirectErrorStream(true);

            // プロセスの標準出力をファイルに書き込むためのストリーム
            File output_file = new File(outputPath);

            if (output_file.exists()) {
                output_file.delete();
            } else {
                System.out.println(output_file.getAbsolutePath() + "は存在しません。");
            }
            output_file.createNewFile();

            OutputStream outputStream = new FileOutputStream(outputPath);

            // プロセスを開始
            currentProcess = processBuilder.start();

            // プロセスの標準出力を読み取るための Reader
            BufferedReader reader = new BufferedReader(new InputStreamReader(currentProcess.getInputStream()));

            // ファイルに書き込みながら標準出力を表示
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                outputStream.write((line + System.lineSeparator()).getBytes());
            }

            // ファイルへの書き込みを閉じる
            outputStream.close();

            // プロセスの終了まで待機
            int exitCode = currentProcess.waitFor();

            // 終了コードを表示
            System.out.println("Bash Script Exit Code: " + exitCode);

            return exitCode;

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
            return 99;
        }
    }


    class CancelAction extends AbstractAction {

        public CancelAction() {
            super("cancel");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (worker != null && !worker.isDone()) {
                worker.cancel(true);
            }
            worker = null;

            if (currentProcess != null) {
                currentProcess.destroy();
            }

        }
    }

    /**
     * @param str
     */
    private void appendLineToTextArea(String str) {
        textArea.append(str + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    private class TextAreaOpener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JOptionPane.showInputDialog("OK");

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}


