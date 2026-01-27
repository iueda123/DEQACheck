// -*- mode:java; encoding:utf-8 -*-
// vim:set fileencoding=utf-8:
// https://ateraimemo.com/Swing/LayoutAnimation.html

package iu.SwingStyle.LCCA.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

//ToDo:　明示的にBarクリックしないでパネルが閉じてしまったときに > < ^ v が適切に反転しないのを直したい。
//ToDo: 例えばCenter領域にTooltipを表示されるとパネルが閉じてしまう。

public final class CollapsiblePanel extends JPanel {

    // 矢印ラベル用のフォント設定
    private static final int ARROW_FONT_SIZE = 16;
    private static final Font ARROW_FONT = new Font(Font.SANS_SERIF, Font.BOLD, ARROW_FONT_SIZE);
    private static final Color ARROW_COLOR = new Color(50, 50, 50);

    Box northShowHideBar = Box.createHorizontalBox();
    Box southShowHideBar = Box.createHorizontalBox();
    Box westShowHideBar = Box.createVerticalBox();
    Box eastShowHideBar = Box.createVerticalBox();

    JLabel showHideNorthLabel;
    JLabel showHideEastLabel;
    JLabel showHideSouthLabel;
    JLabel showHideWestLabel;

    JComponent centerComponent;

    JComponent eastComponent;
    JComponent westComponent;
    JComponent southComponent;
    JComponent northComponent;

