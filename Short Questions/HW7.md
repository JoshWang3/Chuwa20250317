# List all of the annotations you learned from class and homework to annotaitons.md
✅
# Type out the code for the Comment feature of the class project.
## `Comment` Entity
```java
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String content;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;  // Assuming there's a Post entity

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
}
```
## `CommentRepository`
```java
package com.example.demo.repository;

import com.example.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}

```
## `CommentService`
```java
package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}

```

## `CommentController`
```java
package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getComments(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}

```

# In postman, call all of the APIs in PostController and CommentController.
## PostController API Calls
### Create a Post
- Method: `POST`
- URL: `http://localhost:8080/api/posts`
- Body (raw JSON):
```json
{
  "title": "First Blog Post"
}
```
### Get All Posts
- Method: `GET`
- URL: `http://localhost:8080/api/posts`
### Get a Single Post by ID
- Method: `GET`
- URL: `http://localhost:8080/api/posts/1`
### Update a Post
- Method: `PUT`
- URL: `http://localhost:8080/api/posts/1`
- Body (raw JSON):
```json
{
  "title": "Updated Blog Title"
}
```
### Delete a Post
- Method: `DELETE`
- URL: `http://localhost:8080/api/posts/1`

## CommentController API Calls
### Create a Comment
- Method: `POST`
- URL: `http://localhost:8080/api/comments`
- Body (raw JSON):
```json
{
  "author": "Alice",
  "content": "Great post!",
  "post": {
    "id": 1
  }
}
```
### Get Comments for a Post
- Method: `GET`
- URL: `http://localhost:8080/api/comments/post/1`
### Delete a Comment
- Method: `DELETE`
- URL: `http://localhost:8080/api/comments/1`

# What is JPA? and what is Hibernate?
## What is JPA?
JPA stands for Java Persistence API.
- It is a specification, not an implementation.
- JPA defines how Java objects (like `Post`, `Comment`, etc.) are mapped to database tables.
- It provides annotations like `@Entity`, `@Id`, `@OneToMany`, etc.
- JPA allows you to write database queries using object-oriented code.
## What is Hibernate?
Hibernate is a popular implementation of the JPA specification.
- It is the actual library that does the work under the hood: translating Java code into SQL queries, executing them, and managing database connections.
- Hibernate supports extra features beyond the basic JPA standard, like caching, custom types, etc.

# What is Hiraki? what is the benefits of connection pool?
## What is HikariCP?
HikariCP is a high-performance JDBC connection pool.
- It is the default connection pool used by Spring Boot (since version 2.0).
- “Hikari” means “light” in Japanese — it’s named that way because it’s designed to be fast and lightweight.
- It manages a pool of database connections efficiently to reduce the overhead of creating and closing connections repeatedly.
## Benefits of Connection Pool (like HikariCP):
| Benefit        | Explanation                                                                 |
|----------------|-----------------------------------------------------------------------------|
| Speed        | No need to create a new connection every time — just reuse one.             |
| Less Overhead | Reduces CPU and memory usage on both your app and the database server.     |
| Smart Management | HikariCP manages things like max pool size, idle timeouts, leak detection, etc. |
| Scalability   | Helps your app handle more users or requests efficiently.                  |
| Resilience    | Handles issues like dead connections automatically.                        |

# What is the @OneToMany, @ManyToOne, @ManyToMany? write some examples.
## `@OneToMany`
One entity has many of another entity.
### Example: A `Post` has many `Comment`s
```java
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
```
```java
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
```
## `@ManyToOne`
Many entities belong to one entity.
### Example (same as above): Many `Comment`s belong to one `Post`
```java
@ManyToOne
@JoinColumn(name = "post_id")
private Post post;
```
## `@ManyToMany`
Many entities are related to many other entities.
### Example: A `Student` can enroll in many `Course`s, and each `Course` can have many `Student`s.
```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}
```
```java
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
}
```
# What is the `cascade = CascadeType.ALL, orphanRemoval = true`? and what are the other CascadeType and their features? In which situation we choose which one?
## `cascade = CascadeType.ALL`
This tells JPA: "When I do something to the parent entity, do the same thing to the child entities."

