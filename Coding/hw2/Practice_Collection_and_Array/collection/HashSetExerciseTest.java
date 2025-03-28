package com.chuwa.exercise.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author elena
 * @date 6/12/22 4:46 PM
 */
public class HashSetExerciseTest {
    Set<String> animals;
    @Before
    public void setUp() throws Exception {
        animals = new HashSet<>(Arrays.asList("dog", "cat", "dog"));
    }
    /**
     * e.g.
     * Set<Integer> set= new HashSet<>();
     *
     * add(E e)
     * addAll(Collection<> c)
     *
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
        System.out.println(animals);
        animals.add("dog");
        animals.add("lion");
        animals.addAll(Arrays.asList("tiger", "lion"));
        System.out.println(animals);
        System.out.println("contains tiger: " + animals.contains("tiger"));
        animals.remove("tiger");
        animals.clear();
        System.out.println("set is empty: " + animals.isEmpty());
    }
}
