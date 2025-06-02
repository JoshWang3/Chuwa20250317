package com.chuwa.exercise.collection;

import org.junit.Test;

import java.time.DayOfWeek;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author b1go
 * @date 6/12/22 4:48 PM
 */
public class AdditionalMapExerciseTest {

    /**
     * e.g.
     * ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
     *
     * put(K key, V value)
     * putIfAbsent(K key, V value)
     * putAll(Map<? extends K, ? extends V> m)
     */
    @Test
    public void learn_ConcurrentHashMap() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        map.put("Apple", 1);

        map.putIfAbsent("Apple", 2);
        map.putIfAbsent("Banana", 3);

        Map<String, Integer> newMap = new HashMap<>();
        newMap.put("Orange", 4);
        map.putAll(newMap);
        System.out.println(map);                    // {Apple=1, Orange=4, Banana=3}
    }

    /**
     * e.g.
     * Map<DayOfWeek, Integer> map = new IdentityHashMap<>();
     *
     * put(K key, V value)
     * putIfAbsent(K key, V value)
     */
    @Test
    public void learn_IdentityHashMap() {
        Map<String, Integer> map = new IdentityHashMap<>();

        map.put("Apple", 1);

        map.putIfAbsent("Apple", 2);
        map.putIfAbsent("Banana", 3);

        System.out.println(map);    // {Apple=1, Banana=3}
    }

    /**
     * e.g.
     * EnumMap<DayOfWeek, Integer> enumMap = new EnumMap<>(DayOfWeek.class);
     *
     * put(K key, V value)
     * putIfAbsent(K key, V value)
     */
    @Test
    public void learn_EnumMap() {
        EnumMap<DayOfWeek, Integer> map = new EnumMap<>(DayOfWeek.class);

        map.put(DayOfWeek.MONDAY, 1);
        map.put(DayOfWeek.MONDAY, 2);

        map.putIfAbsent(DayOfWeek.MONDAY, 3);
        System.out.println(map);    // {MONDAY=2}
    }
}
