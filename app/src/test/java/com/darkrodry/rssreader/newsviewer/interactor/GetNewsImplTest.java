package com.darkrodry.rssreader.newsviewer.interactor;

import com.darkrodry.rssreader.BaseUnitTest;
import com.darkrodry.rssreader.news.model.NewsItem;
import com.darkrodry.rssreader.news.repository.RSSNewsRepository;
import com.darkrodry.rssreader.utils.concurrent.FakeMainThread;
import com.darkrodry.rssreader.utils.concurrent.MainThread;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;

public class GetNewsImplTest extends BaseUnitTest {

    private GetNewsImpl getNews;

    @Mock
    RSSNewsRepository repositoryMock;
    @Mock
    GetNewsImpl.Callback callbackMock;

    private MainThread mainThread;

    @Before
    public void setUp() throws Exception {
        mainThread = new FakeMainThread();
        getNews = new GetNewsImpl(repositoryMock, mainThread);
    }

    @Test
    public void testExecute() throws Exception {
        getNews.execute(callbackMock);

        verify(repositoryMock).getNewsItems();
        verify(callbackMock).onNewsReceived(anyListOf(NewsItem.class));
    }

}