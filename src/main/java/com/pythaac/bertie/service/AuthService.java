package com.pythaac.bertie.service;

import com.pythaac.bertie.domain.AuthInfo;
import com.pythaac.bertie.domain.Member;
import com.pythaac.bertie.dto.RequestLogin;
import com.pythaac.bertie.exception.IdNotExistsException;
import com.pythaac.bertie.exception.IdPasswordNotMatchedException;
import com.pythaac.bertie.repository.MemberRepository;

import java.util.Optional;

public class AuthService {
    private final MemberRepository memberRepository;

    public AuthService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public AuthInfo authenticate(RequestLogin requestLogin){
        Optional<Member> user = memberRepository.findById(requestLogin.getId());
        if(user.isEmpty())
            throw new IdNotExistsException();
        if(!user.get().getPassword().equals(requestLogin.getPassword()))
            throw new IdPasswordNotMatchedException();
        return new AuthInfo(requestLogin.getId());
    }
}
