package iu.LCAC.Member.componentholder.Concretes.StatusPanel;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;

import javax.swing.*;
import java.awt.*;

public class StatusPanelHolder extends AbstCHolderMember {

    private JPanel panel = new JPanel(new BorderLayout());
    private Box baseBox = Box.createHorizontalBox();
    private JLabel statusLabel = new JLabel("-");

    public StatusPanelHolder(String cholder_name, String short_name) throws HeadlessException {
        super(cholder_name, short_name);

        baseBox.add(statusLabel);
        baseBox.add(Box.createHorizontalGlue());

        panel.add(baseBox, BorderLayout.CENTER);
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

    public void showAMessageWhileFiveSeconds(String message) {
        showAMessageForWhile(message, 500);
    }

    public void showAMessageForWhile(String message, int display_time_in_sec) {
        statusLabel.setText(message);

        Timer timer = new Timer(display_time_in_sec, e -> {
            statusLabel.setText("-");
        });
        timer.setRepeats(false);
        timer.start();
    }
}
