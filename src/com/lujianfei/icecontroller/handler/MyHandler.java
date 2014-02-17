package com.lujianfei.icecontroller.handler;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.lujianfei.icecontroller.Common;
import com.lujianfei.icecontroller.R;
import com.lujianfei.icecontroller.exception.CrashApplication;
import com.lujianfei.icecontroller.ui.SettingPopupWindow;

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
			showToast(R.string.mainactivity_connected);
			break;
		case Common.UI_CONNECT_FAILED:
			showToast(R.string.mainactivity_connect_failed);
			break;
		case Common.UI_CONNECT_SUCCESFULLY:
			showToast(R.string.mainactivity_connected_successfully);
			break;
		case Common.UI_DISCONNECT:
			showToast(R.string.mainactivity_disconnected);
			break;
		default:
			break;
		}
	}
	void showToast(String msg){
		Toast.makeText(activity, msg, 200).show();
	}
	void showToast(int msg){
		Toast.makeText(activity, msg, 200).show();
	}
}