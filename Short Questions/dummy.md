### Short Questions
Write up Example code to demonstrate the three foundmental concepts of OOP.
1. Encapsulation: hiding the internal state and behavior of an object and allowing access through public methods (getters/setters). This helps protect the integrity of the data.
public class Student {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setAge(int age) {
        if (age > 0) this.age = age;
    }
    public int getAge() {
        return age;
    }
}
public class Main {
    public static void main(String[] args) {
        Student s = new Student();
        s.setName("A");
        s.setAge(18);
        System.out.println(s.getName() + " is " + s.getAge() + " years old.");
    }
}
2. Polymorphism: one interface to be used for different types — like calling the same method on different objects and getting different behavior.
class Shape {
    public void draw() {
        System.out.println("Drawing a shape...");
    }
}

class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}

class Rectangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a rectangle");
    }
}
public class Main {
    public static void main(String[] args) {
        Shape shape1 = new Circle();
        Shape shape2 = new Rectangle();
    }
}

3. Inheritance: one class (child/subclass) to inherit fields and methods from another class (parent/superclass).
class Vehicle {
    public void start() {
        System.out.println("Vehicle is starting...");
    }
    public void stop() {
        System.out.println("Vehicle is stopping...");
    }
}

class Car extends Vehicle {
    public void playMusic() {
        System.out.println("Playing music in the car.");
    }
}
public class Main {
    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.start();
        myCar.stop();
        myCar.playMusic();
    }
}
What is wrapper data type classes (e.g. Integer, Double) in Java and Why we need wrapper class? (Cap is W, lower is P)
    1.Wrapper classes = object version of primitives
    2.Needed for collections, null handling, and utility methods
    3.Java handles conversions for you with autoboxing/unboxing
    4.To enable synchronization in multithreading.
What is the difference between HashMap and HashTable?
    HashMap: not thread safe, faster(no overhead of synchronization), allow 1 null key and mult null value.
    HashTable: thread safe, slower(all synchronization), not allow null key and value.
What is String pool in Java and why we need String pool? Explain String immunity.
    String pool is where java store string, JVM check if a string with same value exist in the pool first. if it exists, it returns a reference to the poled string, if not, create a new   string object and adds it to the pool.
    two purposes:
    1. Memory Efficiency: reusing strings with the same content, memory consumption is reduced.
    2. Performance improvement: String comparisons can be faster (when using == operator)
    String is immunity, once created, it cannot be modified, modify a string actually creates a new sting object.

Explain garbage collection? Explain types of garbage collection.
    Garbage collection is Java's automatic memory management mechanism that identifies and removes unused objects to free up memory.
    It prevents memory leaks and free programmers from manually managing memory, reduing programming errors.
    type of GC:
        1. Serial GC, single-threaded, pauses all application threads.
        2. Parallel GC, Multi-threaded, improves efficiency
        3. G1 GC, Divides heap into regions, prioritizes regions with most garbage.
        4. CMS, minimizes pauses by running concurrently.
What are access modifiers and their scopes in Java?
    Public (any class, package), Protected (same package, and by subclasses), Default (only same package), Private (only same class)
Explain final key word? (Filed, Method, Class)
    Final Variable cannot reassigned after initialization (define immutable constants)
    Final Method cannot be overridden in a subclass 
    Final class cannot be extended by any class (prevent subclass modifications)
Explan static keyword? (Filed, Method, Class). When do we usually use it?
    static field can be accessed without creating an object. share by all instances
    static methods cannnot access non-static members
    static cannot be used with local variables
    static classes must be nested classes
    constants are declared as static final
What is the differences between overriding and overloading?
    Overriding: using the same method name but with different parameters, within the same class, determined at compile time, not depend on inheritance, must be different parameters, can be different return type.
    Overriding: child class reimplementing parent's method, in child class, determined at runtime, need inheritance, must be same parameters and return, parent method must be public or protected, parent method cannot be final
Explain how Java defines a method signature, and how it helps on overloading and overriding.
    A method signature in Java consists of the method name and parameter list. it does not include the return type or access modifiers
    Overloading:
        1.different signatures with same method name allow overloading.
        2.compiler identifies which method to call based on arguments.
    Overriding:
        1.signatures must be identical parent and child classes.
        2.ensures proper polymorphic behavior.
What is the differences between super and this?
    super: refer to parent class, access parent class members, call parent class constructor, call overridden methods in parent class, only used in subclass.
    this: refer to current object, access current class member, call curr class constructor, call current class methods, can used in any method.
Explain how equals and hashCode work.
    equals(): 
            check if two objects are logically equivalent
            default implementation checks refernce equality (same memory location)
            when overriding, need to maintain reflexivity, symmetry, transitivity, and consistency
    hashcode():
            check two objects that are equal if have the same hash code.
            return an integer hash value used by hash-based collections (hashMap, hashSet)
            objects may have same hashcode (hash collision)
    a.equals(b) is true, a.hashcode() == b.hashcode() must be true
    a.hashcode() == b.hashcode() is true, a.equals(b) is false
What is the Java load sequence?
    The Java class loading sequence follows this order:
        1.Static blocks/variables - loaded when class is first referenced
        2.Instance blocks - executed during object creation before constructor
        3.Constructors - executed when a new object is created
What is Polymorphism ? And how Java implements it ?
    Polymorphism is the ability of an operation to behave differently based on the object it operates on. Java implements polymorphism in two ways:
        1.Compile-time polymorphism (Static binding): Implemented through method overloading
        2.Runtime polymorphism (Dynamic binding): Implemented through inheritance and method overriding
What is Encapsulation ? How Java implements it? And why we need encapsulation?
    Encapsulation is the bundling of data and methods that operate on that data within a single unit (class), and restricting access to some of the object's components.
    Java implements encapsulation through:
        1.Access modifiers: private, protected, public, and default (package-private)
        2.Getter and setter methods: To control access to private fields
    Why we need encapsulation:
        1.Data hiding: Protects data from unauthorized access
        2.Flexibility: Implementation can change without affecting other code
        3.Maintainability: Class internals can be modified without impacting other code
        4.Validation: Control over data modification through setter methods
Compare interface and abstract class with use cases.
    Interface: pure contract, only method signatures, only constants(public static final), no constructor, support multiple interfaces, all methods are public.
    Abstract: partial implementation, concrete and abstract methods, have instance variables, have constructor, single inheritance only, can private, protected.
    Interface:
        When you want to define a contract for unrelated classes
        When multiple inheritance is needed
        For defining types that can be used by widely different objects
        For capability-based design (e.g., Comparable, Runnable)
    Abstract Class:
        When related classes share code
        When you need constructors or non-public methods
        When you need to maintain state across methods
        For providing a partial implementation with some common functionality
