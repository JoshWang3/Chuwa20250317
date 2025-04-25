package com.chuwa.exercise.collection;

import org.junit.Test;

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
        map.put("abc", 1);
        map.put("abd", 2);
        map.putIfAbsent("abe", 3);
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("abf", 4);
        map.putAll(map1);
        System.out.println(tree.get("abc"));
        System.out.println(tree.firstKey());
        System.out.println(tree.lastKey());

        System.out.println(tree.containsKey("abf"));
        System.out.println(tree.containsValue(4));

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
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("abc", 1);
        map.put("abd", 2);
        map.replace("abc", 1, 3);
        map.replace("abd", 4);
        map.replaceAll((k,v) -> v + 1);
        System.out.println(map);

    }
}
