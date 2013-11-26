package com.lujianfei.icecontroller;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lujianfei.icecontroller.R;
import com.lujianfei.icecontroller.exception.CrashApplication;
import com.lujianfei.icecontroller.model.ConnectionInfo;
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
public class MainActivity extends Activity implements OnClickListener,android.widget.CompoundButton.OnCheckedChangeListener{
	String tag = "MainActivity";
	ProgressDialog pd = null;
	
	CrashApplication myApp = null;
	
	EditText edit_ip = null,edit_port=null;
	
	TextView txt_status_msg = null;
	
	boolean isDisconnectedByMyself = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		myApp = (CrashApplication)CrashApplication.getContext();
		myApp.setHandler(mHandler);
		
		//Initialize View
		initView();
		
		//Initialize Data
	    initData();
	}
	 private void initData() {
		// TODO Auto-generated method stub
		 ConnectionInfo  mConnectionInfo = myApp.loadConnectionInfo();
		 edit_ip.setText(mConnectionInfo.getAddr());
		 edit_port.setText(""+mConnectionInfo.getPort());
	}
	void initView(){
		 	findViewById(R.id.bt_confirm).setOnClickListener(this);
			findViewById(R.id.bt_cancel).setOnClickListener(this);
			
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
				Toast.makeText(this,R.string.mainactivity_connected, 200).show();
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
				log("UI_CONNECTING");
				pd.setMessage(getResources().getString(R.string.mainactivity_connecting));
					break;
			case Common.UI_CONNECT_SUCCESFULLY://Found
				log("UI_CONNECT_SUCCESFULLY");
				pd.setMessage(getResources().getString(R.string.mainactivity_connected_successfully));
				pd.dismiss();
				Toast.makeText(MainActivity.this
						, R.string.mainactivity_connected_successfully, 200).show();
				myApp.saveConnectionInfo(edit_ip.getText().toString(),
						Integer.parseInt(edit_port.getText().toString()));
				//找到了
				txt_status_msg.setText(R.string.mainactivity_connected);
				break;
			case Common.UI_CONNECT_FAILED:{//没找到
				log("UI_CONNECT_FAILED");
				pd.setMessage(getResources().getString(R.string.mainactivity_connect_failed));
				pd.dismiss();
				//连接后发送时出现的异常
				DialogConnectionTerminated();
				}
				break;
			case Common.UI_SEND_IOEXCEPTION:{
				log("UI_SEND_IOEXCEPTION");
				//连接后发送时出现的异常
				DialogConnectionTerminated();
				}
				break;
			case Common.UI_CONNECTION_TERMINATED:{
				log("UI_CONNECTION_TERMINATED");
				if(isDisconnectedByMyself) break;
				//连接后发送时出现的异常
				DialogConnectionTerminated();
				}
				break;
			case Common.UI_CONNECTED:{
				log("UI_CONNECTED");
				pd.setMessage(getResources().getString(R.string.mainactivity_connected));
				pd.dismiss();
				Toast.makeText(MainActivity.this, R.string.mainactivity_connected, 200).show();
				txt_status_msg.setText(R.string.mainactivity_connected);
				}
			case Common.UI_CONNECTION_STATE:{
				log("UI_CONNECTION_STATE");
				if(msg.arg1 == Common.UI_CONNECTION_STATE_CONNECTED){
					txt_status_msg.setText(R.string.mainactivity_connected);
					log("CONNECTED");
				}else{
					txt_status_msg.setText(R.string.mainactivity_disconnected);
					log("DISCONNECTED");
				}
			}
			break;
			}
			
		}
	};
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(R.id.bt_confirm == v.getId()){
			//连接请求
			RequestServiceConnect(edit_ip.getText().toString(),Integer.parseInt(edit_port.getText().toString()));
		}else if(R.id.bt_cancel == v.getId()){
			//断开请求
			RequestServiceDisconnect();
		}
	}
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if(isChecked){
		}
	};
	void RequestServiceConnect(String addr,int port){
		Pattern pattern = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
		Matcher matcher = pattern.matcher(addr); //以验证127.400.600.2为例
		if(!matcher.matches()){
			Toast.makeText(this, R.string.setting_layout_ip_error, 200).show();
			return;
		}
		if(port<=1024 || port>=65535){
			Toast.makeText(this, R.string.setting_layout_port_error, 200).show();
			return;
		}
		//连接请求
		Intent intent_service = new Intent();
		intent_service.setClass(MainActivity.this, 
				SocketConnectionService.class);
		intent_service.putExtra(Common.MessageOfService.IP_ADDRESS, addr);
		intent_service.putExtra(Common.MessageOfService.IP_PORT, port);
		intent_service.putExtra(Common.MessageOfService.SERVICE_REQUEST,Common.MessageValueOfService.LAUNCH_TCP_CONNECTION_SERVICE);
		MainActivity.this.startService(intent_service);
		pd = ProgressDialog.show(this,getResources().getString(R.string.mainactivity_title),getResources().getString(R.string.mainactivity_connecting));
		isDisconnectedByMyself = false;
	}
	
	void RequestServiceDisconnect(){
		//连接请求
		log("RequestServiceDisconnect");
		Toast.makeText(this, "已断开!", 200).show();
		isDisconnectedByMyself = true;
		Intent intent_service = new Intent();
		intent_service.setClass(MainActivity.this, 
				SocketConnectionService.class);
		intent_service.putExtra(Common.MessageOfService.SERVICE_REQUEST,Common.MessageValueOfService.LAUNCH_TCP_DISCONNECTION_SERVICE);
		MainActivity.this.startService(intent_service);
	}
	
	
	void RequestServiceFunction(String requestType){
		Intent intent_service = new Intent();
		intent_service.setClass(MainActivity.this, 
				SocketConnectionService.class);
		intent_service.putExtra(Common.MessageOfService.SERVICE_REQUEST,requestType);
		MainActivity.this.startService(intent_service);
	}
	
	void DialogConnectionTerminated(){
		//连接后发送时出现的异常
		Dialog alertDialog = new AlertDialog.Builder(MainActivity.this). 
                setTitle(R.string.mainactivity_title). 
                setMessage(R.string.mainactivity_connect_error). 
                setIcon(R.drawable.icon). 
                setPositiveButton(R.string.mainactivity_ok, new DialogInterface.OnClickListener() { 
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
                        // TODO Auto-generated method stub 
                    	dialog.dismiss();
                    	RequestServiceConnect(edit_ip.getText().toString(), Integer.parseInt(edit_port.getText().toString()));
                    } 
                }). 
                setNegativeButton(R.string.mainactivity_cancel, new DialogInterface.OnClickListener() { 
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
                        // TODO Auto-generated method stub  
                    	dialog.dismiss();
                    } 
                }). 
                create(); 
        alertDialog.show(); 
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		 if(keyCode==KeyEvent.KEYCODE_BACK){
			 //弹出确定退出对话框
			 new AlertDialog.Builder(this)
			  .setTitle(R.string.mainactivity_title)
			  .setMessage(R.string.mainactivity_exit_message)
			  .setPositiveButton(R.string.mainactivity_ok, new DialogInterface.OnClickListener() {
			     @Override
                 public void onClick(DialogInterface dialog, int which) {
                     // TODO Auto-generated method stub
			    	dialog.cancel();
                    MainActivity.this.finish();
                 }
             })
            .setNegativeButton(R.string.mainactivity_cancel, new DialogInterface.OnClickListener() {
                 @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                     dialog.cancel();
                 }
             })
             .show();
             //这里不需要执行父类的点击事件，所以直接return
             return true;
         }
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		log("onResume");
		RequestServiceFunction(Common.MessageValueOfService.FUNCTION_STATUS);
	}

	protected void onDestroy() {
		super.onDestroy();
		myApp.setHandler(null);
		//连接服务端
		Intent intent_service = new Intent();
		intent_service.setClass(MainActivity.this, 
				SocketConnectionService.class);
//		intent_service.putExtra(Common.MessageOfService.SERVICE_REQUEST,Common.MessageValueOfService.LAUNCH_TCP_DISCONNECTION_SERVICE);
//		MainActivity.this.startService(intent_service);
		MainActivity.this.stopService(intent_service);
		
	}
	void log(String msg){
		Log.d(tag, msg);
	}
}


