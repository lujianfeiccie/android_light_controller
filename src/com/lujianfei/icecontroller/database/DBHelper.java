package com.lujianfei.icecontroller.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
版权所有：版权所有(C)2013，固派软件
文件名称：com.lujianfei.icecontroller.database.DBHelper.java
系统编号：
系统名称：LightController
模块编号：
模块名称：
设计文档：
创建日期：2013-11-26 上午1:31:19
作 者：陆键霏
内容摘要：
类中的代码包括三个区段：类变量区、类属性区、类方法区。
文件调用:
 */
public class DBHelper extends SQLiteOpenHelper{
	private static final String DATABASE_NAME = "test.db";  
    private static final int DATABASE_VERSION = 1;  
    public static final String TB_ConnectionInfo = "ConnectionInfo";
    public DBHelper(Context context) {  
        //CursorFactory设置为null,使用默认值  
        super(context, DATABASE_NAME, null, DATABASE_VERSION);  
    }  
    //数据库第一次被创建时onCreate会被调用  
    @Override  
    public void onCreate(SQLiteDatabase db) {  
        db.execSQL("CREATE TABLE IF NOT EXISTS " +TB_ConnectionInfo+  
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, _name VARCHAR,_addr VARCHAR,_port INTEGER)");  
    }  
    //如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade  
    @Override  
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
        db.execSQL("ALTER TABLE person ADD COLUMN other STRING");  
    }  
}


