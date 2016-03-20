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
        refreshNews();
    }

    private String getFeedUrl(){
        return view.getFeedUrlPreference();
    }

    public void onNewsListItemClick(int position) {
        view.createDetailView(newsItemList.get(position));
    }

    public void onClickSettingsButton() {
        view.launchSettingsActivity();
    }

    public void onClickRefreshButton() {
        refreshNews();
    }

    private void refreshNews() {
        getNews.execute(new GetNews.Callback() {
            @Override
            public void onNewsReceived(List<NewsItem> newsItems) {
                newsItemList = newsItems;
                view.initListAdapter(newsItems);
            }
        }, getFeedUrl());
    }

    public interface View {

        void initListAdapter(List<NewsItem> newsItems);

        void createDetailView(NewsItem newsItem);

        void launchSettingsActivity();

        String getFeedUrlPreference();
    }
}
