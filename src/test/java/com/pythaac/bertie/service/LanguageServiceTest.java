package com.pythaac.bertie.service;

import com.pythaac.bertie.dto.RequestNewPost;
import com.pythaac.bertie.repository.NaverApiInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class LanguageServiceTest {
    private final LanguageService languageService;

    @Autowired
    public LanguageServiceTest(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Test
    void translate() {
        // given
        RequestNewPost requestNewPost =
                new RequestNewPost("Title", "Content", "ko");

        // when
        languageService.translate(requestNewPost);

        // then
        System.out.println("Title -> " + requestNewPost.getTitle() + "\nContent -> " + requestNewPost.getContent());
        assertThat(requestNewPost.getTitle()).isEqualTo("제목");
        assertThat(requestNewPost.getContent()).isEqualTo("컨텐트");
    }
}