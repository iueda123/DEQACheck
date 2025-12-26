package iu.SpringBoot.Vaadin.DEQACheckAll.HelpPages;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import iu.SpringBoot.Vaadin.views.MainView;
import jakarta.annotation.security.RolesAllowed;

/**
 * DEQACheckAll アプリケーションのヘルプページ。
 * 各機能の使い方について説明を記載。
 */
@Route("help")
@PageTitle("Help - DEQACheckAll")
@RolesAllowed({"USER", "GUEST"})
public class TheFirstHelpPage extends VerticalLayout {

    public TheFirstHelpPage() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        add(new H2("DEQACheckAll - Help"));
        add(new RouterLink("< Back to Main", MainView.class));

        Paragraph intro = new Paragraph(
            "このページでは、DEQACheckAll アプリケーションの各機能について説明します。"
        );
        intro.getStyle().set("margin-bottom", "20px");
        add(intro);

        // アコーディオン形式で各機能の説明を表示
        Accordion accordion = new Accordion();
        accordion.setWidthFull();

        accordion.add(createMaterialDownloaderPanel());
        accordion.add(createPromptDownloaderPanel());
        accordion.add(createQAReportCreationPanel());
        accordion.add(createQAResultPerReviewerPanel());
        accordion.add(createQAResultTablePanel());

