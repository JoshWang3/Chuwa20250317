#HW9

## 1. List all of the annotations you learned from class and homework to annotaitons.md (your own cheatsheet).
Updated in elena_xu/main
## 2. Compare Spring and Springboot? What are the benfits of Srpingboot?
### What’s the difference between Spring and Spring Boot?
- Spring is the core framework, which gives us the tools to build java applications, but we have to configure everything manually.
- Spring Boot  is part of the Spring ecosystem. It’s built on top of Spring. It auto-configure applications so we can set things up faster and easier.

### Benefits of Spring Boot
- Less Configuration: no need to write a lot of set up manually
- Build-in Server: No need to install tomcat or jetty, as it's preconfigured in Spring Boot
- Faster Development
- Production Tools: build-in health checks, metrics and monitoring via Actuator
- Starter Templates: eaily add things like web, security or JPA with simple starter dependencies

## 3. What is IoC and What is DI?
### IoC
Inversion of Control is a design principle where the control of creating and managing objects is transferred from the developer to the framework
- In plain Java, we create objects manually using `new`
- With Spring, the Spring framework creates and injects the objets for us

### DI
Dependency Injection is a way of implementing Ioc. Instead of creating dependencies manually, the framework injects them where needed.
There three main types of DI:
- Constructor Injection: most recommanded, makes dependencies immutable and testable
  ```java
  @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
  ```
- Setter Injection: good for optional dependencies
  ```java
  @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
  ```
- Field Injection: Quick to write, but harder to test and not ideal for complex apps
  ```java
  @Autowired
    private UserRepository userRepository;
  ```

## 4. What is `@CompnonentScan` ?
`@ComponentScan` is a Spring annotation that tells the framework: Look in these packages, find the classes marked as beans, and automatically register them in the Spring container.
### Why it is useful?
Without `@ComponentScan`, pring wouldn’t know where to find our components. It’s like giving Spring a map that tells it where to look for the objects (beans) it should manage.
In Spring Boot, `@SpringBootApplication` already includes `@ComponentScan` for the same package and its subpackages. So usually, we don’t need to write it manually — unless we're scanning additional or custom packages outside the default structure.
### Example
`@ComponentScan(basePackages = "com.myapp")` It tells Spring: Scan the package com.myapp and register all the beans you find there.
## 5. What is `@SpringbootApplication` ?
`@SpringBootApplication` is the annotation in Spring Boot that combines three key annotations into one:
- `@Configuration`
- `@EnableAutoConfiguration`
- `@ComponentScan`
So, when we use `@SpringbootApplication`, we're telling Spring Boot to:
1. Treat the class as a congiguration class
2. Automatically configure our applicatoin based on the dependencies on the classpath
3. Scan for beans in the same package and subpackages

In almost every Spring Boot project, we use @SpringBootApplication only once, and it goes on the main class that starts the application.
  
## 6. How many ways to define a bean? Provide code examples.
### Three ways to define a bean
1. Using `@Component` and its specializations (`@Service`, `@Repository`, `@Controller`)
  
This is the most common way in Spring Boot projects.
```java
   @Component
   public class MyComponentBean {
    public void sayHello() {
        System.out.println("Hello from @Component bean!");
    }
}
```
2. Using `@Bean` inside a `@Configuration` class
  
Use this when we need full control over bean creation (e.g., passing constructor arguments or creating third-party class instances).
```java
@Configuration
public class AppConfig {

    @Bean
    public MyBean myBean() {
        return new MyBean();
    }
}
```
3. Using XML configuration (old-style, mostly in legacy projects).
  
When we define beans using `XML` configuration, we typically place the `XML` file inside the `resources/` folder, often naming it something like `beans.xml`.
```xml
<beans xmlns="http://www.springframework.org/schema/beans">
    <bean id="myXmlBean" class="com.example.MyXmlBean"/>
</beans>
```

## 7. What is default bean name for @Component and @Bean ? Also compare @Component and @Bean.
### What is the Default Bean Name?
- `@Component`
  The default bean name is the class name with the first letter in lowercase.
  For example, the following default bean name is: `userService`
  ```java
  @Component
  public class UserService {}
  ```
