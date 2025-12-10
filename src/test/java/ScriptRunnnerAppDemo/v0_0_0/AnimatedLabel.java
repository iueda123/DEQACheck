package ScriptRunnnerAppDemo.v0_0_0;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

public class AnimatedLabel extends JLabel implements ActionListener, HierarchyListener {

    private final Timer animator;
    private final AnimeIcon icon = new AnimeIcon();

    public AnimatedLabel() {
        super();
        animator = new Timer(100, this);
        setIcon(icon);
        addHierarchyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        icon.next();
        repaint();
    }

    @Override
    public void hierarchyChanged(HierarchyEvent e) {
        if ((e.getChangeFlags() & HierarchyEvent.DISPLAYABILITY_CHANGED) != 0 && !isDisplayable()) {
            animator.stop();
        }
    }

    public void startAnimation() {
        icon.setRunning(true);
        animator.start();
    }

    public void stopAnimation() {
        icon.setRunning(false);
        animator.stop();
    }
}
