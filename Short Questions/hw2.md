# 1. Practice collection
# 2. Write code to compare and explain checkedException vs uncheckedException
```java
class AgeException extends Exception {
    public AgeException(String message) {
        super(message);
    }
}

class CustomUncheckedException extends RuntimeException {
    public CustomUncheckedException(String message) {
        super(message);
    }
}

public class CheckedVsUnchecked {

    // Method that throws a checked exception
    public static void processChecked(int age) throws AgeException {
        if (age < 18) {
            throw new AgeException("You must be 18 or older.");
        }
        System.out.println("Checked: Access granted.");
    }

    // Method that throws an unchecked exception
    public static void processUnchecked(int[] arr, int index) {
        System.out.println("Value: " + arr[index]); // May throw ArrayIndexOutOfBoundsException
    }

    public static void main(String[] args) {
        // Checked Exception (must be caught or declared)
        try {
            processChecked(16);
        } catch (AgeException e) {
            System.out.println("Caught Checked Exception: " + e.getMessage());
        }

        // Unchecked Exception (can be caught, but not required)
        try {
            int[] numbers = {1, 2, 3};
            processUnchecked(numbers, 5); // Invalid index
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught Unchecked Exception: " + e.getMessage());
        }
    }
}

```

| Feature                  | Checked Exception                          | Unchecked Exception                          |
|--------------------------|--------------------------------------------|----------------------------------------------|
| **Examples**             | `Exception`, `MyCheckedException`          | `RuntimeException`, `MyUncheckedException`   |
| **Compiler Check**       | ✅ Required                                | ❌ Not Required                              |
| **Must Handle or Declare** | ✅ Yes                                  | ❌ No                                        |
| **Extends**              | `Exception` (but **not** `RuntimeException`) | `RuntimeException`                          |
| **Typical Use Case**     | Expected issues (e.g., invalid input, logic rules) | Programming bugs (e.g., null pointer, bad index) |


# 3. Can there be multiple finally blocks? Can there be multiple catch blocks? Write code to explain.
We can only have one `finally ` block but multiple `catch` blocks.

`finally` is the block that is guaranteed to run. On the other hand, `catch` block is only running when an exception that meets the condition is caught.

```java
public class ExceptionExample {
    public static void main(String[] args) {
        try {
            int[] nums = {1, 2, 3};
            System.out.println(nums[5]); // This throws ArrayIndexOutOfBoundsException
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Caught General Exception: " + e.getMessage());
        } finally {
            System.out.println("This will always run (finally block).");
        }
    }
}

```

# 4. When both catch and finally return values, what will be the final result?

When both `catch` and `finally` blocks have return statements, the `finally` block's return value takes precedence and overrides the one from `catch` (or even `try`).

**`finally` is gauranteed to run.**

# 5. What is the difference between throw and throws?
| Keyword  | Purpose                                 | Used for                                 | Example                                   | Notes                |
|----------|-----------------------------------------|-------------------------------------------|-------------------------------------------|----------------------|
| `throw`  | To actually throw an exception          | Throwing an instance of an exception      | `throw new IOException("File not found");`| Happens at runtime   |
| `throws` | To declare a method might throw an exception | Declaring exceptions a method might raise | `public void readFile() throws IOException` | Happens at compile-time |

# 6. Run the below three pieces codes, Noticed the printed exceptions. why do we put the Null/Runtime exception before Exception ?
```Java
public class Main {
    public static void main(String[] args) {
        int a = 0;
        int b = 3
        String s = null;
        try {
            System.out.println(b / a);
            System.out.println(s.equals("aa"));
            throw new RuntimeException();
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.getMessage();
        }
        System.out.println("End ...");
    }
}
public class Main {
    public static void main(String[] args) {
        int a = 0;
        int b = 3
        String s = null;
        try {
// System.out.println(b / a);
            System.out.println(s.equals("aa"));
            throw new RuntimeException();
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.getMessage();
        }
        System.out.println("End ...");
    }
}
public class Main {
    public static void main(String[] args) {
        int a = 0;
        int b = 3
        String s = null;
        try {
// System.out.println(b / a);
// System.out.println(s.equals("aa"));
            throw new RuntimeException();
        } catch (ArithmeticException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.getMessage();
        }
        System.out.println("End ...");
    }
}
```

First piece throws `ArithmeticException`. Second thorows `NullPointerException`. Third throws `RuntimeException`.

