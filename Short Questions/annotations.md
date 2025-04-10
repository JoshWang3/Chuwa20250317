# Annotations
## Annotations used for IoC
### @Component
该注解用于描述 Spring 中的 Bean，它是一个泛化的概念，仅仅表示容器中的一个组件（Bean），并且可以作用在应用的任何层次，例如 Controller 层、Service 层、Dao 层等。
```java
@Component
public class MyService {
    public void doSomething() {
        System.out.println("Doing something...");
    }
}
```
### @Controller
该注解通常作用在 Controller 层，用于将 Controller 层的类标识为 Spring 中的 Bean。
```java
@Controller
public class MyController {
    @RequestMapping("/hello")
    public String sayHello() {
        return "hello";
    }
}
```
### @Service
该注解通常作用在 Service 层，用于将 Service 层的类标识为 Spring 中的 Bean。
```java
@Service
public class UserService {
    public String getUserName() {
        return "Zeliang Yin";
    }
}
```
### @Repository
该注解通常作用在 Dao 层，用于将 Dao 层的类标识为 Spring 中的 Bean。
```java
@Repository
public class UserRepository {
    public String findUserById(int id) {
        return "Zeliang Yin";
    }
}
```
### @PostConstruct
用于在 Spring Bean 初始化完成之后自动执行某段方法。它常用来执行一些初始化操作，比如加载配置、连接资源、打印日志等。
```java
@Component
public class MyBean {

    @PostConstruct
    public void init() {
        System.out.println("MyBean 已初始化！");
    }
}
```
### @PreDestroy
用来在 Bean 被销毁前执行清理工作。
```java
@Component
public class MyBean {

    @PreDestroy
    public void destroy() {
        System.out.println("MyBean 即将被销毁，执行清理操作");
    }
}
```
### @Scope
用来定义 Bean 的作用域，告诉 Spring 这个 Bean 要以什么方式存在和使用。
| 作用域 | 描述 |
| --- | --- |
| singleton | 默认值，整个 Spring 容器中只有一个共享实例 |
| prototype | 每次获取都会创建一个新的实例 |
| request | 每个 HTTP 请求创建一个新的 Bean（仅限于 Web 项目） |
| session | 每个 HTTP 会话创建一个新的 Bean（仅限于 Web 项目） |
| application | 整个 ServletContext 共享一个 Bean（仅限于 Web 项目） |
| websocket | 每个 WebSocket 会话创建一个新的 Bean（仅限于 WebSocket 项目） |
```java
@Component
@Scope("prototype")
public class MyPrototypeBean {
    public MyPrototypeBean() {
        System.out.println("创建了一个新的 MyPrototypeBean 实例");
    }
}
```
### @Autowired
用于自动注入依赖对象，简化了我们手动写构造方法或 setter 的过程.
```java
@Service
public class UserService {
    public void sayHi() {
        System.out.println("Hello from UserService");
    }
}

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public void handleRequest() {
        userService.sayHi(); // 自动注入，无需手动 new
    }
}
```
## Annotations used by Controller
### @RestController
用于创建 RESTful Web 服务，它是 `@Controller` 和 `@ResponseBody` 注解的结合。这个注解主要用来标记控制器类，其方法返回的数据会直接作为 HTTP 响应的主体（例如 JSON 或 XML）返回，而不是渲染视图（HTML 页面）。
| 特性 | `@Controller` | `@RestController` |
| --- | --- | --- |
| 返回类型 | 默认是视图名称（页面） | 默认是 JSON、XML 等响应体 |
| 需要配合 `@ResponseBody` | 是 | 否 |
| 主要用途 | 用于传统的 MVC模式，返回视图 | 用于构建 RESTful API，返回数据 |
### @RequestMapping
用于将 HTTP 请求映射到相应的处理方法或者类。这个注解非常灵活，支持多种 HTTP 方法（GET, POST, PUT, DELETE 等），并且可以用于指定 URL 路径、请求方法、请求参数等。
### @GetMapping
它是 `@RequestMapping` 的快捷方式，专门用来处理 HTTP GET 请求。
### @PostMapping
它是 `@RequestMapping` 的快捷方式，专门用来处理 HTTP POST 请求。
### @PutMapping
它是 `@RequestMapping` 的快捷方式，专门用来处理 HTTP PUT 请求。
### @DeleteMapping
它是 `@RequestMapping` 的快捷方式，专门用来处理 HTTP DELETE 请求。
### @RequestBody
用于将 HTTP 请求体中的数据 转换为方法参数。通常用于处理 POST、PUT 或 PATCH 请求中的请求体数据。
### @PathVariable
用于获取 URL 路径中的动态部分，通常与 RESTful API 路径中的变量一起使用。
### @RequestParam
用于获取 查询参数（也叫请求参数），这些参数通常附加在 URL 的查询字符串中。
```java
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users")
    public User getUser(@RequestParam(defaultValue = "10") int age) {
        return new User("Zeliang", 25);
    }

    @PostMapping("/users")
    public String createUser(@RequestBody User user) {
        return "User created: " + user.getName();
    }

    @PutMapping("/users")
    public String updateUser(@RequestBody User user) {
        return "User updated: " + user.getName();
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "User with ID " + id + " deleted";
    }
}
```
## Annotations used by Repository
### `@Entity`  
Marks a class as a JPA entity (a table in the database).
```java
@Entity
public class User {
    @Id
    private Long id;
}
```
### `@Table`
Specifies the name of the table in the database.
```java
@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;
}
```
### `@Id`  
Specifies the primary key of an entity.
```java
@Id
private Long id;
```
### `@Column`  
Maps a field to a specific column in the table.
```java
@Column(name = "name", nullable = false)
private String name;
```
### `@CreationTimestamp`  
Automatically sets the timestamp when the entity is first persisted (inserted into the database).
```java
@CreationTimestamp
private LocalDateTime createdDateTime;
```
### `@UpdateTimestamp`  
Automatically updates the timestamp whenever the entity is updated (any change is saved to the DB).
```java
@UpdateTimestamp
private LocalDateTime updatedDateTime;
```