With `CascadeType.ALL`, the following operations are passed from parent to child:
- `PERSIST` (save)
- `MERGE` (update)
- `REMOVE` (delete)
- `REFRESH`
- `DETACH`
## `orphanRemoval = true`
This tells JPA: "If a child is removed from the parent list, delete it from the database too."

Use it when you want automatic cleanup of "orphan" child objects.

## All `CascadeType` Values (and what they do)
| CascadeType | Meaning                                                       |
|-------------|---------------------------------------------------------------|
| PERSIST     | Save child when saving parent                                 |
| MERGE       | Merge (update) child when updating parent                     |
| REMOVE      | Delete child when deleting parent                             |
| REFRESH     | Refresh child from DB when refreshing parent                  |
| DETACH      | Detach child from persistence context when detaching parent   |
| ALL         | All of the above                                              |

## When to use which?
| Use Case                                 | CascadeType        | orphanRemoval |
|------------------------------------------|--------------------|----------------|
| Post with embedded comments (tight couple) | `CascadeType.ALL`     | `true`           |
| Shopping Cart with Items                  | `CascadeType.ALL`     | `true`           |
| Book with Author (shared by other books)  | Maybe only `PERSIST`  | `false`          |
| Student-Course (ManyToMany, shared)       | Usually no cascade  | `false`          |

# What is the `fetch = FetchType.LAZY, fetch = FetchType.EAGER`? what is the difference? In which situation you choose which one?
## `FetchType.LAZY`
It means: Don't load the related data immediately — only load it when needed (on-demand).

This is like lazy loading. Data stays unloaded until you call something like `post.getComments()`.
## `FetchType.EAGER`
Always load the related data immediately, together with the parent entity.

