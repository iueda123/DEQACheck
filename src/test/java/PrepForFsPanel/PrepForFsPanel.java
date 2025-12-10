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

/**
 * A Wrapper for a script which conducts the followings:
 * - Make a Folder "T1WI" in the Project Root Folder.
 * - Perform "Dcm2Nii" or Copy a NIfTI file.
 * -
 */
public class PrepForFsPanel extends CommandProcessPanel {

    final static String SETTING_PROPERTY_FILE_PATH = "settings/MsnAnalyzer/PrepForFs.prop";
    FileChooserPanel_with_FileDrop_v3 FileOrFolderSelector_for_1stArg;
    FileChooserPanel_with_FileDrop_v3 FolderSelector_for_2ndArg;
    JTextField TextField_for_3rdArg = new JTextField("");

    /**
     * Constructor
     */
    public PrepForFsPanel() {
        setLayout(new BorderLayout());

        /* **** North Area **** */
        Box north_panel_base = Box.createVerticalBox();

        /* **** North Panel A ---- Code File Selector **** */
        Box north_panel_A = Box.createVerticalBox();
        north_panel_A.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(), "Select a Code File"));
        // Code File Chooser
        Box north_panel_A1 = Box.createHorizontalBox();
        north_panel_A1.add(new JLabel("Bash code file: "));
        north_panel_A1.add(createBashFileSelector());
        //Open WD Button
        north_panel_A1.add(createOpenWorkingDirButton());
        north_panel_A.add(north_panel_A1);

        Box north_panel_A2 = Box.createHorizontalBox();
        //Load Button
        north_panel_A2.add(createLoadButton());
        north_panel_A2.add(Box.createHorizontalGlue());
        north_panel_A.add(north_panel_A2);

        Box north_panel_A3 = Box.createHorizontalBox();
        // Code Area
        JScrollPane CodeAreaScrollPane = new JScrollPane();
        CodeAreaScrollPane.setPreferredSize(new Dimension(500, 300));
        CodeAreaScrollPane.getViewport().add(createCodeArea());
        north_panel_A3.add(CodeAreaScrollPane);
        north_panel_A.add(north_panel_A3);

        Box north_panel_A4 = Box.createHorizontalBox();
        //Save Button
        north_panel_A4.add(Box.createHorizontalGlue());
        north_panel_A4.add(createSaveCodeButton());
        north_panel_A.add(north_panel_A4);

        north_panel_base.add(north_panel_A);


        /* **** North Panel B ---- Variables **** */
        Box north_panel_B = Box.createVerticalBox();
        north_panel_B.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(), "Set Variables"));

        // Argument 1 : T1WI Dicom Raw Folder Root Path
        Box north_panel_B1 = Box.createHorizontalBox();
        north_panel_B1.add(Box.createHorizontalGlue());
        north_panel_B1.add(new JLabel("NIfTI File or DICOM Folder: "));
        north_panel_B1.add(createFolderSelector_for_1stArg());
        north_panel_B.add(north_panel_B1);

        // Argument 2 : Project Root Folder Path
        Box north_panel_B2 = Box.createHorizontalBox();
        north_panel_B2.add(Box.createHorizontalGlue());
        north_panel_B2.add(new JLabel("Project Root: "));
        north_panel_B2.add(createFolderSelector_for_2ndArg());
        north_panel_B.add(north_panel_B2);

        // Argument 3 : Subject ID
        Box north_panel_B3 = Box.createHorizontalBox();
        north_panel_B3.add(Box.createHorizontalGlue());
        north_panel_B3.add(new JLabel("Subject ID: "));
        north_panel_B3.add(TextField_for_3rdArg);
        north_panel_B.add(north_panel_B3);

        north_panel_base.add(north_panel_B);


        /* **** North Panel C ---- Command String Checking **** */
        Box north_panel_C = Box.createVerticalBox();
        north_panel_C.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(), "Check Command String"));

        Box commmand_string_checking_box = Box.createHorizontalBox();
        //commmand_string_checking_box.add(Box.createHorizontalGlue());
        commmand_string_checking_box.add(createCheckCommandStringButton());
        commmand_string_checking_box.add(Box.createHorizontalGlue());
        north_panel_C.add(commmand_string_checking_box);

        north_panel_C.add(createCommandStringCheckArea(4));
        north_panel_base.add(north_panel_C);

        add(north_panel_base, BorderLayout.NORTH);


        /* **** Center Area ***** */
        Box center_panel = Box.createVerticalBox();
        center_panel.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(), "Execute Command"));


        Box center_panel_A = Box.createHorizontalBox();
        //RUN Button
        center_panel_A.add(createRunBashButton());
        //Cancel Button
        center_panel_A.add(createCancelButton());
        //Space
        center_panel_A.add(Box.createHorizontalGlue());
        center_panel.add(center_panel_A);


        Box center_panel_B = Box.createHorizontalBox();
        // Log Area
        JScrollPane LogAreaScrollPane = new JScrollPane();
        LogAreaScrollPane.setPreferredSize(new Dimension(500, 300));
        //LogAreaScrollPane.getViewport().add(createHtmlLogArea());
        LogAreaScrollPane.getViewport().add(createLogArea());
        center_panel_B.add(LogAreaScrollPane);
        center_panel.add(center_panel_B);


        Box center_panel_C = Box.createHorizontalBox();
        //Clear Log Button
        center_panel_C.add(Box.createHorizontalGlue());
        center_panel_C.add(createClearLogButton());
        center_panel.add(center_panel_C);


        add(center_panel, BorderLayout.CENTER);


        /* **** South Area **** */
        Box south_panel = Box.createHorizontalBox();
        south_panel.add(Box.createHorizontalGlue());
        south_panel.add(StatusLabel);

        add(south_panel, BorderLayout.SOUTH);

        initialize();

    } // End of Constructor


    private FileChooserPanel_with_FileDrop_v3 createFolderSelector_for_1stArg() {
        if (FileOrFolderSelector_for_1stArg == null) {
            FileOrFolderSelector_for_1stArg = new FileChooserPanel_with_FileDrop_v3(
                    null, "", "Please select a NIfTI file or a DICOM folder.",
                    //iu.tools.filefilters.PrepForFsPanel.SupportingClasses.SwingFileFilterFactory.createFilterForDirectory(),
                    null,
                    //PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_DIRECTORIES_ONLY,
                    FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_FILES_AND_DIRECTORY,
                    //PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_DIRECTORIES_ONLY_WITH_SHOWING_FILES,
                    //PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_FILES_ONLY,
                    false,
                    FileChooserPanel_with_FileDrop_v3.SHOW_MODE_SELECT
            ) {
                @Override
                protected void performActionAfterSelectingFile() {
                    System.out.println("Selected folder was changed.");

                }
            };
        }
        return FileOrFolderSelector_for_1stArg;
    }


    private FileChooserPanel_with_FileDrop_v3 createFolderSelector_for_2ndArg() {
        if (FolderSelector_for_2ndArg == null) {
            FolderSelector_for_2ndArg = new FileChooserPanel_with_FileDrop_v3(
                    null, "", "Please select a output folder.",
                    SwingFileFilterFactory.createFilterForDirectory(),
                    FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_DIRECTORIES_ONLY,
                    //PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_FILES_AND_DIRECTORY,
                    //PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_DIRECTORIES_ONLY_WITH_SHOWING_FILES,
                    //PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_FILES_ONLY,
                    false,
                    FileChooserPanel_with_FileDrop_v3.SHOW_MODE_SELECT
            ) {
                @Override
                protected void performActionAfterSelectingFile() {
                    System.out.println("Selected folder was changed.");

                }
            };
        }
        return FolderSelector_for_2ndArg;
    }


    public void initialize() {

        // Load Properties to input default values.
        Properties setting_properties = loadPropertyFile(SETTING_PROPERTY_FILE_PATH);
        setCodeFilePath((String) setting_properties.get("code_file_path"));
        set1stArgument((String) setting_properties.get("default_dicom_folder_root_path"));
        set2ndArgument((String) setting_properties.get("default_project_root_path"));
        set3rdArgument((String) setting_properties.get("default_subject_id"));

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
        ArgumentList.add(FileOrFolderSelector_for_1stArg.getText());
        ArgumentList.add(FolderSelector_for_2ndArg.getTextWithSeparatorEnding());
        ArgumentList.add(TextField_for_3rdArg.getText());
    }

    @Override
    protected boolean saveResultFiles() {
        return false;
    }


    public void set1stArgument(String t1wi_folder) {
        FileOrFolderSelector_for_1stArg.setText(t1wi_folder);
    }

    public void set2ndArgument(String project_root) {
        FolderSelector_for_2ndArg.setText(project_root);
    }

    public void set3rdArgument(String sbjId) {
        TextField_for_3rdArg.setText(sbjId);
    }

    public String getSbjId() {
        return TextField_for_3rdArg.getText();
    }


    public static void main(String[] args) {

        // JFrameのインスタンスを作成
        JFrame frame = new JFrame("Sample Program from MSN Analyzer");

        // フレームのサイズを設定
        frame.setSize(400, 300);

        frame.getContentPane().add(new PrepForFsPanel());

        // フレームが閉じられたときの動作を設定
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // フレームを表示
        frame.setVisible(true);

    }
}

