package pl.kszafran.sda.algo.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.*;

public class Exercises8Test {

    private Exercises8 exercises = new Exercises8();

    @Test
    void test_findDuplicates() {
        assertEquals(Set.of("dwa"), exercises.findDuplicates(List.of("jeden", "dwa", "trzy", "dwa", "cztery")));
        assertEquals(Set.of(7, 8), exercises.findDuplicates(List.of(8, 1, 7, 8, 8, 8, 7, 9, 10)));
        assertTrue(exercises.findDuplicates(emptyList()).isEmpty());
    }

    @Test
    void test_countOccurrences() {
        assertEquals(
                Map.of("jeden", 1, "dwa", 2, "trzy", 1, "cztery", 1),
                exercises.countOccurrences(List.of("jeden", "dwa", "trzy", "dwa", "cztery")));
        assertEquals(
                Map.ofEntries(entry(1, 1), entry(7, 2), entry(8, 4), entry(9, 1), entry(10, 1)),
                exercises.countOccurrences(List.of(8, 1, 7, 8, 8, 8, 7, 9, 10)));
        assertTrue(exercises.countOccurrences(emptyList()).isEmpty());
    }

    @Test
    void test_findCommonValues() {
        assertEquals(
                Set.of(1, 5),
                exercises.findCommonValues(List.of(3, 1, 9, 3, 5, 1, 8, 5), List.of(2, 5, 1, 6, 2, 6, 2, 6)));
        assertTrue(exercises.findCommonValues(List.of(3, 1, 9, 3, 5, 1, 8, 5), emptyList()).isEmpty());
        assertTrue(exercises.findCommonValues(emptyList(), List.of(3, 1, 9, 3, 5, 1, 8, 5)).isEmpty());
        assertTrue(exercises.findCommonValues(List.of(3, 1, 9, 3), List.of(8, 7, 4)).isEmpty());
        assertTrue(exercises.findCommonValues(emptyList(), emptyList()).isEmpty());
    }

    @Test
    void test_mergeHeaders() {
        assertEquals(
                String.join("\n",
                        "Content-Type:text/html; charset=UTF-8",
                        "Date:Thu, 24 May 2018 21:03:35 GMT",
                        "Accept:application/json,multipart/related;type=text/html,application/xml",
                        "Expires:Sat, 23 Jun 2018 21:03:35 GMT",
                        "Cache-Control:public,max-age=2592000",
                        "Server:gws",
                        "Content-Length:218"),
                exercises.mergeHeaders(String.join("\n",
                        "Content-Type:text/html; charset=UTF-8",
                        "Date:Thu, 24 May 2018 21:03:35 GMT",
                        "Accept:application/json",
                        "Expires:Sat, 23 Jun 2018 21:03:35 GMT",
                        "Cache-Control:public",
                        "Content-Type:text/html; charset=UTF-8",
                        "Accept:multipart/related;type=text/html",
                        "Server:gws",
                        "Accept:application/xml",
                        "Content-Length:218",
                        "Cache-Control:max-age=2592000",
                        "")));
        assertEquals("Header:value", exercises.mergeHeaders("Header:value"));
        assertEquals("", exercises.mergeHeaders(""));
        assertThrows(IllegalArgumentException.class, () -> exercises.mergeHeaders("Header=value"));
    }

    @Test
    void test_normalizeHeaders() {
        assertEquals(
                String.join("\n",
                        "Accept:application/json,application/xml,multipart/related;type=text/html",
                        "Cache-Control:max-age=2592000,public",
                        "Content-Length:218",
                        "Content-Type:text/html; charset=UTF-8",
                        "Date:Thu, 24 May 2018 21:03:35 GMT",
                        "Expires:Sat, 23 Jun 2018 21:03:35 GMT",
                        "Server:gws"),
                exercises.normalizeHeaders(String.join("\n",
                        "Content-Type:text/html; charset=UTF-8",
                        "Date:Thu, 24 May 2018 21:03:35 GMT",
                        "Accept:application/json",
                        "Expires:Sat, 23 Jun 2018 21:03:35 GMT",
                        "Cache-Control:public",
                        "Content-Type:text/html; charset=UTF-8",
                        "Accept:application/xml",
                        "Server:gws",
                        "Accept:multipart/related;type=text/html",
                        "Content-Length:218",
                        "Cache-Control:max-age=2592000",
                        "")));
        assertEquals("Header:value", exercises.normalizeHeaders("Header:value"));
        assertEquals("", exercises.normalizeHeaders(""));
        assertThrows(IllegalArgumentException.class, () -> exercises.normalizeHeaders("Header=value"));
    }
}
