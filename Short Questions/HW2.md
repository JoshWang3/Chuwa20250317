
### 2.
Checked Exception: These exceptions are checked at compile time, forcing the programmer to handle them explicitly.
```java
import java.io.*;
class Geeks {

    public static void main(String[] args) throws IOException {

        // Getting the current root directory
        String root = System.getProperty("user.dir");
        System.out.println("Current root directory: " + root);

        // Adding the file name to the root directory
        String path = root + "\\message.txt";
        System.out.println("File path: " + path);

        // Reading the file from the path in the local directory
        try {
            FileReader f = new FileReader(path);

            // Creating an object as one of the ways of taking input
            BufferedReader b = new BufferedReader(f);

            // Printing the first 3 lines of the file "C:\\Devanshu\\JAVACODES\\message.txt"
            for (int counter = 0; counter < 3; counter++)
                System.out.println(b.readLine());

            // Closing file connections
            // using the close() method
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An I/O error occurred: " + e.getMessage());
        }
    }
}
```
Unchecked Exception: These exceptions are checked at runtime and do not require explicit handling at compile time.

```java
// Java Program to Illustrate Un-checked Exceptions
class Geeks {
    public static void main(String args[]) {
      
        // Here we are dividing by 0
        // which will not be caught at compile time
        // as there is no mistake but caught at runtime
        // because it is mathematically incorrect
        int x = 0;
        int y = 10;
        int z = y / x;
    }
}
```

### 3.
No, in Java, there can only be one finally block per try-catch statement. The finally block is designed to ensure that certain code (like resource cleanup or closing streams) is always executed, regardless of whether an exception is thrown or not.

Yes, There can be multiple catch blocks in Java. This is useful when you want to handle different types of exceptions separately. Each catch block can handle a specific type of exception, allowing you to deal with different error scenarios in an appropriate way

```java
public class Main {
    public static void main(String[] args) {
        try {
            // Example code that could throw different exceptions
            int result = 10 / 0;  // ArithmeticException
            String text = null;
            System.out.println(text.length());  // NullPointerException
        } catch (ArithmeticException e) {
            System.out.println("Caught an ArithmeticException: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Caught a NullPointerException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Caught a general exception: " + e.getMessage());
        }
    }
}

```

### 4.
When both the catch and finally blocks return a value, the return value from the finally block will be the one that is ultimately returned. This is because the finally block is executed after the catch block, and if it contains a return statement, it overrides any return value that may have been set in the catch block.
### 5.
1. throw (Used to Throw an Exception)
throw is used inside a method to manually throw an exception.
It is followed by an exception object.
After throw, execution stops immediately unless the exception is caught.

2. throws (Used to Declare Exceptions)
throws is used in a method signature to declare that the method might throw exceptions.
It does not throw the exception itself; it just signals that the method may produce an exception.
If a method is declared with throws, the caller must handle or propagate the exception.

### 6.

1. **Specific exceptions first**: If you put a more general exception (like `Exception`) before a more specific one (like `NullPointerException`), the more general exception will catch the error first, and the specific ones will never be reached. This would be a problem because `NullPointerException` is a subclass of `Exception`, so it will always be caught by the `Exception` block, and the corresponding `NullPointerException` block would never get executed.
    
2. **Runtime exceptions**: `RuntimeException` is also a subclass of `Exception`. If you place it after `Exception`, the `RuntimeException` block will never be executed for runtime exceptions, because `Exception` would catch them first. To ensure that the specific `RuntimeException` is caught before the more general `Exception`, it needs to come earlier in the `catch` blocks.




### 7.

In Java, Optional is a container object used to represent a value that may or may not be present. It's part of the java.util package and is commonly used to handle values that could potentially be null, helping to reduce the risk of NullPointerException (NPE) and improving the readability of code.

1. **Avoid `NullPointerException`:** Instead of returning `null` for absent values, you can return an `Optional.empty()`, signaling that the value is absent. This provides a more explicit way to handle missing values.
    
2. **Improve Readability:** Using `Optional` clarifies the intention of the code, making it clear that a value might be absent. This makes the code more self-documenting and readable.
    
3. **Functional Programming Style:** `Optional` encourages a more functional programming style. It provides methods like `.map()`, `.filter()`, and `.ifPresent()`, which enable cleaner and more expressive code when dealing with absent or present values.

```java
import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        Optional<String> optionalName = Optional.ofNullable(getName());

        // Using ifPresent() to check if value is present
        optionalName.ifPresent(name -> System.out.println("Hello, " + name));

        // Using orElse() to provide a default value if not present
        String name = optionalName.orElse("Guest");
        System.out.println("Welcome, " + name);
    }

    public static String getName() {
        // Return null or a string based on some condition
        return null; // Simulating absence of value
    }
}

```

