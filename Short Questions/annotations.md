Spring Boot Annotations:
1. Core Spring Annotations
@Component
Marks a Java class as a Spring bean (generic component).
@Component
public class MyService {
    public void serve() {
        System.out.println("Service Called");
    }
}
@Autowired
Injects a bean automatically using dependency injection.
@Component
public class MyController {
    @Autowired
    private MyService myService;

    public void callService() {
        myService.serve();
    }
}
@Qualifier
Used with @Autowired when multiple beans of the same type exist.
@Component("bean1")
public class MyBean1 {}

@Component("bean2")
public class MyBean2 {}

@Autowired
@Qualifier("bean1")
private MyBean1 myBean;
2. Spring Boot Annotations:
@SpringBootApplication
Combination of:
@Configuration
@EnableAutoConfiguration
@ComponentScan
It bootstraps the application.
@SpringBootApplication
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
@Configuration
Defines configuration class for Spring beans.
@Configuration
public class AppConfig {
    @Bean
    public MyBean myBean() {
        return new MyBean();
    }
}
@Bean
Declares a bean manually in a @Configuration class.
@Bean
public MyBean myBean() {
    return new MyBean();
}
@EnableAutoConfiguration
Tells Spring Boot to auto-configure beans based on classpath settings.
@ComponentScan
Tells Spring where to look for @Component, @Service, etc.
@ComponentScan(basePackages = "com.example")
3. Web/Spring MVC Annotations:
@RestController
A combination of @Controller and @ResponseBody. Used for REST APIs.
@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }
}
@RequestMapping
Maps HTTP requests to handler methods.
@RequestMapping(value = "/users", method = RequestMethod.GET)
public List<User> getUsers() {
    return userService.getAllUsers();
}
Shortcut Annotations:
@GetMapping

@PostMapping

@PutMapping

@DeleteMapping

@PatchMapping
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) {
    return userService.getUser(id);
}
@PathVariable
Extracts values from the URI.
@GetMapping("/user/{id}")
public User getUser(@PathVariable("id") Long id) {
    return service.findUser(id);
}
@RequestParam
Extracts query parameters.
@GetMapping("/search")
public String search(@RequestParam("q") String query) {
    return "Results for " + query;
}
@RequestBody
Maps the request body to a Java object.
@PostMapping("/users")
public User createUser(@RequestBody User user) {
    return service.save(user);
}
@ResponseBody
Returns the response body directly, usually as JSON.
@ResponseBody
public User getUser() {
    return new User("Tom");
}
4. Exception Handing Annotations:
@ControllerAdvice
Global exception handler for controllers.
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
@ExceptionHandler
Handles a specific exception.
@ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<String> handleIllegal(IllegalArgumentException ex) {
    return ResponseEntity.badRequest().body("Invalid input");
}
5. Persistence Annotations:
@Entity
Marks a class as a JPA entity.
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
@Id, @GeneratedValue
Primary key and auto-generation strategy.
@Repository
Marks a class as a Spring-managed DAO or repository.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
@Table, @Column
Customize table/column names.
@Table(name = "users")
@Column(name = "full_name")

