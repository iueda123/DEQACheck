package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DESI;

import iu.SwingStyle.LCCA.Mediator.action.ActionMediator;
import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.DEQAResultPane.One_A_Style_Pane;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.DEQAResultPane.One_DEQAResult_Pane_Abs;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.ConfirmBeforeSwitchSelectionModel;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.SubTabBehaviorUtils;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.SubTabsHolderItrfc;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class DESI_SubTabsHolder extends AbstCHolderMember implements SubTabsHolderItrfc {

    public static final String staticMemberName = "sub_tabs_holder_DESI";

    static final String LOCATION_OF_JSON = "DE_v10/json/";


    static String sectionName = "study_identification_part";

    static String subSection_1_Name = "si1_study_id";
    static String subSection_2_Name = "si2_reference_file_names";
    static String subSection_3_Name = "si3_author_journal_year";
    static String subSection_4_Name = "si4_title";
    static String subSection_5_Name = "si5_doi";

    static String subSection_1_TabName = "SI1 Study ID";
    static String subSection_2_TabName = "SI2 Reference File Names";
    static String subSection_3_TabName = "SI3 Author Journal Year";
    static String subSection_4_TabName = "SI4 Title";
    static String subSection_5_TabName = "SI5 DOI";


    JPanel panel = new JPanel(new BorderLayout());
    JTabbedPane baseTabPane = new JTabbedPane();

    // Reference Cohort and Imaging
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_1 = new ManagerOfSubTabBasePane("DE", subSection_1_TabName, sectionName, subSection_1_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_2 = new ManagerOfSubTabBasePane("DE", subSection_2_TabName, sectionName, subSection_2_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_3 = new ManagerOfSubTabBasePane("DE", subSection_3_TabName, sectionName, subSection_3_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_4 = new ManagerOfSubTabBasePane("DE", subSection_4_TabName, sectionName, subSection_4_Name, baseTabPane);
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_5 = new ManagerOfSubTabBasePane("DE", subSection_5_TabName, sectionName, subSection_5_Name, baseTabPane);

    ArrayList<ManagerOfSubTabBasePane> arrayList_of_ManagerOfSubTabBasePane = new ArrayList<>();
    private final String authorYear;

    public DESI_SubTabsHolder(String cholder_name, String short_name, String authorYear) {
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

        // ./json/ フォルダの確認
        Path jsonFolderPathString = Paths.get("./data", authorYear).resolve(LOCATION_OF_JSON);
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

                //if (this.actionMediator != null) {
                //    System.err.println("The actionMediator is not null.");
                //}else {
                //    System.err.println("The actionMediator is null. @ SI_SubTabsHolder.java");
                //}

                mngrOfSubTabBasePane_1.addToTheDePaneArray(new One_A_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_1_Name));
                mngrOfSubTabBasePane_2.addToTheDePaneArray(new One_A_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_2_Name));
                mngrOfSubTabBasePane_3.addToTheDePaneArray(new One_A_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_3_Name));
                mngrOfSubTabBasePane_4.addToTheDePaneArray(new One_A_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_4_Name));
                mngrOfSubTabBasePane_5.addToTheDePaneArray(new One_A_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_5_Name));
            }
        }

        for (ManagerOfSubTabBasePane managerOfSubTabBasePaneSI : arrayList_of_ManagerOfSubTabBasePane) {
            baseTabPane.add(managerOfSubTabBasePaneSI.getTabName(), managerOfSubTabBasePaneSI.constructBasePaneOfSubTab());
        }

        // タブ切替前に保存の確認（キャンセルで切替阻止）
        baseTabPane.setModel(new ConfirmBeforeSwitchSelectionModel(panel, arrayList_of_ManagerOfSubTabBasePane));

        panel.add(baseTabPane, BorderLayout.CENTER);
    }

    public One_DEQAResult_Pane_Abs getTheFirstJsonPanel() {
        //panel の baseTabPane の 0 番目 の 中で一番上に配置されている OneDEResult_Pane_Abs クラスオブジェクトを取得する
        if (baseTabPane.getTabCount() == 0) {
            return null;
        }

        // baseTabPane の 0 番目のタブのコンポーネントを取得
        Component firstTabComponent = baseTabPane.getComponentAt(0);
        if (firstTabComponent == null) {
            return null;
        }

        // そのタブに対応する ManagerOfSubTabBasePane を検索
        ManagerOfSubTabBasePane targetManager = null;
        for (ManagerOfSubTabBasePane manager : arrayList_of_ManagerOfSubTabBasePane) {
            JPanel basePaneForDEResultPanes = manager.getBasePaneForDEQAResultPanes();
            // basePaneForDEResultPanes が firstTabComponent の子孫かどうかを確認
            Container parent = basePaneForDEResultPanes.getParent();
            while (parent != null) {
                if (parent == firstTabComponent) {
                    targetManager = manager;
                    break;
                }
                parent = parent.getParent();
            }
            if (targetManager != null) {
                break;
            }
        }

        if (targetManager == null) {
            return null;
        }

        // basePaneForDEResultPanes から実際の表示順序で一番上の One_DEResult_Pane_Abs を取得
        JPanel basePaneForDEResultPanes = targetManager.getBasePaneForDEQAResultPanes();
        Component[] components = basePaneForDEResultPanes.getComponents();

        for (Component component : components) {
            if (component instanceof One_DEQAResult_Pane_Abs) {
                return (One_DEQAResult_Pane_Abs) component;
            }
        }

        return null;
    }

    @Override
    public String getAuthorYear() {
        return this.authorYear;
    }

    @Override
    public void postInitialize() {
        //System.out.println("postInitialize() @ SI_SubTabsHolder.java");
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

        return null;

    }

    public ArrayList<ManagerOfSubTabBasePane> getArrayList_of_ManagerOfSubTabBasePane() {
        return arrayList_of_ManagerOfSubTabBasePane;
    }

    public String getSectionName() {
        return sectionName;
    }

}
