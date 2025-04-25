package com.chuwa.exercise.collection;

import org.junit.Test;

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
        List<Integer> list = new LinkedList<Integer>();
        list.add(Integer.valueOf(1));
        list.addFirst(Integer.valueOf(2));
        list.add(1, 3);
        list.addAll(1, Arrays.asList(4, 5, 6));
        System.out.println(list);

        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list.get(2));
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
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 4, 4, 4, 5, 6, 4, 6, 7));
        list.remove(1);
        list.removeFirst();
        list.removeLast();
        list.remove(Integer.valueOf(4));
        list.removeLastOccurrence(Integer.valueOf(4));
        list.sort((p1, p2) -> p1 - p2);
        System.out.println(list);
    }
}
