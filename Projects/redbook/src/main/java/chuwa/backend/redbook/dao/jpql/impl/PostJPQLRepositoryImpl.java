package chuwa.backend.redbook.dao.jpql.impl;

import chuwa.backend.redbook.dao.jpql.PostJPQLRepository;
import chuwa.backend.redbook.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: PostJPQLRepositoryImpl
 * Package: chuwa.backend.redbook.dao.jpql.impl
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/9 11:09
 * @version 1.0
 */
@Repository
@Transactional
public class PostJPQLRepositoryImpl implements PostJPQLRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Post> getAllPostWithJPQL() {
        TypedQuery<Post> posts = entityManager.createNamedQuery("Post.getAll", Post.class);
        return posts.getResultList();
    }

    public Post insertPost(Post post) {
        return entityManager.merge(post);
    }

    public Post updatePost(Post post) {
        return entityManager.merge(post);
    }

    public Post getPostById(Long id) {
        return entityManager.find(Post.class, id);
    }

    public void deleteById(Long id) {
        Post post = entityManager.find(Post.class, id);
        entityManager.remove(post);
    }

    public <T> T insertData(T t) {
        return entityManager.merge(t);
    }
}
