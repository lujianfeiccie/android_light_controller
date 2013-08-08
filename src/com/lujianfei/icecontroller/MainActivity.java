package com.lujianfei.icecontroller;


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
	
	boolean isDisconnectedByMyself = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		myApp = (CrashApplication)CrashApplication.getContext();
		myApp.setHandler(mHandler);
		
		//InitView
		findViewById(R.id.bt_confirm).setOnClickListener(this);
		findViewById(R.id.bt_cancel).setOnClickListener(this);
		findViewById(R.id.bt_next).setOnClickListener(this);
		findViewById(R.id.bt_previous).setOnClickListener(this);
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
			case Common.UI_CONNECT_FAILED:{//û�ҵ�
				pd.setMessage("����ʧ��");
				pd.dismiss();
				//���Ӻ���ʱ���ֵ��쳣
				DialogConnectionTerminated();
				}
				break;
			case Common.UI_SEND_IOEXCEPTION:{
				//���Ӻ���ʱ���ֵ��쳣
				DialogConnectionTerminated();
				}
				break;
			case Common.UI_CONNECTION_TERMINATED:{
				if(isDisconnectedByMyself) break;
				//���Ӻ���ʱ���ֵ��쳣
				DialogConnectionTerminated();
				}
				break;
			}
			
		}
	};
	
	void log(String msg){
		Log.d(tag, msg);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(R.id.bt_confirm == v.getId()){
			//��������
			RequestServiceConnect(edit_ip.getText().toString(),Integer.parseInt(edit_port.getText().toString()));
		}else if(R.id.bt_cancel == v.getId()){
			//�Ͽ�����
			RequestServiceDisconnect();
		}else if(R.id.bt_next == v.getId()){
			RequestServiceFunction(Common.MessageValueOfService.FUNCTION1);
		}else if(R.id.bt_previous == v.getId()){
			RequestServiceFunction(Common.MessageValueOfService.FUNCTION2);
		}
	}

	void RequestServiceConnect(String addr,int port){
		//��������
		Intent intent_service = new Intent();
		intent_service.setClass(MainActivity.this, 
				SocketConnectionService.class);
		intent_service.putExtra(Common.MessageOfService.IP_ADDRESS, addr);
		intent_service.putExtra(Common.MessageOfService.IP_PORT, port);
		intent_service.putExtra(Common.MessageOfService.SERVICE_REQUEST,Common.MessageValueOfService.LAUNCH_TCP_CONNECTION_SERVICE);
		MainActivity.this.startService(intent_service);
		pd = ProgressDialog.show(this, "��ʾ", "����������...���Ժ�");
		isDisconnectedByMyself = false;
	}
	
	void RequestServiceDisconnect(){
		//��������
		//log("RequestServiceDisconnect");
		Toast.makeText(this, "RequestServiceDisconnect", 200).show();
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
		//���Ӻ���ʱ���ֵ��쳣
		Dialog alertDialog = new AlertDialog.Builder(MainActivity.this). 
                setTitle("��ʾ"). 
                setMessage("�����쳣������?"). 
                setIcon(R.drawable.ic_launcher). 
                setPositiveButton("ȷ��", new DialogInterface.OnClickListener() { 
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
                        // TODO Auto-generated method stub 
                    	dialog.dismiss();
                    	RequestServiceConnect(edit_ip.getText().toString(), Integer.parseInt(edit_port.getText().toString()));
                    } 
                }). 
                setNegativeButton("ȡ��", new DialogInterface.OnClickListener() { 
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
			 //����ȷ���˳��Ի���
			 new AlertDialog.Builder(this)
			  .setTitle("�˳�")
			  .setMessage("ȷ���˳���")
			  .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			     @Override
                 public void onClick(DialogInterface dialog, int which) {
                     // TODO Auto-generated method stub
			    	dialog.cancel();
                    MainActivity.this.finish();
                 }
             })
            .setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
                 @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                     dialog.cancel();
                 }
             })
             .show();
             //���ﲻ��Ҫִ�и���ĵ���¼�������ֱ��return
             return true;
         }
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}

	protected void onDestroy() {
		super.onDestroy();
		myApp.setHandler(null);
		//���ӷ����
		Intent intent_service = new Intent();
		intent_service.setClass(MainActivity.this, 
				SocketConnectionService.class);
//		intent_service.putExtra(Common.MessageOfService.SERVICE_REQUEST,Common.MessageValueOfService.LAUNCH_TCP_DISCONNECTION_SERVICE);
//		MainActivity.this.startService(intent_service);
		MainActivity.this.stopService(intent_service);
		
	};
}


