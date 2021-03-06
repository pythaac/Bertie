package com.pythaac.bertie.service;

import com.pythaac.bertie.dto.ModelLangCode;
import com.pythaac.bertie.dto.RequestNewPost;
import com.pythaac.bertie.exception.ApiFailedException;
import com.pythaac.bertie.exception.ApiInfoNotExistException;

import java.util.Collection;

public abstract class LanguageService {
    protected abstract String translate(String str, String source, String target);
    protected abstract String detectLang(String str);
    public abstract Collection<ModelLangCode> getLangCode();

    public void translatePost(RequestNewPost requestNewPost){
        if (checkValidRequest(requestNewPost))
            return;
        try{
            // title
            String source = detectLang(requestNewPost.getTitle());
            String target = requestNewPost.getLang();
            String translatedTitle = translate(requestNewPost.getTitle(), source, target);

            // content
            source = detectLang(requestNewPost.getContent());
            String translatedContent = translate(requestNewPost.getContent(), source, target);

            requestNewPost.setTitle(translatedTitle);
            requestNewPost.setContent(translatedContent);
        } catch(ApiFailedException e){
            throw new ApiFailedException();
        } catch(NullPointerException e){
            throw new NullPointerException();
        } catch(ApiInfoNotExistException e){
            throw new ApiInfoNotExistException();
        }
    }

    protected boolean checkValidRequest(RequestNewPost requestNewPost){
        if (requestNewPost.getTitle().isEmpty())
            return true;
        if (requestNewPost.getContent().isEmpty())
            return true;
        if (requestNewPost.getLang().isEmpty())
            return true;
        return false;
    }
}
