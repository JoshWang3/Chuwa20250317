2.
    // Checked Exception Example
    public static void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
        reader.readLine();
        reader.close();
    }

    // Unchecked Exception Example
    public static void divideByZero() {
        int x = 10 / 0; // ArithmeticException (unchecked)
    }

    in main:
        try {
            readFile(); // checked, will be checked by runtime and catched
        } catch (IOException e) {
        }

        divideByZero(); // unchecked

3. only one final, multiple catch bolck
try {
        int[] arr = new int[2];
        System.out.println(arr[5]); 
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (Exception e) {
        } finally {
            System.out.println("always execute");
        }
4. finally will overwrite catch
5.
throw: explicitly throw an exception, use inside method
throws: method keyword, the method can throw an exp
6.
1st main: ArithmeticException is thrown and caught
2nd main: NullPointerException
3rd main:RuntimeException
java check block from top to bottom, the specific exp wont be reached if place general exp front
7. an feature in java 8 used to avoid NullPointerException by assigning a default value to object(first check if object is null, then assign defaulf value)
import java.util.Optional;
public class OptionalDemo {
    public static void main(String[] args)
    {
        String[] words = new String[10];
      	Optional<String> checkNull = Optional.ofNullable(words[5]);
      	if (checkNull.isPresent()) {
            String word = words[5].toLowerCase();
            System.out.print(word);
        }
        else
            System.out.println("word is null");
    }
}
8. Creational, Structural, Behavioral
singleton, factory/proxy
9.
public class DclSingleton {
    private static volatile DclSingleton instance;
    public static DclSingleton getInstance() {
        if (instance == null) {
            synchronized (DclSingleton .class) {
                if (instance == null) {
                    instance = new DclSingleton();
                }
            }
        }
        return instance;
    }

    // private constructor and other methods...
}
thread safe: volitile keyword to ensure same status for threads, first lock check if the object is created, synchronized ensure one thread can exec creation, second lock prevent if multiple thread reach the first check and already created object
factory: 
interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class ShapeFactory {
    public Shape getShape(String type) {
        if ("circle".equalsIgnoreCase(type)) return new Circle();
        return null;
    }
}

builder:
class User {
    private String name;
    private int age;

    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    public static class Builder {
        private String name;
        private int age;

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder age(int age) {
            this.age = age;
            return this;
        }
        public User build() {
            return new User(this);
        }
    }
}

10.
Single Responsibility,Open/Closed,Liskov Substitution,Interface Segregation, Dependency Inversion Principle
OCP: entities (classes, modules, functions) should be open for extension but closed for modification

11. 1




    

