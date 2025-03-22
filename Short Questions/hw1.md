## Short Questions
### 1. Write up Example code to demonstrate the three foundmental concepts of OOP.
   - Encapsulation: bundle variables and methods into a single class. Hide data by using private variables and controlled access via getter and setter.
   - Inheritance: Allows a subclass interit properties and behaviors from superclass. Java allows extend only single abstract class or regular concrete class, but allows implements multiple interfaces. 
   - Polymorphism: Realized by compile time polymorphism overload and runtime polymorphism override.
   ```java
   // Inheritance
   // Encapsulation: bundle variables and methods into one class
   public class Dog extends Animal {
     private String breed; // Encapsulation: Hide data by useing private fields

     public Dog(String name, String breed) {
       super(name);
       this.breed = breed;
     }

     public String getBreed() { // Encapsulation: control access to private fields by setter and getter
       return this.breed;
     }

     public void setBreed(String breed) {
       this.breed = breed;
     }

     @Override // Runtime Polymorphism
     public void makeSound() {
       System.out.println(name + " is barking: Wof! Wof!");
     }
     
     // Overload - Compile-time Polymorphism
     public void makeSound(int times) {
       for (int i = 0; i < times; ++i) {
         System.out.println("Wolf!");
       }
     }
   }
   ```

### 2. What is wrapper data type classes (e.g. Integer, Double) in Java and Why we need wrapper class?
  - Wrapper classes are the Object represetation of primitive data types. Totally has 8 primitive types and 8 corresponding wrapper classes.
  - Why use?
    - Enable primitives be used in Collections where only accept Object types
    - Primitives cannot be null, but wrapper class can be, good for scenarios where missing value needed
    - Wrapper classes have more methods and untilities
    - Autoboxing and unboxing
  ```java
  public static void main(String[] args) {
    int a = 5;
    List<Integer> nums = new ArrayList<>(); // Use primitives in Collection 
    nums.add(a); // Autoboxing
    int b = nums.get(0); // Autounboxing
    
    // Scenarios when missing value is needed
    Integer result = null;
    if (result == null) {
      result = 40;
    } else {
      System.out.println("Result: " + result);
    }
    System.out.println("Final result: " + result);
  }
  ```

### 3. What is the difference between HashMap and HashTable?
- Both key-value data structures
  
| HashMap | Hashtable |
|---------|-----------|
Not thread-safe, must use `ConcurrentHashMap` if needed | Thread-safe, locks the whole table |
| Allows one `null` key, multiple `null` values | Does **not** allow `null` for both key and value |
| Faster (no lock overhead) | Slower  |
| For single-threaded applications | For multi-threaded applications requiring safe access |

```java
public class Main {
  public static void main(String[] args) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(null, null); // Works
    map.put(1, 2);
    Map<Integer, Integer> table = new Hashtable<>();
    System.out.println(map);  // {null=null, 1=2}

    try {
      table.put(null, null);
    } catch (Exception e) {
      System.out.println(e); // NullPointerException
    }
    System.out.println(table); // {}
  }
}
```

### 4. What is String pool in Java and why we need String pool? Explain String immunity.
   - String Pool is an area in heap memory to store String literals to optimize memory usage.
   - Why need String Pool
     - Memory efficiency and performance optimizaion 
   - String immutability means that once a String object is created, it cannot be changed.
   ```java
    package hw1;
    public class Main {
      public static void main(String[] args) {
        String s1 = "hello"; 
        String s2 = "hello";  
        System.out.println(s1 == s2);  // true (same memory reference)

        String s3 = new String("hello"); // New object in Heap, NOT in the pool
            System.out.println(s1 == s3);  // false (different memory references)
        
        String s4 = s3.intern(); // Adding s3 to the String Pool manually
        System.out.println(s1 == s4);  // true (same memory references with s1 and s2)
      }
    }
  ```
  
