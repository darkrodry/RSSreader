package com.darkrodry.rssreader.newsviewer.interactor;

import com.darkrodry.rssreader.news.model.NewsItem;

import java.util.List;

public interface GetNews {

    void execute(Callback callback);

    interface Callback {

        void onNewsReceived(List<NewsItem> newsItems);
    }
}
