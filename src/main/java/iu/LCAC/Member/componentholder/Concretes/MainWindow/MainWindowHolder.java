package iu.LCAC.Member.componentholder.Concretes.MainWindow;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Mediator.componentholder.CHolderMediator;
import iu.LCAC.Member.componentholder.Abstract.AbstCHolderMember;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.*;

public class MainWindowHolder extends AbstCHolderMember {

    private JFrame mainWindow = new JFrame();
    private Box southBox = Box.createVerticalBox();
    private String authorYear = "Someone20XX";

    public MainWindowHolder(String cholder_name, String short_name, String authorYear) throws HeadlessException {
        super(cholder_name, short_name);

        this.authorYear = authorYear;

        mainWindow.addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.out.println("The main window is Closing...");
                        // mainWindow.postInitialize();
                    }

                    @Override
                    public void windowClosed(WindowEvent e) {
                        System.out.println("The main window Closed.");
                        System.exit(0);
                    }
                });
        mainWindow.getContentPane().add(southBox, BorderLayout.SOUTH);
    }

    @Override
    public JComponent getBaseComponent() {
        return null;
    }

    @Override
    public void postInitialize() {
        //System.out.println("postInitialize() @ MainWindowHolder.java");
        /* パネル順序のロード */
        actionMediator
                .getInstanceOfAMember("load_pane_order")
                .perform(new ActionEvent(this, 0, "Load Panel Order."));

        /* NotePaneのロード */
        actionMediator
                .getInstanceOfAMember("load_note_pane")
                .perform(new ActionEvent(this, 0, "Load Note Pane Texts."));

        /* JFrameタイトル */
        Path currentPath = Paths.get("./").toAbsolutePath().normalize();
        String currentDirName = currentPath.getFileName().toString();
        String parentDirName = currentPath.getParent().getFileName().toString();
        String windowTitle = parentDirName + " / " + currentDirName + " / " + authorYear;
        mainWindow.setTitle(windowTitle);

    }

    public JFrame getMainWindow() {
        return this.mainWindow;
    }

    public void addPanelToCenter(JComponent panel) {
        mainWindow.getContentPane().add(panel, BorderLayout.CENTER);
    }


    public void addPanelToSouth(JComponent comp) {
        southBox.add(comp);
    }

    public void addPanelToNorth(JComponent panel) {
        mainWindow.getContentPane().add(panel, BorderLayout.NORTH);
    }

    public void addPanelToWest(JComponent panel) {
        mainWindow.getContentPane().add(panel, BorderLayout.WEST);
    }

    public void addPanelToEast(JComponent panel) {
        mainWindow.getContentPane().add(panel, BorderLayout.EAST);
    }

    public void displayAndInitialize() {

        // System.out.println("displayAndInitialize()");

        EventQueue.invokeLater(
                new Runnable() {

                    @Override
                    public void run() {

                        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                        try {
                            // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mainWindow.pack();
                        mainWindow.setVisible(true);


                        mainWindow.setLocationRelativeTo(null);
                    }
                });
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
