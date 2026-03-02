package iu.SpringBoot.Vaadin.DEQACheckAll.RsltComparatorWeb;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Pre;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import iu.SpringBoot.Vaadin.views.MainView;
import jakarta.annotation.security.RolesAllowed;

import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Web version of the Swing-based RsltComparator.
 * Side-by-side comparison of AI-extracted JSON data for a selected AuthorYear + Version.
 */
@PageTitle("Result Comparator")
@Route("rslt-comparator")
@RolesAllowed({"ADMIN"})
public class RsltComparatorWebView extends VerticalLayout {

    private static final Path DATA_ROOT = Paths.get(System.getProperty("user.dir"), "share_package/data");
    private static final Path PROMPTS_ROOT = Paths.get(System.getProperty("user.dir"), "share_package/prompts");
    private static final Path TEMPLATES_ROOT = Paths.get(System.getProperty("user.dir"), "share_package/templates");

    private final ComboBox<String> authorYearCombo = new ComboBox<>("AuthorYear");
    private final ComboBox<String> versionCombo = new ComboBox<>("Version");
    private final VerticalLayout contentArea = new VerticalLayout();

    /** All column components created during buildUI, for Save All / Load All */
    private final List<ComparisonColumnComponent> allColumns = new ArrayList<>();

    public RsltComparatorWebView() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        // Header
        HorizontalLayout header = new HorizontalLayout();
        header.setAlignItems(Alignment.BASELINE);
        header.setSpacing(true);

        H2 title = new H2("Result Comparator");
        title.getStyle().set("margin", "0");

        RouterLink backLink = new RouterLink("Back to Main", MainView.class);
        backLink.getStyle().set("margin-left", "auto");

