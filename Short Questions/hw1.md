1. Write up Example code to demonstrate the three fundamental concepts of OOP.
   
    1. **Encapsulation**: Protecting Data via Private Fields and Public Methods.
    
       ```java
       class BankAccount{
          // Private fields: hidden from other classes
           private double balance;
           private String accountNumber;
           
           // Constructor
          public BankAccount(String accountNumber, double initialBalance){
              this.accountNumber = accountNumber;
              this.balance = initialBalance;
          }
          
          // Public getter methods to access private fields
          public double getBalance(){
              return balance;
          }
          
          public boolean deposit(double amount){
              if(amount > 0){
                  balance += amount;
                  return true;
              }
              System.out.println("Deposit failed: amount must be greater than 0.");
              return false;
          }
          
          public boolean withdraw(double amount){
              if(amount > 0 && amount <= balance){
                  balance -= amount;
                  return true;
              }
              System.out.println("Withdraw failed: invalid amount or insufficient balance.");
              return false;
          }
       }
         
       public class Main{
          public static void main(String[] args){
              BankAccount accountEncap = new BankAccount("2001", 1000);
              accountEncap.deposit(500); // +500
              accountEncap.withdraw(700); // -700
              System.out.println("Final Balance: " + accountEncap.getBalance());   
              // Final Balance: 800.0
          }
       }
       ```

    2. **Polymorphism**: Overriding Methods and Using Superclass References.

       ```java
       // Base class
       class BankAccount{
           private double balance;
           private String accountNumber;
           
           // Constructor
           public BankAccount(String accountNumber, double initialBalance){
               this.accountNumber = accountNumber;
               this.balance = initialBalance;
           }
           
           public double getBalance(){
               return balance;
           }
           
           public boolean deposit(double amount){
               if(amount > 0){
                   balance += amount;
                   return true;
               }
               System.out.println("Deposit failed: amount must be greater than 0.");
               return false;
           }
           
           public boolean withdraw(double amount){
               if(amount > 0 && amount <= balance){
                   balance -= amount;
                   return true;
               }
               System.out.println("Withdraw failed: invalid amount or insufficient balance.");
               return false;
           }
           
           // Method to be overridden (for polymorphism)
           public void printAccountType(){
               System.out.println("This is a generic bank account.");
           }
       }
       
       // Subclass: SavingsAccount has interest feature
       class SavingsAccount extends BankAccount{
           private double interestRate;
           
           // Constructor
           public SavingsAccount(String accountNumber, double initialBalance, double interestRate){
               super(accountNumber, initialBalance);
               this.interestRate = interestRate;
           }
           
           public void applyInterest(){
               deposit(getBalance() * interestRate);
           }
           
           @Override
           public void printAccountType(){
               System.out.println("This is a savings account.");
           }
       }
       
       // Subclass: CheckingAccount has transaction fee
       class CheckingAccount extends BankAccount{
           private double transactionFee;
           
           // Constructor
           public CheckingAccount(String accountNumber, double initialBalance, double transactionFee){
               super(accountNumber, initialBalance);
               this.transactionFee = transactionFee;
           }
           
           // Override withdraw to apply fee
           @Override
           public boolean withdraw(double amount){
               double totalAmount = amount + transactionFee;
               if(totalAmount > 0 && totalAmount <= getBalance()){
                   return super.withdraw(totalAmount);
               }
               System.out.println("Withdraw failed: insufficient funds including fee.");
               return false;
           }
           
           @Override
           public void printAccountType(){
               System.out.println("This is a checking account.");
           }
       }
       
       public class Main{
           public static void main(String[] args){
               BankAccount accountPolySA = new SavingsAccount("2002", 1000, 0.1);
               accountPolySA.printAccountType();
               //This is a savings account.
               if (accountPolySA instanceof SavingsAccount sa) {
                   sa.applyInterest();
                   System.out.println("Balance after interest: " + sa.getBalance()); 
                   // Balance after interest: 1100.0
               }
               
               System.out.println();
               
               BankAccount accountPolyCA = new CheckingAccount("2003", 1000, 2);
               accountPolyCA.printAccountType();
               // This is a checking account.
               accountPolyCA.withdraw(100);
               System.out.println("Balance after withdraw: " + accountPolyCA.getBalance()); 
               // Balance after withdraw: 898.0
           }
       }
       ```
    
    3. **Inheritance**: Creating Specialized Subclasses from a General Superclass.
    
       ```java    
       class BankAccount{
           private double balance;
           private String accountNumber;
           
           // Constructor
           public BankAccount(String accountNumber, double initialBalance){
               this.accountNumber = accountNumber;
               this.balance = initialBalance;
           }
           
           public double getBalance(){
               return balance;
           }
           
           public boolean deposit(double amount){
               if(amount > 0){
                   balance += amount;
                   return true;
               }
               System.out.println("Deposit failed: amount must be greater than 0.");
               return false;
           }
           
           public boolean withdraw(double amount){
               if(amount > 0 && amount <= balance){
                   balance -= amount;
                   return true;
               }
               System.out.println("Withdraw failed: invalid amount or insufficient balance.");
               return false;
           }
       }
       
       // Subclass: inherits all functionality from BankAccount and adds interest behavior
       class SavingsAccount extends BankAccount{
           private double interestRate;
           
           // Constructor
           public SavingsAccount(String accountNumber, double initialBalance, double interestRate){
               super(accountNumber, initialBalance);
               this.interestRate = interestRate;
           }
           
           public void applyInterest(){
               deposit(getBalance() * interestRate);
           }
       }
       
       public class Main{
           public static void main(String[] args){
               SavingsAccount accountInher = new SavingsAccount("2004", 1000, 0.1);
               accountInher.applyInterest();
               System.out.println("Balance after interest: " + accountInher.getBalance()); 
               // Balance after interest: 1100.0
           }
       }
       ```
    
       
    
