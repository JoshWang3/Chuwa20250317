# 1. Practice with below examples (You can find any Open APIs on the internet):

## 5 GET APIs with different response type
 
API-GET-1\
API： GET https://cat-fact.herokuapp.com/facts/\
Request Body: N/A, since it is GET\
Response status: `200 OK`\
Response: HTML
```html
<!DOCTYPE html>
<html>

<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="utf-8">
	<title>Application Error</title>
	<style media="screen">
		html,
		body,
		iframe {
			margin: 0;
			padding: 0;
		}

		html,
		body {
			height: 100%;
			overflow: hidden;
		}

		iframe {
			width: 100%;
			height: 100%;
			border: 0;
		}
	</style>
</head>

<body>
	<iframe src="//www.herokucdn.com/error-pages/application-error.html"></iframe>
</body>

</html>
```

API-GET-2\
API: GET https://dog.ceo/api/breeds/list/all\
Request Body: N/A, since it is GET\
Response status: `200 OK`\
Response: json
```json
{
    "message": {
        "affenpinscher": [],
        "african": [],
        "airedale": [],
        "akita": [],
        "appenzeller": [],
        "australian": [
            "kelpie",
            "shepherd"
        ],
        "bakharwal": [
            "indian"
        ],
        "basenji": [],
        "beagle": [],
        "bluetick": [],
        "borzoi": [],
        "bouvier": [],
        "boxer": [],
        "brabancon": [],
        "briard": [],
        "buhund": [
            "norwegian"
        ],
        "bulldog": [
            "boston",
            "english",
            "french"
        ],
        "bullterrier": [
            "staffordshire"
        ],
        "cattledog": [
            "australian"
        ],
        ...
        "tervuren": [],
        "vizsla": [],
        "waterdog": [
            "spanish"
        ],
        "weimaraner": [],
        "whippet": [],
        "wolfhound": [
            "irish"
        ]
    },
    "status": "success"
}
```
API-GET-3\
API: GET https://vpic.nhtsa.dot.gov/api/vehicles/getallmanufacturers?format=xml\
Request Body: N/A, since it is GET\
Response status: `200 OK`\
Response body: XML
```XML
<Response xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    <Count>100</Count>
    <Message>Response returned successfully</Message>
    <Results>
        <Manufacturers>
            <Mfr_ID>955</Mfr_ID>
            <Mfr_Name>TESLA, INC.</Mfr_Name>
            <Mfr_CommonName>Tesla</Mfr_CommonName>
            <Country>UNITED STATES (USA)</Country>
            <VehicleTypes>
                <VehicleType>
                    <Name>Passenger Car</Name>
                    <IsPrimary>true</IsPrimary>
                </VehicleType>
                <VehicleType>
                    <Name>Truck</Name>
                    <IsPrimary>false</IsPrimary>
                </VehicleType>
                <VehicleType>
                    <Name>Multipurpose Passenger Vehicle (MPV)</Name>
                    <IsPrimary>false</IsPrimary>
                </VehicleType>
            </VehicleTypes>
        </Manufacturers>
        ...
        <Manufacturers>
            <Mfr_ID>1088</Mfr_ID>
            <Mfr_Name>TOYOTA MOTOR MANUFACTURING, KENTUCKY, INC.</Mfr_Name>
            <Mfr_CommonName>Toyota</Mfr_CommonName>
            <Country>UNITED STATES (USA)</Country>
            <VehicleTypes />
        </Manufacturers>
        <Manufacturers>
            <Mfr_ID>1089</Mfr_ID>
            <Mfr_Name>JOHN THOMAS, INC</Mfr_Name>
            <Country>UNITED STATES (USA)</Country>
            <VehicleTypes>
                <VehicleType>
                    <Name>Trailer</Name>
                    <IsPrimary>true</IsPrimary>
                </VehicleType>
            </VehicleTypes>
        </Manufacturers>
    </Results>
</Response>
```

API-GET-4\
API: GET https://api.qrserver.com/v1/create-qr-code/?data=HelloWorld\
Request Body: N/A, since it is GET\
Response status: `200 OK`\
Response body: PNG image

![Get Response](getResponse.png)

API-GET-5\
API: GET https://currentmillis.com/time/minutes-since-unix-epoch.php\
Request Body: N/A, since it is GET\
Response status: `200 OK`\
Response Body: Pure text number
```
29061718
```

# 5 Post API with json request body, please also paste the response here

API-POST-1
API: POST https://httpbin.org/post
Request Body: 
```json
{
     "Age": 20,
     "Name": "Joe"
 }
```
Response status: `200 OK`\
Response Body:
```json
{
    ...
    "json": {
        "Age": 20,
        "Name": "Joe"
    },
    ...
}
```

API-POST-2\
API: POST localhost:8080/api/v1/post\
Request Body:
```json
{
    "id": 123,
    "title": "title",
    "description": "description",
    "content":"content"
}
```
Response status: `200 OK`\
Response Body:
``` 

```


API-POST-2\
API: POST localhost:8080/api/v1/post\
Request Body:
```json
{
    "id": 123,
    "title": "title",
    "description": "description",
    "content":"content"
}
```
Response status: `200 OK`\
Response Body:
``` 

```

API-POST-3\
API: POST https://reqres.in/api/users\
Request Body:
```json
{
    "name": "morpheus",
    "job": "leader"
}
```
Response status: `201 Created`\
Response Body:
```json
{
    "name": "morpheus",
    "job": "leader",
    "id": "69",
    "createdAt": "2025-04-03T18:51:03.932Z"
}
```

API-POST-4\
API: POST https://reqres.in/api/register\
Request Body:
```json
{
    "email": "eve.holt@reqres.in",
    "password": "pistol"
}
```
Response status: `200 OK`\
Response Body:
```json
{
    "id": 4,
    "token": "QpwL5tke4Pnpja7X4"
}
```