    // スタイル付き矢印ラベルを作成するヘルパーメソッド
    private static JLabel createArrowLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(ARROW_FONT);
        label.setForeground(ARROW_COLOR);
        return label;
    }

    public CollapsiblePanel(JComponent center_comp,
                            JComponent east_comp,
                            JComponent west_comp,
                            JComponent south_comp,
                            JComponent north_comp) {
        this(center_comp, east_comp, true, west_comp, true, south_comp, true, north_comp, true);
    }


    public CollapsiblePanel(JComponent center_comp,
                            JComponent east_comp, boolean eastIsInitiallyColosed,
                            JComponent west_comp, boolean westIsInitiallyColosed,
                            JComponent south_comp, boolean southIsInitiallyColosed,
                            JComponent north_comp, boolean northIsInitiallyColosed) {

        super(new BorderLayout());

        this.centerComponent = center_comp;
        this.eastComponent = east_comp;
        this.westComponent = west_comp;
        this.southComponent = south_comp;
        this.northComponent = north_comp;

        JPanel outerBasePanel = new JPanel(new BorderLayout());

        /* **** NORTH **** */
        Timer animator_for_north = new Timer(5, null);
        ControlsBorderLayout_for_NorthAndSouth north_layout = new ControlsBorderLayout_for_NorthAndSouth(animator_for_north);
        // 初期状態を設定
        if (northIsInitiallyColosed) {
            north_layout.isHidden = false;
            showHideNorthLabel = createArrowLabel("▼");
        } else {
            north_layout.isHidden = true;
            showHideNorthLabel = createArrowLabel("▲");
        }
        JPanel northBasePane = new JPanel(north_layout);
        if (northComponent != null) {
            northBasePane.add(northComponent);
        }
        animator_for_north.addActionListener(e -> northBasePane.revalidate());
        northShowHideBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!animator_for_north.isRunning()) {
                    north_layout.isHidden = northBasePane.getHeight() == 0;
                    showHideNorthLabel.setText(north_layout.isHidden ? "▲" : "▼");
                    animator_for_north.start();
                }
            }
        });
        northShowHideBar.setFocusable(false);
        outerBasePanel.add(northBasePane, BorderLayout.NORTH);

        /* **** SOUTH **** */
        Timer animator_for_south = new Timer(5, null);
        ControlsBorderLayout_for_NorthAndSouth south_layout = new ControlsBorderLayout_for_NorthAndSouth(animator_for_south);
        // 初期状態を設定
        if (southIsInitiallyColosed) {
            south_layout.isHidden = false;
            showHideSouthLabel = createArrowLabel("▲");
        } else {
            south_layout.isHidden = true;
            showHideSouthLabel = createArrowLabel("▼");
        }
        JPanel southBasePane = new JPanel(south_layout);
        if (southComponent != null) {
            southBasePane.add(southComponent);
        }
        animator_for_south.addActionListener(e -> southBasePane.revalidate());
        southShowHideBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!animator_for_south.isRunning()) {
                    south_layout.isHidden = southBasePane.getHeight() == 0;
                    showHideSouthLabel.setText(south_layout.isHidden ? "▼" : "▲");
                    animator_for_south.start();
                }
            }
        });
        southShowHideBar.setFocusable(false);
        outerBasePanel.add(southBasePane, BorderLayout.SOUTH);

        /* **** EAST **** */
        Timer animator_for_east = new Timer(5, null);
        ControlsBorderLayout_for_EastAndWest east_layout = new ControlsBorderLayout_for_EastAndWest(animator_for_east);
        // 初期状態を設定
        if (eastIsInitiallyColosed) {
            east_layout.isHidden = false;
            showHideEastLabel = createArrowLabel("◀");
        } else {
            east_layout.isHidden = true;
            showHideEastLabel = createArrowLabel("▶");
        }
        JPanel eastBasePane = new JPanel(east_layout);
        if (eastComponent != null) {
            eastBasePane.add(eastComponent);
        }
        animator_for_east.addActionListener(e -> eastBasePane.revalidate());
        eastShowHideBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!animator_for_east.isRunning()) {
                    east_layout.isHidden = eastBasePane.getWidth() == 0;
                    showHideEastLabel.setText(east_layout.isHidden ? "▶" : "◀");
                    animator_for_east.start();
                }
            }
        });
        eastShowHideBar.setFocusable(false);
        outerBasePanel.add(eastBasePane, BorderLayout.EAST);

        /* **** WEST **** */
        Timer animator_for_west = new Timer(5, null);
        ControlsBorderLayout_for_EastAndWest west_layout = new ControlsBorderLayout_for_EastAndWest(animator_for_west);
        // 初期状態を設定
        if (westIsInitiallyColosed) {
            west_layout.isHidden = false;
            showHideWestLabel = createArrowLabel("▶");
        } else {
            west_layout.isHidden = true;
            showHideWestLabel = createArrowLabel("◀");
        }
        JPanel westBasePane = new JPanel(west_layout);
        if (westComponent != null) {
            westBasePane.add(westComponent);
        }
        animator_for_west.addActionListener(e -> westBasePane.revalidate());
        westShowHideBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!animator_for_west.isRunning()) {
                    west_layout.isHidden = westBasePane.getWidth() == 0;
                    showHideWestLabel.setText(west_layout.isHidden ? "◀" : "▶");
                    animator_for_west.start();
                }
            }
        });
        westShowHideBar.setFocusable(false);
        outerBasePanel.add(westBasePane, BorderLayout.WEST);

        //中心部をスクロールパネルにしたい場合
        //outerBasePanel.add(new InnerBasePane(new JScrollPane(center_comp)), BorderLayout.CENTER);

        //中心部をスクロールパネルにしたくない場合
        outerBasePanel.add(new InnerBasePane(centerComponent), BorderLayout.CENTER);


        add(outerBasePanel);
    }


    class InnerBasePane extends JPanel {
        public InnerBasePane(JComponent comp) {
            this.setLayout(new BorderLayout());

            CollapsiblePanel.this.northShowHideBar.setBackground(Color.LIGHT_GRAY);
            CollapsiblePanel.this.northShowHideBar.setOpaque(true);
            CollapsiblePanel.this.northShowHideBar.add(Box.createHorizontalGlue());
            CollapsiblePanel.this.northShowHideBar.add(CollapsiblePanel.this.showHideNorthLabel);
            CollapsiblePanel.this.northShowHideBar.add(Box.createHorizontalGlue());
            if (northComponent != null) {
                add(CollapsiblePanel.this.northShowHideBar, BorderLayout.NORTH);
            }

            CollapsiblePanel.this.southShowHideBar.setBackground(Color.LIGHT_GRAY);
            CollapsiblePanel.this.southShowHideBar.setOpaque(true);
            CollapsiblePanel.this.southShowHideBar.add(Box.createHorizontalGlue());
            CollapsiblePanel.this.southShowHideBar.add(CollapsiblePanel.this.showHideSouthLabel);
            CollapsiblePanel.this.southShowHideBar.add(Box.createHorizontalGlue());
            if (southComponent != null) {
                add(CollapsiblePanel.this.southShowHideBar, BorderLayout.SOUTH);
            }

            CollapsiblePanel.this.eastShowHideBar.setBackground(Color.LIGHT_GRAY);
            CollapsiblePanel.this.eastShowHideBar.setOpaque(true);
            CollapsiblePanel.this.eastShowHideBar.add(Box.createVerticalGlue());
            CollapsiblePanel.this.eastShowHideBar.add(CollapsiblePanel.this.showHideEastLabel);
            CollapsiblePanel.this.eastShowHideBar.add(Box.createVerticalGlue());
            if (eastComponent != null) {
                add(CollapsiblePanel.this.eastShowHideBar, BorderLayout.EAST);
            }

            CollapsiblePanel.this.westShowHideBar.setBackground(Color.LIGHT_GRAY);
            CollapsiblePanel.this.westShowHideBar.setOpaque(true);
            CollapsiblePanel.this.westShowHideBar.add(Box.createVerticalGlue());
            CollapsiblePanel.this.westShowHideBar.add(CollapsiblePanel.this.showHideWestLabel);
            CollapsiblePanel.this.westShowHideBar.add(Box.createVerticalGlue());
            if (westComponent != null) {
                add(CollapsiblePanel.this.westShowHideBar, BorderLayout.WEST);
            }


            add(comp, BorderLayout.CENTER);
        }
    }


    class ControlsBorderLayout_for_NorthAndSouth extends BorderLayout {
        public boolean isHidden = true;
        private final Timer animator;
        private int controlsHeight;
        private int expandedHeight;

        public ControlsBorderLayout_for_NorthAndSouth(Timer animator) {
            super(5, 5);
            this.animator = animator;
        }

        @Override
        public Dimension preferredLayoutSize(Container target) {
            // synchronized (target.getTreeLock()) {
            Dimension ps = super.preferredLayoutSize(target);
            expandedHeight = Math.max(expandedHeight, ps.height);
            int defaultHeight = expandedHeight;

            if (animator.isRunning()) {
                if (isHidden) {
                    controlsHeight = Math.min(defaultHeight, controlsHeight + 20);
                    if (controlsHeight >= defaultHeight) {
                        animator.stop();
                    }
                } else {
                    controlsHeight = Math.max(0, controlsHeight - 20);
                    if (controlsHeight <= 0) {
                        animator.stop();
                    }
                }
            } else {
                controlsHeight = isHidden ? defaultHeight : 0;
            }

            ps.height = controlsHeight;
            return ps;
        }
    }


    class ControlsBorderLayout_for_EastAndWest extends BorderLayout {
        public boolean isHidden = true;
        private final Timer animator;
        private int controlsWidth;
        private int expandedWidth;

        public ControlsBorderLayout_for_EastAndWest(Timer animator) {
            super(5, 5);
            this.animator = animator;
        }

        @Override
        public Dimension preferredLayoutSize(Container target) {
            // synchronized (target.getTreeLock()) {
            Dimension ps = super.preferredLayoutSize(target);
            expandedWidth = Math.max(expandedWidth, ps.width);
            int defaultWidth = expandedWidth;

            if (animator.isRunning()) {
                if (isHidden) {
                    controlsWidth = Math.min(defaultWidth, controlsWidth + 20);
                    if (controlsWidth >= defaultWidth) {
                        animator.stop();
                    }
                } else {
                    controlsWidth = Math.max(0, controlsWidth - 20);
                    if (controlsWidth <= 0) {
                        animator.stop();
                    }
                }
            } else {
                controlsWidth = isHidden ? defaultWidth : 0;
            }

            ps.width = controlsWidth;
            return ps;
        }
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
        JTextArea textArea = new JTextArea("CENTER");
        textArea.setToolTipText("This is a tooltip text");
        center_pane.add(new JScrollPane(textArea));

        JPanel south_pane = new JPanel();
        south_pane.add(new JLabel("SOUTH"));

        JPanel north_pane = new JPanel();
        north_pane.add(new JLabel("NORTH"));

        frame.getContentPane().add(new CollapsiblePanel(center_pane,
                east_pane, true,
                //west_pane, true,
                null, true,
                south_pane, false,
                north_pane, true));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
