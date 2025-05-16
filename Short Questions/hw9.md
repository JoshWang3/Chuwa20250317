1. List all of the annotations you learned from class and homework to annotaitons.md (your own 
cheatsheet).
https://www.notion.so/Java-Annotations-1f59e620eb698072a469f8ec2c89a8ac?pvs=4

2. Compare Spring and Springboot? What are the benfits of Srpingboot?
   **Spring Framework**:

* A comprehensive programming and configuration model for Java applications.
* Requires boilerplate code and XML configurations.

**Spring Boot**:

* Built on top of Spring Framework.
* Offers auto-configuration, embedded servers, production-ready features.

**Benefits of Spring Boot**:

* Rapid development
* No XML configuration
* Built-in server (Tomcat, Jetty)
* Auto dependency management
* Production-ready metrics and health checks


3. What is IOC and What is DI?
* **IoC (Inversion of Control)**: Framework manages object creation and lifecycle.
* **DI (Dependency Injection)**: One form of IoC — injects dependencies instead of constructing them manually.


4. What is @CompnonentScan ?
* Scans specified packages to find and register classes annotated with `@Component`, `@Service`, `@Repository`, and `@Controller` as Spring Beans.


5. What is @SpringbootApplication ?
* A convenience annotation combining:

    * `@Configuration`
    * `@EnableAutoConfiguration`
    * `@ComponentScan`

```java
@SpringBootApplication
public class App {}
```


6. How many ways to define a bean? Provide code examples.
   [1] **@Component**:

```java
@Component
public class MyService {}
```

[2] **@Bean**:

```java
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```

[3] **XML (legacy)**:

```xml
<bean id="myService" class="com.example.MyService" />
```

7. What is default bean name for @Component and @Bean ? Also compare @Component and @Bean.
* **@Component**: default is class name with first letter lowercased (e.g., `myService`)
* **@Bean**: default is method name

| @Component      | @Bean                        |
| --------------- | ---------------------------- |
| Used on class   | Used in @Configuration class |
| Auto-discovered | Manually defined             |
| No method logic | Full control via method      |


8. Compare @component and @service , @repository , @controller ?

All are specializations of `@Component`:

* `@Service`: Business logic
* `@Repository`: DAO, enables exception translation
* `@Controller`: MVC web controller
* `@Component`: Generic

9. Explain @Autowired , @Qualifier , @Resource and @Primary ?
* `@Autowired`: Inject by type
* `@Qualifier`: Specify bean name when multiple beans match
* `@Resource`: Inject by name first, fallback to type
* `@Primary`: Mark default bean when multiple types exist


10. How many annotaitons we can use to inject a bean?

* `@Autowired`
* `@Resource`
* `@Inject` (JSR-330)
* `@Qualifier`
* `@Primary`


11. Explain and compare differnet types of denpendency injection, their pros and cons, and use cases.

##### Constructor Injection

```java
@Autowired
public MyComponent(MyService service) {}
```
* Required dependencies
* Immutable
* Recommended for mandatory dependencies

##### Setter Injection
```java
@Autowired
public void setService(MyService service) { this.service = service; }
```
* Optional dependencies

##### Field Injection
```java
@Autowired
private MyService service;
```
* Short and simple, but hard to test


12. If we have multiple beans for one type, how to set one is primary? and how Spring IOC picks one bean to 
inject if no primay, demo with code examples.
### ✅ If We Have Multiple Beans of the Same Type: How to Control Which One is Injected?

---

### 🔹 Scenario

You have **multiple beans of the same interface/type**. Spring’s IOC container needs a way to decide **which one to inject** when doing `@Autowired`.

---

### Option 1: Use `@Primary`

Mark one bean as the default.

```java
public interface MessageService {
    void send(String message);
}

@Component
@Primary
public class EmailService implements MessageService {
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}

@Component
public class SMSService implements MessageService {
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

@Component
public class NotificationController {
    @Autowired
    private MessageService messageService;

    public void notifyUser() {
        messageService.send("Hello User");
    }
}
```
Because `EmailService` is marked `@Primary`, Spring injects it when there’s ambiguity.


### Option 2: Use `@Qualifier` to Specify the Bean by Name

```java
@Component
public class NotificationController {
    @Autowired
    @Qualifier("sMSService")
    private MessageService messageService;
}
```


