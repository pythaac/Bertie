package com.pythaac.bertie;

import com.pythaac.bertie.repository.MemberRepository;
import com.pythaac.bertie.repository.NaverApiInfoRepository;
import com.pythaac.bertie.repository.PostRepository;
import com.pythaac.bertie.service.LanguageService;
import com.pythaac.bertie.service.NaverLanguageService;
import com.pythaac.bertie.service.PostService;
import com.pythaac.bertie.time.BertieTimeHandler;
import com.pythaac.bertie.time.Seoul;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final NaverApiInfoRepository apiInfoRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository, PostRepository postRepository, NaverApiInfoRepository apiInfoRepository) {
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
        this.apiInfoRepository = apiInfoRepository;
    }

    @Bean
    public PostService postService() { return new PostService(postRepository, timeHandler()); }

    @Bean
    public BertieTimeHandler timeHandler() { return new Seoul(); }

    @Bean
    public LanguageService languageService() { return new NaverLanguageService(apiInfoRepository); }
}
