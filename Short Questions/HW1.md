### Q1. 
**Encapsulation** in Java is a mechanism of wrapping the data and code acting on the data  together as a single unit. In encapsulation, the variables of a class will be hidden from other classes, and can be accessed only through the methods of their current class.



```java
public class EncapTest {
   private String name;
   private String idNum;
   private int age;

   public int getAge() {
      return age;
   }

   public String getName() {
      return name;
   }

   public String getIdNum() {
      return idNum;
   }

   public void setAge( int newAge) {
      age = newAge;
   }

   public void setName(String newName) {
      name = newName;
   }

   public void setIdNum( String newId) {
      idNum = newId;
   }
}
```

**Inheritance**: This allows one class (the subclass or derived class) to inherit attributes and methods from another class (the superclass or base class). Inheritance promotes code reuse and can create a hierarchy of classes. A subclass can extend or modify the behavior of the superclass.

```java
class Animal {
    public void speak() {
        System.out.println("The animal makes a sound");
    }
}

// Subclass (Derived class)
class Dog extends Animal {
    @Override
    public void speak() {
        System.out.println("The dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal myDog = new Dog();


        myDog.speak(); // Output: The dog barks
        
    }
}

```

***polymorphism*** means ‘having many forms’. In Java, polymorphism refers to the ability of a message to be displayed in more than one form. This concept is a key feature of *Object-Oriented Programming* and it allows objects to behave differently based on their specific class type.

***Method Overloading***

```java
class Helper {

    // Method with 2 integer parameters
    static int Multiply(int a, int b)
    {
        // Returns product of integer numbers
        return a * b;
    }

    // Method 2
    // With same name but with 2 double parameters
    static double Multiply(double a, double b)
    {
        // Returns product of double numbers
        return a * b;
    }
}

// Class 2
// Main class
class Geeks
{
    // Main driver method
    public static void main(String[] args) {
      
        // Calling method by passing
        // input as in arguments
        System.out.println(Helper.Multiply(2, 4));
        System.out.println(Helper.Multiply(5.5, 6.3));
    }
}
```

**Method Overriding***
``` java
// Base class Person
class Person {
  
    // Method that displays the 
    // role of a person
    void role() {
        System.out.println("I am a person.");
    }
}

// Derived class Father that 
// overrides the role method
class Father extends Person {
  
    // Overridden method to show 
    // the role of a father
    @Override
    void role() {
        System.out.println("I am a father.");
    }
}

public class Main {
    public static void main(String[] args) {
      
        // Creating a reference of type Person 
        // but initializing it with Father class object
        Person p = new Father();
        
        // Calling the role method. It calls the 
        // overridden version in Father class
        p.role();  
    }
}

```


### Q2.
Wrapper classes provide a way to use primitive data types (`int`, `boolean`, etc..) as objects. sometimes we must use wrapper classes, for example when working with Collection objects, such as `ArrayList`, where primitive types cannot be used (the list can only store objects):

```java
ArrayList<Integer> myNumbers = new ArrayList<Integer>(); // Valid
```


### Q3.

**Hashmap vs Hashtable** 

- HashMap is non-synchronized. It is not thread-safe and can’t be shared between many threads without proper synchronization code whereas Hashtable is synchronized. It is thread-safe and can be shared with many threads.
- HashMap allows one null key and multiple null values whereas Hashtable doesn’t allow any null key or value.
- HashMap is generally preferred over HashTable if thread synchronization is not needed.

``` java
import java.util.*;
import java.lang.*;
import java.io.*;
 
// Name of the class has to be "Main" 
// only if the class is public
class Ideone
{
    public static void main(String args[])
    {
        //----------hashtable -------------------------
        Hashtable<Integer,String> ht=new Hashtable<Integer,String>();
        ht.put(101," ajay");
        ht.put(101,"Vijay");
        ht.put(102,"Ravi");
        ht.put(103,"Rahul");
        System.out.println("-------------Hash table--------------");
        for (Map.Entry m:ht.entrySet()) {
            System.out.println(m.getKey()+" "+m.getValue());
        }
 
        //----------------hashmap--------------------------------
        HashMap<Integer,String> hm=new HashMap<Integer,String>();
        hm.put(100,"Amit");
        hm.put(104,"Amit");  
        hm.put(101,"Vijay");
        hm.put(102,"Rahul");
        System.out.println("-----------Hash map-----------");
        for (Map.Entry m:hm.entrySet()) {
            System.out.println(m.getKey()+" "+m.getValue());
        }
    }
}
```

### Q4.
***String Pool:*** Java stores string literals in a pool to save memory. Immutability ensures one reference does not change the value for others pointing to the same string.
***strings are immutable*** means their values cannot be changed once they are created. This feature enhances performance, security, and thread safety.