### 8.

1. Creational Design Patterns
*These patterns deal with object creation mechanisms, improving flexibility and reusability.*
Singleton – Ensures only one instance of a class exists.
Factory Method – Provides an interface for creating objects, but lets subclasses alter the type.
Abstract Factory – Creates families of related objects without specifying their concrete classes.
Builder – Separates object construction from its representation, useful for complex objects.

Prototype – Creates objects by copying an existing instance.
2. **Structural Design Patterns**
*These patterns deal with object composition and relationships to form larger structures.*

- **Adapter (Wrapper)** – Allows incompatible interfaces to work together.
- **Bridge** – Separates abstraction from implementation for easier changes.
- **Composite** – Allows treating individual and composite objects uniformly.
- **Decorator** – Dynamically adds responsibilities to an object without modifying its structure.
- **Facade** – Provides a unified interface to a set of interfaces in a subsystem.
- **Flyweight** – Minimizes memory usage by sharing objects.
- **Proxy** – Provides a placeholder for another object to control access.

 3. **Behavioral Design Patterns**

### 9.
##### singleton:
```java
public class Singleton {
    // Private static instance of the class
    private static Singleton instance;

    // Private constructor to prevent instantiation
    private Singleton() {}

    // Public method to provide global access
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) { // Thread safety
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // Example method
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        singleton.showMessage();
    }
}

```

The Singleton pattern implementation I provided is thread-safe because it uses **double-checked locking** within the `getInstance()` method.

Builder design
```java
public class House {
    private String foundation, walls, roof;
    private int floors;

    public House(String foundation, String walls, String roof, int floors) {
        this.foundation = foundation;
        this.walls = walls;
        this.roof = roof;
        this.floors = floors;
    }

    @Override
    public String toString() {
        return "House [foundation=" + foundation + ", walls=" + walls + ", roof=" + roof + ", floors=" + floors + "]";
    }
}
public class HouseBuilder {
    private String foundation, walls, roof;
    private int floors;

    public HouseBuilder setFoundation(String foundation) { this.foundation = foundation; return this; }
    public HouseBuilder setWalls(String walls) { this.walls = walls; return this; }
    public HouseBuilder setRoof(String roof) { this.roof = roof; return this; }
    public HouseBuilder setFloors(int floors) { this.floors = floors; return this; }

    public House build() {
        return new House(foundation, walls, roof, floors);
    }
}
public class BuilderPatternExample {
    public static void main(String[] args) {
        House house = new HouseBuilder()
            .setFoundation("Concrete Foundation")
            .setWalls("Brick Walls")
            .setRoof("Tile Roof")
            .setFloors(2)
            .build();
        
        System.out.println(house);
    }
}

```

Factory design
```java
// Product Interface
interface Car {
    void drive();
}

// Concrete Products
class Sedan implements Car {
    public void drive() {
        System.out.println("Driving a Sedan");
    }
}

class SUV implements Car {
    public void drive() {
        System.out.println("Driving an SUV");
    }
}

// Factory Class
class CarFactory {
    public Car createCar(String type) {
        if (type.equals("sedan")) {
            return new Sedan();
        } else if (type.equals("suv")) {
            return new SUV();
        }
        return null;
    }
}

// Client Code
public class FactoryPatternExample {
    public static void main(String[] args) {
        CarFactory factory = new CarFactory();
        
        // Create a Sedan
        Car sedan = factory.createCar("sedan");
        sedan.drive();
        
        // Create an SUV
        Car suv = factory.createCar("suv");
        suv.drive();
    }
}

```
### 10.

S - Single Responsibility Principle (SRP): A class should have only one reason to change.

O - Open/Closed Principle (OCP): Software entities should be open for extension but closed for modification.

L - Liskov Substitution Principle (LSP): Subtypes should be substitutable for their base types.

I - Interface Segregation Principle (ISP): Clients should not be forced to depend on interfaces they do not use.

D - Dependency Inversion Principle (DIP): High-level modules should not depend on low-level modules; both should depend on abstractions.

Further explaination on OCP: 
- **Open for extension** → You should be able to add new functionality to a class or module without modifying its existing code.
    
- **Closed for modification** → Once a class or module is implemented and tested, you shouldn't have to change its internal code to introduce new behaviors.

Why Is It Important?

- **Prevents breaking existing code**: If you modify existing code, you risk introducing new bugs.
- **Enhances maintainability**: New features can be added without altering stable parts of the system.
- **Encourages reusability**: Well-designed code following OCP is easier to reuse and extend.


### 11.
answer is 1.