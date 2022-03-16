package com.pythaac.bertie.dto;

public class RequestNewPost {
    private String title;
    private String content;
    private String lang;

    public RequestNewPost(String title, String content, String lang) {
        this.title = title;
        this.content = content;
        this.lang = lang;
    }

    public RequestNewPost() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
