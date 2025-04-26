### `@Bean`

- Used to manually define a bean in a `@Configuration` class.
- Returns the object that Spring registers into the application context.
- Commonly used for third-party classes or when more control over instantiation is needed.

```java
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }
}
```

---

### `@Qualifier`

- Used with `@Autowired` to resolve conflicts when multiple beans of the same type exist.
- Allows injection **by name**.

```java
@Autowired
@Qualifier("myServiceA")
private MyService myService;
```

---

### `@Primary`

- Marks one bean as the default when multiple candidates exist for injection **by type**.
- Used to avoid ambiguity without specifying a `@Qualifier`.

```java
@Bean
@Primary
public MyService myDefaultService() {
    return new DefaultServiceImpl();
}
```

---

### `@ComponentScan`

- Tells Spring where to search for components (`@Component`, `@Service`, `@Repository`, `@Controller`).
- By default, it scans the package of the class it's used in and sub-packages.

```java
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.services", "com.example.controllers"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

---

### `@Resource`

- Part of Java EE (`javax.annotation.Resource`).
- Injects a bean by **name first**, and by type if no name match is found.
- Alternative to `@Autowired` + `@Qualifier`.

```java
@Resource(name = "myServiceA")
private MyService myService;
```

---