This is eager loading. Even if you don’t use it, the data is already fetched.
## Summary Table
| FetchType | When it loads | Used for              | Pros                       | Cons                              |
|-----------|----------------|------------------------|-----------------------------|-----------------------------------|
| `LAZY`      | On demand       | OneToMany, ManyToMany   | Better performance          | Need open session or transaction  |
| `EAGER`     | Immediately     | ManyToOne, OneToOne     | Simpler, no lazy errors     | May load too much data            |
## In which situation you choose which one
| Situation                                       | Recommended FetchType         |
|------------------------------------------------|--------------------------------|
| Child data is large and rarely used            | `LAZY`                           |
| Child data is always needed (e.g. profile info) | `EAGER`                          |
| Many children per parent (e.g. comments on a post) | `LAZY`                        |
| Few or one child (e.g. a user's address)        | `EAGER` or `LAZY` (case by case)  |

# What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list some examples?
## JPA Naming Convention
Rule: Follow the pattern: `findBy`, `countBy`, `existsBy`, `deleteBy`, then use field names from your entity in camelCase.
## Examples
Assume you have this entity:
```java
@Entity
public class User {
    private Long id;
    private String username;
    private String email;
    private int age;
    private LocalDate createdAt;
}
```
`UserRepository` can have methods like:
```java
// Find by one field
User findByUsername(String username);

// Find by two fields
User findByUsernameAndEmail(String username, String email);

// Count
Long countByAgeGreaterThan(int age);

// Check if a user exists
boolean existsByEmail(String email);

// Delete by condition
void deleteByUsername(String username);

// Get top 5 users ordered by age
List<User> findTop5ByOrderByAgeDesc();

// Find users created after a certain date
List<User> findByCreatedAtAfter(LocalDate date);
```
## Shall we implement the method by ourselves?
No — only if:
- You need a complex query (joins, subqueries, etc.)
- You want to write native SQL
- You need custom logic inside a method

# Try to use JPA advanced methods in your class project. In the repository layer, you need to use the naming convention to use the method provided by JPA.
✅

# What is JPQL?
JPQL stands for Java Persistence Query Language.

It's an object-oriented query language used in JPA to query Java entity objects, not database tables directly.

Unlike SQL (Structured Query Language) which queries tables and columns, JPQL queries entity classes and their fields.
## Key Differences from SQL:
| SQL                          | JPQL                          |
|-----------------------------|-------------------------------|
| SELECT * FROM posts         | SELECT p FROM Post p          |
| WHERE title LIKE '%Java%'   | WHERE p.title LIKE '%Java%'   |
| Uses table/column names     | Uses entity/field names       |
| Returns rows/columns        | Returns Java objects          |

# What is `@NamedQuery` and `@NamedQueries`?
## What is `@NamedQuery`?
`@NamedQuery` is an annotation in JPA used to define a pre-written JPQL query, which is assigned a name and can be reused throughout your code.

It's like creating a "reusable JPQL statement" for your entity.
## What is `@NamedQueries`?
If you want to define multiple named queries on the same entity, you use the container annotation `@NamedQueries`.
| Benefit       | Description                             |
|---------------|-----------------------------------------|
| Reusability | Define once, use many times             |
| Testability | Easy to unit test and manage            |
| Performance | May be parsed & validated at app startup |

# What is `@Query`? In which Interface we write the sql or JPQL?
## What is `@Query`?
The `@Query` annotation lets you define custom JPQL or SQL queries directly in your repository interface, instead of using method name conventions.

It's perfect when method names get too long or complex, or when you need joins, filtering, etc.
## Where do we write it?
In your repository interface, which extends `JpaRepository`.
```java
public interface PostRepository extends JpaRepository<Post, Long> {

    // JPQL query
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword%")
    List<Post> searchByKeyword(@Param("keyword") String keyword);

    // Native SQL query
    @Query(value = "SELECT * FROM post WHERE title LIKE %:keyword%", nativeQuery = true)
    List<Post> searchByKeywordNative(@Param("keyword") String keyword);
}
```

# What is HQL and Criteria Queries?
## What is HQL?
HQL stands for Hibernate Query Language.

It’s very similar to JPQL (Java Persistence Query Language), but it’s specific to Hibernate.

It’s an object-oriented query language — you write queries based on entity class names and fields, not database tables or columns.
## What are Criteria Queries?
Criteria API is a type-safe, programmatic way to build dynamic queries in JPA.

Instead of writing query strings, you build the query using Java code.
## Features of Criteria API
| Feature       | Description                                                                 |
|---------------|-----------------------------------------------------------------------------|
| Type-safe   | Field names checked by compiler                                              |
| Dynamic     | You can build complex queries programmatically (e.g. add filters conditionally) |
| Flexible    | No string concatenation, better for large dynamic filters                    |
| Verbose     | Requires more lines of code, harder to read for simple queries               |

# What is `EnityManager`?
`EntityManager` is the main interface in JPA used to interact with the persistence context (basically the part of memory that manages your entity objects and their database state).

It’s like a middleman that helps you save, update, delete, and query your entities in the database.
## Common Methods of `EntityManager`
| Method               | Description                               |
|----------------------|-------------------------------------------|
| persist(entity)      | Insert a new entity into the DB           |
| merge(entity)        | Update an existing entity                 |
| remove(entity)       | Delete an entity                          |
| find(Class, id)      | Find by primary key                       |
| createQuery()        | Create a JPQL query                       |
| createNativeQuery()  | Create a native SQL query                 |
| getReference()       | Lazy-load an entity                       |
| flush()              | Synchronize persistence context to DB     |
| clear()              | Detach all managed entities               |

# What is `SessionFactory` and `Session`?
## What is `SessionFactory`?
- `SessionFactory` is a Hibernate object responsible for creating `Session` instances.
- It is a heavy-weight, thread-safe object — should be created once (usually at application startup).
- Think of it as a connection factory to your database.
## What is `Session`?
`Session` is similar to JPA’s `EntityManager`.

It’s a single-threaded object used for:
- Querying
- Saving
- Updating
- Deleting entities
- Transaction management

# What is Transaction? how to manage your transaction?
## What is a Transaction?
A transaction is a unit of work that must either:
- Complete entirely (commit), or
- Fail completely (rollback)

It's the database's way of ensuring data consistency and integrity — especially when multiple changes are made together.
## How to Manage Transactions?
Spring Boot uses Spring's transaction management, and it’s really easy by using `@Transactional`
```java
@Service
public class TransferService {

    @Autowired
    private AccountRepository accountRepo;

    @Transactional
    public void transferMoney(Long fromId, Long toId, Double amount) {
        Account from = accountRepo.findById(fromId).orElseThrow();
        Account to = accountRepo.findById(toId).orElseThrow();

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
    }
}
```
# What is hibernate Caching? Explain Hibernate caching mechanism in detail.
## What is Hibernate Caching?
Hibernate caching helps reduce database hits by storing frequently accessed data in memory.

Instead of querying the database every time, Hibernate can cache entities and reuse them if they haven't changed.
## Levels of Caching in Hibernate
### 1st Level Cache (Enabled by Default)
- Built into Hibernate
- Stores entities in the current Session
- Only works within a single session
- It's automatic – you don’t need to configure it
### 2nd Level Cache (Optional, Needs Setup)
- Works across sessions
- Stores entities in a shared cache region
- Requires integration with a caching provider like:
    - Ehcache
    - Hazelcast
    - Infinispan
    - Redis

# What is the difference between first-level cache and second-level cache?
## First-Level Cache (Session Cache)
| Feature               | Description                                      |
|------------------------|--------------------------------------------------|
| Enabled by Default   | Always on, no configuration needed               |
| Scope                | A single Hibernate Session                       |
| Stored In            | The memory of that session object                |
| When it works        | Only during one session's lifecycle              |
| What it stores       | Entities already loaded in the session           |
| Cleared when         | session.close() or session.clear()               |
## Second-Level Cache
| Feature            | Description                                                                 |
|--------------------|-----------------------------------------------------------------------------|
| Needs Setup       | You must enable it and choose a cache provider (Ehcache, Redis, etc.)       |
| Scope            | Shared across sessions and transactions                                     |
| Stored In        | External memory (e.g. in a caching region or file/memory grid)              |
| When it works    | Used across multiple sessions and app restarts (if persistent)              |
| What it stores   | Entity data and optionally collections (e.g. list of comments)              |
| Cleared when      | Manually or via eviction policies (TTL, LRU, etc.)                          |
##  Key Differences
| Feature              | First-Level Cache                | Second-Level Cache                              |
|----------------------|----------------------------------|--------------------------------------------------|
| Enabled by default   | ✅ Yes                           | ❌ No (must be configured)                       |
| Scope                | One session                     | Application-wide                                |
| Shared across sessions | ❌ No                          | ✅ Yes                                           |
| Cleared when         | Session is closed               | Manual or auto (TTL, LRU, etc.)                 |
| Performance Boost    | Minor                           | Significant (for read-heavy)                    |
| Configuration Needed | No                              | Yes (add dependency + config)                   |
# When to Use Each?
| Situation                              | Use Which Cache?              |
|----------------------------------------|-------------------------------|
| Always                                 | First-level cache             |
| Repeated reads across sessions         | Second-level cache            |
| Frequently updated entities            | Avoid second-level          |
| Read-heavy reference data (categories, tags) | Use second-level       |

# How do you understand `@Transactional`?
`@Transactional` is an annotation in Spring Framework that marks a method (or class) to be executed within a database transaction. It ensures data consistency and rollback on failure.
## How it works
When a method annotated with `@Transactional` is called:
- Spring opens a transaction before the method executes.
- If the method completes successfully, it commits the transaction.
- If an exception is thrown, it rolls back the transaction (by default, only on unchecked exceptions).