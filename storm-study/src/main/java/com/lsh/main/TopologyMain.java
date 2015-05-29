package com.lsh.main;
import com.lsh.spout.WordReader;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

import com.lsh.bolt.WordCounter;
import com.lsh.bolt.WordNormalizer;

/**
 * 主测试类
 * @author Luoshuhong
 * @Company  
 * 2015年5月29日
 *
 */
public class TopologyMain {
	public static void main(String[] args) throws InterruptedException {
         
        //Topology definition
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("word-reader",new WordReader(), 2); //.setNumTasks(2); //设置task数量  不设置是默认=executor
		//word-reader 一个组   所有tuple会被随机的分发给bolt组里的 task
		//shuffle Grouping 表示随机发送 给一个可用的 bolt
		//fields grouping 表示相同的 tuple 会发送给同一个task
		builder.setBolt("word-normalizer", new WordNormalizer(), 6)
			.shuffleGrouping("word-reader"); 
		builder.setBolt("word-counter", new WordCounter(),4)  //1表示executor  并发数
			.fieldsGrouping("word-normalizer", new Fields("word"));
		
        //Configuration
		Config conf = new Config();
//		conf.put("wordsFile", "D:/学习/storm/words.txt");
//		conf.setNumWorkers(3);
		conf.setMessageTimeoutSecs(20);  //一个tuple处理超时时间  在该时间内没有处理完 则认为处理失败
		//设置 acker的数量  此参数设置为0 storm会在spout发射一个tuple之后马上调用spout的ack方法 如果不在意失败后会损失数据 可以这样做
		conf.setNumAckers(5);  
		//topology里面各个组件(spout, bolt)设定一个线程数量上限。一般来说生成环境的这个配置很大(100左右), 而这对于本地测试来说太大了， 这个配置可以让你把它调小
//		conf.setMaxTaskParallelism(10);
//		conf.put(Config.TOPOLOGY_MAX_TASK_PARALLELISM, 10);  //效果同上
		
//		conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1); //线程数 
//		conf.setMaxSpoutPending(1);    //线程数
		
//		conf.setDebug(false);
		conf.setDebug(true);
		
		//远程提交
		if (args != null && args.length > 0) {
	      try {
	    	  conf.setNumWorkers(8); //设置topology worker数
	    	  StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
			} catch (AlreadyAliveException e) {
				e.printStackTrace();
			} catch (InvalidTopologyException e) {
				e.printStackTrace();
			}
	    } else {
	        //本地模式
	    	conf.setMaxTaskParallelism(3);
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("word_count_test_lsh", conf, builder.createTopology());
			Thread.sleep(1000 * 10);
			cluster.killTopology("word_count_test_lsh");
			cluster.shutdown();
	    }
	}
}
