package com.pythaac.bertie.controller;

import com.pythaac.bertie.domain.AuthInfo;
import com.pythaac.bertie.dto.RequestLogin;
import com.pythaac.bertie.exception.IdNotExistsException;
import com.pythaac.bertie.exception.IdPasswordNotMatchedException;
import com.pythaac.bertie.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String main(HttpSession httpSession){
        AuthInfo authInfo = (AuthInfo)httpSession.getAttribute("authInfo");
        if(authInfo == null)
            return "login";
        return "redirect:/home";
    }

    @PostMapping("/")
    public String login(RequestLogin requestLogin, Model model, HttpSession httpSession){
        try{
            AuthInfo authInfo = authService.authenticate(requestLogin);
            httpSession.setAttribute("authInfo", authInfo);
        } catch (IdNotExistsException e){
            model.addAttribute("message", "아이디를 확인하세요.");
            return "login";
        } catch (IdPasswordNotMatchedException e){
            model.addAttribute("message", "비밀번호를 확인하세요.");
            return "login";
        }
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "login";
    }
}
