# List all of the annotations you learned from this class session.
```java
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@Component
@Service
@Repository
@Controller
@RestController
@Autowired
@Bean
@Configuration
@PropertySource
@ConfigurationProperties
@RequestMapping
@GetMapping
@PostMapping
@PutMapping
@DeleteMapping
@RequestParam
@PathVariable
@RequestBody
@ResponseBody
```

# Explain `tight coupling` vs `loose coupling` and what does Spring IOC do?
## Tight Coupling
- **Definition:** When one class directly depends on another class and controls its instantiation.
- **Problem:** Hard to test, change, or reuse because changing one class requires modifying the other.

## Loose Coupling
- **Definition:** When classes depend on abstractions (e.g., interfaces) instead of concrete implementations.
- **Benefit:** Easier to change, test, and maintain. Promotes flexibility and reusability.

## What Does Spring IoC (Inversion of Control) Do?
You just declare your components, and Spring:
- Creates objects (beans),
- Manages their lifecycles,
- Injects dependencies automatically (e.g., via @Autowired, constructor, etc.).

# What is MVC pattern?
The MVC (Model-View-Controller) pattern is a design pattern widely used in web and desktop application development — including in Spring Boot — to separate concerns and improve organization and scalability.

## Model
- Represents the data and business logic of the application.
- Responsible for accessing data from the database, performing calculations, and enforcing rules.

## View
- The UI (User Interface): what the user sees.
- In web apps, it's usually HTML, JSP, Thymeleaf, or any frontend rendered output.

## Controller
- Handles user input and routes it to the appropriate model and view.
- Acts as the middle layer between View and Model.

# What is Front-Controller?
A **Front Controller** is a single entry point for handling all incoming HTTP requests in a web application. Instead of having each controller handle routing individually, one central controller delegates requests to appropriate components (like your actual controllers, handlers, or views).

# Explain `DispatcherServlet` and how it works.
## What is `DispatcherServlet`?
- It's a Servlet provided by Spring (`org.springframework.web.servlet.DispatcherServlet`)
- Acts as the single entry point for handling all incoming web requests
- Delegates requests to appropriate handlers (like controllers)

## How Does It Work?
### Request Received
- A user sends an HTTP request (e.g., `GET /users`)
- The request is intercepted by the `DispatcherServlet`
### Handler Mapping
- `DispatcherServlet` consults HandlerMapping to find the right controller method based on:
    - URL (`@RequestMapping`, `@GetMapping`, etc.)
    - HTTP method
- If found, it gets a HandlerExecutionChain (the controller + interceptors)
### Handler Adapter
Uses a HandlerAdapter to invoke the matched controller method (typically a `@Controller` or `@RestController`)
### Controller Execution
- The controller method executes business logic
- Returns a `ModelAndView` (for web apps) or a response body (for REST APIs)
### View Resolution (Optional)
If a `ModelAndView` is returned, `ViewResolver` resolves the view name to an actual template (like a `.html` file)
### Render the View or Return Response
- If it’s a view (Thymeleaf, JSP, etc.), the view is rendered and returned to the client
- If it’s a REST API, the response is serialized (e.g., JSON) and returned

# What is JSP and What is Model And View？
## JSP = JavaServer Pages
- A server-side technology used to build dynamic web pages with Java.
- Think of it as HTML + Java — you embed Java code inside an HTML-like file.
## What is `ModelAndView`?
`ModelAndView` is a Spring MVC object that holds:
- **Model** – the data you want to show (e.g., a list of users)
- **View** – the name of the view (e.g., a JSP or Thymeleaf template)

# Explain servlet and servlet container, name some servlet implementations and servlet containers other than tomcat
## Servlet Definition:
A Servlet is a Java class used to handle HTTP requests and generate responses — it's the foundation of Java web applications.
## Servlet Container Definition:
A Servlet Container (a.k.a. Web Container) is part of a web server that:
- Manages Servlet lifecycle (init, service, destroy)
- Handles HTTP request/response mapping to servlets
- Provides services like multithreading, security, session management
## Servlet Implementations
These are Java classes that implement the javax.servlet.Servlet interface, usually extended from HttpServlet.

Examples:

- HttpServlet (most common)
- Custom classes like MyServlet extends HttpServlet

## Popular Servlet Containers
| **Servlet Container**        | **Notes**                                                                 |
|-----------------------------|---------------------------------------------------------------------------|
| **Jetty**                   | Lightweight, embeddable, often used in microservices (e.g., embedded in Spring Boot) |
| **Undertow**                | Very lightweight, used in WildFly, supports non-blocking I/O              |
| **GlassFish**               | Full Java EE (Jakarta EE) reference implementation; supports servlets + EJB, JPA, etc. |
| **WildFly (formerly JBoss AS)** | Full Java EE app server, includes a servlet container (Undertow)        |
| **WebLogic**                | Oracle's enterprise application server                                     |
| **WebSphere**               | IBM's enterprise app server, servlet container built-in                    |
