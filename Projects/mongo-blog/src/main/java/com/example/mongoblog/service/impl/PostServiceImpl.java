package com.example.mongoblog.service.impl;

import com.example.mongoblog.dto.PostDto;
import com.example.mongoblog.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    @Override
    public PostDto createPost(PostDto postDto) {
        System.out.println("Created post: " + postDto.getTitle());
        return postDto;
    }
}