### 5. Explain garbage collection? Explain types of garbage collection.
   - A memroy management process in Java to remove unused and unreachable objects from memroy to release space and imporove performace.
   - Types
     - Serial GC: Use a single thread to perform GC, `stop-the-world` pause
     - Parallel GC: Use multiple threads to perform GC, still STW pause
     - G1 GC (Garbage first): Divides heap into regions, identify and collect the regions with the most grabage.
     - ZGC: Fully concurrenct GC, move objects without STW pauses, use colored pointers for tracking.
     - Shenandoah GC: Concurrent GC with relocation phase.
  
      ```java
      package hw1;

      public class Sample {
        // Called when the object is garbage collected
        @Override
        protected void finalize() throws Throwable {
            System.out.println("Garbage Collected!");
        }
        
        public static void main(String[] args) {
            // Step 1: Create an object
            Sample obj = new Sample();
            // Step 2: Free reference (eligible for GC)
            obj = null;
            // Step 3: Request Garbage Collection
            System.gc();
            System.out.println("Garbage Collection requested.");
        }
      }
      ```

6. What are access modifiers and their scopes in Java?
   
| Access Modifier | Scope |
|----------------|-----------|
| public | Accessible anywhere |
| protected | Accessible within the same package and subclasses |
| default (no modifier) | Accessible within the same package |
| private | Accessible only within the same class |
```java
package hw1;

public class AccessModifiers {
    private int privateVar = 10;      // Private: Accessible only in this class
    int defaultVar = 20;              // Default: Accessible in the same package
    protected int protectedVar = 30;  // Protected: Accessible in the same package + subclasses
    public int publicVar = 40;        // Public: Accessible everywhere

    private void privateMethod() {
        System.out.println("Private Method Calls");
    }

    void defaultMethod() {
        System.out.println("Default Method Calls");
    }

    protected void protectedMethod() {
        System.out.println("Protected Method Calls");
    }

    public void publicMethod() {
        System.out.println("Public Method Calls");
    }

    // Method to access private members within the class
    public void showPrivate() {
        System.out.println("Private Variable: " + privateVar);
        privateMethod();
    }
}
```
```java
package hw1;

public class SamePackageTest {
    public static void main(String[] args) {
        AccessModifiers obj = new AccessModifiers();

        // Accessible
        System.out.println("Public Variable: " + obj.publicVar);
        System.out.println("Protected Variable: " + obj.protectedVar);
        System.out.println("Default Variable: " + obj.defaultVar);
        obj.publicMethod();
        obj.protectedMethod();
        obj.defaultMethod();

        // Private member not accessible
        // System.out.println("Private Variable: " + obj.privateVar); // Error
        // obj.privateMethod(); // Error

        obj.showPrivate(); // Accessing private via a public method
    }
}
```
```java
package hw1Diff;
import hw1.AccessModifiers;

public class DifferentPackageTest extends AccessModifiers{

  public static void main(String[] args) {
    AccessModifiers obj = new AccessModifiers();
    // Public members are accessible
    System.out.println("Public Variable: " + obj.publicVar);
    obj.publicMethod();

    // Protected members are accessible only thorugh inheritance
    DifferentPackageTest childObj = new DifferentPackageTest();
    System.out.println("Protected Variable (via subclass): " + childObj.protectedVar);
    childObj.protectedMethod();

    // Protected, Default, and Private members are NOT accessible directly
    // System.out.println("Protected Variable: " + obj.protectedVar); // Error
    // System.out.println("Default Variable: " + obj.defaultVar); // Error
    // System.out.println("Private Variable: " + obj.privateVar); // Error
    // obj.protectedMethod(); // Error
    // obj.defaultMethod(); // Error
    // obj.privateMethod(); // Error
  }
}
```

