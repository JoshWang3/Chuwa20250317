# hw 2: Exception, Enum, Collection

## 2. Checked vs Unchecked Exception

### **Checked Exception**
- Checked at compile time
- Must be either caught or declared using `throws`
- Example: `IOException`, `SQLException`

```java
import java.io.*;

public class CheckedEx {
    public static void main(String[] args) {
        try {
            throw new IOException("Checked Exception");
        } catch (IOException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
```

### **Unchecked Exception**
- Occurs at runtime
- Not checked at compile time
- Example: `NullPointerException`, `ArithmeticException`

```java
public class UncheckedEx {
    public static void main(String[] args) {
        String str = null;
        System.out.println(str.length()); // Throws NullPointerException
    }
}
```

---

## 3. Multiple `catch` and `finally`

- Java allows **multiple `catch` blocks**, but **only one `finally`** block.

```java
public class TryCatchFinally {
    public static void main(String[] args) {
        try {
            int a = 5 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception");
        } catch (Exception e) {
            System.out.println("General Exception");
        } finally {
            System.out.println("Finally block runs always");
        }
    }
}
```

---

## 4. Return in `catch` and `finally`

```java
public class ReturnTest {
    public static void main(String[] args) {
        System.out.println(test());
    }

    public static int test() {
        try {
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }
    }
}
```

**Output:** `3`  
Reason: `finally` block overrides any previous return statements.

---

## 5. `throw` vs `throws`

| `throw`                          | `throws`                           |
|----------------------------------|------------------------------------|
| Used to **explicitly** throw an exception | Declares that method can throw exception |
| Followed by an instance          | Followed by exception class        |
| Used inside method               | Used in method signature           |
---

## 6. Why Put Specific Exceptions Before General Ones?

If `Exception` (superclass) is placed before `RuntimeException` or `NullPointerException`, they become unreachable.


---

## 7. What is Optional?

Used to avoid `NullPointerException` and handle absent values gracefully.

### Example:

```java
import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Optional<String> name = Optional.ofNullable(null);
        System.out.println(name.orElse("Default Name"));
    }
}
```

---

## 8. Java Design Patterns

### **Creational Patterns:**
- Singleton
- Factory
- Builder
- Prototype
- Abstract Factory

### **Structural Patterns:**
- Adapter
- Decorator
- Proxy
- Composite
- Facade
- Bridge
- Flyweight

---

## 9. Singleton, Factory, Builder Implementation

### **Singleton (Thread-safe)**

```java
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized(Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

### **Factory Pattern**

```java
public interface Shape {
    void draw();
}

public class Circle implements Shape {
    public void draw() {
        System.out.println("Circle");
    }
}

public class ShapeFactory {
    public Shape getShape(String type) {
        if (type.equals("circle")) return new Circle();
        return null;
    }
}
```

### **Builder Pattern**

```java
public class User {
    private final String firstName;
    private final String lastName;

    private User(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    public static class Builder {
        private String firstName;
        private String lastName;

        public Builder firstName(String val) { firstName = val; return this; }
        public Builder lastName(String val) { lastName = val; return this; }
        public User build() { return new User(this); }
    }
}
```

---

## 10. SOLID Principles

- **S**: Single Responsibility Principle  
- **O**: Open/Closed Principle  
- **L**: Liskov Substitution Principle  
- **I**: Interface Segregation Principle  
- **D**: Dependency Inversion Principle  

### Open/Closed Principle (OCP)

Software entities should be **open for extension**, but **closed for modification**.

Example: Use interfaces or abstract classes so new functionality can be added without changing existing code.

---

## 11. Liskov Substitution Principle

**Correct Answer**:  
**1.** It means that if the object of type A can do something, the object of type B should also be able to perform the same thing.*

LSP ensures derived classes can substitute base classes without breaking the application.

