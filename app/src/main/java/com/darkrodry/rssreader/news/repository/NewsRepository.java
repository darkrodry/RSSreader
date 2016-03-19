package com.darkrodry.rssreader.news.repository;

import com.darkrodry.rssreader.news.model.NewsItem;

import java.util.List;

public interface NewsRepository {

    List<NewsItem> getNewsItems();
}
