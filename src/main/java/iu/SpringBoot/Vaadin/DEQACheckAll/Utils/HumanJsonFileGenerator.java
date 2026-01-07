package iu.SpringBoot.Vaadin.DEQACheckAll.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Human系JSONファイルを生成するユーティリティクラス
 */
public class HumanJsonFileGenerator {

    /**
     * Human系JSONファイルが存在しない場合、テンプレートから生成する
     *
     * @param pathListOfAuthorYearDir AuthorYearディレクトリのリスト
     * @param templatePath テンプレートファイルのパス
     * @return 生成結果のメッセージリスト
     */
    public static List<String> ensureHumanJsonFiles(List<Path> pathListOfAuthorYearDir, String templatePath) {
        List<String> messages = new ArrayList<>();
        Path template = Paths.get(System.getProperty("user.dir"), templatePath);

        if (!Files.exists(template)) {
            messages.add("テンプレートファイルが見つかりません: " + template);
            return messages;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);

        for (Path authorYearPath : pathListOfAuthorYearDir) {
            String authorYear = authorYearPath.getFileName().toString();
            Path jsonDir = authorYearPath.resolve("DE").resolve("json");

            // jsonディレクトリが存在しない場合は作成
            if (!Files.exists(jsonDir)) {
                try {
                    Files.createDirectories(jsonDir);
                } catch (IOException e) {
                    messages.add(authorYear + ": jsonディレクトリの作成に失敗 - " + e.getMessage());
                    continue;
                }
            }

            // Human系ファイルの存在確認
            boolean hasHumanFile;
            try {
                hasHumanFile = Files.list(jsonDir)
                        .filter(Files::isRegularFile)
                        .anyMatch(p -> {
                            String name = p.getFileName().toString().toLowerCase();
                            return name.endsWith(".json") && name.contains("human");
                        });
            } catch (IOException e) {
                messages.add(authorYear + ": ファイルの確認に失敗 - " + e.getMessage());
                continue;
            }

            // Human系ファイルが存在しない場合、テンプレートから生成
            if (!hasHumanFile) {
                String newFileName = String.format("DE_%s_by_human_%s.json", authorYear, timestamp);
                Path targetPath = jsonDir.resolve(newFileName);

                try {
                    Files.copy(template, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    messages.add("生成しました: " + newFileName + " at " + targetPath.toFile().getParent());
                } catch (IOException e) {
                    messages.add(authorYear + ": ファイルの生成に失敗 - " + e.getMessage());
                }
            }
        }

        return messages;
    }
}