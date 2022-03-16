package com.pythaac.bertie.repository;

import com.pythaac.bertie.domain.Post;

import java.util.Collection;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findByNum(Long num);
    Collection<Post> findAll();
}
