package com.lujianfei.icecontroller;


/*
版权所有：版权所有(C)2013，固派软件
文件名称：com.goopai.android.bt.mainbt.Common.java
系统编号：
系统名称：SMART-IVI
模块编号：
模块名称：
设计文档：
完成日期：2013-4-5
作 者：陆键霏
内容摘要：公共变量
类中的代码包括三个区段：类变量区、类属性区、类方法区。
文件调用:
 */

public class Common {
	public static final int port = 5000;
	public static final int tcp_timeout = 1000;
	public static final int ndk_port = 6666;
	public static final int UIFLAG = 1;
	
	public static final int UI_CONNECTING = 2;
	public static final int UI_CONNECT_SUCCESFULLY = 3;
	public static final int UI_CONNECT_FAILED = 4;
	public static final int UI_DISCONNECT = 5;
	public static final int UI_MESSAGE = 6;
	public static final int UI_SEND_IOEXCEPTION = 7;
	public static final int UI_CONNECTED = 8;
	/**
	 * 服务端关闭服务
	 */
	public static final int UI_CONNECTION_TERMINATED = 9;
	
	
	public static class MessageOfService{
		/**
		 * 服务请求
		 */
		public static final String SERVICE_REQUEST = "SERVICE_REQUEST";
		/**
		 * IP地址
		 */
		public static final String IP_ADDRESS = "IP_ADDRESS";
		/**
		 * 端口
		 */
		public static final String IP_PORT = "IP_PORT";
		/**
		 * 请求打开文件名
		 */
		public static final String FILE_NAME= "FILE_NAME";
	}
	
	public static class MessageValueOfService{
		/**
		 * 启动Tcp连接服务
		 */
		public static final String LAUNCH_TCP_CONNECTION_SERVICE= "LAUNCH_TCP_CONNECTION_SERVICE";
		/**
		 * 关闭Tcp断开连接服务
		 */
		public static final String LAUNCH_TCP_DISCONNECTION_SERVICE= "LAUNCH_TCP_DISCONNECTION_SERVICE";
		
		/**
		 * 功能
		 */
		public static final String FUNCTION1_ON= "FUNCTION1_ON";
		public static final String FUNCTION1_OFF= "FUNCTION1_OFF";
		
		public static final String FUNCTION2_ON = "FUNCTION2_ON";
		public static final String FUNCTION2_OFF = "FUNCTION2_OFF";
		
		public static final String FUNCTION3_ON = "FUNCTION3_ON";
		public static final String FUNCTION3_OFF = "FUNCTION3_OFF";
		
		public static final String FUNCTION4_ON = "FUNCTION4_ON";
		public static final String FUNCTION4_OFF = "FUNCTION4_OFF";
		
		public static final String FUNCTION5_ON = "FUNCTION5_ON";
		public static final String FUNCTION5_OFF = "FUNCTION5_OFF";
		
		public static final String FUNCTION6_ON = "FUNCTION6_ON";
		public static final String FUNCTION6_OFF = "FUNCTION6_OFF";
	}
}