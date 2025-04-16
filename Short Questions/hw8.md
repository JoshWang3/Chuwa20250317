# 1. List all of the annotations you learned from class and homework to annotaitons.md (your own cheatsheet).

# 2. Compare Spring and Springboot? What are the benfits of Srpingboot?

Spring is a framework for building Java applications. Its main focus was on inversion of control. Spring Boot is based on Spring. It adds multiple helpful features to make spring easier and faster by avoiding boilerplate code. These features include embedded server like Tomcat, auto-configuration and meta-annotations(`SpringBootApplication`). Spring Boot in general is easier to use and faster to in development.

| Feature                          | Spring                                          | Spring Boot                                                   |
| -------------------------------- | ----------------------------------------------- | ------------------------------------------------------------- |
| **Setup**                  | Manual: configure XML or Java-based configs     | Auto-configuration, minimal setup                             |
| **Boilerplate Code**       | More code required (e.g., servlet, beans setup) | Reduces boilerplate with sensible defaults                    |
| **Deployment**             | Needs external servlet container (e.g., Tomcat) | Comes with embedded servers (Tomcat, Jetty, etc.)             |
| **Dependencies**           | Manual dependency management                    | Starter dependencies simplify setup                           |
| **Configuration**          | Verbose and repetitive                          | Simplified using `application.properties` or `.yml` files |
| **Focus**                  | General-purpose Spring framework                | Rapid development on top of Spring                            |
| **Build Tool Integration** | Works with Maven/Gradle with custom configs     | Built-in Maven/Gradle plugin support                          |

# 3. What is IOC and What is DI?

IoC (Inversion of Control) is a concept that the control of object creation is no longer by the program. It is instead managed by the framework.

DI (Dependency Injection) is a design pattern that implements IoC. It means injecting required objects (dependencies) into a class, rather than the class creating them.

# 4. What is @CompnonentScan ?

`@CompnonentScan` tells Spring to look for **components (like** **`@Component`,** **`@Service`,** **`@Repository`,** **`@Controller`)** so it can register them as beans in the application context.

It scans the specified packages (and their subpackages) for classes annotated with component stereotypes and registers them as beans.

```java
@Configuration
@ComponentScan(basePackages = "com.example.myapp")
public class AppConfig {
}
```

# 5. What is @SpringbootApplication ?

**`@SpringbootApplication`** is a meta annotation that contains three separate annotations.

* **`@Configuration`** — Marks the class as a source of bean definitions.
* **`@EnableAutoConfiguration`** — Tells Spring Boot to automatically configure beans based on the classpath, other beans, and settings (e.g. DataSource, MVC config).
* **`@ComponentScan`** — Automatically scans the current package and sub-packages for** **`@Component`,** **`@Service`,** **`@Repository`, etc.

# 6. How many ways to define a bean? Provide code examples.

There are three main ways:

1. Using `@Component` and Stereotype Annotations
   ```java
   @Component
   public class MyService {
       public void doSomething() {
           System.out.println("Doing something...");
       }
   }

   ```
2. Using `@Bean` in a `@Configuration` Class
   ```java
   @Configuration
   public class AppConfig {

       @Bean
       public MyService myService() {
           return new MyService();
       }
   }

   ```
3. Using XML Configuration (Old School)
   ```xml
   <bean id="myService" class="com.example.MyService"/>
   ```

# 7. What is default bean name for @Component and @Bean ? Also compare @Component and @Bean.

`@Component`: Class name with lowercase first character.

`@Bean`: method name.

| Feature                   | `@Component`                                  | `@Bean`                                      |
| ------------------------- | ----------------------------------------------- | ---------------------------------------------- |
| Annotation Level          | Class-level                                     | Method-level inside a `@Configuration` class |
| Instantiation             | Automatically via component scanning            | Manually in method body                        |
| Use Case                  | For application-defined classes (e.g. services) | For third-party classes or custom logic        |
| Default Bean Name         | Class name with lowercase first letter          | Method name                                    |
| Configuration Flexibility | Limited                                         | High (you control every aspect)                |
| Scanning Requirement      | Requires `@ComponentScan`                     | No scanning required                           |
| Dependency Injection      | Typically constructor injection                 | You define how dependencies are injected       |
| Example                   | `@Component public class MyService {}`        | `@Bean public MyService myService() {}`      |

# 8. Compare @component and @service , @repository, @controller ?

`@Component` is a generic type where the rest are sub types.

| Annotation      | Purpose                                        | Layer Used In     | Special Behavior                                                                                     | Scanned by `@ComponentScan`? |
| --------------- | ---------------------------------------------- | ----------------- | ---------------------------------------------------------------------------------------------------- | ------------------------------ |
| `@Component`  | Generic stereotype for any Spring-managed bean | Any layer         | None special                                                                                         | ✅ Yes                         |
| `@Service`    | Marks a service class (business logic)         | Service layer     | Indicates it's a service (semantic clarity)                                                          | ✅ Yes                         |
| `@Repository` | Marks a DAO class (data access logic)          | Persistence layer | Enables automatic exception translation (e.g. from JDBC exceptions to Spring’s DataAccessException) | ✅ Yes                         |
| `@Controller` | Marks a web controller                         | Web layer (MVC)   | Used with Spring MVC to handle web requests                                                          | ✅ Yes                         |

# 9. Explain @Autowired , @Qualifier , @Resource and @Primary ?

`@Autowired` automatically injects a bean by type. It can be used with different injection methods.

`@Qualifier` is used with `@Autowired` to specify which bean to inject while multiple beans with the same type exist.

