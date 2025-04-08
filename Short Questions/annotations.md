# Spring Annotations
## Entity Layer Annotations (JPA/Hibernate)
These annotations define how a Java class maps to a relational database.
@Entity
- Usage: Declares a class as a JPA entity (i.e., mapped to a table in the database).
- Understanding: This tells Spring/Hibernate, "this class should be persisted in the database."
  ```java
  @Entity
  public class Post {
      // fields
  }
  ```
@Table(name = "table_name")
- Usage: Specifies the database table name explicitly.
- Understanding: Optional. If not used, Hibernate will use the class name as the table name.
  ```java
  @Table(name = "posts")
  public class Post {
      // fields
  }
  ```

@Id
- Usage: Marks a field as the primary key.
- Understanding: It's like putting a crown on this field — it's the unique identifier for the entity.
  ```java
  @Id
  private Long id;
  ```

@GeneratedValue(strategy = GenerationType.IDENTITY)
- Usage: Tells JPA to auto-generate the primary key.
- Understanding: Hands off — the database will take care of this ID.
  ```java
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  ```

@Column(name = "column_name", nullable = false)
- Usage: Maps a field to a specific column and sets constraints.
- Understanding: Customize how each field connects to a column in the DB
  ```java
  @Column(name = "title", nullable = false)
  private String title;
  ```

@UpdateTimestamp
Usage: Automatically updates the timestamp whenever the entity is updated.
Understanding: Useful for tracking changes to records with minimal effort.
  ```java
  @UpdateTimestamp
  private LocalDateTime lastUpdated;
  ```

@ManyToOne
- Usage: Creates a many-to-one relationship between entities.
- Understanding: Many Post entities can belong to one User.
The Post holds the foreign key (user_id) to connect back to the User.
```java
@ManyToOne
@JoinColumn(name = "user_id") // optional: specifies the column name in the DB
private User author;
```

@OneToMany
- Usage: Defines a one-to-many relationship between entities.
- Understanding: One Post can have many Comment entities.
The Comment side holds the foreign key, so this side is inverse.
```java
@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Comment> comments = new ArrayList<>();
```

@ManyToMany
- Usage: Establishes a many-to-many relationship between entities.
- Understanding: Many Student entities can enroll in many Course entities, and vice versa.
A join table is automatically created.
```java
@ManyToMany
@JoinTable(
    name = "student_course",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id")
)
private List<Course> courses;
```

@JoinColumn
- Usage: Specifies the foreign key column for relationships.
- Understanding: Helps define how two tables are joined in a relationship.
```java
@ManyToOne
@JoinColumn(name = "user_id")
private User user;
```

## Controller Layer Annotations (Spring MVC)
These annotations define how HTTP requests are routed and handled.
@RestController
  - This combines @Controller and @ResponseBody.
    - @Controller: tells Spring this class handles web requests.
    - @ResponseBody: tells Spring to automatically serialize the return value to JSON.
  - Usage: Marks a class as a REST controller.
  - Understanding: Combines @Controller + @ResponseBody, meaning every method returns JSON.
    ```java
    @RestController
    public class PostController {
        // endpoints
    }
    ```

@RequestMapping("/api/v1")
  - sets the base URL for all endpoints in this controller. All mappings inside this class will start with `/api/v1`.
  - Usage: Base path for all endpoints in the controller.
  - Understanding: Organizes endpoints with a shared root path.
  ```java
  @RequestMapping("/api/v1/posts")
  public class PostController {
      // endpoints
  }
  ```
  
@GetMapping("/{id}")
- Usage: Handles HTTP GET requests for the given path.
- Understanding: Shortcut for @RequestMapping(method = RequestMethod.GET).
  ```java
  @GetMapping("/{id}")
  public Post getPost(@PathVariable Long id) {
      return postService.findById(id);
  }
  ```

@GetMapping, @PostMapping, @PutMapping, @DeleteMapping
- Usage: Shorthand for HTTP methods (GET, POST, PUT, DELETE).
- Understanding: Maps HTTP requests to Java methods by method type.
  ```java
  @GetMapping("/posts")
  public List<PostDto> getPosts() { }
  ```

@PathVariable
- Usage: Binds a URI variable to a method parameter.
- Understanding: Extracts {id} from the URL and assigns it to a method parameter.
```java
  @GetMapping("/{id}")
  public Post getPost(@PathVariable Long id) {
      return postService.findById(id);
  }
  ```

@RequestBody
- Usage: Binds the body of the HTTP request to an object.
- Understanding: Automatically deserializes JSON from the request body into a Java object.
  ```java
  @PostMapping("/posts")
  public PostDto create(@RequestBody PostDto postDto) { }
  ```

## Service-related Annotations (Spring Core)
@Service
- Usage: Marks a class as a service component in the service layer.
- Understanding: Indicates that the class holds business logic and should be managed by Spring.
  ```java
  @Service
  public class PostServiceImpl implements PostService { }
  ```

@Autowired
- Usage: Automatically injects dependencies.
- Understanding: Lets Spring resolve and inject beans into dependent components.
  ```java
  @Autowired
  private PostRepository postRepository;
  ```

## Repository-related Annotations (Spring Data)
@Repository
- Usage: Marks the class as a Spring Data Repository (DAO layer).
- Understanding: Indicates that the interface handles data access logic and should be picked up by Spring.
  ```java
  @Repository
  public interface PostRepository extends JpaRepository<Post, Long> { }
  ```