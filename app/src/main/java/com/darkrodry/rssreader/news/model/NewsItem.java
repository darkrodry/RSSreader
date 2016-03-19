package com.darkrodry.rssreader.news.model;

public class NewsItem {

    private String title;
    private String content;
    private String imgUrl;
    private String link;

    public NewsItem(String title, String content, String imgUrl, String link) {
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.link = link;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
