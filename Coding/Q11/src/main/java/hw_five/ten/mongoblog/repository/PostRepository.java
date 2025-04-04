package hw_five.ten.mongoblog.repository;

import hw_five.ten.mongoblog.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}

