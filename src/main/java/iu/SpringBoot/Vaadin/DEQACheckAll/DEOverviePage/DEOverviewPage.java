package iu.SpringBoot.Vaadin.DEQACheckAll.DEOverviePage;

import iu.SpringBoot.Vaadin.DEQACheckAll.Utils.ExternalJarLauncher;
import iu.SpringBoot.Vaadin.DEQACheckAll.Utils.HumanJsonFileGenerator;
import iu.SpringBoot.Vaadin.DEQACheckAll.DEOverviePage.RowObject.RowObject;
import iu.SpringBoot.Vaadin.views.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.annotation.security.RolesAllowed;

@PageTitle("DE Result Overview")
@Route("de-result-overview")
@RolesAllowed("ADMIN")
public class DEOverviewPage extends VerticalLayout {

    final static String DATA_FOLDER_NAME = "share_package/data";
    final static String JAR_WORKING_DIR = "share_package";
    final static String DEQACheckJar = "share_package/jar/DEQACheck-v20260107-all.jar";
    final static String TEMPLATE_FOR_HUMAN_DE = "share_package/templates/DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v10_1.json";

    public DEOverviewPage() {
        // layout settings
        getStyle().set("padding", "var(--lumo-space-m)");
        setSpacing(true);
        setWidthFull();

        add(new H2("DEQAData/AuthorYear/DE/json/*human*.json 一覧"));

        Path base = Paths.get(System.getProperty("user.dir"), DATA_FOLDER_NAME);

        if (!Files.exists(base) || !Files.isDirectory(base)) {
            add(new Paragraph("Data ディレクトリが見つかりません: " + base.toAbsolutePath()));
            return;
        }

        // サブフォルダを取得（例: DEQAData/Bayer2022, ...）
        List<Path> pathListOfAuthorYearDir;
        try {
            pathListOfAuthorYearDir = Files.list(base)
                    .filter(Files::isDirectory)
                    .sorted(Comparator.comparing(p -> p.getFileName().toString().toLowerCase()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            add(new Paragraph("サブフォルダの走査でエラー: " + e.getMessage()));
            return;
        }

        if (pathListOfAuthorYearDir.isEmpty()) {
            add(new Paragraph("Dataディレクトリ配下にフォルダがありません。"));
            return;
        }

        // Human系JSONファイルが存在しない場合、テンプレートから生成
        List<String> messages = HumanJsonFileGenerator.ensureHumanJsonFiles(pathListOfAuthorYearDir, TEMPLATE_FOR_HUMAN_DE);
        for (String message : messages) {
            add(new Paragraph(message));
        }

        // デモ用データ
        List<RowObject> rows = constructRowObjectList(pathListOfAuthorYearDir);

        if (rows.isEmpty()) {
            add(new Paragraph("Human系JSONファイルが見つかりません。"));
            return;
        }
        // リロードボタン
        Button reloadButton = new Button("リロード", e -> UI.getCurrent().getPage().reload());
        add(reloadButton);

        add(new Paragraph("チェックの入ったチェックボックス（☑）は _DE_AuthorXXXX_by_Human_.json のAnswer欄に何らかの入力がなされていることを意味します。マウスポインタを重ねるとその値が参照できます。（なお、チェックを入れたり消したりしてもjsonファイルには何ら影響は及ぼしません。）"));

        // Gridは使わず、Vaadinの要素APIとコンポーネントでtableを構築
        Div scrollWrapper = new Div();
        scrollWrapper.getStyle().set("max-height", "70vh");
        scrollWrapper.getStyle().set("overflow", "auto");
        scrollWrapper.getStyle().set("border", "1px solid var(--lumo-contrast-20pct)");

        Element table = new Element("table");
        table.setAttribute("style", "border-collapse: collapse; width: 100%; font-size: var(--lumo-font-size-m);");

        // thead
        Element thead = new Element("thead");
        Element trHead = new Element("tr");
        appendHeaderCell(trHead, "AuthorYear");
        appendHeaderCell(trHead, "DEQACheck.jar");

        int subSectionSize = rows.get(0).valueList_SI.size();
        for (int i = 1; i <= subSectionSize; i++) {
            appendHeaderCell(trHead, "SI" + i);
        }
        subSectionSize = rows.get(0).valueList_SC.size();
        for (int i = 1; i <= subSectionSize; i++) {
            appendHeaderCell(trHead, "SC" + i);
        }
        subSectionSize = rows.get(0).valueList_RCI.size();
        for (int i = 1; i <= subSectionSize; i++) {
            appendHeaderCell(trHead, "RCI" + i);
        }
        subSectionSize = rows.get(0).valueList_NM.size();
        for (int i = 1; i <= subSectionSize; i++) {
            appendHeaderCell(trHead, "NM" + i);
        }
        subSectionSize = rows.get(0).valueList_CAA.size();
        for (int i = 1; i <= subSectionSize; i++) {
            appendHeaderCell(trHead, "CAA" + i);
        }
        subSectionSize = rows.get(0).valueList_GN.size();
        for (int i = 1; i <= subSectionSize; i++) {
            appendHeaderCell(trHead, "GN" + i);
        }

        appendHeaderCell(trHead, "Modified Time");
        appendHeaderCell(trHead, "Size");
        appendHeaderCell(trHead, "File Name");
        thead.appendChild(trHead);
        table.appendChild(thead);

        // tbody
        Element tbody = new Element("tbody");
        boolean even = false;
        //for (RowObject row : rows) {
        for (int r = 0; r < rows.size(); r++) {
            RowObject row = rows.get(r);
            Element tr = new Element("tr");
            if (even) {
                tr.setAttribute("style", "background: var(--lumo-contrast-5pct);");
            }
            appendNormalCell(tr, row.authorYear);
            appendLauncherCell(tr, "open", row.authorYear);

            subSectionSize = row.valueList_SI.size();
            for (int i = 0; i < subSectionSize; i++) {
                appendCheckBoxCell(tr, row.valueList_SI.get(i));
            }
            subSectionSize = row.valueList_SC.size();
            for (int i = 0; i < subSectionSize; i++) {
                appendCheckBoxCell(tr, row.valueList_SC.get(i));
            }
            subSectionSize = row.valueList_RCI.size();
            for (int i = 0; i < subSectionSize; i++) {
                appendCheckBoxCell(tr, row.valueList_RCI.get(i));
            }
            subSectionSize = row.valueList_NM.size();
            for (int i = 0; i < subSectionSize; i++) {
                appendCheckBoxCell(tr, row.valueList_NM.get(i));
            }
            subSectionSize = row.valueList_CAA.size();
            for (int i = 0; i < subSectionSize; i++) {
                appendCheckBoxCell(tr, row.valueList_CAA.get(i));
            }
            subSectionSize = row.valueList_GN.size();
            for (int i = 0; i < subSectionSize; i++) {
                appendCheckBoxCell(tr, row.valueList_GN.get(i));
            }

            appendNormalCell(tr, row.modifiedTime);
            appendNormalCell(tr, String.valueOf(row.jsonFileSize));
            appendNormalCell(tr, row.jsonFileName);
            tbody.appendChild(tr);
            even = !even;
        }
        table.appendChild(tbody);

        scrollWrapper.getElement().

                appendChild(table);

        add(scrollWrapper);


        // 戻るリンク
        add(new RouterLink("メインページへ戻る", MainView.class));
    }

    private List<RowObject> constructRowObjectList(List<Path> pathListOfAuthorYearDir) {

        List<RowObject> rows = new ArrayList<>();

        for (Path authorYearPath : pathListOfAuthorYearDir) {
            // フォルダ名が小文字で始まる場合はスキップ
            String folderName = authorYearPath.getFileName().toString();
            if (!folderName.isEmpty() && Character.isLowerCase(folderName.charAt(0))) {
                continue;
            }

            Path jsonDir = authorYearPath.resolve("DE").resolve("json");
            if (!Files.exists(jsonDir) || !Files.isDirectory(jsonDir)) {
                continue;
            }

            // Human系JSONファイルの探索と収集
            List<Path> jsonFiles;
            try {
                jsonFiles = Files.list(jsonDir)
                        .filter(Files::isRegularFile)
                        .filter(p -> {
                            String name = p.getFileName().toString().toLowerCase();
                            return name.endsWith(".json") && name.contains("human");
                        })
                        .sorted(Comparator.comparing(p -> p.getFileName().toString().toLowerCase()))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                // エラー時はこのディレクトリをスキップし、ページ上部にメッセージ
                add(new Paragraph(authorYearPath.getFileName() + ": JSON ファイルの取得でエラー - " + e.getMessage()));
                continue;
            }

            // Build up rows
            for (Path jsonFile : jsonFiles) {
                rows.add(new RowObject(jsonFile));
            }
        }
        return rows;
    }

    // ヘッダセル（Element API）
    private void appendHeaderCell(Element tr, String text) {
        Element th = new Element("th");
        th.setAttribute("style", "position: sticky; top: 0; z-index: 1; text-align: left; background: var(--lumo-contrast-10pct); border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m);");
        th.setText(text == null ? "" : text);
        tr.appendChild(th);
    }

    // 通常セル（Element API）
    private void appendNormalCell(Element tr, String text) {
        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m);");
        td.setText(text == null ? "" : text);
        tr.appendChild(td);
    }

    // チェックボックスセル
    private void appendCheckBoxCell(Element tr, String text) {
        // チェックボックスセルを作成
        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m);");

        Element checkbox = new Element("input");
        checkbox.setAttribute("type", "checkbox");

        boolean checked = (text != null && !text.isEmpty());
        checkbox.setProperty("checked", checked);
        checkbox.setAttribute("title", text == null ? "" : text);

        td.appendChild(checkbox);
        tr.appendChild(td);
    }

    // ランチャーセル：クリックでVaadin Dialogを開き、YesでJAR起動＆Notification表示
    private void appendLauncherCell(Element tr, String text, String authorYear) {
        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m);");

        Button launcher = new Button(text == null ? "" : text);
        launcher.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        launcher.addClickListener(e -> {
            Dialog d = new Dialog();
            d.add(new Paragraph("DECheck.jarを起動しますか？"));
            Button yes = new Button("Yes", ev -> {
                d.close();

                boolean ok = ExternalJarLauncher.launch(DEQACheckJar, JAR_WORKING_DIR, authorYear);
                if (ok) {
                    Notification.show("起動要求を送信しました");
                } else {
                    Notification.show("起動に失敗しました（ログを確認してください）");
                }
            });
            Button no = new Button("No", ev -> d.close());
            d.getFooter().add(no, yes);
            add(d);
            d.open();
        });

        td.appendChild(launcher.getElement());
        tr.appendChild(td);
    }
}
