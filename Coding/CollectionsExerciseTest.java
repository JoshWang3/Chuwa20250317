import org.junit.Test;

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
        List<Integer> l = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(Collections.min(l));
        System.out.println(Collections.max(l));
        System.out.println(Collections.frequency(l, Integer.valueOf(5)));
    }

    /**
     * synchronizedList()
     */

    @Test
    public void learn_thread_safe_ArrayList() {
        List<Integer> l = Collections.synchronizedList(new ArrayList<>());
        l.add(1);
        l.add(2);
        System.out.println(l);
    }
}