API-POST-5\
API: POST https://fakestoreapi.com/carts\
Request Body:
```json
{
    "userId": 1,
    "date": "2025-04-03",
    "products": [
        {
            "productId": 1,
            "quantity": 2
        }
    ]
}
```
Response status: `200 OK`\
Response Body:
```json
{
    "id": 11,
    "userId": 1,
    "date": "2025-04-03",
    "products": [
        {
            "productId": 1,
            "quantity": 2
        }
    ]
}
```


# 3 PUT API with json request body, please also paste the response here
API-PUT-1\
API: PUT https://reqres.in/api/users/2\
Request Body:
```json
{
    "name": "morpheus",
    "job": "zion resident"
}
```
Response status: `200 OK`\
Response Body:
```json
{
    "name": "morpheus",
    "job": "zion resident",
    "updatedAt": "2025-04-03T18:58:27.533Z"
}
```

API-PUT-2\
API: PUT https://jsonplaceholder.typicode.com/posts/1\
Request Body:
```json
{
    "id": 1,
    "title": "updated title",
    "body": "updated body",
    "userId": 1
}
```
Response status: `200 OK`\
Response Body:
```json
{
    "id": 1,
    "title": "updated title",
    "body": "updated body",
    "userId": 1
}
```

API-PUT-3\
API: PUT https://fakestoreapi.com/products/7\
Request Body:
```json
{
    "title": "Updated T-Shirt",
    "price": 15.99,
    "description": "A fresh new look",
    "image": "https://example.com/image.png",
    "category": "clothing"
}
```
Response status: `200 OK`\
Response Body:
```json
{
    "id": 7,
    "title": "Updated T-Shirt",
    "price": 15.99,
    "description": "A fresh new look",
    "image": "https://example.com/image.png",
    "category": "clothing"
}
```

# 2 DELETE API
API-DELETE-1\
API: DELETE https://reqres.in/api/users/2\
Request Body: N/A, since it is DELETE\
Response status: `204 No Content`\
Response Body: N/A

API-DELETE-2\
API: DELETE \
Request Body: N/A, since it is DELETE\
Response status: `200 OK`\
Response Body:
```json
{}
```

- Each example with 404, 401,500 and any http status codes you know

API: GET https://jsonplaceholder.typicode.com/asdfasdf\
Request Body: N/A, since it is GET\
Response status: `404 Not Found`\
Response Body:
```json
{}
```

API: GET https://httpbin.org/basic-auth/user/passwd\
Request Body: N/A, since it is GET\
Response status: `401 UNAUTHORIZED`\
Response Body: NA

API: GET https://httpstat.us/500\
Request Body: N/A, since it is GET\
Response status: `500 Internal Server Error`\
Response Body:
```
500 Internal Server Error
```

API: POST https://reqres.in/api/register\
Request Body: 
```json
{"email": "eve.holt@reqres.in"}
```
Response status: `400 Bad Request`\
Response Body:
```json
{
    "error": "Missing password"
}
```


# API Design Guide

## 1. Find the Customer's Payments  
**Endpoint:**  
```
GET /customers/{customerId}/payments
```

**Response Example:**
```json
[
  { "id": "1", "type": "credit_card", "last4": "1234" },
  { "id": "2", "type": "credit_card", "last4": "5678" },
  { "id": "3", "type": "paypal", "email": "user@example.com" },
  { "id": "4", "type": "apple_pay", "device": "iPhone 14" }
]
```

---

## 2. Find the Customer's Order History (Date Range)  
**Endpoint:**  
```
GET /customers/{customerId}/orders?start=2022-10-10&end=2022-10-24
```

**Response Example:**
```json
[
  { "id": "1001", "date": "2022-10-11", "total": 89.99 },
  { "id": "1002", "date": "2022-10-20", "total": 149.50 }
]
```

---

## 3. Find the Customer's Delivery Addresses  
**Endpoint:**  
```
GET /customers/{customerId}/addresses
```

**Response Example:**
```json
[
  { "id": "1", "address": "123 Main St, CA", "type": "home" },
  { "id": "2", "address": "456 Work Blvd, CA", "type": "work" }
]
```

---

## 4. Get Default Payment and Delivery Address  
**Endpoint:**  
```
GET /customers/{customerId}/defaults
```

**Response Example:**
```json
{
  "default_payment": { "id": "1", "type": "credit_card", "last4": "1234" },
  "default_address": { "id": "2", "address": "456 Work Blvd, CA" }
}
```

---

## 5. Two Example API Naming Conventions

### Twitter API
- `GET /2/users/:id`
- `GET /2/tweets/:id`
- `POST /2/tweets`
- Clear resource-based structure, versioned (`/2/`)

### PayPal API
- `GET /v1/payments/payment/:id`
- `POST /v1/payments/payment`
- Uses `/v1/` for versioning, follows nouns for resource names

---

## 6. Blog Website API Collection

| Action        | Endpoint                            | Description                       |
|---------------|-------------------------------------|-----------------------------------|
| GET           | `/posts`                            | List all posts                    |
| GET           | `/posts/{postId}`                   | Get single post                   |
| POST          | `/posts`                            | Create a new post                 |
| PUT           | `/posts/{postId}`                   | Update a post                     |
| DELETE        | `/posts/{postId}`                   | Delete a post                     |
| GET           | `/posts/{postId}/comments`          | Get comments for a post           |
| POST          | `/posts/{postId}/comments`          | Add a comment                     |
| DELETE        | `/posts/{postId}/comments/{commentId}` | Delete a comment               |
| GET           | `/users/{userId}/posts`             | Get posts by a specific user      |