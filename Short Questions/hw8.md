1. Learn Java generics by reading and practicing following code: <https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t01_basic/generic>

2. Read the following code repo and type it one by one by yourself.  <https://github.com/CTYue/chuwa-eij-tutorial/tree/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/features>

3. Practice the following stream API exercises at least 3 times <https://github.com/gavinklfong/stream-api-exercises/blob/main/src/test/java/space/gavinklfong/demo/streamapi/StreamApiTest.java>

4. Practice Optional methods at least 2 times <https://github.com/CTYue/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/tutorial/t06_java8/exercise/ShoppingCartUtil.java>

5. Discuss best practices for null pointer exception prevention, and provide a code snippet for each practice you mention.

   1. **Use Objects.requireNonNull()**

      ```java
      public class RequireNonNullExample {
          public void processData(String data) {
              // Validate parameter at method entry
              String validData = Objects.requireNonNull(data, "Data cannot be null");
              
              // Now proceed with validData which is guaranteed to be non-null
              System.out.println("Processing: " + validData.toUpperCase());
          }
      }
      ```

   2. **Defensive Checks**

      ```java
      public class DefensiveCheckExample {
          public String getUpperCaseName(User user) {
              // Defensive check before accessing potentially null object
              if (user == null || user.getName() == null) {
                  return "UNKNOWN";
              }
              
              return user.getName().toUpperCase();
          }
      }
      ```

   3. **Optional Class**

      ```java
      public class OptionalExample {
          public Optional<String> findUserName(int id) {
              // Simulate database lookup that might not find a user
              User user = database.findById(id);
              
              // Return an Optional instead of null
              return Optional.ofNullable(user)
                            .map(User::getName);
          }
          
          public void displayUserName(int id) {
              // Consume the Optional safely
              findUserName(id).ifPresent(name -> System.out.println("Found: " + name));
              
              // Or provide default value
              String name = findUserName(id).orElse("Guest User");
          }
      }
      ```

   4. **Null Object Pattern**

      ```java
      public interface MessageLogger {
          void log(String message);
      }
      
      public class ConsoleLogger implements MessageLogger {
          public void log(String message) {
              System.out.println(message);
          }
      }
      
      public class NullLogger implements MessageLogger {
          public void log(String message) {
              // Do nothing
          }
      }
      
      public class LoggingService {
          private MessageLogger logger;
          
          public LoggingService(MessageLogger logger) {
              // Use NullLogger instead of null
              this.logger = (logger != null) ? logger : new NullLogger();
          }
          
          public void logImportantInfo(String info) {
              // No null check needed here
              logger.log("IMPORTANT: " + info);
          }
      }
      ```

   5. **@NotNull and @Nullable Annotations**

      ```java
      public class AnnotationExample {
          public void processUser(@Nonnull User user, @Nullable String comment) {
              // IDE and static analysis tools will warn about potential null issues
              System.out.println("Processing user: " + user.getName());
              
              if (comment != null) {
                  System.out.println("Comment: " + comment);
              }
          }
      }
      ```

   6. **Use final Variables with Initialization**

      ```java
      public class FinalVariableExample {
          private final String name;
          
          public FinalVariableExample(String name) {
              // Ensure name is not null during construction
              this.name = (name != null) ? name : "Default";
          }
          
          public void printInfo() {
              // name is guaranteed to be non-null here
              System.out.println("Length: " + name.length());
          }
      }
      ```

   7. **Avoid null as Return Value**

      ```java
      public class ReturnValueExample {
          public List<String> getUsers() {
              List<String> users = fetchUsersFromDatabase();
              
              // Return empty collection instead of null
              return (users != null) ? users : Collections.emptyList();
          }
          
          public void processUsers() {
              // No need for null check, safe to iterate
              for (String user : getUsers()) {
                  System.out.println(user);
              }
          }
      }
      ```

   8. **Use try-with-resources for Auto-Closeable Resources**

      ```java
      public void readFile(String path) {
          // Resources automatically closed, avoiding null issues with close()
          try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
              String line;
              while ((line = reader.readLine()) != null) {
                  System.out.println(line);
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
      ```

      

