
# hw1
### 1. Write up Example code to demonstrate the three foundmental concepts of OOP.
#### 1) Encapsulation
Encapsulation means keeping the internal state (fields) private and providing public methods to access and update it.
```java
class Animal {
    // Private fields (Encapsulation)
    private String name;
    private int age;

    // Constructor
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Public getters and setters (Encapsulation)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Method to be overridden
    public void speak() {
        System.out.println(name + " makes a sound.");
    }
}

```
#### 2) Polymorphism
Polymorphism allows objects to take many forms, like method overriding.
```java
class Main {
    public static void main(String[] args) {
        // Using encapsulation
        Animal genericAnimal = new Animal("Creature", 5);
        Dog dog = new Dog("Buddy", 3);

        // Polymorphism in action
        Animal[] animals = {genericAnimal, dog};

        for (Animal a : animals) {
            a.speak();  // Runtime polymorphism
        }
    }
}
```

#### 3) Inheritance;
Inheritance allows one class to inherit the properties and methods of another.
```java
// Dog class inherits from Animal
class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);  // Call the constructor of Animal
    }

    // Method overriding (Polymorphism)
    @Override
    public void speak() {
        System.out.println(getName() + " barks.");
    }
}
```

### 2. What is **wrapper data type classes** (e.g. Integer, Double) in Java and Why we need wrapper class?
- In Java, **wrapper classes** are object representations of the **primitive data types**.
- They "wrap" primitive types into objects so that they can be used in contexts where **objects** are required.
```java
public class WrapperExample {
    public static void main(String[] args) {
        Integer num = 10;              // autoboxing
        int square = num * num;        // unboxing
        System.out.println("Square: " + square);

        ArrayList<Double> list = new ArrayList<>();
        list.add(3.14);  // autoboxing
        System.out.println("First element: " + list.get(0));
    }
}

```
### 3. What is the difference between **HashMap** and **HashTable**?
| Feature | HashMap | HashTable |
| --- | --- | --- |
| Thread Safety | ❌ Not synchronized (not thread-safe) | ✅ Synchronized (thread-safe) |
| Null keys & values | Allows **1 null key** and **multiple null values** | ❌ Does **not allow** null keys or values |
| Performance | Faster (no synchronization overhead) | Slower (due to synchronization) |

❗ **`Hashtable` is considered outdated** and rarely used in modern code.

```java
import java.util.*;

public class MapExample {
    public static void main(String[] args) {
        // HashMap example
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("A", "Apple");
        hashMap.put(null, "NullKey");         // ✅ Allowed
        hashMap.put("B", null);               // ✅ Allowed
        System.out.println("HashMap: " + hashMap);

        // Hashtable example
        Hashtable<String, String> hashTable = new Hashtable<>();
        hashTable.put("X", "Xylophone");
        // hashTable.put(null, "Oops");       // ❌ Throws NullPointerException
        // hashTable.put("Y", null);          // ❌ Throws NullPointerException
        System.out.println("Hashtable: " + hashTable);
    }
}

```

### 4. What is **String pool** in Java and why we need String pool? Explain String immutability.
The **String Pool** (also called the **intern pool**) is a special memory area inside the **Java Heap** that stores **unique string literals**.

When you create a string using **double quotes (`"Hello"`)**, Java checks the pool:

-   If the string **already exists** in the pool, it returns a reference to the **existing object**.
    
-   If it doesn’t exist, it creates a **new string** in the pool.
```java
public class StringPoolExample {
    public static void main(String[] args) {
        String s1 = "Java";         // stored in String pool
        String s2 = "Java";         // refers to the same object in pool

        String s3 = new String("Java");  // creates a new object in heap

        System.out.println(s1 == s2); // true (same object in pool)
        System.out.println(s1 == s3); // false (different objects)
    }
}

```
### 🔍 Why Do We Need a String Pool?

1.  ✅ **Memory Efficiency**: Reusing common string literals saves memory.
    
