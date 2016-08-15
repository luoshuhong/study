package com.lsh.other;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;

import org.springframework.util.StringUtils;

//统计代码中  空行 注释 ... 
public class CodeCounter {
//	static long normalLines  = 0;    //正常行
//	static long commentLines = 0;    //注释
//	static long whiteLine    = 0;    //空白
	static long total        = 0;    //总和 
	static long fileCount        = 0;    //文件数 
	
	static long error        = 0;    //异常 
	
	public static void main(String[] args) throws Exception{
		File f = new File("E:/nl/b2c");
		list(f);
//		parse(f);
		
//		System.out.println("== 正常行  normalLines:  " + normalLines);
//		System.out.println("== 注释行  commentLines:  " + commentLines);
//		System.out.println("== 空白行  whiteLine:  " + whiteLine);
//		System.out.println("== 文件数  fileCount:  " + fileCount);
//		System.out.println("==========  总和:" + total + "行========");
		System.out.println("==   fileCount:  " + fileCount);
		System.out.println("==   	 total:  " + total);
		System.out.println("==   	 error:  " + error);
		
	}
	
	/**
	 * 遍历 文件夹
	 */
	public static void list (File f) throws Exception{
		if (f.isFile()) {
			parse(f);
		}
		
		File[] files = f.listFiles();
		if(null != files) {
			for (File fi : files) {
				list(fi);
			}
		}
	}

	private static void parse(File file) throws Exception {
		BufferedReader reader = null;
		if (!(file.getName().endsWith(".cs") || file.getName().endsWith(".js") || file.getName().endsWith(".aspx"))) {
			return;
		}  
//		if (file.getName().contains("Global.asax.cs")) {
//			System.out.println(file.getAbsolutePath());
//		}
//		if (file.getName().contains("qm_")) {
//			System.out.println(file.getAbsolutePath());
//		}
//		return;
		
//		// 检查是不是以.java结尾的文件
//		if (file.getName().contains("java")) {
//		    fileCount ++;
//		    
		    //下面读文件具体内容
			reader = new BufferedReader(new FileReader(file));
			String line;
			while(null != (line = reader.readLine())) {
				if (StringUtils.isEmpty(line)) {
					continue;
				}
//				line = line.replaceAll("  ", " ");
				total ++;
				if (line.contains("ajaxSimple")) {
					System.out.println("===" + file.getAbsolutePath());
					System.out.println(line);
				}
			}
       
			reader.close();
			reader = null;
		}
//	}
}