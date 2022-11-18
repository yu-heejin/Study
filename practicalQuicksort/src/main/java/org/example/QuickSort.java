package org.example;

import java.util.List;

public class QuickSort {
    public QuickSort(List<Integer> numbers, int start, int end) {
        quickSort(numbers, start, end);
    }
    public void quickSort(List<Integer> numbers, int start, int end) {
        int part = partition(numbers, start, end);
        if (start < part - 1) quickSort(numbers, start, part - 1);
        if (end > part) quickSort(numbers, part, end);
    }

    public int partition(List<Integer> numbers, int start, int end) {
        int pivot = numbers.get((start + end) / 2);

        while (start <= end) {
            while (numbers.get(start) < pivot) {
                start++;
            }

            while (numbers.get(end) > pivot) {
                end--;
            }

            if(start <= end) {
                swap(numbers, start, end);
                start++;
                end--;
            }
        }

        return start;
    }

    public void swap(List<Integer> numbers, int start, int end) {
        int temp = numbers.get(start);
        numbers.set(start, numbers.get(end));
        numbers.set(end, temp);
    }
}
