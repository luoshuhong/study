package com.lsh.testmav.restlet;

import com.zhe800.shell.AbstractResource;

/**
 * 
 * @author Luoshuhong
 * 2015年3月30日
 *
 */
public class TestAResource extends AbstractResource {
	@Override
	public String deal(String data) {  
		return result(0,"A success");
	}
}
