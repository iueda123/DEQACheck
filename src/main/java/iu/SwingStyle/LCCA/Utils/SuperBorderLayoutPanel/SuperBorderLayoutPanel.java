package iu.SwingStyle.LCCA.Utils.SuperBorderLayoutPanel;

import javax.swing.*;
import java.awt.*;

public class SuperBorderLayoutPanel extends JPanel {

    // このJPanel拡張クラスは、IntelliJなどのIDEで採用されているような、West、East、North、South、Center領域があり、領域境界は可変的で、必要に応じて閉じることができるものである。

    private static final int USER_DIVIDER_SIZE = 8;

    private JSplitPane mainVerticalSplit;
    private JSplitPane northCenterSplit;
    private JSplitPane westCenterSplit;
    private JSplitPane fullHorizontalSplit;

    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JPanel centerPanel;
    private JPanel emptyNorth;
    private JPanel emptySouth;
    private JPanel emptyWest;
    private JPanel emptyEast;

    private boolean northVisible = false;
    private boolean southVisible = false;
    private boolean westVisible = false;
    private boolean eastVisible = false;

    private int northDividerLocation = 200;
    private int southDividerLocation = 200;
    private int westDividerLocation = 200;
    private int eastDividerLocation = 200;

    public SuperBorderLayoutPanel() {
        setLayout(new BorderLayout());
        initializePanels();
        buildLayout();
    }

    private void initializePanels() {
        northPanel = new JPanel(new BorderLayout());
        southPanel = new JPanel(new BorderLayout());
        westPanel = new JPanel(new BorderLayout());
        eastPanel = new JPanel(new BorderLayout());
        centerPanel = new JPanel(new BorderLayout());
        emptyNorth = createPlaceholder();
        emptySouth = createPlaceholder();
        emptyWest = createPlaceholder();
        emptyEast = createPlaceholder();
    }

    private void buildLayout() {
        // Center area: (West | Center) | East
        westCenterSplit = createSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        westCenterSplit.setLeftComponent(emptyWest);
        westCenterSplit.setRightComponent(centerPanel);
        westCenterSplit.setResizeWeight(0.0);
        trackWestDivider(westCenterSplit);

        fullHorizontalSplit = createSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        fullHorizontalSplit.setLeftComponent(westCenterSplit);
        fullHorizontalSplit.setRightComponent(emptyEast);
        fullHorizontalSplit.setResizeWeight(1.0);
        trackEastDivider(fullHorizontalSplit);

        // Vertical stacking: North | (Center section with west/east) | South
        northCenterSplit = createSplitPane(JSplitPane.VERTICAL_SPLIT);
        northCenterSplit.setTopComponent(emptyNorth);
        northCenterSplit.setBottomComponent(fullHorizontalSplit);
        northCenterSplit.setResizeWeight(0.0);
        trackNorthDivider(northCenterSplit);

        mainVerticalSplit = createSplitPane(JSplitPane.VERTICAL_SPLIT);
        mainVerticalSplit.setTopComponent(northCenterSplit);
        mainVerticalSplit.setBottomComponent(emptySouth);
        mainVerticalSplit.setResizeWeight(1.0);
        trackSouthDivider(mainVerticalSplit);

        // Initialize with current visibility flags
        updateLayout();

        add(mainVerticalSplit, BorderLayout.CENTER);
    }

