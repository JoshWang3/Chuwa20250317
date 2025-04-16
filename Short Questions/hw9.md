1. List all of the annotations you learned from class and homework into `annotations.md` (your own cheatsheet).

   Already updated and written in `Short Questions/annotations.md`

   

2. Compare Spring and Spring Boot. What are the benefits of Spring Boot?

   **Spring Framework**

   Spring is a comprehensive framework for Java application development that provides infrastructure support at the application level. It offers numerous features like Dependency Injection (DI), Aspect-Oriented Programming (AOP), data access, transaction management, MVC web framework, etc.

   **Spring Boot**

   Spring Boot is an extension of the Spring Framework that simplifies the initial setup and development of new Spring applications. It follows the "opinionated defaults configuration" approach to reduce developer effort.

   **Benefits of Spring Boot**

   1. **Automatic Configuration**: Spring Boot automatically configures your application based on the dependencies you've added.
   2. **Standalone Applications**: Spring Boot creates standalone applications that include an embedded server.
   3. **No XML Configuration**: Spring Boot minimizes XML configuration.
   4. **Starter Dependencies**: Simplifies build configuration with starter dependencies.
   5. **Production-Ready Features**: Built-in monitoring, health checks, and externalized configuration.
   6. **Microservices Support**: Excellent foundation for microservices architecture.
   7. **Rapid Development**: Significantly reduces boilerplate code and configuration.

   

3. What is IoC and what is DI?

   **Inversion of Control (IoC)**

   IoC is a design principle in which the control of object creation, lifecycle, and dependencies is transferred from the application code to a container or framework. Instead of the application code controlling the flow and creating objects, the framework takes control.

   ```java
   // Traditional approach (without IoC)
   public class UserService {
       private UserRepository userRepository;
       
       public UserService() {
           // Service creates its own dependencies
           this.userRepository = new UserRepositoryImpl();
       }
   }
   
   // With IoC
   public class UserService {
       private UserRepository userRepository;
       
       // Framework provides the dependency
       public UserService(UserRepository userRepository) {
           this.userRepository = userRepository;
       }
   }
   ```

   **Dependency Injection (DI)**

   DI is a specific implementation technique of IoC. It is a pattern where dependencies of a class are "injected" from outside rather than created by the class itself.

   There are three common types of dependency injection:

   1. **Constructor Injection**: Dependencies are provided through a class constructor.

      ```java
      public class UserService {
          private final UserRepository userRepository;
          
          // Constructor injection
          @Autowired
          public UserService(UserRepository userRepository) {
              this.userRepository = userRepository;
          }
      }
      ```

   2. **Setter Injection**: Dependencies are provided through setter methods.

      ```java
      public class UserService {
          private UserRepository userRepository;
          
          // Setter injection
          @Autowired
          public void setUserRepository(UserRepository userRepository) {
              this.userRepository = userRepository;
          }
      }
      ```

   3. **Field Injection**: Dependencies are provided directly into fields.

      ```java
      public class UserService {
          // Field injection
          @Autowired
          private UserRepository userRepository;
      }
      ```

      

4. What is `@ComponentScan`?

   `@ComponentScan` is an annotation in Spring that tells the framework where to look for Spring components, configurations, and services. It detects classes annotated with:

   - `@Component`
   - `@Service`
   - `@Repository`
   - `@Controller`
   - `@RestController`
   - `@Configuration`
   - `@ControllerAdvice`
   - `@RestControllerAdvice`

   **Basic Usage**

   ```java
   @Configuration
   @ComponentScan(basePackages = "com.example.myapp")
   public class AppConfig {
       // Configuration details
   }
   ```

   In Spring Boot, `@ComponentScan` is included in `@SpringBootApplication`:

   ```java
   @SpringBootApplication // Includes @ComponentScan implicitly
   public class MyApplication {
       public static void main(String[] args) {
           SpringApplication.run(MyApplication.class, args);
       }
   }
   ```

   **Advanced Features**

   1. **Scanning multiple packages**:

      ```java
      @ComponentScan(basePackages = {"com.example.repository", "com.example.service"})
      ```

   2. **Using type-safe references**:

      ```java
      @ComponentScan(basePackageClasses = {UserRepository.class, ProductService.class})
      ```

   3. **Filtering**:

      ```java
      @ComponentScan(
          includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Repository"),
          excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)
      )
      ```

      

