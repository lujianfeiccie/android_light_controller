package com.lujianfei.icecontroller.utilities;

import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.lujianfei.icecontroller.exception.CrashApplication;

public class SystemHelper {
  static String tag = "SystemHelper";
  
  /**
   * 得到版本的名称
   * @param context
   * @return
   * @throws Exception
   */
  public  static String getVersionName(Context context) throws Exception
  {
           // 获取packagemanager的实例
           PackageManager packageManager = context.getPackageManager();
           // getPackageName()是你当前类的包名，0代表是获取版本信息
           PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
           String version = packInfo.versionName;
           return version;
  }
  /**
   * 得到系统版本号
   * @param context
   * @return
   */
  public static String getSystemVersionNo()
  {
     return android.os.Build.VERSION.RELEASE;
  }
  public static List<String> getAllTheLauncher(Context context){  
	  ActivityManager mActivityManager = (ActivityManager) context
	  .getSystemService(context.ACTIVITY_SERVICE);
	  List<ActivityManager.RunningAppProcessInfo> mRunningProcess = mActivityManager
	  .getRunningAppProcesses();

	  int i = 1;
	  List<String> names = new ArrayList<String>();
	  for (ActivityManager.RunningAppProcessInfo amProcess : mRunningProcess)
	  {
		  if(amProcess.processName.startsWith("com.android")){
		  names.add("PID: " + amProcess.pid
				  + " processName=" + amProcess.processName + " UID="+amProcess.uid+"");
		  }
	  //Log.i("Application", (i++) + "PID: " + amProcess.pid
	//  + "(processName=" + amProcess.processName + "UID="+amProcess.uid+")");
	  }
      return names;  
  }  
  public static String getCurrentPackageFrontRunning(){
	  ActivityManager am = (ActivityManager) CrashApplication.getContext().getSystemService(CrashApplication.getContext().ACTIVITY_SERVICE);
	  ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
	  return cn.getPackageName();
  }
  public static String getCurrentClassFrontRunning(Context context){
	  ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
	  ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
	  return cn.getClassName();
  }
	public static boolean isServiceRunning(Context mContext, String className) {
		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager) mContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager
				.getRunningServices(30);
		if (!(serviceList.size() > 0)) {
			return false;
		}
		for (int i = 0; i < serviceList.size(); i++) {
			if (serviceList.get(i).service.getClassName().equals(className) == true) {
				isRunning = true;
				break;
			}
		}
		return isRunning;
	}
	public static boolean killProcess(String packageName,Context context){
		boolean result = false;
		ShellTool shellTool = new ShellTool();
		ActivityManager mActivityManager = (ActivityManager) context
		  .getSystemService(context.ACTIVITY_SERVICE);
		try{
			  List<ActivityManager.RunningAppProcessInfo> mRunningProcess = mActivityManager
			  .getRunningAppProcesses();
			  for (ActivityManager.RunningAppProcessInfo amProcess : mRunningProcess)
			  {
				  if(amProcess.processName.equals(packageName)){
				  //names.add("PID: " + amProcess.pid
					//	  + " processName=" + amProcess.processName + " UID="+amProcess.uid+"");
					  shellTool.Write("kill -9 "+amProcess.pid);
				  }
			  }
			result = true;
		}catch(Exception e){
			result = false;
			log("killProcess:"+e.getMessage());
		}
		return result;
	}
	/**
	 * 得到设备型号名字
	 * @return
	 */
	public static String getDeviceName() {
  	  String manufacturer = Build.MANUFACTURER;
  	  String model = Build.MODEL;
  	  if (model.startsWith(manufacturer)) {
  	    return capitalize(model);
  	  } else {
  	    return capitalize(manufacturer) + " " + model;
  	  }
  	}

	private static String capitalize(String s) {
	  if (s == null || s.length() == 0) {
	    return "";
	  }
	  char first = s.charAt(0);
	  if (Character.isUpperCase(first)) {
	    return s;
	  } else {
	    return Character.toUpperCase(first) + s.substring(1);
	  }
	} 
	/**
	 * 结束别的进程
	 */
	public static void killOtherRunningService(){
		ShellTool shellTool = new ShellTool();
		ActivityManager activityManager = (ActivityManager)CrashApplication.getContext().getSystemService(Context.ACTIVITY_SERVICE);     
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();   
        for(ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses){    
            //activityManager.restartPackage(runningAppProcessInfo.processName);  
            //log(""+runningAppProcessInfo.processName);
        	//将无关系统应用的服务干掉, 保留当前运行的软件
			if(!runningAppProcessInfo.processName.equals("com.goopai.android.bt") &&
				!runningAppProcessInfo.processName.equals("com.android.settings") &&
				!runningAppProcessInfo.processName.equals("com.android.phone") &&
				!runningAppProcessInfo.processName.equals("com.android.systemui") &&
				!runningAppProcessInfo.processName.equals("com.android.defcontainer") &&
				!runningAppProcessInfo.processName.equals("com.android.bluetooth") &&
				!runningAppProcessInfo.processName.equals("com.android.server.device.enterprise:remote") &&
				!runningAppProcessInfo.processName.equals("com.android.server.vpn.enterprise:remote") &&
				!runningAppProcessInfo.processName.equals("com.android.MtpApplication") &&
				!runningAppProcessInfo.processName.equals("com.android.musicfx") &&
				
				!runningAppProcessInfo.processName.equals("android.process.acore") &&
				!runningAppProcessInfo.processName.equals("android.process.media") &&
				
				!runningAppProcessInfo.processName.equals("com.sec.android.app.twdvfs") &&
				!runningAppProcessInfo.processName.equals("com.sec.android.sCloudRelayData") &&
				!runningAppProcessInfo.processName.equals("com.sec.android.app.videoplayer") &&
				!runningAppProcessInfo.processName.equals("com.sec.android.cloudagent") &&
				!runningAppProcessInfo.processName.equals("com.sec.android.sCloudBackupApp") &&
				!runningAppProcessInfo.processName.equals("com.sec.android.widgetapp.favoriteswidget") &&
				!runningAppProcessInfo.processName.equals("com.sec.android.app.launcher") &&
				!runningAppProcessInfo.processName.equals("com.sec.android.app.controlpanel") &&
				!runningAppProcessInfo.processName.equals("com.sec.android.service.cm") &&
				
				!runningAppProcessInfo.processName.equals("system") &&
				!runningAppProcessInfo.processName.equals("com.samsung.inputmethod") &&
				!runningAppProcessInfo.processName.equals("com.qihoo360.mobilesafe") &&
				!runningAppProcessInfo.processName.equals("eu.chainfire.supersu") &&
				!runningAppProcessInfo.processName.equals(getCurrentPackageFrontRunning())){
			  shellTool.Write("kill -9 "+runningAppProcessInfo.pid);
			}
        }  
        //Toast.makeText(context, "成功结束了"+(start-end)+"个程序", Toast.LENGTH_SHORT).show();  
	}
	static void log(String msg){
		MyLog.d(tag, msg);
	}
}
