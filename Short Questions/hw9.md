# hw9
### 1. List all of the annotations you learned from class and homework to annotaitons.md (your own cheatsheet)
Updated annotations.md in the branch "zeliang_yin/hw6".
### 2. Compare Spring and Springboot? What are the benfits of Srpingboot?
| Feature | Spring Framework | Spring Boot |
| --- | --- | --- |
| Definition | A comprehensive, modular framework for Java EE apps | A tool built on top of Spring to simplify setup and development |
| Configuration | Requires a lot of manual configuration (XML/Java) | Comes with auto-configuration out of the box |
| Server Setup | Needs to deploy manually to a servlet container | Comes with embedded servers (Tomcat, Jetty) |
| Dependency Management | Manual (you specify all dependencies) | Uses "starter" dependencies to simplify dependency setup |
| Production Ready | Requires additional setup | Comes with production-ready features like metrics, health checks |
- Benfits of Srpingboot:
    1. **Fast Development**: Auto-configuration and starters speed up the setup process.
    2. **Embedded Server**: No need to deploy WAR files to external servers.
    3. **Reduced Boilerplate**: Annotations and smart defaults make coding faster and cleaner.
    4. **Microservices Ready**: Built with microservice architecture in mind.
    5. **Built-in Monitoring**: Spring Boot Actuator gives endpoints for health, metrics, and more.
    6. **Better Dependency Management**: Starter dependencies simplify Maven/Gradle configuration.
### 3. What is IOC and What is DI?
1. **IOC (Inversion of Control)**: It's a design principle where the control of object creation and dependencies is transferred from the programmer to the framework. With IoC, the framework creates and injects objects for you.
2. **DI (Dependency Injection)**: DI is a specific way to achieve IoC where the framework injects the dependencies (objects that a class needs) into the class automatically. We use `@Autowired` to implement the injection.
```java
@Component
public class Engine {
    public void start() {
        System.out.println("Engine started.");
    }
}

@Component
public class Car {

    private final Engine engine;

    // Constructor Injection
    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        engine.start();
        System.out.println("Car is driving...");
    }
}
```
### 4. What is `@CompnonentScan`?
A Spring annotation used to tell the framework where to look for components (like classes annotated with `@Component`, `@Service`, `@Repository`, `@Controller`, etc.) to automatically register them as beans in the Spring context.  
Spring only scans beans in the same package as the main application class and its sub-packages.
If your components are outside that, you need @ComponentScan to include them manually.
```java
@ComponentScan("com.example.myapp")
public class AppConfig {
    // This class will scan com.example.myapp and all its subpackages
}
```
### 5. What is `@SpringbootApplication`?
A convenience annotation in Spring Boot that combines three important annotations (`@Configuration`, `@EnableAutoConfiguration`, `@ComponentScan`) to quickly configure and bootstrap a Spring Boot application.
| Annotation | Role |
| --- | --- |
| `@Configuration` | Marks the class as a source of Spring bean definitions. |
| `@EnableAutoConfiguration` | Automatically configures your app based on dependencies in your classpath. |
| `@ComponentScan` | Automatically scans the package and sub-packages for Spring components. |
```java
@SpringBootApplication
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
```
### 6. How many ways to define a bean? Provide code examples.
1. Using `@Component` (and Stereotype Annotations like `@Service`, `@Repository`, `@Controller`)
    ```java
    @Component
    public class MyComponent {
        public void doSomething() {
            System.out.println("Hello from MyComponent");
        }
    }
    ```
2. Using `@Bean` inside a `@Configuration` class: This is useful for third-party or manually configured beans.
    ```java
    @Configuration
    public class AppConfig {
        
        @Bean
        public MyComponent myComponent() {
            return new MyComponent();
        }
    }
    ```
3. Using XML Configuration
    ```xml
    <!-- applicationContext.xml -->
    <beans xmlns="http://www.springframework.org/schema/beans" ...>
        <bean id="myComponent" class="com.example.MyComponent"/>
    </beans>
    ```
    ```java
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    MyComponent mc = context.getBean(MyComponent.class);
    ```
### 7. What is default bean name for `@Component` and `@Bean`? Also compare `@Component` and `@Bean`.
1. Default bean name for `@Component`: The class name with the first letter in lowercase.
    ```java
    @Service
    public class MyService {
    }
    ```
    Default bean name: "myService"
2. Default bean name for `@Bean`: The method name that returns the bean.
    ```java
    @Configuration
    public class AppConfig {
        @Bean
        public MyService customService() {
            return new MyService();
        }
    }
    ```
    Default bean name: "customService"

