package com.pythaac.bertie;

import com.pythaac.bertie.repository.MemberRepository;
import com.pythaac.bertie.repository.PostRepository;
import com.pythaac.bertie.service.AuthService;
import com.pythaac.bertie.service.MemberService;
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

    @Autowired
    public SpringConfig(MemberRepository memberRepository, PostRepository postRepository) {
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    @Bean
    public AuthService authService(){
        return new AuthService(memberRepository);
    }

    @Bean
    public PostService postService() { return new PostService(postRepository, timeHandler()); }

    @Bean
    public BertieTimeHandler timeHandler() { return new Seoul(); }
}
