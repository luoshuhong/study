package com.lsh.testmav.restlet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 测试客户端
 * @author Luoshuhong
 * @Company zhe800.com
 * 2015年3月30日
 *
 */
public class ClientMain {
	public static void main(String[] args) {
		String url = "http://www.lsh.com:8888/b/?data=testa&sign=xxxx"; 
        System.out.println(http(url));
        
        //添加zk
//        String appPath = com.zhe800.imago.client.Constants.ZOOKEEPER_ROOT_PATH.concat("/").concat(app.getAppKey());
//        errorApp = appPath;//如果出错, 先记录异常app
//        this.configManager.createDir(appPath);
//        //获取app下所有的配置项
//        List<ImagoConfig> configList = this.getConfigListByAppKey(app.getAppKey());
//        if (configList != null) {
//            for (ImagoConfig config : configList) {
//
//                String dataPath = appPath.concat("/").concat(config.getConfigKey());
//                errorConfig = dataPath; //如果出错, 先记录异常data
//                this.configManager.writeData(dataPath, config.getConfigValue().getBytes());
//            }
//        }
	}
	
	/**
	 * Http访问 
	 * @param urlStr url
	 * @return 
	 */
	public static String http(String urlStr) {
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
		
		return "";
	}
}
