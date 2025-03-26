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
        HashMap<String, Integer> map = new LinkedHashMap<>();

        map.put("Apple", 1);
        map.putIfAbsent("Orange", 3);
        Map<String, Integer> newMap = new HashMap<>();
        newMap.put("Grape", 4);
        newMap.put("Banana", 2);
        map.putAll(newMap);
        System.out.println(map);    // {Apple=1, Orange=3, Grape=4, Banana=2}

        System.out.println(map.get("Apple"));   // 1
        System.out.println(map.getOrDefault("Strawberry", -1)); // -1

        System.out.println(map.containsKey("Apple"));   // true
        System.out.println(map.containsValue(2));   // true

        System.out.println(map.keySet());           // [Apple, Orange, Grape, Banana]
        System.out.println(map.values());           // [1, 3, 4, 2]
        System.out.println(map.isEmpty());          // false
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
        HashMap<String, Integer> map = new LinkedHashMap<>();

        map.put("Apple", 1);
        map.put("Orange", 3);
        map.put("Grape", 4);
        map.put("Banana", 2);

        map.replace("Banana", 2, 20);
        map.replace("Orange", 30);
        map.replaceAll((k, v) -> k.length() * v);
        System.out.println(map);    // {Apple=5, Orange=180, Grape=20, Banana=120}

        map.remove("Apple");
        map.remove("Banana", 20);
        System.out.println(map);    // {Orange=180, Grape=20, Banana=120}

        System.out.println(map.compute("Orange", (k, v) -> v / k.length()));            // 30
        System.out.println(map.computeIfAbsent("Apple", key -> key.length()));          // 5
        System.out.println(map.computeIfPresent("Orange", (k, v) -> v + k.length()));   // 36
    }
}
