## 4. JPA Hibernate?

### JPA (Java Persistence API)
JPA is a specification for accessing, persisting, and managing data between Java objects and relational databases. It is part of the Java EE framework and provides a standardized way to manage relational data in Java applications.

#### Key Features of JPA:
- Object-Relational Mapping (ORM) support.
- Annotations for defining entity classes.
- Querying with JPQL (Java Persistence Query Language).
- Entity management via EntityManager.

### Hibernate
Hibernate is an ORM tool that implements the JPA specification. It simplifies the development of Java applications by mapping Java classes to database tables. Hibernate provides an abstraction layer on top of JDBC, allowing developers to work with objects rather than SQL statements.

#### Key Features of Hibernate:
- Session and SessionFactory for database operations.
- Automatic table generation.
- Support for lazy loading and caching.
- Hibernate Query Language (HQL) for database interactions.

---

## 5. Hikari connection pool

### HikariCP (Hikari Connection Pool)
HikariCP is a fast and reliable JDBC connection pool for Java applications. It is known for its performance, lightweight footprint, and efficient resource management. 

#### Key Features of HikariCP:
- **High Performance:** Low latency and high throughput for database connections.
- **Lightweight:** Minimal overhead compared to other connection pools.
- **Reliable:** Handles database connectivity issues efficiently.
- **Automatic Recovery:** Re-establishes connections when failures occur.
- **Optimized Connection Pooling:** Efficiently manages database connections.

#### Key Benefits:
1. **Improved Performance:**
   - Reusing connections avoids the costly process of establishing new connections.
   - Minimizes the impact of connection creation on application performance.

2. **Efficient Resource Management:**
   - Reduces the number of active connections to the database.
   - Prevents resource exhaustion by limiting the number of concurrent connections.

3. **Enhanced Scalability:**
   - Handles a large number of concurrent database operations efficiently.
   - Suitable for high-traffic applications where rapid database access is necessary.

4. **Connection Reuse:**
   - Minimizes the time spent on database connection setup.
   - Increases application throughput by reducing idle time.

5. **Automatic Connection Recovery:**
   - Detects and closes stale or invalid connections.
   - Prevents connection leaks by managing the pool effectively.

---

## @OneToMany, @ManyToOne, and @ManyToMany
### 1. @OneToMany
The `@OneToMany` annotation defines a one-to-many relationship between two entities. This means that one entity can be associated with multiple instances of another entity.

#### Example:
```java
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;
}

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
```
### 2. @ManyToOne
The `@ManyToOne` annotation defines a many-to-one relationship. This means that multiple entities can be associated with a single entity.

#### Example:
```java
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}
```
### 3. @ManyToMany
The @ManyToMany annotation defines a many-to-many relationship. This means that one entity can be associated with multiple instances of another entity and vice versa.

#### Example:
```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "student_course",
               joinColumns = @JoinColumn(name = "student_id"),
               inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
}

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

## 7. `cascade = CascadeType.ALL`, `orphanRemoval = true`
### `cascade = CascadeType.ALL`
This cascade type means that all JPA operations (PERSIST, MERGE, REMOVE, REFRESH, DETACH) applied to the parent entity will also be applied to the associated child entities.

### `orphanRemoval = true`
This flag automatically deletes child entities that are removed from the relationship (i.e., removed from the collection in the parent entity).


| CascadeType     | Description                                                                 |
|-----------------|-----------------------------------------------------------------------------|
| `PERSIST`       | When the parent entity is persisted, all associated child entities are also persisted. |
| `MERGE`         | When the parent entity is merged, changes in the child entities are also merged.        |
| `REMOVE`        | When the parent entity is removed, all associated child entities are also removed.      |
| `REFRESH`       | When the parent entity is refreshed, all associated child entities are refreshed too.   |
| `DETACH`        | When the parent entity is detached from the persistence context, all child entities are detached as well. |
| `ALL`           | Applies all the above cascade types (`PERSIST`, `MERGE`, `REMOVE`, `REFRESH`, `DETACH`). |

## 8.`fetch = FetchType.LAZY`, `fetch = FetchType.EAGER`
### `fetch = FetchType.LAZY`
- **Lazy fetching** means related entities are not loaded from the database until they are explicitly accessed.
- This is the **default** behavior for `@OneToMany` and `@ManyToMany` relationships.

### fetch = FetchType.EAGER
- **Eager fetching** means related entities are loaded immediately along with the parent entity.

- This is the default for @ManyToOne and @OneToOne relationships.

### When to Use Each:

| Situation                                           | Fetch Type        | Reason                                                |
|----------------------------------------------------|-------------------|--------------------------------------------------------|
| Large collections or infrequently accessed data     | `LAZY`            | Improves performance by loading only when needed       |
| Critical data always required with parent           | `EAGER`           | Simplifies logic by loading everything together        |
| REST APIs using serialization (e.g., Jackson)       | `LAZY` (with DTO) | Prevents infinite loops or heavy data loading          |
| Complex object graphs with nested relations         | `LAZY`            | Allows fine-grained control over loading behavior      |
| Small, simple relationships where data is always used| `EAGER`           | Ensures data is readily available with parent          |

---

## 9. JPA naming convention
- Method names start with a keyword: `findBy`, `readBy`, `getBy`, `countBy`, etc.
- Followed by the entity field name(s) in CamelCase.
- You can add logical operators like `And`, `Or`, `Between`, `LessThan`, `GreaterThan`, etc.

---
### method implementing
- **No**, in most cases, you don't need to implement the method logic yourself.
- If you follow the naming convention, Spring Data JPA will **automatically implement** the method.
- **Yes**, only if:
  - You need custom queries (use `@Query` or implement manually).
  - The logic is too complex to express in a method name.

---

### Examples:

```java
List<User> findByLastName(String lastName);
List<User> findByEmailAndStatus(String email, String status);
List<User> findByCreatedDateBetween(Date start, Date end);
List<User> findByAgeGreaterThanEqual(int age);
List<User> findByDepartmentNameOrderByFirstNameAsc(String departmentName);
int countByActiveTrue();

