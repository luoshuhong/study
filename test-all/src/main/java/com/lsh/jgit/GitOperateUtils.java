package com.lsh.jgit;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

/**
 * Git 操作工具类
 * 封装Git的 一些常规操作
 * @author Luoshuhong
 * 2015年5月13日
 *
 */
public class GitOperateUtils {
	public static Git git;
	public static String userName;
	public static String password;
	
	/**
	 * 初始化信息
	 * @param remoteGitUrl 远程git仓库地址
	 * @param localGitDir  本地git仓库地址
	 * @param userName     用户名
	 * @param password     密码
	 */
	public static void init (String remoteGitUrl, String localGitDir, String userName, String password) throws Exception {
		//clone 
		File file = new File(localGitDir);
		if (!file.exists()) {
			file.mkdirs();
			GitOperateUtils.clone(remoteGitUrl, localGitDir, userName, password);  //clone 到本地
		} else {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				if (0 == files.length) {
					GitOperateUtils.clone(remoteGitUrl, localGitDir, userName, password);  //clone 到本地
				}
			} else {
				throw new Exception("本地Git目录错误，不是文件夹！ localGitDir:" + localGitDir);
			}
		}
		
		GitOperateUtils.userName = userName;
		GitOperateUtils.password = password;
		
		//初始化Git 实例
		try {
	        git = new Git(new FileRepositoryBuilder().setGitDir(new File(localGitDir + File.separator + ".git"))
	  	          .readEnvironment()
		          .findGitDir()
		          .build());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//pull
		GitOperateUtils.pull();
	}
	
	/**
	 * pull 操作
	 */
	public static void pull () {
		try {
			PullCommand pullCommand = git.pull();
			pullCommand.call().isSuccessful();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * push 操作
	 */
	public static void push () {
		try {
			PushCommand pushCommond = git.push();//.call();
	        pushCommond.setCredentialsProvider(new UsernamePasswordCredentialsProvider("luoshuhong", "Luoshuhong008"));
	        pushCommond.call();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * add 操作
	 * @param filepattern 添加的文件
	 */
	public static void add (String filepattern) {
		AddCommand addCommand = git.add();
        addCommand.addFilepattern("filepattern"); //在git仓库中相对位置的文件
        try {
			addCommand.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * commit 操作
	 * @param comment  注释
	 */
	public static void commit(String comment) {
		CommitCommand commit = git.commit();
        commit.setAll(true);
        try {
			commit.setMessage("comment").call();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * clone 操作
	 * @param remoteGitUrl 远程git仓库地址
	 * @param localGitDir  本地git仓库地址
	 * @param userName     用户名
	 * @param password     密码
	 */
	public static void clone(String remoteGitUrl,String localGitDir, String userName, String password) {
        try {
			Git.cloneRepository()
				.setURI(remoteGitUrl)//.setBranch("")//这里可以指定切换到某个分支
				.setDirectory(new File(localGitDir))
				.setCredentialsProvider(new UsernamePasswordCredentialsProvider(userName, password)).call();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
