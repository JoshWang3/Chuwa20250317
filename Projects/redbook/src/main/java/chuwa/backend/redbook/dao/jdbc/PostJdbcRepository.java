package chuwa.backend.redbook.dao.jdbc;

import chuwa.backend.redbook.entity.Post;

import java.util.List;
import java.util.Optional;

/**
 * ClassName: PostJdbcRepository
 * Package: chuwa.backend.redbook.dao.jdbc
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/9 10:18
 * @version 1.0
 */
public interface PostJdbcRepository {
    Post save(Post post);
    List<Post> findAll();
    Optional<Post> findById(Long id);
    void delete(Post post);
}
