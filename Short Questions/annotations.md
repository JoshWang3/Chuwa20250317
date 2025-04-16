# Controller Annotations

1. `RestController`
   - Used for indicating a Spring MVC controller class.
   - Example:
     ```java
     @RestController
     public class MyController {
         @GetMapping("/helloWorld")
         public String sayHello() {
             return "Hello, World!";
         }
     }

     ```
2. `@RequestMapping()` & `<Method>Mapping`
   - Defines base URL path for a controller. Maps HTTP requests to handler methods.
   - Example:
     ```Java
     @RestController
     @RequestMapping("/api/v1")
     public class PostController {
         @Autowired
         public PostServiceImpl postServiceImpl;

         @PostMapping("/posts")
         public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
             return ResponseEntity.ok(postServiceImpl.createPost(postDTO));
         }
     }
     ```
3. `@RequestBody`
   - Used on a method parameter to bind HTTP request body to a Java object.
   - Example: See above example

# Dependency Injection Annotations

1. `@Autowired`
   - Inject dependent beans automatically by type
   - Example:
     ```Java
     @Service
     public class MyService {
         public String process() {
             return "Processed";
         }
     }

     @RestController
     public class MyController {

         @Autowired
         private MyService myService;

         @GetMapping("/process")
         public String process() {
             return myService.process();
         }
     }
     ```

# Component Scanning and Configuration Annotations

1. `@Service`

   - Mark a class as a service provider. The class contains business logic and should be managed as a service component.
   - Example:
     ```Java
     @Service
     public class PostServiceImpl implements PostService {
         @Override
         public PostDTO createPost(PostDTO postDTO) {
             System.out.println("Created post: " + postDTO.getTitle());
             return null;
         }
     }
     ```
2. `@Repository`

   - Marks a class as a repository, which is used for DAO layers. Indicates that the class provides mechanisms for storage, retrieval, search, update and delete operations on objects.

# Entity Annotations

1. `@Entity`

   - Mark a class as a JPA entity, maps to a database table.
2. `@Table`

   - Table-related metadata, specifies name and constraints
3. `@UniqueConstraint`

   - Specifies that a column or group of columns must be unique across all rows.
   - Example:
     ```Java
       @Entity
       @Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
       public class User {
           // fields and methods
       }
     ```
4. `@Id`

   - Marks primary key
5. `@GeneratedValue`

   - Specifies the strategy for auto-generating primary key values
6. `@Column`

   - Specify comlumn details like naming, nullability...
7. `@CreationTimestamp`

   - Automatically sets the timestamp when the entity is created.
8. `@UpdateTimestamp`

   - Automatically updates the timestamp when the entity is updated.
9. Relationships:

   - `@OneToOne`
   - Defines a one-to-one relationship between two entities.
   - Example:

     ```java
       @Entity
       public class User {
           @OneToOne
           @JoinColumn(name = "profile_id")
           private Profile profile;
       }
     ```
   - `@OneToMany`
   - `@ManyToOne`
   - `@ManyToMany`

### Serialization and JSON Mapping Annotations

1. `@JsonProperty`

- **Usage**: Applied to fields or getter/setter methods in a class.
- **Explanation**: Specifies the JSON property name used during serialization/deserialization. Useful when the Java field name does not match the JSON key.
- **Example**:
  ```java
  public class User {
      @JsonProperty("user_name")
      private String username;
  }
  ```