5. What is `@SpringBootApplication`?

   `@SpringBootApplication` is a convenience annotation that combines three other annotations:

   1. `@Configuration`: Marks the class as a source of bean definitions for the application context.
   2. `@EnableAutoConfiguration`: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
   3. `@ComponentScan`: Tells Spring to look for other components, configurations, and services in the package where the application is located.

   The `@SpringBootApplication` annotation simplifies the configuration and allows Spring Boot application to automatically detect and configure components with minimal code.

   

6. How many ways are there to define a bean? Provide code examples for each method.

   1. Using `@Component` and Component Scanning

      ```java
      @Component
      public class UserService {
          // Implementation
      }
      ```

   2. Using `@Bean` in Configuration Classes

      ```java
      @Configuration
      public class AppConfig {
          @Bean
          public UserService userService() {
              return new UserService();
          }
      }
      ```

   3. Using XML Configuration

      ```java
      <beans xmlns="http://www.springframework.org/schema/beans"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://www.springframework.org/schema/beans
                                 http://www.springframework.org/schema/beans/spring-beans.xsd">
          
          <bean id="userService" class="com.example.UserService" />
      </beans>
      ```

   4. Using Stereotype Annotations

      ```java
      // For MVC controllers
      @Controller
      public class UserController {
          // Implementation
      }
      
      // For REST controllers
      @RestController
      public class UserRestController {
          // Implementation
      }
      
      // For service layer
      @Service
      public class CustomerService {
          // Implementation
      }
      
      // For data access layer
      @Repository
      public class UserRepository {
          // Implementation
      }
      ```

   5. Using Java-based Configuration with `@Import`

      ```java
      @Configuration
      public class DatabaseConfig {
          @Bean
          public DataSource dataSource() {
              // Configure and return a DataSource
              return new BasicDataSource();
          }
      }
      
      @Configuration
      @Import(DatabaseConfig.class)
      public class AppConfig {
          // Additional configurations
      }
      ```

   6. Using Constructor-based DI with `@Autowired`

      ```java
      // Option 1: Using @Autowired on the constructor (Spring 4.3+)
      @Service
      public class UserService {
          private final UserRepository userRepository;
          
          @Autowired // Optional in newer Spring versions
          public UserService(UserRepository userRepository) {
              this.userRepository = userRepository;
          }
      }
      
      // Option 2: Implicit constructor injection (Spring 4.3+)
      @Service
      public class UserService {
          private final UserRepository userRepository;
          
          // No @Autowired needed for single constructor
          public UserService(UserRepository userRepository) {
              this.userRepository = userRepository;
          }
      }
      ```

   7. Using `@ComponentScan` with Filters

      ```java
      @Configuration
      @ComponentScan(
          basePackages = "com.example",
          includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Service"),
          excludeFilters = @ComponentScan.Filter(Repository.class)
      )
      public class AppConfig {
          // Configuration methods
      }
      ```

      

7. What is the default bean name for `@Component` and `@Bean`? Also, compare `@Component` and `@Bean`.

   | Feature          | `@Component`                                  | `@Bean`                                               |
   | ---------------- | --------------------------------------------- | ----------------------------------------------------- |
   | Applied to       | Class                                         | Method                                                |
   | Detected by      | Component scanning                            | Manually registered in `@Configuration` classes       |
   | Creation control | Limited - class is instantiated by Spring     | Full control over instantiation process               |
   | Use case         | Your own classes that should be auto-detected | Third-party classes or conditional bean creation      |
   | Configuration    | Minimal control over bean configuration       | Complete control over bean creation and configuration |
   | Lifecycle        | Handled automatically                         | Can be customized in method body                      |
   | Dependencies     | Injected by Spring                            | Can be manually wired in method                       |

