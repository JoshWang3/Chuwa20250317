## 1. Write up Example code to demonstrate the three foundmental concepts of OOP.  

1. Encapsulation;
```Java
public class user {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setAge(int newAge) {
        age = newAge;
    }
}
```
2. Inheritance;
```Java
class Animal {
    public void eat() {
        System.out.println("This animal eats food.");
    }
}

class Dog extends Animal {
    public void bark() {
        System.out.println("The dog barks.");
    }
}

public class InheritanceExample {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.eat();
        myDog.bark();
    }
}
```
3. Polymorphism
```Java
class Animal {
    public void sound() {
        System.out.println("Animal sound.");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Woof.");
    }
}

class Cat extends Animal {
    @Override
    public void sound() {
        System.out.println("Meow.");
    }
}

public class InheritanceExample {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.sound(); // Woof
        Dog myCat = new Cat();
        myCat.sound(); // Meow
    }
}
```

## 3. What is wrapper data type classes (e.g. Integer, Double) in Java and Why we need wrapper class?
A wrapper class is the corresponding class to a primitive data type. The process of converting between them is called boxing and unboxing. Since Java is a object-oriented programming language, having such classes ensures we can treat all data as objects.

## 4. What is the difference between HashMap and HashTable?
- HashMap is not thread-safe while hashtable can be thread-safe using synchronized. 
- HashMap allows `null` keys and values but HashTable does not
- HashMap has fail-fast iterators

## 5. What is String pool in Java and why we need String pool? Explain String immunity.
String pool stores literal strings for all string variables. All strings declared with `""` wil be stored in string pool. 

String pool ensures memory efficiency as it only preserves one copy of the same literal string. Also, it helps make string comparison faster as when comparing with `==` we can compare reference only.

String immunity is why string pool can exist. Since strings cannot be modified in Java, it is safe to reuse strings.
- Strings can be used as hash keys, since they won't change and hash code can be cached.
- Strings are thread safe as they cannot be modified
- Strings can be used for storing urls or api keys and are dependable.
## 6. Explain garbage collection? Explain types of garbage collection.
Garbage collection automatically manages memory for JVM. There is no need to manually declare or free memory. Therefore, it prevents memory leak or dangling pointers.

| Type               | Pros                          | Cons                         |
|--------------------|-------------------------------|------------------------------|
| Reference Counting | Simple                        | Fails on circular references |
| Mark and Sweep     | Handles circular refs         | Can cause pauses             |
| Generational       | Optimized for object lifespan | Complex implementation       |
| Copying            | Fast, no fragmentation        | Wastes half the memory       |
| Mark-Compact       | Avoids fragmentation          | Slower than copying          |
| Concurrent         | Low pause time                | Complex, more overhead       |


## 7. What are access modifiers and their scopes in Java?
Access modifiers control visibility of class (instances) and members (methods and variables). 

| Modifier   | Same Class | Same Package | Subclass (Different Package) | Other Classes |
|------------|------------|--------------|------------------------------|----------------|
| public     | ✅         | ✅           | ✅                           | ✅             |
| protected  | ✅         | ✅           | ✅                           | ❌             |
| default    | ✅         | ✅           | ❌                           | ❌             |
| private    | ✅         | ❌           | ❌                           | ❌             |


## 8. Explain final key word? (Field, Method, Class)
- Field: 
  - Primitive types: cannot change values
  - Reference types: cannot change reference, but internal state can be changed
- Method:
  - Cannot be overriden by a child class.
- Class:
  - Cannot be extended.

## 9.  Explan static keyword? (Filed, Method, Class). When do we usually use it?
- Fields: 
  - Belongs to the class
  - Shared by all instances and only one copy is stored in memory.
- Method:
  - Belongs to the class
  - Can be called without an instance
  - Cannot be abstract
  - Cannot have `this` or `super`
  - Can only contain static fields 
- Class
  - Can only be applied to nested classes
  - Doesn't rely on outside class instances and cannot use non-static fields and methods in outside class
## 🔧 `static` Keyword Use Cases in Java

| Use Case                      | Description                                                                 | Example                            |
|------------------------------|-----------------------------------------------------------------------------|------------------------------------|
| Shared Field (Class Variable)| Shared across all instances of a class.                                     | `static int counter;`             |
| Utility/Helper Methods       | Methods that don't need object state.                                       | `Math.max(a, b);`                 |
| Constant Declaration         | Often used with `final` for constants.                                      | `static final double PI = 3.14;`  |
| Main Method                  | Entry point for the program.                                                | `public static void main(...)`    |
| Static Block                 | Used to initialize static data. Runs once when the class is loaded.         | `static { ... }`                  |
| Static Nested Class          | Nested class that doesn’t access instance members of the outer class.       | `static class Node { ... }`       |
| Global Configuration         | Store global config/settings in static fields.                              | `static String DB_URL;`           |
| Singleton Pattern Support    | Used for static instance and getter method.                                 | `static Singleton instance;`      |


## 10. What is the differences between overriding and overloading?
Overrideing rewrites the implementation of a method from parent class.

Overloading defines multiple parameters for methods with the same name.

