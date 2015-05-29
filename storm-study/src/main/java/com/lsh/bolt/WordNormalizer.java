package com.lsh.bolt;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * 负责拆分句子
 * @author Luoshuhong
 * @Company  
 * 2015年5月29日
 *
 */
public class WordNormalizer extends BaseBasicBolt {
	OutputCollector _collector;
	
	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		super.prepare(stormConf, context);
	}
	
	public void cleanup() {}
	/**
	 * The bolt will receive the line from the
	 * words file and process it to Normalize this line
	 * The normalize will be put the words in lower case
	 * and split the line to get all words in this 
	 */
	public void execute(Tuple input, BasicOutputCollector collector) {
        String sentence = input.getString(0);
        String[] words = sentence.split(" ");
        for(String word : words){
            word = word.trim();
            if(!word.isEmpty()){
                word = word.toLowerCase();
                collector.emit(new Values(word));
                
                //如果失败 可以直接调用fail方法  这样可以摆快速重新处理
                //collector.getOutputter().fail(input);
            }
        }
	}
	
	/**
	 * The bolt will only emit the field "word" 
	 */
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));  //定义输出的字段
	}
}