- `@Bean`
  The default name is the method name
```java
@Configuration
public class AppConfig {
    @Bean
    public UserService userService() {
        return new UserService();
    }
}
```

### Comparison `@Component` vs. `@Bean`
- Using `@Component` when we control the class and want Spring to auto-detect it.
- Using `@Bean` when we want full control or need to register external/third-party classes.

| Feature               | @Component                                 | @Bean                                                   |
|-----------------------|--------------------------------------------|----------------------------------------------------------|
| Used On               | Class                                      | Method (inside @Configuration class)                     |
| Automatic Detection   | Yes — picked up by @ComponentScan          | No — must be manually declared in config class           |
| Custom Bean Logic     | Only default constructor used              |  Can customize how the bean is built                    |
| Use Case              | Our own classes (services, controllers, etc.) | Third-party classes or when fine control needed       |

## 8. Compare @component and @service , @repository, @controller ?
All of these are Spring beans, but they are used in differenct layers of Spring applicatoin.
```java
@Component
public class EmailValidator {}

@Service
public class UserService {}

@Repository
public class UserRepository {}

@Controller
public class HomeController {}
```

| Annotation   | Layer               | Purpose                                     | Extra Behavior?                        |
|--------------|---------------------|---------------------------------------------|----------------------------------------|
| @Component   | Generic (any layer) | Base annotation — generic bean              | No extra behavior                      |
| @Service     | Service layer       | Holds business logic                        | No extra behavior — semantic only      |
| @Repository  | Data access layer   | Handles database interaction (DAOs)         | Yes — auto exception translation       |
| @Controller  | Web layer (MVC)     | Handles web requests, returns views or JSON | Yes — integrates with Spring MVC       |

## 9. Explain @Autowired , @Qualifier , @Resource and @Primary ?
### `@@Autowired`
- Tells Spring to inject a bean automatically by type
- Can be applied to constructors, fields, or setters.
- If Spring finds only one matching bean, it injects it.
- If multiple beans of the same type exist, we need other three

### `@Qualifier`
- Used with `@Autowired` to specify which bean to inject by name. Helps resolve ambiguity when we have multiple beans of the same type.
```java
@Autowired
@Qualifier("advancedUserService")
private UserService userService;
```
### `@Resource`
- Comes from Java (JSR-250), not Spring-specific.
- Injects bean by name first, then falls back to type.
- Works similarly to @Autowired + @Qualifier, but syntax is shorter.
```java
@Resource(name = "userService")
private UserService service;
```
### `@Primary`
- Used on a bean to mark it as the default when multiple beans of the same type exist.
- When Spring sees multiple candidates, it picks the `@Primary` one automatically (unless overridden by @Qualifier).
```java
@Bean
@Primary
public UserService defaultUserService() {
    return new DefaultUserServiceImpl();
}
```

## 10. How many annotaitons we can use to inject a bean?
We commonly use four annotations to inject a bean in Spring
1. `@Autowired` (Spring-specific)
   - Injects by type.
   - Can be used on constructors, fields, or setters.
   ```java
   @Autowired
   private UserService userService;
   ```
2. `@Qualifier` (Spring-specific)
   - Used with `@Autowired` to specify the bean by name, when multiple beans of the same type exist.
   ```java
   @Autowired
   @Qualifier("adminUserService")
   private UserService userService;
   ```
3. `@Resource` (JSR-250, Java standard)
   - Injects by name first, then type if name not found.
   ```java
   @Resource(name = "userService")
   private UserService userService;

   ```
4. `@Inject` (JSR-330, Java standard)
   - Similar to @Autowired, but from javax.inject.
   - No required=false or @Qualifier support unless explicitly used.
   ```java
   @Inject
   private UserService userService;
   ```
## 11. Explain and compare differnet types of denpendency injection, their pros and cons, and use cases.
- Constructor Injection: most recommanded, makes dependencies immutable and testable
  - We inject dependencies through the constructor.
  ```java
   @Service
  public class OrderService {

      private final PaymentService paymentService;

      @Autowired
      public OrderService(PaymentService paymentService) {
          this.paymentService = paymentService;
      }
  }
  ```
  - Pros:
    - Immutable — fields can be final.
    - Great for unit testing (easy to mock).
    - Required dependencies are enforced at creation time.
  - Cons:
    - More boilerplate (especially with many dependencies).
    - Can be verbose if constructor has too many params.
  - Recommended for most real-world apps, especially when dependencies are required and must not be null.