13. Compare BeanFactory and ApplicationContext in Spring framework?

Both `BeanFactory` and `ApplicationContext` are interfaces in Spring used for dependency injection and bean management. However, `ApplicationContext` builds on top of `BeanFactory` and provides many additional features.

| Feature                        | BeanFactory                           | ApplicationContext                          |
| ------------------------------ | ------------------------------------- | ------------------------------------------- |
| Definition                     | Basic container for managing beans    | Full-featured container and framework entry |
| Bean Initialization            | Lazy (only when requested)            | Eager (at application startup)              |
| Internationalization Support   | Not supported                         | Supported through MessageSource             |
| Event Handling                 | Not supported                         | Supports event publication and listening    |
| AOP and Annotation Scanning    | Not supported                         | Supported                                   |
| Web Application Integration    | Not used in web context               | Used in WebApplicationContext               |
| BeanPostProcessor Registration | Manual                                | Automatic                                   |
| Use Case                       | Lightweight apps or simple test cases | Enterprise and Spring Boot applications     |



In Spring Boot applications, `ApplicationContext` is always used since it supports autoconfiguration, annotation scanning, and lifecycle events which are fundamental for building production-grade systems.


14. Explain bean scope in Spring IOC? List bean scopes with explainations and code examples if possible.

In Spring, **bean scope** defines **how many instances of a bean** are created and **how long they live** within the container (IoC context). By default, all beans are **singleton scoped**, but there are multiple scopes available depending on the application type.

##### **singleton** (default)

* **Only one instance** of the bean is created per Spring container.
* All requests for the bean return the **same object**.

```java
@Component
@Scope("singleton") // optional, because it's the default
public class MySingletonService {}
```

```java
// Both variables will point to the same object
@Autowired MySingletonService s1;
@Autowired MySingletonService s2;
```

---

#####  **prototype**

* A **new instance is created every time** the bean is requested from the container.

```java
@Component
@Scope("prototype")
public class MyPrototypeService {}
```

```java
@Autowired MyPrototypeService p1;
@Autowired MyPrototypeService p2;  // p1 != p2
```

##### **request** (Web only)

* One bean instance **per HTTP request**.
* Only works in a **web application context**.

```java
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopedBean {}
```

Each HTTP request will receive a different instance of `RequestScopedBean`.


##### **session** (Web only)

* One bean instance **per HTTP session**.

```java
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopedBean {}
```

Different users or sessions will have their own instance.


#####  **application** (Web only)

* One bean instance **per ServletContext** (i.e., per web app).

```java
@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class AppScopedBean {}
```

#####  **websocket** (Web only)

* One instance per WebSocket session.

```java
@Component
@Scope(value = \"websocket\", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WebSocketScopedBean {}
```

| Scope         | Description                   | Context  |
| ------------- | ----------------------------- | -------- |
| `singleton`   | One shared instance (default) | All apps |
| `prototype`   | New instance every time       | All apps |
| `request`     | One per HTTP request          | Web only |
| `session`     | One per HTTP session          | Web only |
| `application` | One per servlet context       | Web only |
| `websocket`   | One per WebSocket session     | Web only |


15. Write a Spring application that registers and autowires beans, 
Demo different types of dependency injection
Demo bean scopes.
Demo dependency injection by type and by name, when there's ambiguity in bean definition.
Demo bean registration by both @Component and @Bean


16. Explain builder pattern with code examples.

The **Builder Pattern** is a creational design pattern used to construct complex objects step by step. It avoids the need for telescoping constructors by allowing more readable and flexible object creation.

When to Use:
* When an object has many optional fields.
* When constructors with many parameters become confusing.

```java
public class User {
    private String name;
    private int age;
    private String email;
    private String phone;

    // Private constructor to enforce the use of Builder
    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    // Static inner Builder class
    public static class Builder {
        private String name;
        private int age;
        private String email;
        private String phone;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + ", email='" + email + "', phone='" + phone + "'}";
    }
}
```


```java
public class Demo {
    public static void main(String[] args) {
        User user = new User.Builder()
                        .setName("Alice")
                        .setAge(30)
                        .setEmail("alice@example.com")
                        .build();

        System.out.println(user);
    }
}
```
**Advantages**:

* Improves readability
* Avoids constructor overloading
* Flexible construction of objects
