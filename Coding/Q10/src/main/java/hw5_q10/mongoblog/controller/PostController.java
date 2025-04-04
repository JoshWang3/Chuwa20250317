package hw5_q10.mongoblog.controller;

import hw5_q10.mongoblog.model.Post;
import hw5_q10.mongoblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable String id) {
        return postService.getPostById(id);
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable String id) {
        postService.deletePostById(id);
        return "Post deleted successfully!";
    }
}
