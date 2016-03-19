package com.darkrodry.rssreader.news.repository;

import com.darkrodry.rssreader.news.model.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class RSSNewsRepository implements NewsRepository {
    
    @Override
    public List<NewsItem> getNewsItems() {
        ArrayList<NewsItem> newsItemsList = new ArrayList<>();

        newsItemsList.add(new NewsItem("Fake data 1", "Fake description 1", "link", "link"));
        newsItemsList.add(new NewsItem("Fake data 2", "Fake description 2", "link", "link"));
        newsItemsList.add(new NewsItem("Fake data 3", "Fake description 3", "link", "link"));
        newsItemsList.add(new NewsItem("Fake data 4", "Fake description 4", "link", "link"));
        
        return newsItemsList;
    }
}
