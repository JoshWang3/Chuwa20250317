1. create a file to list all of the annotaitons you learned and known, and explain the usage and how do you 
understand it. you need to update it when you learn a new annotation. Please organize those annotations 
well, like annotations used by entity, annotations used by controller.
- File name: annotations.md
- you'd better also list a code example under the annotations.
Link: https://www.notion.so/Java-Annotations-1f59e620eb698072a469f8ec2c89a8ac?pvs=4

2. explain how the below annotaitons specify the table in database?
```
@Column(columnDefinition = "varchar(255) default 'John Snow'")
private String name;

@Column(name="STUDENT_NAME", length=50, nullable=false, unique=false)
private String studentName;
```
* `@Column(columnDefinition = "varchar(255) default 'John Snow')"`:
  Directly controls the SQL column definition (e.g., type and default value).
  → Final SQL column will be: `name varchar(255) default 'John Snow'`

* `@Column(name="STUDENT_NAME", length=50, nullable=false, unique=false)`:

    * `name`: Maps to column name `STUDENT_NAME`
    * `length`: VARCHAR length
    * `nullable`: Must have a value (NOT NULL)
    * `unique`: Allows duplicates


3. What is the default column names of the table in database for @Column?

```Java
@Column
private String firstName;
@Column
private String operatingSystem;
```
If not specified, the column name is **the same as the field name** in camelCase, but the database **may convert it to snake\_case**, depending on JPA implementation or configuration.

Example:

```java
@Column
private String firstName;
```

Might be stored as `first_name` in DB if naming strategy is set to `SPRING_PHYSICAL_NAMING`.


4. What are the layers in springboot application? what is the role of each layer?

| Layer             | Responsibility                           |
| ----------------- | ---------------------------------------- |
| **Controller**    | Handles HTTP requests/responses          |
| **Service**       | Contains business logic                  |
| **Repository**    | Handles database access (CRUD)           |
| **Model/Entity**  | Represents data structure and DB mapping |
| **Configuration** | Bean definitions, app config             |



5. Describe the flow in all of the layers if an API is called by Postman.

**Postman sends** a request to an API endpoint.

**Controller** receives the request, maps it via annotations like `@PostMapping`.

**Service** layer is called to process business logic.

**Repository** layer interacts with the database (via JPA).

**Result** is returned up from repository → service → controller.

**Controller returns** response to Postman in JSON.


6. What is the application.properties? do you know application.yml?

* Both are config files for Spring Boot.
* You can define:

    * DB config
    * Logging
    * Server port
    * Spring profiles
    * Any custom property

### Example `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db
server.port=8080
```

### Example `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/db
server:
  port: 8080
```
`application.yml` is **more readable** for complex configs (especially nested ones).

7. What’s the naming differences between GraphQL vs. REST ? Why is the differences ? 

| Feature      | REST                    | GraphQL                     |
| ------------ | ----------------------- | --------------------------- |
| Endpoint     | `/users`, `/users/{id}` | Single `/graphql` endpoint  |
| Naming Style | Resource-based          | Operation-based (`getUser`) |
| Flexibility  | Fixed responses         | Client chooses fields       |
| Versioning   | Via URL (`/v1/users`)   | No versioning in endpoint   |
GraphQL avoids redundant endpoints and versioning by letting clients **specify exactly what they want**.

8. Provide 2 real-world examples of N+1 problem in REST that can be solved by GraphQL.
   
### REST N+1 Problem #1: Blog Posts and Authors

```http
GET /posts   --> returns 10 posts  
(for each post)
GET /authors/{id}  --> 10 additional queries
```

- GraphQL Solution:

```graphql
query {
  posts {
    title
    author {
      name
    }
  }
}
```


### REST N+1 Problem #2: Orders and Items

```http
GET /orders  --> returns 20 orders  
(for each order)
GET /orders/{id}/items --> 20 more calls
```

### GraphQL Solution:

```graphql
query {
  orders {
    id
    items {
      name
    }
  }
}
```


9.  Finish the following API
- REST : DELETE post by ID (with exception cases) 
- GraphQL : Query getAllPost 


### REST: DELETE Post by ID
```java
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        try {
            postService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
```
- Service Layer
```Java
public void deleteById(Long id) {
    Post post = postRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
    postRepo.delete(post);
}
```

### GraphQL: `getAllPost` Query

- Schema:
```graphql
type Query {
  getAllPost: [Post]
}

type Post {
  id: ID
  title: String
  content: String
}
```

- Resolver:
```java
@Component
public class PostResolver implements GraphQLQueryResolver {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }
}
```


10. Create a Project, name it with mongo-blog, write a POST API for mongo-blog, change database to 
MongoDB. https://www.mongodb.com/compatibility/spring-boot
