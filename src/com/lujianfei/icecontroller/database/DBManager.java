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
��Ȩ���У���Ȩ����(C)2013���������
�ļ����ƣ�com.lujianfei.icecontroller.database.DBManager.java
ϵͳ��ţ�
ϵͳ���ƣ�LightController
ģ���ţ�
ģ�����ƣ�
����ĵ���
�������ڣ�2013-11-26 ����1:33:45
�� �ߣ�½����
����ժҪ��
���еĴ�������������Σ���������������������෽������
�ļ�����:
 */
public class DBManager {
	private DBHelper helper;  
	private SQLiteDatabase db;  
    public DBManager(Context context) {  
        helper = new DBHelper(context);  
        //��ΪgetWritableDatabase�ڲ�������mContext.openOrCreateDatabase(mName, 0, mFactory);  
        //����Ҫȷ��context�ѳ�ʼ��,���ǿ��԰�ʵ����DBManager�Ĳ������Activity��onCreate��  
        db = helper.getWritableDatabase();  
    }  
      
    /** 
     * add ConnectionInfos 
     * @param ConnectionInfos 
     */  
    public void add(List<ConnectionInfo> ConnectionInfos) {  
        db.beginTransaction();  //��ʼ����  
        try {  
            for (ConnectionInfo ConnectionInfo : ConnectionInfos) {  
                db.execSQL("INSERT INTO "+DBHelper.TB_ConnectionInfo+"(_name,_addr,_port) VALUES(?, ?, ?, ?)", 
                		new Object[]{ConnectionInfo.getName(), ConnectionInfo.getAddr(), ConnectionInfo.getPort()});  
            }  
            db.setTransactionSuccessful();  //��������ɹ����  
        } finally {  
            db.endTransaction();    //��������  
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


