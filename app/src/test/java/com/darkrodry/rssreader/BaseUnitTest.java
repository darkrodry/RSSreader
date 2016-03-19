package com.darkrodry.rssreader;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public abstract class BaseUnitTest {

	@Before
	public void initMocks() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
}