    private void updateLayout() {
        // West visibility
        if (westVisible) {
            westCenterSplit.setLeftComponent(westPanel);
            westCenterSplit.setDividerSize(USER_DIVIDER_SIZE);
            setDividerLocationLater(westCenterSplit, westDividerLocation);
        } else {
            westCenterSplit.setLeftComponent(emptyWest);
            westCenterSplit.setDividerSize(0);
            westCenterSplit.setDividerLocation(0);
            SwingUtilities.invokeLater(() -> westCenterSplit.setDividerLocation(0));
        }

        // East visibility (remember width when hiding)
        if (eastVisible) {
            fullHorizontalSplit.setRightComponent(eastPanel);
            fullHorizontalSplit.setDividerSize(USER_DIVIDER_SIZE);
            setDividerLocationFromRight(fullHorizontalSplit, eastDividerLocation);
        } else {
            fullHorizontalSplit.setRightComponent(emptyEast);
            fullHorizontalSplit.setDividerSize(0);
            fullHorizontalSplit.setDividerLocation(Integer.MAX_VALUE);
            SwingUtilities.invokeLater(() -> fullHorizontalSplit.setDividerLocation(Integer.MAX_VALUE));
        }

        // North visibility
        if (northVisible) {
            northCenterSplit.setTopComponent(northPanel);
            northCenterSplit.setDividerSize(USER_DIVIDER_SIZE);
            setDividerLocationLater(northCenterSplit, northDividerLocation);
        } else {
            northCenterSplit.setTopComponent(emptyNorth);
            northCenterSplit.setDividerSize(0);
            northCenterSplit.setDividerLocation(0);
            SwingUtilities.invokeLater(() -> northCenterSplit.setDividerLocation(0));
        }

        // South visibility
        if (southVisible) {
            mainVerticalSplit.setBottomComponent(southPanel);
            mainVerticalSplit.setDividerSize(USER_DIVIDER_SIZE);
            setDividerLocationFromBottom(mainVerticalSplit, southDividerLocation);
        } else {
            mainVerticalSplit.setBottomComponent(emptySouth);
            mainVerticalSplit.setDividerSize(0);
            mainVerticalSplit.setDividerLocation(Integer.MAX_VALUE);
            SwingUtilities.invokeLater(() -> mainVerticalSplit.setDividerLocation(Integer.MAX_VALUE));
        }

        revalidate();
        repaint();
    }

    private JPanel createPlaceholder() {
        JPanel placeholder = new JPanel();
        placeholder.setPreferredSize(new Dimension(0, 0));
        placeholder.setMinimumSize(new Dimension(0, 0));
        placeholder.setMaximumSize(new Dimension(0, 0));
        return placeholder;
    }

    private void setDividerLocationLater(JSplitPane splitPane, int dividerLocation) {
        Runnable setter = () -> splitPane.setDividerLocation(dividerLocation);
        setter.run();
        SwingUtilities.invokeLater(setter);
    }

    private void setDividerLocationFromRight(JSplitPane splitPane, int distanceFromRight) {
        Runnable setter = () -> {
            int width = splitPane.getWidth();
            int location = Math.max(0, width - distanceFromRight);
            splitPane.setDividerLocation(location);
        };
        setter.run();
        SwingUtilities.invokeLater(setter);
    }

    private void setDividerLocationFromBottom(JSplitPane splitPane, int distanceFromBottom) {
        Runnable setter = () -> {
            int height = splitPane.getHeight();
            int location = Math.max(0, height - distanceFromBottom);
            splitPane.setDividerLocation(location);
        };
        setter.run();
        SwingUtilities.invokeLater(setter);
    }

    private JSplitPane createSplitPane(int orientation) {
        JSplitPane splitPane = new JSplitPane(orientation);
        splitPane.setDividerSize(USER_DIVIDER_SIZE);
        splitPane.setContinuousLayout(true);
        splitPane.setBorder(null);
        return splitPane;
    }

