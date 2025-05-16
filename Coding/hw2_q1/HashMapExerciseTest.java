package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author b1go
 * @date 6/12/22 4:47 PM
 */
public class HashMapExerciseTest {

    /**
     * e.g.
     * Map<String, Integer> map = new HashMap<>();
     *
     * put(K key, V value)
     * putIfAbsent(K key, V value)
     * putAll(Map<? extends K, ? extends V> m)
     *
     * get(Object key)
     * getOrDefault(Object key, V defaultValue)
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
        Map<String, Integer> map = new HashMap<>();

        // put()
        map.put("apple", 3);
        map.put("banana", 5);

        // putIfAbsent()
        map.putIfAbsent("banana", 10); // already exists
        map.putIfAbsent("orange", 7); // new key

        assertEquals(Integer.valueOf(5), map.get("banana"));
        assertEquals(Integer.valueOf(7), map.get("orange"));

        // putAll()
        Map<String, Integer> extra = new HashMap<>();
        extra.put("grape", 8);
        extra.put("melon", 4);
        map.putAll(extra);

        // get()
        assertEquals(Integer.valueOf(8), map.get("grape"));

        // getOrDefault()
        assertEquals(Integer.valueOf(4), map.getOrDefault("melon", 0));
        assertEquals(Integer.valueOf(0), map.getOrDefault("pineapple", 0));

        // containsKey()
        assertTrue(map.containsKey("apple"));
        assertFalse(map.containsKey("peach"));

        // containsValue()
        assertTrue(map.containsValue(3));
        assertFalse(map.containsValue(100));

        // keySet()
        Set<String> keys = map.keySet();
        assertTrue(keys.contains("apple"));

        // values()
        Collection<Integer> values = map.values();
        assertTrue(values.contains(8));

        // isEmpty()
        assertFalse(map.isEmpty());
    }

    /**
     * replace(K key, V oldValue, V newValue)
     * replace(K key, V value)
     * replaceAll(BiFunction<? super K, ? super V, ? extends V> function)
     *
     * remove(Object key)
     * remove(Object key, Object value)
     *
     * compute(Key, BiFunction)
     * computeIfAbsent(Key, Function)
     * computeIfPresent(Key, BiFunction)
     */
    @Test
    public void learn_Remove_Replacing_Updating() {
        Map<String, Integer> map = new HashMap<>();
        map.put("dog", 1);
        map.put("cat", 2);
        map.put("rabbit", 3);

        // replace(key, oldValue, newValue)
        boolean replaced = map.replace("cat", 2, 5);
        assertTrue(replaced);
        assertEquals(Integer.valueOf(5), map.get("cat"));

        // replace(key, value)
        map.replace("dog", 10);
        assertEquals(Integer.valueOf(10), map.get("dog"));

        // replaceAll()
        map.replaceAll((key, val) -> val * 2);
        assertEquals(Integer.valueOf(20), map.get("dog"));
        assertEquals(Integer.valueOf(10), map.get("cat"));
        assertEquals(Integer.valueOf(6), map.get("rabbit"));

        // remove(key)
        map.remove("rabbit");
        assertFalse(map.containsKey("rabbit"));

        // remove(key, value)
        boolean removed = map.remove("cat", 100); // wrong value
        assertFalse(removed);
        removed = map.remove("cat", 10); // correct value
        assertTrue(removed);
        assertFalse(map.containsKey("cat"));

        // compute()
        map.compute("dog", (key, val) -> val + 5);
        assertEquals(Integer.valueOf(25), map.get("dog"));

        // computeIfAbsent()
        map.computeIfAbsent("mouse", key -> 7);
        assertEquals(Integer.valueOf(7), map.get("mouse"));

        // computeIfPresent()
        map.computeIfPresent("mouse", (key, val) -> val * 2);
        assertEquals(Integer.valueOf(14), map.get("mouse"));
    }
}
