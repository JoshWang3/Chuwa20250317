Question 1.
// 1. Encapsulation
class Vehicle {
    private String brand;
    private int speed;

    public Vehicle(String brand, int speed) {
        this.brand = brand;
        setSpeed(speed);
    }

    // Getters and setters
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed >= 0) {
            this.speed = speed;
        }
    }

    // Method to be overridden
    public void start() {
        System.out.println("The vehicle is starting.");
    }

    // Method overloading
    public void start(String location) {
        System.out.println(brand + " is starting from " + location + ".");
    }

    public void start(boolean isColdWeather) {
        if (isColdWeather) {
            System.out.println(brand + " is warming up before starting.");
        } else {
            System.out.println(brand + " starts instantly.");
        }
    }
}

// 2. Inheritance
class Car extends Vehicle {
    public Car(String brand, int speed) {
        super(brand, speed);
    }

    @Override
    public void start() {
        System.out.println(getBrand() + " car engine roars to life!");
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String brand, int speed) {
        super(brand, speed);
    }

    @Override
    public void start() {
        System.out.println(getBrand() + " motorcycle starts with a vroom!");
    }
}

// 3. Main class
public class Main {
    public static void main(String[] args) {
        Car car = new Car("Toyota", 120);
        Motorcycle moto = new Motorcycle("Yamaha", 90);

        Vehicle[] vehicles = new Vehicle[] { car, moto };

        for (Vehicle v : vehicles) {
            v.start();
        }

        car.start("garage");
        moto.start(true);
    }
}

Question 2: What is wrapper classes and why we need wrapper class?
A wrapper class in Java is a class that encapsulates (or "wraps") a primitive data type (like int, boolean, etc.) into an object.

Wrapper classes allow primitives to be treated as objects and provide:
Compatibility with Collections and Generics
Helpful static utility methods
Autoboxing/unboxing
Nullability

Question 3:difference HashMap and HashTable
1. Thread Safety:
Hashtable is thread-safe (uses synchronized methods).
HashMap is not thread-safe (use ConcurrentHashMap if needed).

2. Performance:
HashMap is faster in single-threaded environments.
Hashtable is slower due to locking.

3. Null Support:
HashMap allows one null key and multiple null values.
Hashtable allows neither null keys nor null values.

4. Age:
Hashtable is old and legacy.
HashMap is modern and preferred.

Question 4
What is String Pool in Java, why string pool, string immunity
String Pool:
A special memory area where Java stores string literals to save memory.
Example: "hello" is reused — not recreated.

Why String Pool:
Saves memory
Speeds up string comparison (== works if same pool object)

String Immutability:
Strings in Java are immutable — you can’t change them after creation.
Makes strings safe to share
Helps with caching and string pool
Improves security and thread safety


Question 5
Garbage Collection is the process by which the Java Virtual Machine (JVM) automatically reclaims memory by removing objects that are no longer reachable or needed.
Types of GC:
GC Type	Description
Serial GC	Single-threaded, simple, good for small apps
Parallel GC	Multi-threaded for both minor and major collections, good throughput
CMS (Concurrent Mark Sweep) GC	Collects in phases with minimal pause time, deprecated as of Java 9
G1 GC (Garbage First)	Prioritizes low-pause-time and is good for large heaps; default since Java 9
ZGC (Z Garbage Collector)	Scalable, low-latency GC; supports heaps from MBs to TBs with pauses <10ms
Shenandoah GC	Also low-pause-time GC, developed by Red Hat; pauses are independent of heap size


Question 6
Access Modifier	Scope
public	Accessible from anywhere (same package or different package).
protected	Accessible within the same package and subclasses (even in different packages).
default	Accessible only within the same package (no modifier specified).
private	Accessible only within the same class.


Question 7
final with Fields	Makes the field value constant after initialization.
final with Methods	Prevents the method from being overridden by subclasses.
final with Classes	Prevents the class from being extended.


