package iu.SwingStyle.LCCA.Member.Concretes.componentholder.Sample.CheckboxPanel;


import iu.SwingStyle.LCCA.Mediator.action.ActionMediator;
import iu.SwingStyle.LCCA.Mediator.componentholder.CHolderMediator;
import iu.SwingStyle.LCCA.Member.Abstracts.componentholder.AbstCHolderMember;
import iu.SwingStyle.LCCA.Utils.FontManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CheckboxPanelHolder extends AbstCHolderMember {

    JPanel panel = new JPanel();

    JCheckBox SampleCheckBox = new JCheckBox("Change Color");

    public CheckboxPanelHolder(String cholder_name, String short_name, String... args) {
        super(cholder_name, short_name);

        SampleCheckBox.addActionListener(new SampleSelectionChangeListener());
        SampleCheckBox.addPropertyChangeListener(new SamplePropertyChangeListener());

        panel.add(SampleCheckBox);
    }

    @Override
    public JComponent getBaseComponent() {
        return this.panel;
    }

    @Override
    public void postInitialize() {
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

    private class SampleSelectionChangeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("Something of SampleCheckBox has changed.");

            if (actionMediator != null) {
                actionMediator
                        .getInstanceOfAMember("change_color_of_center")
                        .perform(new ActionEvent(this, 0, "Property of SampleCheckBox has changed."));
            } else {
                System.err.println("Action Starter is null!" + "@" + this.getClass().toString());
            }
        }
    }

    /**
     * コンポーネントのプロパティが変更された時に呼び出される。 コンポーネントのプロパティが変更されるときとは、 フォント、前景色、背景色が変更されたとき、 コンポーネント生成時などである。
     *
     * <p>起動時に行わせたい処理を書き込むと良いのだと思う。 TODO:起動時に2回呼び出されるバグ？あり。大きな問題はないので一旦放置。なんか気持ち悪い。
     */
    private class SamplePropertyChangeListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
            System.out.println("Property of SampleCheckBox has changed.");

            if (actionMediator != null) {
                System.out.println("Action Starter is not null!" + "@" + this.getClass().toString());
                actionMediator
                        .getInstanceOfAMember("change_color_of_center")
                        .perform(new ActionEvent(this, 0, "Property of SampleCheckBox has changed."));
            } else {
                System.err.println("Action Starter is null!" + "@" + this.getClass().toString());
            }
        }
    }

    private static void createAndShowGUI() {
        FontManager.setGlobalFont();

        JFrame frame = new JFrame("Member Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AbstCHolderMember holder = new CheckboxPanelHolder("checkbox_pane", "Checkbox Panel", "");
        holder.initialize(); // Build the component before adding to frame

        frame.getContentPane().add(holder.getBaseComponent(), BorderLayout.CENTER);
        frame.pack();
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        holder.postInitialize(); // Run post initialization after the component becomes visible
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
}
