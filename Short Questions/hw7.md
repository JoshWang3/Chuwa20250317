# HW7 
## 1. List all of the Spring data related annotations your learned and explain its usage.
See details in file `annotations.md` in current folder

## 2. What is DTO, VO, Payload, PO, model, DAO?
### DTO — Data Transfer Object
- Used to transfer data between layers or systems, usually without any logic.
  - Often used between controller ↔ service, or between client ↔ API
  - Has only fields + getters/setters
  - No logic or persistence
- Why use DTO?
  - Prevent exposing sensitive or internal fields (like passwords, IDs)
  - Customize data sent to client
```java
public class PostDTO {
    private String title;
    private String content;
}
// constructors, getters and setters
```

### VO — Value Object
- Represents an immutable object with no identity. Think of it as a description, not an entity.
  - Equality is based on field values, not ID
  - Common in Domain-Driven Design (DDD)
- Why use VO?
  - Useful for things like Price, Coordinates, Email — where values define the object, not its ID.
```java
public class Money {
    private final BigDecimal amount;
    private final String currency;
    // equals & hashCode based on both fields
}
```

### Payload
- A casual term, typically used to describe data coming from the client (usually in JSON).
  - Not a formal pattern like DTO or VO
  - Used in request bodies
- Why use Payload?
  - To separate request data from actual domain models.
```java
public class CreatePostPayload {
    private String title;
    private String content;
}
```

### PO — Persistent Object (aka POJO mapped to DB)
- A class mapped to a database table (often via JPA). Basically: our `@Entity` class.
- Why use PO?
  - It’s the object that lives in our database layer.
```java
@Entity
public class Post {
    @Id
    private Long id;
    private String title;
}
```

###  Model in Spring MVL
- Used for objects exposed to templates/view layers

### DAO — Data Access Object
- A class dedicated to interacting with the database.
  - Abstracts away raw SQL or ORM logic
  - Often replaced by Spring Data JPA repositories.
- Why use DAO?
  - To decouple DB operations from business logic.
```java
@Repository
public interface PostDAO extends JpaRepository<Post, Long> {}
```

## 3. What is @JsonProperty("description_yyds")
- A Jackson annotation used to map a JSON property named "description_yyds" to a Java field, method, or constructor parameter.
  - Used during serialization and deserialization.
  - Overrides the default name Jackson would use.
  - Useful when JSON field names don’t match Java naming conventions.
```java
public class Product {
    @JsonProperty("description_yyds")
    private String description;
}
```
## 4. Explain the purpose of following dependency?
```html
<dependency>
<groupId>com.fasterxml.jackson.core</groupId>
<artifactId>jackson-databind</artifactId>
<version>2.13.3</version>
<scope>compile</scope>
</dependency>
```
Purpose
- This Maven dependency adds Jackson Databind to our project, enabling powerful JSON serialization and deserialization in Java.
  - Provides the core functionality to convert Java objects to JSON (serialization) and JSON to Java objects (deserialization).
  - Combines the streaming API (jackson-core) and tree model (jackson-annotations) into a high-level data-binding API.
  - 
Detail
  - groupId: com.fasterxml.jackson.core — Organization that maintains Jackson.
  - artifactId: jackson-databind — The module for object mapping.
  - version: 2.13.3 — Specific version being used.
  - scope: compile — Available during all build phases and at runtime.
  
Essential for almost any Spring Boot app that works with JSON.

## 5. What is spring-boot-stater?
- A Spring Boot starter is a pre-configured set of dependencies that helps to get started quickly with a particular feature or tech stack. Instead of manually importing 5–10 libraries, we just use one starter, and it pulls them all in automatically.

