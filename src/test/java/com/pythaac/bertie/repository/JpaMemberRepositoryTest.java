package com.pythaac.bertie.repository;

import com.pythaac.bertie.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource("classpath:application_test.properties")
class JpaMemberRepositoryTest {
    @Autowired private MemberRepository memberRepository;

    @Test
    public void save(){
        // given
        Member member = new Member("test", "test");

        // when
        memberRepository.save(member);

        // then
        assertThat(memberRepository.findById("test").isEmpty()).isFalse();
        Member saved = memberRepository.findById("test").get();
        assertThat(saved.getId()).isEqualTo(member.getId());
        assertThat(saved.getPassword()).isEqualTo(member.getPassword());
    }
}