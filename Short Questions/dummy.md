### Short Questions
1. Practice collection
2. Write code to compare and explain checkedException vs uncheckedException
checked exception: at compile time, must be handled or declared, must be caught(try-catch) or using throws. IOException, SQLException
public class checkedEx {
    public static void main(String[] args) {
        try {
            throw new IOException("checked exception");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
Unchecked exception: at runtime, NullPointerException
public class UncheckedEx {
    public static void main(String[] args) {
        String str = null;
        System.out.println(str.length());
    }
}
3. Can there be multiple finally blocks? Can there be multiple catch blocks? Write code to explain.
only one finally per try statement. multiple catch can exist in one blocks
public class Main {
    public static void main(String[] args) {
        try {
            int result = 1 / 0;
        } catch (ArithmeticException) {
            System.out.println("Arithmetic Exception");
        } catch (Exception e) {
            System.out.println("General Exception");
        } finally {
            System.out.println("Finally block runs always");
        }
    }
}
4. When both catch and finally return values, what will be the final result?
public class ReturnValues {
    public static void main(String[] args) {
        System.out.println(test());
    }
    public static int test() {
        try {
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }
    }
}
return 3. finally will override previous return statement.
5. What is the difference between throw and throws?
throw: throw an exception, followed by an instance, used inside method.
throws: declares method can throw exception, followed by exception class, used in method signature.
6. Run the below three pieces codes, Noticed the printed exceptions. why do we put the Null/Runtime exception before Exception ?
public class Main {
public static void main(String[] args) {
int a = 0;
int b = 3
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
public class Main {
public static void main(String[] args) {
int a = 0;
int b = 3
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
public class Main {
public static void main(String[] args) {
int a = 0;
int b = 3
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
if put exception before runtimeexception or nullpointerexception, it will never reach those two exceptions.
7. What is optional? why do you use it? write an optional example to demo how it avoids NPE.
To avoid NullPointerException
public class OptionalDemo {
    public static void main(String[] args) {
        Optional<String> str = Optional.ofNullable(null);
        System.out.println(str.orElse("str null"));
    }
}
8. What are the types of design patterns in Java ? Name popular design patters, particularly Creational Patterns and Structural Patterns
Creational Patterns:
Singleton, Factory, Builder, Prototype, Abstract Factory.
Structural Patterns:
Adapter, Decorator, Proxy, Composite, Facade
9. Implement Singleton, Factory, and Builer patterns, explain how to guarantee thread-safe in your singleton pattern implementation.
public class Singleton {
    private static volatile Singleton instance;
    private Singleton() {
    }
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized(Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
Thread Safety Explanation:
volatile ensures visibility and prevents instruction reordering.

interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

class Rectangle implements Shape {
    public void draw() {
        System.out.println("Drawing a Rectangle");
    }
}

class ShapeFactory {
    public Shape getShape(String shapeType) {
        if (shapeType == null) return null;

        switch(shapeType.toLowerCase()) {
            case "circle": return new Circle();
            case "rectangle": return new Rectangle();
            default: return null;
        }
    }
}

10. Explain SOLID Principles ? Further explain Open-Closed Principle (OCP) ?
S: Single Responsibility Principle
O: Open/Closed Principle: entities open to extension, close for modification, in intefaces or abstract classes. 
L: Liskov Substitution Principle
I: Interface Segregation Principle
D: Dependency Inversion Principle
11. Liskov’s substitution principle states that if class B is a subtype of class A, then object of type A may be substituted with any object of type B. What does this actually mean? (from OA ) choose your answer.
1
1. It mean that if the object of type A can do something, the object of type B could also be able tp
perform the same thing
12. d9
2. It means that all the objects of type A could execute all the methods present in its subtype B
3. It means if a method is present in class A, it should also be present in class B so that the object of
type B could substitute object of type A.
4. It means that for the class B to inherit class A, objects of type B and objects of type A must be same.
Watch design pattern video as below.
singleton: https://www.bilibili.com/video/BV1Np4y1z7BU?p=22
Factory: https://www.bilibili.com/video/BV1Np4y1z7BU?p=35&vd_source=310561eab1216a27f7accf859bf7f6
Builder: https://www.bilibili.com/video/BV1Np4y1z7BU?p=50&vd_source=310561eab1216a27f7accf859bf7f6d
9
Publisher_Subscriber: https://www.bilibili.com/video/BV1Np4y1z7BU?p=114&vd_source=310561eab1216a27f
7accf859bf7f6d9