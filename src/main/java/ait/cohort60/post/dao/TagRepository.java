package ait.cohort60.post.dao;


import ait.cohort60.post.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,String> {
}
