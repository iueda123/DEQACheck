package iu.SpringBoot.Vaadin.views.DEQACheckAll.DESummaryPage.SummaryView;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import iu.SpringBoot.Vaadin.logging.UiActionLogger;
import iu.SpringBoot.Vaadin.views.DEQACheckAll.Utils.ExternalJarLauncher;
import jakarta.annotation.security.RolesAllowed;

import java.util.LinkedHashMap;
import java.util.Map;

@PageTitle("Launch Summary View 5")
@Route("summary-view-5-launch/:authorYear")
@RolesAllowed("ADMIN")
public class SummaryView5LaunchView extends VerticalLayout implements BeforeEnterObserver {

    private static final String VERSION_NAME = "DE_v13";
    private static final String JAR_WORKING_DIR = "share_package";
    private static final String RSLT_COMPARATOR_V13_JAR = "share_package/jar/RsltComparator-v20260107-V13.jar";

    public SummaryView5LaunchView() {
        setSpacing(true);
        getStyle().set("padding", "var(--lumo-space-m)");
        add(new H3("RsltComparator v13 launcher"));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        removeAll();
        add(new H3("RsltComparator v13 launcher"));

        String authorYear = event.getRouteParameters().get("authorYear").orElse("");
        Map<String, Object> details = new LinkedHashMap<>();
        details.put("authorYear", authorYear);
        details.put("version", VERSION_NAME);
        details.put("jar", RSLT_COMPARATOR_V13_JAR);

        if (authorYear.isBlank()) {
            add(new Paragraph("authorYear が指定されていません。"));
            add(new Anchor("/summary-view-5", "summary-view-5 に戻る"));
            return;
        }

        UiActionLogger.logAction("summary_view_5_launch_route_entered", details);
        boolean ok;
        try {
            ok = ExternalJarLauncher.launch(
                    RSLT_COMPARATOR_V13_JAR,
                    JAR_WORKING_DIR,
                    authorYear,
                    VERSION_NAME
            );
        } catch (Exception ex) {
            UiActionLogger.logError("summary_view_5_launch_route_exception", ex, details);
            add(new Paragraph("起動に失敗しました: " + ex.getMessage()));
            add(new Anchor("/summary-view-5", "summary-view-5 に戻る"));
            return;
        }

        details.put("ok", ok);
        if (ok) {
            UiActionLogger.logAction("summary_view_5_launch_route_started", details);
            add(new Paragraph("起動要求を送信しました: " + authorYear + " / " + VERSION_NAME));
        } else {
            UiActionLogger.logAction("summary_view_5_launch_route_failed", details);
            add(new Paragraph("起動に失敗しました。ログを確認してください。"));
        }

        add(new Anchor("/summary-view-5", "summary-view-5 に戻る"));
    }
}
