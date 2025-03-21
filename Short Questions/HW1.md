# Java OOP & Core Concepts Questions

## 1. Write up Example code to demonstrate the three fundamental concepts of OOP.

 - **Encapsulation:** Protecting data with private fields and public getters/setters.
 - **Polymorphism:** Allowing objects to take many forms through method overriding.
 - **Inheritance:** Enabling new classes to acquire properties and behaviors of existing classes.

### a. Encapsulation

Encapsulation involves wrapping data (variables) and code (methods) together as a single unit. In Java, this is achieved by making class fields private and providing public getter and setter methods.

```java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }
}
```

### b. Polymorphism  

Polymorphism allows methods to do different things based on the object that is invoking them. This is typically achieved via method overriding in Java.

```java
class Animal {
    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow");
    }
}

public class TestPolymorphism {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        Animal myCat = new Cat();

        myDog.makeSound();
        myCat.makeSound();
    }
}
```

### c. Inheritance  

Inheritance enables a new class to inherit properties and methods from an existing class. The new class (subclass) extends the functionality of the parent class.

```java
class Vehicle {
    protected String brand = "Ford";

    public void honk() {
        System.out.println("Beep, beep!");
    }
}

class Car extends Vehicle {
    private String modelName = "Mustang";

    public void display() {
        System.out.println("Brand: " + brand + " Model: " + modelName);
    }
}

public class TestInheritance {
    public static void main(String[] args) {
        Car myCar = new Car();

        myCar.honk();

        myCar.display();
    }
}
```

## 2. What is wrapper data type classes (e.g. Integer, Double) in Java and Why we need wrapper class?

Wrapper classes in Java are object representations of the primitive data types. For every primitive type, such as `int`, `double`, `char`, etc., there is a corresponding wrapper class (`Integer`, `Double`, `Character`, etc.). These classes serve multiple purposes:

1. **Object Representation:**  
   Collections (e.g., `ArrayList`, `HashMap`) can only store objects. Wrapper classes allow primitive values to be stored in such collections.

2. **Utility Methods:**  
   Wrapper classes provide methods for converting between types, parsing strings to numbers, comparing values, and more. For example, `Integer.parseInt(String)` converts a string to an integer.

3. **Autoboxing and Unboxing:**  
   Java automatically converts between primitives and their corresponding wrapper classes. This feature, known as autoboxing (primitive to wrapper) and unboxing (wrapper to primitive), simplifies code and enhances readability.

4. **Immutability:**  
   Wrapper objects are immutable, meaning once created, their values cannot be changed. This is particularly useful in contexts where objects need to be shared safely across different parts of a program.

---

### Sample Code Demonstration

```java
import java.util.ArrayList;

public class WrapperExample {
    public static void main(String[] args) {
        Integer num = 10;
        
        int n = num;

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        
        for (Integer number : numbers) {
            System.out.println("Number: " + number);
        }
        
        String strNumber = "123";
        int parsedNumber = Integer.parseInt(strNumber);
        System.out.println("Parsed Number: " + parsedNumber);
    }
}
```

## 3. What is the difference between HashMap and HashTable?

HashMap and Hashtable are both implementations of the Map interface, but they have several key differences:

1. **Synchronization:**  
   - **HashMap:** Not synchronized, making it faster for non-threaded applications.  
   - **Hashtable:** Synchronized, which makes it thread-safe but slower in single-threaded scenarios.

2. **Null Keys and Values:**  
   - **HashMap:** Allows one null key and multiple null values.  
   - **Hashtable:** Does not allow null keys or null values.

3. **Legacy:**  
   - **HashMap:** Introduced in Java 1.2 as part of the Collections Framework.  
   - **Hashtable:** A legacy class that predates the Collections Framework.

4. **Performance:**  
   - **HashMap:** Generally offers better performance due to lack of synchronization overhead.  
   - **Hashtable:** May be slower because all its methods are synchronized.

---

### Sample Code Demonstration

The following example demonstrates the usage of both `HashMap` and `Hashtable`:

```java
import java.util.HashMap;
import java.util.Hashtable;

public class MapComparison {
    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Apple", 10);
        hashMap.put("Banana", 20);
        hashMap.put(null, 30);
        hashMap.put("Cherry", null);

        System.out.println("HashMap contents:");
        for (String key : hashMap.keySet()) {
            System.out.println("Key: " + key + ", Value: " + hashMap.get(key));
        }

        Hashtable<String, Integer> hashTable = new Hashtable<>();
        hashTable.put("Apple", 10);
        hashTable.put("Banana", 20);

        System.out.println("\nHashtable contents:");
        for (String key : hashTable.keySet()) {
            System.out.println("Key: " + key + ", Value: " + hashTable.get(key));
        }
    }
}
```

## 4. What is String pool in Java and why we need String pool? Explain String immunity.
### What is the String Pool?