| Feature | `@Component` | `@Bean` |
| --- | --- | --- |
| Applied On | Class | Method inside a `@Configuration` class |
| Instantiation | Automatically by Spring via scanning | Manually in Java code |
| Use Case | Classes under your control | 3rd-party classes or when more control is needed |
| Flexibility | Limited customization | Full control over bean construction |
| Default Bean Name | Class name (first letter lowercase) | Method name |
| Part of | Component-scanning system (`@ComponentScan`) | Java Config system (`@Configuration`) |
### 8. Compare `@component` and `@service`, `@repository`, `@controller`?
They are used to mark classes as Spring-managed components and get picked up during component scanning via `@ComponentScan`.  
`@Service`, `@Repository`, and `@Controller` are specializations of `@Component`.
| Annotation | Layer | Purpose |
| --- | --- | --- |
| `@Component` | General | Base annotation for any component |
| `@Service` | Service Layer | Indicates business logic/service class |
| `@Repository` | Data Access Layer | Marks DAO classes; enables exception translation |
| `@Controller` | Web Layer | Handles web requests (MVC controller) |
| `@RestController` | Web Layer | Same as `@Controller` + `@ResponseBody` for REST APIs |
### 9. Explain `@Autowired`, `@Qualifier`, `@Resource` and `@Primary`?
1. `@Autowired`: Spring’s default DI annotation. It works by type. If multiple candidates exist, Spring throws an exception unless further specified.
    ```java
    @Autowired
    private UserService userService;
    ```
2. `@Qualifier`: Used along with `@Autowired` to resolve ambiguity when multiple beans of the same type exist. It specifies which exact bean name to inject.
    ```java
    @Autowired
    @Qualifier("mySqlRepo")
    private UserRepository userRepository;
    ```
3. `@Resource`: Injects by name first, then by type if name not found. It can't be used for constructor injection.
    ```java
    @Resource(name = "userService")
    private UserService service;
    ```
4. `@Primary`: Used when multiple beans of the same type exist and you want one of them to be the default for autowiring.
    ```java
    @Primary
    @Bean
    public UserRepository mongoRepo() {
        return new MongoUserRepository();
    }
    ```
    ```java
    @Autowired
    private UserRepository userRepository;
    ```
### 10. How many annotaitons we can use to inject a bean?
| Annotation | Provided By | Injects By | Qualifier Support | Constructor Support |
| --- | --- | --- | --- | --- |
| `@Autowired` | Spring | Type | Yes (`@Qualifier`) | Yes |
| `@Qualifier` | Spring | Name (with `@Autowired`) | - | - |
| `@Primary` | Spring | Type (default) | - | Yes |
| `@Resource` | JSR-250 (Java) | Name → Type | No | No |
| `@Inject` | JSR-330 (Java) | Type | With `@Named` | Yes |
### 11. Explain and compare different types of denpendency injection, their pros and cons, and use cases.
1. **Constructor Injection**:
    - Pros:
        - Immutable dependencies (good for thread safety and clarity).
        - Encourages mandatory dependency declaration.
        - Supports final fields.
    - Cons:
        - Can cause constructor explosion.
    ```java
    @Service
    public class UserService {
        private final UserRepository userRepository;

        @Autowired
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }
    }
    ```
2. **Setter Injection**:
    - Pros:
        - More flexible; can inject optional dependencies.
        - Can change the dependency after construction.
    - Cons:
        - Object is partially constructed until all setters are called.
        - More risk of null pointer exceptions.
        - Not ideal for required dependencies.
    ```java
    @Service
    public class UserService {
        private UserRepository userRepository;

        @Autowired
        public void setUserRepository(UserRepository userRepository) {
            this.userRepository = userRepository;
        }
    }
    ```
3. **Field Injection**:
    - Pros:
        - Very concise, quick to write.
        - Easy to read
    - Cons:
        - Not testable without reflection or special frameworks.
        - Hidden dependencies — you can't see them in the constructor.
        - Breaks encapsulation and immutability.
    ```java
    @Service
    public class UserService {
        @Autowired
        private UserRepository userRepository;
    }
    ```
