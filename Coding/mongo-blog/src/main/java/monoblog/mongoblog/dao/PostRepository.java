package monoblog.mongoblog.dao;

import monoblog.mongoblog.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
