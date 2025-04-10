package chuwa.backend.mongoblog.Controller;

import chuwa.backend.mongoblog.DTO.PostDto;
import chuwa.backend.mongoblog.Modal.Post;
import chuwa.backend.mongoblog.Service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody PostDto postDto) {
        Post savedPost = postService.createPost(postDto);
        return ResponseEntity.ok(savedPost);
    }

}