2. What is wrapper data type classes (e.g. Integer, Double) in Java and Why we need wrapper class?  

   - Wrapper classes in Java are used to convert primitive data types (int, char, boolean, etc.) into objects. The main wrapper classes are Integer, Character, Boolean, Double, Float, Long, Short, and Byte. 
   - We need wrapper classes for:
     1. To use primitives in collections (which can only store objects)
     2. To use methods and constants defined in wrapper classes
     3. When working with generics which require object types
     4. To support null values (primitives cannot be null)
     5. To enable synchronization in multithreading (since locks work with objects)  

   ```java
   import java.util.ArrayList;
   
   public class Main
   {
   	public static void main(String[] args) {
           // Primitive type
   		int primitiveInt = 10;
           // Wrapper class - Autoboxing: int -> Integer
           Integer wrapperInt = primitiveInt;
           
           // Collection only supports objects, not primitives
           ArrayList<Integer> list = new ArrayList<>();
           list.add(wrapperInt);
           
           // Unboxing: Integer -> int
           int extractedValue = list.get(0);
           
           System.out.println("Primitive int: " + primitiveInt); // Primitive int: 10
           System.out.println("Wrapper Integer: " + wrapperInt); // Wrapper Integer: 10 
           System.out.println("Value from ArrayList: " + extractedValue); // Value from ArrayList: 10
   	}
   }
   ```

   

3. What is the difference between HashMap and Hashtable? 

   | Feature          | HashMap                                      | Hashtable                                     |
   | ---------------- | -------------------------------------------- | --------------------------------------------- |
   | Thread-safety    | ❌ Not synchronized (not thread-safe)         | ✅ Synchronized (thread-safe)                  |
   | Performance      | Faster (no overhead of synchronization)      | Slower (because all methods are synchronized) |
   | Null keys/values | ✅ Allows 1 null key and multiple null values | ❌ Does not allow any null key or value        |

   ```java
   import java.util.*;
   
   public class Main
   {
       public static void main(String[] args) {
           // HashMap allows null keys/values
           HashMap<String, String> hashMap = new HashMap<>();
           hashMap.put(null, "value1");
           hashMap.put("key2", null);
           System.out.println("HashMap: " + hashMap);
           // HashMap: {null=value1, key2=null}
   
           // Hashtable does NOT allow null keys or values
           Hashtable<String, String> hashtable = new Hashtable<>();
           // hashtable.put(null, "value1");    // Throws NullPointerException
           // hashtable.put("key2", null);      // Throws NullPointerException
           hashtable.put("key1", "value1");
           System.out.println("Hashtable: " + hashtable);
           // Hashtable: {key1=value1}
       }
   } 
   ```

   

