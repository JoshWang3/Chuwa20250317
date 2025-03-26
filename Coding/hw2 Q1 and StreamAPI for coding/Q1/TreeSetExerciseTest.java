package com.chuwa.exercise.collection;

import org.junit.Test;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author b1go
 * @date 6/12/22 4:46 PM
 */
public class TreeSetExerciseTest {
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
        TreeSet<String> tree = new TreeSet<>();
        tree.add("Apple");
        tree.add("Peach");
        tree.add("Strawberry");
        tree.addAll(Arrays.asList("Blueberry, Apple, Grapes"));
        System.out.println(tree.contains("Apple"));
        System.out.println(tree.contains("Melon"));
        System.out.println(tree);
        System.out.println(tree.first());
        System.out.println(tree.last());
        System.out.println(tree.subSet("Blueberry", "Strawberry"));
        System.out.println(tree.headSet("Blueberry"));
        System.out.println(tree.tailSet("Blueberry"));

        tree.remove("Blueberry");
        System.out.println(tree.size());
        tree.clear();
        System.out.println(tree.isEmpty());
    }
}
