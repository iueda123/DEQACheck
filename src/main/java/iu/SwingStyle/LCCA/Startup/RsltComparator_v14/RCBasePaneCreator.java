package iu.SwingStyle.LCCA.Startup.RsltComparator_v14;

import iu.SwingStyle.LCCA.Mediator.RsltComparator.RCCHolderMediator;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.MainWindow.MainWindowHolder;
import iu.SwingStyle.LCCA.Utils.VerticalTextTabbedPane;

import javax.swing.*;
import java.awt.*;

public class RCBasePaneCreator {

    private final RCCHolderMediator cHolderMediator;

    public RCBasePaneCreator(RCCHolderMediator cHolderMediator) {
        this.cHolderMediator = cHolderMediator;
    }

    public void addBasePaneToMainFrame() {
        ((MainWindowHolder) cHolderMediator.getInstanceOfAMember("main_window_holder"))
                .addPanelToCenter(createBasePane());
    }

    private JComponent createBasePane() {
        JComponent comparisonPanel = cHolderMediator
                .getInstanceOfAMember("side_by_side_comparison_holder")
                .getBaseComponent();
        comparisonPanel.setPreferredSize(new Dimension(1200, 800));

        JComponent notebookLmPanel = cHolderMediator
                .getInstanceOfAMember("with_notebook_lm_pane_holder")
                .getBaseComponent();

        VerticalTextTabbedPane tabbedPane = new VerticalTextTabbedPane(JTabbedPane.LEFT);
        tabbedPane.addTab("1. COMP", comparisonPanel);
        tabbedPane.setToolTipTextAt(0, "Side-by-side comparison");
        tabbedPane.addTab("98. NLM", notebookLmPanel);
        tabbedPane.setToolTipTextAt(1, "NotebookLM");

        return tabbedPane;
    }
}
