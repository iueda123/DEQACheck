package iu.LCCA.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DENM;

import iu.LCCA.Mediator.action.ActionMediator;
import iu.LCCA.Mediator.componentholder.CHolderMediator;
import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.One_ACRSL_Style_Pane;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.One_ADCSL_Style_Pane;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.One_DEQAResult_Pane_Abs;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.ConfirmBeforeSwitchSelectionModel;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.SubTabBehaviorUtils;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.SubTabsHolderItrfc;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class DENM_SubTabsHolder extends AbstCHolderMember implements SubTabsHolderItrfc {

    static String sectionName = "normative_modeling_part";

    static String subSection_1_Name = "nm1_model_origin";
    static String subSection_2_Name = "nm2_modeling_method";
    static String subSection_3_Name = "nm3_software_tool";
    static String subSection_4_Name = "nm4_response_variable";
    static String subSection_5_Name = "nm5_predictor_variables";
    static String subSection_6_Name = "nm6_predictor_effects";
    static String subSection_7_Name = "nm7_nm_vldtn_handle_ns";
    static String subSection_8_Name = "nm8_nm_vldtn_same_domain_nonindep";
    static String subSection_9_Name = "nm9_nm_vldtn_same_domain_indep";
    static String subSection_10_Name = "nm10_nm_vldtn_diff_domain";

    static String subSection_1_TabName = "NM1 Model Origin";
    static String subSection_2_TabName = "NM2 Modeling Method";
    static String subSection_3_TabName = "NM3 Software and Tool";
    static String subSection_4_TabName = "NM4 Response Variable";
    static String subSection_5_TabName = "NM5 Predictor Variables";
    static String subSection_6_TabName = "NM6 Predictor Effects";
    static String subSection_7_TabName = "NM7 Validation - Handing Nuisance Structure";
    static String subSection_8_TabName = "NM8 Validation - Same Domain Non-independent Dataset";
    static String subSection_9_TabName = "NM9 Validation - Same Domain Independent Dataset";
    static String subSection_10_TabName = "NM10 Validation - Different Domain";


    JPanel panel = new JPanel(new BorderLayout());
    JTabbedPane baseTabPane = new JTabbedPane();

    // Reference Cohort and Imaging
    public ManagerOfSubTabBasePane mngrOfSubTabBasePane_1 = new ManagerOfSubTabBasePane("DE", subSection_1_TabName, sectionName, subSection_1_Name, baseTabPane);
    public ManagerOfSubTabBasePane mngrOfSubTabBasePane_2 = new ManagerOfSubTabBasePane("DE", subSection_2_TabName, sectionName, subSection_2_Name, baseTabPane);
    public ManagerOfSubTabBasePane mngrOfSubTabBasePane_3 = new ManagerOfSubTabBasePane("DE", subSection_3_TabName, sectionName, subSection_3_Name, baseTabPane);
    public ManagerOfSubTabBasePane mngrOfSubTabBasePane_4 = new ManagerOfSubTabBasePane("DE", subSection_4_TabName, sectionName, subSection_4_Name, baseTabPane);
    public ManagerOfSubTabBasePane mngrOfSubTabBasePane_5 = new ManagerOfSubTabBasePane("DE", subSection_5_TabName, sectionName, subSection_5_Name, baseTabPane);
    public ManagerOfSubTabBasePane mngrOfSubTabBasePane_6 = new ManagerOfSubTabBasePane("DE", subSection_6_TabName, sectionName, subSection_6_Name, baseTabPane);
    public ManagerOfSubTabBasePane mngrOfSubTabBasePane_7 = new ManagerOfSubTabBasePane("DE", subSection_7_TabName, sectionName, subSection_7_Name, baseTabPane);
    public ManagerOfSubTabBasePane mngrOfSubTabBasePane_8 = new ManagerOfSubTabBasePane("DE", subSection_8_TabName, sectionName, subSection_8_Name, baseTabPane);
    public ManagerOfSubTabBasePane mngrOfSubTabBasePane_9 = new ManagerOfSubTabBasePane("DE", subSection_9_TabName, sectionName, subSection_9_Name, baseTabPane);
    public ManagerOfSubTabBasePane mngrOfSubTabBasePane_10 = new ManagerOfSubTabBasePane("DE", subSection_10_TabName, sectionName, subSection_10_Name, baseTabPane);

    ArrayList<ManagerOfSubTabBasePane> arrayList_of_ManagerOfSubTabBasePane = new ArrayList<>();
    private final String authorYear;

    public DENM_SubTabsHolder(String cholder_name, String short_name, String authorYear) {
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

        // ./json/ フォルダの確認
        Path jsonFolderPathString = Paths.get("./DE/json");
        jsonFolderPathString = Paths.get("./data/" + authorYear + "/").resolve(jsonFolderPathString);
        File jsonDir = jsonFolderPathString.toFile();

        // jsonディレクトリが存在しない、またはディレクトリではない場合
        if (!jsonDir.exists() || !jsonDir.isDirectory()) {
            JOptionPane.showMessageDialog(
                    null,
                    "json/フォルダが見つかりません。\n" + jsonFolderPathString + "/フォルダを作成し、JSONファイルを格納してください。",
                    "エラー",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
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

        for (File jsonFile : jsonFiles) {
            String jsonFileName = jsonFile.getName();
            //System.out.println("jsonFileName: " + jsonFileName);
            mngrOfSubTabBasePane_1.addToTheDePaneArray(new One_ADCSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_1_Name));
            mngrOfSubTabBasePane_2.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_2_Name));
            mngrOfSubTabBasePane_3.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_3_Name));
            mngrOfSubTabBasePane_4.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_4_Name));
            mngrOfSubTabBasePane_5.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_5_Name));
            mngrOfSubTabBasePane_6.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_6_Name));
            mngrOfSubTabBasePane_7.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_7_Name));
            mngrOfSubTabBasePane_8.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_8_Name));
            mngrOfSubTabBasePane_9.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_9_Name));
            mngrOfSubTabBasePane_10.addToTheDePaneArray(new One_ACRSL_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_10_Name));
        }

        //個々のサブタブの中身の準備と配備
        for (ManagerOfSubTabBasePane managerOfSubTabBasePaneNM : arrayList_of_ManagerOfSubTabBasePane) {
            baseTabPane.add(managerOfSubTabBasePaneNM.getTabName(), managerOfSubTabBasePaneNM.constructBasePaneOfSubTab());
        }

        // 選択変更前に確認ダイアログを出し、キャンセル時は切替自体を行わないSelectionModelに差し替え
        baseTabPane.setModel(new ConfirmBeforeSwitchSelectionModel(panel, arrayList_of_ManagerOfSubTabBasePane));

        panel.add(baseTabPane, BorderLayout.CENTER);
    }

    // 以前のConfirmableSelectionModelとcheckUpdatesForTabは共通化のため削除し、
    // ConfirmBeforeSwitchSelectionModel（Common）を使用。


    @Override
    public void postInitialize() {
        //System.out.println("postInitialize() @ NM_SubTabsHolder.java");
        if (actionMediator != null) {

            //ToDo: この部分で新規JSONの再探索とパネルの再構築も行いたい。
            // 問題は、パネルサイズ計算等もやり直すことになりレイアウトが崩れぬようにする手間がかかるということ
            // JSONの探索、パネルの構築、値の流し込み部分の切り分け、
            // もう少しスマートな構成にしないといけない。

            /* 値を流し込む */
            SubTabBehaviorUtils.loadAllJsonPanels(arrayList_of_ManagerOfSubTabBasePane);


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
