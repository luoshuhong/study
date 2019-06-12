package com.lsh.jar;

import java.io.IOException;

/**
 * 测试主类
 * @author Luoshuhong
 * 2015年3月25日
 *
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
//		String[] params = new String[]{"jar", "cvf", "E:/2/sss.jar", "E:/2/com"}; 
//		Runtime.getRuntime().exec(params);
        
		String javaSourcePath = "E:/2";
//		String javaClassPath = "E:/2";
//		String targetPath = "E:/3/com.jar"; 
		JavaCompileUtil.compile(javaSourcePath, javaSourcePath);   //编译
//		JavaCompileUtil.produceJar(javaClassPath, targetPath);     //打包
				
	}
	
}

