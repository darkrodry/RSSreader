package com.darkrodry.rssreader.localstorage.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.darkrodry.rssreader.localstorage.database.SQLiteHelper;
import com.darkrodry.rssreader.localstorage.database.model.NewsItemDao;

import java.util.ArrayList;
import java.util.List;

public class NewsDataSource {

    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = {
            SQLiteHelper.COLUMN_ID,
            SQLiteHelper.COLUMN_TITLE,
            SQLiteHelper.COLUMN_CONTENT,
            SQLiteHelper.COLUMN_URLIMG,
            SQLiteHelper.COLUMN_URL };

    public NewsDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public NewsItemDao createNewsItem(String title, String content, String urlImg, String url) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_TITLE, title);
        values.put(SQLiteHelper.COLUMN_CONTENT, content);
        values.put(SQLiteHelper.COLUMN_URLIMG, urlImg);
        values.put(SQLiteHelper.COLUMN_URL, url);
        long insertId = database.insert(SQLiteHelper.TABLE_NEWS, null,
                values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_NEWS,
                allColumns, SQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        NewsItemDao newsItemDao = cursorToNewsItem(cursor);
        cursor.close();
        return newsItemDao;
    }

    public void deleteNewsItem(NewsItemDao newsItem) {
        long id = newsItem.getId();
        database.delete(SQLiteHelper.TABLE_NEWS, SQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<NewsItemDao> getAllNewsItems() {
        List<NewsItemDao> newsItemDaos = new ArrayList<NewsItemDao>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_NEWS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NewsItemDao newsItemDao = cursorToNewsItem(cursor);
            newsItemDaos.add(newsItemDao);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return newsItemDaos;
    }

    private NewsItemDao cursorToNewsItem(Cursor cursor) {
        NewsItemDao newsItemDao = new NewsItemDao();
        newsItemDao.setId(cursor.getLong(0));
        newsItemDao.setTitle(cursor.getString(1));
        newsItemDao.setContent(cursor.getString(2));
        newsItemDao.setImgUrl(cursor.getString(3));
        newsItemDao.setLink(cursor.getString(4));
        return newsItemDao;
    }
}

