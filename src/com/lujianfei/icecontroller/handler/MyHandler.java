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
			showToast("������");
			break;
		case Common.UI_CONNECT_FAILED:
			showToast("����ʧ��");
			break;
		case Common.UI_CONNECT_SUCCESFULLY:
			showToast("���ӳɹ�");
			break;
		case Common.UI_DISCONNECT:
			showToast("���ӶϿ�");
			break;
		default:
			break;
		}
	}
	void showToast(String msg){
		Toast.makeText(activity, msg, 200).show();
	}
}