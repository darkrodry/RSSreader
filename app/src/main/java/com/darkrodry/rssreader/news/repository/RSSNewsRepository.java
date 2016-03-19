package com.darkrodry.rssreader.news.repository;

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
            InputStream inputStream = new URL("http://www.xatakandroid.com/tag/feeds/rss2.xml").openConnection().getInputStream();

            Feed feed = EarlParser.parseOrThrow(inputStream, 0);
            for (Item item : feed.getItems()) {
                newsItemsList.add(new NewsItem(item.getTitle(),
                        item.getDescription(),
                        item.getImageLink(),
                        item.getLink()));
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
}