8. Compare `@Component`, `@Service`, `@Repository`, and `@Controller`.

   All four annotations (`@Component`, `@Service`, `@Repository`, and `@Controller`) are stereotype annotations in Spring that mark classes as Spring-managed components. While they all have the same basic functionality of making a class eligible for component scanning, they serve different semantic purposes.

   | Feature                  | `@Component`                         | `@Service`                                  | `@Repository`                          | `@Controller`                             |
   | ------------------------ | ------------------------------------ | ------------------------------------------- | -------------------------------------- | ----------------------------------------- |
   | Purpose                  | Generic component                    | Business logic layer                        | Data access layer                      | Presentation layer                        |
   | Specialization           | Base stereotype                      | Specialization of `@Component`              | Specialization of `@Component`         | Specialization of `@Component`            |
   | Additional functionality | None                                 | None                                        | Exception translation                  | Request mapping capabilities              |
   | Use case                 | Generic beans with no specific layer | Service classes implementing business logic | DAO classes interacting with databases | MVC or REST controllers handling requests |
   | Auto-configuration       | Basic component scan                 | Same as `@Component`                        | Enables AOP for persistence exceptions | Enables MVC features when detected        |

   

9. Explain `@Autowired`, `@Qualifier`, `@Resource`, and `@Primary`.

   `@Autowired` is a Spring annotation that automatically injects dependencies.

   ```java
   @Service
   public class UserService {
       // Field injection
       @Autowired
       private UserRepository userRepository;
       
       // Constructor injection
       @Autowired
       public UserService(UserRepository userRepository) {
           this.userRepository = userRepository;
       }
       
       // Setter injection
       @Autowired
       public void setUserRepository(UserRepository userRepository) {
           this.userRepository = userRepository;
       }
   }
   ```

   `@Qualifier` is used with `@Autowired` to specify which bean should be injected when multiple beans of the same type exist.

   ```java
   @Service
   public class MovieRecommendationService {
       private final RecommendationEngine engine;
       
       @Autowired
       public MovieRecommendationService(@Qualifier("machineLearningSolution") RecommendationEngine engine) {
           this.engine = engine;
       }
   }
   
   @Component("simpleSolution")
   public class SimpleRecommendationEngine implements RecommendationEngine {
       // Implementation
   }
   
   @Component("machineLearningSolution")
   public class MachineLearningRecommendationEngine implements RecommendationEngine {
       // Implementation
   }
   ```

   `@Primary` indicates that a particular bean should be given preference when multiple beans of the same type are present.

   ```java
   @Component
   @Primary
   public class PrimaryDataSource implements DataSource {
       // Implementation
   }
   
   @Component
   public class SecondaryDataSource implements DataSource {
       // Implementation
   }
   
   @Service
   public class UserService {
       private final DataSource dataSource;
       
       @Autowired
       public UserService(DataSource dataSource) {
           // Will inject PrimaryDataSource due to @Primary annotation
           this.dataSource = dataSource;
       }
   }
   ```

   `@Resource` is a Java EE annotation (javax.annotation) supported by Spring for dependency injection.

   ```java
   @Service
   public class NotificationService {
       // Injection by name, then by type
       @Resource(name = "emailSender")
       private MessageSender messageSender;
       
       // Without name, injects by field name
       @Resource
       private SMSProvider smsProvider;
   }
   ```

   | Feature                       | `@Autowired`                                      | `@Qualifier`                              | `@Primary`                  | `@Resource`          |
   | ----------------------------- | ------------------------------------------------- | ----------------------------------------- | --------------------------- | -------------------- |
   | Origin                        | Spring                                            | Spring                                    | Spring                      | Java EE              |
   | Injection type                | By type                                           | Used with `@Autowired` to qualify by name | Designates a preferred bean | By name then by type |
   | Usage                         | Fields, constructors, methods                     | With `@Autowired`                         | On bean definition          | Fields, methods      |
   | When multiple beans available | Throws exception or requires `@Qualifier`         | Specifies which bean to use               | Marks a bean as preferred   | Uses the named bean  |
   | Default behavior              | Required=true (throws exception if no bean found) | N/A                                       | N/A                         | Required=true        |
   | JSR compliance                | No                                                | No                                        | No                          | Yes (JSR-250)        |

   **`@Primary` vs `@Qualifier`**:

   - Use `@Primary` for application-wide default choice
   - Use `@Qualifier` for specific injection points

   **`@Autowired` vs `@Resource`**:

   - `@Autowired` is Spring-specific and more flexible

   - `@Resource` is standard Java but more limited

     

