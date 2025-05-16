package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @author b1go
 * @date 6/12/22 4:48 PM
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
        List<Integer> numbers = Arrays.asList(5, 3, 9, 1, 3, 7, 3);

        // 使用默认自然排序获取最小值
        int minValue = Collections.min(numbers);
        assertEquals(1, minValue);

        // 使用默认自然排序获取最大值
        int maxValue = Collections.max(numbers);
        assertEquals(9, maxValue);

        // 使用 Comparator 获取最小值（反转比较器，获取最大值当作最小值）
        int reverseMin = Collections.min(numbers, Comparator.reverseOrder());
        assertEquals(9, reverseMin);

        // 统计某个元素出现的次数
        int freqOf3 = Collections.frequency(numbers, 3);
        assertEquals(3, freqOf3);
    }

    /**
     * synchronizedList()
     */

    @Test
    public void learn_thread_safe_ArrayList() {
        List<String> unsafeList = new ArrayList<>();
        unsafeList.add("apple");
        unsafeList.add("banana");

        // 将普通 List 包装成线程安全 List
        List<String> safeList = Collections.synchronizedList(unsafeList);

        synchronized (safeList) {
            // 同步块中进行遍历等复合操作是线程安全的
            for (String item : safeList) {
                System.out.println(item);
            }
        }

        // 简单 add 操作在 synchronizedList 中已经是线程安全的
        safeList.add("cherry");

        assertEquals(3, safeList.size());
    }
}
