1. List all of the annotations you learned from class and homework to annotaitons.md (your own
cheatsheet).
2. Compare Spring and Springboot? What are the benfits of Srpingboot?
Spring: modular framework for Java, need manual configuration, to deploy to servlet container, and to dependency management.
Springboot: a tool built on top of spring to simplify setup and development, auto-configuration, embedded server, use "starter" dependencies to simplify dependency setup, production-ready feature like health checks.
Fast Development: Auto-configuration and starters speed up the setup process.
Embedded Server: No need to deploy WAR files to external servers.
Reduced Boilerplate: Annotations and smart defaults make coding faster and cleaner.
Microservices Ready: Built with microservice architecture in mind.
Built-in Monitoring: Spring Boot Actuator gives endpoints for health, metrics, and more.
Better Dependency Management: Starter dependencies simplify Maven/Gradle configuration.
3. What is IOC and What is DI?
IOC (Inversion of Control): It's a design principle where the control of object creation and dependencies is transferred from the programmer to the framework. With IoC, the framework creates and injects objects for you.
DI (Dependency Injection): DI is a specific way to achieve IoC where the framework injects the dependencies (objects that a class needs) into the class automatically. We use @Autowired to implement the injection.
4. What is @CompnonentScan ?
A Spring annotation used to tell the framework where to look for components (like classes annotated with @Component, @Service, @Repository, @Controller, etc.) to automatically register them as beans in the Spring context.
Spring only scans beans in the same package as the main application class and its sub-packages. If your components are outside that, you need @ComponentScan to include them manually.
5. What is @SpringbootApplication ?
A convenience annotation in Spring Boot that combines three important annotations (@Configuration, @EnableAutoConfiguration, @ComponentScan) to quickly configure and bootstrap a Spring Boot application.
6. How many ways to define a bean? Provide code examples.
1. @Component, @Service, @Repository, @Controller
@Component
public class MyComponent {
    public void doSomething() {
        System.out.println("Hello from MyComponent");
    }
}
2. @Bean inside of @Configuration
@Configuration
public class AppConfig {
    @Bean
    public MyComponent myComponent() {
        return new MyComponent();
    }
}
3. use XML configuration
<!-- applicationContext.xml -->
<beans xmlns="http://www.springframework.org/schema/beans" ...>
    <bean id="myComponent" class="com.example.MyComponent"/>
</beans>
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
MyComponent mc = context.getBean(MyComponent.class);
7. What is default bean name for @Component and @Bean ? Also compare @Component and @Bean.
Default bean name is the class name with first letter in lowercase
@Component: class, auto scanning by spring
@Bean: method inside @Configuration, manually in java code
8. Compare @component and @service , @repository, @controller ?
@Component: General,	Base annotation for any component
@Service: Service Layer,	Indicates business logic/service class
@Repository: Data Access Layer,	Marks DAO classes; enables exception translation
@Controller: Web Layer,	Handles web requests (MVC controller)
@RestController: Web Layer,	Same as @Controller + @ResponseBody for REST APIs
9. Explain @Autowired , @Qualifier , @Resource and @Primary ?
@Autowired: Spring’s default DI annotation. It works by type. If multiple candidates exist, Spring throws an exception unless further specified.
@Qualifier: Used along with @Autowired to resolve ambiguity when multiple beans of the same type exist. It specifies which exact bean name to inject.
@Resource: Injects by name first, then by type if name not found. It can't be used for constructor injection.
@Primary: Used when multiple beans of the same type exist and you want one of them to be the default for autowiring.
10. How many annotaitons we can use to inject a bean?
@Autowired, @Qualifier, @Primary
11. Explain and compare differnet types of denpendency injection, their pros and cons, and use cases.
1 Constructor Injection:
@Component
public class CarService {
    private final Engine engine;
    @Autowired  // Optional since Spring 4.3
    public CarService(Engine engine) {
        this.engine = engine;
    }
}
Pros:
Promotes immutability (fields can be final)
Easier to test (no need for setters)
Guarantees that dependencies are provided
Cons:
Boilerplate for many dependencies
Cannot change dependency after object creation
Use Case:
Recommended for required dependencies and production code.
2. Setter Injection:
@Component
public class CarService {
    private Engine engine;

    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
Pros:
Good for optional dependencies
More flexible (can change dependency later)
Cons:
Dependency may not be available at object construction
Allows object to be in an incomplete state
Use Case:
Optional or configurable dependencies
3. Field Injection:
@Component
public class CarService {

    @Autowired
    private Engine engine;
}
Pros:
Very concise
Minimal boilerplate
Cons:
Harder to test (can’t easily mock dependencies)
No immutability
Violates encapsulation
Harder to track dependencies
Use Case:
Quick prototypes or small projects. Not recommended for production.
12. If we have multiple beans for one type, how to set one is primary? and how Spring IOC picks one bean to inject if no primay, demo with code examples.
@Primary: (At interface add @Primary)
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
@Component
public class Checkout {
    @Autowired
    private PaymentService paymentService; // Stripe will be injected
}
@Qualifier: (at DI object @Qualifier with name)
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
@Component
public class Checkout {
    @Autowired
    @Qualifier("paypalPaymentService")
    private PaymentService paymentService; // PayPal will be injected
}
13. Compare BeanFactory and ApplicationContext in Spring framework?
Feature	BeanFactory	ApplicationContext
Base Interface	BeanFactory	Extends BeanFactory
Bean Initialization	Lazy	Eager
Internationalization support	No	Yes
Event Handling	No	Yes
Annotation support	Limited	Full support
AOP auto proxying	No	Yes
14. Explain bean scope in Spring IOC? List bean scopes with explainations and code examples if possible.
singleton:	One shared instance per Spring container (default)
prototype:	New instance every time the bean is requested
request:	One bean per HTTP request (web only)
session:	One bean per HTTP session (web only)
application:	One bean per ServletContext (web only)
websocket:	One bean per WebSocket session
1. singleton (Default)
A single instance per Spring container.
Used for stateless services, config beans.
@Component
@Scope("singleton") // Optional, this is default
public class MySingletonService {
}
2. prototype
A new instance is created every time the bean is requested.
Used for stateful beans.
@Component
@Scope("prototype")
public class MyPrototypeBean {
}
15. Write a Spring application that registers and autowires beans,
Demo different types of dependency injection
Demo bean scopes.
Demo dependency injection by type and by name, when there's ambiguity in bean definition.
Demo bean registration by both @Component and @Bean
16. Explain builder pattern with code examples.
The Builder Pattern is used to construct complex objects step by step. It allows you to build different types and representations of an object using the same construction code.
Builder code:
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
Usage:
User user = new User.Builder()
                 .setName("Alice")
                 .setAge(30)
                 .setEmail("alice@example.com")
                 .build();