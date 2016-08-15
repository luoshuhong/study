package com.lsh.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 
 * @author Luoshuhong
 * @Company 
 * 2015年7月21日
 *
 */
public class CuratorClient {
	private static CuratorFramework client;
	
	static {
	String serverIp = "xxx:2181,xxx:2181,xxx:2181";
		client = CuratorFrameworkFactory
				.builder()
				.connectString(serverIp)
				.sessionTimeoutMs(30000)
				.connectionTimeoutMs(30000)
				.canBeReadOnly(false)
				.retryPolicy(new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))
				.build();
		client.start(); 
	}
}
