package com.darkrodry.rssreader.news.repository;

import android.text.Html;

import com.darkrodry.rssreader.news.model.NewsItem;
import com.darkrodry.rssreader.utils.NetworkUtils;
import com.einmalfel.earl.EarlParser;
import com.einmalfel.earl.Feed;
import com.einmalfel.earl.Item;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class RSSNewsRepository implements NewsRepository {

    private static RSSNewsRepository singleton = new RSSNewsRepository();

    public static RSSNewsRepository getInstance() {
        return singleton;
    }

    private RSSNewsRepository() {
    }

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    public List<NewsItem> getNewsItems() {
        if (NetworkUtils.isOnline()) {
            ArrayList<NewsItem> newsItemsList = new ArrayList<>();

            try {
                InputStream inputStream = new URL(url).openConnection().getInputStream();

                Feed feed = EarlParser.parseOrThrow(inputStream, 0);
                for (Item item : feed.getItems()) {
                    newsItemsList.add(getNewsItemFromRSSItem(item));
                }

            } catch (IOException | DataFormatException | XmlPullParserException e) {
                e.printStackTrace();
            }

            return newsItemsList;
        } else {
            return DatabaseNewsRepository.getInstance().getNewsItems();
        }
    }

    private NewsItem getNewsItemFromRSSItem(Item rssItem) {
        String description = stripHtml(rssItem.getDescription());

        String imgUrl = rssItem.getImageLink();
        return new NewsItem(rssItem.getTitle(),
                description,
                imgUrl,
                rssItem.getLink());
    }

    private String stripHtml(String htmlString) {
        return Html.fromHtml(htmlString).toString()
                .replace('\n', (char) 32)
                .replace((char) 160, (char) 32)
                .replace((char) 65532, (char) 32)
                .trim();
    }
}
