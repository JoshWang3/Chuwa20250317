# hw2
### 1. Practice collection
The codes are in the "Coding/hw2" folder.
### 2. Write code to compare and explain `checkedException` vs `uncheckedException`
| Feature | checkedException | uncheckedException |
| --- | --- | --- |
| Type | Complie time exception | Runtime exception |
| Handling | Must be handled (try-catch or throws) | Not required to handle |
| Compiler enforcement | Yes | No |

checkedException:
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader file = new FileReader("test.txt");
            BufferedReader br = new BufferedReader(file);
            System.out.println(br.readLine());
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```
uncheckedException:
```java
public class Main {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        System.out.println(nums[10]);   // java.lang.ArrayIndexOutOfBoundsException
    }
}
```
### 3. Can there be multiple finally blocks? Can there be multiple catch blocks? Write code to explain.
For one `try` block:
- Multiple `finally` blocks are NOT allowed.
- Multiple `catch` blocks ARE allowed to handle different exception types.
```java
public class Main {
    public static void main(String[] args) {
        try {
            int[] arr = new int[3];
            arr[5] = 10;            // Throws ArrayIndexOutOfBoundsException
            int result = 10 / 0;    // Throws ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Caught Generic Exception: " + e.getMessage());
        } finally {
            System.out.println("finally block");
        }
    }
}
```
### 4. When both catch and finally return values, what will be the final result?
The `finally` block overrides the `catch` block's return value.
```java
public class Main {
    public static void main(String[] args) {
        System.out.println(test());     // Output: 3
    }

    static int test() {
        try {
            int x = 10 / 0;
            return 1;
        } catch (ArithmeticException e) {
            System.out.println("Catch block executed");
            return 2; // This is overridden by finally
        } finally {
            System.out.println("Finally block executed");
            return 3; // This always executes and overrides catch return
        }
    }
}
```
### 5. What is the difference between **throw** and **throws**?
| Feature | throw | throws |
| --- | --- | --- |
| Definition | Manually throw an exception | Declare exceptions that a method might throw |
| Usage	| Inside the method body | In the method signature |
| Handling requirement | Must be followed by an exception object | Doesn't handle the exception, just declares it |
```java
public class Main {
    public static void main(String[] args) throws Exception{
        throw new Exception("Exception");
    }
}
```
### 6. Run the below three pieces codes, Noticed the printed exceptions. why do we put the Null/Runtime exception before Exception?
In Java, `catch` blocks are checked in order, and the first matching `catch` block handles the exception. `Exception` is the parent or ancestor of other exceptions. If we put `Exception` to the first, it will make subsequent specific `catch` blocks unreachable. All exceptions will reach the first `catch` block.
### 7. What is **optional**? why do you use it? write an optional example to demo how it avoids NPE.
- Optional is a **container object** which may or may not contain a non-null value.
- The main goal of Optional is to **avoid NullPointerExceptions (NPE)** and make code more readable by explicitly **handling null values**.
```java
import java.util.Optional;

public class Main {
    public static void main(String[] args){
        String name = null;

        Optional<String> optionalName = Optional.ofNullable(name);

        optionalName.ifPresent(n -> System.out.println(n.length()));

        int length = optionalName.map(String::length).orElse(0);
        System.out.println("length = " + length);   // Output: length = 0
    }
}
```
### 8. What are the types of design patterns in Java ? Name popular design patters, particularly Creational Patterns and Structural Patterns
There are three types of design patterns in Java:
1. Creational Patterns
2. Structural Patterns
3. Behavioral Patterns
- Creational Patterns: Singleton, Factory, Abstract Factory, Builder, Prototype
- Structural Patterns: Adapter, Bridge, Composite, Decorator, Facade, Flyweight, Proxy
### 9. Implement `Singleton` , `Factory` , and `Builer` patterns, explain how to guarantee thread-safe in your `singleton` pattern implementation.
Singleton:
- The `volatile` keyword ensures visibility and atomicity.
- The double-check locking prevents unnecessary synchronization after the instance is created.
- Using `synchronized` only when necessary ensures performance efficiency.
```java
public class Main {
    public static void main(String[] args){
        Singleton singleton = Singleton.getInstance();
        singleton.showMessage();
    }
}

