package com.lsh.httpshell;

import com.zhe800.shell.AbstractResource;

/**
 * 
 * @author Luoshuhong
 * @Company zhe800.com
 * 2015年3月30日
 *
 */
public class TestcTask extends AbstractResource {
	@Override
	public String deal(String data) {  
		System.out.println("----- TestcTask start ....");
		try {
			Thread.sleep(5 * 60 * 1000);  //5分钟
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result(0,"C success");
	}
}
