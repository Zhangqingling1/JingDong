package com.aqinga.jingdong.model.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aqinga.jingdong.model.bean.DbBean;
import com.aqinga.jingdong.model.db.MySQLiteOpenHelper;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/1413:49
 */

public class DBDao {
    private SQLiteDatabase db;
    public DBDao(Context context) {
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context);
        db = helper.getReadableDatabase();
    }
    public void add(String name,String password){
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("password",password);
        db.insert("jingdong",null,values);
    }
    public DbBean query(String name,String password){
        DbBean bean = null;
        Cursor cursor = db.query("jingdong", null,"name = ? and password = ?", new String[]{name,password}, null, null, null);
        while (cursor.moveToNext()){
            String name1 = cursor.getString(cursor.getColumnIndex("name"));
            String password1 = cursor.getString(cursor.getColumnIndex("password"));
            bean = new DbBean();
            bean.setName(name1);
            bean.setPassword(password1);

        }
        return bean;
    }
}
