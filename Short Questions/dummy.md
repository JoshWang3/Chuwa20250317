### Short Questions
<p>
2.
</p>

```java
public class FileHandler {
    //checked exception
    public static void checkedException(String fileName) {
        try {
            readFile(fileName);
        } catch (Exception e) {
            System.out.println("Error: Cannot find file " + fileName);
        }
    }
    
    public static void readFile(String fileName) throws FileNotFoundException {
    }

    public static void main(String[] args) {
        checkedException("file.txt");
    }
}
```

```java
//unchecked exception
public class LengthReader{
    public static void main(){
        String text = null;
        try {
            System.out.println(text.length());
        } catch (NullPointerException e) {
            System.out.println("text is none");
        }
    }
}
```

<p>
3. There can be multiple catch blocks, but can only have one finally block.
</p>

```java
public class Question3{
    public static void main(){
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[4]); // ArrayIndexOutOfBoundsException
            String text = null;
            System.out.println(text.length()); // NullPointerException
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("array too short");
        }
        catch (NullPointerException e) {
            System.out.println("pointer is None");
        }
        finally {
            System.out.println("finishing executing");
        }
    }
}
```

<p>
4. Finally will be the final result. It is because finally will always be executed right before 
the method exits.
</p>

<p>
5. Throws can be used on a method signature, meaning the method might return an exception.
On the other hand, throw is used inside a method, meaning an exception happens for sure.
</p>

<p>
6. More specific exception types must be caught before more general ones in the catch block order. 
This is because Java uses the first matching catch block it finds.
</p>

<p>
7. Optional explicitly handles null values without causing null pointer
exception.
</p>

```java
import java.util.Optional;

public class Test {
    public static void main() {
        Optional<String> safeName = getUserNameSafe();
        safeName.ifPresent(name -> 
            System.out.println(name.length())
        );
    }

    // Safe method (returns Optional)
    static Optional<String> getUserNameSafe() {
        return Optional.ofNullable(null); // Wraps potential null
    }
}
```

<p>
8.<br>
Creational: Singleton, Factory, Builder, Prototype<br>
Structural: Adapter, Decorator, Proxy, Facade
</p>

<p>
9.
</p>

```java
public class ThreadSafeSingleton {
    //singleton
    private static volatile ThreadSafeSingleton instance;
    
    private ThreadSafeSingleton() {}
    
    public static ThreadSafeSingleton getInstance() {
        if (instance == null) { // First check (no locking)
            synchronized (ThreadSafeSingleton.class) {  // Lock only if instance is null
                if (instance == null) {  // Second check (inside lock)
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}
```

```java
//factory
interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override public void draw() { System.out.println("Drawing Circle"); }
}

class Square implements Shape {
    @Override public void draw() { System.out.println("Drawing Square"); }
}

class ShapeFactory {
    // Factory method to create objects
    public Shape createShape(String type) {
        return switch (type.toUpperCase()) {
            case "CIRCLE" -> new Circle();
            case "SQUARE" -> new Square();
            default -> throw new IllegalArgumentException("Unknown shape type");
        };
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        Shape circle = factory.createShape("CIRCLE");
        circle.draw(); // Output: Drawing Circle
    }
}
```

```java
//builder
public class User {
    private final String name;     // Required
    private final int age;         // Optional
    private final String email;    // Optional

    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
    }

    public static class Builder {
        private final String name; // Required field
        private int age = 0;
        private String email = null;

        public Builder(String name) { this.name = name; }

        public Builder age(int age) { 
            this.age = age; 
            return this; 
        }

        public Builder email(String email) { 
            this.email = email; 
            return this; 
        }

        public User build() {
            return new User(this);
        }
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        User user = new User.Builder("Alice")
            .age(30)
            .email("alice@example.com")
            .build();
    }
}
```

<p>
10.<br>
Single Responsibility: A class should have only one reason to change<br>
Open-Closed	Software: Entities should be open for extension but closed for modification	Reduced regression risk<br>
Liskov Substitution:Subtypes must be substitutable for their base types	Safe polymorphism<br>
Interface Segregation: Clients shouldn't depend on unused interfaces<br>
Dependency Inversion: Depend on abstractions, not concretions<br>

OCP means:<br>
Open for Extension: New behavior can be added without touching existing code.<br>
Closed for Modification: Existing code remains unchanged to avoid introducing bugs.
</p>

<p>
11.<br>
Option1. It is because B is a subclass of A. B can call all methods in A.
So B is able to do what A does.
</p>

