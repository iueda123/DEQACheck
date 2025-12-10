package Components;


import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.im.InputSubset;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

/**
 * AutoCompleteComboBoxは、入力に応じた候補を提示する編集可能なコンボボックスです。
 * キー入力により候補がフィルタリングされ、候補が自動的に追加される機能を提供します。
 *
 * <p>
 * キーバインディング:
 * <ul>
 *   <li>RIGHT: 自動補完</li>
 *   <li>ENTER: 候補追加および選択確定</li>
 *   <li>ESC: ポップアップ非表示</li>
 * </ul>
 * </p>
 *
 * 作成日: 2016/10/28
 */
public class AutoCompleteComboBox2 extends JComboBox<String> {

    // デバッグ用フラグ
    private static final boolean DEBUG = false;

    // 入力候補用リスト（動的に追加された候補）
    private Vector<String> suggestionList = new Vector<>();
    // 初期候補リスト（コンボボックス作成時の候補）
    private Vector<String> defaultList = new Vector<>();

    /**
     * 文字列の配列から候補を初期化するコンストラクタ
     *
     * @param items 候補となる文字列の配列
     */
    public AutoCompleteComboBox2(String[] items) {
        super(items);
        initialize();
    }

    /**
     * テキストファイルから候補を読み込むコンストラクタ
     *
     * @param itemsListFilePath 候補リストが記述されたテキストファイルのパス（1行1候補）
     */
    public AutoCompleteComboBox2(String itemsListFilePath) {
        super();
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) this.getModel();
        ArrayList<String> loadedWords = readListFromFile(new File(itemsListFilePath));
        if (loadedWords != null) {
            for (String word : loadedWords) {
                model.addElement(word);
            }
        }
        initialize();
    }

    /**
     * コンボボックスの初期設定を行います。
     * ・編集可能に設定、初期選択解除、テキストフィールドの初期化
     * ・候補提示用のキーハンドラとカスタムUIの設定
     * ・ツールチップの設定
     * ・初期候補リストの保持
     */
    private void initialize() {
        setEditable(true);
        setSelectedIndex(-1);
        JTextField textField = (JTextField) getEditor().getEditorComponent();
        textField.setText("");
        // キー入力による候補提示のためのリスナーを追加
        textField.addKeyListener(new AutoCompleteKeyHandler());
        // カスタムUIの設定
        setUI(new AutoCompleteComboBoxUI());
        // 使用方法ツールチップの設定
        setUsageTooltip();
        // コンボボックスに登録されている候補を初期候補リストとして保持
        for (int i = 0; i < getModel().getSize(); i++) {
            defaultList.add(getItemAt(i));
        }
    }

    /**
     * 現在入力されているテキストを取得します。
     *
     * @return 現在の入力テキスト
     */
    public String getText() {
        JTextField textField = (JTextField) getEditor().getEditorComponent();
        return textField.getText();
    }

    /**
     * コンボボックスに登録されている全候補を取得します。
     *
     * @return 候補リスト(ArrayList&lt;String&gt;)
     */
    public ArrayList<String> getWordList() {
        ArrayList<String> wordsList = new ArrayList<>();
        for (int i = 0; i < defaultList.size(); i++) {
            wordsList.add(defaultList.get(i));
        }
        return wordsList;
    }

    /**
     * 候補リストをテキストファイルに書き出します。
     *
     * @param saveFilePath 出力先ファイルのパス
     * @return 書き出し成功ならtrue、失敗ならfalse
     */
    public boolean exportWords(String saveFilePath) {
        return writeListToFile(getWordList(), new File(saveFilePath));
    }

    /**
     * 編集フィールドの背景色を変更します。
     *
     * @param color 新しい背景色
     */
    public void changeBackground(Color color) {
        JTextField textField = (JTextField) getEditor().getEditorComponent();
        textField.setBackground(color);
    }

    /**
     * 使用方法を示すツールチップを設定します。
     */
    private void setUsageTooltip() {
        JTextField textField = (JTextField) getEditor().getEditorComponent();
        String tooltipText = "Char: show popup / ESC: hide popup / RIGHT: completion / ENTER: add and select";
        textField.setToolTipText(tooltipText);
    }

    /**
     * 半角英数字モードに設定します。
     */
    private void setAsciiMode() {
        JTextField textField = (JTextField) getEditor().getEditorComponent();
        Character.Subset[] subsets = {InputSubset.LATIN};
        textField.getInputContext().setCompositionEnabled(false);
        textField.getInputContext().setCharacterSubsets(subsets);
    }

    /**
     * カタカナモードに設定します。
     */
    private void setKatakanaMode() {
        JTextField textField = (JTextField) getEditor().getEditorComponent();
        Character.Subset[] subsets = {Character.UnicodeBlock.KATAKANA};
        textField.getInputContext().setCompositionEnabled(true);
        textField.getInputContext().setCharacterSubsets(subsets);
    }

    /**
     * 漢字モードに設定します。
     */
    private void setKanjiMode() {
        JTextField textField = (JTextField) getEditor().getEditorComponent();
        Character.Subset[] subsets = {InputSubset.KANJI};
        textField.getInputContext().setCompositionEnabled(true);
        textField.getInputContext().setCharacterSubsets(subsets);
    }

    /**
     * 編集フィールドのフォント高さを取得します。
     *
     * @return フォントの高さ
     */
    public int getFontHeight() {
        JTextField textField = (JTextField) getEditor().getEditorComponent();
        FontMetrics fm = textField.getFontMetrics(textField.getFont());
        return fm.getHeight();
    }

    /**
     * 編集フィールドのフォント幅情報を取得します。
     *
     * @return フォントの幅の配列
     */
    public int[] getFontWidth() {
        JTextField textField = (JTextField) getEditor().getEditorComponent();
        FontMetrics fm = textField.getFontMetrics(textField.getFont());
        return fm.getWidths();
    }

    /**
     * AutoCompleteKeyHandlerは、キー入力に応じた自動補完・候補提示を実現するためのリスナーです。
     */
    class AutoCompleteKeyHandler extends KeyAdapter {
        private boolean shouldHide = false;
        private final JComboBox<String> comboBox = AutoCompleteComboBox2.this;

        @Override
        public void keyTyped(final KeyEvent e) {
            EventQueue.invokeLater(() -> {
                JTextField textField = (JTextField) e.getSource();
                int caretPosition = textField.getCaretPosition();
                String text = textField.getText();
                ComboBoxModel<String> suggestedModel = getSuggestedModel(suggestionList, text);
                // 入力にマッチする候補がない場合は初期候補に戻す
                if (suggestedModel.getSize() == 0 || shouldHide) {
                    setSuggestionModel(new DefaultComboBoxModel<>(defaultList), text);
                    comboBox.hidePopup();
                } else {
                    setSuggestionModel(suggestedModel, text);
                    comboBox.showPopup();
                }
                // キャレット位置を元に戻す（BackSpace/Delete時のズレ対策）
                textField.setCaretPosition(caretPosition);
            });
        }

        @Override
        public void keyPressed(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            String text = textField.getText();
            shouldHide = false;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    // 入力内容で始まる候補を自動補完
                    for (String s : suggestionList) {
                        if (s.startsWith(text)) {
                            textField.setText(s);
                            return;
                        }
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    // 新しい候補を追加
                    if (!suggestionList.contains(text)) {
                        suggestionList.add(text);
                        Collections.sort(suggestionList);
                        setSuggestionModel(getSuggestedModel(suggestionList, text), text);
                    }
                    if (!defaultList.contains(text)) {
                        defaultList.add(text);
                        Collections.sort(defaultList);
                    }
                    shouldHide = true;
                    break;
                case KeyEvent.VK_ESCAPE:
                    shouldHide = true;
                    break;
            }
        }

        /**
         * モデルを更新し、エディタにテキストを設定します。
         *
         * @param model 新しい候補モデル
         * @param text  表示するテキスト
         */
        private void setSuggestionModel(ComboBoxModel<String> model, String text) {
            comboBox.setModel(model);
            comboBox.setSelectedIndex(-1);
            ((JTextField) comboBox.getEditor().getEditorComponent()).setText(text);
        }

        /**
         * 入力テキストにマッチする候補のみを含むモデルを返します。
         *
         * @param list 候補全体のリスト
         * @param text 入力されたテキスト
         * @return フィルタリングされた候補モデル
         */
        private ComboBoxModel<String> getSuggestedModel(Vector<String> list, String text) {
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            if (text != null && !text.isEmpty()) {
                for (String s : list) {
                    if (s.startsWith(text)) {
                        model.addElement(s);
                    }
                }
            }
            return model;
        }
    }

    /**
     * AutoCompleteComboBoxUIは、カスタムUIとしてエディタの初期化と候補リストの設定を行います。
     */
    class AutoCompleteComboBoxUI extends BasicComboBoxUI {
        private KeyListener editorKeyListener = null;

        @Override
        protected void configureEditor() {
            super.configureEditor();
            JTextField textField = (JTextField) editor;
            // 初期選択解除
            AutoCompleteComboBox2.this.setSelectedIndex(-1);
            if (editorKeyListener == null) {
                editorKeyListener = new AutoCompleteKeyHandler();
            }
            editor.addKeyListener(editorKeyListener);
            // コンボボックスの現在のアイテムから候補リストを初期化
            for (int i = 0; i < AutoCompleteComboBox2.this.getModel().getSize(); i++) {
                suggestionList.add(AutoCompleteComboBox2.this.getItemAt(i));
            }
            textField.setText("");
        }

        @Override
        protected void unconfigureEditor() {
            super.unconfigureEditor();
            if (editorKeyListener != null) {
                editor.removeKeyListener(editorKeyListener);
            }
        }
    }

    /**
     * テキストファイルから各行を読み込み、単語リストとして返します。
     *
     * @param file 入力テキストファイル
     * @return ファイル内の各行を要素とする ArrayList、読み込み失敗時は null
     */
    public static ArrayList<String> readListFromFile(File file) {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (FileNotFoundException ex) {
            if (DEBUG) ex.printStackTrace();
            return null;
        } catch (IOException ex) {
            if (DEBUG) ex.printStackTrace();
            return null;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                if (DEBUG) ex.printStackTrace();
                return null;
            }
        }
        return list;
    }

    /**
     * 単語リストをテキストファイルへ書き出します。
     *
     * @param list 単語リスト
     * @param file 出力先のテキストファイル
     * @return 書き出し成功ならtrue、失敗ならfalse
     */
    public static boolean writeListToFile(ArrayList<String> list, File file) {
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            for (String s : list) {
                bw.write(s);
                bw.newLine();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                return false;
            }
        }
        System.out.println(file.getAbsolutePath() + " へ書き出しました。");
        return true;
    }

    // ----------------------------------------------------------------
    // Main method - エントリーポイント
    // ----------------------------------------------------------------

    /**
     * GUIを生成し、AutoCompleteComboBoxの動作を確認するためのサンプルアプリケーションです。
     */
    public static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("AutoCompleteComboBox Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // サンプルの単語リスト
        String[] words = {"aaaa", "aaaabbb", "aaaabbbcc", "aaaabbbccddd", "abcde", "abefg", "bbb1", "bbb12"};
        // ファイルから候補を読み込む場合は、new AutoCompleteComboBox("src/GuiParts/test_candidate.txt");
        final AutoCompleteComboBox2 comboBox = new AutoCompleteComboBox2(words);
        panel.add(comboBox);

        // 単語リストをファイルに書き出すボタン
        JButton exportButton = new JButton("Export Words");
        exportButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox.exportWords("save_folder/word_list.txt");
            }
        });
        panel.add(exportButton);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * メインエントリーポイント。
     *
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> createAndShowGUI());
    }
}
