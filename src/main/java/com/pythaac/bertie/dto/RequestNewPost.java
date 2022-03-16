package com.pythaac.bertie.dto;

public class RequestNewPost {
    private String title;
    private String content;

    public RequestNewPost(String title, String content) {
        this.title = title;
        this.content = content;
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
}
