package chuwa.backend.redbook.dao;

import chuwa.backend.redbook.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findById(long id);
    String findByBody(String body);
    List<Comment> findByPostId(long postId);
}