package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Arrays;
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
        TreeSet<Integer> set= new TreeSet<>();

        set.add(1);
        set.addAll(Arrays.asList(5, 4, 3, 2));
        System.out.println(set);                // [1, 2, 3, 4, 5]

        System.out.println(set.contains(6));    // false

        System.out.println(set.first());        // 1
        System.out.println(set.last());         // 5

        System.out.println(set.subSet(2, 4));   // [2, 3]
        System.out.println(set.headSet(3));     // [1, 2]
        System.out.println(set.tailSet(3));     // [3, 4, 5]

        set.remove(3);

        System.out.println(set.size());         // 4
        System.out.println(set.isEmpty());      // false
    }
    }
}
