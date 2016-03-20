package com.darkrodry.rssreader.newsviewer.interactor;

import com.darkrodry.rssreader.news.model.NewsItem;
import com.darkrodry.rssreader.news.repository.RSSNewsRepository;
import com.darkrodry.rssreader.utils.concurrent.MainThread;

import java.util.List;

public class GetNewsImpl implements GetNews {

    private final RSSNewsRepository rssNewsRepository;
    private final MainThread mainThread;

    private Callback callback;

    public GetNewsImpl(RSSNewsRepository tripRepository, MainThread mainThread) {
        this.rssNewsRepository = tripRepository;
        this.mainThread = mainThread;
    }

    @Override
    public void execute(final Callback callback, String url) {
        this.callback = callback;
        rssNewsRepository.setUrl(url);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<NewsItem> newsItems = rssNewsRepository.getNewsItems();

                mainThread.post(new Runnable() {
                    public void run() {
                        GetNewsImpl.this.callback.onNewsReceived(newsItems);
                    }
                });
            }
        }).start();
    }
}
