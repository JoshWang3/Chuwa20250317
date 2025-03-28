package com.chuwa.exercise.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author elena
 * @date 6/12/22 4:47 PM
 */
public class HashMapExerciseTest {
    Map<String, Integer> map;

    @Before
    public void setUp() throws Exception {
        map = new HashMap<>();
        map.put("apple", 3);
        map.put("banana", 5);
        map.putIfAbsent("banana", 10);
        map.putIfAbsent("cherry", 7);
    }

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
        System.out.println(map);
        map.putIfAbsent("apple", 4);
        map.put("peach", 0);
        Map<String, Integer> newFruits = new HashMap<>();
        newFruits.put("date", 4);
        newFruits.put("elderberry", 6);
        map.putAll(newFruits);
        System.out.println(map);

        System.out.println("apple count: " + map.get("apple"));
        System.out.println("fig count: " + map.getOrDefault("fig", 0));

        System.out.println("Contains key 'banana'? " + map.containsKey("banana"));
        System.out.println("Contains value 6? " + map.containsValue(6));

        System.out.println("Keys: " + map.keySet());
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
        map.replace("apple", 4);
        map.replace("banana", 5, 9);
        map.replaceAll((k, v) -> v + 2);
        map.remove("apple");
        map.remove("banana", 2);
        map.compute("apple", (key, value) -> value == null ? 1 : value + 1);
        map.computeIfAbsent("date", key -> 100);
        map.computeIfPresent("apple", (key, value) -> value + 5);
        System.out.println(map);
    }
}
