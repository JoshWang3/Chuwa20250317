#  hw5
1. GET /customers/{customerId}/payments  
[
  { "type": "Credit Card", "last4": "1234" },
  { "type": "PayPal", "email": "abc@example.com" },
  { "type": "Apple Pay" }
]

2. GET /customers/{customerId}/orders?from=2022-10-10&to=2022-10-24  

3. GET /customers/{customerId}/addresses  

4. GET /customers/{customerId}/payments/default  
GET /customers/{customerId}/addresses/default  

5. 
Twitter API (Tweets, Users, Likes):  
GET /users/{id}/tweets  
POST /tweets  
GET /tweets/{id}/likes  

YouTube API (Videos, Channels, Comments):  
GET /channels/{id}/videos  
GET /videos/{id}/comments  
POST /videos  

6. 
Posts  
GET /posts – get all posts  
GET /posts/{postId} – get one post  
POST /posts – create a post  
PUT /posts/{postId} – update a post  
DELETE /posts/{postId} – delete a post

Comments  
GET /posts/{postId}/comments – get all comments for a post  
POST /posts/{postId}/comments – add a comment  
DELETE /posts/{postId}/comments/{commentId} – delete a comment  
  
Users  
GET /users – list users  
GET /users/{userId} – user profile  
POST /users – register  
PUT /users/{userId} – update profile  
DELETE /users/{userId} – delete user