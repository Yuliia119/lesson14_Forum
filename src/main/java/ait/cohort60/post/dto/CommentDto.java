package ait.cohort60.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("user")
    private String username;
    private String message;
    private LocalDateTime dateCreated;
    private Long likes;
}
