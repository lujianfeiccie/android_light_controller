package com.lujianfei.icecontroller.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.lujianfei.icecontroller.R;
import com.lujianfei.icecontroller.exception.CrashApplication;
import com.lujianfei.icecontroller.handler.MyHandler;
import com.lujianfei.icecontroller.model.ConnectionInfo;
import com.lujianfei.icecontroller.ui.util.Util;

public class BaseActivity extends Activity {
	  protected SettingPopupWindow mPopupWindow;
	  protected CrashApplication mApp  =null;
	  private MyHandler mMyHandler = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
			mApp = (CrashApplication) CrashApplication.getContext();
			mPopupWindow = new SettingPopupWindow(this);
		     ConnectionInfo mConnectionInfo=mApp.loadConnectionInfo();
		    mPopupWindow.setIp(mConnectionInfo.getAddr());
		    mPopupWindow.setPort(mConnectionInfo.getPort());
		    mMyHandler = new MyHandler(this,mPopupWindow);
		    mApp.setHandler(mMyHandler);
	}
	protected void showSettingDialog(){
		View view = findViewById(R.id.btn_home);
		mPopupWindow.getWindow().showAsDropDown(view, Util.DipToPixels(this, -30),Util.DipToPixels(this, 20));
		mPopupWindow.updateControl();
	}
}