| Type | Visibility | Testability | Good for Required | Supports Immutability | Best Use Case |
| --- | --- | --- | --- | --- | --- |
| Constructor | High | Great | Yes | Yes | Required dependencies, clean design |
| Setter | Medium | Good | No | No | Optional or reconfigurable dependencies |
| Field | Low | Poor | No | No | Quick setups, prototypes |
### 12. If we have multiple beans for one type, how to set one is primary? and how Spring IOC picks one bean to inject if no primay, demo with code examples.
1. Use `@Primary` to Set a Default Bean
    ```java
    public interface PaymentService {
        void pay();
    }

    @Service
    public class PaypalPaymentService implements PaymentService {
        public void pay() {
            System.out.println("Pay with PayPal");
        }
    }

    @Primary
    @Service
    public class StripePaymentService implements PaymentService {
        public void pay() {
            System.out.println("Pay with Stripe");
        }
    }
    ```
    ```java
    @Component
    public class Checkout {
        @Autowired
        private PaymentService paymentService; // Stripe will be injected
    }
    ```
2. Use `@Qualifier` to Specify Which Bean You Want
    ```java
    public interface PaymentService {
        void pay();
    }

    @Service
    public class PaypalPaymentService implements PaymentService {
        public void pay() {
            System.out.println("Pay with PayPal");
        }
    }

    @Service
    public class StripePaymentService implements PaymentService {
        public void pay() {
            System.out.println("Pay with Stripe");
        }
    }
    ```
    ```java
    @Component
    public class Checkout {
        @Autowired
        @Qualifier("paypalPaymentService")
        private PaymentService paymentService; // PayPal will be injected
    }
    ```
### 13. Compare BeanFactory and ApplicationContext in Spring framework?
| Feature | BeanFactory | ApplicationContext |
| --- | --- | --- |
| Base Interface | BeanFactory | Extends BeanFactory |
| Bean Initialization | Lazy | Eager |
| Internationalization support | No | Yes |
| Event Handling | No | Yes |
| Annotation support | Limited | Full support |
| AOP auto proxying | No | Yes |
### 14. Explain bean scope in Spring IOC? List bean scopes with explainations and code examples if possible.
Bean scope defines how many instances of a bean Spring container should create and how long they should live.  
You can define the scope of a Spring bean using the `@Scope` annotation.
Scope | Description | Applies To
--- | --- | ---
singleton | (Default) Only **one instance** of the bean is created per Spring container. | Any Spring app
prototype | A **new instance** is created **every time** the bean is requested. | Any Spring app
request | A single bean instance **per HTTP request**. | Spring MVC/Web apps
session | A single bean **per HTTP session**. | Spring MVC/Web apps
application | A single bean **per ServletContext**. | Spring MVC/Web apps
websocket | A single bean **per WebSocket session**. | Spring WebSocket apps
1. singleton (default)
    ```java
    @Component
    @Scope("singleton")
    public class MyService { }
    ```
2. prototype
    ```java
    @Component
    @Scope("prototype")
    public class MyPrototypeService { }
    ```
3. request (Web apps only)
    ```java
    @Component
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public class MyRequestBean { }
    ```
4. session (Web apps only)
    ```java
    @Component
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public class MySessionBean { }
    ```
5. application (Web apps only)
    ```java
    @Component
    @Scope(value = WebApplicationContext.SCOPE_APPLICATION)
    public class AppWideBean { }
    ```
6. websocket (WebSocket apps)
    ```java
    @Component
    @Scope(scopeName = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public class WebSocketBean { }
    ```
### 15. Write a Spring application that registers and autowires beans,
- Demo different types of dependency injection
- Demo bean scopes.
- Demo dependency injection by type and by name, when there's ambiguity in bean definition.
- Demo bean registration by both @Component and @Bean

Codes are in the folder "Coding"
### 16. Explain builder pattern with code examples.
The Builder Pattern is used to construct complex objects step by step. It allows you to build different types and representations of an object using the same construction code.
- Builder code:
    ```java
    public class Person {
        private final String name;
        private final int age;
        private final String email;
        private final String phone;

        private Person(Builder builder) {
            this.name = builder.name;
            this.age = builder.age;
            this.email = builder.email;
            this.phone = builder.phone;
        }

        public static class Builder {
            private String name;
            private int age;
            private String email;
            private String phone;

            public Builder setName(String name) {
                this.name = name;
                return this; // enables method chaining
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

            public Person build() {
                return new Person(this);
            }
        }

        @Override
        public String toString() {
            return name + ", " + age + ", " + email + ", " + phone;
        }
    }
    ```
- Usage:
    ```java
    public class Main {
        public static void main(String[] args) {
            Person person = new Person.Builder()
                                .setName("Zeliang Yin")
                                .setAge(25)
                                .setEmail("charlieyin99@gmail.com")
                                .setPhone("213-691-4594")
                                .build();

            System.out.println(person);
        }
    }
    ```