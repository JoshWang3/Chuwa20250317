# hw7
### 1. List all of the Spring data related annotations your learned and explain its usage.
1. `@Repository`  
    Marks a class as a Data Access Object (DAO). Enables Spring to automatically detect and create an implementation for data access layers.
    ```java
    @Repository
    public interface UserRepository extends JpaRepository<User, Long> {}
    ```
2. `@Entity`  
    Marks a class as a JPA entity (a table in the database).
    ```java
    @Entity
    public class User {
        @Id
        private Long id;
    }
    ```
3. `@Table`
    Specifies the name of the table in the database.
    ```java
    @Entity
    @Table(name = "users")
    public class User {
        @Id
        private Long id;
    }
    ```
4. `@Id`  
    Specifies the primary key of an entity.
    ```java
    @Id
    private Long id;
    ```
5. `@Column`  
    Maps a field to a specific column in the table.
    ```java
    @Column(name = "name", nullable = false)
    private String name;
    ```
6. `@CreationTimestamp`  
    Automatically sets the timestamp when the entity is first persisted (inserted into the database).
    ```java
    @CreationTimestamp
    private LocalDateTime createdDateTime;
    ```
7. `@UpdateTimestamp`  
    Automatically updates the timestamp whenever the entity is updated (any change is saved to the DB).
    ```java
    @UpdateTimestamp
    private LocalDateTime updatedDateTime;
    ```
### 2. What is DTO, VO, Payload, PO, model, DAO?
1. DTO (Data Transfer Object)  
    Used to transfer data between layers, especially between the backend and frontend. Contains only data (fields + getters/setters)
    ```java
    public class UserDTO {
        private String name;
        private String email;
    }
    ```
2. VO (Value Object)  
    An immutable object that represents a value with equality based on content, not identity.
    ```java
    public class AddressVO {
        private final String street;
        private final String city;

        public AddressVO(String street, String city) {
            this.street = street;
            this.city = city;
        }
    }
    ```
3. Payload  
    A general term referring to data sent in a request or response body, usually for APIs. Not a specific Java class or pattern.
    ```json
    {
        "username": "zeliang",
        "password": "abc123"
    }
    ```
4. PO (Persistent Object)  
    Refers to an entity class that directly maps to a database table. Annotated with `@Entity`.
    ```java
    @Entity
    public class UserPO {
        @Id
        private Long id;
        private String name;
    }
    ```
5. Model  
    A general-purpose term used to describe a domain object, especially in MVC or REST architecture. Could be a DTO, Entity, or form object.
    ```java
    public class Product {
        private String name;
        private BigDecimal price;
    }
    ```
6. DAO (Data Access Object)  
    Handles all database interactions for a specific entity or table. Abstracts CRUD operations.
    ```java
    @Repository
    public interface PostRepository extends JpaRepository<Post, Long> {
    }
    ```
### 3. What is `@JsonProperty("description_yyds")`
`@JsonProperty("...")` maps a Java field or getter/setter to a specific JSON property name.
```java
public class Product {
    @JsonProperty("description_yyds")
    private String descriptionYyds;
}
```
- When converting **Java -> JSON**, `descriptionYyds` will show up as `description_yyds`.  
- When converting **JSON -> Java**, it will map `description_yyds` back to the `descriptionYyds` field.
### 4. Explain the purpose of following dependency?
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.3</version>
    <scope>compile</scope>