```

## 13. JPQL

### JPQL (Java Persistence Query Language)

JPQL is a **platform-independent** query language defined as part of the JPA specification. It is used to write queries against **entity objects** in a way similar to SQL but operates on the **Java class and field names**, not table and column names.

Unlike SQL, which works directly on database tables, **JPQL queries entities and their relationships**.

---

## 14. `@NamedQuery`, `@NamedQueries`?

### `@NamedQuery`
`@NamedQuery` is a JPA annotation used to define **static, reusable JPQL queries** with a name. These queries are defined at the **entity level** and can be called by name throughout the application.

### `@NamedQueries`
@NamedQueries is a container annotation used to define multiple named queries on a single entity.

---

## 15. `@Query`

### `@Query` Annotation
The `@Query` annotation is used in Spring Data JPA to define **custom JPQL or native SQL queries** directly on a repository method. It gives more flexibility than method name conventions and allows complex queries.

---

## 16. HQL and Criteria Queries

### HQL (Hibernate Query Language)

HQL is an **object-oriented query language** similar to JPQL but specific to **Hibernate**. It operates on **Hibernate entity classes and fields** rather than database tables and columns.

### Criteria Queries (JPA Criteria API)

Criteria queries are a type-safe, programmatic way to build queries using Java code rather than string-based JPQL or HQL. They are especially useful for dynamic queries.

---

## 17. EntityManager

`EntityManager` is the **core interface in JPA** used to interact with the persistence context. It is responsible for managing the **lifecycle of entity instances**, performing **CRUD operations**, and executing **queries**.

---

## 18. SessionFactory and Session

### SessionFactory (Hibernate)

`SessionFactory` is a heavyweight, **thread-safe** object provided by Hibernate. It is responsible for creating and managing `Session` objects. Typically, an application will have **only one instance** of `SessionFactory`, created at startup and reused across the app.

### Session (Hibernate)
`Session` is a lightweight, non-thread-safe object used to interact with the database. It represents a single unit of work and wraps a JDBC connection. All CRUD operations on persistent entities are performed through Session.

## 19. Transaction

A **transaction** is a sequence of one or more operations that are treated as a **single unit of work**. In databases, a transaction must satisfy the **ACID** properties:

- **A**tomicity: All operations succeed or none at all.
- **C**onsistency: Transforms the database from one valid state to another.
- **I**solation: Transactions do not interfere with each other.
- **D**urability: Changes made by a committed transaction persist even after a failure.

---

### Managing Transactions in JPA (Spring)

#### 1. **Using `@Transactional` (Declarative Approach)**
Spring provides the `@Transactional` annotation to automatically manage transactions.

#### 2. Using Programmatic Transactions
Use this when you need fine-grained control over transaction boundaries rr with PlatformTransactionManager:

---

## 20. Hibernate Caching

Hibernate caching is a mechanism that reduces the number of database hits by storing frequently accessed data in memory. It improves performance by avoiding repetitive queries and allowing faster data retrieval.

Hibernate provides two levels of caching:

### 1. First-Level Cache (Session Cache)
- Enabled by default.
- Associated with the Hibernate `Session` object.
- Stores entities within the same session context.
- Once the session is closed, the cache is cleared.

### 2. Second-Level Cache (SessionFactory Cache)
- Optional: Must be enabled explicitly.
- Shared across sessions in the same application.
- Stores entity data beyond a single session.
- Uses external providers like Redis.

## 21. First-Level Cache and Second-Level Cache
###  Key Differences

| Feature              | First-Level Cache              | Second-Level Cache              |
|----------------------|-------------------------------|----------------------------------|
| Scope                | Per session                   | Application-wide                |
| Configuration        | No configuration needed       | Requires explicit setup         |
| Eviction             | At session close              | Manual or timed eviction        |
| Persistence Context  | Yes                           | No                              |
| Use Case             | Short-term, per-request caching | Shared data, read-heavy apps     |
| Storage Medium       | Memory (per session)          | External provider (e.g., Ehcache) |

## 22. `@Transactional`

`@Transactional` is a Spring annotation used to **manage transactions declaratively**. It ensures that a method executes within a transaction context, and it will **commit or roll back** based on the success or failure of the method.

---

### Key Behaviors:
- **Commits** the transaction when the method completes successfully.
- **Rolls back** the transaction if an unchecked exception (`RuntimeException` or `Error`) is thrown.
- Can be applied at **class** or **method** level.
- Works with **Spring-managed beans** (i.e., classes annotated with `@Service`, `@Repository`, etc.).

---

### Rollback Rules:
By default:

- Rolls back on unchecked exceptions (RuntimeException, Error)

- Does not roll back on checked exceptions (Exception) unless specified

### Additional Options:

| Attribute         | Description                                               |
|------------------|-----------------------------------------------------------|
| `readOnly=true`  | Optimizes the transaction for read-only operations (no writes allowed). |
| `rollbackFor`    | Defines which exceptions (including checked ones) should trigger a rollback. |
| `noRollbackFor`  | Defines which exceptions should **not** trigger a rollback. |
| `propagation`    | Controls transaction behavior when nested or called within another transaction. |
| `isolation`      | Specifies the isolation level to manage concurrency and visibility issues. |

