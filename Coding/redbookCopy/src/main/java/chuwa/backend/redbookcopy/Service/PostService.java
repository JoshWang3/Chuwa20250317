package chuwa.backend.redbookcopy.Service;

import chuwa.backend.redbookcopy.Dto.PostDto;
import chuwa.backend.redbookcopy.Dto.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostDto getPost(Long id);
    PostDto updatePost(Long id, PostDto postDto);
    void deletePost(Long id);
    PostResponse getPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
