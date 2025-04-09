package chuwa.backend.redbook.service;

import chuwa.backend.redbook.dto.PostDTO;

import java.util.List;

/**
 * ClassName: PostService
 * Package: chuwa.backend.redbook.service
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/2 22:34
 * @version 1.0
 */
public interface PostService {
    PostDTO createPosts(PostDTO postDTO);
    List<PostDTO> getAllPost();
    PostDTO getPostById(Long id);
    PostDTO updatePost(PostDTO postDTO, Long id);
    void deletePostById(Long id);

    List<PostDTO> getAllPostWithJPQL();
    PostDTO getPostByIdJPQLIndexParameter(Long id, String title);
    PostDTO getPostByIdJPQLNamedParameter(Long id, String title);
    PostDTO getPostByIdSQLIndexParameter(Long id, String title);
    PostDTO getPostByIdSQLNamedParameter(Long id, String title);

}
