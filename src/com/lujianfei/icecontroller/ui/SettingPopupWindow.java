package com.lujianfei.icecontroller.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
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
	private boolean connecting = false;
	CrashApplication mApp = null;
	public PopupWindow getWindow() {
		return window;
	}
	public SettingPopupWindow(Activity activity){
		this.activity = activity;
		this.window = makePopupWindow();
		mApp  = (CrashApplication) CrashApplication.getContext();
	}
    private PopupWindow makePopupWindow()  
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
			if(connecting){
				bt_confirm.setText("连接");
				edit_ip.setEnabled(true);
				edit_port.setEnabled(true);
				connecting = false;
			}else{
				
				if(!checkValid(edit_ip.getText().toString(),Integer.parseInt(edit_port.getText().toString()),activity)){
					return;
				}
				bt_confirm.setText("断开");
				edit_ip.setEnabled(false);
				edit_port.setEnabled(false);
				connecting = true;
			}
		}
			break;
		}
	}  
    boolean checkValid(String addr,int port,Activity activity){
    	Pattern pattern = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
		Matcher matcher = pattern.matcher(addr); //以验证127.400.600.2为例
		if(!matcher.matches()){
			Toast.makeText(activity, "请输入正确的IP地址", 200).show();
			return false;
		}
		if(port<=1024 || port>=65535){
			Toast.makeText(activity, "请输入正确范围的端口号(1024,65535)", 200).show();
			return false;
		}
		return true;
    }
}