class Singleton {
    private static volatile Singleton instance; // Ensures visibility across threads

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) { // First check (no locking)
            synchronized (Singleton.class) { // Locking only when necessary
                if (instance == null) { // Second check (thread-safe)
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Singleton Instance: " + this);
    }
}
```
Factory:
```java
public class Main {
    public static void main(String[] args){
        Animal dog = Factory.getAnimal("dog");
        dog.speak();    // Output: Woof! Woof!

        Animal cat = Factory.getAnimal("cat");
        cat.speak();    // Output: Meow! Meow!
    }
}
interface Animal {
    void speak();
}

class Dog implements Animal {
    public void speak() {
        System.out.println("Woof! Woof!");
    }
}

class Cat implements Animal {
    public void speak() {
        System.out.println("Meow! Meow!");
    }
}

class Factory {
    public static Animal getAnimal(String type) {
        if ("dog".equalsIgnoreCase(type)) {
            return new Dog();
        } else if ("cat".equalsIgnoreCase(type)) {
            return new Cat();
        }
        return null;
    }
}
```
Builder:
```java
public class Main {
    public static void main(String[] args){
        Car car = new Car.CarBuilder("V8", 4).setSunroof(true).build();
        car.showCar();  // Output: Car with Engine: V8, Wheels: 4, Sunroof: true
    }
}

class Car {
    private String engine;
    private int wheels;
    private boolean sunroof;
    
    private Car(CarBuilder builder) {
        this.engine = builder.engine;
        this.wheels = builder.wheels;
        this.sunroof = builder.sunroof;
    }
    
    public static class CarBuilder {
        private String engine;
        private int wheels;
        private boolean sunroof;

        public CarBuilder(String engine, int wheels) {
            this.engine = engine;
            this.wheels = wheels;
        }

        public CarBuilder setSunroof(boolean sunroof) {
            this.sunroof = sunroof;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

    public void showCar() {
        System.out.println("Car with Engine: " + engine + ", Wheels: " + wheels + ", Sunroof: " + sunroof);
    }
}
```
### 10. Explain **SOLID** Principles ? Further explain **Open-Closed Principle (OCP)** ?
The SOLID principles are a set of five design principles:
- **S** - Single Responsibility Principle (SRP): A class should have only one reason to change.
- **O** - Open-Closed Principle (OCP): A class should be open for extension, but closed for modification.
- **L** - Liskov Substitution Principle (LSP): Subclasses should be substitutable for their base classes without breaking functionality.
- **I** - Interface Segregation Principle (ISP): Don't force classes to implement methods they don’t use.
- **D** - Dependency Inversion Principle (DIP): Depend on abstractions (interfaces) rather than concrete implementations.

Open-Closed Principle (OCP): Use **inheritance** or **interfaces** instead of modifying existing code.
```java
public class Main {
    public static void main(String[] args){
        double regularDiscount = DiscountCalculator.calculate(new RegularDiscount(), 100);
        double vipDiscount = DiscountCalculator.calculate(new VIPDiscount(), 100);

        System.out.println("Regular Discount: $" + regularDiscount);    // Output: Regular Discount: $10.0
        System.out.println("VIP Discount: $" + vipDiscount);            // Output: VIP Discount: $20.0
    }
}

interface Discount {
    double apply(double amount);
}

class RegularDiscount implements Discount {
    public double apply(double amount) {
        return amount * 0.10; // 10% discount
    }
}

class VIPDiscount implements Discount {
    public double apply(double amount) {
        return amount * 0.20; // 20% discount
    }
}
class DiscountCalculator {
    public static double calculate(Discount discount, double amount) {
        return discount.apply(amount);
    }
}
```
### 11. Liskov’s substitution principle states that if class B is a subtype of class A, then object of type A may be substituted with any object of type B. What does this actually mean? (from OA ) choose your answer.
1. **It mean that if the object of type A can do something, the object of type B could also be able to perform the same thing**
2. ~~It means that all the objects of type A could execute all the methods present in its subtype B~~
3. ~~It means if a method is present in class A, it should also be present in class B so that the object of type B could substitute object of type A~~
4. ~~It means that for the class B to inherit class A, objects of type B and objects of type A must be same~~
### 12. Watch design pattern video as below.
singleton: https://www.bilibili.com/video/BV1Np4y1z7BU?p=22  
Factory: https://www.bilibili.com/video/BV1Np4y1z7BU?p=35&vd_source=310561eab1216a27f7accf859bf7f6d9  
Builder: https://www.bilibili.com/video/BV1Np4y1z7BU?p=50&vd_source=310561eab1216a27f7accf859bf7f6d9  
Publisher_Subscriber: https://www.bilibili.com/video/BV1Np4y1z7BU?p=114&vd_source=310561eab1216a27f7accf859bf7f6d9