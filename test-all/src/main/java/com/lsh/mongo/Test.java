package com.lsh.mongo;


import java.io.BufferedWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.aspectj.bridge.Constants;
import org.bson.types.ObjectId;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
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
			update();
            
//            bw = new BufferedWriter(new FileWriter("D:/result.txt"));
//            //读文件
//            List<String> list = new ArrayList<String>();
//    		BufferedReader br = new BufferedReader(new FileReader("D:/product.txt"));
//    		String line = null;
//    		while (null != (line = br.readLine())) {
//    			if (StringUtils.isEmpty(line)) {
//    				continue;
//    			}
//    			list.add(line.trim());
//    		}
//    		br.close();
//    		
//    		//调用mongo
//    		for (String productId : list) {
//    			while (((ThreadPoolExecutor) businessDealPool).getActiveCount() >= 48) {
//					Thread.sleep(500);
//				}
//    			bw.flush();
//    			businessDealPool.execute(new MyThread(mongo, productId));
//    		}
//    		
//    		bw.flush();
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	public static void update() throws Exception {
		Mongo mongo = new Mongo("192.168.10.66", 27017);
    	DB db = mongo.getDB("trade");
		db.authenticate("admin", "admin123456".toCharArray());
		DBCollection collection = db.getCollection("product_detail");
		DBObject query = new BasicDBObject();
		query.put("_id", new ObjectId("55b204f0e4b0ac2afb9ea94e"));

		DBObject obj = collection.findOne(query);
		obj.put("updateStatus", 0);
//		obj.put("detailContent", "<p>无线测试-不<img src='https://img.baidu.com/imgextra/i1/2046363775/TB2H4umcVXXXXcLXpXXXXXXXXXX-2046363775.jpg' />同sku不同价格</p>");
		analyzeHTML(obj.get("detailContent").toString());
		
		collection.update(query, obj);
	}
	
	
	private static void analyzeHTML(String content) {
//		logger.info(">>>>>methodName:[analyzeHTML]----param:[ProductDetailBean:" + bean + "]<<<<<");
		Document doc = Jsoup.parse(content);
		Elements imgs = doc.select("img");
		List<Element> toBeRemoved = new ArrayList<Element>();// 将要删除的数据集
		Set<String> imgUrlStringSet = new HashSet<String>();// 正确的图片的src集合
		for (Element e : imgs) {
			// 先移除宽高属性
			e.removeAttr("width");
			e.removeAttr("height");
			String src = e.attr("src");
//			src = new UploadImgUrlUtils().replace(src);
//			UpdateImgFilterUtils imgFilter = new UpdateImgFilterUtils();

			// 添加筛选条件,什么情况下不添加到Set中
//			if (imgFilter.inUrlBlackList(src)) {
//				toBeRemoved.add(e);
//			} else if (imgFilter.check(src)) {
				imgUrlStringSet.add(src);
//			}
		}
		
		System.out.println(imgUrlStringSet);
		System.out.println(toBeRemoved);
//		return result;
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
		DBObject result = collection.findOne(obj);
		Test.put(productId + " ; status="  + result.get("updateStatus").toString());
	}
}
