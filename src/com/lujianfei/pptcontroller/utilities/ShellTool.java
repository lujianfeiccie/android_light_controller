package com.lujianfei.pptcontroller.utilities;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import android.util.Log;

public class ShellTool {
	private String TAG = "ShellTool";
	Process p = null;
	DataOutputStream os = null;
	DataInputStream in = null;
	public ShellTool(){
//		try {
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	void log(String msg){
		Log.d(TAG,msg);
	}
	
	public void Write(String command) {
		log("Write");
		// Lets see if i need to boot daemon...
		try {
			p = Runtime.getRuntime().exec("su");
			OutputStream os = p.getOutputStream();
			writeCommand(os, command);
			log(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log(e.getMessage());
		} 
	}
	public String getStringWriteNoRoot(String command) {
		String result = "";
		log("Write");
		byte[] buffer = new byte[100];
		// Lets see if i need to boot daemon...
		try {
			p = Runtime.getRuntime().exec(command);
			in = new DataInputStream(p.getInputStream());
			int readSize = in.read(buffer);
			byte[] temp_buffer = new byte[readSize];
			System.arraycopy(buffer, 0, temp_buffer, 0, readSize);
			result = new String(temp_buffer);
			log(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log(e.getMessage());
		} 
		return result;
	}
	public boolean getHDMIState(){
		String temp_result = getStringWriteNoRoot("cat /sys/devices/virtual/switch/hdmi/state");
		boolean result = false;
		if(temp_result.trim().equals("0")){
			result = false;
		}else{
			result = true;
		}
		return result;
	}
	public void WriteNoRoot(String command) {
		log("Write");
		// Lets see if i need to boot daemon...
		try {
			Runtime.getRuntime().exec(command);
			log(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log(e.getMessage());
		} 
	}
	
	public void Write(String[] commands) {
		log("Write");
		// Lets see if i need to boot daemon...
		log("Running as root...");
		try {
			p = Runtime.getRuntime().exec("su");
			os = new DataOutputStream(p.getOutputStream()); 
			for(String single : commands){
				os.writeBytes(single+"\n");
			}
			//os.writeBytes("exit\n");
			os.flush();
			os.close();
			//p.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log(e.getMessage());
		} 
	}
	
	public void Write(List<String> commands) {
		log("Write");
		// Lets see if i need to boot daemon...
		log("Running as root...");
		try {
			p = Runtime.getRuntime().exec("su");
			os = new DataOutputStream(p.getOutputStream()); 
			for(String single : commands){
			//	log(single);
				os.writeBytes(single+"\n");
			}
			//os.writeBytes("exit\n");
			os.flush();
			os.close();
			//p.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log(e.getMessage());
		}
	}
	static void writeCommand(OutputStream os, String command) throws Exception {
		os.write((command + "\n").getBytes("ASCII"));
	}
}
