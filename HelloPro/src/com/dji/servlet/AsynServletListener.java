package com.dji.servlet;

import java.io.IOException;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

public class AsynServletListener implements AsyncListener{

	@Override
	public void onComplete(AsyncEvent arg0) throws IOException {
		System.out.println("listener onComplete");
	}

	@Override
	public void onError(AsyncEvent arg0) throws IOException {
		System.out.println("listener onError");
	}

	@Override
	public void onStartAsync(AsyncEvent arg0) throws IOException {
		System.out.println("listener onStartAsync");
	}

	@Override
	public void onTimeout(AsyncEvent arg0) throws IOException {
		System.out.println("listener onTimeout");
	}

}
