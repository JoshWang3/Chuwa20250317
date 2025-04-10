package chuwa.backend.mongoblog.Repository;
import chuwa.backend.mongoblog.Modal.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, Long> {

}
