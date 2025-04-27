package com.chuwa.redbook.service.impl;

import com.chuwa.redbook.dao.CommentRepository;
import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Comment;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.payload.CommentDto;
import org.junit.jupiter.api.*;
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
    private CommentRepository mockedCommentRepository;

    @Mock
    private PostRepository mockedPostRepository;

    @Mock
    private ModelMapper mockedModelMapper;

    @InjectMocks
    private CommentServiceImpl commentService;

    private Comment comment;
    private CommentDto commentDto;
    private Post post;

    @BeforeAll
    static void startMyTest() {
        logger.info("Comment test starts");
    }

    @AfterAll
    static void endMyTest() {
        logger.info("Comment test ends");
    }

    @BeforeEach
    void setUp() {
        logger.info("Set up comment for each test");
        this.comment = new Comment(1L, "Xiaoran", "wu.xiaorc@gmail.com", "nice post");
        this.post = new Post(1L, "test tile", "test description", "test content",
                LocalDateTime.now(), LocalDateTime.now());
        this.commentDto = new CommentDto();
        commentDto.setId(1L);
        commentDto.setName("Xiaoran");
        commentDto.setEmail("wu.xiaorc@gmail.com");
        commentDto.setBody("nice post");
        comment.setPost(post);
    }

    @Test
    void testCreateComment() {
        Mockito.when(mockedModelMapper.map(ArgumentMatchers.any(CommentDto.class), ArgumentMatchers.eq(Comment.class))).thenReturn(comment);
        Mockito.when(mockedPostRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(post));
        Mockito.when(mockedCommentRepository.save(ArgumentMatchers.any(Comment.class))).thenReturn(comment);
        Mockito.when(mockedModelMapper.map(ArgumentMatchers.any(Comment.class), ArgumentMatchers.eq(CommentDto.class))).thenReturn(commentDto);

        CommentDto commentResponse = commentService.createComment(1L, commentDto);
        Assertions.assertEquals(commentResponse.getName(), commentDto.getName());
        Assertions.assertEquals(commentResponse.getEmail(), commentDto.getEmail());
        Assertions.assertEquals(commentResponse.getBody(), commentDto.getBody());

        Mockito.verify(mockedCommentRepository, Mockito.times(1)).save(Mockito.any(Comment.class));
        Mockito.verify(mockedPostRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    void getCommentsByPostIdTest() {
        List<Comment> comments= new ArrayList<>();
        comments.add(comment);

        Mockito.when(mockedCommentRepository.findByPostId(Mockito.anyLong())).thenReturn(comments);
        Mockito.when(mockedModelMapper.map(Mockito.any(Comment.class), Mockito.eq(CommentDto.class))).thenReturn(commentDto);

        List<CommentDto> commentsResponse = commentService.getCommentsByPostId(1L);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(commentsResponse),
                () -> Assertions.assertEquals(1, commentsResponse.size()),
                () -> Assertions.assertEquals(comment.getName(), commentsResponse.get(0).getName()),
                () -> Assertions.assertEquals(comment.getEmail(), commentsResponse.get(0).getEmail()),
                () -> Assertions.assertEquals(comment.getBody(), commentsResponse.get(0).getBody())
        );

        Mockito.verify(mockedCommentRepository, Mockito.times(1)).findByPostId(Mockito.anyLong());
    }

    @Test
    void updateCommentTest() {
        Mockito.when(mockedPostRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(post));
        Mockito.when(mockedCommentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(comment));
        Mockito.when(mockedCommentRepository.save(Mockito.any(Comment.class))).thenReturn(comment);
        Mockito.when(mockedModelMapper.map(Mockito.any(Comment.class), Mockito.eq(CommentDto.class))).thenReturn(commentDto);

        CommentDto commentResponse = commentService.updateComment(1L, 1L, commentDto);

        Assertions.assertAll(
                () -> Assertions.assertEquals(commentResponse.getName(), commentDto.getName()),
                () -> Assertions.assertEquals(commentResponse.getEmail(), commentDto.getEmail()),
                () -> Assertions.assertEquals(commentResponse.getBody(), commentDto.getBody())
        );

        Mockito.verify(mockedCommentRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(mockedCommentRepository, Mockito.times(1)).save(Mockito.any(Comment.class));
        Mockito.verify(mockedPostRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    void deleteCommentTest() {
        Mockito.when(mockedPostRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(post));
        Mockito.when(mockedCommentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(comment));
        Mockito.doNothing().when(mockedCommentRepository).delete(Mockito.any(Comment.class));

        commentService.deleteComment(1L, 1L);

        Mockito.verify(mockedCommentRepository, Mockito.times(1)).delete(Mockito.any(Comment.class));
        Mockito.verify(mockedCommentRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(mockedPostRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }
}

