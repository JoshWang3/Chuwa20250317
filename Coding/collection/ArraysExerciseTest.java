import java.util.Arrays;
import java.util.List;

public class ArraysExerciseTest {

    public static void main(String[] args) {
        learnInsertingAndRetrieving();
        learnSearchAndSort();
        learnCopyOfArray();
        learnCommonOperations();
    }

    // Insert and retrieve elements in an array
    public static void learnInsertingAndRetrieving() {
        int[] numbers = {1, 2, 3, 4, 5};

        // Access and update
        System.out.println("Element at index 2: " + numbers[2]); // 3

        numbers[2] = 99;
        System.out.println("Updated array: " + Arrays.toString(numbers)); // [1, 2, 99, 4, 5]
    }

    // Binary search and sorting
    public static void learnSearchAndSort() {
        int[] numbers = {5, 3, 1, 4, 2};

        // Sort the array
        Arrays.sort(numbers);
        System.out.println("Sorted array: " + Arrays.toString(numbers)); // [1, 2, 3, 4, 5]

        // Binary search (must be sorted)
        int index = Arrays.binarySearch(numbers, 4);
        System.out.println("Index of 4: " + index); // 3

        // Parallel sort (on larger arrays it’s faster)
        int[] nums = {10, 8, 6, 9, 7};
        Arrays.parallelSort(nums);
        System.out.println("Parallel sorted: " + Arrays.toString(nums)); // [6, 7, 8, 9, 10]
    }

    // Copy arrays using Arrays utility methods
    public static void learnCopyOfArray() {
        int[] original = {1, 2, 3, 4, 5};

        int[] copy = Arrays.copyOf(original, original.length);
        int[] range = Arrays.copyOfRange(original, 1, 4); // [2, 3, 4]

        System.out.println("Copied array: " + Arrays.toString(copy));
        System.out.println("Copied range: " + Arrays.toString(range));
    }

    // Common operations: equals, fill, asList
    public static void learnCommonOperations() {
        int[] numbers1 = {1, 2, 3};
        int[] numbers2 = {1, 2, 3};

        System.out.println("Are arrays equal? " + Arrays.equals(numbers1, numbers2)); // true

        // Fill array
        Arrays.fill(numbers2, 9);
        System.out.println("Filled array: " + Arrays.toString(numbers2)); // [9, 9, 9]

        // asList (for object types, not primitives)
        String[] fruits = {"apple", "banana", "cherry"};
        List<String> fruitList = Arrays.asList(fruits);
        System.out.println("Fruit list: " + fruitList); // [apple, banana, cherry]
    }
}
