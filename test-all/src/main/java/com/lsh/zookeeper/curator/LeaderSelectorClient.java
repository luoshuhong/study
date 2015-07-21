package com.lsh.zookeeper.curator;

import java.io.Closeable;
import java.io.IOException;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

/**
 * LeaderSelector 所有存活的客户端不间断的轮流做Leader
 * @author Luoshuhong
 * @Company  
 * 2015年7月21日
 *
 */
public class LeaderSelectorClient extends LeaderSelectorListenerAdapter implements Closeable {  
    private final String name;  
  private final LeaderSelector leaderSelector;   // 所有存活的客户端不间断的轮流做Leader

  public LeaderSelectorClient(CuratorFramework client, String name) {  
      this.name = name;  
		leaderSelector = new LeaderSelector(client,
				"/product-detail/leader",
				this);
      leaderSelector.autoRequeue();  
      
      
  }  

  public void start() throws Exception {  
      leaderSelector.start();  
  }  

  @Override  
  public void close() throws IOException {  
      leaderSelector.close();  
  }  

  /** 
   * client成为leader后，会调用此方法 
   */  
  @Override  
  public void takeLeadership(CuratorFramework client) throws Exception {  
      System.out.println(name + "是当前的leader");  
  }  
}
