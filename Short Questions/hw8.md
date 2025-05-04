1. List all of the annotations you learned from class and homework to annotaitons.md
2. Type out the code for the Comment feature of the class project.
```java
package chuwa.backend.redbook.controller;

import chuwa.backend.redbook.dto.CommentDto;
import chuwa.backend.redbook.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.createComment(commentDto));
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getComments() {
        return ResponseEntity.ok(commentService.getComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getComment(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateComment(id, commentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok("Delete: " + id.toString());
    }
}
```
```java
package chuwa.backend.redbook.service;

import chuwa.backend.redbook.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto);
    List<CommentDto> getComments();
    CommentDto getComment(Long id);
    CommentDto updateComment(Long id, CommentDto commentDto);
    void deleteComment(Long id);
}
```
```java
package chuwa.backend.redbook.service.impl;

import chuwa.backend.redbook.dao.CommentRepository;
import chuwa.backend.redbook.dto.CommentDto;
import chuwa.backend.redbook.entity.Comment;
import chuwa.backend.redbook.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Comment savedComment = commentRepository.save(comment);
        return mapToDto(savedComment);
    }

    @Override
    public List<CommentDto> getComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getComment(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id).orElse(null);
        comment.setContent(commentDto.getContent());

        Comment updatedComment = commentRepository.save(comment);

        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        commentRepository.delete(comment);
    }

    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setPostId(comment.getPostId());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setPostId(commentDto.getPostId());
        return comment;
    }
}
```
```java
package chuwa.backend.redbook.dao;

import chuwa.backend.redbook.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
```
```java
package chuwa.backend.redbook.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="content", nullable = false)
    private String content;

    @Column(name="post_id", nullable = false)
    private Long postId;

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    public Comment() {
    }

    public Comment(Long id, String content, Long postId, LocalDateTime createdDateTime) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.createdDateTime = createdDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }
}
```
```java
package chuwa.backend.redbook.dto;

public class CommentDto {
    private Long id;
    private String content;
    private Long postId;

    public CommentDto() {
    }

    public CommentDto(Long id, String content, Long postId) {
        this.id = id;
        this.content = content;
        this.postId = postId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
```
3. In postman, call all of the APIs in PostController and CommentController.
4. What is JPA? and what is Hibernate?
JPA (Java Persistence API):
A standard specification in Java for mapping Java objects to relational database tables. It defines interfaces, not implementations.

Hibernate:
An implementation of the JPA specification. It provides the actual logic for persisting Java objects into databases and adds extra features beyond JPA.

5. What is Hiraki? what is the benefits of connection pool?
HikariCP:
A high-performance JDBC connection pool library used in Java applications. It's known for being fast, lightweight, and reliable.

Benefits of Connection Pool:
Improves performance by reusing database connections.
Reduces overhead of opening/closing connections repeatedly.
Limits resources to avoid exhausting DB connections.
Supports scaling under high load.

6. What is the @OneToMany, @ManyToOne, @ManyToMany ? write some examples.
OneToMany:One entity has many related entities.
```java
@Entity
   public class User {
   @OneToMany(mappedBy = "user")
   private List<Order> orders;
   }
```
@ManyToOne
Many entities relate to one entity.
```java
@Entity
public class Order {
    @ManyToOne
    private User user;
}
```
@ManyToMany
Many entities relate to many entities.
```java
@Entity
public class Student {
    @ManyToMany
    private List<Course> courses;
}

@Entity
public class Course {
    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
}
```

7. What is the cascade = CascadeType.ALL, orphanRemoval = true ? and what are the other CascadeType
   and their features? In which situation we choose which one?
cascade and orphanRemoval are powerful features in JPA that help manage related entities automatically — especially useful in parent-child relationships like Post and Comment.
orphanRemoval = true: If a child is removed from the parent’s collection, and it is no longer referenced elsewhere, then it should be automatically deleted from the database.

8. What is the fetch = FetchType.LAZY, fetch = FetchType.EAGER ? what is the difference? In which
   situation you choose which one?
