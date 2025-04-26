## 2. Spring and Springboot
### Benefits of Spring Boot
1. **Auto-configuration**: Automatically configures beans based on dependencies in the classpath.
2. **Standalone Applications**: Run apps with `java -jar`, no external servlet container needed.
3. **Starter Dependencies**: Easy to use and manage dependencies via `spring-boot-starter-*`.
4. **Embedded Server**: No need to install and configure Tomcat or Jetty.
5. **Less Boilerplate Code**: Focus on business logic.
6. **Spring Initializr**: Quick project setup with Spring Boot Initializr 
7. **DevTools**: Enables hot reload and faster development cycle.
8. **Production-Ready**: Built-in support for monitoring and managing apps (Actuator).


| Feature                    | Spring              | Spring Boot                          |
|---------------------------|---------------------|--------------------------------------|
| Setup                     | Manual              | Auto-configured                      |
| Server                    | External required   | Embedded (Tomcat/Jetty/Undertow)     |
| Configuration             | Verbose             | Minimal, convention-based            |
| Dependency Management     | Manual              | Starters simplify it                 |
| Monitoring                | Manual              | Built-in via Actuator                |
| Microservices Friendly    | Limited             | Optimized for microservices          |

---

## 3. IOC and DI
### Inversion of Control (IoC)

- IoC is a design principle in which the control of object creation and dependency management is transferred from the program (developer) to the framework (like Spring).
- Instead of creating objects manually using `new`, the Spring container is responsible for creating and injecting dependencies.

### Dependency Injection (DI)
- DI is a pattern to implement IoC, where the dependencies of a class are injected by the framework (Spring) rather than being created internally.

- It allows loose coupling between classes and makes the system more modular and testable.

---

## 4. @CompnonentScan

- `@ComponentScan` is a Spring annotation used to specify the **base packages** to scan for annotated components such as:
  - `@Component`
  - `@Service`
  - `@Repository`
  - `@Controller`

- It tells the Spring container where to look for beans that need to be managed.

---

## 5. @SpringbootApplication

- `@SpringBootApplication` is a **convenience annotation** in Spring Boot that combines three core annotations:
```java
  @SpringBootApplication
  // is equivalent to:
  @Configuration
  @EnableAutoConfiguration
  @ComponentScan
```
@Configuration
- Marks the class as a source of bean definitions.
- Allows you to define beans using @Bean methods.

@EnableAutoConfiguration
- Tells Spring Boot to automatically configure your application based on the dependencies on the classpath. Like `spring-boot-starter-web`, it will auto-configure a Tomcat server and Spring MVC.

@ComponentScan
- Enables component scanning in the current package and its sub-packages.
- Finds @Component, @Service, @Repository, @Controller, etc.

---

## 6. Define a bean

### Using `@Component` and Its Specializations
- Annotations:
  - `@Component`
  - `@Service`
  - `@Repository`
  - `@Controller`

```java
@Component
public class MyComponent {
    public void hello() {
        System.out.println("Hello world!");
    }
}
```
### Using @Bean Inside a @Configuration Class
```java
@Configuration
public class AppConfig {

    @Bean
    public MyBean myBean() {
        return new MyBean();
    }
}
```
### Using XML Configuration
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       ...>
    <bean id="myXmlBean" class="com.example.MyXmlBean"/>
</beans>
```

---

## 7. @Component and @Bean 

### `@Component`
- The default bean name is the class name with the first letter in lowercase.

### `@Bean`
- The default bean name is the **method name**.

| Feature            | `@Component`                                 | `@Bean`                                       |
|--------------------|----------------------------------------------|-----------------------------------------------|
| Declaration Place | On the **class**                             | Inside a method in a **`@Configuration`** class |
| Detection         | Discovered by **component scanning**         | Manually declared in configuration class      |
| Use Case          | For general components and auto-detection    | For third-party libraries or complex logic     |
| Flexibility       | Limited customization during creation        | Full control over bean instantiation          |
| Scope Support     | Yes (with `@Scope`)                          | Yes (with `@Scope`)                           

---

## 8. @Componentand, @Service,@Repository,@Controller

| Annotation      | Role in Application      | Typical Layer           | Purpose |
|----------------|--------------------------|--------------------------|---------|
| `@Component`    | Generic Spring-managed bean | Utility / Helper classes | General-purpose |
| `@Service`      | Marks a service class    | Service Layer            | Business logic, service operations |
| `@Repository`   | Marks a DAO class        | Data Access Layer        | Handles persistence, wraps exceptions |
| `@Controller`   | Marks a web controller   | Presentation Layer       | Handles HTTP requests in MVC |

---

## 9. @Autowired, @Qualifier, @Resource, and @Primary

### `@Autowired`

- Used to automatically inject a bean by **type**.
- Can be applied to constructors, setters, or fields.
- If multiple candidates are found, you’ll need `@Qualifier` or `@Primary` to resolve ambiguity.

### `@Qualifier`

- Used **with `@Autowired`** to specify the exact bean to inject **by name**.
- Helps when there are **multiple beans of the same type**.

### `@Resource`

- Injects by **name first**, then by type if no match.
- Part of **Java EE**, not Spring-specific.
- Similar to combining `@Autowired` and `@Qualifier`.

### `@Primary`

- Marks a bean as the **default choice** when multiple beans of the same type are present.
- Used in conjunction with `@Autowired`.

---

## 10. Inject Beans annotations

| Annotation     | Description |
|----------------|-------------|
| `@Autowired`   | Spring-specific annotation that injects bean **by type**. |
| `@Qualifier`   | Works with `@Autowired` to inject a bean **by name**. |
| `@Resource`    | Java EE annotation that injects bean **by name first**, then by type. |
| `@Inject`      | JSR-330 annotation (Java standard) that injects **by type**, similar to `@Autowired`. |
| `@Value`       | Injects a **primitive or string value**, e.g., from properties. |
| `@Primary`     | Designates a bean as the **default** when multiple candidates exist. |
| `@Bean`        | Used to manually **define** and inject a bean from a `@Configuration` class. |

---

## 11: Compare different types of dependency injection

### Types of Dependency Injection in Spring

1. **Constructor Injection**
2. **Setter Injection**
3. **Field Injection**

### Constructor Injection

```java
@Component
public class OrderService {
    private final UserRepository userRepository;

