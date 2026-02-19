package iu.SpringBoot.Vaadin.DEQACheckAll.DESummaryPage;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.StreamResource;
import iu.SpringBoot.Vaadin.DEQACheckAll.DESummaryPage.RowObject.RowObject;
import iu.SpringBoot.Vaadin.DEQACheckAll.Utils.ExternalJarLauncher;
import iu.SpringBoot.Vaadin.DEQACheckAll.Utils.HumanJsonFileGenerator;
import iu.SpringBoot.Vaadin.views.MainView;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

import jakarta.annotation.security.RolesAllowed;

@PageTitle("DE v10 Result Overview (ver2)")
@Route("summary-view-2")
@StyleSheet("./styles/summary-table.css")
@RolesAllowed("ADMIN")
public class SummaryView_DEv10_2 extends VerticalLayout {

    private enum ColumnBlock {
        NO,
        AUTHOR_YEAR,
        V10_SI_STUDY_ID,
        V10_SI_REFERENCE_FILES,
        V10_SI_AUTHOR_JOURNAL_YEAR,
        V10_SI_TITLE,
        V10_SI_DOI,
        V10_SC_STUDY_OBJECTIVE,
        V10_SC_STUDY_DESIGN,
        V10_SC_STUDY_DESIGN_OTHER,
        V10_RCI_DATASET_NAME,
        V10_RCI_HC_N,
        V10_RCI_HC_AGE,
        V10_RCI_HC_SEX,
        V10_RCI_IMAGING_MODALITY,
        V10_RCI_ANALYSIS_LEVEL,
        V10_RCI_PREPROCESSING_PIPELINE,
        V10_RCI_QUALITY_CHECKING,
        V10_RCI_SITE_EFFECT_HANDLING,
        V10_NM_MODEL_ORIGIN,
        V10_NM_MODELING_METHOD,
        V10_NM_SOFTWARE_TOOL,
        V10_NM_RESPONSE_VARIABLE,
        V10_NM_PREDICTOR_VARIABLES,
        V10_NM_PREDICTOR_EFFECTS,
        V10_NM_VLDTN_HANDLE_NS,
        V10_NM_VLDTN_SAME_DOMAIN_NONINDEP,
        V10_NM_VLDTN_SAME_DOMAIN_INDEP,
        V10_NM_VLDTN_DIFF_DOMAIN,
        V10_CAA_CLINICAL_DATASET,
        V10_CAA_DISEASES_STUDIED,
        V10_CAA_CLINICAL_GROUPS_N,
        V10_CAA_CLINICAL_GROUPS_AGE,
        V10_CAA_CLINICAL_GROUPS_SEX,
        V10_CAA_DEVIATION_METRIC,
        V10_CAA_ASSOCIATION_ANALYSIS,
        V10_CAA_KEY_FINDINGS_BRIEF,
        V10_CAA_KEY_FINDINGS_DETAILED,
        V10_CAA_KEY_LIMITATIONS,
        V10_CAA_APPLICATION_NOTES,
        V10_GN,
        //V12_HUMAN_LIST,
        V12_SI_STUDY_ID,
        V12_SI_REFERENCE_FILES,
        V12_SI_AUTHOR_JOURNAL_YEAR,
        V12_SI_TITLE,
        V12_NM2_MODELING_METHOD,
        V12_NM2_RESPONSE_VARIABLE
    }

    // 並び替え用の列順（この配列を並べ替えることでヘッダと行の順序が変わる）
    private static final ColumnBlock[] COLUMN_ORDER = new ColumnBlock[]{
            ColumnBlock.NO,
            ColumnBlock.AUTHOR_YEAR,
            //ColumnBlock.V10_SI_STUDY_ID,
            //ColumnBlock.V10_SI_TITLE,
            //ColumnBlock.V10_SI_DOI,
            //ColumnBlock.V10_SC_STUDY_DESIGN,
            //ColumnBlock.V10_SC_STUDY_DESIGN_OTHER,

            ColumnBlock.V10_RCI_DATASET_NAME,
            ColumnBlock.V10_RCI_HC_N,
            ColumnBlock.V10_RCI_HC_AGE,
            ColumnBlock.V10_RCI_HC_SEX,
            ColumnBlock.V10_RCI_IMAGING_MODALITY,
            ColumnBlock.V10_RCI_ANALYSIS_LEVEL,
            ColumnBlock.V10_RCI_PREPROCESSING_PIPELINE,
            ColumnBlock.V10_RCI_QUALITY_CHECKING,

            ColumnBlock.V10_NM_MODEL_ORIGIN,
            ColumnBlock.V10_NM_MODELING_METHOD,
            ColumnBlock.V12_NM2_MODELING_METHOD,
            ColumnBlock.V10_NM_RESPONSE_VARIABLE,
            ColumnBlock.V12_NM2_RESPONSE_VARIABLE,
            ColumnBlock.V10_NM_PREDICTOR_VARIABLES,
            ColumnBlock.V10_NM_PREDICTOR_EFFECTS,
            ColumnBlock.V10_NM_SOFTWARE_TOOL,
            ColumnBlock.V10_RCI_SITE_EFFECT_HANDLING,

            ColumnBlock.V10_NM_VLDTN_HANDLE_NS,
            ColumnBlock.V10_NM_VLDTN_SAME_DOMAIN_NONINDEP,
            ColumnBlock.V10_NM_VLDTN_DIFF_DOMAIN,
            ColumnBlock.V10_NM_VLDTN_SAME_DOMAIN_INDEP,

            ColumnBlock.V10_CAA_CLINICAL_DATASET,
            ColumnBlock.V10_CAA_DISEASES_STUDIED,
            ColumnBlock.V10_CAA_CLINICAL_GROUPS_N,
            ColumnBlock.V10_CAA_CLINICAL_GROUPS_AGE,
            ColumnBlock.V10_CAA_CLINICAL_GROUPS_SEX,
            ColumnBlock.V10_CAA_DEVIATION_METRIC,
            ColumnBlock.V10_CAA_ASSOCIATION_ANALYSIS,
            //ColumnBlock.V10_CAA_KEY_LIMITATIONS,
            //ColumnBlock.V10_CAA_APPLICATION_NOTES,

            //ColumnBlock.V12_SI_STUDY_ID,
            //ColumnBlock.V12_SI_AUTHOR_JOURNAL_YEAR,
            ColumnBlock.V12_SI_TITLE,
            ColumnBlock.V10_SC_STUDY_OBJECTIVE,
            ColumnBlock.V10_CAA_KEY_FINDINGS_BRIEF,
            //ColumnBlock.V10_CAA_KEY_FINDINGS_DETAILED,
            //ColumnBlock.V10_GN,
            //ColumnBlock.V12_SI_REFERENCE_FILES,
            ColumnBlock.V10_SI_AUTHOR_JOURNAL_YEAR


    };

    // 0-based index order設定（空なら従来のデフォルト順）。例: SI の index=3 が SI4、index=4 が SI5。
    private static final List<Integer> ORDER_V10_SI = List.of();
    private static final List<Integer> ORDER_V10_SC = List.of();
    private static final List<Integer> ORDER_V10_RCI = List.of();
    private static final List<Integer> ORDER_V10_NM = List.of();
    private static final List<Integer> ORDER_V10_CAA = List.of(); // index7 は Findings 用なので既定では除外
    private static final List<Integer> ORDER_V10_GN = List.of();
    private static final List<Integer> ORDER_V12_SI = List.of();
    private static final List<Integer> ORDER_V12_NM2 = List.of();

    private enum SectionType {
        NO, AUTHOR_YEAR, V12_HUMAN_LIST,
        V10_SI, V10_SC, V10_RCI, V10_NM, V10_CAA, V10_GN,
        V12_SI, V12_NM2
    }

    private static class BlockIndex {
        final SectionType section;
        final int index; // 0-based, -1 if not indexed
        final boolean isV12;

        BlockIndex(SectionType section, int index, boolean isV12) {
            this.section = section;
            this.index = index;
            this.isV12 = isV12;
        }
    }

    final static String DATA_FOLDER_NAME = "share_package/data";
    final static String JAR_WORKING_DIR = "share_package";
    final static String DEQACheckJar = "share_package/jar/DEQACheck-v20260107-all.jar";
    final static String TEMPLATE_FOR_HUMAN_DE = "share_package/templates/DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v10_1.json";

    // ソートオプション
    private static final String SORT_BY_YEAR = "年数順 (Study Name内)";
    private static final String SORT_BY_N = "N (サンプルサイズ) 順";
    private static final String SORT_BY_ALPHABET = "アルファベット順 (Study Name)";
    // インスタンス変数
    private List<RowObject> rowsForV10;
    private List<RowObject> rowsForV12;
    private Div scrollWrapper;
    private boolean useNormalizedModality = true; // Modality(RCI5)の表示を正規化するか
    private Anchor downloadAnchor;
    private Map<String, List<RowObject>> rowsByAuthorYearForV12;
    private int v12SizeSI;
    private int v12SizeNM;
    private Map<String, V12Aggregated> v12AggregateCache = new HashMap<>();

