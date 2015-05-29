package com.lsh.restlet;

import org.restlet.Component;
import org.restlet.data.Protocol;

public class Service {
	
	public static void main(String[] args) throws Exception {
		
		//启动服务 （发布资源）
//		Server server = new Server(Protocol.HTTP, 8888, Resource.class);
//		server.start();
		
		Component comp = new Component() ;
		comp.getServers().add(Protocol.HTTP, 8888) ;
		comp.getDefaultHost().attach("/quartz", new Resource().getClass()) ;
//		comp.getDefaultHost().attach("/jackson", Resource.class) ;
		comp.start(); 
	}
}
