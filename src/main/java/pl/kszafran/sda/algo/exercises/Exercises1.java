package pl.kszafran.sda.algo.exercises;

import java.util.Set;

/**
 * Zaimplementuj poniższe metody Z WYKORZYSTANIEM REKURENCJI.
 */
public class Exercises1 {

    /**
     * Oblicza silnię argumentu n. Zwraca 1 dla n = 0.
     */


    public int factorial(int n) {

        return n != 0 ? n * factorial(n - 1) : 1;
    }

    /**
     * Oblicza sumę wszystkich liczb w podanej tablicy.
     * Uwaga: aby policzyć sumę rekurencyjnie, potrzebna będzie dodatkowa metoda.
     */
    public int sum(int[] numbers) {

        return newSum(numbers, 0);
    }

    private int newSum(int[] numbers, int offset) {

        return numbers.length != offset ? numbers[offset] + newSum(numbers, offset + 1) : 0;
    }


    /**
     * Odwraca podany napis, np. dla "test" zwraca "tset".
     */


    public String reverse(String text) {

        return text.length() != 0 ? text.substring(text.length() - 1) +
                reverse(text.substring(0, text.length() - 1)) : "";


    }

    /**
     * Oblicza NWW (najmniejszą wspólną wielokrotnosć) podanych argumentów.
     * <p>
     * (po angielsku LCM - Least Common Multiple)
     * <p>
     * Podpowiedź:
     * NWW(a, b) = a * b / NWD(a, b)
     * NWW(a1, a2, ..., an) = NWW(a1, NWW(a2, a3, ..., an))
     *
     * @throws IllegalArgumentException jeśli któryś z argument jest ujemny lub gdy nie podano żadnych argumentów
     */
    public int lcm(int... numbers) {

        return 0;
    }


    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    /**
     * Zwraca wszystkie możliwe permutacje podanego ciągu (różnych) znaków.
     * Np. dla "123" zwraca ["123", "132", "213", "231", "312", "321"].
     */
    public Set<String> permutations(String string) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
