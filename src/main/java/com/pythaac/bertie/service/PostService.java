package com.pythaac.bertie.service;

import com.pythaac.bertie.domain.Post;
import com.pythaac.bertie.dto.RequestNewPost;
import com.pythaac.bertie.exception.PostContentIsEmptyException;
import com.pythaac.bertie.exception.PostNotExistsException;
import com.pythaac.bertie.exception.PostTitleIsEmptyException;
import com.pythaac.bertie.repository.PostRepository;
import com.pythaac.bertie.time.BertieTimeHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Optional;

public class PostService {
    private final PostRepository postRepository;
    private final BertieTimeHandler timeHandler;

    @Autowired
    public PostService(PostRepository postRepository, BertieTimeHandler timeHandler) {
        this.postRepository = postRepository;
        this.timeHandler = timeHandler;
    }

    public Collection<Post> readAllPosts(){
        return postRepository.findAll();
    }

    public Post readPost(Long num){
        Optional<Post> post = postRepository.findByNum(num);
        if (post.isEmpty())
            throw new PostNotExistsException();
        return post.get();
    }

    public Post publish(RequestNewPost requestNewPost, String id){
        if (requestNewPost.getTitle().isEmpty())
            throw new PostTitleIsEmptyException();
        if (requestNewPost.getContent().isEmpty())
            throw new PostContentIsEmptyException();
        Post post = new Post(requestNewPost.getTitle(), requestNewPost.getContent(), id, timeHandler.getStandardCurrentTime());
        return postRepository.save(post);
    }
}
