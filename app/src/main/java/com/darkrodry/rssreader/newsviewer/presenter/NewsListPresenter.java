package com.darkrodry.rssreader.newsviewer.presenter;

import com.darkrodry.rssreader.news.model.NewsItem;
import com.darkrodry.rssreader.newsviewer.interactor.GetNews;

import java.util.List;

public class NewsListPresenter {

    private View view;
    private List<NewsItem> newsItemList;
    private GetNews getNews;

    public NewsListPresenter(View view, GetNews getNews) {
        this.view = view;
        this.getNews = getNews;
    }

    public void onInit() {
        getNews.execute(new GetNews.Callback() {
            @Override
            public void onNewsReceived(List<NewsItem> newsItems) {
                view.initListAdapter(newsItems);
            }
        });
    }

    public interface View {

        void initListAdapter(List<NewsItem> newsItems);

    }
}