### What dependecies in the below starter? do you know any starters?
```html
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
- This starter sets up everything needed to build web applications and REST APIs.
### Core Spring Boot Dependencies

| Dependency                        | Purpose                                         |
|-----------------------------------|-------------------------------------------------|
| `spring-web`                      | Core web and REST support (controllers, etc.)   |
| `spring-webmvc`                   | MVC framework (DispatcherServlet, etc.)         |
| `spring-boot`                     | Core Spring Boot support                        |
| `spring-boot-starter`            | Core starter config (logging, auto-config)      |
| `jackson-databind`               | JSON serialization/deserialization              |
| `tomcat-embed-core`              | Embedded Tomcat server (default)                |
| `validation-api` + `hibernate-validator` | Input validation via annotations        |


### Common Spring Boot Starters

| Starter                           | Purpose                          |
|-----------------------------------|----------------------------------|
| `spring-boot-starter-data-jpa`    | JPA + Hibernate support          |
| `spring-boot-starter-security`    | Spring Security                  |
| `spring-boot-starter-test`        | JUnit, Mockito, Spring Test      |
| `spring-boot-starter-thymeleaf`   | Thymeleaf template engine        |
| `spring-boot-starter-actuator`    | App monitoring endpoints         |
| `spring-boot-starter-mail`        | Sending emails                   |
| `spring-boot-starter-amqp`        | RabbitMQ support                 |
| `spring-boot-starter-data-redis`  | Redis integration                |

## 6. Explain `@RequestMapping(value = "/users", method = RequestMethod.POST)` ? could you list CRUD by this style?
- This annotation is used in Spring MVC to map HTTP requests to a controller method.
  - value = "/users": URL path to match
  - method = RequestMethod.POST: Only matches HTTP POST requests
-  It maps POST /users to the annotated method—typically for creating a user.

### CRUD with @RequestMapping

| Operation   | HTTP Method | URL Pattern     | Example Annotation                                                   |
|-------------|-------------|------------------|------------------------------------------------------------------------|
| Create      | POST        | `/users`         | `@RequestMapping(value = "/users", method = RequestMethod.POST)`       |
| Read All    | GET         | `/users`         | `@RequestMapping(value = "/users", method = RequestMethod.GET)`        |
| Read One    | GET         | `/users/{id}`    | `@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)`   |
| Update      | PUT         | `/users/{id}`    | `@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)`   |
| Delete      | DELETE      | `/users/{id}`    | `@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)`|

`@RequestMapping` is flexible, but we can simplify it using these specialized annotations:
 - `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`

### @RequestMapping: Old Style vs Shorthand, They are Equivalent
| Old Style                                                             | Shorthand                    |
|----------------------------------------------------------------------|------------------------------|
| `@RequestMapping(value = "/users", method = RequestMethod.POST)`     | `@PostMapping("/users")`     |
| `@RequestMapping(value = "/users", method = RequestMethod.GET)`      | `@GetMapping("/users")`      |
| `@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)` | `@PutMapping("/users/{id}")` |
| `@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)` | `@DeleteMapping("/users/{id}")` |

## 7. What is ResponseEntity? why do we need it?
```java
// 1. Returns 200 OK with a body
new ResponseEntity<>(postResponse, HttpStatus.OK);
// 2. Returns 201 Created with a body
new ResponseEntity<>(postResponse, HttpStatus.CREATED);
// 3. Shortcut for 200 OK with a body
ResponseEntity.ok(postService.getPostById(id));
```
### What is ResponseEntity?
- ResponseEntity<T> is a wrapper for an HTTP response. It lets us control:
  - The body (T)
  - The status code (200 OK, 201 CREATED, etc.)
  - Optional headers (like Location, Content-Type, etc.)

### Why do we need it?
Because sometimes returning just the object (Post, User, etc.) isn’t enough! We might want to: 
  - Change the status code (e.g., 201 Created, 204 No Content)
  - Add custom headers
  - Return an empty body with a specific status
  - Fully customize the HTTP response based on conditions

## 8. What is ResultSet in jdbc? and describe the flow how to get data using JDBC
### What is ResultSet in JDBC?
ResultSet is a table-like object in Java that holds the data returned from a SQL query. We use it to read rows from the database, one by one.
### JDBC Data Retrieval Flow
1. Load the Driver
```java
Class.forName("com.mysql.cj.jdbc.Driver");
```
2. Establish Connection
```java
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/mydb", "user", "password");
```
3. Create Statement
```java
Statement stmt = conn.createStatement();
```
4. Execute Query
```java
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
```
5. Process ResultSet
```java
while (rs.next()) {
    int id = rs.getInt("id");
    String name = rs.getString("name");
    // Use the data as needed
}
```
6. Close Resources
```java
rs.close();
stmt.close();
conn.close();
```

## 9. Compare Spring Data JPA vs Hibernate vs JDBC.
- JDBC: Raw, powerful, verbose. We write everything. Great for fine-tuned performance but painful for large projects.
- Hibernate: Full-featured ORM. Handles mapping, caching, lazy loading, etc. Needs more setup but powerful.
- Spring Data JPA: Abstraction layer. Auto-generates repos and queries. Great for rapid dev with minimal code.
### JDBC vs Hibernate vs Spring Data JPA

| Feature             | JDBC              | Hibernate                | Spring Data JPA                      |
|---------------------|-------------------|---------------------------|--------------------------------------|
| Level               | Low-level API     | ORM Framework             | Abstraction over JPA                 |
| Boilerplate         | High              | Medium                    | Low                                  |
| SQL Required        | Yes (manual)      | Optional (HQL/JPQL)       | Rare (auto queries)                  |
| Object Mapping      | Manual            | Automatic                 | Automatic                            |
| Ease of Use         | Hard              | Moderate                  | Easy                                 |
| Query Customization | Full SQL control  | HQL/JPQL/native SQL       | Derived methods + JPQL               |

## 10. Learn how to use ObjectMapper by this example
https://github.com/TAIsRich/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/exercise/oa/api/FoodOutletJackson.java
```java
// JSON (String) → Java object
FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);
// Java object → JSON (String)
String s = objectMapper.writeValueAsString(foodOutlet);
// JSON → Tree (JsonNode)
objectMapper.readTree() // learn how to use it?
```

## 11. What is the serialization and desrialization?
https://hazelcast.com/glossary/serialization/
- Serialization: the process of converting a Java object into a byte stream(Java object → stream of bytes), so it can be: 
  - Saved to a file
  - Sent over a network
  - Stored in memory (e.g. Hazelcast, Redis)
  - Used by another system
- Deserialization: It takes the byte stream and reconstructs the original Java object.(stream of bytes → Java object)
```java
// Serialization
ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser"));
out.writeObject(myObject);

// Deserialization
ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.ser"));
MyObject myObject = (MyObject) in.readObject();
```

## 12. use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32]
```java
import java.util.Arrays;

public class StreamAverage {
    public static void main(String[] args) {
        int[] nums = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};
        Double average = Arrays.stream(nums).average().orElse(0);
        System.out.println("Average: " + average);
    }
}
```

## 13. 抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/03_post_pageable 下的代码
## 14. 抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/04_comment 下的代码