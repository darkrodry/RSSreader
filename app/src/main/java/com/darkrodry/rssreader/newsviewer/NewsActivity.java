package com.darkrodry.rssreader.newsviewer;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.darkrodry.rssreader.R;
import com.darkrodry.rssreader.news.model.NewsItem;
import com.darkrodry.rssreader.newsviewer.ui.NewsDetailFragment;
import com.darkrodry.rssreader.newsviewer.ui.NewsListFragment;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            NewsListFragment newsListFragment = new NewsListFragment();

            newsListFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, newsListFragment)
                    .commit();
        }
    }

    public void showDetailFragment(NewsItem newsItem) {
        NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
        newsDetailFragment.setNewsItem(newsItem);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, newsDetailFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

}
