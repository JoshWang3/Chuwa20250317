package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
/**
 * @author b1go
 * @date 6/12/22 4:47 PM
 */
public class TreeMapExerciseTest {

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
        TreeMap<String, Integer> map = new TreeMap<>();

        // put(K, V)
        map.put("banana", 3);
        map.put("apple", 5);
        map.put("cherry", 7);

        // putIfAbsent(K, V)
        map.putIfAbsent("banana", 100); // won't overwrite
        map.putIfAbsent("date", 9);     // will insert

        assertEquals(Integer.valueOf(3), map.get("banana"));
        assertEquals(Integer.valueOf(9), map.get("date"));

        // putAll(Map)
        Map<String, Integer> moreFruits = new HashMap<>();
        moreFruits.put("elderberry", 11);
        moreFruits.put("fig", 13);
        map.putAll(moreFruits);

        // get(Object key)
        assertEquals(Integer.valueOf(5), map.get("apple"));

        // firstKey() & lastKey()
        assertEquals("apple", map.firstKey()); // Sorted by key
        assertEquals("fig", map.lastKey());

        // containsKey() & containsValue()
        assertTrue(map.containsKey("cherry"));
        assertFalse(map.containsKey("grape"));
        assertTrue(map.containsValue(11));
        assertFalse(map.containsValue(1000));

        // keySet() & values()
        Set<String> keys = map.keySet();
        assertTrue(keys.contains("banana"));

        Collection<Integer> values = map.values();
        assertTrue(values.contains(13));

        // isEmpty()
        assertFalse(map.isEmpty());
    }

    /**
     * replace(K key, V oldValue, V newValue)
     * replace(K key, V value)
     *
     * remove(Object key)
     */
    @Test
    public void learn_Remove_Replacing_Updating() {
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("cherry", 3);

        // replace(K, oldValue, newValue)
        boolean replaced = map.replace("banana", 2, 20);
        assertTrue(replaced);
        assertEquals(Integer.valueOf(20), map.get("banana"));

        // replace(K, newValue)
        map.replace("apple", 10);
        assertEquals(Integer.valueOf(10), map.get("apple"));

        // remove(Object key)
        map.remove("cherry");
        assertFalse(map.containsKey("cherry"));
    }
}
