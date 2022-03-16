package com.pythaac.bertie.repository;

import com.pythaac.bertie.domain.Post;
import com.pythaac.bertie.time.BertieTimeHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource("classpath:application_test.properties")
class JpaPostRepositoryTest {
    private final PostRepository postRepository;
    private final BertieTimeHandler timeHandler;

    @Autowired
    public JpaPostRepositoryTest(PostRepository postRepository, BertieTimeHandler timeHandler) {
        this.postRepository = postRepository;
        this.timeHandler = timeHandler;
    }

    @Test
    public void save(){
        // given
        Post post = new Post("title", "content", "id", timeHandler.getStandardCurrentTime());

        // when
        Post saved = postRepository.save(post);
        Optional<Post> read = postRepository.findByNum(saved.getNum());

        // then
        assertThat(read.isEmpty()).isFalse();
        assertThat(post.getId()).isEqualTo(read.get().getId());
    }

    @Test
    public void saveDuplicate(){
        // given
        Post post1 = new Post("title", "content", "id", timeHandler.getStandardCurrentTime());
        Post post2 = new Post("title", "content", "id", timeHandler.getStandardCurrentTime());

        // when
        Post saved1 = postRepository.save(post1);
        Post saved2 = postRepository.save(post2);
        Optional<Post> read1 = postRepository.findByNum(saved1.getNum());
        Optional<Post> read2 = postRepository.findByNum(saved2.getNum());

        // then
        assertThat(read1.isEmpty()).isFalse();
        assertThat(read2.isEmpty()).isFalse();
        assertThat(post1.getId()).isEqualTo(read1.get().getId());
        assertThat(post2.getId()).isEqualTo(read2.get().getId());
    }

    @Test
    public void getAllPosts(){
        // given
        postRepository.save(new Post("title1", "conent1", "id1", timeHandler.getStandardCurrentTime()));
        postRepository.save(new Post("title2", "conent2", "id2", timeHandler.getStandardCurrentTime()));
        postRepository.save(new Post("title3", "conent3", "id3", timeHandler.getStandardCurrentTime()));

        // when
        Collection<Post> posts = postRepository.findAll();

        // then
        assertThat(posts.size()).isEqualTo(3);
    }

    @Test
    public void getPost(){
        // given
        Post saved1 = postRepository.save(new Post("title1", "conent1", "id1", timeHandler.getStandardCurrentTime()));
        Post saved2 = postRepository.save(new Post("title2", "conent2", "id2", timeHandler.getStandardCurrentTime()));
        Post saved3 = postRepository.save(new Post("title3", "conent3", "id3", timeHandler.getStandardCurrentTime()));

        // when
        Optional<Post> read1 = postRepository.findByNum(saved1.getNum());
        Optional<Post> read2 = postRepository.findByNum(saved2.getNum());
        Optional<Post> read3 = postRepository.findByNum(saved3.getNum());

        // then
        assertThat(read1.isEmpty()).isFalse();
        assertThat(read2.isEmpty()).isFalse();
        assertThat(read3.isEmpty()).isFalse();
        assertThat(read1.get().getId()).isEqualTo(saved1.getId());
        assertThat(read2.get().getId()).isEqualTo(saved2.getId());
        assertThat(read3.get().getId()).isEqualTo(saved3.getId());
    }
}