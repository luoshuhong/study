package com.lsh.zookeeper.curator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatch.State;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import com.google.common.collect.Lists;

/**
 * 测试类
 * @author Luoshuhong
 * @Company  
 * 2015年7月21日
 *
 */
public class Test {
	public static void main(String[] args) {
		String serverIp = "192.168.10.66:2181,192.168.10.57:2181,192.168.10.58:2181";
        List<CuratorFramework> clients = Lists.newArrayList();  
        List<LeaderLatch> examples = Lists.newArrayList();  
        try {  
            for (int i = 0; i < 9; i++) {  
                CuratorFramework client = CuratorFrameworkFactory
        				.builder()
        				.connectString(serverIp)
        				.sessionTimeoutMs(30000)
        				.connectionTimeoutMs(30000)
        				.canBeReadOnly(false)
        				.retryPolicy(new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))
        				.build();
                
                LeaderLatch example = new LeaderLatch(client, "/product-detail/leader", "Client #" + i);  
                clients.add(client);  
                examples.add(example);  
  
                client.start();  
                example.start();  
            }
  
            System.out.println("----------先观察一会选举的结果-----------");  
            Thread.sleep(10000);  
			for (int i = 0; i < 9; ++i) {
				if (examples.get(i).hasLeadership()) {
					LeaderLatch latch = examples.get(i);
					System.out.println("current leader is " + latch.getId());
					latch.close();
				}
			}
			System.out.println("---" + examples.get(0).getLeader().getId());
            System.out.println("----------先观察一会选举的结果-----------");  
            Thread.sleep(10000);  
            for (int i = 0; i < 9; ++i) {
            	if (examples.get(i).getState().equals(State.CLOSED)) {
            		continue;
            	}
				if (examples.get(i).hasLeadership()) {
					LeaderLatch latch = examples.get(i);
					System.out.println("current leader is " + latch.getId());
					latch.close();
				}
			}
            System.out.println("---" + examples.get(6).getLeader().getId());
            System.out.println("----------先观察一会选举的结果-----------");  
            Thread.sleep(10000);  
            for (int i = 0; i < 9; ++i) {
            	if (examples.get(i).getState().equals(State.CLOSED)) {
            		continue;
            	}
				if (examples.get(i).hasLeadership()) {
					LeaderLatch latch = examples.get(i);
					System.out.println("current leader is " + latch.getId());
					latch.close();
				}
			}
            System.out.println("---" + examples.get(6).getLeader().getId());
            // 这里有个小技巧，让main程序一直监听控制台输入，异步的代码就可以一直在执行。不同于while(ture)的是，按回车或esc可退出  
            new BufferedReader(new InputStreamReader(System.in)).readLine();  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            for (LeaderLatch exampleClient : examples) {
                CloseableUtils.closeQuietly(exampleClient);  
            }  
            for (CuratorFramework client : clients) {  
                CloseableUtils.closeQuietly(client);  
            }  
        }  
	}
}