4. What is String pool in Java and why we need String pool? Explain String immunity. 

   - String Pool is a special area in Java memory used to store string literals. When we create a string literal, JVM first checks if a string with the same value already exists in the pool. If it exists, it returns a reference to the pooled string; if not, it creates a new string object and adds it to the pool.
   - String Pool serves two main purposes:
     - **Memory Efficiency**: By reusing strings with the same content, memory consumption is reduced
     - **Performance Improvement**: String comparisons can be faster (especially when using the == operator)

   - In Java, String is immutable, which means once created, its value cannot be modified. Any operation that appears to modify a string actually creates a new string object.  

   ```java
   public class Main
   {
   	public static void main(String[] args) {
   		// String Pool example
           String s1 = "hello";  // Creates a string literal stored in the String Pool
           String s2 = "hello";  // Reuses the string from the pool instead of creating a new object
           String s3 = new String("hello");  // Forces creation of a new object, not using the pooled string
           
           // Verify s1 and s2 reference the same object
           System.out.println(s1 == s2);  // true
           
           // Verify s1 and s3 reference different objects
           System.out.println(s1 == s3);  // false
           
           // String immutability example
           String original = "Hello";
           String modified = original.concat(" World");  // Appears to modify the string, but actually creates a new object
           
           // Verify the original string was not modified
           System.out.println(original);  // Hello
           System.out.println(modified);  // Hello World
           
           // Verify string operations create new objects
           System.out.println(original == modified);  // false
   	}
   }
   ```

   

5. Explain garbage collection? Explain types of garbage collection. 

   - Garbage collection is Java's automatic memory management mechanism that identifies and removes unused objects to free up memory.
   - To prevent memory leaks and free programmers from manually managing memory, reducing programming errors.
   - Types of Garbage Collection:
     - **Serial GC** - Single-threaded, pauses all application threads
     - **Parallel GC** - Multi-threaded, improves efficiency
     - **Concurrent Mark Sweep (CMS)** - Minimizes pauses by running concurrently
     - **G1 Garbage Collector** - Divides heap into regions, prioritizes regions with most garbage

   ```java
   public class Main {
       public static void main(String[] args) {
           // Create objects with a finalize method
           for(int i = 0; i < 5; i++) {
               new MyObject(i);
           }
           
           // Suggest JVM to run garbage collection
           System.gc();
           System.runFinalization();
           
           // Wait a bit to give finalize time to execute
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           
           System.out.println("Main method completed");
       }
       
       static class MyObject {
           private int id;
           
           public MyObject(int id) {
               this.id = id;
               System.out.println("Created object " + id);
           }
           
           @Override
           protected void finalize() {
               System.out.println("Finalizing object " + id);
           }
       }
   }
   ```

   ```tex
   Created object 0
   Created object 1
   Created object 2
   Created object 3
   Created object 4
   Finalizing object 4
   Finalizing object 3
   Finalizing object 2
   Finalizing object 1
   Finalizing object 0
   Main method completed
   ```

   

6. What are access modifiers and their scopes in Java?  

   Access modifiers in Java control the visibility and accessibility of classes, methods, variables, and constructors. They determine which parts of your code can access specific elements.

   - **Public**: Accessible from any class or package.
   - **Protected**: Accessible within same package and by subclasses.
   - **Default** (no modifier): Accessible only within same package.
   - **Private**: Accessible only within the same class.

   | Modifier    | Class Scope | Same Package | Subclass (same package) | Subclass (different package) | Other Packages |
   | ----------- | ----------- | ------------ | ----------------------- | ---------------------------- | -------------- |
   | `public`    | ✅           | ✅            | ✅                       | ✅                            | ✅              |
   | `protected` | ✅           | ✅            | ✅                       | ✅                            | ❌              |
   | `default`   | ✅           | ✅            | ✅                       | ❌                            | ❌              |
   | `private`   | ✅           | ❌            | ❌                       | ❌                            | ❌              |

7. Explain final key word? (Filed, Method, Class) 

   - Final Variable cannot be reassigned after initialization.
   - Final Method cannot be overridden in a subclass.
   - Final Class cannot be extended by any class.

   | Scenario        | When to Use                                               |
   | --------------- | --------------------------------------------------------- |
   | **Constants**   | Define immutable constants (`final int MAX = 100;`)       |
   | **Security**    | Prevent subclass modifications (use `final` class)        |
   | **Performance** | Java optimizes `final` methods for better execution speed |

   ```java
   public class Main {
       public static void main(String[] args) {
           // Final field example
           final int MAX = 100;
           // MAX = 200;  // Error: cannot reassign final variable
           
           System.out.println("Final field value: " + MAX); // Final field value: 100
       }
   }
   
   // Final method example
   class Parent {
       final void display() {
           System.out.println("Final method");
       }
   }
   
   class Child extends Parent {
       // void display() {}  // Error: cannot override final method
   }
   
   // Final class example
   final class FinalClass {
       // This class cannot be extended
   }
   
   // class ChildClass extends FinalClass {}  // Error: cannot inherit from final class
   ```

   

