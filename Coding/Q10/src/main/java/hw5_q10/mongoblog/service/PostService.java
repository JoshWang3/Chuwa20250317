package hw5_q10.mongoblog.service;

import hw5_q10.mongoblog.model.Post;
import hw5_q10.mongoblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(String id) {
        return postRepository.findById(id).orElse(null);
    }

    public void deletePostById(String id) {
        postRepository.deleteById(id);
    }
}
