package chuwa.backend.redbook.service;

import chuwa.backend.redbook.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto);
    List<CommentDto> getComments();
    CommentDto getComment(Long id);
    CommentDto updateComment(Long id, CommentDto commentDto);
    void deleteComment(Long id);
}
