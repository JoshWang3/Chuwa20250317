# Spring Boot RUD Homework

## 1. Spring Data Annotations

### @Entity
- Marks a class as an entity that will be mapped to a database table.
### @Id
- Specifies the primary key of an entity.
### @Table
- Specifies the table name in the database for the entity.
### @GeneratedValue
- Defines the strategy for primary key generation.
### @Column
- Specifies the column name and configuration in the database.

### @Repository
- Marks a class as a Data Access Object (DAO).
---
## 2. Data Structures
- **DTO (Data Transfer Object)**: An object that carries data between processes.
- **VO (Value Object)**: An object that contains values, often immutable.
- **Payload**: Data sent in the body of a request or response.
- **PO (Persistent Object)**: An object mapped to a database record.
- **Model**: Represents the data and business logic.
- **DAO (Data Access Object)**: An object that provides an abstract interface to some type of database.
---
## 3. @JsonProperty
- `@JsonProperty("description_yyds")`: Annotation used to map JSON property names to Java object fields.
---
## 4. Spring Boot Dependencies
- The Jackson Databind dependency is used for JSON processing in Spring Boot.

- It allows serialization and deserialization of Java objects to and from JSON.

- Commonly used with Spring Boot for RESTful APIs to handle JSON responses.
---
## 5. Spring Boot Starter
- A **starter** in Spring Boot is a set of dependencies grouped together to make development easier.
### Example explanation:
- The **spring-boot-starter-web** is a key dependency in Spring Boot for building web applications, including RESTful services.

### Other Common Spring Boot Starters:

1. **spring-boot-starter-data-jpa**
   - For database access using JPA and Hibernate.

2. **spring-boot-starter-security**
   - Adds Spring Security for authentication and authorization.

3. **spring-boot-starter-test**
   - Includes testing libraries like JUnit, Mockito, and AssertJ.
---
## 6. @RequestMapping in Spring Boot

- The `@RequestMapping` annotation is used to map HTTP requests to handler methods in Spring MVC.

```java
@RequestMapping(value = "/users", method = RequestMethod.POST)
public ResponseEntity<String> createUser(@RequestBody User user) {
    return new ResponseEntity<>("User created", HttpStatus.CREATED);
}
```
### Explanation:
- @RequestMapping: Annotation to map web requests to specific handler methods.

- value: Specifies the URL pattern (e.g., /users).

- method: Specifies the HTTP method (e.g., RequestMethod.POST).
---
## 7. ResponseEntity in Spring Boot

### What is ResponseEntity?
`ResponseEntity` is a generic wrapper class in Spring Boot used to represent the entire HTTP response. It allows you to:
- Set the HTTP status code.
- Add custom headers.
- Include a response body.

### Why Do We Need ResponseEntity?
- **Flexible HTTP Responses:** You can customize the HTTP status, headers, and body.
- **Error Handling:** Easily return error codes and messages.
- **Full Control:** Allows setting response details in one object.
- **Integration:** Works well with REST APIs to standardize responses.
---
## 8. ResultSet in JDBC

### What is ResultSet?
`ResultSet` is an interface in Java used to store the result of a database query executed using JDBC (Java Database Connectivity). It acts as a pointer to the retrieved data and allows iterating through rows returned from the SQL query.

### Flow:

1. **Load the JDBC Driver:**
```java
Class.forName("com.mysql.cj.jdbc.Driver");
```
2. **Establish a Database Connection:**
```java
Connection connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/mydb", "username", "password");
```
3. **Create a Statement:**
```java
Statement statement = connection.createStatement();
```
4. **Execute a Query:**
```java
ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
```
5. **Process the ResultSet:**
```java
while (resultSet.next()) {
    int id = resultSet.getInt("id");
    String name = resultSet.getString("name");
    System.out.println("ID: " + id + ", Name: " + name);
}
```
6. **Close the Resources:**
```java
resultSet.close();
statement.close();
connection.close();
```
---
## 9. Comparison of Spring Data JPA, Hibernate, and JDBC

### 1. Overview:

| Feature                    | Spring Data JPA                          | Hibernate                                   | JDBC                                  |
|---------------------------|------------------------------------------|---------------------------------------------|----------------------------------------|
| Level of Abstraction       | High                                      | Medium                                       | Low                                     |
| Approach                  | Object-Relational Mapping (ORM)            | ORM Framework                                | Raw SQL                                 |
| Data Access API           | Uses JPA (Java Persistence API)            | Uses Hibernate-specific APIs                 | Uses SQL directly                       |
| Configuration             | Automatic configuration via Spring Boot   | Requires manual configuration                | Manual configuration required           |
| Ease of Use               | Easy, with built-in CRUD methods           | Medium, requires annotations and mapping      | Low-level, requires writing SQL queries |
| Performance               | Good for complex queries, caching support | Good with caching and lazy loading            | Fast for simple, raw SQL operations     |
| Transaction Management    | Integrated with Spring's transaction API  | Integrated with Spring and Hibernate TX API   | Manually handled                        |
| Query Language            | JPQL (Java Persistence Query Language)    | HQL (Hibernate Query Language)                | SQL                                     |
| Caching                   | Built-in caching support                  | Built-in (Second-Level Cache, Query Cache)    | No caching, needs manual implementation |
| Learning Curve            | Low to moderate                           | Moderate to high                             | Moderate                                 |

---

## 11. Serialization and Deserialization

### Serialization:
Serialization is the process of converting a Java object into a byte stream so that it can be:
- Stored in a file or database.
- Transmitted over a network.
- Preserved for future use.
### Deserialization: 
Deserialization is the process of converting a byte stream back into a Java object. It is the reverse of serialization.
---

## 12.
```java
import java.util.Arrays;

public class StreamAverage {
    public static void main(String[] args) {
        int[] numbers = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};

        double average = Arrays.stream(numbers)  
                .average()                       
                .orElse(0);                      

        System.out.println("Average: " + average);
    }
}
```