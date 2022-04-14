package com.pythaac.bertie.service.LangaugeServices;

import com.pythaac.bertie.dto.RequestNewPost;
import com.pythaac.bertie.repository.KakaoApiInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KakaoLanguageServiceTest {
    private final KakaoLanguageService kakaoLanguageService;

    @Autowired
    public KakaoLanguageServiceTest(KakaoApiInfoRepository kakaoApiInfoRepository) {
        this.kakaoLanguageService = new KakaoLanguageService(kakaoApiInfoRepository);
    }

    @Test
    public void translate(){
        // given
        RequestNewPost requestNewPost =
                new RequestNewPost("Title", "Content", "kr");

        // when
        kakaoLanguageService.translatePost(requestNewPost);

        // then
        System.out.println("Title -> " + requestNewPost.getTitle() + "\nContent -> " + requestNewPost.getContent());
        assertThat(requestNewPost.getTitle()).isEqualTo("제목");
        assertThat(requestNewPost.getContent()).isEqualTo("내용물");
    }
}