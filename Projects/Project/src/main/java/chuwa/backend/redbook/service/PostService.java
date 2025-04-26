package chuwa.backend.redbook.service;

import chuwa.backend.redbook.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);
    List<PostDTO> getAllPost();

    PostDTO getPostById(long id);

    PostDTO updatePost(PostDTO postDto, long id);

    void deletePostById(long id);
}
