# Practice collection
## ArrayListExerciseTest.java
```java
package com.chuwa.exercise.collection;

import org.junit.Test;

/**
 * @author Linyun (William) Wei
 * @date Mar/26/2025
 */
public class ArrayListExerciseTest {
    /**
     * new ArrayList()
     * add elements
     * get element
     * get Size
     * list.addAll(anotherList)
     */
    @Test
    public void learn_Inserting_And_Retrieving() {
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");

        fruits.add(1, "Orange"); // Apple, Orange, Banana, Cherry

        assertEquals("Orange", fruits.get(1));

        assertEquals(4, fruits.size());

        List<String> moreFruits = Arrays.asList("Mango", "Peach");
        fruits.addAll(moreFruits);

        assertTrue(fruits.contains("Mango"));
        assertEquals(6, fruits.size());
    }

    /**
     * remove(int index)
     * remove(Object o)
     * removeRange(int fromIndex, int toIndex)
     * removeAll(Collection<?> c)
     * clear()
     *
     * Update:
     * set(int index, E e)
     * replaceAll(UnaryOperator<E> operator)
     *
     * check:
     * contains(Object o)
     * indexOf(Object o)
     * lastIndexOf(Object o)
     */
    @Test
    public void learn_Remove_Replacing_Updating() {
        List<String> items = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H"));

        items.remove(2); // removes "C"

        items.remove("E");

        items.removeAll(Arrays.asList("F", "G"));

        items.set(0, "Z");

        items.replaceAll(s -> s.toLowerCase());

        assertTrue(items.contains("b"));

        assertEquals(1, items.indexOf("b"));
        assertEquals(1, items.lastIndexOf("b"));

        items.clear();
        assertEquals(0, items.size());
    }

    /**
     * iterator()
     * hasNext()
     * next()
     * remove()
     * forEachRemaining(Consumer<? super E> action) -- from Java8
     */

    @Test
    public void learn_Iterator() {
        List<String> colors = new ArrayList<>(Arrays.asList("Red", "Green", "Blue", "Yellow"));

        Iterator<String> iterator = colors.iterator();

        while (iterator.hasNext()) {
            String color = iterator.next();
            if (color.equals("Green")) {
                iterator.remove();
            }
        }

        assertFalse(colors.contains("Green"));

        iterator = colors.iterator();
        StringBuilder result = new StringBuilder();
        iterator.forEachRemaining(result::append);

        assertEquals("RedBlueYellow", result.toString());
    }

    /**
     * sort(List<T> list)
     * Collections.sort(List<T> t)
     * Comparator.reverseOrder()
     */

    @Test
    public void learn_Sorting() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 3, 9, 1, 4));

        Collections.sort(numbers);
        assertEquals(Arrays.asList(1, 3, 4, 5, 9), numbers);

        numbers.sort(Comparator.reverseOrder());
        assertEquals(Arrays.asList(9, 5, 4, 3, 1), numbers);
    }
}
```

## LinkedListExerciseTest.java
```java
package com.chuwa.exercise.collection;

import org.junit.Test;

/**
 * @author Linyun (William) Wei
 * @date Mar/26/2025
 */
public class LinkedListExerciseTest {

    /**
     * e.g.
     * List<Integer> list = new LinkedList<Integer>();
     * Inserting:
     * add(E e) or addLast(E e)
     * addFirst(E e)
     * add(int index, E element)
     * addAll(Collection c)
     * addAll(int index, Collection c)
     *
     * Retrieving:
     * getFirst()
     * getLast()
     * get(int index)
     *
     */
    @Test
    public void learn_Inserting_And_Retrieving() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(10);
        list.addLast(20);

        list.addFirst(5);

        list.add(1, 8);  // list: [5, 8, 10, 20]

        List<Integer> moreNumbers = Arrays.asList(30, 40);
        list.addAll(moreNumbers);

        list.addAll(2, Arrays.asList(99, 100));  // list: [5, 8, 99, 100, 10, 20, 30, 40]

        assertEquals(5, list.getFirst());
        assertEquals(40, list.getLast());
        assertEquals(99, list.get(2));
        assertEquals(8, list.size());

    }

    /**
     * removeFirst()
     * removeLast()
     * remove(int index)
     * remove(Object o)
     * removeLastOccurrence()
     *
     * sort()
     */

    @Test
    public void learn_Remove_Sort() {
        LinkedList<String> list = new LinkedList<>(Arrays.asList("Banana", "Apple", "Cherry", "Banana", "Date"));

        String firstRemoved = list.removeFirst(); // "Banana"
        assertEquals("Banana", firstRemoved);

        String lastRemoved = list.removeLast(); // "Date"
        assertEquals("Date", lastRemoved);

        String removedAtIndex = list.remove(1); // removes "Cherry"
        assertEquals("Cherry", removedAtIndex);

        boolean removed = list.remove("Banana"); // removes second "Banana"
        assertTrue(removed);

        list.add("Apple");
        list.add("Apple");

        boolean lastOccurrenceRemoved = list.removeLastOccurrence("Apple");
        assertTrue(lastOccurrenceRemoved);

        Collections.sort(list);
        assertEquals(Arrays.asList("Apple", "Apple"), list);
    }
}
```