The **String pool** (also known as the intern pool) is a special area in the Java heap memory where string literals are stored. When you create a string literal (e.g., `"Hello"`), Java checks if the same literal already exists in the pool. If it does, the reference to the existing literal is returned. If not, the new literal is added to the pool.

### Why Do We Need the String Pool?

- **Memory Efficiency:**  
  Reusing immutable string objects reduces memory overhead. Instead of creating multiple objects with the same value, Java reuses the single instance from the pool.

- **Performance:**  
  Comparing strings using `==` can be faster if they are from the pool because it checks reference equality rather than performing a full character-by-character comparison.

### String Immutability

**String immutability** means that once a `String` object is created, its value cannot be changed. This property is essential for several reasons:

- **Thread Safety:**  
  Since strings cannot be modified, they are inherently thread-safe. Multiple threads can share the same string instance without synchronization concerns.

- **Security and Consistency:**  
  Immutability prevents accidental or malicious changes to the string value, which is particularly important when strings are used as keys in data structures like HashMap.

- **Facilitates String Pooling:**  
  The immutability of strings guarantees that the pooled strings remain constant, so they can safely be shared across different parts of a program.

---

### Sample Code Demonstration

The following code demonstrates the use of the String pool and the concept of string immutability:

```java
public class StringPoolExample {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "Hello";

        System.out.println("s1 == s2: " + (s1 == s2)); // true

        String s3 = new String("Hello");
        System.out.println("s1 == s3: " + (s1 == s3)); // false
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // true

        s3 = s3.intern();
        System.out.println("After interning, s1 == s3: " + (s1 == s3)); // true

        String original = "Immutable";
        String modified = original.replace("Immutable", "Changed");
        System.out.println("Original String: " + original); // Immutable
        System.out.println("Modified String: " + modified); // Changed
    }
}
```

## 5. Explain garbage collection? Explain types of garbage collection.

Garbage collection (GC) is the process by which the Java Virtual Machine (JVM) automatically reclaims memory by removing objects that are no longer accessible. This relieves developers from manual memory management and helps prevent memory leaks.

### How Garbage Collection Works

- **Automatic Memory Management:**  
  The JVM tracks object references and automatically frees memory occupied by objects that are no longer reachable.

- **Generational Approach:**  
  Modern JVMs typically use a generational model that divides the heap into:
  - **Young Generation:** Where new objects are created. It includes the Eden space and Survivor spaces.
  - **Old Generation (Tenured):** Where objects that have survived several garbage collection cycles are promoted.
  - **Permanent Generation/Metaspace:** Stores class metadata (in Java 8 and earlier as PermGen, later replaced by Metaspace).

---

### Types of Garbage Collection Algorithms

1. **Serial Garbage Collector:**
   - **Characteristics:**  
     Uses a single thread for garbage collection.  
     Performs stop-the-world pauses where all application threads are suspended.
   - **When to Use:**  
     Best suited for small applications or single-threaded environments.
   
2. **Parallel Garbage Collector (Throughput Collector):**
   - **Characteristics:**  
     Uses multiple threads to perform garbage collection in the young generation.  
     Aims to maximize overall throughput by reducing the total time spent on GC.
   - **When to Use:**  
     Suitable for multi-threaded applications where high throughput is more important than short pause times.

3. **Concurrent Mark Sweep (CMS) Collector:**
   - **Characteristics:**  
     Designed to reduce pause times by performing most of the garbage collection work concurrently with the application threads.  
     Involves phases like initial mark, concurrent mark, remark, and sweep.
   - **When to Use:**  
     Useful for applications requiring low latency and responsiveness.

4. **Garbage First (G1) Collector:**
   - **Characteristics:**  
     Divides the heap into regions and collects regions with the most garbage first, thereby trying to meet a specified pause-time target.  
     Works concurrently and incrementally to balance throughput and pause times.
   - **When to Use:**  
     Ideal for large heaps and applications that need predictable pause times.

5. **Advanced Collectors (ZGC, Shenandoah):**
   - **Characteristics:**  
     Designed for very low pause times even with large heap sizes.  
     Perform most garbage collection work concurrently with application threads.
   - **When to Use:**  
     Suitable for high-performance applications where minimizing GC pauses is critical.

---

### Sample Code Demonstration

The following Java code creates objects that become eligible for garbage collection. Although calling `System.gc()` only suggests to the JVM to run the garbage collector (it’s not guaranteed), it illustrates how objects can be made eligible for collection.

```java
public class GarbageCollectionDemo {
    public void createGarbage() {
        for (int i = 0; i < 10000; i++) {
            String temp = new String("Garbage");
        }
    }

    public static void main(String[] args) {
        GarbageCollectionDemo demo = new GarbageCollectionDemo();
        demo.createGarbage();
        
        System.gc();
        
        System.out.println("Garbage Collection requested.");
    }
}
```

