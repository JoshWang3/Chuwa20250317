# 1. Learn Java generics by reading and practicing the following code:  
    [Java Generics Tutorial](https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t01_basic/generic)

# 2. Read the following code repository and type it out yourself:  
    [Java 8 Features Code](https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/features)

# 3. Practice the following Stream API exercises at least 3 times:  
    [Stream API Exercises](https://github.com/gavinklfong/stream-api-exercises/blob/main/src/test/java/space/gavinklfong/demo/streamapi/StreamApiTest.java)

# 4. Practice Optional methods at least 2 times:  
    [Optional Methods Exercise](https://github.com/CTYue/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/exercise/ShoppingCartUtil.java)

# 5. Discuss best practices for preventing `NullPointerException` and provide code snippets for each practice mentioned.
Using `Optional` type. An `Optional<T>` object can encourage programmer to actively think about the circumstance where the object might be null to avoid `NullPointerException`. 

```java
Optional<String> optionalName = Optional.ofNullable(getName())
                                        .orElse("Unknown");
```

# 6. Discuss Java 8 new features with code snippets.
   - Lambda Expressions: Lambda expressions enable you to write concise code for interfaces with a single abstract method (functional interfaces). Implements Single Abstract Method.
      ```java
      Foo foo = parameter -> parameter.toUpperCase() + " from Foo";

      String hello = foo.aMethod("hello");
      System.out.println(hello);
      ```

   - Functional Interfaces: These are interfaces with a single abstract method. 
     ```java
      @FunctionalInterface
      interface MyFuncInterface {
          void show();
      }

      public class Test {
          public static void main(String[] args) {
              MyFuncInterface f = () -> System.out.println("Functional Interface Example");
              f.show();
          }
      }
 
     ```
   - Stream API: The `Stream` API enables functional-style operations on collections (e.g., map, filter, reduce).
    ```java
    import java.util.Arrays;
    import java.util.List;

    public class StreamDemo {
        public static void main(String[] args) {
            List<String> names = Arrays.asList("Tom", "Jerry", "Bob", "Tim");

            names.stream()
                .filter(s -> s.startsWith("T"))
                .sorted()
                .forEach(System.out::println);  // Output: Tim, Tom
        }
    }
    ```



# 7. What are the advantages of the `Optional` class?

1. **Avoids NullPointerException**
   - `Optional` provides a clear and explicit way to deal with potentially `null` values, reducing the chance of encountering `NullPointerException`.

2. **Improves Code Readability**
   - The use of `Optional` makes the code more readable and expressive by clearly indicating that a value may be absent.

3. **Encourages Functional Programming**
   - `Optional` supports functional-style methods like `map`, `flatMap`, `filter`, and `ifPresent`, promoting a more declarative approach to handling optional values.

4. **Forces Proper Null Handling**
   - By making the absence of a value explicit, `Optional` encourages developers to handle such cases proactively instead of relying on `null` checks scattered throughout the code.

5. **Avoids Explicit Null Checks**
   - Eliminates the need for verbose `if (obj != null)` checks by providing concise alternatives like `optional.isPresent()` or `optional.orElse()`.

6. **Improves API Design**
   - Returning `Optional` from methods instead of `null` improves API clarity and communicates the possibility of an absent value to the consumer.

7. **Reduces Runtime Errors**
   - By encouraging compile-time handling of optional values, `Optional` can help catch potential bugs early during development.

8. **Provides Default Values Easily**
   - With methods like `orElse`, `orElseGet`, and `orElseThrow`, it's easy to provide fallback logic when a value is missing.

   
# 8. Explain Functional Interfaces and Lambdas with code samples.
A functional interface in Java is an interface that has only one abstract method. It can have multiple default or static methods, but just one abstract method.

Functional interfaces are the backbone of lambda expressions in Java.

Java provides several built-in functional interfaces in the java.util.function package, like `Runnable`, `Callable`, `Function<T, R>`, `Predicate<T>`, etc.
```java
@FunctionalInterface
interface MyFunctionalInterface {
    void sayHello();
}
```
The `@FunctionalInterface` annotation is optional, but it helps the compiler to enforce the rule of having only one abstract method.

A lambda expression is a shorthand for implementing a functional interface. It enables you to write instances of single-method interfaces (functional interfaces) in a more concise way.

```java
@FunctionalInterface
interface MyFunctionalInterface {
    void sayHello();
}

public class LambdaExample {
    public static void main(String[] args) {
        // Lambda implementation of the interface
        MyFunctionalInterface greeting = () -> System.out.println("Hello from Lambda!");
        greeting.sayHello();  // Output: Hello from Lambda!
    }
}
```

# 9.  Explain Method References with code samples.
A method reference is a shorthand syntax for a lambda expression that simply calls a method. It makes your code cleaner and more readable.
```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static int compareByName(Person p1, Person p2) {
        return p1.name.compareTo(p2.name);
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

public class MethodReferenceExample {
    public static void main(String[] args) {
        // 1. 静态方法引用
        List<Person> people = Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 35));

        // 使用Lambda表达式
        people.sort((p1, p2) -> Person.compareByName(p1, p2));

        // 使用静态方法引用
        people.sort(Person::compareByName);

        // 2. 实例方法引用（特定对象的实例方法）
        Comparator<Person> byAgeComparator = Comparator.comparingInt(Person::getAge);
        people.sort(byAgeComparator);

        // 3. 类的实例方法引用
        // 不要尝试理解Function,将会被stream使用。
        Function<Person, String> getNameFunction = Person::getName;
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.sort(String::compareToIgnoreCase);

        // 4. 构造方法引用
      	// 不要尝试理解BiFunction,将会被stream使用。
        BiFunction<String, Integer, Person> personCreator = Person::new;
        Person newPerson = personCreator.apply("David", 40);

        System.out.println(people);
    }
}

```

# 10. Explain "Lambda can use unchanged variables outside of lambda" with a code snippet.
Lambdas in Java can access variables from the enclosing scope (like a method or block), but only if those variables are effectively final — meaning they aren’t changed after being assigned.

```java
    @Test
    public void testFinal() {
        final String localVariable = "Local";
        Foo foo = parameter -> {
            return parameter + " " + localVariable;
        };

        System.out.println(foo.aMethod("hello"));
    }

    /**
     * Use “Effectively Final” Variables
     * 当variable只赋值一次，没有任何变动的时候，Java默认是final。
     * 注意，在lambda expression的前后都不能被改变
     */
    @Test
    public void testEffectivelyFinal() {
        String localVariable = "Local";
        Foo foo = parameter -> {
            return parameter + " " + localVariable;
        };

        System.out.println(foo.aMethod("hello"));
    }

    /**
     * 换object会报错，因为换了内存地址
     */
    @Test
    public void testFinal21() {
        String localVariable = "Local";
        localVariable = "LOCAL"; // 新的内存地址

        Foo foo = parameter -> {
            return parameter + " " + localVariable; // 会报错
        };

        System.out.println(foo.aMethod("hello"));
    }

    @Test
    public void testFinal22() {
        String localVariable = "Local";

        Foo foo = parameter -> {
						return parameter + " " + localVariable; // 会报错
        };

        localVariable = "LOCAL"; // 新的内存地址

        System.out.println(foo.aMethod("hello"));
    }

    /**
     * Object 的set方法不会报错
     */
    @Test
    public void testFinal3() {
        List<Employee> employees = EmployeeData.getEmployees();

        Employee employee = employees.get(0);
        employee.setAge(55);
        Foo foo = parameter -> {
            return parameter + " " + employee;
        };

        System.out.println(foo.aMethod("hello"));
    }

```

# 11. Can a functional interface extend/inherit another interface?
Yes, a functional interface in Java can extend (inherit) another interface, including another functional interface — as long as it still satisfies the functional interface constraint, which is exactly one abstract method.
```java
interface A {
    void doSomething();
}

interface B {
    void doSomethingElse();
}

@FunctionalInterface
interface C extends A, B {
    // Compilation error: inherits two abstract methods
}
```


# 12. What are Intermediate and Terminal operations?
Intermediate operations transform a stream into another stream.

Terminal operations produce a result or a side effect and mark the end of the stream pipeline. After this, the stream is considered consumed and can't be reused.

```java
List<String> names = List.of("Alice", "Bob", "Charlie");

long count = names.stream()              // creates a stream
    .filter(name -> name.length() > 3)   // intermediate
    .map(String::toUpperCase)            // intermediate
    .count();                            // terminal
```


# 13. Demonstrate the most commonly used Intermediate operations in the Stream API with code snippets.
```java
        List<String> topAdultNames = people.stream()
            .filter(p -> p.age >= 18) // keep only adults
            .collect(Collectors.collectingAndThen(
                Collectors.toMap(p -> p.name, p -> p, (p1, p2) -> p1), // deduplicate by name
                map -> new ArrayList<>(map.values())
            ))
            .stream()
            .sorted(Comparator.comparingInt((Person p) -> p.age).reversed()) // sort by age descending
            .map(p -> p.name) // map to names
            .limit(3) // take top 3
            .collect(Collectors.toList());
```

# 14. How are Collections different from Streams?
| Feature                   | Collections                                       | Streams                                             |
|---------------------------|---------------------------------------------------|-----------------------------------------------------|
| **Concept**               | Data structure for storing elements               | Abstraction for processing elements                 |
| **Storage**               | Stores data in memory                             | Does not store data, operates on the source         |
| **Evaluation**            | Eager (results computed immediately)              | Lazy (executed only on terminal operation)          |
| **Usage**                 | Used for data storage and access                  | Used for data transformation and querying           |
| **Reusability**           | Can be reused (iterate multiple times)           | Cannot be reused (one-time use)                     |
| **Iteration**             | External (e.g. `for-each` loop)                  | Internal (handled by the Stream API)                |
| **Mutability**            | Mutable (can add/remove elements)                 | Immutable (does not modify the source)              |
| **Parallelism**           | Manual (requires explicit threading)              | Built-in support via `parallelStream()`             |


# 15. Implement the Stream API's `filter` and `map` methods yourself.
```java
class MyStream<T> {
    private final List<T> data;

    public MyStream(List<T> data) {
        this.data = data;
    }

    public static <T> MyStream<T> of(List<T> data) {
        return new MyStream<>(data);
    }

    public MyStream<T> filter(Predicate<T> predicate) {
        List<T> filtered = new ArrayList<>();
        for (T item : data) {
            if (predicate.test(item)) {
                filtered.add(item);
            }
        }
        return new MyStream<>(filtered);
    }

    public <R> MyStream<R> map(Function<T, R> mapper) {
        List<R> mapped = new ArrayList<>();
        for (T item : data) {
            mapped.add(mapper.apply(item));
        }
        return new MyStream<>(mapped);
    }

    public List<T> collect() {
        return data;
    }
}

```