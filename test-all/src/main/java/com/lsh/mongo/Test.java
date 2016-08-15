package com.lsh.mongo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class Test {
	public static List<String> result = new ArrayList<String>();
	public static BufferedWriter bw;
	private static ExecutorService businessDealPool = Executors.newFixedThreadPool(50); ;  //主业务处理线程池
	public static int i = 0;
	
	public synchronized static void put (String r) {
		try {
			bw.write(r);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
//			update();
			
			Mongo mongo = new Mongo("119.254.161.43", 27027);
            bw = new BufferedWriter(new FileWriter("D:/result.txt"));
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
    		
    		//调用mongo
    		for (String record : list) {
    			while (((ThreadPoolExecutor) businessDealPool).getActiveCount() >= 48) {
					Thread.sleep(500);
				}
    			bw.flush();
    			businessDealPool.execute(new MyThread(mongo, record.split("=")[0], record.split("=")[1]));
    		}
    		
//    		Thread.sleep(1000 * 60);
//    		bw.flush();
    		System.out.println("---end...");
    		
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
//	public static void update(String productId) throws Exception {
//		Mongo mongo = new Mongo("192.168.10.66", 27017);
//    	DB db = mongo.getDB("trade");
//		db.authenticate("admin", "admin123456".toCharArray());
//		DBCollection collection = db.getCollection("product_detail");
//		DBObject query = new BasicDBObject();
//		query.put("_id", new ObjectId("55b204f0e4b0ac2afb9ea94e"));
//
//		DBObject obj = collection.findOne(query);
//		obj.put("updateStatus", 0);
//		
//		collection.update(query, obj);
//	}
}



class MyThread extends Thread {
	DBCollection collection;
	DB db;
     
     
	String productId;
	String id;
	public MyThread(Mongo mongo, String productId, String id) {
		this.db = mongo.getDB("trade");
		db.authenticate("trade_write", "1WM6df680WFI4227b2ba10ab".toCharArray());
		collection = db.getCollection("product_detail");
		this.productId = productId;
		this.id = id;
	}
	@Override
	public void run() {
		DBObject obj = new BasicDBObject();
		obj.put("_id", new ObjectId(this.id));
		DBObject result = collection.findOne(obj);
		
//		DBCursor cursor = collection.find(obj).sort(new BasicDBObject("updateTime",-1));
//		DBObject firstObj = cursor.toArray().get(0);
		
		Test.put(productId + ", " + id +  " ; status="  + result.get("updateStatus").toString());
	}
}
