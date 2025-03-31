# HW2
 1 - 1. Practice Collections
[Open Foler](../Coding/hw2/Practice_Collection_and_Array/collection/)
- [x] ArrayList  
- [x] LinkedList  
- [x] CopyOnWriteArrayList  
- [x] HashSet  
- [x] TreeSet  
- [x] HashMap  
- [x] TreeMap  
- [x] LinkedHashMap  
- [x] Additional Map  
- [x] Arrays  
- [x] Collections  
----
 1- 2. Assignment con't: additional coding questions, MUST use java stream api to resolve, please submit to "coding practice" channel in the same way you submit normal coding questions.
[Open Foler](../Coding/hw2/StreamAPI_on_LeetCodePractice/)
1. Two Sum
2. Group Anagrams
3. Top K frequent Element
4. Longest Word in Dictionary
5. Missing number
----
 2. Write code to compare and explain checkedException vs uncheckedException
 - checked exception: forced handle by `try-catch` by compiler, caused by external dependencies
 - unchecked exception: not forced to handle, can compile, but crash programming at runtime, have to fix
```java
public class Main {
    public static void main(String[] args) {
        // Checked Exception: FileNotFoundException
        // Scanner scanner = new Scanner(new File("note.txt")); // Must handle: Unhandled exception: java.io.FileNotFoundException
        try {
            Scanner scanner = new Scanner(new File("note.txt"));
        } catch (FileNotFoundException e){
            System.out.println(e);
        }
        
        // Unchecked Exception: ArithmeticException // Can compile successfully
        int x = 10 / 0;
    }
}
```
----
 3. Can there be multiple finally blocks? Can there be multiple catch blocks? Write code to explain.
- No, only one finally block
- Yes, can have mulitple catch to handle different exception types
```java
        try {
            Scanner scanner = new Scanner(new File("note.txt"));
        } catch (FileNotFoundException e){
            System.out.println(e);
        } finally {
            if (scanner != null) {
                scanner.close();
                System.out.println("Scanner closed in finally block");
            }
        }
```
```java
    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(new File("nonexistent.txt"));
            System.out.println(scanner.nextLine());
            int result = 10 / 0;

        } catch (FileNotFoundException e) {
            System.out.println("Caught FileNotFoundException: " + e.getMessage());

        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Caught generic Exception: " + e.getMessage());
        }

        System.out.println("Program continued after exception handling.");
    }
```
----
 4. When both catch and finally return values, what will be the final result?
- finally will be the final result, since finally block executes last, after try-catch block, its return value will override anything previous returend from `try` or `catch`
```java
    public static void main(String[] args) {
        System.out.println("Final res: " + testReturn()); // res is 3
    }
    public static int testReturn() {
        try {
            int x = 10 / 0;
            return 1;
        } catch (ArithmeticException e) {
            System.out.println("In catch.");
            return 2;
        } finally {
            System.out.println("Finally.");
            return 3;
        }
    }
```
----
 5. What is the difference between throw and throws?
  - `throws` used in method signiture to declare exception
  - `throw` used manually throws an exception in method
  ```java
  public void myMethod() throws IOException { ... }
  ```
  ```java
  if (age < 18) {
    throw new IllegalArgumentException("Age must be 18 or older"); 
  }
  ```
  ----
 6. Run the below three pieces codes, Noticed the printed exceptions. why do we put the Null/Runtime exception before Exception ?
  - Since Java checks `catch` blocks top-down, it executes the first matching exception. If a parent class like `Exception` comes first, it's a kind of sytax error, it will catch everything, and the more specific exceptions will be unreachable.
```java
public class Main {
    public static void main(String[] args) {
        int a = 0;
        int b = 3;
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
// ArithmeticException
```
```java
public class Main {
    public static void main(String[] args) {
        int a = 0;
        int b = 3;
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
// NullPointerException
```
```java
public class Main {
    public static void main(String[] args) {
        int a = 0;
        int b = 3;
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
// RuntimeException
```
----
 7. What is optional? why do you use it? write an optional example to demo how it avoids NPE.
  - Optional is a final concrete class, used to handle NPE
  ```java
      public static void main(String[] args) {
        String str = null;
        // Without Optional
        try {
            System.out.println(str.length()); // Crash: NPE
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        // With Optional handle NPE
        Optional<String> optionalStr = Optional.ofNullable(str);
        int length = optionalStr.map(String::length).orElse(0);
        System.out.println(length);
    }
  ```
