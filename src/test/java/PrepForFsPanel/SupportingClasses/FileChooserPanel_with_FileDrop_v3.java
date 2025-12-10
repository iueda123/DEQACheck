package PrepForFsPanel.SupportingClasses;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * OSネイティブではなく、Swing の ファイルチューザーを起動するタイプ。
 * ファイルフィルタが使える。
 *
 * 2018.12.17 アドレス領域が柔軟に広がるように改造した。
 * @author issey
 *
 * 2020.01.23 サンプルコードを修正した。
 */
public abstract class FileChooserPanel_with_FileDrop_v3 extends JPanel {

    private JButton LoadButton = new JButton("…");
    private JTextField_with_FileDrop TextField;

    public String DefaultPath;
    public String DialogTitle;

    public FileFilter SwingFileFilter; //ファイルフィルタ

    public int SelectionMode;
    public final static int SELECTION_MODE_FILES_AND_DIRECTORY = 0;
    public final static int SELECTION_MODE_FILES_ONLY = 1;
    public final static int SELECTION_MODE_DIRECTORIES_ONLY = 2;
    public final static int SELECTION_MODE_DIRECTORIES_ONLY_WITH_SHOWING_FILES = 3;

    public boolean canMultipleSelect;

    public int ShowMode; // any, open, save
    public final static int SHOW_MODE_SELECT = 0, SHOW_MODE_OPEN = 1, SHOW_MODE_SAVE = 2;

    public int ReturnValue;

