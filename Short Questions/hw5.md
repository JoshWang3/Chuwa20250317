# hw5
## Database
CRUD MySQL and MongoDB (separated PDF)
## REST API
### Postman practice
Practice with below examples (You can find any Open APIs on the internet):
- 5 GET APIs with different response type
- 5 Post API with json request body, please also paste the response here
- 3 PUT API with json request body, please also paste the response here
- 2 DELETE API
- Each example with 404, 401,500 and any http status codes you know

#### API-GET-1
**API**: GET https://jsonplaceholder.typicode.com/posts/1  
**RequestBody**: N/A, since it is GET  
**Response status**: 200 OK  
**Response**: JSON
```json
{
    "userId": 1,
    "id": 1,
    "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
    "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
}
```
#### API-GET-2
**API**: GET https://www.w3schools.com/xml/note.xml  
**RequestBody**: N/A, since it is GET  
**Response status**: 200 OK  
**Response**: XML
```xml
<?xml version="1.0" encoding="UTF-8"?>
<note>
    <to>Tove</to>
    <from>Jani</from>
    <heading>Reminder</heading>
    <body>Don't forget me this weekend!</body>
</note>
```
#### API-GET-3
**API**: GET https://baconipsum.com/api/?type=meat-and-filler&paras=1&format=text  
**RequestBody**: N/A, since it is GET  
**Response status**: 200 OK  
**Response**: Plain text
```
Biltong fugiat meatball enim picanha capicola eiusmod ut pork chop pork loin esse est officia shankle.  Proident lorem anim, burgdoggen capicola id non pig esse t-bone salami sed pork loin duis boudin.  Incididunt aliquip capicola cupim nostrud.  Chicken eiusmod andouille, tri-tip dolor meatball quis chuck dolore esse incididunt.  Alcatra quis nisi, irure strip steak tempor ground round occaecat culpa.  Aute filet mignon voluptate bresaola turkey.  Picanha buffalo shank voluptate beef ribs landjaeger meatloaf elit pork chop occaecat tail pig doner venison.
```
#### API-GET-4
**API**: GET https://example.com  
**RequestBody**: N/A, since it is GET  
**Response status**: 200 OK  
**Response**: HTML
```html
<!doctype html>
<html>

<head>
    <title>Example Domain</title>

    <meta charset="utf-8" />
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <style type="text/css">
        body {
            background-color: #f0f0f2;
            margin: 0;
            padding: 0;
            font-family: -apple-system, system-ui, BlinkMacSystemFont, "Segoe UI", "Open Sans", "Helvetica Neue", Helvetica, Arial, sans-serif;

        }

        div {
            width: 600px;
            margin: 5em auto;
            padding: 2em;
            background-color: #fdfdff;
            border-radius: 0.5em;
            box-shadow: 2px 3px 7px 2px rgba(0, 0, 0, 0.02);
        }

        a:link,
        a:visited {
            color: #38488f;
            text-decoration: none;
        }

        @media (max-width: 700px) {
            div {
                margin: 0 auto;
                width: auto;
            }
        }
    </style>
</head>

<body>
    <div>
        <h1>Example Domain</h1>
        <p>This domain is for use in illustrative examples in documents. You may use this
            domain in literature without prior coordination or asking for permission.</p>
        <p><a href="https://www.iana.org/domains/example">More information...</a></p>
    </div>
</body>

</html>
```
#### API-GET-5
**API**: GET https://placehold.co/600x400/png  
**RequestBody**: N/A, since it is GET  
**Response status**: 200 OK  
**Response**: PNG image
![Response: PNG image](https://placehold.co/600x400/png)
#### API-POST-1
**API**: POST https://jsonplaceholder.typicode.com/posts  
**RequestBody**:
```json
{
    "title": "foo",
    "body": "bar",
    "userId": 1
}
```
**Response status**: 201 Created  
**Response**:
```json
{
    "title": "foo",
    "body": "bar",
    "userId": 1,
    "id": 101
}
```
#### API-POST-2
**API**: POST https://jsonplaceholder.typicode.com/post  
**RequestBody**:
```json
{
    "title": "foo",
    "body": "bar",
    "userId": 1
}
```
**Response status**: 404 Not Found  
**Response**:
```json
{}
```
#### API-POST-3
**API**: POST https://example.com  
**RequestBody**:
```json
{
    "title": "New Post",
    "body": "This is a new post",
    "userId": 1
}
```
**Response status**: 403 Forbidden  
**Response**:
```html
<HTML>

<HEAD>
    <TITLE>Access Denied</TITLE>
</HEAD>

<BODY>
    <H1>Access Denied</H1>

    You don't have permission to access "http&#58;&#47;&#47;example&#46;com&#47;" on this server.<P>
        Reference&#32;&#35;18&#46;88f6d517&#46;1743633863&#46;1a5cb9a7
    <P>https&#58;&#47;&#47;errors&#46;edgesuite&#46;net&#47;18&#46;88f6d517&#46;1743633863&#46;1a5cb9a7</P>
</BODY>

</HTML>
```
#### API-POST-4
**API**: POST https://jsonplaceholder.typicode.com/comments  
**RequestBody**:
```json
{
    "postId": 1,
    "name": "test",
    "email": "test@example.com",
    "body": "This is a test comment."
}
```
**Response status**: 201 Created  
**Response**:
```json
{
    "postId": 1,
    "name": "test",
    "email": "test@example.com",
    "body": "This is a test comment.",
    "id": 501
}
```
#### API-POST-5
**API**: POST https://jsonplaceholder.typicode.com/todos  
**RequestBody**:
```json
{
    "title": "Buy groceries",
    "completed": false,
    "userId": 1
}
```
**Response status**: 201 Created  
**Response**:
```json
{
    "title": "Buy groceries",
    "completed": false,
    "userId": 1,
    "id": 201
}
```
#### API-PUT-1
**API**: PUT https://jsonplaceholder.typicode.com/posts/1  
**RequestBody**:
```json
{
    "id": 1,
    "title": "new title",
    "body": "new body",
    "userId": 1
}
```
**Response status**: 200 OK  
**Response**:
```json
{
    "id": 1,
    "title": "new title",
    "body": "new body",
    "userId": 1
}
```
#### API-PUT-2
**API**: PUT https://jsonplaceholder.typicode.com/posts/101  
**RequestBody**:
```json
{
    "id": 101,
    "title": "new title",
    "body": "new body",
    "userId": 1
}
```
**Response status**: 500 Internal Server Error  
**Response**:
```
TypeError: Cannot read properties of undefined (reading 'id')
    at update (/app/node_modules/json-server/lib/server/router/plural.js:262:24)
    at Layer.handle [as handle_request] (/app/node_modules/express/lib/router/layer.js:95:5)
    at next (/app/node_modules/express/lib/router/route.js:137:13)
    at next (/app/node_modules/express/lib/router/route.js:131:14)
    at Route.dispatch (/app/node_modules/express/lib/router/route.js:112:3)
    at Layer.handle [as handle_request] (/app/node_modules/express/lib/router/layer.js:95:5)
    at /app/node_modules/express/lib/router/index.js:281:22
    at param (/app/node_modules/express/lib/router/index.js:354:14)
    at param (/app/node_modules/express/lib/router/index.js:365:14)
    at Function.process_params (/app/node_modules/express/lib/router/index.js:410:3)
```
#### API-PUT-3
**API**: PUT https://jsonplaceholder.typicode.com/posts  
**RequestBody**:
```json
{
    "id": 1,
    "title": "new title",
    "body": "new body",
    "userId": 1
}
```
**Response status**: 404 Not Found  
**Response**:
```json
{}
```
#### API-DELETE-1
**API**: DELETE https://jsonplaceholder.typicode.com/posts/1  
**RequestBody**: None  
**Response status**: 200 OK  
**Response**:
```json
{}
```
#### API-DELETE-2
**API**: DELETE https://jsonplaceholder.typicode.com/posts  
**RequestBody**: None  
**Response status**: 404 Not Found  
**Response**:
```json
{}
```
### API Design
#### 1. Find the customer's payments, like credit card 1, credit card 2, paypal, Apple Pay.
`GET` `/api/v1/customers/{customer_id}/payments`
#### 2. Find the customer's history orders from 10/10/2022 to 10/24/2022
`GET` `/api/v1/customers/{customer_id}/orders?start-date=2022-10-10&end-date=2022-10-24`
#### 3. Find the customer's delievery addresses
`GET` `/api/v1/customers/{customer_id}/addresses`
#### 4. If I also want to get customer's default payment and default delievery address, what kind of the API (URL) should be?
`GET` `/api/v1/customers/{customer_id}/payments/default`  
`GET` `/api/v1/customers/{customer_id}/addresses/default`
#### 5. Find 2 collection of APIs example. ie. Twitter, Paypal, Youtube etc. -- 命名规范
- Twitter:
    | API | Purpose |
    | --- | --- |
    | `GET` `/{user_id}` | Retrieve the tweets of the user |
    | `GET` `/{user_id}/status/{tweets_id}` | Retrieve a specific tweet of the user |
    | `GET` `/{user_id}/followers` | Retrieve the followers of the user |
    | `GET` `/{user_id}/following` | Retrieve the following users of the user |
- Youtube:
    | API | Purpose |
    | --- | --- |
    | `GET` `/watch?v={video_id}&ab_channel={channel_id}` | Retrieve the video from the channel |
    | `GET` `/@{channel_id}` | Retrieve the home page of the channel |
    | `GET` `/@{channel_id}/videos` | Retrieve the videos page of the channel |
#### 6. Design a collection of APIs for a Blog Website, please specify GET POST PUT DELETE
- `GET` `/users`: Retrieve a list of users.
- `GET` `/users/{user_id}`: Get details of a specific user.
- `POST` `/users`: Create a new user.
- `PUT` `/users/{user_id}`: Update the user's information.
- `DELETE` `/users/{user_id}`: Delete a specific user.
- `GET` `/posts`: Retrieve all blog posts.
- `GET` `/posts/{post_id}`: Get a specific blog post.
- `POST` `/posts`: Create a new blog post.
- `PUT` `/posts/{post_id}`: Update an existing blog post.
- `DELETE` `/posts/{post_id}`: Delete a specific blog post.
- `GET` `/posts/{post_id}/comments`: Retrieve all comments for a blog post.
- `POST` `/posts/{post_id}/comments`: Create a new comment to a blog post.
- `DELETE` `/posts/{post_id}/comments/{comment_id}`: Delete a specific comment of a specific blog post.