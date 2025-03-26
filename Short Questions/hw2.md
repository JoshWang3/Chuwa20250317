1. All my Java collection practice files are organized in the `Coding/collection` directory.

2. Write code to compare and explain `checkedException` vs `uncheckedException`

   ```java
   import java.io.FileReader;
   import java.io.IOException;
   
   public class Main {
       
       // Checked exception example - must be handled or declared
       public static void checkedExceptionDemo() throws IOException {
           FileReader file = new FileReader("nonexistent.txt");
       }
       
       // Unchecked exception example - no mandatory handling
       public static void uncheckedExceptionDemo() {
           int result = 10 / 0;  // Throws ArithmeticException
       }
       
       public static void main(String[] args) {
           // Checked exceptions require try-catch or throws
           try {
               System.out.println("Calling method with checked exception...");
               checkedExceptionDemo();
           } catch (IOException e) {
               System.out.println("Caught checked exception: " + e.getMessage());
           }
           
           // Unchecked exceptions can be handled optionally
           try {
               System.out.println("Calling method with unchecked exception...");
               uncheckedExceptionDemo();
           } catch (ArithmeticException e) {
               System.out.println("Caught unchecked exception: " + e.getMessage());
           }
       }
   }
   ```

   ```tex
   Calling method with checked exception...
   Caught checked exception: nonexistent.txt (No such file or directory)
   Calling method with unchecked exception...
   Caught unchecked exception: / by zero
   ```

   

3. Can there be multiple finally blocks?  Can there be multiple catch blocks? Write code to explain.

   - Only ONE `finally` block per try statement

   - Multiple `catch` blocks for different exception types

   ```java
   public class Main {
       public static void main(String[] args) {
           try {
               System.out.println("Trying to divide by zero");
               int result = 10 / 0;
               
               // This won't execute due to the exception above
               String str = null;
               System.out.println(str.length());
               
           } catch (ArithmeticException e) {
               // First catch block - handles arithmetic exceptions
               System.out.println("Caught arithmetic exception: " + e.getMessage());
               
           } catch (NullPointerException e) {
               // Second catch block - handles null pointer exceptions
               System.out.println("Caught null pointer exception: " + e.getMessage());
               
           } catch (Exception e) {
               // General catch block - handles any other exceptions
               System.out.println("Caught general exception: " + e.getMessage());
               
           } finally {
               // Only one finally block is allowed
               System.out.println("Finally block always executes");
               
               /* This would cause a compilation error:
               finally {
                   System.out.println("Second finally block - not allowed!");
               }
               */
           }
       }
   }
   ```

   ```tex
   Trying to divide by zero
   Caught arithmetic exception: / by zero
   Finally block always executes
   ```

   

4. When both catch and finally return values, what will be the final result?

   When both a `catch` block and a `finally` block return values, the value from the `finally` block will be the final result. The `finally` block always overrides any return value from the `catch` block.

   ```java
   public class Main {
       public static void main(String[] args) {
           int result = testMethod();
           System.out.println("Final result: " + result);
       }
       
       public static int testMethod() {
           try {
               int x = 10 / 0;
               return 1; // This will never execute
           } catch (ArithmeticException e) {
               System.out.println("Caught exception: " + e.getMessage());
               return 2; // This return is overridden by finally
           } finally {
               System.out.println("In finally block");
               return 3; // This return value takes precedence
           }
       }
   }
   ```

   ```tex
   Caught exception: / by zero
   In finally block
   Final result: 3
   ```

   

5. What is the difference between **`throw`**   and **`throws`**?

   **`throw`**:

   - Used to explicitly throw an exception
   - Used inside methods
   - Followed by an exception object

   **`throws`**:

   - Used in method declaration
   - Specifies exceptions that might be thrown
   - Followed by exception class names

   ```java
   public class Main {
       // "throws" in method declaration
       public static void method() throws ArithmeticException {
           // "throw" to actually throw the exception
           throw new ArithmeticException("Division by zero");
       }
       
       public static void main(String[] args) {
           try {
               method();
           } catch (ArithmeticException e) {
               System.out.println("Exception caught: " + e.getMessage());
           }
       }
   }
   ```

   ```tex
   Exception caught: Division by zero
   ```

   

