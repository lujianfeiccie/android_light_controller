package com.lujianfei.icecontroller.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lujianfei.icecontroller.R;
import com.lujianfei.icecontroller.ui.util.Util;

/*
��Ȩ���У���Ȩ����(C)2013���������
�ļ����ƣ�com.lujianfei.icecontroller.ui.SettingEditActivity.java
ϵͳ��ţ�
ϵͳ���ƣ�LightController
ģ���ţ�
ģ�����ƣ�
����ĵ���
�������ڣ�2013-11-25 ����8:00:42
�� �ߣ�½����
����ժҪ��
���еĴ�������������Σ���������������������෽������
�ļ�����:
 */
public class SettingEditActivity extends BaseActivity implements OnClickListener{
	EditText edit_ip;
	EditText edit_port;
	EditText edit_name;
	Button bt_cancel;
	Button bt_ok;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_edit_activity);
		
		initView();
		initEvent();
		intiData();
	}

	private void intiData() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		if(intent.getStringExtra("type").equals("add")){
			log("add");
		}else if(intent.getStringExtra("type").equals("edit")){
			log("edit");
		}
	}

	private void initEvent() {
		bt_cancel.setOnClickListener(this);
		bt_ok.setOnClickListener(this);
	}

	private void initView() {
		edit_ip = (EditText)findViewById(R.id.edit_ip);
		edit_port = (EditText)findViewById(R.id.edit_port);
		edit_name = (EditText)findViewById(R.id.edit_name);
		
		bt_cancel = (Button)findViewById(R.id.bt_cancel);
		bt_ok = (Button)findViewById(R.id.bt_ok);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_cancel:
			log("cancel");
			setResult(RESULT_CANCELED);
			finish();
			break;
		case R.id.bt_ok:
			log("ok");
			
			int port = 0;
			try {
				port =Integer.parseInt(edit_port.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				port = 0;
			}
			String ip = edit_ip.getText().toString();
			String name = edit_name.getText().toString();
			final String t_ip = ip;
			final int t_port = port;
			if(!Util.isIpValid(t_ip)){
				showToast(R.string.setting_layout_ip_error);
				return;
			}
			else if(!Util.isPortValid(t_port)){
				showToast(R.string.setting_layout_port_error);
				return;
			}
			else if(!Util.isNameValid(name)){
				showToast(R.string.setting_layout_name_error);
				return;
			}
				
			Intent intent = new Intent(this,SettingActivity.class);
			intent.putExtra("edit_ip", edit_ip.getText().toString());
			intent.putExtra("edit_port", edit_port.getText().toString());
			intent.putExtra("edit_name", edit_name.getText().toString());
			setResult(RESULT_OK, intent);
			finish();
			break;
		default:
			break;
		}
	}
	void showToast(int msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	void log(String msg){
		Log.d(getClass().getSimpleName(), msg);
	}
}