We put more specific exceptions first (like `NullPointerException`, `RuntimeException`) and general ones like `Exception` last, because Java picks the first matching handler and requires no unreachable code in the catch chain.

# 7. What is optional? why do you use it? write an optional example to demo how it avoids NPE.
`Optional<T>` is a container object that may or may not contain a non-null value. It avoids manually checking `null` values and encourages better API design by making it clear a value might not be present.

```java
import java.util.Optional;

public class User {
    private Optional<String> email;

    public User(String email) {
        this.email = Optional.ofNullable(email);
    }

    public Optional<String> getEmail() {
        return email;
    }
}
public class Main {
    public static void main(String[] args) {
        User user = new User(null);

        // Safely handle potential null
        String email = user.getEmail().orElse("default@example.com");

        System.out.println("Email: " + email); // ✅ "Email: default@example.com"
    }
}

```

# 8. What are the types of design patterns in Java? Name popular design patters, particularly Creational  Patterns and Structural Patterns

Design patterns are categorized into three main types:

### 🔧 Creational Patterns
Deal with object creation.

- **Singleton** – Ensures only one instance of a class.
- **Factory Method** – Creates objects via a common interface.
- **Abstract Factory** – Creates families of related objects.
- **Builder** – Constructs complex objects step-by-step.
- **Prototype** – Clones existing objects.

### 🧱 Structural Patterns
Concerned with class/object composition.

- **Adapter** – Makes incompatible interfaces compatible.
- **Bridge** – Separates abstraction from implementation.
- **Composite** – Treats individual and composite objects uniformly.
- **Decorator** – Adds behavior dynamically.
- **Facade** – Provides a unified interface to a subsystem.
- **Flyweight** – Shares data to support many fine-grained objects efficiently.
- **Proxy** – Controls access to another object.

### 🧠 Behavioral Patterns *(for reference)*
Deal with communication between objects.

Examples: **Observer**, **Strategy**, **Command**, **State**, **Iterator**, etc.

# 10. Explain **SOLID** Principles ? Further explain **Open-Closed Principle (OCP)**?

S – Single Responsibility Principle (SRP)
- A class should have only one reason to change, meaning it should have only one job or responsibility.

O – Open-Closed Principle (OCP)
- Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification.

L – Liskov Substitution Principle (LSP)
- Objects of a superclass should be replaceable with objects of its subclasses without altering the correctness of the program.

I – Interface Segregation Principle (ISP)
- No client should be forced to depend on interfaces it does not use. Prefer many small, specific interfaces over one large general-purpose interface.
  
D – Dependency Inversion Principle (DIP)
- High-level modules should not depend on low-level modules. Both should depend on abstractions. Also, abstractions should not depend on details.

### Open-Closed-Principles

A programmer should be able to add new functionality to a class without modifying its existing code. This is typically achieved through inheritance, interfaces, or composition.

- Reduces risk of breaking existing functionality.
- Encourages reuse and scalability.
- Makes code easier to test and maintain.


Example:
```java
class PaymentMethod:
    def pay(self):
        raise NotImplementedError

class CreditCard(PaymentMethod):
    def pay(self):
        print("Processing credit card")

class PayPal(PaymentMethod):
    def pay(self):
        print("Processing PayPal")

class Stripe(PaymentMethod):
    def pay(self):
        print("Processing Stripe")

class PaymentProcessor:
    def process(self, method: PaymentMethod):
        method.pay()

```

# 11. Liskov’s substitution principle states that if class B is a subtype of class A, then object of type A may be substituted with any object of type B. What does this actually mean? (from OA) choose your answer.

    1. **It mean that if the object of type A can do something, the object of type B could also be able to perform the same thing **    
    2. It means that all the objects of type A could execute all the methods present in its subtype B
    3. It means if a method is present in class A, it should also be present in class B so that the object of type B could substitute object of type A.
    4. It means that for the class B to inherit class A, objects of type B and objects of type A must be same.
    

# 12. Watch design pattern video as below.
singleton: https://www.bilibili.com/video/BV1Np4y1z7BU?p=22\
Factory: https://www.bilibili.com/video/BV1Np4y1z7BU?p=35&vd_source=310561eab1216a27f7accf859bf7f6\
Builder: https://www.bilibili.com/video/BV1Np4y1z7BU?p=50&vd_source=310561eab1216a27f7accf859bf7f6d9\
Publisher_Subscriber: https://www.bilibili.com/video/BV1Np4y1z7BU?p=114&vd_source=310561eab1216a27f7accf859bf7f6d9