10. How many annotations can we use to inject a bean?

    1. **`@Autowired`** - Spring's main annotation for dependency injection
    2. **`@Resource`** - Java EE annotation (javax.annotation)
    3. **`@Inject`** - Java EE annotation (javax.inject) from JSR-330
    4. **`@Value`** - For injecting values from properties files or environment variables
    5. **`@Lookup`** - For method injection where a new instance is required each time
    6. **Constructor injection without annotation** - In Spring 4.3+, no annotation needed for single constructor classes

    Here's a comparison of the three main injection annotations:

    | Feature                    | `@Autowired`                           | `@Resource`                | `@Inject`                               |
    | -------------------------- | -------------------------------------- | -------------------------- | --------------------------------------- |
    | Origin                     | Spring                                 | Java EE (JSR-250)          | Java EE (JSR-330)                       |
    | Injection type             | By type                                | By name, then type         | By type                                 |
    | Support for qualifiers     | Yes, with `@Qualifier`                 | Yes, with `name` attribute | Yes, with `@Named` or custom qualifiers |
    | Required dependency        | Yes by default, can set required=false | Yes by default             | Yes by default                          |
    | Constructor, field, method | All three                              | Field and setter method    | All three                               |
    | Framework-specific         | Yes                                    | No (standard Java)         | No (standard Java)                      |

11. Explain and compare different types of dependency injection, their pros and cons, and use cases.

    1. **Constructor Injection**

       ```java
       public class UserService {
           private final UserRepository userRepository;
           private final EmailService emailService;
           
           // Dependencies are injected through the constructor
           public UserService(UserRepository userRepository, EmailService emailService) {
               this.userRepository = userRepository;
               this.emailService = emailService;
           }
       }
       ```

       **Pros:**

       - Supports immutability (dependencies can be final)
       - Makes dependencies explicit and visible
       - Guarantees complete initialization
       - Better for testing (dependencies clearly defined)
       - Prevents NullPointerException
       - Works well with required dependencies

       **Cons:**

       - Can become unwieldy with many dependencies
       - Constructor signature changes when dependencies change

       **Use Cases:**

       - When dependencies are mandatory
       - When immutability is desired
       - For services with stable dependencies

    2. **Setter Injection**

       ```java
       public class UserService {
           private UserRepository userRepository;
           private EmailService emailService;
           
           // Dependencies are injected through setter methods
           @Autowired
           public void setUserRepository(UserRepository userRepository) {
               this.userRepository = userRepository;
           }
           
           @Autowired
           public void setEmailService(EmailService emailService) {
               this.emailService = emailService;
           }
       }
       ```

       **Pros:**

       - More flexible, dependencies can be changed at runtime
       - Easier to handle optional dependencies
       - Avoids large constructor signatures
       - Dependencies can be added without changing existing code

       **Cons:**

       - Doesn't ensure complete initialization
       - Dependencies cannot be final (immutable)
       - Potential for NullPointerException if dependency not set
       - Object might be in an inconsistent state

       **Use Cases:**

       - Optional dependencies
       - When circular dependencies exist
       - When dependencies might change at runtime

    3. **Field Injection**

       ```java
       public class UserService {
           @Autowired
           private UserRepository userRepository;
           
           @Autowired
           private EmailService emailService;
       }
       ```

       **Pros:**

       - Minimal boilerplate code
       - Cleaner, more concise code
       - Easy to add new dependencies

       **Cons:**

       - Hard to test without Spring container
       - Dependencies are hidden (not explicit)
       - Cannot make fields final (immutable)
       - No way to create immutable objects
       - Potential for NullPointerException

       **Use Cases:**

       - Quick prototyping
       - In test classes where brevity is valued
       - When working with legacy code

    4. **Method Injection**

       ```java
       @Component
       public class SingletonBean {
           @Lookup
           public PrototypeBean getPrototypeBean() {
               return null; // Spring overrides this method
           }
           
           public void doSomething() {
               PrototypeBean bean = getPrototypeBean();
               // Use the bean
           }
       }
       ```

       **Pros:**

       - Solves scoping issues (e.g., singleton bean depends on prototype bean)
       - Provides a new instance each time
       - Useful for special dependency scenarios

       **Cons:**

       - Complex and rarely needed
       - Hard to understand and maintain
       - Tightly coupled to Spring framework

       **Use Cases:**

       - When a singleton bean needs a new instance of a prototype bean
       - Dealing with different bean scopes
       - Special use cases with scope mismatches

    5. **Interface Injection**

       ```java
       // Less common in Spring, more common in other frameworks
       public interface ServiceInjector {
           void injectUserRepository(UserRepository repository);
       }
       
       public class UserService implements ServiceInjector {
           private UserRepository userRepository;
           
           @Override
           public void injectUserRepository(UserRepository repository) {
               this.userRepository = repository;
           }
       }
       ```

       **Pros:**

       - Clearly defines the injection contract
       - Enforces dependency implementation
       - Useful in some frameworks

       **Cons:**

       - More verbose
       - Increases coupling through interface
       - Rarely used in Spring

       **Use Cases:**

       - When explicit dependency contracts are needed
       - In frameworks designed around interface injection

    | Feature               | Constructor Injection | Setter Injection     | Field Injection      | Method Injection       |
    | --------------------- | --------------------- | -------------------- | -------------------- | ---------------------- |
    | Immutability          | Supports final fields | No support for final | No support for final | No support for final   |
    | Required dependencies | Good for required     | Better for optional  | No distinction       | Special cases          |
    | Testability           | Excellent             | Good                 | Poor                 | Poor                   |
    | Readability           | Explicit dependencies | Somewhat explicit    | Hidden dependencies  | Complex                |
    | Circular dependencies | Cannot handle         | Can handle           | Can handle           | Can handle             |
    | Spring support        | Fully supported       | Fully supported      | Fully supported      | Supported with @Lookup |
    | Code verbosity        | Medium                | High                 | Low                  | Medium                 |
    | Runtime changes       | Not possible          | Possible             | Not recommended      | Possible               |

    **Best Practices**

    1. **Prefer Constructor Injection** for required dependencies

       - Makes dependencies explicit

       - Supports immutability

       - Better testability

    2. **Use Setter Injection** for optional dependencies

    3. **Avoid Field Injection** in production code

       - Hard to test

       - Hides dependencies

    4. **Use Method Injection** only for special scope scenarios

    5. **Mix injection types when appropriate**:

       - Constructor for required dependencies

       - Setters for optional ones

         

