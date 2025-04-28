package hw5_q10.mongoblog.repository;

import hw5_q10.mongoblog.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}