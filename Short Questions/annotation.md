
## 1. @RequestMapping

Used to map HTTP requests to handler methods of REST controllers.

### Example:
```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public String getAllUsers() {
        return "List of users";
    }
}
```

---

## 2. @GetMapping, @PostMapping, @PutMapping, @DeleteMapping

Shortcut annotations for `@RequestMapping` with specific HTTP methods.

### Example:
```java
@GetMapping("/users")
public List<User> getUsers() { ... }

@PostMapping("/users")
public User createUser(@RequestBody User user) { ... }
```

---

## 3. @PathVariable

Used to extract values from the URI path.

### Example:
```java
@GetMapping("/users/{id}")
public User getUserById(@PathVariable Long id) {
    return userService.findById(id);
}
```

---

## 4. @RequestBody

Used to bind the HTTP request body to a method parameter (typically a POJO).

### Example:
```java
@PostMapping("/users")
public User createUser(@RequestBody User user) {
    return userService.save(user);
}
```

---

## 5. @Component

Marks a Java class as a Spring-managed component (bean). It is the base annotation for `@Service`, `@Repository`, and `@Controller`.

### Example:
```java
@Component
public class EmailSender {
    public void send(String message) {
        System.out.println("Email sent: " + message);
    }
}
```