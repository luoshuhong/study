package com.lsh.bolt;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

/**
 * 统计单词数量 count Bolt
 * @author Luoshuhong
 * @Company  
 * 2015年5月5日
 *
 */
public class WordCounter extends BaseBasicBolt {

	Integer id;
	String name;
	Map<String, Long> counters;

	/**
	 * At the end of the spout (when the cluster is shutdown
	 * We will show the word counters
	 */
	@Override
	public void cleanup() {
//		System.out.println("-- Word Counter ["+name+"-"+id+"] --");
		for(Map.Entry<String, Long> entry : counters.entrySet()){
			System.out.println("-----------" + entry.getKey()+": "+entry.getValue());
		}
	}

	/**
	 * On create 
	 */
	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		this.counters = new HashMap<String, Long>();
		this.name = context.getThisComponentId();
		this.id = context.getThisTaskId();
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {}


	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		String str = input.getString(0);
		if (StringUtils.isEmpty(str)) {
			return;
		}
		str = str.trim();
//		System.out.println("---WordCounter---sentence:" + str);
		if(!counters.containsKey(str)){
			counters.put(str, 1l);
		}else{
			Long c = counters.get(str) + 1l;
			counters.put(str, c);
		}
	}
}
