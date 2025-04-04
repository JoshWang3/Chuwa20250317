package chuwa.backend.mongoblog.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * ClassName: PostVO
 * Package: chuwa.backend.mongoblog.model.vo
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/4 2:51
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class PostVO {
    private String id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;

    private String summary;

    public PostVO(String id, String title, String content, String author, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;

        this.summary = content.length() > 100 ? content.substring(0, 100) + "..." : content;
    }
}
