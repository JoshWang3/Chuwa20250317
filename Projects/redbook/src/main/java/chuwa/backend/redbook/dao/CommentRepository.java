package chuwa.backend.redbook.dao;

import chuwa.backend.redbook.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: CommentRepository
 * Package: chuwa.backend.redbook.dao
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/8 21:49
 * @version 1.0
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
}
