package com.lsh.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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
//		String url = "http://192.168.4.222:18990/baseQueryBusiness?data=";
//		String url = "http://192.168.4.222:18990/baseQueryBusiness?data={\"action\":\"queryGroupByDate\",\"param\":{\"startDate\":\"2015-3-1\",\"endDate\":\"2015-7-30\"}}";
		
//		JSONObject data = new JSONObject();
//		data.put("action", "queryGroupByDate");
//		
//		JSONObject param = new JSONObject();
//		param.put("startDate", "2015-8-1");
//		param.put("endDate", "2015-8-6");
//		
//		data.put("param", param.toString());
//		String result = HttpUtils.doHttpPost(url+ URLEncoder.encode(data.toString(),"utf-8"));
//		System.out.println("--result:" + result);
//		
//		JSONObject job = JSONObject.parseObject(result);
//		JSONArray jsonArr = JSONArray.parseArray(job.getString("result"));
//		for (int i = 0; i < jsonArr.size(); i++) {
//			System.out.println(jsonArr.get(i));
//		}
		
//		String url = "http://img02.taobaocdn.com/imgextra/i2/1651692347/TB297BEcpXXXXckXXXXXXXXXXXX_%21%211651692347.jpg";
		String url = "https://img.alicdn.com/bao/uploaded/i4/TB1_t3fIVXXXXXdXFXXXXXXXXXX_!!0-item_pic.jpg";
//		String url = "https://img.alicdn.com/bao/uploaded/i1/TB1568NHVXXXXc1XpXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg";
		
//		String result = HttpUtils.doHttpPost(url);
//		System.out.println(result);
		
		System.out.println(download(url));
	}
	
	
	public static String download(String urlString) {
		InputStream is = null;     // 输入流
		OutputStream os = null;    // 输出的文件流
		String localUrl = null;    // 文件名称
		
		try {
			// 构造URL
			URL url = new URL(urlString);
			// 打开连接
			URLConnection c = url.openConnection();
			if (c instanceof HttpURLConnection) {
				HttpURLConnection con  =  (HttpURLConnection) c;
				System.out.println(con.getResponseCode());
				
				con.setConnectTimeout(30000);   // 链接超时时间30秒
				con.setReadTimeout(30000);      // 写入超时时间30秒
				
				
				// 获得输入流
				is = con.getInputStream();
				// 1K的数据缓冲
				byte[] bs = new byte[1024];
				
				new File("d:/test").mkdir();
				localUrl = "d:/test/2.jpg";
				
				os = new FileOutputStream(localUrl);
				// 开始读取
				int len;
				while ((len = is.read(bs)) != -1) {
					os.write(bs, 0, len);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error=" + e.getMessage();
		} finally {
	    	try {
				if(os != null){
					os.close();
				}
				if(is != null){
					is.close();	
				}
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		return localUrl;
	    
	}
	
}
