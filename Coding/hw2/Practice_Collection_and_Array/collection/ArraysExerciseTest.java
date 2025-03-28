package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author elena
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
        int[] numbers = {1, 2, 3, 4, 5};

        // Retrieve values
        System.out.println("Element at index 2: " + numbers[2]); // 3

        // Insert (overwrite)
        numbers[2] = 99;
        System.out.println("Updated element at index 2: " + numbers[2]); // 99

        // Loop through array
        System.out.print("Array elements: ");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
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
        int[] numbers = {5, 1, 9, 3, 7};

        // Sort using Arrays.sort()
        Arrays.sort(numbers);
        System.out.println("Sorted array: " + Arrays.toString(numbers)); // [1, 3, 5, 7, 9]

        // Binary search
        int index = Arrays.binarySearch(numbers, 5);
        System.out.println("Index of 5: " + index); // Should be 2

        // Partial sort
        int[] partial = {10, 4, 2, 8, 6};
        Arrays.sort(partial, 1, 4); // only sort elements at index 1 to 3
        System.out.println("Partial sorted array: " + Arrays.toString(partial)); // e.g. [10, 2, 4, 8, 6]

        // Parallel sort (for large arrays, uses multithreading)
        int[] bigArray = {100, 20, 30, 10};
        Arrays.parallelSort(bigArray);
        System.out.println("Parallel sorted array: " + Arrays.toString(bigArray));

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
        int[] numbers = {1, 2, 3, 4, 5};

        // Copy full array
        int[] copy = Arrays.copyOf(numbers, numbers.length);
        System.out.println("Copied array: " + Arrays.toString(copy));

        // Copy range
        int[] firstThree = Arrays.copyOfRange(numbers, 0, 3); // index 0 to 2
        System.out.println("First 3 elements: " + Arrays.toString(firstThree));
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
        Integer[] numbers = {1, 2, 3, 4, 5};

        // Convert array to list
        List<Integer> list = Arrays.asList(numbers);
        System.out.println("Array as list: " + list);

        // equals()
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        int[] c = {3, 2, 1};
        System.out.println("a equals b? " + Arrays.equals(a, b)); // true
        System.out.println("a equals c? " + Arrays.equals(a, c)); // false

        // fill()
        int[] filled = new int[5];
        Arrays.fill(filled, 42);
        System.out.println("Filled array: " + Arrays.toString(filled)); // [42, 42, 42, 42, 42]
    }
}
