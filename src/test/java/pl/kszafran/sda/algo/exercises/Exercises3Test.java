package pl.kszafran.sda.algo.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercises3Test {

    private Exercises3 exercises = new Exercises3();

    private Path tempDir;
    private Path file1txt;
    private Path file2txt;

    private Path subDir;
    private Path subfile1txt;
    private Path subfile2txt;
    private Path subfile1log;

    /**
     * Przygotowuje poniższą strukturę plików:
     *
     * java97029848529390830660
     * ├── file1.txt
     * ├── file2.txt
     * └── sub
     *     ├── subfile1.log
     *     ├── subfile1.txt
     *     └── subfile2.txt
     */
    @BeforeEach
    void setUp() throws IOException {
        tempDir = Files.createTempDirectory("java9");
        tempDir.toFile().deleteOnExit();
        file1txt = Files.createFile(tempDir.resolve("file1.txt"));
        file2txt = Files.createFile(tempDir.resolve("file2.txt"));

        subDir = Files.createDirectory(tempDir.resolve("sub"));
        subfile1log = Files.createFile(subDir.resolve("subfile1.log"));
        subfile1txt = Files.createFile(subDir.resolve("subfile1.txt"));
        subfile2txt = Files.createFile(subDir.resolve("subfile2.txt"));
    }

    @Test
    void test_findFiles() throws IOException {
        assertEquals(
                List.of(file1txt.toFile(), file2txt.toFile()),
                exercises.findFiles(tempDir.toFile(), "file.*"));

        assertEquals(
                List.of(file1txt.toFile(), file2txt.toFile(), subfile1txt.toFile(), subfile2txt.toFile()),
                exercises.findFiles(tempDir.toFile(), ".*file\\d+.txt"));

        assertEquals(
                List.of(file1txt.toFile()),
                exercises.findFiles(file1txt.toFile(), ".*")); // not a directory

        assertEquals(
                emptyList(),
                exercises.findFiles(new File("does/not/exist"), ".*"));
    }

    @Test
    void test_findFilesNIO() throws IOException {
        assertEquals(
                List.of(file1txt, file2txt),
                exercises.findFilesNIO(tempDir, Pattern.compile("file.*")).collect(toList()));

        assertEquals(
                List.of(file1txt, file2txt, subfile1txt, subfile2txt),
                exercises.findFilesNIO(tempDir, Pattern.compile(".*file\\d+.txt")).collect(toList()));

        assertEquals(
                List.of(file1txt),
                exercises.findFilesNIO(file1txt, Pattern.compile(".*")).collect(toList())); // not a directory

        assertEquals(
                emptyList(),
                exercises.findFilesNIO(Paths.get("does/not/exist"), Pattern.compile(".*")).collect(toList()));
    }
}
