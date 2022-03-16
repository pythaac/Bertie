package com.pythaac.bertie.service;

import com.pythaac.bertie.dto.RequestMemberJoin;
import com.pythaac.bertie.exception.IdAlreadyExistsException;
import com.pythaac.bertie.exception.PasswordNotEqualsToConfirmException;
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
class MemberServiceTest {
    private final MemberService memberService;

    @Autowired
    public MemberServiceTest(MemberRepository memberRepository) {
        this.memberService = new MemberService(memberRepository);
    }

    @Test
    public void join(){
        // given
        RequestMemberJoin request = new RequestMemberJoin("test", "123", "123");

        // when
        memberService.join(request);

        // then
    }

    @Test
    public void joinDuplicated(){
        // given
        RequestMemberJoin request1 = new RequestMemberJoin("test", "123", "123");
        RequestMemberJoin request2 = new RequestMemberJoin("test", "345", "345");

        // when
        memberService.join(request1);

        // then
        RuntimeException e = assertThrows(RuntimeException.class, () -> memberService.join(request2));
        assertThat(e).isInstanceOf(IdAlreadyExistsException.class);
    }

    @Test
    public void joinConfirmPasswordMissmatched(){
        // given
        RequestMemberJoin request = new RequestMemberJoin("test", "123", "456");

        // when


        // then
        RuntimeException e = assertThrows(RuntimeException.class, () -> memberService.join(request));
        assertThat(e).isInstanceOf(PasswordNotEqualsToConfirmException.class);
    }
}