package com.chuwa.exercise.collection;

import com.sun.source.tree.BinaryTree;
import org.junit.Test;

import java.util.*;

/**
 * @author b1go
 * @date 6/12/22 4:43 PM
 */
public class ArrayListExerciseTest extends ArrayList<Integer> {
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
        list.add(2);
        list.add(3);
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        List<Integer> list2 = new ArrayList<>();
        list2.add(4);
        list2.add(5);
        list2.add(6);
        list.addAll(list2);

        System.out.println(list.size());
        System.out.println(list);
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
        ArrayListExerciseTest list = new ArrayListExerciseTest();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.remove(1);
        list.remove(Integer.valueOf(2));
        list.removeRange(0, 2);
        System.out.println(list);
        List<Integer> l = new ArrayList<>(List.of(5, 6, 7));
        list.removeAll(l);

        System.out.println(list);
        list.clear();
        System.out.println(list.size());

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.set(0, 2);
        list.set(1, 2);
        list.set(3, 2);
        list.set(4, 2);
        list.replaceAll(n -> n * 2);
        System.out.println(list);

        System.out.println(list.contains(Integer.valueOf(8)));
        System.out.println(list.contains(Integer.valueOf(12)));
        System.out.println(list.indexOf(Integer.valueOf(8)));
        System.out.println(list.indexOf(Integer.valueOf(12)));
        System.out.println(list.lastIndexOf(Integer.valueOf(4)));
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
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Iterator<Integer> iterator = list.iterator();
        List<Integer> temp = new ArrayList<>();
        iterator.forEachRemaining(x -> temp.add(x + 2));
        Iterator<Integer> iterator1 = temp.iterator();
        while(iterator1.hasNext()) {
            int i = iterator1.next();
            if(i % 2 == 0) iterator1.remove();
        }
        System.out.println(temp);
        System.out.println(list);
        System.out.println();
    }

    /**
     * sort(List<T> list)
     * Collections.sort(List<T> t)
     * Comparator.reverseOrder()
     */

    @Test
    public void learn_Sorting() {
        List<Integer> list = Arrays.asList(4, 5, 9, 1, 0, 3, 2, 7);
        list.sort((p1, p2) -> p1 - p2);
        System.out.println(list);
        List<Integer> list1 = Arrays.asList(4, 5, 9, 1, 0, 3, 2, 7);
        Collections.sort(list1);
        System.out.println(list1);
        list.sort(Comparator.reverseOrder());
        System.out.println(list);
    }
}
