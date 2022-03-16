package com.pythaac.bertie.service;

import com.pythaac.bertie.domain.Post;
import com.pythaac.bertie.dto.RequestNewPost;
import com.pythaac.bertie.exception.PostContentIsEmptyException;
import com.pythaac.bertie.exception.PostNotExistsException;
import com.pythaac.bertie.exception.PostTitleIsEmptyException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource("classpath:application_test.properties")
class PostServiceTest {
    private final PostService postService;

    @Autowired
    public PostServiceTest(PostService postService) {
        this.postService = postService;
    }

    @Test
    public void publishAndRead(){
        // given
        RequestNewPost requestNewPost = new RequestNewPost("title", "content", "");
        Post published = postService.publish(requestNewPost, "id");

        // when
        Post read = postService.readPost(published.getNum());

        // then
        assertThat(read.getId()).isEqualTo(published.getId());
    }

    @Test
    public void readNotExists() {
        // given
        RequestNewPost requestNewPost = new RequestNewPost("title", "content", "");
        Post published = postService.publish(requestNewPost, "id");

        // when
        RuntimeException e = assertThrows(RuntimeException.class, () -> postService.readPost(published.getNum() + 1L));

        // then
        assertThat(e).isInstanceOf(PostNotExistsException.class);
    }

    @Test
    public void publishEmptyTitle(){
        // given
        RequestNewPost requestNewPost = new RequestNewPost("", "content", "");

        // when
        RuntimeException e = assertThrows(RuntimeException.class, () -> postService.publish(requestNewPost, "id"));

        // then
        assertThat(e).isInstanceOf(PostTitleIsEmptyException.class);
    }

    @Test
    public void publishEmptyContent(){
        // given
        RequestNewPost requestNewPost = new RequestNewPost("title", "", "");

        // when
        RuntimeException e = assertThrows(RuntimeException.class, () -> postService.publish(requestNewPost, "id"));

        // then
        assertThat(e).isInstanceOf(PostContentIsEmptyException.class);
    }
}