7. Explain final key word?
   - final variables cannot be reassigned once initialized
   - final methods cannot be overridden by subclasses
   - final classes cannot be extended by other classes
  
   ```java
   package hw1;

   public class FinalModifier {
     class Parent {
       public final void show() {
         System.out.println("Final Method in Parent");
       }
     }
     class Child extends Parent {
       // @Override
       // public final void show() {
       //   System.out.println("Trying to override final method"); 
       // }
       // Error: show() in FinalModifier.Child cannot override show() in FinalModifier.Parent overridden method is final
     }
     final class FinalClass {}
     // class TryChild extends FinalClass {} 
     // Error: cannot inherit from final FinalModifier.FinalClass
     
     public static void main(String[] args) {
         final String str = "abc";
         // str = "bcd"; // Error: cannot assign a value to final variable str

     }
   }
  
8. Explan static keyword? When do we usually use it?
   - static variables: Shared by all instances, belongs to class.
   - static methods: Can be called without creating object, no access to non-static members.
   - static block: Execute once when class is loaded, execute before contructor and instance initialization.
   - static class: Only inner class can be static, no access to non-staic memebers
     ```java
       package hw1;

       public class StaticExample {
         static double pi = 3.14;
         static void 
         public static void main(String[] args) {
           System.out.println(StaticExample.pi);
         }
       }
     ```
     ```java
     package hw1;

     // public static class StaticExample { // Error: modifier static not allowed here
     public class StaticExample {
       static double pi = 3.14;
       private int ins = 1;

       static {
         System.out.println("Static block Executed.");
       }

       static void show() {
         System.out.println("Call static method.");
       }

       static class StaticNestedClass {
         void display() {
           System.out.println("Inside Static Nested Class");
           // System.out.println(ins); // Error: non-static variable ins cannot be referenced from a static context
         }
       }
       public static void main(String[] args) {
         System.out.println(StaticExample.pi);
         StaticExample.show();
         StaticNestedClass stc = new StaticNestedClass();
         stc.display();
       }
     }

9. What is the differences between overriding and overloading
    
   | Feature  | Override | Overload |
   |----------|---------|---------|
   | **Usage** | Used in subclasses to override superclass methods | Used within the same class |
   | **Method Signature** | Must have the same method signature as the superclass method | Methods have the same name but different parameter lists (number, type, or order) |
   | **Return Type** | Must be the same (or covariant) | Can be different |
   | **Access Modifier** | Must be the same or less restrictive than the superclass method | Can have any access modifier |

     ```java
      @Override // Runtime Polymorphism
      public void makeSound() {
        System.out.println(name + " is barking: Wof! Wof!");
      }
      
      // Overload - Compile-time Polymorphism
      public void makeSound(int times) {
        for (int i = 0; i < times; ++i) {
          System.out.println("Wolf!");
        }
      }
   ```

10. Explain how Java defines a method signature, and how it helps on overloading and overriding.
  - Method Signature in Java consists of name and parameter list(type, number, order)
  - Application in Overloading and Overriding
    - Valid Overloading → Each method has a unique method signature.(Same method name, different parameter list)
    - Method Overriding → Same method signature in subclass
    ```java
    class OverloadExample {
        void add(int a, int b) {  // Method Signature: add(int, int)
            System.out.println("Sum: " + (a + b));
        }

        void add(double a, double b) {  // Method Signature: add(double, double)
            System.out.println("Sum: " + (a + b));
        }
    }
    ```
    ```java
    class Parent {
        void show() {  // Method Signature: show()
            System.out.println("Parent Method");
        }
    }

    class Child extends Parent {
        @Override
        void show() {  // Method Signature: show() → SAME as Parent class
            System.out.println("Child Method");
        }
    }
    ```

11. What is the differences between super and this?
    
    | Feature   | this                              | super                                |
    |-----------|----------------------------------|--------------------------------------|
    | Used In   | Refers to current class         | Refers to superclass (parent class) |
    | Accesses  | Current class variables, methods, and constructors | Superclass variables, methods, and constructors |

    ```java
    class Parent {
        String name = "Parent Class";

        void show() {
            System.out.println("Super Class Method");
        }
    }

    class Child extends Parent {
        String name = "Child Class";

        void display() {
            System.out.println("Using this: " + this.name);   // Refers to current class variable
            System.out.println("Using super: " + super.name); // Refers to superclass variable
        }

        void showMethods() {
            this.show();  // Calls current class method (if overridden)
            super.show(); // Calls parent class method
        }
    }

    public class Test {
        public static void main(String[] args) {
            Child obj = new Child();
            obj.display();
            obj.showMethods();
        }
    }
    ```

