import java.util.HashMap;
import java.util.Map;

public class HashMapExerciseTest {

    public static void main(String[] args) {
        learnInsertingAndRetrieving();
        learnRemoveReplacingUpdating();
    }

    // Insert and retrieve elements from HashMap
    public static void learnInsertingAndRetrieving() {
        Map<String, Integer> fruitCounts = new HashMap<>();

        // Add entries
        fruitCounts.put("apple", 3);
        fruitCounts.put("banana", 2);
        fruitCounts.put("cherry", 5);

        // Put if absent
        fruitCounts.putIfAbsent("banana", 10); // won't overwrite
        fruitCounts.putIfAbsent("date", 4);    // will be added

        // Bulk put
        Map<String, Integer> more = Map.of("elderberry", 6, "fig", 7);
        fruitCounts.putAll(more);

        // Retrieve values
        System.out.println("apple count: " + fruitCounts.get("apple"));
        System.out.println("grape count (default 0): " + fruitCounts.getOrDefault("grape", 0));

        // Check existence
        System.out.println("Contains key 'banana'? " + fruitCounts.containsKey("banana"));
        System.out.println("Contains value 6? " + fruitCounts.containsValue(6));

        // Print keys, values, map
        System.out.println("Keys: " + fruitCounts.keySet());
        System.out.println("Values: " + fruitCounts.values());
        System.out.println("Is empty? " + fruitCounts.isEmpty());
        System.out.println("Fruit counts: " + fruitCounts);
    }

    // Remove, replace, and update values in HashMap
    public static void learnRemoveReplacingUpdating() {
        Map<String, Integer> fruitCounts = new HashMap<>(Map.of(
                "apple", 3,
                "banana", 2,
                "cherry", 5
        ));

        // Replace values
        fruitCounts.replace("banana", 4); // overwrite unconditionally
        fruitCounts.replace("cherry", 5, 6); // only replace if value is 5

        // Remove by key and key-value
        fruitCounts.remove("apple");
        fruitCounts.remove("banana", 10); // fails
        fruitCounts.remove("banana", 4);  // succeeds

        // Compute
        fruitCounts.compute("date", (k, v) -> (v == null) ? 1 : v + 1);
        fruitCounts.computeIfAbsent("elderberry", k -> 10);
        fruitCounts.computeIfPresent("cherry", (k, v) -> v * 2); // 6 -> 12

        // Print final map
        System.out.println("Updated fruit counts: " + fruitCounts);
    }
}
