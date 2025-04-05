package com.example.mongoblog.service;

import com.example.mongoblog.dto.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
