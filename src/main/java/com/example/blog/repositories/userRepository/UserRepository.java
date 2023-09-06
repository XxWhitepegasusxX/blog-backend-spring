package com.example.blog.repositories.userRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.blog.models.user.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByEmail(String email);
    UserDetails findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
