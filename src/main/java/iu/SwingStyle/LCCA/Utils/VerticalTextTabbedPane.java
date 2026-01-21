package iu.SwingStyle.LCCA.Utils;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class VerticalTextTabbedPane extends JTabbedPane {

    private static final int TAB_FONT_SIZE = 14;

    public VerticalTextTabbedPane(int tabPlacement) {
        super(tabPlacement);
        // フォントサイズを大きく設定
        setFont(getFont().deriveFont(Font.BOLD, TAB_FONT_SIZE));
        setUI(new VerticalTextTabbedPaneUI());
    }

    private static class VerticalTextTabbedPaneUI extends BasicTabbedPaneUI {

        // 選択時の文字色（濃い青）
        private static final Color SELECTED_TEXT_COLOR = new Color(0, 51, 153);
        // 非選択時の文字色（濃いグレー - ライト/ダーク両テーマで見やすい）
        private static final Color UNSELECTED_TEXT_COLOR = new Color(50, 50, 50);

        @Override
        protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics,
                                 int tabIndex, String title, Rectangle textRect, boolean isSelected) {

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

            // フォントを太字かつ大きめに
            Font largerFont = font.deriveFont(Font.BOLD, TAB_FONT_SIZE);
            FontMetrics largerMetrics = g2d.getFontMetrics(largerFont);

            if (tabPlacement == LEFT) {
                // テキストを90度回転（反時計回り）
                // タブの幅の中央にテキストを配置
                int centerX = textRect.x + textRect.width / 2;
                AffineTransform at = new AffineTransform();
                at.translate(centerX, textRect.y + textRect.height - 5);
                at.rotate(-Math.PI / 2);
                g2d.setTransform(at);
                g2d.setFont(largerFont);
                g2d.setColor(isSelected ? SELECTED_TEXT_COLOR : UNSELECTED_TEXT_COLOR);
                g2d.drawString(title, -largerMetrics.stringWidth(title) / 2, largerMetrics.getAscent() / 2);

                // 元に戻す
                g2d.setTransform(new AffineTransform());
            } else if (tabPlacement == RIGHT) {
                // テキストを90度回転（時計回り）
                // タブの幅の中央にテキストを配置
                int centerX = textRect.x + textRect.width / 2;
                AffineTransform at = new AffineTransform();
                at.translate(centerX, textRect.y + 5);
                at.rotate(Math.PI / 2);
                g2d.setTransform(at);
                g2d.setFont(largerFont);
                g2d.setColor(isSelected ? SELECTED_TEXT_COLOR : UNSELECTED_TEXT_COLOR);
                g2d.drawString(title, -largerMetrics.stringWidth(title) / 2, largerMetrics.getAscent() / 2);

                // 元に戻す
                g2d.setTransform(new AffineTransform());
            } else {
                super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect, isSelected);
            }
        }

        @Override
        protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
            if (tabPlacement == LEFT || tabPlacement == RIGHT) {
                // 回転時はテキストの幅分を高さとして使用（大きめフォント用に調整）
                String title = tabPane.getTitleAt(tabIndex);
                Font largerFont = tabPane.getFont().deriveFont(Font.BOLD, TAB_FONT_SIZE);
                FontMetrics metrics = tabPane.getFontMetrics(largerFont);
                return metrics.stringWidth(title) + 30;
            }
            return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight);
        }

        @Override
        protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
            if (tabPlacement == LEFT || tabPlacement == RIGHT) {
                // 回転時はテキストの高さ分を幅として使用（大きめフォント用に調整）
                return TAB_FONT_SIZE + 16;
            }
            return super.calculateTabWidth(tabPlacement, tabIndex, metrics);
        }
    }
}
