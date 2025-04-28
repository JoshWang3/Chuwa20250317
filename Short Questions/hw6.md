## 5. Best Practices for NullPointerException Prevention

### Using Optional
```java
Optional<String> value = Optional.ofNullable(someString);
value.ifPresent(System.out::println);
```

### Avoid Returning Null
```java
public Optional<String> getName() {
    return Optional.ofNullable(name);
}
```

### Use Objects.requireNonNull()
```java
String name = Objects.requireNonNull(input, "Name cannot be null");
```

### Use Default Values with Optional
```java
String result = Optional.ofNullable(input).orElse("Default");
```

### Use isPresent() for Safe Access
```java
if(optional.isPresent()) {
    System.out.println(optional.get());
}
```
---

## 6. Java 8 New Features

### Lambda Expressions
```java
Runnable r = () -> System.out.println("Hello, Lambda!");
r.run();
```
### Stream API
```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.stream()
    .filter(name -> name.startsWith("A"))
    .forEach(System.out::println);
```

### Optional Class
```java
Optional<String> value = Optional.ofNullable(null);
System.out.println(value.orElse("Default"));
```

### Method References
```java
List<String> names = Arrays.asList("Alice", "Bob");
names.forEach(System.out::println);
```

### Default and Static Methods in Interfaces
```java
interface MyInterface {
    default void printMessage() {
        System.out.println("Hello from default method");
    }

    static void printStatic() {
        System.out.println("Hello from static method");
    }
}
```
### Functional Interfaces
```java
@FunctionalInterface
interface MyFunctionalInterface {
    void execute();
}

MyFunctionalInterface func = () -> System.out.println("Executing...");
func.execute();
```

### Stream Enhancements
```java
IntStream.range(1, 5).forEach(System.out::println);
```

### CompletableFuture
```java
CompletableFuture.supplyAsync(() -> "Hello, World!")
    .thenAccept(System.out::println);
```

---

## 7. Advantages of the Optional Class

- Eliminates NullPointerExceptions
- Improves Code Readability
- Avoids Explicit Null Checks
- Functional Style Handling
- Provides Clear Intent
- Built-in Methods for Safe Handling

---

## 8. Functional Interface and Lambda

### Functional Interface
A **functional interface** in Java is an interface that contains exactly one abstract method. It can have multiple default and static methods but must have only one abstract method. Functional interfaces can be represented using lambda expressions, method references, or anonymous classes.

### Lambda Expressions
Lambda expressions provide a clear and concise way to implement functional interfaces. They eliminate the need for anonymous class implementations.

```java
@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}

public class LambdaExample {
    public static void main(String[] args) {
        Calculator addition = (a, b) -> a + b;
        int result = addition.add(5, 3);
        System.out.println("Sum: " + result);
    }
}
```

---

## 9. Method Reference in Java

Method references are a shorthand notation of a lambda expression that executes a method. They make code more readable and concise. 

### Types of Method References

1. **Reference to a Static Method**
2. **Reference to an Instance Method of a Particular Object**
3. **Reference to an Instance Method of an Arbitrary Object of a Particular Type**
4. **Reference to a Constructor**

---

### 1. Reference to a Static Method
**Example:**
```java
import java.util.function.Consumer;

public class StaticMethodRef {
    public static void printMessage(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        Consumer<String> consumer = StaticMethodRef::printMessage;
        consumer.accept("Hello, Static Method Reference!");
    }
}
```

---

### 2. Reference to an Instance Method of a Particular Object
**Example:**
```java
public class InstanceMethodRef {
    public void display(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        InstanceMethodRef ref = new InstanceMethodRef();
        Consumer<String> consumer = ref::display;
        consumer.accept("Hello, Instance Method Reference!");
    }
}
```

---

### 3. Reference to an Instance Method of an Arbitrary Object of a Particular Type
**Example:**
```java
import java.util.Arrays;

public class ArbitraryObjectMethodRef {
    public static void main(String[] args) {
        String[] names = {"Alice", "Bob", "Charlie"};
        Arrays.sort(names, String::compareToIgnoreCase);
        for (String name : names) {
            System.out.println(name);
        }
    }
}
```

---

### 4. Reference to a Constructor
**Example:**
```java
import java.util.function.Supplier;

public class ConstructorMethodRef {
    public ConstructorMethodRef() {
        System.out.println("Constructor invoked!");
    }

    public static void main(String[] args) {
        Supplier<ConstructorMethodRef> supplier = ConstructorMethodRef::new;
        supplier.get();
    }
}
```