## CopyOnWriteArrayListExerciseTest.java
```java
package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Linyun (William) Wei
 * @date Mar/26/2025
 */
public class CopyOnWriteArrayListExerciseTest {

    /**
     * e.g.
     * List list = new CopyOnWriteArrayList();
     *
     * add(E e)
     * add(int index, E element)
     * addAll(Collection c)
     * addIfAbsent(E e)
     * addAllAbsent(Collection c)
     */
    @Test
    public void learn_Inserting_And_Retrieving() {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        list.add("A");
        list.add("B");

        list.add(1, "X"); // A, X, B

        list.addAll(Arrays.asList("C", "D")); // A, X, B, C, D

        boolean added = list.addIfAbsent("E");
        assertTrue(added);

        boolean notAdded = list.addIfAbsent("A");
        assertFalse(notAdded);

        int count = list.addAllAbsent(Arrays.asList("F", "A", "G"));
        assertEquals(2, count);

        assertEquals(Arrays.asList("A", "X", "B", "C", "D", "E", "F", "G"), list);
    }

    /**
     * iterator()
     * hasNext()
     * next()
     * remove()
     */

    @Test
    public void learn_Iterator() {
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Orange");

        Iterator<String> itr = list.iterator();

        StringBuilder result = new StringBuilder();

        while (itr.hasNext()) {
            String fruit = itr.next();
            result.append(fruit).append(" ");
        }

        assertEquals("Apple Banana Orange ", result.toString());

        itr = list.iterator();
        assertThrows(UnsupportedOperationException.class, itr::remove);
    }
}
```

## HashSetExerciseTest.java
```java
package com.chuwa.exercise.collection;

import org.junit.Test;

/**
 * @author Linyun (William) Wei
 * @date Mar/26/2025
 */
public class HashSetExerciseTest {
    /**
     * e.g.
     * Set<Integer> set= new HashSet<>();
     *
     * add(E e)
     * addAll(Collection<> c)
     *
     * get()
     * contains()
     *
     * remove(Object o)
     * clear()
     *
     * isEmpty()
     *
     *
     */

    @Test
    public void learn_Inserting_And_Retrieving_Removing() {
        Set<Integer> set = new HashSet<>();

        assertTrue(set.add(1));
        assertTrue(set.add(2));
        assertFalse(set.add(1));

        set.addAll(Arrays.asList(3, 4, 5));

        assertTrue(set.contains(3));
        assertFalse(set.contains(10));

        assertTrue(set.remove(2));
        assertFalse(set.remove(100));

        assertFalse(set.isEmpty());

        set.clear();
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
    }
}
```