```java

import java.io.*;

class GFG {
  
    public static void main(String[] args) {
      
        String s1 = "java";
      
        // creates a new String "java rules", 
        // but does not change s1
        s1.concat(" rules");

        // s1 still refers to "java"
        System.out.println("s1 refers to " + s1);
    }
}


```

In the above example, even though we call concat to append `" rules"`, the original string `s1` still refers to `"java"`. The new string `"java rules"` is created, but it is not assigned to any variable, so it is lost.

### Q5.

**Garbage collection** in Java is an automatic memory management process that helps Java programs run efficiently. Java programs compile to bytecode that can be run on a Java Virtual Machine (JVM). When Java programs run on the JVM, objects in the heap, which is a portion of memory dedicated to the program. Eventually, some objects will no longer be needed. The garbage collector finds these unused objects and deletes them to free up memory.

1. **Serial Garbage Collector (Serial GC)**
2. **Parallel Garbage Collector (Throughput GC)**
3. **Concurrent Mark-Sweep (CMS) Collector** _(deprecated in later Java versions)_
4. G1 Garbage Collector (Garbage First GC)
5. Z Garbage Collector (ZGC)


``` java
class GCDemo {
    protected void finalize() {
        System.out.println("Object is garbage collected");
    }

    public static void main(String[] args) {
        GCDemo obj = new GCDemo();
        obj = null;
        System.gc();
    }
}
```


### Q6.
Access Modifiers in Java control the visibility and accessibility of classes, methods, and variables, ensuring better security and encapsulation.

**Private**: This is used for encapsulating sensitive data and internal helper methods that should not be accessed outside the class.
Example: Private fields in a model class with getter and setter methods.
**Default** (Package-Private): This is suitable for classes and methods that should only be accessible within the same package, often used in package-scoped utilities or helper classes.
**Protected**: This is ideal for methods and fields that should be accessible within the same package and subclasses, commonly used in inheritance-based designs like framework extensions.
**Public**: This is used for classes, methods, or fields meant to be accessible from anywhere, such as API endpoints, service classes, or utility methods shared across different parts of an application.

``` java
class AccessModifiers {
    public String publicField = "Public";
    private String privateField = "Private";
    protected String protectedField = "Protected";
    String defaultField = "Default";
}

public class Main {
    public static void main(String[] args) {
        AccessModifiers obj = new AccessModifiers();
        
        System.out.println(obj.publicField);   // Accessible
        // System.out.println(obj.privateField); // Compile-time error: private field
        System.out.println(obj.protectedField); // Accessible within same package
        System.out.println(obj.defaultField);   // Accessible within same package
    }
}

```


### Q7.

The `final` keyword in Java is used to indicate that a field, method, or class cannot be modified.

1. Final Field
A final field is a constant or a variable whose value cannot be changed once it is initialized. It can be initialized only once, either at the time of declaration or within the constructor if it's an instance variable.

```java
class MyClass {
    final int MAX_VALUE = 100;  // This value cannot be changed once initialized

    public void changeValue() {
        // MAX_VALUE = 200; // This would cause a compilation error
    }
}

```

2. Final Method
A final method is one that cannot be overridden by subclasses. This is useful when you want to ensure that a particular behavior defined in a superclass is preserved in its subclasses.

```java
class Animal {
    final void makeSound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    // This would cause a compilation error
    // void makeSound() { 
    //     System.out.println("Bark");
    // }
}
```

3. Final Class
A final class is a class that cannot be extended (i.e., no subclass can be created from it). This is used to prevent inheritance and ensure that the behavior of the class remains unchanged.

```java
final class FinalClass {
    // Class code here
}

// This would cause a compilation error
// class SubClass extends FinalClass { }

```


Q8.

The `static` keyword in Java is used to indicate that a field, method, or inner class belongs to the class itself rather than to instances (objects) of the class. This means that the field or method is shared across all instances of the class.

1. Static Field
A static field (also known as a class variable) is a field that is shared by all instances of the class. It belongs to the class rather than to any particular instance, so there's only one copy of the field in memory, regardless of how many objects of that class are created.

```java
class MyClass {
    static int counter = 0;  // This field is shared by all instances of MyClass

    MyClass() {
        counter++;  // Every time a new object is created, the counter is incremented
    }
}

public class Main {
    public static void main(String[] args) {
        MyClass obj1 = new MyClass();
        MyClass obj2 = new MyClass();
        System.out.println(MyClass.counter);  // Output: 2, since the static field is shared
    }
}

```

2. Static Method
A static method belongs to the class itself, rather than an instance of the class. It can be called using the class name without creating an object. Static methods can only directly access other static fields or static methods of the class. They cannot access instance variables or instance methods.

