package iu.SpringBoot.Vaadin.DEQACheckAll.DESummaryPage;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import iu.SpringBoot.Vaadin.DEQACheckAll.DESummaryPage.RowObject.RowObject;
import iu.SpringBoot.Vaadin.DEQACheckAll.Utils.ExternalJarLauncher;
import iu.SpringBoot.Vaadin.DEQACheckAll.Utils.HumanJsonFileGenerator;
import iu.SpringBoot.Vaadin.views.MainView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@PageTitle("DE Result Overview")
@Route("summary-view")
@StyleSheet("./styles/summary-table.css")
public class SummaryView extends VerticalLayout {

    final static String DATA_FOLDER_NAME = "share_package/data";
    final static String DEQACheckJar = "share_package/jar/DEQACheck-v20251210-all.jar";
    final static String TEMPLATE_FOR_HUMAN_DE = "share_package/templates/DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v10_1.json";

    // ソートオプション
    private static final String SORT_BY_YEAR = "年数順 (Study Name内)";
    private static final String SORT_BY_N = "N (サンプルサイズ) 順";
    private static final String SORT_BY_ALPHABET = "アルファベット順 (Study Name)";

    // インスタンス変数
    private List<RowObject> rows;
    private Div scrollWrapper;
    private boolean useNormalizedModality = true; // Modality(RCI5)の表示を正規化するか

    public SummaryView() {
        // layout settings
        getStyle().set("padding", "var(--lumo-space-m)");
        setSpacing(true);
        setWidthFull();

        add(new H2("DEQAData/AuthorYear/DE/json/*human*.json 一覧"));

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
        rows = SummaryRowsBuilder.constructRowObjectList(
                pathListOfAuthorYearDir,
                msg -> add(new Paragraph(msg))
        );

        if (rows.isEmpty()) {
            add(new Paragraph("Human系JSONファイルが見つかりません。"));
            return;
        }

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

        add(controlLayout);

        add(new Paragraph("チェックの入ったチェックボックス（☑）は _DE_AuthorXXXX_by_Human_.json のAnswer欄に何らかの入力がなされていることを意味します。マウスポインタを重ねるとその値が参照できます。（なお、チェックを入れたり消したりしてもjsonファイルには何ら影響は及ぼしません。）なお、RCI3 列は年齢の擬似ボックスプロット、RCI4 列は性比の円グラフ（F=ピンク, M=青, NR=灰）を表示します。"));

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
        if (rows == null || rows.isEmpty()) return;

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
        rows.sort(comparator);
    }

