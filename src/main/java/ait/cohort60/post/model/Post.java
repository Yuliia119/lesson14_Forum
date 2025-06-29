package ait.cohort60.post.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Setter
    @Column(name = "title")
    private String title;
    @Setter
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @Setter
    @Column(name = "author")
    private String author;
    @Setter
    @Column(name = "date_created")
    private LocalDateTime dateCreated =  LocalDateTime.now();
    @ManyToMany
    @JoinTable(
            name = "posts_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_name")
    )
    private Set<Tag> tags = new HashSet<>();
    @Column(name = "likes")
    private int likes;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public Post(String title, String content, String author, Set<String> tags) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.tags.addAll(tags.stream().map(Tag::new).toList());
    }
    public void addComment(Comment comment) {
        comments.add(comment);
    }
    public void addLikes() {
        likes++;
    }
    public boolean addTag(Tag tag) {
        return tags.add(tag);
    }
}
