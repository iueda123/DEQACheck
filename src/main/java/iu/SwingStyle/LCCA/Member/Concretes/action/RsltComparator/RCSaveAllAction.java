package iu.SwingStyle.LCCA.Member.Concretes.action.RsltComparator;

import iu.SwingStyle.LCCA.Mediator.action.ActionMediator;
import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;
import iu.SwingStyle.LCCA.Member.Abstracts.action.AbstActionMember;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.DEQAResultPane.One_DEQAResult_Pane_Abs;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.ManagerOfSubTabBasePane;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.RsltComparator.SideBySideComparisonHolder;
import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.DEQAResultPane.ComparisonColumnPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * 全ての ComparisonColumnPane の saveJson() を呼ぶアクション。
 * Ctrl+S ショートカット。
 */
public class RCSaveAllAction extends AbstActionMember {

    public RCSaveAllAction(String actionName, String shortName) {
        super(actionName, shortName);
    }

    @Override
    protected void setAcceleratorKeyStroke() {
        this.getMenuItem().setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void perform(ActionEvent actionEvent) {
        if (cholderMediator == null) {
            System.err.println("CHolderMediator is null in RCSaveAllAction.");
            return;
        }

        SideBySideComparisonHolder holder = (SideBySideComparisonHolder)
                cholderMediator.getInstanceOfAMember("side_by_side_comparison_holder");
        if (holder == null) return;

        ArrayList<ManagerOfSubTabBasePane> managers = holder.getArrayList_of_ManagerOfSubTabBasePane();
        int count = 0;
        for (ManagerOfSubTabBasePane manager : managers) {
            for (One_DEQAResult_Pane_Abs pane : manager.getDeqaPaneArray()) {
                pane.saveJson();
                count++;
            }
        }
        System.out.println("RCSaveAllAction: saved " + count + " panes.");
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
}
