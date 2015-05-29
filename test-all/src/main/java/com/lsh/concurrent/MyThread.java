package com.lsh.concurrent;

/**
 * 测试线程
 * @author Luoshuhong
 * @Company zhe800.com
 * 2015年3月26日
 *
 */
public class MyThread implements Runnable {
	private String name;
	
	public MyThread(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println("--my name is:" + this.name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
