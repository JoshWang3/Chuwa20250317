package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.EnumMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author elena
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

        map.put("apple", 1);
        map.putIfAbsent("banana", 2);
        map.putAll(Map.of("cherry", 3, "date", 4));

        System.out.println("ConcurrentHashMap: " + map);

        // Note: thread-safe for concurrent reads/writes
        map.computeIfAbsent("elderberry", k -> 5);
        map.replace("banana", 10);

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
        IdentityHashMap<String, Integer> map = new IdentityHashMap<>();

        String a1 = new String("apple");
        String a2 = new String("apple");

        map.put(a1, 1);
        map.put(a2, 2); // Both keys are treated as different (reference-based)

        map.putIfAbsent("banana", 3);

        System.out.println("IdentityHashMap: " + map);

    }

    enum Day {
        MON, TUE, WED
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

        EnumMap<Day, Integer> map = new EnumMap<>(Day.class);
        map.put(Day.MON, 1);
        map.putIfAbsent(Day.TUE, 2);
        map.put(Day.WED, 3);

        System.out.println("EnumMap: " + map);
    }
}
