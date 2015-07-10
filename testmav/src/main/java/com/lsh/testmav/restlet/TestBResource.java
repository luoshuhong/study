package com.lsh.testmav.restlet;

import com.zhe800.shell.AbstractResource;

/**
 * 
 * @author Luoshuhong
 * @Company zhe800.com
 * 2015年3月30日
 *
 */
public class TestBResource extends AbstractResource {
	int i = 0;
	@Override
	public String deal(String data) {   
		try {
			Thread.sleep(3 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return result(0,"B success");
	}
}