2.  ✅ **Performance**: Comparing strings using `==` is faster (compares references).
    
3.  ✅ **Optimization**: String literals are heavily used (e.g., variable names, keys).

### What is **String Immutability** in Java?

A `String` in Java is **immutable**, which means **once a string object is created, it cannot be changed**.

### 5. Explain **garbage collection**? Explain types of garbage collection.
**Garbage Collection (GC)** is the process by which **Java automatically deletes objects from memory** (heap) that are **no longer reachable or needed** by the program.

✅ It helps manage memory efficiently and **prevents memory leaks**.

| Garbage Collector | Best For | Downsides |
| --- |  --- |  --- |
| Serial GC | Small applications | Freezes app during GC |
| Parallel | GC High-throughput applications | Still has stop-the-world pauses |
| CMS GC | Low-latency applications | More CPU usage, fragmentation issues |
| G1 GC | Large heaps, low-latency apps | Higher complexity |
| ZGC | Ultra-low-latency apps | More CPU overhead |
| Shenandoah GC | Very large-scale applications | Experimental in some Java versions |
```java
public class GCExample {
    public static void main(String[] args) {
        String s1 = new String("Hello");
        s1 = null;  // The object is now unreachable
        System.gc(); // Suggests the JVM to run garbage collector
    }

    @Override
    protected void finalize() {
        System.out.println("Garbage collected!");
    }
}
```

### 6. What are **access modifiers** and their scopes in Java?
Access modifiers control **where** and **how** class members (fields, methods, etc.) can be accessed.

There are **four** main access levels:
| Modifier | Class | Package | Subclass | Everywhere |
| --- | --- | --- | --- | --- |
| **private** | Yes | No | No | No |
| **default** | Yes | Yes | No | No |
| **protected** | Yes | Yes | Yes | No |
| **public** | Yes | Yes | Yes | Yes |

*default: No modifier specified (also called **package-private**)
```java
class Employee {
    private String privateName = "privateName";
    public String publicName = "publicName";
}

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee();
        // System.out.println(employee.privateName);    // Error: 'privateName' has private access in 'Employee'
        System.out.println(employee.publicName);        // Output: publicName
    }
}
```
### 7. Explain **final** key word? (Filed, Method, Class)

The `final` keyword means **"cannot be changed or overridden"**.

| Use of `final` | Meaning |
| --- | ---|
| `final` field | Value cannot be reassigned |
| `final` method | Cannot be overridden in a subclass |
| `final` class| Cannot be subclassed or extended |
```java
public class Main {

    private static final int n = 0;
    public static void main(String[] args) {
        // n = 1;   // Error: Cannot assign a value to final variable 'n'
        System.out.println(n);  // Output: 0
    }
}
```

### 8. Explan **static** keyword? (Filed, Method, Class). When do we usually use it?

The `static` keyword means the **member belongs to the class**, not to any specific object (instance).

This means:

-   You **don’t need to create an object** to access it.
    
-   All instances **share the same static field or method**.

| Use of `static` | Meaning |
| --- | ---|
| `static` field | Shared by all instances |
| `static` method | Belongs to class, not object |
| `static` class (nested)| No reference to outer class instance|

```java
class MathUtils {
    static int square(int num) {
        return num * num;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(MathUtils.square(5));    // Output: 25
    }
}
```

### 9. What is the differences between **overriding** and **overloading**?
### Method **Overriding**

### ✅ Definition:

Overriding happens when a **subclass provides a specific implementation** of a method that is already defined in its **superclass**.

### 📌 Key Points:

-   Same **method name**, **parameters**, and **return type**.
    
-   Happens across **inheritance** (parent-child classes).
    
-   Enables **runtime polymorphism**.
    
-   The overridden method must be **`non-static`** and **not `final/private`**.
```java
class Animal {
    void makeSound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barks");
    }
}

```
### Method **Overloading**
### ✅ Definition:

