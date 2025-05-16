package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

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
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Retrieve
        int valueAtIndex3 = numbers[3];
        assertEquals(4, valueAtIndex3);

        // Insert (overwrite)
        numbers[3] = 100;
        assertEquals(100, numbers[3]);
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
        int[] numbers = {6, 3, 9, 1, 5, 2};

        // sort entire array
        Arrays.sort(numbers);
        assertArrayEquals(new int[]{1, 2, 3, 5, 6, 9}, numbers);

        // binarySearch (must search in sorted array)
        int index = Arrays.binarySearch(numbers, 5);
        assertEquals(3, index);

        // parallelSort (multi-threaded sort)
        int[] otherNumbers = {8, 4, 7, 2, 6};
        Arrays.parallelSort(otherNumbers);
        assertArrayEquals(new int[]{2, 4, 6, 7, 8}, otherNumbers);

        // partial sort
        int[] partial = {9, 5, 3, 7, 1};
        Arrays.sort(partial, 1, 4);  // only sort index 1 to 3
        assertArrayEquals(new int[]{9, 3, 5, 7, 1}, partial);
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

        // copyOf
        int[] copy = Arrays.copyOf(numbers, numbers.length);
        assertArrayEquals(numbers, copy);

        // copyOfRange
        int[] firstThree = Arrays.copyOfRange(numbers, 0, 3);
        assertArrayEquals(new int[]{1, 2, 3}, firstThree);
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
        int[] numbers = {1, 2, 3, 4, 5};

        // asList
        Integer[] boxed = {1, 2, 3, 4, 5};
        List<Integer> list = Arrays.asList(boxed);
        assertEquals(5, list.size());
        assertTrue(list.contains(3));

        // equals
        int[] numbers2 = {1, 2, 3, 4, 5};
        assertTrue(Arrays.equals(numbers, numbers2));

        // fill
        Arrays.fill(numbers, 20);
        assertArrayEquals(new int[]{20, 20, 20, 20, 20}, numbers);
    }
}
