import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapExerciseTest {

    public static void main(String[] args) {
        learnInsertingAndRetrieving();
        learnRemoveReplacingUpdating();
    }

    // Insert and retrieve values in LinkedHashMap
    public static void learnInsertingAndRetrieving() {
        LinkedHashMap<String, Integer> fruitCounts = new LinkedHashMap<>();

        // Add elements
        fruitCounts.put("apple", 3);
        fruitCounts.put("banana", 2);
        fruitCounts.put("cherry", 5);

        // putIfAbsent
        fruitCounts.putIfAbsent("banana", 10); // no overwrite
        fruitCounts.putIfAbsent("date", 4);    // added

        // Bulk put
        Map<String, Integer> moreFruits = Map.of(
                "elderberry", 6,
                "fig", 7
        );
        fruitCounts.putAll(moreFruits);

        // Get value
        System.out.println("apple count: " + fruitCounts.get("apple"));
        System.out.println("grape count (default 0): " + fruitCounts.getOrDefault("grape", 0));

        // Check keys/values
        System.out.println("Contains key 'banana'? " + fruitCounts.containsKey("banana"));
        System.out.println("Contains value 6? " + fruitCounts.containsValue(6));

        // Print keys, values and map
        System.out.println("Keys: " + fruitCounts.keySet());
        System.out.println("Values: " + fruitCounts.values());
        System.out.println("Is empty? " + fruitCounts.isEmpty());
        System.out.println("Fruit counts: " + fruitCounts);
    }

    // Replace, update and remove values
    public static void learnRemoveReplacingUpdating() {
        LinkedHashMap<String, Integer> fruitCounts = new LinkedHashMap<>(Map.of(
                "apple", 3,
                "banana", 2,
                "cherry", 5
        ));

        // Replace value
        fruitCounts.replace("banana", 4);
        fruitCounts.replace("cherry", 5, 6); // only if current value is 5

        // Remove by key and by key-value
        fruitCounts.remove("apple");
        fruitCounts.remove("banana", 10); // fails
        fruitCounts.remove("banana", 4);  // succeeds

        // compute, computeIfAbsent, computeIfPresent
        fruitCounts.compute("date", (k, v) -> (v == null) ? 1 : v + 1);
        fruitCounts.computeIfAbsent("elderberry", k -> 10);
        fruitCounts.computeIfPresent("cherry", (k, v) -> v * 2); // 6 -> 12

        // Print final state
        System.out.println("Updated fruit counts: " + fruitCounts);
    }
}
