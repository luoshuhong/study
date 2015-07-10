package com.lsh.testmav.restlet;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试启动方法
 * @author Luoshuhong
 * @Company zhe800.com
 * 2015年3月30日
 */
public class ServerMain {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("/stand-alone.xml").start(); 
	}
}