| **Method Overloading**                                       | **Method Overriding**                                        |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| Overloading happens at **compile time.**                     | Overriding happens at **runtime**                            |
| Gives **better performance** because the binding is being done at compile time. | Gives **less performance** because the binding is being done at run time. |
| **Private** and **final** methods can be **overloaded**.     | **Private** and **final** methods can **NOT** be overridden. |
| Return type of method does not matter in case of method overloading. | Return type of method must be the same in the case of overriding. |
| **Arguments must be different** in the case of overloading. (method signature) | Arguments must be the same in the case of overriding.        |
| It is being done in the **same class**.                      | Base and **derived(child) classes** are required here.       |
| Mostly used to increase the **readability** of the code.     | Mostly used to provide the **implementation** of the method that is already provided by its base class. |

## 11. Explain how Java defines a method signature, and how it helps on overloading and overriding.
In Java, method signature is defined as method name + parameter types (in order)

Overloading depends on method signature. As long as the parameters (signatures) are different, there can be multiple methods with the same name.

Overriding requires exact match of method signature. The return type also have to be the same or covariant.

In other words, overloading requires exactly different signatures but overriding requires exactly the same signature.

## 12. What is the differences between super and this?
`super` refers to the parent class so it's referencing to a class.

`this` refers to current instance of the class so it refers to an object.

## 13.  Explain how equals and hashCode work.
`equals()` compares logical equality, not reference equality. 

`hashCode()` returns an `int` representing the hash code of an object.

If two objects are equal `equals()`, they must have the same `hashCode()`. But they are not gauranteed to be equal if they have the same `hashCode()`.

## 14.  What is the Java load sequence?
1. Parent Class
   - Static variables  
   - Static blocks  

2. Child Class
   - Static variables  
   - Static blocks  

3. Parent Class
   - Instance variables  
   - Instance initializer blocks  
   - Constructor  

4. Child Class
   - Instance variables  
   - Instance initializer blocks  
   - Constructor  


## 15.  What is Polymorphism ? And how Java implements it ?
Polymorphism refers to the same object exhibiting different forms and behaviors.

Static Polymorphism - Overload (same class) - compile time
Dynamic Polymorphism - Override (child class) - run time

## 16.  What is Encapsulation? How Java implements it? And why we need encapsulation?
Encapsulation in OOP refers to binding the data and the methods to manipulate that data together in a single unit (class).

In Java we have acces modifiers and can set up getters and setters to access and modify internal data for objects.

Main reasons for having encapsulation includes security, data validation and Modularity.

## 17.  Compare interface and abstract class with use cases.
| Feature                            | **Interface**                                                | **Abstract Class**                                           |
| ---------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Default Method Implementations** | **Java 8 and later only**: can provide `default` method implementations, but historically had no method implementations. | Can provide method implementations (both abstract and concrete methods). |
| **Fields**                         | Cannot have instance variables, only `public static final` constants (i.e., constants only). | Can have instance variables (fields), which can be inherited by subclasses. |
| **Constructors**                   | Cannot have constructors, because interfaces cannot be instantiated. | Can have constructors, used for initializing instance variables. Subclasses can call super constructors. |
| **State (fields)**                 | Cannot maintain state (no instance variables).               | Can maintain state (can have instance variables).            |
| **Multiple Inheritance**           | A class can implement multiple interfaces (supports multiple inheritance of types). | A class can only extend one abstract class (single inheritance of implementation). |
| **Access Modifiers**               | Methods are implicitly `public`. Java 9 allows `private` methods for helper functions but no `protected` methods. | Can have any access modifiers: `public`, `protected`, `private`. |
| **Use Case**                       | Defines a contract or behavior that unrelated classes can implement (i.e., behavioral abstraction). | Used to define a base class with common behavior and state for related classes (i.e., shared functionality). |
| **Inheritance Type**               | Represents "can do" or "is able to" behavior (e.g., `CanRun`, `Flyable`). | Represents "is-a" relationship (e.g., `Animal`, `Vehicle`).  |
| **Static Methods**                 | Can have static methods since Java 8. These methods belong to the interface, not to implementing classes. | Can have static methods. These methods belong to the abstract class. |
| **Instantiation**                  | Cannot be instantiated directly.                             | Cannot be instantiated directly (still requires subclassing). |
| **Multiple Behaviors**             | Useful for adding common behavior across unrelated classes (e.g., a class can implement multiple interfaces like `Runnable`, `Serializable`). | Suitable for defining shared behavior among related classes (e.g., common base functionality). |
| **Constructor Calling**            | Cannot invoke constructors of an interface in implementing classes. | Can invoke the abstract class's constructor using `super()` in subclasses. |
| **Suitability**                    | Ideal for situations where classes need to implement **shared behaviors** but don’t share a common ancestor (class hierarchy). | Ideal for **related classes** that share common behavior and state. |
| **Flexibility**                    | More flexible due to multiple inheritance of interfaces.     | Less flexible since a class can only extend one abstract class. |

#### Interface:
```Java
interface Flyable {
    void fly();
}

class Bird implements Flyable {
    public void fly() {
        System.out.println("Bird flies using wings.");
    }
}

class Airplane implements Flyable {
    public void fly() {
        System.out.println("Airplane flies using jet engines.");
    }
}
```

#### Abstract Class:

```Java
abstract class Animal {
    String name;

    Animal(String name) {
        this.name = name;
    }

    abstract void makeSound();

    void eat() {
        System.out.println(name + " is eating.");
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);
    }

    void makeSound() {
        System.out.println(name + " says: Woof!");
    }
}

```