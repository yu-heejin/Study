package org.example;

import java.util.List;

public class InsertionSort {
    public void insertionSort(List<Integer> numbers) {
        for (int i = 1; i < numbers.size(); i++) {
            int target = numbers.get(i);
            int j = i - 1;

            while (j >= 0 && target < numbers.get(j)) {
                numbers.set(j + 1, numbers.get(j));
                j--;
            }

            numbers.set(j + 1, target);
        }
    }
}
