# hw4
### 1. Learn Java generics by reading and practicing following code: https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t01_basic/generic
### 2. Read the follwoing code repo and type it one by one by yourself. https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/features
### 3. Practice following stream API exercises at least 3 times https://github.com/gavinklfong/stream-api-exercises/blob/main/src/test/java/space/gavinklfong/demo/streamapi/StreamApiTest.java
### 4. Practice Optional methods at least 2 times https://github.com/CTYue/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/exercise/ShoppingCartUtil.java
### 5. Discuss best practices on nullptr exception prevention, provide code snippet for each practice that you mentioned.
1. Use Java 8 `Optional` to avoid returning or accepting nulls
    ```java
    import java.util.Optional;

    public class UserService {
        public Optional<User> findUserById(String id) {
            // simulate finding user
            return Optional.ofNullable(null); // could be real user object
        }

        public void printUserName(String id) {
            findUserById(id).ifPresent(user -> System.out.println(user.getName()));
        }
    }
    ```
2. Use annotations (`@NonNull`, `@Nullable`)
    ```java
    import org.jetbrains.annotations.NotNull;

    public void printName(@NotNull String name) {
        System.out.println(name);
    }
    ```
3. Use default values when null is possible
    ```java
    public static String getUserName(User user) {
        return Optional.ofNullable(user)
                    .map(User::getName)
                    .orElse("Unknown");
    }
    ```
### 6. Discuss Java 8 new features with code snippet.
1. Default interface method  
    Interface:
    ```java
    interface MyInterface {
        void greet(String name);

        default void sayHello() {
            System.out.println("Hello from MyInterface!");
        }
    }
    ```
    Implementation:
    ```java
    class MyClass implements MyInterface {
        public void greet(String name) {
            System.out.println("Hi, " + name);
        }
    }

    public class Main {
        public static void main(String[] args) {
            MyClass obj = new MyClass();
            obj.greet("Zeliang");         // Output: Hi, Zeliang
            obj.sayHello();               // Output: Hello from MyInterface!
        }
    }
    ```
2. Lambda
    ```java
    Runnable r = () -> System.out.println("Hello from Lambda!");
    r.run(); // Output: Hello from Lambda!
    ```
3. Functional Interface
    ```java
    @FunctionalInterface
    interface MathOperation {
        int operate(int a, int b);
    }

    public class Main {
        public static void main(String[] args) {
            MathOperation add = (a, b) -> a + b;
            MathOperation multiply = (a, b) -> a * b;

            System.out.println(add.operate(3, 5));       // 8
            System.out.println(multiply.operate(3, 5));  // 15
        }
    }
    ```
4. Method reference
    ```java
    Function<Integer, String> intToString = String::valueOf;
    System.out.println(intToString.apply(123));  // "123"
    ```
5. Optional
    ```java
    String name = Optional.ofNullable(null).orElse("Guest");
    System.out.println(name);  // Output: Guest
    ```
6. Stream API
    ```java
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

    names.stream()
        .filter(name -> name.length() > 5)
        .forEach(System.out::println); // Charlie
    ```
### 7. What are the advantages of the Optional class?
1. Avoid `NullPointerException`
    ```java
    Optional<String> name = Optional.ofNullable(maybeNullName);
    name.ifPresent(n -> System.out.println(n));
    ```
2. Functional & fluent APIs
    ```java
    Optional<User> userOptional = Optional.ofNullable(new User("Zeliang", 25));
    String name = userOptional.map(User::getName).orElse("Guest");
    ```
3. Safe chaining and transformations
    ```java
    Optional<String> email = userOptional
        .flatMap(User::getContactInfo)
        .map(Contact::getEmail);
    ```
4. Improves code readability and intent
    ```java
    // Old way
    String name = user != null ? user.getName() : "Guest";

    // Modern way
    String name = Optional.ofNullable(user)
                        .map(User::getName)
                        .orElse("Guest");
    ```
5. Avoid overuse of defensive code. Reduces boilerplate null-checks and early returns.
    ```java
    Optional<String> country = Optional.ofNullable(user.getCountry());
    country.ifPresent(c -> System.out.println("Country: " + c));
    ```
### 8. Explain Functional Interface and Lambda with code samples.
A Functional Interface is an interface that contains exactly one abstract method. These are used as the target types for lambda expressions and method references.
```java
@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}

public class LambdaExample {
    public static void main(String[] args) {
        MathOperation addition = (a, b) -> a + b;
        MathOperation multiplication = (a, b) -> a * b;

        System.out.println(addition.operate(5, 3));      // 8
        System.out.println(multiplication.operate(5, 3)); // 15
    }
}
```
### 9. Explain Method Reference with code samples?
A method reference is a shorthand syntax for invoking a method using a reference (instead of a lambda expression). It’s particularly useful when the method is already defined and doesn’t need any additional logic in the lambda.
```java
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
```
### 10. Explain "Lambda can use unchanged variable outside of lambda", with code snippet.
Lambda expressions can access and use variables from the outer scope (local variables), but the variables must be effectively final, which means the variables don't change after they are initialized (even if they are not declared `final`).
```java
public class LambdaExample {
    public static void main(String[] args) {
        int multiplier = 5;  // effectively final

        Runnable task = () -> {
            System.out.println("Multiplier is: " + multiplier);
        };

        task.run();  // Output: Multiplier is: 5
    }
}
```
### 11. Can a functional interface extend/inherit another interface?
Yes, a functional interface in Java can extend another interface. However, it must still only declare one abstract method (in total across all interfaces it inherits) to remain a functional interface.
```java
@FunctionalInterface
interface A {
    void doSomething();
}

@FunctionalInterface
interface B extends A {
    // No additional abstract methods allowed here!
}
```
### 12. What are Intermediate and Terminal operations?
In Java's Stream API, operations are categorized into two types:
1. Intermediate operations  
    Intermediate operations transform a stream and return a new stream, allowing chaining of multiple operations. They are lazy, meaning they are not executed until a terminal operation is invoked.
    | Operation | Description |
    | --- | --- |
    | filter() | Select elements based on a condition |
    | map() | Transform each element |
    | distinct() | Remove duplicates |
    | sorted() | Sort elements |
    | limit() | Limit the number of elements |
    | skip() | Skip a number of elements |
    | peek() | Perform an action |