12.  Explain how equals and hashCode work.
  - `equals()` used for object comparision. Must be overridden based on exact class.
  - `hashcode()` generates a unique integer (hash) for an object.
  
     ```java
     class Person {
         String name;

         Person(String name) {
             this.name = name;
         }

         @Override
         public boolean equals(Object obj) {
             if (this == obj) return true;
             if (obj == null || getClass() != obj.getClass()) return false;
             Person person = (Person) obj;
             return name.equals(person.name);
         }

         @Override
         public int hashCode() {
             return Objects.hash(name); 
         }
     }

     public class HashCodeExample {
         public static void main(String[] args) {
             Person p1 = new Person("Alice");
             Person p2 = new Person("Alice");

             System.out.println(p1.equals(p2)); // true
             System.out.println(p1.hashCode() == p2.hashCode()); // true
         }
     }
     ```
13.  What is the Java load sequence?
     1.  Load class file into memory
     2.  JVM verifies, prepares and resolves class dependencies
     3.  Execute static blocks and static variables once
     4.  Execute instance variables and constructors
    
      ```java
      class Load {
          static int x = initializeVar();
          
          static {
              System.out.println("Static Block Executed");
          }

          static int initializeVar() {
              System.out.println("Static Variable Initialized");
              return 10;
          }
      }

      public class InitExample {
          public static void main(String[] args) {
              System.out.println("Main Method");
              Load obj = new Load(); 
          }
      } 
      // Output: Static Variable Initialized // Static Block Executed // Main Method
      ```
    
14.  What is Polymorphism ? And how Java implements it ?
  - Realized by compile time polymorphism overload and runtime polymorphism override.
  
     ```java
     // Overload
     class MathOperations {
      public int add(int a, int b) { return a + b; }
      public double add(double a, double b) { return a + b; }
     }
     // Override
     class Animal { void makeSound() { System.out.println("Animal sound"); } }
     class Dog extends Animal { @Override void makeSound() { System.out.println("Woof!"); } }
     ```

15.  What is Encapsulation ? How Java implements it? And why we need encapsulation?
  - Bundle variables and methods into a single class. Hide data by using private variables and controlled access via getter and setter.

     ```java
       class Person {
         private String name;  // Private variable (data hiding)

         public Person(String name) {
             this.name = name;
         }

         // Getter method to access private variable
         public String getName() {
             return name;
         }

         // Setter method to modify private variable
         public void setName(String name) {
             this.name = name;
         }
     }
     ```

16.  Compare interface and abstract class with use cases.
- Both are used to achieve abstraction
  
  | Feature             | Interface                                           | Abstract Class                                  |
  |---------------------|---------------------------------------------------|-----------------------------------------------|
  | **Definition** | Only have abstract methods (Java 8+, supports `default` & `static` methods) | Can have both abstract and concrete methods |
  | **Fields/Variables** | Only `public static final`                        | Can have instance variables with any access modifier |
  | **Constructors**    | No constructors                                    | Can have constructors                        |
  | **Multiple Inheritance** | Allowed                                       | Not Allowed                                  |
  | **Use Case**        | Defines a **contract** that multiple classes must follow | Provides a **base class** with common behavior that subclasses inherit |

  ```java
  interface Vehicle {
      int MAX_SPEED = 120;  // Public static final variable
      void start();  // Abstract method without implementation
      default void stop() { // Default method
          System.out.println("Vehicle stopped.");
      }
  }

  abstract class Car {
      protected String brand;  // Instance variable
      public Car(String brand) { // Constructor allowed
          this.brand = brand;
      }
      abstract void accelerate();  // Abstract method
      public void displayBrand() {  // Concrete method
          System.out.println("Car brand: " + brand);
      }
  }

  // Concrete class implementing the interface and extending the abstract class
  class Tesla extends Car implements Vehicle {
      public Tesla(String brand) {
          super(brand);
      }
      @Override
      public void start() { 
          System.out.println(brand + " is starting with an electric engine.");
      }
      @Override
      public void accelerate() {
          System.out.println(brand + " accelerates silently!");
      }
  }
  ```
