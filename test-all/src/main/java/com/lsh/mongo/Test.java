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
    		for (String productId : list) {
    			while (((ThreadPoolExecutor) businessDealPool).getActiveCount() >= 48) {
					Thread.sleep(500);
				}
    			bw.flush();
    			businessDealPool.execute(new MyThread(mongo, productId));
    		}
    		
    		
    		Thread.sleep(1000 * 60);
    		bw.flush();
    		
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	public static void update(String productId) throws Exception {
		Mongo mongo = new Mongo("192.168.10.66", 27017);
    	DB db = mongo.getDB("trade");
		db.authenticate("admin", "admin123456".toCharArray());
		DBCollection collection = db.getCollection("product_detail");
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId("55b204f0e4b0ac2afb9ea94e"));

		DBObject obj = collection.findOne(query);
		obj.put("updateStatus", 0);
		analyzeHTML(obj.get("detailContent").toString());
		
		collection.update(query, obj);
	}
	
	
	private static void analyzeHTML(String content) {
		Document doc = Jsoup.parse(content);
		Elements imgs = doc.select("img");
		List<Element> toBeRemoved = new ArrayList<Element>();// 将要删除的数据集
		Set<String> imgUrlStringSet = new HashSet<String>();// 正确的图片的src集合
		for (Element e : imgs) {
			// 先移除宽高属性
			e.removeAttr("width");
			e.removeAttr("height");
			String src = e.attr("src");
			imgUrlStringSet.add(src);
		}
		System.out.println(imgUrlStringSet);
		System.out.println(toBeRemoved);
	}
}



class MyThread extends Thread {
	DBCollection collection;
	DB db;
     
     
	String productId;
	public MyThread(Mongo mongo, String productId) {
		this.db = mongo.getDB("trade");
		db.authenticate("trade_write", "1WM6df680WFI4227b2ba10ab".toCharArray());
		collection = db.getCollection("product_detail");
		this.productId = productId;
	}
	@Override
	public void run() {
		DBObject obj = new BasicDBObject();
		obj.put("productId", productId);
//		DBObject result = collection.findOne(obj);
//		System.out.println(productId + " ; status="  + result.get("updateStatus").toString());
//		Test.put(productId + " ; status="  + result.get("updateStatus").toString());
		
		DBCursor cursor = collection.find(obj).sort(new BasicDBObject("updateTime",-1));
//		List<DBObject> list = cursor.toArray();
//        System.out.println(list.size());//list的长度
//        for (DBObject bdo : list) {
//        	System.out.println(productId + " ; status="  + bdo.get("updateStatus").toString() + ", updateTime=" + bdo.get("updateTime"));
//        }
		DBObject firstObj = cursor.toArray().get(0);
		Test.put(productId + " ; status="  + firstObj.get("updateStatus").toString());
	}
}
