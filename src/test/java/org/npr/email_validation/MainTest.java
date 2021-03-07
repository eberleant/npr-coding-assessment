package org.npr.email_validation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.file.FileVisitResult.CONTINUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MainTest {
    private static final Logger LOGGER = Logger.getLogger(MainTest.class.getName());
    private static final Map<String, String[]> testFiles = Map.of(
            "1", new String[]{"b@b.b\na@a.a\nc.c.c", "a@a.a\nb@b.b"},
            "2", new String[]{"invalid", ""});
    private static final String testDir = "test_temp";

    @BeforeAll
    public static void setUp() throws IOException{
        Path testDirPath = Paths.get(testDir);
        Files.createDirectory(testDirPath);
        LOGGER.log(Level.INFO, "Created test directory");
    }

    @AfterAll
    public static void cleanUp() throws IOException {
        // cleanup files
        Files.walkFileTree(Paths.get(testDir), new DeleteFiles());
        // cleanup directory
        Files.delete(Paths.get(testDir));
        LOGGER.log(Level.INFO, "Deleted test directory");
    }

    @Test
    public void testMain() {
        // create files
        testFiles.forEach((name, contents) -> {
            Path path = getPath(name);
            try {
                Files.createFile(path);
                Files.writeString(path, contents[0]);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Failed to create " + path.toString(), e);
            }
        });
        // run main and validate output files
        Main.main(new String[]{getPath("1").toString(), getPath("2").toString()});
        testFiles.forEach((name, contents) -> {
            Path path = getPath(name + "_output");
            try {
                String actualContents = Files.readString(path);
                assertEquals(contents[1], actualContents);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Failed to read " + path.toString(), e);
            }
        });
    }

    @Test
    public void testFileDoesNotExist() {
        Main.main(new String[]{getPath("nonexistent").toString()});
        assertFalse(Files.exists(getPath("nonexistent")));
        assertFalse(Files.exists(getPath("nonexistent_output")));
    }

    private static Path getPath(String file) {
        return Paths.get(testDir + File.separator + file + ".txt");
    }

    /**
     * FileVisitor that deletes each file it visits
     */
    private static class DeleteFiles extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
            Files.delete(file);
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to delete " + file.getFileName(), e);
            return CONTINUE;
        }
    }
}