## 6. What are access modifiers and their scopes in Java?

Access modifiers in Java determine the visibility of classes, methods, and variables. They are essential for encapsulation and for controlling how different parts of your program interact. Java provides four access modifiers:

1. **public**  
   - **Scope:** Accessible from any class in any package.
   - **Usage:** Use for classes, methods, or variables that need to be widely available.

2. **protected**  
   - **Scope:** Accessible within the same package and in subclasses (even if they are in different packages).
   - **Usage:** Ideal for allowing subclass access while still restricting access from non-related classes.

3. **default (package-private)**  
   - **Scope:** Accessible only within the same package. (No explicit modifier is used.)
   - **Usage:** Use for members that should only be accessible to other classes in the same package.

4. **private**  
   - **Scope:** Accessible only within the class where it is declared.
   - **Usage:** Use for members that should not be accessible outside the class, promoting encapsulation.

---

### Sample Code Demonstration

Below is a sample Java code snippet that demonstrates the use of different access modifiers:

```java
package com.example.access;

// Public class: accessible from any package.
public class AccessDemo {
    // Public variable: accessible from anywhere.
    public int publicVar = 10;
    
    // Protected variable: accessible within the package and by subclasses.
    protected int protectedVar = 20;
    
    // Default (package-private) variable: accessible only within this package.
    int defaultVar = 30;
    
    // Private variable: accessible only within this class.
    private int privateVar = 40;
    
    // Public method: accessible from anywhere.
    public void publicMethod() {
        System.out.println("Public Method");
    }
    
    // Protected method: accessible within the package and subclasses.
    protected void protectedMethod() {
        System.out.println("Protected Method");
    }
    
    // Default method: accessible only within this package.
    void defaultMethod() {
        System.out.println("Default Method");
    }
    
    // Private method: accessible only within this class.
    private void privateMethod() {
        System.out.println("Private Method");
    }
    
    public static void main(String[] args) {
        AccessDemo demo = new AccessDemo();
        
        // Accessing variables within the same class.
        System.out.println("Public Var: " + demo.publicVar);
        System.out.println("Protected Var: " + demo.protectedVar);
        System.out.println("Default Var: " + demo.defaultVar);
        System.out.println("Private Var: " + demo.privateVar);
        
        // Invoking methods within the same class.
        demo.publicMethod();
        demo.protectedMethod();
        demo.defaultMethod();
        demo.privateMethod();
    }
}
```
## 7. Explain `final` keyword (Field, Method, Class)

The `final` keyword in Java can be applied to fields (variables), methods, and classes. It is used to restrict modification or extension in various ways:

### Final Fields (Variables)

Declaring a variable as `final` means that its value can only be assigned once. After initialization, the value cannot be changed.

```java
public class FinalFieldExample {
    // Final instance variable: must be initialized here or in the constructor.
    final int constantValue = 100;

    // Final variable without initialization: must be assigned in the constructor.
    final int anotherConstant;

    public FinalFieldExample(int value) {
        anotherConstant = value; // Assignment happens only once.
    }

    public void tryToChangeValue() {
        // The following line would cause a compile-time error:
        // constantValue = 200;
    }

    public static void main(String[] args) {
        FinalFieldExample example = new FinalFieldExample(50);
        System.out.println("constantValue: " + example.constantValue);
        System.out.println("anotherConstant: " + example.anotherConstant);
    }
}
```

### Final Methods

A final method cannot be overridden by any subclasses. This is useful when you want to guarantee that the implementation of the method remains unchanged.

```java
class BaseClass {
    // Final method: cannot be overridden by any subclass.
    public final void displayMessage() {
        System.out.println("This is a final method in BaseClass.");
    }
}

class DerivedClass extends BaseClass {
    // Attempting to override the final method below would cause a compile-time error.
    /*
    @Override
    public void displayMessage() {
        System.out.println("Trying to override a final method.");
    }
    */
}

public class FinalMethodDemo {
    public static void main(String[] args) {
        DerivedClass obj = new DerivedClass();
        obj.displayMessage(); // Calls the final method from BaseClass.
    }
}
```

### Final Classes

A final class cannot be subclassed. This ensures that the class’s implementation remains unaltered by preventing inheritance.

```java
// Final class: cannot be extended.
public final class FinalClass {
    public void showMessage() {
        System.out.println("This is a final class. It cannot be subclassed.");
    }
}

// The following code would result in a compile-time error:
// public class SubClass extends FinalClass {
// }

public class FinalClassDemo {
    public static void main(String[] args) {
        FinalClass finalObj = new FinalClass();
        finalObj.showMessage();
    }
}
```

## 8. Explain `static` keyword (Field, Method, Class). When do we usually use it?

The `static` keyword in Java is used to define class-level members that belong to the class itself rather than to any particular instance. It can be applied to fields (variables), methods, and nested classes. Below, we explain each use and provide sample code.

