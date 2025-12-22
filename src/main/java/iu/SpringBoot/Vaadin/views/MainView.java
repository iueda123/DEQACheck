package iu.SpringBoot.Vaadin.views;

import iu.SpringBoot.Vaadin.DEQACheckAll.DEOverviePage.DEOverviewPage;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.component.html.Anchor;
import iu.SpringBoot.Vaadin.DEQACheckAll.DESummaryPage.SummaryView;
import iu.SpringBoot.Vaadin.DEQACheckAll.DESummaryPage.SummaryView2;
import iu.SpringBoot.Vaadin.DEQACheckAll.QAInputPage.QAInputPage;
import iu.SpringBoot.Vaadin.DEQACheckAll.QASummaryPage.QASummaryPage;
import com.vaadin.flow.server.VaadinServletRequest;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Route("")
@PageTitle("Hello")
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

        // SubViewへのリンク
        RouterLink link1 = new RouterLink("DE Overview", DEOverviewPage.class);
        add(link1);
        RouterLink link2 = new RouterLink("Summary View", SummaryView.class);
        add(link2);
        RouterLink link3 = new RouterLink("Summary View 2", SummaryView2.class);
        add(link3);

        RouterLink link4 = new RouterLink("QA Summary", QASummaryPage.class);
        add(link4);

        RouterLink link5 = new RouterLink("QA Input", QAInputPage.class);
        add(link5);

        Anchor spreadsheetLink = new Anchor(
            "https://docs.google.com/spreadsheets/d/1cbgV4JkQRuyA0HzBgRNw8CbJjSAgq1aO/edit?gid=1558917589#gid=1558917589",
            "Google Spreadsheet - table1_NM_2025.11.17"
        );
        spreadsheetLink.setTarget("_blank");
        add(spreadsheetLink);

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

