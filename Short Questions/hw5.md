HW 5
Database
CRUD MySQL and MongoDB (separated PDF)
REST API
Postman practice
Practice with below examples (You can find any Open APIs on the internet):
5 GET APIs with different response type
5 Post API with json request body, please also paste the response here
3 PUT API with json request body, please also paste the response here
2 DELETE API
Each example with 404, 401,500 and any http status codes you know
GET:
1. API: GET https://jsonplaceholder.typicode.com/posts/1
RequestBody: N/A, since it is GET
Response status: 200 OK
Response: JSON
{
    "userId": 1,
    "id": 2,
    "title": "qui est esse",
    "body": "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"
}
2. API:GET https://api.github.com/user
Description: Retrieves a github users.
Response Status: 401 Unauthorized
{
    "message": "Requires authentication",
    "documentation_url": "https://docs.github.com/rest/users/users#get-the-authenticated-user",
    "status": "401"
}
3. API: GET https://httpstat.us/403
Response Status: 403
403 Forbidden
4. API: GET https://github.com/jianan_yao
Response Status: 404
Not Found
5. API: GET https://httpstat.us/500
Response Status: 500
500 Internal Server Error
POST:
1. API: POST https://jsonplaceholder.typicode.com/posts
body: {
    "name": "a",
    "job": "sde2"
}
Response Status: 201
{
    "name": "a",
    "job": "sde2",
    "id": 101
}
2. API: POST https://jsonplaceholder.typicode.com/post
Response Status: 404
3. API: POST https://jsonplaceholder.typicode.com/comments
body:{
    "postId":1,
    "name": "a",
    "body": "hw5 test"
}
Response Status: 201
{
    "postId": 1,
    "name": "a",
    "body": "hw5 test",
    "id": 501
}
4. API: POST https://api.github.com/user/repos
{
    "postId":1,
    "name": "a",
    "body": "hw5 test"
}
Response Status: 401
{
    "message": "Requires authentication",
    "documentation_url": "https://docs.github.com/rest/repos/repos#create-a-repository-for-the-authenticated-user",
    "status": "401"
}
5. API: POST https://reqres.in/api/register
{
  "email": "123321@ret"
}
Response Status: 400
{
    "error": "Missing password"
}
PUT:
API: PUT https://jsonplaceholder.typicode.com/posts/1
{
"id":1,
"body": "updated"
}
Response Status: 200
{
    "id": 1,
    "body": "updated"
}
API: PUT https://jsonplaceholder.typicode.com/posts/1
{
"id":1,
"body": "updated"
"userId": 1
}
Response Status: 500
SyntaxError: Unexpected string in JSON at position 28
at JSON.parse (<anonymous>)
    at parse (/app/node_modules/body-parser/lib/types/json.js:89:19)
    at /app/node_modules/body-parser/lib/read.js:121:18
    at invokeCallback (/app/node_modules/body-parser/node_modules/raw-body/index.js:224:16)
    at done (/app/node_modules/body-parser/node_modules/raw-body/index.js:213:7)
    at IncomingMessage.onEnd (/app/node_modules/body-parser/node_modules/raw-body/index.js:273:7)
    at IncomingMessage.emit (node:events:525:35)
    at endReadableNT (node:internal/streams/readable:1358:12)
    at processTicksAndRejections (node:internal/process/task_queues:83:21)
API: https://httpbin.org/status/401
{
"id":1,
"body": "updated"
}
Response Status: 401
DELETE:
API: https://jsonplaceholder.typicode.com/posts/1
Response Status: 200
API: https://jsonplaceholder.typicode.com/post
Response Status: 404

1. find the customer's payments, like credit card 1, credit card 2, paypal, Apple Pay.
GET /api/v1/customers/{customerId}/payments
2. Find the customer's history orders from 10/10/2022 to 10/24/2022
GET /api/v1/customers/{customerId}/order?start-date=2022-10-10&end-date=2022-10-24
3. find the customer's delievery addresses
GET /api/v1/customers/{customerId}/addresses
4. If I also want to get customer's default payment and default delievery address, what kind of the API (URL) should be?
GET /api/v1/customers/{customerId}/payments/default
GET /api/v1/customers/{customerId}/addresses/default
5. Find 2 collection of APIs example. ie. Twitter, Paypal, Youtube etc. -- 命名规范
Twitter:
POST /tweets
GET /tweets/:id
Paypal:
GET /v2/payments/payment
POST /v2/checkout/orders
6. Design a collection of APIs for a Blog Website, please specify GET POST PUT DELETE
GET /users: Retrieve a list of users.
GET /users/{user_id}: Get details of a specific user.
POST /users: Create a new user.
PUT /users/{user_id}: Update the user's information.
DELETE /users/{user_id}: Delete a specific user.
GET /posts: Retrieve all blog posts.
GET /posts/{post_id}: Get a specific blog post.
POST /posts: Create a new blog post.
PUT /posts/{post_id}: Update an existing blog post.
DELETE /posts/{post_id}: Delete a specific blog post.
GET /posts/{post_id}/comments: Retrieve all comments for a blog post.
POST /posts/{post_id}/comments: Create a new comment to a blog post.
DELETE /posts/{post_id}/comments/{comment_id}: Delete a specific comment of a specific blog post.
Design APIs for the following features (思考：path variable 怎么⽤？有sub resources, 哪些地⽅该⽤复数)

