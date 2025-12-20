package iu.LCCA.Member.componentholder.Concretes.Sample.ButtonPanel;


import iu.LCCA.Mediator.action.ActionMediator;
import iu.LCCA.Mediator.componentholder.CHolderMediator;
import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCCA.Utils.FontManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ButtonPanelHolder extends AbstCHolderMember {

    JPanel basePanel = new JPanel();

    JButton sampleButton = new JButton("Button");

    public ButtonPanelHolder(String cholder_name, String short_name, String... args) {
        super(cholder_name, short_name);

        if (args.length > 0) {
            //System.out.println(args[0]);
            sampleButton.setText("これは第１引数です → " + args[0]);
        }

        sampleButton.addActionListener(
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (actionMediator != null) {
                            actionMediator.getInstanceOfAMember("change_color_of_center").perform(actionEvent);
                        } else {
                            System.err.println("Action Starter is null! @ " + this.getClass().toString());
                        }
                    }
                });

        basePanel.add(sampleButton);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void postInitialize() {
    }

    @Override
    public void doWorkAsMember() {
    }

    public void setTextToTheButton(String text) {
        this.sampleButton.setText(text);
    }

    @Override
    public JComponent getBaseComponent() {
        return this.basePanel;
    }

    @Override
    public void setCHolderMediator(CHolderMediator cHolderMediator) {
        this.cholderMediator = cHolderMediator;
    }

    @Override
    public void setActionMediator(ActionMediator actionMediator) {
        this.actionMediator = actionMediator;
    }

    private static void createAndShowGUI() {
        FontManager.setGlobalFont();

        JFrame frame = new JFrame("Member Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AbstCHolderMember holder = new ButtonPanelHolder("button_pane", "Button Panel", "");
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
