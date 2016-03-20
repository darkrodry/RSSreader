package com.darkrodry.rssreader.newsviewer.interactor;

import android.content.Context;

import com.darkrodry.rssreader.localstorage.database.dao.NewsDataSource;
import com.darkrodry.rssreader.news.model.NewsItem;
import com.darkrodry.rssreader.news.repository.DatabaseNewsRepository;
import com.darkrodry.rssreader.news.repository.NewsRepository;
import com.darkrodry.rssreader.news.repository.RSSNewsRepository;
import com.darkrodry.rssreader.utils.concurrent.MainThread;

import java.util.List;

public class GetNewsImpl implements GetNews {

    private final NewsRepository newsRepository;
    private final MainThread mainThread;
    private final Context context;

    private Callback callback;

    public GetNewsImpl(NewsRepository newsRepository,
                       MainThread mainThread,
                       Context context) {
        this.newsRepository = newsRepository;
        this.mainThread = mainThread;
        this.context = context;
    }

    @Override
    public void execute(final Callback callback, String url) {
        this.callback = callback;
        com.darkrodry.rssreader.news.repository.RSSNewsRepository.getInstance().setUrl(url);
        DatabaseNewsRepository.getInstance().setDataSource(new NewsDataSource(context));

        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<NewsItem> newsItems = newsRepository.getNewsItems();

                mainThread.post(new Runnable() {
                    public void run() {
                        GetNewsImpl.this.callback.onNewsReceived(newsItems);
                    }
                });
            }
        }).start();
    }
}
