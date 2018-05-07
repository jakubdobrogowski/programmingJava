package pl.kszafran.sda.algo.exercises;


import java.util.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


/**
 * Zaimplementuj poniższe algorytmy wyszukiwania.
 */
public class Exercises3 {

    /**
     * Wyszukuje element o wartości value w podanej tablicy i zwraca jego indeks.
     * Zwraca -1 jeśli element nie znajduje się w tablicy.
     */
    public int linearSearch(int[] array, int value) {

        for (int i = 0; i < array.length; i++) {

            if (array[i] == value) {

                return i;
            }
        }
        return -1;
    }

    /**
     * Wyszukuje element o wartości value w podanej POSORTOWANEJ tablicy i zwraca jego indeks.
     * Zwraca -1 jeśli element nie znajduje się w tablicy.
     */
    public int binarySearch(int[] array, int value) {
        int begin = 0;
        int end = array.length;
        int x = 0;

        for (int j = 0; j < array.length; j++) {

            x = (end - begin) / 2;

            if (array[j] == value) {

                return j;
            }

            if (array[j] >= value) {

                end = x;

            } else {

                begin = x;
            }
        }
        return -1;
    }

    /**
     * Wyszukuje element o wartości value w podanej POSORTOWANEJ liście i zwraca jego indeks.
     */
    public <T> Optional<Integer> indexOf(List<T> list, T value, Comparator<T> comparator) {

        int begin = 0;
        int end = list.size();
        int x = 0;

        for (int j = 0; j < list.size(); j++) {

            x = (end - begin) / 2;

            if (comparator.compare(list.get(j), value) == 0) {   //list.get(j) == value

                return Optional.of(j);
            }

            if (comparator.compare(list.get(j), value) < 0) {  //list.get(j) >= value

                end = x;

            } else {

                begin = x;
            }
        }
        return Optional.empty();

    }

    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    /**
     * Wyszukuje wszystkie elementy o wartości value w podanej POSORTOWANEJ tablicy i zwraca zakres ich indeksów.
     * <p>
     * Uwaga: istnieją dwie możliwe implementacje, jedna o stałej złożoności O(log n)
     * oraz druga, która potrafi zdegradować się do złożoności O(n) w najgorszym przypadku.
     */
    public Optional<IntRange> binarySearchRange(int[] array, int value) {

        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Wyszukuje element o wartości value w podanej POSORTOWANEJ tablicy i zwraca jego indeks.
     * Zwraca -1 jeśli element nie znajduje się w tablicy.
     * <p>
     * Uwaga: należy zastosować wyszukiwanie interpolacyjne (interpolation search).
     */
    public int interpolationSearch(int[] array, int value) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static class IntRange {

        private final int start;
        private final int end;

        public IntRange(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }


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


