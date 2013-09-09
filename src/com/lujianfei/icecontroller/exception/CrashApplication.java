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
	 * ���ؿ���
	 * @param uino ����
	 * @param lightno �ƺ�
	 * @param status ״̬
	 */
	public void control_toggle(byte uino,int lightno,boolean status){
		byte[] data = new byte[6];
		data[0] = Protocol.FLAG_HEADER;
		data[1] = uino;
		data[2] = Protocol.FLAG_FUNCTION_ONOFF;
		data[3] = (byte) lightno; //�ƺ�
		data[4] = (status==true?Protocol.FLAG_FUNCTION_ONOFF_ON:Protocol.FLAG_FUNCTION_ONOFF_OFF); 
		data[5] = Protocol.FLAG_TAIL; 
		SocketSend(data);
		data = null;
	}
	/**
	 * ���ȿ���
	 * @param uino ����
	 * @param status ״̬ 
	 */
	public void control_bright_dark(byte uino,byte status){
		byte[] data = new byte[6];
		data[0] = Protocol.FLAG_HEADER;
		data[1] = uino;
		data[2] = Protocol.FLAG_FUNCTION_BRIGHTDARK;
		data[3] = 0; //�ƺ� 0Ϊ�ܿ���
		data[4] = status; 
		data[5] = Protocol.FLAG_TAIL; 
		SocketSend(data);
	}
	boolean cool = true;
	/**
	 * ������ɫ��ůɫ
	 * @param cool_warm
	 */
	public void setCool(boolean cool){
		this.cool = cool;
	}
	/**
	 * ���ůɫ��������
	 * @param warm_or_cool 
	 * @param status_increase ���»���̧��
	 */
	public void control_cool_or_warm_up(byte status_increase){
		byte[] data = new byte[6];
		data[0] = Protocol.FLAG_HEADER;
		data[1] = Protocol.FLAG_UI_COLOR;
		if(cool){
			//��ɫ
			data[2] = Protocol.FLAG_FUNCTION_COOL_BRIGHT;
		}else{
			//ůɫ
			data[2] = Protocol.FLAG_FUNCTION_WARM_BRIGHT;
		}
		data[3] = 0; 
		data[4] = status_increase; 
		data[5] = Protocol.FLAG_TAIL; 
		SocketSend(data);
	}
	
	/**
	 * ���ůɫ��������
	 * @param warm_or_cool 
	 * @param status_increase ���»���̧��
	 */
	public void control_cool_or_warm_down(byte status_increase){
		byte[] data = new byte[6];
		data[0] = Protocol.FLAG_HEADER;
		data[1] = Protocol.FLAG_UI_COLOR;
		if(cool){
			//��ɫ
			data[2] = Protocol.FLAG_FUNCTION_COOL_DARK;
		}else{
			//ůɫ
			data[2] = Protocol.FLAG_FUNCTION_WARM_DARK;
		}
		data[3] = 0; 
		data[4] = status_increase; 
		data[5] = Protocol.FLAG_TAIL; 
		SocketSend(data);
	}
	byte mode = Protocol.FLAG_FUNCTION_NIGHT;
	/**
	 * ����ģʽ
	 * @param mode
	 */
	public void setMode(byte mode){
		this.mode = mode;
	}
	/**
	 * ģʽ����
	 * @param ui ����
	 * @param lightno �ƺ�
	 */
	public void control_mode(byte ui,int lightno,boolean status){
		byte[] data = new byte[6];
		//����+ģʽ����+�ƺ�+00
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