## TreeSetExerciseTest.java
```java
package com.chuwa.exercise.collection;

import org.junit.Test;

/**
 * @author Linyun (William) Wei
 * @date Mar/26/2025
 */
public class TreeSetExerciseTest {
    /**
     * e.g.
     * Set<Integer> set= new TreeSet<>();
     *
     * add(E e)
     * addAll(Collection<> c)
     *
     * contains(Object o)
     *
     * first()
     * last()
     * subSet(E fromElement, E toElement)
     * headSet(E toElement)
     * tailSet(E fromElement)
     *
     * remove(Object o)
     *
     * size()
     * isEmpty()
     *
     *
     */

    @Test
    public void learn_Inserting_And_Retrieving_Removing() {
        Set<Integer> set = new TreeSet<>();

        set.add(50);
        set.add(10);
        set.add(30);
        set.add(70);

        set.addAll(Arrays.asList(20, 40, 60));

        assertTrue(set.contains(30));
        assertFalse(set.contains(99));

        TreeSet<Integer> treeSet = new TreeSet<>(set); // 10, 20, 30, 40, 50, 60, 70

        assertEquals(10, treeSet.first());
        assertEquals(70, treeSet.last());

        SortedSet<Integer> subSet = treeSet.subSet(20, 60); // [20, 30, 40, 50]
        assertEquals(Arrays.asList(20, 30, 40, 50), subSet.stream().toList());

        SortedSet<Integer> headSet = treeSet.headSet(40); // [10, 20, 30]
        assertEquals(Arrays.asList(10, 20, 30), headSet.stream().toList());

        SortedSet<Integer> tailSet = treeSet.tailSet(40); // [40, 50, 60, 70]
        assertEquals(Arrays.asList(40, 50, 60, 70), tailSet.stream().toList());

        assertTrue(treeSet.remove(30));
        assertFalse(treeSet.remove(100));

        assertEquals(6, treeSet.size());
        assertFalse(treeSet.isEmpty());

        treeSet.clear();
        assertTrue(treeSet.isEmpty());
    }
}
```

## HashMapExerciseTest.java
```java
package com.chuwa.exercise.collection;

import org.junit.Test;

/**
 * @author Linyun (William) Wei
 * @date Mar/26/2025
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

        map.put("Apple", 10);
        map.put("Banana", 20);

        map.putIfAbsent("Orange", 15);
        map.putIfAbsent("Apple", 100);

        Map<String, Integer> moreFruits = Map.of("Mango", 25, "Peach", 30);
        map.putAll(moreFruits);

        assertEquals(10, map.get("Apple"));
        assertNull(map.get("Pear"));

        assertEquals(0, map.getOrDefault("Pear", 0));

        assertTrue(map.containsKey("Banana"));
        assertFalse(map.containsKey("Pineapple"));

        assertTrue(map.containsValue(25));
        assertFalse(map.containsValue(99));

        Set<String> keys = map.keySet();
        Collection<Integer> values = map.values();

        assertTrue(keys.contains("Mango"));
        assertTrue(values.contains(30));
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
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        boolean replaced = map.replace("A", 1, 10);
        assertTrue(replaced);
        assertEquals(10, map.get("A"));

        map.replace("B", 20);
        assertEquals(20, map.get("B"));

        map.replaceAll((k, v) -> v * 2);
        assertEquals(20, map.get("A"));
        assertEquals(40, map.get("B"));
        assertEquals(6, map.get("C")); // 3×2

        map.remove("C");
        assertFalse(map.containsKey("C"));

        boolean removed = map.remove("B", 999);
        assertFalse(removed);

        removed = map.remove("B", 40);
        assertTrue(removed);
        assertFalse(map.containsKey("B"));

        map.compute("A", (k, v) -> v + 5); // A = 25
        assertEquals(25, map.get("A"));

        map.computeIfAbsent("D", k -> 100); // D = 100
        assertEquals(100, map.get("D"));

        map.computeIfPresent("A", (k, v) -> v * 2); // A = 50
        assertEquals(50, map.get("A"));
    }
}
```

## TreeMapExerciseTest.java
```java
package com.chuwa.exercise.collection;

import org.junit.Test;

/**
 * @author Linyun (William) Wei
 * @date Mar/26/2025
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

        map.put("Banana", 20);
        map.put("Apple", 10);
        map.put("Cherry", 30);

        map.putIfAbsent("Date", 25);
        map.putIfAbsent("Apple", 100);

        Map<String, Integer> moreFruits = Map.of("Elderberry", 40, "Fig", 50);
        map.putAll(moreFruits);

        assertEquals(10, map.get("Apple"));
        assertNull(map.get("Grape"));

        assertEquals("Apple", map.firstKey());
        assertEquals("Fig", map.lastKey());

        assertTrue(map.containsKey("Date"));
        assertTrue(map.containsValue(50));

        Set<String> keys = map.keySet();
        Collection<Integer> values = map.values();

        assertEquals(
            "[Apple, Banana, Cherry, Date, Elderberry, Fig]",
            keys.toString()
        );
        assertFalse(map.isEmpty());
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
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        boolean replaced = map.replace("A", 1, 10);
        assertTrue(replaced);
        assertEquals(10, map.get("A"));

        map.replace("B", 20);
        assertEquals(20, map.get("B"));

        Integer removed = map.remove("C");
        assertEquals(3, removed);
        assertFalse(map.containsKey("C"));

        assertNull(map.remove("Z"));
    }
}
```

