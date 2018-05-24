package pl.kszafran.sda.algo.solutions;

import pl.kszafran.sda.algo.exercises.Exercises8;

import java.util.*;
import java.util.function.Supplier;

import static java.util.stream.Collectors.joining;

public class Solutions8 extends Exercises8 {

    @Override
    public <T> Set<T> findDuplicates(List<T> values) {
        Set<T> all = new HashSet<>();
        Set<T> duplicates = new HashSet<>();
        for (T value : values) {
            if (!all.add(value)) {
                duplicates.add(value);
            }
        }
        return duplicates;
    }

    @Override
    public <T> Map<T, Integer> countOccurrences(List<T> values) {
        Map<T, Integer> counts = new HashMap<>();
        for (T value : values) {
            counts.compute(value, (k, v) -> v == null ? 1 : v + 1);
        }
        return counts;
    }

    @Override
    public <T> Set<T> findCommonValues(List<T> list1, List<T> list2) {
        Set<T> intersection = new HashSet<>(list1);
        intersection.retainAll(list2);
        return intersection;
    }

    @Override
    public String mergeHeaders(String headers) {
        return mergeHeaders(headers, LinkedHashMap::new, LinkedHashSet::new);
    }

    private String mergeHeaders(String headers, Supplier<Map<String, Set<String>>> map, Supplier<Set<String>> set) {
        Map<String, Set<String>> merged = map.get();
        for (String header : headers.split("\n")) {
            String[] parts = header.split(":", 2);
            if (parts.length == 1 && parts[0].isEmpty()) {
                continue; // empty line
            }
            if (parts.length < 2) {
                throw new IllegalArgumentException("Invalid header: " + header);
            }
            merged.compute(parts[0], (name, value) -> {
                if (value == null) {
                    value = set.get();
                }
                value.add(parts[1]);
                return value;
            });
        }
        return merged.entrySet().stream()
                .map(entry -> entry.getKey() + ":" + String.join(",", entry.getValue()))
                .collect(joining("\n"));
    }

    @Override
    public String normalizeHeaders(String headers) {
        return mergeHeaders(headers, TreeMap::new, TreeSet::new);
    }
}
