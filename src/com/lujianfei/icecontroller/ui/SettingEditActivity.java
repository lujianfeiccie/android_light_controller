package com.lujianfei.icecontroller.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lujianfei.icecontroller.R;
import com.lujianfei.icecontroller.database.DBManager;
import com.lujianfei.icecontroller.model.ConnectionInfo;
import com.lujianfei.icecontroller.ui.util.Util;

/*
版权所有：版权所有(C)2013，固派软件
文件名称：com.lujianfei.icecontroller.ui.SettingEditActivity.java
系统编号：
系统名称：LightController
模块编号：
模块名称：
设计文档：
创建日期：2013-11-25 下午8:00:42
作 者：陆键霏
内容摘要：
类中的代码包括三个区段：类变量区、类属性区、类方法区。
文件调用:
 */
public class SettingEditActivity extends BaseActivity implements OnClickListener{
	EditText edit_ip;
	EditText edit_port;
	EditText edit_name;
	Button bt_cancel;
	Button bt_ok;
	private DBManager dbmanager;  
	String type = "";
	ConnectionInfo mConnectionInfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_edit_activity);
		dbmanager = new DBManager(this);
		initView();
		initEvent();
		intiData();
	}

	private void intiData() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		type = intent.getStringExtra("type");
		if(type.equals("add")){
			log("add");
			addStatus();
		}else if(type.equals("edit")){
			log("edit");
			mConnectionInfo = intent.getParcelableExtra("data");
			editStatus(mConnectionInfo);
		}
	}

	private void addStatus() {
		edit_ip.setText("");
		edit_port.setText("");
		edit_name.setText("");
	}
	private void editStatus(String ip,String port,String name) {
		edit_ip.setText(ip);
		edit_port.setText(port);
		edit_name.setText(name);
	}
	private void editStatus(ConnectionInfo mConnectionInfo) {
		edit_ip.setText(mConnectionInfo.getAddr());
		edit_port.setText(""+mConnectionInfo.getPort());
		edit_name.setText(mConnectionInfo.getName());
	}
	private void editToModel() {
		if(mConnectionInfo==null){
			mConnectionInfo = new ConnectionInfo();
		}
		mConnectionInfo.setAddr(edit_ip.getText().toString());
		mConnectionInfo.setPort(Integer.parseInt(edit_port.getText().toString()));
		mConnectionInfo.setName(edit_name.getText().toString());
	}
	
	private void initEvent() {
		bt_cancel.setOnClickListener(this);
		bt_ok.setOnClickListener(this);
	}

	private void initView() {
		edit_ip = (EditText)findViewById(R.id.edit_ip);
		edit_ip.setInputType(EditorInfo.TYPE_CLASS_PHONE);
		edit_port = (EditText)findViewById(R.id.edit_port);
		edit_port.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
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
			editToModel();
			intent.putExtra("data", mConnectionInfo);
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