## LinkedHashMapExerciseTest.java
```java
package com.chuwa.exercise.collection;

import org.junit.Test;

/**
 * @author Linyun (William) Wei
 * @date Mar/26/2025
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

        map.put("Banana", 20);
        map.put("Apple", 10);
        map.put("Cherry", 30);

        map.putIfAbsent("Date", 25);
        map.putIfAbsent("Apple", 99);

        Map<String, Integer> moreFruits = Map.of("Elderberry", 40, "Fig", 50);
        map.putAll(moreFruits);

        assertEquals(10, map.get("Apple"));
        assertEquals(0, map.getOrDefault("Grape", 0));

        assertTrue(map.containsKey("Date"));
        assertFalse(map.containsKey("Grape"));
        assertTrue(map.containsValue(50));

        List<String> keys = new ArrayList<>(map.keySet());
        List<Integer> values = new ArrayList<>(map.values());

        assertEquals(
            Arrays.asList("Banana", "Apple", "Cherry", "Date", "Elderberry", "Fig"),
            keys
        );

        assertEquals(
            Arrays.asList(20, 10, 30, 25, 40, 50),
            values
        );

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
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        boolean replaced = map.replace("A", 1, 10);
        assertTrue(replaced);
        assertEquals(10, map.get("A"));

        map.replace("B", 20);
        assertEquals(20, map.get("B"));

        map.replaceAll((k, v) -> v + 100);
        assertEquals(110, map.get("A"));
        assertEquals(120, map.get("B"));
        assertEquals(103, map.get("C"));

        Integer removed = map.remove("C");
        assertEquals(103, removed);

        boolean success = map.remove("B", 120);
        assertTrue(success);
        assertFalse(map.containsKey("B"));

        map.compute("A", (k, v) -> v * 2); // 110 * 2
        assertEquals(220, map.get("A"));

        map.computeIfAbsent("D", k -> 1000);
        assertEquals(1000, map.get("D"));

        map.computeIfPresent("D", (k, v) -> v + 500);
        assertEquals(1500, map.get("D"));
    }
}
```

## AdditionalMapExerciseTest.java
```java
package com.chuwa.exercise.collection;

import org.junit.Test;

/**
 * @author Linyun (William) Wei
 * @date Mar/26/2025
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

        map.put("A", 1);
        map.put("B", 2);

        map.putIfAbsent("B", 100);
        map.putIfAbsent("C", 3);

        Map<String, Integer> more = Map.of("D", 4, "E", 5);
        map.putAll(more);

        assertEquals(1, map.get("A"));
        assertEquals(2, map.get("B"));
        assertEquals(3, map.get("C"));
        assertEquals(4, map.get("D"));

        assertThrows(NullPointerException.class, () -> map.put(null, 1));
        assertThrows(NullPointerException.class, () -> map.put("Z", null));
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

        String k1 = new String("Key");
        String k2 = new String("Key");

        map.put(k1, 100);
        map.put(k2, 200);

        assertEquals(2, map.size());
        assertEquals(100, map.get(k1));
        assertEquals(200, map.get(k2));

        // putIfAbsent
        map.putIfAbsent(k1, 999);
        assertEquals(100, map.get(k1));
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
        EnumMap<DayOfWeek, Integer> enumMap = new EnumMap<>(DayOfWeek.class);

        enumMap.put(DayOfWeek.MONDAY, 1);
        enumMap.put(DayOfWeek.FRIDAY, 5);

        enumMap.putIfAbsent(DayOfWeek.MONDAY, 100);
        enumMap.putIfAbsent(DayOfWeek.SUNDAY, 7);

        assertEquals(1, enumMap.get(DayOfWeek.MONDAY));
        assertEquals(5, enumMap.get(DayOfWeek.FRIDAY));
        assertEquals(7, enumMap.get(DayOfWeek.SUNDAY));

        assertTrue(enumMap.containsKey(DayOfWeek.FRIDAY));
        assertFalse(enumMap.containsKey(DayOfWeek.TUESDAY));
    }
}
```

