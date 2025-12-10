package PrepForFsPanel;

import PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3;
import PrepForFsPanel.SupportingClasses.SwingFileFilterFactory;
import PrepForFsPanel.SupportingClasses.SystemPropertyManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class CommandProcessPanel extends JPanel  {

    //Path String
    String RCodeFolderPathStr = SystemPropertyManager.getUserHome();
    String BashCodeFolderPathStr = SystemPropertyManager.getUserHome();
    String PythonCodeFolderPathStr = SystemPropertyManager.getUserHome();
    String OutputFolderPathStr = SystemPropertyManager.getUserHome();
    String InputFolderPathStr = SystemPropertyManager.getUserHome();

    // Code Selector
    protected FileChooserPanel_with_FileDrop_v3 CodeFileSelector;

    // Argument Selector
    //protected PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3 InputFolderSelector;
    //protected PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3 OutputFolderSelector;

    // Argument Text
    protected ArrayList<String> ArgumentList = new ArrayList<>();

    // Command String TextArea
    protected JEditorPane CommandStringTextArea;

    // Code
    protected JTextArea CodeArea;

    // Log
    protected JEditorPane HtmlLogArea;
    protected JEditorPane LogArea;

    // Buttons
    JButton RunButton;

    JButton LoadCodeButton;
    JButton SaveCodeButton;
    JButton OpenWorkingDirectoryButton;

    JButton ClearLogButton;
    JButton CancelButton;
    protected JButton CheckCommandStringButton;

    protected JButton SaveResultButton;


    // SwingWorker
    LongTaskWorker Worker;

    // Status Disp Area
    protected JLabel StatusLabel = new JLabel("    ");

    protected JButton createLoadButton() {
        if (LoadCodeButton == null) {
            LoadCodeButton = new JButton("Load");
            LoadCodeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    loadCodeFile(Paths.get(CodeFileSelector.getText()));
                }
            });
        }
        return LoadCodeButton;

    }

    protected JButton createSaveCodeButton() {
        if (SaveCodeButton == null) {
            SaveCodeButton = new JButton("Save");
            SaveCodeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    saveCodeToFile(CodeFileSelector.getText());
                }
            });
        }
        return SaveCodeButton;
    }

    protected JButton createOpenWorkingDirButton() {
        if (OpenWorkingDirectoryButton == null) {
            OpenWorkingDirectoryButton = new JButton("Open WD");
            OpenWorkingDirectoryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if (SystemPropertyManager.getOsName().endsWith("Linux")) {
                        ArrayList<String> command_list = new ArrayList<>();
                        command_list.add("nautilus");
                        command_list.add("-s");
                        command_list.add(new File(CodeFileSelector.getText()).getAbsolutePath());
                        runCode(command_list);
                    }
                }
            });
        }
        return OpenWorkingDirectoryButton;
    }


    protected FileChooserPanel_with_FileDrop_v3 createBashFileSelector() {
        if (CodeFileSelector == null) {
            CodeFileSelector = new FileChooserPanel_with_FileDrop_v3(
                    null, BashCodeFolderPathStr, "Please select a Bash code file.",
                    SwingFileFilterFactory.createFilterFromExtension("sh", "bash code file"),
                    //FileChooserPanel_with_FileDrop.SELECTION_MODE_DIRECTORIES_ONLY,
                    //FileChooserPanel_with_FileDrop.SELECTION_MODE_FILES_AND_DIRECTORY,
                    //FileChooserPanel_with_FileDrop_v20200124.SELECTION_MODE_DIRECTORIES_ONLY_WITH_SHOWING_FILES,
                    FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_FILES_ONLY,
                    false,
                    FileChooserPanel_with_FileDrop_v3.SHOW_MODE_SELECT
            ) {
                @Override
                protected void performActionAfterSelectingFile() {
                    System.out.println("Selected Bash file was changed.");

                    // Load a code file
                    loadCodeFile(Paths.get(CodeFileSelector.getText()));
                }
            };
        }
        return CodeFileSelector;
    }

    protected FileChooserPanel_with_FileDrop_v3 createRFileSelector() {
        if (CodeFileSelector == null) {
            CodeFileSelector = new FileChooserPanel_with_FileDrop_v3(
                    null, RCodeFolderPathStr, "Please select a R code file.",
                    SwingFileFilterFactory.createFilterFromExtension("R", "R code file"),
                    //FileChooserPanel_with_FileDrop.SELECTION_MODE_DIRECTORIES_ONLY,
                    //FileChooserPanel_with_FileDrop.SELECTION_MODE_FILES_AND_DIRECTORY,
                    //FileChooserPanel_with_FileDrop_v20200124.SELECTION_MODE_DIRECTORIES_ONLY_WITH_SHOWING_FILES,
                    FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_FILES_ONLY,
                    false,
                    FileChooserPanel_with_FileDrop_v3.SHOW_MODE_SELECT
            ) {
                @Override
                protected void performActionAfterSelectingFile() {
                    System.out.println("Selected R file was changed.");

                    // Load a code file
                    loadCodeFile(Paths.get(CodeFileSelector.getText()));
                }
            };
        }
        return CodeFileSelector;
    }

    protected FileChooserPanel_with_FileDrop_v3 createPythonFileSelector() {
        if (CodeFileSelector == null) {
            CodeFileSelector = new FileChooserPanel_with_FileDrop_v3(
                    null, PythonCodeFolderPathStr, "Please select a Python code file.",
                    SwingFileFilterFactory.createFilterFromExtension("py", "Python code file"),
                    //FileChooserPanel_with_FileDrop.SELECTION_MODE_DIRECTORIES_ONLY,
                    //FileChooserPanel_with_FileDrop.SELECTION_MODE_FILES_AND_DIRECTORY,
                    //FileChooserPanel_with_FileDrop_v20200124.SELECTION_MODE_DIRECTORIES_ONLY_WITH_SHOWING_FILES,
                    FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_FILES_ONLY,
                    false,
                    FileChooserPanel_with_FileDrop_v3.SHOW_MODE_SELECT
            ) {
                @Override
                protected void performActionAfterSelectingFile() {
                    System.out.println("Selected Python file was changed.");

                    // Load Python code
                    loadCodeFile(Paths.get(CodeFileSelector.getText()));
                }
            };
        }
        return CodeFileSelector;
    }

    /*
    protected PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3 createInputFolderSelector() {
        if (InputFolderSelector == null) {
            InputFolderSelector = new PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3(
                    null, InputFolderPathStr, "Please select a input folder.",
                    iu.tools.filefilters.PrepForFsPanel.SupportingClasses.SwingFileFilterFactory.createFilterForDirectory(),
                    PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_DIRECTORIES_ONLY,
                    //PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_FILES_AND_DIRECTORY,
                    //PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_DIRECTORIES_ONLY_WITH_SHOWING_FILES,
                    //PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_FILES_ONLY,
                    false,
                    PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3.SHOW_MODE_SELECT
            ) {
                @Override
                protected void performActionAfterSelectingFile() {
                    System.out.println("Selected folder was changed.");

                }
            };
        }
        return InputFolderSelector;
    }
    */
    /*
    protected PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3 createOutputFolderSelector() {
        if (OutputFolderSelector == null) {
            OutputFolderSelector = new PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3(
                    null, OutputFolderPathStr, "Please select a output folder.",
                    iu.tools.filefilters.PrepForFsPanel.SupportingClasses.SwingFileFilterFactory.createFilterForDirectory(),
                    PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_DIRECTORIES_ONLY,
                    //PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_FILES_AND_DIRECTORY,
                    //PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_DIRECTORIES_ONLY_WITH_SHOWING_FILES,
                    //PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_FILES_ONLY,
                    false,
                    PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3.SHOW_MODE_SELECT
            ) {
                @Override
                protected void performActionAfterSelectingFile() {
                    System.out.println("Selected folder was changed.");

                }
            };
        }
        return OutputFolderSelector;
    }
    */

    protected JButton createCheckCommandStringButton() {
        if (CheckCommandStringButton == null) {
            CheckCommandStringButton = new JButton("Check Command String");
            CheckCommandStringButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    checkCommandString();
                }
            });
        }
        return CheckCommandStringButton;
    }

    protected JEditorPane createCommandStringCheckArea(int default_row_num) {
        if (CommandStringTextArea == null) {
            CommandStringTextArea = new JEditorPane();
            CommandStringTextArea.setContentType("text/html");
            String text = "";
            text += "<html><body>";
            for (int i = 0; i < default_row_num; i++) {
                text += "<br>";
            }
            text += "</html></body>";
            CommandStringTextArea.setText(text);
            Font custom_font1 = new Font(Font.DIALOG_INPUT, Font.PLAIN, 14);
            CommandStringTextArea.setFont(custom_font1);

            CommandStringTextArea.setEditable(false);
        }
        return CommandStringTextArea;
    }

    protected JButton createRunBashButton() {
        if (RunButton == null) {
            RunButton = new JButton("Run");
            RunButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    setupArguments();
                    if (ArgumentList.size() == 0) {
                        System.out.println("The size of ArgumentList is ZERO!!!");
                    }
                    String[] arguments = (String[]) ArgumentList.toArray(new String[0]);

                    File bash_file = new File(CodeFileSelector.getText());

                    runBashCode(bash_file, arguments);
                }
            });
        }
        return RunButton;
    }

    protected JButton createRunRButton() {
        if (RunButton == null) {
            RunButton = new JButton("Run");
            RunButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    setupArguments();
                    if (ArgumentList.size() == 0) {
                        System.out.println("The size of ArgumentList is ZERO!!!");
                    }
                    String[] arguments = (String[]) ArgumentList.toArray(new String[0]);

                    File r_file = new File(CodeFileSelector.getText());

                    runRCode(r_file, arguments);
                }
            });
        }
        return RunButton;
    }

    protected JButton createRunPyhonButton() {
        if (RunButton == null) {
            RunButton = new JButton("Run");
            RunButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    setupArguments();
                    if (ArgumentList.size() == 0) {
                        System.out.println("The size of ArgumentList is ZERO!!!");
                    }
                    String[] arguments = (String[]) ArgumentList.toArray(new String[0]);

                    File r_file = new File(CodeFileSelector.getText());

                    runPythonCode(r_file, arguments);
                }
            });
        }
        return RunButton;
    }

    protected JButton createCancelButton() {
        if (CancelButton == null) {
            CancelButton = new JButton("Cancel");
            CancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if (Worker != null && !Worker.isDone()) {
                        Worker.cancel(true);
                    }
                    Worker = null;
                }
            });
        }
        return CancelButton;
    }

    protected JButton createClearLogButton() {
        if (ClearLogButton == null) {
            ClearLogButton = new JButton("Clear Log");
            ClearLogButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    LogArea.setText("");
                    StatusLabel.setText("");
                }
            });
        }
        return ClearLogButton;
    }

    protected JButton createSaveResultButton() {
        if (SaveResultButton == null) {
            SaveResultButton = new JButton("Save Results");
            SaveResultButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    saveResultFiles();
                }
            });
        }
        return SaveResultButton;
    }

    protected JTextArea createCodeArea() {
        if (CodeArea == null) {
            CodeArea = new JTextArea();
            //CodeArea.setEditable(false);
            Font custom_font1 = new Font(Font.DIALOG_INPUT, Font.PLAIN, 14);
            CodeArea.setFont(custom_font1);
        }
        return CodeArea;
    }

    protected JEditorPane createLogArea() {
        if (LogArea == null) {
            LogArea = new JEditorPane();
            LogArea.setContentType("text/plain");
            Font custom_font1 = new Font(Font.DIALOG_INPUT, Font.PLAIN, 14);
            LogArea.setFont(custom_font1);
        }
        return LogArea;
    }

    protected void appendLineToLogArea(String text_line) {
        String current_text = LogArea.getText();
        current_text = current_text + text_line;
        LogArea.setText(current_text);
    }

    protected JEditorPane createHtmlLogArea() {
        if (HtmlLogArea == null) {
            HtmlLogArea = new JEditorPane();
            HtmlLogArea.setContentType("text/html");
            HtmlLogArea.setText("<html><body>");
            Font custom_font1 = new Font(Font.DIALOG_INPUT, Font.PLAIN, 14);
            HtmlLogArea.setFont(custom_font1);
        }
        return HtmlLogArea;
    }

    /*
    protected void appendLineToHtmlLogArea(String html_text_line) {
        String current_text = HtmlLogArea.getText();
        //System.out.println(current_text);
        current_text = current_text.replaceFirst("</body>", html_text_line + "</body>");
        HtmlLogArea.setText(current_text);
    }
    */

    protected void loadCodeFile(Path a_code_file_path) {

        System.out.println("load a code file: " + a_code_file_path.toAbsolutePath());

        BufferedReader br = null;

        CodeArea.setText("");

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(a_code_file_path.toFile().getAbsolutePath()), "UTF-8"));

            String line;
            while ((line = br.readLine()) != null) {
                System.out.print(line + "\n");
                CodeArea.append(line + "\n");
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
            }
        }

        CodeArea.revalidate();
        CodeArea.repaint();
    }

    protected void saveCodeToFile(String a_code_file_path_str) {
        // フォルダではないかチェック
        if (new File(a_code_file_path_str).isDirectory()) {
            StatusLabel.setText("'" + a_code_file_path_str + "' is not a file.");
            return;
        }
        // 上書き確認
        if (new File(a_code_file_path_str).exists()) {

            Object[] options = {"No", "Yes"};
            String message = "'" + a_code_file_path_str + "' already exits. Do you want to overwrite it?";
            String title = "Overwrite Checing!!!";
            int ans = JOptionPane.showOptionDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null,     //do not use a custom Icon
                    options,  //the titles of buttons
                    options[0]); //default button title
            if (ans == 0) {
                StatusLabel.setText("Save processing was aborted.");
                return;
            }
        }

        // Save Processing
        String[] code_lines = CodeArea.getText().split("\n");

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(a_code_file_path_str), "UTF-8"));
            System.out.println("Saving...");
            for (String line : code_lines) {
                bw.write(line + "\n");
                System.out.println(line + "->" + a_code_file_path_str);
            }
            System.out.println("The code was saved to '" + a_code_file_path_str + "'");
            StatusLabel.setText("The code was saved to '" + a_code_file_path_str + "'");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            StatusLabel.setText("Save failed: " + ex.getLocalizedMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            StatusLabel.setText("Save failed: " + ex.getLocalizedMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                StatusLabel.setText(ex.getLocalizedMessage());
            }
        }
    }

    /* **** Regarding Code String Checking **** */



    /* **** Regarding Code Running ***** */

    protected void runBashCode(File bash_file) {
        runBashCode(bash_file, new String[]{});
    }

    protected void runBashCode(File bash_file, String[] arguments) {
        if (bash_file.isFile()) {

            ArrayList<String> command_list = new ArrayList<>();

            // Change working directory
            File working_directory = bash_file.getParentFile();

            // Construct Command
            command_list.add("bash");
            command_list.add("" + bash_file.getAbsolutePath() + "");
            for (String arg : arguments) {
                command_list.add(arg);
            }

            // run code
            runCode(command_list, working_directory);
        } else {
            StatusLabel.setText("'" + bash_file.getAbsolutePath() + "' is not a file.");
        }
    }

    protected void runRCode(File r_code_file, String[] arguments) {
        if (r_code_file.isFile()) {

            ArrayList<String> command_list = new ArrayList<>();

            // Change working directory
            File working_directory = r_code_file.getParentFile();

            // Construct Command
            command_list.add("Rscript");
            command_list.add(r_code_file.getAbsolutePath());
            for (String arg : arguments) {
                command_list.add(arg);
            }

            // run code
            runCode(command_list, working_directory);
        } else {
            StatusLabel.setText("'" + r_code_file.getAbsolutePath() + "' is not a file.");
        }
    }

    protected void runRCode(File r_code_file) {
        runRCode(r_code_file, new String[]{});
    }

    protected void runPythonCode(File python_code_file, String[] arguments) {
        if (python_code_file.isFile()) {

            ArrayList<String> command_list = new ArrayList<>();

            // Change working directory
            File working_directory = python_code_file.getParentFile();

            // Construct Command
            command_list.add("Python");
            command_list.add(python_code_file.getAbsolutePath());
            for (String arg : arguments) {
                command_list.add(arg);
            }

            // run code
            runCode(command_list, working_directory);
        } else {
            StatusLabel.setText("'" + python_code_file.getAbsolutePath() + "' is not a file.");
        }
    }

    protected void runPythonCode(File python_code_file) {
        runPythonCode(python_code_file, new String[]{});
    }

    protected void runCode(ArrayList<String> command_list) {
        runCode(command_list, new File("./"));
    }

    protected void runCode(ArrayList<String> command_list, File working_directory) {

        //String[] coomand_list_array = (String[]) command_list.toArray(new String[0]);

        Worker = new LongTaskWorker(command_list, working_directory);

        Worker.execute();
    }

    protected abstract boolean saveResultFiles();

    public String checkCommandString() {
        setupArguments();
        String command_str = "";
        command_str += "<html><body>";
        command_str += CodeFileSelector.getText() + " " + "<br>";
        for(String argument : ArgumentList){
            command_str += "&nbsp;&nbsp;&nbsp;&nbsp;" + argument + " " + "<br>" ;
        }
        command_str += "</html></body>";

        CommandStringTextArea.setText(command_str);

        return command_str;
    }

    public abstract void setupArguments();

    public void setCodeFilePath(String s) {
        CodeFileSelector.setText(s);
    }

    // 非同期に行う処理を記述するためのクラス
    class LongTaskWorker extends SwingWorker<Object, String> {

        ArrayList<String> Command;
        File WorkingDirectory;

        public LongTaskWorker(ArrayList<String> commands, File working_directory) {
            RunButton.setEnabled(false);
            this.Command = commands;
            this.WorkingDirectory = working_directory;

            LogArea.setText("");

            appendLineToLogArea("----- A LongTaskWorkerInstance was constructed ----\n");
            appendLineToLogArea("Working Directory: " + working_directory.getAbsolutePath() + "\n");
            appendLineToLogArea("Prepared Command: ");
            for (String command : commands) {
                appendLineToLogArea(command + " ");
            }
            appendLineToLogArea("\n");
            appendLineToLogArea("---------------------------------------------------\n");
            appendLineToLogArea("\n");
        }

        // 非同期に行われる処理
        @Override
        public Object doInBackground() {

            //System.out.println("is Event Dispatch Thread ? -> " + SwingUtilities.isEventDispatchThread());

            // ながーい処理
            try {
                /* **** Preparing ProcessBuilder **** */
                //ProcessBuilder process_builder = new ProcessBuilder("find", "~/Dropbox","-name 'Evans*'");
                //ProcessBuilder process_builder = new ProcessBuilder("nautilus", "-s", "/tmp");
                //ProcessBuilder process_builder = new ProcessBuilder("dcm2nii", "", "");
                //ProcessBuilder process_builder = new ProcessBuilder("cd", "/home/iu/");
                //ProcessBuilder process_builder = new ProcessBuilder("bash", "src/main/resources/iu,MSNConstructor/bash/short-script.sh", "");
                //ProcessBuilder process_builder = new ProcessBuilder("bash", ResourceManager.getAbsolutePathToAResourceFile("iu/MSNConstructor/bash/long-script.sh"), "");
                //ProcessBuilder process_builder = new ProcessBuilder("Rscript", FileChooser.getText(), "");
                ProcessBuilder process_builder = new ProcessBuilder();

                /* **** Set Command **** */
                process_builder.command(Command);

                /* **** Update Environmental Variables **** */

                Map<String, String> env = process_builder.environment();

                // 環境変数の追加・更新
                //env.put("FREESURFER_HOME", "/usr/local/freesurfer");
                //env.put("PATH", env.get("PATH") + ":" + "/usr/local/freesurfer/bin");
                //env.put("PATH", env.get("PATH") + ":" + "/usr/local/freesurfer/mni/bin");

                Iterator it = env.keySet().iterator();
                String key = "";
                System.out.println("________ Environmental Variables ________");
                while (it.hasNext()) {
                    key = (String) it.next();
                    System.out.println("  " + key + " = " + env.get(key));
                }
                System.out.println("^^^^^^^^ Environmental Variables ^^^^^^^^");

                /* **** I/O Redirect **** */
                //process_builder = process_builder.redirectInput(new File("tmp/", "input.txt"));
                //process_builder = process_builder.redirectOutput(new File("tmp/", "output.txt"));

                /* **** Set the source and destination for subprocess standard I/O
                        to be the same as those of the current Java process. **** */
                //process_builder = process_builder.inheritIO();

                /* **** Change Working Directory **** */
                process_builder.directory(WorkingDirectory);

                /* **** Process Start **** */
                Process processing = process_builder.start();
                //String[] coomand_list_array = (String[]) Command.toArray(new String[0]);
                //Process processing = Runtime.getRuntime().exec(coomand_list_array);

                /* **** Receive processing results **** */
                InputStreamReader isr = new InputStreamReader(processing.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                StringBuilder builder = new StringBuilder();
                int c;
                while ((c = reader.read()) != -1) {
                    builder.append((char) c);
                    if ((char) c == '\n') {
                        publish(builder.toString());
                        builder = new StringBuilder();
                    }
                }
                publish("The exit value of the subprocess: : " + processing.waitFor());
                System.out.println("The exit value of the subprocess: : " + processing.waitFor());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


            return null;
        }

        /**
         * process()は
         * doInBackground()と平行に，EDT上で実行させるメソッド．
         * doInBackground()内で，publish()が呼び出された際に実行される．
         * 引数は，Listクラスで，ジェネリスクはSwingWorkerクラスの第 2 パラメータ．
         *
         * @param chunks
         */
        @Override
        protected void process(List<String> chunks) {
            //System.out.println("process() is EDT?: " + EventQueue.isDispatchThread());
            for (String message : chunks) {
                System.out.print("process: " + message);
                //appendLineToHtmlLogArea("<font color='#ff0000'>" + message + "</font><br>");
                appendLineToLogArea(message);
            }
        }

        // 非同期処理後に実行
        @Override
        protected void done() {
            // 処理が終了したので，文字列を元に戻し
            // ボタンを使用可能にする
            //System.out.println("is Event Dispatch Thread ? -> " + SwingUtilities.isEventDispatchThread());

            //RunButton.setText("RUN");
            RunButton.setEnabled(true);
            StatusLabel.setText(CodeFileSelector.getText() + " has been done.");
        }
    }


}

