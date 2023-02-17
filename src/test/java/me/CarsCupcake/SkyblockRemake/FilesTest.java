package me.CarsCupcake.SkyblockRemake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesTest {
    @Test
    @DisplayName("File Loading Test")
    public void tryLoad() throws URISyntaxException {
        URI uri = FileSystems.class.getResource("/assets/").toURI();
        Path Path;
        FileSystem fileSystem;
        if (uri.getScheme().equals("jar")) {
            fileSystem = FileSystems.getFileSystem(uri);
            Path = fileSystem.getPath("/assets/");
        } else {
            Path = Paths.get(uri);
        }
        System.out.println(Path);
    }
}
