Question 1
See directory Coding/HW2/Question1.

Question 2: compare checked exception and unchecked exception
Checked when?	       Compile time	                Runtime
Must be caught/thrown  Yes               	        No
Common Examples	       IOException, SQLException	NullPointerException, ArithmeticException, IndexOutOfBoundsException

checked exception:
public class Main {
public static void readFile(String fileName) throws IOException {
FileReader file = new FileReader(fileName); 
BufferedReader fileInput = new BufferedReader(file);
        System.out.println(fileInput.readLine());
        fileInput.close();
    }
    public static void main(String[] args) {
        try {
            readFile("nonexistence.txt");
        } catch (IOException e) {
            System.out.println("Caught checked exception: " + e.getMessage());
        }
    }
}

Unchecked Exception:
public class Main {
public static void divide(int a, int b) {
int result = a / b; 
System.out.println("Result: " + result);
}
    public static void main(String[] args) {
        try {
            divide(10, 0);
        } catch (ArithmeticException e) {
            System.out.println("caught unchecked exception: " + e.getMessage());
        }
    }
}


Question 3: Can there multiple finally block and catch block?
Java allows multiple catch blocks but only one finally block per try.
try {
int result = 10 / 0;  
} catch (ArithmeticException e) {
System.out.println("Caught ArithmeticException");
} catch (Exception e) {
System.out.println("Caught Exception");
}


Question 4: both catch and finally return, what will be result?
If both catch and finally have return statements, the return value from the finally block takes precedence.


Question 5
throw:
Used to explicitly throw an exception in your code.
It is followed by an instance of an exception class, like throw new Exception("Error message").
The throw statement can be used inside methods, constructors, or any block of code to signal that an exception has occurred.
throws:
Used in method declarations to specify that a method may throw one or more exceptions.
It informs the calling code that the method might throw a particular exception, so the calling code should handle it (either by catching it or by declaring it with throws).
throws does not throw the exception; it just declares that the method may throw an exception.


Question 6
Java exception hierarchy and catch blocking ordering rules:
Checks catch top to bottom and uses the first match.
Catching Exception too early blocks all the remaining exceptions.

Catch more specific exceptions first (like ArithmeticException, NullPointerException)
Then catch more general exceptions (RuntimeException)
Finally, catch the most general ones (Exception)


Question 7
Optional is a container object used to represent a value that may or may not be present. It's introduced in Java 8 to deal with null values in a more expressive and safer way.

import java.util.Optional;
public class Main {
public static void main(String[] args) {
String name = null;

        Optional<String> optionalName = Optional.ofNullable(name);
        String result = optionalName
                .map(String::toUpperCase)   // Convert to uppercase if present
                .orElse("Default");         // Return "Default" if the value is absent
        System.out.println(result);  // Output: Default
    }
}


Question 8: Creational design patterns and structural patterns:
Creational design patterns are used to abstract the instantiation process, ensuring that objects are created in a way that suits the context.
Singleton Pattern, Factory Method Pattern, Abstract Factory Pattern, Builder Pattern, Prototype Pattern

Structural Design Patterns
Structural patterns deal with the composition of objects or classes to form larger structures while keeping them flexible and efficient. 
Adapter Pattern, Decorator Pattern, Facade Pattern, Composite Pattern, Bridge Pattern, Flyweight Pattern, Proxy Pattern


Question 9
Singleton
public class Singleton {
private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {                     
            synchronized (Singleton.class) {
                if (instance == null) {             
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}


Thread-Safety Explanation:
volatile ensures visibility of changes to instance across threads.
Double-checked locking reduces the performance cost of acquiring a lock.
Synchronization is only used during the first creation.

Factory
interface Shape {
void draw();
}

class Circle implements Shape {
public void draw() {
System.out.println("Drawing a Circle"); } }

class Square implements Shape {
public void draw() {
System.out.println("Drawing a Square"); } }

class ShapeFactory {
public static Shape getShape(String type) {
if (type == null) return null;
if (type.equalsIgnoreCase("circle")) return new Circle();
if (type.equalsIgnoreCase("square")) return new Square();
return null; } }

public class FactoryPatternDemo {
public static void main(String[] args) {
Shape shape1 = ShapeFactory.getShape("circle");
shape1.draw();  // Output: Drawing a Circle

        Shape shape2 = ShapeFactory.getShape("square");
        shape2.draw();  // Output: Drawing a Square
    }
}


Builder
class Computer {
// Required parameters
private final String CPU;
private final String RAM;

    // Optional parameters
    private final boolean hasSSD;
    private final boolean hasGPU;

    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.hasSSD = builder.hasSSD;
        this.hasGPU = builder.hasGPU;
    }

    public static class Builder {
        private final String CPU;
        private final String RAM;
        private boolean hasSSD = false;
        private boolean hasGPU = false;

        public Builder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }

        public Builder withSSD(boolean val) {
            this.hasSSD = val;
            return this;
        }

        public Builder withGPU(boolean val) {
            this.hasGPU = val;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }

    public void specs() {
        System.out.println("CPU: " + CPU + ", RAM: " + RAM + ", SSD: " + hasSSD + ", GPU: " + hasGPU);
    }
}


Question 10:SOLID and OCP
SOLID Principles
The SOLID principles are a set of five design principles intended to make software more maintainable, scalable, and robust.
S	Single Responsibility Principle	A class should have only one reason to change.
O	Open-Closed Principle	Software entities should be open for extension but closed for modification.
L	Liskov Substitution Principle	Subtypes must be substitutable for their base types without altering correctness.
I	Interface Segregation Principle	No client should be forced to depend on methods it does not use.
D	Dependency Inversion Principle	Depend on abstractions, not concretions. High-level modules should not depend on low-level modules.
Open-Closed Principle (OCP)
Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification.
This means that existing code should not be changed, but the behavior should be extendable to accommodate new requirements. This reduces the risk of introducing bugs in existing functionality and promotes maintainability.

Question 11:LSP
Liskov Substitution Principle (LSP)
If class B is a subtype of class A, then object of type A may be substituted with any object of type B.
This means that if the object of type A can do something, the object of type B can also do it.