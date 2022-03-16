package com.pythaac.bertie.controller;

import com.pythaac.bertie.domain.AuthInfo;
import com.pythaac.bertie.domain.Post;
import com.pythaac.bertie.dto.RequestNewPost;
import com.pythaac.bertie.exception.ApiFailedException;
import com.pythaac.bertie.exception.PostContentIsEmptyException;
import com.pythaac.bertie.exception.PostNotExistsException;
import com.pythaac.bertie.exception.PostTitleIsEmptyException;
import com.pythaac.bertie.service.LanguageService;
import com.pythaac.bertie.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PostController {
    private final PostService postService;
    private final LanguageService languageService;

    @Autowired
    public PostController(PostService postService, LanguageService languageService) {
        this.postService = postService;
        this.languageService = languageService;
    }

    @GetMapping("/home")
    public String home(HttpSession httpSession, Model model){
        AuthInfo authInfo = (AuthInfo)httpSession.getAttribute("authInfo");
        if(authInfo == null){
            model.addAttribute("message", "세션이 만료되었습니다.");
            return "login";
        }
        model.addAttribute("posts", postService.readAllPosts());
        return "home";
    }

    @GetMapping("/post/new")
    public String newPost(HttpSession httpSession, Model model){
        AuthInfo authInfo = (AuthInfo)httpSession.getAttribute("authInfo");
        if(authInfo == null){
            model.addAttribute("message", "세션이 만료되었습니다.");
            return "login";
        }
        return "createPost";
    }

    @PostMapping("/post/new")
    public String publishPost(RequestNewPost requestNewPost, HttpSession httpSession, Model model){
        AuthInfo authInfo = (AuthInfo)httpSession.getAttribute("authInfo");
        if(authInfo == null){
            model.addAttribute("message", "세션이 만료되었습니다.");
            return "login";
        }
        try {
            languageService.translate(requestNewPost);
            postService.publish(requestNewPost, authInfo.getId());
        } catch(PostTitleIsEmptyException | PostContentIsEmptyException e){
            return "redirect:/home";
        } catch(ApiFailedException | NullPointerException e){
            return "redirect:/home";
        }
        return "redirect:/home";
    }

    @GetMapping("/post/read/{num}")
    public String readPost(@PathVariable("num") Long num, HttpSession httpSession, Model model){
        AuthInfo authInfo = (AuthInfo)httpSession.getAttribute("authInfo");
        if(authInfo == null){
            model.addAttribute("message", "세션이 만료되었습니다.");
            return "login";
        }

        try{
            Post post = postService.readPost(num);
            model.addAttribute("post", post);
        } catch (PostNotExistsException e){
            return "redirect:/home";
        }
        return "/readPost";
    }
}
