package iu.SpringBoot.Vaadin.DEQACheckAll.DEOverviePage;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import iu.SpringBoot.Vaadin.DEQACheckAll.Utils.ExternalJarLauncher;
import iu.SpringBoot.Vaadin.views.MainView;
import jakarta.annotation.security.RolesAllowed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@PageTitle("DE File Table")
@Route("de-file-table")
@RolesAllowed("ADMIN")
public class DEFileTable extends VerticalLayout {

    private static final String DATA_FOLDER_NAME = "share_package/data";
    private static final String JAR_WORKING_DIR = "share_package";
    private static final String DEQACheckJar = "share_package/jar/DEQACheck-v20260107-all.jar";
    private static final String[] SOURCES = {"human", "codex", "claude", "gemini"};

    public DEFileTable() {
        getStyle().set("padding", "var(--lumo-space-m)");
        setSpacing(true);
        setWidthFull();

        add(new H2("DE_v11/DE_v12 JSON (human/codex/claude/gemini) 存在一覧"));

        Path base = Paths.get(System.getProperty("user.dir"), DATA_FOLDER_NAME);
        if (!Files.exists(base) || !Files.isDirectory(base)) {
            add(new Paragraph("Data ディレクトリが見つかりません: " + base.toAbsolutePath()));
            return;
        }

        List<Path> authorYearDirs;
        try {
            authorYearDirs = Files.list(base)
                    .filter(Files::isDirectory)
                    .sorted(Comparator.comparing(p -> p.getFileName().toString().toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            add(new Paragraph("サブフォルダの走査でエラー: " + e.getMessage()));
            return;
        }

        if (authorYearDirs.isEmpty()) {
            add(new Paragraph("Dataディレクトリ配下にフォルダがありません。"));
            return;
        }

        List<Row> rows = new ArrayList<>();
        for (Path authorYearDir : authorYearDirs) {
            String folderName = authorYearDir.getFileName().toString();
            if (!folderName.isEmpty() && Character.isLowerCase(folderName.charAt(0))) {
                continue;
            }
            Row row = new Row(folderName);
            Path v11JsonDir = authorYearDir.resolve("DE_v11").resolve("json");
            Path v12JsonDir = authorYearDir.resolve("DE_v12").resolve("json");
            row.v11 = resolveExists(v11JsonDir);
            row.v12 = resolveExists(v12JsonDir);
            rows.add(row);
        }

        if (rows.isEmpty()) {
            add(new Paragraph("DE_v11/DE_v12 の対象フォルダが見つかりません。"));
            return;
        }

        Button reloadButton = new Button("リロード", e -> UI.getCurrent().getPage().reload());
        add(reloadButton);

        Div scrollWrapper = new Div();
        scrollWrapper.getStyle().set("max-height", "70vh");
        scrollWrapper.getStyle().set("overflow", "auto");
        scrollWrapper.getStyle().set("border", "1px solid var(--lumo-contrast-20pct)");

        Element table = new Element("table");
        table.setAttribute("style", "border-collapse: collapse; width: 100%; font-size: var(--lumo-font-size-m);");

        Element thead = new Element("thead");
        Element trHead = new Element("tr");
        appendHeaderCell(trHead, "AuthorYear");
        for (String source : SOURCES) {
            appendHeaderCell(trHead, "v11-" + source);
        }
        for (String source : SOURCES) {
            appendHeaderCell(trHead, "v12-" + source);
        }
        thead.appendChild(trHead);
        table.appendChild(thead);

        Element tbody = new Element("tbody");
        boolean even = false;
        for (Row row : rows) {
            Element tr = new Element("tr");
            if (even) {
                tr.setAttribute("style", "background: var(--lumo-contrast-5pct);");
            }
            appendLauncherCell(tr, row.authorYear);
            for (boolean exists : row.v11) {
                appendCheckCell(tr, exists);
            }
            for (boolean exists : row.v12) {
                appendCheckCell(tr, exists);
            }
            tbody.appendChild(tr);
            even = !even;
        }
        table.appendChild(tbody);
        scrollWrapper.getElement().appendChild(table);
        add(scrollWrapper);

        add(new RouterLink("メインページへ戻る", MainView.class));
    }

    private boolean[] resolveExists(Path jsonDir) {
        boolean[] result = new boolean[SOURCES.length];
        for (int i = 0; i < SOURCES.length; i++) {
            result[i] = hasKeywordJson(jsonDir, SOURCES[i]);
        }
        return result;
    }

    private boolean hasKeywordJson(Path jsonDir, String keyword) {
        if (!Files.exists(jsonDir) || !Files.isDirectory(jsonDir)) {
            return false;
        }
        try (Stream<Path> stream = Files.list(jsonDir)) {
            String needle = keyword.toLowerCase(Locale.ROOT);
            return stream
                    .filter(Files::isRegularFile)
                    .map(p -> p.getFileName().toString().toLowerCase(Locale.ROOT))
                    .anyMatch(name -> name.endsWith(".json") && name.contains(needle));
        } catch (IOException e) {
            add(new Paragraph(jsonDir.getFileName() + ": JSON ファイルの取得でエラー - " + e.getMessage()));
            return false;
        }
    }

    private void appendHeaderCell(Element tr, String text) {
        Element th = new Element("th");
        th.setAttribute("style", "position: sticky; top: 0; z-index: 1; text-align: left; background: var(--lumo-contrast-10pct); border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m);");
        th.setText(text == null ? "" : text);
        tr.appendChild(th);
    }

    private void appendNormalCell(Element tr, String text) {
        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m);");
        td.setText(text == null ? "" : text);
        tr.appendChild(td);
    }

    private void appendLauncherCell(Element tr, String authorYear) {
        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m);");

        Button launcher = new Button(authorYear == null ? "" : authorYear);
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
            d.open();
        });

        td.appendChild(launcher.getElement());
        tr.appendChild(td);
    }

    private void appendCheckCell(Element tr, boolean exists) {
        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m);");
        Element checkbox = new Element("input");
        checkbox.setAttribute("type", "checkbox");
        checkbox.setProperty("checked", exists);
        checkbox.setProperty("disabled", true);
        td.appendChild(checkbox);
        tr.appendChild(td);
    }

    private static class Row {
        final String authorYear;
        boolean[] v11 = new boolean[SOURCES.length];
        boolean[] v12 = new boolean[SOURCES.length];

        Row(String authorYear) {
            this.authorYear = authorYear;
        }
    }
}