fetch = FetchType.LAZY: The related data is loaded only when accessed.
Better performance, especially with large data or complex relationships.

fetch = FetchType.EAGER
The related data is loaded immediately with the parent entity.
Slower, but ensures all data is available right away.

9. What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list
   some examples?
JPA uses property-based mapping (getters/setters) or field-based mapping (direct fields). Naming follows Java bean conventions:
for basic CRUD operations: Use Spring Data JPA and interfaces like JpaRepository — no need to implement them.
10. Try to use JPA advanced methods in your class project. In the repository layer, you need to use the
    naming convention to use the method provided by JPA.
11. (Optional) Check out a new branch(https://github.com/TAIsRich/springboot-redbook/tree/hw02_01_jdbcT
    emplate) from branch 02_post_RUD, replace the dao layer using JdbcTemplate.
12. type the code, you need to checkout new branch from branch 02_post_RUD, name the new branch with h
    ttps://github.com/TAIsRich/springboot-redbook/tree/hw05_01_slides_JPQL.
13. What is JPQL?
JPQL stands for Java Persistence Query Language. It is a query language used to write database queries against JPA entities, not directly on tables like in SQL.14. What is @NamedQuery and @NamedQueries?
    @Query("SELECT u FROM User u WHERE u.age > :age")
    List<User> findUsersOlderThan(@Param("age") int age);
14. What is @NamedQuery and @NamedQueries?
These are annotations in JPA used to define static, pre-defined JPQL queries, often directly inside the entity class.
16. What is @Query? In which Interface we write the sql or JPQL?
@Query is a Spring Data JPA annotation used to write custom queries (either in JPQL or native SQL) directly inside a repository interface method.
16. What is HQL and Criteria Queries?
HQL is very similar to JPQL, but it's specific to Hibernate. In practice, JPQL and HQL are almost the same, and you can use either when working with Hibernate + JPA.
17. What is EnityManager?
The EntityManager is the main interface in JPA (Java Persistence API) that manages the persistence lifecycle of entities. Think of it as the bridge between your Java application and the database.
18. What is SessionFactory and Session?
SessionFactory is a factory class in Hibernate that is responsible for creating Session objects. It's one of the core components of Hibernate's configuration and session management.
Session is the primary interface used by Hibernate to interact with the database. It represents a single unit of work in the Hibernate ORM framework and acts as a gateway to perform CRUD operations.
19. What is Transaction? how to manage your transaction?
    Transaction is a unit of work that is performed on the database. It ensures that a series of CRUD operations are executed in a consistent and reliable way.
    The basic workflow of a transaction involves:
    Start: Begin the transaction.
    Operations: Perform one or more database operations (e.g., saving, updating, deleting).
    Commit: If everything is successful, commit the transaction (making all changes permanent).
    Rollback: If there is an error, roll back the transaction to undo all changes.
20. What is hibernate Caching? Explain Hibernate caching mechanism in detail.
    Hibernate Caching refers to the ability of Hibernate to store data temporarily in memory (cache) to improve the performance of read-heavy operations.
    Hibernate's caching mechanism includes the First-Level Cache, Second-Level Cache, and Query Cache.
    First-Level Cache (Session Cache): stores objects that are loaded or persisted within the session.
    Second-Level Cache (Global Cache): stores entities, collections, and query results to avoid querying the database repeatedly.
    Query Cache: If a query has been executed before and the underlying data hasn't changed, the result can be fetched from the cache instead of executing the query against the database again.
21. What is the difference between first-level cache and second-level cache?
    The first-level cache and second-level cache are both used to improve the performance of data access in applications (especially in ORM tools like Hibernate), but they differ in scope, configuration, and behavior:
22. How do you understand @Transactional? (https://github.com/TAIsRich/tutorial-transaction)
    The @Transactional annotation in Spring is used to define the scope of a database transaction. When a method is annotated with @Transactional, Spring ensures that all the operations within that method either complete successfully as a unit or are rolled back in case of failure, preserving data integrity.