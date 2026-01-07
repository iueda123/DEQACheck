package iu.LCCA.Startup.MainStarter;

import iu.LCCA.Mediator.componentholder.CHolderMediator;
import iu.LCCA.Member.componentholder.Concretes.MainWindow.MainWindowHolder;
import iu.LCCA.Utils.CollapsiblePanel;
import iu.LCCA.Utils.VerticalTextTabbedPane;

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

    tabbedPane.add("1. DE-SI", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_DESI")).getBaseComponent());
    tabbedPane.setToolTipTextAt(0, "Study Identification");

    tabbedPane.add("2. DE-SC", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_DESC")).getBaseComponent());
    tabbedPane.setToolTipTextAt(1, "Study Characteristics");

    tabbedPane.add("3. DE-RCI", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_DERCI")).getBaseComponent());
    tabbedPane.setToolTipTextAt(2, "Reference Cohort and Imaging");

    tabbedPane.add("4. DE-NM", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_DENM")).getBaseComponent());
    tabbedPane.setToolTipTextAt(3, "Normative Modeling");

    tabbedPane.add("5. DE-CAA", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_DECAA")).getBaseComponent());
    tabbedPane.setToolTipTextAt(4, "Clinical Application and Analysis");

    tabbedPane.add("6. DE-GN", (cHolderMediator.getInstanceOfAMember("sub_tabs_holder_DEGN")).getBaseComponent());
    tabbedPane.setToolTipTextAt(5, "General Notes");

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
    tabbedPane.setToolTipTextAt(6, "NotebookLM");

    tabbedPane.add("99. SMRRY", (cHolderMediator.getInstanceOfAMember("summary_pane_holder")).getBaseComponent());
    tabbedPane.setToolTipTextAt(7, "Summary Pane");

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
