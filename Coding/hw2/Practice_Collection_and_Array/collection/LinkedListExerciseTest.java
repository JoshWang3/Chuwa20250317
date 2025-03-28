package com.chuwa.exercise.collection;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * @author elena
 * @date 6/12/22 4:45 PM
 */
public class LinkedListExerciseTest {
    LinkedList<String> animals;

    @Before
    public void setUp() {
        animals = new LinkedList<>();
        animals.addAll(Arrays.asList("dog", "cat", "rabbit", "bird", "fish"));
    }

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
        System.out.println("list: " + animals);
        // Insert
        animals.add("deer");
        animals.addFirst("tiger");
        animals.addLast("lion");
        animals.add(1, "fox");
        List<String> animalsToAdd = new LinkedList<>(Arrays.asList("wolf", "zebra"));
        animals.addAll(animalsToAdd);
        animals.addAll(1, animalsToAdd);
        System.out.println("list: " + animals);
        // Retrieve
        System.out.println("First element: " + animals.getFirst());
        System.out.println("Last element: " + animals.getLast());
        System.out.println("Second element: " + animals.get(1));
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
        System.out.println("list: " + animals);
        // Remove
        animals.removeFirst();
        animals.removeLast();
        animals.remove(1);
        animals.remove("cat");
        animals.addAll(Arrays.asList("tiger", "lion", "tiger"));
        animals.removeLastOccurrence("tiger");
        System.out.println("list: " + animals);
    }
}
