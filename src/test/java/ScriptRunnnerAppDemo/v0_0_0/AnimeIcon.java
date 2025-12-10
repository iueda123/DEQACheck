package ScriptRunnnerAppDemo.v0_0_0;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;

public class AnimeIcon implements Icon {

    private static final Color cColor = new Color(0.5f, 0.5f, 0.5f);
    private static final double r = 2.0d;
    private static final double sx = 1.0d;
    private static final double sy = 1.0d;
    private static final Dimension dim = new Dimension((int) (r * 8 + sx * 2), (int) (r * 8 + sy * 2));
    private final java.util.List<Shape> list = new ArrayList<Shape>(Arrays.asList(
            new Ellipse2D.Double(sx + 3 * r, sy + 0 * r, 2 * r, 2 * r),
            new Ellipse2D.Double(sx + 5 * r, sy + 1 * r, 2 * r, 2 * r),
            new Ellipse2D.Double(sx + 6 * r, sy + 3 * r, 2 * r, 2 * r),
            new Ellipse2D.Double(sx + 5 * r, sy + 5 * r, 2 * r, 2 * r),
            new Ellipse2D.Double(sx + 3 * r, sy + 6 * r, 2 * r, 2 * r),
            new Ellipse2D.Double(sx + 1 * r, sy + 5 * r, 2 * r, 2 * r),
            new Ellipse2D.Double(sx + 0 * r, sy + 3 * r, 2 * r, 2 * r),
            new Ellipse2D.Double(sx + 1 * r, sy + 1 * r, 2 * r, 2 * r)));
    private boolean isRunning = false;

    public void next() {
        if (isRunning) {
            list.add(list.remove(0));
        }
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public int getIconWidth() {
        return dim.width;
    }

    @Override
    public int getIconHeight() {
        return dim.height;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint((c != null) ? c.getBackground() : Color.WHITE);
        g2d.fillRect(x, y, getIconWidth(), getIconHeight());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(cColor);
        float alpha = 0.0f;
        g2d.translate(x, y);
        for (Shape s : list) {
            alpha = isRunning ? alpha + 0.1f : 0.5f;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.fill(s);
        }
        g2d.translate(-x, -y);
    }
}