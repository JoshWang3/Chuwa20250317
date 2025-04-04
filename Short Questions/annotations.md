# Controller Annotations
1. `RestController`
   - Used for indicating a Spring MVC controller class.
   - Example:
        ```java
        @RestController
        public class MyController {
            @GetMapping("/helloWorld")
            public String sayHello() {
                return "Hello, World!";
            }
        }
        
        ```
2. `@RequestMapping()` & `<Method>Mapping`
   - Defines base URL path for a controller. Maps HTTP requests to handler methods.
   - Example:
        ```Java
        @RestController
        @RequestMapping("/api/v1")
        public class PostController {
            @Autowired
            public PostServiceImpl postServiceImpl;

            @PostMapping("/posts")
            public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
                return ResponseEntity.ok(postServiceImpl.createPost(postDTO));
            }
        }
        ```
3. `@RequestBody`
    - Used on a method parameter to bind HTTP request body to a Java object.
    - Example: See above example

# Dependency Injection Annotations
1. `@Autowired`
   - Inject dependent beans automatically by type
   - Example:
        ```Java
        @Service
        public class MyService {
            public String process() {
                return "Processed";
            }
        }

        @RestController
        public class MyController {

            @Autowired
            private MyService myService;

            @GetMapping("/process")
            public String process() {
                return myService.process();
            }
        }
        ```

# Component Scanning and Configuration Annotations
1. `@Service`
    - Mark a class as a service provider. The class contains business logic and should be managed as a service component.
    - Example:
        ```Java
        @Service
        public class PostServiceImpl implements PostService {
            @Override
            public PostDTO createPost(PostDTO postDTO) {
                System.out.println("Created post: " + postDTO.getTitle());
                return null;
            }
        }
        ```