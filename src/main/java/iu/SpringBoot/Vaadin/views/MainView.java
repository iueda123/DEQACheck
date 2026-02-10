package iu.SpringBoot.Vaadin.views;

import iu.SpringBoot.Vaadin.DEQACheckAll.DEOverviePage.DEFileTable;
import iu.SpringBoot.Vaadin.DEQACheckAll.DEOverviePage.DEOverviewPage;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.component.html.Anchor;
import iu.SpringBoot.Vaadin.DEQACheckAll.DESummaryPage.SummaryView_DEv10;
import iu.SpringBoot.Vaadin.DEQACheckAll.DESummaryPage.SummaryView_DEv10_2;
import iu.SpringBoot.Vaadin.DEQACheckAll.DESummaryPage.ResultOverView_DEv11_v12;
import iu.SpringBoot.Vaadin.DEQACheckAll.MaterialDownloader.MaterialDownloader;
import iu.SpringBoot.Vaadin.DEQACheckAll.PromptDownloader.PromptDownloader;
import iu.SpringBoot.Vaadin.DEQACheckAll.QAReportCreationPage.QAReportCreationPage;
import iu.SpringBoot.Vaadin.DEQACheckAll.QAResultPage.QAResultPerReviewerPage;
import com.vaadin.flow.server.VaadinServletRequest;
import iu.SpringBoot.Vaadin.DEQACheckAll.QAResultPage.QAResultTablePage;
import iu.SpringBoot.Vaadin.DEQACheckAll.HelpPages.TheFirstHelpPage;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Route("")
@PageTitle("DEQACheckAll")
@RolesAllowed({"USER", "GUEST"})
public class MainView extends VerticalLayout {

    public MainView() {
        setSizeFull();
        setAlignItems(Alignment.START);
        setPadding(true);

        // Header with title and logout button
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);
        header.setAlignItems(Alignment.CENTER);

        H1 title = new H1("DEQACheckAll");
        title.getStyle().set("margin", "0");

        // User info and logout button
        HorizontalLayout userSection = new HorizontalLayout();
        userSection.setAlignItems(Alignment.CENTER);
        userSection.setSpacing(true);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Span userLabel = new Span("User: " + username);
        userLabel.getStyle()
                .set("font-size", "14px")
                .set("color", "#666");

        Button logoutButton = new Button("Logout", e -> logout());
        logoutButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);

        userSection.add(userLabel, logoutButton);
        header.add(title, userSection);
        add(header);

        /// ///////////////////////////////////////////////////////

        add(new H2("Common"));

        RouterLink link7 = new RouterLink("Download Guides", PromptDownloader.class);
        add(link7);

        RouterLink link6 = new RouterLink("Download Materials", MaterialDownloader.class);
        add(link6);

        Anchor mainDoctLink = new Anchor(
                "https://docs.google.com/document/d/1txVIhmzKKYp5FDrX5DbbeZIjRj5DTlBt7c1dOGz018M/edit?tab=t.0",
                "Google Doc - 議事録"
        );
        mainDoctLink.setTarget("_blank");
        add(mainDoctLink);


        /// ////////////////////////////////////////////////////////
        Hr separator1 = new Hr();
        separator1.getStyle().set("width", "100%").set("margin", "10px 0");
        add(separator1);
        /// ////////////////////////////////////////////////////////

        add(new H2("Quality Assessment"));

        RouterLink link5 = new RouterLink("Do QA", QAReportCreationPage.class);
        add(link5);

        RouterLink link4 = new RouterLink("QA Result Per Reviewer", QAResultPerReviewerPage.class);
        add(link4);

        RouterLink link9 = new RouterLink("QA Result Table (Per AuthorYear and PromptName)", QAResultTablePage.class);
        add(link9);

        Hr separator2 = new Hr();
        separator2.getStyle().set("width", "100%").set("margin", "10px 0");
        add(separator2);

        /// ///////////////////////////////////////////////

        add(new H2("Data Extraction (for local server)"));

        Anchor spreadsheetLink = new Anchor(
                "https://docs.google.com/spreadsheets/d/1cbgV4JkQRuyA0HzBgRNw8CbJjSAgq1aO/edit?gid=1558917589#gid=1558917589",
                "Google Spreadsheet - table1_NM_2025.11.17"
        );
        spreadsheetLink.setTarget("_blank");
        add(spreadsheetLink);

        // only visible to ADMIN users (admin, local)
        if (hasAdminRole()) {

            RouterLink link1 = new RouterLink("DE Overview", DEOverviewPage.class);
            add(link1);

            RouterLink link11 = new RouterLink("DE File Table (v11/v12)", DEFileTable.class);
            add(link11);

            RouterLink link2 = new RouterLink("Summary View for DE_v10", SummaryView_DEv10.class);
            add(link2);

            RouterLink link3 = new RouterLink("Summary View for DE_v10 (ver 2)", SummaryView_DEv10_2.class);
            add(link3);

            RouterLink link10 = new RouterLink("Summary View for DE_v11/12", ResultOverView_DEv11_v12.class);
            add(link10);


        }

        // Help link - fixed at bottom right
        RouterLink helpLink = new RouterLink("Help", TheFirstHelpPage.class);
        helpLink.getStyle()
                .set("position", "fixed")
                .set("bottom", "20px")
                .set("right", "20px")
                .set("background-color", "#1976d2")
                .set("color", "white")
                .set("padding", "10px 20px")
                .set("border-radius", "20px")
                .set("text-decoration", "none")
                .set("font-weight", "bold")
                .set("box-shadow", "0 2px 5px rgba(0,0,0,0.3)")
                .set("z-index", "1000");
        add(helpLink);

    }

    private boolean hasAdminRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return false;
        return auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));
    }

    private void logout() {
        UI.getCurrent().getPage().setLocation("/login");
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(
                VaadinServletRequest.getCurrent().getHttpServletRequest(),
                null,
                null
        );
    }
}
