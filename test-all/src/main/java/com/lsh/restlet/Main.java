package com.lsh.restlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

/**
 * 主测试类
 * @author Luoshuhong
 * @Company zhe800.com
 * 2015年3月27日
 *
 */
public class Main {
	
	public static void main(String[] args) throws ResourceException, IOException {
//		ClientResource client = new ClientResource("http://www.lsh.com:8888/quartz/?taskName=com.test.yin0");
//        client.get().write(System.out);
//		Representation result;
		
//		result =  client.get() ;		    //调用get方法
//		System.out.println(result.getText());  
		
//		result =  client.post(null) ;		//调用post方法
//		System.out.println(result.getText());  
		
//		result =  client.put(null) ;		//调用put方法
//		System.out.println(result.getText());  
//		
//		result =  client.delete() ;		//调用delete方法
//		System.out.println(result.getText());  
		
//		String url = "http://www.lsh.com:8888/quartz/pay1/?taskName=com.test.yin0";
//        System.out.println(http(url));
        
        String url = "http://192.168.56.1:8888/quzrtz?data=xxx";
        System.out.println(http(url));
	}	
	
	
	public static String http (String urlStr) {
		try {
			URL url = new URL(urlStr);
	        URLConnection urlConn = url.openConnection();
	        if (urlConn instanceof HttpURLConnection) {
	            HttpURLConnection httpURLConn = (HttpURLConnection) urlConn;
	            httpURLConn.setConnectTimeout(30000);
	            InputStream in = httpURLConn.getInputStream();
	            BufferedReader inb = new BufferedReader(new InputStreamReader(in,"UTF-8"));
	            StringBuffer buffer = new StringBuffer();
	            String line = "";
	            while ((line = inb.readLine()) != null) {
	                buffer.append(line);
	            }
	            inb.close();
	            in.close();
	            
	            return buffer.toString();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}	
