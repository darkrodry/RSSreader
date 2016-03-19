package com.darkrodry.rssreader.utils.concurrent;

/**
 * Only for testing purposes
 */
public class FakeMainThread implements MainThread {

	@Override
	public void post(Runnable runnable) {
		runnable.run();
	}
}
