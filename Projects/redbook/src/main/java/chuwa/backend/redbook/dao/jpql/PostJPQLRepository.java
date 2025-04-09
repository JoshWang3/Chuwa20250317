package chuwa.backend.redbook.dao.jpql;

import chuwa.backend.redbook.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: PostJPQLRepository
 * Package: chuwa.backend.redbook.dao.jpql
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/9 11:07
 * @version 1.0
 */
public interface PostJPQLRepository {
    List<Post> getAllPostWithJPQL();
}