### Static Fields (Variables)

- **Definition:**  
  A static field is shared across all instances of the class. Only one copy exists regardless of how many objects are created.

- **When to Use:**  
  Use static fields for constants or to maintain common data, such as counters or configuration settings, that should be the same across all instances.

- **Sample Code:**
  ```java
  public class StaticFieldExample {
      // Static variable shared by all instances.
      static int counter = 0;

      public StaticFieldExample() {
          // Increment the counter each time a new instance is created.
          counter++;
      }

      public static void main(String[] args) {
          new StaticFieldExample();
          new StaticFieldExample();
          new StaticFieldExample();
          // All instances share the same counter value.
          System.out.println("Number of instances created: " + StaticFieldExample.counter);
      }
  }
  ```

### Static Methods
- **Definition:**  
  A static method belongs to the class rather than to any instance. It can be called without creating an object and can only access static members directly.

- **When to Use:**  
  Use static methods for utility or helper functions that perform operations independent of instance state (e.g., mathematical computations).

- **Sample Code:**
  ```java
  public class StaticMethodExample {
    // Static method to perform a calculation.
    public static int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        // Calling the static method without an instance.
        int result = StaticMethodExample.add(5, 10);
        System.out.println("Result of addition: " + result);
    }
  }
  ```

### Static Nested Classes (Static Inner Classes)
- **Definition:**  
  A static nested class is a static class defined within another class. It does not require an instance of the outer class to be instantiated.

- **When to Use:**  
  Use static nested classes when you have a class that is only relevant within the context of its outer class but does not need access to the outer class’s instance members.

- **Sample Code:**
  ```java
  public class OuterClass {
    // Static nested class.
    public static class StaticNestedClass {
        public void display() {
            System.out.println("Inside static nested class");
        }
    }

    public static void main(String[] args) {
        // Instantiating the static nested class without an instance of OuterClass.
        OuterClass.StaticNestedClass nestedObject = new OuterClass.StaticNestedClass();
        nestedObject.display();
    }
  }
  ```

### When Do We Usually Use static?
- **Constants:**
Use static final to define constant values (e.g., public static final int MAX_VALUE = 100;) that remain the same for all instances.
- **Utility Methods:**
Implement helper methods that do not depend on instance variables (e.g., methods in the Math class).
- **Shared Data:**
Use static fields for data that should be shared across all instances, such as counters, caches, or configuration settings.
- **Nested Classes:**
Use static nested classes for better organization when the nested class does not need to access instance members of the outer class.

  By using the static keyword appropriately, you can design your Java applications to be more efficient, modular, and easier to understand.


## 9. What is the difference between overriding and overloading?

Overriding and overloading are two key concepts in Java that enable polymorphism, but they are applied in different scenarios.

### Method Overriding

**Definition:**  
Overriding occurs when a subclass provides a specific implementation for a method that is already defined in its superclass. This allows the subclass to modify or extend the behavior of the superclass method.

**Key Points:**
- The method name, return type, and parameter list must be identical to the overridden method.
- It supports **runtime polymorphism**.
- The `@Override` annotation is commonly used to indicate that a method is overriding a superclass method.

**Example:**
```java
// Superclass
class Animal {
    public void sound() {
        System.out.println("Generic animal sound");
    }
}

// Subclass overriding the sound method
class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Bark");
    }
}

public class TestOverriding {
    public static void main(String[] args) {
        Animal myAnimal = new Dog(); // Runtime polymorphism in action
        myAnimal.sound(); // Outputs: Bark
    }
}
```

### Method Overloading

**Definition:**  
Overloading occurs when multiple methods in the same class have the same name but different parameter lists (different number, type, or order of parameters). It enables methods to perform similar functions with different inputs.

**Key Points:**
- TThe method name remains the same, but the parameter list differs.
- It supports **compile-time polymorphism**.
- The return type can vary, but it does not contribute to overloading decisions.

**Example:**
```java
class Calculator {
    // Overloaded method with two integer parameters
    public int add(int a, int b) {
        return a + b;
    }

    // Overloaded method with two double parameters
    public double add(double a, double b) {
        return a + b;
    }

    // Overloaded method with three integer parameters
    public int add(int a, int b, int c) {
        return a + b + c;
    }
}

public class TestOverloading {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println("Addition of 2 ints: " + calc.add(10, 20));
        System.out.println("Addition of 2 doubles: " + calc.add(10.5, 20.5));
        System.out.println("Addition of 3 ints: " + calc.add(10, 20, 30));
    }
}
```

### Summary
**Overriding:**
 - Involves inheritance.
 - A subclass method has the same signature as its superclass method.
 - Enables runtime polymorphism.

**Overloading:**
 - Occurs within the same class.
 - Methods share the same name but differ in their parameter lists.
 - Enables compile-time polymorphism.

