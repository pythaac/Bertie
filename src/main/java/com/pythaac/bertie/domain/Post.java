package com.pythaac.bertie.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Entity
public class Post {
    @Column(name = "num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long num;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "id")
    private String id;
    @Column(name = "time")
    private Timestamp time;

    public Post(String title, String content, String id, Timestamp time) {
        this.title = title;
        this.content = content;
        this.id = id;
        this.time = time;
    }

    public Post() {
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
