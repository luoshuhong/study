package com.lsh.zookeeper.curator;

import java.io.Closeable;
import java.io.IOException;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;

/**
 * LeaderLatch 一旦选举出Leader，除非有客户端挂掉重新触发选举，否则不会交出领导权
 * @author Luoshuhong
 * @Company  
 * 2015年7月21日
 *
 */
public class LeaderLatchClient implements LeaderLatchListener, Closeable {
	private final String name;
	private final LeaderLatch leaderLatch; //

	public LeaderLatchClient(CuratorFramework client, String name) {
		this.name = name;
		leaderLatch = new LeaderLatch(client, "/product-detail/leader");

	}

	public void start() throws Exception {
		leaderLatch.start();
	}

	@Override
	public void close() throws IOException {
		leaderLatch.close();
	}

	@Override
	public void isLeader() {
		if (leaderLatch.hasLeadership()) {
			System.out.println(name + "是当前的leader");  
		}
	}

	@Override
	public void notLeader() {
		System.out.println(name + "不是当前的leader");  
	}


}
