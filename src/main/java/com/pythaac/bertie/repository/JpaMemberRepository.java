package com.pythaac.bertie.repository;

import com.pythaac.bertie.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMemberRepository extends MemberRepository, JpaRepository<Member, String> {
    Optional<Member> findById(String id);
}
