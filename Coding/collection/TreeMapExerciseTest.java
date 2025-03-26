import java.util.Map;
import java.util.TreeMap;

public class TreeMapExerciseTest {

    public static void main(String[] args) {
        learnInsertingAndRetrieving();
        learnRemoveReplacingUpdating();
    }

    // Insert, retrieve and check values from TreeMap
    public static void learnInsertingAndRetrieving() {
        TreeMap<String, Integer> fruitCounts = new TreeMap<>();

        // Add values
        fruitCounts.put("banana", 2);
        fruitCounts.put("apple", 3);
        fruitCounts.put("cherry", 5);

        // Put if absent
        fruitCounts.putIfAbsent("banana", 10); // won't overwrite
        fruitCounts.putIfAbsent("date", 4);    // added

        // Bulk put
        Map<String, Integer> more = Map.of("elderberry", 6, "fig", 7);
        fruitCounts.putAll(more);

        // Retrieve values
        System.out.println("apple count: " + fruitCounts.get("apple"));
        System.out.println("First key: " + fruitCounts.firstKey());
        System.out.println("Last key: " + fruitCounts.lastKey());

        // Check existence
        System.out.println("Contains key 'banana'? " + fruitCounts.containsKey("banana"));
        System.out.println("Contains value 6? " + fruitCounts.containsValue(6));

        // Print keys, values, and map
        System.out.println("Keys: " + fruitCounts.keySet());
        System.out.println("Values: " + fruitCounts.values());
        System.out.println("Is empty? " + fruitCounts.isEmpty());
        System.out.println("Fruit counts: " + fruitCounts);
    }

    // Remove and replace values in TreeMap
    public static void learnRemoveReplacingUpdating() {
        TreeMap<String, Integer> fruitCounts = new TreeMap<>(Map.of(
                "apple", 3,
                "banana", 2,
                "cherry", 5
        ));

        // Replace
        fruitCounts.replace("banana", 4); // overwrite directly
        fruitCounts.replace("cherry", 5, 6); // conditional replace

        // Remove
        fruitCounts.remove("apple");
        fruitCounts.remove("banana", 10); // won't remove
        fruitCounts.remove("banana", 4);  // successful remove

        // Print final state
        System.out.println("Updated fruit counts: " + fruitCounts);
    }
}
