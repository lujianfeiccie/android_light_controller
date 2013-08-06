package com.lujianfei.icecontroller.utilities;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

/*
版权所有：版权所有(C)2013，固派软件
文件名称：com.goopai.android.bt.utilitiesScreenHelper.java
系统编号：
系统名称：SMART-IVI
模块编号：
模块名称：
设计文档：
完成日期：2013-5-9
作 者：陆键霏
内容摘要： 
类中的代码包括三个区段：类变量区、类属性区、类方法区。
文件调用:
 */
public class ScreenHelper {
	 /**
	 * 获得当前屏幕亮度的模式 
	 * SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
	 * SCREEN_BRIGHTNESS_MODE_MANUAL=0 为手动调节屏幕亮度
	 */
	 public static int getScreenMode(Context context){
	 int screenMode=0;
	 try{
	 screenMode = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
	 }
	 catch (Exception localException){


	 }
	 return screenMode;
	 }


	 /**
	 * 获得当前屏幕亮度值 0--255
	 */
	 public static int getScreenBrightness(Context context){
	 int screenBrightness=255;
	 try{
	 screenBrightness = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
	 }
	 catch (Exception localException){


	 }
	 return screenBrightness;
	 }
	 /**
	 * 设置当前屏幕亮度的模式 
	 * SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
	 * SCREEN_BRIGHTNESS_MODE_MANUAL=0 为手动调节屏幕亮度
	 */
	 public static boolean setScreenMode(int paramInt, Context context) {
	 try {
	 return Settings.System.putInt(context.getContentResolver(),
	 Settings.System.SCREEN_BRIGHTNESS_MODE, paramInt);
	 } catch (Exception localException) {
	 localException.printStackTrace();
	 return false;
	 }
	 }
	 /**
	  * 停止自动亮度调节
	  */
	 public static boolean stopAutoBrightness(Context context) {
	     return Settings.System.putInt(context.getContentResolver(),
	             Settings.System.SCREEN_BRIGHTNESS_MODE,
	             Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
	 }
	 /**
	  * 开始自动亮度调节
	  */
	 public static boolean startAutoBrightness(Context context) {
	     return Settings.System.putInt(context.getContentResolver(),
	             Settings.System.SCREEN_BRIGHTNESS_MODE,
	             Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
	 }
	 /**
	 * 设置当前屏幕亮度值 0--255
	 */
	 public static boolean saveScreenBrightness(int paramInt,Context context){
		 try{
		 return   Settings.System.putInt(context.getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS, paramInt);
		 }
		 catch (Exception localException){
		 localException.printStackTrace();
		 return false;
		 }
	 }
	 /**
	 * 保存当前的屏幕亮度值，并使之生效
	 */
	 public static void setScreenBrightness(int paramInt, Context context) {
	 Window localWindow = ((Activity)context).getWindow();
	 WindowManager.LayoutParams localLayoutParams = localWindow
	 .getAttributes();
	 float f = paramInt / 255.0F;
	 localLayoutParams.screenBrightness = f;
	 localWindow.setAttributes(localLayoutParams);
	 }
}