6. Discuss Java 8 new features with code snippets.

   1. **Lambda Expressions**

      ```java
      // Before Java 8
      Runnable runnable = new Runnable() {
          @Override
          public void run() {
              System.out.println("Hello world!");
          }
      };
      
      // With Lambda in Java 8
      Runnable lambdaRunnable = () -> System.out.println("Hello world!");
      
      // With parameters
      Comparator<String> comparator = (String s1, String s2) -> s1.compareTo(s2);
      
      // With multiple statements
      Consumer<String> consumer = s -> {
          String result = s.toUpperCase();
          System.out.println(result);
      };
      ```

   2. **Stream API**

      ```java
      List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Dave");
      
      // Filter and collect
      List<String> filteredNames = names.stream()
          .filter(name -> name.startsWith("C"))
          .collect(Collectors.toList());
      
      // Map operation
      List<Integer> nameLengths = names.stream()
          .map(String::length)
          .collect(Collectors.toList());
      
      // Parallel processing
      long count = names.parallelStream()
          .filter(name -> name.length() > 3)
          .count();
      ```

   3. **Default and Static Methods in Interfaces**

      ```java
      interface Vehicle {
          void start();
          
          // Default method - implementation in interface
          default void honk() {
              System.out.println("Beep beep!");
          }
          
          // Static method in interface
          static int getWheelCount(String vehicleType) {
              return vehicleType.equals("car") ? 4 : 2;
          }
      }
      
      class Car implements Vehicle {
          @Override
          public void start() {
              System.out.println("Car engine started");
          }
          // No need to implement honk() - uses default implementation
      }
      ```

   4. **Optional Class**

      ```java
      // Creating Optional objects
      Optional<String> empty = Optional.empty();
      Optional<String> value = Optional.of("Hello");
      Optional<String> nullable = Optional.ofNullable(maybeNull());
      
      // Using Optional
      String result = nullable
          .map(String::toUpperCase)
          .orElse("Default Value");
      
      nullable.ifPresent(System.out::println);
      
      // With Optional, we can avoid null checks
      String fallback = nullable
          .filter(s -> s.length() > 5)
          .orElseThrow(() -> new IllegalArgumentException("String too short"));
      ```

   5. **New Date and Time API**

      ```java
      // Current date and time
      LocalDate today = LocalDate.now();
      LocalTime time = LocalTime.now();
      LocalDateTime dateTime = LocalDateTime.now();
      ZonedDateTime zonedDateTime = ZonedDateTime.now();
      
      // Creating specific dates
      LocalDate date = LocalDate.of(2023, Month.APRIL, 20);
      
      // Date manipulations
      LocalDate tomorrow = today.plusDays(1);
      LocalDate lastMonth = today.minusMonths(1);
      
      // Parsing and formatting
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      String formattedDate = today.format(formatter);
      LocalDate parsedDate = LocalDate.parse("2023-04-20", formatter);
      ```

   6. **Method References**

      ```java
      List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
      
      // Instead of lambda
      names.forEach(name -> System.out.println(name));
      
      // With method reference
      names.forEach(System.out::println);
      
      // Different types of method references
      Function<String, Integer> strToInt = Integer::parseInt;
      BiFunction<String, String, Boolean> contains = String::contains;
      Supplier<List<String>> listSupplier = ArrayList::new;
      ```

   7. **Functional Interfaces**

      ```java
      // Built-in functional interfaces
      Predicate<String> isLong = s -> s.length() > 10;
      Consumer<String> printer = s -> System.out.println(s);
      Function<String, Integer> lengthFunc = s -> s.length();
      Supplier<String> supplier = () -> "Generated Value";
      
      // Custom functional interface
      @FunctionalInterface
      interface MathOperation {
          int operate(int a, int b);
      }
      
      MathOperation addition = (a, b) -> a + b;
      MathOperation subtraction = (a, b) -> a - b;
      ```

   8. **CompletableFuture for Asynchronous Programming**

      ```java
      // Asynchronous computation
      CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
          // Simulate long running task
          try {
              Thread.sleep(1000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          return "Result";
      });
      
      // Chain operations
      CompletableFuture<String> resultFuture = future
          .thenApply(s -> s + " processed")
          .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " and completed"));
      
      // Handle errors
      CompletableFuture<String> safeFuture = future.exceptionally(ex -> "Error: " + ex.getMessage());
      
      // Combine futures
      CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
      CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");
      CompletableFuture<String> combined = future1.thenCombine(future2, (s1, s2) -> s1 + " " + s2);
      ```

      

