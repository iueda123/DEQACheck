package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.QAResult_v7.QANM;

import iu.SwingStyle.LCCA.Mediator.action.ActionMediator;
import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.DEQAResultPane.One_ACRSL_Style_Pane;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.DEQAResultPane.One_DEQAResult_Pane_Abs;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.SubTabsHolderItrfc;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class QANM_SubTabsHolder extends AbstCHolderMember implements SubTabsHolderItrfc {

    static final String LOCATION_OF_JSON = "QA/json/";

    static String sectionName = "normative_modeling_part";

    static String subSection_1_Name = "nm1_clear_definition_of_target_population";
    static String subSection_2_Name = "nm2_clarity_of_inclusion_and_exclusion_criteria";
    static String subSection_3_Name = "nm3_handling_of_confounding_variables";
    static String subSection_4_Name = "nm4_clarity_of_data_sources";
    static String subSection_5_Name = "nm5_description_of_image_acquisition_protocol";
    static String subSection_6_Name = "nm6_details_of_data_preprocessing";
    static String subSection_7_Name = "nm7_clarity_of_data_partitioning_methods";
    static String subSection_8_Name = "nm8_details_of_normative_modeling_approach";
    static String subSection_9_Name = "nm9_details_of_training_algorithm";
    static String subSection_10_Name = "nm10_model_performance_evaluation_metrics";
    static String subSection_11_Name = "nm11_implementation_of_internal_validation";
    static String subSection_12_Name = "nm12_external_data_validation";
    static String subSection_13_Name = "nm13_description_of_dataset_characteristics";
    static String subSection_14_Name = "nm14_consideration_for_reproducibility";
    static String subSection_15_Name = "nm15_interpretation_specific_to_normative_modeling";

    static String subSection_1_TabName = "NM1 Target Population Definition";
    static String subSection_2_TabName = "NM2 Clarity of Inc/Exc Criteria";
    static String subSection_3_TabName = "NM3 Clarity of Data Sources";
    static String subSection_4_TabName = "NM4 Image Acquisition Protocol";
    static String subSection_5_TabName = "NM5 Data Preprocessing Details";
    static String subSection_6_TabName = "NM6 Validity of Outcome Measures";
    static String subSection_7_TabName = "NM7 Confounding Var Handling";
    static String subSection_8_TabName = "NM8 Details of NM Approach";
    static String subSection_9_TabName = "NM9 Details of Training";
    static String subSection_10_TabName = "NM10 Performance Evaluation Metrics";
    static String subSection_11_TabName = "NM11 Internal Vldtn";
    static String subSection_12_TabName = "NM12 External Vldtn";
    static String subSection_13_TabName = "NM13 Dataset Characteristics";
    static String subSection_14_TabName = "NM14 Reproducibility Consideration";
    static String subSection_15_TabName = "NM15 NM Interpretation";

    JPanel panel = new JPanel(new BorderLayout());
    JTabbedPane baseTabbedPane = new JTabbedPane();

    // Reference Cohort and Imaging
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_1 = new ManagerOfSubTabBasePane("QA", subSection_1_TabName, sectionName, subSection_1_Name, baseTabbedPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_2 = new ManagerOfSubTabBasePane("QA", subSection_2_TabName, sectionName, subSection_2_Name, baseTabbedPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_3 = new ManagerOfSubTabBasePane("QA", subSection_3_TabName, sectionName, subSection_3_Name, baseTabbedPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_4 = new ManagerOfSubTabBasePane("QA", subSection_4_TabName, sectionName, subSection_4_Name, baseTabbedPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_5 = new ManagerOfSubTabBasePane("QA", subSection_5_TabName, sectionName, subSection_5_Name, baseTabbedPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_6 = new ManagerOfSubTabBasePane("QA", subSection_6_TabName, sectionName, subSection_6_Name, baseTabbedPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_7 = new ManagerOfSubTabBasePane("QA", subSection_7_TabName, sectionName, subSection_7_Name, baseTabbedPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_8 = new ManagerOfSubTabBasePane("QA", subSection_8_TabName, sectionName, subSection_8_Name, baseTabbedPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_9 = new ManagerOfSubTabBasePane("QA", subSection_9_TabName, sectionName, subSection_9_Name, baseTabbedPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_10 = new ManagerOfSubTabBasePane("QA", subSection_10_TabName, sectionName, subSection_10_Name, baseTabbedPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_11 = new ManagerOfSubTabBasePane("QA", subSection_11_TabName, sectionName, subSection_11_Name, baseTabbedPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_12 = new ManagerOfSubTabBasePane("QA", subSection_12_TabName, sectionName, subSection_12_Name, baseTabbedPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_13 = new ManagerOfSubTabBasePane("QA", subSection_13_TabName, sectionName, subSection_13_Name, baseTabbedPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_14 = new ManagerOfSubTabBasePane("QA", subSection_14_TabName, sectionName, subSection_14_Name, baseTabbedPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_15 = new ManagerOfSubTabBasePane("QA", subSection_15_TabName, sectionName, subSection_15_Name, baseTabbedPane);

    ArrayList<ManagerOfSubTabBasePane> arrayList_of_ManagerOfSubTabBasePane = new ArrayList<>();
    private final String authorYear;

    public QANM_SubTabsHolder(String cholder_name, String short_name, String authorYear) {
        super(cholder_name, short_name);

        this.authorYear = authorYear;

        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_1);
        mngrOfSubTabBasePane_1.registerSubTabsHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_2);
        mngrOfSubTabBasePane_2.registerSubTabsHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_3);
        mngrOfSubTabBasePane_3.registerSubTabsHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_4);
        mngrOfSubTabBasePane_4.registerSubTabsHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_5);
        mngrOfSubTabBasePane_5.registerSubTabsHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_6);
        mngrOfSubTabBasePane_6.registerSubTabsHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_7);
        mngrOfSubTabBasePane_7.registerSubTabsHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_8);
        mngrOfSubTabBasePane_8.registerSubTabsHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_9);
        mngrOfSubTabBasePane_9.registerSubTabsHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_10);
        mngrOfSubTabBasePane_10.registerSubTabsHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_11);
        mngrOfSubTabBasePane_11.registerSubTabsHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_12);
        mngrOfSubTabBasePane_12.registerSubTabsHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_13);
        mngrOfSubTabBasePane_13.registerSubTabsHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_14);
        mngrOfSubTabBasePane_14.registerSubTabsHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_15);
        mngrOfSubTabBasePane_15.registerSubTabsHolder(this);

        // ./json/ フォルダの確認
        Path   jsonFolderPathString = Paths.get("./data/" + authorYear + "/").resolve(LOCATION_OF_JSON);
        File jsonDir = jsonFolderPathString.toFile();

        // jsonディレクトリが存在しない、またはディレクトリではない場合
        if (!jsonDir.exists() || !jsonDir.isDirectory()) {
            JOptionPane.showMessageDialog(
                    null,
                    "json/フォルダが見つかりません。\n" + jsonFolderPathString + "/フォルダを作成し、JSONファイルを格納してください。",
                    "エラー",
                    JOptionPane.ERROR_MESSAGE
            );
            //System.exit(1);
        }
        // ./json下のすべてのJSONファイルを取得
        File[] jsonFiles = jsonDir.listFiles((dir, name) -> name.endsWith(".json"));
        // jsonFiles に格納されているもののうち、ファイル名に "human" が含まれるものを先頭に持ってくる
        if (jsonFiles != null) {
            Arrays.sort(jsonFiles, (f1, f2) -> {
                boolean f1ContainsHuman = f1.getName().toLowerCase().contains("human");
                boolean f2ContainsHuman = f2.getName().toLowerCase().contains("human");
                return Boolean.compare(f2ContainsHuman, f1ContainsHuman);
            });
        }

        if (jsonFiles != null) {
            for (File jsonFile : jsonFiles) {
                String jsonFileName = jsonFile.getName();
                mngrOfSubTabBasePane_1.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_1_Name));
                mngrOfSubTabBasePane_2.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_2_Name));
                mngrOfSubTabBasePane_3.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_3_Name));
                mngrOfSubTabBasePane_4.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_4_Name));
                mngrOfSubTabBasePane_5.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_5_Name));
                mngrOfSubTabBasePane_6.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_6_Name));
                mngrOfSubTabBasePane_7.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_7_Name));
                mngrOfSubTabBasePane_8.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_8_Name));
                mngrOfSubTabBasePane_9.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_9_Name));
                mngrOfSubTabBasePane_10.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_10_Name));
                mngrOfSubTabBasePane_11.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_11_Name));
                mngrOfSubTabBasePane_12.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_12_Name));
                mngrOfSubTabBasePane_13.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_13_Name));
                mngrOfSubTabBasePane_14.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_14_Name));
                mngrOfSubTabBasePane_15.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_15_Name));
            }
        }

        //個々のサブタブの中身の準備と配備
        for (ManagerOfSubTabBasePane managerOfSubTabBasePane : arrayList_of_ManagerOfSubTabBasePane) {
            baseTabbedPane.add(managerOfSubTabBasePane.getTabName(), managerOfSubTabBasePane.constructBasePaneOfSubTab());
        }

        panel.add(baseTabbedPane, BorderLayout.CENTER);
    }

    @Override
    public void postInitialize() {
        //System.out.println("postInitialize() @ CAAA_SubTabsHolder.java");
        if (actionMediator != null) {

            /* 値を流し込む */
            for (One_DEQAResult_Pane_Abs oneDEQAResultPane : mngrOfSubTabBasePane_1.getDeqaPaneArray()) {
                oneDEQAResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEQAResultPane : mngrOfSubTabBasePane_2.getDeqaPaneArray()) {
                oneDEQAResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEQAResultPane : mngrOfSubTabBasePane_3.getDeqaPaneArray()) {
                oneDEQAResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEQAResultPane : mngrOfSubTabBasePane_4.getDeqaPaneArray()) {
                oneDEQAResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEQAResultPane : mngrOfSubTabBasePane_5.getDeqaPaneArray()) {
                oneDEQAResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEQAResultPane : mngrOfSubTabBasePane_6.getDeqaPaneArray()) {
                oneDEQAResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEQAResultPane : mngrOfSubTabBasePane_7.getDeqaPaneArray()) {
                oneDEQAResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEQAResultPane : mngrOfSubTabBasePane_8.getDeqaPaneArray()) {
                oneDEQAResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEQAResultPane : mngrOfSubTabBasePane_9.getDeqaPaneArray()) {
                oneDEQAResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEQAResultPane : mngrOfSubTabBasePane_10.getDeqaPaneArray()) {
                oneDEQAResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEQAResultPane : mngrOfSubTabBasePane_11.getDeqaPaneArray()) {
                oneDEQAResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEQAResultPane : mngrOfSubTabBasePane_12.getDeqaPaneArray()) {
                oneDEQAResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEQAResultPane : mngrOfSubTabBasePane_13.getDeqaPaneArray()) {
                oneDEQAResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEQAResultPane : mngrOfSubTabBasePane_14.getDeqaPaneArray()) {
                oneDEQAResultPane.loadJson();
            }

            for (One_DEQAResult_Pane_Abs oneDEQAResultPane : mngrOfSubTabBasePane_15.getDeqaPaneArray()) {
                oneDEQAResultPane.loadJson();
            }

        } else {
            System.err.println("actionMediator is null in postInitialize() @ " + this.getClass());
        }
    }

    @Override
    public JComponent getBaseComponent() {
        return this.panel;
    }


    @Override
    public void setCHolderMediator(CHolderMediator cHolderMediator) {
        this.cholderMediator = cHolderMediator;
    }

    @Override
    public void setActionMediator(ActionMediator actionMediator) {
        this.actionMediator = actionMediator;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void doWorkAsMember() {
    }


    /**
     * すべての paneArray の要素の中から、
     * jsonName, sectionName, subSectionName が一致するものを返す。
     */
    public One_DEQAResult_Pane_Abs getResultPane(String jsonName, String sectionName, String subSectionName) {
        //System.out.println("Start searching the DEResultPane with following: ");
        //System.out.println("  JSON Name: " + jsonName);
        //System.out.println("  Section Name: " + sectionName);
        //System.out.println("  Subsection Name: " + subSectionName);

        //System.out.println(mngrOfSubTabBasePane_MODEL_ORIGIN.getDePaneArray().size());

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_1.getDeqaPaneArray()) {
            //System.out.println("Candidate Info: ");
            //System.out.println("  JSON Name: " + pane.getJsonName());
            //System.out.println("  Section Name: " + pane.getSectionName());
            //System.out.println("  Subsection Name: " + pane.getSubSectionName());
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_2.getDeqaPaneArray()) {
            //System.out.println("Candidate Info: ");
            //System.out.println("  JSON Name: " + pane.getJsonName());
            //System.out.println("  Section Name: " + pane.getSectionName());
            //System.out.println("  Subsection Name: " + pane.getSubSectionName());
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_3.getDeqaPaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_4.getDeqaPaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_5.getDeqaPaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_6.getDeqaPaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_7.getDeqaPaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_8.getDeqaPaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_9.getDeqaPaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_10.getDeqaPaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_11.getDeqaPaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_12.getDeqaPaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_13.getDeqaPaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_14.getDeqaPaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        for (One_DEQAResult_Pane_Abs pane : mngrOfSubTabBasePane_15.getDeqaPaneArray()) {
            if (pane.getJsonName().equals(jsonName) &&
                    pane.getSectionName().equals(sectionName) &&
                    pane.getSubSectionName().equals(subSectionName)) {
                return pane;
            }
        }

        return null;

    }

    public ArrayList<ManagerOfSubTabBasePane> getArrayList_of_ManagerOfSubTabBasePane() {
        return arrayList_of_ManagerOfSubTabBasePane;
    }

    @Override
    public One_DEQAResult_Pane_Abs getTheFirstJsonPanel() {
        return null;
    }

    @Override
    public String getAuthorYear() {
        return this.authorYear;
    }

    public String getSectionName() {
        return sectionName;
    }

}
