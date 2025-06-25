package ait.cohort60.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewCommentDto {  // добавление коментария клиентом
    private String message;
}