    private List<Integer> resolveOrder(List<Integer> customOrder, int size, int defaultStart, IntPredicate allowed) {
        LinkedHashSet<Integer> order = new LinkedHashSet<>();
        if (customOrder != null && !customOrder.isEmpty()) {
            for (Integer idx : customOrder) {
                if (idx == null) continue;
                if (idx < 0 || idx >= size) continue;
                if (allowed != null && !allowed.test(idx)) continue;
                order.add(idx);
            }
        } else {
            for (int idx = defaultStart; idx < size; idx++) {
                if (allowed == null || allowed.test(idx)) {
                    order.add(idx);
                }
            }
        }
        return new ArrayList<>(order);
    }

    private List<Integer> getV10SiOrder(int size) {
        return resolveOrder(ORDER_V10_SI, size, 3, idx -> idx != 3 && idx != 4);
    }

    private List<Integer> getV10ScOrder(int size) {
        return resolveOrder(ORDER_V10_SC, size, 3, null);
    }

    private List<Integer> getV10RciOrder(int size) {
        return resolveOrder(ORDER_V10_RCI, size, 0, null);
    }

    private List<Integer> getV10NmOrder(int size) {
        return resolveOrder(ORDER_V10_NM, size, 0, null);
    }

    private List<Integer> getV10CaaOrder(int size) {
        return resolveOrder(ORDER_V10_CAA, size, 0, idx -> idx != 7);
    }

    private List<Integer> getV10GnOrder(int size) {
        return resolveOrder(ORDER_V10_GN, size, 0, null);
    }

    private List<Integer> getV12SiOrder() {
        return resolveOrder(ORDER_V12_SI, v12SizeSI, 0, null);
    }

    private List<Integer> getV12Nm2Order() {
        return resolveOrder(ORDER_V12_NM2, v12SizeNM, 0, null);
    }

    private BlockIndex parseIndexedBlock(ColumnBlock block) {
        switch (block) {
            case V10_SI_STUDY_ID:
                return new BlockIndex(SectionType.V10_SI, 0, false);
            case V10_SI_REFERENCE_FILES:
                return new BlockIndex(SectionType.V10_SI, 1, false);
            case V10_SI_AUTHOR_JOURNAL_YEAR:
                return new BlockIndex(SectionType.V10_SI, 2, false);
            case V10_SI_TITLE:
                return new BlockIndex(SectionType.V10_SI, 3, false);
            case V10_SI_DOI:
                return new BlockIndex(SectionType.V10_SI, 4, false);
            case V10_SC_STUDY_OBJECTIVE:
                return new BlockIndex(SectionType.V10_SC, 0, false);
            case V10_SC_STUDY_DESIGN:
                return new BlockIndex(SectionType.V10_SC, 1, false);
            case V10_SC_STUDY_DESIGN_OTHER:
                return new BlockIndex(SectionType.V10_SC, 2, false);
            case V10_RCI_DATASET_NAME:
                return new BlockIndex(SectionType.V10_RCI, 0, false);
            case V10_RCI_HC_N:
                return new BlockIndex(SectionType.V10_RCI, 1, false);
            case V10_RCI_HC_AGE:
                return new BlockIndex(SectionType.V10_RCI, 2, false);
            case V10_RCI_HC_SEX:
                return new BlockIndex(SectionType.V10_RCI, 3, false);
            case V10_RCI_IMAGING_MODALITY:
                return new BlockIndex(SectionType.V10_RCI, 4, false);
            case V10_RCI_ANALYSIS_LEVEL:
                return new BlockIndex(SectionType.V10_RCI, 5, false);
            case V10_RCI_PREPROCESSING_PIPELINE:
                return new BlockIndex(SectionType.V10_RCI, 6, false);
            case V10_RCI_QUALITY_CHECKING:
                return new BlockIndex(SectionType.V10_RCI, 7, false);
            case V10_RCI_SITE_EFFECT_HANDLING:
                return new BlockIndex(SectionType.V10_RCI, 8, false);
            case V10_NM_MODEL_ORIGIN:
                return new BlockIndex(SectionType.V10_NM, 0, false);
            case V10_NM_MODELING_METHOD:
                return new BlockIndex(SectionType.V10_NM, 1, false);
            case V10_NM_SOFTWARE_TOOL:
                return new BlockIndex(SectionType.V10_NM, 2, false);
            case V10_NM_RESPONSE_VARIABLE:
                return new BlockIndex(SectionType.V10_NM, 3, false);
            case V10_NM_PREDICTOR_VARIABLES:
                return new BlockIndex(SectionType.V10_NM, 4, false);
            case V10_NM_PREDICTOR_EFFECTS:
                return new BlockIndex(SectionType.V10_NM, 5, false);
            case V10_NM_VLDTN_HANDLE_NS:
                return new BlockIndex(SectionType.V10_NM, 6, false);
            case V10_NM_VLDTN_SAME_DOMAIN_NONINDEP:
                return new BlockIndex(SectionType.V10_NM, 7, false);
            case V10_NM_VLDTN_SAME_DOMAIN_INDEP:
                return new BlockIndex(SectionType.V10_NM, 8, false);
            case V10_NM_VLDTN_DIFF_DOMAIN:
                return new BlockIndex(SectionType.V10_NM, 9, false);
            case V10_CAA_CLINICAL_DATASET:
                return new BlockIndex(SectionType.V10_CAA, 0, false);
            case V10_CAA_DISEASES_STUDIED:
                return new BlockIndex(SectionType.V10_CAA, 1, false);
            case V10_CAA_CLINICAL_GROUPS_N:
                return new BlockIndex(SectionType.V10_CAA, 2, false);
            case V10_CAA_CLINICAL_GROUPS_AGE:
                return new BlockIndex(SectionType.V10_CAA, 3, false);
            case V10_CAA_CLINICAL_GROUPS_SEX:
                return new BlockIndex(SectionType.V10_CAA, 4, false);
            case V10_CAA_DEVIATION_METRIC:
                return new BlockIndex(SectionType.V10_CAA, 5, false);
            case V10_CAA_ASSOCIATION_ANALYSIS:
                return new BlockIndex(SectionType.V10_CAA, 6, false);
            case V10_CAA_KEY_FINDINGS_BRIEF:
                return new BlockIndex(SectionType.V10_CAA, 7, false);
            case V10_CAA_KEY_FINDINGS_DETAILED:
                return new BlockIndex(SectionType.V10_CAA, 8, false);
            case V10_CAA_KEY_LIMITATIONS:
                return new BlockIndex(SectionType.V10_CAA, 9, false);
            case V10_CAA_APPLICATION_NOTES:
                return new BlockIndex(SectionType.V10_CAA, 10, false);
            case V12_SI_STUDY_ID:
                return new BlockIndex(SectionType.V12_SI, 0, true);
            case V12_SI_REFERENCE_FILES:
                return new BlockIndex(SectionType.V12_SI, 1, true);
            case V12_SI_AUTHOR_JOURNAL_YEAR:
                return new BlockIndex(SectionType.V12_SI, 2, true);
            case V12_SI_TITLE:
                return new BlockIndex(SectionType.V12_SI, 3, true);
            case V12_NM2_MODELING_METHOD:
                return new BlockIndex(SectionType.V12_NM2, 0, true);
            case V12_NM2_RESPONSE_VARIABLE:
                return new BlockIndex(SectionType.V12_NM2, 1, true);
            default:
                return null;
        }
    }


    public SummaryView_DEv10_2() {
        // layout settings
        getStyle().set("padding", "var(--lumo-space-m)");
        setSpacing(true);
        setWidthFull();

        add(new H2("DEQAData/AuthorYear/DE_v10/json/*human*.json 一覧"));

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
            // messageの内容例
            //- テンプレート未発見: 「テンプレートファイルが見つかりません: <絶対パス>」
            //- jsonディレクトリ作成失敗: 「: jsonディレクトリの作成に失敗 - <エラー内容>」
            //- ファイル確認失敗: 「: ファイルの確認に失敗 - <エラー内容>」
            //- 生成成功: 「生成しました: DE_by_human<タイムスタンプ>.json」
            //- 生成失敗: 「: ファイルの生成に失敗 - <エラー内容>」
            add(new Paragraph(message));
        }

        // デモ用データ（行データ構築は外部クラスへ切り出し）
        rowsForV10 = SummaryRowsBuilder_for_DE_v10.constructRowObjectList(
                pathListOfAuthorYearDir,
                msg -> add(new Paragraph(msg))
        );
        if (rowsForV10.isEmpty()) {
            add(new Paragraph("Human系JSONファイルが見つかりません。"));
            return;
        }

