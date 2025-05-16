1. Learn Java generics by reading and practicing following code:
https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t01_basic/generic
2. Read the follwoing code repo and type it one by one by yourself. 
https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/features
3. Practice following stream API exercises at least 3 times
https://github.com/gavinklfong/stream-api-exercises/blob/main/src/test/java/space/gavinklfong/demo/streamapi/StreamApiTest.java
4. Practice Optional methods at least 2 times
https://github.com/CTYue/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/exercise/ShoppingCartUtil.java
5. Discuss best practices on nullptr exception prevention, provide code snippet for each practice that you 
mentioned.
- Use `Optional` instead of null
```java
Optional<String> name = Optional.ofNullable(getName());
name.ifPresent(System.out::println);
```

- Null-safe equals
```java
Objects.equals(a, b);  // Avoids NPE if a is null
```

- Initialize fields early
```java
private List<String> items = new ArrayList<>();
```

-  Avoid chaining on possibly null values

```java
// BAD
person.getAddress().getCity();

// GOOD
Optional.ofNullable(person)
        .map(Person::getAddress)
        .map(Address::getCity)
        .ifPresent(System.out::println);
```


6. Discuss Java 8 new features with code snippet.
- Lambda Expressions
```java
List<String> list = Arrays.asList("a", "b", "c");
list.forEach(item -> System.out.println(item));
```

- Functional Interfaces (`@FunctionalInterface`)

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

Calculator add = (a, b) -> a + b;
```

- Stream API

```java
List<String> names = Arrays.asList("Tom", "Jerry", "Bob");
names.stream()
     .filter(n -> n.startsWith("J"))
     .forEach(System.out::println);
```

- Optional
```java
Optional<String> maybeName = Optional.ofNullable(null);
maybeName.orElse("Default");
```


7. What are the advantages of the Optional class?
* Prevents NPE in a functional style.
* Encourages developers to **explicitly handle null cases**.
* Provides useful methods: `map()`, `flatMap()`, `ifPresent()`, `orElse()`...


8. Explain Functional Interface and Lambda with code samples.
- Functional Interface:
```java
@FunctionalInterface
interface Greeting {
    void say(String message);
}
```

- Lambda:
```java
Greeting greet = msg -> System.out.println("Hello " + msg);
greet.say("World");
```


9. Explain Method Reference with code samples?
```java
List<String> list = Arrays.asList("A", "B", "C");

// Lambda
list.forEach(s -> System.out.println(s));

// Method Reference
list.forEach(System.out::println);
```
Types:
* `object::instanceMethod`
* `Class::staticMethod`
* `Class::instanceMethod`

10. Explain "Lambda can use unchanged variable outside of lambda", with code snippet.
```java
String prefix = "Hello ";

Consumer<String> greeter = name -> System.out.println(prefix + name);
greeter.accept("Alice");

// prefix must be final or effectively final (not changed after declaration)
```


11. Can a functional interface extend/inherit another interface?

Yes, as long as it doesn’t introduce more than one abstract method.
```java
@FunctionalInterface
interface A {
    void methodA();
}

@FunctionalInterface
interface B extends A {
    // still functional as only one abstract method
}
```


12. What are Intermediate and Terminal operations?


| Operation Type | Examples                            | Returns                       |
| -------------- | ----------------------------------- | ----------------------------- |
| Intermediate   | `filter()`, `map()`, `sorted()`     | Stream (lazy)                 |
| Terminal       | `forEach()`, `collect()`, `count()` | Result (value or side-effect) |


13. Demontrate the most commonly used Intermediate operations in Stream API, with code snippet.

```java
List<String> words = Arrays.asList("apple", "banana", "cherry");

List<String> result = words.stream()
    .filter(w -> w.length() > 5)         // keep long words
    .map(String::toUpperCase)            // convert to upper case
    .sorted()                            // sort
    .collect(Collectors.toList());

System.out.println(result); // [BANANA, CHERRY]
```


14. How are Collections different from Stream?

| Collection                    | Stream                          |
| ----------------------------- | ------------------------------- |
| Stores elements               | Describes computation on data   |
| Eager                         | Lazy (evaluated on terminal op) |
| Can be modified               | Cannot modify source            |
| External iteration (for-each) | Internal iteration              |



15. Implement Stream API's filter and map methods by your self.
```java
public class CustomStream<T> {
    private final List<T> data;

    public CustomStream(List<T> data) {
        this.data = data;
    }

    public CustomStream<T> filter(Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : data) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return new CustomStream<>(result);
    }

    public <R> CustomStream<R> map(Function<T, R> mapper) {
        List<R> result = new ArrayList<>();
        for (T item : data) {
            result.add(mapper.apply(item));
        }
        return new CustomStream<>(result);
    }

    public List<T> collect() {
        return data;
    }

    // Example usage
    public static void main(String[] args) {
        List<String> result = new CustomStream<>(List.of("apple", "banana", "cat"))
            .filter(s -> s.length() > 3)
            .map(String::toUpperCase)
            .collect();

        System.out.println(result); // [APPLE, BANANA]
    }
}
```
