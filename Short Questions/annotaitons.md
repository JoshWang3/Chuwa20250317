
## `@EnableEurekaClient`

### Purpose:
Registers the current microservice with a Eureka Service Registry.

### Example:
```java
@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
```

---

## `@FeignClient`

### Purpose:
Declarative REST client that calls another service via service discovery.

### Interface:
```java
@FeignClient(name = "order-service", fallback = OrderServiceFallback.class)
public interface OrderClient {
    @GetMapping("/orders/{id}")
    OrderDto getOrderById(@PathVariable("id") Long id);
}
```

### Fallback:
```java
@Component
public class OrderServiceFallback implements OrderClient {
    public OrderDto getOrderById(Long id) {
        return new OrderDto("fallback-order");
    }
}
```

---

## `@CircuitBreaker`

### Purpose:
Protects a method by automatically breaking the circuit when failures exceed a threshold.

### Example:
```java
@Service
public class PaymentService {

    @CircuitBreaker(name = "paymentService", fallbackMethod = "fallback")
    public String payOrder(Long orderId) {
        if (new Random().nextBoolean()) {
            throw new RuntimeException("Service unavailable");
        }
        return "Payment successful";
    }

    public String fallback(Long orderId, Throwable t) {
        return "Fallback payment processed";
    }
}
```

---

## `@LoadBalanced`

### Purpose:
Enables client-side load balancing via service discovery for `RestTemplate`.

### Configuration:
```java
@Configuration
public class AppConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

### Usage:
```java
@Autowired
private RestTemplate restTemplate;

public String callOrderService() {
    return restTemplate.getForObject("http://order-service/orders/1", String.class);
}
```

---

## `@RefreshScope`

### Purpose:
Allows beans to be reloaded dynamically when configuration changes (e.g., from Spring Cloud Config Server).

### Example:
```java
@RestController
@RefreshScope
public class ConfigController {

    @Value("${app.message:Default message}")
    private String message;

    @GetMapping("/message")
    public String getMessage() {
        return message;
    }
}
```
---

## `@EnableConfigServer`

### Purpose:
Starts a Spring Cloud Config Server to serve centralized config files.

### Main Application:
```java
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
```

### application.yml:
```yaml
server:
  port: 8888

spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/your-org/config-repo
```