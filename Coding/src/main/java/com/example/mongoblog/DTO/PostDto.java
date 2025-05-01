package com.example.mongoblog.DTO;

public class PostDto {
  private String id;
  private String content;

  public PostDto() {
  }

  public PostDto(String id, String content) {
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
}
