## HW1

### 1. Example Code Demonstrating OOP Concepts
#### **Encapsulation**
Encapsulation means bundling data and methods that operate on the data within a single unit, typically a class, while restricting direct access.

```java
class Person {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class Main {
    public static void main(String[] args) {
        Person p = new Person();
        p.setName("Ce Zhao");
        System.out.println(p.getName());
    }
}
```

#### **Polymorphism**
**Method Overloading:**
```java
class MathOperations {
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
	
	int add(int a, int b, int c) {
		return a + b + c;
	}
}

public class Main {
    public static void main(String[] args) {
        MathOperations m = new MathOperations();
	int x = m.add(1, 2);
	double y = m.add(0.3d, 0.71d);
	int z = m.add(1, 2, 3);
	System.out.println("x: " + x + "\ny: " + y + "\nz: " + z);
    }
}
```

**Method Overriding:**
```java
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal dog = new Dog();
	Animal a = new Animal();
	dog.sound();
	a.sound();
    }
}
```

#### **Inheritance**
```java
class Animal {
    void eat() {
        System.out.println("This animal eats food");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("The dog barks");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
	dog.bark();
	dog.eat();
    }
}
```

### 2. Wrapper Classes
```java
public class Main {
    public static void main(String[] args) {
        Integer num = Integer.valueOf(10);
        int pri_num = num.intValue();
	List<Integer> l = new ArrayList<Integer>();
        System.out.println("Wrapper value: " + num);
        System.out.println("Primitive value: " + pri_num);
    }
}
```
Wrapper classes are used for:
- Collections (`ArrayList<Integer>`) 
- Some methods like `Integer.parseInt("123")`
- `Integer.MAX_VALUE, Integer.MIN_VALUE`
### 3. HashMap vs HashTable
| Feature       | `HashMap` | `Hashtable` |
|--------------|----------|------------|
| Thread-Safety | No | Yes |
| Performance   | Faster | Slower |
| Null Keys/Values | Allows one `null` key, multiple `null` values | No `null` keys or values |
```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // HashMap
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "Apple");
        hashMap.put(2, "Banana");
        hashMap.put(null, "Cherry"); 
        System.out.println("HashMap: " + hashMap);

        // Hashtable
        Hashtable<Integer, String> hashTable = new Hashtable<>();
        hashTable.put(1, "Orange");
        hashTable.put(2, "Grapes");
        // hashTable.put(null, "Mango"); // Throws NullPointerException
        System.out.println("Hashtable: " + hashTable);
    }
}
```

## 4. String Pool in Java and String Immutability
- The String Pool is a memory area in the heap where Java stores string to avoid duplication.
- String Immutability means once a `String` object is created, it cannot be changed.

```java
public class Main {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "hello";
        System.out.println(s1 == s2); 

        String s3 = new String("hello");
        System.out.println(s1 == s3); 
    }
}
```

## 5. Garbage Collection & Its Types
Garbage collection in Java automatically removes unreferenced objects. Types:
- Serial GC – Uses a single thread.
- Parallel GC – Uses multiple threads.
- G1 GC – Balances pause time and throughput.
- ZGC – Designed for low-latency applications.
```java
public class Main {
    protected void finalize() {
        System.out.println("Object is garbage collected");
    }
    
    public static void main(String[] args) {
        Main obj1 = new Main();
        obj1 = null;
        System.gc();
    }
}
```

### 6. Access Modifiers
| Modifier | Scope |
|----------|-------|
| `private` | Within the same class |
| `default` | Within the same package |
| `protected` | Within the same package and subclasses |
| `public` | Accessible from everywhere |

```java
pubic class Main {
    private int privateVar = 10;
    protected int protectedVar = 20;
    public int publicVar = 30;

    public static void main(String[] args) {
        ModifierExample obj = new ModifierExample();
        System.out.println("Private: " + obj.privateVar);
        System.out.println("Protected: " + obj.protectedVar);
        System.out.println("Public: " + obj.publicVar);
    }
}
```

### 7. Final Keyword
- Final Variable cannot be reassigned.
- Final Method cannot be overridden.
- Final Class cannot be extended.
```java
public final class Main {
    final int MAX_VALUE = 100;

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println("Max Value: " + m.MAX_VALUE);
    }
}
```