## ArraysExerciseTest.java
```java
package com.chuwa.exercise.collection;

import org.junit.Test;

/**
 * @author Linyun (William) Wei
 * @date Mar/26/2025
 */
public class ArraysExerciseTest {

    /**
     * e.g.
     * int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
     * numbers[?]
     *
     * numbers[?] = #
     */

    @Test
    public void learn_Inserting_And_Retrieving() {
        int[] numbers = { 1, 2, 3, 4, 5 };

        assertEquals(1, numbers[0]);
        assertEquals(5, numbers[numbers.length - 1]);

        numbers[2] = 99;
        assertEquals(99, numbers[2]);
    }

    /**
     * binarySearch()
     * e.g.
     * Arrays.binarySearch(numbers, 4);
     *
     * sort(array)
     * sort(array, fromIndex, toIndex)
     * e.g.
     * Arrays.sort(numbers);
     *
     * Arrays.parallelSort(numbers);
     */
    @Test
    public void learn_search_and_sort() {
        int[] numbers = { 5, 2, 8, 1, 3 };

        Arrays.sort(numbers); // [1, 2, 3, 5, 8]
        assertEquals(2, Arrays.binarySearch(numbers, 3));

        int[] partial = { 9, 7, 5, 3, 1 };
        Arrays.sort(partial, 1, 4);
        assertArrayEquals(new int[]{9, 3, 5, 7, 1}, partial);

        int[] parallel = { 20, 10, 30, 5 };
        Arrays.parallelSort(parallel);
        assertArrayEquals(new int[]{5, 10, 20, 30}, parallel);
    }

    /**
     * copyOf()
     * e.g.
     * Arrays.copyOf(numbers, numbers.length);
     *
     * copyOfRange()
     * e.g.
     * Arrays.copyOfRange(numbers, 0, 5);
     */
    @Test
    public void learn_copy_of_array() {
        int[] numbers = { 10, 20, 30, 40, 50 };

        int[] copy = Arrays.copyOf(numbers, numbers.length);
        assertArrayEquals(numbers, copy);

        int[] range = Arrays.copyOfRange(numbers, 1, 4);
        assertArrayEquals(new int[]{20, 30, 40}, range);
    }

    /**
     * asList()
     * e.g.
     * List<Integer> list = Arrays.asList(numbers);
     *
     * equals()
     * e.g.
     * Arrays.equals(numbers1, numbers2);
     *
     * fill()
     * e.g.
     * Arrays.fill(numbers, 20);
     *
     */

    @Test
    public void learn_common_operations() {
        Integer[] numbers = { 1, 2, 3, 4, 5 };

        List<Integer> list = Arrays.asList(numbers);
        assertEquals(5, list.size());
        assertEquals(3, list.get(2));

        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        int[] c = {1, 2, 4};
        assertTrue(Arrays.equals(a, b));
        assertFalse(Arrays.equals(a, c));

        int[] filled = new int[5];
        Arrays.fill(filled, 20); // [20, 20, 20, 20, 20]
        for (int val : filled) {
            assertEquals(20, val);
    }
}
```

