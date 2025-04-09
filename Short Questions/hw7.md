# 1. List all of the annotations you learned from class and homework to annotaitons.md
# 2. Type out the code for the Comment feature of the class project.
# 3. In postman, call all of the APIs in PostController and CommentController.
# 4. What is JPA? and what is Hibernate?
- Java Persistence API is a specification that defines how to manage relational data. It directly maps java objects to database tables, instead of writing SQL (you can still write sql with it).
- Hibernate is an implementation of JPA (and much more). It's a powerful ORM (Object-Relational Mapping) framework for Java.

# 5. What is Hikari? what is the benefits of connection pool?
**Hikari** is a high-performance JDBC connection pool library for Java. 

A connection pool is a cache of database connections maintained so that connections can be reused when future requests to the database are required. It avoids creation and deletion of connections, which is expensive and time-consuming. It is also easier to manage and has better scalability.

# 6. What is the @OneToMany, @ManyToOne, @ManyToMany? write some examples.
These are annotations that define relationship between different entities.

```java
@Entity
public class Department {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department")
    private List<Professor> professors;
}

@Entity
public class Professor {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "professor")
    private List<Course> courses;
}

@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses;
}
```

# 7. What is the cascade = CascadeType.ALL, orphanRemoval = true? and what are the other CascadeType and their features? In which situation we choose which one?
`cascade = CascadeType.ALL`:\
This means that all operations (persist, merge, remove, refresh, detach) on the parent entity will be automatically applied to the child entities.

`orphanRemoval = true`:\
This means that if a child entity is removed from the collection in the parent, it will also be deleted from the database.

```java
@Entity
public class Order {
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;
}
```

# 8. What is the fetch = FetchType.LAZY, fetch = FetchType.EAGER? what is the difference? In which situation you choose which one?
- Lazy loading means the related entity or collection is not loaded from the database until it is accessed.
- Use `LAZY` when:
    - You don't always need the associated data.
    - You want to optimize performance and reduce memory/DB load.
    - You’re dealing with large collections or complex object graphs.
- Eager loading means the associated entity is fetched immediately with the parent entity.
- Use `EAGER` when:
    - You almost always need the related data along with the parent.
    - The associated object is small and won't impact performance.
    - You want to reduce the number of separate queries.


# 9. What is the rule of JPA naming convention? Shall we implement the method by ourselves? Could you list some examples?
Naming conventions often refer to how JPQL query method names are constructed.

We can implement the methods when we need to write complex query or boost query optimizations like joins.

| Method Name                               | JPQL Equivalent                                                                 | Description                                 |
|-------------------------------------------|----------------------------------------------------------------------------------|---------------------------------------------|
| `findByUsername(String username)`         | `SELECT u FROM User u WHERE u.username = ?1`                                     | Find user by exact username                 |
| `findByEmailAndStatus(String email, String status)` | `SELECT u FROM User u WHERE u.email = ?1 AND u.status = ?2`           | Find by multiple fields (AND)               |
| `findByAgeGreaterThan(int age)`          | `SELECT u FROM User u WHERE u.age > ?1`                                          | Find users older than given age             |
| `findByCreatedAtBetween(Date start, Date end)` | `SELECT u FROM User u WHERE u.createdAt BETWEEN ?1 AND ?2`               | Find users created in date range            |
| `findByUsernameLike(String pattern)`     | `SELECT u FROM User u WHERE u.username LIKE ?1`                                  | Pattern matching                            |
| `findByRoleIn(List<String> roles)`       | `SELECT u FROM User u WHERE u.role IN (?1)`                                      | Match against list of roles                 |
| `findTop3ByOrderByAgeDesc()`             | `SELECT u FROM User u ORDER BY u.age DESC` (limit 3)                             | Get top 3 users by age, descending          |
| `findFirstByEmail(String email)`         | `SELECT u FROM User u WHERE u.email = ?1 LIMIT 1`                                | Get the first matching result               |
| `findByIsActiveTrue()`                   | `SELECT u FROM User u WHERE u.isActive = true`                                   | Find all active users                       |
| `findByDepartment_Name(String name)`     | `SELECT u FROM User u JOIN u.department d WHERE d.name = ?1`                     | Query by nested entity field (with join)    |


