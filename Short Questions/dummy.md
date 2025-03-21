### Short Questions
<p>
Question2:

Wrapper class encapsulates primitive data types such as int into an Integer.
We use it because collections in Java only accepts objects. Integer is an
object but int is not.
</p>

<p>
Question3:

The main difference is that Hashmap is not thread safe, and allows null values.
HashTable is thread safe, and does not allow null values as keys.

</p>

<p>
Question4:

String Pool is a Java special memory area that stores some common string literals.
We use string pool so that we can reuse identical strings, reducing overhead.
String immunity means a string cannot be changed after created
</p>

<p>
Question5:

Garbage collection means to delete objects that are no longer in use. Java
includes collectors like GC, parallel GC.

</p>

<p>
Question6:

Access modifies controls visibility and accessibility of class, methods, and
variables.<br>
Public: accessible from anywhere;<br>
Private: accessible only within the class;<br>
Default: accessible only within the same package;<br>
Protected: accessible only within the class and subclass;<br>

</p>

<p>
Question7:

Final means cannot change. If used to on variables, means the variable cannot be
changed. If used on methods, means the method cannot be overriden. If used on classes,
means the class cannot be inherited.

</p>

<p>
Question8:

Static refers to a method or a variable. It means this method or variable belongs to the
class rather than the instance. We do not need to create an instance and call the method.
Static methods only plays with static variables.

</p>

<p>
Question9:

Overriding refers to a method from the child class can dominate the same method
from the parent class. Overloading refers to two methods having the same signature
within the same class. But their input parameters and return type can be different.
</p>

<p>
Question10:

Signature includes return type, access modifier, method name, and parameter list.
This definition helps in overriding by ensuring the subclass method matches the 
superclass method's signature exactly, and in overloading by allowing multiple 
methods with the same name but different parameter lists to coexist in the same class.

</p>

<p>
Question11:

The keyword super is used to access or call the parent's methods, constructors, 
or variables. This refers to the current instance of the class, accessing 
its own methods, constructors, or variables.


</p>

<p>
Question12:

The equals() method in Java is used to compare the content of two 
objects for equality, while the hashCode() method returns an integer 
hash value representing the object's state.

</p>

<p>
Question13:

Loading sequence means the order in which the variables and methods and
static components are loaded and initialized when the program starts.
Java first loads all the static components, then the variables and methods.

</p>

<p>
Question14

Polymorphism is the ability of an object to take on many forms, allowing methods to 
behave differently based on the object that invokes them. Java implements polymorphism 
through method overriding.
</p>

<p>
Question15

Encapsulation is to declare variables as private, and used provided methods
to access the variables. We need encapsulation to make sure the variables is
safe, and we control how outside can access our private data.
</p>

<p>
Question16

Abstract class can have methods implementation. A class can only inherits
one abstract class. Interface only has method declaration. A class can
implements an interface multiple times.

</p>

```java
//interface
interface movable{
    void move();
}

class airplane implements movable{
    public void move(){
        System.out.println("Flying");
    }
}

//abstract class
abstract class sounds{
    abstract void makeSound();
}

class Dog extends sounds{
    public void makeSound(){
        System.out.println("Bark!");
    }
}
```