```java
class MyClass {
    static void printMessage() {
        System.out.println("Hello from a static method!");
    }
}

public class Main {
    public static void main(String[] args) {
        MyClass.printMessage();  // Call the static method without creating an instance of MyClass
    }
}

```

3. Static Class
A static class is an inner class that can be instantiated without an instance of the outer class. Static inner classes can only access static members (fields and methods) of the outer class, and they do not have access to the instance members of the outer class.

```java
class OuterClass {
    static int staticValue = 10;
    
    static class StaticInnerClass {
        void display() {
            System.out.println("Static value: " + staticValue);  // Can access static members of OuterClass
        }
    }
}

public class Main {
    public static void main(String[] args) {
        OuterClass.StaticInnerClass innerObj = new OuterClass.StaticInnerClass();
        innerObj.display();  // Output: Static value: 10
    }
}

```

### Q9.
Method Overloading is a Compile time polymorphism. In method overloading, more than one method shares the same method name with a different signature in the class. In method overloading, the return type can or can not be the same, but we have to change the parameter because, in java, we can not achieve method overloading by changing only the return type of the method. 

```java
import java.io.*;

class MethodOverloadingEx {

    static int add(int a, int b) { return a + b; }

    static int add(int a, int b, int c)
    {
        return a + b + c;
    }

    // Main Function
    public static void main(String args[])
    {
        System.out.println("add() with 2 parameters");
        // Calling function with 2 parameters
        System.out.println(add(4, 6));

        System.out.println("add() with 3 parameters");
        // Calling function with 3 Parameters
        System.out.println(add(4, 6, 7));
    }
}
```

Method Overriding is a type of runtime polymorphism. In method overriding, a method in a derived class has the same name, return type, and parameters as a method in its parent class. The derived class provides a specific implementation for the method that is already defined in the parent class.
```java
import java.io.*;

// Base Class
class Animal {
    void eat() {
        System.out.println("eat() method of base class");
        System.out.println("Animal is eating.");
    }
}

// Derived Class
class Dog extends Animal {
    @Override
    void eat() {
        System.out.println("eat() method of derived class");
        System.out.println("Dog is eating.");
    }
    
    // Method to call the base class method
    void eatAsAnimal() {
        super.eat();
    }
}
```

### Q10.
In Java, a method signature is part of the method declaration. It's the combination of the method name and the parameter list.

 **Overloading**: It's the ability to write methods that have the same name but accept different parameters. The Java compiler is able to discern the difference between the methods through their method signatures.
```java
public void display(int a) { 
    System.out.println(a);
}

public void display(String a) { 
    System.out.println(a);
}
```

**Method Overriding** involves defining a method in a subclass with the same method signature as in the parent class. The overriding method must match the method in the parent class in name, return type, and parameter list. Overriding allows a subclass to provide a specific implementation of a method already defined in the parent class.
```java
class Animal {
    public void sound() {
        System.out.println("Some sound");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Bark");
    }
}

```
### Q11.
In java, super keyword is used to access methods of the parent class while this is used to access methods of the current class.
```java
// Program to illustrate super keyword
// refers super-class instance

class Parent {
    // instance variable
    int a = 10;

    // static variable
    static int b = 20;
}

class Base extends Parent {
    void rr()
    {
        // referring parent class(i.e, class Parent)
        // instance variable(i.e, a)
        System.out.println(super.a);

        // referring parent class(i.e, class Parent)
        // static variable(i.e, b)
        System.out.println(super.b);
    }

    public static void main(String[] args)
    {
        // Uncomment this and see here you get
        // Compile Time Error since cannot use 'super'
        // in static context.
        // super.a = 700;
        new Base().rr();
    }
}

```

this keyword is a reserved keyword in java i.e, we can’t use it as an identifier. It is used to refer current class’s instance as well as static members. It can be used in various contexts as given below:
```java
// Program to illustrate this keyword 
// is used to refer current class
class RR {
    // instance variable
    int a = 10;

    // static variable
    static int b = 20;

    void GFG()
    {
        // referring current class(i.e, class RR) 
        // instance variable(i.e, a)
        this.a = 100;

        System.out.println(a);

        // referring current class(i.e, class RR) 
        // static variable(i.e, b)
        this.b = 600;

        System.out.println(b);
    }

    public static void main(String[] args)
    {
        // Uncomment this and see here you get 
        // Compile Time Error since cannot use 
        // 'this' in static context.
        // this.a = 700;
        new RR().GFG();
    }
}


```

### Q12.
1. equals() Method
The equals() method is used to compare two objects for equality. By default, the equals() method in the Object class compares memory references (i.e., whether two references point to the same object). However, many classes override equals() to compare the actual content of objects (i.e., if two objects are logically equal).

2. hashCode() Method
The hashCode() method returns an integer value that represents the object's hash code. This value is used in hash-based collections like HashMap or HashSet to determine the location of the object. The hashCode() method is used for bucketizing objects in these collections.