        rowsForV12 = SummaryRowsBuilder_for_DE_v12.constructRowObjectList(
                pathListOfAuthorYearDir,
                msg -> add(new Paragraph(msg))
        );
        rowsByAuthorYearForV12 = rowsForV12.stream().collect(Collectors.groupingBy(r -> r.authorYear));
        computeV12LayoutMetrics(rowsForV12);


        // リロードボタンとソートUIを横並びに配置
        HorizontalLayout controlLayout = new HorizontalLayout();
        controlLayout.setAlignItems(Alignment.BASELINE);
        controlLayout.setSpacing(true);

        Button reloadButton = new Button("リロード", e -> UI.getCurrent().getPage().reload());
        controlLayout.add(reloadButton);

        // ソート用ComboBox
        ComboBox<String> sortComboBox = new ComboBox<>("ソート順");
        sortComboBox.setItems(SORT_BY_ALPHABET, SORT_BY_YEAR, SORT_BY_N);
        sortComboBox.setValue(SORT_BY_ALPHABET);
        sortComboBox.setWidth("250px");
        controlLayout.add(sortComboBox);

        // Modality表示: Raw/Normalized トグル
        Checkbox normalizeToggle = new Checkbox("Normalized Modality", true);
        normalizeToggle.addValueChangeListener(e -> {
            useNormalizedModality = Boolean.TRUE.equals(e.getValue());
            rebuildTable();
        });
        controlLayout.add(normalizeToggle);

        // ダウンロード用アンカー（非表示）を先に作成し、UIにアタッチしておく
        downloadAnchor = new Anchor();
        downloadAnchor.getStyle().set("display", "none");
        downloadAnchor.getElement().setAttribute("download", true);
        add(downloadAnchor);

        // SORTボタン
        Button sortButton = new Button("SORT", e -> {
            String selected = sortComboBox.getValue();
            if (selected != null) {
                sortRows(selected);
                rebuildTable();
            }
        });
        sortButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        controlLayout.add(sortButton);

        // TSV ダウンロードボタン
        Button downloadButton = new Button("TSVダウンロード");
        downloadButton.addClickListener(e -> {
            String tsv = buildTsv();
            if (tsv == null || tsv.isEmpty()) {
                Notification.show("TSVを生成できませんでした");
                return;
            }
            StreamResource resource = new StreamResource(
                    "summary-view-2.tsv",
                    () -> new ByteArrayInputStream(tsv.getBytes(StandardCharsets.UTF_8))
            );
            downloadAnchor.setHref(resource);
            downloadAnchor.getElement().callJsFunction("click");
        });
        controlLayout.add(downloadButton);

        add(controlLayout);

        add(new Paragraph("チェックの入ったチェックボックス（☑）は _DE_AuthorXXXX_by_Human_.json のAnswer欄に何らかの入力がなされていることを意味します。マウスポインタを重ねるとその値が参照できます。（なお、チェックを入れたり消したりしてもjsonファイルには何ら影響は及ぼしません。）なお、RCI3 列は年齢の擬似ボックスプロット、RCI4 列は性比の円グラフ（F=ピンク, M=青, NR=灰）を表示します。"));

        // 戻るリンク
        add(new RouterLink("メインページへ戻る", MainView.class));

        // Gridは使わず、Vaadinの要素APIとコンポーネントでtableを構築
        scrollWrapper = new Div();
        scrollWrapper.getStyle().set("max-height", "70vh");
        scrollWrapper.getStyle().set("overflow", "auto");
        scrollWrapper.getStyle().set("border", "1px solid var(--lumo-contrast-20pct)");

        // テーブルを構築
        rebuildTable();

        add(scrollWrapper);


