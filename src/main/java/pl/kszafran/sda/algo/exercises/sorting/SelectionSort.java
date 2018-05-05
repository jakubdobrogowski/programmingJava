package pl.kszafran.sda.algo.exercises.sorting;

public class SelectionSort implements IntSortingAlgorithm {

    @Override
    public void sort(int[] array) {


        for (int x = 0; x < array.length; x++) {
            int min = x;
            for (int i = x; i < array.length; i++) {

                if (array[min] > array[i]) {

                    min = i;
                }
            }
            swap(array, min, x);
        }
    }

    public void swap(int[] array, int i, int j) {

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
