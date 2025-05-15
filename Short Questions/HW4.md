# CRUD MySQL and MongoDB
## MySQL
### Create
```sql
INSERT INTO users (name, age) VALUES ('Alice', 25);
```
### Read
```sql
SELECT * FROM users;
SELECT * FROM users WHERE name = 'Alice';
```

### Update
```sql
UPDATE users SET age = 26 WHERE name = 'Alice';
```
### Delete
```sql
DELETE FROM users WHERE name = 'Alice';
```

## MongoDB
```json
{ "_id": ObjectId("..."), "name": "Alice", "age": 25 }
```
### Create
```js
db.users.insertOne({ name: "Alice", age: 25 });
```

### Read
```js
db.users.find(); // All users
db.users.find({ name: "Alice" }); // Filtered
```

### Update
```js
db.users.updateOne(
  { name: "Alice" },
  { $set: { age: 26 } }
);
```

### Delete
```js
db.users.deleteOne({ name: "Alice" });
```

# 5 GET APIs with different response type
## JSON Response
```bash
GET https://jsonplaceholder.typicode.com/posts/1
```
**Response Type**: application/json
### Example Response
```json
{
  "userId": 1,
  "id": 1,
  "title": "sunt aut facere repellat provident occaecati...",
  "body": "quia et suscipit\nsuscipit recusandae consequuntur..."
}
```
## XML Response
```bash
GET https://api.openweathermap.org/data/2.5/weather?q=London&mode=xml&appid=YOUR_API_KEY
```
**Response Type**: application/xml
### Example Response
```xml
<current>
  <city name="London">
    <coord lon="-0.13" lat="51.51"/>
    <temperature value="280.32" unit="kelvin"/>
  </city>
</current>
```

## Plain Text Response
```bash
GET https://icanhazdadjoke.com/
Accept: text/plain
```
**Response Type**: text/plain
### Example Response
```plaintext
Why do chicken coops only have two doors? Because if they had four, they’d be chicken sedans!
```

## Image Response
```bash
GET https://picsum.photos/200/300
```
**Response Type**: image/jpeg
### Example Response
```plaintext
Returns a random 200x300 image.
```

## HTML Response
```bash
GET https://example.com
```
**Response Type**: text/html
### Example Response
```html
<!doctype html>
<html>
  <head>
    <title>Example Domain</title>
  </head>
  <body>
    <div>This domain is for use in illustrative examples in documents.</div>
  </body>
</html>
```

# 5 Post API with json request body, please also paste the response here

## JSONPlaceholder – Create a Post
### Endpoint
```bash
POST https://jsonplaceholder.typicode.com/posts
```
### Request Body:
```json
{
  "title": "foo",
  "body": "bar",
  "userId": 1
}
```
### Response:
```json
{
  "title": "foo",
  "body": "bar",
  "userId": 1,
  "id": 101
}
```

## ReqRes – Create a User
### Endpoint
```bash
POST https://reqres.in/api/users
```
### Request Body:
```json
{
  "name": "morpheus",
  "job": "leader"
}
```
### Response:
```json
{
  "name": "morpheus",
  "job": "leader",
  "id": "725",
  "createdAt": "2025-04-02T09:00:00.000Z"
}
```

## Petstore Swagger (Fake Pet API)
### Endpoint
```bash
POST https://petstore.swagger.io/v2/pet
```
### Request Body:
```json
{
  "id": 123456,
  "name": "Doggo",
  "photoUrls": ["https://example.com/dog.jpg"],
  "status": "available"
}
```
### Response:
```json
{
  "id": 123456,
  "name": "Doggo",
  "photoUrls": ["https://example.com/dog.jpg"],
  "tags": [],
  "status": "available"
}
```

## Webhook.site Echo API
### Endpoint
```bash
POST https://webhook.site/#!/your-custom-url
```
### Request Body:
```json
{
  "message": "Hello from client",
  "timestamp": "2025-04-02T10:00:00Z"
}
```
### Response:
```plaintext
The request gets stored and displayed on the dashboard – great for testing POSTs.
```

## httpbin – Post Test
### Endpoint
```bash
POST https://httpbin.org/post
```
### Request Body:
```json
{
  "username": "admin",
  "password": "123456"
}
```
### Response:
```json
{
  "args": {},
  "data": "{\"username\": \"admin\", \"password\": \"123456\"}",
  "files": {},
  "form": {},
  "headers": {
    "...": "..."
  },
  "json": {
    "username": "admin",
    "password": "123456"
  },
  "url": "https://httpbin.org/post"
}
```

# 3 PUT API with json request body, please also paste the response here
## JSONPlaceholder – Update a Post
### Endpoint
```bash
PUT https://jsonplaceholder.typicode.com/posts/1
```
### Request Body:
```json
{
  "id": 1,
  "title": "updated title",
  "body": "updated body",
  "userId": 1
}
```
### Response:
```json
{
  "id": 1,
  "title": "updated title",
  "body": "updated body",
  "userId": 1
}
```

## ReqRes – Update a User
### Endpoint
```bash
PUT https://reqres.in/api/users/2
```
### Request Body:
```json
{
  "name": "morpheus",
  "job": "zion resident"
}
```
### Response:
```json
{
  "name": "morpheus",
  "job": "zion resident",
  "updatedAt": "2025-04-02T10:30:00.000Z"
}
```

