package iu.LCAC.Startup.MainStarter;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Concretes.MainWindow.MainWindowHolder;
import iu.LCAC.Member.componentholder.Concretes.StatusPanel.StatusPanelHolder;

import javax.swing.*;
import java.awt.*;

public class StatusPanelCreator {
    private final CHolderMediator cholderMediator;
    private final ActionMediator actionMediator;

    public StatusPanelCreator(ActionMediator actionMediator, CHolderMediator cholderMediator) {
        this.actionMediator = actionMediator;
        this.cholderMediator = cholderMediator;
    }

    public void addStatusAreaToMainFrame() {
        ((MainWindowHolder) cholderMediator.getInstanceOfAMember("main_window_holder"))
                .addPanelToSouth(createStatusArea());
    }

    private JPanel createStatusArea() {
        JPanel basePane = new JPanel(new BorderLayout());
        //basePane.setBackground(Color.ORANGE);

        Box box = Box.createHorizontalBox();

        //StatusLabel;
        StatusPanelHolder statusPanelHolder = ((StatusPanelHolder) cholderMediator.getInstanceOfAMember("status_panel_holder"));
        box.add(statusPanelHolder.getBaseComponent());

        box.add(Box.createHorizontalGlue());

        basePane.add(box, BorderLayout.CENTER);

        return basePane;
    }

}
