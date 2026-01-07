package iu.LCCA.Member.componentholder.Concretes.DEQAResult.DEResult_v10.DESC;

import iu.LCCA.Mediator.action.ActionMediator;
import iu.LCCA.Mediator.componentholder.CHolderMediator;
import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.DEQAResultPane.One_A_Style_Pane;
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

public class DESC_SubTabsHolder extends AbstCHolderMember implements SubTabsHolderItrfc {

    static String sectionName = "study_characteristics_part";

    static String subSection_1_Name = "sc1_study_objective";
    static String subSection_2_Name = "sc2_study_design";
    static String subSection_3_Name = "sc3_study_design_other";

    static String subSection_1_TabName = "SC1 Study Objective";
    static String subSection_2_TabName = "SC2 Study Design";
    static String subSection_3_TabName = "SC3 Study Design Other";


    JPanel panel = new JPanel(new BorderLayout());
    JTabbedPane baseTabPane = new JTabbedPane();

    // Reference Cohort and Imaging
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_1 = new ManagerOfSubTabBasePane("DE", subSection_1_TabName, sectionName, subSection_1_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_2 = new ManagerOfSubTabBasePane("DE", subSection_2_TabName, sectionName, subSection_2_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_3 = new ManagerOfSubTabBasePane("DE", subSection_3_TabName, sectionName, subSection_3_Name, baseTabPane);

    ArrayList<ManagerOfSubTabBasePane> arrayList_of_ManagerOfSubTabBasePane = new ArrayList<>();
    private final String authorYear;

    public DESC_SubTabsHolder(String cholder_name, String short_name, String authorYear) {
        super(cholder_name, short_name);

        this.authorYear=authorYear;

        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_1);
        mngrOfSubTabBasePane_1.registerSubTabsHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_2);
        mngrOfSubTabBasePane_2.registerSubTabsHolder(this);
        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_3);
        mngrOfSubTabBasePane_3.registerSubTabsHolder(this);

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

        if (jsonFiles != null) {
            for (File jsonFile : jsonFiles) {
                String jsonFileName = jsonFile.getName();

                //if (this.actionMediator != null) {
                //    System.err.println("The actionMediator is not null.");
                //} else {
                //    System.err.println("The actionMediator is null. @ SC_SubTabsHolder.java");
                //}

                mngrOfSubTabBasePane_1.addToTheDePaneArray(new One_A_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_1_Name));
                mngrOfSubTabBasePane_2.addToTheDePaneArray(new One_A_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_2_Name));
                mngrOfSubTabBasePane_3.addToTheDePaneArray(new One_A_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_3_Name));
            }
        }

        for (ManagerOfSubTabBasePane managerOfSubTabBasePaneRCAI : arrayList_of_ManagerOfSubTabBasePane) {
            baseTabPane.add(managerOfSubTabBasePaneRCAI.getTabName(), managerOfSubTabBasePaneRCAI.constructBasePaneOfSubTab());
        }

        // タブ切替前に保存の確認（キャンセルで切替阻止）
        baseTabPane.setModel(new ConfirmBeforeSwitchSelectionModel(panel, arrayList_of_ManagerOfSubTabBasePane));

        panel.add(baseTabPane, BorderLayout.CENTER);
    }

    @Override
    public void postInitialize() {
        //System.out.println("postInitialize() @ SC_SubTabsHolder.java");
        if (actionMediator != null) {

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

        //System.out.println(mngrOfSubTabBasePane_1.getDePaneArray().size());

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
