package ait.cohort60.post.service;

import ait.cohort60.post.dao.CommentRepository;
import ait.cohort60.post.dao.PostRepository;
import ait.cohort60.post.dao.TagRepository;
import ait.cohort60.post.dto.NewCommentDto;
import ait.cohort60.post.dto.NewPostDto;
import ait.cohort60.post.dto.PostDto;
import ait.cohort60.post.dto.exceptions.PostNotFoundException;
import ait.cohort60.post.model.Comment;
import ait.cohort60.post.model.Post;
import ait.cohort60.post.model.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public PostDto addNewPost(String author, NewPostDto newPostDto) {
        Post post = new Post(newPostDto.getTitle(),newPostDto.getContent(),author);
        Set<String> tags = newPostDto.getTags();
        if (tags != null) {
            for (String tagName : tags) {
                Tag tag = tagRepository.findById(tagName).orElseGet(() -> tagRepository.save(new Tag(tagName)));
                post.addTag(tag);
            }
        }
        post = postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    @Transactional
    public void addLikes(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        post.addLikes();
    }

    @Override
    public PostDto updatePost(Long id, NewPostDto newPostDto) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        post.setTitle(newPostDto.getTitle());
        post.setContent(newPostDto.getContent());
        post.getTags().clear();
        if (newPostDto.getTags() != null) {
            for (String tagName : newPostDto.getTags()) {
                Tag tag = tagRepository.findById(tagName).orElseGet(() -> tagRepository.save(new Tag(tagName)));
                post.addTag(tag);
            }
        }
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    @Transactional
    public PostDto deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        postRepository.delete(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto addComment(Long id, String author, NewCommentDto newCommentDto) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        Comment comment = new Comment(author, newCommentDto.getMessage());
        comment.setPost(post);
        commentRepository.save(comment);
        return modelMapper.map(comment, PostDto.class);
    }

    @Override
    public Iterable<PostDto> findPostsByAuthor(String author) {
        return null;
    }

    @Override
    public Iterable<PostDto> findPostsByTags(List<String> tags) {
        return null;
    }

    @Override
    public Iterable<PostDto> findPostsByPeriod(LocalDateTime start, LocalDateTime end) {
        return null;
    }
}
