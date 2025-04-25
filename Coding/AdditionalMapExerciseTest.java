package com.chuwa.exercise.collection;

import org.junit.Test;

/**
 * @author Jianan Yao
 * @date 04/08/2025
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
        ConcurrentHashMap<String, Integer> carCnt = new ConcurrentHashMap<>();
        carCnt.put("sedan", 10);
        carCnt.put("trunk", 4);
        carCnt.putIfAbsent("suv", 7);
        Map<String, Integer> hm = new HashMap<>();
        hm.put("crossover", 2);
        carCnt.putAll(hm);
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
        map.put("a", 1);
        map.putIfAbsent("a",2);
        map.putIfAbsent("b",3);
        System.out.println(map);
    }

    /**
     * e.g.
     * EnumMap<DayOfWeek, Integer> enumMap = new EnumMap<>(DayOfWeek.class);
     *
     * put(K key, V value)
     * putIfAbsent(K key, V value)
     */
    enum Color {
        red, blue, black
    }
    @Test
    public void learn_EnumMap() {
        EnumMap<Color, Integer> hm = new EnumMap<>(Color.class);
        map.put(Color.red, 1);
        map.putIfAbsent(Color.red, 2);
        map.putIfAbsent(Color.black, 3);
        System.out.println(map);
    }
}
