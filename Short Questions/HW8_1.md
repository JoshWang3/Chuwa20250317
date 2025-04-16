# List all of the annotations you learned from class and homework to annotaitons.md (your own cheatsheet).
✅
# Compare Spring and Springboot? What are the benfits of Srpingboot?
| **Feature**       | **Spring Framework**                                               | **Spring Boot**                                                                 |
|------------------|---------------------------------------------------------------------|----------------------------------------------------------------------------------|
| **Purpose**       | Comprehensive framework for building Java applications             | Rapid development with Spring, with auto-configuration                           |
| **Setup**         | Manual XML or Java config for beans, web server, etc.              | No boilerplate setup — just run it                                               |
| **Configuration** | Requires manual setup of dependencies and beans                    | Uses `@SpringBootApplication` and auto-configures                                |
| **Web Server**    | Must deploy to external server (e.g., Tomcat)                      | Comes with embedded Tomcat/Jetty/Undertow                                        |
| **Build Tool**    | Maven/Gradle manually configured                                   | Starter POMs simplify dependencies (e.g., `spring-boot-starter`)                 |
| **Deployment**    | WAR deployment usually                                             | Easily run as a standalone JAR                                                   |
| **Opinionated?**  | No — full freedom (but more complexity)                           | Yes — convention over configuration                                              |
| **Learning Curve**| Steeper for beginners                                              | Easier entry point, beginner-friendly                                            |

## Benefits of Spring Boot
### Auto-Configuration
- Automatically configures beans, databases, web servers, etc.
- No need to manually set up applicationContext.xml or servlet mappings.
### Embedded Web Server
- Comes with embedded Tomcat/Jetty/Undertow
- No need to install or deploy to a separate app server
### Spring Boot Starters
- Pre-configured dependency packages for common use cases.
### Actuator for Monitoring
- spring-boot-starter-actuator provides production-ready endpoints:
    - /actuator/health
    - /actuator/metrics
    - /actuator/env
### Externalized Configuration
- Easy to manage app settings via `application.properties` or `application.yml`
- Supports profiles for dev/test/prod environments
### Easier Testing
- Built-in support for testing with `@SpringBootTest`, `@WebMvcTest`, `@DataJpaTest`, etc.

# What is IOC and What is DI?
## What is IoC (Inversion of Control)
Inversion of Control is a design principle where the control of object creation and dependency management is inverted — instead of the class creating its own dependencies, the framework does it.
## What is DI (Dependency Injection)
Dependency Injection is a way of implementing IoC, where dependencies are provided (injected) to a class rather than the class creating them.

It’s the technique by which IoC is achieved.

# What is `@CompnonentScan` ?
@ComponentScan is a Spring annotation that tells the framework to scan specific packages for components (beans) like:
- @Component
- @Service
- @Repository
- @Controller

These components are then automatically registered as beans in the Spring container.

# What is `@SpringbootApplication` ?
`@SpringBootApplication` is a convenience annotation that combines three core Spring annotations.
| **Annotation**             | **Purpose**                                                                                              |
|----------------------------|-----------------------------------------------------------------------------------------------------------|
| `@Configuration`           | Marks the class as a source of bean definitions (like `applicationContext.xml`)                           |
| `@EnableAutoConfiguration` | Tells Spring Boot to automatically configure beans based on classpath settings, properties, etc.          |
| `@ComponentScan`           | Scans the current package and subpackages for Spring-managed components (`@Service`, `@Controller`, etc.) |

# How many ways to define a bean? Provide code examples.
##  Using `@Component` and Component Scanning (Annotation-based)
```java
@Component
public class MyService {
    public void doSomething() {
        System.out.println("Service logic");
    }
}
```
```java
@SpringBootApplication  // includes @ComponentScan
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
```
## Using `@Bean` in a `@Configuration` Class
```java
@Configuration
public class AppConfig {
    
    @Bean
    public MyService myService() {
        return new MyService();  // manually constructing the bean
    }
}
```

# What is default bean name for `@Component` and `@Bean` ? Also compare `@Component` and `@Bean`.
## Default Bean Name
### `@Component`
Default name: the class name with the first letter in lowercase
### `@Bean`
Default name: the name of the method that defines the bean
## `@Component` vs `@Bean` Comparison
| **Aspect**                 | **@Component**                                                | **@Bean**                                                                  |
|----------------------------|---------------------------------------------------------------|----------------------------------------------------------------------------|
| **Usage**                  | On class level to mark it as a Spring-managed bean            | On method level inside `@Configuration` class                              |
| **When to use**            | For auto-detectable classes (via scanning)                    | For 3rd-party or manually constructed objects                              |
| **Instantiation**          | Automatically created by component scanning                   | Returned manually from a method                                            |
| **Flexibility**            | Less flexible — only no-arg constructor supported              | Full control over instantiation logic                                      |
| **Configuration Class Needed** | No — can be used directly                                | Yes — must be inside a class annotated with `@Configuration`              |
| **Bean Name (Default)**    | Class name with lowercase first letter                        | Method name                                                                |
| **Custom Name Support**    | Yes: `@Component("name")`                                     | Yes: `@Bean(name = "name")`                                                |
| **Example**                | `@Component public class MyService {}`                        | `@Bean public MyService myService() { return new MyService(); }`          |

