package com.lujianfei.icecontroller.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
��Ȩ���У���Ȩ����(C)2013���������
�ļ����ƣ�com.lujianfei.icecontroller.database.DBHelper.java
ϵͳ��ţ�
ϵͳ���ƣ�LightController
ģ���ţ�
ģ�����ƣ�
����ĵ���
�������ڣ�2013-11-26 ����1:31:19
�� �ߣ�½����
����ժҪ��
���еĴ�������������Σ���������������������෽������
�ļ�����:
 */
public class DBHelper extends SQLiteOpenHelper{
	private static final String DATABASE_NAME = "test.db";  
    private static final int DATABASE_VERSION = 1;  
    public static final String TB_ConnectionInfo = "ConnectionInfo";
    public DBHelper(Context context) {  
        //CursorFactory����Ϊnull,ʹ��Ĭ��ֵ  
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }  
    //���ݿ��һ�α�����ʱonCreate�ᱻ����  
    @Override  
    public void onCreate(SQLiteDatabase db) {  
        db.execSQL("CREATE TABLE IF NOT EXISTS " +TB_ConnectionInfo+  
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, _name VARCHAR,_addr VARCHAR,_port INTEGER)");  
    }  
    //���DATABASE_VERSIONֵ����Ϊ2,ϵͳ�����������ݿ�汾��ͬ,�������onUpgrade  
    @Override  
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
        db.execSQL("ALTER TABLE person ADD COLUMN other STRING");  
    }  
}


