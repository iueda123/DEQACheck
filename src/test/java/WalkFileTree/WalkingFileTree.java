package WalkFileTree;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class WalkingFileTree {

    static Path startPointPath = Paths.get("/home/iu/Downloads");

    public static void main(String[] args){

        try {
            Files.walkFileTree(startPointPath, new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                    System.out.println(file.toFile().getAbsolutePath());

                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