Question 8
Static Fields (Variables)	Shared across all instances of the class. Same value for all objects.
Static Methods	Belong to the class itself, not to an instance. Can be called without creating an object.	
Static Nested Classes	A class inside another class that does not reply on an instance of the outer class. It can access static members of the outer class.	


Question 9
Feature	Overloading	Overriding
Method Signature	Same method name, but different parameters (type/number).	Same method name, same parameters.
Polymorphism Type	Compile-time polymorphism (resolved at compile time).	Runtime polymorphism (resolved at runtime).
Class Context	Happens within the same class.	Happens in inheritance.
Purpose	To provide multiple ways to call a method.	To customize or modify inherited behavior from a superclass.
Return Type	Return type can be the same or different.	Must have the same return type.
Access Modifier	No restriction.	Access modifier must be the same or more permissive in overridden methods.


Question 10
Java method signature consists of: method name, parameter types (in order)
Overloading	Overriding
Same method name, different param types (different signature)


Question 11
this:
Refers to the current instance of the class.
Used to access instance variables and methods of the current class.
Can also be used to call another constructor in the same class.

super:
Refers to the parent class (superclass).
Used to access instance variables and methods of the parent class.
Can also be used to call the parent class constructor.


Question 12
equals()
The default implementation compares memory addresses (i.e., reference equality) of two objects. If comparison between the object themselves is the desired behavior, then equals() must be overridden.

hashCode()
The default implementation returns an integer value for the object as a hash code value (to be used in hash-based collections).

Working with a HashMap
Java calls hashCode() on the key.
It uses the hash code to compute an index (e.g., hash % array.length) into an internal bucket array.
If no collision: the entry is added.
If collision (another key with the same hash code):
Java checks equals() to determine whether:
It's the same key (update the value).
A different key (add to the linked list or tree inside the bucket).


Question 13
The Java load sequence (also called the class loading and initialization sequence) describes how the JVM loads, links, and initializes classes before they are used at runtime.

+---------------------------------------------+
| 1. Loading                                  |
|---------------------------------------------|
| - ClassLoader loads .class file into memory |
| - Class object created                      |
+--------------------------------------------+
        ↓
+--------------------------------------------------+
| 2. Linking                                       |
|--------------------------------------------------|
| a. Verification                                  |
|    - Bytecode is checked for correctness         |
| b. Preparation                                   |
|    - Static fields allocated with default values |
| c. Resolution                                    |
|    - Symbolic references resolved to direct refs |
+--------------------------------------------------+
        ↓
+---------------------------------------+
| 3. Initialization                     |
|---------------------------------------|
| - Static blocks executed              |
| - Static fields assigned real values  |
| - <clinit>() method runs (if present) |
+---------------------------------------+
        ↓
+-----------------------------+
| 4. Instantiation (Optional) |
|-----------------------------|
| - Object is created (heap)  |
| - Constructor is called     |
+-----------------------------+
Triggered When:
A class is instantiated (new MyClass())
A static method or variable is accessed
Reflection is used (Class.forName("MyClass"))


Question 14
Polymorphism in Java means "many forms." It allows objects of different classes to be treated as objects of a common superclass. The most common use of polymorphism is when a parent class reference is used to refer to a child class object.

Polymorphism allows methods to behave differently based on the object calling them, providing flexibility and scalability in code.


Question 15
Encapsulation refers to bundling data (variables) and methods that operate on that data into a single unit, known as a class.

Benefit:
Data Security	Hides internal details, allowing controlled access.
Improved Flexibility	Internal changes don’t affect external code using the class.
Better Code Organization	Keeps related data and methods together, improving readability.


Question 16
Feature	Interface	Abstract Class
Purpose	Defines a contract for implementing classes	Define a common base class for related classes
Methods	All methods are implicitly abstract (until Java 8)	Can have both abstract and concrete methods
Fields	Can only have static final fields (constants)	Can have instance variables (fields)
Constructor	Cannot have constructors	Can have constructors
Access Modifiers	Methods are public by default	Methods can have any access modifiers
Inheritance	A class can implement multiple interfaces	A class can only extend one abstract class
Default Methods	From Java 8 onwards, can have default methods (with a body)