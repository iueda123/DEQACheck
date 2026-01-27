package iu.SpringBoot.Vaadin.DEQACheckAll.MaterialDownloader;

/**
 * # Spec Info
 * <p>
 * このページは、share_package/data/<AuthorYear>/materials/non-optimized/ をダウンロードするためのページです。
 * コンボボックスで AuthorYear を選択し、「Download Materials」ボタンを押すとダウンロードが始まります。
 * ダウンロード対象のフォルダはzip圧縮されてダウンロードされる。
 * ダウンロード依頼 -> /tmp/zipped-materials/<AuthorYear>.zip があればそれをクライアント側にダウンロード許可。
 * zipが無ければを生成してからクライアント側にダウンロード許可。
 * 一度 zip化 されたmaterialsは再度ダウンロード要求があったときに備えて保管しておく。
 */

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.StreamResource;
import iu.SpringBoot.Vaadin.views.MainView;
import jakarta.annotation.security.RolesAllowed;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@PageTitle("Material Download")
@Route("material-download")
@RolesAllowed({"USER", "GUEST"})
public class MaterialDownloader extends VerticalLayout {

    private static final String DATA_PATH = "share_package/data";
    private static final String MATERIALS_SUBPATH = "materials/non-optimized";
    private static final Path ZIP_CACHE_DIR = Paths.get("tmp/zipped-materials");

    private ComboBox<String> authorYearCombo;
    private Anchor downloadAnchor;
    private Button prepareButton;
    private Paragraph statusLabel;

    public MaterialDownloader() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        add(new H2("Material Download"));
        add(new RouterLink("< Back to Main", MainView.class));

        add(new Paragraph("share_package/data/<AuthorYear>/materials/non-optimized/ フォルダをZIP圧縮してダウンロードします。"));

        createControls();
    }

    private void createControls() {
        authorYearCombo = new ComboBox<>("AuthorYear を選択");
        authorYearCombo.setItems(getAuthorYearList());
        authorYearCombo.setWidth("300px");
        authorYearCombo.addValueChangeListener(e -> resetDownloadState());

        prepareButton = new Button("Download Materials", e -> prepareDownload());
        prepareButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        downloadAnchor = new Anchor();
        downloadAnchor.getElement().setAttribute("download", true);
        downloadAnchor.setVisible(false);

        statusLabel = new Paragraph();
        statusLabel.getStyle().set("color", "#666");

        Button reloadButton = new Button("Reload AuthorYear list", e -> authorYearCombo.setItems(getAuthorYearList()));

        HorizontalLayout controls = new HorizontalLayout(authorYearCombo, reloadButton, prepareButton, downloadAnchor);
        controls.setAlignItems(Alignment.END);
        controls.setSpacing(true);

        add(controls);
        add(statusLabel);
    }

    private List<String> getAuthorYearList() {
        Path dataDir = Paths.get(DATA_PATH);

        if (!Files.exists(dataDir) || !Files.isDirectory(dataDir)) {
            return List.of();
        }

        try (Stream<Path> stream = Files.list(dataDir)) {
            return stream
                    .filter(Files::isDirectory)
                    .map(p -> p.getFileName().toString())
                    .filter(name -> !name.isEmpty() && Character.isUpperCase(name.charAt(0)))
                    .filter(this::hasMaterialsFolder)
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            Notification.show("Failed to list data folders: " + e.getMessage(), 4000, Notification.Position.BOTTOM_START);
            return List.of();
        }
    }

    private boolean hasMaterialsFolder(String authorYear) {
        Path materialsPath = Paths.get(DATA_PATH, authorYear, MATERIALS_SUBPATH);
        return Files.exists(materialsPath) && Files.isDirectory(materialsPath);
    }

    private void resetDownloadState() {
        downloadAnchor.setVisible(false);
        downloadAnchor.setHref("");
        statusLabel.setText("");
    }

    private void prepareDownload() {
        String authorYear = authorYearCombo.getValue();
        if (authorYear == null || authorYear.isEmpty()) {
            Notification.show("AuthorYear を選択してください。", 3000, Notification.Position.BOTTOM_START);
            return;
        }

        Path materialsPath = Paths.get(DATA_PATH, authorYear, MATERIALS_SUBPATH);
        if (!Files.exists(materialsPath) || !Files.isDirectory(materialsPath)) {
            Notification.show("Materials フォルダが見つかりません: " + materialsPath, 4000, Notification.Position.BOTTOM_START);
            return;
        }

        try {
            // Ensure cache directory exists
            Files.createDirectories(ZIP_CACHE_DIR);

            Path cachedZip = ZIP_CACHE_DIR.resolve(authorYear + ".zip");
            byte[] zipBytes;

            if (Files.exists(cachedZip)) {
                // Use cached ZIP
                statusLabel.setText("キャッシュ済みZIPを使用します: " + cachedZip.getFileName());
                zipBytes = Files.readAllBytes(cachedZip);
            } else {
                // Create new ZIP
                statusLabel.setText("ZIPファイルを生成中...");
                zipBytes = createZipFromFolder(materialsPath, authorYear);

                // Cache the ZIP for future downloads
                Files.write(cachedZip, zipBytes);
                statusLabel.setText("ZIPファイルを生成しました: " + cachedZip.getFileName());
            }

            // Create StreamResource for download
            String fileName = authorYear + "_materials.zip";
            StreamResource resource = new StreamResource(fileName, () -> new ByteArrayInputStream(zipBytes));
            resource.setContentType("application/zip");

            downloadAnchor.setHref(resource);
            downloadAnchor.setText("Click to download: " + fileName);
            downloadAnchor.setVisible(true);

            Notification.show("ダウンロードリンクが準備できました。", 3000, Notification.Position.BOTTOM_START);

        } catch (IOException e) {
            Notification.show("ZIPファイルの生成に失敗しました: " + e.getMessage(), 5000, Notification.Position.BOTTOM_START);
            statusLabel.setText("エラー: " + e.getMessage());
        }
    }

    /**
     * ZIPファイルをメモリ上（ByteArrayOutputStream）に生成し、byte[]として返す。
     * ファイルシステムへの保存は行わない。
     * 呼び出し元の prepareDownload() で ./tmp/zipped-materials/<AuthorYear>.zip にキャッシュ保存される。
     */
    private byte[] createZipFromFolder(Path sourceFolder, String authorYear) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (ZipOutputStream zos = new ZipOutputStream(baos)) {
            String baseName = authorYear + "_materials";

            try (Stream<Path> walker = Files.walk(sourceFolder)) {
                walker.filter(Files::isRegularFile).forEach(file -> {
                    try {
                        // Create relative path for ZIP entry
                        Path relativePath = sourceFolder.relativize(file);
                        String entryName = baseName + "/" + relativePath;

                        ZipEntry zipEntry = new ZipEntry(entryName);
                        zos.putNextEntry(zipEntry);

                        Files.copy(file, zos);
                        zos.closeEntry();
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to add file to ZIP: " + file, e);
                    }
                });
            }
        }

        return baos.toByteArray();
    }
}
