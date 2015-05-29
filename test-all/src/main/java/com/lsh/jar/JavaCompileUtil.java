package com.lsh.jar;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * java 文件 编译 打包工具类
 * @author Luoshuhong
 * @Company zhe800.com
 * 2015年3月25日
 *
 */
public class JavaCompileUtil {
	
	/**
	 * 编译java 源文件
	 * @param javaSourcePath  java源文件
	 * @param javaClassPath   class生成目标地址
	 * @throws IOException
	 */
	public static void compile (String javaSourcePath, String javaClassPath) throws IOException {
		System.out.println("*** --> 开始编译java源代码...");  
		  
	    File javaclassDir = new File(javaClassPath);  
	    if (!javaclassDir.exists()) {  
	        javaclassDir.mkdirs();  
	    }  
	  
	    List<String> javaSourceList = new ArrayList<String>();  
	    getFileList(new File(javaSourcePath), javaSourceList);  
	  
	    JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();  
	    int result = -1;  
	    for (int i = 0; i < javaSourceList.size(); i++) {  
	    	if (javaSourceList.get(i).contains(".java")) {
		        result = javaCompiler.run(null, null, null, "-d", javaClassPath, javaSourceList.get(i));  
		        System.out.println(result == 0 ? "*** 编译成功 : " + javaSourceList.get(i) : "### 编译失败 : " + javaSourceList.get(i));  
	    	}
	    }  
	    System.out.println("*** --> java源代码编译完成。");    
		
	}
	
	/**
	 * 生成jar文件
	 * @param javaClassPath class路径
	 * @param targetPath    jar目标路径
	 * @throws IOException 
	 */
	public static void produceJar(String javaClassPath, String targetPath) throws IOException {
		System.out.println("*** --> 开始生成jar包...");  
	    String targetDirPath = targetPath.substring(0, targetPath.lastIndexOf("/"));  
	    File targetDir = new File(targetDirPath);  
	    if (!targetDir.exists()) {  
	        targetDir.mkdirs();
	    }  
	  
	    Manifest manifest = new Manifest();  
	    manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");  
	  
	    JarOutputStream target = new JarOutputStream(new FileOutputStream(targetPath), manifest);
	    writeClassFile(javaClassPath, new File(javaClassPath), target);  
	    target.close();  
	    System.out.println("*** --> jar包生成完毕。");  
	}
	
	/**
	 * 遍历 文件夹
	 * @param file 		元素文件夹
	 * @param fileList  文件夹下文件列表
	 * @throws IOException IOException
	 */
	private static void getFileList(File file, List<String> fileList) throws IOException {  
		if (!file.isDirectory()) {
			fileList.add(file.getPath());
			return;
		}
		
        File[] files = file.listFiles();  
        for (int i = 0; i < files.length; i++) {  
            if (files[i].isDirectory()) {  
                getFileList(files[i], fileList);  
            } else {  
                fileList.add(files[i].getPath());  
            }  
        }  
    }  
	
	/**
	 * 
	 * @param javaClassPath
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	private static void writeClassFile(String javaClassPath, File source, JarOutputStream target) throws IOException {  
	    BufferedInputStream in = null;  
	    try {  
	        if (source.isDirectory()) {  
	            String name = source.getPath().replace("\\", "/");  
	            if (!name.isEmpty()) {  
	                if (!name.endsWith("/")) {  
	                    name += "/";  
	                }  
	                name = name.substring(javaClassPath.length());  
	                JarEntry entry = new JarEntry(name);  
	                entry.setTime(source.lastModified());  
	                target.putNextEntry(entry);  
	                target.closeEntry();  
	            }  
	            for (File nestedFile : source.listFiles())  {
	                writeClassFile(javaClassPath, nestedFile, target);  
	            }
	            return;  
	        }  
	  
	        String middleName = source.getPath().replace("\\", "/").substring(javaClassPath.length());  
	        JarEntry entry = new JarEntry(middleName);  
	        entry.setTime(source.lastModified());  
	        target.putNextEntry(entry);  
	        in = new BufferedInputStream(new FileInputStream(source));  
	  
	        byte[] buffer = new byte[1024];  
	        while (true) {  
	            int count = in.read(buffer);  
	            if (count == -1)  
	                break;  
	            target.write(buffer, 0, count);  
	        }  
	        target.closeEntry();  
	    } finally {
	        if (in != null)  
	            in.close();  
	    }  
	}  
}
