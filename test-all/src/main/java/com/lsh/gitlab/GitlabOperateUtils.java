package com.lsh.gitlab;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lsh.http.HttpUtils;

/**
 * Gitlab 操作工具类
 * 封装Git的 一些常规操作
 * @author Luoshuhong
 * @Company 
 * @see  http://doc.gitlab.com/
 * 2015年6月4日
 *
 */
public class GitlabOperateUtils {
	private static final Logger logger = LoggerFactory.getLogger(GitlabOperateUtils.class);
	private static final String HEAD = "http://git.tuan800-inc.com/api/v3/projects/";
	
	private static String projectId ;     //项目id
	private static String private_token;  //用户key
	private static String sudo;			  //用户名
	private static String assignee_id;	  //merge_request发给谁
	private static String source_branch;  //merge_request源分支
	private static String target_branch;  //merge_request目标分支
	
	/**
	 * 创建mergerequest
	 */
	public static void createMG() {
		verify();
//		String url = "http://git.tuan800-inc.com/api/v3/projects/723/merge_requests?assignee_id=237&source_branch=featrue/rm79741_20150604_luoshuhong_auto-compare-serv_push_dbinfo&target_branch=master&title=testgitlibapi1&private_token=zrPvMABJvq1LiUouzg97&sudo=luoshuhong";
		String url = HEAD + "/" + projectId + "/merge_requests?assignee_id=" + assignee_id
				+ "&source_branch=" + source_branch 
				+ "&target_branch=" + target_branch 
				+ "&title=" + source_branch + " >> " + target_branch
				+ "&private_token=" + private_token
				+ "&sudo=" + sudo;
		try {
			HttpUtils.doHttpPost(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	/**
	 * 验证 初始化
	 */
	private static void verify() {
		if (StringUtils.isEmpty(projectId) 
				|| StringUtils.isEmpty(private_token)
				|| StringUtils.isEmpty(sudo) 
				|| StringUtils.isEmpty(assignee_id)
				|| StringUtils.isEmpty(source_branch)) {
			projectId = "xxx";
			private_token = "xxx";
			sudo = "xxx";
			assignee_id = "xxx";
			source_branch = "xxx";
			target_branch = "xxx";
		}
	}
	
}
