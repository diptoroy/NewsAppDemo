package com.atc.newsappfinalproject.Model;

public class HeadlineModel {

    String title;
    String author;
    String description;
    String url;
    String publishedAt;

    public HeadlineModel() {
    }

    public HeadlineModel(String title, String author, String description, String url, String publishedAt) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.url = url;
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
