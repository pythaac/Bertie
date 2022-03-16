package com.pythaac.bertie.repository;

import com.pythaac.bertie.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPostRepository extends JpaRepository<Post, Long>, PostRepository {
    Optional<Post> findByNum(Long num);
}