## CollectionsExerciseTest.java
```java
package com.chuwa.exercise.collection;

import org.junit.Test;

/**
 * @author Linyun (William) Wei
 * @date Mar/26/2025
 */
public class CollectionsExerciseTest {

    /**
     * Collections.min(list))
     * min(Collection c, Comparator comp)
     *
     * Collections.max(list)
     * max(Collection c, Comparator comp)
     *
     * frequency(Collection c, object o)
     */

    @Test
    public void learn_common_collections_operations() {
        List<Integer> numbers = Arrays.asList(5, 3, 9, 1, 3, 7);

        assertEquals(1, Collections.min(numbers));
        assertEquals(9, Collections.max(numbers));

        List<String> words = Arrays.asList("Java", "Python", "Go", "JavaScript");
        String longest = Collections.max(words, Comparator.comparingInt(String::length));
        assertEquals("JavaScript", longest);

        int freq = Collections.frequency(numbers, 3);
        assertEquals(2, freq);
    }

    /**
     * synchronizedList()
     */

    @Test
    public void learn_thread_safe_ArrayList() {
        List<String> unsafeList = new ArrayList<>();
        unsafeList.add("A");
        unsafeList.add("B");

        List<String> safeList = Collections.synchronizedList(unsafeList);

        synchronized (safeList) {
            for (String item : safeList) {
                assertNotNull(item);
            }
        }

        safeList.add("C");
        assertEquals(3, safeList.size());
    }
}
```

# Write code to compare and explain checkedException vs uncheckedException
## Explanation

| Aspect               | Checked Exception                           | Unchecked Exception                            |
|----------------------|---------------------------------------------|------------------------------------------------|
| **Inheritance**      | Extends `Exception`                         | Extends `RuntimeException`                     |
| **Compiler Handling**| **Must** be caught or declared with `throws`| Optional to catch                              |
| **Common Examples**  | `IOException`, `SQLException`               | `NullPointerException`, `ArithmeticException`  |
| **Use Case**         | For recoverable issues                      | For programming bugs or logic errors           |
| **Checked in `main()`** | Needs `try-catch` or `throws`           | No need to handle unless desired               |

## Java Code demo: Checked vs Unchecked Exception
```java
import java.io.*;

// Custom Checked Exception
class MyCheckedException extends Exception {
    public MyCheckedException(String message) {
        super(message);
    }
}

// Custom Unchecked Exception
class MyUncheckedException extends RuntimeException {
    public MyUncheckedException(String message) {
        super(message);
    }
}

public class ExceptionDemo {

    // Method that throws a checked exception
    public static void checkedExample(boolean trigger) throws MyCheckedException {
        if (trigger) {
            throw new MyCheckedException("This is a CHECKED exception.");
        }
    }

    // Method that throws an unchecked exception
    public static void uncheckedExample(boolean trigger) {
        if (trigger) {
            throw new MyUncheckedException("This is an UNCHECKED exception.");
        }
    }

    public static void main(String[] args) {
        // Checked Exception - must be handled with try/catch or declared with throws
        try {
            checkedExample(true);
        } catch (MyCheckedException e) {
            System.out.println("Caught checked exception: " + e.getMessage());
        }

        // Unchecked Exception - optional to handle
        try {
            uncheckedExample(true);
        } catch (MyUncheckedException e) {
            System.out.println("Caught unchecked exception: " + e.getMessage());
        }
    }
}
```

# Can there be multiple catch blocks? Write code to explain.
Yes, in Java, **there can be multiple catch blocks** to handle different types of exceptions separately. Each catch block handles a specific exception type, and Java will execute **only the first matching catch block** when an exception occurs.
```java
public class MultipleCatchExample {
    public static void main(String[] args) {
        try {
            // Code that may throw multiple exceptions
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[5]);  // This will throw ArrayIndexOutOfBoundsException

            int result = 10 / 0;             // This will throw ArithmeticException (if reached)
        } 
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        } 
        catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: " + e.getMessage());
        } 
        catch (Exception e) {
            System.out.println("Caught a general exception: " + e.getMessage());
        }

        System.out.println("Program continues after try-catch blocks.");
    }
}
```

# When both catch and finally return values, what will be the final result?
In Java, when both the catch and finally blocks contain return statements, **the finally block’s return value will override** the return value from the catch (or even try) block.

```java
public class Test {
    public static int testMethod() {
        try {
            int a = 5 / 0; // This will throw ArithmeticException
            return 1;
        } catch (ArithmeticException e) {
            System.out.println("In catch block");
            return 2;
        } finally {
            System.out.println("In finally block");
            return 3;
        }
    }

    public static void main(String[] args) {
        System.out.println("Returned: " + testMethod());
    }
}
```

# What is the difference between throw and throws?
## Explanation

