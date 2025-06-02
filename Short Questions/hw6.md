# hw6
### 1. create a file to list all of the annotaitons you learned and known, and explain the usage and how do you understand it. you need to update it when you learn a new annotation. Please organize those annotations well, like annotations used by entity, annotations used by controller.
1. File name: annotations.md
2. you'd better also list a code example under the annotations.
### 2. explain how the below annotaitons specify the table in database?
```java
@Column(columnDefinition = "varchar(255) default 'John Snow'")
private String name;

@Column(name="STUDENT_NAME", length=50, nullable=false, unique=false)
private String studentName;
```
The `@Column` annotation in JPA is used to specify how a Java field maps to a column in the underlying database table.
1. `columnDefinition` allows you to explicitly specify the exact SQL column definition. You will create a column of type `varchar(255)` and set its default value to `'John Snow'`.
2. This configures the `studentName` field to map to a column in the database with specific characteristics. The column name in the database will be `STUDENT_NAME`. The column will have a maximum character length of 50. The column cannot be `NULL` and doesn't need to have unique values.
### 3. What is the default column names of the table in database for `@Column`?
```java
@Column
private String firstName;
@Column
private String operatingSystem;
```
In Spring Boot, the default naming strategy may convert `camelCase` to `snake_case`, depending on the config. So the default column names are `first_name` and `operating_system`.
### 4. What are the **layers** in springboot application? what is the **role** of each layer?
| Layer | Annotation | Role |
| --- | --- | --- |
| Controller | `@RestController`, `@Controller` | Handles HTTP requests |
| Service | `@Service` | Business logic |
| DAO | `@Repository` | Database interaction |
| Entity | `@Entity` | Data structure (tables) |
| DTO | — | API data transport |
### 5. Describe the **flow in all of the layers** if an API is called by Postman.
1. Postman -> **Controller**: Controller handles the incoming HTTP request.
2. **Controller** -> **Service**: The controller calls the service layer for business logic.
3. **Service** -> **DAO**: The service calls the DAO to fetch the data from the database.
4. **DAO** <-> Database: The DAO interacts with the database (via JPA).
5. **DAO** -> **Service**: The DAO returns the data to the service layer.
6. **Service** -> **Controller**: The service sends the data back to the controller.
7. **Controller** -> Postman: The controller returns a response (JSON) to Postman.
### 6. What is the **application.properties**? do you know application.yml?
In Spring Boot, both `application.properties` and `application.yml` are used for configuration purposes. They allow you to define various application settings. They serve the same purpose, just in different formats.
- `application.properties`:
```properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=root
logging.level.org.springframework=INFO
```
- `application.yml`:
```yml
server:
  port: 8080
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: root
    password: root
logging:
  level:
    org.springframework: INFO
```
### 7. What’s the naming differences between **GraphQL** vs. **REST** ? Why is the differences ?
1. - In REST, each resource is typically represented by its own endpoint (URL). Each endpoint corresponds to a specific entity or resource, and the operations correspond to standard CRUD operations.  
For example, `/users/{id}` is to get a specific user.  
    - In GraphQL, there is typically one endpoint (e.g., `/graphql`) for all queries and mutations. The operations are defined by the GraphQL query.
        ```graphql
        query {
            users {
                name
                age
            }
            posts {
                title
                content
            }
        }
        ```
2. - REST was designed around the idea of resources with unique URIs for each resource, leading to multiple endpoints for different actions.  
    - GraphQL was designed for flexibility and efficiency, allowing clients to request exactly the data they need with a single endpoint. The query itself defines the shape of the response, making it much more dynamic.
### 8. Provide 2 real-world examples of N+1 problem in REST that can be solved by GraphQL.
1. **E-commerce Website – Fetching Products and Their Reviews**
    - REST: Have the following two endpoints:  
`GET` `/products`: Fetch all products **(1 query)**  
`GET` `/reviews?product-id={id}`: Fetch reviews for a specific product **(N queries)**
    - GraphQL: Can request both the products and their reviews in **a single query**:
        ```graphql
        query {
            products {
                id
                name
                reviews {
                    id
                    comment
                    rating
                }
            }
        }
        ```
2. **Social Media – Fetching Users and Their Posts**
    - REST: Have the following two endpoints:  
`GET` `/users`: Fetch all users **(1 query)**  
`GET` `/posts?user-id={id}`: Fetch posts for a specific user **(N queries)**
    - GraphQL: Can query the users and their posts in **a single query**:
        ```graphql
        query {
            users {
                id
                username
                posts {
                    id
                    title
                    content
                }
            }
        }
        ```
### 9. Finish the following API
#### **REST**: DELETE post by ID (with exception cases)
`DELETE` `/posts/{id}`
```java
@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        try {
            boolean isDeleted = postService.deletePostById(id);
            if (isDeleted) {
                return ResponseEntity.ok("Post deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting the post.");
        }
    }
}
```
#### **GraphQL**: Query getAllPost
```graphql
query {
    getAllPosts {
        id
        title
        content
        author {
            id
            name
        }
    }
}
```
```java
@Component
public class PostResolver implements GraphQLQueryResolver {

    @Autowired
    private PostService postService;

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
}
```
### 10. Create a Project, name it with **mongo-blog**, write a **POST API** for mongo-blog, change database to **MongoDB**;
### 11. https://www.mongodb.com/compatibility/spring-boot