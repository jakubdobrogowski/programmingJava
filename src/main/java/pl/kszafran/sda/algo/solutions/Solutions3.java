package pl.kszafran.sda.algo.solutions;

import pl.kszafran.sda.algo.exercises.Exercises3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Comparator.comparing;

public class Solutions3 extends Exercises3 {

    @Override
    public List<File> findFiles(File directory, String regex) throws IOException {
        if (directory.isFile()) {
            return directory.getName().matches(regex) ? singletonList(directory) : emptyList();
        }

        File[] files = directory.listFiles();
        if (files == null) {
            return emptyList();
        }

        List<File> found = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                found.addAll(findFiles(file, regex));
            } else if (file.getName().matches(regex)) {
                found.add(file);
            }
        }
        found.sort(comparing(File::getAbsolutePath));
        return found;
    }

    @Override
    public Stream<Path> findFilesNIO(Path directory, Pattern regex) throws IOException {
        if (!Files.exists(directory)) {
            return Stream.empty();
        }
        return Files.walk(directory)
                .filter(path -> regex.matcher(path.getFileName().toString()).matches())
                .sorted();
    }
}
