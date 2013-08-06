package com.lujianfei.icecontroller.utilities;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

/*
��Ȩ���У���Ȩ����(C)2013���������
�ļ����ƣ�com.goopai.android.bt.utilitiesScreenHelper.java
ϵͳ��ţ�
ϵͳ���ƣ�SMART-IVI
ģ���ţ�
ģ�����ƣ�
����ĵ���
������ڣ�2013-5-9
�� �ߣ�½����
����ժҪ�� 
���еĴ�������������Σ���������������������෽������
�ļ�����:
 */
public class ScreenHelper {
	 /**
	 * ��õ�ǰ��Ļ���ȵ�ģʽ 
	 * SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 Ϊ�Զ�������Ļ����
	 * SCREEN_BRIGHTNESS_MODE_MANUAL=0 Ϊ�ֶ�������Ļ����
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
	 * ��õ�ǰ��Ļ����ֵ 0--255
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
	 * ���õ�ǰ��Ļ���ȵ�ģʽ 
	 * SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 Ϊ�Զ�������Ļ����
	 * SCREEN_BRIGHTNESS_MODE_MANUAL=0 Ϊ�ֶ�������Ļ����
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
	  * ֹͣ�Զ����ȵ���
	  */
	 public static boolean stopAutoBrightness(Context context) {
	     return Settings.System.putInt(context.getContentResolver(),
	             Settings.System.SCREEN_BRIGHTNESS_MODE,
	             Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
	 }
	 /**
	  * ��ʼ�Զ����ȵ���
	  */
	 public static boolean startAutoBrightness(Context context) {
	     return Settings.System.putInt(context.getContentResolver(),
	             Settings.System.SCREEN_BRIGHTNESS_MODE,
	             Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
	 }
	 /**
	 * ���õ�ǰ��Ļ����ֵ 0--255
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
	 * ���浱ǰ����Ļ����ֵ����ʹ֮��Ч
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


