package com.darkrodry.rssreader.news.repository;

import android.text.Html;

import com.darkrodry.rssreader.news.model.NewsItem;
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
    
    @Override
    public List<NewsItem> getNewsItems() {
        ArrayList<NewsItem> newsItemsList = new ArrayList<>();

        try {
            InputStream inputStream = new URL("http://feeds.weblogssl.com/xataka2").openConnection().getInputStream();

            Feed feed = EarlParser.parseOrThrow(inputStream, 0);
            for (Item item : feed.getItems()) {
                newsItemsList.add(getNewsItemFromRSSItem(item));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataFormatException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        
        return newsItemsList;
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
