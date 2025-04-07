# HW4
## 1. Learn Java generics by reading and practicing following code:
https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t01_basic/generic
- Generics allow classes, interfaces, and methods to operate on objects of various types, while ensuring type safety at compile time.

## 2. Read the follwoing code repo and type it one by one by yourself.
https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/features

## 3. Practice following stream API exercises at least 3 times
https://github.com/gavinklfong/stream-api-exercises/blob/main/src/test/java/space/gavinklfong/demo/streamapi/StreamApiTest.java

## 4. Practice Optional methods at least 2 times
https://github.com/CTYue/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/exercise/ShoppingCartUtil.java

## 5. Discuss best practices on nullptr exception prevention, provide code snippet for each practice that you mentioned.
1. Use Optional for return types: Never return null—return Optional to signal "value might be absent."
```java
public Optional<String> getUsernameById(int id) {
    return Optional.ofNullable(findUserById(id)).map(User::getUsername);
}
// Usage
String username = getUsernameById(5).orElse("Guest");
```
2. Use Objects.requireNonNull() to fail fast: Throw a clear exception early if null is unacceptable.
```java
public User(UserProfile profile) {
    this.profile = Objects.requireNonNull(profile, "Profile is required");
}
```
3. Leverage @NonNull / @Nullable annotations (IDE support): These annotations help tools like IntelliJ and static analyzers warn us before runtime.
```java
public String greet(@NonNull String name) {
    return "Hi, " + name;
}
```
4. Guard with null checks (when absolutely necessary)
```java
if (user != null && user.getProfile() != null) {
    System.out.println(user.getProfile().getBio());
}
```
```java
if (obj instanceof User user && user.getProfile() != null) {
    System.out.println(user.getProfile().getBio());
}
```

5. Use default values (Objects.requireNonNullElse)
```java
String name = Objects.requireNonNullElse(inputName, "Anonymous");
```

6. Design APIs to never return null collections
```java
public List<String> getTags() {
    return tags != null ? tags : Collections.emptyList();
}
```

## 6. Discuss Java 8 new features with code snippet.
1. Lambda Expressions
Write anonymous functions in a sleek way
```java
List<String> names = Arrays.asList("Elena", "Alex", "Luna");
names.forEach(name -> System.out.println("Hello " + name));
```

2. Stream API: 
Work with collections functionally: filtering, mapping, reducing
```java
List<String> names = Arrays.asList("Elena", "Alex", "Luna");

List<String> filtered = names.stream()
    .filter(n -> n.startsWith("A"))
    .collect(Collectors.toList());

System.out.println(filtered); // [Alex]
```

3. Functional Interfaces
Interfaces with a single abstract method. Often used with lambdas.
```java
@FunctionalInterface
interface Greeter {
    void greet(String name);
}

Greeter g = name -> System.out.println("Hi, " + name);
g.greet("Elena");
```

4. Method References
```java
List<String> list = Arrays.asList("a", "b", "c");
list.forEach(System.out::println);
```

5. Default & Static Methods in Interfaces
```java
interface MyInterface {
    default void sayHello() {
        System.out.println("Hello from default method!");
    }

    static void sayStatic() {
        System.out.println("Static method here!");
    }
}
```

6. Optional: handle NPE
```java
Optional<String> name = Optional.ofNullable(getName());
String safeName = name.orElse("Guest");
```

## 7. What are the advantages of the Optional class?
- Null safety: Encourages handling absent values without using null, reducing NPE risks.
- Readable code: Makes it clear when a value is optional (Optional<User> vs User), improving API clarity.
- Functional style: Supports chaining with methods like .map(), .flatMap(), .filter(), and .ifPresent(), promoting a more expressive and cleaner coding style.
- Better alternatives to null checks: Replaces verbose if (obj != null) logic with elegant methods like orElse(), orElseGet(), or orElseThrow().
- Attention: DO NOT use Optional for fields or method parameters, just for return types where a value may or may not exist.

## 8. Explain Functional Interface and Lambda with code samples.
### Functional Interface
A Functional Interface is an interface with only one abstract method. It can have default or static methods, but just one abstract method.
```java
@FunctionalInterface
interface Greeter {
    void greet(String name);
}
```
### Lambda Expression
A Lambda is a concise way to create an instance of a functional interface.
```java
Greeter g = new Greeter() {
    public void greet(String name) {
        System.out.println("Hello, " + name);
    }
};
g.greet("Elena");
```
```java
Greeter g = (name) -> System.out.println("Hello, " + name);
g.greet("Elena");
```
```java
Runnable task = () -> System.out.println("Running in a thread");
new Thread(task).start();
```

## 9. Explain Method Reference with code samples?
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
3. Reference to an instance method of an arbitrary object of a particular type
Each element becomes the receiver of the method.
```java
List<String> names = Arrays.asList("Elena", "Alex", "Luna");
names.forEach(String::toUpperCase); // doesn't change the list

// Real example with printing:
names.forEach(System.out::println);
```

4. Reference to a constructor
Constructor references are very useful when creating objects in streams or dependency injection.
```java
Supplier<List<String>> listSupplier = ArrayList::new;
List<String> newList = listSupplier.get();
```

## 10. Explain "Lambda can use unchanged variable outside of lambda", with code snippet.

## 11. Can a functional interface extend/inherit another interface?

## 12.What are Intermediate and Terminal operations?

## 13. Demontrate the most commonly used Intermediate operations in Stream API, with code snippet.

## 14. How are Collections different from Stream?

## 15. Implement Stream API's filter and map methods by your self.