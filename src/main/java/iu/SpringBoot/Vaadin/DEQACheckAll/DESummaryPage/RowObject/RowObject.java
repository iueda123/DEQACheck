package iu.SpringBoot.Vaadin.DEQACheckAll.DESummaryPage.RowObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RowObject {


    public String jsonFileName = "";
    public final String authorYear;
    public String modifiedTime = "";
    public String jsonFileSize = "";
    public String jsonFilePathString = "";

    public ArrayList<String> valueList_SI; // = new ArrayList<>();
    public ArrayList<String> valueList_SC; // = new ArrayList<>();
    public ArrayList<String> valueList_RCI; // = new ArrayList<>();
    public ArrayList<String> valueList_NM; // = new ArrayList<>();
    public ArrayList<String> valueList_CAA; // = new ArrayList<>();
    public ArrayList<String> valueList_GN; // = new ArrayList<>();


    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            .withLocale(Locale.JAPAN);

    public RowObject(Path jsonFilePath) {
        this.jsonFileName = jsonFilePath.getFileName().toString();
        // プロジェクトルート（pom.xml が存在するディレクトリ）からの相対パスを保持
        try {
            Path projectRoot = findProjectRoot(Paths.get("").toAbsolutePath());
            Path rel = projectRoot.relativize(jsonFilePath.toAbsolutePath().normalize());
            this.jsonFilePathString = rel.toString();
        } catch (Exception ignore) {
            // 失敗時は従来どおりの文字列化
            this.jsonFilePathString = jsonFilePath.toString();
        }

        this.authorYear = jsonFilePath.getParent().getParent().getParent().toFile().getName();

        try {
            BasicFileAttributes attr = Files.readAttributes(jsonFilePath, BasicFileAttributes.class);
            this.jsonFileSize = humanReadableSize(attr.size());

            this.modifiedTime = TIME_FMT.format(attr.lastModifiedTime().toInstant().atZone(ZoneId.systemDefault()));

        } catch (IOException ignore) {
            // 読み取り失敗時はスキップ
        }

        valueList_SI = collect_SI_Answers(jsonFilePath);
        valueList_SC = collect_SC_Answers(jsonFilePath);
        valueList_RCI = collect_RCI_Answers(jsonFilePath);
        valueList_NM = collect_NM_Answers(jsonFilePath);
        valueList_CAA = collect_CAA_Answers(jsonFilePath);
        valueList_GN = collect_GN_Answers(jsonFilePath);
    }

    private ArrayList<String> collect_GN_Answers(Path jsonFilePath) {
        // GN は多くのJSONでトップレベルに gn1_... 形式で配置
        return collectBySectionAndPrefix(jsonFilePath, "general_note_part", "gn");
    }

    private ArrayList<String> collect_CAA_Answers(Path jsonFilePath) {
        // clinical_application_and_analysis_part 内の caa1_... を収集（値直下 or answer）
        return collectBySectionAndPrefix(jsonFilePath, "clinical_application_and_analysis_part", "caa");
    }

    private ArrayList<String> collect_NM_Answers(Path jsonFilePath) {
        // normative_modeling_part 内の nm1_... を収集（値直下 or answer）
        return collectBySectionAndPrefix(jsonFilePath, "normative_modeling_part", "nm");
    }

    private ArrayList<String> collect_RCI_Answers(Path jsonFilePath) {
        // reference_cohort_and_imaging_part 内の rci1_... を収集（値直下 or answer）
        return collectBySectionAndPrefix(jsonFilePath, "reference_cohort_and_imaging_part", "rci");
    }

    private ArrayList<String> collect_SC_Answers(Path jsonFilePath) {
        // study_characteristics_part 内の sc1_... を収集（値直下 or answer）
        return collectBySectionAndPrefix(jsonFilePath, "study_characteristics_part", "sc");
    }

    private ArrayList<String> collect_SI_Answers(Path jsonFilePatn) {
        // study_identification_part 内の si1_... を収集（値直下 or answer）
        return collectBySectionAndPrefix(jsonFilePatn, "study_identification_part", "si");
    }

    // 共通ロジック: 指定セクション内の prefixNN_... キーの値（answer優先）を順序付きで収集
    private ArrayList<String> collectBySectionAndPrefix(Path jsonFilePath, String sectionKey, String prefix) {
        ArrayList<String> values = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonFilePath.toFile());

            JsonNode section = (sectionKey == null) ? root : root.get(sectionKey);
            if (section != null && section.isObject()) {
                // キー順の安定化（数値部分を考慮した自然順）
                List<Map.Entry<String, JsonNode>> entries = new ArrayList<>();
                section.fields().forEachRemaining(entries::add);
                entries.sort(createNaturalOrderComparator(prefix));

                for (Map.Entry<String, JsonNode> e : entries) {
                    String key = e.getKey();
                    if (key != null && key.matches("^" + prefix + "\\d+_.*")) {
                        values.add(extractValueOrAnswer(e.getValue()));
                    }
                }
                return values;
            }

            // セクションが無い場合は全体走査で prefix に合致するキーを抽出
            collectByPrefixRecursive(root, prefix, values);
        } catch (Exception ignore) {
            // 読み取り失敗時は空リスト
        }
        return values;
    }

    private void collectByPrefixRecursive(JsonNode node, String prefix, List<String> out) {
        if (node == null) return;
        if (node.isObject()) {
            // 順序は保証されないため、自然順で安定化（数値部分を考慮）
            List<Map.Entry<String, JsonNode>> entries = new ArrayList<>();
            node.fields().forEachRemaining(entries::add);
            entries.sort(createNaturalOrderComparator(prefix));
            for (Map.Entry<String, JsonNode> e : entries) {
                String key = e.getKey();
                JsonNode child = e.getValue();
                if (key != null && key.matches("^" + prefix + "\\d+_.*")) {
                    out.add(extractValueOrAnswer(child));
                }
                collectByPrefixRecursive(child, prefix, out);
            }
        } else if (node.isArray()) {
            for (JsonNode elem : node) collectByPrefixRecursive(elem, prefix, out);
        }
    }

    private String extractValueOrAnswer(JsonNode n) {
        if (n == null || n.isNull()) return "";
        if (n.isObject() && n.has("answer")) {
            JsonNode a = n.get("answer");
            if (a == null || a.isNull()) return "";
            if (a.isTextual()) return a.asText();
            return a.toString();
        }
        if (n.isTextual()) return n.asText();
        return n.toString();
    }

    // 数値部分を考慮した自然順Comparatorを生成（nm1 < nm2 < nm10 のようにソート）
    private static Comparator<Map.Entry<String, JsonNode>> createNaturalOrderComparator(String prefix) {
        return (e1, e2) -> {
            String key1 = e1.getKey();
            String key2 = e2.getKey();

            Integer num1 = extractNumber(key1, prefix);
            Integer num2 = extractNumber(key2, prefix);

            // 両方が対象パターンの場合は数値で比較
            if (num1 != null && num2 != null) {
                int cmp = num1.compareTo(num2);
                if (cmp != 0) return cmp;
                // 数値が同じ場合は全体を文字列比較
                return key1.compareTo(key2);
            }

            // どちらかがパターンに合致しない場合は文字列比較
            return key1.compareTo(key2);
        };
    }

    // キーから数値部分を抽出（例: "nm10_xxx" -> 10, "caa2_yyy" -> 2）
    private static Integer extractNumber(String key, String prefix) {
        if (key == null || !key.startsWith(prefix)) return null;

        int start = prefix.length();
        int end = start;

        // 数値部分の終わりを探す
        while (end < key.length() && Character.isDigit(key.charAt(end))) {
            end++;
        }

        if (end > start && end < key.length() && key.charAt(end) == '_') {
            try {
                return Integer.parseInt(key.substring(start, end));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    private static String humanReadableSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        String pre = "KMGTPE".charAt(exp - 1) + "";
        return String.format(Locale.US, "%.1f %sB", bytes / Math.pow(1024, exp), pre);
    }

    // 現在ディレクトリから上に辿って pom.xml が見つかる場所をプロジェクトルートとみなす
    private static Path findProjectRoot(Path start) {
        Path p = start;
        while (p != null) {
            if (Files.exists(p.resolve("pom.xml"))) {
                return p;
            }
            p = p.getParent();
        }
        return start; // 見つからない場合は開始位置を返す
    }
}
