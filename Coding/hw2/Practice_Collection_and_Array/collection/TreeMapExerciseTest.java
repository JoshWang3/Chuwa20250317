package com.chuwa.exercise.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author elena
 * @date 6/12/22 4:47 PM
 */
public class TreeMapExerciseTest {
    TreeMap<String, Integer> map;

    @Before
    public void setUp() {
        // Basic put and putIfAbsent
        map = new TreeMap<>();
        map.put("banana", 3);
        map.put("apple", 5);
        map.putIfAbsent("cherry", 7);
        map.putIfAbsent("apple", 10); // Won't overwrite
    }

    /**
     * e.g.
     * TreeMap<String, Integer> map = new TreeMap<>();
     *
     * put(K key, V value)
     * putIfAbsent(K key, V value)
     * putAll(Map<? extends K, ? extends V> m)
     *
     * get(Object key)
     * firstKey()
     * lastKey()
     *
     * containsKey(Object key)
     * containsValue(Object value)
     *
     * keySet()
     * values()
     * isEmpty()
     */

    @Test
    public void learn_Inserting_And_Retrieving() {
        System.out.println("map: " + map);
        // putAll
        Map<String, Integer> extra = new TreeMap<>();
        extra.put("date", 2);
        extra.put("fig", 6);
        map.putAll(extra);

        // Get and special access
        System.out.println("Value of 'banana': " + map.get("banana"));
        System.out.println("First key: " + map.firstKey()); // Smallest key
        System.out.println("Last key: " + map.lastKey());   // Largest key

        // Contains
        System.out.println("Contains 'apple'? " + map.containsKey("apple"));
        System.out.println("Contains value 7? " + map.containsValue(7));

        // View
        System.out.println("Keys (sorted): " + map.keySet());
        System.out.println("Values: " + map.values());
        System.out.println("Is empty? " + map.isEmpty());
    }

    /**
     * replace(K key, V oldValue, V newValue)
     * replace(K key, V value)
     *
     * remove(Object key)
     */
    @Test
    public void learn_Remove_Replacing_Updating() {
        System.out.println(map);
        // Replace
        map.replace("banana", 10);                                     // replaces 3 with 10
        map.replace("apple", 5, 15);            // replaces 5 with 15
        map.replace("cherry", 9, 20);           // fails silently (current value ≠ 9)

        // Remove
        map.remove("banana");               // removes entry
        map.remove("apple", 100);               // fails silently
        map.remove("apple", 15);                // succeeds

        System.out.println(map);
    }
}
