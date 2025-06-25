package ait.cohort60.post.service;

import ait.cohort60.post.dto.CommentDto;
import ait.cohort60.post.dto.NewCommentDto;
import ait.cohort60.post.dto.NewPostDto;
import ait.cohort60.post.dto.PostDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface PostService {
    PostDto addNewPost(String author, NewPostDto newPostDto);  // добавляет новый пост

    PostDto findPostById(Long id);  // ищет пост по его Id

    void addLikes(Long id);  // добавляет лайки у поста

    PostDto updatePost (Long id, NewPostDto newPostDto);  // обновляет данные у поста

    PostDto deletePost(Long id);  // удаляет пост по Id

    PostDto addComment(Long id, String author, NewCommentDto newCommentDto); // добавляет новый коммент к посту

    Iterable<PostDto> findPostsByAuthor(String author); // находит посты конкретного автора

    Iterable<PostDto> findPostsByTags(List<String> tags); // находит посты с конкретными тегами

    Iterable<PostDto> findPostsByPeriod (LocalDateTime start, LocalDateTime end);  // находит посты, созданные в определ. период

}
