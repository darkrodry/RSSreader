package com.darkrodry.rssreader.newsviewer.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.darkrodry.rssreader.news.model.NewsItem;

import java.util.List;

public class NewsListAdapter extends BaseAdapter {

    static class ViewHolder {
        TextView title;
        TextView description;
        ImageView image;
    }

    private List<NewsItem> newsItemList;
    private LayoutInflater inflater;

    public NewsListAdapter(LayoutInflater inflater, List<NewsItem> newsItemList) {
        this.inflater = inflater;
        this.newsItemList = newsItemList;
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

        Log.d("check View", "in getView for position " + position + ", convertView is "
                + ((convertView == null) ? "null" : "being recycled"));

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = inflater.inflate(android.R.layout.simple_list_item_2, null);

            holder.title = (TextView) convertView.findViewById(android.R.id.text1);
            holder.description = (TextView) convertView.findViewById(android.R.id.text2);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NewsItem item = newsItemList.get(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getContent());

        return convertView;
    }
}
