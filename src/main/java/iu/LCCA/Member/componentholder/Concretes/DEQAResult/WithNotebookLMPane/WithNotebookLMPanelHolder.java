package iu.LCCA.Member.componentholder.Concretes.DEQAResult.WithNotebookLMPane;


import iu.LCCA.Mediator.action.ActionMediator;
import iu.LCCA.Mediator.componentholder.CHolderMediator;
import iu.LCCA.Member.componentholder.Abstract.AbstCHolderMember;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.Common.NotePane;
import iu.LCCA.Member.componentholder.Concretes.DEQAResult.WithNotebookLMPane.A_QandA_Panel.A_QandA_Panel;
import iu.LCCA.Utils.ColorChangeableTextField;
import iu.LCCA.Utils.FontManager;
import iu.LCCA.Utils.JsonManagerWithConflictSafe.JsonManagerCallback;
import iu.LCCA.Utils.JsonManagerWithConflictSafe.JsonManagerWithConflictSafe;

import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class WithNotebookLMPanelHolder extends AbstCHolderMember implements JsonManagerCallback {

    private String authorYear = "";
    private String noteJsonFilePathStr = "";
    private JsonManagerWithConflictSafe jsonManager = null;

    JPanel basePanel = new JPanel();

    JLabel label_Information = new JLabel("");

    JLabel authorYearLabel = new JLabel();

    JLabel label_NoteJsonFilePath = new JLabel();

    ColorChangeableTextField tfield_NotebookLM_Url = new ColorChangeableTextField("https://notebooklm.google.com/");
    JButton button_SaveNotebookLmUrl = new JButton("save url");
    JButton button_OpenNotebookLm = new JButton("open url");

    JButton button_OpenPdf = new JButton("PDF");
    JButton button_OpenMaterialsFolder = new JButton("materials/");

    JButton button_Save_QandA = new JButton("save Q&A");
    JButton button_Load_QandA = new JButton("load Q&A");
    JButton button_Add_A_QandA_Panel = new JButton("add Q&A");

    Box box_Base_for_QandA_Panels = Box.createVerticalBox();
    ArrayList<A_QandA_Panel> list_of_A_QandA_Panel = new ArrayList<>();


    public WithNotebookLMPanelHolder(String cholder_name, String short_name, String... args) {
        super(cholder_name, short_name);

        if (args.length > 0) {
            //System.out.println(args[0]);
            //sampleButton.setText("これは第１引数です → " + args[0]);
            authorYear = args[0];
            noteJsonFilePathStr = authorYear + "/notes/WithNotebookLM.json";
        }

        this.jsonManager = new JsonManagerWithConflictSafe(noteJsonFilePathStr, this);

    }

    @Override
    public void initialize() {

        button_SaveNotebookLmUrl.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!noteJsonFilePathStr.equals("")) {
                    jsonManager.setValue("notebook-lm-url", tfield_NotebookLM_Url.getText());
                    jsonManager.writeJson();
                } else {
                    System.err.println("noteJsonFilePathStr is empty.");
                }

                tfield_NotebookLM_Url.updateDefaultValue();
                tfield_NotebookLM_Url.resetBackgroundColor();

            }
        });

        button_OpenNotebookLm.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String notebook_lm_url = tfield_NotebookLM_Url.getText();

                if (notebook_lm_url != null && !notebook_lm_url.isEmpty()) {
                    openUrlInBrowser(notebook_lm_url);
                } else {
                    showTemporaryMessage("URLが入力されていません", Color.RED);
                }
            }
        });


        /* ** openPdfButton と openMaterialsFolderButton のセットアップ ** */
        setupButton(button_OpenPdf, "/icons/pdf.png", "pdf", "メインPDFを開く");
        button_OpenPdf.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPdf();
            }
        });
        setupButton(button_OpenMaterialsFolder, "/icons/folder_gray.png", "materials/", "materialsフォルダを開く");
        button_OpenMaterialsFolder.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMaterialsFolder();
            }
        });

        button_Save_QandA.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save_QandA_to_JsonFile();
            }
        });

        button_Load_QandA.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load_QandA_from_JsonFile();
            }
        });

        button_Add_A_QandA_Panel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add_One_QandA_Panel("unknown");
            }
        });


        basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.Y_AXIS));

        /* **** North Area **** */
        Box box_north = Box.createVerticalBox();

        // Information
        Box box_for_Information = Box.createHorizontalBox();
        box_for_Information.add(Box.createGlue());
        label_Information.setHorizontalAlignment(JLabel.CENTER);
        box_for_Information.add(label_Information);
        box_for_Information.add(Box.createGlue());
        box_north.add(box_for_Information);

        // NotebookLM URL Area
        JPanel subapane_for_NotebookLMAddress = new JPanel();
        subapane_for_NotebookLMAddress.setLayout(new BorderLayout());
        subapane_for_NotebookLMAddress.add(tfield_NotebookLM_Url, BorderLayout.CENTER);
        Box box_buttons = Box.createHorizontalBox();
        box_buttons.add(button_SaveNotebookLmUrl);
        box_buttons.add(button_OpenNotebookLm);
        box_buttons.add(button_OpenPdf);
        box_buttons.add(button_OpenMaterialsFolder);
        subapane_for_NotebookLMAddress.add(box_buttons, BorderLayout.EAST);
        subapane_for_NotebookLMAddress.setMaximumSize(new Dimension(800, 50));
        box_north.add(subapane_for_NotebookLMAddress);

        /* **** JSON LOAD and SAVE Area **** */
        Box box_QandA_Panel_Controller = Box.createHorizontalBox();
        label_NoteJsonFilePath.setText(noteJsonFilePathStr);
        label_NoteJsonFilePath.setHorizontalAlignment(JLabel.CENTER);
        box_QandA_Panel_Controller.add(label_NoteJsonFilePath);
        box_QandA_Panel_Controller.add(Box.createHorizontalStrut(10));
        //authorYearLabel.setText(authorYear);
        //basePanel.add(authorYearLabel);
        box_QandA_Panel_Controller.add(button_Load_QandA);
        box_QandA_Panel_Controller.add(button_Save_QandA);
        box_QandA_Panel_Controller.add(Box.createHorizontalStrut(10));
        box_QandA_Panel_Controller.add(button_Add_A_QandA_Panel);
        //box_QandA_Panel_Controller.setMaximumSize(new Dimension(600, 50));
        box_north.add(box_QandA_Panel_Controller);

        // Finalize
        basePanel.add(box_north, BorderLayout.NORTH);
        //Q and A area
    }


    @Override
    public void postInitialize() {

        load_QandA_from_JsonFile();

        JScrollPane scrollPane = new JScrollPane(box_Base_for_QandA_Panels);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        basePanel.add(scrollPane, BorderLayout.SOUTH);

        basePanel.validate();
        basePanel.repaint();

    }


    private void load_QandA_from_JsonFile() {

        tfield_NotebookLM_Url.setText(jsonManager.getValueAsString("notebook-lm-url"));
        tfield_NotebookLM_Url.updateDefaultValue();
        tfield_NotebookLM_Url.resetBackgroundColor();

        // 既存のパネルリストをクリア
        list_of_A_QandA_Panel.clear();

        JsonObject qAndAArray = jsonManager.getJsonObject().getAsJsonObject("q_and_a_array");
        if (qAndAArray != null) {
            for (String id : qAndAArray.keySet()) {
                String question_str = jsonManager.getValueAsString("q_and_a_array/" + id + "/q");
                String answer_str = jsonManager.getValueAsString("q_and_a_array/" + id + "/a");
                list_of_A_QandA_Panel.add(new A_QandA_Panel(id, question_str, answer_str, this));
            }
        }

        //QandA_Panelsを再描画
        rebuild_QandA_Panels();
    }


    private void save_QandA_to_JsonFile() {
        // 既存の q_and_a_array を削除してから保存する
        JsonObject jsonObject = jsonManager.getJsonObject();
        jsonObject.remove("q_and_a_array");

        for (int i = 0; i < list_of_A_QandA_Panel.size(); i++) {

            A_QandA_Panel a_qandA_panel = list_of_A_QandA_Panel.get(i);

            jsonManager.setValue("q_and_a_array/" + a_qandA_panel.ID + "/q", a_qandA_panel.getQuestion());
            jsonManager.setValue("q_and_a_array/" + a_qandA_panel.ID + "/a", a_qandA_panel.getAnswer());
        }
        jsonManager.writeJson();

        for (A_QandA_Panel a_qandA_panel : list_of_A_QandA_Panel) {
            a_qandA_panel.updateDefaultValuesAndResetBackgroundColors();
        }

    }

    public void moveUpPanel(String id) {
        for (int i = 0; i < list_of_A_QandA_Panel.size(); i++) {
            if (list_of_A_QandA_Panel.get(i).ID.equals(id)) {
                if (i > 0) {
                    A_QandA_Panel panel = list_of_A_QandA_Panel.remove(i);
                    list_of_A_QandA_Panel.add(i - 1, panel);
                    rebuild_QandA_Panels();
                }
                break;
            }
        }
    }

    public void moveDewnPanel(String id) {
        for (int i = 0; i < list_of_A_QandA_Panel.size(); i++) {
            if (list_of_A_QandA_Panel.get(i).ID.equals(id)) {
                if (i < list_of_A_QandA_Panel.size() - 1) {
                    A_QandA_Panel panel = list_of_A_QandA_Panel.remove(i);
                    list_of_A_QandA_Panel.add(i + 1, panel);
                    rebuild_QandA_Panels();
                }
                break;
            }
        }
    }



    public void add_One_QandA_Panel(String ID) {

        int the_index_for_new_panel = list_of_A_QandA_Panel.size();
        for (int i = 0; i < list_of_A_QandA_Panel.size(); i++) {
            if (list_of_A_QandA_Panel.get(i).ID.equals(ID)) {
                the_index_for_new_panel = i + 1;
                break;
            }
        }

        String new_id = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss"));

        A_QandA_Panel new_QandA_Panel = new A_QandA_Panel(new_id, "", "", this);

        list_of_A_QandA_Panel.add(the_index_for_new_panel, new_QandA_Panel);

        rebuild_QandA_Panels();


    }

    public void remove_One_QandA_Panel(String target_ID) {

        int result = JOptionPane.showConfirmDialog(
                basePanel,
                "ID: " + target_ID + " のQ&Aパネルを削除しますか？",
                "削除の確認",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (result != JOptionPane.YES_OPTION) {
            return;
        }

        for (A_QandA_Panel a_QandA_panel : list_of_A_QandA_Panel) {
            //System.out.println("Now checking : " + a_QandA_panel.ID);
            if (a_QandA_panel.ID.equals(target_ID)) {
                list_of_A_QandA_Panel.remove(a_QandA_panel);
                break;
            }
        }
        rebuild_QandA_Panels();

        save_QandA_to_JsonFile();
    }

    private void rebuild_QandA_Panels() {
        //QandA_Panelsを再描画
        box_Base_for_QandA_Panels.removeAll();
        for (int i = 0; i < list_of_A_QandA_Panel.size(); i++) {
            //System.out.println("postInitialize of" + this.getClass().getName());
            box_Base_for_QandA_Panels.add(list_of_A_QandA_Panel.get(i));
        }
        basePanel.revalidate();
        basePanel.repaint();
    }

    @Override
    public void doWorkAsMember() {
    }


    @Override
    public JComponent getBaseComponent() {
        return this.basePanel;
    }

    @Override
    public void setCHolderMediator(CHolderMediator cHolderMediator) {
        this.cholderMediator = cHolderMediator;
    }

    @Override
    public void setActionMediator(ActionMediator actionMediator) {
        this.actionMediator = actionMediator;
    }


    @Override
    public Component getFrame() {
        return null;
    }

    @Override
    public void actionAfterSuccessfullyOpeningJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        showTemporaryMessage("JSONファイルを正常に開きました: " + jsonManagerWithConflictSafe.getJsonFile().getName(), Color.GREEN);
    }

    @Override
    public void actionAfterFailingToOpenJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        showTemporaryMessage("JSONファイルを開けませんでした", Color.RED);
    }

    @Override
    public void actionAfterSuccessfullySavingJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        showTemporaryMessage("JSONファイルを正常に保存しました: " + jsonManagerWithConflictSafe.getJsonFile().getName(), Color.GREEN);
    }

    @Override
    public void actionAfterFailingToSaveJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        showTemporaryMessage("JSONファイルの保存に失敗しました", Color.RED);
    }

    @Override
    public void actionAfterSuccessfullyReloadingJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        showTemporaryMessage("JSONファイルを正常に再読み込みしました: " + jsonManagerWithConflictSafe.getJsonFile().getName(), Color.GREEN);
    }

    @Override
    public void actionAfterFailingToReloadJson(JsonManagerWithConflictSafe jsonManagerWithConflictSafe) {
        showTemporaryMessage("JSONファイルの再読み込みに失敗しました", Color.RED);
    }

    private void showTemporaryMessage(String message, Color color) {
        label_Information.setText(message);
        label_Information.setForeground(color);

        Timer timer = new Timer(10000, e -> {
            label_Information.setText("　");
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void openUrlInBrowser(String url) {
        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("linux")) {
                // Linux: xdg-open を使用
                new ProcessBuilder("xdg-open", url).start();
            } else if (os.contains("mac")) {
                // macOS: open コマンドを使用
                new ProcessBuilder("open", url).start();
            } else if (os.contains("win")) {
                // Windows: Desktop API を使用
                Desktop.getDesktop().browse(new java.net.URI(url));
            } else {
                // その他: Desktop API を試す
                Desktop.getDesktop().browse(new java.net.URI(url));
            }
        } catch (Exception ex) {
            showTemporaryMessage("URLを開けませんでした: " + ex.getMessage(), Color.RED);
        }
    }

    public void openPdf() {
        String currentWorkingDirectoryPathStr = System.getProperty("user.dir");

        // authorYearFolder 下にある authorYear+".pdf" という名前の（例えば、Bedford2025.pdf）PDFを検索して、最初に見つかったものを開こうとする
        Path authorYearFolderPath = Paths.get(currentWorkingDirectoryPathStr, authorYear);
        try {
            if (!authorYearFolderPath.toFile().exists()) {
                JOptionPane.showMessageDialog(
                        basePanel,
                        "フォルダが見つかりません: " + authorYearFolderPath.toAbsolutePath(),
                        "エラー",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // authorYearFolder 配下を再帰的に検索して、authorYear+".pdf" を探す
            String targetPdfName = authorYear + ".pdf";
            Optional<Path> foundPdfPath;

            try (Stream<Path> pathStream = Files.walk(authorYearFolderPath)) {
                foundPdfPath = pathStream
                        .filter(Files::isRegularFile)
                        .filter(p -> p.getFileName().toString().equals(targetPdfName))
                        .findFirst();
            }

            if (foundPdfPath.isPresent()) {
                Path pdfPath = foundPdfPath.get();
                System.out.println("Found PDF: " + pdfPath.toAbsolutePath());

                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(pdfPath.toFile());
                } else {
                    JOptionPane.showMessageDialog(
                            basePanel,
                            "このシステムではファイルを開く機能がサポートされていません。",
                            "エラー",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(
                        basePanel,
                        "PDFファイルが見つかりません: " + targetPdfName + " in " + authorYearFolderPath.toAbsolutePath(),
                        "エラー",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    basePanel,
                    "ファイル検索中にエラーが発生しました: " + e.getMessage(),
                    "エラー",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    basePanel,
                    "ファイルを開く際にエラーが発生しました: " + e.getMessage(),
                    "エラー",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    public void openMaterialsFolder() {
        String currentWorkingDirectoryPathStr = System.getProperty("user.dir");

        Path materialsPath = Paths.get(currentWorkingDirectoryPathStr, authorYear, "/materials/");

        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();

                if (materialsPath.toFile().exists()) {
                    desktop.open(materialsPath.toFile());
                } else {
                    JOptionPane.showMessageDialog(
                            basePanel,
                            "Materialsフォルダが見つかりません: " + materialsPath.toAbsolutePath(),
                            "エラー",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(
                        basePanel,
                        "このシステムではフォルダを開く機能がサポートされていません。",
                        "エラー",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    basePanel,
                    "フォルダを開く際にエラーが発生しました: " + e.getMessage(),
                    "エラー",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    /**
     * @param icon_location   : /icons/folder_icon.png などを渡す
     * @param alternative_str
     * @param tooltip
     */
    protected void setupButton(JButton setupTargetButton, String icon_location, String alternative_str, String tooltip) {
        URL icon_url = NotePane.class.getResource(icon_location);
        if (icon_url == null) {
            System.err.println("JSONアイコンが見つかりません。パスを確認してください: " + icon_url);
            setupTargetButton.setText(alternative_str);
        } else {
            setupTargetButton.setText("");
            setupTargetButton.setIcon(new ImageIcon(icon_url));
        }
        setupTargetButton.setToolTipText(tooltip);
    }

    private static void createAndShowGUI() {
        FontManager.setGlobalFont();

        JFrame frame = new JFrame("Member Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AbstCHolderMember holder = new WithNotebookLMPanelHolder("button_pane", "Button Panel", "Fang2025");
        holder.initialize(); // Build the component before adding to frame

        frame.getContentPane().add(holder.getBaseComponent(), BorderLayout.CENTER);
        frame.pack();
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        holder.postInitialize(); // Run post initialization after the component becomes visible
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }


}
