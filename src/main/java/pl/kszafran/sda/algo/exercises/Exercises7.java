package pl.kszafran.sda.algo.exercises;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Exercises7 {

    /**
     * Zwraca true jeśli elementy w podanej tablicy tworzą kopiec,
     * tzn. tworzą kompletne drzewo binarne, w którym każdy węzeł
     * ma wartość większą lub równą wartości swoich dzieci.
     */
    public boolean isHeap(int[] array) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public <T extends Comparable<T>> SdaHeap<T> createHeap(T[] heap, int capacity) {
        return new FixedSizeHeapSdaHeap<>(heap, capacity);
    }

    public interface SdaHeap<T extends Comparable<T>> {

        /**
         * Dodaje nowy element do kopca. Po dodaniu elementu do tablicy, własność kopca musi zostać zachowana.
         *
         * @throws IllegalStateException jeśli kopiec jest pełny
         */
        void push(T element);

        /**
         * Usuwa z kopca i zwraca element o najwyższej wartości.
         *
         * @throws NoSuchElementException jeśli kopiec jest pusty
         */
        T pop();

        /**
         * Zwraca ilość elementów na kopcu.
         */
        int size();

        /**
         * Zwraca tablicę zawierającą elementy kopca.
         */
        T[] toArray();
    }

    private static class FixedSizeHeapSdaHeap<T extends Comparable<T>> implements SdaHeap<T> {

        private T[] heap;
        private int size;

        // zakładamy, że "heap" jest poprawnym kopcem
        public FixedSizeHeapSdaHeap(T[] heap, int capacity) {
            this.heap = Arrays.copyOf(heap, capacity);
            this.size = heap.length;
        }

        @Override
        public void push(T element) {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Override
        public T pop() {
            throw new UnsupportedOperationException("Not implemented yet");
        }

        @Override
        public int size() {
            return size;
        }

        public T[] toArray() {
            return Arrays.copyOf(heap, size);
        }
    }
}
