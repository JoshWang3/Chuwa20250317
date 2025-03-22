# hw1
### 1. Write up Example code to demonstrate the three foundmental concepts of OOP.
#### 1) Encapsulation;
Encapsulation means to bind the data and the methods in a single class. It makes the rules for the others from the outside to manipulate the data.
```java
class Employee {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee();
        // employee.name = "Zeliang Yin";  // Error: 'name' has private access in 'Employee'
        employee.setName("Zeliang Yin");
        System.out.println(employee.getName()); // Output: Zeliang Yin
    }
}
```
#### 2) Polymorphism;
**Static polymorphism - Overload**: Two methods have the same name, but different argument numbers or argument types.
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
**Dynamic polymorphism - Override**: The child class has the same method as the parent class, but different implementation.
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
#### 3) Inheritance;
The child class inherits all the non-private variables and methods of the parent class.
```java
class Employee {
    public void work() {
        System.out.println("Work");
    }
}

class Engineer extends Employee {
    public void repair() {
        System.out.println("Repair");
    }
}

public class Main {
    public static void main(String[] args) {
        Engineer engineer = new Engineer();
        engineer.work();    // Output: Work
        engineer.repair();  // Output: Repair
    }
}
```
### 2. What is **wrapper data type classes** (e.g. Integer, Double) in Java and Why we need wrapper class?
- Wrapper classes are classes that provide a way to use primitive data types.
- Primitives are not objects in Java. They don't have methods. Wrapper classes **provide utility methods**. They allow you to **work with collections and generics that only accept objects**. And wrapper classes **can be assigned `null`**.
```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Integer integer = new Integer(1);
        double d = integer.doubleValue();
        System.out.println(d);      // Output: 1.0
        integer = null;
        List<Integer> list = new ArrayList<>();
        list.add(integer);
        System.out.println(list);   // Output: [null]
    }
}
```
### 3. What is the difference between **HashMap** and **HashTable**?
| Feature | HashMap | HashTable |
| --- | --- | --- |
| Synchronized | No | Yes |
| Null keys & values | One null key & multiple null values | Not allowed |
| Performance | Better in single thread | Slower  in single thread |
| Traversing elements | Iterator | Enumerator |
```java
import java.util.HashMap;
import java.util.Hashtable;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(null, null);
        hashMap.put(0, 0);
        hashMap.put(1, null);
        System.out.println(hashMap);       // Output: {null=null, 0=0, 1=null}

        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        // hashtable.put(null, null);      // Throws: NullPointerException
        hashtable.put(0, 0);
        // hashtable.put(1, null);         // Throws: NullPointerException
        System.out.println(hashtable);     // Output: {0=0}
    }
}
```
### 4. What is **String pool** in Java and why we need String pool? Explain String immutability.
- The String pool in Java is a **special memory area** inside the Heap Memory.
- Whenever a String literal is created, the JVM checks the String pool. If it exists, the reference to the existing string is returned instead of creating a new object, thus **saving memory and improving performance**.
- Immutability means that once a String object is created, its **value cannot be changed**.
```java
public class Main {
    public static void main(String[] args) {
        String s1 = "Zeliang";
        String s2 = "Zeliang";
        System.out.println(s1 == s2);   // Output: true

        String s3 = new String("Zeliang");
        String s4 = new String("Zeliang");
        System.out.println(s2 == s3);   // Output: false
        System.out.println(s3 == s4);   // Output: false
    }
}
```
### 5. Explain **garbage collection**? Explain types of garbage collection.
Java uses **automatic garbage collection**. Developers don’t need to explicitly deallocate memory.
| Garbage Collector | Best For | Downsides |
| --- |  --- |  --- |
| Serial GC | Small applications | Freezes app during GC |
| Parallel | GC High-throughput applications | Still has stop-the-world pauses |
| CMS GC | Low-latency applications | More CPU usage, fragmentation issues |
| G1 GC | Large heaps, low-latency apps | Higher complexity |
| ZGC | Ultra-low-latency apps | More CPU overhead |
| Shenandoah GC | Very large-scale applications | Experimental in some Java versions |
```java
public class Main {
    @Override
    protected void finalize() {
        System.out.println("Garbage Collected: " + this);
    }
    public static void main(String[] args) {
        System.out.println("Creating Object");
        Main obj = new Main();
        obj = null;
        System.gc();
        System.out.println("End of Main Method");
    }
    /**
     * Output:
     * Creating Object
     * End of Main Method
     * Garbage Collected: Main@51fdac5c
     */
}
```
### 6. What are **access modifiers** and their scopes in Java?
Access modifiers in Java **control the visibility and accessibility** of classes, methods, and variables.
| Modifier | Class | Package | Subclass | Everywhere |
| --- | --- | --- | --- | --- |
| **private** | Yes | No | No | No |
| **default** | Yes | Yes | No | No |
| **protected** | Yes | Yes | Yes | No |
| **public** | Yes | Yes | Yes | Yes |
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
| Usage | Effect |
| --- | ---|
| final variable | Define constants |
| final method | Can't be overridden in a subclass |
| final class | Cannot be extended (inherited) |
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
| Usage | Effect |
| --- | ---|
| static variable | Shared among all instances (single memory allocation) |
| static method | Can be called without creating an object |
| static block | Executes once when the class is loaded |
| static class | Can be instantiated without an outer class instance |
- We usually use it to develop the methods of utils classes (Integer, String, Math, System etc.).
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
| Difference | Overriding | Overloading |
| --- | --- | --- |
| Definition | Redefining a method in a subclass | Defining multiple methods with the same name but different parameters in the same class |
| Method Signature | Must be the same | Must be different (different parameters) |
| Return Type | Must be the same | Can be different |
| Access Modifier | Can't be more restrictive than the superclass method | Can have any access modifier |
| static / final methods | Can't override static / final methods | Can overload static / final methods |
| Performance Impact | Runtime Polymorphism (slower) | Compile-time Polymorphism (faster) |
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
### 10. Explain how Java defines a **method signature**, and how it helps on overloading and overriding.
- The **method name** and the **parameter list** together form the method signature. **Return type** and **access modifiers** are NOT part of the method signature.
- Overload: The **compiler** differentiates overloaded methods using method signatures.
- Override: The JVM determines which method to call at **runtime** based on the method signature.
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
- `equals()`: Compares the **logical equality** of two objects.
- `hashCode()`: Returns an **integer hash code** for an object.
```java
class Employee {
    String name;
    int age;

    Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return age == employee.age && name.equals(employee.name);
    }

    @Override
    public int hashCode() {
        return 31 * name.hashCode() + age;
    }
}

public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee("A", 18);
        Employee e2 = new Employee("A", 18);
        Employee e3 = e1;
        Employee e4 = new Employee("B", 20);
        System.out.println(e1.equals(e2));  // Output: true
        System.out.println(e1.equals(e3));  // Output: true
        System.out.println(e1.equals(e4));  // Output: false

        System.out.println(e1.hashCode() == e2.hashCode());  // Output: true
        System.out.println(e1.hashCode() == e3.hashCode());  // Output: true
        System.out.println(e1.hashCode() == e4.hashCode());  // Output: false
    }
}
```
### 13. What is the Java **load sequence**?
1. **Class Loading**: The class is loaded when it is first referenced.
2. **Verification**: The bytecode is verified.
3. **Preparation**: Static fields are initialized with default values.
4. **Resolution**: Symbolic references (like method calls) are resolved.
5. **Initialization**: The static block is executed.
6. **Main Method Execution**: The main method is executed.
7. **Object Creation**: The constructor is invoked.
```java
public class Main {
    static {
        System.out.println("Static block executed!");
    }

    public Main() {
        System.out.println("Main object created!");
    }

    public static void main(String[] args) {
        System.out.println("main method executed!");
        Main obj = new Main();
    }

    /**
     * Output:
     * Static block executed!
     * main method executed!
     * Main object created!
     */
}
```
### 14. What is **Polymorphism** ? And how Java implements it ?
Polymorphism allows one entity (such as a method, class, or interface) to take on multiple forms.  
There are two types of polymorphism in Java:
1. **Static polymorphism - Overload**: Two methods have the same name, but different argument numbers or argument types.
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
2. **Dynamic polymorphism - Override**: The child class has the same method as the parent class, but different implementation.
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
Encapsulation means to bind the data and the methods in a single class. It makes the rules for the others from the outside to manipulate the data.  
Java implements encapsulation through the following mechanisms:  
1. Private access modifier
2. Public Getter and Setter methods  

We use encapsulation to:  
1. Increase flexibility and maintenance
2. Improve data integrity
3. Enhance security
4. Improve reusability
```java
class Employee {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class Main {
    public static void main(String[] args) {
        Employee employee = new Employee();
        // employee.name = "Zeliang Yin";  // Error: 'name' has private access in 'Employee'
        employee.setName("Zeliang Yin");
        System.out.println(employee.getName()); // Output: Zeliang Yin
    }
}
```
### 16. Compare **interface** and **abstract class** with use cases.
| Difference | interface | abstract class |
| --- | --- | --- |
| Methods | All methods are abstract | Can have both abstract and concrete methods |
| Variables | Only `public static final` variables | Can have instance variables |
| Constructor | Can't have constructor | Can have constructor |
| Inheritance | A class can implement multiple interfaces | A class can extend only one abstract class |
| Access Modifiers | Methods are `public` by default | Methods can have any access modifier |
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
        dog.makeSound();// Output: This animal eats food
        Animal cat = new Cat();
        cat.eat();      // Output: This animal eats food
        cat.makeSound();// Output: Meow
    }
}
```