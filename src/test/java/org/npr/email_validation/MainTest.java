package org.npr.email_validation;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    public void testMain() throws IOException {
        // create files
        String testDir = "test_temp";
        Path testDirPath = Paths.get(testDir);
        Files.createDirectory(testDirPath);
        Map<String, String[]> testFiles = Map.of(
                "1", new String[]{"b@b.b\na@a.a\nc.c.c", "a@a.a\nb@b.b"},
                "2", new String[]{"invalid", ""});
        testFiles.forEach((name, contents) -> {
            Path path = getPath(testDir, name);
            try {
                Files.createFile(path);
                Files.writeString(path, contents[0]);
            } catch (IOException e) {
                System.err.println("Failed to create " + path.toString() + ". Error: " + e.toString());
            }
        });
        // run main and validate output files
        Main.main(new String[]{getPath(testDir, "1").toString(), getPath(testDir, "2").toString()});
        testFiles.forEach((name, contents) -> {
            Path path = getPath(testDir, name + "_output");
            try {
                String actualContents = Files.readString(path);
                assertEquals(contents[1], actualContents);
            } catch (IOException e) {
                System.err.println("Failed to read " + path.toString() + ". Error: " + e.toString());
            } finally {
                // cleanup files
                try {
                    Files.delete(getPath(testDir, name));
                    Files.delete(path);
                } catch (IOException e) {
                    System.err.println("Failed to delete file. Error: " + e.toString());
                }
            }
        });
        // cleanup directory
        Files.delete(testDirPath);
    }

    private Path getPath(String dir, String file) {
        return Paths.get(dir + File.separator + file + ".txt");
    }
}