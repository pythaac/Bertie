package com.pythaac.bertie.service;

import com.pythaac.bertie.domain.AuthInfo;
import com.pythaac.bertie.domain.Member;
import com.pythaac.bertie.dto.RequestLogin;
import com.pythaac.bertie.exception.IdNotExistsException;
import com.pythaac.bertie.exception.IdPasswordNotMatchedException;
import com.pythaac.bertie.repository.MemberRepository;
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
class AuthServiceTest {
    private final AuthService authService;
    private final MemberRepository memberRepository;

    @Autowired
    public AuthServiceTest(AuthService authService, MemberRepository memberRepository) {
        this.authService = authService;
        this.memberRepository = memberRepository;
    }

    @Test
    public void authenticate(){
        // given
        memberRepository.save(new Member("test", "123"));
        RequestLogin requestLogin = new RequestLogin("test", "123");

        // when
        AuthInfo authInfo = authService.authenticate(requestLogin);

        // then
        assertThat(authInfo.getId()).isEqualTo(new AuthInfo("test").getId());
    }

    @Test
    public void authenticateIdNotExist(){
        // given
        memberRepository.save(new Member("test", "123"));
        RequestLogin requestLogin = new RequestLogin("test1", "123");

        // when
        RuntimeException e = assertThrows(RuntimeException.class, () -> authService.authenticate(requestLogin));

        // then
        assertThat(e).isInstanceOf(IdNotExistsException.class);
    }

    @Test
    public void authenticatePasswordMissmatched(){
        // given
        memberRepository.save(new Member("test", "123"));
        RequestLogin requestLogin = new RequestLogin("test", "456");

        // when
        RuntimeException e = assertThrows(RuntimeException.class, () -> authService.authenticate(requestLogin));

        // then
        assertThat(e).isInstanceOf(IdPasswordNotMatchedException.class);
    }
}