---

## 10. Lambda Can Use Unchanged Variable Outside of Lambda

In Java, lambda expressions can use variables from their enclosing scope. However, these variables must be **effectively final**. This means that the variable cannot be modified after it has been initialized.

---

### Example 1: Valid Usage of an Unchanged Variable
```java
public class LambdaExample {
    public static void main(String[] args) {
        int number = 5;

        Runnable r = () -> System.out.println("Number: " + number);
        r.run();
    }
}
```

---

### Example 2: Invalid Usage of a Changed Variable
```java
public class LambdaExample {
    public static void main(String[] args) {
        int number = 5;

        number = 10;

        Runnable r = () -> System.out.println("Number: " + number); // Compilation Error
        r.run();
    }
}
```

---

### Example 3: Using Final Keyword
```java
public class LambdaExample {
    public static void main(String[] args) {
        final int number = 42;

        Runnable r = () -> System.out.println("Final Number: " + number);
        r.run();
    }
}
```

---

## 11. Can a Functional Interface Extend/Inherit Another Interface?

### Yes, a Functional Interface can extend another interface in Java, provided that:
- The extended interface itself must be a functional interface or have only one abstract method.
- The resulting interface must still contain exactly one abstract method to qualify as a functional interface.

---

## 12. Intermediate and Terminal Operations in Stream API

### 1. Intermediate Operations
- These operations return a new stream.
- They are **lazy**, meaning they are not executed until a terminal operation is invoked.
- Can be **chained** together to form a pipeline.
- Examples: `map()`, `filter()`, `sorted()`, `distinct()`, `limit()`, `skip()`

### 2. Terminal Operations
- These operations **produce a result** or **a side-effect**.
- Trigger the processing of intermediate operations.
- Once a terminal operation is invoked, the stream pipeline is considered **consumed** and cannot be used again.
- Examples: `forEach()`, `collect()`, `reduce()`, `count()`, `anyMatch()`, `findFirst()`

---

## 13. Most Commonly Used Intermediate Operations in Stream API


### 1. `filter()`

**Example:**
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.stream()
       .filter(n -> n % 2 == 0)
       .forEach(System.out::println);
```

---

### 2. `map()`

**Example:**
```java
List<String> names = Arrays.asList("john", "jane", "jack");
names.stream()
     .map(String::toUpperCase)
     .forEach(System.out::println);
```

---

### 3. `concat()`

**Example:**
```java
Stream<String> stream1 = Stream.of("A", "B");
Stream<String> stream2 = Stream.of("C", "D");
Stream.concat(stream1, stream2)
      .forEach(System.out::println);
```

---

### 4. `sorted()`

**Example:**
```java
List<String> names = Arrays.asList("Bob", "Alice", "Charlie");
names.stream()
     .sorted()
     .forEach(System.out::println);
```

---

## 14. Collections vs. Streams in Java

| Feature             | Collections                                | Streams                                        |
|---------------------|--------------------------------------------|------------------------------------------------|
| Storage             | Stores elements in memory                   | Does not store elements, only processes them    |
| Processing          | Eager (elements are processed immediately)  | Lazy (processed only when a terminal operation is called) |
| Reusability         | Can be reused after traversal               | Cannot be reused once consumed                  |
| Modifiability       | Supports adding, removing, and updating elements | Immutable and cannot modify the source          |
| Traversal           | Iterated explicitly (using loops or iterators) | Traversed implicitly (using internal iteration) |
| Data Source         | Finite and predefined                       | Can be finite or infinite                       |
| Parallelism         | Requires manual handling                    | Supports parallel processing via `parallelStream()` |
| Type                | Part of the **java.util** package            | Part of the **java.util.stream** package         |

---

## 15. Implementing Stream API's `filter()` and `map()` Methods

### 1. Implementing the `filter()` Method

```java
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CustomFilter {
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<Integer> evenNumbers = filter(numbers, n -> n % 2 == 0);
        System.out.println("Filtered list (even numbers): " + evenNumbers);
    }
}
```
### 2. Implementing the `map()` Method

```java
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CustomMap {
    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T item : list) {
            result.add(function.apply(item));
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> names = List.of("alice", "bob", "charlie");
        List<String> upperNames = map(names, String::toUpperCase);
        System.out.println("Mapped list (uppercase): " + upperNames);
    }
}
```