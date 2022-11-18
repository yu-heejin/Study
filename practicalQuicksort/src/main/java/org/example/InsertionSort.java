package org.example;


public class InsertionSort {
    public void insertionSort(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            int target = numbers[i];
            int j = i - 1;

            while (j >= 0 && target < numbers[j]) {
                numbers[j + 1] = numbers[j];
                j--;
            }

            numbers[j + 1] = target;
        }
    }
}
