package com.lsh.http;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author Luoshuhong
 * @Company zhe800.com
 * 2015年6月4日
 *
 */
public class Main {
	/**
	 * 测试用
	 * @param args
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException, IOException {
//		String userId = "534";
//		String source_branch = "";
//		
//		String url = "http://git.tuan800-inc.com/api/v3/projects/723/merge_requests?assignee_id=237&source_branch=featrue/rm79741_20150604_luoshuhong_auto-compare-serv_push_dbinfo&target_branch=master&title=testgitlibapi1&private_token=zrPvMABJvq1LiUouzg97&sudo=luoshuhong";
//		System.out.println(10791.2 -10114);
		String url = "http://192.168.4.222:18990/baseQueryBusiness?data=";
//		String url = "http://192.168.4.222:18990/baseQueryBusiness?data={\"action\":\"queryGroupByDate\",\"param\":{\"startDate\":\"2015-3-1\",\"endDate\":\"2015-7-30\"}}";
		
		JSONObject data = new JSONObject();
		data.put("action", "queryGroupByDate");
		
		JSONObject param = new JSONObject();
		param.put("startDate", "2015-8-1");
		param.put("endDate", "2015-8-6");
		
		data.put("param", param.toString());
		String result = HttpUtils.doHttpPost(url+ URLEncoder.encode(data.toString(),"utf-8"));
		System.out.println("--result:" + result);
		
		JSONObject job = JSONObject.parseObject(result);
		JSONArray jsonArr = JSONArray.parseArray(job.getString("result"));
		for (int i = 0; i < jsonArr.size(); i++) {
			System.out.println(jsonArr.get(i));
		}
	}
}
