package com.lsh.mongo;


import java.io.BufferedReader;
import java.io.FileReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class UpdateTest {
	public static List<String> result = new ArrayList<String>();
	private static ExecutorService businessDealPool = Executors.newFixedThreadPool(50); ;  //主业务处理线程池
	public static int i = 0;
	
	
	public static void main(String[] args) {
		try {
//			Mongo mongo = new Mongo("192.168.10.66", 27017);
			Mongo mongo = new Mongo("119.254.161.43", 27027);
            //读文件
            List<String> list = new ArrayList<String>();
    		BufferedReader br = new BufferedReader(new FileReader("D:/product.txt"));
    		String line = null;
    		while (null != (line = br.readLine())) {
    			if (StringUtils.isEmpty(line)) {
    				continue;
    			}
    			list.add(line.trim());
    		}
    		br.close();
    		
    		
//    		new MyThread1(mongo, "").start();
    		//调用mongo
    		for (String productId : list) {
    			while (((ThreadPoolExecutor) businessDealPool).getActiveCount() >= 48) {
					Thread.sleep(500);
				}
    			businessDealPool.execute(new MyThread1(mongo, productId));
    		}
    		
    		System.out.println("----");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyThread1 extends Thread {
	DBCollection collection;
	DB db;
	String productId;
	public MyThread1(Mongo mongo, String productId) {
		this.db = mongo.getDB("trade");
		db.authenticate("trade_write", "1WM6df680WFI4227b2ba10ab".toCharArray());
//		db.authenticate("admin", "admin123456".toCharArray());
		collection = db.getCollection("product_detail");
		this.productId = productId;
	}
	@Override
	public void run() {
		DBObject query = new BasicDBObject();
//		query.put("productId", productId);
		query.put("_id", new ObjectId(productId));
//		query.put("productId", "ze150413105403000743");
//		query.put("updateStatus", 4);
		
//		DBCursor cursor = collection.find(query).sort(new BasicDBObject("updateTime",-1));
//		DBObject firstObj = cursor.toArray().get(0);
//		if (null == firstObj) {
//			return;
//		}
		DBObject firstObj = collection.findOne(query);
		
		firstObj.put("updateStatus", 1);
//		firstObj.put("updateTime", new Date());
		
		DBObject query1 = new BasicDBObject();
		query1.put("_id", new ObjectId(firstObj.get("_id").toString()));
		collection.update(query1, firstObj);
	}
}
