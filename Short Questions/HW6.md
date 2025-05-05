# List all of the Spring data related annotations your learned and explain its usage.
## Entity and Table Mapping
| Annotation                         | Usage                                                                 |
|-----------------------------------|-----------------------------------------------------------------------|
| `@Entity`                         | Marks a class as a JPA entity (mapped to a database table).          |
| `@Table(name = "table_name")`     | Specifies the table name in the database (optional if the class name matches). |
| `@Id`                             | Marks a field as the primary key.                                    |
| `@GeneratedValue(strategy = ...)` | Defines how the primary key is generated (`AUTO`, `IDENTITY`, `SEQUENCE`, `TABLE`). |
| `@Column(name = "column_name")`   | Maps a field to a specific column; used to customize column properties. |
| `@Transient`                      | Prevents a field from being persisted in the database.               |
| `@Lob`                            | Maps a field to a large object (e.g., `CLOB`, `BLOB`).               |
| `@Enumerated(EnumType.STRING)`   | Stores enum values as strings (instead of ordinals).                 |

## Relationship Mapping
| Annotation                        | Usage                                                                 |
|----------------------------------|-----------------------------------------------------------------------|
| `@OneToOne`                      | Defines a one-to-one relationship between entities.                   |
| `@OneToMany`                     | Defines a one-to-many relationship (e.g., one user has many orders).  |
| `@ManyToOne`                     | Defines a many-to-one relationship (e.g., many orders belong to one user). |
| `@ManyToMany`                    | Defines a many-to-many relationship.                                  |
| `@JoinColumn(name = "column_name")` | Specifies the foreign key column for relationships.               |
| `@JoinTable(...)`                | Specifies the join table for `@ManyToMany` relationships.             |

## Repository and Query Annotations
| Annotation                        | Usage                                                                                                  |
|----------------------------------|----------------------------------------------------------------------------------------------------------|
| `@Repository`                    | Marks a class as a Spring Data repository (for exception translation). Usually not needed if you extend `JpaRepository`. |
| `@Query("JPQL or SQL query")`    | Used to define a custom query in a repository method.                                                   |
| `@Modifying`                     | Used with `@Query` for update/delete operations.                                                        |
| `@Transactional`                | Ensures the query executes within a transaction (needed for modifying queries).                         |
| `@Param("name")`                | Binds method parameters to named parameters in `@Query`.                                                |

## Auditing Annotations
| Annotation             | Usage                                                                 |
|------------------------|-----------------------------------------------------------------------|
| `@CreatedDate`         | Automatically stores the entity creation timestamp.                  |
| `@LastModifiedDate`    | Automatically stores the last modification timestamp.                |
| `@CreatedBy`           | Automatically stores the user who created the entity.                |
| `@LastModifiedBy`      | Automatically stores the user who last modified the entity.          |
| `@EnableJpaAuditing`   | Enables auditing features (usually on a configuration class).        |

# What is DTO, VO, Payload, PO, model, DAO?
## DTO – Data Transfer Object
### Purpose
Used to transfer data between layers (especially between controller and service or between services).
### Key Characteristics:
- Contains only data and no business logic.
- Often used to expose only necessary fields (e.g., hiding internal DB fields from API responses).

## VO – Value Object
### Purpose: 
Represents a value or a concept without identity (immutable).
### Key Characteristics:
- Used more in domain-driven design.
- Considered immutable – once created, it shouldn’t change.

## Payload
### Purpose: 
Often used to describe the body of a request or response in API communication.
### Key Characteristics:
- Could be interchangeable with DTO in many RESTful APIs.
- Often used to represent input data (request payload) or output (response payload).

## PO – Persistent Object (or POJO)
### Purpose: 
Represents a direct mapping to database tables (a.k.a. Entity).
### Key Characteristics:
- Annotated with @Entity, @Table, etc.
- Used by JPA/Hibernate to persist data in the database.

## Model
### Purpose: 
Generic term for data-carrying classes used throughout the application.
### Key Characteristics:
- Can refer to entity, DTO, or domain objects depending on context.
- In MVC (Model-View-Controller), the Model refers to the part that handles the business logic and data.

## DAO – Data Access Object
### Purpose: 
Handles direct interaction with the database.
### Key Characteristics:
- Abstracts the persistence logic.
- In modern Spring apps, replaced by Spring Data interfaces like JpaRepository, but you might still see DAOs used for custom SQL operations.

