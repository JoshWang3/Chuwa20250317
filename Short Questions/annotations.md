### @Override
Usage: Indicates a method is overriding a superclass method.

# Entity/Model Layer
@Entity  
Usage: Marks a class as a JPA entity (mapped to a DB table).  
  
@OneToMany, @ManyToOne, @OneToOne, @ManyToMany  
Usage: Defines relationships between entities.  
@OneToMany(mappedBy = "user")
private List<Post> posts;  

# controller
@RestController  
Usage: Combination of @Controller + @ResponseBody.  
  
@RestController
public class UserController {
}

@RequestMapping  
Usage: Maps HTTP requests to methods.  
@RequestMapping("/api/users")
public class UserController {
}  
  
@GetMapping, @PostMapping, @PutMapping, @DeleteMapping  
Usage: Shortcut annotations for HTTP methods.  
@GetMapping("/{id}")
public User getUser(@PathVariable Long id) {
    return userService.getUser(id);
}  
  
@RequestParam  
Usage: Extracts query parameters.  
@GetMapping("/search")
public List<User> search(@RequestParam String name) {
    return userService.search(name);
}  
  
@RequestBody  
Usage: Binds the HTTP request body to a method parameter.  
@PostMapping("/users")
public User createUser(@RequestBody User user) {
    return userService.save(user);
}  

### service
@Service  
Usage: Marks a class as a service component.  
@Service
public class UserService {
}  

@Autowired  
Usage: Injects dependencies automatically.  
@Autowired
private UserRepository userRepository;  
