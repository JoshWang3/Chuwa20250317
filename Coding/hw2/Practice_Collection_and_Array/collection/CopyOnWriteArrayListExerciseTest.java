package com.chuwa.exercise.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author elena
 * @date 6/12/22 4:46 PM
 */
public class CopyOnWriteArrayListExerciseTest {
    CopyOnWriteArrayList<String> animals;

    @Before
    public void setUp() {
        animals = new CopyOnWriteArrayList<>(Arrays.asList("dog", "cat", "rabbit", "bird", "fish"));
    }
    /**
     * e.g.
     * List list = new CopyOnWriteArrayList();
     *
     * add(E e)
     * add(int index, E element)
     * addAll(Collection c)
     * addIfAbsent(E e)
     * addAllAbsent(Collection c)
     */
    @Test
    public void learn_Inserting_And_Retrieving() {
        System.out.println("list: " + animals);
        animals.add("whale");
        animals.add(1, "elephant");
        animals.addAll(2, Arrays.asList("tiger", "lion"));
        animals.addIfAbsent("dog");
        animals.addIfAbsent("cow");
        animals.add("dog");
        animals.addAllAbsent(Arrays.asList("dog", "cow"));
        animals.addAllAbsent(Arrays.asList("panda", "lion"));
        System.out.println("list: " + animals);
    }

    /**
     * iterator()
     * hasNext()
     * next()
     * remove()
     */

    @Test
    public void learn_Iterator() {
        //Created an iterator
        Iterator<String> iterator = animals.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("list: " + animals);
    }
}
