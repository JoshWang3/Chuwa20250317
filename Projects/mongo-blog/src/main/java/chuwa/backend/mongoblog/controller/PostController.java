package chuwa.backend.mongoblog.controller;

import chuwa.backend.mongoblog.model.dto.PostDTO;
import chuwa.backend.mongoblog.model.vo.PostVO;
import chuwa.backend.mongoblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: PostController
 * Package: chuwa.backend.mongoblog.controller
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/4 2:50
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostVO> createPost(@RequestBody PostDTO postDTO) {
        PostVO createdPost = postService.createPost(postDTO);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostVO>> getAllPosts() {
        List<PostVO> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
