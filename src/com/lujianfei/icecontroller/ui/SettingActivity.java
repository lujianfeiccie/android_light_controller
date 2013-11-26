package com.lujianfei.icecontroller.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lujianfei.icecontroller.R;
import com.lujianfei.icecontroller.adapter.SettingAdapter;
import com.lujianfei.icecontroller.database.DBManager;
import com.lujianfei.icecontroller.model.ConnectionInfo;

/*
版权所有：版权所有(C)2013，固派软件
文件名称：com.lujianfei.icecontroller.ui.SettingActivity.java
系统编号：
系统名称：LightController
模块编号：
模块名称：
设计文档：
创建日期：2013-11-25 下午7:02:39
作 者：陆键霏
内容摘要：
类中的代码包括三个区段：类变量区、类属性区、类方法区。
文件调用:
 */
public class SettingActivity extends BaseActivity implements OnItemClickListener{
	private List<ConnectionInfo> mData;
	SettingAdapter mSettingAdapter = null;
	ListView listview = null;
	SettingItemPopup mSettingItemPopup = null;
	Button bt_add;
	final int REQUEST_EDIT=2; 
	final int REQUEST_ADD=3; 
	private DBManager dbmanager;
	private int selectedIndex = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_activity);
		dbmanager = new DBManager(this);
		initView();
		initData();
	}
	private void initData() {
		mSettingItemPopup = new SettingItemPopup(this,onPopupViewListener);
		mData = new ArrayList<ConnectionInfo>();
		mSettingAdapter = new SettingAdapter(this, mData);
	}
	private void initView() {
		listview = (ListView)findViewById(R.id.listview);
		bt_add = (Button)findViewById(R.id.bt_add);
		listview.setAdapter(mSettingAdapter);
		listview.setOnItemClickListener(this);
		bt_add.setOnClickListener(onTitleListener);
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		log(String.format("%s", arg2));
		selectedIndex = arg2;
		mSettingItemPopup.show();
	}
	android.view.View.OnClickListener onTitleListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.bt_add:
				Intent intent = new Intent(SettingActivity.this, SettingEditActivity.class);
				intent.putExtra("type", "add");
				startActivityForResult(intent,REQUEST_ADD);
				break;
			default:
				break;
			}
		}
	};
	OnClickListener onPopupViewListener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.bt_connect:
				log("connect");
				break;
			case R.id.bt_edit:
				log("edit");
				Intent intent = new Intent(SettingActivity.this, SettingEditActivity.class);
				intent.putExtra("type", "edit");
				startActivityForResult(intent,REQUEST_EDIT);
				if(mSettingItemPopup!=null){
					mSettingItemPopup.dismiss();
				}
				break;
			case R.id.bt_delete:
				log("delete");
				ConnectionInfo mConnectionInfo = mData.get(selectedIndex);
				int flag =  dbmanager.delete(mConnectionInfo);
				if(flag>0){
					showToast(R.string.setting_activity_delete);
					reload();
				}
				
				if(mSettingItemPopup!=null){
					mSettingItemPopup.dismiss();
				}
				break;
			case R.id.bt_cancel:
				log("cancel");
				if(mSettingItemPopup!=null){
					mSettingItemPopup.dismiss();
				}
				break;
			default:
				break;
			}
		} 
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		log(String.format("requestCode=%s resultCode=%s", requestCode,resultCode));
		if(resultCode!=RESULT_OK){
			return;
		 }
		String ip = data.getStringExtra("edit_ip");
		String port = data.getStringExtra("edit_port");
		String name = data.getStringExtra("edit_name");
		switch (requestCode) {
		case REQUEST_EDIT:
			  log(String.format("edit %s %s %s", ip,port,name));
			break;
		case REQUEST_ADD:
  			log(String.format("add %s %s %s", ip,port,name));
  			ConnectionInfo mConnectionInfo = new ConnectionInfo(ip,Integer.parseInt(port),name);
  			dbmanager.add(mConnectionInfo);
			break;
		default:
			break;
		}
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		reload();
	//	dbmanager.showAllTable();
	}
	void reload(){
		mData = dbmanager.query();
		mSettingAdapter.refreshList(mData);	
	}
	void showToast(int msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	void showToast(String msg){
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	void log(String msg){
		Log.d(getClass().getSimpleName(), msg);
	}
}