6. Run the below three pieces codes, Noticed the printed exceptions. why do we put the Null/Runtime  exception before Exception ?

   ```java
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

   We put `NullPointerException` and `RuntimeException` before `Exception` because Java checks catch blocks from top to bottom. If we catch the general `Exception` first, the more specific exceptions below will never be reached, which causes a compile-time error. So we must catch more specific exceptions first.

   

7. What is **optional**? why do you use it? write an optional example to demo how it avoids `NullPointerExceptions`.

   - **`Optional`**  is a container object in Java introduced in Java 8 to represent a value that may or may not be present. 
   - It helps avoid `NullPointerExceptions` by forcing explicit handling of potentially null values.

   ```java
   import java.util.Optional;
   import java.util.HashMap;
   import java.util.Map;
   
   public class Main {
       
       private static Map<Integer, String> users = new HashMap<>();
       
       // Traditional approach - can return null
       public static String findUserTraditional(Integer id) {
           return users.get(id); // Might return null
       }
       
       // Optional approach - returns an Optional container
       public static Optional<String> findUserOptional(Integer id) {
           return Optional.ofNullable(users.get(id));
       }
       
       public static void main(String[] args) {
           // Initialize our user data
           users.put(1, "Alice");
           users.put(2, "Bob");
           
           // TRADITIONAL APPROACH - Potential NPE
           try {
               Integer userId = 3; // This user doesn't exist
               String user = findUserTraditional(userId);
               System.out.println("User's name length: " + user.length()); // NPE occurs here!
           } catch (NullPointerException e) {
               System.out.println("Traditional approach caused NPE: " + e.getMessage());
           }
           
           // OPTIONAL APPROACH - No NPE
           Integer userId = 3; // This user doesn't exist
           Optional<String> userOptional = findUserOptional(userId);
           
           // Method 1: Using isPresent() and get()
           if (userOptional.isPresent()) {
               System.out.println("User found: " + userOptional.get());
           } else {
               System.out.println("No user found with ID: " + userId);
           }
           
           // Method 2: Using orElse() - provide default value
           String userName = userOptional.orElse("Unknown User");
           System.out.println("User name: " + userName);
           
           // Method 3: Using map() and orElse()
           int nameLength = userOptional
                   .map(name -> name.length())
                   .orElse(0);
           System.out.println("Name length: " + nameLength);
       }
   }
   ```

   ```tex
   Traditional approach caused NPE: Cannot invoke "String.length()" because "<local2>" is null
   No user found with ID: 3
   User name: Unknown User
   Name length: 0
   ```

   

8. What are the types of design patterns in Java? Name popular design patterns, particular Creational Patterns and Structural Patterns.

   **Types of Design Patterns**

   1. **Creational Patterns** - Handle object creation mechanisms
   2. **Structural Patterns** - Establish relationships between objects
   3. **Behavioral Patterns** - Focus on communication between objects

   **Popular Creational Patterns**

   1. **Singleton** - Ensures a class has only one instance
   2. **Factory Method** - Creates objects without specifying exact class
   3. **Abstract Factory** - Creates families of related objects
   4. **Builder** - Constructs complex objects step by step
   5. **Prototype** - Creates new objects by copying existing ones

   **Popular Structural Patterns**

   1. **Adapter** - Allows incompatible interfaces to work together
   2. **Decorator** - Adds responsibilities to objects dynamically
   3. **Composite** - Composes objects into tree structures
   4. **Proxy** - Represents another object
   5. **Facade** - Provides simplified interface to a complex subsystem
   6. **Bridge** - Separates abstraction from implementation
   7. **Flyweight** - Minimizes memory usage by sharing common parts of objects

   Each pattern solves specific design problems and promotes code reusability, maintainability, and flexibility.

   

9. Implement Singleton, Factory, and Builer patterns, explain how to guarantee thread-safe in your singleton pattern implementation.

   **Singleton Pattern (Thread-safe)**

   ```java
   // Thread-safe Singleton with Double-Checked Locking
   public class Singleton {
       private static volatile Singleton instance;
       
       private Singleton() {}
       
       public static Singleton getInstance() {
           if (instance == null) {
               synchronized (Singleton.class) {
                   if (instance == null) {
                       instance = new Singleton();
                   }
               }
           }
           return instance;
       }
   }
   
   // Or using Enum (simplest thread-safe implementation)
   public enum SingletonEnum {
       INSTANCE;
       
       public void doSomething() {}
   }
   ```

   **Factory Pattern**

   ```java
   interface Product { void use(); }
   
   class ConcreteProductA implements Product {
       public void use() { System.out.println("Using Product A"); }
   }
   
   class ConcreteProductB implements Product {
       public void use() { System.out.println("Using Product B"); }
   }
   
   class Factory {
       public Product createProduct(String type) {
           if ("A".equals(type)) return new ConcreteProductA();
           if ("B".equals(type)) return new ConcreteProductB();
           return null;
       }
   }
   ```

   **Builder Pattern**

   ```java
   class Product {
       private String partA, partB;
       
       private Product(Builder builder) {
           this.partA = builder.partA;
           this.partB = builder.partB;
       }
       
       public static class Builder {
           private String partA = "";
           private String partB = "";
           
           public Builder partA(String partA) {
               this.partA = partA;
               return this;
           }
           
           public Builder partB(String partB) {
               this.partB = partB;
               return this;
           }
           
           public Product build() {
               return new Product(this);
           }
       }
   }
   
   // Usage: new Product.Builder().partA("A").partB("B").build();
   ```

   **Thread-Safety in Singleton**

   - **Double-Checked Locking**: Uses volatile to prevent reordering, synchronized block for thread safety

   - **Enum Approach**: Thread-safe by JVM guarantee, prevents reflection/serialization attacks

   - **Eager Initialization**: Class loading guarantees thread safety, but creates instance even if unused

   - **Static Inner Class**: Lazy loading with thread safety via class loading mechanism

     

10. Explain **SOLID** Principles ? Further explain **Open-Closed Principle (OCP)**?

    **SOLID** is an acronym for five design principles in object-oriented programming intended to make software designs more understandable, flexible, and maintainable:

    1. **S** - Single Responsibility Principle (SRP)
       - A class should have only one reason to change
       - Each class should have a single responsibility or purpose
    2. **O** - Open-Closed Principle (OCP)
       - Software entities should be open for extension but closed for modification
       - You should be able to add new functionality without changing existing code
    3. **L** - Liskov Substitution Principle (LSP)
       - Objects of a superclass should be replaceable with objects of a subclass without affecting program correctness
       - Subtypes must be substitutable for their base types
    4. **I**- Interface Segregation Principle (ISP)
       - Clients should not be forced to depend on interfaces they don't use
       - Many specific interfaces are better than one general interface
    5. **D** - Dependency Inversion Principle (DIP)
       - High-level modules should not depend on low-level modules; both should depend on abstractions
       - Abstractions should not depend on details; details should depend on abstractions

    The **Open-Closed Principle** states that software entities (classes, modules, functions) should be:

    - **Open for extension**: You can add new functionality
    - **Closed for modification**: You don't modify existing, tested, working code

    

11. Liskov's substitution principle states that if class B is a subtype of class A, then object of type A may be  substituted with any object of type B. What does this actually mean? (from OA ) choose your answer.

    - [x] It mean that if the object of type A can do something, the object of type B could also be able to  perform the same thing.
    - [ ] It means that all the objects of type A could execute all the methods present in its subtype B.
    - [ ] It means if a method is present in class A, it should also be present in class B so that the object of  type B could substitute object of type A.
    - [ ] It means that for the class B to inherit class A, objects of type B and objects of type A must be same.
