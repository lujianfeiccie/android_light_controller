package com.lujianfei.icecontroller.database;

import java.util.ArrayList;
import java.util.List;

import com.lujianfei.icecontroller.model.ConnectionInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/*
版权所有：版权所有(C)2013，固派软件
文件名称：com.lujianfei.icecontroller.database.DBManager.java
系统编号：
系统名称：LightController
模块编号：
模块名称：
设计文档：
创建日期：2013-11-26 上午1:33:45
作 者：陆键霏
内容摘要：
类中的代码包括三个区段：类变量区、类属性区、类方法区。
文件调用:
 */
public class DBManager {
	private DBHelper helper;  
	private SQLiteDatabase db;  
    public DBManager(Context context) {  
        helper = new DBHelper(context);  
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);  
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里  
        db = helper.getWritableDatabase();  
    }  
      
    /** 
     * add ConnectionInfos 
     * @param ConnectionInfos 
     */  
    public void add(List<ConnectionInfo> ConnectionInfos) {  
        db.beginTransaction();  //开始事务  
        try {  
            for (ConnectionInfo ConnectionInfo : ConnectionInfos) {  
                db.execSQL("INSERT INTO "+DBHelper.TB_ConnectionInfo+"(_name,_addr,_port) VALUES(?, ?, ?, ?)", 
                		new Object[]{ConnectionInfo.getName(), ConnectionInfo.getAddr(), ConnectionInfo.getPort()});  
            }  
            db.setTransactionSuccessful();  //设置事务成功完成  
        } finally {  
            db.endTransaction();    //结束事务  
        }  
    }  
    /** 
     * add ConnectionInfos 
     * @param ConnectionInfos 
     */  
    public void add(ConnectionInfo mConnectionInfo) {
    	  ContentValues cv = new ContentValues();  
          cv.put("_addr", mConnectionInfo.getAddr());  
          cv.put("_name", mConnectionInfo.getName());  
          cv.put("_port", mConnectionInfo.getPort());  
          db.insert(DBHelper.TB_ConnectionInfo, null, cv);
    }  
      
    /** 
     * update ConnectionInfo's age 
     * @param ConnectionInfo 
     */  
    public void update(ConnectionInfo ConnectionInfo) {  
        ContentValues cv = new ContentValues();  
        cv.put("_addr", ConnectionInfo.getAddr());  
        cv.put("_name", ConnectionInfo.getName());  
        cv.put("_port", ConnectionInfo.getPort());  
        db.update(DBHelper.TB_ConnectionInfo, cv, "_id = ?", new String[]{""+ConnectionInfo.getId()});  
    }  
      
    /** 
     * delete old ConnectionInfo 
     * @param ConnectionInfo 
     */  
    public void deleteOldConnectionInfo(ConnectionInfo ConnectionInfo) {  
        //db.delete("ConnectionInfo", "age >= ?", new String[]{String.valueOf(ConnectionInfo.age)});  
    }  
    public int delete(ConnectionInfo ConnectionInfo) {  
    	return db.delete("ConnectionInfo", "_id=?", new String[]{String.valueOf(ConnectionInfo.getId())});  
    }  
      
    /** 
     * query all ConnectionInfos, return list 
     * @return List<ConnectionInfo> 
     */  
    public List<ConnectionInfo> query() {  
        ArrayList<ConnectionInfo> ConnectionInfos = new ArrayList<ConnectionInfo>();  
        Cursor c = queryTheCursor();  
        while (c.moveToNext()) {  
            ConnectionInfo mConnectionInfo = new ConnectionInfo();  
            mConnectionInfo.setId(c.getInt(c.getColumnIndex("_id")));  
            mConnectionInfo.setName(c.getString(c.getColumnIndex("_name")));  
            mConnectionInfo.setAddr(c.getString(c.getColumnIndex("_addr")));  
            mConnectionInfo.setPort(c.getInt(c.getColumnIndex("_port")));  
            ConnectionInfos.add(mConnectionInfo);  
            log(""+mConnectionInfo.toString());
        }  
        c.close();  
        return ConnectionInfos;  
    }  
      
    /** 
     * query all ConnectionInfos, return cursor 
     * @return  Cursor 
     */  
    public Cursor queryTheCursor() {  
        Cursor c = db.rawQuery("SELECT * FROM "+DBHelper.TB_ConnectionInfo, null);  
        return c;  
    }
    public void showAllTable() {
		Cursor cursor = null;
		try {
			String sql = "select * from sqlite_master where type ='table'";
			cursor = db.rawQuery(sql, null);
			while (cursor.moveToNext()) {
				String tb_name = cursor.getString(cursor.getColumnIndexOrThrow("tbl_name"));
				log("tablename=" + tb_name);
			}
			cursor.close();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
      
    /** 
     * close database 
     */  
    public void closeDB() {  
        db.close();  
    }  
    void log(String msg){
    	Log.d(getClass().getSimpleName(), msg);
    }
}


