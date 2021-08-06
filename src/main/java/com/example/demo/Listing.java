package com.example.demo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Listing {

    private long id;

    @NotEmpty
    @NotNull
    private String title;

    @NotEmpty
    @NotNull
    private String description;

    @NotEmpty
    @NotNull
    private String postedDate;

    @NotEmpty
    @NotNull
    private String author;

    @NotEmpty
    @NotNull
    private String phone;

    private long userId;

    public Listing() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
