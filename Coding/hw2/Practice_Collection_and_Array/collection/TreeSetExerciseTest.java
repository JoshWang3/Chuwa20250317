package com.chuwa.exercise.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author elena
 * @date 6/12/22 4:46 PM
 */
public class TreeSetExerciseTest {
    TreeSet<String> animals;
    @Before
    public void setUp() {
        animals = new TreeSet<>(Arrays.asList("dog", "dog", "cat"));
    }

    /**
     * e.g.
     * Set<Integer> set= new TreeSet<>();
     *
     * add(E e)
     * addAll(Collection<> c)
     *
     * contains(Object o)
     *
     * first()
     * last()
     * subSet(E fromElement, E toElement)
     * headSet(E toElement)
     * tailSet(E fromElement)
     *
     * remove(Object o)
     *
     * size()
     * isEmpty()
     *
     *
     */

    @Test
    public void learn_Inserting_And_Retrieving_Removing() {
        System.out.println(animals);
        animals.add("tiger");
        animals.addAll(Arrays.asList("dog", "dog", "cat", "tiger", "whale", "bird", "lion"));
        System.out.println(animals);
        System.out.println("Set contains dog: " + animals.contains("dog"));
        System.out.println("First element: " + animals.first());
        System.out.println("Last element: " + animals.last());

        System.out.println("HeadSet(<'lion'): " + animals.headSet("lion"));
        System.out.println("TailSet(>='lion'): " + animals.tailSet("lion"));
        System.out.println("SubSet('cat', 'tiger'): " +  animals.subSet("cat", "tiger"));

        animals.remove("tiger");
        System.out.println(animals);
        animals.removeAll(Arrays.asList("dog", "dog", "cat", "tiger"));
        System.out.println(animals);
        System.out.println("size: " + animals.size());
        System.out.println("Is empty: " + animals.isEmpty());
    }
}
