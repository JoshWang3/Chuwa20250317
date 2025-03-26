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
        TreeMap<Integer, String> tree = new TreeMap<>();
        tree.put(1, "John");
        tree.put(2, "Jack");
        tree.put(3, "Jason");
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(4, "Teresa");
        map1.put(5, "Rose");
        tree.putAll(map1);

        System.out.println(tree.get(1));
        System.out.println(tree.firstKey());
        System.out.println(tree.lastKey());

        System.out.println(tree.containsKey(3));
        System.out.println(tree.containsValue("Jack"));

        System.out.println(tree.keySet());
        System.out.println(tree.values());
        System.out.println(tree.isEmpty());
    }

    /**
     * replace(K key, V oldValue, V newValue)
     * replace(K key, V value)
     *
     * remove(Object key)
     */
    @Test
    public void learn_Remove_Replacing_Updating() {
        TreeMap<Integer, String> tree = new TreeMap<>();
        tree.put(1, "John");
        tree.put(2, "Jack");
        tree.put(3, "Jason");
        tree.replace(1, "Jack", "Tom");
        tree.replace(2, "Ben");

        tree.replaceAll((k, v) -> v + "s");
        System.out.println(tree);
    }
}