# 10.  Try to use JPA advanced methods in your class project. In the repository layer, you need to use the naming convention to use the method provided by JPA.


# 11. (Optional) Check out a new branch(https://github.com/TAIsRich/springboot-redbook/tree/hw02_01_jdbcTemplate) from branch 02_post_RUD, replace the dao layer using JdbcTemplate.
# 12. Type the code, you need to checkout new branch from branch 02_post_RUD, name the new branch with https://github.com/TAIsRich/springboot-redbook/tree/hw05_01_slides_JPQL.
# 13.  What is JPQL?
JPQL stands for Java Persistence Query Language. It's a query language used in Java applications that use the Java Persistence API (JPA) to manage relational data in a database. It looks like this: `SELECT u FROM User u WHERE u.age > 25`. Notice how we use class name `User`.


# 14.  What is @NamedQuery and @NamedQueries?
`@NamedQuery` is used to define a single named JPQL query. It’s placed at the class level (typically on an entity class).

When you want to define multiple named queries on a single entity, use `@NamedQueries`, which is just a container for multiple `@NamedQuery` annotations.
```java
@Entity
@NamedQueries({
    @NamedQuery(
        name = "User.findByEmail",
        query = "SELECT u FROM User u WHERE u.email = :email"
    ),
    @NamedQuery(
        name = "User.findAll",
        query = "SELECT u FROM User u"
    )
})
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
}
```

# 15.  What is @Query? In which Interface we write the sql or JPQL?
It is used to write custom queries (SQL or JPQL). It is placed above method in a repository interface.

```java
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);
}

```

# 16. What is HQL and Criteria Queries?
HQL(Hibernate Query Language) is similar to SQL but operates on Java objects (entities), not database tables.
```java
String hql = "FROM Employee E WHERE E.salary > :salary";
Query query = session.createQuery(hql);
query.setParameter("salary", 50000);
List<Employee> results = query.list();
```

Criteria queries are programmatic, meaning you build them using Java code rather than writing query strings. They are type-safe and easier to construct dynamically.
```java
CriteriaBuilder builder = session.getCriteriaBuilder();
CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
Root<Employee> root = criteria.from(Employee.class);
criteria.select(root).where(builder.gt(root.get("salary"), 50000));
List<Employee> results = session.createQuery(criteria).getResultList();
```


# 17. What is EnityManager?
EntityManager is the primary interface used to interact with the persistence context. It provides CRUB operations on the entity.

Hibernate uses `EntityManager` to implements methods defined in JPA.

# 18. What is SessionFactory and Session?
`SessionFactory` creates and manages `Session` objects. It follows a factory design pattern and is thread-safe.

The `Session` object provides an interface between the application and data stored in the database.

# 19.  What is Transaction? how to manage your transaction?
Transaction represent series of atomic operations that need to be done together. In other words, a unit of work. It follows the principle of ACID: if one step fails, the whole transaction fails.

Usually, transactions are declared using `@Transactional` annotation.

# 20. What is hibernate Caching? Explain Hibernate caching mechanism in detail.
Hibernate Caching is a feature in Hibernate ORM that improves performance by decreasing database queries.

There are two levels of caching:\
**First-level**
  - session caches
  - When you get same data in same session, no query is fired

**Second-level**
  - SessionFactory cache, shared across sessions
  - Not enabled by default
  - Stable data

Hibernate will check: First-level -> Second-level -> database 

# 21. What is the difference between first-level cache and second-level cache?
First-level is session specific. Second-level is not. Second-level usually stores more stable data and is shared across sessions.

# 22. How do you understand @Transactional? (https://github.com/TAIsRich/tutorial-transaction)
`@Transactional` basically tells Spring that all of the following operations have to be successful. If all of the operations are successful, then commit the change. Otherwise, rollback to original state.

