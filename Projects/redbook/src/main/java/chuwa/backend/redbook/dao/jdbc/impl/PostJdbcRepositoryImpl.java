package chuwa.backend.redbook.dao.jdbc.impl;

import chuwa.backend.redbook.dao.jdbc.PostJdbcRepository;
import chuwa.backend.redbook.entity.Post;
import chuwa.backend.redbook.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * ClassName: PostJdbcRepositoryImpl
 * Package: chuwa.backend.redbook.dao.jdbc
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/9 10:19
 * @version 1.0
 */
@Repository
public class PostJdbcRepositoryImpl implements PostJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostJdbcRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Post save(Post post) {
        if (post.getId() == null) {
            return insertPost(post);
        } else {
            return updatePost(post);
        }
    }

    private Post insertPost(Post post) {
        LocalDateTime now = LocalDateTime.now();
        post.setCreateDateTime(now);
        post.setUpdateDateTime(now);

        final String sql = "INSERT INTO posts (title, description, content, create_date_time, update_date_time) " +
                "VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getDescription());
            ps.setString(3, post.getContent());
            ps.setTimestamp(4, Timestamp.valueOf(post.getCreateDateTime()));
            ps.setTimestamp(5, Timestamp.valueOf(post.getUpdateDateTime()));
            return ps;
        }, keyHolder);

        post.setId(keyHolder.getKey().longValue());
        return post;
    }

    private Post updatePost(Post post) {
        post.setUpdateDateTime(LocalDateTime.now());

        final String sql = "UPDATE posts SET title = ?, description = ?, content = ?, update_date_time = ? " +
                "WHERE id = ?";

        int updated = jdbcTemplate.update(sql,
                post.getTitle(),
                post.getDescription(),
                post.getContent(),
                Timestamp.valueOf(post.getUpdateDateTime()),
                post.getId());

        if (updated == 0) {
            throw new ResourceNotFoundException("Post", "id", post.getId());
        }

        return post;
    }

    @Override
    public List<Post> findAll() {
        final String sql = "SELECT id, title, description, content, create_date_time, update_date_time FROM posts";
        return jdbcTemplate.query(sql, postRowMapper);
    }

    @Override
    public Optional<Post> findById(Long id) {
        final String sql = "SELECT id, title, description, content, create_date_time, update_date_time FROM posts WHERE id = ?";

        try {
            Post post = jdbcTemplate.queryForObject(sql, postRowMapper, id);
            return Optional.ofNullable(post);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(Post post) {
        final String sql = "DELETE FROM posts WHERE id = ?";
        jdbcTemplate.update(sql, post.getId());
    }

    private final RowMapper<Post> postRowMapper = (rs, rowNum) -> {
        Post post = new Post();
        post.setId(rs.getLong("id"));
        post.setTitle(rs.getString("title"));
        post.setDescription(rs.getString("description"));
        post.setContent(rs.getString("content"));
        post.setCreateDateTime(rs.getTimestamp("create_date_time").toLocalDateTime());
        post.setUpdateDateTime(rs.getTimestamp("update_date_time").toLocalDateTime());
        return post;
    };
}
