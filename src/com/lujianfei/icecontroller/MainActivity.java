package com.lujianfei.icecontroller;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.lujianfei.icecontroller.exception.CrashApplication;
import com.lujianfei.icecontroller.services.SocketConnectionService;

/*
版权所有：版权所有(C)2013，固派软件
文件名称：com.lujianfei.pptcontroller.main.MainActivity.java
系统编号：
系统名称：SMART-IVI
模块编号：
模块名称：
设计文档：
完成日期：2013-6-2
作 者：陆键霏
内容摘要： 
类中的代码包括三个区段：类变量区、类属性区、类方法区。
文件调用:
 */
public class MainActivity extends Activity implements OnClickListener{
	String tag = "MainActivity";
	ProgressDialog pd = null;
	
	CrashApplication myApp = null;
	
	EditText edit_ip = null,edit_port=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myApp = (CrashApplication)CrashApplication.getContext();
		myApp.setHandler(mHandler);
		findViewById(R.id.bt_confirm).setOnClickListener(this);
		edit_ip = (EditText)findViewById(R.id.edit_ip);
		edit_port = (EditText)findViewById(R.id.edit_port);
	}
	 
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(data!=null){
			
			if(!myApp.isConnecting()){
				String ip = data.getStringExtra("ip");
				int port = data.getIntExtra("port",1234);
				log(String.format("requestCode=%s resultCode=%s ip=%s port=%s",
						requestCode,resultCode,ip,port));
				
				//连接服务端
				Intent intent_service = new Intent();
				intent_service.setClass(MainActivity.this, 
						SocketConnectionService.class);
				intent_service.putExtra(Common.MessageOfService.SERVICE_REQUEST,Common.MessageValueOfService.LAUNCH_TCP_CONNECTION_SERVICE);
				intent_service.putExtra(Common.MessageOfService.IP_ADDRESS,ip);
				intent_service.putExtra(Common.MessageOfService.IP_PORT,port);
				MainActivity.this.startService(intent_service);
			}else{
				Toast.makeText(this, "已连接", 200).show();
			}
		}else{
			log("data==null");
		}
	}

	Handler mHandler= new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch(msg.what){
			case Common.UI_CONNECTING://Searching
				pd.setMessage("正在连接中,请稍后...");
					break;
			case Common.UI_CONNECT_SUCCESFULLY://Found
				pd.setMessage("成功连接");
				pd.dismiss();
				Toast.makeText(MainActivity.this
						, "成功连接!", 200).show();
				//找到了
				break;
			case Common.UI_CONNECT_FAILED://没找到
				pd.setMessage("连接失败");
				pd.dismiss();
				Toast.makeText(MainActivity.this
						, "连接失败!", 200).show();
				break;
			}
		}
	};
	protected void onDestroy() {
		super.onDestroy();
		//连接服务端
		Intent intent_service = new Intent();
		intent_service.setClass(MainActivity.this, 
				SocketConnectionService.class);
		intent_service.putExtra(Common.MessageOfService.SERVICE_REQUEST,Common.MessageValueOfService.LAUNCH_TCP_DISCONNECTION_SERVICE);
		MainActivity.this.startService(intent_service);
		MainActivity.this.stopService(intent_service);
	};
	void log(String msg){
		Log.d(tag, msg);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		/*
		//请求上一页
		Intent intent_service = new Intent();
		intent_service.setClass(MainActivity.this, 
				SocketConnectionService.class);
		intent_service.putExtra(Common.MessageOfService.SERVICE_REQUEST,Common.MessageValueOfService.LAUNCH_FILE_PREVIOUS);
		MainActivity.this.startService(intent_service);
		*/
		if(R.id.bt_confirm == v.getId()){
			//请求上一页
			Intent intent_service = new Intent();
			intent_service.setClass(MainActivity.this, 
					SocketConnectionService.class);
			intent_service.putExtra(Common.MessageOfService.IP_ADDRESS, edit_ip.getText().toString());
			intent_service.putExtra(Common.MessageOfService.IP_PORT, Integer.parseInt(edit_port.getText().toString()));
			intent_service.putExtra(Common.MessageOfService.SERVICE_REQUEST,Common.MessageValueOfService.LAUNCH_TCP_CONNECTION_SERVICE);
			MainActivity.this.startService(intent_service);
			pd = ProgressDialog.show(this, "提示", "正在连接中...请稍候");
		}else if(R.id.bt_cancel == v.getId()){
			//请求上一页
			Intent intent_service = new Intent();
			intent_service.setClass(MainActivity.this, 
					SocketConnectionService.class);
			intent_service.putExtra(Common.MessageOfService.SERVICE_REQUEST,Common.MessageValueOfService.LAUNCH_TCP_DISCONNECTION_SERVICE);
			MainActivity.this.startService(intent_service);
		}if(R.id.bt_next == v.getId()){
			Intent intent_service = new Intent();
			intent_service.setClass(MainActivity.this, 
					SocketConnectionService.class);
			intent_service.putExtra(Common.MessageOfService.SERVICE_REQUEST,Common.MessageValueOfService.FUNCTION1);
			MainActivity.this.startService(intent_service);
		}
	}

}


