```java
/*
 * Java Annotations Reference
 * Organized by usage context: Entity, Controller, Service, etc.
 * Keep this file updated as you learn new annotations.
 */

/********************************
 * ENTITY-RELATED ANNOTATIONS *
 ********************************/

// javax.persistence
@Entity
// Marks a class as a JPA entity. It maps to a table in the database.
public class User {

    @Id
    // Marks the primary key of the entity
    private Long id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Specifies the primary key generation strategy
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    // Maps a field to a specific column in the database
    private String username;

    @OneToMany(mappedBy = "user")
    // Defines a one-to-many relationship
    private List<Order> orders;

    // Getters and setters
}

/********************************
 * CONTROLLER-RELATED ANNOTATIONS *
 ********************************/

// org.springframework.web.bind.annotation
@RestController
// Marks a class as a RESTful web service controller
@RequestMapping("/api/users")
// Maps HTTP requests to handler methods
public class UserController {

    @GetMapping("/{id}")
    // Maps HTTP GET requests to a method
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(new User());
    }

    @PostMapping
    // Maps HTTP POST requests to a method
    public ResponseEntity<String> createUser(@RequestBody User user) {
        return ResponseEntity.ok("User created");
    }
}

/********************************
 * SERVICE-RELATED ANNOTATIONS *
 ********************************/

@Service
// Marks a class as a service provider
public class UserService {
    // Business logic here
}

/********************************
 * SPRING BOOT CONFIGURATION *
 ********************************/

@SpringBootApplication
// Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}

/********************************
 * VALIDATION ANNOTATIONS *
 ********************************/

// javax.validation.constraints
@NotNull
// Ensures the field is not null
@Size(min = 3, max = 20)
// Ensures the string has a length between 3 and 20
private String username;

/********************************
 * SECURITY ANNOTATIONS *
 ********************************/

@PreAuthorize("hasRole('ADMIN')")
// Method-level security to restrict access based on roles
public void deleteUser(Long id) {
    // Only accessible by ADMIN
}
```