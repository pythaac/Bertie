package com.pythaac.bertie.repository;

import com.pythaac.bertie.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(String id);
}
