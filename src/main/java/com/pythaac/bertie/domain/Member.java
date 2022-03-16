package com.pythaac.bertie.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {
    @Id
    @Column(name="id")
    String id;
    @Column(name="password")
    String password;

    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public Member() {
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
