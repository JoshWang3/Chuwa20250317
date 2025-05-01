package com.example.mongoblog.Service.impl;

import com.example.mongoblog.DAO.PostRepository;
import com.example.mongoblog.DTO.PostDto;
import com.example.mongoblog.Entity.Post;
import com.example.mongoblog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;

  @Autowired
  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }
  @Override
  public Post createPost(PostDto postDto) {
    Post post = new Post(postDto.getId(), postDto.getContent());
    return postRepository.save(post);
  }
}
