1. List all of the Spring data related annotations your learned and explain its usage.

   Already updated and written in `Short Questions/annotations.md`

   

2. What is DTO, VO, Payload, PO, model, DAO?

   | Term    | Full Name            | Description                                                  |
   | ------- | -------------------- | ------------------------------------------------------------ |
   | DTO     | Data Transfer Object | Objects used to transfer data between subsystems, such as from service to controller or between server and client applications |
   | VO      | View Object          | Objects tailored for frontend display, containing formatted data ready for UI presentation |
   | Payload | Payload              | The actual data transmitted in the body of an API request or response, typically in JSON or XML format |
   | PO      | Persistent Object    | Objects representing database entities, mapped to database tables with JPA annotations |
   | Model   | Model                | Domain objects containing both data and behavior in applications, representing business concepts |
   | DAO     | Data Access Object   | Objects encapsulating logic for database operations, separating domain from data access code |

3. What is `@JsonProperty("description_yyds")`

   `@JsonProperty("description_yyds")` is an annotation from the Jackson JSON library for Java. This annotation is used to specify the name of a JSON property during serialization and deserialization.

   In this case, it maps a Java class field to the JSON property name "description_yyds". This means:

   1. When converting a Java object to JSON (serialization), the annotated field will appear in the JSON as "description_yyds" regardless of the Java field's actual name.
   2. When converting JSON to a Java object (deserialization), the JSON property "description_yyds" will be mapped to the annotated field.

   ```java
   public class Product {
       private String name;
       
       @JsonProperty("description_yyds")
       private String description;
       
       // getters and setters
   }
   ```

   With this annotation, the JSON output would look like:

   ```json
   {
       "name": "Product Name",
       "description_yyds": "Product Description"
   }
   ```

   

   

4. Explain the purpose of following dependency?

   ```xml
   <dependency>
       <groupId>com.fasterxml.jackson.core</groupId>
       <artifactId>jackson-databind</artifactId>
       <version>2.13.3</version>
       <scope>compile</scope>
   </dependency>
   ```

   The `jackson-databind` dependency is a core component of the Jackson library, which is a high-performance JSON processor for Java. This dependency provides functionality for converting between Java objects and JSON (serialization and deserialization).

   Key purposes and features:

   - Enables automatic conversion of Java objects to JSON format (serialization)

   - Allows parsing JSON into Java objects (deserialization)

   - Provides annotations like `@JsonProperty` to customize the JSON mapping process

   - Supports data binding for Java collections, maps, and various data types

   - Handles common JSON processing needs in REST APIs and web applications

   - Works seamlessly with Spring Boot for JSON request/response handling

     

5. What is **spring-boot-stater**? What dependecies in the below starter? do you know any starters?

   ```xml
   <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
   </dependency>
   ```

   **Spring Boot Starter** is a set of convenient dependency descriptors that you can include in your application. Spring Boot Starters simplify the Maven/Gradle configuration by providing a curated set of dependencies that work well together, following Spring Boot's *convention over configuration* philosophy.

   **spring-boot-starter-web** specifically includes dependencies for building web applications, including:

   - spring-boot-starter (core Spring Boot starter)
   - spring-web and spring-webmvc (Spring MVC framework)
   - spring-json (JSON processing)
   - tomcat (embedded servlet container)
   - validation-api (for data validation)
   - hibernate-validator (implementation of validation API)

   **Other common Spring Boot starters include:**

   - spring-boot-starter-data-jpa (for JPA with Hibernate)

   - spring-boot-starter-security (for Spring Security)

   - spring-boot-starter-test (for testing)

   - spring-boot-starter-actuator (for application monitoring)

   - spring-boot-starter-jdbc (for JDBC operations)

   - spring-boot-starter-data-mongodb (for MongoDB integration)

   - spring-boot-starter-data-redis (for Redis integration)

     