Overloading happens when **multiple methods** in the **same class** have the **same name** but **different parameter lists** (different number or types of arguments).

### 📌 Key Points:

-   Same method name, but different **parameters**.
    
-   Happens in the **same class** (or subclass).
    
-   **No inheritance required**.
    
-   Improves **readability and flexibility**.

```java
class MathUtils {
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}

```

### 10. Explain how Java defines a **method signature**, and how it helps on overloading and overriding.
### What is a **Method Signature** in Java?

In Java, a **method signature** is defined by:

- 🔹 **Method name** + **Parameter list (types and order)**

- 🚫 It does **not** include return type, access modifier, or exceptions.


```java
class Employee {
    public void work() {
        System.out.println("Work");
    }

    public void work(String content) {
        System.out.println("Work: " + content);
    }
}

class Engineer extends Employee {
    @Override
    public void work() {
        System.out.println("Code");
    }
}

public class Main {
    public static void main(String[] args) {
        Employee employee = new Engineer();
        employee.work();    // Output: Code
        employee.work("Code");  // Output: Work: Code
    }
}
```
### 11. What is the differences between **super** and **this**?
| Difference | super | this |
| --- | --- | --- |
| Definition | Refers to the parent (superclass) object | Refers to the current object (same class) |
| Usage | Used to call superclass methods, constructors, and access superclass fields | Used to call current class methods, constructors, and access fields|
| Work scope | Only in a subclass | Anywhere inside the same class |
| Overridden method access | Used to call superclass versions of overridden methods | Not used for overriding |
```java
class Employee {
    public void work() {
        System.out.println("Work");
    }
}

class Engineer extends Employee {
    @Override
    public void work() {
        System.out.println("Code");
    }

    public void superWork() {
        super.work();
    }
}

public class Main {
    public static void main(String[] args) {
        Engineer engineer = new Engineer();
        engineer.superWork();   // Output: Work
        engineer.work();        // Output: Code
    }
}
```
### 12. Explain how **equals** and **hashCode** work.
###  `equals()` Method

### 📌 Purpose:

Used to **compare two objects for logical equality** (i.e., whether their content is the same).

### 🔧 Default behavior:

By default, `equals()` from `Object` compares **references (memory addresses)** — so it behaves like `==`.

###  `hashCode()` Method

### 📌 Purpose:

Returns an **integer hash code** for the object. It’s used by **hash-based collections** (e.g., `HashMap`, `HashSet`) to organize and retrieve objects efficiently.

### 🧠 Rule:

If two objects are **equal according to `equals()`**, they **must have the same hash code**.

But:

-   If two objects have the same hash code, they **are not necessarily equal**.
```java
import java.util.Objects;

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Override equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person p = (Person) o;
        return age == p.age && name.equals(p.name);
    }

    // Override hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

```
### 13. What is the Java **load sequence**?
When a Java program runs, the **Java Virtual Machine (JVM)** follows a specific **load and execution order** for classes.

This order is:

### 🔢 1. **Class Loading**

-   Done by the **ClassLoader**.
    
-   The `.class` file is **loaded into memory** when referenced for the first time.
    

----------

### 🔢 2. **Linking** (by JVM)

Linking has three sub-steps:

1.  **Verification** – Ensure bytecode is valid.
    
2.  **Preparation** – Allocate memory for `static` variables, initialized to default values.
    
3.  **Resolution** – Replace symbolic references with actual addresses.
    

----------

### 🔢 3. **Initialization**

Now the fun part begins — code starts running!

Java initializes in this order:

Step

What Happens

1️⃣

Static blocks and static variables (in **order of appearance** in the class)

2️⃣

Main method is called

3️⃣

