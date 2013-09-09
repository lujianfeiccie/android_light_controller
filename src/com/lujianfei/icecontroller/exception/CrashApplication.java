package com.lujianfei.icecontroller.exception;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.util.Log;

import com.lujianfei.icecontroller.Common;
import com.lujianfei.icecontroller.MainActivity;
import com.lujianfei.icecontroller.Protocol;
import com.lujianfei.icecontroller.model.ConnectionInfo;
import com.lujianfei.icecontroller.services.SocketConnectionService;
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
	public void saveConnectionInfo(String addr,int port){
		   Editor sharedata = getSharedPreferences("data", 0).edit();   
		   sharedata.putString(Common.MessageOfService.IP_ADDRESS,addr);
		   sharedata.putInt(Common.MessageOfService.IP_PORT, port);
		   sharedata.commit();  
	}
	public ConnectionInfo loadConnectionInfo(){
		ConnectionInfo mConnectionInfo= new ConnectionInfo();
		SharedPreferences sharedata = getSharedPreferences("data", 0);   
		mConnectionInfo.setAddr(sharedata.getString(Common.MessageOfService.IP_ADDRESS, "192.168.1.101"));
		mConnectionInfo.setPort(sharedata.getInt(Common.MessageOfService.IP_PORT,8888));
		return mConnectionInfo;
	}
	
	public void SocketConnect(String ip,int port){
		Intent intent_service = new Intent();
		intent_service.setClass(this, 
				SocketConnectionService.class);
		intent_service.putExtra(Common.MessageOfService.SERVICE_REQUEST,Common.MessageValueOfService.LAUNCH_TCP_CONNECTION_SERVICE);
		intent_service.putExtra(Common.MessageOfService.IP_ADDRESS,ip);
		intent_service.putExtra(Common.MessageOfService.IP_PORT,port);
		startService(intent_service);
	}
	
	public void SocketDisconnect(){
		Intent intent_service = new Intent();
		intent_service.setClass(this, 
				SocketConnectionService.class);
		intent_service.putExtra(Common.MessageOfService.SERVICE_REQUEST,Common.MessageValueOfService.LAUNCH_TCP_DISCONNECTION_SERVICE);
		startService(intent_service);
	}
	
	public void SocketSend(byte[] data){
		Intent intent_service = new Intent();
		intent_service.setClass(this, 
				SocketConnectionService.class);
		intent_service.putExtra(Common.MessageOfService.SERVICE_REQUEST,Common.MessageValueOfService.SEND_MESSAGE);
		intent_service.putExtra(Common.MessageOfService.BYTE_ARRAY,data);
		startService(intent_service);
	}
	/**
	 * 开关控制
	 * @param uino 界面
	 * @param lightno 灯号
	 * @param status 状态
	 */
	public void control_toggle(byte uino,int lightno,boolean status){
		byte[] data = new byte[6];
		data[0] = Protocol.FLAG_HEADER;
		data[1] = uino;
		data[2] = Protocol.FLAG_FUNCTION_ONOFF;
		data[3] = (byte) lightno; //灯号
		data[4] = (status==true?Protocol.FLAG_FUNCTION_ONOFF_ON:Protocol.FLAG_FUNCTION_ONOFF_OFF); 
		data[5] = Protocol.FLAG_TAIL; 
		SocketSend(data);
		data = null;
	}
	/**
	 * 亮度控制
	 * @param uino 界面
	 * @param status 状态 
	 */
	public void control_bright_dark(byte uino,byte status){
		byte[] data = new byte[6];
		data[0] = Protocol.FLAG_HEADER;
		data[1] = uino;
		data[2] = Protocol.FLAG_FUNCTION_BRIGHTDARK;
		data[3] = 0; //灯号 0为总开关
		data[4] = status; 
		data[5] = Protocol.FLAG_TAIL; 
		SocketSend(data);
	}
	boolean cool = true;
	/**
	 * 设置冷色或暖色
	 * @param cool_warm
	 */
	public void setCool(boolean cool){
		this.cool = cool;
	}
	/**
	 * 冷或暖色调亮控制
	 * @param warm_or_cool 
	 * @param status_increase 按下还是抬起
	 */
	public void control_cool_or_warm_up(byte status_increase){
		byte[] data = new byte[6];
		data[0] = Protocol.FLAG_HEADER;
		data[1] = Protocol.FLAG_UI_COLOR;
		if(cool){
			//冷色
			data[2] = Protocol.FLAG_FUNCTION_COOL_BRIGHT;
		}else{
			//暖色
			data[2] = Protocol.FLAG_FUNCTION_WARM_BRIGHT;
		}
		data[3] = 0; 
		data[4] = status_increase; 
		data[5] = Protocol.FLAG_TAIL; 
		SocketSend(data);
	}
	
	/**
	 * 冷或暖色调暗控制
	 * @param warm_or_cool 
	 * @param status_increase 按下还是抬起
	 */
	public void control_cool_or_warm_down(byte status_increase){
		byte[] data = new byte[6];
		data[0] = Protocol.FLAG_HEADER;
		data[1] = Protocol.FLAG_UI_COLOR;
		if(cool){
			//冷色
			data[2] = Protocol.FLAG_FUNCTION_COOL_DARK;
		}else{
			//暖色
			data[2] = Protocol.FLAG_FUNCTION_WARM_DARK;
		}
		data[3] = 0; 
		data[4] = status_increase; 
		data[5] = Protocol.FLAG_TAIL; 
		SocketSend(data);
	}
	byte mode = Protocol.FLAG_FUNCTION_NIGHT;
	/**
	 * 设置模式
	 * @param mode
	 */
	public void setMode(byte mode){
		this.mode = mode;
	}
	/**
	 * 模式控制
	 * @param ui 界面
	 * @param lightno 灯号
	 */
	public void control_mode(byte ui,int lightno,boolean status){
		byte[] data = new byte[6];
		//界面+模式代码+灯号+00
		data[0] = Protocol.FLAG_HEADER;
		data[1] = ui;
		data[2] = mode;
		data[3] = (byte) lightno; 
		data[4] = (status==true?Protocol.FLAG_FUNCTION_ONOFF_ON:Protocol.FLAG_FUNCTION_ONOFF_OFF); 
		data[5] = Protocol.FLAG_TAIL; 
		SocketSend(data);
	}
	
	void log(String msg){
		Log.d(tag, msg);
	}
}


