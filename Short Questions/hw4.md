1. Learn Java generics by reading and practicing following code:
https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t01_basic/generic
2. Read the follwoing code repo and type it one by one by yourself.
https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/features
3. Practice following stream API exercises at least 3 times
https://github.com/gavinklfong/stream-api-exercises/blob/main/src/test/java/space/gavinklfong/demo/streamapi/StreamApiTest.java
4. Practice Optional methods at least 2 times
https://github.com/CTYue/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/exercise/ShoppingCartUtil.java
5. Discuss best practices on nullptr exception prevention, provide code snippet for each practice that you mentioned.
use Optional:
public Optional<String> getUsernameById(int id) {
    return Optional.ofNullable(findUserById(id)).map(User::getUsername);
}
use @NonNull, @Nullable:
public void printName(@NotNull String name) {
    System.out.println(name);
}
6. Discuss Java 8 new features with code snippet.
1. Default interface method:
interface MyInterface {
    void printNum(Integer num);
    default void sayNum() {
        System.out.println("Hello from MyInterface!");
    }
}
class MyClass implements MyInterface {
    public void printNum(Integer num) {
        System.out.println("num: " + num);
    }
}
public class Main {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        obj.printNum(8);         // Output: num: 8
        obj.sayNum();            // Output: Hello from MyInterface!
    }
}
2. Lambda:
public class LambdaExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        names.forEach(name -> System.out.println(name));
    }
}
3. Stream Api:
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Anna");
        // Using Stream API to filter names that start with 'A' and collect them into a new list
        List<String> namesStartingWithA = names.stream()
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println(namesStartingWithA); // Output: [Alice, Anna]
    }
}
4. Optional:
String name = Optional.ofNullable(null).orElse("A");
System.out.println(name);  // Output: A
7. What are the advantages of the Optional class?
Avoid NullPointerException
8. Explain Functional Interface and Lambda with code samples.
A Functional Interface is an interface that contains exactly one abstract method. These are used as the target types for lambda expressions and method references.
@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}
public class LambdaExample {
    public static void main(String[] args) {
        MathOperation addition = (a, b) -> a + b;
        MathOperation multiplication = (a, b) -> a * b;

        System.out.println(addition.operate(1,2));      // 3
        System.out.println(multiplication.operate(1,2)); // 2
    }
}
9. Explain Method Reference with code samples?
A method reference is a shorthand syntax for invoking a method using a reference (instead of a lambda expression). It’s particularly useful when the method is already defined and doesn’t need any additional logic in the lambda.
import java.util.Arrays;
import java.util.List;

class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    public void printName() {
        System.out.println(name);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(new Person("Alice"), new Person("Bob"));

        // Lambda
        people.forEach(person -> person.printName());

        // Method Reference
        people.forEach(Person::printName);
    }
}
10. Explain "Lambda can use unchanged variable outside of lambda", with code snippet.
Lambda expressions can access and use variables from the outer scope (local variables), but the variables must be effectively final, which means the variables don't change after they are initialized (even if they are not declared final).
public class LambdaExample {
    public static void main(String[] args) {
        int multiplier = 3;  // effectively final

        Runnable task = () -> {
            System.out.println("Multiplier is: " + multiplier);
        };

        task.run();  // Output: Multiplier is: 3
    }
}
11. Can a functional interface extend/inherit another interface?
Yes, a functional interface in Java can extend another interface. However, it must still only declare one abstract method (in total across all interfaces it inherits) to remain a functional interface.
12. What are Intermediate and Terminal operations?
Intermediate operations: filter(), map(), distinct(), sorted(), limit(), skip(), peek().
Intermediate operations transform a stream and return a new stream, allowing chaining of multiple operations. They are lazy, meaning they are not executed until a terminal operation is invoked.
Terminal operations: forEach(), collect(), reduce(), count(), anyMatch(), findAny(), toArray().
Terminal operations produce a result or side effect and trigger the processing of the stream pipeline. Once a terminal operation is called, the stream pipeline is closed and cannot be reused.
13. Demontrate the most commonly used Intermediate operations in Stream API, with code snippet.
1.filter():
Filters elements that match a condition.
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Alice", "Eve");
List<String> filtered = names.stream()
    .filter(name -> name.startsWith("A"))
    .collect(Collectors.toList());
System.out.println(filtered); // [Alice, Alice]
2.map():
Transforms each element in the stream.
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Alice", "Eve");
List<Integer> lengths = names.stream()
    .map(String::length)
    .collect(Collectors.toList());
System.out.println(lengths); // [5, 3, 7, 5, 5, 3]
3,distinct():
Removes duplicate elements.
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Alice", "Eve");
List<String> unique = names.stream()
    .distinct()
    .collect(Collectors.toList());
System.out.println(unique); // [Alice, Bob, Charlie, David, Eve]
4.sorted():
Sorts the stream in natural or custom order.
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Alice", "Eve");
List<String> reverseSorted = names.stream()
    .sorted(Comparator.reverseOrder())
    .collect(Collectors.toList());
System.out.println(reverseSorted); // [Eve, David, Charlie, Bob, Alice, Alice]
5.limit():
Truncates the stream to the first n elements.
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Alice", "Eve");
List<String> limited = names.stream()
    .limit(3)
    .collect(Collectors.toList());
System.out.println(limited); // [Alice, Bob, Charlie]
6.skip():
Skips the first n elements.
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Alice", "Eve");
List<String> skipped = names.stream()
    .skip(2)
    .collect(Collectors.toList());
System.out.println(skipped); // [Charlie, David, Alice, Eve]
7.peek():
Performs an action on each element (useful for debugging).
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Alice", "Eve");
List<String> peeked = names.stream()
    .peek(name -> System.out.println("Processing: " + name))
    .map(String::toUpperCase)
    .collect(Collectors.toList());
System.out.println(peeked); // [ALICE, BOB, CHARLIE, DAVID, ALICE, EVE]
14. How are Collections different from Stream?
Collections: data structure that holds and stores data in memory, external iteration, data is static, adding/ removing elements change the collection, operations happen immediately.
Stream: sequence of data operations that process elements from a Collection without modifying it, not store data, only pulls data from source and processes it on-demand. internal iteration. data is dynamically. Operations are not executed until a terminal operation is called.
15. Implement Stream API's filter and map methods by your self.
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyStream<T> {
    private List<T> list;

    public MyStream(List<T> list) {
        this.list = list;
    }

    // Custom filter method
    public MyStream<T> filter(Predicate<T> predicate) {
        List<T> filteredList = new ArrayList<>();
        for (T element : list) {
            if (predicate.test(element)) {
                filteredList.add(element);
            }
        }
        return new MyStream<>(filteredList);
    }

    // Custom map method
    public <R> MyStream<R> map(Function<T, R> mapper) {
        List<R> mappedList = new ArrayList<>();
        for (T element : list) {
            mappedList.add(mapper.apply(element));
        }
        return new MyStream<>(mappedList);
    }

    // Custom collect method (just returns the list)
    public List<T> collect() {
        return list;
    }
}
import java.util.Arrays;
import java.util.List;

public class TestMyStream {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        List<String> result = new MyStream<>(names)
                .filter(name -> name.startsWith("A"))
                .map(name -> name.toUpperCase())
                .collect();

        System.out.println(result); // Output: [ALICE]
    }
}