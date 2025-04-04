package chuwa.backend.mongoblog.service;

import chuwa.backend.mongoblog.model.dto.PostDTO;
import chuwa.backend.mongoblog.model.vo.PostVO;

import java.util.List;

/**
 * ClassName: PostService
 * Package: chuwa.backend.mongoblog.service
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/4 2:48
 * @version 1.0
 */
public interface PostService {
    PostVO createPost(PostDTO postDTO);
    List<PostVO> getAllPosts();
}