# Compare `@component` and `@service` , `@repository`, `@controller` ?
| **Annotation**     | **Layer**             | **Purpose & Usage**                                                                 | **Extra Features**                                                              |
|--------------------|------------------------|-------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| `@Component`       | Generic bean           | The base stereotype — used for general-purpose beans that don’t fall into a specific layer. | ❌ No extra features                                                             |
| `@Service`         | Service layer          | Used for business logic classes.                                                   | ✅ Clear semantic role (used in AOP, etc.)                                       |
| `@Repository`      | Persistence layer      | Used for DAO/repository classes (interact with database).                          | ✅ Enables automatic exception translation (`@Repository` → `DataAccessException`) |
| `@Controller`      | Web layer              | Used to define a Spring MVC controller that handles HTTP requests and returns a view. | ✅ Works with `@RequestMapping`, returns `ModelAndView`                         |
| `@RestController`  | Web layer (REST)       | Shortcut for `@Controller` + `@ResponseBody` — used for REST APIs.                 | ✅ Returns JSON/XML instead of a view                                            |

# Explain `@Autowired` , `@Qualifier` , `@Resource` and `@Primary` ?
## `@Autowired`
Tells Spring to automatically inject a bean by type.
## `@Qualifier`
Used with `@Autowired` to specify exactly which bean to inject when multiple candidates exist.
## `@Resource`
- Comes from JDK (javax.annotation.Resource), not Spring
- Injects by name first, then falls back to type
## `@Primary`
- Used to mark one bean as the default when multiple candidates of the same type exist.
- Can avoid needing `@Qualifier` if you want one bean to be injected by default.

# How many annotaitons we can use to inject a bean?
| **Annotation**         | **Source**                                     | **Injection Strategy**            | **Notes**                                                              |
|------------------------|------------------------------------------------|-----------------------------------|------------------------------------------------------------------------|
| `@Autowired`           | Spring (`org.springframework.beans.factory.annotation`) | By type (default)                 | Most common; can combine with `@Qualifier`                            |
| `@Qualifier`           | Spring                                          | By name (used with `@Autowired`)  | Disambiguates when multiple beans exist                                |
| `@Resource`            | Java EE (`javax.annotation.Resource`)          | By name first, then type          | Doesn’t need `@Autowired`                                              |
| `@Inject`              | JSR-330 (`javax.inject.Inject`)                | By type                           | Java standard; similar to `@Autowired`, but less flexible              |
| `@Value`               | Spring                                          | Injects literal values (e.g., from properties) | For `application.properties` values                             |
| `@Bean` method injection | Spring (`@Configuration` classes)             | Manually declares a bean          | Not for injecting but for defining beans                               |

# Explain and compare differnet types of denpendency injection, their pros and cons, and use cases.
| Type               | Description                                | How it works in Java/Spring                  |
|--------------------|--------------------------------------------|---------------------------------------------|
| Constructor Injection | Dependencies are passed via the constructor | Preferred method in Spring                  |
| Setter Injection      | Dependencies are passed via public setters  | Used when optional or changeable            |
| Field Injection       | Dependencies are injected directly into fields | Quick and easy, but less testable          |

## Constructor Injection
### Pros:
- Promotes immutability
- Clear what the dependencies are
- Makes testing easy (can pass mock dependencies)
### Cons:
- Can become messy if too many dependencies (too many parameters)
### Use When:
- Dependencies are required
- You want to follow best practice (preferred in Spring)

## Setter Injection
### Pros:
- Optional dependencies supported
- Easier to change dependencies after object creation
### Cons:
- Object may be incomplete until all setters are called
- Can allow accidental reconfiguration
### Use When:
- Dependency is optional
- You need to mutate the dependency after construction

