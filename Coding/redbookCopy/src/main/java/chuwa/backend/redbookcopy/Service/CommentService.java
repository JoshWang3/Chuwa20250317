package chuwa.backend.redbookcopy.Service;

import chuwa.backend.redbookcopy.Dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto commentDto);
    void deleteComment(Long postId, Long id);
    CommentDto updateComment(Long postId, Long id, CommentDto commentDto);
    List<CommentDto> getComments(Long postId);
    CommentDto getComment(Long postId, Long id);
}
