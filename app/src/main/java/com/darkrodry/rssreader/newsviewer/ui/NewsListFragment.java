package com.darkrodry.rssreader.newsviewer.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.darkrodry.rssreader.R;
import com.darkrodry.rssreader.news.model.NewsItem;
import com.darkrodry.rssreader.news.repository.RSSNewsRepository;
import com.darkrodry.rssreader.newsviewer.NewsActivity;
import com.darkrodry.rssreader.newsviewer.interactor.GetNewsImpl;
import com.darkrodry.rssreader.newsviewer.presenter.NewsListPresenter;
import com.darkrodry.rssreader.preferences.PreferenceManager;
import com.darkrodry.rssreader.preferences.SettingsActivity;
import com.darkrodry.rssreader.utils.concurrent.MainThreadImpl;

import java.util.List;

public class NewsListFragment extends Fragment implements NewsListPresenter.View {

    private ListView newsListView;

    private NewsListPresenter presenter;
    private NewsListAdapter newsListAdapter;

    public NewsListFragment() {
        presenter = new NewsListPresenter(this,
                new GetNewsImpl(new RSSNewsRepository(), new MainThreadImpl(new Handler())));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_newslist, container, false);

        newsListView = (ListView) rootView.findViewById(R.id.news_listview);

        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_newslist, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                presenter.onClickSettingsButton();
                return true;
            case R.id.action_refresh:
                presenter.onClickRefreshButton();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onInit();
    }

    @Override
    public void initListAdapter(List<NewsItem> newsItems) {
        newsListAdapter = new NewsListAdapter(LayoutInflater.from(this.getContext()),
                newsItems,
                this.getContext());
        newsListView.setAdapter(newsListAdapter);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onNewsListItemClick(position);
            }
        });
    }

    @Override
    public void createDetailView(NewsItem newsItem) {
        NewsActivity activity = (NewsActivity) this.getActivity();

        activity.showDetailFragment(newsItem);
    }

    @Override
    public void launchSettingsActivity() {
        startActivity(new Intent(this.getContext(), SettingsActivity.class));
    }

    @Override
    public String getFeedUrlPreference() {
        return PreferenceManager.getFeedUrl(getContext());

    }
}
