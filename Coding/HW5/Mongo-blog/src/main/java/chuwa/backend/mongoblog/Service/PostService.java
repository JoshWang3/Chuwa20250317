package chuwa.backend.mongoblog.Service;

import chuwa.backend.mongoblog.DTO.PostDto;
import chuwa.backend.mongoblog.Modal.Post;

public interface PostService {
    Post createPost(PostDto postDto);
}