6. Explain `@RequestMapping(value = "/users", method = RequestMethod.POST)` ? Could you list CRUD by this style?

   `@RequestMapping(value = "/users", method = RequestMethod.POST)` is a Spring MVC annotation that maps HTTP POST requests to the "/users" URL path to a specific controller method. This annotation defines:

   - `value = "/users"`: The URL path that this method handles
   - `method = RequestMethod.POST`: The HTTP method (POST) this method responds to

   CRUD Operations Using RequestMapping Style:

   ```java
   // CREATE - Create a new user - @PostMapping("/users")
   @RequestMapping(value = "/users", method = RequestMethod.POST)
   public ResponseEntity<User> createUser(@RequestBody User user) {
       // Implementation
   }
   
   // READ - Get all users - @GetMapping("/users")
   @RequestMapping(value = "/users", method = RequestMethod.GET)
   public ResponseEntity<List<User>> getAllUsers() {
       // Implementation
   }
   
   // READ - Get a specific user - @GetMapping("/users/{id}")
   @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
   public ResponseEntity<User> getUserById(@PathVariable Long id) {
       // Implementation
   }
   
   // UPDATE - Update a user - @PutMapping("/users/{id}")
   @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
   public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
       // Implementation
   }
   
   // DELETE - Delete a user - @DeleteMapping("/users/{id}")
   @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
       // Implementation
   }
   ```

   

7. What is ResponseEntity? why do we need it?

   ```java
   new ResponseEntity<>(postResponse, HttpStatus.OK);
   new ResponseEntity<>(postResponse, HttpStatus.CREATED);
   ResponseEntity.ok(postService.getPostById(id));
   ```

   `ResponseEntity` is a class in Spring Framework that represents the entire HTTP response, including:

   - HTTP status code
   - HTTP headers
   - Response body

   We need `ResponseEntity` because it gives us full control over the HTTP response sent back to the client, enabling us to:

   1. Set specific HTTP status codes appropriate for the operation:

      - `new ResponseEntity<>(postResponse, HttpStatus.CREATED);` - Returns `201 Created` for successful resource creation
      - `new ResponseEntity<>(postResponse, HttpStatus.OK);` - Returns `200 OK` for successful operations
      - `ResponseEntity.ok(postService.getPostById(id));` - A shorthand method that returns `200 OK` with the body

   2. Include custom HTTP headers when needed (security tokens, location headers, etc.)

   3. Properly represent REST API semantics by matching HTTP methods with appropriate status codes

   4. Provide standardized error handling with appropriate status codes (4xx for client errors, 5xx for server errors)

      

8. What is ResultSet in jdbc? and describe the flow how to get data using JDBC?

   **ResultSet in JDBC**

   ResultSet is an interface in JDBC that represents a result set obtained from executing a SQL query. It acts as a table of data that is returned by the database and provides methods to navigate through and retrieve data from this table.

   **JDBC Data Retrieval Flow**

   1. **Load the JDBC driver**:

      ```java
      Class.forName("com.mysql.jdbc.Driver");
      ```

   2. **Establish a connection**:

      ```java
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "username", "password");
      ```

   3. **Create a Statement object**:

      ```java
      Statement statement = connection.createStatement();
      ```

   4. **Execute the query**:

      ```java
      ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
      ```

   5. **Process the ResultSet**:

      ```java
      while(resultSet.next()) {
          String name = resultSet.getString("name");
          int age = resultSet.getInt("age");
          // Process retrieved data
      }
      ```

   6. **Close resources**:

      ```java
      resultSet.close();
      statement.close();
      connection.close();
      ```

      

