package chuwa.backend.mongoblog.service.impl;

import chuwa.backend.mongoblog.model.document.Post;
import chuwa.backend.mongoblog.model.dto.PostDTO;
import chuwa.backend.mongoblog.model.vo.PostVO;
import chuwa.backend.mongoblog.repository.PostRepository;
import chuwa.backend.mongoblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: PostServiceImpl
 * Package: chuwa.backend.mongoblog.service.impl
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/4 2:51
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public PostVO createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setAuthor(postDTO.getAuthor());
        post.setCreatedAt(LocalDateTime.now());

        Post savedPost = postRepository.save(post);

        return new PostVO(
                savedPost.getId(),
                savedPost.getTitle(),
                savedPost.getContent(),
                savedPost.getAuthor(),
                savedPost.getCreatedAt()
        );

    }

    @Override
    public List<PostVO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> new PostVO(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getAuthor(),
                        post.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
}
