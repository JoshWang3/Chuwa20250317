package walmart.payment.checking.entity;


import jakarta.persistence.*;
import jdk.jfr.Enabled;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table (name="posts",uniqueConstraints ={
        @UniqueConstraint(columnNames = {"title"})
})

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false,columnDefinition = "varchar(255) default 'John Snow'")
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;

    @CreationTimestamp
    private LocalDateTime createdDataTime;

    @UpdateTimestamp
    private LocalDateTime updatedDataTime;

    public Post() {
    }


    public Post(long id, String title, String description, String content, LocalDateTime createdDataTime, LocalDateTime updatedDataTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.createdDataTime = createdDataTime;
        this.updatedDataTime = updatedDataTime;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getUpdatedDataTime() {
        return updatedDataTime;
    }

    public void setUpdatedDataTime(LocalDateTime updatedDataTime) {
        this.updatedDataTime = updatedDataTime;
    }

    public LocalDateTime getCreatedDataTime() {
        return createdDataTime;
    }

    public void setCreatedDataTime(LocalDateTime createdDataTime) {
        this.createdDataTime = createdDataTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