12. If we have multiple beans of the same type, how do we set one as primary? How does Spring IoC pick one bean to inject if none is marked as primary? Provide code examples.

    When multiple beans of the same type exist and Spring IoC needs to autowire one of them, it will choose the one marked as primary.

    If none of the beans is marked as primary, Spring IoC uses several strategies to resolve the ambiguity:

    1. **By Name**: If the variable name matches a bean name/ID, Spring will inject that bean
    2. **@Qualifier Annotation**: You can specify which bean to use with @Qualifier
    3. **By Type with Precedence**: If one implementation is more specific (like a subclass)

    ```java
    // By name resolution
    @Autowired
    private DataSource primaryDataSource; // Will inject the bean named "primaryDataSource"
    
    // Using @Qualifier
    @Autowired
    @Qualifier("secondaryDataSource")
    private DataSource dataSource; // Will inject the specifically qualified bean
    ```

    

13. Compare `BeanFactory` and `ApplicationContext` in the Spring Framework.

    **BeanFactory**

    The `BeanFactory` is the most basic container in Spring, providing the fundamental IoC (Inversion of Control) functionality. Key characteristics include:

    - It's a basic container providing fundamental DI support
    - Lazy initialization of beans (only instantiated when requested)
    - Minimal memory footprint and startup time
    - Doesn't provide enterprise features out of the box
    - More suitable for resource-constrained environments

    ```java
    Resource resource = new ClassPathResource("beans.xml");
    BeanFactory factory = new XmlBeanFactory(resource);
    MyBean bean = (MyBean) factory.getBean("myBean");
    ```

    **ApplicationContext**

    The `ApplicationContext` is a more advanced container built on top of the `BeanFactory` interface. It offers all `BeanFactory` features plus additional enterprise-specific functionality:

    - Eager initialization of singleton beans by default
    - Automatic `BeanPostProcessor` registration
    - Automatic `BeanFactoryPostProcessor` registration
    - Convenient access to resources in different locations
    - Event publication to registered listeners
    - Internationalization support with `MessageSource`
    - Application layer specific contexts (e.g., WebApplicationContext)

    ```java
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    MyBean bean = context.getBean("myBean", MyBean.class);
    ```

    | Feature                  | BeanFactory                                             | ApplicationContext                                           |
    | ------------------------ | ------------------------------------------------------- | ------------------------------------------------------------ |
    | **Definition**           | Basic container providing fundamental IoC functionality | Advanced container extending BeanFactory with enterprise features |
    | **Initialization**       | Lazy initialization (on demand)                         | Eager initialization of singleton beans by default           |
    | **Memory Usage**         | Lower memory footprint                                  | Higher memory consumption due to pre-instantiation           |
    | **Startup Speed**        | Faster startup                                          | Slower startup due to pre-loading beans                      |
    | **Auto-wiring**          | Limited support (requires explicit configuration)       | Enhanced support with annotations like `@Autowired`          |
    | **Annotation Support**   | Limited built-in support                                | Full support for annotations like `@Component`, `@Service`, etc. |
    | **Event Publishing**     | Not supported                                           | Supports application event publishing                        |
    | **Resource Access**      | Basic resource loading                                  | Enhanced resource loading from multiple locations            |
    | **Internationalization** | Not supported                                           | Supports i18n via MessageSource                              |
    | **AOP Integration**      | Requires explicit setup                                 | Seamless integration with AOP                                |
    | **Use Cases**            | Resource-constrained environments                       | Most Spring applications                                     |

