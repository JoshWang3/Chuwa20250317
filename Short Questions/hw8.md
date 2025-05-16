1. List all of the annotations you learned from class and homework to annotaitons.md
https://www.notion.so/Java-Annotations-1f59e620eb698072a469f8ec2c89a8ac?pvs=4
2. Type out the code for the Comment feature of the class project.
3. In postman, call all of the APIs in PostController and CommentController.
4. What is JPA? and what is Hibernate?
* **JPA** (Java Persistence API): A specification for accessing, persisting, and managing data using Java objects.
* **Hibernate**: A popular implementation of JPA. Provides powerful ORM (Object Relational Mapping) features.


5. What is Hiraki? what is the benefits of connection pool?
* **HikariCP**: A high-performance JDBC connection pooling library used by default in Spring Boot.
* **Benefits of connection pool**:

  * Reuses database connections → faster
  * Reduces overhead of creating/closing connections
  * Helps scale your application efficiently


6. What is the @OneToMany, @ManyToOne, @ManyToMany ? write some examples.
```java
// One Post has many Comments
@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
private List<Comment> comments;

// Many Comments belong to one Post
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "post_id")
private Post post;

// Many Users like many Posts
@ManyToMany
@JoinTable(
  name = "user_post_likes",
  joinColumns = @JoinColumn(name = "user_id"),
  inverseJoinColumns = @JoinColumn(name = "post_id")
)
private Set<Post> likedPosts;
```

7. What is the cascade = CascadeType.ALL, orphanRemoval = true ? and what are the other CascadeType 
and their features? In which situation we choose which one?

* `CascadeType.ALL`: All operations (persist, merge, remove, refresh, detach) are cascaded.
* `orphanRemoval = true`: If a child is removed from the parent, it will be deleted from the DB.

**Other Cascade Types:**

* `PERSIST`: Child is saved with parent
* `REMOVE`: Child is deleted when parent is deleted
* `MERGE`, `DETACH`, `REFRESH`: Sync-related operations

Use `ALL` when the child’s lifecycle is fully tied to the parent.


8. What is the fetch = FetchType.LAZY, fetch = FetchType.EAGER ? what is the difference? In which 
situation you choose which one?
* `LAZY`: Fetch data only when accessed (default for `@OneToMany`)
* `EAGER`: Fetch immediately with the parent (default for `@ManyToOne`)

Use `LAZY` for better performance unless data is always needed.


9. What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list 
some examples?
* Method names in Repository auto-generate SQL/JPQL:

```java
User findByEmail(String email);
List<Post> findByTitleContaining(String keyword);
```

You don’t need to implement methods yourself if you follow naming conventions.


10. Try to use JPA advanced methods in your class project. In the repository layer, you need to use the 
naming convention to use the method provided by JPA.
11. (Optional) Check out a new branch(https://github.com/TAIsRich/springboot-redbook/tree/hw02_01_jdbcT
emplate) from branch 02_post_RUD, replace the dao layer using JdbcTemplate.
12. type the code, you need to checkout new branch from branch 02_post_RUD, name the new branch with h
ttps://github.com/TAIsRich/springboot-redbook/tree/hw05_01_slides_JPQL.
13. What is JPQL?
* Java Persistence Query Language
* Object-oriented query language, similar to SQL but for entities, not tables

```java
@Query("SELECT p FROM Post p WHERE p.title = ?1")
List<Post> searchByTitle(String title);
```

14. What is @NamedQuery and @NamedQueries?

@NamedQuery and @NamedQueries are used to define static, precompiled JPQL (Java Persistence Query Language) queries at the entity level. 

These queries are associated with a specific entity class and are typically defined once and reused many times.
```java
@Entity
@NamedQuery(name = "Post.findByTitle", query = "SELECT p FROM Post p WHERE p.title = :title")
public class Post {}
```
Predefined queries at the entity level.

15. What is @Query? In which Interface we write the sql or JPQL?
* Used in **Repository interfaces**:

```java
@Query("SELECT p FROM Post p WHERE p.category = :category")
List<Post> findByCategory(@Param("category") String category);
```


16. What is HQL and Criteria Queries?
* **HQL**: Hibernate Query Language — object-based
* **Criteria API**: Type-safe, programmatic way to build queries


17. What is EnityManager?
* Core JPA interface used for interacting with persistence context
* Handles CRUD operations, queries, transactions


18. What is SessionFactory and Session?
* `SessionFactory`: Creates Hibernate `Session` objects
* `Session`: Interface for DB operations (similar to `EntityManager`)


19. What is Transaction? how to manage your transaction?
* A group of operations executed as a single unit of work
* Spring uses `@Transactional` for automatic transaction management


20. What is hibernate Caching? Explain Hibernate caching mechanism in detail.
* Improves performance by reducing DB hits

#### First-Level Cache:

* Enabled by default (per Session)
* Stores entities for current transaction

#### Second-Level Cache:

* Shared across sessions
* Uses external provider (e.g. Ehcache, Redis)


21. What is the difference between first-level cache and second-level cache?

| Cache Level  | Scope          | Default | Shared |
| ------------ | -------------- | ------- | ------ |
| First-Level  | Session        | Yes     | No     |
| Second-Level | SessionFactory | No      | Yes    |


22. How do you understand @Transactional? (https://github.com/TAIsRich/tutorial-transaction)
* Manages rollback/commit automatically
* Supports propagation, isolation levels
