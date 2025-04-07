# 1. List all of the Spring data related annotations your learned and explain its usage.
# 2. What is DTO, VO, Payload, PO, model, DAO?

### DTO (Data Transfer Object)
- Used to transfer data between layers (e.g., controller ↔ service).
- Contains only data fields, no business logic.

### 📄 VO (Value Object)
- Immutable object representing a value (e.g., money, coordinates).
- Equality is based on value, not identity.

### Payload
- The actual data sent in a request or response, often used in APIs.
- Usually matches a DTO or JSON structure.

### PO (Persistent Object)
- Maps to database tables (often same as Entity).
- Used by ORM tools (e.g., Hibernate, Entity Framework).

### Model
- General term for business/domain objects.
- May include logic, used across the application.

### DAO (Data Access Object)
- Provides CRUD operations for PO.
- Abstracts and encapsulates access to the database.

# 3. What is @JsonProperty("description_yyds")
It tells Jackson (used for JSON serialization/deserialization) that the field in JSON called "description_yyds" should be mapped to the annotated Java field.
- explicit mapping from json field to java field
```Java
public class Product {
    
    @JsonProperty("description_yyds")
    private String description;

}
```

# 4. Explain the purpose of following dependency?
```xml
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.13.3</version>
        <scope>compile</scope>
    </dependency>
```
This dependency adds the Jackson Databind library to the project. Jackson is a popular Java library used for converting between Java Objects and JSON.

# 5. What is spring-boot-stater?
In Spring Boot, a starter is a convenient dependency descriptor to quickly pull in a curated set of libraries for a specific feature or functionality.

- what dependecies in the below starter? do you know any starters?
    ```XML
    <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    ```
This starter is used to build web applications, including RESTful services using Spring MVC.

`spring-boot-starter-data-jpa`: Spring Data JPA with Hibernate
`mysql-connector-j`: Official JDBC driver for connecting applications to MySQL databases.

# 6. Explain `@RequestMapping(value = "/users", method = RequestMethod.POST)`? could you list CRUD by this style?
This is a string annotation to map http request to specific controller or handler methods. The endpoint is `"/users"` and http request is `POST`. 

Create: `@RequestMapping(value = "/users", method = RequestMethod.POST)`
Read: `@RequestMapping(value = "/users", method = RequestMethod.GET)`
Update: `@RequestMapping(value = "/users", method = RequestMethod.PUT)`
Delete: `@RequestMapping(value = "/users", method = RequestMethod.DELETE)`

# 7. What is ResponseEntity? why do we need it?
Spring uses `ResponseEntity` to represent the entire HTTP response. It includes status code, headers and body. Spring serializes the return object to JSON and returns an HTTP 200 OK by default, but `ResponseEntity` provides more control.
```Java
new ResponseEntity<>(postResponse, HttpStatus.OK);
new ResponseEntity<>(postResponse, HttpStatus.CREATED);
ResponseEntity.ok(postService.getPostById(id));
```

# 8. What is ResultSet in jdbc? and describe the flow how to get data using JDBC
A `ResultSet` is an object that holds the data retrieved from a database after executing a SQL query (usually SELECT). It acts like a cursor or iterator that allows you to read rows of data one at a time.

### 1. Load the JDBC Driver

```java
Class.forName("com.mysql.cj.jdbc.Driver");
```

### 2. Establish a Connection

```java
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/mydb", "username", "password");
```

### 3. Create a Statement or PreparedStatement

#### Using `Statement`:
```java
Statement stmt = conn.createStatement();
```

#### Using `PreparedStatement`:
```java
PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
pstmt.setInt(1, 1001);
```

### 4. Execute the Query

#### With `Statement`:
```java
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
```

#### With `PreparedStatement`:
```java
ResultSet rs = pstmt.executeQuery();
```

### 5. Process the ResultSet

```java
while (rs.next()) {
    int id = rs.getInt("id");
    String name = rs.getString("name");
    System.out.println("ID: " + id + ", Name: " + name);
}
```

### 6. Close Resources

```java
rs.close();
stmt.close();  // Or pstmt.close();
conn.close();
```
# 9. Compare Spring Data JPA vs Hibernate vs JDBC.
| Feature                     | JDBC                                  | Hibernate                                  | Spring Data JPA                             |
|-----------------------------|---------------------------------------|--------------------------------------------|---------------------------------------------|
| **Level of Abstraction**    | Low                                   | Medium                                     | High                                        |
| **Boilerplate Code**        | A lot (manual queries, mapping)       | Less (automated ORM mapping)               | Minimal (declarative interfaces)            |
| **Ease of Use**             | Complex (manual connection handling)  | Easier (ORM, caching, lazy loading)        | Very easy (repository pattern, no SQL)      |
| **Control Over SQL**        | Full control                          | Partial (can use HQL/Native SQL)           | Limited (but supports custom queries)       |
| **Learning Curve**          | Steep                                 | Moderate                                   | Easy (if familiar with Spring and JPA)      |
| **Flexibility**             | High                                  | Moderate                                   | Moderate to Low                             |
| **Transaction Management**  | Manual                                | Built-in                                   | Built-in via Spring                         |
| **Performance Tuning**      | High control                          | Many options (caching, fetch strategies)   | Tied to JPA provider like Hibernate         |
| **Use Case Fit**            | Simple or performance-critical apps   | Standard enterprise apps                   | Spring-based CRUD-heavy apps                |


# 10. Learn how to use ObjectMapper by this example.
https://github.com/TAIsRich/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/exercise/oa/api/FoodOutletJackson.java
```Java
FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);
String s = objectMapper.writeValueAsString(foodOutlet);
objectMapper.readTree() // learn how to use it?
```

# 11. What is the serialization and desrialization?

### Serialization

Serialization is the process of converting an object (or data structure) into a format that can be easily stored or transmitted. For example:
- Converting a Python object into a JSON string
- Turning a Java object into a byte stream
- Encoding data for transmission over a network

**Purpose**: Save to a file, send over a network, or store in a database.

**Examples**:
- In Java: `ObjectOutputStream` writes an object to a file or socket.

---

### Deserialization

Deserialization is the reverse process — converting serialized data back into an object or usable data structure.

**Purpose**: Read stored data, receive and use transmitted data.

**Examples**:
- In Java: `ObjectInputStream` reads the serialized object and reconstructs it.

---

### Security Note

Deserializing data from untrusted sources can be dangerous and lead to security vulnerabilities like remote code execution. Always validate and sanitize incoming data.


# 12. use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].
```Java
int[] numbers = new int[]{20, 3, 78, 9, 6, 53, 73, 99, 24, 32};
double average = Arrays.stream(numbers)
                               .average()
                               .orElse(0.0);
System.out.println("Average: " + average);
```

# 13. 抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/03_post_pageable 下的代码
# 14. 抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/04_comment 下的代码