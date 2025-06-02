package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.*;

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
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(10);
        list.add(100);

        System.out.println(list.get(1));    // 10

        list.addAll(Arrays.asList(2, 20, 200));
        System.out.println(list);           // [1, 10, 100, 2, 20, 200]
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
        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 10, 100, 1000, 10000, 100000, 1000000));

        list.remove(new Integer(1));
        System.out.println(list);       // [10, 100, 1000, 10000, 100000, 1000000]

        list.remove(1);
        System.out.println(list);       // [10, 1000, 10000, 100000, 1000000]

        list.removeAll(Arrays.asList(1000, 100000));
        System.out.println(list);       // [10, 10000, 1000000]

        list.clear();
        System.out.println(list);       // []

        list.add(0);
        list.add(0);
        list.set(0, 1);
        list.set(1, 10);
        System.out.println(list);       // [1, 10]

        list.replaceAll(x -> x * 2);
        System.out.println(list);       // [2, 20]

        System.out.println(list.contains(2));   // true

        list.add(20);
        System.out.println(list.indexOf(20));       // 1
        System.out.println(list.lastIndexOf(20));   // 2
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
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }

        iterator = list.iterator();
        iterator.forEachRemaining(System.out::print);   // 135
    }

    /**
     * sort(List<T> list)
     * Collections.sort(List<T> t)
     * Comparator.reverseOrder()
     */

    @Test
    public void learn_Sorting() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        list.sort((o1, o2) -> o2 - o1);
        System.out.println(list);   // [5, 4, 3, 2, 1]

        Collections.sort(list);
        System.out.println(list);   // [1, 2, 3, 4, 5]

        list.sort(Comparator.reverseOrder());
        System.out.println(list);   // [5, 4, 3, 2, 1]
    }
}