    // Study Nameを取得
    private String getStudyName(RowObject row) {
        return (row.valueList_SI.size() >= 3) ? row.valueList_SI.get(2) : "";
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

        // 先頭列: AuthorYear（クリックでJARランチャー）
        appendHeaderCell(trHead, "AuthorYear");

        // Study Name (SI3) は最終列の一つ前に配置するため、ここでは追加しない

        int subSectionSize = rows.get(0).valueList_SI.size();
        // SI1, SI2 はスキップ。SI3 は Study Name、SI4 は列としては表示せず（ツールチップ表示）。
        // SI5 も削除し、ここでは SI6 以降を追加
        for (int i = 4; i <= subSectionSize; i++) {
            if (i == 4 || i == 5) continue; // SI4=Title と SI5 は表示しない
            appendHeaderCell(trHead, "SI" + i);
        }
        subSectionSize = rows.get(0).valueList_SC.size();
        // SC1-3 は削除
        for (int i = 4; i <= subSectionSize; i++) {
            appendHeaderCell(trHead, "SC" + i);
        }
        subSectionSize = rows.get(0).valueList_RCI.size();
        // RCI の見出しを個別に設定
        if (subSectionSize >= 1) appendHeaderCellWithStyle(trHead, "Dataset", "width:16ch;");
        if (subSectionSize >= 2) appendHeaderCellWithStyle(trHead, "N", "text-align: center;");
        if (subSectionSize >= 3) appendHeaderCell(trHead, "RC Age");
        if (subSectionSize >= 4) appendHeaderCell(trHead, "Sex");
        if (subSectionSize >= 5) appendHeaderCell(trHead, "Modality");
        for (int i = 6; i <= subSectionSize; i++) {
            appendHeaderCell(trHead, "RCI" + i);
        }
        subSectionSize = rows.get(0).valueList_NM.size();
        for (int i = 1; i <= subSectionSize; i++) {
            if (i == 1) {
                appendHeaderCell(trHead, "Origin");
            } else {
                appendHeaderCell(trHead, "NM" + i);
            }
        }
        subSectionSize = rows.get(0).valueList_CAA.size();
        boolean hasFindings = subSectionSize >= 8;
        for (int i = 1; i <= subSectionSize; i++) {
            if (i == 2) {
                appendHeaderCell(trHead, "Disease");
            } else if (i == 8) {
                // Findings は最終列に配置するため、ここでは追加しない
                continue;
            } else {
                appendHeaderCell(trHead, "CAA" + i);
            }
        }
        subSectionSize = rows.get(0).valueList_GN.size();
        for (int i = 1; i <= subSectionSize; i++) {
            appendHeaderCell(trHead, "GN" + i);
        }

        // 最後から2列目に Study Name、最後の列に Findings を配置
        appendHeaderCell(trHead, "Study Name");
        if (hasFindings) {
            appendHeaderCell(trHead, "Findings");
        } else {
            // Findings データが無い場合でも列を用意するなら以下を有効化
            // appendHeaderCell(trHead, "Findings");
        }

        // 最終列にランチャーは置かない（先頭列に移動）
        thead.appendChild(trHead);
        table.appendChild(thead);

        // tbody
        Element tbody = new Element("tbody");
        boolean even = false;
        for (int r = 0; r < rows.size(); r++) {
            RowObject row = rows.get(r);
            Element tr = new Element("tr");
            if (even) {
                tr.setAttribute("style", "background: var(--lumo-contrast-5pct);");
            }
            // 先頭列: AuthorYear（クリックでJARランチャー）
            appendLauncherCell(tr, row.authorYear, row.authorYear);

            // Study Name (SI3) は最後から2列目に配置するため、ここでは追加しない
            String studyName = getStudyName(row);
            String si4 = (row.valueList_SI.size() > 3) ? row.valueList_SI.get(3) : ""; // index 3 -> SI4

            // SI4 は Title（列）を廃止したため表示しない。SI5 も削除。ここでは SI6 以降をチェックボックスで表示
            subSectionSize = row.valueList_SI.size();
            for (int i = 3; i < subSectionSize; i++) { // i=3 -> SI4
                if (i == 3 || i == 4) continue; // SI4=Title と SI5 はスキップ
                appendCheckBoxCell(tr, row.valueList_SI.get(i));
            }
            subSectionSize = row.valueList_SC.size();
            // SC1-3 は削除
            for (int i = 3; i < subSectionSize; i++) { // i=3 -> SC4
                appendCheckBoxCell(tr, row.valueList_SC.get(i));
            }
            subSectionSize = row.valueList_RCI.size();
            for (int i = 0; i < subSectionSize; i++) {
                // RCI1: Dataset → 文字列
                // RCI2: N → 文字列
                // RCI3: RC Age → 擬似ボックスプロット
                // RCI4: 性比 → 円グラフ
                // RCI5: Modality → 文字列
                // それ以降は従来通りチェックボックス
                if (i == 0) {
                    appendDatasetCell(tr, row.valueList_RCI.get(i));
                } else if (i == 1) {
                    // N 列は正規化により複数行や Model/Phase 情報が入ることがあるため、改行表示に対応
                    appendPreWrappedCell(tr, row.valueList_RCI.get(i), false);
                } else if (i == 2) {
                    appendAgeBoxPlotCell(tr, row.valueList_RCI.get(i));
                } else if (i == 3) {
                    appendSexPieCell(tr, row.valueList_RCI.get(i));
                } else if (i == 4) {
                    appendModalityCell(tr, row.valueList_RCI.get(i));
                } else {
                    appendCheckBoxCell(tr, row.valueList_RCI.get(i));
                }
            }
            // NM: NM1（index 0）は文字列、それ以外はチェックボックス
            subSectionSize = row.valueList_NM.size();
            for (int i = 0; i < subSectionSize; i++) {
                if (i == 0) {
                    appendNormalCell(tr, row.valueList_NM.get(i));
                } else {
                    appendCheckBoxCell(tr, row.valueList_NM.get(i));
                }
            }
            // CAA: CAA2（index 1）は文字列、CAA8（index 7）は最終列に回す、その他はチェックボックス
            subSectionSize = row.valueList_CAA.size();
            String findingsValue = null;
            for (int i = 0; i < subSectionSize; i++) {
                if (i == 7) { // CAA8 -> Findings
                    findingsValue = row.valueList_CAA.get(i);
                    continue;
                }
                if (i == 1) {
                    appendNormalCell(tr, row.valueList_CAA.get(i));
                } else {
                    appendCheckBoxCell(tr, row.valueList_CAA.get(i));
                }
            }
            subSectionSize = row.valueList_GN.size();
            for (int i = 0; i < subSectionSize; i++) {
                appendCheckBoxCell(tr, row.valueList_GN.get(i));
            }

            // 最後から2列目: Study Name (SI3)。Title(SI4) はツールチップで表示
            appendStudyNameLauncherCell(tr, studyName, row.authorYear, si4);
            // 最終列: Findings（CAA8）
            if (findingsValue != null) {
                appendNormalCell(tr, findingsValue);
            } else {
                appendNormalCell(tr, "");
            }

            // 最終列のランチャーは廃止（先頭列に移動）
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
        String display;
        if (isMissingOrInvalidModality(raw)) {
            display = "NA";
        } else if (useNormalizedModality) {
            String norm = normalizeModality(raw);
            display = (norm == null || norm.isEmpty()) ? "NA" : norm;
        } else {
            display = stripTrailingPeriod(raw.trim());
        }

        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m);");
        td.setAttribute("title", tooltip);
        td.setText(display);
        tr.appendChild(td);
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

    // 改行表示に対応したセル（white-space: pre-wrap）
    private void appendPreWrappedCell(Element tr, String text, boolean center) {
        Element td = new Element("td");
        String style = "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m); white-space: pre-wrap;";
        if (center) style += " text-align: center;"; else style += " text-align: left;";
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
    private void appendSexPieCell(Element tr, String text) {
        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m);");

        // 複数行や Model/Phase を含む正規化済み表現はそのまま改行表示
        if (text != null && (text.contains("\n") || text.contains("Model:") || text.contains("Phase:"))) {
            td.setAttribute("style", td.getAttribute("style") + " white-space: pre-wrap; text-align: left;");
            td.setText(text);
            tr.appendChild(td);
            return;
        }

        Double femalePct = extractFemalePercentFromText(text);

        Element pie = new Element("div");
        pie.setAttribute("title", text == null ? "" : text);
        pie.getStyle().set("width", "16px");
        pie.getStyle().set("height", "16px");
        pie.getStyle().set("border-radius", "50%");
        pie.getStyle().set("display", "inline-block");
        pie.getStyle().set("vertical-align", "middle");
        pie.getStyle().set("border", "1px solid #555555");

        if (femalePct == null) {
            // NR: 灰色
            pie.getStyle().set("background", "#cccccc");
        } else {
            // conic-gradient で円グラフ（ピンク=F, 青=M）
            // 例: conic-gradient(pink 0 42.6%, blue 0)
            String bg = String.format(Locale.US, "conic-gradient(#c90076 0 %.3f%%, #2986cc 0)", femalePct);
            pie.getStyle().set("background", bg);
        }

        td.appendChild(pie);
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
    // 利用できる値: min / max / mean / sd / range a-b
    // 欠損が多い場合は mean±sd を箱、min-max を全体のスケールとして描く（なければ mean±2sd）
    // 何も取れない/NR の場合は "NR" を表示
    private void appendAgeBoxPlotCell(Element tr, String text) {
        Element td = new Element("td");
        td.setAttribute("style", "border-bottom: 1px solid var(--lumo-contrast-20pct); padding: var(--lumo-space-s) var(--lumo-space-m);");

        // 正規化済みの複数行/Model/Phase 形式はそのまま改行表示
        if (text != null && (text.contains("\n") || text.contains("Model:") || text.contains("Phase:"))) {
            td.setAttribute("style", td.getAttribute("style") + " white-space: pre-wrap; text-align: left;");
            td.setText(text);
            tr.appendChild(td);
            return;
        }

        if (text == null || text.trim().isEmpty() || text.trim().equalsIgnoreCase("NR")) {
            Element span = new Element("span");
            span.setText("NR");
            td.appendChild(span);
            tr.appendChild(td);
            return;
        }

        AgeStats stats = parseAgeStats(text);
        if (!stats.hasAnyNumeric()) {
            Element span = new Element("span");
            span.setText("NR");
            td.appendChild(span);
            tr.appendChild(td);
            return;
        }

        // ドメインは全行固定で 0–100 歳
        double domainMin = 0.0;
        double domainMax = 100.0;

        // 箱（IQR の代替として mean±sd）。sd が NR(欠損)のときは箱を描画しない。
        Double boxL = null;
        Double boxR = null;
        if (stats.mean != null && stats.sd != null && stats.sd > 0) {
            boxL = stats.mean - stats.sd;
            boxR = stats.mean + stats.sd;
        }

        Double centerVal = stats.mean; // 中央線として mean を採用

        Element container = new Element("div");
        container.setAttribute("title", text);
        container.getStyle().set("position", "relative");
        container.getStyle().set("width", "120px");
        container.getStyle().set("height", "22px");
        container.getStyle().set("display", "inline-block");

        // ベースライン（全体）
        Element base = new Element("div");
        base.getStyle().set("position", "absolute");
        base.getStyle().set("left", "0");
        base.getStyle().set("right", "0");
        base.getStyle().set("top", "7px");
        base.getStyle().set("height", "2px");
        base.getStyle().set("background", "#bbbbbb");
        container.appendChild(base);

        // min tick（値がわかる場合のみ描画）
        if (stats.min != null) {
            Element minTick = new Element("div");
            minTick.getStyle().set("position", "absolute");
            minTick.getStyle().set("left", toPct(stats.min, domainMin, domainMax));
            minTick.getStyle().set("top", "3px");
            minTick.getStyle().set("width", "1px");
            minTick.getStyle().set("height", "10px");
            minTick.getStyle().set("background", "#555555");
            container.appendChild(minTick);
        }

        // max tick（値がわかる場合のみ描画）
        if (stats.max != null) {
            Element maxTick = new Element("div");
            maxTick.getStyle().set("position", "absolute");
            maxTick.getStyle().set("left", toPct(stats.max, domainMin, domainMax));
            maxTick.getStyle().set("top", "3px");
            maxTick.getStyle().set("width", "1px");
            maxTick.getStyle().set("height", "10px");
            maxTick.getStyle().set("background", "#555555");
            container.appendChild(maxTick);
        }

        // box（sd がない場合は描画しない）
        if (boxL != null && boxR != null && boxR > boxL) {
            double boxLClamped = Math.max(domainMin, Math.min(domainMax, boxL));
            double boxRClamped = Math.max(domainMin, Math.min(domainMax, boxR));
            if (boxRClamped < boxLClamped) {
                double tmp = boxLClamped;
                boxLClamped = boxRClamped;
                boxRClamped = tmp;
            }
            Element box = new Element("div");
            box.getStyle().set("position", "absolute");
            box.getStyle().set("left", toPct(boxLClamped, domainMin, domainMax));
            box.getStyle().set("width", toPctWidth(boxLClamped, boxRClamped, domainMin, domainMax));
            box.getStyle().set("top", "2px");
            box.getStyle().set("height", "12px");
            box.getStyle().set("background", "#dde9f7");
            box.getStyle().set("border", "1px solid #2986cc");
            box.getStyle().set("border-radius", "2px");
            container.appendChild(box);
        }

        // center line (mean)
        if (centerVal != null) {
            double c = Math.max(domainMin, Math.min(domainMax, centerVal));
            Element center = new Element("div");
            center.getStyle().set("position", "absolute");
            center.getStyle().set("left", toPct(c, domainMin, domainMax));
            center.getStyle().set("top", "1px");
            center.getStyle().set("width", "2px");
            center.getStyle().set("height", "14px");
            center.getStyle().set("background", "#2986cc");
            container.appendChild(center);
        }

        td.appendChild(container);
        // ラベル: 左下 0、右下 100
        Element labelLeft = new Element("div");
        labelLeft.setText("0");
        labelLeft.getStyle().set("position", "absolute");
        labelLeft.getStyle().set("left", "0");
        labelLeft.getStyle().set("bottom", "0");
        labelLeft.getStyle().set("font-size", "10px");
        labelLeft.getStyle().set("color", "#666");
        labelLeft.getStyle().set("line-height", "1");

        Element labelRight = new Element("div");
        labelRight.setText("100");
        labelRight.getStyle().set("position", "absolute");
        labelRight.getStyle().set("right", "0");
        labelRight.getStyle().set("bottom", "0");
        labelRight.getStyle().set("font-size", "10px");
        labelRight.getStyle().set("color", "#666");
        labelRight.getStyle().set("line-height", "1");

        // td は position: static なのでラベルの座標基準に container を使うため、container に append
        container.appendChild(labelLeft);
        container.appendChild(labelRight);
        tr.appendChild(td);
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

        boolean hasAnyNumeric() {
            return min != null || max != null || mean != null || sd != null;
        }
    }

    private AgeStats parseAgeStats(String text) {
        AgeStats s = new AgeStats();
        if (text == null) return s;
        String t = text.replace('\u2013', '-') // en dash → hyphen
                .replace('\u2212', '-') // minus sign → hyphen
                .replaceAll(",", ".");
        try {
            java.util.regex.Matcher mMin = java.util.regex.Pattern
                    .compile("min\\s+(-?\\d+(?:\\.\\d+)?)", java.util.regex.Pattern.CASE_INSENSITIVE)
                    .matcher(t);
            if (mMin.find()) s.min = Double.parseDouble(mMin.group(1));

            java.util.regex.Matcher mMax = java.util.regex.Pattern
                    .compile("max\\s+(-?\\d+(?:\\.\\d+)?)", java.util.regex.Pattern.CASE_INSENSITIVE)
                    .matcher(t);
            if (mMax.find()) s.max = Double.parseDouble(mMax.group(1));

            java.util.regex.Matcher mRange = java.util.regex.Pattern
                    .compile("range\\s+(-?\\d+(?:\\.\\d+)?)\\s*[-~–]\\s*(-?\\d+(?:\\.\\d+)?)", java.util.regex.Pattern.CASE_INSENSITIVE)
                    .matcher(t);
            if (mRange.find()) {
                double a = Double.parseDouble(mRange.group(1));
                double b = Double.parseDouble(mRange.group(2));
                if (s.min == null) s.min = Math.min(a, b);
                if (s.max == null) s.max = Math.max(a, b);
            }

            java.util.regex.Matcher mMean = java.util.regex.Pattern
                    .compile("mean\\s+(-?\\d+(?:\\.\\d+)?)", java.util.regex.Pattern.CASE_INSENSITIVE).matcher(t);
            if (mMean.find()) s.mean = Double.parseDouble(mMean.group(1));

            java.util.regex.Matcher mSd = java.util.regex.Pattern
                    .compile("sd\\s+(-?\\d+(?:\\.\\d+)?)", java.util.regex.Pattern.CASE_INSENSITIVE).matcher(t);
            if (mSd.find()) s.sd = Double.parseDouble(mSd.group(1));
        } catch (Exception ignore) {
        }
        return s;
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

                boolean ok = ExternalJarLauncher.launch(DEQACheckJar, DATA_FOLDER_NAME, authorYear);
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

                boolean ok = ExternalJarLauncher.launch(DEQACheckJar, DATA_FOLDER_NAME, authorYear);
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