----
 8. What are the types of design patterns in Java ? Name popular design patters, particularly Creational Patterns and Structural Patterns
  - Creational Patterns
    - Singleton
    - Factory
    - Builder
  - Structural Patterns
    - Adapter
    - Bridge
    - Composite
  - Behaviroal Patterns
----
 9.  Implement Singleton, Factory, and Builer patterns, explain how to guarantee thread-safe in your singleton pattern implementation.
```java
// Singleton
// Thread-Safe Version
public class Singleton {
    // Step 1: Only one instance, and mark it volatile to make all thread always access the updated instance 
    private static volatile Singleton instance;

    // Step 2: Private constructor so no one else can create it
    private Singleton() {
        System.out.println("Singleton instance created");
    }

    // Step 3: Public method to get the one-and-only instance
    public static Singleton getInstance() {
        if (instance == null) { // First check: no locking: all threads can run through the check at the same time
            synchronized (Singleton.class) { // Synchronized block, only allows one thread create a new instance if existing is null
                if (instance == null) { // Second check: inside lock
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```
```java
// Factory
// Product interface
interface Animal {
    void speak();
}

// Concrete classes
class Dog implements Animal {
    public void speak() { System.out.println("Woof!"); }
}

class Cat implements Animal {
    public void speak() { System.out.println("Meow!"); }
}

// Factory
class AnimalFactory {
    public static Animal createAnimal(String type) {
        if ("dog".equalsIgnoreCase(type)) return new Dog();
        if ("cat".equalsIgnoreCase(type)) return new Cat();
        return null;
    }
}

public class Test {
    public static void main(String[] args) {
        Animal a = AnimalFactory.create("dog");
        a.speak(); // Woof!

        Animal b = AnimalFactory.create("cat");
        b.speak(); // Meow!
    }
}
```
```java
// Builder
class Pizza {
    private String dough;
    private String sauce;
    private String topping;

    private Pizza(Builder builder) {
        this.dough = builder.dough;
        this.sauce = builder.sauce;
        this.topping = builder.topping;
    }

    public static class Builder {
        private String dough;
        private String sauce;
        private String topping;

        public Builder dough(String d) { this.dough = d; return this; }
        public Builder sauce(String s) { this.sauce = s; return this; }
        public Builder topping(String t) { this.topping = t; return this; }

        public Pizza build() {
            return new Pizza(this);
        }
    }

    public String toString() {
        return dough + " dough, " + sauce + " sauce, " + topping + " topping";
    }
}

public class Test {
    public static void main(String[] args) {
        Pizza p = new Pizza.Builder()
                .dough("Thin Crust")
                .sauce("Tomato Basil")
                .topping("Extra Cheese")
                .build();

        System.out.println(p); // Thin Crust dough, Tomato Basil sauce, Extra Cheese topping
    }
}
```
----
 10.  Explain SOLID Principles ? Further explain Open-Closed Principle (OCP) ?
- Single Respoinsibility
- Open-Close 
  - Code should be open for extension, but closed for modification.
- Liskov Substituition 
- Interface Segregation
- Dependency Inversion
----
 11. Liskov’s substitution principle states that if class B is a subtype of class A, then object of type A may be
substituted with any object of type B. What does this actually mean? (from OA ) choose your answer.
      1. It mean that if the object of type A can do something, the object of type B could also be able tp
perform the same thing ✅
      2. It means that all the objects of type A could execute all the methods present in its subtype B
      3. It means if a method is present in class A, it should also be present in class B so that the object of
type B could substitute object of type A.
      4. It means that for the class B to inherit class A, objects of type B and objects of type A must be same.
----

 12.    Watch design pattern video as below.
- singleton: https://www.bilibili.com/video/BV1Np4y1z7BU?p=22
- Factory: https://www.bilibili.com/video/BV1Np4y1z7BU?p=35&vd_source=310561eab1216a27f7accf859bf7f6d9
- Builder: https://www.bilibili.com/video/BV1Np4y1z7BU?p=50&vd_source=310561eab1216a27f7accf859bf7f6d9
- Publisher_Subscriber: https://www.bilibili.com/video/BV1Np4y1z7BU?p=114&vd_source=310561eab1216a27f7accf859bf7f6d9