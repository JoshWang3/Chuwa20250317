package chuwa.backend.redbook.service;

import chuwa.backend.redbook.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getPosts();
    PostDto getPost(Long id);
    PostDto updatePost(Long id, PostDto postDto);
    void deletePost(Long id);

}
