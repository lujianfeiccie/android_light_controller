package com.lujianfei.icecontroller.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import com.lujianfei.icecontroller.R;

/*
��Ȩ���У���Ȩ����(C)2013���������
�ļ����ƣ�com.lujianfei.icecontroller.ui.SettingPopup.java
ϵͳ��ţ�
ϵͳ���ƣ�LightController
ģ���ţ�
ģ�����ƣ�
����ĵ���
�������ڣ�2013-11-25 ����11:03:00
�� �ߣ�½����
����ժҪ��
���еĴ�������������Σ���������������������෽������
�ļ�����:
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