8. Explain static keyword? (Field, Method, Class). When do we usually use it?

   - Static members belong to the class itself , not to any instance
     - Static fields can be accessed without creating an object

     - Static methods cannot access non-static members

     - Static cannot be used with local variables

     - Static classes must be nested classes

     - Constants are typically declared as `static final`

   - Non-static members belong to specific object instances

   ```java
   public class Main {
       // Static field - shared by all instances
       public static int count = 0;
       
       // Non-static field
       private String message;
       
       // Constructor
       public Main(String message) {
           this.message = message;
           count++; // Increases the shared counter
       }
       
       // Static method
       public static void printCount() {
           System.out.println("Total objects: " + count);
       }
       
       // Non-static method
       public void printMessage() {
           System.out.println("Message: " + this.message);
       }
       
       public static void main(String[] args) {
           // Access static members directly
           System.out.println("Initial count: " + Main.count); // Initial count: 0
           
           // Create objects
           Main obj1 = new Main("First object"); // count++
           Main obj2 = new Main("Second object"); // count++
           
           // Call static method
           Main.printCount(); // Total objects: 2
           
           // Call instance methods
           obj1.printMessage(); // Message: First object
           obj2.printMessage(); // Message: Second object
           
           // Demonstrate shared nature of static field
           System.out.println("Final count: " + count); // Can access directly in static context
           // Final count: 2
       }
   }
   ```

   

9. What is the differences between overriding and overloading?

   | Feature            | Overloading                                              | Overriding                                     |
   | ------------------ | -------------------------------------------------------- | ---------------------------------------------- |
   | Definition         | Using the same method name but with different parameters | A child class reimplementing a parent's method |
   | Location           | Happens within the same class                            | Happens in a child class                       |
   | Binding            | Determined at compile time                               | Determined at runtime                          |
   | Inheritance        | Does not depend on inheritance                           | Requires inheritance                           |
   | Parameters         | Must be different (type/number/order)                    | Must be the same                               |
   | Return type        | Can be different                                         | Must be the same or a subtype                  |
   | Access modifier    | No restrictions                                          | Parent method must be public or protected      |
   | Other restrictions |                                                          | Parent method cannot be final                  |

   ```java
   public class Main {
       public static void main(String[] args) {
           // Overloading test
           print(5);           // Int: 5
           print("Hello");     // String: Hello
           
           // Overriding and polymorphism test
           Parent p = new Child();  // Polymorphism
           p.show();                // Child
       }
       
       // Overloaded methods - same name, different parameters
       static void print(int x) {
           System.out.println("Int: " + x);
       }
       
       static void print(String s) {
           System.out.println("String: " + s);
       }
   }
   
   class Parent {
       void show() {
           System.out.println("Parent");
       }
   }
   
   class Child extends Parent {
       @Override
       void show() {
           System.out.println("Child");
       }
   }
   ```

   

10. Explain how Java defines a method signature, and how it helps on overloading and overriding. 

    - A method signature in Java consists of the method name and parameter list (types, number, and order). It does not include the return type or access modifiers.

    - **Role in overloading:**

      - Different signatures with the same method name allow overloading
      - Compiler identifies which method to call based on arguments

    - **Role in overriding:**

      - Signatures must be identical in parent and child classes
      - Ensures proper polymorphic behavior

      

11. What is the differences between super and this? 

    | Feature      | `this`                                  | `super`                                  |
    | ------------ | --------------------------------------- | ---------------------------------------- |
    | Purpose      | Refers to the current object            | Refers to the parent class               |
    | Usage        | Access current class members            | Access parent class members              |
    | Constructor  | Calls another constructor in same class | Calls parent class constructor           |
    | Method calls | Calls current class methods             | Calls overridden methods in parent class |
    | Scope        | Can be used in any method               | Can only be used in a subclass           |

    ```java
    public class Main {
        public static void main(String[] args) {
            Child c = new Child();
            c.display();
            // Child: 20
            // Parent: 10
            // Child method: 20
            // Parent: 10
        }
    }
    
    class Parent {
        int value = 10;
        void show() {
            System.out.println("Parent: " + value);
        }
    }
    
    class Child extends Parent {
        int value = 20;
        
        void display() {
            System.out.println("Child: " + this.value);   // 20
            System.out.println("Parent: " + super.value); // 10
            this.show();   // Calls Child's show
            super.show();  // Calls Parent's show
        }
        
        @Override
        void show() {
            System.out.println("Child method: " + value);
        }
    }
    ```

    

