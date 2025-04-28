# 2. Explanation of @Column Annotations


```java
@Column(columnDefinition = "varchar(255) default 'John Snow'")
private String name;
```

### Explanation:
- **columnDefinition = "varchar(255) default 'John Snow'"**: 
  - Specifies the column type as `varchar` with a length of 255 characters.
  - Sets a default value of `'John Snow'` for the column.

```java
@Column(name = "STUDENT_NAME", length = 50, nullable = false, unique = false)
private String studentName;
```
### Explanation:
- **name = "STUDENT_NAME"**: 
  - Explicitly sets the column name in the database table to `STUDENT_NAME`.
- **length = 50**: 
  - Sets the maximum length of the column to 50 characters.
- **nullable = false**: 
  - Ensures that the column cannot have a `null` value.
- **unique = false**: 
  - Indicates that duplicate values are allowed.


# 3. Default Column Names for @Column in Spring Boot

In Spring Boot, when using the `@Column` annotation without specifying a column name, the default column name will be the same as the field name defined in the entity class. 

## Example 1: Default Column Name
```java
@Entity
public class User {
    @Column
    private String firstName;
}
```
### Explanation:
- first_name - The field name firstName follows the camelCase to snake_case convention.
---

```java
@Column
private String operatingSystem;
```
### Explanation:
- operating_system - The field name operatingSystem follows the camelCase to snake_case convention.
---

# 4. Layers in Spring Boot Application

Spring Boot applications typically follow a layered architecture. Each layer has a specific responsibility to keep the code modular and manageable. 

## Layered Architecture:

1. **Controller Layer:** Manages HTTP requests and responses.
2. **Service Layer:** Contains the business logic.
3. **Repository Layer:** Interacts with the database.
4. **Model Layer:** Represents the data structure.

---

## Roles of Each Layer:

### 1. Controller Layer:
- Handles incoming HTTP requests.
- Maps the requests to appropriate service methods.
- Returns the response to the client.

### 2. Service Layer:
- Implements the business logic.
- Calls the repository to perform data operations.
- Acts as an intermediary between the controller and repository.

### 3. Repository Layer:
- Handles database operations.
- Uses Spring Data JPA to interact with the database.
- Abstracts the data access layer from business logic.

### 4. Model Layer:
- Represents the data in the form of Java objects.
- Mapped to database tables using JPA annotations.

# 5. Flow of API Call in Spring Boot using Postman

When an API is called by Postman, the request goes through various layers in the Spring Boot application. Here’s the step-by-step flow:

---

## Step 1: Client (Postman) Sends a Request
- Postman sends an HTTP request (GET, POST, PUT, DELETE) to a specified URL.
- The request contains:
  - HTTP method (e.g., GET, POST).
  - URL endpoint.
  - Headers (e.g., Content-Type).
  - Optional body (for POST/PUT).

---

## Step 2: Controller Layer Receives the Request
- The request reaches the **Controller** via mappings like `@GetMapping`, `@PostMapping`.
- The controller processes the incoming data and calls the appropriate service.

---

## Step 3: Service Layer Processes the Request
- The controller calls a method from the **Service Layer**.
- The service contains the business logic and interacts with the repository.

---

## Step 4: Repository Layer Interacts with the Database
- The service calls the **Repository Layer** to fetch data from the database.
- The repository uses methods from JPA or custom queries to retrieve data.

---

## Step 5: Data Returned to the Service Layer
- The repository returns the data to the service layer.
- The service might perform further processing if needed.

---

## Step 6: Controller Returns the Response
- The controller returns the processed data as a response.
- The response can be in JSON or XML format.

---

## Step 7: Postman Receives the Response
- Postman displays the response status, headers, and body.
- The response status (e.g., 200 OK, 404 Not Found) indicates the result of the API call.

---

# 6. application.properties and application.yml in Spring Boot

## application.properties
- A key-value pair format file.
- Commonly used for configuring application properties.
- Located in the `src/main/resources` directory.

## application.yml
- Uses YAML format for configuration.
- Offers a more hierarchical and readable structure.
- Located in the `src/main/resources` directory.

