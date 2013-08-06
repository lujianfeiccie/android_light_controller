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
	
	public final static byte FLAG_HEADER = (byte)0x0a;
	
	public final static int FLAG_CONTROL = 1;
	/**
	 * 获取文件列表
	 */
	public final static byte FLAG_FILE_LIST = (byte)0x03;
	
	/**
	 * 打开文件
	 */
	public final static byte FLAG_FILE_OPEN = (byte)0x04;
	/**
	 * 关闭文件
	 */
	public final static byte FLAG_FILE_CLOSE = (byte)0x05;
	/**
	 * 文件下一页
	 */
	public final static byte FLAG_FILE_NEXT = (byte)0x06;
	/**
	 * 文件上一页
	 */
	public final static byte FLAG_FILE_PREVIOUS = (byte)0x07;
	/**
	 * 文件全屏播放
	 */
	public final static byte FLAG_FILE_FULLSCREEN = (byte)0x08;
	public final static byte FLAG_EXIT = (byte)0xff;
}