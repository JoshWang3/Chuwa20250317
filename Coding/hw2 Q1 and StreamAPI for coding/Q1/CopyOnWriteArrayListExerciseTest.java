package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1, 1);
        list.addAll(Arrays.asList(4, 5, 6));
        System.out.println(list.addIfAbsent(4));
        System.out.println(list.addIfAbsent(7));
        list.addAllAbsent(Arrays.asList(2, 5, 9, 12, 17));
        System.out.println(list);
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

        //Created an iterator
        Iterator<String> itr = list.iterator();
        while(itr.hasNext()) {
            String str = itr.next();
            System.out.println(str);
//            if(str.equals("Apple")) itr.remove(); //throws UnsupportedOperationException
        }
        System.out.println(list);
    }
}
