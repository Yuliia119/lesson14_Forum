package ait.cohort60.post.dao;

import ait.cohort60.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
