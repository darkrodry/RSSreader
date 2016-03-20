package com.darkrodry.rssreader.localstorage.database.model;

import com.darkrodry.rssreader.news.model.NewsItem;

public class NewsItemDao {

    private long id;
    private String title;
    private String content;
    private String imgUrl;
    private String link;

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

    public NewsItem mapToNewsItem() {
        return new NewsItem(title, content, imgUrl, link);
    }
}
