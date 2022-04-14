package com.pythaac.bertie.service.LangaugeServices;

import com.pythaac.bertie.dto.RequestNewPost;
import com.pythaac.bertie.repository.NaverApiInfoRepository;
import com.pythaac.bertie.service.LangaugeServices.NaverLanguageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class NaverLanguageServiceTest {
    private final NaverLanguageService naverLanguageService;

    @Autowired
    public NaverLanguageServiceTest(NaverApiInfoRepository naverApiInfoRepository) {
        this.naverLanguageService = new NaverLanguageService(naverApiInfoRepository);
    }

    @Test
    void translate() {
        // given
        RequestNewPost requestNewPost =
                new RequestNewPost("Title", "Content", "ko");

        // when
        naverLanguageService.translatePost(requestNewPost);

        // then
        System.out.println("Title -> " + requestNewPost.getTitle() + "\nContent -> " + requestNewPost.getContent());
        assertThat(requestNewPost.getTitle()).isEqualTo("제목");
        assertThat(requestNewPost.getContent()).isEqualTo("컨텐트");
    }
}