package com.lujianfei.icecontroller.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.lujianfei.icecontroller.R;
import com.lujianfei.icecontroller.exception.CrashApplication;
import com.lujianfei.icecontroller.ui.util.Util;

public class SettingPopupWindow implements OnClickListener{
	// 创建一个包含自定义view的PopupWindow  
	private Activity activity;
	private PopupWindow window;  
	private EditText edit_ip,edit_port; 

	private Button bt_confirm;
	CrashApplication mApp = null;
	ProgressDialog dlg = null;
	public SettingPopupWindow(Activity activity){
		this.activity = activity;
		this.window = makePopupWindow();
		mApp  = (CrashApplication) CrashApplication.getContext();
	}
	public void setIp(String ip) {
		this.edit_ip.setText(ip);
	}

	public void setPort(int port) {
		this.edit_port.setText(""+port);
	}
	
	public void updateControl(){
		if(mApp.isConnecting()){
			bt_confirm.setText(R.string.setting_layout_disconnect);
		}else{
			bt_confirm.setText(R.string.setting_layout_connect);
		}
	}
	public PopupWindow getWindow() {
		return window;
	}
	
    private  PopupWindow makePopupWindow()  
    {  
    	PopupWindow window;  
        window = new PopupWindow(activity);  
        View contentView = LayoutInflater.from(activity).inflate(R.layout.setting_layout, null);
        edit_ip = (EditText)contentView.findViewById(R.id.edit_ip);
        edit_port = (EditText)contentView.findViewById(R.id.edit_port);
        bt_confirm = (Button)contentView.findViewById(R.id.bt_confirm);
        bt_confirm.setOnClickListener(this);
        window.setContentView(contentView);   
        window.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.setting_back));  
        window.setWidth(Util.DipToPixels(activity,310));  
        window.setHeight(Util.DipToPixels(activity,200));  
        // 设置PopupWindow外部区域是否可触摸   
        window.setFocusable(true); //设置PopupWindow可获得焦点   
        return window;  
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bt_confirm:
		{
			int port = 0;
			try {
				port =Integer.parseInt(edit_port.getText().toString());
			} catch (Exception e) {
				// TODO: handle exception
				port = 0;
			}
			String ip = edit_ip.getText().toString();
			
			final String t_ip = ip;
			final int t_port = port;
			if(!mApp.isConnecting()){
				activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
							if(!Util.isIpValid(t_ip)){
								showToast(R.string.setting_layout_ip_error);
								return;
							}
							else if(!Util.isPortValid(t_port)){
								showToast(R.string.setting_layout_port_error);
								return;
							}
							mApp.SocketConnect(t_ip, t_port);
					}
				});
			}else{
				mApp.SocketDisconnect();
			}
			window.dismiss();
		}	
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
