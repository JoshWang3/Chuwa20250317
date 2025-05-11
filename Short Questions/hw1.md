### 1 Write up Example code to demonstrate the three foundmental concepts of OOP

```
class User { // encapsulation
    private String name;
    private int age;
    private String gender;

    public User(String name, int age){
        this.name = name;
        this.age = age;
    }

    public User(String name, int age, String gender){
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getGender(){
        return this.gender;
    }
}

class FemaleUser extends User{ // inheritance
    
    public FemaleUser(String name, int age) {
        super(name, age);
    }

    @Override
    public String getGender(){
        return "Female";
    }
}

class MaleUser extends User{ // inheritance
    public MaleUser(String name, int age) {
        super(name, age);
    }

    @Override
    public String getGender(){
        return "male";
    }
}

public class Main {
    public static void main(String[] args) {
        User user1 = new User("YueYu", 99);
        User user2 = new User("YueYu", 99, "Female"); // static polymorphism
        User user3 = new FemaleUser("Yue", 99); // dynamic polymorphism
        User user4 = new MaleUser("Yu",99);
        
        System.out.println();
    } 
}
```

### 2 What is wrapper data type classes(e.g. Integer, Double) in Java and Why we need wrapper class? 
Wrapper classes are object representations of primitive data types. 

| Primitive Type | Wrapper Class |
|----------------|----------------|
| int            | Integer        |
| double         | Double         |
| float          | Float          |
| char           | Character      |
| byte           | Byte           |
| short          | Short          |
| long           | Long           |
| boolean        | Boolean        |


Reason: 
Provide a way to use primitive data types as objects, so they can be used in Collections, Generics, also can represent null values while primitive types can't.


### 3 What is the difference between HashMap and HashTable?
| Feature                | `HashMap`                                                    | `Hashtable`                                                    |
| ---------------------- | ------------------------------------------------------------ | -------------------------------------------------------------- |
| **Thread-Safety**      | NO                                                           | Yes                                                            |
| **Performance**        | Faster (no overhead of locking)                              | Slower (synchronization overhead)                              |
| **Null Keys/Values**   | Allows one null key, multiple null values                    | Not allowed                                                    |
| **Iterator Type**      | Uses **fail-fast** `Iterator`                                | Uses **enumeration**, not fail-fast                            |
| **Use in Modern Code** | Preferred                                                    | Rarely used; replaced by `ConcurrentHashMap` in multithreading |


### 4 What is String pool in Java and why we need String pool? Explain String immutability.
The String pool is a special memory region that stores string literals in __Heap Memory__. When the same literal is used again, Java reuses the object from the pool instead of creating a new one. This __saves memory__ and __improves performance__.
String immutability: __strings cannot be changed after creation__
```
String s1 = "s1";
String s2 = "s2"; // reuse existing string literals
System.out.println(s1 == s2); // True

String s1 = new String("s1");
String s2 = new String("s2");
System.out.println(s1 == s2); // False
```

### 5 Explain garbage collection? Explain types of garbage collection.
Garbage collection (GC): Jva automatically deallocates memory by removing unreachable objects. 


| GC Type    | Pause Time | Concurrency | Heap Size Suitability | Use Case                        |
| ---------- | ---------- | ----------- | --------------------- | ------------------------------- |
| Serial     | High       | No          | Small                 | Single-threaded, low-memory     |
| Parallel   | Medium     | Minor+Full  | Medium-Large          | Throughput-focused apps         |
| CMS (old)  | Low        | Yes         | Medium                | Low-latency (legacy systems)    |
| G1         | Low-Med    | Yes         | Medium-Large          | Balanced, default in Java 9+    |
| ZGC        | Very Low   | Yes         | Very Large (TB+)      | Ultra-low-latency systems       |
| Shenandoah | Very Low   | Yes         | Large                 | Interactive/low-pause workloads |


### 6 What are access modifiers and their scopes in Java?
access modifier: control the visibility and accessibility of classes, variables and methods.
| **Modifier** | **Same Class** | **Same Package** | **Subclass (other package)** | **Other Classes (global)** |
| ------------ | -------------- | ---------------- | ---------------------------- | -------------------------- |
| `private`    | Yes            | No               | No                           | No                         |
| *(default)*  | Yes            | Yes              | No                           | No                         |
| `protected`  | Yes            | Yes              | Yes                          | No                         |
| `public`     | Yes            | Yes              | Yes                          | Yes                        |


### 7 Explain final keyword? (Field, Method, Class)

Final field: Cannot be reassigned after initialization.
Final method: Cannot be overridden in subclasses.
Final class: Cannot be extended/inherited (e.g., String class).


### 8 Explain static keyword? (Field, Method, Class). When do we usually use it?

