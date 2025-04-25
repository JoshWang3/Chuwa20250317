package com.chuwa.exercise.collection;

import org.junit.Test;

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
        TreeSet<Integer> tree = new TreeSet<>();
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.addAll(Arrays.asList(1,2,4));
        System.out.println(tree.contains(1));
        System.out.println(tree.contains(5));
        System.out.println(tree);
        System.out.println(tree.first());
        System.out.println(tree.last());
        System.out.println(tree.subSet(2,3));
        System.out.println(tree.headSet(3));
        System.out.println(tree.tailSet(3));

        tree.remove(3);
        System.out.println(tree.size());
        tree.clear();
        System.out.println(tree.isEmpty());

    }
}