## 10. Explain how Java defines a method signature, and how it helps on overloading and overriding.

In Java, a **method signature** is defined by the method name and the list of parameter types (including their order and number). The signature does **not** include the return type, access modifiers, or exceptions thrown. This definition is crucial when it comes to both method overloading and overriding.

### How Method Signature Affects Overloading

**Overloading** allows multiple methods in the same class to share the same name as long as they have different parameter lists. Since the method signature is used by the compiler to distinguish between these methods, each overloaded method must have a unique signature.

**Example of Overloading:**
```java
class Calculator {
    // Method signature: add(int, int)
    public int add(int a, int b) {
        return a + b;
    }

    // Overloaded method with a different parameter list: add(double, double)
    public double add(double a, double b) {
        return a + b;
    }

    // Overloaded method with another unique signature: add(int, int, int)
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println("Addition of 2 ints: " + calc.add(10, 20));
        System.out.println("Addition of 2 doubles: " + calc.add(10.5, 20.5));
        System.out.println("Addition of 3 ints: " + calc.add(10, 20, 30));
    }
}
```
**Explanation:**
Each add method has a unique signature based on the number and type of parameters. This allows the compiler to determine which method to call at compile time.

---

### How Method Signature Affects Overriding

**Overriding** occurs when a subclass provides a specific implementation for a method already defined in its superclass. For a method in a subclass to override a method in its superclass, it must have the **exact same signature**.

**Example of Overloading:**
```java
class Animal {
    // Method signature: sound()
    public void sound() {
        System.out.println("Generic animal sound");
    }
}

class Dog extends Animal {
    // Overriding method must have the same name and parameter list.
    @Override
    public void sound() {
        System.out.println("Bark");
    }
}

public class TestOverriding {
    public static void main(String[] args) {
        Animal myAnimal = new Dog(); // Upcasting Dog to Animal.
        myAnimal.sound(); // Outputs: Bark (calls the overridden method in Dog)
    }
}
```
**Explanation:**
The sound() method in Dog exactly matches the signature of sound() in Animal. This matching signature ensures that at runtime, the Dog version of the method is invoked, demonstrating runtime polymorphism.

---

### Summary
**Method Signature:**
Consists of the method name and the parameter list (types, order, and number). It does not include the return type, access modifiers, or exceptions.

**Overloading:**
Requires methods to have different signatures even though they share the same name, enabling compile-time polymorphism.

**Overriding:**
Requires the subclass method to have the exact same signature as the superclass method, allowing the subclass to modify or extend the behavior of the method, which is essential for runtime polymorphism.

## 11. What is the difference between `super` and `this`?

In Java, `this` and `super` are keywords used to refer to different objects in an inheritance hierarchy:

- **`this`:**  
  - Refers to the current instance of the class.
  - Used to access current class members (variables, methods).
  - Helps resolve naming conflicts between instance variables and parameters.
  - Can be used for constructor chaining within the same class.

- **`super`:**  
  - Refers to the immediate parent (superclass) of the current class.
  - Used to access superclass members (variables, methods) that may be hidden by subclass members.
  - Helps call the parent class's constructor.
  - Useful in method overriding to invoke the parent's implementation.

---

### Sample Code Demonstration

```java
// Parent class
class Animal {
    String name = "Generic Animal";

    // Parent class constructor
    public Animal() {
        System.out.println("Animal constructor called");
    }

    // Method in parent class
    public void display() {
        System.out.println("Animal: " + name);
    }
}

// Child class
class Dog extends Animal {
    String name = "Dog";

    // Child class constructor
    public Dog() {
        // Calling parent class constructor using super()
        super();
        System.out.println("Dog constructor called");
    }

    // Method demonstrating use of 'this' and 'super'
    public void displayNames() {
        // 'this.name' refers to the Dog's instance variable
        System.out.println("This name: " + this.name);
        // 'super.name' refers to the Animal's instance variable
        System.out.println("Super name: " + super.name);
    }

    // Overriding the display method from Animal
    @Override
    public void display() {
        System.out.println("Dog's display method.");
        // Calling the parent class's display method using super
        super.display();
    }
}

public class TestSuperThis {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.displayNames();
        myDog.display();
    }
}
```
### Explanation:
**Using this:**
 - In the displayNames() method, this.name accesses the name field defined in the Dog class.

**Using super:**
 - In the displayNames() method, super.name accesses the name field from the Animal class.
 - The call super() in the Dog constructor invokes the constructor of the Animal class.
 - In the overridden display() method, super.display() calls the display() method of the Animal class.

These examples illustrate how this is used to refer to members of the current class, while super is used to access members and constructors of the parent class.

## 12. Explain how `equals()` and `hashCode()` work.

