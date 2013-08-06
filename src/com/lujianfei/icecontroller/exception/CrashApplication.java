package com.lujianfei.icecontroller.exception;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.lujianfei.icecontroller.utilities.SystemHelper;

/*
版权所有：版权所有(C)2013，固派软件
文件名称：com.goopai.android.bt.exception.CrashApplication.java
系统编号：
系统名称：SMART-IVI
模块编号：
模块名称：
设计文档：
完成日期：2013-5-1
作 者：陆键霏
内容摘要： 用于保存各种常用的常量,如蓝牙连接状态、全局上下文等等
类中的代码包括三个区段：类变量区、类属性区、类方法区。
文件调用:
 */
public class CrashApplication extends Application {
	private String tag = "CrashApplication";
	/**
	 * 全局上下文
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
        //全局上下文
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


