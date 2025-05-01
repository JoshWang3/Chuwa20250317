package com.example.mongoblog.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posts")
public class Post {
  @Id
  private String id;
  private String content;

  public Post() {
  }

  public Post(String id, String content) {
    this.id = id;
    this.content = content;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return "Post{" +
        "id='" + id + '\'' +
        ", content='" + content + '\'' +
        '}';
  }
}