14. Explain bean scopes in Spring IoC. List the available bean scopes with explanations and code examples if possible.

    1. **Singleton (Default)**

       - **Description**: Single instance per Spring IoC container

       - **Lifecycle**: Created on container startup (or first request if lazy-initialized)

       - **Use Case**: Stateless services, repositories, utility classes

       ```java
       @Component
       @Scope("singleton") // This is default, so not required
       public class UserService {
           // Implementation
       }
       ```

    2. **Prototype**

       - **Description**: New instance created each time requested

       - **Lifecycle**: Created on each request, not managed after creation

       - **Use Case**: Stateful beans, beans with mutable state

       ```java
       @Component
       @Scope("prototype")
       public class ShoppingCart {
           private List<Item> items = new ArrayList<>();
           
           public void addItem(Item item) {
               items.add(item);
           }
       }
       ```

    3. **Request**

       - **Description**: One instance per HTTP request

       - **Lifecycle**: Created at start of HTTP request, destroyed when request completes

       - **Use Case**: Web-specific objects needed for single request

       ```java
       @Component
       @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
       public class RequestLogger {
           private String requestId = UUID.randomUUID().toString();
           
           public String getRequestId() {
               return requestId;
           }
       }
       ```

    4. **Session**

       - **Description**: One instance per HTTP session

       - **Lifecycle**: Created at session start, destroyed when session ends

       - **Use Case**: User-specific data maintained across requests

       ```java
       @Component
       @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
       public class UserPreferences {
           private Map<String, String> preferences = new HashMap<>();
           
           public void setPreference(String key, String value) {
               preferences.put(key, value);
           }
       }
       ```

    5. **Application**

       - **Description**: One instance per ServletContext

       - **Lifecycle**: Tied to the lifecycle of a ServletContext

       - **Use Case**: Application-wide settings or counters

       ```java
       @Component
       @Scope(WebApplicationContext.SCOPE_APPLICATION)
       public class AppStatistics {
           private AtomicInteger totalRequests = new AtomicInteger(0);
           
           public int incrementAndGetRequestCount() {
               return totalRequests.incrementAndGet();
           }
       }
       ```

    6. **Websocket**

       - **Description**: One instance per WebSocket session

       - **Lifecycle**: Created when WebSocket session starts, destroyed when it ends

       - **Use Case**: WebSocket-specific data

       ```java
       @Component
       @Scope(scopeName = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
       public class WebSocketMessage {
           private List<String> messageHistory = new ArrayList<>();
           
           public void addMessage(String message) {
               messageHistory.add(message);
           }
       }
       ```

    7. **Custom Scopes**

       Spring also allows defining custom scopes by implementing the `Scope` interface

       ```java
       @Component
       @Scope(scopeName = "thread", proxyMode = ScopedProxyMode.TARGET_CLASS)
       public class ThreadLocalContext {
           private Map<String, Object> context = new HashMap<>();
           
           // Implementation
       }
       ```

       

