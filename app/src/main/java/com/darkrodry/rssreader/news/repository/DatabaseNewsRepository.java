package com.darkrodry.rssreader.news.repository;

import com.darkrodry.rssreader.localstorage.database.dao.NewsDataSource;
import com.darkrodry.rssreader.localstorage.database.model.NewsItemDao;
import com.darkrodry.rssreader.news.model.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class DatabaseNewsRepository implements NewsRepository {

    private static DatabaseNewsRepository singleton = new DatabaseNewsRepository();

    public static DatabaseNewsRepository getInstance() {
        return singleton;
    }

    private NewsDataSource newsDataSource;

    private DatabaseNewsRepository() {
    }

    @Override
    public List<NewsItem> getNewsItems() {
        newsDataSource.open();
        List<NewsItem> newsItems = new ArrayList<>();
        for (NewsItemDao newsItemDao : newsDataSource.getAllNewsItems()) {
            newsItems.add(newsItemDao.mapToNewsItem());
        }
        newsDataSource.close();
        return newsItems;
    }

    public void updateNewsItems(List<NewsItem> newsItems) {
        newsDataSource.open();
        for (NewsItem item : newsItems) {
            newsDataSource.createNewsItem(item.getTitle(),
                    item.getContent(),
                    item.getImgUrl(),
                    item.getLink());
        }
        newsDataSource.close();
    }

    public void setDataSource(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }
}
