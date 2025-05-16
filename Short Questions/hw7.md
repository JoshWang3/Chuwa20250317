1. List all of the Spring data related annotations your learned and explain its usage.

| 注解                                        | 用途                       |
| ----------------------------------------- | ------------------------ |
| `@Entity`                                 | 将类映射为数据库表                |
| `@Table(name = "xxx")`                    | 指定数据库表名                  |
| `@Id`                                     | 主键字段                     |
| `@GeneratedValue`                         | 主键生成策略（如 auto-increment） |
| `@Column(name = "xxx")`                   | 指定字段名及属性                 |
| `@Repository`                             | 表示数据访问层，用于自动装配           |
| `@Query`                                  | 自定义 SQL/HQL 查询语句         |
| `@Modifying`                              | 用于修改操作的 SQL              |
| `@Transactional`                          | 事务管理                     |
| `@OneToMany`, `@ManyToOne`, `@JoinColumn` | 实体之间的关联关系                |



2. What is DTO, VO, Payload, PO, model, DAO?

| 类型                        | 含义          | 作用                       |
| ------------------------- | ----------- | ------------------------ |
| DTO（Data Transfer Object） | 数据传输对象      | 控制层与服务层之间传递数据用           |
| VO（View Object）           | 视图对象        | 专门为前端展示设计的对象             |
| Payload                   | 有时指 DTO 的变体 | 通常指请求体负载（比如接收 JSON 请求体）  |
| PO（Persistent Object）     | 持久化对象       | 数据库中的映射类（即 Entity）       |
| Model                     | 通常泛指业务模型    | Spring MVC 中指用于传递到视图层的数据 |
| DAO（Data Access Object）   | 数据访问对象      | 封装对数据库的 CRUD 操作          |


3. What is @JsonProperty("description_yyds")

将 Java 字段绑定到 JSON 字段名：
```Java
@JsonProperty("description_yyds")
private String description;
```
当序列化或反序列化 JSON 时，对应字段名为 description_yyds。


4. Explain the purpose of following dependency?
```Java
<dependency>
<groupId>com.fasterxml.jackson.core</groupId>
<artifactId>jackson-databind</artifactId>
<version>2.13.3</version>
<scope>compile</scope>
</dependency>
```
作用：用于 JSON 的序列化和反序列化（Java ↔ JSON）。核心类包括 ObjectMapper


5. What is spring-boot-stater? 
- what dependecies in the below starter? do you know any starters?
```Java
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

**starter** 是一组预配置依赖的集合，比如：

```xml
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

包含：

* spring-web
* spring-webmvc
* Jackson
* Tomcat
  常见的 starter：
* spring-boot-starter-data-jpa
* spring-boot-starter-security
* spring-boot-starter-test


6. Explain @RequestMapping(value = "/users", method = RequestMethod.POST) ? could you list CRUD by 
this style?

```java
@RequestMapping(value = "/users", method = RequestMethod.POST)
public ResponseEntity<User> createUser(@RequestBody User user) { ... }
```
当客户端发送一个 POST 请求到 /users 路径，就会执行这个方法。

CRUD 示例：

```java
@RequestMapping(value = "/users", method = RequestMethod.GET) // Read
@RequestMapping(value = "/users", method = RequestMethod.POST) // Create
@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT) // Update
@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE) // Delete
```

7. What is ResponseEntity? why do we need it?
```Java
new ResponseEntity<>(postResponse, HttpStatus.OK);
        new ResponseEntity<>(postResponse, HttpStatus.CREATED);
        ResponseEntity.ok(postService.getPostById(id));
```
用于自定义 HTTP 响应体和状态码.

好处：

* 控制状态码（200, 201, 404 等）
* 可包含 headers
* 可返回任意对象


8. What is ResultSet in jdbc? and describe the flow how to get data using JDBC
* `ResultSet` 表示数据库查询结果集
  JDBC 获取数据流程：

```java
Connection conn = DriverManager.getConnection(...);
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM user");
while (rs.next()) {
    String name = rs.getString("name");
}
```


9. Compare Spring Data JPA vs Hibernate vs JDBC.

| 特性   | JDBC   | Hibernate | Spring Data JPA |
| ---- | ------ | --------- | --------------- |
| 级别   | 最底层    | 中间层       | 最上层             |
| 操作   | 手写 SQL | HQL/自动映射  | 接口+注解即可         |
| 灵活性  | 高      | 中         | 最低              |
| 学习成本 | 低      | 中         | 高               |
| 适合场景 | 复杂 SQL | 复杂关系      | 快速开发            |


10. Learn how to use ObjectMapper by this example.
[1]https://github.com/TAIsRich/chuwa-eij-tutorial/blob/main/02-java-core/src/main/java/com/chuwa/exercise/oa/api/FoodOutletJackson.java
```Java
FoodOutlet foodOutlet = objectMapper.readValue(resBody, FoodOutlet.class);
String s = objectMapper.writeValueAsString(foodOutlet);
objectMapper.readTree() // learn how to use it?
```
12. What is the serialization and desrialization?
[1]https://hazelcast.com/glossary/serialization/
- Serialization: The process of converting a Java object into a byte stream or a JSON string, so it can be easily stored (e.g., in a file or database) or transmitted (e.g., over a network).
- Deserialization: The reverse process — converting a byte stream or JSON string back into a Java object, so your program can work with the original data structure.

12. use stream api to get the average of the array [20, 3, 78, 9, 6, 53, 73, 99, 24, 32].
```java
List<Integer> nums = Arrays.asList(20, 3, 78, 9, 6, 53, 73, 99, 24, 32);
double avg = nums.stream().mapToInt(i -> i).average().orElse(0);
System.out.println(avg);
```



13. 抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/03_post_pageable 下的代码
14. 抄写并理解 https://github.com/TAIsRich/springboot-redbook/tree/04_comment 下的代码