- Setter Injection: good for optional dependencies
  - We inject the dependency through a public setter method.
  ```java
  @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
  ```
   - Pros:
      - Good for optional dependencies.
      - Clear method to override during testing.
  - Cons: 
      - Allows object to be in a partially constructed state.
      - Dependencies are mutable.
  - Used when the dependency is optional or may be reconfigured later.

- Field Injection: Quick to write, but harder to test and not ideal for complex apps
  - We inject directly into the field using @Autowired.
  ```java
  @Autowired
    private UserRepository userRepository;
  ```
  - Pros:
    - Very concise and quick to write.
    - No constructor or setter needed.
  - Cons:
    - Harder to test — can’t mock easily.
    - Breaks encapsulation — Spring needs to access private fields.
    - Not ideal for large or complex applications.
  - Good for quick prototypes or very simple apps, but not recommended for serious development.
  
## 12. If we have multiple beans for one type, how to set one is primary? and how Spring IOC picks one bean to inject if no primay, demo with code examples.
### Multiple Beans of the Same Type
```java
@Component
public class VipUserService implements UserService {}

@Component
public class RegularUserService implements UserService {}
```
Now Spring sees two candidates when we try to inject UserService
```java
@Autowired
private UserService userService; // Error: No qualifying bean — too many choices
```
### Solution 1: Use `@Primary` to Set a Default
```java
@Component
@Primary
public class VipUserService implements UserService {}

@Component
public class RegularUserService implements UserService {}
```
```java
@Autowired
private UserService userService; // Injects VipUserService
```
### Solution 2: Use `@Qualifier` to Specify Which One
```java
@Component("vipUserService")
public class VipUserService implements UserService {}

@Component("regularUserService")
public class RegularUserService implements UserService {}
```
```java
@Autowired
@Qualifier("regularUserService")
private UserService userService; // Injects RegularUserService
```
### What if we don't set @Primary or @Qualifier
Spring throws an error: `No qualifying bean of type 'UserService' available: expected single matching bean but found 2`, Because Spring IoC doesn’t know which bean to inject, it fails at startup.

## 13. Compare BeanFactory and ApplicationContext in Spring framework?
### Compare: `BeanFactory` vs. `ApplicationContext`
| Feature              | BeanFactory                          | ApplicationContext                               |
|----------------------|--------------------------------------|--------------------------------------------------|
| Core Role            | Basic bean container                 | Advanced container (extends BeanFactory)         |
| Bean Loading         | Lazy (loaded when requested)         | Eager (beans loaded at startup by default)       |
| Application Events   | Not supported                        | Supports event publishing/listening              |
| Internationalization | Not supported                        | Supports i18n (message sources)                  |
| AOP Support          | Limited or none                      | Fully supported                                  |
| Used In              | Lightweight / legacy apps            | Most Spring apps (especially Spring Boot)        |

```java
BeanFactory factory = new XmlBeanFactory(new ClassPathResource("beans.xml"));
MyService service = factory.getBean(MyService.class);
```
```java
ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
MyService service = context.getBean(MyService.class);
```
In practice, we rarely use BeanFactory directly anymore — ApplicationContext is the go-to for real-world Spring development.
## 14. Explain bean scope in Spring IOC? List bean scopes with explainations and code examples if possible.
Bean scope tells Spring how and when to create and manage bean instances in the application context.
By default, Spring beans are singleton, but we can change this with the `@Scope` annotation.
### Common Bean Scopes in Spring

| Scope       | Description                                      | Context         |
|-------------|--------------------------------------------------|-----------------|
| singleton   | One instance per Spring container (default)      | All applications|
| prototype   | A new instance every time it's requested         | All applications|
| request     | One instance per HTTP request                    | Web only        |
| session     | One instance per HTTP session                    | Web only        |
| application | One instance per ServletContext                  | Web only        |
| websocket   | One instance per WebSocket session               | WebSocket apps  |

