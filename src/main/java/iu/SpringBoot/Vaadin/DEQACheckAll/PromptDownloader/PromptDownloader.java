package iu.SpringBoot.Vaadin.DEQACheckAll.PromptDownloader;

/**
 * ./share_package/prompts にあるファイルををダウンロードさせるためのページ。
 * 対象拡張子は  *.md, *.doc, *.txt, *.docx
 * MainView からこのページへのリンクも貼る。
 */

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.StreamResource;
import iu.SpringBoot.Vaadin.views.MainView;
import jakarta.annotation.security.RolesAllowed;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@PageTitle("Prompt Downloader")
@Route("prompt-download")
@RolesAllowed({"USER", "GUEST"})
public class PromptDownloader extends VerticalLayout {

    private static final Path PROMPTS_DIR = Paths.get("share_package/prompts");
    private static final Set<String> ALLOWED_EXTENSIONS = Set.of(".md", ".doc", ".txt", ".docx", ".pdf");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public PromptDownloader() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        add(new H2("Prompt Downloader"));
        add(new RouterLink("< Back to Main", MainView.class));

        add(new Paragraph("share_package/prompts/ 配下のファイルをダウンロードできます。"));

        buildFileList();
    }

    private void buildFileList() {
        if (!Files.exists(PROMPTS_DIR) || !Files.isDirectory(PROMPTS_DIR)) {
            add(new Paragraph("プロンプトディレクトリが見つかりません: " + PROMPTS_DIR));
            return;
        }

        List<Path> files;
        try (Stream<Path> stream = Files.list(PROMPTS_DIR)) {
            files = stream
                    .filter(Files::isRegularFile)
                    .filter(this::hasAllowedExtension)
                    .sorted((a, b) -> a.getFileName().toString().compareToIgnoreCase(b.getFileName().toString()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            add(new Paragraph("ファイル一覧の取得に失敗しました: " + e.getMessage()));
            return;
        }

        if (files.isEmpty()) {
            add(new Paragraph("ダウンロード可能なファイルがありません。"));
            return;
        }

        // ファイル一覧をテーブル形式で表示
        Div tableWrapper = new Div();
        tableWrapper.getStyle()
                .set("border", "1px solid #ddd")
                .set("border-radius", "6px")
                .set("overflow", "hidden");

        // ヘッダー行
        Div headerRow = createRow("ファイル名", "サイズ", "更新日時", true);
        tableWrapper.add(headerRow);

        // ファイル行
        boolean even = false;
        for (Path file : files) {
            Div row = createFileRow(file, even);
            tableWrapper.add(row);
            even = !even;
        }

        add(tableWrapper);
    }

    private boolean hasAllowedExtension(Path path) {
        String fileName = path.getFileName().toString().toLowerCase();
        return ALLOWED_EXTENSIONS.stream().anyMatch(fileName::endsWith);
    }

    private Div createRow(String col1, String col2, String col3, boolean isHeader) {
        Div row = new Div();
        row.getStyle()
                .set("display", "grid")
                .set("grid-template-columns", "1fr 100px 150px")
                .set("gap", "16px")
                .set("padding", "12px 16px")
                .set("align-items", "center");

        if (isHeader) {
            row.getStyle()
                    .set("background", "#f5f5f5")
                    .set("font-weight", "bold")
                    .set("border-bottom", "1px solid #ddd");
        }

        row.add(new Span(col1), new Span(col2), new Span(col3));
        return row;
    }

    private Div createFileRow(Path file, boolean even) {
        Div row = new Div();
        row.getStyle()
                .set("display", "grid")
                .set("grid-template-columns", "1fr 100px 150px")
                .set("gap", "16px")
                .set("padding", "12px 16px")
                .set("align-items", "center")
                .set("border-bottom", "1px solid #eee");

        if (even) {
            row.getStyle().set("background", "#fafafa");
        }

        String fileName = file.getFileName().toString();

        // ダウンロードリンク
        StreamResource resource = new StreamResource(fileName, () -> {
            try {
                return Files.newInputStream(file);
            } catch (IOException e) {
                Notification.show("ファイルの読み込みに失敗しました: " + e.getMessage(), 4000, Notification.Position.BOTTOM_START);
                return InputStream.nullInputStream();
            }
        });
        setContentType(resource, fileName);

        Anchor downloadLink = new Anchor(resource, fileName);
        downloadLink.getElement().setAttribute("download", true);
        downloadLink.getStyle().set("color", "#1976d2").set("text-decoration", "none");

        // ファイルサイズ
        String size;
        try {
            long bytes = Files.size(file);
            size = formatFileSize(bytes);
        } catch (IOException e) {
            size = "-";
        }

        // 更新日時
        String modifiedTime;
        try {
            Instant instant = Files.getLastModifiedTime(file).toInstant();
            modifiedTime = DATE_FORMAT.format(instant.atZone(ZoneId.systemDefault()));
        } catch (IOException e) {
            modifiedTime = "-";
        }

        row.add(downloadLink, new Span(size), new Span(modifiedTime));
        return row;
    }

    private void setContentType(StreamResource resource, String fileName) {
        String lower = fileName.toLowerCase();
        if (lower.endsWith(".md") || lower.endsWith(".txt")) {
            resource.setContentType("text/plain; charset=utf-8");
        } else if (lower.endsWith(".doc")) {
            resource.setContentType("application/msword");
        } else if (lower.endsWith(".docx")) {
            resource.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        } else {
            resource.setContentType("application/octet-stream");
        }
    }

    private String formatFileSize(long bytes) {
        if (bytes < 1024) {
            return bytes + " B";
        } else if (bytes < 1024 * 1024) {
            return String.format("%.1f KB", bytes / 1024.0);
        } else {
            return String.format("%.1f MB", bytes / (1024.0 * 1024.0));
        }
    }
}
