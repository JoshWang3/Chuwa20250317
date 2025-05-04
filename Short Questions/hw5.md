1. 5 GET APIs with different response 
GET: https://api.github.com/
Response status: 200
    
GET:https://api.openweathermap.org/data/2.5/weather?q=&appid=demo
Response status: 400

GET:https://api.spotify.com/v1/me
Response status: 401

GET:https://httpstat.us/500
Response status: 500

GET:https://api.github.com/users/notexist999
Response status: 404

2. 5 POST APIs
POST:https://dummyjson.com/auth/login
Response status: 200
   {
   "username": "kminchelle",
   "password": "0lelplR"
   }

POST:https://dummyjson.com/products/add
Response status: 201
{
"title": "Test Product"
}

POST: https://reqres.in/api/register
Response status: 400
{
"email": "eve.holt@reqres.in"
}

POST:https://api.spotify.com/v1/me
Response status: 401

POST:https://httpstat.us/500
Response status: 500
{
"test": "data"
}
3. 3 PUT APIs
   PUT:https://dummyjson.com/users/1
   Response status: 200
   {
   "name": "Alice",
   "email": "alice@newmail.com",
   "phone": "123456789"
   }

PUT:https://reqres.in/api/users/2
Response status: 400
{
"name": "John Doe"
}

PUT:https://reqres.in/api/users/9999
Response status: 404
{
"name": "John Doe"
}
4. 2 DELETE APIs
   DELETE:https://reqres.in/api/users/2
   Response status: 200

DELETE:https://reqres.in/api/users/9999
Response status: 404


1.Find the customer's payments
GET /customers/{customerId}/payment

2.Find the customer's history orders from 10/10/2022 to 10/24/2022
GET /customers/{customerId}/orders?start=2022-10-10&end=2022-10-24

3.Find the customer's delivery addresses
GET /customers/{customerId}/addresses

4.Get Customer's default payment and default delivery address
GET /customers/{customerId}/default-payment
GET /customers/{customerId}/default-address

5.Find 2 collection of APIs example
Paypal API
GET /v2/payments/payment
POST /v2/checkout/orders
GET /v2/customer/partner-referrals
Twitter API (v2)
POST /tweets
GET /tweets/:id
/2/users/:id/tweets

6.Blog Website API Design
/posts

GET /posts (get all posts)
POST /posts (create a new post)
/posts/{postId}

GET /posts/{postId} (get one post)
PUT /posts/{postId} (update ceratin post)
DELETE /posts/{postId} (delete certain post)
/posts/{postId}/comments

GET /posts/{postId}/comments (get comments for certain post)
POST /posts/{postId}/comments (add comment to post)
/users

GET /users (get list of users)
POST /users (regiser a new user)
/users/{userId}

GET /users/{userId} (get user profile)
PUT /user/{userId} (update user profile)
