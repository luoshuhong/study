package com.lsh.testmav.restlet;

import com.zhe800.shell.annotation.HttpShell;

/**
 * 使用注解测试 httpshell
 * @author Luoshuhong
 * @Company zhe800.com
 * 2015年5月15日
 *
 */
@HttpShell
public class TestAnnotationTask {
	
	@HttpShell(path="/annotationTask1")
	public String annotationTask1 () {
		return  "annotationTask1" ;
	}
	
	@HttpShell(path="/annotationTask2")
	public String annotationTask2 () {
		return  "annotationTask2" ;
	}
	
	@HttpShell(path="/annotationTask3")
	public String annotationTask3 () {
		return  "annotationTask3" ;
	}
}
