package chuwa.backend.redbook.service;

import chuwa.backend.redbook.dto.CommentDTO;

import java.util.List;

/**
 * ClassName: CommentService
 * Package: chuwa.backend.redbook.service
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/8 21:52
 * @version 1.0
 */
public interface CommentService {
    CommentDTO createComment(Long postId, CommentDTO commentDTO);
    List<CommentDTO> getCommentsByPostId(Long postId);
    CommentDTO getCommentById(Long postId, Long commentId);
    CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO);
    void deleteComment(Long postId, Long commentId);
}
