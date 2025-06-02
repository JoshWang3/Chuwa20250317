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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImplTest.class);

    @Mock
    private CommentRepository commentRepositoryMock;

    @Mock
    private PostRepository postRepositoryMock;

    @Mock
    private ModelMapper modelMapperMock;

    @InjectMocks
    private CommentServiceImpl commentService;

    private long commentId;
    private CommentDto commentDto;
    private Comment comment;
    private long postId, postId2;
    private Post post, post2;

    @BeforeAll
    static void beforeAll(){
        logger.info("Comment Service Test Start");
    }

    @BeforeEach
    void setUp() {
        logger.info("Set Up Comment For Each Test");
        commentId = 1L;
        comment = new Comment(commentId, "Charlie", "charlie@gmail.com", "Content");
        commentDto = new CommentDto(commentId, "Charlie", "charlie@gmail.com", "Content");
        postId = 1L;
        post = new Post(postId, "Title", "Desc", "Content", LocalDateTime.now(), LocalDateTime.now());
        postId2 = 2L;
        post2 = new Post(postId2, "Title2", "Desc2", "Content2", LocalDateTime.now(), LocalDateTime.now());
        comment.setPost(post);
    }

    @Test
    public void testCreateComment() {
        Mockito.when(modelMapperMock.map(ArgumentMatchers.any(CommentDto.class), ArgumentMatchers.eq(Comment.class))).thenReturn(comment);
        Mockito.when(modelMapperMock.map(ArgumentMatchers.any(Comment.class), ArgumentMatchers.eq(CommentDto.class))).thenReturn(commentDto);
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(post));
        Mockito.when(commentRepositoryMock.save(ArgumentMatchers.any(Comment.class))).thenReturn(comment);

        CommentDto commentResponse = commentService.createComment(postId, commentDto);

        Assertions.assertEquals(commentDto.getId(), commentResponse.getId());
        Assertions.assertEquals(commentDto.getName(), commentResponse.getName());
        Assertions.assertEquals(commentDto.getEmail(), commentResponse.getEmail());
        Assertions.assertEquals(commentDto.getBody(), commentResponse.getBody());
    }

    @Test
    public void testGetCommentsByPostId() {
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        Mockito.when(commentRepositoryMock.findByPostId(ArgumentMatchers.anyLong())).thenReturn(comments);
        Mockito.when(modelMapperMock.map(ArgumentMatchers.any(Comment.class), ArgumentMatchers.eq(CommentDto.class))).thenReturn(commentDto);

        List<CommentDto> commentsResponse = commentService.getCommentsByPostId(postId);

        Assertions.assertEquals(comments.size(), commentsResponse.size());
        CommentDto dto = commentsResponse.get(0);
        Assertions.assertEquals(commentDto.getId(), dto.getId());
        Assertions.assertEquals(commentDto.getName(), dto.getName());
        Assertions.assertEquals(commentDto.getEmail(), dto.getEmail());
        Assertions.assertEquals(commentDto.getBody(), dto.getBody());
    }

    @Test
    public void testGetCommentById() {
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(post));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(comment));
        Mockito.when(modelMapperMock.map(ArgumentMatchers.any(Comment.class), ArgumentMatchers.eq(CommentDto.class))).thenReturn(commentDto);

        CommentDto commentResponse = commentService.getCommentById(postId, commentId);

        Assertions.assertEquals(commentDto.getId(), commentResponse.getId());
        Assertions.assertEquals(commentDto.getName(), commentResponse.getName());
        Assertions.assertEquals(commentDto.getEmail(), commentResponse.getEmail());
        Assertions.assertEquals(commentDto.getBody(), commentResponse.getBody());
    }

    @Test
    public void testGetCommentById_ResourceNotFoundException() {
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenThrow(new ResourceNotFoundException("Post", "id", 3L));

        Assertions.assertThrows(ResourceNotFoundException.class, () -> commentService.getCommentById(3L, 1L));
    }

    @Test
    public void testGetCommentById_BlogAPIException() {
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(post2));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(comment));

        Assertions.assertThrows(BlogAPIException.class, () -> commentService.getCommentById(2L, 1L));
    }

    @Test
    public void testUpdateComment() {
        String body = "New Content";
        commentDto.setBody(body);

        Comment updatedComment = new Comment(comment.getId(), comment.getName(), comment.getEmail(), body);

        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(post));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(comment));
        Mockito.when(commentRepositoryMock.save(ArgumentMatchers.any(Comment.class))).thenReturn(updatedComment);
        Mockito.when(modelMapperMock.map(ArgumentMatchers.any(Comment.class), ArgumentMatchers.eq(CommentDto.class))).thenReturn(commentDto);

        CommentDto commentResponse =  commentService.updateComment(postId, commentId, commentDto);

        Assertions.assertEquals(commentDto.getId(), commentResponse.getId());
        Assertions.assertEquals(commentDto.getName(), commentResponse.getName());
        Assertions.assertEquals(commentDto.getEmail(), commentResponse.getEmail());
        Assertions.assertEquals(commentDto.getBody(), commentResponse.getBody());
    }

    @Test
    public void testUpdateComment_ResourceNotFoundException() {
        String body = "New Content";
        commentDto.setBody(body);
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenThrow(new ResourceNotFoundException("Post", "id", 3L));

        Assertions.assertThrows(ResourceNotFoundException.class, () -> commentService.updateComment(3L, 1L, commentDto));
    }

    @Test
    public void testUpdateComment_BlogAPIException() {
        String body = "New Content";
        commentDto.setBody(body);
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(post2));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(comment));

        Assertions.assertThrows(BlogAPIException.class, () -> commentService.updateComment(2L, 1L, commentDto));
    }

    @Test
    public void testDeleteComment() {
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(post));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(comment));
        Mockito.doNothing().when(commentRepositoryMock).delete(ArgumentMatchers.any(Comment.class));

        commentService.deleteComment(postId, commentId);

        Mockito.verify(commentRepositoryMock, Mockito.times(1)).delete(ArgumentMatchers.any(Comment.class));
    }

    @Test
    public void testDeleteComment_ResourceNotFoundException() {
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenThrow(new ResourceNotFoundException("Post", "id", 3L));

        Assertions.assertThrows(ResourceNotFoundException.class, () -> commentService.deleteComment(3L, 1L));
    }

    @Test
    public void testDeleteComment_BlogAPIException() {
        Mockito.when(postRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(post2));
        Mockito.when(commentRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(comment));

        Assertions.assertThrows(BlogAPIException.class, () -> commentService.deleteComment(2L, 1L));
    }

}
