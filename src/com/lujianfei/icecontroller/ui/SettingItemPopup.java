package com.lujianfei.icecontroller.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import com.lujianfei.icecontroller.R;

/*
版权所有：版权所有(C)2013，固派软件
文件名称：com.lujianfei.icecontroller.ui.SettingPopup.java
系统编号：
系统名称：LightController
模块编号：
模块名称：
设计文档：
创建日期：2013-11-25 下午11:03:00
作 者：陆键霏
内容摘要：
类中的代码包括三个区段：类变量区、类属性区、类方法区。
文件调用:
 */
public class SettingItemPopup extends AlertDialog {

	private android.view.View.OnClickListener listener;
	Button bt_connect;
	Button bt_edit;
	Button bt_delete;
	Button bt_cancel;
	protected SettingItemPopup(Context context,android.view.View.OnClickListener listener) {
		super(context);
		this.listener = listener;
	}
	
	protected SettingItemPopup(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}

	protected SettingItemPopup(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_activity_item_longclick);
		
		bt_connect = (Button)findViewById(R.id.bt_connect);
		bt_edit = (Button)findViewById(R.id.bt_edit);
		bt_delete = (Button)findViewById(R.id.bt_delete);
		bt_cancel = (Button)findViewById(R.id.bt_cancel);
		
		bt_connect.setOnClickListener(listener);
		bt_edit.setOnClickListener(listener);
		bt_delete.setOnClickListener(listener);
		bt_cancel.setOnClickListener(listener);
	}
}


