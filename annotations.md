@RestController
It defines how we want to respond to a rest api. This annotation means that this class
gets http requests and returns responses

@RequestMapping
It maps http requests to specific methods. It is for class level.
Example:
@RestController
@RequestMapping("/users")
public class userController {
        @GetMapping("/{id}") 
        public String getUser(@PathVariable("id") Long id) {
            return id;
        }
    }

@GetMapping
It handles get request.
@PostMapping
It handles post request.
@PutMapping
It handles put request.
@DeleteMapping
It handles delete request.


@Autowired
It injects a bean. A bean is a key component that can be used by some part of the application. 
bean: @Controller, @Service, @Repository @Component
Example: 
    @Autowired
    private SomeService someService

@Service
It marks a class as service class. This class contains business logic.

@RequestBody
It accepts client side JSON, and it converts JSON to java objects.

@Repository
It marks data access object(DAO). It interacts with database.

@Entity
It maps a java class to a table of database. 

@Table
What specific table the class map to. 
Example: @Table(name = "posts");

@Column
It maps a java class field to a column of a database table.
Example:
@Column(name = "username", nullable = false)
private String username;

@Id
It defines the primary key of the current table.

@EnableTransactionManagement
along with @Transactional to detect transactional methods
@Transactional
mark the transactional method. without it, database won't roll back



