package com.chuwa.redbook.service.impl;

import com.chuwa.redbook.dao.CommentRepository;
import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Comment;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.exception.BlogAPIException;
import com.chuwa.redbook.exception.ResourceNotFoundException;
import com.chuwa.redbook.payload.CommentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Comment comment;
    private CommentDto commentDto;
    private Post post;

    @BeforeEach
    public void setup() {
        post = new Post();
        post.setId(1L);

        comment = new Comment();
        comment.setId(1L);
        comment.setBody("Nice post!");
        comment.setPost(post);

        commentDto = new CommentDto();
        commentDto.setId(1L);
        commentDto.setBody("Nice post!");
    }

    @Test
    public void testCreateComment() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(modelMapper.map(commentDto, Comment.class)).thenReturn(comment);
        when(commentRepository.save(comment)).thenReturn(comment);
        when(modelMapper.map(comment, CommentDto.class)).thenReturn(commentDto);

        CommentDto result = commentService.createComment(1L, commentDto);

        assertNotNull(result);
        assertEquals(commentDto.getBody(), result.getBody());
    }

    @Test
    public void testGetCommentsByPostId() {
        when(commentRepository.findByPostId(1L)).thenReturn(List.of(comment));
        when(modelMapper.map(comment, CommentDto.class)).thenReturn(commentDto);

        List<CommentDto> result = commentService.getCommentsByPostId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(commentDto.getBody(), result.get(0).getBody());
    }

    @Test
    public void testGetCommentById() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(modelMapper.map(comment, CommentDto.class)).thenReturn(commentDto);

        CommentDto result = commentService.getCommentById(1L, 1L);

        assertNotNull(result);
        assertEquals(commentDto.getBody(), result.getBody());
    }

    @Test
    public void testGetCommentById_BlogAPIException() {
        Post otherPost = new Post();
        otherPost.setId(2L);
        comment.setPost(otherPost);

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        assertThrows(BlogAPIException.class, () -> commentService.getCommentById(1L, 1L));
    }

    @Test
    public void testUpdateComment() {
        commentDto.setBody("Updated Comment");

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(commentRepository.save(comment)).thenReturn(comment);
        when(modelMapper.map(comment, CommentDto.class)).thenReturn(commentDto);

        CommentDto result = commentService.updateComment(1L, 1L, commentDto);

        assertNotNull(result);
        assertEquals("Updated Comment", result.getBody());
    }

    @Test
    public void testUpdateComment_BlogAPIException() {
        Post otherPost = new Post();
        otherPost.setId(2L);
        comment.setPost(otherPost);

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        assertThrows(BlogAPIException.class, () -> commentService.updateComment(1L, 1L, commentDto));
    }

    @Test
    public void testDeleteComment() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        commentService.deleteComment(1L, 1L);

        verify(commentRepository, times(1)).delete(comment);
    }

    @Test
    public void testDeleteComment_BlogAPIException() {
        Post otherPost = new Post();
        otherPost.setId(2L);
        comment.setPost(otherPost);

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        assertThrows(BlogAPIException.class, () -> commentService.deleteComment(1L, 1L));
    }
}
