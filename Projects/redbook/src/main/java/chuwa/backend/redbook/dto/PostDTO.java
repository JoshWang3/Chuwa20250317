package chuwa.backend.redbook.dto;

/**
 * ClassName: PostDTO
 * Package: chuwa.backend.redbook.dto
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/2 22:46
 * @version 1.0
 */
public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private String content;

    public PostDTO() {
    }

    public PostDTO(Long id, String title, String description, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
