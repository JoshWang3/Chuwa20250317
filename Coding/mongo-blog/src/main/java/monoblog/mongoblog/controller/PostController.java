package monoblog.mongoblog.controller;

import monoblog.mongoblog.dto.PostDTO;
import monoblog.mongoblog.entity.Post;
import monoblog.mongoblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }
}