9. Compare **Spring Data JPA** vs **Hibernate** vs **JDBC**.

   | Feature                 | Spring Data JPA                                         | Hibernate                                        | JDBC                                                         |
   | ----------------------- | ------------------------------------------------------- | ------------------------------------------------ | ------------------------------------------------------------ |
   | **Abstraction Level**   | Highest - provides repository abstraction on top of JPA | Medium - implements JPA specification            | Lowest - direct database access                              |
   | **Boilerplate Code**    | Minimal - uses repository interfaces                    | Moderate - entity mapping and session management | Extensive - manual connection, statement and result handling |
   | **Query Methods**       | Method name queries, @Query annotation                  | HQL/JPQL, Criteria API                           | Raw SQL strings                                              |
   | **Configuration**       | Simple with Spring Boot starters                        | XML or annotation configuration                  | Manual driver loading and connection setup                   |
   | **Database Changes**    | Abstracted, easier to switch databases                  | Supports database portability with dialects      | Database-specific code often required                        |
   | **Performance Control** | Limited fine-grained control                            | Good control with caching, batch processing      | Complete control but manual optimization                     |
   | **Learning Curve**      | Steepest (requires Spring, JPA understanding)           | Moderate (ORM concepts)                          | Lowest (just SQL and Java)                                   |
   | **Use Case**            | Rapid development, standard CRUD operations             | Complex domain models, ORM requirements          | Performance-critical applications, complex queries           |

10. Learn how to use ObjectMapper by this example.

    ```java
    FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);
    String s = objectMapper.writeValueAsString(foodOutlet);
    objectMapper.readTree() // learn how to use it?
    ```

    ObjectMapper is a core class in the Jackson library that provides functionality for converting between Java objects and JSON.

    1. `readValue` Method

       ```java
       FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);
       ```

       This method deserializes JSON content (from a string, file, URL, etc.) into a Java object of the specified type. In this case, it converts the JSON string `resBody` into a `FoodOutlet` object.

       The `readValue()` function accepts various forms of input, such as strings, files, URLs, and input streams containing JSON. 

    2. `writeValueAsString` Method

       ```java
       String s = objectMapper.writeValueAsString(foodOutlet);
       ```

       This method serializes a Java object into a JSON string. It converts the `foodOutlet` object into a JSON-formatted string.

    3. `readTree` Method

       ```java
       objectMapper.readTree() // How to use it?
       ```

       The `readTree` method parses JSON content into a tree of `JsonNode` objects, which allows you to navigate and access JSON data in a more flexible way without binding to a specific Java class.

       With `readTree()`, you can parse JSON into a JsonNode object that lets you navigate JSON as a Java object in a dynamic and flexible way.

       ```java
       String json = "{\"name\":\"Restaurant ABC\", \"rating\":4.5}";
       JsonNode jsonNode = objectMapper.readTree(json);
       
       // Access fields by name
       String name = jsonNode.get("name").asText();
       double rating = jsonNode.get("rating").asDouble();
       
       // Check if a field exists
       if(jsonNode.has("location")) {
           // Process location data
       }
       ```

       

       

11. What is the serialization and desrialization?

    **Serialization** is the process of converting a data structure or object into a format that can be stored (in a file, memory buffer, or transmitted across a network) and reconstructed later. In Java, serialization specifically refers to converting Java objects into byte streams.

    **Deserialization** is the reverse process of serialization - it converts the serialized format (like JSON, XML, or binary data) back into the original data structure or object. In Java, deserialization reconstructs Java objects from byte streams.

    In the context of Jackson and JSON processing:

    1. Serialization (Java object → JSON):
       - Converting Java objects into JSON strings
       - Example: `objectMapper.writeValueAsString(foodOutlet)`
       - Also called "marshalling" in some contexts
    2. Deserialization (JSON → Java object):
       - Converting JSON strings into Java objects
       - Example: `objectMapper.readValue(resBody, FoodOutlet.class)`
       - Also called "unmarshalling" in some contexts

    The serialization/deserialization process often involves mapping fields between objects and the serialized format, handling type conversions, and maintaining object relationships.

    

12. use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].

    ```java
    import java.util.Arrays;
    
    public class Main {
        public static void main(String[] args) {
            int[] numbers = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};
            
            // Calculate average using Stream API
            double average = Arrays.stream(numbers)  // Convert array to stream
                                  .average()         // Calculate average
                                  .getAsDouble();    // Get result as double
            
            System.out.println("Average: " + average);
        }
    }
    ```

    ```tex
    Average: 39.7
    ```

    
