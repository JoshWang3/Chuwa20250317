import java.time.DayOfWeek;
import java.util.EnumMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AdditionalMapExerciseTest {

    public static void main(String[] args) {
        learnConcurrentHashMap();
        learnIdentityHashMap();
        learnEnumMap();
    }

    // Demonstrate ConcurrentHashMap usage
    public static void learnConcurrentHashMap() {
        ConcurrentHashMap<String, Integer> fruitCounts = new ConcurrentHashMap<>();

        fruitCounts.put("apple", 3);
        fruitCounts.put("banana", 2);
        fruitCounts.putIfAbsent("banana", 5);
        fruitCounts.putIfAbsent("cherry", 4);

        Map<String, Integer> more = Map.of("date", 6, "elderberry", 7);
        fruitCounts.putAll(more);

        System.out.println("ConcurrentHashMap: " + fruitCounts);
    }

    // Demonstrate IdentityHashMap usage
    public static void learnIdentityHashMap() {
        IdentityHashMap<String, Integer> map = new IdentityHashMap<>();

        // These two keys have same value, but are different objects
        String key1 = new String("apple");
        String key2 = new String("apple");

        map.put(key1, 1);
        map.put(key2, 2); // will be treated as a different key

        System.out.println("IdentityHashMap: " + map);
        System.out.println("Size of IdentityHashMap: " + map.size()); // expected: 2
    }

    // Demonstrate EnumMap usage
    public static void learnEnumMap() {
        EnumMap<DayOfWeek, String> schedule = new EnumMap<>(DayOfWeek.class);

        schedule.put(DayOfWeek.MONDAY, "Math");
        schedule.putIfAbsent(DayOfWeek.TUESDAY, "Physics");
        schedule.put(DayOfWeek.FRIDAY, "English");

        System.out.println("EnumMap: " + schedule);
    }
}
