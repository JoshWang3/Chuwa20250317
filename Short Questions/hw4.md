# HW4
1. Learn Java generics by reading and practicing following code:
https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t01_basic/generic

2. Read the follwoing code repo and type it one by one by yourself.
https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/features

3. Practice following stream API exercises at least 3 times
https://github.com/gavinklfong/stream-api-exercises/blob/main/src/test/java/space/gavinklfong/demo/streamapi/StreamApiTest.java

4. Practice Optional methods at least 2 times
https://github.com/CTYue/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/exercise/ShoppingCartUtil.java

5. Discuss best practices on nullptr exception prevention, provide code snippet for each practice that you mentioned.
1). Use Optional for return types:
public Optional<String> getUsernameById(int id) {
    return Optional.ofNullable(findUserById(id)).map(User::getUsername);
}
String username = getUsernameById(5).orElse("Guest");

2)Leverage @NonNull / @Nullable annotations (IDE support): These annotations help tools like IntelliJ and static analyzers warn us before runtime.
public String greet(@NonNull String name) {
    return "Hi, " + name;
}

6. Discuss Java 8 new features with code snippet.
1). Lambda Expressions
Write anonymous functions in a sleek way
List<String> list = Arrays.asList("1", "2", "3");
list.forEach(name -> System.out.println("Hello " + name));

2). Stream API: 
Work with collections functionally: filtering, mapping, reducing
List<String> names = Arrays.asList("1", "2", "3");

List<String> filtered = names.stream()
    .filter(n -> n.startsWith("A"))
    .collect(Collectors.toList());

System.out.println(filtered); // [Alex]

3).Optional: handle NPE
Optional<String> name = Optional.ofNullable(getName());
String safeName = name.orElse("Guest");


7. What are the advantages of the Optional class?
- Null safety: Encourages handling absent values without using null, reducing NPE risks.
- Readable code: Makes it clear when a value is optional (Optional<User> vs User), improving API clarity.
- Functional style: Supports chaining with methods like .map(), .flatMap(), .filter(), and .ifPresent(), promoting a more expressive and cleaner coding style.
- Better alternatives to null checks: Replaces verbose if (obj != null) logic with elegant methods like orElse(), orElseGet(), or orElseThrow().
- Attention: DO NOT use Optional for fields or method parameters, just for return types where a value may or may not exist.

8. Explain Functional Interface and Lambda with code samples.
### Functional Interface
A Functional Interface is an interface with only one abstract method. It can have default or static methods, but just one abstract method.

@FunctionalInterface
interface Greeter {
    void greet(String name);
}
### Lambda Expression
A Lambda is a concise way to create an instance of a functional interface.
Greeter g = new Greeter() {
    public void greet(String name) {
        System.out.println("Hello, " + name);
    }
};
g.greet("Elena");
Greeter g = (name) -> System.out.println("Hello, " + name);
g.greet("Elena");

9. Explain Method Reference with code samples?
A method reference is a shorthand for a lambda that calls an existing method.
### 4 Types of Method References
1. Reference to a static method
```java
class Utils {
    static void sayHello(String name) {
        System.out.println("Hello, " + name);
    }
}

Consumer<String> greeter = Utils::sayHello;
greeter.accept("Elena");

// Equivalent Lambda 
(name) -> Utils.sayHello(name)
```
2. Reference to an instance method of a particular object
```java
class Printer {
    void print(String message) {
        System.out.println(message);
    }
}

Printer printer = new Printer();
Consumer<String> printAction = printer::print;
printAction.accept("I can do this!");
```

10. Explain "Lambda can use unchanged variable outside of lambda", with code snippet.
    public class LambdaExample {
    public static void main(String[] args) {
    int x = 10;

        // Lambda expression that uses x from the enclosing scope
        MyFunction addX = (y) -> x + y;

        System.out.println(addX.apply(5)); // Output: 15
    }

    interface MyFunction {
    int apply(int y);
    }
    }


11. Can a functional interface extend/inherit another interface?
    Yes, a functional interface in Java can extend or inherit from another interface, including:

Another functional interface

A non-functional interface

However, to remain a functional interface, the resulting interface must still have exactly one abstract method.

12.What are Intermediate and Terminal operations?
In Java Streams (from java.util.stream), Intermediate and Terminal operations are two categories of operations used for stream processing.
Intermediate:Operations that transform a stream into another stream.They are not executed immediately — only when a terminal operation is invoked.
Terminal:Operations that produce a result or a side effect and end the stream pipeline.Once a terminal operation is called, the pipeline is processed and closed.

13. Demontrate the most commonly used Intermediate operations in Stream API, with code snippet.
    List<String> names = List.of("Alice", "Bob", "Angela");
    List<String> result = names.stream()
    .filter(name -> name.startsWith("A"))
    .collect(Collectors.toList());
    // Output: ["Alice", "Angela"]

    List<String> names = List.of("Alice", "Bob");
    List<Integer> nameLengths = names.stream()
    .map(String::length)
    .collect(Collectors.toList());
    // Output: [5, 3]

    List<Integer> numbers = List.of(3, 1, 4, 1, 5);
    List<Integer> sorted = numbers.stream()
    .sorted()
    .collect(Collectors.toList());
    // Output: [1, 1, 3, 4, 5]


14. How are Collections different from Stream?
Use Collections when you need to store and manage data.
Use Streams when you need to process data functionally and possibly in parallel.

15. Implement Stream API's filter and map methods by your self.
List<String> names = List.of("Alice", "Bob", "Charlie");
    List<String> filtered = StreamLikeUtils.filter(names, name -> name.length() > 3);
    List<String> uppercased = StreamLikeUtils.map(filtered, String::toUpperCase);
    System.out.println(uppercased); // Output: [ALICE, CHARLIE]
