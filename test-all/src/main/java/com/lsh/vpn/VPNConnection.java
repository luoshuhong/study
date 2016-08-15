package com.lsh.vpn;

/**
 * 
 * @author someone
 * @Company  
 * 2015年8月11日
 *
 */
public class VPNConnection{
	public static final int STATUS_UNDEFINED = 0; //未定义的状态
	public static final int STATUS_CONNECTED = 1; //已连接
	public static final int STATUS_DISCONNECTED = 2; //连接已断开
	public static final int STATUS_NETWORK_LOGIN = 3; //正在登录网络
	public static final int STATUS_DEVICE_CONNECTING = 4; //正在连接设备
	public static final int STATUS_DEVICE_CONNECTED = 5; //设备已连接
	public static final int STATUS_PORT_CONNECTING = 6; //正在连接端口
	public static final int STATUS_PORT_CONNECTED = 7; //端口已连接
	public static final int STATUS_AUTHENTICATEING = 8; //正在验证
	public static final int STATUS_REAUTHENTICATE = 9; //重新验证
	public static final int STATUS_AUTHENTICATE_NOTIFY = 10; //验证通知
	public static final int STATUS_AUTHENTICATE_WAITFORCALLBACK = 11; //等待回叫
	public static final int STATUS_AUTHENTICATE_START = 12; //开始验证
	public static final int STATUS_AUTHENTICATE_RESTART = 13; //验证重试
	public static final int STATUS_AUTHENTICATE_COMPLETED = 14; //验证完成
	
	public static final int ERROR_TIMEOUT = 0; //连接超时
	public static final int ERROR_CANNOTCREATE = -1; //无法创建VPN连接
	public static final int ERROR_SYSTEM = -2; //系统错误
	public static final int ERROR_CANNOTHANGUP = -3; //关闭连接错误

	public static final int STATUS_TIMEOUT = 0;
	
	private Connector connector = null;
	static{
		System.loadLibrary("VPN");
	}
	public VPNConnection(Connector connector){
		if(connector == null) throw new NullPointerException("Connector is null");
		this.connector = connector;
		this.initialization();
	}
	public void loadAccount(){
		this.loadAccount(connector);
	}
	public void normalMessage(int statusCode){
		connector.normalMessage(statusCode);
	}
	public void errorMessage(int errorCode, String message){
		connector.errorMessage(errorCode, message);
	}
	public void onConnected(){
		connector.onConnected();
	}
	public void onDisConnected(){
		connector.onDisConnected();
	}
	public Connector getConnector() {
		return connector;
	}
	private native void initialization();
	private native void loadAccount(Connector connector);
	public native boolean connect();
	public native boolean disconnect();
	public native boolean isActive();
	public native boolean deleteConnection();
}