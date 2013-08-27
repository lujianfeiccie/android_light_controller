package com.lujianfei.icecontroller.handler;

import com.lujianfei.icecontroller.Common;
import com.lujianfei.icecontroller.ui.SettingPopupWindow;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class MyHandler extends Handler{
	Activity activity;
	SettingPopupWindow mSettingPopupWindow= null;
	public MyHandler(Activity activity,SettingPopupWindow mSettingPopupWindow){
		this.activity = activity;
		this.mSettingPopupWindow = mSettingPopupWindow;
	}
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		switch (msg.what) {
		case Common.UI_CONNECTED:
			showToast("已连接");
			break;
		case Common.UI_CONNECT_FAILED:
			showToast("连接失败");
			break;
		case Common.UI_CONNECT_SUCCESFULLY:
			showToast("连接成功");
			break;
		case Common.UI_DISCONNECT:
			showToast("连接断开");
			break;
		default:
			break;
		}
	}
	void showToast(String msg){
		Toast.makeText(activity, msg, 200).show();
	}
}