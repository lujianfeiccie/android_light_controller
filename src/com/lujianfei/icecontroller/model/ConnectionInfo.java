package com.lujianfei.icecontroller.model;

public class ConnectionInfo {
	private int id;
	private String addr;
	private String name;
	private int port;
	
	public ConnectionInfo(){
		
	}
	public ConnectionInfo(String addr,int port,String name){
		this.addr = addr;
		this.port = port;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String toString(){
		return String.format("id=%s addr=%s port=%s name=%s",id,addr,port,name);
	}
}