</dependency>
```
It is the core library for converting Java objects to JSON and vice versa. This dependency is required at compile time and runtime.  
JSON:
```json
{
    "name": "Zeliang",
    "age": 25
}
```
Java model:
```java
public class User {
    private String name;
    private int age;
}
```
Jackson usage:
```java
ObjectMapper mapper = new ObjectMapper();
User user = mapper.readValue(jsonString, User.class);
```
### 5. What is spring-boot-stater?
#### what dependecies in the below starter? do you know any starters?
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
- A Spring Boot Starter is a convenient dependency package that bundles together commonly used libraries and configurations. It auto-includes the necessary dependencies for common tasks.
- The dependecies in Spring Boot Starter:
    - `spring-web`
    - `spring-webmvc`
    - `spring-boot`
    - `spring-boot-autoconfigure`
    - `jackson-databind`
    - `validation-api & hibernate-validator`
    - `tomcat`
- Other starters:
    - `spring-boot-starter-data-jpa`: JPA + Hibernate + Spring Data for DB access
    - `spring-boot-starter-security`: Spring Security setup
    - `spring-boot-starter-thymeleaf`: For server-side HTML rendering with Thymeleaf
    - `spring-boot-starter-test`: Testing
### 6. Explain `@RequestMapping(value = "/users", method = RequestMethod.POST)`? could you list CRUD by this style?
- `@RequestMapping` is the annotation for general-purpose request mapping. `value = "/users"` defines the URL path it handles. `method = RequestMethod.POST` sets the HTTP method to "POST". So this annotation means: Handle POST requests sent to `/users`.
- CRUD list:
    - Create (POST): `@RequestMapping(value = "/users", method = RequestMethod.POST)`
    - Read All (GET): `@RequestMapping(value = "/users", method = RequestMethod.GET)`
    - Read by ID (GET): `@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)`
    - Update (PUT): `@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)`
    - Delete (DELETE): `@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)`
### 7. What is ResponseEntity? why do we need it?
```java
new ResponseEntity<>(postResponse, HttpStatus.OK);
new ResponseEntity<>(postResponse, HttpStatus.CREATED);
ResponseEntity.ok(postService.getPostById(id));
```
- `ResponseEntity<T>` is a generic class provided by Spring that represents the full HTTP response. It lets us control: HTTP status code, HTTP headers and HTTP body.
- Why? It gives us **full control over the response**, unlike just returning an object (which always gives 200 OK by default).
### 8. What is ResultSet in jdbc? and describe the flow how to get data using JDBC
- `ResultSet` is a Java object that holds the result of a SQL query executed using JDBC. It's like a cursor that points to one row of data at a time in your result.
- The flow to get data using JDBC:
    1. Connect to DB via `DriverManager.getConnection()`
    2. Create SQL statement
    3. Execute query using `executeQuery()`
    4. Process data via `ResultSet`
    5. Close everything
    ```java
    import java.sql.*;

    public class JdbcExample {
        public static void main(String[] args) {
            // Step 1: Establish connection
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "user", "password")) {
                // Step 2: Create a Statement
                String sql = "SELECT * FROM users";
                PreparedStatement stmt = conn.prepareStatement(sql);
                // Step 3: Execute the query
                ResultSet rs = stmt.executeQuery();
                // Step 4: Process the result
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    System.out.println("ID: " + id + ", Name: " + name);
                }
                // Step 5: Close resources
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    ```
### 9. Compare Spring Data JPA vs Hibernate vs JDBC.
- Spring Data JPA: Use when you want to build CRUD-heavy apps quickly with minimum code.
- Hibernate: Use when you want powerful ORM features and don’t want to write SQL manually.
- JDBC: Use when you want maximum control or performance tuning with raw SQL.

| Feature | JDBC | Hibernate | Spring Data JPA |
| --- | --- | --- | --- |
| Abstraction Level | Low | Medium | High |
| Required SQL | Yes | Optional (HQL or SQL) | Rarely (auto query gen) |
| Boilerplate Code | High | Moderate | Very Low |
| Learning Curve | Low | Medium | Easy for basic use |
| Control/Flexibility | Full | Medium | Low (can override with JPQL) |
| Spring Integration | Manual | Supported | Excellent (built-in) |
| Best For | Fine-grained control | Advanced ORM use | Rapid CRUD development |
### 10. Learn how to use ObjectMapper by this example
#### https://github.com/TAIsRich/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/exercise/oa/api/FoodOutletJackson.java
```java
FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);
String s = objectMapper.writeValueAsString(foodOutlet);
objectMapper.readTree() // learn how to use it?
```
```java
FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);
String s = objectMapper.writeValueAsString(foodOutlet);
JsonNode jsonNode = objectMapper.readTree(s);
int page = jsonNode.get("page").asInt();
System.out.println("page = " + page);   // page = 1
```
### 11. What is the serialization and desrialization?
#### https://hazelcast.com/glossary/serialization/
- **Serialization** is the process of converting a Java object into a byte stream (or into a format such as JSON, XML, etc.) so it can be easily stored or transmitted.
```java
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationExample {
    public static void main(String[] args) throws Exception {
        Person person = new Person("John", 30);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(person);
        System.out.println(jsonString); // Output: {"name":"John","age":30}
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```
- **Deserialization** is the process of converting a byte stream (or a JSON string, XML, etc.) back into a Java object.
```java
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeserializationExample {
    public static void main(String[] args) throws Exception {
        String jsonString = "{\"name\":\"John\",\"age\":30}";
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(jsonString, Person.class);
        System.out.println(person.getName() + " : " + person.getAge()); // Output: John : 30
    }
}

class Person {
    private String name;
    private int age;

    public String getName() { return name; }
    public int getAge() { return age; }
}
```
### 12. use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].
```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};
        double average = Arrays.stream(numbers)
                .average()
                .orElse(Double.NaN);
        System.out.println("average = " + average); // Output: average = 39.7
    }
}
```
### 13. 抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/03_post_pageable 下的代码
### 14. 抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/04_comment 下的代码