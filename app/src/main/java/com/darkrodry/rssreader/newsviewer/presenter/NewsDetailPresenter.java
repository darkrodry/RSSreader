package com.darkrodry.rssreader.newsviewer.presenter;

import android.net.Uri;

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
        view.setImage(newsItem.getImgUrl());
    }

    public void setNewsItem(NewsItem newsItem) {
        this.newsItem = newsItem;
    }

    public void onClickShowInBrowserButton() {
        view.launchViewUrlIntent(Uri.parse(newsItem.getLink()));
    }

    public interface View {

        void setContent(String content);

        void setTitle(String title);

        void setImage(String imageUrl);

        void launchViewUrlIntent(Uri url);
    }
}
