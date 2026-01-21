package iu.SwingStyle.LCCA.Member.Concretes.componentholder.DEQAResult.Common;

import iu.SwingStyle.LCCA.Utils.ColorChangeableTextArea;
import iu.SwingStyle.LCCA.Utils.ColorChangeableTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class NotePane extends JPanel {

    private static final String AUTHOR_YEAR_FOLDER_LOCATION = "data";
    private static final String MATERIALS_FOLDER_NAME = "materials";
    private static final String MATERIALS_BUTTON_LABEL = MATERIALS_FOLDER_NAME + "/";

    final String sectionName;
    final String subSectionName;
    final String tabName;

    JTabbedPane parentTabbedPanel;

    ColorChangeableTextField tFiled_Status = new ColorChangeableTextField("");
    final String tooltipForStatusFiled = "先頭文字がタブに表示される。タブ視認性向上のために使う。";

    JButton button_OpenPdf = new JButton("PDF");
    JButton openMaterialsFolderButton = new JButton(MATERIALS_BUTTON_LABEL);

    ColorChangeableTextArea tArea_Note = new ColorChangeableTextArea("");
    final String tooltipForNoteArea = "自由記載ノート。";

    JTextArea tArea_Explanation = new JTextArea("");
    final String tooltipForExplanation = "項目説明";

    ManagerOfSubTabBasePane managerOfSubTabBasePane;

    public NotePane(String tabName, String sectionName, String subSectionName, JTabbedPane parentTabbedPanel, ManagerOfSubTabBasePane managerOfSubTabBasePane) {

        this.setBackground(Color.YELLOW);  //For debug

        // Initialization
        this.tabName = tabName;
        this.sectionName = sectionName;
        this.subSectionName = subSectionName;
        this.parentTabbedPanel = parentTabbedPanel;
        this.managerOfSubTabBasePane = managerOfSubTabBasePane;

        this.setLayout(new BorderLayout());

        /* **** NORTH AREA **** */
        JPanel baseOfNorth = new JPanel();
        //baseOfNorth.setBackground(Color.YELLOW);  //For debug
        baseOfNorth.setLayout(new BoxLayout(baseOfNorth, BoxLayout.X_AXIS));

        /* *** LOWER of NORTH AREA *** */
        // Explanation Area
        JScrollPane scrollPaneA = new JScrollPane(tArea_Explanation);
        tArea_Explanation.setLineWrap(true);
        tArea_Explanation.setEditable(false);
        tArea_Explanation.setBackground(Color.LIGHT_GRAY);
        scrollPaneA.setPreferredSize(new Dimension(600, 230));
        baseOfNorth.add(scrollPaneA);

        /* *** RIGHT of NORTH AREA *** */
        Box rightOfNorthArea = Box.createVerticalBox();

        /* ** UPPER of RIGHT NORTH AREA ** */
        Box upperOfRightNorthArea = Box.createHorizontalBox();
        // Status TextField
        //tFiled_Status.setMaximumSize(new Dimension(600, 30));
        tFiled_Status.setPreferredSize(new Dimension(150, 30));
        upperOfRightNorthArea.add(tFiled_Status);
        // PDF Button and Materials Button
        upperOfRightNorthArea.add(button_OpenPdf);
        button_OpenPdf.setMaximumSize(new Dimension(50, 30));
        upperOfRightNorthArea.add(openMaterialsFolderButton);
        openMaterialsFolderButton.setMaximumSize(new Dimension(50, 30));
        rightOfNorthArea.add(upperOfRightNorthArea);

        /* ** LOWER of RIGHT NORTH AREA ** */
        // Note Text Area
        JScrollPane scrollPaneB = new JScrollPane(tArea_Note);
        scrollPaneB.setPreferredSize(new Dimension(200, 200));
        //scrollPaneB.setMaximumSize(new Dimension(200, 200));
        rightOfNorthArea.add(scrollPaneB);

        rightOfNorthArea.setMaximumSize(new Dimension(200, 200));
        baseOfNorth.add(rightOfNorthArea);


        /* **** FINALIZATION **** */
        this.setLayout(new BorderLayout());
        this.add(baseOfNorth, BorderLayout.CENTER);
        //this.setMaximumSize(new Dimension(800, 230));
        this.setPreferredSize(new Dimension(800, 230));

        tArea_Explanation.setToolTipText(tooltipForExplanation);
        tArea_Note.setToolTipText(tooltipForNoteArea);
        tArea_Note.setWrapStyleWord(true);
        tFiled_Status.setToolTipText(tooltipForStatusFiled);

        /* ** openPdfButton と openMaterialsFolderButton のセットアップ ** */
        setupButton(button_OpenPdf, "/icons/pdf.png", "pdf", "メインPDFを開く");
        button_OpenPdf.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPdf();
            }
        });
        setupButton(openMaterialsFolderButton, "/icons/folder_gray.png", MATERIALS_BUTTON_LABEL, "materialsフォルダを開く");
        openMaterialsFolderButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMaterialsFolder();
            }
        });


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


    public void updateTabTitle() {
        String text = tFiled_Status.getText();
        // この JTextField が含まれているタブインデックスを調べる
        Component tabComponent = NotePane.this;
        while (tabComponent != null && tabComponent.getParent() != parentTabbedPanel) {
            tabComponent = tabComponent.getParent();
        }
        if (tabComponent != null) {
            int idx = parentTabbedPanel.indexOfComponent(tabComponent);
            if (idx != -1) {
                //String baseTitle = "タブ" + (idx + 1);
                String baseTitle = tabName;
                if (!text.isEmpty()) {
                    // 最大4文字まで表示（4文字未満の場合は存在する文字数だけ表示）
                    int displayLength = Math.min(4, text.length());
                    baseTitle += " - " + text.substring(0, displayLength);
                }
                //System.out.println(idx + " - " + baseTitle);
                parentTabbedPanel.setTitleAt(idx, baseTitle);
            }
        }
    }

    public String getStatusText() {
        return tFiled_Status.getText();
    }

    public String getNoteText() {
        return tArea_Note.getText();
    }

    public void setStatusText(String text) {
        tFiled_Status.setText(text);
    }

    public void setNoteText(String text) {
        tArea_Note.setText(text);
    }

    public void resetBackgroundColors() {
        tFiled_Status.resetBackgroundColor();
        tArea_Note.resetBackgroundColor();
    }

    public void updateDefaultValues() {
        tFiled_Status.updateDefaultValue();
        tArea_Note.updateDefaultValue();
    }

    public void openPdf() {
        String authorYear = managerOfSubTabBasePane.getAuthorYear();
        String currentWorkingDirectoryPathStr = System.getProperty("user.dir");

        // authorYearFolder 下にある authorYear+".pdf" という名前の（例えば、Bedford2025.pdf）PDFを検索して、最初に見つかったものを開こうとする
        Path authorYearFolderPath = Paths.get(currentWorkingDirectoryPathStr, AUTHOR_YEAR_FOLDER_LOCATION, authorYear);
        try {
            if (!authorYearFolderPath.toFile().exists()) {
                JOptionPane.showMessageDialog(
                        this,
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
                            this,
                            "このシステムではファイルを開く機能がサポートされていません。",
                            "エラー",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "PDFファイルが見つかりません: " + targetPdfName + " in " + authorYearFolderPath.toAbsolutePath(),
                        "エラー",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "ファイル検索中にエラーが発生しました: " + e.getMessage(),
                    "エラー",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "ファイルを開く際にエラーが発生しました: " + e.getMessage(),
                    "エラー",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    public void openMaterialsFolder() {
        String authorYear = managerOfSubTabBasePane.getAuthorYear();
        String currentWorkingDirectoryPathStr = System.getProperty("user.dir");
        Path materialsPath = Paths.get(currentWorkingDirectoryPathStr, AUTHOR_YEAR_FOLDER_LOCATION, authorYear, MATERIALS_FOLDER_NAME);

        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();

                if (materialsPath.toFile().exists()) {
                    desktop.open(materialsPath.toFile());
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Materialsフォルダが見つかりません: " + materialsPath.toAbsolutePath(),
                            "エラー",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "このシステムではフォルダを開く機能がサポートされていません。",
                        "エラー",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "フォルダを開く際にエラーが発生しました: " + e.getMessage(),
                    "エラー",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    public void setSubsetOfExplanation(String explanation) {
        this.tArea_Explanation.setText(explanation);
    }
}
