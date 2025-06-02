package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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

        map.put("Apple", 1);

        map.putIfAbsent("Apple", 2);
        map.putIfAbsent("Banana", 3);

        Map<String, Integer> newMap = new HashMap<>();
        newMap.put("Orange", 4);
        map.putAll(newMap);
        System.out.println(map);                    // {Apple=1, Orange=4, Banana=3}

        System.out.println(map.get("Apple"));       // 1
        System.out.println(map.getOrDefault("Grape", -1));  // -1

        System.out.println(map.containsKey("Apple"));   // true
        System.out.println(map.containsValue(2));   // false

        System.out.println(map.keySet());           // [Apple, Orange, Banana]
        System.out.println(map.values());           // [1, 4, 3]
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
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Orange", 3);

        map.replace("Banana", 2, 20);
        map.replace("Orange", 30);
        map.replaceAll((k, v) -> k.length() * v);
        System.out.println(map);    // {Apple=5, Orange=180, Banana=120}

        map.remove("Apple");
        map.remove("Banana", 20);
        System.out.println(map);    // {Orange=180, Banana=120}

        System.out.println(map.compute("Orange", (k, v) -> v / k.length()));            // 30
        System.out.println(map.computeIfAbsent("Apple", key -> key.length()));          // 5
        System.out.println(map.computeIfPresent("Orange", (k, v) -> v + k.length()));   // 36
    }
}
