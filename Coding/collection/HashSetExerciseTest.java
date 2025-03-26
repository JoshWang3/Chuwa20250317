import java.util.HashSet;
import java.util.Set;

public class HashSetExerciseTest {

    public static void main(String[] args) {
        learnInsertingRetrievingRemoving();
    }

    // Insert, check, remove and clear HashSet
    public static void learnInsertingRetrievingRemoving() {
        Set<String> fruits = new HashSet<>();

        // Add elements
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("cherry");

        // Try to add duplicate
        fruits.add("apple"); // no effect

        // Add another collection
        Set<String> moreFruits = Set.of("date", "elderberry", "banana"); // "banana" is duplicate
        fruits.addAll(moreFruits);

        // Check if set contains an element
        System.out.println("Contains 'apple'? " + fruits.contains("apple"));
        System.out.println("Contains 'fig'? " + fruits.contains("fig"));

        // Remove an element
        fruits.remove("banana");

        // Check if empty
        System.out.println("Is empty? " + fruits.isEmpty());

        // Print set
        System.out.println("Fruits in set: " + fruits);

        // Clear all
        fruits.clear();
        System.out.println("After clear, is empty? " + fruits.isEmpty());
    }
}
