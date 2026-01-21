package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common;

import iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common.DEQAResultPane.One_DEQAResult_Pane_Abs;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.List;

public class SubTabBehaviorUtils {

    public static void attachConfirmOnTabSwitch(JTabbedPane tabbedPane,
                                                Component parent,
                                                String title,
                                                String message,
                                                Runnable onYes) {
        tabbedPane.addChangeListener(new ChangeListener() {
            private int lastIndex = -1;
            private boolean initialized = false;
            private boolean internalChange = false;

            @Override
            public void stateChanged(ChangeEvent e) {
                if (internalChange) return;

                if (!initialized) {
                    lastIndex = tabbedPane.getSelectedIndex();
                    initialized = true;
                    return;
                }

                int newIndex = tabbedPane.getSelectedIndex();
                if (newIndex != lastIndex) {
                    int result = JOptionPane.showConfirmDialog(
                            parent,
                            message,
                            title,
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (result == JOptionPane.CANCEL_OPTION) {
                        internalChange = true;
                        tabbedPane.setSelectedIndex(lastIndex);
                        internalChange = false;
                        return;
                    } else if (result == JOptionPane.YES_OPTION) {
                        if (onYes != null) onYes.run();
                    }

                    lastIndex = newIndex;
                }
            }
        });
    }

    public static void loadAllJsonPanels(List<ManagerOfSubTabBasePane> managers) {
        for (ManagerOfSubTabBasePane m : managers) {
            for (One_DEQAResult_Pane_Abs pane : m.getDeqaPaneArray()) {
                pane.loadJson();
            }
        }
    }
}

