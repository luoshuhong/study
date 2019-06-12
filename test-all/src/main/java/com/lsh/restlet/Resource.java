package com.lsh.restlet;

import javax.ws.rs.Path;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

/**
 * 资源类 相当于 servlet
 * @author Luoshuhong
 * 2015年3月27日
 *
 */
public class Resource extends ServerResource{  
	
	@Override
	public void init(Context context, Request request, Response response) {
		super.init(context, request, response);
		//如果有需要，可以在该处加入初始化代码
	}
	
	@Get
	@Path("/pay")
	public String play(){
		Form form = getRequest().getResourceRef().getQueryAsForm() ;	//获取查询参数
		String taskName = form.getFirstValue("taskName");		 
		return "quartz 任务正在运行，任务名称：" + taskName;
	}
	
	@Get
	@Path("/pay1")
	public String play1(){
		Form form = getRequest().getResourceRef().getQueryAsForm() ;	//获取查询参数
		String taskName = form.getFirstValue("taskName");		 
		return "quartz play1 任务正在运行，任务名称：" + taskName;
	}
	
	
	@Post
	public String pause(){
		Form form = getRequest().getResourceRef().getQueryAsForm() ;	//获取查询参数
		String taskName = form.getFirstValue("taskName");		 
		return "quartz 任务暂停，任务名称：" + taskName;
	}
	
	@Put
	public String upload(){
		return "quartz 更新任务...";
	}
	
	@Delete
	public String deleteMovie(){
		return "quartz 删除任务...";
	}

}
