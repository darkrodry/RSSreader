package com.darkrodry.rssreader.utils.concurrent;

import android.os.Handler;

public class MainThreadImpl implements MainThread {

	private Handler handler;

	public MainThreadImpl(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void post(Runnable runnable) {
		handler.post(runnable);
	}
}
