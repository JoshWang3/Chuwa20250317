import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LinkedListExerciseTest {

    public static void main(String[] args) {
        learnInsertingAndRetrieving();
        learnRemoveAndSort();
    }

    // Insert and retrieve fruits using LinkedList
    public static void learnInsertingAndRetrieving() {
        LinkedList<String> fruits = new LinkedList<>();

        // Add elements
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("cherry");

        // Add to beginning and specific index
        fruits.addFirst("avocado");
        fruits.add(2, "blueberry");

        // Add another list
        List<String> moreFruits = List.of("date", "elderberry");
        fruits.addAll(moreFruits);

        // Print list
        System.out.println("Fruits: " + fruits);

        // Retrieve elements
        System.out.println("First fruit: " + fruits.getFirst());
        System.out.println("Last fruit: " + fruits.getLast());
        System.out.println("Fruit at index 3: " + fruits.get(3));
    }

    // Remove and sort fruits in LinkedList
    public static void learnRemoveAndSort() {
        LinkedList<String> fruits = new LinkedList<>(List.of(
                "banana", "apple", "cherry", "banana", "date"
        ));

        // Remove by index and value
        fruits.remove(1); // remove second element
        fruits.remove("banana"); // remove first "banana"
        fruits.removeFirst();
        fruits.removeLast();

        // Add more and sort
        fruits.add("fig");
        fruits.add("grape");
        Collections.sort(fruits);

        // Print result
        System.out.println("Modified and sorted fruits: " + fruits);
    }
}