7. What are the advantages of the Optional class?

   1. **Explicit Documentation** - Using Optional makes it clear that a value might be absent, serving as a form of self-documenting code.

   2. **Null Safety** - Optional provides a container object that may or may not contain a non-null value, reducing NullPointerExceptions.

   3. **Cleaner API Design** - Methods that return Optional clearly indicate that they might not return a value.

   4. **Functional Programming Style** - Optional integrates well with functional programming:

      ```java
      // Without Optional
      User user = getUserById(id);
      if (user != null) {
          Address address = user.getAddress();
          if (address != null) {
              String city = address.getCity();
              if (city != null) {
                  System.out.println(city.toUpperCase());
              }
          }
      }
      
      // With Optional
      Optional<User> user = getUserById(id);
      user.flatMap(User::getAddress)
          .flatMap(Address::getCity)
          .map(String::toUpperCase)
          .ifPresent(System.out::println);
      ```

   5. **Avoiding Defensive Coding** - Reduces the need for repetitive null checks.

   6. **Providing Alternatives** - Elegant ways to provide default values or behaviors:

      ```java
      // Get value or use default
      String name = optionalName.orElse("Unknown");
      
      // Throw specific exception if absent
      User user = optionalUser.orElseThrow(() -> 
          new UserNotFoundException("User not found with ID: " + id));
      
      // Supply a value only when needed
      String value = optionalValue.orElseGet(this::computeDefaultValue);
      ```

   7. **Filtering Values** - Apply conditions without null checks:

      ```java
      // Only process if present and meets condition
      optionalValue
          .filter(value -> value.length() > 5)
          .ifPresent(this::process);
      ```

   8. **API Consistency** - Provides a standard way to handle potentially missing values across an application.

   

8. Explain Functional Interfaces and Lambdas with code samples.

   **Functional Interfaces**

   A functional interface in Java is an interface that contains exactly one abstract method. Java 8 introduced the `@FunctionalInterface` annotation to mark these interfaces, though the annotation is optional.

   ```java
   @FunctionalInterface
   interface Calculator {
       int calculate(int a, int b);
   }
   ```

   Java 8 provides several built-in functional interfaces in the `java.util.function` package:

   ```java
   // Predicate - takes one argument and returns boolean
   Predicate<String> isLongString = s -> s.length() > 10;
   boolean result = isLongString.test("Hello World"); // true
   
   // Consumer - accepts one argument but returns no result
   Consumer<String> printer = s -> System.out.println("Value: " + s);
   printer.accept("Hello"); // prints "Value: Hello"
   
   // Function - takes one argument and produces a result
   Function<String, Integer> stringLength = s -> s.length();
   int length = stringLength.apply("Hello"); // 5
   
   // Supplier - takes no arguments but returns a value
   Supplier<Double> randomValue = () -> Math.random();
   double value = randomValue.get(); // random double
   
   // BiFunction - takes two arguments and produces a result
   BiFunction<Integer, Integer, String> formatter = (a, b) -> String.format("%d + %d = %d", a, b, a + b);
   String formatted = formatter.apply(5, 3); // "5 + 3 = 8"
   ```

   **Lambda Expressions**

   Lambda expressions provide a concise way to create instances of functional interfaces. They allow us to treat functionality as a method argument.

   - **Basic Lambda Syntax**

     ```java
     // Without lambda (anonymous class)
     Runnable oldWay = new Runnable() {
         @Override
         public void run() {
             System.out.println("Running...");
         }
     };
     
     // With lambda
     Runnable withLambda = () -> System.out.println("Running...");
     
     // With parameters
     Calculator add = (a, b) -> a + b;
     Calculator subtract = (a, b) -> a - b;
     Calculator multiply = (a, b) -> a * b;
     
     // Using the calculator
     int result = add.calculate(5, 3); // 8
     ```

   - **Lambda with Multiple Statements**

     ```java
     Calculator complexCalculator = (a, b) -> {
         int result = a * b;
         if (result > 100) {
             return result / 2;
         }
         return result;
     };
     ```

   - **Using Lambdas with Collections**

     ```java
     List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
     
     // Sort using lambda
     names.sort((s1, s2) -> s1.compareTo(s2));
     
     // Filter with lambda
     List<String> filteredNames = names.stream()
         .filter(name -> name.startsWith("C"))
         .collect(Collectors.toList()); // ["Charlie"]
     
     // Transform with lambda
     List<Integer> nameLengths = names.stream()
         .map(name -> name.length())
         .collect(Collectors.toList()); // [5, 3, 7, 5]
     
     // Process each element
     names.forEach(name -> System.out.println("Hello, " + name));
     ```

   - **Capturing Variables**

     ```java
     String prefix = "User: ";
     Consumer<String> greeter = name -> System.out.println(prefix + name);
     // prefix is "captured" from the enclosing scope
     greeter.accept("Alice"); // prints "User: Alice"
     
     // Variables used in lambdas must be effectively final
     ```

     

