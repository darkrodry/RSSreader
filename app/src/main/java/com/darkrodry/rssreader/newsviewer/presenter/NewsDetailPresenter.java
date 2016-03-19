package com.darkrodry.rssreader.newsviewer.presenter;

import com.darkrodry.rssreader.news.model.NewsItem;

public class NewsDetailPresenter {

    private View view;
    private NewsItem newsItem;

    public NewsDetailPresenter(View view) {
        this.view = view;
    }

    public void onInit() {
        view.setContent(newsItem.getContent());
        view.setTitle(newsItem.getTitle());
    }

    public void setNewsItem(NewsItem newsItem) {
        this.newsItem = newsItem;
    }

    public interface View {

        void setContent(String content);

        void setTitle(String title);

        void setImage(String imageUrl);
    }
}
