package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author b1go
 * @date 6/12/22 4:48 PM
 */
public class LinkedHashMapExerciseTest {
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
        HashMap<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "Tom");
        map.put(2, "Mary");
        map.putIfAbsent(3, "John");
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(4, "Teresa");
        map1.put(5, "Rose");
        map.putAll(map1);
        System.out.println(map.get(2));
        System.out.println(map.getOrDefault(2, "No"));
        System.out.println(map.containsKey(3));
        System.out.println(map.containsValue("Rose"));

        System.out.println(map.keySet());
        System.out.println(map.values());
        System.out.println(map.isEmpty());
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
        HashMap<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "Tom");
        map.put(2, "Mary");
        map.putIfAbsent(3, "John");
        map.replace(1, "Tom", "Jack");
        map.replace(2, "Ben");

        map.replaceAll((k, v) -> v + "s");
        System.out.println(map);

        map.remove(2);
        map.remove(3, "John");

        map.compute(1, (k, v) -> v + "eat");
        map.computeIfAbsent(5, k -> "Jerry");
        map.computeIfPresent(3, (k, v) -> v + "nothing");

        System.out.println(map);
    }
}
