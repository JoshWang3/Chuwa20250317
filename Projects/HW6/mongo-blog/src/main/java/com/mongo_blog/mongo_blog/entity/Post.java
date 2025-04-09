package com.mongo_blog.mongo_blog.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posts")  // This annotation tells MongoDB to store posts in the "posts" collection.
public class Post {

    @Id
    private String id;  // MongoDB generates a unique ID for each document

    private String title;
    private String content;

    // Constructors
    public Post() {}

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
