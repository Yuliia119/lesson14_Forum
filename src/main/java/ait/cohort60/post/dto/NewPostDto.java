package ait.cohort60.post.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewPostDto {  // создание поста (запрос)
    private String title;
    private String content;
    private Set<String> tags;
}

