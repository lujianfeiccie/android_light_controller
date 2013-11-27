package com.lujianfei.icecontroller.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ConnectionInfo implements Parcelable {
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
	
	 @Override
	 public void writeToParcel(Parcel dest, int flags) {
	 // TODO Auto-generated method stub
	 		dest.writeInt(id);  
	        dest.writeString(addr);  
	        dest.writeString(name);  
	        dest.writeInt(port);  
	 }
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**     
     * ��Ҫ��������ֶδ����µĶ��� 
     * Ҫô�Ե����������ʽ��Ҫô���������ʽ 
     * ����ζ������ʹ��Ĭ�Ϲ��췽�������������ʹ���������췽�� 
     */  
	public static final Parcelable.Creator<ConnectionInfo> CREATOR = new Parcelable.Creator<ConnectionInfo>() {  
         public ConnectionInfo createFromParcel(Parcel source) {  
        	 ConnectionInfo mConnectionInfo = new ConnectionInfo();  
        	 mConnectionInfo.id=source.readInt();  
        	 mConnectionInfo.addr=source.readString();  
        	 mConnectionInfo.name=source.readString();  
        	 mConnectionInfo.port=source.readInt();  
             return mConnectionInfo;  
         }  
         public ConnectionInfo[] newArray(int size) {  
             return new ConnectionInfo[size];  
         }  
  };  
}
