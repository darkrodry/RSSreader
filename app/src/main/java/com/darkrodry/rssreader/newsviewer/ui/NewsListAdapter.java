package com.darkrodry.rssreader.newsviewer.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.darkrodry.rssreader.R;
import com.darkrodry.rssreader.news.model.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsListAdapter extends BaseAdapter {

    static class ViewHolder {
        TextView title;
        TextView description;
        ImageView image;
    }

    private List<NewsItem> newsItemList;
    private LayoutInflater inflater;
    private Context context;

    public NewsListAdapter(LayoutInflater inflater,
                           List<NewsItem> newsItemList,
                           Context context) {
        this.inflater = inflater;
        this.newsItemList = newsItemList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return newsItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = inflater.inflate(R.layout.listview_news_item, null);

            holder.title = (TextView) convertView.findViewById(R.id.textview_title);
            holder.description = (TextView) convertView.findViewById(R.id.textview_content);
            holder.image = (ImageView) convertView.findViewById(R.id.imageview_image);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewsItem item = newsItemList.get(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getImgUrl());
        Picasso.with(context).load(item.getImgUrl())
                .into(holder.image);

        return convertView;
    }
}
