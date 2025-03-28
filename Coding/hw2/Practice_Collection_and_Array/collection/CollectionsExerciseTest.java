package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.*;

/**
 * @author elena
 * @date 6/12/22 4:48 PM
 */
public class CollectionsExerciseTest {

    /**
     * Collections.min(list))
     * min(Collection c, Comparator comp)
     *
     * Collections.max(list)
     * max(Collection c, Comparator comp)
     *
     * frequency(Collection c, object o)
     */

    @Test
    public void learn_common_collections_operations() {
        List<String> animals = Arrays.asList("dog", "cat", "dog", "lion", "tiger");

        // min and max (natural ordering)
        String minAnimal = Collections.min(animals); // alphabetically first
        String maxAnimal = Collections.max(animals); // alphabetically last
        System.out.println("Min: " + minAnimal); // cat
        System.out.println("Max: " + maxAnimal); // tiger

        // min and max with Comparator (by length)
        Comparator<String> byLength = Comparator.comparingInt(String::length);
        System.out.println("Shortest animal: " + Collections.min(animals, byLength));
        System.out.println("Longest animal: " + Collections.max(animals, byLength));

        // frequency
        int count = Collections.frequency(animals, "dog");
        System.out.println("Frequency of 'dog': " + count);
    }

    /**
     * synchronizedList()
     */

    @Test
    public void learn_thread_safe_ArrayList() {
        List<String> unsafeList = new ArrayList<>();
        unsafeList.add("one");
        unsafeList.add("two");

        // Wrap it to make it thread-safe
        List<String> safeList = Collections.synchronizedList(unsafeList);

        // Safe usage in multithreaded context would require synchronized block
        synchronized (safeList) {
            for (String item : safeList) {
                System.out.println(item);
            }
        }
    }
}