    private void trackNorthDivider(JSplitPane splitPane) {
        splitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, evt -> {
            Object newValue = evt.getNewValue();
            if (northVisible && newValue instanceof Integer) {
                northDividerLocation = (Integer) newValue;
            }
        });
    }

    private void trackSouthDivider(JSplitPane splitPane) {
        splitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, evt -> {
            Object newValue = evt.getNewValue();
            if (southVisible && newValue instanceof Integer) {
                int height = splitPane.getHeight();
                if (height > 0) {
                    southDividerLocation = Math.max(0, height - (Integer) newValue);
                }
            }
        });
    }

    private void trackWestDivider(JSplitPane splitPane) {
        splitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, evt -> {
            Object newValue = evt.getNewValue();
            if (westVisible && newValue instanceof Integer) {
                westDividerLocation = (Integer) newValue;
            }
        });
    }

    private void trackEastDivider(JSplitPane splitPane) {
        splitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, evt -> {
            Object newValue = evt.getNewValue();
            if (eastVisible && newValue instanceof Integer) {
                int width = splitPane.getWidth();
                if (width > 0) {
                    eastDividerLocation = Math.max(0, width - (Integer) newValue);
                }
            }
        });
    }

    // Public API methods

    public void setNorthComponent(JComponent component) {
        northPanel.removeAll();
        if (component != null) {
            northPanel.add(component, BorderLayout.CENTER);
        }
        northPanel.revalidate();
        northPanel.repaint();
    }

    public void setSouthComponent(JComponent component) {
        southPanel.removeAll();
        if (component != null) {
            southPanel.add(component, BorderLayout.CENTER);
        }
        southPanel.revalidate();
        southPanel.repaint();
    }

    public void setWestComponent(JComponent component) {
        westPanel.removeAll();
        if (component != null) {
            westPanel.add(component, BorderLayout.CENTER);
        }
        westPanel.revalidate();
        westPanel.repaint();
    }

    public void setEastComponent(JComponent component) {
        eastPanel.removeAll();
        if (component != null) {
            eastPanel.add(component, BorderLayout.CENTER);
        }
        eastPanel.revalidate();
        eastPanel.repaint();
    }

    public void setCenterComponent(JComponent component) {
        centerPanel.removeAll();
        if (component != null) {
            centerPanel.add(component, BorderLayout.CENTER);
        }
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public void setNorthVisible(boolean visible) {
        if (northVisible != visible) {
            northVisible = visible;
            updateLayout();
        }
    }

    public void setSouthVisible(boolean visible) {
        if (southVisible != visible) {
            southVisible = visible;
            updateLayout();
        }
    }

    public void setWestVisible(boolean visible) {
        if (westVisible != visible) {
            westVisible = visible;
            updateLayout();
        }
    }

    public void setEastVisible(boolean visible) {
        if (eastVisible != visible) {
            if (!visible) {
                rememberEastDividerLocation();
            }
            eastVisible = visible;
            updateLayout();
        }
    }

    public boolean isNorthVisible() {
        return northVisible;
    }

    public boolean isSouthVisible() {
        return southVisible;
    }

    public boolean isWestVisible() {
        return westVisible;
    }

    public boolean isEastVisible() {
        return eastVisible;
    }

    public void setNorthDividerLocation(int location) {
        northDividerLocation = location;
        updateLayout();
    }

    public void setSouthDividerLocation(int location) {
        southDividerLocation = location;
        updateLayout();
    }

    public void setWestDividerLocation(int location) {
        westDividerLocation = location;
        updateLayout();
    }

    public void setEastDividerLocation(int location) {
        eastDividerLocation = location;
        updateLayout();
    }

    public int getNorthDividerLocation() {
        return northDividerLocation;
    }

    public int getSouthDividerLocation() {
        return southDividerLocation;
    }

    public int getWestDividerLocation() {
        return westDividerLocation;
    }

    public int getEastDividerLocation() {
        return eastDividerLocation;
    }

    public JPanel getNorthPanel() {
        return northPanel;
    }

    public JPanel getSouthPanel() {
        return southPanel;
    }

    public JPanel getWestPanel() {
        return westPanel;
    }

    public JPanel getEastPanel() {
        return eastPanel;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    private void rememberEastDividerLocation() {
        Container parent = eastPanel.getParent();
        if (parent instanceof JSplitPane) {
            JSplitPane splitPane = (JSplitPane) parent;
            int width = splitPane.getWidth();
            if (width > 0) {
                eastDividerLocation = Math.max(0, width - splitPane.getDividerLocation());
                return;
            }
        }
        int eastWidth = eastPanel.getWidth();
        if (eastWidth > 0) {
            eastDividerLocation = eastWidth;
        }
    }

    /*
    public void setWestWidth(int w) {
        westDividerLocation = w;
        updateLayout();
    }
    */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create main frame
            JFrame frame = new JFrame("SuperBorderLayoutPanel Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create SuperBorderLayoutPanel instance
            SuperBorderLayoutPanel panel = new SuperBorderLayoutPanel();

            // Create and configure Center panel
            JPanel centerContent = new JPanel(new BorderLayout());
            centerContent.setBackground(Color.WHITE);
            JLabel centerLabel = new JLabel("<html><div style='text-align: center;'>" +
                    "<h1>Center Panel - Main Content Area</h1>" +
                    "<p>This is the main content area.</p>" +
                    "<p>Resize the dividers by dragging them.</p>" +
                    "<p>Use the buttons in the West panel to show/hide regions.</p>" +
                    "</div></html>", SwingConstants.CENTER);
            centerContent.add(centerLabel, BorderLayout.CENTER);
            panel.setCenterComponent(centerContent);

            // Create and configure North panel
            JPanel northContent = new JPanel(new BorderLayout());
            northContent.setBackground(new Color(173, 216, 230)); // Light blue
            northContent.setPreferredSize(new Dimension(0, 60));
            JLabel northLabel = new JLabel("North Panel - Toolbar Area", SwingConstants.CENTER);
            northLabel.setFont(northLabel.getFont().deriveFont(16f));
            northContent.add(northLabel, BorderLayout.CENTER);
            panel.setNorthComponent(northContent);
            panel.setNorthVisible(true);
            panel.setNorthDividerLocation(60);

            // Create and configure South panel
            JPanel southContent = new JPanel(new BorderLayout());
            southContent.setBackground(new Color(144, 238, 144)); // Light green
            southContent.setPreferredSize(new Dimension(0, 50));
            JLabel southLabel = new JLabel("South Panel - Status Bar", SwingConstants.CENTER);
            southLabel.setFont(southLabel.getFont().deriveFont(14f));
            southContent.add(southLabel, BorderLayout.CENTER);
            panel.setSouthComponent(southContent);
            panel.setSouthVisible(true);
            panel.setSouthDividerLocation(50);

            // Create and configure West panel with control buttons
            JPanel westContent = new JPanel();
            westContent.setLayout(new BoxLayout(westContent, BoxLayout.Y_AXIS));
            westContent.setBackground(new Color(255, 255, 224)); // Light yellow
            westContent.setPreferredSize(new Dimension(200, 0));
            westContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel westLabel = new JLabel("West Panel - Controls");
            westLabel.setFont(westLabel.getFont().deriveFont(Font.BOLD, 14f));
            westLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            westContent.add(westLabel);
            westContent.add(Box.createVerticalStrut(20));

            // Toggle North button
            JButton toggleNorthBtn = new JButton("Toggle North");
            toggleNorthBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            toggleNorthBtn.addActionListener(e -> {
                panel.setNorthVisible(!panel.isNorthVisible());
            });
            westContent.add(toggleNorthBtn);
            westContent.add(Box.createVerticalStrut(10));

            // Toggle South button
            JButton toggleSouthBtn = new JButton("Toggle South");
            toggleSouthBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            toggleSouthBtn.addActionListener(e -> {
                panel.setSouthVisible(!panel.isSouthVisible());
            });
            westContent.add(toggleSouthBtn);
            westContent.add(Box.createVerticalStrut(10));

            // Toggle East button
            JButton toggleEastBtn = new JButton("Toggle East");
            toggleEastBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            toggleEastBtn.addActionListener(e -> {
                panel.setEastVisible(!panel.isEastVisible());
            });
            westContent.add(toggleEastBtn);
            westContent.add(Box.createVerticalStrut(20));

            JLabel infoLabel = new JLabel("<html><div style='width: 160px;'>" +
                    "Click buttons to show/hide panels. " +
                    "Drag dividers to resize.</div></html>");
            infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            westContent.add(infoLabel);

            panel.setWestComponent(westContent);
            panel.setWestVisible(true);
            panel.setWestDividerLocation(200);

            // Create and configure East panel
            JPanel eastContent = new JPanel(new BorderLayout());
            eastContent.setBackground(new Color(255, 182, 193)); // Light pink
            eastContent.setPreferredSize(new Dimension(200, 0));
            eastContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel eastLabel = new JLabel("<html><div style='text-align: center;'>" +
                    "<b>East Panel</b><br><br>Properties<br><br>" +
                    "This panel is initially hidden. " +
                    "Click 'Toggle East' to show/hide it.</div></html>");
            eastLabel.setHorizontalAlignment(SwingConstants.CENTER);
            eastContent.add(eastLabel, BorderLayout.NORTH);
            panel.setEastComponent(eastContent);
            panel.setEastVisible(false);
            panel.setEastDividerLocation(200);

            // Add panel to frame and display
            frame.getContentPane().add(panel);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }


}
