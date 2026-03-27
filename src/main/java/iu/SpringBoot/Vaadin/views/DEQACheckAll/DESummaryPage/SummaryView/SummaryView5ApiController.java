package iu.SpringBoot.Vaadin.views.DEQACheckAll.DESummaryPage.SummaryView;

import iu.SpringBoot.Vaadin.logging.UiActionLogger;
import iu.SpringBoot.Vaadin.views.DEQACheckAll.Utils.ExternalJarLauncher;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RolesAllowed("ADMIN")
public class SummaryView5ApiController {

    private static final String VERSION_NAME = "DE_v13";
    private static final String DATA_FOLDER_NAME = "share_package/data";
    private static final String JAR_WORKING_DIR = "share_package";
    private static final String RSLT_COMPARATOR_V13_JAR = "share_package/jar/RsltComparator-v20260107-V13.jar";

    @PostMapping(value = "/api/summary-view-5/launch/{authorYear}", produces = MediaType.TEXT_HTML_VALUE)
    public String launch(@PathVariable String authorYear) {
        Map<String, Object> details = new LinkedHashMap<>();
        details.put("authorYear", authorYear);
        details.put("version", VERSION_NAME);
        details.put("jar", RSLT_COMPARATOR_V13_JAR);

        boolean ok = ExternalJarLauncher.launch(
                RSLT_COMPARATOR_V13_JAR,
                JAR_WORKING_DIR,
                authorYear,
                VERSION_NAME
        );
        details.put("ok", ok);
        if (ok) {
            UiActionLogger.logAction("summary_view_5_api_launch_started", details);
            return htmlMessage("起動要求を送信しました: " + authorYear + " / " + VERSION_NAME);
        }
        UiActionLogger.logAction("summary_view_5_api_launch_failed", details);
        return htmlMessage("起動に失敗しました。ログを確認してください。");
    }

    @PostMapping(
            value = "/api/summary-view-5/note/{authorYear}",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_HTML_VALUE
    )
    public String saveNote(
            @PathVariable String authorYear,
            @RequestParam(name = "content", required = false) String content
    ) throws IOException {
        String note = content == null ? "" : content;
        Path notePath = Paths.get(System.getProperty("user.dir"), DATA_FOLDER_NAME,
                authorYear, VERSION_NAME, "note", "summary-view-5.txt");
        Files.createDirectories(notePath.getParent());
        Files.writeString(notePath, note);

        Map<String, Object> details = new LinkedHashMap<>();
        details.put("authorYear", authorYear);
        details.put("path", notePath.toAbsolutePath());
        details.put("length", note.length());
        UiActionLogger.logAction("summary_view_5_api_note_saved", details);

        return htmlMessage("保存しました: " + authorYear);
    }

    private String htmlMessage(String message) {
        String escaped = message
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
        return "<!doctype html><html><body>" + escaped + "</body></html>";
    }
}
