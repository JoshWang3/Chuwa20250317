package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
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
        Set<Integer> set = new HashSet<>();

        // add(E e)
        assertTrue(set.add(1));
        assertTrue(set.add(2));
        assertFalse(set.add(1)); // 不重复插入

        // addAll(Collection c)
        set.addAll(Arrays.asList(3, 4, 5));
        assertTrue(set.contains(3));
        assertTrue(set.contains(5));

        // contains(Object o)
        assertTrue(set.contains(1));
        assertFalse(set.contains(10));

        // remove(Object o)
        assertTrue(set.remove(2));
        assertFalse(set.remove(100));

        // clear()
        set.clear();
        assertTrue(set.isEmpty());
    }
}
