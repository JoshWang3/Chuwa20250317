package chuwa.backend.redbook.dao;

import chuwa.backend.redbook.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * ClassName: PostRepository
 * Package: chuwa.backend.redbook.dao
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/4 21:19
 * @version 1.0
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    /**
     * JPQL
     * use Entity name other than database table name.
     * index Parameters
     * @return post
     */
    @Query("select p from Post p where p.id = ?1 or p.title = ?2")
    Post getPostByIDOrTitleWithJPQLIndexParameters(Long id, String title);

    /**
     * JPQL
     * use Entity name other than database table name.
     * named Parameters
     * @return post
     */
    @Query("select p from Post p where p.id = :key or p.title = :title")
    Post getPostByIDOrTitleWithJPQLNamedParameters(@Param("key") Long id,
                                                   @Param("title") String title);

    /**
     * SQL
     * use database table name.
     * index Parameters
     * @return post
     */
    @Query(value = "select * from posts p where p.id = ?1 or p.title = ?2 ORDER BY p.update_date_time", nativeQuery = true)
    Post getPostByIDOrTitleWithSQLIndexParameters(Long id, String title);

    /**
     * SQL
     * use database table name.
     * named Parameters
     * @return post
     */
    @Query(value = "select * from posts p where p.id = :key or p.title = :title", nativeQuery = true)
    Post getPostByIDOrTitleWithSQLNamedParameters(@Param("key") Long id,
                                                  @Param("title") String title);
}
