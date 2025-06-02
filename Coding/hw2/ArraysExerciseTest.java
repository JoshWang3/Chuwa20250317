package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author b1go
 * @date 6/12/22 4:48 PM
 */
public class ArraysExerciseTest {

    /**
     * e.g.
     * int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
     * numbers[?]
     *
     * numbers[?] = #
     */

    @Test
    public void learn_Inserting_And_Retrieving() {
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        System.out.println(numbers[0]); // 1

        numbers[0] = 100;
        System.out.println(Arrays.toString(numbers));   // [100, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    }

    /**
     * binarySearch()
     * e.g.
     * Arrays.binarySearch(numbers, 4);
     *
     * sort(array)
     * sort(array, fromIndex, toIndex)
     * e.g.
     * Arrays.sort(numbers);
     *
     * Arrays.parallelSort(numbers);
     */
    @Test
    public void learn_search_and_sort() {
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        System.out.println(Arrays.binarySearch(numbers, 4));    // 3

        numbers[0] = 100;
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers));   // [2, 3, 4, 5, 6, 7, 8, 9, 10, 100]

        numbers[0] = 1000;
        Arrays.sort(numbers, 5, 10);
        System.out.println(Arrays.toString(numbers));   // [1000, 3, 4, 5, 6, 7, 8, 9, 10, 100]
    }

    /**
     * copyOf()
     * e.g.
     * Arrays.copyOf(numbers, numbers.length);
     *
     * copyOfRange()
     * e.g.
     * Arrays.copyOfRange(numbers, 0, 5);
     */
    @Test
    public void learn_copy_of_array() {
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        int[] copy1 = Arrays.copyOf(numbers, numbers.length);
        System.out.println(Arrays.toString(copy1)); // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        int[] copy2 = Arrays.copyOfRange(numbers, 0, 5);
        System.out.println(Arrays.toString(copy2)); // [1, 2, 3, 4, 5]
    }

    /**
     * asList()
     * e.g.
     * List<Integer> list = Arrays.asList(numbers);
     *
     * equals()
     * e.g.
     * Arrays.equals(numbers1, numbers2);
     *
     * fill()
     * e.g.
     * Arrays.fill(numbers, 20);
     *
     */

    @Test
    public void learn_common_operations() {
        Integer[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        List<Integer> list = Arrays.asList(numbers);
        System.out.println(list);   // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        int[] numbers1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int[] numbers2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        System.out.println(Arrays.equals(numbers1, numbers2));  // true

        int[] arr = new int[10];
        Arrays.fill(arr, 10);
        System.out.println(Arrays.toString(arr));   // [10, 10, 10, 10, 10, 10, 10, 10, 10, 10]
    }
}