12. Explain how  equals and  hashCode work. 

    - `equals()`

      - Determines if two objects are logically equivalent

      - Default implementation checks reference equality (same memory location)

      - When overriding, must maintain reflexivity, symmetry, transitivity, and consistency

    - `hashCode()` 

      - Returns an integer hash value used by hash-based collections (HashMap, HashSet)

      - Objects that are equal must have the same hash code
      - Different objects may have the same hash code (hash collision)
    
    - If `a.equals(b)` is true, then `a.hashCode() == b.hashCode()` must be true
    - If `a.hashCode() == b.hashCode()` is true, `a.equals(b)` may be false
    - Failing to override both methods together can cause bugs in hash-based collections

    ```java
    import java.util.HashMap;
    import java.util.Map;
    import java.util.Objects;
    
    public class Main {
        public static void main(String[] args) {
            // Create two Person objects with same values
            Person p1 = new Person("John", 30);
            Person p2 = new Person("John", 30);
            
            // Test equals
            System.out.println("p1 equals p2: " + p1.equals(p2));  // p1 equals p2: true
            
            // Test hashCode
            System.out.println("p1 hashCode: " + p1.hashCode());   // p1 hashCode: 71751700
            System.out.println("p2 hashCode: " + p2.hashCode());   // p2 hashCode: 71751700
            
            // Demonstrate in HashMap
            Map<Person, String> map = new HashMap<>();
            map.put(p1, "Person One");
            
            // Can retrieve with equal object
            System.out.println("Get with p2: " + map.get(p2));     // Get with p2: Person One
        }
        
        static class Person {
            String name;
            int age;
            
            Person(String name, int age) {
                this.name = name;
                this.age = age;
            }
            
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Person person = (Person) o;
                return age == person.age && Objects.equals(name, person.name);
            }
            
            @Override
            public int hashCode() {
                return Objects.hash(name, age);
            }
        }
    }
    ```

    

13. What is the Java load sequence? 

    The Java class loading sequence follows this order:

    1. **Static blocks/variables** - loaded when class is first referenced
    2. **Instance blocks** - executed during object creation before constructor
    3. **Constructors** - executed when a new object is created

    ```java
    public class Main {
        public static void main(String[] args) {
            System.out.println("Main method execution starts");
            Sample sample = new Sample();
            System.out.println("Main method execution ends");
        }
    }
    
    class Sample {
        // Static block - executed when class is loaded
        static {
            System.out.println("1. Static block executes");
        }
        
        // Static variable initialization
        static int staticVar = initStaticVar();
        
        // Instance block - runs before constructor
        {
            System.out.println("3. Instance block executes");
        }
        
        // Instance variable initialization
        int instanceVar = initInstanceVar();
        
        // Constructor - executes last during object creation
        public Sample() {
            System.out.println("4. Constructor executes");
        }
        
        // Helper methods
        static int initStaticVar() {
            System.out.println("2. Static variable initialization");
            return 10;
        }
        
        int initInstanceVar() {
            System.out.println("3.5. Instance variable initialization");
            return 20;
        }
    }
    ```

    ```tex
    Main method execution starts
    1. Static block executes
    2. Static variable initialization
    3. Instance block executes
    3.5. Instance variable initialization
    4. Constructor executes
    Main method execution ends
    ```

    This demonstrates that static elements are loaded first when the class is referenced, followed by instance initialization, and finally the constructor when an object is created.

    

14. What is Polymorphism ? And how Java implements it ?  

    **Polymorphism** is the ability of an operation to behave differently based on the object it operates on. Java implements polymorphism in two ways:

    1. **Compile-time polymorphism (Static binding)**: Implemented through method overloading
    2. **Runtime polymorphism (Dynamic binding)**: Implemented through inheritance and method overriding

    ```java
    public class Main {
        public static void main(String[] args) {
            // Runtime polymorphism
            Animal animal = new Dog();
            animal.sound();  // Outputs "Dog barks" not "Animal sound"
            // Dog barks
            
            // Compile-time polymorphism
            print(10);      // Integer: 10
            print("Hello"); // String: Hello
        }
        
        static void print(int x) {
            System.out.println("Integer: " + x);
        }
        
        static void print(String s) {
            System.out.println("String: " + s);
        }
    }
    
    class Animal {
        void sound() {
            System.out.println("Animal sound");
        }
    }
    
    class Dog extends Animal {
        @Override
        void sound() {
            System.out.println("Dog barks");
        }
    }
    ```

    Java implements dynamic method dispatch by binding method calls to the actual object type rather than the reference type.

    

