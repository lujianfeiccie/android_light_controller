package com.lujianfei.icecontroller;
/*
版权所有：版权所有(C)2013，固派软件
文件名称：com.goopai.android.bt.mainbt.Protocol.java
系统编号：
系统名称：SMART-IVI
模块编号：
模块名称：
设计文档：
完成日期：2013-4-5
作 者：陆键霏
内容摘要：协议
类中的代码包括三个区段：类变量区、类属性区、类方法区。
文件调用:
 */
public class Protocol {
/**
 * 	说明	协议头	界面	功能	灯号	指令	协议结尾
 */

	public final static byte FLAG_HEADER = (byte)0x4a;
	public final static byte FLAG_TAIL = (byte)0x6a;
	
	public final static int FLAG_CONTROL = 1;
	/**
	 * RGB界面：0x01
	 */
	public final static byte FLAG_UI_RGB = (byte)0x01;
	/**
	 * 冷暖色调光界面：0x02
	 */
	public final static byte FLAG_UI_COLOR = (byte)0x02;
	/**
	 * 模式界面：0x03
	 */
	public final static byte FLAG_UI_MODE = (byte)0x03;
	/**
	 * 开
	 */
	public final static byte FLAG_FUNCTION_ONOFF_ON = (byte)0x01;
	/**
	 * 关
	 */
	public final static byte FLAG_FUNCTION_ONOFF_OFF = (byte)0x00;
	/**
	 * 开关功能
	 */
	public final static byte FLAG_FUNCTION_ONOFF = (byte)0x01;
	/**
	 * 	亮暗功能代码
	 */
	public final static byte FLAG_FUNCTION_BRIGHTDARK = (byte)0x02;
	/**
	 * 调亮
	 */
	public final static byte FLAG_FUNCTION_BRIGHTUP = (byte)0x01;
	/**
	 * 调暗
	 */
	public final static byte FLAG_FUNCTION_BRIGHTDOWN = (byte)0x00;
	/**
	 * 12色环功能
	 */
	public final static byte FLAG_FUNCTION_COLOR = (byte)0x03;
	
	/**
	 * 	a)	冷暖调光选择功能代码: 0x00
	 */
	public final static byte FLAG_FUNCTION_COOL_WARM = (byte)0x00;
	/**
	 * b)	冷色调亮	功能代码：0x01
	 */
	public final static byte FLAG_FUNCTION_COOL_BRIGHT = (byte)0x01;
	/**
	 * c)	暖色调亮功能代码：0x02
	 */
	public final static byte FLAG_FUNCTION_WARM_BRIGHT = (byte)0x02;
	/**
	 * d)	冷色调暗功能代码：0x03
	 */
	public final static byte FLAG_FUNCTION_COOL_DARK = (byte)0x03;
	/**
	 * e)	暖色调暗功能代码：0x04
	 */
	public final static byte FLAG_FUNCTION_WARM_DARK = (byte)0x04;
	/**
	 * 亮度增加
	 */
	public final static byte FLAG_FUNCTION_COOL_WARM_INCREASE = (byte)0x01;
	/**
	 * 亮度停止增加
	 */
	public final static byte FLAG_FUNCTION_COOL_WARM_STOP = (byte)0x00;
	
	
	
	//界面+模式代码+灯号+00
/**
 * 1.	night夜光模式		0x01
 */
	public final static byte FLAG_FUNCTION_NIGHT = (byte)0x01;
/**
 * 2.	meeting会议模式		0x02
 */
	public final static byte FLAG_FUNCTION_MEETING = (byte)0x02;
/**
 * 3.	Reading读书模式		0x03
 */
	public final static byte FLAG_FUNCTION_READING = (byte)0x03;
/**
 * 4.	MODE 颜色变换		0x04
 */
	public final static byte FLAG_FUNCTION_MODE = (byte)0x04;
/**
 * 5.	Timer				0x05
 */
	public final static byte FLAG_FUNCTION_TIMER = (byte)0x05;
/**
 * 6.	Alarm				0x06
 */
	public final static byte FLAG_FUNCTION_ALARM = (byte)0x06;
/**
 * 7.	Sleep睡眠模式		0x07
 */
	public final static byte FLAG_FUNCTION_SLEEP = (byte)0x07;
	/**
	 * 8.	Recreation 模式		0x08
	 */
	public final static byte FLAG_FUNCTION_RECREATION = (byte)0x08;
	public final static byte FLAG_EXIT = (byte)0xff;
}