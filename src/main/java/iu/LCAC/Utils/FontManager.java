package iu.LCAC.Utils;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

public class FontManager {

    /**
     * アプリケーション全体のデフォルトフォントを設定する
     */
    public static void setGlobalFont() {
        // 日本語・英語対応で読みやすいフォントを優先順位で試行
        String[] preferredFonts = {
                "Noto Sans CJK JP",      // Linux (Google Noto fonts)
                "IPAexGothic",           // Linux (IPA fonts)
                "Meiryo",                // Windows
                "Yu Gothic UI",          // Windows 10+
                "Hiragino Sans",         // macOS
                "Hiragino Kaku Gothic ProN", // macOS (older)
                "SansSerif"              // フォールバック
        };

        Font selectedFont = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] availableFonts = ge.getAvailableFontFamilyNames();

        // 利用可能なフォントから優先順位の高いものを選択
        for (String fontName : preferredFonts) {
            for (String available : availableFonts) {
                if (available.equals(fontName)) {
                    selectedFont = new Font(fontName, Font.PLAIN, 13);
                    break;
                }
            }
            if (selectedFont != null) break;
        }

        // フォールバック
        if (selectedFont == null) {
            selectedFont = new Font(Font.SANS_SERIF, Font.PLAIN, 13);
        }

        // 全UIコンポーネントにフォントを適用
        setUIFont(new FontUIResource(selectedFont));

        // 1.25倍
        //setUIFont(new FontUIResource("SansSerif", Font.PLAIN, 15)); // 標準12pt → 15pt (1.25倍)
    }

    /**
     * UIManager経由で全コンポーネントのフォントを設定
     */
    private static void setUIFont(FontUIResource font) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, font);
            }
        }
    }


}
