package com.darkrodry.rssreader.newsviewer.interactor;

import com.darkrodry.rssreader.news.model.NewsItem;
import com.darkrodry.rssreader.news.repository.RSSNewsRepository;

import java.util.List;

public class GetNewsImpl implements GetNews {

    private final RSSNewsRepository rssNewsRepository;

    private Callback callback;

    public GetNewsImpl(RSSNewsRepository tripRepository) {
        this.rssNewsRepository = tripRepository;
    }

    @Override
    public void execute(final Callback callback) {
        this.callback = callback;

        List<NewsItem> newsItems = rssNewsRepository.getNewsItems();

        GetNewsImpl.this.callback.onNewsReceived(newsItems);
    }
}
