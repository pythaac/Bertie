package com.pythaac.bertie.controller;

import com.pythaac.bertie.dto.RequestMemberJoin;
import com.pythaac.bertie.exception.IdAlreadyExistsException;
import com.pythaac.bertie.exception.PasswordNotEqualsToConfirmException;
import com.pythaac.bertie.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/new")
    public String createMemberForm(){
        return "createMember";
    }

    @PostMapping("/member/new")
    public String createMember(RequestMemberJoin requestMember, Model model){
        try{
            memberService.join(requestMember);
            model.addAttribute("message", "회원가입이 완료되었습니다.");
        } catch (PasswordNotEqualsToConfirmException e){
            model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
        } catch (IdAlreadyExistsException e){
            model.addAttribute("message", "이미 존재하는 아이디입니다.");
        }
        return "login";
    }
}
