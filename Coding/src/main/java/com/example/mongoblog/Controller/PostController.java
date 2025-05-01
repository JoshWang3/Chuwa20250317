package com.example.mongoblog.Controller;

import com.example.mongoblog.DTO.PostDto;
import com.example.mongoblog.Entity.Post;
import com.example.mongoblog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
 private final PostService postService;
 @Autowired
  public PostController(PostService postService) {
   this.postService = postService;
 }

 @PostMapping
  public Post createPost(@RequestBody PostDto postDto) {
   return postService.createPost(postDto);
 }
}