15. What is Encapsulation ? How Java implements it? And why we need encapsulation?  

    - **Encapsulation** is the bundling of data and methods that operate on that data within a single unit (class), and restricting access to some of the object's components.

    - Java implements encapsulation through:

      1. **Access modifiers**: private, protected, public, and default (package-private)

      2. **Getter and setter methods**: To control access to private fields

    - Why we need encapsulation:

      1. **Data hiding**: Protects data from unauthorized access

      2. **Flexibility**: Implementation can change without affecting other code

      3. **Maintainability**: Class internals can be modified without impacting other code

      4. **Validation**: Control over data modification through setter methods

    ```java
    public class Main {
        public static void main(String[] args) {
            Student student = new Student();
            
            // Using setter with validation
            student.setAge(15);
            
            // Using getter
            System.out.println("Age: " + student.getAge()); // Age: 15
            
            // Direct access would cause error
            // student.age = -5; // Compilation error - private field
        }
    }
    
    class Student {
        // Private data - hidden from outside
        private String name;
        private int age;
        
        // Public getter method
        public int getAge() {
            return age;
        }
        
        // Public setter with validation
        public void setAge(int age) {
            if (age > 0 && age < 120) {
                this.age = age;
            } else {
                System.out.println("Invalid age");
            }
        }
    }
    ```

    Encapsulation helps create more robust code by preventing unintended modifications and providing a stable interface to interact with objects.

    

16. Compare interface and abstract class with use cases.

    | Feature          | Interface                                                    | Abstract Class                                       |
    | ---------------- | ------------------------------------------------------------ | ---------------------------------------------------- |
    | Methods          | All abstract by default (Java 8+ allows default/static methods) | Can have both abstract and concrete methods          |
    | Variables        | Only constants (`public static final`)                       | Can have instance variables with any access modifier |
    | Inheritance      | A class can implement multiple interfaces                    | A class can extend only one abstract class           |
    | Access Modifiers | All methods are implicitly public                            | Methods can have any access modifier                 |
    | Constructor      | Cannot have constructors                                     | Can have constructors                                |

    **Use Case**

    **Interface**:

    - When you want to define a contract for unrelated classes
    - When multiple inheritance is needed
    - For defining types that can be used by widely different objects
    - For capability-based design (e.g., Comparable, Runnable)

    **Abstract Class**:

    - When related classes share code
    - When you need constructors or non-public methods
    - When you need to maintain state across methods
    - For providing a partial implementation with some common functionality

    ```java
    public class Main {
        public static void main(String[] args) {
            // Using interface polymorphism
            Playable player1 = new MusicPlayer();
            Playable player2 = new Car();
            
            player1.play(); // Playing music
            player2.play(); // Playing car radio
            
            // Using abstract class
            Vehicle car = new Car();
            car.accelerate();
            car.brake(); // Vehicle stopped
            
            // Demonstrating multiple inheritance through interfaces
            Car myCar = new Car();
            myCar.accelerate();
            myCar.play(); // Playing car radio 
            myCar.stop(); // Car radio stopped
        }
    }
    
    // Interface example - capability
    interface Playable {
        void play();
        void stop();
    }
    
    // Abstract class example - partial implementation
    abstract class Vehicle {
        protected int speed;
        
        public Vehicle() {
            this.speed = 0;
        }
        
        public void brake() {
            this.speed = 0;
            System.out.println("Vehicle stopped");
        }
        
        abstract void accelerate();
    }
    
    // Implementation examples
    class MusicPlayer implements Playable {
        public void play() { System.out.println("Playing music"); }
        public void stop() { System.out.println("Music stopped"); }
    }
    
    class Car extends Vehicle implements Playable {
        public void accelerate() { speed += 10; }
        public void play() { System.out.println("Playing car radio"); }
        public void stop() { System.out.println("Car radio stopped"); }
    }
    ```

    
