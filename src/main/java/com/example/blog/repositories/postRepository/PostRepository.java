package com.example.blog.repositories.postRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.models.post.PostModel;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostModel, UUID> {
    boolean existsByTitle(String title);
}
