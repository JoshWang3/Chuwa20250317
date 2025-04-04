package chuwa.backend.mongoblog.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * ClassName: Post
 * Package: chuwa.backend.mongoblog.model.document
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/4 2:50
 * @version 1.0
 */
@Data
@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
}
