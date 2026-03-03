package iu.SwingStyle.LCCA.Startup.RsltComparatorV10_11_12;

import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v11.DEDC.DEDC_v11_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v11.DESI.DESI_v11_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.DEResult_v12.DENM2.DENM_v12_SubTabsHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.MainWindow.MainWindowHolder;
import iu.SwingStyle.LCCA.Utils.CollapsiblePanel;
import iu.SwingStyle.LCCA.Utils.VerticalTextTabbedPane;

import java.awt.*;
import javax.swing.*;

public class BasePaneCreator {

    CHolderMediator cHolderMediator;

    public BasePaneCreator(CHolderMediator cHolderMediator) {
        this.cHolderMediator = cHolderMediator;
    }

    public void addBasePaneToMainFrame() {
        /* **** 土台パネルをメインパネルに埋め込む **** */
        // このとき各サブパネルに登録されているPropertyChangeListenerにシグナルが送られる。
        ((MainWindowHolder) cHolderMediator.getInstanceOfAMember("main_window_holder"))
                .addPanelToCenter(createBasePane());
    }

    private JPanel createBasePane() {
        JPanel basePane = new JPanel(new BorderLayout());

        // Factory を介して各パネル（Panel_Aのサブクラス）を生成し、配置。

        /* **** Component を配置する **** */
        //basePane.add(
        //    (cHolderMediator.getInstanceOfAMember("button_panel_holder")).getBaseComponent(),
        //    BorderLayout.WEST);

        VerticalTextTabbedPane tabbedPane = new VerticalTextTabbedPane(JTabbedPane.LEFT);

        //tabbedPane.add("1. SI", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_SI")).getBaseComponent());
        //tabbedPane.setToolTipTextAt(0, "Study Identification");

        int index = 0;

        tabbedPane.add("1. DE-SI", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_DESI")).getBaseComponent());
        tabbedPane.setToolTipTextAt(index, "Study Identification");
        index++;

        tabbedPane.add("2. DE-SC", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_DESC")).getBaseComponent());
        tabbedPane.setToolTipTextAt(index, "Study Characteristics");
        index++;

        tabbedPane.add("3. DE-RCI", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_DERCI")).getBaseComponent());
        tabbedPane.setToolTipTextAt(index, "Reference Cohort and Imaging");
        index++;

        tabbedPane.add("4. DE-NM", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_DENM")).getBaseComponent());
        tabbedPane.setToolTipTextAt(index, "Normative Modeling");
        index++;

        tabbedPane.add("5. DE-CAA", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_DECAA")).getBaseComponent());
        tabbedPane.setToolTipTextAt(index, "Clinical Application and Analysis");
        index++;

        tabbedPane.add("6. DE-GN", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_DEGN")).getBaseComponent());
        tabbedPane.setToolTipTextAt(index, "General Notes");
        index++;

        //-----------------------
        tabbedPane.add("7. DE-SI", (cHolderMediator.getInstanceOfAMember(DESI_v11_SubTabsHolder.staticMemberName)).getBaseComponent());
        tabbedPane.setToolTipTextAt(index, "Study Identification");
        index++;

        tabbedPane.add("8. DE-DC", (cHolderMediator.getInstanceOfAMember(DEDC_v11_SubTabsHolder.staticMemberName)).getBaseComponent());
        tabbedPane.setToolTipTextAt(index, "Dataset Characteristics");
        index++;

        tabbedPane.add("9. DE-NM2", (cHolderMediator.getInstanceOfAMember(DENM_v12_SubTabsHolder.staticMemberName)).getBaseComponent());
        tabbedPane.setToolTipTextAt(index, "Normative Modeling 2");
        index++;


        //-----------------------

        //tabbedPane.add("7. QA-CM", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_QACM")).getBaseComponent());
        //tabbedPane.setToolTipTextAt(6, "Common Part of QA");

        //tabbedPane.add("8. QA-NM", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_QANM")).getBaseComponent());
        //tabbedPane.setToolTipTextAt(7, "Normative Modeling Part of QA");

        //tabbedPane.add("9. QA-CR", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_QACR")).getBaseComponent());
        //tabbedPane.setToolTipTextAt(8, "Clinical Research Part of QA");

        //tabbedPane.add("7. QASI", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_QASI")).getBaseComponent());
        //tabbedPane.setToolTipTextAt(6, "Study Identification of QA");

        //tabbedPane.add("8. QA1_v6", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_QA1_v6")).getBaseComponent());
        //tabbedPane.setToolTipTextAt(7, "Quality Assessment Part 1 (v6)");

        //tabbedPane.add("9. QA2_v6", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_QA2_v6")).getBaseComponent());
        //tabbedPane.setToolTipTextAt(8, "Quality Assessment Part 2 (v6)");

        //tabbedPane.add("10. QAAC", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_QAAC")).getBaseComponent());
        //tabbedPane.setToolTipTextAt(9, "Additional Comment of QA");

        //-----------------------
        tabbedPane.add("98. NLM", (cHolderMediator.getInstanceOfAMember("with_notebook_lm_pane_holder")).getBaseComponent());
        tabbedPane.setToolTipTextAt(index, "NotebookLM");
        index++;

        tabbedPane.add("99. SMRRY", (cHolderMediator.getInstanceOfAMember("summary_pane_holder")).getBaseComponent());
        tabbedPane.setToolTipTextAt(index, "Summary Pane");
        index++;

        //-----------------------

        JComponent explanationPanelHolder = (cHolderMediator.getInstanceOfAMember("explanation_panel_holder").getBaseComponent());

        CollapsiblePanel collapsiblePanel = new CollapsiblePanel(
                tabbedPane,
                explanationPanelHolder,
                null,
                null,
                null);

        collapsiblePanel.setPreferredSize(new Dimension(900, 900));

        return collapsiblePanel;
        //return new CollapsiblePanel(tabbedPane, null, new JLabel("West"), new JLabel("South"), new JLabel("North"));

        //basePane.add(tabbedPane, BorderLayout.CENTER);
        //return basePane;

    }
}
