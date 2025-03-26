import java.util.Set;
import java.util.TreeSet;

public class TreeSetExerciseTest {

    public static void main(String[] args) {
        learnInsertingRetrievingRemoving();
    }

    // Insert, retrieve, and remove elements from a TreeSet
    public static void learnInsertingRetrievingRemoving() {
        TreeSet<String> fruits = new TreeSet<>();

        // Add elements
        fruits.add("banana");
        fruits.add("apple");
        fruits.add("cherry");

        // Add duplicate
        fruits.add("apple"); // no effect

        // Add all
        Set<String> moreFruits = Set.of("date", "elderberry", "fig");
        fruits.addAll(moreFruits);

        // Contains check
        System.out.println("Contains 'banana'? " + fruits.contains("banana"));
        System.out.println("Contains 'grape'? " + fruits.contains("grape"));

        // Get first and last
        System.out.println("First: " + fruits.first());
        System.out.println("Last: " + fruits.last());

        // Subsets
        System.out.println("SubSet (banana to fig): " + fruits.subSet("banana", "fig")); // exclude fig
        System.out.println("HeadSet (before date): " + fruits.headSet("date")); // exclude date
        System.out.println("TailSet (from cherry): " + fruits.tailSet("cherry")); // include cherry

        // Remove element
        fruits.remove("banana");

        // Size and empty check
        System.out.println("Size: " + fruits.size());
        System.out.println("Is empty? " + fruits.isEmpty());

        // Print full set
        System.out.println("Fruits in TreeSet: " + fruits);
    }
}
