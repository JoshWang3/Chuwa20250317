package chuwa.backend.redbookcopy.Service.Impl;
import chuwa.backend.redbookcopy.Dao.CommentRepository;
import chuwa.backend.redbookcopy.Dao.PostRepository;
import chuwa.backend.redbookcopy.Dto.CommentDto;
import chuwa.backend.redbookcopy.Entity.Comment;
import chuwa.backend.redbookcopy.Entity.Post;
import chuwa.backend.redbookcopy.Exception.BlogAPIException;
import chuwa.backend.redbookcopy.Exception.ResourceNotFoundException;
import chuwa.backend.redbookcopy.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = DtoToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        return entityToDto(savedComment);
    }

    @Override
    public void deleteComment(Long postId, Long id) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", id));
        if(!Objects.equals(comment.getPost().getId(), post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        commentRepository.delete(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long id, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", id));
        if(!Objects.equals(comment.getPost().getId(), post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment savedComment = commentRepository.save(comment);
        return entityToDto(savedComment);
    }

    @Override
    public List<CommentDto> getComments(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getComment(Long postId, Long id) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", id));
        if(!Objects.equals(comment.getPost().getId(), post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        return entityToDto(comment);
    }

    private Comment DtoToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }

    private CommentDto entityToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());
        return commentDto;
    }
}
