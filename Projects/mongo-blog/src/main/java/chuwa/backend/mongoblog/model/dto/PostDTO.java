package chuwa.backend.mongoblog.model.dto;

import lombok.Data;

/**
 * ClassName: PostDTO
 * Package: chuwa.backend.mongoblog.model.dto
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/4 2:51
 * @version 1.0
 */
@Data
public class PostDTO {
    private String title;
    private String content;
    private String author;
}
