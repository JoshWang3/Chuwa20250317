package walmart.payment.checking.controller;

import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.GraphQlSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import walmart.payment.checking.ExceptionHandling.PostNotFoundException;
import walmart.payment.checking.Service.PostService;
import org.springframework.http.HttpStatus;
import walmart.payment.checking.dto.PostDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {
    // define all api


    private final PostService postService;
//    private final GraphQlSource graphQlSource;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
//        this.graphQlSource = graphQlSource;
    }

    //@GetMapping
    //POST method, path: api/v1/posts
    @PostMapping("/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.createPost(postDto));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getPosts(){
        return ResponseEntity.ok(postService.getPost());
    }

    @GetMapping("posts/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPost(id));
    }

    @PutMapping("posts/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody PostDto postDto){
        return ResponseEntity.ok(postService.updatePost(postDto));
    }

    @DeleteMapping("posts/{id}")
    public ResponseEntity<PostDto> deletePost(@PathVariable Long id){
    return ResponseEntity.ok(postService.deletePost(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Long id) {
        try {
            postService.deletePost(id);  // Call service to delete post
            return ResponseEntity.ok("Post deleted successfully.");
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the post.");
        }
    }


}
