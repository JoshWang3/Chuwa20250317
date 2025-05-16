package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @author b1go
 * @date 6/12/22 4:43 PM
 */
public class ArrayListExerciseTest {
    /**
     * new ArrayList()
     * add elements
     * get element
     * get Size
     * list.addAll(anotherList)
     */
    @Test
    public void learn_Inserting_And_Retrieving() {
        ArrayList list = new ArrayList();
        list.add(1);
        assertEquals((Integer)1, list.get(0));
        assertEquals(1, list.size());
        ArrayList list2 = new ArrayList();
        list2.add(2);
        list.addAll(list2);
        assertEquals(2, list.size());
    }

    /**
     * remove(int index)
     * remove(Object o)
     * removeRange(int fromIndex, int toIndex)
     * removeAll(Collection<?> c)
     * clear()
     *
     * Update:
     * set(int index, E e)
     * replaceAll(UnaryOperator<E> operator)
     *
     * check:
     * contains(Object o)
     * indexOf(Object o)
     * lastIndexOf(Object o)
     */
    @Test
    public void learn_Remove_Replacing_Updating() {
        ArrayList<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.add("banana");
        list.add("pineapple");
        list.add("straberry");
        // remove all(int fromIndex, int toIndex) - protected method in ArrayList

        // remove(int index)
        list.remove(2); // remove "cherry"
        assertFalse(list.contains("cherry"));

        // remove(Object o)（移除第一次出现的 banana）
        list.remove("banana");
        assertEquals(1, list.indexOf("banana")); // 剩下一个 banana

        // set(index, newValue)
        list.set(0, "apricot");
        assertEquals("apricot", list.get(0));

        // replaceAll
        list.replaceAll(s -> s.toUpperCase());
        assertEquals("APRICOT", list.get(0));

        // contains, indexOf, lastIndexOf
        assertTrue(list.contains("BANANA"));
        assertEquals(1, list.indexOf("BANANA"));
        assertEquals(1, list.lastIndexOf("BANANA"));

        // removeAll
        ArrayList<String> toRemove = new ArrayList<>();
        toRemove.add("BANANA");
        list.removeAll(toRemove);
        assertFalse(list.contains("BANANA"));

        // clear
        list.clear();
        assertTrue(list.isEmpty());

    }

    /**
     * iterator()
     * hasNext()
     * next()
     * remove()
     * forEachRemaining(Consumer<? super E> action) -- from Java8
     */

    @Test
    public void learn_Iterator() {
        ArrayList<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");

        Iterator<String> iterator = list.iterator();

        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if ("two".equals(element)) {
                iterator.remove(); // remove "two"
            } else {
                sb.append(element).append(" ");
            }
        }

        assertEquals("one three ", sb.toString());
        assertEquals(Arrays.asList("one", "three"), list);

        // 使用 Java 8 的 forEachRemaining
        list.clear();
        list.add("four");
        list.add("five");

        Iterator<String> it = list.iterator();
        StringBuilder sb2 = new StringBuilder();
        it.forEachRemaining(sb2::append);
        assertEquals("fourfive", sb2.toString());
    }


    /**
     * sort(List<T> list)
     * Collections.sort(List<T> t)
     * Comparator.reverseOrder()
     */

    @Test
    public void learn_Sorting() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(1);
        list.add(4);
        list.add(2);

        // 升序排序
        Collections.sort(list);
        assertEquals(Arrays.asList(1, 2, 3, 4), list);

        // 降序排序
        list.sort(Comparator.reverseOrder());
        assertEquals(Arrays.asList(4, 3, 2, 1), list);
    }
}
