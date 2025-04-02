1. Database

2. REST API (Postman practice)

   Practice with below examples (Each example with 404, 401,500 and any http status codes you know): 

   1. 5 **GET** API with different response type 

      **API-GET-1**

      API: GET <https://reqres.in/api/users?page=2>

      RequestBody: N/A, since it is GET

      Response status: `200 OK`

      Response:

      ```json
      {
          "page": 2,
          "per_page": 6,
          "total": 12,
          "total_pages": 2,
          "data": [
              {
                  "id": 7,
                  "email": "michael.lawson@reqres.in",
                  "first_name": "Michael",
                  "last_name": "Lawson",
                  "avatar": "https://reqres.in/img/faces/7-image.jpg"
              },
              {
                  "id": 8,
                  "email": "lindsay.ferguson@reqres.in",
                  "first_name": "Lindsay",
                  "last_name": "Ferguson",
                  "avatar": "https://reqres.in/img/faces/8-image.jpg"
              },
              {
                  "id": 9,
                  "email": "tobias.funke@reqres.in",
                  "first_name": "Tobias",
                  "last_name": "Funke",
                  "avatar": "https://reqres.in/img/faces/9-image.jpg"
              },
              {
                  "id": 10,
                  "email": "byron.fields@reqres.in",
                  "first_name": "Byron",
                  "last_name": "Fields",
                  "avatar": "https://reqres.in/img/faces/10-image.jpg"
              },
              {
                  "id": 11,
                  "email": "george.edwards@reqres.in",
                  "first_name": "George",
                  "last_name": "Edwards",
                  "avatar": "https://reqres.in/img/faces/11-image.jpg"
              },
              {
                  "id": 12,
                  "email": "rachel.howell@reqres.in",
                  "first_name": "Rachel",
                  "last_name": "Howell",
                  "avatar": "https://reqres.in/img/faces/12-image.jpg"
              }
          ],
          "support": {
              "url": "https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral",
              "text": "Tired of writing endless social media content? Let Content Caddy generate it for you."
          }
      }
      ```

      **API-GET-2**

      API: GET <https://sheets.googleapis.com/v4/spreadsheets/PRIVATE_SHEET_ID/values/Sheet1!A1:D5>

      RequestBody: N/A, since it is GET

      Response status: `403 Forbidden`

      Response:

      ```json
      {
          "error": {
              "code": 403,
              "message": "Method doesn't allow unregistered callers (callers without established identity). Please use API Key or other form of API consumer identity to call this API.",
              "status": "PERMISSION_DENIED"
          }
      }
      ```
      
      **API-GET-3**
      
      API: GET <https://reqres.in/api/users/23>
      
      RequestBody: N/A, since it is GET

      Response status: `404 Not Found`

      Response:

      ```json
      {}
      ```

      **API-GET-4**

      API: GET <https://api.github.com/user/repos>
      
      RequestBody: N/A, since it is GET

      Response status: `401 Unauthorized`

      Response:

      ```json
      {
          "message": "Requires authentication",
          "documentation_url": "https://docs.github.com/rest/repos/repos#list-repositories-for-the-authenticated-user",
          "status": "401"
      }
      ```
      
      **API-GET-5**
      
      API: GET <https://httpstat.us/500>
      
      RequestBody: N/A, since it is GET

      Response status: `500 Internal Server Error`

      Response:

      ```json
      500 Internal Server Error
      ```

   2. 5 **Post** API with json request body, please also paste the response here 

      **API-POST-1**
   
      API: POST <https://reqres.in/api/users>

      RequestBody:

      ```json
      {
          "name": "morpheus",
          "job": "leader"
      }
      ```
   
      Response status: `201 Created`
   
      Created Response:
   
      ```json
      {
          "name": "morpheus",
          "job": "leader",
          "id": "662",
          "createdAt": "2025-04-02T17:59:04.137Z"
      }
      ```
   
      **API-POST-2**
   
      API: POST <https://reqres.in/api/register>
   
      RequestBody:

      ```json
      {
          "email": "eve.holt@reqres.in",
          "password": "pistol"
      }
      ```
   
      Response status: `200 OK`
   
      Created Response:
   
      ```json
      {
          "id": 4,
          "token": "QpwL5tke4Pnpja7X4"
      }
      ```
   
      **API-POST-3**
   
      API: POST <https://reqres.in/api/register>
   
      RequestBody:

      ```json
      {
          "email": "sydney@fife"
      }
      ```

      Response status: `400 Bad Request`
   
      Created Response:
   
      ```json
      {
          "error": "Missing password"
      }
      ```

      **API-POST-4**
   
      API: POST <https://api.github.com/user/repos>
   
      RequestBody:

      ```json
      {
          "name": "new-repository",
          "description": "This is a new repository",
          "private": false
      }
      ```
   
      Response status: `401 Unauthorized`
   
      Created Response:
   
      ```json
      {
          "message": "Requires authentication",
          "documentation_url": "https://docs.github.com/rest/repos/repos#create-a-repository-for-the-authenticated-user",
          "status": "401"
      }
      ```
   
      **API-POST-5**
   
      API: POST <https://api.github.com/repos/owner/repo/issues>
   
      RequestBody:

      ```json
      {
        "title": "Bug report",
        "body": "Steps to reproduce the issue..."
      }
      ```
   
      Response status: `404 Not Found`
   
      Created Response:
   
      ```json
      {
          "message": "Not Found",
          "documentation_url": "https://docs.github.com/rest/issues/issues#create-an-issue",
          "status": "404"
      }
      ```
   
   3. 3 **PUT** API with json request body, please also paste the response here

      **API-PUT-1**

      API: PUT <https://reqres.in/api/users/2>
   
      RequestBody:
   
      ```json
      {
          "name": "morpheus",
          "job": "zion resident"
      }
      ```
   
      Response status: `200 OK`
   
      Response:
   
      ```json
      {
          "name": "morpheus",
          "job": "zion resident",
          "updatedAt": "2025-04-02T18:13:15.376Z"
      }
      ```
   
      **API-PUT-2**

      API: PUT <https://api.github.com/repos/username/repo-name>
   
      RequestBody:
   
      ```json
      {
          "name": "new-repo-name",
          "description": "Updated repository description",
          "private": true
      }
      ```
   
      Response status: `404 Not Found`
   
      Response:
   
      ```json
      {
          "message": "Not Found",
          "documentation_url": "https://docs.github.com/rest",
          "status": "404"
      }
      ```
   
      **API-PUT-3**

      API: PUT <https://api.spotify.com/v1/me>
   
      RequestBody:
   
      ```json
      {
          "display_name": "New Display Name"
      }
      ```
   
      Response status: `401 Unauthorized`
   
      Response:
   
      ```json
      {
          "error": {
              "status": 401,
              "message": "No token provided"
          }
      }
      ```
   
   4. 2 **DELETE** API 

      **API-DELETE-1**

      API: DELETE <https://reqres.in/api/users/2>

      RequestBody: N/A

      Response status: `204 No Content`

      Response: 

      ```json
      // No content, indicates successful deletion
      ```

      **API-DELETE-2**

      API: DELETE <https://api.github.com/repos/username/repo-name>
   
      RequestBody: N/A
   
      Response status: `404 Not Found`
      
      Response:
      
      ```json
      {
          "message": "Not Found",
          "documentation_url": "https://docs.github.com/rest/repos/repos#delete-a-repository",
          "status": "404"
      }
      ```
   
