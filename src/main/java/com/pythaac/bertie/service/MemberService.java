package com.pythaac.bertie.service;

import com.pythaac.bertie.domain.Member;
import com.pythaac.bertie.dto.RequestMemberJoin;
import com.pythaac.bertie.exception.IdAlreadyExistsException;
import com.pythaac.bertie.exception.InvalidIdException;
import com.pythaac.bertie.exception.InvalidPasswordException;
import com.pythaac.bertie.exception.PasswordNotEqualsToConfirmException;
import com.pythaac.bertie.repository.MemberRepository;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(RequestMemberJoin requestMemberJoin){
        checkIdValid(requestMemberJoin.getId());
        checkPasswordValid(requestMemberJoin.getPassword());
        checkAlreadyExsistId(requestMemberJoin.getId());
        if (!requestMemberJoin.getPassword().equals(requestMemberJoin.getConfirmPassword()))
            throw new PasswordNotEqualsToConfirmException();

        memberRepository.save(new Member(requestMemberJoin.getId(), requestMemberJoin.getPassword()));
    }

    private void checkAlreadyExsistId(String id){
        memberRepository.findById(id).ifPresent(m -> {
            throw new IdAlreadyExistsException();
        });
    }

    private void checkIdValid(String id){
        if (id.isEmpty())
            throw new InvalidIdException();
    }

    private void checkPasswordValid(String password){
        if (password.isEmpty())
            throw new InvalidPasswordException();
    }
}