9. Explain Method Reference with code samples.

   Method references provide a more concise way to express lambdas that simply call an existing method. They're shortcuts that let you refer to methods (or constructors) without executing them.

   **Basic Syntax**

   ```java
   // Lambda expression
   Consumer<String> printer1 = s -> System.out.println(s);
   
   // Equivalent method reference
   Consumer<String> printer2 = System.out::println;
   ```

   **Four Types of Method References**

   1. **Static Method References**

      ```java
      Function<String, Integer> parser = Integer::parseInt;
      ```

   2. **Instance Method References**

      ```java
      String str = "Hello";
      Supplier<Integer> lengthGetter = str::length;
      ```

   3. **Type Method References**

      ```java
      Function<String, Integer> lengthFunc = String::length;
      ```

   4. **Constructor References**

      ```java
      Supplier<ArrayList<String>> listCreator = ArrayList::new;
      ```

      

10. Explain "Lambdas can use effectively final variables outside of lambda" with a code snippet.

    Lambda expressions can access variables from their enclosing scope, but these variables must be either:

    1. Final variables (explicitly declared with the `final` keyword)
    2. "Effectively final" variables (not explicitly declared as final but never modified after initialization)

    ```java
    public class EffectivelyFinalExample {
        public static void main(String[] args) {
            // An effectively final variable - not declared as final
            // but never modified after initialization
            String message = "Hello";
            
            // A mutable counter - NOT effectively final
            int[] counter = {0};
            
            // Using the effectively final variable in a lambda
            Runnable r1 = () -> {
                System.out.println(message); // This works fine
                // message = "World"; // Would cause compiler error if uncommented
            };
            
            // Using array elements (mutable but reference is effectively final)
            Runnable r2 = () -> {
                counter[0]++; // Legal - modifying array element, not the reference
                System.out.println("Counter: " + counter[0]);
            };
            
            r1.run(); // Prints: Hello
            r2.run(); // Prints: Counter: 1
            r2.run(); // Prints: Counter: 2
            
            // This would make 'message' not effectively final
            // message = "World"; // Would cause error for r1 lambda above
        }
        
        public static void demonstrateError() {
            int x = 10;
            
            Runnable r = () -> {
                // Using x from enclosing scope
                System.out.println(x);
            };
            
            // This makes x not effectively final
            x = 20; // Compiler error: "Variable used in lambda expression should be final or effectively final"
            
            r.run();
        }
    }
    ```

    