```java
class Person {
    private String name;
    private int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return age == person.age && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        // Simple hash calculation based on fields
        return 31 * name.hashCode() + age;
    }
}

```

Q13.
Summary of the Execution Order:
Static Block (executed when the class is loaded into memory, once for the class).
Static Variables (initialized during class loading, once for the class).
Instance Block (executed before the constructor, every time an object is created).
Constructor (executed after instance blocks, every time a new object is created).
```java
public class Example {

    // Static variable
    static int staticVar = 10;

    // Static block (executed once when the class is loaded)
    static {
        System.out.println("Static block executed.");
        staticVar = 20; // Modifying static variable
    }

    // Instance variable
    int instanceVar;

    // Instance block (executed before the constructor every time an object is created)
    {
        System.out.println("Instance block executed.");
        instanceVar = 30; // Initializing instance variable
    }

    // Constructor (executed after the instance block when an object is created)
    public Example() {
        System.out.println("Constructor executed.");
        System.out.println("instanceVar: " + instanceVar);
    }

    public static void main(String[] args) {
        System.out.println("Main method starts.");
        Example obj1 = new Example(); // First object creation
        Example obj2 = new Example(); // Second object creation

        System.out.println("staticVar: " + staticVar); // Accessing static variable
    }
}

```


### Q14.
***polymorphism*** means ‘having many forms’. In Java, polymorphism refers to the ability of a message to be displayed in more than one form. This concept is a key feature of *Object-Oriented Programming* and it allows objects to behave differently based on their specific class type.

***Method Overloading***

```java
class Helper {

    // Method with 2 integer parameters
    static int Multiply(int a, int b)
    {
        // Returns product of integer numbers
        return a * b;
    }

    // Method 2
    // With same name but with 2 double parameters
    static double Multiply(double a, double b)
    {
        // Returns product of double numbers
        return a * b;
    }
}

// Class 2
// Main class
class Geeks
{
    // Main driver method
    public static void main(String[] args) {
      
        // Calling method by passing
        // input as in arguments
        System.out.println(Helper.Multiply(2, 4));
        System.out.println(Helper.Multiply(5.5, 6.3));
    }
}
```

**Method Overriding***
``` java
// Base class Person
class Person {
  
    // Method that displays the 
    // role of a person
    void role() {
        System.out.println("I am a person.");
    }
}

// Derived class Father that 
// overrides the role method
class Father extends Person {
  
    // Overridden method to show 
    // the role of a father
    @Override
    void role() {
        System.out.println("I am a father.");
    }
}

public class Main {
    public static void main(String[] args) {
      
        // Creating a reference of type Person 
        // but initializing it with Father class object
        Person p = new Father();
        
        // Calling the role method. It calls the 
        // overridden version in Father class
        p.role();  
    }
}

```

### Q15.

**Encapsulation** in Java is a mechanism of wrapping the data and code acting on the data  together as a single unit. In encapsulation, the variables of a class will be hidden from other classes, and can be accessed only through the methods of their current class.



```java
public class EncapTest {
   private String name;
   private String idNum;
   private int age;

   public int getAge() {
      return age;
   }

   public String getName() {
      return name;
   }

   public String getIdNum() {
      return idNum;
   }

   public void setAge( int newAge) {
      age = newAge;
   }

   public void setName(String newName) {
      name = newName;
   }

   public void setIdNum( String newId) {
      idNum = newId;
   }
}
```

Encapsulation is crucial because it:

- **Protects** the integrity of the object.
- **Ensures** that the object’s state is modified only in controlled ways.
- Provides **security**, **modularity**, and **flexibility** in your code.

### Q16.

Abstract classes in Java serve as blueprints that cannot be instantiated directly and can include both abstract and concrete methods. They allow for partial implementation and can contain various types of member variables with any access modifier. A class can inherit only one abstract class. Interfaces, on the other hand, are contracts that specify a set of abstract methods that a class must implement, though they can include default and static methods from Java 8 onwards. Interfaces enable multiple inheritance of type, support polymorphism, and enforce a common protocol for different software components. Unlike abstract classes, all interface methods are implicitly public, and variables are public, static, and final.

```java
abstract class Animal {
    // Abstract method (must be implemented in the subclass)
    public abstract void sound();

    // Concrete method (already implemented)
    public void breathe() {
        System.out.println("This animal breathes oxygen.");
    }
}

interface Swim {
    // Abstract method (implicitly abstract)
    void swim();
}

class Fish extends Animal implements Swim {
    // Implementing the abstract method from Animal
    public void sound() {
        System.out.println("Fish make no sound.");
    }

    // Implementing the abstract method from Swim interface
    public void swim() {
        System.out.println("Fish can swim.");
    }
}

```

