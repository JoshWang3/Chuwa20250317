package com.chuwa.exercise.collection;

import org.junit.Test;

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
        System.out.println(numbers[1]);
        numbers[0] = 99;
        System.out.println(numbers[0]);
        for (int number : numbers) {
            System.out.print(number);;
        }
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
        int[] numbers = {5, 1, 2, 9, 3, 7, 8};
        System.out.println(Arrays.binarySearch(numbers, 9));
        Arrays.sort(numbers);
        System.out.println(numbers);
        int[] numbers1 = {5, 1, 2, 9, 3, 7, 8};
        Arrays.sort(numbers1, 2, 4);
        System.out.println(numbers1);
        int[] numbers2 = {5, 1, 2, 9, 3, 7, 8};
        Arrays.parallelSort(numbers2);
        System.out.println(numbers2);
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
        int[] copy = Arrays.copyOf(numbers, numbers.length);
        System.out.println(Arrays.toString(copy));
        int[] firstThree = Arrays.copyOfRange(numbers, 0, 3);
        System.out.println(Arrays.toString(firstThree));
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
        List<Integer> list = Arrays.asList(numbers);
        System.out.println(list);
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        int[] c = {1, 2, 4};
        System.out.println("a equals b? " + Arrays.equals(a, b));
        System.out.println("a equals c? " + Arrays.equals(a, c));
        int[] filled = new int[5];
        Arrays.fill(filled, 20);
        System.out.println(Arrays.toString(filled));

    }
}
