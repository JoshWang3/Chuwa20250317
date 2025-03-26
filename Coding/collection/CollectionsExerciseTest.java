import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsExerciseTest {

    public static void main(String[] args) {
        learnCommonCollectionsOperations();
        learnThreadSafeArrayList();
    }

    // Common operations like min, max, frequency
    public static void learnCommonCollectionsOperations() {
        List<String> fruits = new ArrayList<>();
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("cherry");
        fruits.add("banana");
        fruits.add("date");

        // Find min and max
        String minFruit = Collections.min(fruits); // natural order
        String maxFruit = Collections.max(fruits); // natural order

        // Custom comparator (reverse order)
        String minByLength = Collections.min(fruits, Comparator.comparingInt(String::length));
        String maxByLength = Collections.max(fruits, Comparator.comparingInt(String::length));

        // Frequency
        int bananaCount = Collections.frequency(fruits, "banana");

        // Print results
        System.out.println("Fruits: " + fruits);
        System.out.println("Min: " + minFruit);
        System.out.println("Max: " + maxFruit);
        System.out.println("Min (by length): " + minByLength);
        System.out.println("Max (by length): " + maxByLength);
        System.out.println("Frequency of 'banana': " + bananaCount);
    }

    // Making a thread-safe version of an ArrayList
    public static void learnThreadSafeArrayList() {
        List<String> unsafeList = new ArrayList<>();
        unsafeList.add("apple");
        unsafeList.add("banana");

        List<String> safeList = Collections.synchronizedList(unsafeList);

        // Access must be synchronized if using iterators in multi-threaded environment
        synchronized (safeList) {
            for (String fruit : safeList) {
                System.out.println("Safe list item: " + fruit);
            }
        }
    }
}
