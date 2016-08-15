package com.lsh.vpn;

/**
 * 
 * @author someone
 * @Company  
 * 2015年8月11日
 *
 */
public abstract class Connector {
	protected String connectionDomain = null;
	protected String connectionAccount = null;
	protected String connectionPassword = null;
	protected String connectionName = null;
	
	public Connector(){
		this(null,null,null);
	}
	
	public Connector(String connectionDomain,String connectionAccount,String connectionPassword){
		this(connectionDomain,connectionAccount,connectionPassword,connectionDomain);
	}

	public Connector(String connectionDomain,String connectionAccount,String connectionPassword,String connectionName){
		this.connectionDomain = connectionDomain;
		this.connectionAccount = connectionAccount;
		this.connectionPassword = connectionPassword;
		this.connectionName = connectionName;
	}

	protected String getConnectionDomain() {
		return connectionDomain;
	}
	public void setConnectionDomain(String connectionDomain) {
		this.connectionDomain = connectionDomain;
	}
	protected String getConnectionAccount() {
		return connectionAccount;
	}
	public void setConnectionAccount(String connectionAccount) {
		this.connectionAccount = connectionAccount;
	}
	protected String getConnectionPassword() {
		return connectionPassword;
	}
	public void setConnectionPassword(String connectionPassword) {
		this.connectionPassword = connectionPassword;
	}
	protected String getConnectionName() {
		return connectionName;
	}
	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}
	public abstract void normalMessage(int statusCode);
	public abstract void errorMessage(int errorCode, String message);
	public abstract void onConnected();
	public abstract void onDisConnected();
}