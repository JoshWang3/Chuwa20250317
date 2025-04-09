package chuwa.backend.redbook.service.impl;

import chuwa.backend.redbook.dao.PostRepository;
import chuwa.backend.redbook.dao.jpql.PostJPQLRepository;
import chuwa.backend.redbook.dto.PostDTO;
import chuwa.backend.redbook.entity.Post;
import chuwa.backend.redbook.exception.ResourceNotFoundException;
import chuwa.backend.redbook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: PostServiceImpl
 * Package: chuwa.backend.redbook.service
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/2 22:34
 * @version 1.0
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostJPQLRepository postJPQLRepository;

    @Override
    public PostDTO createPosts(PostDTO postDTO){

        Post post = mapToEntity(postDTO);

        Post savedPost = postRepository.save(post);

        return mapToDTO(savedPost);
    }

    @Override
    public List<PostDTO> getAllPost() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postsDTO = posts.stream()
                .map(post -> mapToDTO(post))
                .collect(Collectors.toList());
        return postsDTO;
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        Post updatePost = postRepository.save(post);
        return mapToDTO(updatePost);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    @Override
    public List<PostDTO> getAllPostWithJPQL() {
        return postJPQLRepository.getAllPostWithJPQL().stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostByIdJPQLIndexParameter(Long id, String title) {
        Post post = postRepository.getPostByIDOrTitleWithJPQLIndexParameters(id, title);
        return mapToDTO(post);
    }

    @Override
    public PostDTO getPostByIdJPQLNamedParameter(Long id, String title) {
        Post post = postRepository.getPostByIDOrTitleWithJPQLNamedParameters(id, title);
        return mapToDTO(post);
    }

    @Override
    public PostDTO getPostByIdSQLIndexParameter(Long id, String title) {
        Post post = postRepository.getPostByIDOrTitleWithSQLIndexParameters(id, title);
        return mapToDTO(post);
    }

    @Override
    public PostDTO getPostByIdSQLNamedParameter(Long id, String title) {
        Post post = postRepository.getPostByIDOrTitleWithSQLNamedParameters(id, title);
        return mapToDTO(post);
    }


    private Post mapToEntity(PostDTO postDTO) {
        Post post = new Post();
        if(postDTO.getTitle() != null){
            post.setTitle(postDTO.getTitle());
        }else{
            post.setTitle("");
        }
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        return post;
    }

    private PostDTO mapToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setContent(post.getContent());

        return postDTO;
    }
}
