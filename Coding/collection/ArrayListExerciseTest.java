import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ArrayListExerciseTest {

    public static void main(String[] args) {
        learnInsertingAndRetrieving();
        learnRemoveReplacingUpdating();
        learnIterator();
        learnSorting();
    }

    // Create list, add elements, get element, size, and addAll
    public static void learnInsertingAndRetrieving() {
        List<String> fruits = new ArrayList<>();
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("cherry");

        System.out.println("First fruit: " + fruits.get(0));
        System.out.println("Size: " + fruits.size());

        List<String> moreFruits = new ArrayList<>();
        moreFruits.add("date");
        moreFruits.add("elderberry");

        fruits.addAll(moreFruits);
        System.out.println("All fruits: " + fruits);
    }

    // Remove elements and update values
    public static void learnRemoveReplacingUpdating() {
        List<String> fruits = new ArrayList<>(List.of("apple", "banana", "cherry", "banana"));

        fruits.remove(1); // remove by index
        fruits.remove("banana"); // remove by value
        fruits.set(0, "avocado"); // update element

        System.out.println("Modified list: " + fruits);
    }

    // Use iterator to traverse the list
    public static void learnIterator() {
        List<String> fruits = new ArrayList<>(List.of("apple", "banana", "cherry"));
        Iterator<String> iterator = fruits.iterator();

        while (iterator.hasNext()) {
            String fruit = iterator.next();
            System.out.println("Iterating: " + fruit);
        }
    }

    // Sort list in ascending and descending order
    public static void learnSorting() {
        List<String> fruits = new ArrayList<>(List.of("banana", "cherry", "apple"));

        Collections.sort(fruits);
        System.out.println("Sorted ascending: " + fruits);

        fruits.sort(Comparator.reverseOrder());
        System.out.println("Sorted descending: " + fruits);
    }
}
