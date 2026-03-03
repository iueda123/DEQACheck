package iu.SpringBoot.Vaadin.views.DEQACheckAll.DESummaryPage.SummaryRowsBuilder;

import iu.SpringBoot.Vaadin.views.DEQACheckAll.DESummaryPage.RowObject.RowObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Helper to construct row data for DE Summary View.
 */
public class SummaryRowsBuilder_for_DE_v10 {

    /**
     * Build list of RowObject from author-year directories.
     * Any message (e.g., error notes) will be reported via messageConsumer.
     */
    public static List<RowObject> constructRowObjectList(List<Path> pathListOfAuthorYearDir,
                                                         Consumer<String> messageConsumer) {
        List<RowObject> rows = new ArrayList<>();

        for (Path authorYearPath : pathListOfAuthorYearDir) {
            // Skip folder names that start with lowercase
            String folderName = authorYearPath.getFileName().toString();
            if (!folderName.isEmpty() && Character.isLowerCase(folderName.charAt(0))) {
                continue;
            }

            Path jsonDir = authorYearPath.resolve("DE_v10").resolve("json");
            if (!Files.exists(jsonDir) || !Files.isDirectory(jsonDir)) {
                continue;
            }

            // Collect Human json files
            List<Path> jsonFiles;
            try {
                jsonFiles = Files.list(jsonDir)
                        .filter(Files::isRegularFile)
                        .filter(p -> {
                            String name = p.getFileName().toString().toLowerCase();
                            return name.endsWith(".json") && name.contains("human");
                        })
                        .sorted(Comparator.comparing(p -> p.getFileName().toString().toLowerCase()))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                if (messageConsumer != null) {
                    messageConsumer.accept(authorYearPath.getFileName() + ": JSON ファイルの取得でエラー - " + e.getMessage());
                }
                continue;
            }

            for (Path jsonFile : jsonFiles) {
                rows.add(new RowObject(jsonFile));
            }
        }
        return rows;
    }
}

