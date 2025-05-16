package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.*;
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
        // 创建 TreeSet（自动按升序排序）
        TreeSet<Integer> set = new TreeSet<>();

        // add(E e)
        assertTrue(set.add(10));
        assertTrue(set.add(5));
        assertFalse(set.add(10)); // 不添加重复元素

        // addAll(Collection<> c)
        set.addAll(Arrays.asList(20, 15, 25, 30));
        assertEquals(6, set.size()); // [5, 10, 15, 20, 25, 30]

        // contains(Object o)
        assertTrue(set.contains(15));
        assertFalse(set.contains(100));

        // first() & last()
        assertEquals(Integer.valueOf(5), set.first());
        assertEquals(Integer.valueOf(30), set.last());

        // subSet(fromElement, toElement) - 左闭右开
        SortedSet<Integer> midSet = set.subSet(10, 25); // [10, 15, 20]
        assertEquals(Arrays.asList(10, 15, 20), midSet.stream().toList());

        // headSet(toElement) - 小于该值
        SortedSet<Integer> head = set.headSet(15); // [5, 10]
        assertEquals(Arrays.asList(5, 10), head.stream().toList());

        // tailSet(fromElement) - 大于等于该值
        SortedSet<Integer> tail = set.tailSet(20); // [20, 25, 30]
        assertEquals(Arrays.asList(20, 25, 30), tail.stream().toList());

        // remove(Object o)
        assertTrue(set.remove(10));
        assertFalse(set.remove(100));
        assertFalse(set.contains(10));

        // isEmpty() & size()
        assertFalse(set.isEmpty());
        assertEquals(5, set.size());
    }
}