11. Can a functional interface extend/inherit another interface?

    Yes, a functional interface can extend or inherit from another interface. 

    1. A functional interface must still have exactly one abstract method (not including methods inherited from Object)
    2. When a functional interface extends another interface:
       - If the parent interface is non-functional (has no abstract methods), the child can define one abstract method
       - If the parent interface is functional (has one abstract method), the child can't add any new abstract methods
       - The child can override the parent's abstract method with the same signature

    ```java
    // Parent interface with no abstract methods
    interface Parent {
        default void sayHello() {
            System.out.println("Hello from parent");
        }
    }
    
    // Functional interface extending non-functional parent
    @FunctionalInterface
    interface ChildFunctional extends Parent {
        // This is the single abstract method required for a functional interface
        void process(String input);
    }
    
    // Using the functional interface with a lambda
    public class Main {
        public static void main(String[] args) {
            ChildFunctional processor = (s) -> System.out.println("Processing: " + s);
            processor.process("test data");  // Prints: Processing: test data
            processor.sayHello();            // Prints: Hello from parent
        }
    }
    ```

    ```java
    // Functional parent interface
    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }
    
    // Child interface that inherits the abstract method
    @FunctionalInterface
    interface AdvancedCalculator extends Calculator {
        // No new abstract methods allowed here
        
        // But we can add default methods
        default int square(int a) {
            return calculate(a, a); // Uses the abstract method from parent
        }
    }
    ```

    