3. API Design

   1. Find the customer's payments, like credit card 1, credit card 2, paypal, Apple Pay
   
      ```http
      GET /api/v1/customers/{customerId}/payments
      ```

      ```json
      [
        { "type": "credit_card", "last4": "1234", "provider": "Visa" },
        { "type": "paypal", "email": "user@example.com" },
        { "type": "apple_pay", "device": "iPhone 14" }
      ]
      ```
   
   2. Find the customer's history orders from 10/10/2022 to 10/24/2022
   
      ```http
      GET /api/v1/customers/{customerId}/orders?start_date=2022-10-10&end_date=2022-10-24
      ```
   
   3. Find the customer's delivery  addresses
   
      ```http
      GET /api/v1/customers/{customerId}/delivery-addresses
      ```
   
   4. If I also want to get customer's default payment and default delivery address, what kind of the API (URL)  should be?
   
      ```http
      GET /api/v1/customers/{customerId}/defaults
      ```
   
      ```json
      {
        "defaultPayment": {
          "type": "credit_card",
          "last4": "5678",
          "provider": "MasterCard"
        },
        "defaultAddress": {
          "street": "123 Main St",
          "city": "New York",
          "zip": "10001"
        }
      }
      ```
   
   5. Find 2 collection of APIs example. ie. Twitter, Paypal, Youtube etc.
   
      **PayPal API Collection**
   
      **Orders APIs**
   
      | HTTP Method | Endpoint                                             | Description                         |
      | ----------- | ---------------------------------------------------- | ----------------------------------- |
      | POST        | /v2/checkout/orders                                  | Create a new order                  |
      | POST        | /v2/checkout/orders/:order_id/confirm-payment-source | Confirm payment source for an order |
      | GET         | /v2/checkout/orders/:order_id                        | Get order details                   |
      | PATCH       | /v2/payments/refunds/:refund_id                      | Update an existing order            |
   
      **Payments APIs**
   
      | HTTP Method | Endpoint                                                  | Description                        |
      | ----------- | --------------------------------------------------------- | ---------------------------------- |
      | GET         | /v2/payments/authorizations/:authorization_id             | Get details for authorized payment |
      | POST        | /v2/payments/authorizations/:authorization_id/reauthorize | Reauthorize an authorized payment  |
      | GET         | /v2/payments/captures/:capture_id                         | Get details of captured payment    |
      | GET         | /v2/payments/refunds/:refund_id                           | Get details of a refund            |
   
      **X (Twitter) API Collection**
   
      **Users APIs**
   
      | HTTP Method | Endpoint                                             | Description             |
      | ----------- | ---------------------------------------------------- | ----------------------- |
      | GET         | /2/users/{id}                                        | Get user profile by ID  |
      | GET         | /2/users/{id}/followers                              | Get followers of a user |
      | POST        | /2/users/{id}/following                              | Follow a user           |
      | DELETE      | /2/users/{source_user_id}/following/{target_user_id} | Unfollow a user         |
   
      **Posts APIs (Tweets)**
   
      | HTTP Method | Endpoint                           | Description                 |
      | ----------- | ---------------------------------- | --------------------------- |
      | GET         | /2/tweets/{id}                     | Get tweet details by ID     |
      | POST        | /2/users/{id}/bookmarks            | Add tweet to bookmarks      |
      | DELETE      | /2/users/{id}/bookmarks/{tweet_id} | Remove tweet from bookmarks |
      | PUT         | /2/tweets/{tweet_id}/hidden        | Hide replies for a tweet    |
   
   6. Design a collection of APIs for a Blog Website, please specify GET POST PUT DELETE
   
      **Posts API**
   
      | HTTP Method | Endpoint               | Description                  |
      | ----------- | ---------------------- | ---------------------------- |
      | GET         | /api/v1/posts          | Retrieve all blog posts      |
      | GET         | /api/v1/posts/{postId} | Get details of a single post |
      | POST        | /api/v1/posts          | Create a new blog post       |
      | PUT         | /api/v1/posts/{postId} | Update a specific blog post  |
      | DELETE      | /api/v1/posts/{postId} | Delete a blog post           |
   
      **Comments API** *(Sub-resource of Posts)*
   
      | HTTP Method | Endpoint                                    | Description                          |
      | ----------- | ------------------------------------------- | ------------------------------------ |
      | GET         | /api/v1/posts/{postId}/comments             | Get all comments for a specific post |
      | POST        | /api/v1/posts/{postId}/comments             | Add a comment to a specific post     |
      | PUT         | /api/v1/posts/{postId}/comments/{commentId} | Update a specific comment            |
      | DELETE      | /api/v1/posts/{postId}/comments/{commentId} | Delete a specific comment            |
   
      **Users API**
   
      | HTTP Method | Endpoint                     | Description                      |
      | ----------- | ---------------------------- | -------------------------------- |
      | GET         | /api/v1/users/{userId}       | Get a user’s profile             |
      | GET         | /api/v1/users/{userId}/posts | Get all posts by a specific user |
   
      

