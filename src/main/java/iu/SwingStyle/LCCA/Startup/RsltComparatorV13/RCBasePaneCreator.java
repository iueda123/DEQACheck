package iu.SwingStyle.LCCA.Startup.RsltComparatorV13;

import iu.SwingStyle.LCCA.Mediator.RsltComparator.RCCHolderMediator;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.MainWindow.MainWindowHolder;

import javax.swing.*;
import java.awt.*;

/**
 * RsltComparator 用の BasePaneCreator。
 * SideBySideComparisonHolder の baseComponent をセンターに配置する。
 */
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
        return comparisonPanel;
    }
}
