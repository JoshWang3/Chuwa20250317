package chuwa.backend.mongoblog.Service.Impl;

import chuwa.backend.mongoblog.DTO.PostDto;
import chuwa.backend.mongoblog.Modal.Post;
import chuwa.backend.mongoblog.Repository.PostRepository;
import chuwa.backend.mongoblog.Service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    final private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(PostDto postDto) {
        Post newPost = new Post();
        newPost.setTitle(postDto.getTitle());
        newPost.setDescription(postDto.getDescription());
        newPost.setContent(postDto.getContent());
        return postRepository.save(newPost);
    }
}
