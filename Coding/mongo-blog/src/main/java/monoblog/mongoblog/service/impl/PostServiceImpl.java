package monoblog.mongoblog.service.impl;

import monoblog.mongoblog.dao.PostRepository;
import monoblog.mongoblog.dto.PostDTO;
import monoblog.mongoblog.entity.Post;
import monoblog.mongoblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(PostDTO postDTO) {
        Post post = new Post(postDTO.getTitle(), postDTO.getContent(), postDTO.getAuthor());
        return postRepository.save(post);
    }
}
