package com.example.demo;


import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureTask;

public class CustomizeThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
	
	private final static Logger LOG = LoggerFactory.getLogger(CustomizeThreadPoolTaskExecutor.class);


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private void traceThreadPool() {
		ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();

	        if(null==threadPoolExecutor){
	            return;
	        }

	        LOG.info("======{} -taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]",
	                this.getThreadNamePrefix(),
	                threadPoolExecutor.getTaskCount(),
	                threadPoolExecutor.getCompletedTaskCount(),
	                threadPoolExecutor.getActiveCount(),
	                threadPoolExecutor.getQueue().size());
	}

	public CustomizeThreadPoolTaskExecutor() {
		super();
	}
	
	@Override
	public void execute(Runnable task) {
		traceThreadPool();
		super.execute(task);
	}

	@Override
	public Future<?> submit(Runnable task) {
		traceThreadPool();
		return super.submit(task);
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		traceThreadPool();
		return super.submit(task);
	}

	@Override
	public ListenableFuture<?> submitListenable(Runnable task) {
		traceThreadPool();
		return super.submitListenable(task);
	}

	@Override
	public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
		traceThreadPool();
		return super.submitListenable(task);
		
	}

}
