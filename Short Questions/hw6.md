create a file to list all of the annotaitons you learned and known, and explain the usage and how do you
understand it. you need to update it when you learn a new annotation. Please organize those annotations
well, like annotations used by entity, annotations used by controller.
    1. File name: annotations.md
    2. you'd better also list a code example under the annotations.
2. explain how the below annotaitons specify the table in database?
@Column(columnDefinition = "varchar(255) default 'John Snow'")
private String name;
defines the column type as varchar(255), and default value is John Snow.
@Column(name="STUDENT_NAME", length=50, nullable=false, unique=false)
private String studentName;
defines the column name is STUDENT_NAME, max length = 50, can be null, do not have to be unique


3. What is the default column names of the table in database for @Column?
@Column
private String firstName;
firstName
@Column
private String operatingSystem;
operatingSystem
4. What are the layers in springboot application? what is the role of each layer?
Controller: how the application respond to a api. It gets http requests and return responses
Service: defines the business logics
Entity: map to specific database
Repository: defines how to interact with database
DTO: transfer data between layers
5. Describe the flow in all of the layers if an API is called by Postman.
postman <-> Controller <-> Service <-> Repository <-> Database
6. What is the application.properties? do you know application.yml?
Configuration file for spring boot app, database connections, logging, security.
application.yml same as properties but more readable structure.
7. What’s the naming differences between GraphQL vs. REST ? Why is the differences ?
Graphql has a single endpoint while rest has multiple endpoints. Graphql uses query types for methods, query,
mutation, subscription. Rest uses http methods, get, post, delete. Graphql is more flexible than rest, it can
fetch related data in a single query. They are for different purpose, rest is to get predefined structure of
data and graphql is for more flexible requests.
8. Provide 2 real-world examples of N+1 problem in REST that can be solved by GraphQL.
REST:
GET /users
GET /users/1/post
GraphQL:
query {
    users {
        id {
            posts {

            }
        }
    }
}

REST:
GET /users/account/history
GraphQL:
query {
    users {
        id {
            history {

            }
        }
    }
}
9. Finish the following API
    REST
    DELETE post by ID (with exception cases)
    GraphQL
    Query getAllPost
10. Create a Project, name it with mongo-blog, write a POST API for mongo-blog, change database to MongoDB;
11. https://www.mongodb.com/compatibility/spring-boot