    /**
     * Constructor:
     *
     * @param parentframe
     * @param defalut_path
     * @param dialog_title
     * @param file_filtere
     * @param selection_mode
     * @param can_multiple_select
     * @param show_mode
     */
    public FileChooserPanel_with_FileDrop_v3(final JFrame parentframe,
                                             String defalut_path, String dialog_title,
                                             FileFilter file_filtere,
                                             int selection_mode, boolean can_multiple_select, int show_mode) {
        //「LOAD」ダイアログ
        final JFrame ParentFrame = parentframe;

        //初期値
        this.DefaultPath = defalut_path;
        this.DialogTitle = dialog_title;
        this.SwingFileFilter = file_filtere;
        this.SelectionMode = selection_mode;
        this.canMultipleSelect = can_multiple_select;
        this.ShowMode = show_mode;

        //テキストボックス
        TextField = new JTextField_with_FileDrop();
        TextField.setText(defalut_path);

        //ボタン
        LoadButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        FileChooserPanel_with_FileDrop_v3.this.setEnabled(false);   //PropertyChangeListenerに通知するための機構

                        //初期値の確認
                        String open_path ="";
                        if (new File(TextField.getText()).exists()){
                            open_path = TextField.getText();
                        }else{
                            //無効なファイルパスなら親を辿って、存在するところを返す。なければユーザーホームを返す。
                            open_path = findExistingFileRecursively(new File(TextField.getText())).getAbsolutePath();
                        }

                        //ファイルチューザーの構築と表示
                        JFileChooser chooser = new JFileChooser(open_path);


                        //ファイルフィルタ設定
                        chooser.setFileFilter(SwingFileFilter);

                        //ファイル選択モード
                        switch (SelectionMode) {
                            case SELECTION_MODE_FILES_AND_DIRECTORY:
                                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                                break;
                            case SELECTION_MODE_FILES_ONLY:
                                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                                break;
                            case SELECTION_MODE_DIRECTORIES_ONLY:
                                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                                break;
                            case SELECTION_MODE_DIRECTORIES_ONLY_WITH_SHOWING_FILES:
                                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                                //chooser.setFileFilter(AlmightyFileFilterConstructor_Extension.getSwingFileFilter("", "フォルダのみ選択可"));
                                //chooser.setFileFilter(AlmightyFileFilterConstructor_Extension.getSwingFileFilter("", "フォルダのみ選択可"));
                                break;
                            default:
                                break;
                        }

                        //複数選択モード
                        chooser.setMultiSelectionEnabled(canMultipleSelect);

                        //ダイアログタイトル設定
                        chooser.setDialogTitle(DialogTitle);

                        //FileChooserの表示（３種類ある）
                        switch (ShowMode) {
                            case FileChooserPanel_with_FileDrop_v3.SHOW_MODE_SELECT:
                                //Do Something;
                                ReturnValue = chooser.showDialog(parentframe, "選択");//任意のボタン名
                                break;
                            case SHOW_MODE_OPEN:
                                ReturnValue = chooser.showOpenDialog(parentframe);//「開く」
                                break;
                            case FileChooserPanel_with_FileDrop_v3.SHOW_MODE_SAVE:
                                ReturnValue = chooser.showSaveDialog(parentframe);//「保存」「取り消し」
                                break;
                            default:
                                //Do Something;
                        }

                        //選択後に投げ込むところ
                        if (ReturnValue == JFileChooser.APPROVE_OPTION) {//開いた時または保存した時
                            File selected_target_file = new File(chooser.getSelectedFile().getAbsolutePath());

                            // ダブルクリックでフォルダを開いていった後に、選択等のボタンを押すと、フォルダ名が末尾に追加されるエラー回避のための処理
                            if(!selected_target_file.exists()){
                                selected_target_file = selected_target_file.getParentFile();
                            }

                            //末尾に「.」や「..」がある場合そのまま表示してしてしまうため、次の処理を噛ましておく
                            Path selected_target_path = Paths.get(selected_target_file.getAbsolutePath());
                            selected_target_file = selected_target_path.normalize().toFile();

                            if (SelectionMode == SELECTION_MODE_DIRECTORIES_ONLY_WITH_SHOWING_FILES) {
                               //「SELECTION_MODE_DIRECTORIES_ONLY_WITH_SHOWING_FILES」モードの場合
                                //実際的にはファイルを選択することも可能なため次の処理をかましておく。
                                if (selected_target_file.isFile()) {
                                    TextField.setText(selected_target_file.getParentFile().getAbsolutePath());
                                } else {
                                    TextField.setText(selected_target_file.getAbsolutePath());
                                }
                            } else {
                                TextField.setText(selected_target_file.getAbsolutePath());
                            }
                            performActionAfterSelectingFile();
                        } else if (ReturnValue == JFileChooser.CANCEL_OPTION) {//取り消した時

                        } else if (ReturnValue == JFileChooser.ERROR_OPTION) {//強制終了した時

                        }
                        FileChooserPanel_with_FileDrop_v3.this.setEnabled(true);   //PropertyChangeListenerに通知するための機構
                    }
                }
        );

        //レイアウト
        //サイズを規定せずBorderLayoutでのCenter領域が自在に伸縮することを利用
        this.setLayout(new BorderLayout());
        Box hBox1 = Box.createHorizontalBox();
        hBox1.add(TextField);
        Box hBox2 = Box.createHorizontalBox();

        //hBox2.add(Box.createVerticalStrut(5));
        hBox2.add(LoadButton);
        this.add(hBox1, BorderLayout.CENTER);
        this.add(hBox2, BorderLayout.EAST);

    }

    protected static File findExistingFileRecursively(File search_file){
        if(search_file == null){
            return new File(SystemPropertyManager.getUserHome());
        }else {
            if (search_file.exists()) {
                return search_file;
            } else {
                return findExistingFileRecursively(search_file.getParentFile());
            }
        }

    };

    protected abstract void performActionAfterSelectingFile();

    public String getText() {
        return TextField.getText();
    }

    /**
     * ファイルパス区切り文字で終わる形でパス文字列を取り出す
     */
    public String getTextWithSeparatorEnding(){
        String rslt_str = this.TextField.getText();
        if(!rslt_str.endsWith(SystemPropertyManager.getFileSeparator())){
            rslt_str = rslt_str + SystemPropertyManager.getFileSeparator();
        }
        return rslt_str;
    }

    public void setText(String text) {
        TextField.setText(text);
    }

    public void setColumns(int i){
        TextField.setColumns(i);
    }

    public static void main(String[] args) {

        final JFrame frame = new JFrame();


        FileChooserPanel_with_FileDrop_v3 file_chooser =
                new FileChooserPanel_with_FileDrop_v3(
                        frame, "./", "てすと",
                        null,
                        FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_DIRECTORIES_ONLY,
                        //PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_FILES_AND_DIRECTORY,
                        //PrepForFsPanel.SupportingClasses.FileChooserPanel_with_FileDrop_v3.SELECTION_MODE_DIRECTORIES_ONLY_WITH_SHOWING_FILES,
                        false,
                        FileChooserPanel_with_FileDrop_v3.SHOW_MODE_SELECT
                ){
                    @Override
                    protected void performActionAfterSelectingFile() {
                        System.out.println("Selection was changed.");
                    }
                };


        // Property Change Listener
        PropertyChangeListener pcl = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                System.out.println("Property has changed!");
                System.out.println(propertyChangeEvent.getSource().getClass());
                System.out.println(propertyChangeEvent.getOldValue() + "→" + propertyChangeEvent.getNewValue());
            }
        };

        file_chooser.addPropertyChangeListener(pcl);


        // RUN Button
        JButton run_button = new JButton("RUN");
        run_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println(file_chooser.getText());
            }
        });

        //配備
        Box hbox = Box.createHorizontalBox();
        hbox.add(file_chooser);
        hbox.add(run_button);

        frame.getContentPane().add(hbox);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.setPreferredSize(new Dimension(500, 50));
        frame.pack();
        frame.setVisible(true);

    }
}
