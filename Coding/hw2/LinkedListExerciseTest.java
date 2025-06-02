package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author b1go
 * @date 6/12/22 4:45 PM
 */
public class LinkedListExerciseTest {

    /**
     * e.g.
     * List<Integer> list = new LinkedList<Integer>();
     * Inserting:
     * add(E e) or addLast(E e)
     * addFirst(E e)
     * add(int index, E element)
     * addAll(Collection c)
     * addAll(int index, Collection c)
     *
     * Retrieving:
     * getFirst()
     * getLast()
     * get(int index)
     *
     */
    @Test
    public void learn_Inserting_And_Retrieving() {
        List<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(0, 3);
        System.out.println(list);           // [3, 1, 2]

        list.addAll(1, Arrays.asList(4, 5, 6));
        System.out.println(list);           // [3, 4, 5, 6, 1, 2]

        System.out.println(list.get(2));    // 5
    }

    /**
     * removeFirst()
     * removeLast()
     * remove(int index)
     * remove(Object o)
     * removeLastOccurrence()
     *
     * sort()
     */

    @Test
    public void learn_Remove_Sort() {
        List<Integer> list = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));

        list.remove(2);
        System.out.println(list);   // [1, 2, 4, 5]

        list.remove(new Integer(2));
        System.out.println(list);   // [1, 4, 5]
    }
}
