package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

/**
 * @author b1go
 * @date 6/12/22 4:46 PM
 */
public class CopyOnWriteArrayListExerciseTest {

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
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        // add(E e)
        list.add("A");
        list.add("B");

        // add(int index, E element)
        list.add(1, "C");
        assertEquals("C", list.get(1));

        // addAll(Collection c)
        list.addAll(Arrays.asList("D", "E"));
        assertTrue(list.contains("D"));

        // addIfAbsent(E e)
        boolean added = list.addIfAbsent("E"); // "E" already exists
        assertFalse(added);

        added = list.addIfAbsent("F"); // "F" is absent
        assertTrue(added);
        assertTrue(list.contains("F"));

        // addAllAbsent(Collection c)
        List<String> newItems = Arrays.asList("B", "G", "H");
        int addedCount = list.addAllAbsent(newItems); // only G, H will be added
        assertEquals(2, addedCount);
        assertTrue(list.containsAll(Arrays.asList("G", "H")));
    }

    /**
     * iterator()
     * hasNext()
     * next()
     * remove()
     */

    @Test
    public void learn_Iterator() {
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Orange");

        // 创建迭代器
        Iterator<String> itr = list.iterator();

        StringBuilder sb = new StringBuilder();
        while (itr.hasNext()) {
            String element = itr.next();
            sb.append(element).append(" ");
        }

        assertEquals("Apple Banana Orange ", sb.toString());

        // CopyOnWriteArrayList 的迭代器不支持 remove 操作
        Iterator<String> itr2 = list.iterator();
        try {
            itr2.remove(); // 会抛出异常
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // expected
        }
    }
}
