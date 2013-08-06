package com.lujianfei.icecontroller.utilities;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Pattern;

import android.util.Log;

public class NetworkTool {
	static String tag = "NetworkTool";
	public static String getIpAddress() {

		try{
		    for(Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();en.hasMoreElements();){  
		        NetworkInterface intf = en.nextElement();  
		        for(Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();){  
		            InetAddress inetAddress = enumIpAddr.nextElement();  
		            if(!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)){  
		                return inetAddress.getHostAddress().toString();  
		            }  
		        }  
		    }  
		}catch (SocketException e) {
			// TODO: handle exception
			log(e.getMessage());
		}
		return "";  
	}


	public static boolean IsValidIP(String strIP)
	{
		Pattern pattern = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
		return pattern.matcher(strIP).matches();
	}
	
	public static boolean isNumber(String str)
    {
        java.util.regex.Pattern pattern=java.util.regex.Pattern.compile("[0-9]*");
        java.util.regex.Matcher match=pattern.matcher(str);
        if(match.matches()==false)
        {
             return false;
        }
        else
        {
             return true;
        }
    }
	static void log(String msg){
		Log.d(tag,msg);
	}
}