15. Write a Spring application that registers and autowires beans.

    - Demonstrate different types of dependency injection.
      - Field injection (with `@Autowired`)
      - Constructor injection
      - Setter method injection
    - Demonstrate bean scopes.
      - Singleton scope (default): Demonstrated with `singletonMessage` bean
      - Prototype scope: Implemented for `UserService` and `prototypeMessage`
    - Demonstrate dependency injection by type and by name when there's ambiguity in bean definitions.
      - By type with `@Primary`: Using the primary bean when multiple implementations exist
      - By qualifier with `@Qualifier`: Explicitly specifying which implementation to use
      - By name: Variable name matching bean name (with an interesting discovery)
    - Demonstrate bean registration using both `@Component` and `@Bean`.
      - Annotation-based: Using `@Component` and its specializations
      - Java configuration: Using `@Bean` methods in a `@Configuration` class

    The project code is in `Projects/springdidemo`

    ```swift
    src/main/java/chuwa/backend/springdidemo/
    ├── SpringDiDemoApplication.java
    ├── config/
    │   └── AppConfig.java
    ├── model/
    │   └── Message.java
    ├── service/
    │   ├── EmailService.java
    │   ├── MessageService.java
    │   ├── PushNotificationService.java
    │   ├── SmsService.java
    │   └── UserService.java
    └── runner/
        └── DemoRunner.java
    
    ```

    ```bash
    ========== SPRING DEPENDENCY INJECTION DEMO ==========
    
    2025-04-15T02:43:06.121-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.runner.DemoRunner       : ---------- DEPENDENCY INJECTION TYPES ----------
    Using default service: Email Service
    2025-04-15T02:43:06.121-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.service.EmailService    : Sending EMAIL to user@example.com: Welcome to Spring DI Demo
    Using SMS service: SMS Service
    2025-04-15T02:43:06.123-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.service.SmsServiceImpl  : Sending SMS to user@example.com: Welcome to Spring DI Demo
    Using Push service: Email Service
    2025-04-15T02:43:06.123-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.service.EmailService    : Sending EMAIL to user@example.com: Welcome to Spring DI Demo
    Using constructor-injected service: Email Service
    2025-04-15T02:43:06.123-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.service.EmailService    : Sending EMAIL to user@example.com: Welcome to Spring DI Demo
    Using setter-injected service: Email Service
    2025-04-15T02:43:06.123-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.service.EmailService    : Sending EMAIL to user@example.com: Welcome to Spring DI Demo
    2025-04-15T02:43:06.123-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.runner.DemoRunner       : 
    ---------- BEAN SCOPES ----------
    2025-04-15T02:43:06.123-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.runner.DemoRunner       : Singleton Message 1: 106457780
    2025-04-15T02:43:06.123-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.runner.DemoRunner       : Singleton Message 2: 106457780
    2025-04-15T02:43:06.123-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.runner.DemoRunner       : Are singleton beans the same instance? true
    2025-04-15T02:43:06.123-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.runner.DemoRunner       : Prototype Message 1: 392519492
    2025-04-15T02:43:06.123-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.runner.DemoRunner       : Prototype Message 2: 90235834
    2025-04-15T02:43:06.123-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.runner.DemoRunner       : Are prototype beans the same instance? false
    2025-04-15T02:43:06.124-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.runner.DemoRunner       : UserService 1: 152343548
    2025-04-15T02:43:06.124-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.runner.DemoRunner       : UserService 2: 1289504160
    2025-04-15T02:43:06.124-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.runner.DemoRunner       : Are UserService beans the same instance? false
    2025-04-15T02:43:06.124-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.runner.DemoRunner       : 
    ---------- BEAN REGISTRATION ----------
    2025-04-15T02:43:06.124-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.runner.DemoRunner       : Welcome Message: Message(content=Welcome to Spring DI Demo, recipient=user@example.com)
    2025-04-15T02:43:06.124-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.runner.DemoRunner       : Custom Named Message: Message(content=I am a named bean, recipient=named@example.com)
    2025-04-15T02:43:06.124-04:00  INFO 62092 --- [springdidemo] [  restartedMain] c.b.springdidemo.runner.DemoRunner       : 
    =========== DEMO COMPLETED ===========
    ```

    During implementation, I discovered that Spring follows a specific priority order when resolving dependencies:

    1. `@Qualifier` annotations have the highest priority
    2. `@Primary` beans take precedence over name matching
    3. Variable name matching is attempted if no qualifier or primary bean exists

    This is demonstrated in the application where a variable named `pushService` is actually injected with the `EmailService` bean (marked as `@Primary`) rather than the `PushNotificationService` bean (named "pushService").

    This behavior confirms Spring's dependency resolution strategy and shows why explicit qualifiers are often necessary in complex applications to ensure the correct beans are injected.

    

