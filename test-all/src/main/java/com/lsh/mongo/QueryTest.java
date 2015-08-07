package com.lsh.mongo;

import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.GroupCommand;
import com.mongodb.Mongo;

/**
 * 一些聚合查询 例子
 * @author Luoshuhong
 * @Company  
 * 2015年7月30日
 *
 */
public class QueryTest {
	private static Mongo mongo;
	private static DB db;
	private static DBCollection collection;
	static {
		try {
			mongo = new Mongo("192.168.10.66", 27017);
			db = mongo.getDB("trade");
			db.authenticate("admin", "admin123456".toCharArray());
			collection = db.getCollection("product_detail");
			
//			GroupCommand gc = new GroupCommand();
//			
//			collection.group(cmd)
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
//		DBObject query = new BasicDBObject();
//		query.put("_id", new ObjectId("55b204f0e4b0ac2afb9ea94e"));
//
//		DBObject obj = collection.findOne(query);
//		obj.put("updateStatus", 0);
//		collection.update(query, obj);
	}
	
	public static void main(String[] args) {
//	 public GroupCommand(DBCollection inputCollection, DBObject keys, DBObject condition, DBObject initial, String reduce, String finalize) {
//	        this.input = inputCollection.getName();
//	        this.keys = keys;
//	        this.condition = condition;
//	        this.initial = initial;
//	        this.reduce = reduce;
//	        this.finalize = finalize;
//	    }
		BasicDBObject  keys = new BasicDBObject ();
//		Date date = new Date();
//		String dateS = "" +date.getYear()+ "-" +(date.getMonth()+1) + "-" +date.getDate();
//		keys.append("updateTime", dateS);
		
//		BasicDBObject dateCondition = new BasicDBObject();
//		Calendar calander = Calendar.getInstance();
//		Date startDate = calander.getTime();
//		calander.set(Calendar.DAY_OF_MONTH, 1);
//		Date endDate = calander.getTime();
//		dateCondition.append("$gte", startDate);
//		dateCondition.append("$lt", endDate);
//		keys.append("updateTime", dateCondition);

		keys.append("updateDate", true);
//		keys.append("updateStatus", true);
		
		BasicDBObject  initial = new BasicDBObject ();
//		initial.put("count", 0);
		initial.put("count1", 0);
		initial.put("count2", 0);
		initial.put("count3", 0);
		initial.put("count4", 0);
		initial.put("count5", 0);
		initial.put("count99", 0);
		
//		String reduce = "function Reduce(doc, out) {"
//						+"	if(doc._id){"
//						+"		out.count +=1;"
//						+"	}"
//						+"}";
		
		String reduce = "function Reduce(doc, out) {"
				+"	if(doc.updateStatus == 1){"
				+"		out.count1 +=1;"
				+"	}"
				+"	if(doc.updateStatus == 2){"
				+"		out.count2 +=1;"
				+"	}"
				+"	if(doc.updateStatus == 3){"
				+"		out.count3 +=1;"
				+"	}"
				+"	if(doc.updateStatus == 4){"
				+"		out.count4 +=1;"
				+"	}"
				+"	if(doc.updateStatus == 5){"
				+"		out.count5 +=1;"
				+"	}"
				+"	if(doc.updateStatus == 99){"
				+"		out.count99 +=1;"
				+"	}"
				+"}";
		GroupCommand gc = new GroupCommand(collection, keys, null, initial,reduce, null);
		DBObject result = collection.group(gc);
		System.out.println(result);
	}
	
	
	
}