        // 戻るリンク
        add(new RouterLink("メインページへ戻る", MainView.class));
    }

    // ソート処理
    private void sortRows(String sortOption) {
        if (rowsForV10 == null || rowsForV10.isEmpty()) return;

        Comparator<RowObject> comparator;
        switch (sortOption) {
            case SORT_BY_YEAR:
                // Study Name から年数を抽出してソート
                comparator = Comparator.comparingInt(this::extractYearFromStudyName);
                break;
            case SORT_BY_N:
                // N（サンプルサイズ）でソート（RCI2）
                comparator = Comparator.comparingInt(this::extractNFromRow);
                break;
            case SORT_BY_ALPHABET:
            default:
                // Study Name（SI3）のアルファベット順
                comparator = Comparator.comparing(
                        row -> getStudyName(row).toLowerCase(Locale.ROOT)
                );
                break;
        }
        rowsForV10.sort(comparator);
    }

    // Study Nameを取得
    private String getStudyName(RowObject row) {
        return (row.valueList_SI.size() >= 3) ? row.valueList_SI.get(2) : "";
    }

    private String getDoi(RowObject row) {
        if (row.valueList_SI == null || row.valueList_SI.size() <= 4) return "";
        String doi = row.valueList_SI.get(4);
        return doi == null ? "" : doi;
    }

    private boolean hasFindingsColumn() {
        return rowsForV10 != null && !rowsForV10.isEmpty() && rowsForV10.get(0).valueList_CAA.size() >= 8;
    }

    // Study Name から年数を抽出（例: "Bayer2022" → 2022）
    private int extractYearFromStudyName(RowObject row) {
        String studyName = getStudyName(row);
        java.util.regex.Matcher m = java.util.regex.Pattern
                .compile("(\\d{4})")
                .matcher(studyName);
        if (m.find()) {
            try {
                return Integer.parseInt(m.group(1));
            } catch (NumberFormatException ignore) {
            }
        }
        return 0; // 年数が見つからない場合は0
    }

    // N（サンプルサイズ）を抽出（RCI2から）
    private int extractNFromRow(RowObject row) {
        if (row.valueList_RCI.size() >= 2) {
            String nStr = row.valueList_RCI.get(1);
            if (nStr != null && !nStr.isEmpty()) {
                // 数字のみを抽出
                String digits = nStr.replaceAll("[^0-9]", "");
                if (!digits.isEmpty()) {
                    try {
                        return Integer.parseInt(digits);
                    } catch (NumberFormatException ignore) {
                    }
                }
            }
        }
        return 0; // Nが見つからない場合は0
    }

    private void computeV12LayoutMetrics(List<RowObject> v12Rows) {
        v12SizeSI = maxSectionSize(v12Rows, r -> r.valueList_SI);
        v12SizeNM = maxSectionSize(v12Rows, r -> r.valueList_NM);
    }

    private int maxSectionSize(List<RowObject> list, Function<RowObject, List<String>> getter) {
        if (list == null || list.isEmpty()) return 0;
        return list.stream()
                .map(getter)
                .filter(Objects::nonNull)
                .mapToInt(List::size)
                .max()
                .orElse(0);
    }

    private boolean hasV12Data() {
        return rowsByAuthorYearForV12 != null && !rowsByAuthorYearForV12.isEmpty();
    }

    // テーブルを再構築
    private void rebuildTable() {
        // 既存のテーブルをクリア
        scrollWrapper.getElement().removeAllChildren();

        // 列幅・整列などのスタイルは CSS ファイル（frontend/styles/summary-table.css）に分離

        Element table = new Element("table");
        table.setAttribute("style", "border-collapse: collapse; width: 100%; font-size: var(--lumo-font-size-m);");
        table.setAttribute("class", "summary-table");

        // thead
        Element thead = new Element("thead");
        Element trHead = new Element("tr");

        boolean hasFindings = hasFindingsColumn();

        for (ColumnBlock block : COLUMN_ORDER) {
            appendHeadersForBlock(block, trHead, hasFindings);
        }

        // 最終列にランチャーは置かない（先頭列に移動）
        thead.appendChild(trHead);
        table.appendChild(thead);

        // tbody
        Element tbody = new Element("tbody");
        boolean even = false;
        for (int r = 0; r < rowsForV10.size(); r++) {
            RowObject row = rowsForV10.get(r);
            Element tr = new Element("tr");
            if (even) {
                tr.setAttribute("style", "background: var(--lumo-contrast-5pct);");
            }
            V12Aggregated v12Agg = getV12Aggregated(row.authorYear);
            for (ColumnBlock block : COLUMN_ORDER) {
                appendCellsForBlock(block, tr, row, hasFindings, v12Agg, r);
            }

            // 末尾列へのランチャー配置は廃止（先頭列に集約）
            tbody.appendChild(tr);
            even = !even;
        }
        table.appendChild(tbody);

        scrollWrapper.getElement().appendChild(table);
    }

    // ヘッダセル（Element API）
    private void appendHeaderCell(Element tr, String text) {
        Element th = new Element("th");
        th.setAttribute("style", "position: sticky; top: 0; z-index: 1; text-align: center; background: var(--lumo-contrast-10pct); border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m);");
        th.setText(text == null ? "" : text);
        tr.appendChild(th);
    }

    private void appendHeaderCellWithStyle(Element tr, String text, String extraCss) {
        Element th = new Element("th");
        String base = "position: sticky; top: 0; z-index: 1; text-align: center; background: var(--lumo-contrast-10pct); border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m);";
        th.setAttribute("style", base + (extraCss != null ? (" " + extraCss) : ""));
        th.setText(text == null ? "" : text);
        tr.appendChild(th);
    }

    private String getSiHeaderLabel(int idx, boolean isV12) {
        return (isV12 ? "v12-SI" : "SI") + (idx + 1);
    }

    private String getV12SiDisplayLabel(int idx) {
        switch (idx) {
            case 0:
                return "v12 Study ID (SI1)";
            case 1:
                return "v12 Reference Files (SI2)";
            case 2:
                return "v12 Author/Journal/Year (SI3)";
            case 3:
                return "v12 Title (SI4)";
            default:
                return getSiHeaderLabel(idx, true);
        }
    }

    private String getScHeaderLabel(int idx, boolean isV12) {
        return (isV12 ? "v12-SC" : "SC") + (idx + 1);
    }

    private void appendRciHeaderCell(Element trHead, int idx, boolean isV12) {
        String prefix = isV12 ? "v12 " : "";
        if (idx == 0) {
            appendHeaderCellWithStyle(trHead, prefix + "Dataset", "width:16ch;");
        } else if (idx == 1) {
            appendHeaderCellWithStyle(trHead, prefix + "N", "text-align: center;");
        } else if (idx == 2) {
            appendHeaderCell(trHead, prefix + "RC Age");
        } else if (idx == 3) {
            appendHeaderCell(trHead, prefix + "Sex");
        } else if (idx == 4) {
            appendHeaderCell(trHead, prefix + "Modality");
        } else {
            String label = (isV12 ? "v12-RCI" : "RCI") + (idx + 1);
            appendHeaderCell(trHead, label);
        }
    }

    private String getRciHeaderLabel(int idx, boolean isV12) {
        String prefix = isV12 ? "v12 " : "";
        if (idx == 0) return prefix + "Dataset";
        if (idx == 1) return prefix + "N";
        if (idx == 2) return prefix + "RC Age";
        if (idx == 3) return prefix + "Sex";
        if (idx == 4) return prefix + "Modality";
        return (isV12 ? "v12-RCI" : "RCI") + (idx + 1);
    }

    private String getNmHeaderLabel(int idx, boolean isV12) {
        if (isV12) {
            if (idx == 0) return "v12 Modeling Method";
            if (idx == 1) return "v12\nResponse Variable";
            return "v12-NM" + (idx + 1);
        } else {
            if (idx == 0) return "Origin";
            return "NM" + (idx + 1);
        }
    }

    private String getCaaHeaderLabel(int idx, boolean isV12) {
        if (idx == 1) {
            return isV12 ? "v12 Disease" : "Disease";
        }
        if (isV12) {
            return "v12-CAA" + (idx + 1);
        }
        return "CAA" + (idx + 1);
    }

    private String getGnHeaderLabel(int idx, boolean isV12) {
        return (isV12 ? "v12-GN" : "GN") + (idx + 1);
    }

    private String getV10SiDisplayLabel(int idx) {
        switch (idx) {
            case 0:
                return "Study ID (SI1)";
            case 1:
                return "Reference Files (SI2)";
            case 2:
                return "Author/Journal/Year (SI3)";
            case 3:
                return "Title (SI4)";
            case 4:
                return "DOI (SI5)";
            default:
                return getSiHeaderLabel(idx, false);
        }
    }

    private String getV10ScDisplayLabel(int idx) {
        if (idx == 0) return "Study Objective (SC1)";
        if (idx == 1) return "Study Design (SC2)";
        if (idx == 2) return "Study Design Other (SC3)";
        return getScHeaderLabel(idx, false);
    }

    private String getV10NmDisplayLabel(int idx) {
        switch (idx) {
            case 0:
                return "Model Origin (NM1)";
            case 1:
                return "Modeling Method (NM2)";
            case 2:
                return "Software/Tool (NM3)";
            case 3:
                return "Response Variable (NM4)";
            case 4:
                return "Predictor Variables (NM5)";
            case 5:
                return "Predictor Effects (NM6)";
            case 6:
                return "Validation Handle Ns (NM7)";
            case 7:
                return "Validation Same Domain Nonindep (NM8)";
            case 8:
                return "Validation Same Domain Indep (NM9)";
            case 9:
                return "Validation Diff Domain (NM10)";
            default:
                return getNmHeaderLabel(idx, false);
        }
    }

    private String getV10CaaDisplayLabel(int idx) {
        switch (idx) {
            case 0:
                return "Clinical Dataset (CAA1)";
            case 1:
                return "Diseases Studied (CAA2)";
            case 2:
                return "Clinical Groups N (CAA3)";
            case 3:
                return "Clinical Groups Age (CAA4)";
            case 4:
                return "Clinical Groups Sex (CAA5)";
            case 5:
                return "Deviation Metric (CAA6)";
            case 6:
                return "Association Analysis (CAA7)";
            case 7:
                return "Key Findings Brief (CAA8)";
            case 8:
                return "Key Findings Detailed (CAA9)";
            case 9:
                return "Key Limitations (CAA10)";
            case 10:
                return "Application Notes (CAA11)";
            default:
                return getCaaHeaderLabel(idx, false);
        }
    }

    private boolean appendIndexedHeader(ColumnBlock block, Element trHead) {
        BlockIndex info = parseIndexedBlock(block);
        if (info == null || info.index < 0) return false;
        switch (info.section) {
            case V10_SI:
                appendHeaderCell(trHead, getV10SiDisplayLabel(info.index));
                return true;
            case V10_SC:
                appendHeaderCell(trHead, getV10ScDisplayLabel(info.index));
                return true;
            case V10_RCI:
                appendRciHeaderCell(trHead, info.index, false);
                return true;
            case V10_NM:
                appendHeaderCell(trHead, getV10NmDisplayLabel(info.index));
                return true;
            case V10_CAA:
                appendHeaderCell(trHead, getV10CaaDisplayLabel(info.index));
                return true;
            case V12_SI:
                appendHeaderCell(trHead, getV12SiDisplayLabel(info.index));
                return true;
            case V12_NM2:
                appendHeaderCell(trHead, getNmHeaderLabel(info.index, true));
                return true;
            default:
                return false;
        }
    }

    private void appendHeadersForBlock(ColumnBlock block, Element trHead, boolean hasFindings) {
        if (appendIndexedHeader(block, trHead)) return;

        switch (block) {
            case NO:
                appendHeaderCell(trHead, "No");
                break;
            case AUTHOR_YEAR:
                appendHeaderCell(trHead, "AuthorYear");
                break;
            case V10_GN: {
                List<Integer> order = getV10GnOrder(rowsForV10.get(0).valueList_GN.size());
                for (int idx : order) {
                    appendHeaderCell(trHead, getGnHeaderLabel(idx, false));
                }
                break;
            }
            default:
                break;
        }
    }

    private void appendV12HeaderCells(Element trHead) {
        if (!hasV12Data()) return;

        for (int idx : getV12SiOrder()) {
            appendHeaderCell(trHead, getSiHeaderLabel(idx, true));
        }
        for (int idx : getV12Nm2Order()) {
            appendHeaderCell(trHead, getNmHeaderLabel(idx, true));
        }
    }

    // 通常セル（Element API）
    private void appendNormalCell(Element tr, String text) {
        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m);");
        td.setText(text == null ? "" : text);
        tr.appendChild(td);
    }

    private void appendDatasetCell(Element tr, String text) {
        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m); width:16ch; max-width:16ch; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;");
        td.setText(text == null ? "" : text);
        tr.appendChild(td);
    }

    // RCI5: Modality 表示（Raw/Normalized 切替、ツールチップに元文字列、欠損はNA）
    private void appendModalityCell(Element tr, String raw) {
        String tooltip = (raw == null || raw.trim().isEmpty()) ? "NA" : raw.trim();
        String display = getDisplayedModality(raw);

        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m);");
        td.setAttribute("title", tooltip);
        td.setText(display);
        tr.appendChild(td);
    }

    private String getDisplayedModality(String raw) {
        String display;
        if (isMissingOrInvalidModality(raw)) {
            display = "NA";
        } else if (useNormalizedModality) {
            String norm = normalizeModality(raw);
            display = (norm == null || norm.isEmpty()) ? "NA" : norm;
        } else {
            display = stripTrailingPeriod(raw.trim());
        }
        return display;
    }

    private boolean isMissingOrInvalidModality(String raw) {
        if (raw == null) return true;
        String s = raw.trim();
        if (s.isEmpty()) return true;
        String lower = s.toLowerCase(Locale.ROOT);
        return lower.equals("nr") || lower.equals("na") || lower.equals("n/a") || lower.equals("yes")
                || lower.contains("not reported");
    }

    private String stripTrailingPeriod(String s) {
        if (s == null) return null;
        String t = s.trim();
        if (t.endsWith(".")) t = t.substring(0, t.length() - 1);
        return t;
    }

    // Modality 正規化（カテゴリのみ: T1w MRI / T2w MRI / fMRI / dMRI / PET / EEG / MEG / Others）
    private String normalizeModality(String raw) {
        if (raw == null) return "";
        String s = stripTrailingPeriod(raw).replace('\u3000', ' ').trim();
        if (s.isEmpty()) return "";

        // セミコロンで分割し、各要素をカテゴリにマップ。重複は除去。
        String[] parts = s.split("\\s*;\\s*");
        java.util.LinkedHashSet<String> cats = new java.util.LinkedHashSet<>();
        for (String p0 : parts) {
            String p = p0.trim();
            if (p.isEmpty()) continue;
            String c = classifyModalityCategory(p);
            if (c != null && !c.isEmpty()) cats.add(c);
        }
        return String.join("; ", cats);
    }

    private String classifyModalityCategory(String t) {
        String src = t.trim();
        String lower = src.toLowerCase(Locale.ROOT);

        // T1w MRI
        if (lower.matches(".*\\bt1\\s*-?weighted\\b.*\\bmri\\b.*")
                || lower.contains("structural mri")
                || lower.matches(".*\\bsmri\\b.*")
                || lower.matches(".*\\bt1w\\b.*")) {
            return "T1w MRI";
        }
        // T2w MRI (includes FLAIR)
        if ((lower.contains("t2-weighted") && lower.contains("mri")) || lower.contains("flair")) {
            return "T2w MRI";
        }
        // fMRI (any)
        if (lower.contains("fmri")) {
            return "fMRI";
        }
        // dMRI (includes DTI/DWI/DSI)
        if (lower.contains("diffusion") || lower.contains("dwi") || lower.contains("dti") || lower.contains("dmri") || lower.contains("dsi")) {
            return "dMRI";
        }
        // PET (any)
        if (lower.contains("pet")) {
            return "PET";
        }
        // EEG
        if (lower.contains("eeg")) {
            return "EEG";
        }
        // MEG
        if (lower.contains("meg")) {
            return "MEG";
        }
        // Others
        return "Others";
    }

    private String canonicalizeFmriDetail(String d) {
        String lower = d.toLowerCase(Locale.ROOT);
        if (lower.contains("bold")) return "BOLD";
        if (lower.contains("alff")) return "ALFF";
        if (lower.contains("functional connectivity") || lower.equals("fc")) return "FC";
        return d;
    }

    private String extractDetails(String src, String[] keys) {
        String lower = src.toLowerCase(Locale.ROOT);
        List<String> found = new ArrayList<>();
        // 既存の括弧を優先
        int lp = src.indexOf('(');
        int rp = src.lastIndexOf(')');
        if (lp >= 0 && rp > lp) {
            String in = src.substring(lp + 1, rp).trim();
            if (!in.isEmpty()) found.add(in);
        }
        // キーワード検出
        for (String k : keys) {
            if (lower.contains(k)) {
                String label = k.equals("mp-rage") ? "MPRAGE" : k.toUpperCase(Locale.ROOT);
                if (!containsIgnoreCase(found, label)) found.add(label);
            }
        }
        return String.join(", ", found);
    }

    private String extractMetrics(String lower, String[] metrics) {
        List<String> found = new ArrayList<>();
        for (String m : metrics) {
            if (lower.matches(".*\\b" + java.util.regex.Pattern.quote(m) + "\\b.*")) {
                String up = m.toUpperCase(Locale.ROOT);
                if (up.equals("AXD")) up = "AxD";
                found.add(up);
            }
        }
        return String.join(", ", found);
    }

    private boolean containsIgnoreCase(List<String> list, String s) {
        for (String e : list) {
            if (e.equalsIgnoreCase(s)) return true;
        }
        return false;
    }

    // 中央揃えセル
    private void appendCenteredCell(Element tr, String text) {
        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m); text-align: center;");
        td.setText(text == null ? "" : text);
        tr.appendChild(td);
    }

    // TSV 生成
    private String buildTsv() {
        if (rowsForV10 == null || rowsForV10.isEmpty()) return "";
        boolean hasFindings = hasFindingsColumn();

        List<String> header = buildTsvHeader(hasFindings);
        StringBuilder sb = new StringBuilder();
        sb.append(String.join("\t", header)).append("\n");

        for (int i = 0; i < rowsForV10.size(); i++) {
            RowObject row = rowsForV10.get(i);
            List<String> cols = buildTsvRow(row, hasFindings, i + 1);
            sb.append(cols.stream().map(this::sanitizeForTsv).collect(Collectors.joining("\t"))).append("\n");
        }
        return sb.toString();
    }

    private List<String> buildTsvHeader(boolean hasFindings) {
        List<String> header = new ArrayList<>();
        for (ColumnBlock block : COLUMN_ORDER) {
            appendTsvHeadersForBlock(block, header, hasFindings);
        }
        return header;
    }

    private List<String> buildTsvRow(RowObject row, boolean hasFindings, int displayIndex) {
        List<String> cols = new ArrayList<>();
        V12Aggregated v12Agg = getV12Aggregated(row.authorYear);
        for (ColumnBlock block : COLUMN_ORDER) {
            appendTsvForBlock(block, cols, row, hasFindings, v12Agg, displayIndex);
        }
        return cols;
    }

    private boolean appendIndexedTsvHeaders(ColumnBlock block, List<String> header) {
        BlockIndex info = parseIndexedBlock(block);
        if (info == null || info.index < 0) return false;
        switch (info.section) {
            case V10_SI:
                header.add(getV10SiDisplayLabel(info.index));
                return true;
            case V10_SC:
                header.add(getV10ScDisplayLabel(info.index));
                return true;
            case V10_RCI:
                header.add(getRciHeaderLabel(info.index, false));
                return true;
            case V10_NM:
                header.add(getV10NmDisplayLabel(info.index));
                return true;
            case V10_CAA:
                header.add(getV10CaaDisplayLabel(info.index));
                return true;
            case V12_SI:
                header.add(getV12SiDisplayLabel(info.index));
                return true;
            case V12_NM2:
                header.add(getNmHeaderLabel(info.index, true));
                return true;
            default:
                return false;
        }
    }

    private boolean appendIndexedTsv(ColumnBlock block, List<String> cols, RowObject row, V12Aggregated v12Agg) {
        BlockIndex info = parseIndexedBlock(block);
        if (info == null || info.index < 0) return false;
        switch (info.section) {
            case V10_SI:
                cols.add(row.valueList_SI.size() > info.index ? nullToEmpty(row.valueList_SI.get(info.index)) : "");
                return true;
            case V10_SC:
                cols.add(row.valueList_SC.size() > info.index ? nullToEmpty(row.valueList_SC.get(info.index)) : "");
                return true;
            case V10_RCI: {
                String val = row.valueList_RCI.size() > info.index ? row.valueList_RCI.get(info.index) : "";
                if (info.index == 4) cols.add(getDisplayedModality(val));
                else cols.add(nullToEmpty(val));
                return true;
            }
            case V10_NM:
                cols.add(row.valueList_NM.size() > info.index ? nullToEmpty(row.valueList_NM.get(info.index)) : "");
                return true;
            case V10_CAA:
                cols.add(row.valueList_CAA.size() > info.index ? nullToEmpty(row.valueList_CAA.get(info.index)) : "");
                return true;
            case V12_SI:
                cols.add(getListValue(v12Agg == null ? null : v12Agg.si, info.index));
                return true;
            case V12_NM2:
                cols.add(getListValue(v12Agg == null ? null : v12Agg.nm, info.index));
                return true;
            default:
                return false;
        }
    }

    private void appendTsvHeadersForBlock(ColumnBlock block, List<String> header, boolean hasFindings) {
        if (appendIndexedTsvHeaders(block, header)) return;
        switch (block) {
            case NO:
                header.add("No");
                break;
            case AUTHOR_YEAR:
                header.add("AuthorYear");
                break;
            case V10_GN: {
                List<Integer> order = getV10GnOrder(rowsForV10.get(0).valueList_GN.size());
                for (int idx : order) {
                    header.add(getGnHeaderLabel(idx, false));
                }
                break;
            }
            default:
                break;
        }
    }

    private void appendTsvForBlock(ColumnBlock block, List<String> cols, RowObject row, boolean hasFindings, V12Aggregated v12Agg, int displayIndex) {
        if (appendIndexedTsv(block, cols, row, v12Agg)) return;
        switch (block) {
            case NO:
                cols.add(String.valueOf(displayIndex));
                break;
            case AUTHOR_YEAR:
                cols.add(row.authorYear == null ? "" : row.authorYear);
                break;
            case V10_GN: {
                List<Integer> order = getV10GnOrder(row.valueList_GN.size());
                for (int idx : order) {
                    cols.add(nullToEmpty(row.valueList_GN.get(idx)));
                }
                break;
            }
            default:
                break;
        }
    }

    private String sanitizeForTsv(String s) {
        if (s == null) return "";
        return s.replace("\t", " ").replace("\r", " ").replace("\n", " ").trim();
    }

    private String nullToEmpty(String s) {
        return s == null ? "" : s;
    }

    private String getV12Info(String authorYear) {
        if (authorYear == null || rowsByAuthorYearForV12 == null) return "";
        List<RowObject> list = rowsByAuthorYearForV12.get(authorYear);
        if (list == null || list.isEmpty()) return "";
        return list.stream()
                .map(r -> r.jsonFileName == null ? "" : r.jsonFileName)
                .filter(v -> v != null && !v.isEmpty())
                .distinct()
                .collect(Collectors.joining("\n"));
    }

    private V12Aggregated getV12Aggregated(String authorYear) {
        if (!hasV12Data()) return null;
        if (authorYear == null) return null;
        if (v12AggregateCache.containsKey(authorYear)) {
            return v12AggregateCache.get(authorYear);
        }
        List<RowObject> v12Rows = rowsByAuthorYearForV12.get(authorYear);
        V12Aggregated agg = new V12Aggregated();
        agg.si = aggregateV12Section(v12Rows, r -> r.valueList_SI, v12SizeSI);
        agg.nm = aggregateV12Section(v12Rows, r -> r.valueList_NM, v12SizeNM);
        v12AggregateCache.put(authorYear, agg);
        return agg;
    }

    private List<String> aggregateV12Section(List<RowObject> v12Rows, Function<RowObject, List<String>> getter, int size) {
        if (size <= 0) return Collections.emptyList();
        List<String> result = new ArrayList<>(Collections.nCopies(size, ""));
        if (v12Rows == null || v12Rows.isEmpty()) return result;

        for (int idx = 0; idx < size; idx++) {
            List<String> collected = new ArrayList<>();
            for (RowObject r : v12Rows) {
                List<String> section = getter.apply(r);
                if (section != null && section.size() > idx) {
                    String val = section.get(idx);
                    if (val != null && !val.isEmpty()) {
                        collected.add(val);
                    }
                }
            }
            if (!collected.isEmpty()) {
                result.set(idx, String.join("\n", collected));
            }
        }
        return result;
    }

    private String getListValue(List<String> list, int idx) {
        if (list == null || idx < 0 || idx >= list.size()) return "";
        String val = list.get(idx);
        return val == null ? "" : val;
    }

    private boolean appendIndexedCells(ColumnBlock block, Element tr, RowObject row, boolean hasFindings, V12Aggregated v12Agg) {
        BlockIndex info = parseIndexedBlock(block);
        if (info == null || info.index < 0) return false;

        switch (info.section) {
            case V10_SI:
                if (info.index == 2) {
                    String studyName = getStudyName(row);
                    String si4 = (row.valueList_SI.size() > 3) ? row.valueList_SI.get(3) : "";
                    appendStudyNameLauncherCell(tr, studyName, row.authorYear, si4);
                } else if (info.index == 4) {
                    String doi = row.valueList_SI.size() > info.index ? row.valueList_SI.get(info.index) : "";
                    appendNormalCell(tr, doi);
                } else {
                    if (row.valueList_SI.size() > info.index) appendCheckBoxCell(tr, row.valueList_SI.get(info.index));
                    else appendCheckBoxCell(tr, "");
                }
                return true;
            case V10_SC:
                if (row.valueList_SC.size() > info.index) appendCheckBoxCell(tr, row.valueList_SC.get(info.index));
                else appendCheckBoxCell(tr, "");
                return true;
            case V10_RCI:
                String rciVal = row.valueList_RCI.size() > info.index ? row.valueList_RCI.get(info.index) : "";
                if (info.index == 0) appendDatasetCell(tr, rciVal);
                else if (info.index == 1) appendPreWrappedCell(tr, rciVal, false);
                else if (info.index == 2) appendAgeBoxPlotCell(tr, rciVal);
                else if (info.index == 3) appendSexPieCell(tr, rciVal);
                else if (info.index == 4) appendModalityCell(tr, rciVal);
                else appendCheckBoxCell(tr, rciVal);
                return true;
            case V10_NM:
                String nmVal = row.valueList_NM.size() > info.index ? row.valueList_NM.get(info.index) : "";
                if (info.index == 0) appendNormalCell(tr, nmVal);
                else appendCheckBoxCell(tr, nmVal);
                return true;
            case V10_CAA:
                String caaVal = row.valueList_CAA.size() > info.index ? row.valueList_CAA.get(info.index) : "";
                if (info.index == 1) appendNormalCell(tr, caaVal);
                else appendCheckBoxCell(tr, caaVal);
                return true;
            case V12_SI:
                if (hasV12Data() && v12Agg != null) {
                    appendCheckBoxCell(tr, getListValue(v12Agg.si, info.index));
                } else {
                    appendCheckBoxCell(tr, "");
                }
                return true;
            case V12_NM2:
                if (hasV12Data() && v12Agg != null) {
                    String val = getListValue(v12Agg.nm, info.index);
                    if (info.index == 0) appendNormalCell(tr, val);
                    else if (info.index == 1) appendPreWrappedCell(tr, val, false);
                    else appendCheckBoxCell(tr, val);
                } else {
                    appendCheckBoxCell(tr, "");
                }
                return true;
            default:
                return false;
        }
    }

    private void appendCellsForBlock(ColumnBlock block, Element tr, RowObject row, boolean hasFindings, V12Aggregated v12Agg, int rowIndex) {
        if (appendIndexedCells(block, tr, row, hasFindings, v12Agg)) return;
        switch (block) {
            case NO:
                appendCenteredCell(tr, String.valueOf(rowIndex + 1));
                break;
            case AUTHOR_YEAR:
                appendLauncherCell(tr, row.authorYear, row.authorYear);
                break;
            case V10_GN: {
                List<Integer> order = getV10GnOrder(row.valueList_GN.size());
                for (int idx : order) {
                    appendCheckBoxCell(tr, row.valueList_GN.get(idx));
                }
                break;
            }
            default:
                break;
        }
    }

    private static class V12Aggregated {
        List<String> si;
        List<String> nm;
    }

    // 改行表示に対応したセル（white-space: pre-wrap）
    private void appendPreWrappedCell(Element tr, String text, boolean center) {
        Element td = new Element("td");
        String style = "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m); white-space: pre-wrap;";
        if (center) style += " text-align: center;";
        else style += " text-align: left;";
        td.setAttribute("style", style);
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

    // チェックボックスセル（中央揃えオプション）
    private void appendCheckBoxCell(Element tr, String text, boolean center) {
        Element td = new Element("td");
        String base = "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m);";
        if (center) {
            base += " text-align: center;";
        }
        td.setAttribute("style", base);

        Element checkbox = new Element("input");
        checkbox.setAttribute("type", "checkbox");

        boolean checked = (text != null && !text.isEmpty());
        checkbox.setProperty("checked", checked);
        checkbox.setAttribute("title", text == null ? "" : text);

        td.appendChild(checkbox);
        tr.appendChild(td);
    }

    // Title 列は廃止（SI4 は Study Name のツールチップで表示）

    // RCI4: 性比を小さな円グラフで表示（F=ピンク, M=青, NR=灰）
    // 形式: "Phase: Uninvestigated | F 99 (17.4%), M 470 (82.6%)"
    // 「|」より左側はPhase情報（円グラフの上に表示）、右側は性比データ（円グラフ描画）
    private void appendSexPieCell(Element tr, String text) {
        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m);");

        // 「|」で分割してPhase情報と性比データを取得
        String phaseInfo = null;
        String sexDataForPie = text;

        if (text != null && text.contains("|")) {
            int pipeIndex = text.indexOf("|");
            phaseInfo = text.substring(0, pipeIndex).trim();
            sexDataForPie = text.substring(pipeIndex + 1).trim();
        }

        Double femalePct = extractFemalePercentFromText(sexDataForPie);

        // コンテナを作成（Phase情報を上、円グラフを下に縦並び）
        Element container = new Element("div");
        container.getStyle().set("display", "flex");
        container.getStyle().set("flex-direction", "column");
        container.getStyle().set("align-items", "center");
        container.getStyle().set("gap", "2px");

        // Phase情報があれば円グラフの上に文字列として表示
        if (phaseInfo != null && !phaseInfo.isEmpty()) {
            Element phaseLabel = new Element("span");
            phaseLabel.setText(phaseInfo);
            phaseLabel.getStyle().set("font-size", "var(--lumo-font-size-xs)");
            phaseLabel.getStyle().set("color", "var(--lumo-secondary-text-color)");
            phaseLabel.getStyle().set("white-space", "nowrap");
            container.appendChild(phaseLabel);
        }

        Element pie = new Element("div");
        pie.setAttribute("title", text == null ? "" : text);
        pie.getStyle().set("width", "16px");
        pie.getStyle().set("height", "16px");
        pie.getStyle().set("border-radius", "50%");
        pie.getStyle().set("display", "inline-block");
        pie.getStyle().set("vertical-align", "middle");
        pie.getStyle().set("border", "1px solid #555555");
        pie.getStyle().set("flex-shrink", "0");

        if (femalePct == null) {
            // NR: 灰色
            pie.getStyle().set("background", "#cccccc");
        } else {
            // conic-gradient で円グラフ（ピンク=F, 青=M）
            String bg = String.format(Locale.US, "conic-gradient(#c90076 0 %.3f%%, #2986cc 0)", femalePct);
            pie.getStyle().set("background", bg);
        }

        container.appendChild(pie);

        td.appendChild(container);
        tr.appendChild(td);
    }

    // テキストから女性比率(%)を抽出。取得不能なら null
    private Double extractFemalePercentFromText(String text) {
        if (text == null) return null;
        String s = text.trim();
        if (s.isEmpty()) return null;
        String lower = s.toLowerCase(Locale.ROOT);
        if ("nr".equals(lower) || lower.contains("not reported")) {
            return null;
        }

        try {
            // パターン1: F ... (xx.x%)
            java.util.regex.Matcher mFParen = java.util.regex.Pattern
                    .compile("F\\s*\\d*\\s*\\(\\s*([0-9]+(?:\\.[0-9]+)?)%\\s*\\)", java.util.regex.Pattern.CASE_INSENSITIVE)
                    .matcher(s);
            if (mFParen.find()) {
                return Double.parseDouble(mFParen.group(1));
            }

            // パターン2: F xx.x%
            java.util.regex.Matcher mF = java.util.regex.Pattern
                    .compile("F\\s*([0-9]+(?:\\.[0-9]+)?)%", java.util.regex.Pattern.CASE_INSENSITIVE)
                    .matcher(s);
            if (mF.find()) {
                return Double.parseDouble(mF.group(1));
            }

            // パターン3: M ... (xx.x%) -> 100 - male
            java.util.regex.Matcher mMParen = java.util.regex.Pattern
                    .compile("M\\s*\\d*\\s*\\(\\s*([0-9]+(?:\\.[0-9]+)?)%\\s*\\)", java.util.regex.Pattern.CASE_INSENSITIVE)
                    .matcher(s);
            if (mMParen.find()) {
                return 100.0 - Double.parseDouble(mMParen.group(1));
            }

            // パターン4: M xx.x% -> 100 - male
            java.util.regex.Matcher mM = java.util.regex.Pattern
                    .compile("M\\s*([0-9]+(?:\\.[0-9]+)?)%", java.util.regex.Pattern.CASE_INSENSITIVE)
                    .matcher(s);
            if (mM.find()) {
                return 100.0 - Double.parseDouble(mM.group(1));
            }

            // パターン5: 個体数から比率を算出 (F nnn, M mmm)
            java.util.regex.Matcher fCount = java.util.regex.Pattern
                    .compile("F\\s*(\\d+)\\b", java.util.regex.Pattern.CASE_INSENSITIVE)
                    .matcher(s);
            java.util.regex.Matcher mCount = java.util.regex.Pattern
                    .compile("M\\s*(\\d+)\\b", java.util.regex.Pattern.CASE_INSENSITIVE)
                    .matcher(s);
            if (fCount.find() && mCount.find()) {
                double f = Double.parseDouble(fCount.group(1));
                double m = Double.parseDouble(mCount.group(1));
                double total = f + m;
                if (total > 0) return 100.0 * f / total;
            }
        } catch (Exception ignore) {
        }
        return null;
    }

    // RCI3: 年齢（HC）の擬似ボックスプロットを表示
    // 形式例: "Model: functional | Phase: Overall | mean: 39.85 weighted | sd: 7.94 pooled | median: NR | iqr: NR | min: 2 inferred | max: 100 inferred"
    // 複数行ある場合は各行ごとにボックスプロットを描画
    private void appendAgeBoxPlotCell(Element tr, String text) {
        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m);");

        if (text == null || text.trim().isEmpty() || text.trim().equalsIgnoreCase("NR")) {
            Element span = new Element("span");
            span.setText("NR");
            td.appendChild(span);
            tr.appendChild(td);
            return;
        }

        // 改行で分割して複数行を処理
        String[] lines = text.split("\\n");

        // 複数行の場合は縦並びコンテナを作成
        Element outerContainer = new Element("div");
        outerContainer.getStyle().set("display", "flex");
        outerContainer.getStyle().set("flex-direction", "column");
        outerContainer.getStyle().set("gap", "4px");

        boolean hasAnyPlot = false;
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            // ラベル部分と統計情報部分を分離
            String label = extractAgeLabel(line);
            AgeStats stats = parseAgeStatsFromLine(line);

            if (!stats.hasAnyNumeric()) {
                // 統計情報がない場合はラベルのみ表示
                if (label != null && !label.isEmpty()) {
                    Element labelSpan = new Element("span");
                    labelSpan.setText(label + ": NR");
                    labelSpan.getStyle().set("font-size", "var(--lumo-font-size-xs)");
                    labelSpan.getStyle().set("color", "var(--lumo-secondary-text-color)");
                    outerContainer.appendChild(labelSpan);
                }
                continue;
            }

            hasAnyPlot = true;

            // 各行のコンテナ（ラベル + ボックスプロット）
            Element rowContainer = new Element("div");
            rowContainer.getStyle().set("display", "flex");
            rowContainer.getStyle().set("align-items", "center");
            rowContainer.getStyle().set("gap", "6px");

            // ラベル表示
            if (label != null && !label.isEmpty()) {
                Element labelSpan = new Element("span");
                labelSpan.setText(label);
                labelSpan.getStyle().set("font-size", "var(--lumo-font-size-xs)");
                labelSpan.getStyle().set("color", "var(--lumo-secondary-text-color)");
                labelSpan.getStyle().set("white-space", "nowrap");
                labelSpan.getStyle().set("min-width", "80px");
                rowContainer.appendChild(labelSpan);
            }

            // ボックスプロット描画
            Element plotContainer = createBoxPlotElement(stats, line);
            rowContainer.appendChild(plotContainer);

            outerContainer.appendChild(rowContainer);
        }

        if (!hasAnyPlot && lines.length == 1) {
            // 単一行で統計情報がない場合
            Element span = new Element("span");
            span.setText("NR");
            td.appendChild(span);
        } else {
            td.appendChild(outerContainer);
        }

        tr.appendChild(td);
    }

    // ラベル部分を抽出（Model: xxx | Phase: yyy の部分）
    private String extractAgeLabel(String line) {
        if (line == null) return null;

        // mean: の前までをラベルとする
        int meanIdx = line.toLowerCase(Locale.ROOT).indexOf("mean:");
        if (meanIdx > 0) {
            String labelPart = line.substring(0, meanIdx).trim();
            // 末尾の | を削除
            if (labelPart.endsWith("|")) {
                labelPart = labelPart.substring(0, labelPart.length() - 1).trim();
            }
            // Model: と Phase: を短縮表示
            labelPart = labelPart.replace("Model: ", "").replace("Phase: ", "");
            // | を / に置換して短く
            labelPart = labelPart.replace(" | ", "/");
            return labelPart;
        }
        return null;
    }

    // 行から統計情報をパース
    private AgeStats parseAgeStatsFromLine(String line) {
        AgeStats s = new AgeStats();
        if (line == null) return s;

        String t = line.replace('\u2013', '-').replace('\u2212', '-').replaceAll(",", ".");
        String lower = t.toLowerCase(Locale.ROOT);

        try {
            // mean: 39.85 weighted のような形式から数値を抽出
            java.util.regex.Matcher mMean = java.util.regex.Pattern
                    .compile("mean:\\s*(-?\\d+(?:\\.\\d+)?)", java.util.regex.Pattern.CASE_INSENSITIVE)
                    .matcher(t);
            if (mMean.find()) s.mean = Double.parseDouble(mMean.group(1));

            // sd: 7.94 pooled
            java.util.regex.Matcher mSd = java.util.regex.Pattern
                    .compile("sd:\\s*(-?\\d+(?:\\.\\d+)?)", java.util.regex.Pattern.CASE_INSENSITIVE)
                    .matcher(t);
            if (mSd.find()) s.sd = Double.parseDouble(mSd.group(1));

            // median: 40.5
            java.util.regex.Matcher mMedian = java.util.regex.Pattern
                    .compile("median:\\s*(-?\\d+(?:\\.\\d+)?)", java.util.regex.Pattern.CASE_INSENSITIVE)
                    .matcher(t);
            if (mMedian.find()) s.median = Double.parseDouble(mMedian.group(1));

            // iqr: 10.5 または iqr: 30-50
            java.util.regex.Matcher mIqrRange = java.util.regex.Pattern
                    .compile("iqr:\\s*(-?\\d+(?:\\.\\d+)?)\\s*[-~–]\\s*(-?\\d+(?:\\.\\d+)?)", java.util.regex.Pattern.CASE_INSENSITIVE)
                    .matcher(t);
            if (mIqrRange.find()) {
                s.iqrLow = Double.parseDouble(mIqrRange.group(1));
                s.iqrHigh = Double.parseDouble(mIqrRange.group(2));
            } else {
                java.util.regex.Matcher mIqr = java.util.regex.Pattern
                        .compile("iqr:\\s*(-?\\d+(?:\\.\\d+)?)", java.util.regex.Pattern.CASE_INSENSITIVE)
                        .matcher(t);
                if (mIqr.find()) {
                    double iqrVal = Double.parseDouble(mIqr.group(1));
                    // IQR値のみの場合、medianがあればmedian±IQR/2で推定
                    if (s.median != null) {
                        s.iqrLow = s.median - iqrVal / 2;
                        s.iqrHigh = s.median + iqrVal / 2;
                    }
                }
            }

            // min: 2 inferred
            java.util.regex.Matcher mMin = java.util.regex.Pattern
                    .compile("min:\\s*(-?\\d+(?:\\.\\d+)?)", java.util.regex.Pattern.CASE_INSENSITIVE)
                    .matcher(t);
            if (mMin.find()) s.min = Double.parseDouble(mMin.group(1));

            // max: 100 inferred
            java.util.regex.Matcher mMax = java.util.regex.Pattern
                    .compile("max:\\s*(-?\\d+(?:\\.\\d+)?)", java.util.regex.Pattern.CASE_INSENSITIVE)
                    .matcher(t);
            if (mMax.find()) s.max = Double.parseDouble(mMax.group(1));

            // 旧形式のサポート: range 20-80
            java.util.regex.Matcher mRange = java.util.regex.Pattern
                    .compile("range\\s+(-?\\d+(?:\\.\\d+)?)\\s*[-~–]\\s*(-?\\d+(?:\\.\\d+)?)", java.util.regex.Pattern.CASE_INSENSITIVE)
                    .matcher(t);
            if (mRange.find()) {
                double a = Double.parseDouble(mRange.group(1));
                double b = Double.parseDouble(mRange.group(2));
                if (s.min == null) s.min = Math.min(a, b);
                if (s.max == null) s.max = Math.max(a, b);
            }

            // 旧形式のサポート: mean 39.85 (コロンなし)
            if (s.mean == null) {
                java.util.regex.Matcher mMeanOld = java.util.regex.Pattern
                        .compile("mean\\s+(-?\\d+(?:\\.\\d+)?)", java.util.regex.Pattern.CASE_INSENSITIVE)
                        .matcher(t);
                if (mMeanOld.find()) s.mean = Double.parseDouble(mMeanOld.group(1));
            }
            if (s.sd == null) {
                java.util.regex.Matcher mSdOld = java.util.regex.Pattern
                        .compile("sd\\s+(-?\\d+(?:\\.\\d+)?)", java.util.regex.Pattern.CASE_INSENSITIVE)
                        .matcher(t);
                if (mSdOld.find()) s.sd = Double.parseDouble(mSdOld.group(1));
            }
        } catch (Exception ignore) {
        }
        return s;
    }

    // ボックスプロット要素を作成
    private Element createBoxPlotElement(AgeStats stats, String tooltip) {
        double domainMin = 0.0;
        double domainMax = 100.0;

        Element container = new Element("div");
        container.setAttribute("title", tooltip);
        container.getStyle().set("position", "relative");
        container.getStyle().set("width", "100px");
        container.getStyle().set("height", "16px");
        container.getStyle().set("display", "inline-block");
        container.getStyle().set("flex-shrink", "0");

        // ベースライン（全体）
        Element base = new Element("div");
        base.getStyle().set("position", "absolute");
        base.getStyle().set("left", "0");
        base.getStyle().set("right", "0");
        base.getStyle().set("top", "7px");
        base.getStyle().set("height", "2px");
        base.getStyle().set("background", "#bbbbbb");
        container.appendChild(base);

        // whisker line (min to max)
        if (stats.min != null && stats.max != null) {
            double wL = Math.max(domainMin, Math.min(domainMax, stats.min));
            double wR = Math.max(domainMin, Math.min(domainMax, stats.max));
            Element whisker = new Element("div");
            whisker.getStyle().set("position", "absolute");
            whisker.getStyle().set("left", toPct(wL, domainMin, domainMax));
            whisker.getStyle().set("width", toPctWidth(wL, wR, domainMin, domainMax));
            whisker.getStyle().set("top", "7px");
            whisker.getStyle().set("height", "2px");
            whisker.getStyle().set("background", "#555555");
            container.appendChild(whisker);
        }

        // min tick
        if (stats.min != null) {
            Element minTick = new Element("div");
            minTick.getStyle().set("position", "absolute");
            minTick.getStyle().set("left", toPct(stats.min, domainMin, domainMax));
            minTick.getStyle().set("top", "4px");
            minTick.getStyle().set("width", "1px");
            minTick.getStyle().set("height", "8px");
            minTick.getStyle().set("background", "#555555");
            container.appendChild(minTick);
        }

        // max tick
        if (stats.max != null) {
            Element maxTick = new Element("div");
            maxTick.getStyle().set("position", "absolute");
            maxTick.getStyle().set("left", toPct(stats.max, domainMin, domainMax));
            maxTick.getStyle().set("top", "4px");
            maxTick.getStyle().set("width", "1px");
            maxTick.getStyle().set("height", "8px");
            maxTick.getStyle().set("background", "#555555");
            container.appendChild(maxTick);
        }

        // box (IQR または mean±sd)
        Double boxL = null;
        Double boxR = null;
        if (stats.iqrLow != null && stats.iqrHigh != null) {
            boxL = stats.iqrLow;
            boxR = stats.iqrHigh;
        } else if (stats.mean != null && stats.sd != null && stats.sd > 0) {
            boxL = stats.mean - stats.sd;
            boxR = stats.mean + stats.sd;
        }

        if (boxL != null && boxR != null && boxR > boxL) {
            double boxLClamped = Math.max(domainMin, Math.min(domainMax, boxL));
            double boxRClamped = Math.max(domainMin, Math.min(domainMax, boxR));
            Element box = new Element("div");
            box.getStyle().set("position", "absolute");
            box.getStyle().set("left", toPct(boxLClamped, domainMin, domainMax));
            box.getStyle().set("width", toPctWidth(boxLClamped, boxRClamped, domainMin, domainMax));
            box.getStyle().set("top", "3px");
            box.getStyle().set("height", "10px");
            box.getStyle().set("background", "#dde9f7");
            box.getStyle().set("border", "1px solid #2986cc");
            box.getStyle().set("border-radius", "2px");
            container.appendChild(box);
        }

        // center line (median優先、なければmean)
        Double centerVal = (stats.median != null) ? stats.median : stats.mean;
        if (centerVal != null) {
            double c = Math.max(domainMin, Math.min(domainMax, centerVal));
            Element center = new Element("div");
            center.getStyle().set("position", "absolute");
            center.getStyle().set("left", toPct(c, domainMin, domainMax));
            center.getStyle().set("top", "2px");
            center.getStyle().set("width", "2px");
            center.getStyle().set("height", "12px");
            center.getStyle().set("background", "#2986cc");
            container.appendChild(center);
        }

        return container;
    }

    private String toPct(double v, double min, double max) {
        double p = (v - min) / (max - min);
        p = Math.max(0, Math.min(1, p));
        return String.format(Locale.US, "%.3f%%", p * 100.0);
    }

    private String toPctWidth(double l, double r, double min, double max) {
        double w = (r - l) / (max - min);
        w = Math.max(0, Math.min(1, w));
        return String.format(Locale.US, "%.3f%%", w * 100.0);
    }

    private static class AgeStats {
        Double min;
        Double max;
        Double mean;
        Double sd;
        Double median;
        Double iqrLow;
        Double iqrHigh;

        boolean hasAnyNumeric() {
            return min != null || max != null || mean != null || sd != null || median != null;
        }
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

    // Study Name用ランチャーセル：左寄せ・折返し可（幅はCSSで制御）
    private void appendStudyNameLauncherCell(Element tr, String text, String authorYear, String titleTooltip) {
        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m); text-align: left; white-space: normal; word-break: break-word;");

        Button launcher = new Button(text == null ? "" : text);
        launcher.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        // ボタンラベルの折返しはCSSで制御
        // ツールチップは Title（SI4）の内容を表示
        String tip = (titleTooltip == null || titleTooltip.isEmpty()) ? (text == null ? "" : text) : titleTooltip;
        launcher.getElement().setAttribute("title", tip);
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
