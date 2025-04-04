# Spring Boot Annotations

## 1. @RestController
Used to create RESTful web services. Combines `@Controller` and `@ResponseBody`.

### Example:
```java
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users")
    public List<String> getAllUsers() {
        return Arrays.asList("Alice", "Bob", "Charlie");
    }
}
```

## 2. @Service
Marks a class as a service provider. It is used in the service layer to hold business logic.

### Example:
```java
@Service
public class UserService {

    public String getUserById(Long id) {
        return "User" + id;
    }
}

```

## 3. @Repository
Indicates that a class is a Data Access Object (DAO), which interacts with the database.

Example:

### Example:
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

```

## 4. @Autowired
Automatically injects dependencies into a bean.

### Example:
```java
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
```

## 5. @Entity
Specifies that the class is an entity and is mapped to a database table.

### Example:
```java
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;
}

```