In Java, every class inherits the `equals()` and `hashCode()` methods from the `Object` class. These two methods are closely related and are essential for comparing objects and using them in hash-based collections (like `HashMap`, `HashSet`, etc.).

### The `equals()` Method

- **Purpose:**  
  The `equals()` method is used to compare two objects for equality. By default, the `Object` class's `equals()` method compares object references (i.e., whether the two references point to the same object). However, many classes override this method to provide a logical comparison based on the object's state (its fields).

- **Contract:**  
  For a correct implementation of `equals()`, it must be:
  - **Reflexive:** For any non-null reference `x`, `x.equals(x)` must return `true`.
  - **Symmetric:** For any non-null references `x` and `y`, if `x.equals(y)` is `true`, then `y.equals(x)` must be `true`.
  - **Transitive:** For any non-null references `x`, `y`, and `z`, if `x.equals(y)` and `y.equals(z)` are `true`, then `x.equals(z)` must be `true`.
  - **Consistent:** Multiple invocations of `x.equals(y)` consistently return the same result, provided no information used in equality comparisons is modified.
  - **Non-nullity:** For any non-null reference `x`, `x.equals(null)` must return `false`.

---

### The `hashCode()` Method

- **Purpose:**  
  The `hashCode()` method returns an integer (the hash code) that represents the object. This code is used by hash-based collections to efficiently locate a bucket where the object might be stored.

- **Contract:**  
  The general contract of `hashCode()` is:
  - If two objects are equal according to the `equals()` method, then they must have the same hash code.
  - If two objects have the same hash code, they are not necessarily equal (i.e., hash collisions can occur).
  - The hash code of an object should remain consistent during an execution, as long as the object is not modified in a way that affects equality comparisons.

---

### The Relationship Between `equals()` and `hashCode()`

- **Consistency Requirement:**  
  When overriding `equals()`, you must also override `hashCode()` to maintain the general contract. This ensures that if two objects are equal (i.e., `equals()` returns `true`), they will also have the same hash code. Failing to do so can result in unexpected behavior in hash-based collections.

---

### Sample Code Demonstration

Below is a sample Java class that properly overrides both `equals()` and `hashCode()`:

```java
import java.util.Objects;

public class Person {
    private String name;
    private int age;

    // Constructor to initialize Person object
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Overriding equals() to compare two Person objects logically
    @Override
    public boolean equals(Object obj) {
        // Check if both references point to the same object
        if (this == obj) return true;
        // Check for null and ensure both objects are of the same class
        if (obj == null || getClass() != obj.getClass()) return false;
        // Cast the object and compare fields
        Person person = (Person) obj;
        return age == person.age && Objects.equals(name, person.name);
    }

    // Overriding hashCode() to generate a hash code based on Person's fields
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    // Main method to demonstrate usage
    public static void main(String[] args) {
        Person person1 = new Person("Alice", 30);
        Person person2 = new Person("Alice", 30);
        Person person3 = new Person("Bob", 25);

        // Equals check
        System.out.println("person1 equals person2: " + person1.equals(person2)); // true
        System.out.println("person1 equals person3: " + person1.equals(person3)); // false

        // Hash code check (equal objects must have same hash code)
        System.out.println("person1 hashCode: " + person1.hashCode());
        System.out.println("person2 hashCode: " + person2.hashCode());
        System.out.println("person3 hashCode: " + person3.hashCode());
    }
}
```
### Explanation:
**Overriding equals():**
The equals() method first checks if both objects refer to the same instance. It then checks if the provided object is non-null and of the same class. Finally, it compares the name and age fields to determine logical equality.

**Overriding hashCode():**
The hashCode() method uses Objects.hash() to generate a hash code based on the name and age fields. This ensures that if two Person objects are equal (i.e., their name and age are the same), they will produce the same hash code.

## 13. What is the Java load sequence?

The Java load sequence refers to the order in which the JVM processes a class from the moment it is referenced until it is fully available for use. This process involves three main phases: **Loading**, **Linking**, and **Initialization**.

### 1. Loading

- **What Happens:**  
  The JVM reads the class file (typically from the file system or network) and creates an instance of `java.lang.Class` that represents the class.

- **Key Point:**  
  No initialization (e.g., static blocks or variable initializations) occurs during this phase.

---

### 2. Linking

Linking further breaks down into three sub-phases:

- **Verification:**  
  Ensures the class file follows the JVM specification and is free of structural errors.

- **Preparation:**  
  Allocates memory for class (static) variables and initializes them with default values (e.g., 0 for numbers, null for objects).

- **Resolution:**  
  Converts symbolic references in the class (like method or field names) into direct references in memory.

---

### 3. Initialization

- **Static Initialization:**  
  - Static variables and static blocks are executed in the order they appear in the source code.
  - This phase happens only once per class when it is first loaded.

