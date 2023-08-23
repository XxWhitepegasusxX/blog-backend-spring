package com.example.blog.repositories;

import com.example.blog.models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostModel, UUID> {
    boolean existsByTitle(String title);
}