`@Resource` injects dependency by **name first** , then by type if name not found. It is an alternative to `@Autowired + @Qualifier`.

`@Primary` marks a bean as the **default choice** when multiple beans of the same type exist.

# 10. How many annotaitons we can use to inject a bean?

`@Autowired`: Most common one, injects by type.

`@Resource`: Injects by name -> type.

`@Inject`: Injects by type.

# 11. Explain and compare differnet types of denpendency injection, their pros and cons, and use cases.

Constructor: Most commonly used.

```java
@Component
public class OrderService {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
```

Setter: Dependencies are set via setter methods .

```java
@Component
public class OrderService {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

```


Field: Dependencies are injected directly into fields.

```java
@Component
public class OrderService {
    @Autowired
    private UserService userService;
}

```


| Type        | Immutability | Optional Support | Testability | Boilerplate | Best Use Case              |
| ----------- | ------------ | ---------------- | ----------- | ----------- | -------------------------- |
| Constructor | ✅ Yes       | ❌ No            | ✅ Easy     | ⚠️ Yes    | Mandatory dependencies     |
| Setter      | ❌ No        | ✅ Yes           | ✅ Moderate | ⚠️ Some   | Optional or legacy support |
| Field       | ❌ No        | ✅ Yes           | ❌ Hard     | ✅ Clean    | Simple apps or quick dev   |

# 12. If we have multiple beans for one type, how to set one is primary? and how Spring IOC picks one bean to inject if no primay, demo with code examples.

We can use `@Primary` annotation to mark the default bean. 

If there is no `@Primary` and we do not use `@Qualifier`, Spring will throw an exception.

```java
@Component
public class DefaultUserService implements UserService {}

@Component
public class VipUserService implements UserService {}

@Component
public class OrderService {
    @Autowired
    private UserService userService; // ❌ ERROR: two beans exist, no primary
}

```

Fix with either:

```java
@Autowired
@Qualifier("vipUserService")
private UserService userService;
```

Or:

```java
@Primary
@Component
public class DefaultUserService implements UserService {}
```

# 13. Compare BeanFactory and ApplicationContext in Spring framework?

`BeanFactory` is a very basic, lightweighted DI container, where `ApplicationContext` is more powerful and much easier to use.

| Feature                | BeanFactory                            | ApplicationContext                      |
| ---------------------- | -------------------------------------- | --------------------------------------- |
| Interface Type         | Root interface                         | Sub-interface of `BeanFactory`        |
| Eager Initialization   | ❌ No (lazy loading)                   | ✅ Yes (eager loading of singletons)    |
| Annotation Support     | ❌ Limited                             | ✅ Full support                         |
| AOP Support            | ❌ Manual configuration                | ✅ Automatic support                    |
| Internationalization   | ❌ Not supported                       | ✅ Supported                            |
| Event Handling         | ❌ Not supported                       | ✅ Built-in event handling              |
| BeanPostProcessor Auto | ❌ Needs manual registration           | ✅ Handled automatically                |
| Resource Loading       | Basic                                  | Advanced (i18n, file, URL, etc.)        |
| Use Case               | Lightweight or memory-constrained apps | Standard enterprise Spring applications |

# 14. Explain bean scope in Spring IOC? List bean scopes with explainations and code examples if possible.


| Scope           | Description                                                 | Default | Usage                                                    |
| --------------- | ----------------------------------------------------------- | ------- | -------------------------------------------------------- |
| `singleton`   | One shared instance per Spring container.                   | ✅ Yes  | Default for Spring beans. Used for stateless services.   |
| `prototype`   | A new instance is created every time the bean is requested. | ❌ No   | Used when each use requires a fresh instance.            |
| `request`     | One bean per HTTP request. (Web-aware only)                 | ❌ No   | Used in web apps for request-specific data.              |
| `session`     | One bean per HTTP session. (Web-aware only)                 | ❌ No   | Used for storing user session data.                      |
| `application` | One bean per ServletContext. (Web-aware only)               | ❌ No   | Used for application-wide shared configuration or state. |
| `websocket`   | One bean per WebSocket session. (Web-aware only)            | ❌ No   | Used when working with WebSocket messaging.              |


```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.context.annotation.ScopedProxyMode;

// Singleton scope (default)
@Component
public class MySingletonBean {
    // Shared across the entire Spring container
}

// Prototype scope
@Component
@Scope("prototype")
public class MyPrototypeBean {
    // A new instance is created on every request
}

// Request scope (web applications only)
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyRequestScopedBean {
    // Lives for a single HTTP request
}

// Session scope (web applications only)
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MySessionScopedBean {
    // Lives for the duration of an HTTP session
}

// Application scope (web applications only)
@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyApplicationScopedBean {
    // Shared across the entire ServletContext
}

// WebSocket scope (web applications only)
@Component
@Scope(value = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyWebSocketScopedBean {
    // Lives for the duration of a WebSocket session
}

```

# 15. Write a Spring application that registers and autowires beans,

- Demo different types of dependency injection
- Demo bean scopes.
- Demo dependency injection by type and by name, when there's ambiguity in bean definition.
- Demo bean registration by both @Component and @Bean

# 16. Explain builder pattern with code examples.

The **Builder Pattern** is a creational design pattern used to construct complex objects step by step. Instead of creating the object manually, using a factory to be responsible for the creation of object. It improves maintainability and makes code cleaner.

```java
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
}


@Service
public class UserService {

    public UserResponse getUserById(Long id) {
        // fake DB result
        return UserResponse.builder()
            .id(id)
            .name("John Doe")
            .email("john@example.com")
            .build();
    }
}

```
