1. List all of the Spring data related annotations your learned and explain its usage.
1. @Entity
Marks a class as a JPA entity, i.e., it maps to a database table.
@Entity
public class User {
    @Id
    private Long id;
    private String name;
}
2. @Table
Specifies the table name in the database (optional if class name == table name).
@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;
}
3. @Id
Marks a field as the primary key.
@Id
private Long id;
4. @GeneratedValue
Defines how the primary key is generated (AUTO, IDENTITY, SEQUENCE, etc.).
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
5. @Column
Customize column mapping (name, length, nullable, etc.).
@Column(name = "full_name", nullable = false)
private String name;
6. @Repository
Marks the interface/class as a DAO (Data Access Object) and allows Spring to auto-detect and implement it.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
2. What is DTO, VO, Payload, PO, model, DAO?
DTO: data transfer Object, transfer data between Controller and Service layers, no logic, only field, getter/setter, to avoid exposing internal entities to outside.
VO: Value Object, immutable object that represents a value like Money, address. For better modeling, immutability.
Payload: the actual data sent in HTTP requests/responses, used in REST APIs to bind to JSON/XMLbodies. For @RequestBody or @ResponseBody mapping.
PO: Persistent Object, Entity, Maps to database table(@Entity), contains fields matching DB columns, to persist data using ORM tools like JPA/Hibernate.
Model: used for MVC view models or domain objects, can be DTO, Entity, or domain object depending on context.
DAO: data access object, abstracts interaction with the data source (JPA repository), Interface or class that handles DB operations. To keep DB logic separate from business logic.
3. What is @JsonProperty("description_yyds")
A Jackson annotation used to map a JSON property named "description_yyds" to a Java field, method, or constructor parameter.
    Used during serialization and deserialization.
    Overrides the default name Jackson would use.
    Useful when JSON field names don’t match Java naming conventions.
4. Explain the purpose of following dependency?
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.3</version>
    <scope>compile</scope>
</dependency>
This Maven dependency adds Jackson Databind to our project, enabling powerful JSON serialization and deserialization in Java.
    Provides the core functionality to convert Java objects to JSON (serialization) and JSON to Java objects (deserialization).
    Combines the streaming API (jackson-core) and tree model (jackson-annotations) into a high-level data-binding API.


5. What is spring-boot-stater?
1. what dependecies in the below starter? do you know any starters?
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
A Spring Boot starter is a pre-configured set of dependencies that helps to get started quickly with a particular feature or tech stack. Instead of manually importing 5–10 libraries, we just use one starter, and it pulls them all in automatically.

6. Explain @RequestMapping(value = "/users", method = RequestMethod.POST) ? could you list CRUD by this style?
This annotation is used in Spring MVC to map HTTP requests to a controller method.
value = "/users": URL path to match
method = RequestMethod.POST: Only matches HTTP POST requests
It maps POST /users to the annotated method—typically for creating a user.
Create	POST	/users	@RequestMapping(value = "/users", method = RequestMethod.POST) = @PostMapping("/users")
Read All	GET	/users	@RequestMapping(value = "/users", method = RequestMethod.GET) = @GetMapping("/users)
Read One	GET	/users/{id}	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET) = @GetMapping("/users/{id}")
Update	PUT	/users/{id}	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT) = @PutMapping("/users/{id}")
Delete	DELETE	/users/{id}	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE) = @DeleteMapping("/users/{id}")
7. What is ResponseEntity? why do we need it?
new ResponseEntity<>(postResponse, HttpStatus.OK);
new ResponseEntity<>(postResponse, HttpStatus.CREATED);
ResponseEntity.ok(postService.getPostById(id));
ResponseEntity is a wrapper for an HTTP response. It lets us control:
The body (T)
The status code (200 OK, 201 CREATED, etc.)
Optional headers (like Location, Content-Type, etc.)
sometimes returning object with more detail like status code, custom headers, empty body with a specific status and http response based on conditions.
8. What is ResultSet in jdbc? and describe the flow how to get data using JDBC
ResultSet is a table-like object in Java that holds the data returned from a SQL query. We use it to read rows from the database, one by one.
1.Load the Driver
Class.forName("com.mysql.cj.jdbc.Driver");
2.Establish Connection
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/mydb", "user", "password");
3.Create Statement
Statement stmt = conn.createStatement();
4.Execute Query
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
5.Process ResultSet
while (rs.next()) {
    int id = rs.getInt("id");
    String name = rs.getString("name");
    // Use the data as needed
}
6.Close Resources
rs.close();
stmt.close();
conn.close();
9. Compare Spring Data JPA vs Hibernate vs JDBC.
JDBC: Raw, powerful, verbose. We write everything. Great for fine-tuned performance but painful for large projects.
Hibernate: Full-featured ORM. Handles mapping, caching, lazy loading, etc. Needs more setup but powerful.
Spring Data JPA: Abstraction layer. Auto-generates repos and queries. Great for rapid dev with minimal code.
10. Learn how to use ObjectMapper by this example.
1. https://github.com/TAIsRich/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/exercise/oa/api/FoodOutletJackson.java
FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);
String s = objectMapper.writeValueAsString(foodOutlet);
objectMapper.readTree() // learn how to use it?
11. What is the serialization and desrialization?
1. https://hazelcast.com/glossary/serialization/
Serialization: the process of converting a Java object into a byte stream(Java object → stream of bytes), so it can be:
Saved to a file
Sent over a network
Stored in memory (e.g. Hazelcast, Redis)
Used by another system
Deserialization: It takes the byte stream and reconstructs the original Java object.(stream of bytes → Java object)
12. use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].
public class StreamAve{
    public static void main(String[] args) {
        int[] nums = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};
        Double ave = Arrays.stream(nums).average().orElse(0);
        System.out.println("Average: " + ave);
    }
}
13. 抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/03_post_pageable 下的代码
14. 抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/04_comment 下的代码