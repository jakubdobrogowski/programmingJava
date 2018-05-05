package pl.kszafran.sda.algo.exercises.sorting;

import pl.kszafran.sda.algo.exercises.sorting.IntSortingAlgorithm;

public class MergeSort implements IntSortingAlgorithm {

    @Override
    public void sort(int[] array) {

        newSort(array.clone(), array, 0, array.length);
    }

    private void newSort(int[] sortedArray, int[] array, int begin, int end) {

        int mid = (begin + end) / 2;
        if (end - begin == 1) {

            for (int i = begin; i < end; i++) {

                if (array[begin] <= array[mid] || mid == end) {

                    sortedArray[begin] = array[begin];
                } else {

                    sortedArray[begin] = array[mid];
                }
            }
        }

        newSort(array, sortedArray, begin, mid);
        newSort(array, sortedArray, mid, end);
    }
}