Static field: Shared by all instances of a class. - used for constants or counters
Static method: Can be called without creating an object. - Math.max(a, b)
Static block: Executes once when the class is loaded. - used for static initialization
static nested class: Can be accessed without creating an instance of outer class. 
```
public class Outer {
    static int staticVar = 10;

    static class StaticNested {
        void show() {
            System.out.println("Static var: " + staticVar);
        }
    }
    
    public static void main(String[] args) {
        Outer.StaticNested nested = new Outer.StaticNested();
        nested.show(); // Output: Static var: 10
    }
}
```
Use it when behavior or data is common to all objects (e.g., Math.PI, main() method).


### 9. What is the difference between overriding and overloading?

| **Aspect**             | **Overriding**                                            | **Overloading**                                                       |
| ---------------------- | --------------------------------------------------------- | --------------------------------------------------------------------- |
| **Definition**         | redefine a method in a subclass                           | define multiple methods with the same name but different parameters   |
| **Method Signature**   | must be the same as the superclass method                 | must be different (number/type/order of parameters)               |
| **Return Type**        | same                                                      | can be different                                                      |
| **Access Modifier**    | can't reduce visibility                                   | can have any visibility                                               |
| **Static / final**     | cannot override `static` or `final` methods               | can overload `static` or `final` methods                              |
| **Binding Time**       | runtime polymorphism (slower)                             | compile-time polymorphism(faster)                                     |



### 10  Explain how Java defines a method signature, and how it helps on overloading and overriding.
method signature = method name + parameter types (excluding return type, access modifiers and exceptions).

Overload: __compiler__ differentiates overloaded methods using method signature.
Override: the __JVM__ determines which method to call at __runtime__ using method signature. 


### 11 What is the difference between super and this?

this: Refers to the current object (e.g., this.field or calling another constructor).
super: Refers to the superclass object. Used to access superclass fields, or call superclass constructors/methods.


### 12 Explain how equals and hashCode work.

equals(Object obj): Compares objects for logical equality.
hashCode(): Returns an integer used in hash-based collections (e.g., HashMap).

If two objects are equal via equals(), they must have the same hashCode().


### 13 What is the Java load sequence?
Java Load Sequence: order in which java loads, links, initializes, and executes a class during runtime. 

- Loading: read .class bytecode file and create a Class object in memory
- Linking:
  - Verification: JVM checks the bytecode is valid and safe
  - Preparation: JVM allocates memory for static variables and sets default values(e.g. `0`,`null`)
  - Resolutio: Symbolic references (like method names) are resolved to actual meory references.
- Initialization: 
  - Static variables are assigned with actual values.
  - Static blocks are executed in the order they appear in the calss.
- Instantiation:
  - Instance variable assignments
  - Instance initializer blocks 
  - Class Instructor is executed
```
public class Test {
    static int a = 10;

    static {
        System.out.println("Static block executed. a = " + a);
        a = 20;
    }

    int x = initX();

    {
        System.out.println("Instance initializer block executed. x = " + x);
    }

    public Test() {
        System.out.println("Constructor executed.");
    }

    private int initX() {
        System.out.println("initX() called");
        return 42;
    }

    public static void main(String[] args) {
        System.out.println("Main started.");
        new Test();
    }
}
```
``` Output
Static block executed. a = 10
Main started.
initX() called
Instance initializer block executed. x = 42
Constructor executed.
```


### 14 What is Polymorphism? And how Java implements it?
Polymorphism is the ability to use a single interface or method to operate on different types. 
Java supports:
- Compile-time polymorphism: via method overloading.
- Runtime polymorphism: via method overriding and dynamic dispatch (calling overridden methods based on object type).


### 15 What is Encapsulation? How Java implements it? And why we need encapsulation?
Encapsulation is the concept of wrapping data and methods into a single unit (class) and restricting access to some components.
Java implements it using:
- Private fields (data hiding).
- Public getters and setters for controlled access.
Benefits: modularity, improved maintainability, data protection.


### 16 Compare interface and abstract class with use cases.
| Feature                              | `interface`                                       | `abstract class`                                  |
| ------------------------------------ | ------------------------------------------------- | ------------------------------------------------- |
| Method                               | all methods are abstract                          |  both abstract and concrete methods               |
| method implementations               | Yes (`default`/`static` methods since Java 8)     | Yes                                               |
| constructors                         | Can't have                                        | Can have                                          |
| variables                            | Only `public static final` constants              | Any type (private/protected/static, etc.)         |
| Access modifiers                     | Methods are `public` by default                   | Can be `private`, `protected`, etc.               |
| inheritance                          | A class can implement multiple interfaces         | A class can extend only one abstract class        |

`Interface`
```
public interface Flyable {
    default void fly(){
        System.out.println("fly fly!");
    }
}

public class Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("Bird flies");
    }
}

public class Drone implements Flyable {
    @Override
    public void fly() {
        System.out.println("Drone flies");
    }
}
```

`Abstract Class`
```
public abstract class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void breathe() {
        System.out.println(name + " breathes");
    }

    public abstract void makeSound();
}

public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    public void makeSound() {
        System.out.println("Woof!");
    }
}
```
