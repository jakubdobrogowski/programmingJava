package pl.kszafran.sda.algo.exercises;

import pl.kszafran.sda.algo.exercises.sorting.*;

import java.util.Comparator;

/**
 * Zaimplementuj poniższe algorytmy sortowania.
 * <p>
 * Uwaga: quicksort oraz merge sort najłatwiej zaimplementować z wykorzystaniem rekurencji.
 */
public class Exercises2 {

    public IntSortingAlgorithm createSelectionSort() {

        return new SelectionSort();
    }

    public IntSortingAlgorithm createInsertionSort() {

        return new InsertionSort();
    }

    public IntSortingAlgorithm createQuicksort() {
//        return new Quicksort();
        throw new UnsupportedOperationException("Not implemented yet");
    }

    ////////////////////////////////////////////
    //                                        //
    // PONIŻEJ ZADANIA DODATKOWE DLA CHĘTNYCH //
    //                                        //
    ////////////////////////////////////////////

    public IntSortingAlgorithm createMergeSort() {

        return new MergeSort();
    }

    /**
     * Tworzy object pozwalający sortować Listy dowolnego typu.
     * <p>
     * Uwaga: Implementacja może wykorzystywać dowolny algorytm sortowania.
     */
    public <T> SortingAlgorithm<T> createSortingAlgorithm(Comparator<T> comparator) {
//        return new SortingAlgorithmImpl<>(comparator);
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
