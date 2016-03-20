package com.darkrodry.rssreader.newsviewer.presenter;

import com.darkrodry.rssreader.BaseUnitTest;
import com.darkrodry.rssreader.newsviewer.interactor.GetNews;
import com.darkrodry.rssreader.newsviewer.ui.NewsListFragment;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NewsListPresenterTest extends BaseUnitTest {

    private static final String ANY_URL = "any_url";

    @Mock
    NewsListFragment viewMock;
    @Mock
    GetNews getNewsMock;

    private NewsListPresenter newsListPresenter;

    @Before
    public void setUp() throws Exception {
        newsListPresenter = new NewsListPresenter(viewMock, getNewsMock);
    }

    @Test
    public void testOnInit() throws Exception {
        when(viewMock.getFeedUrlPreference()).thenReturn(ANY_URL);
        newsListPresenter.onInit();

        verify(getNewsMock).execute(any(GetNews.Callback.class), eq(ANY_URL));
    }

}