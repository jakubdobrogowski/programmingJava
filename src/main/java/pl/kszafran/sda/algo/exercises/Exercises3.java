package pl.kszafran.sda.algo.exercises;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Exercises3 {

    /**
     * Znajduje wszystkie pliki o nazwie pasującej do podanego wyrażenia regularnego
     * w podanym katalogu i wszystkich jego podkatalogach.
     * <p>
     * Zwrócone pliki są posortowane alfabetycznie po pełnej ścieżce (nie tylko nazwie pliku).
     * <p>
     * Jeśli directory wskazuje na plik, zwrócona lista ma zawierać tylko ten plik
     * (jeśli jego nazwa pasuje do wyrażenia regularnego).
     * <p>
     * Uwaga: należy skupić się na klasach z pakietu java.io.
     */
    public List<File> findFiles(File directory, String regex) throws IOException {

        ArrayList<File> result = new ArrayList<>();

        File[] files = directory.listFiles();

        if (!directory.exists()) {

            return result;
        }

        if (directory.isFile()) {
            if (directory.getName().matches(regex)) {
                result.add(directory);
            }
        }

        for (File file : files != null ? files : new File[0]) {

            if (file.isDirectory()) {
                result.addAll(findFiles(file, regex));
            }

            if (file.getName().matches(regex)) {
                result.add(file);
            }
        }

        result.sort(Comparator.naturalOrder());
        return result;
    }


    /**
     * Działa tak samo jak findFiles().
     * <p>
     * Uwaga: należy korzystac z dobrodziejstw pakietu java.nio.file.
     */
    public Stream<Path> findFilesNIO(Path directory, Pattern regex) throws IOException {

        if (!Files.exists(directory)) {
            return Stream.empty();
        }

        return Files.walk(directory)
                .filter(e -> e.getFileName().toString().matches(String.valueOf(regex)))
                .sorted(Path::compareTo);

    }
}

