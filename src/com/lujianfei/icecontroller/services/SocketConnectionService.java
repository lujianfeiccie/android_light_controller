package com.lujianfei.icecontroller.services;


import java.io.IOException;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.lujianfei.icecontroller.Common;
import com.lujianfei.icecontroller.Protocol;
import com.lujianfei.icecontroller.exception.CrashApplication;
import com.lujianfei.icecontroller.socket.IpAddress;
import com.lujianfei.icecontroller.socket.TcpConnection;
import com.lujianfei.icecontroller.socket.TcpConnectionListener;
import com.lujianfei.icecontroller.socket.TcpSocket;
import com.lujianfei.icecontroller.socket.TcpSocketListener;

/*
��Ȩ���У���Ȩ����(C)2013���������
�ļ����ƣ�com.goopai.android.bt.services.RotationService.java
ϵͳ��ţ�
ϵͳ���ƣ�SMART-IVI
ģ���ţ�
ģ�����ƣ�
����ĵ���
������ڣ�2013-4-24
�� �ߣ�½����
����ժҪ�� �����ͻ������ӵľ���ʵ��

���еĴ�������������Σ���������������������෽������
�ļ�����:
*/

public class SocketConnectionService extends Service {
	private String TAG = "SocketConnectionService";
	/**
	 * ��ȡUI�߳�
	 */
	Handler handler = null;
	
	/**
	 * ����������̵��жϱ�־
	 */
	boolean process_flag = false;
	
	ActivityManager mActivityManager = null;
	
	TcpConnection _TcpConnection = null;
	
	CrashApplication myApp = null;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		log("onCreate");
		
		myApp = (CrashApplication)CrashApplication.getContext();
		_TcpConnection = null;
	}
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		log("onStart");
		Bundle msg = intent.getExtras();
		String msg_request = msg.getString(Common.MessageOfService.SERVICE_REQUEST);
		
		if(msg_request !=null){
			//�������������
			if(msg_request.equals(Common.MessageValueOfService.LAUNCH_TCP_CONNECTION_SERVICE)){
				log("LAUNCH_TCP_CONNECTION_SERVICE");
				//ȡ�������豸�ĵ�ַ
				String ip_addr = msg.getString(Common.MessageOfService.IP_ADDRESS);
				int port = msg.getInt(Common.MessageOfService.IP_PORT);
				
				if(ip_addr!=null && port!=0){
					log("ip_addr="+ip_addr);
					TcpSocket _Socket = null;
					if(_TcpConnection ==null){
						try {
							_Socket = new TcpSocket(new IpAddress(ip_addr),port,_TcpSocketListener);
							if(_TcpConnection == null){
								_TcpConnection = new TcpConnection(_Socket,_TcpConnectionListener);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
					}else if(_TcpConnection.isRunning()){
						if (myApp.getHandler() != null) {
							myApp.getHandler().sendEmptyMessage(
									Common.UI_CONNECTED);
						}
					}
				}
			}
			else if(msg_request.equals(Common.MessageValueOfService.LAUNCH_TCP_DISCONNECTION_SERVICE)){
				log("LAUNCH_TCP_DISCONNECTION_SERVICE");
				//�ر���������
				if(_TcpConnection!=null && _TcpConnection.isRunning()){
					//ExitClient();
					_TcpConnection.halt();
					try {
						_TcpConnection.getSocket().close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						log("ExitClient:"+e.getMessage());
					}
					_TcpConnection = null;
				}
				myApp.setConnecting(false);
			} else if(msg_request.equals(Common.MessageValueOfService.FUNCTION_STATUS)){
				log("FUNCTION_STATUS");
				//��ȡ״̬
				if(_TcpConnection!=null && _TcpConnection.isRunning()){
					if (myApp.getHandler() != null) {
						Message handler_msg = new Message();
						handler_msg.what = Common.UI_CONNECTION_STATE;
						handler_msg.arg1 = Common.UI_CONNECTION_STATE_CONNECTED;
						myApp.getHandler().sendMessage(
								handler_msg);
					}
					myApp.setConnecting(true);
				}else{
					if (myApp.getHandler() != null) {
						Message handler_msg = new Message();
						handler_msg.what = Common.UI_CONNECTION_STATE;
						handler_msg.arg1 = Common.UI_CONNECTION_STATE_DISCONNECTED;
						myApp.getHandler().sendMessage(
								handler_msg);
					}
					myApp.setConnecting(false);
				}
			} else if(msg_request.equals(Common.MessageValueOfService.SEND_MESSAGE)){
				log("SEND_MESSAGE");
				if(_TcpConnection!=null && _TcpConnection.isRunning()){
					byte[] data = msg.getByteArray(Common.MessageOfService.BYTE_ARRAY);
					if(data!=null){
						try {
							_TcpConnection.send(data);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}// end of msg_request.equals(Common.MessageValueOfService.LAUNCH_BT_CONNECTION_SERVICE)
		}//end of if(msg_request !=null)
	}
	 
	TcpSocketListener _TcpSocketListener = new TcpSocketListener(){

		@Override
		public void onConnectSuccesfull(String ip, int port) {
			log("onConnectSuccesfull "+ip+":"+port);
			myApp.setIp_addr(ip);
			myApp.setPort(port);
			myApp.setConnecting(true);
			myApp.saveConnectionInfo(ip, port);
			myApp.getHandler().sendEmptyMessage(Common.UI_CONNECT_SUCCESFULLY);
		}

		@Override
		public void onConnectFailed() {
			log("onConnectFailed");
			myApp.setConnecting(false);
			myApp.getHandler().sendEmptyMessage(Common.UI_CONNECT_FAILED);
		}
		
	};
	TcpConnectionListener _TcpConnectionListener = new TcpConnectionListener()
	{
		@Override
		public void onReceivedData(TcpConnection tcp_conn, byte[] buffer, int len) {
			// TODO Auto-generated method stub
		}
		public String bytesToHexString(byte[] src){  
		    StringBuilder stringBuilder = new StringBuilder("");  
		    if (src == null || src.length <= 0) {  
		        return null;  
		    }  
		    for (int i = 0; i < src.length; i++) {  
		        int v = src[i] & 0xFF;  
		        String hv = Integer.toHexString(v);  
		        if (hv.length() < 2) {  
		            stringBuilder.append(0);  
		        }  
		        stringBuilder.append(hv.toUpperCase()+" ");  
		    }  
		    return stringBuilder.toString();  
		}  
		@Override
		public void onConnectionTerminated(TcpConnection tcp_conn,
				Exception error) {
			// TODO Auto-generated method stub
			log("onConnectionTerminated");
			if (myApp.getHandler() != null) {
				myApp.getHandler().sendEmptyMessage(
						Common.UI_CONNECTION_TERMINATED);
			}
			myApp.setConnecting(false);
			_TcpConnection = null;
		}
	};
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		log("onDestroy");
//		if(bt_client!=null){
//			bt_client.ExitThread();
//		}
		if(_TcpConnection!=null && _TcpConnection.isRunning()){
			//ExitClient();
			_TcpConnection.halt();
			try {
				_TcpConnection.getSocket().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				log("ExitClient:"+e.getMessage());
			}
			_TcpConnection = null;
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	} 
	
	void log(String msg){
		Log.d(TAG,msg);
		//MyLog.d(TAG, msg);
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}
