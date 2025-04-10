# explain how the below annotaitons specify the table in database?
```java
@Column(columnDefinition = "varchar(255) default 'John Snow'")
private String name;
@Column(name="STUDENT_NAME", length=50, nullable=false, unique=false)
private String studentName;
```
## @Column(columnDefinition = "varchar(255) default 'John Snow'")
This annotation is applied to the name field. It tells JPA how to generate the corresponding column in the database.
### columnDefinition = "varchar(255) default 'John Snow'"
- Overrides the default column creation behavior.
- Directly tells the database to create a column of type varchar(255) with a default value 'John Snow'.
- This is SQL-specific, so it’s passed as raw SQL to the database engine.
```sql
name VARCHAR(255) DEFAULT 'John Snow'
```
## @Column(name="STUDENT_NAME", length=50, nullable=false, unique=false)
### name = "STUDENT_NAME"
The actual column name in the table will be STUDENT_NAME, not studentName.
### length = 50
Sets the maximum length of the column to 50 characters — applies to String fields and results in VARCHAR(50).
### nullable = false
The column cannot be NULL — like NOT NULL in SQL.
### unique = false
No uniqueness constraint is applied — this is the default, so this part is optional unless you want to be explicit.
```sql
STUDENT_NAME VARCHAR(50) NOT NULL
```

# What is the default column names of the table in database for @Column?
When you use the @Column annotation without any parameters, JPA (e.g., Hibernate) will use the name of the field in the Java class as the column name in the database by default.
## The default column names in the database will be:
| Java Field       | Default Column Name in DB |
|------------------|---------------------------|
| firstName        | firstName                 |
| operatingSystem  | operatingSystem           |

# What are the layers in springboot application? what is the role of each layer?
| Layer           | Purpose                                      |
|------------------|----------------------------------------------|
| Controller       | Handle HTTP requests and return responses    |
| Service          | Perform business logic                       |
| Repository       | Interact with the database                   |
| Model/Entity     | Represent the data structure mapped to DB    |
| DTOs (optional)  | Simplify/secure data transfer                |

# Describe the flow in all of the layers if an API is called by Postman.
	1.	Postman → Sends HTTP request.
	2.	Controller → Accepts the request, maps to method.
	3.	Service → Applies business logic.
	4.	Repository → Saves to database using JPA.
	5.	Entity → Mapped to DB table.
	6.	Database → Data is persisted.
	7.	Response → Data flows back through the same layers and is sent to Postman.

# What is the application.properties? do you know application.yml?
## application.properties
This is the default properties file for Spring Boot. It uses key-value pairs in the format. It is simple and flat; good for small or medium-sized configs.
```properties
# Server configuration
server.port=8081

# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=secret

# JPA/Hibernate config
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## application.yml (YAML format)
YAML is more hierarchical and readable, especially for nested structures. It is more structured and readable for complex/nested configs. It is easier for lists, maps, nested objects.
```yaml
server:
  port: 8081

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: root
    password: secret

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

# What’s the naming differences between GraphQL vs. REST ? Why is the differences ?
## REST vs. GraphQL — Naming Differences
### Endpoints vs. Single Entry Point
- REST: You define multiple URLs, each representing a resource (/users, /orders, etc.).
- GraphQL: You define one URL, and the client specifies what data they want inside the body.
### HTTP Method vs. Query Name
- REST: Action is implied by the HTTP method.
- GraphQL: Action is declared inside the query or mutation, not the method.
### Resource Naming
- REST: Emphasizes resources (e.g., /users, /products).
- GraphQL: Emphasizes data types/objects (e.g., user, product).
### Verbosity and Structure
- REST: You might get more or less data than you need.
- GraphQL: You ask for exactly the fields you want.
## Why Are They Different?
| Reason       | REST                                | GraphQL                                |
|--------------|-------------------------------------|----------------------------------------|
| Philosophy   | Resource-based                      | Data/Schema-based                      |
| HTTP Role    | Strong use of HTTP verbs/URLs       | HTTP is just a transport layer         |
| Flexibility  | Fixed responses per endpoint         | Dynamic queries from the client        |
| Versioning   | Often uses URL versioning (/v1/)     | Typically versionless, schema evolves  |

# Provide 2 real-world examples of N+1 problem in REST that can be solved by GraphQL.
##  Example 1: Blog Platform — Users and Posts
### In REST:
1. Call: GET /posts → Returns a list of posts with authorId.
```json
[
  { "id": 1, "title": "GraphQL Rocks", "authorId": 101 },
  { "id": 2, "title": "Spring Boot Tips", "authorId": 102 }
]
```
2. Then for each post, you do:
- GET /users/101
- GET /users/102

### In GraphQL:
You can fetch everything in a single query:
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
## Example 2: E-commerce App — Orders and Products
### In REST:
1. Call: GET /users/123/orders → Returns a list of orders with product IDs.
```json
[
  { "id": 1, "productIds": [11, 12] },
  { "id": 2, "productIds": [13] }
]
```
2. For each product ID, you must call:
- GET /products/11
- GET /products/12
- GET /products/13
### In GraphQL:
Single query handles everything:
```graphql
query {
  user(id: 123) {
    orders {
      id
      products {
        name
        price
      }
    }
  }
}
```

# Finish the following API
## REST DELETE post by ID (with exception cases)
### Controller
```java
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        try {
            postService.deletePostById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (PostNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found with ID: " + id);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting post");
        }
    }
}
```
### Service
```java
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void deletePostById(Long id) {
        if (!postRepository.existsById(id)) {
            throw new PostNotFoundException("Post with ID " + id + " not found");
        }
        postRepository.deleteById(id);
    }
}
```
### Custom Exception
```java
public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String message) {
        super(message);
    }
}
```
## GraphQL: Query getAllPost
### Schema (schema.graphqls)
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
### Resolver
```Resolver
@Component
public class PostQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }
}
```

# Create a Project, name it with mongo-blog, write a POST API for mongo-blog, change database to MongoDB;
## Project Setup
You can use Spring Initializr with the following settings:
- **Project**: Maven
- **Language**: Java
- **Name**: mongo-blog
- **Dependencies**: Spring Web, Spring Data MongoDB, Lombok (optional, for boilerplate reduction)
## application.yml – MongoDB Config
```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/mongo_blog_db
server:
  port: 8080
```
## Define the Entity – Post.java
```java
package com.example.mongoblog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private String title;
    private String content;
    private String author;
}
```

## Repository – PostRepository.java
```java
package com.example.mongoblog.repository;

import com.example.mongoblog.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
```

## Service – PostService.java
```java
package com.example.mongoblog.service;

import com.example.mongoblog.model.Post;
import com.example.mongoblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }
}
```

## Controller – PostController.java
```java
package com.example.mongoblog.controller;

import com.example.mongoblog.model.Post;
import com.example.mongoblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return ResponseEntity.ok(createdPost);
    }
}
```

# https://www.mongodb.com/compatibility/spring-boot
✅