package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 5, 7, 8, 9, 10);

        System.out.println(Collections.min(list));          // 1

        System.out.println(Collections.max(list));          // 10

        System.out.println(Collections.frequency(list, 5)); // 2
    }

    /**
     * synchronizedList()
     */

    @Test
    public void learn_thread_safe_ArrayList() {
        List<Integer> list = Collections.synchronizedList(Arrays.asList(1, 2, 3, 4, 5, 5, 7, 8, 9, 10));

        synchronized (list) {
            for (Integer num : list) {
                System.out.println(num);    // 1 2 3 4 5 6 7 8 9 10
            }
        }
    }
}