## Field Injection
### Pros:
- Very concise and quick to use
- No boilerplate code
### Cons:
- Hard to unit test (you can't inject mocks easily without reflection)
- Not visible what dependencies the class needs
- Violates encapsulation (uses reflection under the hood)
### Use When:
- Quick prototyping
- You don't need to write tests or change dependencies manually

# If we have multiple beans for one type, how to set one is primary? and how Spring IOC picks one bean to inject if no primay, demo with code examples.
## Option 1: Use `@Primary` — Set a Default Bean
```java
public interface Engine {
    void start();
}

@Component
@Primary  // this bean will be injected by default
public class ElectricEngine implements Engine {
    public void start() {
        System.out.println("Starting Electric Engine");
    }
}

@Component
public class GasEngine implements Engine {
    public void start() {
        System.out.println("Starting Gas Engine");
    }
}

@Component
public class Car {
    private final Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        engine.start();
    }
}
```
- Spring sees two `Engine` beans — `ElectricEngine` and `GasEngine`.
- Because `ElectricEngine` is marked with `@Primary`, it’s chosen automatically.

### If No `@Primary` and No `@Qualifier` → ❌ Error
```java
@Component
public class Car {
    @Autowired
    private Engine engine;  // ❌ ERROR: No unique bean of type Engine
}
```
Spring throws:
- NoUniqueBeanDefinitionException: expected single matching bean but found 2: electricEngine,gasEngine

## Option 2: Use `@Qualifier` to Specify Which Bean to Inject
```java
@Component("electricEngine")
public class ElectricEngine implements Engine {
    public void start() { System.out.println("Electric engine"); }
}

@Component("gasEngine")
public class GasEngine implements Engine {
    public void start() { System.out.println("Gas engine"); }
}

@Component
public class Car {

    private final Engine engine;

    @Autowired
    public Car(@Qualifier("gasEngine") Engine engine) {
        this.engine = engine;
    }
}
```
Spring sees `@Qualifier("gasEngine")` and injects only that bean.

## Option 3: Inject All Beans of the Same Type
```java
@Component
public class EngineManager {
    @Autowired
    private List<Engine> engines;

    public void listEngines() {
        engines.forEach(Engine::start);
    }
}
```
This is great for strategy pattern or plugin systems.

# Compare BeanFactory and ApplicationContext in Spring framework?
## What is `BeanFactory`?
- Basic IoC container.
- Responsible for instantiating, configuring, and managing beans.
- Defined by the interface `org.springframework.beans.factory.BeanFactory`.
## What is `ApplicationContext`?
- A sub-interface of `BeanFactory` with extra features.
- Represents a more powerful container — used in most Spring applications.
- Defined by `org.springframework.context.ApplicationContext`.

| Feature                        | BeanFactory                                           | ApplicationContext                                |
|-------------------------------|-------------------------------------------------------|----------------------------------------------------|
| Interface Location            | `org.springframework.beans.factory.BeanFactory`       | `org.springframework.context.ApplicationContext`   |
| Inheritance                   | Base container                                        | Extends BeanFactory                                |
| Bean loading                  | Lazy (only when requested)                            | Eager (on startup)                                 |
| Internationalization (i18n)   | ❌ Not supported                                       | ✅ Supported via `MessageSource`                   |
| Event publishing              | ❌ Not supported                                       | ✅ Built-in `ApplicationEventPublisher`            |
| AOP support                   | ⚠️ Limited / manual                                    | ✅ Built-in                                         |
| Annotation scanning           | ❌ No support                                          | ✅ Yes, with `@ComponentScan`, etc.                |
| BeanPostProcessor & Aware     | Must be registered manually                          | ✅ Auto-detected                                   |
| Use case                      | Lightweight apps or memory-sensitive contexts         | Full-featured enterprise apps                      |
| Preferred in Spring Boot?     | ❌ No                                                  | ✅ Yes (always used)                               |

# Explain bean scope in Spring IOC? List bean scopes with explainations and code examples if possible.
In Spring, a bean scope tells the container how to manage the lifecycle and visibility of a bean — i.e., is it a singleton, prototype, per session, etc.

By default, all beans in Spring are singleton scoped.

| Scope        | Description                                               | Spring Context |
|--------------|-----------------------------------------------------------|----------------|
| singleton    | (Default) Single shared instance per Spring container     | Core           |
| prototype    | A new bean instance each time it’s requested              | Core           |
| request      | One instance per HTTP request (web only)                  | Web            |
| session      | One instance per HTTP session                             | Web            |
| application  | One instance per `ServletContext` (like global app-wide)  | Web            |
| websocket    | One instance per WebSocket session                        | Web            |

## `singleton` (default)
- Spring creates one single instance of the bean per container.
- All requests for this bean return the same instance.
```java
@Component
@Scope("singleton")
public class MyService { }
```
Even if you call `getBean(MyService.class)` multiple times, you'll get the same object.

## `prototype`
- Spring creates a new instance every time you request the bean.
- Useful for stateful beans or short-lived objects.
```java
@Component
@Scope("prototype")
public class MyService { }
```
Each call to `getBean()` returns a new object.

## `request` (web apps only)
- One instance per HTTP request.
- Typically used for controllers or request-specific data.
```java
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopedBean { }
```
Spring will create one bean per request, and discard it afterward.

## `session` (web apps only)
- One instance per HTTP session.
- Useful for storing user session data.
```java
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopedBean { }
```

## `application`
- One bean per ServletContext (like an application-wide singleton).
- Shared across all sessions and requests.
```java
@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class AppScopedBean { }
```

## `websocket` (Spring WebSocket only)
- One bean per WebSocket session.
```java
@Component
@Scope(value = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WebSocketScopedBean { }
```

# Write a Spring application that registers and autowires beans
## `DemoApplication.java`
```java
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

        System.out.println("=== Singleton Scope Test ===");
        SingletonService s1 = context.getBean(SingletonService.class);
        SingletonService s2 = context.getBean(SingletonService.class);
        System.out.println("Are singleton beans same? " + (s1 == s2));

        System.out.println("=== Prototype Scope Test ===");
        PrototypeService p1 = context.getBean(PrototypeService.class);
        PrototypeService p2 = context.getBean(PrototypeService.class);
        System.out.println("Are prototype beans same? " + (p1 == p2));

        context.getBean(Car.class).start();
    }
}
```
## Bean Interface & Implementations
```java
public interface Engine {
    void run();
}

@Component("electricEngine")
@Primary
class ElectricEngine implements Engine {
    public void run() {
        System.out.println("Electric engine running");
    }
}

@Component("gasEngine")
class GasEngine implements Engine {
    public void run() {
        System.out.println("Gas engine running");
    }
}
```
## Bean Registration via `@Bean`
```java
@Configuration
class CustomConfig {
    @Bean
    public ManualService manualService() {
        return new ManualService();
    }
}

class ManualService {
    public void print() {
        System.out.println("ManualService bean registered via @Bean");
    }
}
```
## Demonstrate Constructor Injection (by type or `@Primary`)
```java
@Component
class Car {
    private final Engine engine;

    // Constructor injection (picks @Primary or uses @Qualifier)
    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }

    public void start() {
        System.out.print("Car starting: ");
        engine.run();
    }
}
```
## Demonstrate Setter Injection (with `@Qualifier` by name)
```java
@Component
class Garage {
    private Engine engine;

    @Autowired
    public void setEngine(@Qualifier("gasEngine") Engine engine) {
        this.engine = engine;
    }

    public void test() {
        System.out.print("Garage test: ");
        engine.run();
    }
}
```
## Field Injection Demo
```java
@Component
class Dashboard {
    @Autowired
    ManualService manualService; // from @Bean

    public void display() {
        manualService.print();
    }
}
```
## Bean Scope Demo
```java
@Component
@Scope("singleton")
class SingletonService {
    public SingletonService() {
        System.out.println("SingletonService created");
    }
}

@Component
@Scope("prototype")
class PrototypeService {
    public PrototypeService() {
        System.out.println("PrototypeService created");
    }
}
```
| Feature              | Where It's Used                                      |
|----------------------|------------------------------------------------------|
| @Component           | `ElectricEngine`, `GasEngine`, `Car`, `Garage`, `Dashboard`, etc. |
| @Bean                | `ManualService` in `CustomConfig`                    |
| Constructor injection| `Car`                                                |
| Setter injection     | `Garage`                                             |
| Field injection      | `Dashboard`                                          |
| @Qualifier           | To inject `gasEngine`                                |
| @Primary             | To make `electricEngine` default                     |
| @Scope               | On `SingletonService` and `PrototypeService`         |

# Explain builder pattern with code examples.
The Builder Pattern is a creational design pattern used to construct complex objects step by step. It helps when an object has many optional parameters, or when you want to avoid constructor telescoping (constructors with many parameters).
```java
public class User {
    private final String name;
    private final String email;
    private final int age;
    private final boolean isAdmin;

    // Private constructor
    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
        this.isAdmin = builder.isAdmin;
    }

    // Static nested Builder class
    public static class Builder {
        private String name;
        private String email;
        private int age;
        private boolean isAdmin;

        public Builder(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder isAdmin(boolean isAdmin) {
            this.isAdmin = isAdmin;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public String toString() {
        return name + " | " + email + " | age: " + age + " | isAdmin: " + isAdmin;
    }
}
```
```java
public class Main {
    public static void main(String[] args) {
        User user = new User.Builder("Nina", "nina@example.com")
                         .age(25)
                         .isAdmin(true)
                         .build();

        System.out.println(user);
    }
}
```