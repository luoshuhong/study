package com.lsh.jgit;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class Main {
	public static final String REF_REMOTES = "git@git.tuan800-inc.com:trade-document/trade-document.git";
	public static final String FILE_DIR = "E:/z-jgit/a.thrift";
			
	
	public static void main(String[] args)  throws Exception {
//		String gitRoot = "ssh://git@git.tuan800-inc.com:trade-document/trade-document.git";
		String gitRoot = "/trade-document/.git";
		String branceName = "master";
		String fileName = "thrift/address.thrift";
		try {
			getContentWithFile(gitRoot, branceName, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
    /** 
     * 获取指定分支、指定文件的内容 
     * @param gitRoot git仓库目录 
     * @param branchName 分支名称 
     * @param fileName 文件名称 
     * @return 
     * @throws Exception 
     */  
    public static String getContentWithFile(String gitRoot, final String branchName, String fileName) throws Exception {  
        //克隆  已测试可用
        Git.cloneRepository()
        .setURI("http://git.tuan800-inc.com/trade-document/trade-document.git")
        .setDirectory(new File("E:/test"))
        .setCredentialsProvider(new UsernamePasswordCredentialsProvider("luoshuhong", "Luoshuhong008"))
        .call();
        
        //更新
        System.out.println(Git.open(new File("E:/test/.git")).pull().call().isSuccessful());
        
//        FileRepositoryBuilder builder = new FileRepositoryBuilder();
//        Repository repository = builder.setGitDir(new File("D:/test/.git"))
//          .readEnvironment() // scan environment GIT_* variables
//          .findGitDir() // scan up the file system tree
//          .build();
//        Git git = new Git(repository);
        
        //add 
//        AddCommand addCommand = git.add();
//        addCommand.addFilepattern("thrift/quartz_new_test.thrift");
//        addCommand.call();
        
        //commit 测试已可用
//        CommitCommand commit = git.commit();
//        commit.setAll(true);
//        commit.setMessage("提交quartz thrift_test").call();
        
        //push 测试已可用
//        PushCommand pushCommond = git.push();//.call();
//        pushCommond.setCredentialsProvider(new UsernamePasswordCredentialsProvider("luoshuhong", "Luoshuhong008"));
//        pushCommond.call();
        return null;  
    }  
}