## Petstore Swagger – Update a Pet
### Endpoint
```bash
PUT https://petstore.swagger.io/v2/pet
```
### Request Body:
```json
{
  "id": 123456,
  "name": "Doggo Updated",
  "photoUrls": ["https://example.com/dog-updated.jpg"],
  "status": "sold"
}
```
### Response:
```json
{
  "id": 123456,
  "name": "Doggo Updated",
  "photoUrls": ["https://example.com/dog-updated.jpg"],
  "tags": [],
  "status": "sold"
}
```

# 2 DELETE API
## JSONPlaceholder – Delete a Post
### Endpoint
```bash
DELETE https://jsonplaceholder.typicode.com/posts/1
```
### Request Body: Not required
### Response:
```json
{}
```
## ReqRes – Delete a User
### Endpoint
```bash
DELETE https://reqres.in/api/users/2
```
### Request Body: Not required
### Response:
```http
Status: 204 No Content
```

# Each example with 404, 401,500 and any http status codes you know
## GET – JSONPlaceholder
**Endpoint**: https://jsonplaceholder.typicode.com/posts/{id}
| Status Code | How to Trigger         | Description                                                               |
|-------------|------------------------|---------------------------------------------------------------------------|
| 200 OK      | GET /posts/1           | Found post.                                                               |
| 404 Not Found | GET /invalidendpoint  | Endpoint doesn’t exist.                                                   |
| 400 Bad Request | GET /posts/abc      | Invalid id param (usually still returns 200 though, since it’s mocked).   |
| 500         | Not available          | It’s a fake API, doesn’t simulate server errors.                          |

## POST - ReqRes
**Endpoint**: https://reqres.in/api/users
| Status Code           | How to Trigger                                | Description               |
|------------------------|-----------------------------------------------|---------------------------|
| 201 Created            | Valid JSON: `{ "name": "morpheus", "job": "leader" }` | User created.             |
| 400 Bad Request        | Empty body or malformed JSON                  | Missing required fields.  |
| 500 Internal Server Error | Not simulated                              | No server error mock.     |

## PUT - ReqRes
**Endpoint**: https://reqres.in/api/users/2
| Status Code     | How to Trigger                                           | Description                         |
|------------------|----------------------------------------------------------|-------------------------------------|
| 200 OK           | Valid JSON: `{ "name": "neo", "job": "one" }`           | User updated.                       |
| 404 Not Found    | PUT /api/users/9999                                      | User doesn’t exist (not always enforced). |
| 400 Bad Request  | No body                                                  | Invalid/missing data.               |
| 401 Unauthorized | Add bad/expired token (if auth required)                | Simulated only if token is expected. |

## DELETE - ReqRes
**Endpoint**: https://reqres.in/api/users/2
| Status Code       | How to Trigger                           | Description                          |
|--------------------|------------------------------------------|--------------------------------------|
| 204 No Content     | Valid user id                            | User deleted.                        |
| 404 Not Found      | Invalid endpoint: /api/unknown           | URL doesn’t exist.                   |
| 401 Unauthorized   | (If you add auth headers incorrectly)    | Could simulate unauthorized access.  |

# API Design
## find the customer's payments, like credit card 1, credit card 2, paypal, Apple Pay.
```http
GET /customers/{customerId}/payment-methods
```
## Find the customer's history orders from 10/10/2022 to 10/24/2022
```http
GET /customers/{customerId}/orders?from=2022-10-10&to=2022-10-24
```

## find the customer's delievery addresses
```http
GET /customers/{customerId}/delivery-addresses
```

## If I also want to get customer's default payment and default delievery address, what kind of the API (URL) should be?
```http
GET /customers/{customerId}/payment-methods/default
GET /customers/{customerId}/delivery-addresses/default
```

## Find 2 collection of APIs example. ie. Twitter, Paypal, Youtube etc.
### Twitter API
```http
GET /tweets  
POST /tweets  
GET /users/:id/tweets
```
### GitHub API
```http
GET /repos/:owner/:repo/issues  
POST /repos/:owner/:repo/issues  
GET /users/:username/repos  
```

## Design a collection of APIs for a Blog Website, please specify GET POST PUT DELETE
### Blog Posts
| Method | URL              | Description              |
|--------|------------------|--------------------------|
| GET    | /posts           | List all blog posts      |
| GET    | /posts/{postId}  | Get one blog post        |
| POST   | /posts           | Create a new blog post   |
| PUT    | /posts/{postId}  | Update a post            |
| DELETE | /posts/{postId}  | Delete a post            |

### Comments (as sub-resource)
| Method | URL                                        | Description           |
|--------|--------------------------------------------|-----------------------|
| GET    | /posts/{postId}/comments                   | Get comments for a post |
| POST   | /posts/{postId}/comments                   | Add a comment         |
| DELETE | /posts/{postId}/comments/{commentId}       | Delete a comment      |

### Users
```http
GET /users
GET /users/{userId}
POST /users
PUT /users/{userId}
DELETE /users/{userId}
```