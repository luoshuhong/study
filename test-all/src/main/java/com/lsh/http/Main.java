package com.lsh.http;

import java.io.IOException;
import java.net.MalformedURLException;

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
		String userId = "534";
		String source_branch = "";
		
		String url = "http://git.tuan800-inc.com/api/v3/projects/723/merge_requests?assignee_id=237&source_branch=featrue/rm79741_20150604_luoshuhong_auto-compare-serv_push_dbinfo&target_branch=master&title=testgitlibapi1&private_token=zrPvMABJvq1LiUouzg97&sudo=luoshuhong";
		System.out.println(HttpUtils.doHttpPost(url));
	}
}
