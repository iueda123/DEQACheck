package iu.SpringBoot.Vaadin.DEQACheckAll.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 外部JARファイルを起動するユーティリティクラス
 */
public class ExternalJarLauncher {

    /**
     * 外部JARファイルを起動する
     *
     * @param jarPath          JARファイルのパス
     * @param workingDirectory 作業ディレクトリ
     * @param authorYear       AuthorYear引数
     * @return 起動成功時はtrue、失敗時はfalse
     */
    public static boolean launch(String jarPath, String workingDirectory, String authorYear) {
        try {
            Path jar = Paths.get(jarPath);
            Path projectRootPath = Paths.get(System.getProperty("user.dir"), workingDirectory);

            System.out.println("java -jar " + jar.toAbsolutePath());

            ProcessBuilder pb = new ProcessBuilder(
                    "java", "-jar", jar.toAbsolutePath().toString(),
                    authorYear
            );
            pb.directory(projectRootPath.toFile());
            pb.inheritIO();
            pb.start();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * jarディレクトリから最初に見つかったJARファイルのパスを解決する
     *
     * @return JARファイルのPath（見つからない場合はEmpty）
     */
    public static Optional<Path> resolveJarPath() {
        try {
            Path jarDir = Paths.get(System.getProperty("user.dir"), "jar");
            if (!Files.isDirectory(jarDir)) {
                return Optional.empty();
            }
            try (Stream<Path> s = Files.list(jarDir)) {
                return s.filter(p -> p.getFileName().toString().toLowerCase().endsWith(".jar"))
                        .findFirst();
            }
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}