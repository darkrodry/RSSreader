package com.darkrodry.rssreader.newsviewer.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.darkrodry.rssreader.R;
import com.darkrodry.rssreader.news.model.NewsItem;
import com.darkrodry.rssreader.newsviewer.presenter.NewsDetailPresenter;
import com.squareup.picasso.Picasso;

public class NewsDetailFragment extends Fragment implements NewsDetailPresenter.View {

    private NewsDetailPresenter presenter;

    private TextView contentTextView;
    private TextView titleTextView;
    private ImageView imageView;

    public NewsDetailFragment() {
        presenter = new NewsDetailPresenter(this);
    }

    public void setNewsItem(NewsItem newsItem) {
        presenter.setNewsItem(newsItem);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_newsdetail, container, false);

        contentTextView = (TextView) rootView.findViewById(R.id.textview_content);
        titleTextView = (TextView) rootView.findViewById(R.id.textview_title);
        imageView = (ImageView) rootView.findViewById(R.id.imageview_image);

        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_newsdetail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_show_in_browser:
                presenter.onClickShowInBrowserButton();
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
    public void setContent(String content) {
        contentTextView.setText(content);
    }

    @Override
    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    @Override
    public void setImage(String imageUrl) {
        Picasso.with(getContext()).load(imageUrl)
                .into(imageView);
    }

    @Override
    public void launchViewUrlIntent(Uri url) {
        Intent i = new Intent(Intent.ACTION_VIEW, url);
        startActivity(i);
    }
}
