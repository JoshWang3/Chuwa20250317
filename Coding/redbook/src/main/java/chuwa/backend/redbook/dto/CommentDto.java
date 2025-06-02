package chuwa.backend.redbook.dto;

public class CommentDto {
    private Long id;
    private String content;
    private Long postId;

    public CommentDto() {
    }

    public CommentDto(Long id, String content, Long postId) {
        this.id = id;
        this.content = content;
        this.postId = postId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
