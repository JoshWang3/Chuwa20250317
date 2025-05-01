1. create a file to list all of the annotaitons you learned and known, and explain the usage and how do you
understand it. you need to update it when you learn a new annotation. Please organize those annotations
well, like annotations used by entity, annotations used by controller.
1. File name: annotations.md
2. you'd better also list a code example under the annotations.

2. explain how the below annotaitons specify the table in database?
@Column(columnDefinition = "varchar(255) default 'John Snow'")
private String name;
@Column(name="STUDENT_NAME", length=50, nullable=false, unique=false)
private String studentName;
columnDefinition = "varchar(255) default 'John Snow'": This defines the column in the database as a VARCHAR with a maximum length of 255 characters.
Additionally, it sets the default value of the column to 'John Snow'.
For studentName: The column is named STUDENT_NAME, is VARCHAR(50), must not be NULL, and does not enforce uniqueness.

3. What is the default column names of the table in database for @Column ?
The default column names in the database will be firstName and operatingSystem

4. What are the layers in springboot application? what is the role of each layer?
Controller Layer (aka Web/API layer): Handles HTTP requests and responses
Service Layer: Contains business logic and application rules
Repository Layer: Communicates with the database
Model Layer (aka Entity/Domain layer): Defines data structures

5. Describe the flow in all of the layers if an API is called by Postman.
The request from Postman is mapped to the relevant controller method using annotations like @GetMapping, @PostMapping, etc.
The controller calls the corresponding method in the service class.
The service calls a method on the repository to fetch or manipulate data.
The controller receives the result from the service layer and returns a response, typically as JSON (using @ResponseBody or @RestController).

6. What is the application.properties? do you know application.yml?
application.properties
Located in src/main/resources/application.properties
It’s a Spring Boot-specific configuration file used configure runtime settings like:
Configure:
DB: spring.datasource.url=jdbc:mysql://localhost:3306/root
Server: server.port=8081
Logging: logging.level.org.springframework=DEBUG

application.yml
Same purpose with application.properties, just use different syntaxes.
7. What’s the naming differences between GraphQL vs. REST ? Why is the differences ?
                         Aspect	REST	                           GraphQL
URL (endpoint) naming	Resource-oriented (nouns)	Single endpoint (/graphql)
Operation naming	Verb is implied by HTTP method (GET, POST, etc.)	Explicitly named queries and mutations
Resource access	Multiple endpoints: /users, /users/42/posts	Single endpoint: POST /graphql
Data fields	Server defines what you get	Client chooses field names & structure
Method names	Not named directly	You name the operation: getUser, createPost, etc.


8. Provide 2 real-world examples of N+1 problem in REST that can be solved by GraphQL.
1. E-Commerce Platform: Fetching Orders and Items
Scenario in REST: Imagine an e-commerce platform where you need to retrieve a list of customer orders along with the items for each order.
In a RESTful API, you might have two separate endpoints:
/orders to retrieve all orders for a customer.
/orders/{orderId}/items to retrieve items for each individual order.
If you want to display a customer's orders along with the items in each order, the following sequence of REST requests could occur:
1 query to get all orders (/orders), returning N orders.
N queries to get items for each order (/orders/{orderId}/items), one for each order.

Scenario in REST: Consider a blogging platform where you want to display a list of blog posts along with their comments.
In a RESTful API, there might be two endpoints:
/posts to retrieve a list of blog posts.
/posts/{postId}/comments to fetch the comments for each post.
When displaying blog posts with their comments, the typical REST approach might result in:
1 query to get all posts (/posts), returning N posts.
N queries to get comments for each post (/posts/{postId}/comments), one for each post.


9. Finish the following API
REST
DELETE post by ID (with exception cases
GraphQL
Query getAllPost
10. Create a Project, name it with mongo-blog, write a POST API for mongo-blog, change database to
MongoDB;
11. https://www.mongodb.com/compatibility/spring-boot