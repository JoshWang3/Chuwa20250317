package com.chuwa.exercise.collection;

import java.time.DayOfWeek;
import java.util.EnumMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import static org.junit.Assert.*;
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

        // put
        map.put("apple", 3);
        map.put("banana", 5);

        // putIfAbsent
        map.putIfAbsent("banana", 10); // won't update because "banana" already exists
        map.putIfAbsent("cherry", 7);

        // putAll
        Map<String, Integer> anotherMap = new ConcurrentHashMap<>();
        anotherMap.put("date", 4);
        anotherMap.put("elderberry", 6);
        map.putAll(anotherMap);

        assertEquals(3, (int) map.get("apple"));
        assertEquals(5, (int) map.get("banana")); // not updated
        assertEquals(7, (int) map.get("cherry"));
        assertEquals(4, (int) map.get("date"));
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
        Map<DayOfWeek, Integer> map = new IdentityHashMap<>();
        map.put(DayOfWeek.MONDAY, 0);
        map.put(DayOfWeek.TUESDAY, 0);
        map.putIfAbsent(DayOfWeek.WEDNESDAY, 0);
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
        map.put(DayOfWeek.MONDAY, 0);
        map.putIfAbsent(DayOfWeek.TUESDAY, 0);
    }
}
