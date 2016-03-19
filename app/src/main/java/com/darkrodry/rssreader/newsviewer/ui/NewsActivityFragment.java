package com.darkrodry.rssreader.newsviewer.ui;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.darkrodry.rssreader.R;
import com.darkrodry.rssreader.news.model.NewsItem;
import com.darkrodry.rssreader.news.repository.RSSNewsRepository;
import com.darkrodry.rssreader.newsviewer.interactor.GetNewsImpl;
import com.darkrodry.rssreader.newsviewer.presenter.NewsListPresenter;
import com.darkrodry.rssreader.utils.concurrent.MainThreadImpl;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewsActivityFragment extends Fragment implements NewsListPresenter.View {

    private ListView newsListView;

    private NewsListPresenter presenter;
    private NewsListAdapter newsListAdapter;

    public NewsActivityFragment() {
        presenter = new NewsListPresenter(this,
                new GetNewsImpl(new RSSNewsRepository(), new MainThreadImpl(new Handler())));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        newsListView = (ListView) rootView.findViewById(R.id.news_listview);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onInit();
    }

    @Override
    public void initListAdapter(List<NewsItem> newsItems) {
        newsListAdapter = new NewsListAdapter(LayoutInflater.from(this.getContext()), newsItems);
        newsListView.setAdapter(newsListAdapter);
    }
}