        header.add(title, backLink);
        header.setWidthFull();
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);

        // Selection controls
        authorYearCombo.setPlaceholder("Select AuthorYear...");
        authorYearCombo.setWidth("250px");
        authorYearCombo.setClearButtonVisible(true);
        authorYearCombo.addValueChangeListener(e -> onAuthorYearChanged());

        versionCombo.setPlaceholder("Select Version...");
        versionCombo.setWidth("200px");
        versionCombo.setClearButtonVisible(true);

        Button loadBtn = new Button("Load", e -> onLoad());
        loadBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button saveAllBtn = new Button("Save All", e -> onSaveAll());
        saveAllBtn.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

        Button loadAllBtn = new Button("Load All", e -> onLoadAll());

        HorizontalLayout controls = new HorizontalLayout(
                authorYearCombo, versionCombo, loadBtn, saveAllBtn, loadAllBtn);
        controls.setAlignItems(Alignment.BASELINE);
        controls.setSpacing(true);

        add(header, controls);

        // Content area (populated on Load)
        contentArea.setSizeFull();
        contentArea.setPadding(false);
        contentArea.setSpacing(false);
        add(contentArea);
        setFlexGrow(1, contentArea);

        // Populate AuthorYear combo
        populateAuthorYears();
    }

    // =========================================================================
    // AuthorYear / Version scanning
    // =========================================================================

    private void populateAuthorYears() {
        if (!Files.exists(DATA_ROOT) || !Files.isDirectory(DATA_ROOT)) {
            Notification.show("Data directory not found: " + DATA_ROOT, 5000, Notification.Position.MIDDLE);
            return;
        }
        try (Stream<Path> dirs = Files.list(DATA_ROOT)) {
            List<String> authorYears = dirs
                    .filter(Files::isDirectory)
                    .map(p -> p.getFileName().toString())
                    .filter(name -> !name.equalsIgnoreCase("settings"))
                    .filter(name -> !name.equals("Someone20XX"))
                    .sorted(String.CASE_INSENSITIVE_ORDER)
                    .collect(Collectors.toList());
            authorYearCombo.setItems(authorYears);
        } catch (Exception e) {
            Notification.show("Failed to scan data directory: " + e.getMessage(),
                    5000, Notification.Position.MIDDLE);
            e.printStackTrace();
        }
    }

    private void onAuthorYearChanged() {
        versionCombo.clear();
        String ay = authorYearCombo.getValue();
        if (ay == null || ay.isEmpty()) return;

        Path ayDir = DATA_ROOT.resolve(ay);
        if (!Files.exists(ayDir)) return;

        try (Stream<Path> dirs = Files.list(ayDir)) {
            List<String> versions = dirs
                    .filter(Files::isDirectory)
                    .filter(p -> {
                        String name = p.getFileName().toString();
                        return (name.startsWith("DE_") || name.startsWith("QA_"))
                                && Files.exists(p.resolve("json"));
                    })
                    .map(p -> p.getFileName().toString())
                    .sorted(String.CASE_INSENSITIVE_ORDER)
                    .collect(Collectors.toList());
            versionCombo.setItems(versions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // =========================================================================
    // Load: build the comparison UI
    // =========================================================================

    private void onLoad() {
        String authorYear = authorYearCombo.getValue();
        String version = versionCombo.getValue();
        if (authorYear == null || authorYear.isEmpty()) {
            Notification.show("Please select an AuthorYear", 3000, Notification.Position.MIDDLE);
            return;
        }
        if (version == null || version.isEmpty()) {
            Notification.show("Please select a Version", 3000, Notification.Position.MIDDLE);
            return;
        }

        contentArea.removeAll();
        allColumns.clear();

        Path jsonFolderPath = DATA_ROOT.resolve(authorYear).resolve(version).resolve("json");
        if (!Files.exists(jsonFolderPath) || !Files.isDirectory(jsonFolderPath)) {
            Notification.show("JSON folder not found: " + jsonFolderPath, 5000, Notification.Position.MIDDLE);
            return;
        }

        // Scan JSON files
        File jsonDir = jsonFolderPath.toFile();
        File[] jsonFiles = jsonDir.listFiles((dir, name) -> name.endsWith(".json"));
        if (jsonFiles == null) jsonFiles = new File[0];

        // Ensure human JSON exists
        ensureHumanJsonExists(jsonDir, jsonFiles, authorYear, version);

        // Re-scan after potential human JSON creation
        jsonFiles = jsonDir.listFiles((dir, name) -> name.endsWith(".json"));
        if (jsonFiles == null || jsonFiles.length == 0) {
            Notification.show("No JSON files found in: " + jsonFolderPath, 5000, Notification.Position.MIDDLE);
            return;
        }

        // Sort: human first, then alphabetical
        Arrays.sort(jsonFiles, (f1, f2) -> {
            boolean f1Human = f1.getName().toLowerCase().contains("_by_human");
            boolean f2Human = f2.getName().toLowerCase().contains("_by_human");
            if (f1Human != f2Human) return Boolean.compare(f2Human, f1Human);
            return f1.getName().compareToIgnoreCase(f2.getName());
        });

        // Discover section structure from first JSON
        LinkedHashMap<String, List<String>> sectionStructure = discoverSectionStructure(jsonFiles[0]);
        if (sectionStructure.isEmpty()) {
            Notification.show("Could not discover section structure from JSON", 5000, Notification.Position.MIDDLE);
            return;
        }

        // Load prompt guide
        LinkedHashMap<String, String> promptMap = loadPromptGuide(version);

        // Build UI: section tabs -> subsection tabs -> columns
        buildComparisonUI(sectionStructure, promptMap, jsonFolderPath, jsonFiles);

        Notification.show("Loaded " + jsonFiles.length + " JSON files for " + authorYear + " / " + version,
                3000, Notification.Position.BOTTOM_START);
    }

    // =========================================================================
    // Section structure discovery
    // =========================================================================

    private LinkedHashMap<String, List<String>> discoverSectionStructure(File firstJsonFile) {
        LinkedHashMap<String, List<String>> structure = new LinkedHashMap<>();
        try (InputStreamReader reader = new InputStreamReader(
                Files.newInputStream(firstJsonFile.toPath()), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            JsonObject root = gson.fromJson(reader, JsonObject.class);

            for (String sectionKey : root.keySet()) {
                JsonElement sectionElement = root.get(sectionKey);
                List<String> subSections = new ArrayList<>();

                if (sectionElement.isJsonObject()) {
                    JsonObject sectionObj = sectionElement.getAsJsonObject();
                    for (String subKey : sectionObj.keySet()) {
                        subSections.add(subKey);
                    }
                }
                if (!subSections.isEmpty()) {
                    structure.put(sectionKey, subSections);
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to discover section structure from: " + firstJsonFile.getAbsolutePath());
            e.printStackTrace();
        }
        return structure;
    }

    // =========================================================================
    // Prompt guide
    // =========================================================================

    private LinkedHashMap<String, String> loadPromptGuide(String version) {
        LinkedHashMap<String, String> promptMap = new LinkedHashMap<>();

        String guideFileName = deriveGuideFileName(version);
        if (guideFileName == null) return promptMap;

        Path guidePath = PROMPTS_ROOT.resolve(guideFileName);
        if (!Files.exists(guidePath)) {
            System.err.println("Prompt guide not found: " + guidePath);
            return promptMap;
        }

        try {
            List<String> lines = Files.readAllLines(guidePath, StandardCharsets.UTF_8);
            parseGuide(lines, promptMap);
        } catch (Exception e) {
            System.err.println("Failed to load prompt guide: " + guidePath);
            e.printStackTrace();
        }
        return promptMap;
    }

    private static String deriveGuideFileName(String version) {
        if (version.startsWith("DE_")) return "DE_Guide_" + version.substring(3) + ".md";
        if (version.startsWith("QA_")) return "QA_Guide_" + version.substring(3) + ".md";
        return null;
    }

    private static void parseGuide(List<String> lines, LinkedHashMap<String, String> promptMap) {
        String currentHeadingId = null;
        StringBuilder currentBlock = new StringBuilder();

        for (String line : lines) {
            if (line.matches("^#{2,4}\\s+.*")) {
                if (currentHeadingId != null) {
                    promptMap.put(currentHeadingId, currentBlock.toString().trim());
                }
                currentHeadingId = extractHeadingId(line);
                currentBlock = new StringBuilder();
                currentBlock.append(line).append("\n");
            } else if (currentHeadingId != null) {
                currentBlock.append(line).append("\n");
            }
        }
        if (currentHeadingId != null) {
            promptMap.put(currentHeadingId, currentBlock.toString().trim());
        }
    }

    private static String extractHeadingId(String headingLine) {
        String trimmed = headingLine.replaceAll("^#+\\s+", "").trim();
        int dotIndex = trimmed.indexOf('.');
        if (dotIndex > 0) {
            return trimmed.substring(0, dotIndex).trim().toLowerCase();
        }
        return trimmed.toLowerCase();
    }

    static String subSectionKeyToHeadingId(String subSectionKey) {
        String[] parts = subSectionKey.split("_");
        String prefix = parts[0];

        if (parts.length >= 2 && parts[1].matches("\\d+")) {
            return prefix.toLowerCase() + "-" + parts[1];
        }

        // Handle prefix with embedded digits (rci1, caa7, nm10)
        StringBuilder alpha = new StringBuilder();
        StringBuilder digits = new StringBuilder();
        for (char c : prefix.toCharArray()) {
            if (Character.isDigit(c) && alpha.length() > 0) {
                digits.append(c);
            } else {
                alpha.append(c);
            }
        }
        if (digits.length() > 0) {
            return alpha.toString().toLowerCase() + "-" + digits.toString();
        }

        return prefix.toLowerCase();
    }

    private static String getPromptForSubSection(String subSectionKey, LinkedHashMap<String, String> promptMap) {
        String headingId = subSectionKeyToHeadingId(subSectionKey);
        return promptMap.get(headingId);
    }

    // =========================================================================
    // Human JSON template generation
    // =========================================================================

    private void ensureHumanJsonExists(File jsonDir, File[] jsonFiles, String authorYear, String version) {
        boolean humanExists = false;
        if (jsonFiles != null) {
            for (File f : jsonFiles) {
                if (f.getName().toLowerCase().contains("_by_human")) {
                    humanExists = true;
                    break;
                }
            }
        }
        if (humanExists) return;

        File templateFile = findTemplate(version);
        if (templateFile == null || !templateFile.exists()) {
            System.err.println("Template not found for version: " + version);
            return;
        }

        String humanJsonName = generateHumanJsonName(authorYear, version);
        File humanJsonFile = new File(jsonDir, humanJsonName);

        try {
            Files.copy(templateFile.toPath(), humanJsonFile.toPath());
            Notification.show("Generated human JSON from template: " + humanJsonFile.getName(),
                    3000, Notification.Position.BOTTOM_START);
        } catch (Exception e) {
            System.err.println("Failed to generate human JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private File findTemplate(String version) {
        Map<String, String> templateNames = new LinkedHashMap<>();
        templateNames.put("DE_v13", "DE_v13_Author20XX_by_Someone_YYYYmmddHHMMSS.json");
        templateNames.put("DE_v12", "DE_v12_Author20XX_by_Someone_YYYYmmddHHMMSS.json");
        templateNames.put("DE_v11", "DE_v11_Author20XX_by_Someone_YYYYmmddHHMMSS.json");
        templateNames.put("DE_v10_1", "DE_Author20XX_by_Someone_YYYYmmddHHMMSS_for_v10_1.json");
        templateNames.put("QA_v9", "Author20XX_by_Someone_YYYYmmddHHMMSS_for_QA_v9.json");

        String templateName = templateNames.get(version);
        if (templateName != null) {
            return TEMPLATES_ROOT.resolve(templateName).toFile();
        }
        return null;
    }

    private static String generateHumanJsonName(String authorYear, String version) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        switch (version) {
            case "DE_v13":
                return "DE_v13_" + authorYear + "_by_human_" + timestamp + ".json";
            case "DE_v12":
                return "DE_v12_" + authorYear + "_by_human_" + timestamp + ".json";
            case "DE_v11":
                return "DE_v11_" + authorYear + "_by_human_" + timestamp + ".json";
            case "DE_v10_1":
                return "DE_" + authorYear + "_by_human_" + timestamp + "_for_v10_1.json";
            case "QA_v9":
                return authorYear + "_by_human_" + timestamp + "_for_QA_v9.json";
            default:
                return version + "_" + authorYear + "_by_human_" + timestamp + ".json";
        }
    }

    // =========================================================================
    // UI construction
    // =========================================================================

    private void buildComparisonUI(
            LinkedHashMap<String, List<String>> sectionStructure,
            LinkedHashMap<String, String> promptMap,
            Path jsonFolderPath,
            File[] jsonFiles) {

        TabSheet sectionTabSheet = new TabSheet();
        sectionTabSheet.setSizeFull();

        for (Map.Entry<String, List<String>> entry : sectionStructure.entrySet()) {
            String sectionName = entry.getKey();
            List<String> subSections = entry.getValue();

            TabSheet subSectionTabSheet = new TabSheet();
            subSectionTabSheet.setSizeFull();

            for (String subSectionName : subSections) {
                VerticalLayout subContent = new VerticalLayout();
                subContent.setPadding(false);
                subContent.setSpacing(true);
                subContent.setSizeFull();

                // Prompt guide (collapsible)
                String promptText = getPromptForSubSection(subSectionName, promptMap);
                if (promptText != null && !promptText.isEmpty()) {
                    Pre promptPre = new Pre(promptText);
                    promptPre.getStyle()
                            .set("background-color", "rgb(255, 255, 230)")
                            .set("padding", "8px")
                            .set("margin", "0")
                            .set("white-space", "pre-wrap")
                            .set("word-wrap", "break-word")
                            .set("font-size", "var(--lumo-font-size-s)")
                            .set("max-height", "200px")
                            .set("overflow-y", "auto")
                            .set("border", "1px solid #e0d890")
                            .set("border-radius", "4px");

                    Details promptDetails = new Details("Prompt Guide", promptPre);
                    promptDetails.setOpened(false);
                    promptDetails.setWidthFull();
                    subContent.add(promptDetails);
                }

                // AI columns side by side
                HorizontalLayout columnsLayout = new HorizontalLayout();
                columnsLayout.setSpacing(true);
                columnsLayout.setPadding(false);
                columnsLayout.getStyle().set("overflow-x", "auto")
                                        .set("flex-wrap", "nowrap")
                                        .set("align-items", "stretch");
                columnsLayout.setWidthFull();
                columnsLayout.setMinHeight("500px");

                for (File jsonFile : jsonFiles) {
                    ComparisonColumnComponent column = new ComparisonColumnComponent(
                            jsonFolderPath,
                            jsonFile.getName(),
                            sectionName,
                            subSectionName);
                    columnsLayout.add(column);
                    allColumns.add(column);
                }

                subContent.add(columnsLayout);
                subContent.setFlexGrow(1, columnsLayout);

                subSectionTabSheet.add(formatSubSectionTabName(subSectionName), subContent);
            }

            sectionTabSheet.add(formatSectionTabName(sectionName), subSectionTabSheet);
        }

        contentArea.add(sectionTabSheet);
        contentArea.setFlexGrow(1, sectionTabSheet);
    }

    // =========================================================================
    // Tab name formatting
    // =========================================================================

    private static String formatSectionTabName(String sectionName) {
        Map<String, String> abbreviations = new LinkedHashMap<>();
        abbreviations.put("study_identification_part", "SI");
        abbreviations.put("study_characteristics_part", "SC");
        abbreviations.put("reference_cohort_and_imaging_part", "RCI");
        abbreviations.put("reference_cohort_info_part", "RCI");
        abbreviations.put("normative_modeling_part", "NM");
        abbreviations.put("normative_modeling_2nd_part", "NM2");
        abbreviations.put("clinical_application_and_analysis_part", "CAA");
        abbreviations.put("general_note_part", "GN");
        abbreviations.put("common_part", "CM");
        abbreviations.put("clinical_research_part", "CR");
        abbreviations.put("dataset_characteristics_part", "DC");

        String abbr = abbreviations.get(sectionName);
        if (abbr != null) return abbr;

        // Fallback: first letters of words (excluding "part")
        String[] parts = sectionName.split("_");
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty() && !part.equals("part")) {
                sb.append(Character.toUpperCase(part.charAt(0)));
            }
        }
        return sb.length() > 0 ? sb.toString() : sectionName;
    }

    private static String formatSubSectionTabName(String subSectionName) {
        String[] parts = subSectionName.split("_");
        if (parts.length >= 2) {
            String prefix = parts[0].toUpperCase();
            if (parts[1].matches("\\d+")) {
                return prefix + parts[1];
            }
            return prefix;
        }
        return subSectionName.toUpperCase();
    }

    // =========================================================================
    // Save All / Load All
    // =========================================================================

    private void onSaveAll() {
        if (allColumns.isEmpty()) {
            Notification.show("Nothing to save. Load data first.", 3000, Notification.Position.MIDDLE);
            return;
        }
        for (ComparisonColumnComponent col : allColumns) {
            col.saveJson();
        }
        Notification.show("All columns saved.", 3000, Notification.Position.BOTTOM_START);
    }

    private void onLoadAll() {
        if (allColumns.isEmpty()) {
            Notification.show("Nothing to reload. Load data first.", 3000, Notification.Position.MIDDLE);
            return;
        }
        for (ComparisonColumnComponent col : allColumns) {
            col.loadJson();
        }
        Notification.show("All columns reloaded.", 3000, Notification.Position.BOTTOM_START);
    }
}