- **Instance Initialization (when creating objects):**  
  - **Instance Variables & Instance Initialization Blocks:**  
    Are executed in the order they appear in the class.
  - **Constructor Execution:**  
    The constructor is called after the instance initialization blocks.

- **Superclass Initialization:**  
  If a class extends another class, the superclass is loaded and initialized before the subclass.

---

### Sample Code Demonstration

Below is an example that illustrates the order of static and instance initialization:

```java
public class Example {
    // Static variable initialization
    static int staticVar = initializeStaticVar();

    // Static initialization block
    static {
        System.out.println("Static block executed.");
    }

    // Instance variable initialization
    int instanceVar = initializeInstanceVar();

    // Instance initialization block
    {
        System.out.println("Instance initialization block executed.");
    }

    // Constructor
    public Example() {
        System.out.println("Constructor executed.");
    }

    private static int initializeStaticVar() {
        System.out.println("Static variable initialized.");
        return 10;
    }

    private int initializeInstanceVar() {
        System.out.println("Instance variable initialized.");
        return 20;
    }

    public static void main(String[] args) {
        System.out.println("Main method started.");
        new Example();
    }
}
```

**Expected Output:**
```
Static variable initialized.
Static block executed.
Main method started.
Instance variable initialized.
Instance initialization block executed.
Constructor executed.
```

## 14. What is Polymorphism? And how Java implements it?

Polymorphism is one of the core principles of Object-Oriented Programming (OOP) that allows objects of different types to be treated as objects of a common superclass. The term "polymorphism" means "many forms," and it allows a single interface to represent different underlying forms (data types).

### How Java Implements Polymorphism

Java implements polymorphism primarily in two ways:

1. **Compile-Time Polymorphism (Method Overloading):**
   - **Definition:**  
     Multiple methods in the same class can share the same name as long as their parameter lists (types, number, or order) are different.
   - **Usage:**  
     It is resolved during compile time.
   - **Example:**
     ```java
     class Calculator {
         // Overloaded method with two int parameters
         public int add(int a, int b) {
             return a + b;
         }
         
         // Overloaded method with two double parameters
         public double add(double a, double b) {
             return a + b;
         }
         
         // Overloaded method with three int parameters
         public int add(int a, int b, int c) {
             return a + b + c;
         }
     }

     public class TestOverloading {
         public static void main(String[] args) {
             Calculator calc = new Calculator();
             System.out.println("Sum of 2 ints: " + calc.add(10, 20));
             System.out.println("Sum of 2 doubles: " + calc.add(10.5, 20.5));
             System.out.println("Sum of 3 ints: " + calc.add(10, 20, 30));
         }
     }
     ```

2. **Runtime Polymorphism (Method Overriding):**
   - **Definition:**  
     When a subclass provides a specific implementation of a method that is already defined in its superclass.
   - **Usage:**  
     It is resolved at runtime using dynamic method dispatch.
   - **Example:**
     ```java
     // Superclass
     class Animal {
         // Method to be overridden
         public void sound() {
             System.out.println("Generic animal sound");
         }
     }

     // Subclass overriding the sound method
     class Dog extends Animal {
         @Override
         public void sound() {
             System.out.println("Bark");
         }
     }

     // Another subclass overriding the sound method
     class Cat extends Animal {
         @Override
         public void sound() {
             System.out.println("Meow");
         }
     }

     public class TestOverriding {
         public static void main(String[] args) {
             // Polymorphism in action: the Animal reference can point to any Animal type
             Animal myDog = new Dog();
             Animal myCat = new Cat();

             // Dynamic method dispatch: the appropriate method is called based on the object's actual type
             myDog.sound(); // Outputs: Bark
             myCat.sound(); // Outputs: Meow
         }
     }
     ```

---

### Summary

- **Polymorphism** allows methods to perform differently based on the object that invokes them.
- **Compile-Time Polymorphism (Method Overloading):**  
  Multiple methods with the same name but different parameters within the same class.
- **Runtime Polymorphism (Method Overriding):**  
  A subclass can provide a specific implementation of a method that is defined in its superclass, and the appropriate method is called based on the actual object's type during runtime.

This flexibility enables developers to write more generic and reusable code.

## 15. What is Encapsulation? How Java implements it? And why we need encapsulation?

Encapsulation is a core principle of Object-Oriented Programming (OOP) that involves bundling the data (fields) and the methods (functions) that operate on the data into a single unit, namely a class. This mechanism restricts direct access to some of an object's components, protecting the object's internal state and ensuring that it can only be modified in controlled ways.

### How Java Implements Encapsulation

Java implements encapsulation using:

- **Access Modifiers:**  
  By declaring class fields as `private`, Java hides the internal state of the object from the outside world. This prevents external classes from directly accessing or modifying the fields.

- **Public Getter and Setter Methods:**  
  Getters and setters provide a controlled way to access and update the private fields. They allow you to enforce rules (like validation) before changing the object's state.

---

