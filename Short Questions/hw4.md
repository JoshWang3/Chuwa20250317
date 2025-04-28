# REST API

## 1. Postman

### 1. GET APIs

**Endpoint:** GET https://jsonplaceholder.typicode.com/posts/1 

**Response Status:** 200

**Response:**  
```json
{
    "userId": 1,
    "id": 1,
    "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
    "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
}
```

**Endpoint:** GET GET https://api.github.com/user

**Response Status:** 401  

**Response:**  
```json
{
    "message": "Requires authentication",
    "documentation_url": "https://docs.github.com/rest/users/users#get-the-authenticated-user",
    "status": "401"
}
```

**Endpoint:** GET https://httpstat.us/403

**Response Status:** 403  

**Response:**  
```json
403 Forbidden
```

**Endpoint:** GET https://reqres.in/api/users/999

**Response Status:** 404  

**Response:**  
```json
{}
```

**Endpoint:** GET https://httpstat.us/500

**Response Status:** 500  

**Response:**  
```json
500 Internal Server Error
```

### 2. POST APIs

**Endpoint:** POST https://reqres.in/api/users

**Response Status:** 201

**Body:**
```json
{
    "name": "John",
    "job": "Developer"
}
```
**Response:**  
```json
{
    "name": "John",
    "job": "Developer",
    "id": "399",
    "createdAt": "2025-04-02T22:57:07.777Z"
}
```
**Endpoint:** POST https://reqres.in/api/users

**Response Status:** 400

**Body:**
```json
{
    "name": 
}
```
**Response:**  
```json
Bad request
```

**Endpoint:** POST https://reqres.in/api/register

**Response Status:** 201

**Body:**
```json
{
  "email": "eve.holt@reqres.in",
  "password": "pistol"
}

```
**Response:**  
```json
{
    "id": 4,
    "token": "QpwL5tke4Pnpja7X4"
}
```

**Endpoint:** POST https://reqres.in/api/register

**Response Status:** 400

**Body:**
```json
{
  "email": "sydney@fife"
}

```
**Response:**  
```json
{
    "error": "Missing password"
}
```

**Endpoint:** POST https://reqres.in/api/users

**Response Status:** 201

**Body:**
```json
{
  "name": "morpheus",
  "job": "leader"
}
```
**Response:**  
```json
{
    "name": "morpheus",
    "job": "leader",
    "id": "389",
    "createdAt": "2025-04-02T23:22:46.300Z"
}
```

### 3. PUT APIs
**Endpoint:** PUT https://reqres.in/api/users/2

**Response Status:** 200

**Body:**
```json
{
  "name": "morpheus",
  "job": "zion resident"
}
```
**Response:**  
```json
{
    "name": "morpheus",
    "job": "zion resident",
    "updatedAt": "2025-04-02T23:26:45.774Z"
}
```

**Endpoint:** PUT https://jsonplaceholder.typicode.com/posts/1

**Response Status:** 200

**Body:**
```json
{
  "id": 1,
  "title": "Updated Post Title",
  "body": "Updated content for the post.",
  "userId": 1
}
```
**Response:**  
```json
{
  "id": 1,
  "title": "Updated Post Title",
  "body": "Updated content for the post.",
  "userId": 1
}
```

**Endpoint:** PUT https://reqres.in/api/users/23

**Response Status:** 400

**Body:**
```json
{
  "name":
}
```
**Response:**  
```json
400 Bad Request
```

### 3. DELETE APIs

**Endpoint:** DELETE https://reqres.in/api/users/2
**Response Status:** 204


**Endpoint:** DELETE https://reqres.in/api/users/999
**Response Status:** 404


## 2. API Design

### 1. Find the Customer's Payments
#### Endpoint: GET /api/customers/{customerId}/payments
- Retrieves a list of all payment methods associated with the customer.
- Possible payment types: Credit Card 1, Credit Card 2, PayPal, Apple Pay.



### 2. Find the Customer's Order History

#### Endpoint: GET /api/customers/{customerId}/orders?startDate=2022-10-10&endDate=2022-10-24

- Retrieves the order history for the specified date range.
- The API accepts **customerId** as a path variable.
- Uses **startDate** and **endDate** as query parameters to filter orders.

### 3. Find the Customer's Delivery Addresses

#### Endpoint: GET /api/customers/{customerId}/addresses

- Retrieves all saved delivery addresses associated with a customer.
- The API accepts **customerId** as a path variable.

### 4. Find the Customer's Defalut information

#### Endpoint: GET /customers/{customerId}/payment-methods/default
#### Endooint: GET /customers/{customerId}/delivery-addresses/default

- Retrieves the default payment method and the default delivery address associated with a customer.
- The API accepts **customerId** as a path variable.


### 5. Find 2 Collection of APIs Example (Twitter, PayPal)

#### 1. Twitter API Collection

#### **Base URL:** https://api.twitter.com/2


**API Naming Convention:**
- Uses versioning (`/2`).
- Follows RESTful principles.
- Uses nouns to represent resources.
### **Endpoints:**

#### **Tweets:**
- **GET /tweets/{id}** - Retrieve a tweet by its ID.  
- **POST /tweets** - Create a new tweet.  
- **DELETE /tweets/{id}** - Delete a specific tweet.  
- **PUT /tweets/{id}** - Update a specific tweet.   

### 2. PayPal API Collection
#### **Base URL:** https://api.paypal.com/v2


**API Naming Convention:**
- Uses versioning (/v2).
- Uses descriptive paths.
- Follows RESTful conventions.

### **Endpoints:**
1. Orders:
- **POST /checkout/orders** - Create an order.

- **GET /checkout/orders/{order_id}** - Retrieve an order.

- **PATCH /checkout/orders/{order_id}** - Update an order.

- **DELETE /checkout/orders/{order_id}** - Cancel an order.


### 6. API Collection for a Blog Website

#### **. API Design Principles:**
- Follow **RESTful conventions**.
- Use **HTTP methods** appropriately (GET, POST, PUT, DELETE).
- Use **path variables** for resource identification.
- Use **plural nouns** for collections.
- Use **sub-resources** for nested data.
---


### **GET: Retrieve All Posts**

#### **Endpoints:**GET /api/posts

### **POST: Create a New Post**

#### **Endpoints:** POST /api/posts

#### **Body:**
```json
{
  "title": "Understanding JavaScript",
  "content": "JavaScript is a versatile language...",
  "author": "Alex Johnson"
}
```

### **PUT: Update an Existing Post**

#### **Endpoints:** PUT /api/posts/{postId}

#### **Body:**

```json

{
  "title": "Updated Post Title",
  "content": "Updated content for the post."
}
```

### **DELETE: Delete a Post**

#### **Endpoints:**DELETE /api/posts/{postId}








