package monoblog.mongoblog.service;

import monoblog.mongoblog.dto.PostDTO;
import monoblog.mongoblog.entity.Post;

public interface PostService {
    Post createPost(PostDTO postDTO);
}
