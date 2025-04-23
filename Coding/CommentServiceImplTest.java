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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImplTest.class);

    @Mock
    private CommentRepository commentRepositoryMock;

    @Mock(name="modelMapper")
    private ModelMapper mockedModelMapper;

    @Mock
    private PostRepository postRepositoryMock;

    @InjectMocks
    private CommentServiceImpl commentService;

    private CommentDto commentDto;
    private Comment comment;
    private Post post;

    @BeforeAll
    static void beforeAll() {
        logger.info("START test");
    }

    @BeforeEach
    void setUp() {
        logger.info("set up Comment for each test");
        this.comment = new Comment( 123L, "John Doe", "test1@xhs.com", "Hi");
        this.commentDto = new CommentDto(123L, "John Doe", "test1@xhs.com", "Hi");
        this.post = new Post(1L, "test title", "test description", "Hello",
                LocalDateTime.now(), LocalDateTime.now());
        this.comment.setPost(post);
    }

    @Test
    public void testCreateComment() {
        Mockito.when(
                mockedModelMapper.map(
                        ArgumentMatchers.any(CommentDto.class),
                        ArgumentMatchers.eq(Comment.class)))
                .thenReturn(comment);

        Mockito.when(commentRepositoryMock.save(comment))
                .thenReturn(comment);
        Mockito.when(postRepositoryMock.findById(1L))
                .thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.save(comment)).thenReturn(comment);

        Mockito.when(
                        mockedModelMapper.map(
                                ArgumentMatchers.any(Comment.class),
                                ArgumentMatchers.eq(CommentDto.class)
                        ))
                .thenReturn(commentDto);

        CommentDto commentResponse = commentService.createComment(1L, commentDto);

        Assertions.assertNotNull(commentResponse);
        Assertions.assertEquals(commentDto.getBody(), commentResponse.getBody());
        Assertions.assertEquals(commentDto.getName(), commentResponse.getName());
        Assertions.assertEquals(commentDto.getEmail(), commentResponse.getEmail());
    }

    @Test
    public void testCreateCommentResourceNotFoundException() {
        Mockito.when(postRepositoryMock.findById(404L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> commentService.createComment(404L, commentDto)
        );
        Assertions.assertEquals("Post not found with id : '404'", exception.getMessage());
    }


    @Test
    public void testGetCommentsByPostId() {
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);

        Mockito.when(commentRepositoryMock.findByPostId(1L))
                .thenReturn(comments);
        Mockito.when(
                        mockedModelMapper.map(
                                ArgumentMatchers.any(Comment.class),
                                ArgumentMatchers.eq(CommentDto.class)
                        ))
                .thenReturn(commentDto);

        List<CommentDto> commentDtos = commentService.getCommentsByPostId(1L);

        Assertions.assertNotNull(commentDtos);
        Assertions.assertEquals(1, commentDtos.size());
        CommentDto commentDtoResponse = commentDtos.get(0);
        Assertions.assertEquals(commentDto.getName(), commentDtoResponse.getName());
        Assertions.assertEquals(commentDto.getBody(), commentDtoResponse.getBody());
        Assertions.assertEquals(commentDto.getEmail(), commentDtoResponse.getEmail());
    }

    @Test
    public void testGetCommentById() {
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(comment));

        Mockito.when(mockedModelMapper.map(
                        ArgumentMatchers.any(Comment.class),
                        ArgumentMatchers.eq(CommentDto.class)
                ))
                .thenReturn(commentDto);

        CommentDto commentDtoResponse = commentService.getCommentById(1L, 123L);

        Assertions.assertNotNull(commentDtoResponse);
        Assertions.assertEquals(commentDto.getName(), commentDtoResponse.getName());
        Assertions.assertEquals(commentDto.getBody(), commentDtoResponse.getBody());
        Assertions.assertEquals(commentDto.getEmail(), commentDtoResponse.getEmail());
    }

    @Test
    public void testGetCommentByIdPostNotFound() {
        Mockito.when(postRepositoryMock.findById(404L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> commentService.getCommentById(404L, 999L)
        );
        Assertions.assertEquals("Post not found with id : '404'", exception.getMessage());

    }

    @Test
    public void testGetCommentByIdCommentNotFound() {
        Mockito.when(postRepositoryMock.findById(1L)).thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(999L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> commentService.getCommentById(1L, 999L)
        );
        Assertions.assertEquals("Comment not found with id : '999'", exception.getMessage());

    }


    @Test
    public void testGetCommentByIdNotMatching() {
        Post anotherPost = new Post();
        anotherPost.setId(2L);
        comment.setPost(anotherPost);

        Mockito.when(postRepositoryMock.findById(1L)).thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(123L)).thenReturn(Optional.of(comment));

        BlogAPIException ex = Assertions.assertThrows(
                BlogAPIException.class,
                () -> commentService.getCommentById(1L, 123L));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getHttpStatus());
    }

    @Test
    public void testUpdateComment() {
        CommentDto updatedDto = new CommentDto();
        updatedDto.setName("Updated");
        updatedDto.setEmail("updated@example.com");
        updatedDto.setBody("Updated body");

        comment.setName(updatedDto.getName());
        comment.setEmail(updatedDto.getEmail());
        comment.setBody(updatedDto.getBody());

        Mockito.when(postRepositoryMock.findById(1L)).thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(123L)).thenReturn(Optional.of(comment));
        Mockito.when(commentRepositoryMock.save(comment)).thenReturn(comment);
        Mockito.when(mockedModelMapper.map(
                        ArgumentMatchers.any(Comment.class),
                        ArgumentMatchers.eq(CommentDto.class)
                ))
                .thenReturn(updatedDto);

        CommentDto updated = commentService.updateComment(1L, 123L, updatedDto);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(updatedDto.getName(), updated.getName());
        Assertions.assertEquals(updatedDto.getBody(), updated.getBody());
        Assertions.assertEquals(updatedDto.getEmail(), updated.getEmail());
    }

    @Test
    public void testUpdateCommentByIdPostNotFound() {
        CommentDto updatedDto = new CommentDto();
        updatedDto.setName("Updated");
        updatedDto.setEmail("updated@example.com");
        updatedDto.setBody("Updated body");

        Mockito.when(postRepositoryMock.findById(404L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> commentService.updateComment(404L, 999L, updatedDto)
        );
        Assertions.assertEquals("Post not found with id : '404'", exception.getMessage());

    }

    @Test
    public void testUpdateCommentByIdCommentNotFound() {
        CommentDto updatedDto = new CommentDto();
        updatedDto.setName("Updated");
        updatedDto.setEmail("updated@example.com");
        updatedDto.setBody("Updated body");

        Mockito.when(postRepositoryMock.findById(1L)).thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(999L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> commentService.updateComment(1L, 999L, updatedDto)
        );
        Assertions.assertEquals("Comment not found with id : '999'", exception.getMessage());
    }

    @Test
    public void testUpdateCommentByIdNotMatching() {
        CommentDto updatedDto = new CommentDto();
        updatedDto.setName("Updated");
        updatedDto.setEmail("updated@example.com");
        updatedDto.setBody("Updated body");

        Post anotherPost = new Post();
        anotherPost.setId(2L);
        comment.setPost(anotherPost);

        Mockito.when(postRepositoryMock.findById(1L)).thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(123L)).thenReturn(Optional.of(comment));

        BlogAPIException ex = Assertions.assertThrows(
                BlogAPIException.class,
                () -> commentService.updateComment(1L, 123L, updatedDto));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getHttpStatus());
    }

    @Test
    public void testDeleteComment() {
        Mockito.when(postRepositoryMock.findById(1L)).thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(123L)).thenReturn(Optional.of(comment));
        Mockito.doNothing().when(commentRepositoryMock).delete(comment);


        commentService.deleteComment(1L, 123L);

        Mockito.verify(commentRepositoryMock, Mockito.times(1)).delete(comment);
    }

    @Test
    public void testDeleteCommentByIdPostNotFound() {
        Mockito.when(postRepositoryMock.findById(404L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> commentService.deleteComment(404L, 999L)
        );
        Assertions.assertEquals("Post not found with id : '404'", exception.getMessage());

    }

    @Test
    public void testDeleteCommentByIdCommentNotFound() {
        Mockito.when(postRepositoryMock.findById(1L)).thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(999L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> commentService.deleteComment(1L, 999L)
        );
        Assertions.assertEquals("Comment not found with id : '999'", exception.getMessage());
    }

    @Test
    public void testDeleteCommentByIdNotMatching() {
        Post anotherPost = new Post();
        anotherPost.setId(2L);
        comment.setPost(anotherPost);

        Mockito.when(postRepositoryMock.findById(1L)).thenReturn(Optional.of(post));
        Mockito.when(commentRepositoryMock.findById(123L)).thenReturn(Optional.of(comment));

        BlogAPIException ex = Assertions.assertThrows(
                BlogAPIException.class,
                () -> commentService.deleteComment(1L, 123L));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getHttpStatus());
    }
}
