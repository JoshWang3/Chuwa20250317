package com.example.mongoblog.Service;

import com.example.mongoblog.DTO.PostDto;
import com.example.mongoblog.Entity.Post;

public interface PostService {
  Post createPost(PostDto postDto);
}
