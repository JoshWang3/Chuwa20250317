1. Refer to Coding/hw2_q1

2. Write code to compare and explain checkedException vs uncheckedException
```Java
public class ExceptionExample {

    // Checked Exception 示例
    public void readFile() throws IOException {
        FileReader file = new FileReader("nonexistent.txt");
    }

    // Unchecked Exception 示例
    public void divideByZero() {
        int result = 10 / 0; // ArithmeticException
    }

    public static void main(String[] args) {
        ExceptionExample ex = new ExceptionExample();

        try {
            ex.readFile();
        } catch (IOException e) {
            System.out.println("Caught checked exception: " + e.getMessage());
        }

        ex.divideByZero(); // 不需要 try-catch 也能编译，但运行时报错
    }
}
```
| 类型    | CheckedException（已检查异常）       | UncheckedException（未检查异常）                     |
| ----- | ----------------------------- | --------------------------------------------- |
| 编译时检查 | 需要显式捕获或在方法签名中声明               | 不强制处理                                         |
| 父类    | 继承自 `Exception`               | 继承自 `RuntimeException`                        |
| 示例    | `IOException`, `SQLException` | `NullPointerException`, `ArithmeticException` |


3. Can there be multiple finally blocks? Can there be multiple catch blocks? Write code to explain.

Can: multiple catch blocks

Can't: multiple finally blocks
```Java
public class MultiCatchFinally {

    public static void main(String[] args) {
        try {
            int a = 5 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException");
        } catch (Exception e) {
            System.out.println("Caught general Exception");
        } finally {
            System.out.println("This is finally block");
        }

        // 下面的代码是非法的：不能有多个 finally
        /*
        try {
            // ...
        } finally {
            // ...
        } finally { // Compilation Error
            // ...
        }
        */
    }
}
```


4. When both catch and finally return values, what will be the final result?
The final result would be the finally return value.


5. What is the difference between throw and throws?

|    | `throw`     | `throws`     |
| -- | ----------- | ------------ |
| 作用 | 抛出一个具体的异常对象 | 声明方法可能抛出哪些异常 |
| 位置 | 方法内部        | 方法签名         |
| 后接 | 一个异常对象      | 一个或多个异常类型    |
```Java
public class ThrowVsThrows {

    // throws 用法：声明此方法可能抛出异常
    public static void doSomething() throws IOException {
        throw new IOException("File not found"); // throw 用法：实际抛出异常
    }

    public static void main(String[] args) {
        try {
            doSomething();
        } catch (IOException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}

```


6. Run the below three pieces codes, Noticed the printed exceptions. why do we put the Null/Runtime exception before Exception ?

Java checks exceptions top-down, and only the first matching catch block is used.

Exception is the parent of RuntimeException and NullPointerException, so if it's first, the others are unreachable → compile error.

Correct Order:
```Java
catch (NullPointerException e) { ... }
catch (RuntimeException e) { ... }
catch (Exception e) { ... } // Always last
```


7. What is optional? why do you use it? write an optional example to demo how it avoids NPE.

__Optional<T>__ is a container that may or may not hold a non-null value.

It's been used to:
- Avoid NullPointerException
- Make absence of value explicit
- Encourage functional-style programming
```Java
import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        String name = null;

        // Avoid NPE
        Optional<String> optionalName = Optional.ofNullable(name);
        String result = optionalName.orElse("Default Name");

        System.out.println(result); // prints "Default Name"
    }
}
```


8. What are the types of design patterns in Java ? Name popular design patters, particularly Creational Patterns and Structural Patterns

| Category   | Examples                               |
|------------| -------------------------------------- |
| Creational | Singleton, Factory, Builder, Prototype |
| Structural | Adapter, Decorator, Proxy, Composite   |
| Behavioral | Strategy, Observer, Command, Template  |


9. Implement Singleton , Factory , and Builer patterns, explain how to guarantee thread-safe in your singleton pattern implementation.

```Java
public class Singleton { 
    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) { // Using synchornized to guarantee thread-safe
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
}
```
```Java
interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() { System.out.println("Circle"); }
}

class ShapeFactory {
    public static Shape getShape(String type) {
        return switch (type) {
            case "circle" -> new Circle();
            default -> throw new IllegalArgumentException("Unknown");
        };
    }
}
```
```Java
class User {
    private final String name;
    private final int age;

    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    public static class Builder {
        private String name;
        private int age;

        public Builder name(String name) { this.name = name; return this; }
        public Builder age(int age) { this.age = age; return this; }
        public User build() { return new User(this); }
    }
}

```


10. Explain SOLID Principles ? Further explain Open-Closed Principle (OCP) ?

| Principle | Description                                                   |
| --------- | ------------------------------------------------------------- |
| **S**     | **Single Responsibility**: One class = one job                |
| **O**     | **Open/Closed**: Open for extension, closed for modification  |
| **L**     | **Liskov Substitution**: Subclass must be substitutable       |
| **I**     | **Interface Segregation**: Small interfaces > large ones      |
| **D**     | **Dependency Inversion**: Depend on abstraction, not concrete |


__Open-Closed Principle (OCP)__

You shouldn't modify existing code to add new behavior.
Instead, you extend it (e.g. via subclassing, interfaces, strategy pattern).

11. Liskov’s substitution principle states that if class B is a subtype of class A, then object of type A may be 
substituted with any object of type B. What does this actually mean? (from OA ) choose your answer.
- It mean that if the object of type A can do something, the object of type B could also be able to perform the same thing
- It means that all the objects of type A could execute all the methods present in its subtype B
- It means if a method is present in class A, it should also be present in class B so that the object of type B could substitute object of type A.
- It means that for the class B to inherit class A, objects of type B and objects of type A must be same.

Correct: 
"It means that if the object of type A can do something, the object of type B could also be able to perform the same thing."


12. Watch design pattern video as below.
singleton: https://www.bilibili.com/video/BV1Np4y1z7BU?p=22
Factory: https://www.bilibili.com/video/BV1Np4y1z7BU?p=35&vd_source=310561eab1216a27f7accf859bf7f6d9
Builder: https://www.bilibili.com/video/BV1Np4y1z7BU?p=50&vd_source=310561eab1216a27f7accf859bf7f6d9
Publisher_Subscriber: https://www.bilibili.com/video/BV1Np4y1z7BU?p=114&vd_source=310561eab1216a27f7accf859bf7f6d9