        add(accordion);

    }

    private AccordionPanel createMaterialDownloaderPanel() {
        VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);
        content.setPadding(false);

        content.add(new Paragraph("論文のマテリアル（PDF、補足資料など）をダウンロードするためのページです。"));

        H3 howToUse = new H3("使い方");
        howToUse.getStyle().set("margin-top", "10px").set("margin-bottom", "5px");
        content.add(howToUse);

        UnorderedList steps = new UnorderedList();
        steps.add(new ListItem("1. AuthorYear ドロップダウンから対象の論文を選択"));
        steps.add(new ListItem("2. 「Download Materials」ボタンをクリック"));
        steps.add(new ListItem("3. ダウンロードリンクが表示されるのでクリックしてZIPファイルを取得"));
        content.add(steps);

        H3 notes = new H3("補足");
        notes.getStyle().set("margin-top", "10px").set("margin-bottom", "5px");
        content.add(notes);

        UnorderedList notesList = new UnorderedList();
        notesList.add(new ListItem("ダウンロード対象: share_package/data/<AuthorYear>/materials/non-optimized/"));
        notesList.add(new ListItem("一度生成されたZIPはキャッシュされ、次回以降は高速にダウンロード可能"));
        content.add(notesList);

        return new AccordionPanel("Material Downloader（マテリアルダウンロード）", content);
    }

    private AccordionPanel createPromptDownloaderPanel() {
        VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);
        content.setPadding(false);

        content.add(new Paragraph("QA評価に使用するガイドドキュメントをダウンロードするためのページです。"));

        //----------------------

        H3 howToUse = new H3("使い方");
        howToUse.getStyle().set("margin-top", "10px").set("margin-bottom", "5px");
        content.add(howToUse);

        UnorderedList steps = new UnorderedList();
        steps.add(new ListItem("1. ページにアクセスすると利用可能なファイル一覧が表示"));
        steps.add(new ListItem("2. ダウンロードしたいファイル名をクリック"));
        content.add(steps);

        //----------------------

        H3 notes = new H3("対象ファイル");
        notes.getStyle().set("margin-top", "10px").set("margin-bottom", "5px");
        content.add(notes);

        UnorderedList notesList = new UnorderedList();
        notesList.add(new ListItem("share_package/prompts/ 配下のファイル"));
        notesList.add(new ListItem("対応拡張子: .md, .doc, .docx, .txt, .pdf"));
        content.add(notesList);

        //----------------------
        H3 additional_info = new H3("補足情報");
        additional_info.getStyle().set("margin-top", "10px").set("margin-bottom", "5px");
        content.add(additional_info);

        UnorderedList additional_info_list = new UnorderedList();
        notesList.add(new ListItem("NORMA_12.22.docx を使ってください (2025.12.26)"));
        content.add(additional_info_list);

        return new AccordionPanel("Prompt Downloader（ガイドダウンロード）", content);
    }

    private AccordionPanel createQAReportCreationPanel() {
        VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);
        content.setPadding(false);

        content.add(new Paragraph("QA評価レポートを作成・編集するためのページです。"));

        //----------------------
        H3 howToUse = new H3("使い方");
        howToUse.getStyle().set("margin-top", "10px").set("margin-bottom", "5px");
        content.add(howToUse);

        UnorderedList steps = new UnorderedList();
        steps.add(new ListItem("1. AuthorYear ドロップダウンから評価対象の論文を選択"));
        steps.add(new ListItem("2. 新規作成の場合は「Create New」ボタンをクリック"));
        steps.add(new ListItem("3. 既存レポートを編集する場合は、ファイルを選択して「Load/Edit」をクリック"));
        steps.add(new ListItem("4. 各QA項目について Answer, Confidence Rating, Reason 等を記入"));
        steps.add(new ListItem("5. 「Save」ボタンで保存"));
        content.add(steps);

        //----------------------
        H3 fields = new H3("入力項目");
        fields.getStyle().set("margin-top", "10px").set("margin-bottom", "5px");
        content.add(fields);

        UnorderedList fieldsList = new UnorderedList();
        fieldsList.add(new ListItem("Answer: Yes / Partial / No / NA から選択（必須）"));
        fieldsList.add(new ListItem("Confidence Rating: High / Medium / Low から選択（必須）"));
        fieldsList.add(new ListItem("Reason: 判断根拠を記述（推奨）"));
        fieldsList.add(new ListItem("Supporting Text: 根拠となる本文の引用（任意）"));
        fieldsList.add(new ListItem("Location: 根拠の記載場所（任意）"));
        content.add(fieldsList);

        //----------------------
        H3 notes = new H3("補足情報");
        notes.getStyle().set("margin-top", "10px").set("margin-bottom", "5px");
        content.add(notes);

        UnorderedList note_items = new UnorderedList();
        note_items.add(new ListItem("2025.12.26 現在Quality assessment version 9 (QA_v9) 用となっています。"));
        content.add(note_items);

        return new AccordionPanel("QA Report Creation（QAレポート作成）", content);
    }

    private AccordionPanel createQAResultPerReviewerPanel() {
        VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);
        content.setPadding(false);

        content.add(new Paragraph("特定のレビュワーによるQA評価結果を詳細表示するためのページです。"));

        H3 howToUse = new H3("使い方");
        howToUse.getStyle().set("margin-top", "10px").set("margin-bottom", "5px");
        content.add(howToUse);

        UnorderedList steps = new UnorderedList();
        steps.add(new ListItem("1. AuthorYear を選択"));
        steps.add(new ListItem("2. PromptName（QAバージョン）を選択"));
        steps.add(new ListItem("3. ReviewerName を選択"));
        steps.add(new ListItem("4. 該当レビュワーのQA評価結果が表形式で表示"));
        content.add(steps);

        H3 features = new H3("表示内容");
        features.getStyle().set("margin-top", "10px").set("margin-bottom", "5px");
        content.add(features);

        UnorderedList featuresList = new UnorderedList();
        featuresList.add(new ListItem("各QA項目の回答（Answer）と信頼度（Confidence）"));
        featuresList.add(new ListItem("判断理由（Reason）と根拠テキスト（Supporting Text）"));
        featuresList.add(new ListItem("回答のサマリー（Yes/No/Partial/NA の件数）"));
        content.add(featuresList);

        return new AccordionPanel("QA Result Per Reviewer（レビュワー別結果）", content);
    }

    private AccordionPanel createQAResultTablePanel() {
        VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);
        content.setPadding(false);

        content.add(new Paragraph("複数のレビュワーによるQA評価結果を比較表示するためのページです。"));

        H3 howToUse = new H3("使い方");
        howToUse.getStyle().set("margin-top", "10px").set("margin-bottom", "5px");
        content.add(howToUse);

        UnorderedList steps = new UnorderedList();
        steps.add(new ListItem("1. AuthorYear を選択"));
        steps.add(new ListItem("2. PromptName（QAバージョン）を選択"));
        steps.add(new ListItem("3. すべてのレビュワーの評価結果が一覧テーブルで表示"));
        steps.add(new ListItem("4. 「Download」ボタンでZIP（JSONファイル一式）とTSV（サマリー）をダウンロード可能"));
        content.add(steps);

        H3 tableFormat = new H3("テーブル形式");
        tableFormat.getStyle().set("margin-top", "10px").set("margin-bottom", "5px");
        content.add(tableFormat);

        UnorderedList formatList = new UnorderedList();
        formatList.add(new ListItem("行: QA項目（CM1, NM1-NM11, CR1-CR6）"));
        formatList.add(new ListItem("列: レビュワー名"));
        formatList.add(new ListItem("セル: 回答（Y=Yes, N=No, P=Partial, NA=Not Applicable）"));
        content.add(formatList);

        H3 colorCoding = new H3("色分け");
        colorCoding.getStyle().set("margin-top", "10px").set("margin-bottom", "5px");
        content.add(colorCoding);

        Div legendDiv = new Div();
        legendDiv.getStyle().set("display", "flex").set("gap", "15px").set("flex-wrap", "wrap");
        legendDiv.add(createColorBadge("Y", "Yes", "#28a745"));
        legendDiv.add(createColorBadge("N", "No", "#dc3545"));
        legendDiv.add(createColorBadge("P", "Partial", "#fd7e14"));
        legendDiv.add(createColorBadge("NA", "NA", "#6c757d"));
        content.add(legendDiv);

        return new AccordionPanel("QA Result Table（結果比較テーブル）", content);
    }

    private Div createColorBadge(String shortText, String fullText, String bgColor) {
        Div item = new Div();
        item.getStyle().set("display", "inline-flex").set("align-items", "center").set("gap", "5px");

        Span badge = new Span(shortText);
        badge.getStyle()
            .set("background-color", bgColor)
            .set("color", "white")
            .set("padding", "2px 8px")
            .set("border-radius", "4px")
            .set("font-size", "12px")
            .set("font-weight", "bold");

        Span label = new Span("= " + fullText);
        label.getStyle().set("font-size", "13px");

        item.add(badge, label);
        return item;
    }
}
