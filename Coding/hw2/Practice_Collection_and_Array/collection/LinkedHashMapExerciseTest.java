package com.chuwa.exercise.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author elena
 * @date 6/12/22 4:48 PM
 */
public class LinkedHashMapExerciseTest {
    LinkedHashMap<String, Integer> map;

    @Before
    public void setUp() throws Exception {
        map = new LinkedHashMap<>();
        // Insert
        map.put("apple", 1);
        map.put("banana", 2);
        map.putIfAbsent("cherry", 3);
        map.putIfAbsent("banana", 99); // Won’t overwrite
    }
    /**
     * e.g.
     * HashMap<String, Integer> map = new LinkedHashMap<>();
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
        // Put all
        Map<String, Integer> extra = Map.of("date", 4, "elderberry", 5);
        map.putAll(extra);

        // Get
        System.out.println("banana: " + map.get("banana"));
        System.out.println("fig (default): " + map.getOrDefault("fig", 0));

        // Check
        System.out.println("Contains key 'apple'? " + map.containsKey("apple"));
        System.out.println("Contains value 5? " + map.containsValue(5));

        // View
        System.out.println("Keys (in insertion order): " + map.keySet());
        System.out.println("Values: " + map.values());
        System.out.println("Is empty? " + map.isEmpty());
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
        System.out.println(map);
        // Replace
        map.replace("banana", 20);
        map.replace("cherry", 3, 30); // conditional
        map.replace("apple", 100, 200); // fails silently

        // Remove
        map.remove("apple");
        map.remove("banana", 999); // won't remove
        map.remove("banana", 20);  // removes successfully

        // ReplaceAll
        map.replaceAll((key, val) -> val * 2);

        // Compute
        map.compute("cherry", (k, v) -> v == null ? 1 : v + 5);
        map.computeIfAbsent("date", k -> 10);
        map.computeIfPresent("cherry", (k, v) -> v * 2);

        System.out.println(map);
    }
}