| Feature        | `throw`                            | `throws`                                 |
|----------------|-------------------------------------|-------------------------------------------|
| Purpose        | To **actually throw** an exception  | To **declare** potential exceptions       |
| Usage place    | Inside a method/block               | In the method signature                   |
| Follows        | An exception **object**             | One or more **exception classes**         |
| Number allowed | Only one                            | Multiple, separated by commas             |

## Java Code demo: Throw vs Throws
### Throw
```java
public void setAge(int age) {
    if (age < 0) {
        throw new IllegalArgumentException("Age cannot be negative.");
    }
    this.age = age;
}
```
### Throws
```java
public void readFile(String fileName) throws IOException {
    FileReader reader = new FileReader(fileName);
    // reading logic...
}
```

# Run the below three pieces codes, Noticed the printed exceptions. why do we put the Null/Runtime exception before Exception?
In Java, **catch blocks are checked in order from top to bottom**. Once a matching exception is found, the rest of the catch blocks are skipped.

Since NullPointerException and RuntimeException are **subclasses of Exception**, if you catch Exception first, then all the more specific exceptions like NullPointerException will never be reached.

	•	ArithmeticException and NullPointerException are more specific.
	•	RuntimeException is more general.
	•	Exception is the most general among them.

# What is optional? why do you use it? write an optional example to demo how it avoids NPE.
In Java, Optional is a container object introduced in Java 8 that may or may not contain a non-null value. It’s used to avoid NullPointerException (NPE) and to make the code more expressive and safer when dealing with potentially null values.
## Why Use Optional?
	•	To avoid null checks scattered throughout the code.
	•	To explicitly represent the presence or absence of a value.
	•	To encourage functional-style programming using methods like .map(), .flatMap(), .filter(), etc.
	•	To improve code readability and reduce bugs due to unexpected null values.
## Java Code Demo
```java
import java.util.Optional;

public class User {
    private String email;

    public User(String email) {
        this.email = email;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }
}
```
```java
import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        User user = new User(null); // or new User("user@example.com");

        Optional<String> emailOptional = user.getEmail();

        // Safe access using Optional
        emailOptional
            .map(String::toLowerCase)
            .ifPresent(System.out::println); // Prints only if email is not null
    }
}
```
## Summary
1. Optional<T> is a wrapper that may or may not contain a value of type T.
2. Helps avoid common NPEs by forcing the developer to explicitly handle the absence of a value.
3. Makes code cleaner, more readable, and less error-prone.

# What are the types of design patterns in Java ? Name popular design patters, particularly `Creational Patterns` and `Structural Patterns`
In Java, design patterns are standard solutions to common problems in software design.
## Types of Design Patterns
1. **Creational Patterns** – Deal with object creation mechanisms.  
2. **Structural Patterns** – Deal with class and object composition.  
3. **Behavioral Patterns** – Deal with communication between objects.
### Creational Design Patterns
| Pattern           | Description                                                                 |
|------------------|-----------------------------------------------------------------------------|
| **Singleton**     | Ensures only one instance of a class exists and provides a global access point to it. |
| **Factory Method**| Defines an interface for creating an object, but lets subclasses alter the type of objects that will be created. |
| **Abstract Factory** | Creates families of related or dependent objects without specifying their concrete classes. |
| **Builder**        | Separates the construction of a complex object from its representation.    |
| **Prototype**      | Creates new objects by copying an existing object (clone).                 |
### Structural Design Patterns
| Pattern           | Description                                                                 |
|------------------|-----------------------------------------------------------------------------|
| **Adapter**        | Allows incompatible interfaces to work together by acting as a bridge.     |
| **Decorator**      | Adds responsibilities to an object dynamically without changing its structure. |
| **Facade**         | Provides a simplified interface to a complex subsystem.                   |
| **Proxy**          | Provides a placeholder or surrogate to control access to another object.  |
| **Composite**      | Composes objects into tree structures to represent part-whole hierarchies. |
| **Bridge**         | Decouples abstraction from its implementation so that the two can vary independently. |
| **Flyweight**      | Reduces memory usage by sharing common data between similar objects.       |

