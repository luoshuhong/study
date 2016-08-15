package com.lsh.vpn;

/**
 * 
 * @author someone
 * @Company  
 * 2015年8月11日
 *
 */
public class VPNTest extends Connector{
	private VPNConnection connection = null;
	
	public static void main(String[] args){
//		System.out.println( System.getProperty("java.library.path"));
		
		VPNTest test = new VPNTest();
		test.setConnectionDomain("119.254.161.48");
		test.setConnectionAccount("luoshuhong");
		test.setConnectionPassword("Luoshuhong006");
		test.setConnectionName("zhe800");
		test.connect();
		try {
			Thread.sleep(1000);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public VPNTest(){
		connection = new VPNConnection(this);
	}
	
	public void connect(){
		connection.loadAccount();
		connection.connect();
	}

	public void normalMessage(int statusCode) {
		System.out.println("Normal:" + statusCode);
	}

	public void errorMessage(int errorCode, String message) {
		System.out.println("Error Code:" + errorCode + "\nMessage:" + message);
	}

	public void onConnected() {
		System.out.println("Connected");
	}
	
	public void onDisConnected() {
		connection.deleteConnection();
		System.out.println("DisConnected");
	}
}