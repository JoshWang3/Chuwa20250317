package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import static org.junit.Assert.*;
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
        LinkedList<Integer> list = new LinkedList<>();

        // add(E e) -> 默认添加到末尾
        list.add(10);
        list.add(20);

        // addFirst(E e)
        list.addFirst(5);  // list: [5, 10, 20]

        // addLast(E e)
        list.addLast(25);  // list: [5, 10, 20, 25]

        // add(index, element)
        list.add(2, 15);   // list: [5, 10, 15, 20, 25]

        // addAll(Collection)
        list.addAll(Arrays.asList(30, 35));  // list: [5, 10, 15, 20, 25, 30, 35]

        // addAll(index, Collection)
        list.addAll(1, Arrays.asList(6, 7)); // list: [5, 6, 7, 10, 15, 20, 25, 30, 35]

        // Retrieving
        assertEquals(Integer.valueOf(5), list.getFirst());
        assertEquals(Integer.valueOf(35), list.getLast());
        assertEquals(Integer.valueOf(10), list.get(3));
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
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(10, 20, 30, 10, 40, 20));

        // removeFirst()
        Integer first = list.removeFirst();  // 10
        assertEquals(Integer.valueOf(10), first);

        // removeLast()
        Integer last = list.removeLast();    // 20
        assertEquals(Integer.valueOf(20), last);

        // remove(index)
        Integer removed = list.remove(1);    // remove element at index 1 (30)
        assertEquals(Integer.valueOf(30), removed);

        // remove(Object o)
        boolean removedVal = list.remove(Integer.valueOf(10)); // remove first occurrence
        assertTrue(removedVal);

        // removeLastOccurrence()
        list.add(20); // list: [20, 40, 20]
        boolean lastOccRemoved = list.removeLastOccurrence(20); // remove last 20
        assertTrue(lastOccRemoved);
        assertEquals(Arrays.asList(20, 40), list);

        // sort()
        list.addAll(Arrays.asList(5, 25, 15)); // list: [20, 40, 5, 25, 15]
        Collections.sort(list);               // list: [5, 15, 20, 25, 40]
        assertEquals(Arrays.asList(5, 15, 20, 25, 40), list);
    }
}
