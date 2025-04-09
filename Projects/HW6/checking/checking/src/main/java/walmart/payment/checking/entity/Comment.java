package walmart.payment.checking.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    List<Post> posts;
}
