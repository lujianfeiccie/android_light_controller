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
��Ȩ���У���Ȩ����(C)2013���������
�ļ����ƣ�com.lujianfei.pptcontroller.main.MainActivity.java
ϵͳ��ţ�
ϵͳ���ƣ�SMART-IVI
ģ���ţ�
ģ�����ƣ�
����ĵ���
������ڣ�2013-6-2
�� �ߣ�½����
����ժҪ�� 
���еĴ�������������Σ���������������������෽������
�ļ�����:
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
				
				//���ӷ����
				Intent intent_service = new Intent();
				intent_service.setClass(MainActivity.this, 
						SocketConnectionService.class);
				intent_service.putExtra(Common.MessageOfService.SERVICE_REQUEST,Common.MessageValueOfService.LAUNCH_TCP_CONNECTION_SERVICE);
				intent_service.putExtra(Common.MessageOfService.IP_ADDRESS,ip);
				intent_service.putExtra(Common.MessageOfService.IP_PORT,port);
				MainActivity.this.startService(intent_service);
			}else{
				Toast.makeText(this, "������", 200).show();
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
				pd.setMessage("����������,���Ժ�...");
					break;
			case Common.UI_CONNECT_SUCCESFULLY://Found
				pd.setMessage("�ɹ�����");
				pd.dismiss();
				Toast.makeText(MainActivity.this
						, "�ɹ�����!", 200).show();
				//�ҵ���
				break;
			case Common.UI_CONNECT_FAILED://û�ҵ�
				pd.setMessage("����ʧ��");
				pd.dismiss();
				Toast.makeText(MainActivity.this
						, "����ʧ��!", 200).show();
				break;
			}
		}
	};
	protected void onDestroy() {
		super.onDestroy();
		//���ӷ����
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
		//������һҳ
		Intent intent_service = new Intent();
		intent_service.setClass(MainActivity.this, 
				SocketConnectionService.class);
		intent_service.putExtra(Common.MessageOfService.SERVICE_REQUEST,Common.MessageValueOfService.LAUNCH_FILE_PREVIOUS);
		MainActivity.this.startService(intent_service);
		*/
		if(R.id.bt_confirm == v.getId()){
			//������һҳ
			Intent intent_service = new Intent();
			intent_service.setClass(MainActivity.this, 
					SocketConnectionService.class);
			intent_service.putExtra(Common.MessageOfService.IP_ADDRESS, edit_ip.getText().toString());
			intent_service.putExtra(Common.MessageOfService.IP_PORT, Integer.parseInt(edit_port.getText().toString()));
			intent_service.putExtra(Common.MessageOfService.SERVICE_REQUEST,Common.MessageValueOfService.LAUNCH_TCP_CONNECTION_SERVICE);
			MainActivity.this.startService(intent_service);
			pd = ProgressDialog.show(this, "��ʾ", "����������...���Ժ�");
		}else if(R.id.bt_cancel == v.getId()){
			//������һҳ
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