12. What are Intermediate and Terminal operations?

    In Java's Stream API, operations are classified into two categories: Intermediate and Terminal operations.

    **Intermediate Operations**:

    - Return a new stream

    - Are lazy (not executed until a terminal operation is called)

    - Can be chained together

    - Don't produce a final result

    ```java
    // Examples of intermediate operations
    Stream<String> stream = names.stream()
        .filter(s -> s.startsWith("A"))     // intermediate
        .map(String::toUpperCase)           // intermediate
        .sorted()                           // intermediate
        .distinct();                        // intermediate
    ```

    Common intermediate operations:

    - `filter()` - Filters elements based on a predicate
    - `map()` - Transforms elements using a function
    - `flatMap()` - Transforms and flattens elements
    - `distinct()` - Removes duplicates
    - `sorted()` - Sorts elements
    - `peek()` - Performs an action on each element but returns the same stream
    - `limit()` - Limits the stream to a certain size
    - `skip()` - Skips a number of elements

    **Terminal operations**:

    - Produce a result or side-effect
    - Consume the stream (can't be used again after)
    - Trigger the execution of all intermediate operations

    ```java
    // Examples of terminal operations
    long count = stream.count();             // terminal
    List<String> result = stream.collect(Collectors.toList());  // terminal
    boolean anyMatch = stream.anyMatch(s -> s.contains("x"));  // terminal
    ```

    Common terminal operations:

    - `forEach()` - Performs an action for each element
    - `collect()` - Collects elements into a collection
    - `reduce()` - Reduces elements to a single value
    - `count()` - Counts elements
    - `min()`, `max()` - Finds minimum or maximum element
    - `anyMatch()`, `allMatch()`, `noneMatch()` - Checks elements against a predicate
    - `findFirst()`, `findAny()` - Finds an element
    - `toArray()` - Converts to array

    ```java
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Dave", "Alice");
    
    List<String> result = names.stream()     // Create stream
        .filter(s -> s.length() > 3)         // Intermediate: Filter by length
        .distinct()                          // Intermediate: Remove duplicates
        .map(String::toUpperCase)            // Intermediate: Convert to uppercase
        .sorted()                            // Intermediate: Sort alphabetically
        .collect(Collectors.toList());       // Terminal: Collect to list
    
    System.out.println(result);  // [ALICE, CHARLIE, DAVE]
    ```

    The key difference: intermediate operations build the pipeline, while terminal operations execute it and produce a result.

    

13. Demonstrate the most commonly used Intermediate operations in Stream API with code snippets.

    1. `filter()` Filters elements based on a predicate.

       ```java
       List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");
       
       List<String> longNames = names.stream()
           .filter(name -> name.length() > 3)
           .collect(Collectors.toList());
       
       // Result: [Alice, Charlie, David]
       ```

    2. `map()` Transforms each element using the provided function.

       ```java
       List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
       
       List<Integer> nameLengths = names.stream()
           .map(String::length)
           .collect(Collectors.toList());
       
       // Result: [5, 3, 7]
       ```

    3. `flatMap()` Transforms each element into a stream and then flattens multiple streams into one.

       ```java
       List<List<Integer>> nestedLists = Arrays.asList(
           Arrays.asList(1, 2, 3),
           Arrays.asList(4, 5),
           Arrays.asList(6, 7, 8)
       );
       
       List<Integer> flattenedList = nestedLists.stream()
           .flatMap(Collection::stream)
           .collect(Collectors.toList());
       
       // Result: [1, 2, 3, 4, 5, 6, 7, 8]
       ```

    4. `distinct()` Removes duplicate elements.

       ```java
       List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 5, 5);
       
       List<Integer> distinctNumbers = numbers.stream()
           .distinct()
           .collect(Collectors.toList());
       
       // Result: [1, 2, 3, 4, 5]
       ```

    5. `sorted()` Sorts elements in natural order or by a provided comparator.

       ```java
       List<String> names = Arrays.asList("Charlie", "Alice", "Bob", "David");
       
       // Natural order sorting
       List<String> sortedNames = names.stream()
           .sorted()
           .collect(Collectors.toList());
       // Result: [Alice, Bob, Charlie, David]
       
       // Custom sorting (by length)
       List<String> sortedByLength = names.stream()
           .sorted(Comparator.comparing(String::length))
           .collect(Collectors.toList());
       // Result: [Bob, Alice, David, Charlie]
       ```

    6. `peek()` Performs an action on each element but returns the same stream (useful for debugging).

       ```java
       List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
       
       List<String> uppercaseNames = names.stream()
           .peek(name -> System.out.println("Processing: " + name))
           .map(String::toUpperCase)
           .collect(Collectors.toList());
       
       // Console output:
       // Processing: Alice
       // Processing: Bob
       // Processing: Charlie
       
       // Result: [ALICE, BOB, CHARLIE]
       ```

    7. `limit()` Limits the stream to the specified number of elements.

       ```java
       List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
       
       List<Integer> firstFive = numbers.stream()
           .limit(5)
           .collect(Collectors.toList());
       
       // Result: [1, 2, 3, 4, 5]
       ```

    8. `skip()` Skips the specified number of elements.

       ```java
       List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
       
       List<Integer> afterFive = numbers.stream()
           .skip(5)
           .collect(Collectors.toList());
       
       // Result: [6, 7, 8, 9, 10]
       ```

    Combining multiple intermediate operations in a single pipeline:

    ```java
    List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Bob", "David", "Alice", "Eve");
    
    List<String> result = names.stream()
        .filter(name -> name.length() > 3)           // Filter longer names
        .distinct()                                  // Remove duplicates
        .map(name -> name.toUpperCase())             // Convert to uppercase
        .sorted(Comparator.reverseOrder())           // Sort in reverse order
        .skip(1)                                     // Skip the first element
        .limit(2)                                    // Take only 2 elements
        .collect(Collectors.toList());
    
    // Result: [DAVID, CHARLIE]
    ```

    

14. How are Collections different from Streams?

    Collections and Streams are both ways to work with groups of data in Java, but they have fundamental differences:

    1. **Storage vs. Computation Model**

       - **Collections**: Store all elements in memory. They're data structures that hold elements.

       - **Streams**: Don't store elements. They're computational models that process elements on-demand.

    2. **Eager vs. Lazy Evaluation**

       - **Collections**: Eager evaluation - operations are executed immediately.

       - **Streams**: Lazy evaluation - operations are only executed when a terminal operation is triggered.

       ```java
       // Collection - modification happens immediately
       List<String> names = new ArrayList<>();
       names.add("Alice");  // Immediately modifies the collection
       
       // Stream - operations are lazy until terminal operation
       List<String> result = names.stream()
           .filter(n -> n.length() > 3)  // Not executed yet
           .map(String::toUpperCase)     // Not executed yet
           .collect(Collectors.toList()); // Now everything executes
       ```

    3. **Data Modification**

       - **Collections**: Can be modified directly (add, remove elements).

       - **Streams**: Can't be modified. They're designed for processing, not storage.

    4. **Reusability**

       - **Collections**: Can be traversed multiple times.

       - **Streams**: Can be traversed only once. After a terminal operation, the stream is consumed.

       ```java
       List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
       
       // Collection can be used multiple times
       for (String name : names) {
           System.out.println(name);
       }
       for (String name : names) {
           System.out.println(name.toUpperCase());
       }
       
       // Stream can only be used once
       Stream<String> nameStream = names.stream();
       nameStream.forEach(System.out::println);
       // This would throw an exception - stream already consumed
       // nameStream.forEach(n -> System.out.println(n.toUpperCase()));
       ```

    5. **Operations and Iteration**

       - **Collections**: External iteration (you control the iteration).

       - **Streams**: Internal iteration (the stream controls the iteration).

       ```java
       // Collection - external iteration
       for (String name : names) {
           if (name.length() > 4) {
               System.out.println(name.toUpperCase());
           }
       }
       
       // Stream - internal iteration
       names.stream()
           .filter(name -> name.length() > 4)
           .map(String::toUpperCase)
           .forEach(System.out::println);
       ```

    6. **Functional Programming Style**

       - **Collections**: Primarily imperative programming.

       - **Streams**: Designed for functional programming (filter, map, reduce).

    7. **Parallelism**

       - **Collections**: Require additional code for parallel processing.

       - **Streams**: Built-in support for parallel processing using `parallelStream()`.

       ```java
       // Sequential processing with collection
       for (String name : names) {
           processSomething(name);
       }
       
       // Parallel processing with streams
       names.parallelStream()
           .forEach(Main::processSomething);
       ```

    8. **Infinite Sequences**

       - **Collections**: Always finite.

       - **Streams**: Can represent infinite sequences.

       ```java
       // Can't create an infinite collection easily
       
       // But infinite streams are simple
       Stream<Integer> infiniteStream = Stream.iterate(1, n -> n + 1);
       // Limit to avoid processing forever
       infiniteStream.limit(10).forEach(System.out::println);
       ```

       In summary, Collections are for storing and accessing data, while Streams are for processing data with functional-style operations.

       

15. Implement Stream API's `filter` and  `map` methods by yourself.

    ```java
    import java.util.ArrayList;
    import java.util.List;
    import java.util.function.Function;
    import java.util.function.Predicate;
    
    public class CustomStream<T> {
        private final List<T> elements;
        
        // Constructor
        public CustomStream(List<T> elements) {
            this.elements = new ArrayList<>(elements);
        }
        
        // Implementation of filter
        public CustomStream<T> filter(Predicate<T> predicate) {
            List<T> filteredList = new ArrayList<>();
            
            for (T element : elements) {
                if (predicate.test(element)) {
                    filteredList.add(element);
                }
            }
            
            return new CustomStream<>(filteredList);
        }
        
        // Implementation of map
        public <R> CustomStream<R> map(Function<T, R> mapper) {
            List<R> mappedList = new ArrayList<>();
            
            for (T element : elements) {
                R mappedElement = mapper.apply(element);
                mappedList.add(mappedElement);
            }
            
            return new CustomStream<>(mappedList);
        }
        
        // Terminal operation to collect results
        public List<T> collect() {
            return new ArrayList<>(elements);
        }
        
        // Example of usage
        public static void main(String[] args) {
            List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve");
            
            // Using our custom Stream implementation
            List<String> result = new CustomStream<>(names)
                .filter(name -> name.length() > 3)
                .map(String::toUpperCase)
                .collect();
            
            System.out.println(result); // [ALICE, CHARLIE, DAVID]
        }
    }
    ```

    
