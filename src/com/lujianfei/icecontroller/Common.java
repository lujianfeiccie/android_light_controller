package com.lujianfei.icecontroller;


/*
��Ȩ���У���Ȩ����(C)2013���������
�ļ����ƣ�com.goopai.android.bt.mainbt.Common.java
ϵͳ��ţ�
ϵͳ���ƣ�SMART-IVI
ģ���ţ�
ģ�����ƣ�
����ĵ���
������ڣ�2013-4-5
�� �ߣ�½����
����ժҪ����������
���еĴ�������������Σ���������������������෽������
�ļ�����:
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
	/**
	 * ����˹رշ���
	 */
	public static final int UI_CONNECTION_TERMINATED = 8;
	
	public static class MessageOfService{
		/**
		 * ��������
		 */
		public static final String SERVICE_REQUEST = "SERVICE_REQUEST";
		/**
		 * IP��ַ
		 */
		public static final String IP_ADDRESS = "IP_ADDRESS";
		/**
		 * �˿�
		 */
		public static final String IP_PORT = "IP_PORT";
		/**
		 * ������ļ���
		 */
		public static final String FILE_NAME= "FILE_NAME";
	}
	
	public static class MessageValueOfService{
		/**
		 * ����Tcp���ӷ���
		 */
		public static final String LAUNCH_TCP_CONNECTION_SERVICE= "LAUNCH_TCP_CONNECTION_SERVICE";
		/**
		 * �ر�Tcp�Ͽ����ӷ���
		 */
		public static final String LAUNCH_TCP_DISCONNECTION_SERVICE= "LAUNCH_TCP_DISCONNECTION_SERVICE";
		public static final String FUNCTION1= "FUNCTION1";
		public static final String FUNCTION2 = "FUNCTION2";
	}
}