# Implement `Singleton`, `Factory`, and `Builder` patterns, explain how to guarantee thread-safe in your `singleton` pattern implementation.
## `Singleton` Pattern
```java
public class Singleton {
    // volatile ensures changes are visible to all threads
    private static volatile Singleton instance;

    // private constructor to prevent instantiation
    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                // Double-check
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}
```
#### How it’s Thread-Safe
1. `volatile` ensures visibility and prevents instruction reordering.
2. `synchronized` block ensures only one thread can initialize the instance.
3. `Double-checked locking` avoids unnecessary locking after the instance is initialized.

## `Factory` Pattern
```java
// Product Interface
interface Shape {
    void draw();
}

// Concrete Products
class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class Square implements Shape {
    public void draw() {
        System.out.println("Drawing Square");
    }
}

// Factory Class
class ShapeFactory {
    public Shape getShape(String shapeType) {
        if (shapeType == null) return null;
        if (shapeType.equalsIgnoreCase("CIRCLE")) return new Circle();
        if (shapeType.equalsIgnoreCase("SQUARE")) return new Square();
        return null;
    }
}
```

## `Builder` Pattern
```java
// Product
class Computer {
    private String CPU;
    private String RAM;
    private boolean hasGraphicsCard;

    // private constructor
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.hasGraphicsCard = builder.hasGraphicsCard;
    }

    public static class Builder {
        private String CPU;
        private String RAM;
        private boolean hasGraphicsCard;

        public Builder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }

        public Builder setGraphicsCard(boolean hasGraphicsCard) {
            this.hasGraphicsCard = hasGraphicsCard;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }

    public void printSpecs() {
        System.out.println("CPU: " + CPU + ", RAM: " + RAM + ", Graphics: " + hasGraphicsCard);
    }
}
```

# Explain `SOLID` Principles ? Further explain `Open-Closed Principle (OCP)`?
**SOLID** is an acronym that represents five core design principles that help create well-structured, maintainable, and scalable software.
## SOLID Principles Overview

1. **S — Single Responsibility Principle (SRP):**  
   A class should have only one reason to change — it should do one thing and do it well.  
   Keeps code modular and easier to test/maintain.

2. **O — Open-Closed Principle (OCP):**  
   Software entities (classes, modules, functions) should be open for extension but closed for modification.  
   You should be able to add new behavior without changing existing code.

3. **L — Liskov Substitution Principle (LSP):**  
   Subtypes must be substitutable for their base types without altering the correctness of the program.  
   Derived classes should enhance, not break, the base class functionality.

4. **I — Interface Segregation Principle (ISP):**  
   No client should be forced to depend on methods it does not use.  
   Break large interfaces into smaller, more specific ones.

5. **D — Dependency Inversion Principle (DIP):**  
   High-level modules should not depend on low-level modules. Both should depend on abstractions (e.g., interfaces).  
   Promotes loose coupling via interfaces and dependency injection.

---

## Open-Closed Principle (OCP) in Depth

### Definition:
- A class should be **open for extension** (i.e., its behavior can be extended) but **closed for modification** (i.e., you shouldn’t have to change its source code to change its behavior).

### Why It Matters:
- You avoid modifying tested, stable code when adding new features.  
- Encourages use of **polymorphism**, **inheritance**, and **composition**.

# Liskov’s substitution principle states that if class B is a subtype of class A, then object of type A may be substituted with any object of type B. 
## What does this actually mean? (from OA ) Choose your answer:

**1. ✅ It means that if the object of type A can do something, the object of type B could also be able to perform the same thing**  
2. It means that all the objects of type A could execute all the methods present in its subtype B  
3. It means if a method is present in class A, it should also be present in class B so that the object of type B could substitute object of type A  
4. It means that for the class B to inherit class A, objects of type B and objects of type A must be same

# Watch design pattern video as below.
1. ✅ singleton: https://www.bilibili.com/video/BV1Np4y1z7BU?p=22
2. ✅ Factory: https://www.bilibili.com/video/BV1Np4y1z7BU?p=35&vd_source=310561eab1216a27f7accf859bf7f6d9
3. ✅ Builder: https://www.bilibili.com/video/BV1Np4y1z7BU?p=50&vd_source=310561eab1216a27f7accf859bf7f6d9
4. ✅ Publisher_Subscriber: https://www.bilibili.com/video/BV1Np4y1z7BU?p=114&vd_source=310561eab1216a27f7accf859bf7f6d9