2. Terminal operations  
    Terminal operations produce a result or side effect and trigger the processing of the stream pipeline. Once a terminal operation is called, the stream pipeline is closed and cannot be reused.
    | Operation | Description |
    | --- | --- |
    | forEach() | Perform an action for each element (side effect) |
    | collect() | Gather the stream into a collection |
    | reduce() | Combine elements into a single result |
    | count() | Count the number of elements |
    | anyMatch() / allMatch() / noneMatch() | Match conditions |
    | findFirst() / findAny() | Find elements |
    | toArray() | Convert stream to array |
```java
List<String> names = Arrays.asList("Anna", "Bob", "Alice", "Alex");

List<String> result = names.stream()               // source
    .filter(n -> n.startsWith("A"))                // intermediate
    .map(String::toUpperCase)                      // intermediate
    .sorted()                                      // intermediate
    .collect(Collectors.toList());                 // terminal

System.out.println(result);  // Output: [ALEX, ALICE, ANNA]
```
### 13. Demontrate the most commonly used Intermediate operations in Stream API, with code snippet.
1. filter()  
    Filters elements that match a condition.
    ```java
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Alice", "Eve");
    List<String> filtered = names.stream()
        .filter(name -> name.startsWith("A"))
        .collect(Collectors.toList());
    System.out.println(filtered); // [Alice, Alice]
    ```
2. map()  
    Transforms each element in the stream.
    ```java
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Alice", "Eve");
    List<Integer> lengths = names.stream()
        .map(String::length)
        .collect(Collectors.toList());
    System.out.println(lengths); // [5, 3, 7, 5, 5, 3]
    ```
3. distinct()  
    Removes duplicate elements.
    ```java
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Alice", "Eve");
    List<String> unique = names.stream()
        .distinct()
        .collect(Collectors.toList());
    System.out.println(unique); // [Alice, Bob, Charlie, David, Eve]
    ```
4. sorted()  
    Sorts the stream in natural or custom order.
    ```java
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Alice", "Eve");
    List<String> reverseSorted = names.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());
    System.out.println(reverseSorted); // [Eve, David, Charlie, Bob, Alice, Alice]
    ```
5. limit()  
    Truncates the stream to the first n elements.
    ```java
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Alice", "Eve");
    List<String> limited = names.stream()
        .limit(3)
        .collect(Collectors.toList());
    System.out.println(limited); // [Alice, Bob, Charlie]
    ```
6. skip()  
    Skips the first n elements.
    ```java
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Alice", "Eve");
    List<String> skipped = names.stream()
        .skip(2)
        .collect(Collectors.toList());
    System.out.println(skipped); // [Charlie, David, Alice, Eve]
    ```
7. peek()  
    Performs an action on each element (useful for debugging).
    ```java
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Alice", "Eve");
    List<String> peeked = names.stream()
        .peek(name -> System.out.println("Processing: " + name))
        .map(String::toUpperCase)
        .collect(Collectors.toList());
    System.out.println(peeked); // [ALICE, BOB, CHARLIE, DAVID, ALICE, EVE]
    ```
### 14. How are Collections different from Stream?
| Feature | Collection | Stream |
| --- | --- | --- |
| Nature | Data structure | Data processing pipeline |
| Evaluation | Eager | Lazy |
| Storage | Stores elements | Does not store, just processes |
| Reusability | Reusable | Single-use only |
| Supports mutation | Yes | No (functional style) |
| Parallel processing | Manual | Built-in via parallelStream() |
| Side effects | Common | Discouraged, prefer pure functions |
### 15. Implement Stream API's `filter` and `map` methods by your self.
```java
import java.util.*;
import java.util.function.*;

class MyStream<T> {
    private List<T> source;

    public MyStream(List<T> source) {
        this.source = source;
    }

    public MyStream<T> filter(Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T element : source) {
            if (predicate.test(element)) {
                result.add(element);
            }
        }
        return new MyStream<>(result);
    }

    public <R> MyStream<R> map(Function<T, R> mapper) {
        List<R> result = new ArrayList<>();
        for (T element : source) {
            result.add(mapper.apply(element));
        }
        return new MyStream<>(result);
    }

    public List<T> collect() {
        return source;
    }
}

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        List<String> upperNames = new MyStream<>(names)
            .filter(name -> name.length() > 3)
            .map(String::toUpperCase)
            .collect();

        System.out.println(upperNames); // [ALICE, CHARLIE, DAVID]
    }
}
```