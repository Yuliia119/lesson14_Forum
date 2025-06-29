package ait.cohort60.post.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "message", columnDefinition = "TEXT")
    private String message;
    @Column(name = "date_created")
    private LocalDateTime dateCreated =  LocalDateTime.now();
    @Column(name = "likes")
    private int likes;
    @Setter
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(String user, String message) {
        this.username = user;
        this.message = message;
    }
    public void addLikes() {
        likes++;
    }
}
