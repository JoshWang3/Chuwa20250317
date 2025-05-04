1. List all of the Spring data related annotations your learned and explain its usage.
2. What is DTO, VO, Payload, PO, model, DAO?
   Term	             Full Form	       Description
   DTO	Data Transfer Object	       Used to transfer data between layers or over the network; no business logic.
   VO	Value Object	               Immutable object that represents a value, often used in view or response layers.
   Payload	—	                       The actual data sent in an HTTP request body, especially in POST/PUT requests.
   PO	Persistent Object           	Represents a database entity; mapped to database tables using ORM.
   Model	—	                       General term for the main data structure in an app, may contain business logic.
   DAO	Data Access Object          	A layer responsible for interacting with the database (CRUD operations).
3. What is @JsonProperty("description_yyds")
@JsonProperty("description_yyds") is a Jackson annotation used in Java (commonly in Spring Boot) 
to map a JSON field to a Java object field when their names differ.

4. Explain the purpose of following dependency?
   This Maven dependency adds Jackson Databind to our project, enabling powerful JSON serialization and deserialization in Java.
   Provides the core functionality to convert Java objects to JSON (serialization) and JSON to Java objects (deserialization).
   Combines the streaming API (jackson-core) and tree model (jackson-annotations) into a high-level data-binding API.
5. What is spring-boot-stater?
   what dependecies in the below starter? do you know any starters?
   A Spring Boot starter is a pre-configured set of dependencies that helps to get started quickly with a particular feature or tech stack. 
   Instead of manually importing 5–10 libraries, we just use one starter, and it pulls them all in automatically.
6. Explain @RequestMapping(value = "/users", method = RequestMethod.POST) ? could you list CRUD by
   this style?
   This annotation is used in Spring MVC to map HTTP requests to a controller method.
   value = "/users": URL path to match
   method = RequestMethod.POST: Only matches HTTP POST requests
   It maps POST /users to the annotated method—typically for creating a user.
   CRUD with @RequestMapping
   Operation	HTTP Method	URL Pattern	Example Annotation
   Create	POST	/users	@RequestMapping(value = "/users", method = RequestMethod.POST)
   Read All	GET	/users	@RequestMapping(value = "/users", method = RequestMethod.GET)
   Read One	GET	/users/{id}	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
   Update	PUT	/users/{id}	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
   Delete	DELETE	/users/{id}	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
   @RequestMapping is flexible, but we can simplify it using these specialized annotations:

@GetMapping, @PostMapping, @PutMapping, @DeleteMapping

7. What is ResponseEntity? why do we need it?
ResponseEntity<T> is a Spring class that represents the entire HTTP response, including:
Body – the actual data (T)
Status code – like 200 OK, 404 Not Found, etc.
Headers – like Content-Type, Location, etc.

8. What is ResultSet in jdbc? and describe the flow how to get data using JDBC
   ResultSet is a Java object that holds the data returned from executing a SQL SELECT query using JDBC (Java Database Connectivity).
   // 1. Load JDBC driver (optional for newer versions)
   Class.forName("com.mysql.cj.jdbc.Driver");

// 2. Create a database connection
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "user", "password");

// 3. Create a statement
Statement stmt = conn.createStatement();

// 4. Execute a SELECT query and get a ResultSet
ResultSet rs = stmt.executeQuery("SELECT * FROM students");

// 5. Iterate through the ResultSet
while (rs.next()) {
String name = rs.getString("name");
int age = rs.getInt("age");
// ... use the data
}

// 6. Close resources
rs.close();
stmt.close();
conn.close();

9. Compare Spring Data JPA vs Hibernate vs JDBC.
✅ Use JDBC if you need full control and high performance with manual SQL.
✅ Use Hibernate if you want object-relational mapping and rich ORM features.
✅ Use Spring Data JPA for rapid development, less boilerplate, and simple CRUD.
   | Feature               | **JDBC**                          | **Hibernate**                                       | **Spring Data JPA**                                  |
   |-----------------------|----------------------------------|-----------------------------------------------------|-------------------------------------------------------|
   | **Level**             | Low-level                        | ORM framework (mid-level)                          | High-level abstraction over JPA (and Hibernate)       |
   | **Boilerplate Code**  | A lot (manual SQL, result parsing) | Less (uses HQL/Criteria, auto mapping)             | Minimal (mostly interface-based repositories)         |
   | **Ease of Use**       | Harder (manual handling)         | Easier than JDBC                                   | Easiest (declarative, almost no implementation)       |
   | **Query Language**    | SQL                              | HQL/JPQL + Criteria API                            | JPQL + method naming + @Query                         |
   | **Performance Tuning**| Manual                           | More options (caching, lazy loading, fetch types)  | Inherits Hibernate tuning, but with less control      |
   | **Control over SQL**  | Full control                     | Partial control (HQL hides SQL details)            | Least (but allows native SQL or @Query when needed)   |
   | **Transactions**      | Manual via JDBC/Connection       | Declarative via JTA or manual                      | Declarative via `@Transactional`                      |
   | **Mapping**           | Manual (via code)                | Automatic via annotations (`@Entity`, `@Table`, etc.) | Same as Hibernate                                     |
   | **Learning Curve**    | Steep (verbose)                  | Moderate                                           | Low (declarative & powerful)                          |
10. Learn how to use ObjectMapper by this example.
1. https://github.com/TAIsRich/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/ex
   ercise/oa/api/FoodOutletJackson.java
11. What is the serialization and desrialization?
    Serialization: the process of converting a Java object into a byte stream(Java object → stream of bytes), so it can be:
    Deserialization: It takes the byte stream and reconstructs the original Java object.(stream of bytes → Java object)
1. https://hazelcast.com/glossary/serialization/

12. use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].
    int[] numbers = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};

    double average = Arrays.stream(numbers)
                               .average()
                               .orElse(0.0)
13. 抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/03_post_pageable 下的代码
14. 抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/04_comment 下的代码