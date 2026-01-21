package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v10.DEGN;

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

public class DEGN_SubTabsHolder extends AbstCHolderMember implements SubTabsHolderItrfc {

    public static final String staticMemberName = "sub_tabs_holder_DEGN";

    static final String LOCATION_OF_JSON = "DE/json/";


    static String sectionName = "general_note_part";

    static String subSection_1_Name = "gn1_general_notes";

    static String subSection_1_TabName = "GN1 General Notes";

    JPanel panel = new JPanel(new BorderLayout());
    JTabbedPane baseTabPane = new JTabbedPane();

    // Reference Cohort and Imaging
    ManagerOfSubTabBasePane mngrOfSubTabBasePane_1 = new ManagerOfSubTabBasePane("DE", subSection_1_TabName, sectionName, subSection_1_Name, baseTabPane);

    ArrayList<ManagerOfSubTabBasePane> arrayList_of_ManagerOfSubTabBasePane = new ArrayList<>();
    private final String authorYear;

    public DEGN_SubTabsHolder(String cholder_name, String short_name, String authorYear) {
        super(cholder_name, short_name);

        this.authorYear = authorYear;

        arrayList_of_ManagerOfSubTabBasePane.add(mngrOfSubTabBasePane_1);
        mngrOfSubTabBasePane_1.registerSubTabsHolder(this);

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

                mngrOfSubTabBasePane_1.addToTheDePaneArray(new One_A_Style_Pane(jsonFolderPathString.toString(), jsonFileName, sectionName, subSection_1_Name));
            }
        }

        //個々のサブタブの中身の準備と配備
        for (ManagerOfSubTabBasePane managerOfSubTabBasePaneRCAI : arrayList_of_ManagerOfSubTabBasePane) {
            baseTabPane.add(managerOfSubTabBasePaneRCAI.getTabName(), managerOfSubTabBasePaneRCAI.constructBasePaneOfSubTab());
        }

        // タブ切替前に保存の確認（キャンセルで切替阻止）
        baseTabPane.setModel(new ConfirmBeforeSwitchSelectionModel(panel, arrayList_of_ManagerOfSubTabBasePane));

        panel.add(baseTabPane, BorderLayout.CENTER);
    }

    @Override
    public void postInitialize() {
        //System.out.println("postInitialize() @ GN_SubTabsHolder.java");
        if (actionMediator != null) {

            /* 値を流し込む */
            SubTabBehaviorUtils.loadAllJsonPanels(arrayList_of_ManagerOfSubTabBasePane);

        } else {
            System.err.println("The actionMediator is null. @" + this.getClass());
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
