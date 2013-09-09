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
/**
 * 	˵��	Э��ͷ	����	����	�ƺ�	ָ��	Э���β
 */

	public final static byte FLAG_HEADER = (byte)0x4a;
	public final static byte FLAG_TAIL = (byte)0x6a;
	
	public final static int FLAG_CONTROL = 1;
	/**
	 * RGB���棺0x01
	 */
	public final static byte FLAG_UI_RGB = (byte)0x01;
	/**
	 * ��ůɫ������棺0x02
	 */
	public final static byte FLAG_UI_COLOR = (byte)0x02;
	/**
	 * ģʽ���棺0x03
	 */
	public final static byte FLAG_UI_MODE = (byte)0x03;
	/**
	 * ��
	 */
	public final static byte FLAG_FUNCTION_ONOFF_ON = (byte)0x01;
	/**
	 * ��
	 */
	public final static byte FLAG_FUNCTION_ONOFF_OFF = (byte)0x00;
	/**
	 * ���ع���
	 */
	public final static byte FLAG_FUNCTION_ONOFF = (byte)0x01;
	/**
	 * 	�������ܴ���
	 */
	public final static byte FLAG_FUNCTION_BRIGHTDARK = (byte)0x02;
	/**
	 * ����
	 */
	public final static byte FLAG_FUNCTION_BRIGHTUP = (byte)0x01;
	/**
	 * ����
	 */
	public final static byte FLAG_FUNCTION_BRIGHTDOWN = (byte)0x00;
	/**
	 * 12ɫ������
	 */
	public final static byte FLAG_FUNCTION_COLOR = (byte)0x03;
	
	/**
	 * 	a)	��ů����ѡ���ܴ���: 0x00
	 */
	public final static byte FLAG_FUNCTION_COOL_WARM = (byte)0x00;
	/**
	 * b)	��ɫ����	���ܴ��룺0x01
	 */
	public final static byte FLAG_FUNCTION_COOL_BRIGHT = (byte)0x01;
	/**
	 * c)	ůɫ�������ܴ��룺0x02
	 */
	public final static byte FLAG_FUNCTION_WARM_BRIGHT = (byte)0x02;
	/**
	 * d)	��ɫ�������ܴ��룺0x03
	 */
	public final static byte FLAG_FUNCTION_COOL_DARK = (byte)0x03;
	/**
	 * e)	ůɫ�������ܴ��룺0x04
	 */
	public final static byte FLAG_FUNCTION_WARM_DARK = (byte)0x04;
	/**
	 * ��������
	 */
	public final static byte FLAG_FUNCTION_COOL_WARM_INCREASE = (byte)0x01;
	/**
	 * ����ֹͣ����
	 */
	public final static byte FLAG_FUNCTION_COOL_WARM_STOP = (byte)0x00;
	
	
	
	//����+ģʽ����+�ƺ�+00
/**
 * 1.	nightҹ��ģʽ		0x01
 */
	public final static byte FLAG_FUNCTION_NIGHT = (byte)0x01;
/**
 * 2.	meeting����ģʽ		0x02
 */
	public final static byte FLAG_FUNCTION_MEETING = (byte)0x02;
/**
 * 3.	Reading����ģʽ		0x03
 */
	public final static byte FLAG_FUNCTION_READING = (byte)0x03;
/**
 * 4.	MODE ��ɫ�任		0x04
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
 * 7.	Sleep˯��ģʽ		0x07
 */
	public final static byte FLAG_FUNCTION_SLEEP = (byte)0x07;
	/**
	 * 8.	Recreation ģʽ		0x08
	 */
	public final static byte FLAG_FUNCTION_RECREATION = (byte)0x08;
	public final static byte FLAG_EXIT = (byte)0xff;
}