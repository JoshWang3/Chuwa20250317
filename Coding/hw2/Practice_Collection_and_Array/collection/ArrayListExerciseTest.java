package com.chuwa.exercise.collection;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author elena
 * @date 6/12/22 4:43 PM
 */
public class ArrayListExerciseTest {
    List<String> animals;

    @Before
    public void setup() {
        animals = new ArrayList<>();
        animals.addAll(Arrays.asList("dog", "cat", "rabbit", "bird", "fish", "whale"));
    }
    /**
     * new ArrayList()
     * add elements
     * get element
     * get Size
     * list.addAll(anotherList)
     */
    @Test
    public void learn_Inserting_And_Retrieving() {
        // add
        animals.add("pig");
        // get
        System.out.println("first element: " + animals.get(0));
        // size
        System.out.println("size: " + animals.size());
        // addAll
        List<String> animalsToAdd = Arrays.asList("lion", "tiger");
        animals.addAll(animalsToAdd);
        System.out.println(animals);
    }

    /**
     * remove(int index)
     * remove(Object o)
     * removeRange(int fromIndex, int toIndex) (not a public method, cannot call on arraylist instance)
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
        // check
        System.out.println("list: " + animals + " contains dog: " + animals.contains("dog"));
        System.out.println("fish index: " + animals.indexOf("fish"));
        animals.add("fish");
        animals.add("fish");
        System.out.println("last index of fish in list: " + animals + " is: " + animals.lastIndexOf("fish"));
        // set
        animals.set(3, "deer");
        // replaceAll
        animals.replaceAll(animal -> animal.toUpperCase());
        // Remove by idx
        animals.remove(0);
        // Remove by object
        animals.remove("cat");
        // Remove All
        List<String> toRemove = Arrays.asList("FISH", "WHALE");
        animals.removeAll(toRemove);
        System.out.println("list after remove all: " + animals);
        // Clear
        animals.clear();
        System.out.println("list after clear: " + animals);
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
        System.out.println("list: " + animals);
        Iterator<String> iterator = animals.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        iterator = animals.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("rabbit")) {
                iterator.remove();
            }
        }
        System.out.println("contains rabbit: " + animals.contains("rabbit"));

        List<String> emptyAnimal = new ArrayList<>();
        iterator = animals.iterator();
        iterator.forEachRemaining(animal -> emptyAnimal.add(animal.toUpperCase()));
        System.out.println("list: " + emptyAnimal);
    }

    /**
     * sort(List<T> list)
     * Collections.sort(List<T> t)
     * Comparator.reverseOrder()
     */

    @Test
    public void learn_Sorting() {
        animals.sort(Comparator.naturalOrder());
        System.out.println("list: " + animals);
        Collections.sort(animals, Comparator.reverseOrder());
        System.out.println("list: " + animals);
    }
}