# 7. Naming Differences between GraphQL and REST

## Naming Conventions in REST

- **Endpoint-based Naming:** 
  - Each resource has a dedicated URL.
  - Uses HTTP methods (GET, POST, PUT, DELETE) to perform CRUD operations.
- **Plural Resource Names:** 
  - Endpoints often use plural nouns to represent collections.

### Example:
```
GET /users           - Fetch all users
GET /users/1         - Fetch user with ID 1
POST /users          - Create a new user
PUT /users/1         - Update user with ID 1
DELETE /users/1      - Delete user with ID 1
```

---

## Naming Conventions in GraphQL

- **Query and Mutation-based Naming:**
  - Uses **queries** to fetch data.
  - Uses **mutations** to update data.
- **Singular or Descriptive Naming:**
  - Focuses on what data to retrieve rather than defining endpoints.

### Example:
```
query {
  user(id: 1) {
    name
    age
  }
}

mutation {
  createUser(name: "John", age: 30) {
    id
    name
  }
}
```
---

# 8. Real-World Examples of the N+1 Problem in REST and How GraphQL Solves It

The **N+1 problem** occurs when an application needs to make N+1 database queries to retrieve related data. This often happens in REST APIs when fetching nested or associated data.

---

## Example 1: Fetching Posts with Comments

### REST Approach:
- Endpoint: `/posts`
- Problem: Fetching a list of posts and their comments results in multiple queries.
  - One query to fetch all posts.
  - N additional queries to fetch comments for each post.

#### Example:
```
GET /posts          -> Returns list of posts (N items)
GET /posts/1/comments -> Returns comments for post 1
GET /posts/2/comments -> Returns comments for post 2
...
```
- **N+1 Problem:** 
  - If there are 10 posts, it results in 11 queries (1 for posts + 10 for comments).

### GraphQL Approach:
- Single Query to fetch posts and their comments.
#### Example:
```graphql
query {
  posts {
    id
    title
    comments {
      id
      content
    }
  }
}
```
- **Solution:** 
  - Only one query is needed to fetch both posts and their comments.

---

## 8.2 Example 2: Fetching Users with Their Orders

### REST Approach:
- Endpoint: `/users`
- Problem: Fetching a list of users and their orders requires multiple queries.
  - One query to fetch all users.
  - N additional queries to fetch orders for each user.

#### Example:
```
GET /users          -> Returns list of users (N items)
GET /users/1/orders -> Returns orders for user 1
GET /users/2/orders -> Returns orders for user 2
...
```
- **N+1 Problem:**
  - If there are 20 users, it results in 21 queries (1 for users + 20 for orders).

### GraphQL Approach:
- Single Query to fetch users and their orders.
#### Example:
```graphql
query {
  users {
    id
    name
    orders {
      orderId
      product
    }
  }
}
```
- **Solution:** 
  - Only one query is needed to fetch both users and their orders.

---

# 9. API Implementations: REST and GraphQL

## REST

### Description:
- Implement a DELETE API to remove a post by ID.
- Handle exception cases such as post not found.

### Controller:
```java
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        try {
            postService.deletePostById(id);
            return ResponseEntity.ok("Post deleted successfully.");
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
}
```

### Service:
```java
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void deletePostById(Long id) {
        if (!postRepository.existsById(id)) {
            throw new PostNotFoundException("Post with ID " + id + " not found.");
        }
        postRepository.deleteById(id);
    }
}
```
---

## GraphQL

### Schema Definition:
```graphql
type Post {
  id: ID!
  title: String!
  content: String!
}

type Query {
  getAllPost: [Post]
}
```

### GraphQL Query:
```graphql
query {
  getAllPost {
    id
    title
    content
  }
}
```

### GraphQL Controller:
```java
@GraphQLApi
@RestController
public class PostGraphQLController {

    @Autowired
    private PostService postService;

    @QueryMapping
    public List<Post> getAllPost() {
        return postService.getAllPosts();
    }
}
```

### Service Method:
```java
public List<Post> getAllPosts() {
    return postRepository.findAll();
}
```
---
