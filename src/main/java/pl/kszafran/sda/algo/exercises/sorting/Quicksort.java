package pl.kszafran.sda.algo.exercises.sorting;

import pl.kszafran.sda.algo.exercises.sorting.IntSortingAlgorithm;

public class Quicksort implements IntSortingAlgorithm {

    @Override
    public void sort(int[] array) {

        int pivotIndex = 0;
        for (int i = 1; i < array.length; i++) {

            if (array[i] < array[pivotIndex]) {

                swap(array, i, pivotIndex);
                pivotIndex++;
            }
            swap(array, pivotIndex, pivotIndex - 1);
        }
    }

    public void swap(int[] array, int i, int j) {

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
