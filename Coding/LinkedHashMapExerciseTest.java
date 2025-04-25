package com.chuwa.exercise.collection;

import org.junit.Test;

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
        Map<Integer, Integer> map = new LinkedHashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.putIfAbsent(3, 3);
        Map<Integer, Integer> map1 = new HashMap<>();
        map1.put(4,4);
        map.putAll(map1);
        System.out.println(map.get(1));
        System.out.println(map.getOrDefault(2, 8));
        System.out.println(map.containsKey(3));
        System.out.println(map.containsValue(4));
        System.out.println(map.ketSet());
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
        Map<Integer, Integer> map = new LinkedHashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.putIfAbsent(3, 3);
        map.replace(1, 1, 4);
        map.replace(2, 5);
        map.replaceAll((k, v) -> v + 1);
        System.out.println(map);
        map.remove(2);
        map.remove(3, 3);

        map.compute(1, (k, v) -> v + 2);
        map.computeIfAbsent(5, k -> 5);
        map.computeIfPresent(3, (k, v) -> v + 15);

        System.out.println(map);

    }
}
