package pl.kszafran.sda.algo.exercises;

import org.junit.platform.commons.util.StringUtils;

import java.util.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

public class Exercises8 {

    /**
     * Funkcja zwraca zbiór wartości, które występują więcej niż raz w liście "values".
     * <p>
     * Uwaga: rozwiązanie musi działać w czasie O(n).
     */
    public <T> Set<T> findDuplicates(List<T> values) {

        HashSet<T> set1 = new HashSet<>();
        HashSet<T> set = new HashSet<>();
        for (T value : values) {
            if (!set1.contains(value)) {
                set1.add(value);
            } else {
                set.add(value);
            }
        }

        return set;
    }

    /**
     * Funkcja zwraca mapę przypisującą każdej wartości jej ilość wystąpień w liście "values".
     */
    public <T> Map<T, Integer> countOccurrences(List<T> values) {

//        Map<T, Integer> count = new HashMap<>();
//
//        for (T value : values) {
//
//            if (count.containsKey(value)) {
//
//                count.put(value, count.get(value) + 1);
//            } else {
//
//                count.put(value, 1);
//            }
//        }
//        return count;

       // return values.stream().collect(toMap())

        return null;
    }

    /**
     * Funkcja zwraca zbiór wszystkich wartości występujących w obu podanych listach.
     */
    public <T> Set<T> findCommonValues(List<T> list1, List<T> list2) {

//        Set<T> set = new HashSet<>();
//
//        for (T element : list1) {
//
//            if (list2.contains(element)) {
//
//                set.add(element);
//            }
//        }
//        return set;

     //  return list1.stream().filter(list2::contains).collect(toSet());

        Set<T> intersection = new HashSet<>(list1);
        intersection.retainAll(list2);
        return intersection;
    }

    /**
     * Funkcja łączy wartości nagłówków HTTP o tej samej nazwie.
     * <p>
     * Argumentem funkcji jest lista nagłówków HTTP w postaci "nazwa:wartość".
     * Każdy nagłówek znajduje się w osobnej linijce.
     * Nazwa nagłówka nie może zawierać znaku ":".
     * <p>
     * Jeśli nagłówek o tej samej nazwie występuje wielokrotnie, w wartości wynikowej powinien
     * pojawić się tylko raz, a jego wartości powinny zostać złączone znakiem ",".
     * <p>
     * Np. dla:
     * <p>
     * aaa:123
     * bbb:897
     * aaa:432
     * <p>
     * funkcja zwraca:
     * <p>
     * aaa:123,432
     * bbb:897
     * <p>
     * Jeśli ta sama wartość pojawia się wielokrotnie, powinna zostać zamieszczona tylko raz.
     * <p>
     * Np. dla:
     * <p>
     * aaa:123
     * bbb:897
     * aaa:123
     * <p>
     * funkcja zwraca:
     * <p>
     * aaa:123
     * bbb:897
     * <p>
     * Względna kolejność nagłówków musi zostać zachowana.
     * Względna kolejność wartości danego nagłówka musi zostać zachowana.
     *
     * @throws IllegalArgumentException jeśli linijka zawiera niepoprawny nagłówek, puste linie są dopuszczalne
     */
    public String mergeHeaders(String headers) {

        if(StringUtils.isBlank(headers)){

            return "";
        }
        Map<String, Set<String>> map = new LinkedHashMap<>();
        for (String line : headers.split("\\n")) {
            if(!line.contains(":")){

                throw new IllegalArgumentException();
            }
            String[] split = line.split(":", 2);
            if (map.containsKey(split[0])) {

                Set<String> list = map.get(split[0]);
                    list.add(split[1]);
            } else {

                Set<String> list = new LinkedHashSet<>();
                list.add(split[1]);
                map.put(split[0], list);
            }
        }
        return map.entrySet().stream()
                .map(entry -> (entry.getKey() + ":" + String.join(",", entry.getValue())))
                .collect(joining("\n"));
    }

    /**
     * Funkcja działa tak samo jak mergeHeaders, z tym że:
     * - nagłówki zwracane są w kolejności alfabetycznej
     * - wartości danego nagłówka ustawione są w kolejności alfabetycznej
     */
    public String normalizeHeaders(String headers) {

        if(StringUtils.isBlank(headers)){

            return "";
        }

        Map<String, Set<String>> map = new TreeMap<>();
        for (String line : headers.split("\\n")) {
            if(!line.contains(":")){

                throw new IllegalArgumentException();
            }
            String[] split = line.split(":", 2);
            if (map.containsKey(split[0])) {

                Set<String> list = map.get(split[0]);
                list.add(split[1]);
            } else {

                Set<String> list = new TreeSet<>();
                list.add(split[1]);
                map.put(split[0], list);
            }
        }
        return map.entrySet().stream()
                .map(entry -> (entry.getKey() + ":" + String.join(",", entry.getValue())))
                .collect(joining("\n"));

    }
}