### 8. Static Keyword
- Static field: Shared by all instances.
- Static method: Can be called without creating an object.
- Static Block – A block of code that runs once when the class is loaded.
- Static class: Can exist as a nested class.
```java
class StaticExample {
    static int count = 0;

    static {
        System.out.println("Static block executed");
    }

    StaticExample() {
        count++;
    }

    static void displayCount() {
        System.out.println("Count: " + count);
    }
}

public class Main {
	 public static void main(String[] args) {
        StaticExample s1 = new StaticExample();
        StaticExample s2 = new StaticExample();
        StaticExample.displayCount();
    }
}

```

### 9. Overriding vs Overloading
| Feature | Overriding | Overloading |
|---------|-----------|------------|
| Definition | Changing method in child class | Same method name, different parameters |
| Return Type | Must be same | Can be different |
| Access Modifier | Cannot reduce visibility | No restrictions |
```java
class Car {
    void engine() {
        System.out.println("A car has a engine");
    }
	String engine(String s) {
		return s;
	}
}

class Honda extends Car {
    @Override
    void engine() {
        System.out.println("V8");
    }
}

public class Main {
    public static void main(String[] args) {
        Car car = new Honda();
        car.engine();
		System.out.println(car.engine("I6"));
    }
}

```

### 10. Method Signature
A method signature consists of **method name and parameter types**. It helps in overloading and overriding.
```java
public class Main {
    void print(String s) { System.out.println("String parameter"); }
    void print(int n) { System.out.println("Integer parameter"); }
	public static void main(String[] args) {
        Main m = new Main();
  		m.print("s");
		m.print(1);
    }
}
```

## 11. `super` vs `this`

| Keyword | Use |
|---------|-----|
| `super` | Calls parent class methods or constructor |
| `this` | Refers to the current object |

Example:
```java
class Parent {
    void display() { System.out.println("Parent method"); }
}

class Child extends Parent {
    void show() {
        super.display();
    }
}

public class Main {
    public static void main(String[] args) {
        Child c = new Child();
        c.show();
    }
}
```

---

## 12. `equals()` and `hashCode()`
- `equals()` compares object content.
- `hashCode()` returns an integer representation for hashing.

Example:
```java
class Main {
    String name;

    public Main(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        return (o instanceof Main) && this.name.equals(((Main) o).name);
    }

    public int hashCode() {
        return name.hashCode();
    }

    public static void main(String[] args) {
        Main e1 = new Main("Alice");
        Main e2 = new Main("Alice");
        System.out.println(e1.equals(e2)); // true
        System.out.println(e1.hashCode() == e2.hashCode()); // true
    }
}
```

---

## 13. Java Load Sequence
1. Static Blocks
2. Instance Blocks
3. Constructors
4. Method Calls

Example:
```java
public class Main {
    static {
        System.out.println("Static block");
    }

    {
        System.out.println("Instance block");
    }

    Main() {
        System.out.println("Constructor");
    }

    public static void main(String[] args) {
        new Main();
    }
}
```

---

## 14. Polymorphism in Java
Implemented method overloading and overriding.

Example:
```java
class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
	String sound(String s) {
		return s;
	}
}

class Cat extends Animal {
    @Override
    void sound() {
        System.out.println("Cat meows");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal cat = new Cat();
        cat.sound();
		System.out.println(cat.sound("Meow"));
    }
}
```

---

## 15. Encapsulation in Java
Encapsulation is using private fields and public methods like setter and getter.

Example:
```java
class Person {
    private String gender;

    public Person(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

public class Main {
	 public static void main(String[] args) {
        Person p = new Person("Male");
        System.out.println(p.getGender());
        p.setGender("Female");
        System.out.println(p.getGender());
    }
}
```

---

## 16. Interface vs Abstract Class
| Feature | Interface | Abstract Class |
|---------|-----------|---------------|
| Method Implementation | Only abstract methods (before Java 8) | Can have concrete methods |
| Multiple Inheritance | Allowed | Not allowed |
| Use Case | When multiple classes share behavior | When base class provides some implementation |

Example:
```java
interface Animal {
    void sound();
}

interface CanFly {
	void fly();
}

class Sparrow implements Animal, CanFly {
    @Override
	public void sound() {
		System.out.println("Sparrow sound");
	}
	@Override
    public void fly() {
        System.out.println("Sparrow flying");
    }
}

public class Main {
    public static void main(String[] args) {
        Sparrow sparrow = new Sparrow();
		sparrow.sound();
        sparrow.fly();
    }
}
```

---
