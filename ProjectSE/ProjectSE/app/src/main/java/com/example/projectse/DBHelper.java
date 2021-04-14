package com.example.projectse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public  static final  String DBNAME = "ProjectSE.db";

    public DBHelper(@Nullable Context context) {
        super(context, "ProjectSE.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table user(iduser INTEGER primary key autoincrement,hoten TEXT,taikhoan TEXT, matkhau TEXT,email TEXT, sdt TEXT,diem INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop table if exists user ");
    }
    public Boolean insertData(String hoten,String taikhoan, String matkhau, String email,String sdt,int diem )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoten",hoten);
        contentValues.put("taikhoan",taikhoan);
        contentValues.put("matkhau",matkhau);
        contentValues.put("email",email);
        contentValues.put("sdt",sdt);
        contentValues.put("diem",diem);
        long result = myDB.insert("user",null,contentValues);
        if(result==-1) {
            return false;
        }
        else {
            return true;
        }

    }
    public Boolean checktaikhoan(String taikhoan)
    {
        SQLiteDatabase myDB = this. getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from user where taikhoan = ?", new String[]{taikhoan});
        if(cursor.getCount() >0)
        {
            return  true;
        }
        else
        {
            return false;
        }

    }
    public Boolean checktaikhoanmatkhau(String taikhoan, String matkhau)
    {
        SQLiteDatabase myDB = this. getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from user where taikhoan = ? and matkhau=?", new String[]{taikhoan,matkhau});
        if(cursor.getCount() >0)
        {
            return  true;
        }
        else
        {
            return false;
        }
    }

}
