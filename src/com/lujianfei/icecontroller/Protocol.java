package com.lujianfei.icecontroller;
/*
��Ȩ���У���Ȩ����(C)2013���������
�ļ����ƣ�com.goopai.android.bt.mainbt.Protocol.java
ϵͳ��ţ�
ϵͳ���ƣ�SMART-IVI
ģ���ţ�
ģ�����ƣ�
����ĵ���
������ڣ�2013-4-5
�� �ߣ�½����
����ժҪ��Э��
���еĴ�������������Σ���������������������෽������
�ļ�����:
 */
public class Protocol {
	
	public final static byte FLAG_HEADER = (byte)0x0a;
	
	public final static int FLAG_CONTROL = 1;
	/**
	 * ��ȡ�ļ��б�
	 */
	public final static byte FLAG_FILE_LIST = (byte)0x03;
	
	/**
	 * ���ļ�
	 */
	public final static byte FLAG_FILE_OPEN = (byte)0x04;
	/**
	 * �ر��ļ�
	 */
	public final static byte FLAG_FILE_CLOSE = (byte)0x05;
	/**
	 * �ļ���һҳ
	 */
	public final static byte FLAG_FILE_NEXT = (byte)0x06;
	/**
	 * �ļ���һҳ
	 */
	public final static byte FLAG_FILE_PREVIOUS = (byte)0x07;
	/**
	 * �ļ�ȫ������
	 */
	public final static byte FLAG_FILE_FULLSCREEN = (byte)0x08;
	public final static byte FLAG_EXIT = (byte)0xff;
}