### Sample Code Demonstration

```java
public class EncapsulatedPerson {
    // Private fields: the internal state is hidden from other classes.
    private String name;
    private int age;

    // Constructor to initialize the object.
    public EncapsulatedPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter for name.
    public String getName() {
        return name;
    }

    // Setter for name.
    public void setName(String name) {
        this.name = name;
    }

    // Getter for age.
    public int getAge() {
        return age;
    }

    // Setter for age with validation.
    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Please provide a valid age.");
        }
    }

    public static void main(String[] args) {
        // Creating an instance of EncapsulatedPerson.
        EncapsulatedPerson person = new EncapsulatedPerson("Alice", 30);
        
        // Accessing data through getters.
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
        
        // Modifying data through setters.
        person.setAge(35);
        System.out.println("Updated Age: " + person.getAge());
        
        // Attempting to set an invalid age.
        person.setAge(-5); // Will trigger the validation logic.
    }
}
```

### Why We Need Encapsulation

 - **Data Protection:**
Encapsulation protects an object’s internal state by preventing external classes from modifying it directly. This helps avoid unintended side effects and bugs.

 - **Controlled Access:**
By using getters and setters, you can enforce validation, logging, or other processing whenever an object’s data is accessed or modified. This ensures the object always remains in a valid state.

 - **Improved Maintainability:**
Encapsulated code is easier to maintain and update. Changes to the internal implementation of a class do not affect other parts of the application as long as the public interface remains the same.

 - **Increased Flexibility:**
Encapsulation allows you to change the underlying implementation without impacting code that depends on the class. This leads to better modularity and reusability of code.

Encapsulation is essential for creating robust, maintainable, and secure applications in Java.

## 16. Compare interface and abstract class with use cases.

Both interfaces and abstract classes are used to achieve abstraction in Java, but they serve different purposes and come with distinct features and use cases.

### Interface

#### Characteristics:
- **Pure Abstraction:**  
  Defines a contract of methods that implementing classes must provide.  
  *(Since Java 8, interfaces can also include default and static methods with concrete implementations.)*
- **Multiple Inheritance:**  
  A class can implement multiple interfaces, allowing for multiple inheritance of type.
- **No Instance State:**  
  Interfaces cannot have instance variables (only static final constants).

#### Use Cases:
- **Defining Common Behavior Across Unrelated Classes:**  
  Ideal for specifying a behavior that different, potentially unrelated classes must implement.
- **Multiple Inheritance of Type:**  
  When a class needs to adhere to multiple contracts.

#### Example:
```java
// Defining an interface
public interface Vehicle {
    void start();
    void stop();

    // Default method (Java 8+)
    default void honk() {
        System.out.println("Honking!");
    }
}

// Implementing the interface in a Car class
public class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car starting.");
    }

    @Override
    public void stop() {
        System.out.println("Car stopping.");
    }
}

// Implementing the interface in a Bike class
public class Bike implements Vehicle {
    @Override
    public void start() {
        System.out.println("Bike starting.");
    }

    @Override
    public void stop() {
        System.out.println("Bike stopping.");
    }
}
```

### Abstract Class

#### Characteristics:
- **Partial Abstraction:**  
  Can have both abstract methods (without implementation) and concrete methods (with implementation).
- **State and Constructors:**  
  Can include instance variables to maintain state and constructors to initialize them.
- **Single Inheritance:**  
  A class can extend only one abstract class, making it suitable for related classes sharing common behavior.

#### Use Cases:
- **Sharing Code Among Related Classes:**  
  When several classes share common functionality or state, an abstract class can provide a base implementation.
- **Default Behavior Implementation:**  
  It can offer default behavior that subclasses may inherit or override.

#### Example:
```java
// Defining an abstract class
public abstract class Animal {
    protected String name;

    // Constructor to initialize the state
    public Animal(String name) {
        this.name = name;
    }

    // Abstract method: subclasses must provide their own implementation
    public abstract void makeSound();

    // Concrete method: common behavior for all animals
    public void sleep() {
        System.out.println(name + " is sleeping.");
    }
}

// Subclass extending the abstract class
public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " barks.");
    }
}

// Another subclass extending the abstract class
public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " meows.");
    }
}
```

### Summary Comparison

- **Interfaces:**
  - **Purpose:** Define a contract for behavior.
  - **Inheritance:** Supports multiple inheritance.
  - **State:** No instance state; only constants.
  - **Use Case:** When different classes (even unrelated ones) should implement common methods.

- **Abstract Classes:**
  - **Purpose:** Provide a common base with shared code and state.
  - **Inheritance:** Allows only single inheritance.
  - **State:** Can have instance variables and constructors.
  - **Use Case:** When creating a hierarchical relationship among related classes with shared behavior.

By choosing between interfaces and abstract classes appropriately, you can design flexible, modular, and maintainable applications.