1. `singleton` default
```java
@Component
@Scope("singleton")
public class LogService {}
```
- Created once when the app starts
- Shared across all components
- Best for stateless, reusable services

2. `prototype`
```java
@Component
@Scope("prototype")
public class ReportGenerator {}
```
- A new bean is created every time it’s injected or requested
- Good for stateful or short-lived objects
- Spring only creates it but we manage its lifecycle (no automatic destroy)

3. `request` (Web apps only)
```java
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopedBean {}
```
- One bean per HTTP request
- Useful for request-specific data (e.g., auth context, headers)

4. `session` (Web apps only)
```java
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopedBean {}
```
- One bean per user session
- Used for storing user-specific state

Bean scope lets us control how many bean instances Spring creates and how long they live — from one-per-app to one-per-request.
## 15. Write a Spring application that registers and autowires beans, Demo different types of dependency injection, Demo bean scopes. Demo dependency injection by type and by name, when there's ambiguity in bean definition. Demo bean registration by both @Component and @Bean
```arduino
com.example.demo
├── DemoApplication.java
├── service
│   ├── NotificationService.java
│   ├── EmailService.java
│   ├── SmsService.java
├── config
│   └── AppConfig.java
└── controller
    └── DemoController.java
```
DemoApplication.java
```java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```
NotificationService.java
```java
public interface NotificationService {
    void send(String message);
}
```
EmailService.java — Registered via @Component, marked as @Primary
```java
@Component("emailService")
@Primary
@Scope("singleton") // default scope, explicit for clarity
public class EmailService implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("Email sent: " + message);
    }
}
```
SmsService.java — Also a @Component
```java
@Component("smsService")
@Scope("prototype")
public class SmsService implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("SMS sent: " + message);
    }
}
```
AppConfig.java — Bean registered via @Bean
```java
@Configuration
public class AppConfig {

    @Bean(name = "pushService")
    public NotificationService pushNotificationService() {
        return new NotificationService() {
            @Override
            public void send(String message) {
                System.out.println("Push sent: " + message);
            }
        };
    }
}
```
DemoController.java
```java
@RestController
public class DemoController {

    //  Constructor Injection (uses @Primary by default)
    private final NotificationService notificationService;

    //  Setter Injection (explicitly using Qualifier)
    private NotificationService smsService;

    //  Field Injection (using Qualifier by name)
    @Autowired
    @Qualifier("pushService")
    private NotificationService pushService;

    @Autowired
    public DemoController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Autowired
    @Qualifier("smsService")
    public void setSmsService(NotificationService smsService) {
        this.smsService = smsService;
    }

    @GetMapping("/notify")
    public String notifyAll() {
        notificationService.send("Hello from constructor-injected bean!");
        smsService.send("Hello from setter-injected bean!");
        pushService.send("Hello from field-injected bean!");
        return "Notifications sent!";
    }
}
```
## 16. Explain builder pattern with code examples.
### What is the Builder Pattern?
- The Builder Pattern helps us create objects step by step by using a builder class instead of a long constructor with many parameters.
- It solves the problem of having too many constructor arguments and makes the code easier to read, flexible, and maintainable.
- Without Builder (messy & unclear)
  ```java
  User user = new User("Elena", 26, "Seattle", "Engineer", true);
  ```
- With Builder
  ```java
  User user = new User.Builder()
      .name("Elena")
      .age(26)
      .location("Seattle")
      .job("Engineer")
      .active(true)
      .build();
  ```
```java
public class User {
    private final String name;
    private final int age;
    private final String location;
    private final String job;
    private final boolean active;

    // Private constructor
    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.location = builder.location;
        this.job = builder.job;
        this.active = builder.active;
    }

    // Static nested Builder class
    public static class Builder {
        private String name;
        private int age;
        private String location;
        private String job;
        private boolean active;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder job(String job) {
            this.job = job;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
```
```java
User user = new User.Builder()
    .name("Elena")
    .age(26)
    .location("Seattle")
    .job("Engineer")
    .active(true)
    .build();
```