16. Explain the Builder pattern with code examples.

    The Builder pattern is a creational design pattern that allows for the step-by-step construction of complex objects. It separates the construction of a complex object from its representation, allowing the same construction process to create different representations.

    **Key Components**

    1. **Product**: The complex object being built

    2. **Builder**: Abstract interface defining steps to build the product

    3. **ConcreteBuilder**: Implements the Builder interface with specific implementations

    4. **Director**: Controls the building process using the builder

    **Benefits**

    - Allows construction of objects with many optional parameters

    - Prevents telescoping constructor anti-pattern

    - Enables immutable objects with clear construction steps

    - Improves code readability and maintainability

    - Supports construction of different product representations

    ```java
    // Product class
    public class Pizza {
        private final String size;
        private final boolean cheese;
        private final boolean pepperoni;
        private final boolean mushrooms;
    
        // Private constructor - can only be accessed by the Builder
        private Pizza(Builder builder) {
            this.size = builder.size;
            this.cheese = builder.cheese;
            this.pepperoni = builder.pepperoni;
            this.mushrooms = builder.mushrooms;
        }
    
        // Static Builder class
        public static class Builder {
            // Required parameter
            private final String size;
            
            // Optional parameters with default values
            private boolean cheese = false;
            private boolean pepperoni = false;
            private boolean mushrooms = false;
    
            // Constructor with required parameters
            public Builder(String size) {
                this.size = size;
            }
    
            // Methods for setting optional parameters (fluent interface)
            public Builder cheese(boolean value) {
                cheese = value;
                return this;
            }
    
            public Builder pepperoni(boolean value) {
                pepperoni = value;
                return this;
            }
    
            public Builder mushrooms(boolean value) {
                mushrooms = value;
                return this;
            }
    
            // Build method creates the final object
            public Pizza build() {
                return new Pizza(this);
            }
        }
    
        @Override
        public String toString() {
            return "Pizza: " + size + " size with " +
                   (cheese ? "cheese, " : "") +
                   (pepperoni ? "pepperoni, " : "") +
                   (mushrooms ? "mushrooms" : "");
        }
    }
    
    // Usage example
    public class BuilderDemo {
        public static void main(String[] args) {
            // Creating a pizza with all toppings
            Pizza pizza1 = new Pizza.Builder("Large")
                    .cheese(true)
                    .pepperoni(true)
                    .mushrooms(true)
                    .build();
            System.out.println(pizza1);
    
            // Creating a plain pizza
            Pizza pizza2 = new Pizza.Builder("Small")
                    .build();
            System.out.println(pizza2);
            
            // Creating a pizza with just cheese
            Pizza pizza3 = new Pizza.Builder("Medium")
                    .cheese(true)
                    .build();
            System.out.println(pizza3);
        }
    }
    ```

    

​	