    @Autowired
    public OrderService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

#### Pros:
- Promotes immutability.
- Ensures required dependencies are not null.
- Easier for unit testing (can use constructor args in mocks).

#### Cons:
- Can get verbose with many dependencies.

#### Use Case:
- When all dependencies are **required** and must be available at instantiation.

### Setter Injection

```java
@Component
public class OrderService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

#### Pros:
- More flexible, can inject optional dependencies.
- Can change dependencies after construction (not often recommended).

#### Cons:
- Object can be in an incomplete state if the setter is never called.
- Harder to ensure required dependencies.

#### Use Case:
- When some dependencies are **optional** or when using frameworks/tools that support property injection.

### Field Injection

```java
@Component
public class OrderService {
    @Autowired
    private UserRepository userRepository;
}
```

#### Pros:
- Most concise and readable.
- No boilerplate constructor or setter code.

#### Cons:
- Not recommended for testing (harder to mock).
- Breaks **encapsulation** and immutability.
- Difficult to refactor.

#### Use Case:
- Quick prototyping or small internal tools where testability is not a priority.

---

## 12. Primary select

### Use `@Primary` to Set the Default Bean

If there are multiple beans of the same type, Spring will throw an error **unless** one is marked with `@Primary`, or you use `@Qualifier`.

```java
@Component
@Primary
public class RegularUserService implements UserService {
    public String getUserType() {
        return "Regular";
    }
}
```

```java
@Component("vipUserService")
public class VipUserService implements UserService {
    public String getUserType() {
        return "VIP";
    }
}
```

```java
@Service
public class UserController {
    @Autowired
    private UserService userService;// Injects RegularUserService because it's marked as @Primary
}
```

### Use `@Qualifier` to Explicitly Choose

```java
@Service
public class AdminController {
    @Autowired
    @Qualifier("vipUserService")
    private UserService userService; // Injects VipUserService by name
}
```

### If No `@Primary` and No `@Qualifier`

Spring will throw:
```
NoUniqueBeanDefinitionException:
expected single matching bean but found 2
```

---

## 13. `BeanFactory` and `ApplicationContext`

| Feature                     | `BeanFactory`                   | `ApplicationContext`                         |
|-----------------------------|----------------------------------|-----------------------------------------------|
| Bean Loading                | Lazy                             | Eager (by default)                            |
| Internationalization (i18n) | Not supported                 | Supported                                  |
| Application Events          | No support                    | Supports event listeners                   |
| BeanPostProcessor           | Manual setup                     | Auto-detected                                |
| AOP Integration             | Limited                          | Full support                                 |
| Environment, Profiles       | No support                    | Full support                               |
| Use Case                    | Lightweight, low-memory devices | Full-featured enterprise applications         |

---

## 14. Bean scope in Spring IoC.

---

### What is Bean Scope?

- Bean scope defines the **lifecycle** and **visibility** of a bean within the Spring container.
- Spring supports multiple scopes for beans—some for standard applications and others for web contexts.

---

### Common Bean Scopes

| Scope         | Description                                                                 |
|---------------|-----------------------------------------------------------------------------|
| `singleton`   | Default. Single shared instance per Spring container.                       |
| `prototype`   | New instance every time it's requested.                                     |
| `request`     | One bean per HTTP request (Web only).                                       |
| `session`     | One bean per HTTP session (Web only).                                       |
| `application` | One bean per ServletContext (Web only).                                     |
| `websocket`   | One bean per WebSocket session (Web only).                                  |

---

### Singleton Scope (default)

```java
@Component
@Scope("singleton")
public class MySingletonBean {
}
```

### Prototype Scope

```java
@Component
@Scope("prototype")
public class MyPrototypeBean {
}
```

### Request Scope (Web Applications)

```java
@Component
@Scope("request")
public class MyRequestBean {
}
```
### Session Scope (Web Applications)

```java
@Component
@Scope("session")
public class MySessionBean {
}
```

### Application Scope (Web Applications)

```java
@Component
@Scope("application")
public class MyAppBean {
}
```

### WebSocket Scope (Web Applications)

```java
@Component
@Scope("websocket")
public class MyWebSocketBean {
}
```

---

## 16. Builder Parttern

```java
public class User {
    private String name;
    private int age;
    private String address;

    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.address = builder.address;
    }

    public static class Builder {
        private String name;
        private int age;
        private String address;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + ", address='" + address + "'}";
    }
}
```

















