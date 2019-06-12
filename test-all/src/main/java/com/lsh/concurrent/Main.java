package com.lsh.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 主测试方法
 * @author Luoshuhong
 * 2015年3月26日
 *
 */
public class Main {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		MyThread thread = new MyThread("first thread");
//		pool.submit(thread);
		pool.execute(thread);
	}
	
}
