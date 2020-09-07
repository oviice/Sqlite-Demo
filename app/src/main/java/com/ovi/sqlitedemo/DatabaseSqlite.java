package com.ovi.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseSqlite extends SQLiteOpenHelper {
    public static final String DataBase_Name="Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PhnNumber";
    public static final int DbVersion=1;

    DatabaseSqlite(Context context){
        super(context,DataBase_Name,null,DbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table "+TABLE_NAME+ "(ID INTEGER PRIMARY KEY ,NAME TEXT,PhnNumber TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String id,String name,String PhnNumber){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,PhnNumber);
        db.insert(TABLE_NAME,null,contentValues);
        return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public void delete() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
    }
    public boolean update(String id,String name,String PhnNumber){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,PhnNumber);
        db.update(TABLE_NAME,contentValues,COL_1+"=?",new String[]{id});
        return true;
    }

}
