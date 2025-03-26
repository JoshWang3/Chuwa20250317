import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExerciseTest {

    public static void main(String[] args) {
        learnInsertingAndRetrieving();
        learnIterator();
    }

    // Insert and retrieve elements using CopyOnWriteArrayList
    public static void learnInsertingAndRetrieving() {
        CopyOnWriteArrayList<String> fruits = new CopyOnWriteArrayList<>();

        // Add elements
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("cherry");

        // Add at index
        fruits.add(1, "blueberry");

        // Add if absent
        fruits.addIfAbsent("date"); // will be added
        fruits.addIfAbsent("apple"); // will not be added again

        // Add all (duplicates will be added)
        List<String> moreFruits = List.of("elderberry", "fig");
        fruits.addAll(moreFruits);

        // Add all absent (only if not already present)
        fruits.addAllAbsent(List.of("grape", "banana")); // only grape will be added

        System.out.println("Fruits: " + fruits);
    }

    // Iterate through CopyOnWriteArrayList
    public static void learnIterator() {
        List<String> fruits = new CopyOnWriteArrayList<>(
                List.of("apple", "banana", "cherry")
        );

        Iterator<String> iterator = fruits.iterator();

        while (iterator.hasNext()) {
            String fruit = iterator.next();
            System.out.println("Iterating: " + fruit);
        }
    }
}
