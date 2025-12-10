package iu.SpringBoot.Vaadin.views;

import iu.SpringBoot.Vaadin.DEQACheckAll.DEOverviePage.DEOverviewPage;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.component.html.Anchor;
import iu.SpringBoot.Vaadin.DEQACheckAll.DESummaryPage.SummaryView;

@Route("")
@PageTitle("Hello")
public class MainView extends VerticalLayout {

    public MainView() {
        setSizeFull();
        setAlignItems(Alignment.START);
        setPadding(true);

        //add(new H1("Hello, Vaadin + Spring Boot!"));
        add(new H1("DEQACheckAll"));

        //Button button = new Button("Click me", e -> Notification.show("Hello!"));
        //add(button);

        // SubViewへのリンク
        RouterLink link1 = new RouterLink("DE Overview", DEOverviewPage.class);
        add(link1);
        RouterLink link2 = new RouterLink("Summary View", SummaryView.class);
        add(link2);



        //RouterLink link11 = new RouterLink("QA Overview", DEOverviewPage.class);
        //add(link11);

        Anchor spreadsheetLink = new Anchor(
            "https://docs.google.com/spreadsheets/d/1cbgV4JkQRuyA0HzBgRNw8CbJjSAgq1aO/edit?gid=1558917589#gid=1558917589",
            "Google Spreadsheet - table1_NM_2025.11.17"
        );
        spreadsheetLink.setTarget("_blank");
        add(spreadsheetLink);

    }
}

