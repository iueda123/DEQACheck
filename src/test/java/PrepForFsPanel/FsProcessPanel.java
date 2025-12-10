package PrepForFsPanel;

import PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3;
import PrepForFsPanel.SupportingClasses.PropertyIOHelper;
import PrepForFsPanel.SupportingClasses.SwingFileFilterFactory;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;

public class FsProcessPanel extends CommandProcessPanel {

    final static String SettingPropertyFilePath = "settings/MsnAnalyzer/FsProcessPanel.prop";
    FileChooserPanel_with_FileDrop_v3 FolderSelector_for_1stArg;
    JTextField TextField_for_2rdArg = new JTextField("");

    /**
     * Constructor
     */
    public FsProcessPanel() {
        setLayout(new BorderLayout());

        /* **** North Area **** */
        Box north_panel_base = Box.createVerticalBox();

        /* **** North Panel 1 ---- Code File Selector **** */
        Box north_panel_1 = Box.createHorizontalBox();
        north_panel_1.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(), "Select a Code File"));

        // Code File Chooser
        north_panel_1.add(new JLabel("Bash code file: "));
        north_panel_1.add(createBashFileSelector());

        //Load Button
        north_panel_1.add(createLoadButton());
        //Save Button
        north_panel_1.add(createSaveCodeButton());
        //Open WD Button
        north_panel_1.add(createOpenWorkingDirButton());

        north_panel_base.add(north_panel_1);

        /* **** North Panel 2 ---- Argument Input Boxes **** */
        Box north_panel_2 = Box.createVerticalBox();
        north_panel_2.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(), "Input Arguments"));

        // Argument 1 : Project Root Folder Path
        Box arg_1_box = Box.createHorizontalBox();
        arg_1_box.add(Box.createHorizontalGlue());
        arg_1_box.add(new JLabel("Project Root: "));
        arg_1_box.add(createFolderSelector_for_1stArg());

        north_panel_2.add(arg_1_box);


        // Argument 2 : Subject ID
        Box arg_2_box = Box.createHorizontalBox();
        arg_2_box.add(new JLabel("Subject ID: "));
        arg_2_box.add(TextField_for_2rdArg);
        north_panel_2.add(arg_2_box);

        north_panel_base.add(north_panel_2);

        //north_panel_2.add(SbjIdField);
        //north_panel_2.add(new JLabel("Input Folder: "));
        //InputFolderSelector = createInputFolderSelector();
        //north_panel_2.add(InputFolderSelector); //使っていない。紛らわしいので非表示化。
        //CheckCommandStringButton = createCheckCommandStringButton();
        //north_panel_2.add(CheckCommandStringButton);


        /* **** North Panel 3 ---- Command String Checking **** */
        //Arguments
        Box north_panel_3 = Box.createVerticalBox();
        north_panel_3.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(), "Check Command String"));

        Box commmand_string_checking_box = Box.createHorizontalBox();
        //commmand_string_checking_box.add(Box.createHorizontalGlue());
        commmand_string_checking_box.add(createCheckCommandStringButton());
        commmand_string_checking_box.add(Box.createHorizontalGlue());
        north_panel_3.add(commmand_string_checking_box);

        north_panel_3.add(createCommandStringCheckArea(3));
        north_panel_base.add(north_panel_3);

        add(north_panel_base, BorderLayout.NORTH);

        //north_panel_3.add(Box.createHorizontalStrut(5));
        //north_panel_3.add(new JLabel("Arguments: "));
        //north_panel_3.add(CommandStringTextArea);


        /* **** Center Area ***** */
        Box center_panel = Box.createVerticalBox();
        center_panel.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(), "Execute Command"));

        Box center_panel_sub1 = Box.createHorizontalBox();
        //RUN Button
        center_panel_sub1.add(createRunBashButton());
        //Cancel Button
        center_panel_sub1.add(createCancelButton());
        //Space
        center_panel_sub1.add(Box.createHorizontalGlue());
        //Clear Log Button
        center_panel_sub1.add(createClearLogButton());

        center_panel.add(center_panel_sub1);

        Box center_panel_sub2 = Box.createHorizontalBox();
        // Code Area
        JScrollPane CodeAreaScrollPane = new JScrollPane();
        CodeAreaScrollPane.setPreferredSize(new Dimension(500, 300));
        CodeAreaScrollPane.getViewport().add(createCodeArea());
        center_panel_sub2.add(CodeAreaScrollPane);

        // Log Area
        JScrollPane LogAreaScrollPane = new JScrollPane();
        LogAreaScrollPane.setPreferredSize(new Dimension(500, 300));
        //LogAreaScrollPane.getViewport().add(createHtmlLogArea());
        LogAreaScrollPane.getViewport().add(createLogArea());
        center_panel_sub2.add(LogAreaScrollPane);
        center_panel.add(center_panel_sub2);

        add(center_panel, BorderLayout.CENTER);


        /* **** South Area **** */
        Box south_panel = Box.createHorizontalBox();
        south_panel.add(Box.createHorizontalGlue());
        south_panel.add(StatusLabel);

        add(south_panel, BorderLayout.SOUTH);

        initialize();

    } // End of Constructor

    private FileChooserPanel_with_FileDrop_v3 createFolderSelector_for_1stArg() {
        if (FolderSelector_for_1stArg == null) {
            FolderSelector_for_1stArg = new FileChooserPanel_with_FileDrop_v3(
                    null, "", "Please select a output folder.",
                    SwingFileFilterFactory.createFilterForDirectory(),
                    FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_DIRECTORIES_ONLY,
                    //FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_FILES_AND_DIRECTORY,
                    //FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_DIRECTORIES_ONLY_WITH_SHOWING_FILES,
                    //FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_FILES_ONLY,
                    false,
                    FileChooserPanel_with_FileDrop_v3.SHOW_MODE_SELECT
            ) {
                @Override
                protected void performActionAfterSelectingFile() {
                    System.out.println("Selected folder was changed.");

                }
            };
        }
        return FolderSelector_for_1stArg;
    }


    public void initialize() {

        // Load Properties
        Properties setting_properties = loadPropertyFile(SettingPropertyFilePath);

        //setInputFolderPath((String)setting_properties.get("default_dicom_folder_root_path"));
        //setOutputFolderPath((String)setting_properties.get("output_folder_path"));
        setCodeFilePath((String) setting_properties.get("code_file_path"));


        // Preparation of Component
        //SetupPanel setup_panel = (SetupPanel) ((ActionController_For_Analyzer) TheController).getSetupPanelInstance();
        //AnalyzerWindow analyzer_window = (AnalyzerWindow)  ((ActionController_For_Analyzer) TheController).getParentFrameInstance();

        // Initialization MonitoringPanel
        //fs_process_panel.initialize((String)setting_properties.get("working_directory"));
        //String project_root_folder_path = analyzer_window.getProjectRootFolderPath();
        //String subject_id = analyzer_window.getSbjId();

        //setProjectRootFolderPath(setup_panel.getProjectRootFolderPath());
        //setSbjId(setup_panel.getSubjectId());


        //CodeFileChooser.setText("/home/iu/Downloads");
        //CodeFileChooser.setText(SystemPropertyManager.getUserDir());
        //CodeFileSelector.setText("data/bash/01_Data_Deployments_and_FS_recon-all.sh");
        //ArgumentsTextfield.setToolTipText("<T1WI Dicom Raw Folder Path> <Project Root Folder Path> <Subject ID>");
        //CodeArea.setText("Please select a bash file for FreeSurfer processing.");

        //InputFolderSelector.setText(ProjectRootFolderSelector.getTextWithSeparatorEnding() + "AnonymizedDicom/" + SbjIdField.getText() + "/");

    }

    protected Properties loadPropertyFile(String property_file_path) {

        System.out.println();

        Path setting_file_path = Paths.get(property_file_path);

        Properties setting_properties = PropertyIOHelper.load(setting_file_path);
        System.out.println("    Properties file '" + setting_file_path.getFileName() + "' was loaded.");

        Set<String> property_names = setting_properties.stringPropertyNames();
        String value = "";
        KeyStroke loaded_accelerator_key_stroke = null;
        System.out.println("    ================================");
        System.out.println("    property_name -> property_value");
        System.out.println("    --------------------------------");
        for (String property_name : property_names) {
            value = (String) setting_properties.get(property_name);
            System.out.println("    " + property_name + " -> " + value);
        }
        System.out.println("    ================================");
        System.out.println("");

        /* **** Component Preparation **** */
        //ButtonPanel button_panel = (ButtonPanel) ((ActionController_For_Sample) TheController).getButtonPanel();

        /* **** Initialization MonitoringPanel **** */
        //button_panel.initialization((String)setting_properties.get("default_text"));

        return setting_properties;
    }

    @Override
    public void setupArguments() {
        ArgumentList.clear();
        ArgumentList.add(FolderSelector_for_1stArg.getTextWithSeparatorEnding());
        ArgumentList.add(TextField_for_2rdArg.getText());
    }

    @Override
    protected boolean saveResultFiles() {
        return false;
    }

    public String getProjectRootFolderPath() {
        return FolderSelector_for_1stArg.getTextWithSeparatorEnding();
    }

    public void set1stArgument(String project_root) {
        FolderSelector_for_1stArg.setText(project_root);
    }

    public String getSbjId() {
        return TextField_for_2rdArg.getText();
    }

    public void setSbjId(String sbjId) {
        TextField_for_2rdArg.setText(sbjId);
    }

    public static void main(String[] args) {

        // JFrameのインスタンスを作成
        JFrame frame = new JFrame("Sample Program from MSN Analyzer");

        // フレームのサイズを設定
        frame.setSize(400, 300);

        frame.getContentPane().add(new FsProcessPanel());

        // フレームが閉じられたときの動作を設定
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // フレームを表示
        frame.setVisible(true);

    }
}