# What is @JsonProperty("description_yyds")
This annotation is part of the Jackson library, which is commonly used in Spring Boot for converting Java objects to and from JSON.
## Usage:
```java
public class ProductDTO {
    
    @JsonProperty("description_yyds")
    private String description;
    
    // getters and setters
}
```
This means:
- When converting this Java object to JSON (e.g., for an API response), the field description will appear as "description_yyds" in the JSON.
- When parsing incoming JSON (e.g., in a request body), if the JSON contains a "description_yyds" field, it will be mapped to the Java field description.

# Explain the purpose of following dependency?
```java
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.3</version>
    <scope>compile</scope>
</dependency>
```
## Purpose of jackson-databind
This dependency brings in Jackson Databind, which is a core part of the Jackson library used for:
- Serializing Java objects to JSON (Java → JSON)
- Deserializing JSON to Java objects (JSON → Java)

# What is spring-boot-stater? What dependecies in the below starter? do you know any starters?
```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
A Spring Boot Starter is a predefined set of dependencies that helps you quickly set up a Spring Boot application for a specific purpose (like web, data, security, etc.).

Instead of manually adding each individual dependency (like Spring MVC, Jackson, Tomcat, etc.), you just include a starter, and it pulls in everything you need.

The starter used in the demo code is one of the most commonly used starters: **spring-boot-starter-web**

With just this starter, you can:
- Build REST APIs with @RestController
- Accept and return JSON automatically
- Run your app as a standalone web server (java -jar)
- Handle validation of input objects

## Other Common Spring Boot Starters:
| Starter                             | Purpose                                      |
|-------------------------------------|----------------------------------------------|
| `spring-boot-starter-data-jpa`      | JPA + Hibernate (for database access)        |
| `spring-boot-starter-security`      | Add Spring Security                          |
| `spring-boot-starter-thymeleaf`     | Server-side rendering with Thymeleaf         |
| `spring-boot-starter-test`          | Testing tools: JUnit, Mockito, Spring Test   |
| `spring-boot-starter-validation`    | Bean validation using Hibernate Validator    |
| `spring-boot-starter-mail`          | Email sending support                        |
| `spring-boot-starter-actuator`      | Monitoring and metrics for your app          |

# Explain @RequestMapping(value = "/users", method = RequestMethod.POST) ? could you list CRUD by this style?
This annotation is used in Spring MVC to map HTTP requests to controller methods.
## Meaning:
- value = "/users" → This method handles requests to the /users URL.
- method = RequestMethod.POST → This method is triggered only for HTTP POST requests.
## CRUD Endpoints using @RequestMapping
Here’s how you’d define a full set of CRUD operations (Create, Read, Update, Delete) using the classic @RequestMapping style:
```java
@RestController
@RequestMapping("/users")
public class UserController {

