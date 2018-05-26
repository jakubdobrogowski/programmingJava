package pl.kszafran.sda.algo.exercises.sorting;

import pl.kszafran.sda.algo.exercises.sorting.IntSortingAlgorithm;

public class InsertionSort implements IntSortingAlgorithm {

    @Override
    public void sort(int[] array) {

        for (int i = 1; i < array.length ; i++) {
            if (array[i] < array[i - 1]) {
                for (int current = i; current > 0; current--) {

                    if (array[current] < array[current - 1]) {

                        swap(array, current, current - 1);
                    }
                }
            }
        }
    }

    public void swap(int[] array, int i, int j) {

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
