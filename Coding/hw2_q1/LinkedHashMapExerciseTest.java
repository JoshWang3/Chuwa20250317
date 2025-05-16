package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
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
        Map<String, Integer> map = new LinkedHashMap<>();

        // put
        map.put("apple", 1);
        map.put("banana", 2);

        // putIfAbsent
        map.putIfAbsent("banana", 100); // 不会替换已有值
        map.putIfAbsent("cherry", 3);   // 会插入新键

        assertEquals(Integer.valueOf(2), map.get("banana"));
        assertEquals(Integer.valueOf(3), map.get("cherry"));

        // putAll
        Map<String, Integer> extra = new LinkedHashMap<>();
        extra.put("date", 4);
        extra.put("elderberry", 5);
        map.putAll(extra);

        // get / getOrDefault
        assertEquals(Integer.valueOf(4), map.get("date"));
        assertEquals(Integer.valueOf(0), map.getOrDefault("fig", 0));

        // containsKey / containsValue
        assertTrue(map.containsKey("apple"));
        assertFalse(map.containsKey("fig"));
        assertTrue(map.containsValue(5));
        assertFalse(map.containsValue(999));

        // keySet / values
        Set<String> keys = map.keySet();
        assertTrue(keys.contains("banana"));

        Collection<Integer> values = map.values();
        assertTrue(values.contains(4));

        // isEmpty
        assertFalse(map.isEmpty());
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
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("cat", 10);
        map.put("dog", 20);
        map.put("bird", 30);

        // replace(key, oldValue, newValue)
        boolean replaced = map.replace("dog", 20, 200);
        assertTrue(replaced);
        assertEquals(Integer.valueOf(200), map.get("dog"));

        // replace(key, newValue)
        map.replace("cat", 100);
        assertEquals(Integer.valueOf(100), map.get("cat"));

        // replaceAll
        map.replaceAll((k, v) -> v + 1);
        assertEquals(Integer.valueOf(101), map.get("cat"));

        // remove(key)
        map.remove("bird");
        assertFalse(map.containsKey("bird"));

        // remove(key, value)
        assertFalse(map.remove("dog", 999)); // 不匹配，不删除
        assertTrue(map.remove("dog", 201));  // 匹配成功（+1 后是 201）

        // compute
        map.compute("cat", (k, v) -> v * 2);
        assertEquals(Integer.valueOf(202), map.get("cat"));

        // computeIfAbsent
        map.computeIfAbsent("fish", k -> 50);
        assertEquals(Integer.valueOf(50), map.get("fish"));

        // computeIfPresent
        map.computeIfPresent("fish", (k, v) -> v + 10);
        assertEquals(Integer.valueOf(60), map.get("fish"));
    }
}
