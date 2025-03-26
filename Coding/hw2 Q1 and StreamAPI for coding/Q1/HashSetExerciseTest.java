package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author b1go
 * @date 6/12/22 4:46 PM
 */
public class HashSetExerciseTest {
    /**
     * e.g.
     * Set<Integer> set= new HashSet<>();
     *
     * add(E e)
     * addAll(Collection<> c)
     *
     * get()
     * contains()
     *
     * remove(Object o)
     * clear()
     *
     * isEmpty()
     *
     *
     */

    @Test
    public void learn_Inserting_And_Retrieving_Removing() {
        HashSet<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Peach");
        set.add("Strawberry");
        System.out.println(set.add("Apple"));
        set.addAll(Arrays.asList("Blueberry, Apple, Grapes"));
        System.out.println(set);
        set.contains("Peach");
        set.remove("Peach");
        set.clear();
        System.out.println(set.isEmpty());
    }
}
