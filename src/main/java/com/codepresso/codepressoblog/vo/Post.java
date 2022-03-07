package com.codepresso.codepressoblog.vo;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class Post {
    Integer id;
    Integer userId;
    String title;
    String content;
    Date createdAt;
    User user;

    public Post(Integer id, Integer userId, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public Post(Integer id, Integer userId, String title, String content, String email, String name) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.user = new User(email, name);
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
