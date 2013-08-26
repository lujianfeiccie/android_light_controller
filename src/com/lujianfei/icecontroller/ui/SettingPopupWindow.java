package com.lujianfei.icecontroller.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.lujianfei.icecontroller.R;
import com.lujianfei.icecontroller.ui.util.Util;

public class SettingPopupWindow {
	// 创建一个包含自定义view的PopupWindow  
	Activity activity;
	 PopupWindow window;  
	public PopupWindow getWindow() {
		return window;
	}
	public SettingPopupWindow(Activity activity){
		this.activity = activity;
		this.window = makePopupWindow();
	}
    private PopupWindow makePopupWindow()  
    {  
    	PopupWindow window;  
        window = new PopupWindow(activity);  
        View contentView = LayoutInflater.from(activity).inflate(R.layout.setting_layout, null);
        window.setContentView(contentView);   
        window.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.setting_back));  
        window.setWidth(Util.DipToPixels(activity,310));  
        window.setHeight(Util.DipToPixels(activity,200));  
        // 设置PopupWindow外部区域是否可触摸   
        window.setFocusable(true); //设置PopupWindow可获得焦点   
        return window;  
    }  
}
