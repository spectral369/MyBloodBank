package com.bloodbank.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.vaadin.annotations.PreserveOnRefresh;

@PreserveOnRefresh
public class SimpleExecutor {

	public ScheduledExecutorService sc;
	private static SimpleExecutor exec = null;

	public static SimpleExecutor getInstance() {
		if (exec == null) {
			exec = new SimpleExecutor();
		}
		return exec;
	}

	public SimpleExecutor() {
		sc = Executors.newSingleThreadScheduledExecutor();
	}

	public ScheduledExecutorService getSES() {
		return sc;
	}

	public void destroy() {
		sc.shutdownNow();
	}

}
