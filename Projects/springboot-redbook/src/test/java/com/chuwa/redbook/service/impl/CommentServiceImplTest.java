package com.chuwa.redbook.service.impl;

import com.chuwa.redbook.dao.CommentRepository;
import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Comment;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.exception.BlogAPIException;
import com.chuwa.redbook.exception.ResourceNotFoundException;
import com.chuwa.redbook.payload.CommentDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * ClassName: CommentServiceImplTest
 * Package: com.chuwa.redbook.service.impl
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/22 18:35
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImplTest.class);

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Post post;
    private Comment comment;
    private CommentDto commentDto;

    @BeforeAll
    static void beforeAll() {
        logger.info("START test");
    }

    @BeforeEach
    void setUp() {
        logger.info("Setting up test data for CommentServiceImpl tests");
        this.post = new Post(1L, "Test Post", "Test Description", "Test Content",
                LocalDateTime.now(), LocalDateTime.now());
        this.comment = new Comment(1L, "Test Name", "test@example.com", "Test Body");
        comment.setPost(post);

        commentDto = new CommentDto();
        commentDto.setId(1L);
        commentDto.setName("Test Name");
        commentDto.setEmail("test@example.com");
        commentDto.setBody("Test Body");
    }

    @Test
    @DisplayName("Test Creation Comments - Success Scenario")
    void testCreateComment_Success() {
        Mockito.when(modelMapper.map(ArgumentMatchers.any(CommentDto.class), ArgumentMatchers.eq(Comment.class)))
                .thenReturn(comment);
        Mockito.when(postRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(post));
        Mockito.when(commentRepository.save(ArgumentMatchers.any(Comment.class)))
                .thenReturn(comment);
        Mockito.when(modelMapper.map(ArgumentMatchers.any(Comment.class), ArgumentMatchers.eq(CommentDto.class)))
                .thenReturn(commentDto);

        CommentDto savedComment = commentService.createComment(1L, commentDto);

        Assertions.assertNotNull(savedComment);
        Assertions.assertEquals(commentDto.getName(), savedComment.getName());
        Assertions.assertEquals(commentDto.getEmail(), savedComment.getEmail());
        Assertions.assertEquals(commentDto.getBody(), savedComment.getBody());

        Mockito.verify(postRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.verify(commentRepository, Mockito.times(1)).save(ArgumentMatchers.any(Comment.class));
    }

    @Test
    @DisplayName("Test Create Comment - Post Not Found")
    void testCreateComment_PostNotFound() {
        Mockito.when(postRepository.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new ResourceNotFoundException("Post", "id", 1L));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> commentService.createComment(1L, commentDto));

        Mockito.verify(commentRepository, Mockito.never()).save(ArgumentMatchers.any(Comment.class));
    }

    @Test
    @DisplayName("Test Get Comments By Post ID")
    void testGetCommentsByPostId() {

        List<Comment> comments = Arrays.asList(comment);

        Mockito.when(commentRepository.findByPostId(ArgumentMatchers.anyLong()))
                .thenReturn(comments);
        Mockito.when(modelMapper.map(ArgumentMatchers.any(Comment.class), ArgumentMatchers.eq(CommentDto.class)))
                .thenReturn(commentDto);

        List<CommentDto> commentDtos = commentService.getCommentsByPostId(1L);

        Assertions.assertNotNull(commentDtos);
        Assertions.assertEquals(1, commentDtos.size());

        CommentDto resultDto = commentDtos.get(0);
        Assertions.assertEquals(commentDto.getName(), resultDto.getName());
        Assertions.assertEquals(commentDto.getEmail(), resultDto.getEmail());
        Assertions.assertEquals(commentDto.getBody(), resultDto.getBody());

        Mockito.verify(commentRepository, Mockito.times(1)).findByPostId(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("Test Get Comment By ID - Success")
    void testGetCommentById_Success() {
        Mockito.when(postRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(post));
        Mockito.when(commentRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(comment));
        Mockito.when(modelMapper.map(ArgumentMatchers.any(Comment.class), ArgumentMatchers.eq(CommentDto.class)))
                .thenReturn(commentDto);

        CommentDto resultDto = commentService.getCommentById(1L, 1L);

        Assertions.assertNotNull(resultDto);
        Assertions.assertEquals(commentDto.getName(), resultDto.getName());
        Assertions.assertEquals(commentDto.getEmail(), resultDto.getEmail());
        Assertions.assertEquals(commentDto.getBody(), resultDto.getBody());

        Mockito.verify(postRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.verify(commentRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("Test Get Comment By ID - Post Not Found")
    void testGetCommentById_PostNotFound() {
        Mockito.when(postRepository.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new ResourceNotFoundException("Post", "id", 1L));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> commentService.getCommentById(1L, 1L));

        Mockito.verify(commentRepository, Mockito.never()).findById(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("Test Get Comment By ID - Comment Not Found")
    void testGetCommentById_CommentNotFound() {
        Mockito.when(postRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(post));

        Mockito.when(commentRepository.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new ResourceNotFoundException("Comment", "id", 1L));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> commentService.getCommentById(1L, 1L));
    }

    @Test
    @DisplayName("Test Get Comment By ID - Comment Doesn't Belong To Post")
    void testGetCommentById_CommentNotBelongToPost() {
        Post differentPost = new Post(2L, "Different Post", "Different Desc", "Different Content",
                LocalDateTime.now(), LocalDateTime.now());

        Mockito.when(postRepository.findById(2L))
                .thenReturn(Optional.of(differentPost));
        Mockito.when(commentRepository.findById(1L))
                .thenReturn(Optional.of(comment));

        BlogAPIException exception = Assertions.assertThrows(BlogAPIException.class,
                () -> commentService.getCommentById(2L, 1L));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        Assertions.assertEquals("Comment does not belong to post", exception.getMessage());
    }

    @Test
    @DisplayName("Test Update Comment - Success")
    void testUpdateComment_Success() {
        CommentDto updatedDto = new CommentDto();
        updatedDto.setId(1L);
        updatedDto.setName("Updated Name");
        updatedDto.setEmail("updated@example.com");
        updatedDto.setBody("Updated Body");

        Comment updatedComment = new Comment(1L, "Updated Name", "updated@example.com", "Updated Body");
        updatedComment.setPost(post);

        Mockito.when(postRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(post));
        Mockito.when(commentRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(comment));
        Mockito.when(commentRepository.save(ArgumentMatchers.any(Comment.class)))
                .thenReturn(updatedComment);
        Mockito.when(modelMapper.map(ArgumentMatchers.any(Comment.class), ArgumentMatchers.eq(CommentDto.class)))
                .thenReturn(updatedDto);

        CommentDto resultDto = commentService.updateComment(1L, 1L, updatedDto);

        Assertions.assertNotNull(resultDto);
        Assertions.assertEquals(updatedDto.getName(), resultDto.getName());
        Assertions.assertEquals(updatedDto.getEmail(), resultDto.getEmail());
        Assertions.assertEquals(updatedDto.getBody(), resultDto.getBody());

        Mockito.verify(postRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.verify(commentRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.verify(commentRepository, Mockito.times(1)).save(ArgumentMatchers.any(Comment.class));
    }

    @Test
    @DisplayName("Test Update Comment - Post Not Found")
    void testUpdateComment_PostNotFound() {
        Mockito.when(postRepository.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new ResourceNotFoundException("Post", "id", 1L));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> commentService.updateComment(1L, 1L, commentDto));

        Mockito.verify(commentRepository, Mockito.never()).findById(ArgumentMatchers.anyLong());
        Mockito.verify(commentRepository, Mockito.never()).save(ArgumentMatchers.any(Comment.class));
    }

    @Test
    @DisplayName("Test Update Comment - Comment Not Found")
    void testUpdateComment_CommentNotFound() {
        Mockito.when(postRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(post));
        Mockito.when(commentRepository.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new ResourceNotFoundException("Comment", "id", 1L));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> commentService.updateComment(1L, 1L, commentDto));

        Mockito.verify(commentRepository, Mockito.never()).save(ArgumentMatchers.any(Comment.class));
    }

    @Test
    @DisplayName("Test Update Comment - Comment Doesn't Belong To Post")
    void testUpdateComment_CommentNotBelongToPost() {
        Post differentPost = new Post(2L, "Different Post", "Different Desc", "Different Content",
                LocalDateTime.now(), LocalDateTime.now());

        Mockito.when(postRepository.findById(2L))
                .thenReturn(Optional.of(differentPost));
        Mockito.when(commentRepository.findById(1L))
                .thenReturn(Optional.of(comment));

        BlogAPIException exception = Assertions.assertThrows(BlogAPIException.class,
                () -> commentService.updateComment(2L, 1L, commentDto));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        Assertions.assertEquals("Comment does not belong to post", exception.getMessage());

        Mockito.verify(commentRepository, Mockito.never()).save(ArgumentMatchers.any(Comment.class));
    }

    @Test
    @DisplayName("Test Delete Comment - Success")
    void testDeleteComment_Success() {
        Mockito.when(postRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(post));
        Mockito.when(commentRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(comment));
        Mockito.doNothing().when(commentRepository).delete(ArgumentMatchers.any(Comment.class));

        commentService.deleteComment(1L, 1L);

        Mockito.verify(postRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.verify(commentRepository, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
        Mockito.verify(commentRepository, Mockito.times(1)).delete(ArgumentMatchers.any(Comment.class));
    }

    @Test
    @DisplayName("Test Delete Comment - Post Not Found")
    void testDeleteComment_PostNotFound() {
        Mockito.when(postRepository.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new ResourceNotFoundException("Post", "id", 1L));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> commentService.deleteComment(1L, 1L));

        Mockito.verify(commentRepository, Mockito.never()).findById(ArgumentMatchers.anyLong());
        Mockito.verify(commentRepository, Mockito.never()).delete(ArgumentMatchers.any(Comment.class));
    }

    @Test
    @DisplayName("Test Delete Comment - Comment Not Found")
    void testDeleteComment_CommentNotFound() {
        Mockito.when(postRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(post));
        Mockito.when(commentRepository.findById(ArgumentMatchers.anyLong()))
                .thenThrow(new ResourceNotFoundException("Comment", "id", 1L));

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> commentService.deleteComment(1L, 1L));

        Mockito.verify(commentRepository, Mockito.never()).delete(ArgumentMatchers.any(Comment.class));
    }

    @Test
    @DisplayName("Test Delete Comment - Comment Doesn't Belong To Post")
    void testDeleteComment_CommentNotBelongToPost() {
        Post differentPost = new Post(2L, "Different Post", "Different Desc", "Different Content",
                LocalDateTime.now(), LocalDateTime.now());

        Mockito.when(postRepository.findById(2L))
                .thenReturn(Optional.of(differentPost));
        Mockito.when(commentRepository.findById(1L))
                .thenReturn(Optional.of(comment));

        BlogAPIException exception = Assertions.assertThrows(BlogAPIException.class,
                () -> commentService.deleteComment(2L, 1L));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        Assertions.assertEquals("Comment does not belong to post", exception.getMessage());

        Mockito.verify(commentRepository, Mockito.never()).delete(ArgumentMatchers.any(Comment.class));
    }

}
