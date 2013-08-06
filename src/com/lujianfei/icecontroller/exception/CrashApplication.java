package com.lujianfei.icecontroller.exception;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.lujianfei.icecontroller.utilities.SystemHelper;

/*
��Ȩ���У���Ȩ����(C)2013���������
�ļ����ƣ�com.goopai.android.bt.exception.CrashApplication.java
ϵͳ��ţ�
ϵͳ���ƣ�SMART-IVI
ģ���ţ�
ģ�����ƣ�
����ĵ���
������ڣ�2013-5-1
�� �ߣ�½����
����ժҪ�� ���ڱ�����ֳ��õĳ���,����������״̬��ȫ�������ĵȵ�
���еĴ�������������Σ���������������������෽������
�ļ�����:
 */
public class CrashApplication extends Application {
	private String tag = "CrashApplication";
	/**
	 * ȫ��������
	 * @return
	 */
	private static Context _context;
	
	public static Context getContext() {
		return _context;
	}
	
	private String ip_addr;
	private int port;
	private boolean Connecting = false;
	
	private Handler mHandler = null;
	
	public Handler getHandler() {
		return mHandler;
	}
	public void setHandler(Handler mHandler) {
		this.mHandler = mHandler;
	}
	public boolean isConnecting() {
		return Connecting;
	}
	public void setConnecting(boolean connecting) {
		Connecting = connecting;
	}
	public String getIp_addr() {
		return ip_addr;
	}
	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}

    public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	@Override  
    public void onCreate() {  
        super.onCreate();  
        //ȫ��������
        _context = this;
        Connecting = false;
        CrashHandler crashHandler = CrashHandler.getInstance();  
        crashHandler.init(getApplicationContext()); 
        crashHandler.setCrashListener(crashListener);
    }  
	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
		SystemHelper.killOtherRunningService();
	}
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}
	private CrashListener crashListener = new CrashListener(){
		@Override
		public void onCrashReceiver() {
			// TODO Auto-generated method stub
		}
	};
	void log(String msg){
		Log.d(tag, msg);
	}
}


