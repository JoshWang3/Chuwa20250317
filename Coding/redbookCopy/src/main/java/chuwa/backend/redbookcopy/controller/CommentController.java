package chuwa.backend.redbookcopy.controller;

import chuwa.backend.redbookcopy.Dto.CommentDto;
import chuwa.backend.redbookcopy.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getComments(postId));
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Long postId, @PathVariable Long id) {
        return ResponseEntity.ok(commentService.getComment(postId, id));
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long postId, @RequestBody CommentDto commentDto) {
        return ResponseEntity. ok(commentService.createComment(postId, commentDto));
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long postId, @PathVariable Long id, @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateComment(postId, id, commentDto));
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long id) {
        commentService.deleteComment(postId, id);
        return ResponseEntity.ok("Delete comment with id " + id);
    }
}
