
## @RequestMapping

The `@RequestMapping` annotation is used to map web requests to specific handler functions in controller classes.


```java
@Controller
@RequestMapping("/api")
public class ApiController {

    @RequestMapping(value = "/greet", method = RequestMethod.GET)
    @ResponseBody
    public String greet() {
        return "Hello, World!";
    }
}
```
---
## @RequestParam
The @RequestParam annotation is used to extract query parameters from the URL.

```java
@Controller
@RequestMapping("/api")
public class ApiController {

    @RequestMapping(value = "/greet", method = RequestMethod.GET)
    @ResponseBody
    public String greet(@RequestParam(name = "name", defaultValue = "Guest") String name) {
        return "Hello, " + name + "!";
    }
}
```
---

## @PathVariable
The @PathVariable annotation is used to extract values from the URI path.

```java
@Controller
@RequestMapping("/api")
public class ApiController {

    @RequestMapping(value = "/greet/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String greet(@PathVariable("name") String name) {
        return "Hello, " + name + "!";
    }
}
```
---

## @ResponseBody

The `@ResponseBody` annotation tells Spring to write the return value of a method directly to the HTTP response body, instead of rendering a view.

```java
@RestController
@RequestMapping("/api")
public class GreetingController {

    @GetMapping("/greeting")
    @ResponseBody
    public String getGreeting() {
        return "Hello from @ResponseBody!";
    }
}
```
---

## @RequestBody

The @RequestBody annotation is used to bind the incoming HTTP request body to a Java object, especially useful for POST/PUT with JSON data.

```java
@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping("/users")
    public String createUser(@RequestBody User user) {
        return "Created user: " + user.getName();
    }
}
```
