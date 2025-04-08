package chuwa.backend.redbook.service;

import chuwa.backend.redbook.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO createComment(long postId, CommentDTO commentDto);

    List<CommentDTO> getCommentsByPostId(long postId);

    CommentDTO getCommentById(Long postId, Long commentId);

    CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDtoRequest);

    void deleteComment(Long postId, Long commentId);

}