    // CREATE (POST)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok("User created");
    }

    // READ (GET ALL)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(List.of());
    }

    // READ (GET BY ID)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(new UserDTO());
    }

    // UPDATE (PUT)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDTO user) {
        return ResponseEntity.ok("User updated");
    }

    // DELETE (DELETE)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok("User deleted");
    }
}
```

# What is ResponseEntity? why do we need it?
```java
new ResponseEntity<>(postResponse, HttpStatus.OK);
new ResponseEntity<>(postResponse, HttpStatus.CREATED);
ResponseEntity.ok(postService.getPostById(id));
```
## What is ResponseEntity?
ResponseEntity<T> is a Spring class that represents the entire HTTP response, including:
- The body (your data)
- The status code (like 200 OK, 404 Not Found, 201 Created, etc.)
- HTTP headers (optional)

It gives you full control over the response sent back to the client.

## Why do we need ResponseEntity?
By default, Spring returns the object as JSON with status 200 OK, but sometimes you want to:
- Return a different status code (like 201 Created or 204 No Content)
- Add custom headers (e.g., for pagination, CORS, etc.)
- Return empty responses with status only (e.g., 204 No Content)
- Be explicit and readable in your response logic

# What is ResultSet in jdbc? and describe the flow how to get data using JDBC
ResultSet is a Java object that holds the data returned from a database query (usually a SELECT statement).
- Think of it as a table in memory, where:
- Each row is one record.
- You can move through rows and read column values.

It’s returned by calling executeQuery() on a Statement or PreparedStatement.
## JDBC Flow to Get Data from a Database
### Load the JDBC Driver 
```java
Class.forName("com.mysql.cj.jdbc.Driver");
```
### Establish a Connection
```java
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/mydb", "username", "password");
```
### Create a Statement or PreparedStatement
```java
Statement stmt = conn.createStatement();
// OR
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE age > ?");
pstmt.setInt(1, 25);
```
### Execute the Query
```java
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
// OR
ResultSet rs = pstmt.executeQuery();
```
### Process the ResultSet
```java
while (rs.next()) {
    int id = rs.getInt("id");
    String name = rs.getString("name");
    int age = rs.getInt("age");
    System.out.println(id + ", " + name + ", " + age);
}
```
### Close Resources
```java
rs.close();
stmt.close();
conn.close();
```

# Compare Spring Data JPA vs Hibernate vs JDBC.
## High-Level Overview
| Feature             | Spring Data JPA                                      | Hibernate (JPA Provider)                                | JDBC (Java Database Connectivity)                     |
|---------------------|------------------------------------------------------|----------------------------------------------------------|--------------------------------------------------------|
| Abstraction Level   | High                                                 | Medium                                                   | Low                                                    |
| Boilerplate Code    | Very minimal (just interfaces, annotations)          | Less than JDBC, but still manual mappings                | A lot of boilerplate (SQL, mapping, resources)         |
| Ease of Use         | Easiest (auto methods, query derivation)             | Easier than JDBC, more control than Spring Data          | Verbose and manual                                     |
| Custom Queries      | With `@Query`, JPQL, or native SQL                   | JPQL or native SQL                                       | Full SQL control                                       |
| Entity Management   | Automatic (via Spring + Hibernate)                   | Manual session management or JPA annotations             | No entity model, just raw data handling                |
| Performance Control | Limited (unless you drop to Hibernate level)         | More control (caching, fetching, etc.)                   | Full control                                            |
| Use Case            | Rapid API development, CRUD-heavy apps               | More complex ORM use cases                               | When full SQL control or performance is needed         |

## When to Use What?
| Scenario                                     | Recommended Option         |
|---------------------------------------------|----------------------------|
| Simple CRUD REST API                         | Spring Data JPA            |
| Need more query control & performance tuning | Hibernate                  |
| Need raw SQL, batch operations, or speed     | JDBC                       |
| Working in Spring Boot and want productivity | Spring Data JPA            |
| Complex domain model with DDD                | Hibernate (JPA)            |
| Microservice with fine-tuned queries         | JDBC or MyBatis            |

# Learn how to use ObjectMapper by this example.
## How to Use ObjectMapper.readValue()
This method parses JSON into Java objects.
```java
FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);
```
- resBody: raw JSON string from the HTTP API
- FoodOutlet.class: the top-level Java class that matches the JSON structure

You use this to turn raw API responses into structured Java objects so you can work with them easily.
## How to Use ObjectMapper.writeValueAsString()
This method converts a Java object into a JSON string (i.e., serialization).
```java
String s = objectMapper.writeValueAsString(foodOutlet);
System.out.println(s);
```
This is mostly for debugging or logging what your object looks like as JSON.
## How to Use objectMapper.readTree()
This method parses a JSON string into a JsonNode tree structure — kind of like a lightweight DOM for JSON.
```java
String json = "{\"name\":\"Burger Spot\",\"estimated_cost\":110}";

ObjectMapper objectMapper = new ObjectMapper();
JsonNode root = objectMapper.readTree(json);

String name = root.get("name").asText();
int cost = root.get("estimated_cost").asInt();

System.out.println(name);  // Burger Spot
System.out.println(cost);  // 110
```
Use it when:
- You don’t have a POJO class for the JSON
- You want to read dynamic or partial JSON
- You want to quickly access fields like a map

# What is the serialization and desrialization?
## What is Serialization?
Serialization is the process of converting a Java object into a format that can be stored or transmitted — such as:
- JSON
- XML
- Binary (for files or sockets)
### Example: Java Object → JSON (Serialization)
```java
User user = new User("Alice", 25);
String json = objectMapper.writeValueAsString(user);

System.out.println(json);
// Output: {"name":"Alice","age":25}
```
## What is Deserialization?
Deserialization is the reverse process: converting data (e.g. JSON) back into a Java object.
### Example: JSON → Java Object (Deserialization)
```java
String json = "{\"name\":\"Alice\",\"age\":25}";
User user = objectMapper.readValue(json, User.class);

System.out.println(user.getName());  // Alice
```

# use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].
```java
import java.util.Arrays;

public class AverageUsingStream {
    public static void main(String[] args) {
        int[] numbers = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};

        double average = Arrays.stream(numbers)
                               .average()
                               .orElse(0.0);  // returns 0.0 if array is empty

        System.out.println("Average: " + average);
    }
}
```

# 抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/03_post_pageable 下的代码
✅

# 抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/04_comment 下的代码
✅