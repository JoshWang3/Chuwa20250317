package chuwa.backend.mongoblog.repository;

import chuwa.backend.mongoblog.model.document.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * ClassName: PostRepository
 * Package: chuwa.backend.mongoblog.repository
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/4 2:51
 * @version 1.0
 */
@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
