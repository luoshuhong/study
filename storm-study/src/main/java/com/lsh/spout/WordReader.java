package com.lsh.spout;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class WordReader extends BaseRichSpout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SpoutOutputCollector collector;
	
	private boolean completed = false;
	
	/**
	 * 一个 tuple 处理成功后会调用该方法 
	 */
	public void ack(Object msgId) {
		System.out.println("OK:"+msgId);
	}
	public void close() {}
	
	/**
	 * 一个tuple 处理失败后会调用此方法
	 */
	public void fail(Object msgId) {
		System.out.println("FAIL:"+msgId);
	}

	/**
	 * The only thing that the methods will do It is emit each 
	 * file line
	 */
//	public void nextTuple() {
//		/**
//		 * The nextuple it is called forever, so if we have been readed the file
//		 * we will wait and then return
//		 */
//		if(completed){
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				//Do nothing
//			}
//			return;
//		}
//		String str;
//		//Open the reader
//		BufferedReader reader = new BufferedReader(fileReader);
//		try{
//			//Read all lines
//			while((str = reader.readLine()) != null){
//				
//				/**
//				 * 这里将 str内容作为 msgId 根据该id来追踪具体tuple
//				 * 
//				 *  发射的tuple被传送到消息处理者bolt那里， 
//				 *  storm会跟踪由此所产生的这课tuple树。如果storm检测到一个tuple被完全处理了，
//				 *  那么storm会以最开始的那个message-id作为参数去调用消息源的ack方法；
//				 *  反之storm会调用spout的fail方法。
//				 *  值得注意的一点是， storm调用ack或者fail的task始终是产生这个tuple的那个task。
//				 *  所以如果一个spout被分成很多个task来执行， 
//				 *  消息执行的成功失败与否始终会通知最开始发出tuple的那个task
//				 */
//				this.collector.emit(new Values(str),str);
//			}
//		}catch(Exception e){
//			throw new RuntimeException("Error reading tuple",e);
//		}finally{
//			completed = true;
//		}
//	}
	
	  @Override
	  public void nextTuple() {
	    try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    String[] sentences = new String[]{ "i am ", "zhe800",
	        "tuan800 taobao", "changyou jd", "520 405 531 412 409" };
	    String sentence = sentences[new Random().nextInt(sentences.length)];
	    this.collector.emit(new Values(sentence));
	  }

	/**
	 * We will create the file and get the collector object
	 */
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	/**
	 * Declare the output field "word"
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("line"));
	}
}
