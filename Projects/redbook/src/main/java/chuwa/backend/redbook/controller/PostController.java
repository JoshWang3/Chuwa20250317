package chuwa.backend.redbook.controller;

import chuwa.backend.redbook.dto.PostDTO;
import chuwa.backend.redbook.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: PostController
 * Package: chuwa.backend.redbook.controller
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/2 22:29
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    // define all api

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        PostDTO postResponse = postService.createPosts(postDTO);
        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostDTO> getAllPost() {
        return postService.getAllPost();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id") Long id) {
        PostDTO postResponse = postService.getPostById(id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePostById(@RequestBody PostDTO postDTO, @PathVariable(name = "id") Long id) {
        PostDTO postResponse = postService.updatePost(postDTO, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable(name = "id") Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/jpql")
    public List<PostDTO> getAllPostsJPQL() {
        return postService.getAllPostWithJPQL();
    }

    @GetMapping("/jpql-index/{id}")
    public ResponseEntity<PostDTO> getPostByIdOrTitleJPQLIndex(@PathVariable(name = "id") long id,
                                                               @RequestParam(value = "title", required = false) String title) {
        return ResponseEntity.ok(postService.getPostByIdJPQLIndexParameter(id, title));
    }

    @GetMapping("/jpql-named/{id}")
    public ResponseEntity<PostDTO> getPostByIdOrTitleJPQLNamed(@PathVariable(name = "id") long id,
                                                               @RequestParam(value = "title", required = false) String title) {
        return ResponseEntity.ok(postService.getPostByIdJPQLNamedParameter(id, title));
    }

    @GetMapping("/sql-index/{id}")
    public ResponseEntity<PostDTO> getPostByIdOrTitleSQLIndex(@PathVariable(name = "id") long id,
                                                              @RequestParam(value = "title", required = false) String title) {
        return ResponseEntity.ok(postService.getPostByIdSQLIndexParameter(id, title));
    }

    @GetMapping("/sql-named/{id}")
    public ResponseEntity<PostDTO> getPostByIdOrTitleSQLParameter(@PathVariable(name = "id") long id,
                                                                  @RequestParam(value = "title", required = false) String title) {
        return ResponseEntity.ok(postService.getPostByIdSQLNamedParameter(id, title));
    }
}
