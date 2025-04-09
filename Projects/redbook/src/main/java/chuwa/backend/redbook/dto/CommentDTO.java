package chuwa.backend.redbook.dto;

/**
 * ClassName: CommentDTO
 * Package: chuwa.backend.redbook.dto
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/8 21:54
 * @version 1.0
 */
public class CommentDTO {
    private Long id;
    private String name;
    private String email;
    private String body;

    public CommentDTO() {
    }

    public CommentDTO(String name, String email, String body) {
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public CommentDTO(Long id, String name, String email, String body) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
