# Java & Spring Boot Annotations Reference

This document serves as a comprehensive guide to Java and Spring Boot annotations, organized by their purpose and usage. It will be updated as new annotations are discovered or learned.

## Table of Contents

- [Java Core Annotations](#java-core-annotations)
- [Spring Core Annotations](#spring-core-annotations)
- [Spring Web Annotations](#spring-web-annotations)
- [Spring Data Annotations](#spring-data-annotations)
- [Spring Security Annotations](#spring-security-annotations)
- [Spring Test Annotations](#spring-test-annotations)
- [Spring Boot Annotations](#spring-boot-annotations)
- [Bean Validation Annotations](#bean-validation-annotations)
- [JPA Annotations](#jpa-annotations)

## Java Core Annotations

### `@Override`

- **Usage**: Indicates that a method is intended to override a method in a superclass.
- **Understanding**: Helps catch errors at compile time if the method doesn't actually override a superclass method.

```java
@Override
public String toString() {
    return "CustomObject: " + this.name;
}
```

### `@Deprecated`

- **Usage**: Marks a method or class as deprecated, suggesting it should no longer be used.
- **Understanding**: Warns developers that the annotated element may be removed in future versions.

```java
@Deprecated
public void oldMethod() {
    // Implementation
}
```

### `@SuppressWarnings`

- **Usage**: Suppresses specific compiler warnings.
- **Understanding**: Tells the compiler to ignore certain warnings in the annotated element.

```java
@SuppressWarnings("unchecked")
public List<String> getList() {
    return (List<String>) oldMethod();
}
```

### `@FunctionalInterface`

- **Usage**: Indicates that an interface is intended to be a functional interface (has exactly one abstract method).
- **Understanding**: Helps catch errors at compile time if more than one abstract method is added.

```java
@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b);
}
```

## Spring Core Annotations

### `@Component`

- **Usage**: Marks a class as a Spring component, making it eligible for auto-detection.
- **Understanding**: Root annotation that indicates a class is a Spring-managed component.

```java
@Component
public class UserService {
    // Implementation
}
```

### `@Service`

- **Usage**: Specialized form of `@Component` for the service layer.
- **Understanding**: Indicates the class holds business logic and service operations.

```java
@Service
public class UserServiceImpl implements UserService {
    // Service implementation
}
```

### `@Repository`

- **Usage**: Specialized form of `@Component` for data access objects.
- **Understanding**: Indicates the class deals with database operations and provides exception translation.

```java
@Repository
public class UserRepositoryImpl implements UserRepository {
    // Repository implementation
}
```

### `@Controller`

- **Usage**: Specialized form of `@Component` for presentation layer controllers.
- **Understanding**: Indicates the class handles web requests.

```java
@Controller
public class UserController {
    // Controller implementation
}
```

### `@Configuration`

- **Usage**: Indicates that a class declares one or more `@Bean` methods.
- **Understanding**: Used for Java-based configuration as an alternative to XML configuration.

```java
@Configuration
public class AppConfig {
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
}
```

### `@Bean`

- **Usage**: Indicates that a method produces a bean to be managed by Spring.
- **Understanding**: Used within `@Configuration` classes to define beans explicitly.

```java
@Bean
public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost:3306/mydb");
    dataSource.setUsername("root");
    dataSource.setPassword("password");
    return dataSource;
}
```

### `@Autowired`

- **Usage**: Marks a constructor, field, or setter method to be autowired by Spring's dependency injection.
- **Understanding**: Tells Spring to inject a dependency automatically.

```java
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

### `@Value`

- **Usage**: Injects values from properties files or environment variables.
- **Understanding**: Used for injecting external configuration into Spring beans.

```java
@Service
public class EmailService {
    @Value("${mail.server.host}")
    private String mailServerHost;
    
    @Value("${mail.server.port:25}")
    private int mailServerPort;
}
```

### `@Scope`

- **Usage**: Defines the scope of a bean.
- **Understanding**: Controls the lifecycle and visibility of a bean.

```java
@Component
@Scope("prototype")
public class PrototypeBean {
    // This bean will create a new instance each time it's requested
}
```



## Spring Web Annotations

### `@RestController`

- **Usage**: Combination of `@Controller` and `@ResponseBody`, used for RESTful web services.
- **Understanding**: Indicates that the return value of methods should be bound to the web response body.

```java
@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        // Return user object which will be converted to JSON/XML
    }
}
```

### `@RequestMapping`

- **Usage**: Maps HTTP requests to handler methods of MVC and REST controllers.
- **Understanding**: Defines the URL pattern and HTTP method that a controller method handles.

```java
@Controller
public class HomeController {
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}
```

### `@GetMapping`

- **Usage**: Specialized version of `@RequestMapping` for HTTP GET requests.
- **Understanding**: Shortcut annotation for `@RequestMapping(method = RequestMethod.GET)`.

```java
@RestController
public class UserController {
    @GetMapping("/users")
    public List<User> getAllUsers() {
        // Return all users
    }
}
```

### `@PostMapping`

- **Usage**: Specialized version of `@RequestMapping` for HTTP POST requests.
- **Understanding**: Shortcut for `@RequestMapping(method = RequestMethod.POST)`.

```java
@RestController
public class UserController {
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        // Create and return user
    }
}
```

### `@PutMapping`

- **Usage**: Specialized version of `@RequestMapping` for HTTP PUT requests.
- **Understanding**: Shortcut for `@RequestMapping(method = RequestMethod.PUT)`.

```java
@RestController
public class UserController {
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        // Update and return user
    }
}
```

### `@DeleteMapping`

- **Usage**: Specialized version of `@RequestMapping` for HTTP DELETE requests.
- **Understanding**: Shortcut for `@RequestMapping(method = RequestMethod.DELETE)`.

```java
@RestController
public class UserController {
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        // Delete user
    }
}
```

### `@PatchMapping`

- **Usage**: Specialized version of `@RequestMapping` for HTTP PATCH requests.
- **Understanding**: Shortcut for `@RequestMapping(method = RequestMethod.PATCH)`.

```java
@RestController
public class UserController {
    @PatchMapping("/users/{id}")
    public User partialUpdateUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        // Partially update and return user
    }
}
```

### `@PathVariable`

- **Usage**: Binds a method parameter to a URI template variable.
- **Understanding**: Extracts values from the URI path.

```java
@GetMapping("/users/{id}")
public User getUser(@PathVariable("id") Long userId) {
    // Get user by ID
}
```

### `@RequestParam`

- **Usage**: Binds a method parameter to a web request parameter.
- **Understanding**: Extracts values from the query string.

```java
@GetMapping("/users")
public List<User> searchUsers(@RequestParam(required = false) String name, 
                              @RequestParam(defaultValue = "0") int page) {
    // Search users with the given criteria
}
```

### `@RequestBody`

- **Usage**: Binds the HTTP request body to a method parameter.
- **Understanding**: Converts the incoming HTTP request body to a Java object.

```java
@PostMapping("/users")
public User createUser(@RequestBody User user) {
    // Create and return user
}
```

### `@ResponseBody`

- **Usage**: Indicates that a method return value should be bound to the web response body.
- **Understanding**: Converts the returned object to the response body (bypassing view resolution).

```java
@Controller
public class UserController {
    @GetMapping("/users/{id}")
    @ResponseBody
    public User getUser(@PathVariable Long id) {
        // Return user as JSON/XML
    }
}
```

### `@ResponseStatus`

- **Usage**: Marks a method or exception class with a status code and reason that should be returned.
- **Understanding**: Sets the HTTP status of the response.

```java
@PostMapping("/users")
@ResponseStatus(HttpStatus.CREATED)
public void createUser(@RequestBody User user) {
    // Create user
}
```

### `@ExceptionHandler`

- **Usage**: Handles exceptions thrown by request handling methods.
- **Understanding**: Defines methods that handle specific exceptions.

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
```

### `@CrossOrigin`

- **Usage**: Enables cross-origin resource sharing (CORS) on specific handler methods or controller classes.
- **Understanding**: Allows requests from different origins to access the resources.

```java
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://example.com", maxAge = 3600)
public class UserController {
    @GetMapping("/users")
    public List<User> getAllUsers() {
        // Return all users
    }
}
```

## Spring Data Annotations

### `@Transactional`

- **Usage**: Indicates that a method should be executed within a transactional context.
- **Understanding**: Manages database transactions automatically.

```java
@Service
public class UserService {
    @Transactional
    public void transferMoney(Long fromId, Long toId, BigDecimal amount) {
        // Withdraw from one account and deposit to another
    }
}
```

### `@Query`

- **Usage**: Defines a custom query to be used by a Spring Data repository method.
- **Understanding**: Allows writing custom JPQL or SQL queries.

```java
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);
    
    @Query(value = "SELECT * FROM users WHERE status = ?1", nativeQuery = true)
    List<User> findByStatus(int status);
}
```



## Spring Security Annotations

### `@EnableWebSecurity`

- **Usage**: Enables Spring Security's web security support.
- **Understanding**: Adds the Spring Security configuration to the application.

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Security configuration
}
```

### `@PreAuthorize`

- **Usage**: Specifies a security expression that must evaluate to true before a method can be invoked.
- **Understanding**: Provides method-level security based on SpEL expressions.

```java
@Service
public class UserService {
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(Long id) {
        // Delete user
    }
    
    @PreAuthorize("hasRole('ADMIN') or #username == authentication.principal.username")
    public void updateUser(String username, User user) {
        // Update user
    }
}
```

### `@PostAuthorize`

- **Usage**: Specifies a security expression that must evaluate to true after a method has been invoked.
- **Understanding**: Provides method-level security based on SpEL expressions and the method's return value.

```java
@Service
public class DocumentService {
    @PostAuthorize("returnObject.owner == authentication.principal.username")
    public Document getDocument(Long id) {
        // Return document only if the current user is the owner
        return documentRepository.findById(id).orElse(null);
    }
}
```

### `@Secured`

- **Usage**: Specifies a list of security configuration attributes for a method.
- **Understanding**: Simpler alternative to `@PreAuthorize` for role-based security.

```java
@Service
public class AdminService {
    @Secured("ROLE_ADMIN")
    public void performAdminOperation() {
        // Administrative operation
    }
}
```

## Spring Test Annotations

### `@SpringBootTest`

- **Usage**: Indicates that the class is a Spring Boot test that needs to bootstrap the entire application context.
- **Understanding**: Used for integration tests in Spring Boot applications.

```java
@SpringBootTest
public class UserServiceIntegrationTest {
    @Autowired
    private UserService userService;
    
    @Test
    public void testUserCreation() {
        // Test user creation
    }
}
```

### `@MockBean`

- **Usage**: Creates and injects a Mockito mock for a bean.
- **Understanding**: Replaces a bean with a mock in the ApplicationContext.
- **Example**:

```java
@SpringBootTest
public class UserServiceTest {
    @MockBean
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;
    
    @Test
    public void testGetUser() {
        // Setup mock behavior
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        
        // Test the service
        User user = userService.getUserById(1L);
        assertNotNull(user);
    }
}
```

## Spring Boot Annotations

### `@SpringBootApplication`

- **Usage**: Combines `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.
- **Understanding**: Marks the main class of a Spring Boot application, enabling auto-configuration and component scanning.

```java
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

### `@EnableAutoConfiguration`

- **Usage**: Enables Spring Boot's auto-configuration mechanism.
- **Understanding**: Attempts to configure beans based on the classpath and the beans already defined.

```java
@Configuration
@EnableAutoConfiguration
public class AppConfig {
    // Configuration
}
```

### `@ConfigurationProperties`

- **Usage**: Binds external configuration properties to a Java bean.
- **Understanding**: Automatically maps properties with a common prefix to a POJO.

```java
@Component
@ConfigurationProperties(prefix = "mail")
public class MailProperties {
    private String host;
    private int port;
    private String username;
    private String password;
    
    // Getters and setters
}
```

### `@ConditionalOnProperty`

- **Usage**: Conditionally enables a bean based on the presence and value of a Spring Environment property.
- **Understanding**: Helps with conditional bean registration based on configuration.

```java
@Configuration
public class CacheConfig {
    @Bean
    @ConditionalOnProperty(name = "cache.enabled", havingValue = "true")
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }
}
```

### `@ConditionalOnClass`

- **Usage**: Conditionally enables a bean based on the presence of a class on the classpath.
- **Understanding**: Helps with conditional bean registration based on the classpath.

```java
@Configuration
public class JdbcConfig {
    @Bean
    @ConditionalOnClass(DataSource.class)
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
```

### `@ConditionalOnMissingBean`

- **Usage**: Conditionally enables a bean only if a bean of the specified type doesn't already exist.
- **Understanding**: Helps with providing default beans that can be overridden.

```java
@Configuration
public class SecurityConfig {
    @Bean
    @ConditionalOnMissingBean
    public SecurityManager securityManager() {
        return new DefaultSecurityManager();
    }
}
```

## Bean Validation Annotations

### `@NotNull`

- **Usage**: Validates that the annotated property is not null.
- **Understanding**: Ensures a value is provided for the property.

```java
public class User {
    @NotNull
    private String username;
}
```

### `@Size`

- **Usage**: Validates that the annotated property's size is between the specified boundaries.
- **Understanding**: Used for strings, collections, arrays, etc.

```java
public class User {
    @Size(min = 8, max = 20)
    private String password;
}
```

### `@Min` and `@Max`

- **Usage**: Validates that the annotated property has a value not less/greater than the specified minimum/maximum.
- **Understanding**: Used for numeric properties.

```java
public class Product {
    @Min(0)
    private BigDecimal price;
    
    @Max(100)
    private int discountPercentage;
}
```

### `@Pattern`

- **Usage**: Validates that the annotated property matches the specified regular expression.
- **Understanding**: Used for validating string formats.

```java
public class User {
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
    private String email;
}
```

### `@Email`

- **Usage**: Validates that the annotated property is a well-formed email address.
- **Understanding**: Shorthand for a common regex pattern for emails.

```java
public class User {
    @Email
    private String email;
}
```

### `@Valid`

- **Usage**: Marks a property for cascaded validation.
- **Understanding**: Used to validate nested objects.

```java
public class Order {
    @Valid
    private Customer customer;
}
```

## JPA Annotations

### `@Entity`

- **Usage**: Marks a class as a JPA entity, mapping it to a database table.
- **Understanding**: Core annotation for domain objects to be persisted in a database.

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
}
```

### `@Table`

- **Usage**: Specifies the primary table for the annotated entity.
- **Understanding**: Used when the table name differs from the entity name.

```java
@Entity
@Table(name = "users")
public class User {
    // Fields
}
```

### `@Id`

- **Usage**: Marks a field as the primary key of an entity.
- **Understanding**: Identifies the unique identifier for an entity.

```java
@Entity
public class User {
    @Id
    private Long id;
}
```

### `@GeneratedValue`

- **Usage**: Specifies how the primary key should be generated.
- **Understanding**: Used to define the primary key generation strategy.

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
```

### `@Column`

- **Usage**: Specifies the mapped column for a field or property.
- **Understanding**: Used when the column name differs from the field name or to specify constraints.

```java
@Entity
public class User {
    @Column(name = "user_name", nullable = false, length = 50)
    private String username;
}
```

### `@OneToMany`

- **Usage**: Defines a one-to-many relationship between two entities.
- **Understanding**: Used to map a collection field that references child entities.

```java
@Entity
public class User {
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;
}
```

### `@ManyToOne`

- **Usage**: Defines a many-to-one relationship between two entities.
- **Understanding**: Used to map a field that references a parent entity.

```java
@Entity
public class Order {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
```

### `@OneToOne`

- **Usage**: Defines a one-to-one relationship between two entities.
- **Understanding**: Used to map a field that references a unique entity.

```java
@Entity
public class User {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
```

### `@ManyToMany`

- **Usage**: Defines a many-to-many relationship between two entities.
- **Understanding**: Used to map a collection field that references entities in a many-to-many relationship.

```java
@Entity
public class User {
    @ManyToMany
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
```

### `@JoinColumn`

- **Usage**: Specifies a foreign key column in a relationship.
- **Understanding**: Used to define the foreign key column that references the primary key of the related entity.

```java
@Entity
public class Order {
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
```

### `@Transient`

- **Usage**: Specifies that a field is not to be persisted.
- **Understanding**: Marks a field to be ignored by the persistence provider.

```java
@Entity
public class User {
    private String username;
    
    @Transient
    private String fullName; // Calculated field, not stored in database
}
```

### `@Enumerated`

- **Usage**: Specifies that a field is an enumerated type.
- **Understanding**: Used to map a Java enum to a database field.

```java
@Entity
public class Order {
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
```