If an object is created:  
• Instance variables are initialized  
• Instance initializer blocks run  
• Constructor runs
```java
class Parent {
    static {
        System.out.println("Parent static block");
    }

    {
        System.out.println("Parent instance block");
    }

    Parent() {
        System.out.println("Parent constructor");
    }
}

class Child extends Parent {
    static {
        System.out.println("Child static block");
    }

    {
        System.out.println("Child instance block");
    }

    Child() {
        System.out.println("Child constructor");
    }

    public static void main(String[] args) {
        System.out.println("Main starts");
        new Child();
    }
}

```
### 14. What is **Polymorphism** ? And how Java implements it ?

### 🔧 How Java Implements Polymorphism

### ✅ Method Overloading:

Handled by the **compiler** during parsing/compilation.

```java
class Employee {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setName() {
        this.name = "Default Name";
    }
}

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setName();
        System.out.println(employee.getName()); // Output: Default Name
        employee.setName("Zeliang Yin");
        System.out.println(employee.getName()); // Output: Zeliang Yin
    }
}
```
### ✅ Method Overriding:

-   Uses **dynamic method dispatch**.
    
-   JVM decides which method to call **based on the object** at runtime.
```java
class Employee {
    public void work() {
        System.out.println("Work");
    }
}

class Engineer extends Employee {
    @Override
    public void work() {
        System.out.println("Code");
    }
}

public class Main {
    public static void main(String[] args) {
        Employee employee = new Engineer();
        employee.work();    // Output: Code
    }
}
```

### 15. What is **Encapsulation** ? How Java implements it? And why we need encapsulation?
### 💡 What is **Encapsulation**?

**Encapsulation** means:

**“Wrapping data (fields) and code (methods) together as a single unit”**, and **restricting direct access** to some of the object's internal details.

In simple terms:

➤ **Hide the internal state** and **expose only what is necessary**.

----------

### 🧱 How Java Implements Encapsulation

Java uses:

1.  **Private fields** to hide data.
    
2.  **Public getters/setters** to access/update the data.
```java
public class Person {
    private String name;  // ❌ cannot access directly outside
    private int age;

    // ✅ public getter
    public String getName() {
        return name;
    }

    // ✅ public setter
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0) {           // ✔️ add validation logic
            this.age = age;
        }
    }
}

```
### 16. Compare **interface** and **abstract class** with use cases.

### 📘 What is an **Interface** in Java?

-   An interface is a **pure contract**.
    
-   It defines **what** a class must do, **not how**.
    
-   All methods are implicitly `public` and `abstract` (until Java 8+ which allows `default` and `static` methods).

### 📘 What is an **Abstract Class** in Java?

-   An abstract class is a **partially implemented class**.
    
-   It can have **both abstract and concrete methods**.
    
-   It can also have **fields**, **constructors**, and **access modifiers**.

| Difference | interface | abstract class |
| --- | --- | --- |
| Methods | All methods are abstract | Can have both abstract and concrete methods |
| Variables | Only `public static final` variables | Can have instance variables |
| Constructors | ❌ Not allowed | ✅ Allowed|
| Inheritance Type | Implements (multiple allowed) | Extends (only one) |
| Access Modifiers | Methods are `public` by default | Can use all access levels |

**Interface**:
```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Duck implements Flyable, Swimmable {
    public void fly() { System.out.println("Duck is flying"); }
    public void swim() { System.out.println("Duck is swimming"); }
}

public class Main {
    public static void main(String[] args) {
        Duck duck = new Duck();
        duck.fly();     // Output: Duck is flying
        duck.swim();    // Output: Duck is swimming
    }
}

```
**Abstract class**:
```java
abstract class Animal {
    public void eat() { System.out.println("This animal eats food"); }
    public abstract void makeSound();
}

class Dog extends Animal {
    @Override
    public void makeSound() { System.out.println("Bark"); }
}

class Cat extends Animal {
    @Override
    public void makeSound() { System.out.println("Meow"); }
}

public class Main {
    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.eat();      // Output: This animal eats food
        dog.makeSound();// Output: Bark
        Animal cat = new Cat();
        cat.eat();      // Output: This animal eats food
        cat.makeSound();// Output: Meow
    }
}
```