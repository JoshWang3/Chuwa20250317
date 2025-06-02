package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

        map.put("Apple", 1);

        map.putIfAbsent("Apple", 2);
        map.putIfAbsent("Banana", 3);

        Map<String, Integer> newMap = new HashMap<>();
        newMap.put("Orange", 4);
        map.putAll(newMap);
        System.out.println(map);                // {Apple=1, Banana=3, Orange=4}

        System.out.println(map.get("Banana"));  // 3
        System.out.println(map.firstKey());     // Apple
        System.out.println(map.lastKey());      // Orange

        System.out.println(map.containsKey("Apple"));   // true
        System.out.println(map.containsValue(2));   // false

        System.out.println(map.keySet());           // [Apple, Banana, Orange]
        System.out.println(map.values());           // [1, 3, 4]
        System.out.println(map.isEmpty());          // false
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
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Orange", 3);

        map.replace("Banana", 2, 20);
        map.replace("Orange", 30);

        map.remove("Apple");
        System.out.println(map);    // {Banana=20, Orange=30}
    }
}
