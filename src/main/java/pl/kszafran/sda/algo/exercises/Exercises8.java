package pl.kszafran.sda.algo.exercises;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Exercises8 {

    /**
     * Funkcja zwraca zbiór wartości, które występują więcej niż raz w liście "values".
     *
     * Uwaga: rozwiązanie musi działać w czasie O(n).
     */
    public <T> Set<T> findDuplicates(List<T> values) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Funkcja zwraca mapę przypisującą każdej wartości jej ilość wystąpień w liście "values".
     */
    public <T> Map<T, Integer> countOccurrences(List<T> values) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Funkcja zwraca zbiór wszystkich wartości występujących w obu podanych listach.
     */
    public <T> Set<T> findCommonValues(List<T> list1, List<T> list2) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Funkcja łączy wartości nagłówków HTTP o tej samej nazwie.
     *
     * Argumentem funkcji jest lista nagłówków HTTP w postaci "nazwa:wartość".
     * Każdy nagłówek znajduje się w osobnej linijce.
     * Nazwa nagłówka nie może zawierać znaku ":".
     *
     * Jeśli nagłówek o tej samej nazwie występuje wielokrotnie, w wartości wynikowej powinien
     * pojawić się tylko raz, a jego wartości powinny zostać złączone znakiem ",".
     *
     * Np. dla:
     *
     * aaa:123
     * bbb:897
     * aaa:432
     *
     * funkcja zwraca:
     *
     * aaa:123,432
     * bbb:897
     *
     * Jeśli ta sama wartość pojawia się wielokrotnie, powinna zostać zamieszczona tylko raz.
     *
     * Np. dla:
     *
     * aaa:123
     * bbb:897
     * aaa:123
     *
     * funkcja zwraca:
     *
     * aaa:123
     * bbb:897
     *
     * Względna kolejność nagłówków musi zostać zachowana.
     * Względna kolejność wartości danego nagłówka musi zostać zachowana.
     *
     * @throws IllegalArgumentException jeśli linijka zawiera niepoprawny nagłówek, puste linie są dopuszczalne
     */
    public String mergeHeaders(String headers) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Funkcja działa tak samo jak mergeHeaders, z tym że:
     * - nagłówki zwracane są w kolejności alfabetycznej
     * - wartości danego nagłówka ustawione są w kolejności alfabetycznej
     */
    public String normalizeHeaders(String headers) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
