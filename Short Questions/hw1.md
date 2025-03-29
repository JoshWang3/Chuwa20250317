### Short Questions
1. Write up Example code to demonstrate the three foundmental concepts of OOP.
1. Encapsulation;
class User {
    private String id; 

    public User(String id) {
        this.id = id;
    }
    public String getId() { 
        return id;
    }
    public void setId(String id) { 
        this.id = id;
    }
}


3. Inheritance;
class Animal {
    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
}
class Dog extends Animal { 
    public void bark() {
        System.out.println("Woof!");
    }
}

in main:Animal dog = new Dog();
dog.makeSound();//output: Some generic animal sound
dog.bark();//output: Woof!
2. Polymorphism;
class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow");
    }
}
in main: Animal cat = new Cat();
cat.makeSound(); //output: Meow


2. What is wrapper data type classes (e.g. Integer, Double) in Java and Why we need wrapper class?
Wrapper classes wrap primitive types into objects.
why: need to use primitives in object format in collections.

3. What is the difference between HashMap and HashTable?
hashmap: not thread safe, allow null key, better performance
hashtable: thread safe, no null key, slower

4. What is String pool in Java and why we need String pool? Explain String immunity.
a memory area in the heap, java needs it to store strings to reuse.
String Immutability: string cannot be changed after creation

5. Explain garbage collection? Explain types of garbage collection.
a process in java  which automaticly  free memory by removing unused objects
type:
serial: single-threaded
parallel: multiple threads for better throughput
Concurrent Mark Sweep:minimizes pause time
G1:low pause time, good for large heaps

6. What are access modifiers and their scopes in Java?
public: anywhere
protect:accessable in same package and subclasses
default:accessable in same package
private:within the same class

7. Explain final key word? (Filed, Method, Class)
final field: value cannot be changed after assignment
final method:cannot be overridden by subclasses
final class: cannot be extended/inherited

8. Explan static keyword? (Filed, Method, Class). When do we usually use it?
Static Field: belongs to class, shared across all instances
Static Method: can be called without creating an object
Static Class: nested classes can be static
use:fast access without create an object, constant value, utility method...

9. What is the differences between overriding and overloading?
overide:same method name, different parameters
overload:same method name & parameters, use in subclass

10. Explain how Java defines a method signature, and how it helps on overloading and overriding.
method name + parameter types
Overload: same name+different signatures
Override: must match exact signature

11. What is the differences between super and this?
this: current class's object
super: refer to parent class's method/field

12. Explain how equals and hashCode work.
equals(): compares object values
hashCode(): return integer used in hashing, if equals is true, hashCode is also true

13. What is the Java load sequence?
static -> instance fields -> constructor

14. What is Polymorphism ? And how Java implements it ?
subclass of parent class which has its own behavior for same method
implement: use @override

15. What is Encapsulation ? How Java implements it? And why we need encapsulation?
wrap code inside class, hide logic, only provide getter and setter
implement: use private, access use getter and setter
control access and improve code structure

16. Compare interface and abstract class with use cases.
interface: not constructor, only abstract method(default method is allowed after java 8), multiple interface, use for behavior contracts
abstract: default constructor,one abstract class only, deault method+abstract method, use for base classes with partial implementation
