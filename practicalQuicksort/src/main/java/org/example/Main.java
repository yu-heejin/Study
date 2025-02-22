package org.example;


public class Main {
    final static int K = 1000;
    final static int RANDOM_STANDARD = 999;
    final static double DIVIDE_NUMBER = 1000.0;

    private static final int[] STANDARD_INDEXES = { 17, 33, 65, 129, 257, 513 };
    private static final int[] ARRAY_SIZES = { 10, 100, 200, 400, 800, 1600, 3200, 6400 };


    public static void main(String[] args) {
        DualPivotQuickSort dualPivotQuickSort = new DualPivotQuickSort();
        int[] numbers;

        for (int i = 0; i < STANDARD_INDEXES.length; i++) {
            for (int j = 0; j < ARRAY_SIZES.length; j++) {
                int arrSize = ARRAY_SIZES[j] * K;
                numbers = new int[arrSize];

                // 10k, 100k, 200k, 400k, ...
                for (int k = 0; k < arrSize; k++) {
                    numbers[k] = (int)(Math.random() * RANDOM_STANDARD) + 1;   // 1 - 1000 사이의 난수 발생
                }

                long start = System.currentTimeMillis();
                dualPivotQuickSort.dualPivotQuickSort(numbers, 0, arrSize - 1, STANDARD_INDEXES[i]);
                long end = System.currentTimeMillis();

                System.out.println("n value : " + STANDARD_INDEXES[i] + ", array size : " + arrSize + " - " + ((end - start) / DIVIDE_NUMBER));

                //numbers.clear();
            }
        }


    }
}