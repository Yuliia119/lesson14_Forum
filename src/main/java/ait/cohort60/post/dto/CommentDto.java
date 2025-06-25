package ait.cohort60.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CommentDto {   // ответ на добавление коментария
    private String user;
    private String message;
    private LocalDateTime dateCreated;
    private Long likes;
}
