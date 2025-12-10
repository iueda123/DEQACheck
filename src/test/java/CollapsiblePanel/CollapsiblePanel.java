// -*- mode:java; encoding:utf-8 -*-
// vim:set fileencoding=utf-8:
// https://ateraimemo.com/Swing/LayoutAnimation.html

package CollapsiblePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

public final class CollapsiblePanel extends JPanel {

    Box northShowHideBar = Box.createHorizontalBox();
    Box southShowHideBar = Box.createHorizontalBox();
    Box westShowHideBar = Box.createVerticalBox();
    Box eastShowHideBar = Box.createVerticalBox();

    JLabel showHideNorthLabel = new JLabel("v");
    JLabel showHideEastLabel = new JLabel("<");
    JLabel showHideSouthLabel = new JLabel("^");
    JLabel showHideWestLabel = new JLabel(">");

    public CollapsiblePanel(JComponent center_comp, JComponent east_comp, JComponent west_comp, JComponent south_comp, JComponent north_comp) {
        super(new BorderLayout());

        JPanel outerBasePanel = new JPanel(new BorderLayout());

        Timer animator_for_north = new Timer(5, null);
        ControlsBorderLayout_for_NorthAndSouth north_layout = new ControlsBorderLayout_for_NorthAndSouth(animator_for_north);
        JPanel northBasePane = new JPanel(north_layout);
        northBasePane.add(north_comp);
        animator_for_north.addActionListener(e -> northBasePane.revalidate());
        northShowHideBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!animator_for_north.isRunning()) {
                    north_layout.isHidden = northBasePane.getHeight() == 0;
                    showHideNorthLabel.setText(north_layout.isHidden ? "^" : "v");
                    animator_for_north.start();
                }
            }
        });
        northShowHideBar.setFocusable(false);
        outerBasePanel.add(northBasePane, BorderLayout.NORTH);


        Timer animator_for_south = new Timer(5, null);
        ControlsBorderLayout_for_NorthAndSouth south_layout = new ControlsBorderLayout_for_NorthAndSouth(animator_for_south);
        JPanel southBasePane = new JPanel(south_layout);
        southBasePane.add(south_comp);
        animator_for_south.addActionListener(e -> southBasePane.revalidate());
        southShowHideBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!animator_for_south.isRunning()) {
                    south_layout.isHidden = southBasePane.getHeight() == 0;
                    showHideSouthLabel.setText(south_layout.isHidden ? "v" : "^");
                    animator_for_south.start();
                }
            }
        });
        southShowHideBar.setFocusable(false);
        outerBasePanel.add(southBasePane, BorderLayout.SOUTH);


        Timer animator_for_east = new Timer(5, null);
        ControlsBorderLayout_for_EastAndWest east_layout = new ControlsBorderLayout_for_EastAndWest(animator_for_east);
        JPanel eastBasePane = new JPanel(east_layout);
        eastBasePane.add(east_comp);
        animator_for_east.addActionListener(e -> eastBasePane.revalidate());
        eastShowHideBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!animator_for_east.isRunning()) {
                    east_layout.isHidden = eastBasePane.getWidth() == 0;
                    showHideEastLabel.setText(east_layout.isHidden ? ">" : "<");
                    animator_for_east.start();
                }
            }
        });
        eastShowHideBar.setFocusable(false);
        outerBasePanel.add(eastBasePane, BorderLayout.EAST);

        Timer animator_for_west = new Timer(5, null);
        ControlsBorderLayout_for_EastAndWest west_layout = new ControlsBorderLayout_for_EastAndWest(animator_for_west);
        JPanel westBasePane = new JPanel(west_layout);
        westBasePane.add(west_comp);
        animator_for_west.addActionListener(e -> westBasePane.revalidate());
        westShowHideBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!animator_for_west.isRunning()) {
                    west_layout.isHidden = westBasePane.getWidth() == 0;
                    showHideWestLabel.setText(west_layout.isHidden ? "<" : ">");
                    animator_for_west.start();
                }
            }
        });
        westShowHideBar.setFocusable(false);
        outerBasePanel.add(westBasePane, BorderLayout.WEST);

        outerBasePanel.add(new InnerBasePane(new JScrollPane(center_comp)));

        add(outerBasePanel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(CollapsiblePanel::createAndShowGui);
    }

    private static void createAndShowGui() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException ignored) {
            Toolkit.getDefaultToolkit().beep();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getGlobal().severe(ex::getMessage);
            return;
        }
        JFrame frame = new JFrame("CollapsiblePanel Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel east_pane = new JPanel();
        east_pane.add(new JLabel("EAST"));

        JPanel west_pane = new JPanel();
        west_pane.add(new JLabel("WEST"));

        JPanel center_pane = new JPanel();
        center_pane.setLayout(new BoxLayout(center_pane, BoxLayout.Y_AXIS));
        center_pane.add(new JScrollPane(new JTextArea("CENTER")) );

        JPanel south_pane = new JPanel();
        south_pane.add(new JLabel("SOUTH"));

        JPanel north_pane = new JPanel();
        north_pane.add(new JLabel("NORTH"));

        frame.getContentPane().add(new CollapsiblePanel(center_pane, east_pane, west_pane, south_pane, north_pane));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    class InnerBasePane extends JPanel {
        public InnerBasePane(JComponent comp) {
            this.setLayout(new BorderLayout());

            CollapsiblePanel.this.northShowHideBar.setBackground(Color.LIGHT_GRAY);
            CollapsiblePanel.this.northShowHideBar.setOpaque(true);
            CollapsiblePanel.this.northShowHideBar.add(Box.createHorizontalGlue());
            CollapsiblePanel.this.northShowHideBar.add(CollapsiblePanel.this.showHideNorthLabel);
            CollapsiblePanel.this.northShowHideBar.add(Box.createHorizontalGlue());
            add(CollapsiblePanel.this.northShowHideBar, BorderLayout.NORTH);

            CollapsiblePanel.this.southShowHideBar.setBackground(Color.LIGHT_GRAY);
            CollapsiblePanel.this.southShowHideBar.setOpaque(true);
            CollapsiblePanel.this.southShowHideBar.add(Box.createHorizontalGlue());
            CollapsiblePanel.this.southShowHideBar.add(CollapsiblePanel.this.showHideSouthLabel);
            CollapsiblePanel.this.southShowHideBar.add(Box.createHorizontalGlue());
            add(CollapsiblePanel.this.southShowHideBar, BorderLayout.SOUTH);

            CollapsiblePanel.this.eastShowHideBar.setBackground(Color.LIGHT_GRAY);
            CollapsiblePanel.this.eastShowHideBar.setOpaque(true);
            CollapsiblePanel.this.eastShowHideBar.add(Box.createVerticalGlue());
            CollapsiblePanel.this.eastShowHideBar.add(CollapsiblePanel.this.showHideEastLabel);
            CollapsiblePanel.this.eastShowHideBar.add(Box.createVerticalGlue());
            add(CollapsiblePanel.this.eastShowHideBar, BorderLayout.EAST);

            CollapsiblePanel.this.westShowHideBar.setBackground(Color.LIGHT_GRAY);
            CollapsiblePanel.this.westShowHideBar.setOpaque(true);
            CollapsiblePanel.this.westShowHideBar.add(Box.createVerticalGlue());
            CollapsiblePanel.this.westShowHideBar.add(CollapsiblePanel.this.showHideWestLabel);
            CollapsiblePanel.this.westShowHideBar.add(Box.createVerticalGlue());
            add(CollapsiblePanel.this.westShowHideBar, BorderLayout.WEST);


            add(comp, BorderLayout.CENTER);
        }
    }


    class ControlsBorderLayout_for_NorthAndSouth extends BorderLayout {
        public boolean isHidden = true;
        private final Timer animator;
        private int controlsHeight;

        public ControlsBorderLayout_for_NorthAndSouth(Timer animator) {
            super(5, 5);
            this.animator = animator;
        }

        @Override
        public Dimension preferredLayoutSize(Container target) {
            // synchronized (target.getTreeLock()) {
            Dimension ps = super.preferredLayoutSize(target);
            int defaultHeight = animator.isRunning() ? ps.height : 0;
            if (isHidden) {
                if (target.getHeight() < defaultHeight) {
                    controlsHeight += 5;
                }
            } else {
                if (target.getHeight() > 0) {
                    controlsHeight -= 5;
                }
            }
            if (controlsHeight <= 0) {
                controlsHeight = 0;
                animator.stop();
            } else if (controlsHeight >= defaultHeight) {
                controlsHeight = defaultHeight;
                animator.stop();
            }
            ps.height = controlsHeight;
            return ps;
        }
    }


    class ControlsBorderLayout_for_EastAndWest extends BorderLayout {
        public boolean isHidden = true;
        private final Timer animator;
        private int controlsWidth;

        public ControlsBorderLayout_for_EastAndWest(Timer animator) {
            super(5, 5);
            this.animator = animator;
        }

        @Override
        public Dimension preferredLayoutSize(Container target) {
            // synchronized (target.getTreeLock()) {
            Dimension ps = super.preferredLayoutSize(target);
            int defaultWidth = animator.isRunning() ? ps.width : 0;
            if (isHidden) {
                if (target.getWidth() < defaultWidth) {
                    controlsWidth += 5;
                }
            } else {
                if (target.getWidth() > 0) {
                    controlsWidth -= 5;
                }
            }
            if (controlsWidth <= 0) {
                controlsWidth = 0;
                animator.stop();
            } else if (controlsWidth >= defaultWidth) {
                controlsWidth = defaultWidth;
                animator.stop();
            }
            ps.width = controlsWidth;
            return ps;
        }
    }

}