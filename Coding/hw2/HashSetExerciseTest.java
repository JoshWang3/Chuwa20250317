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
        Set<String> set= new HashSet<>();

        set.add("A");
        set.add("B");
        set.addAll(Arrays.asList("A", "B", "C", "D"));
        System.out.println(set);                // [A, B, C, D]

        System.out.println(set.contains("E"));  // false

        set.remove("A");
        System.out.println(set);                // [B, C, D]

        set.clear();

        System.out.println(set.isEmpty());      // true
    }
}
