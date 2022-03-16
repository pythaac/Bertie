package com.pythaac.bertie.dto;

public class RequestLogin {
    private String id;
    private String password;

    public RequestLogin(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public RequestLogin() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
