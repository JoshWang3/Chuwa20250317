package com.chuwa.exercise.collection;

import org.junit.Test;

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
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        System.out.println(l.size());
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
        List<Integer> l1 = new ArrayList<>();
        l1.add(3);
        l1.add(4);
        l.addAll(l1);
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
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
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            l.add(i);
        }
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
        l.remove(0);
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
        l.remove(Integer.valueOf(2));
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
        l.removeRange(3,4);
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
        List<Integer> l1 = new ArrayList<>();
        l1.add(8);
        l1.add(9);
        l.removeAll(l1);
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
        l.clear();
        System.out.println(l.size());
        for (int i = 0; i < 10; i++) {
            l.add(i);
        }
        l.set(0, 1);
        l.set(2, 1);
        l.replaceAll(n -> n * 2);
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
        System.out.println(list.contains(Integer.valueOf(5)));
        System.out.println(list.contains(Integer.valueOf(10)));
        System.out.println(list.indexOf(Integer.valueOf(5)));
        System.out.println(list.indexOf(Integer.valueOf(10)));
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
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            l.add(i);
        }
        Iterator<Integer> iterator = l.iterator();
        List<Integer> tem = new ArrayList<>();
        iterator.forEachRemaining(x -> tem.add(x + 1));
        Iterator<Integer> iterator1 = tem.iterator();
        while(iterator1.hasNext()) {
            int i = iterator1.next();
            if(i % 2 == 0) iterator1.remove();
        }
        System.out.println(tem);
        System.out.println(l);
    }

    /**
     * sort(List<T> list)
     * Collections.sort(List<T> t)
     * Comparator.reverseOrder()
     */

    @Test
    public void learn_Sorting() {
        List<Integer> l = Arrays.asList(2,7,6,3,9,1,0);
        l.sort();
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
        l = Arrays.asList(2,7,6,3,9,1,0);
        Collections.sort(l);
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
        l.sort(Comparator.reverseOrder());
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
    }
}
