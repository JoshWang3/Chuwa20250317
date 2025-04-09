package com.mongo_blog.mongo_blog.repository;


import com.mongo_blog.mongo_blog.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
    // MongoRepository provides CRUD operations out of the box.
    // You can add custom queries if necessary.
}
