package com.example.projectse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    public  static final  String DBNAME = "HocNgonNgu.db";
    public  static final  int DBVERSION = 1;

    public DatabaseOpenHelper(@Nullable Context context) {
        super(context, DBNAME, null,DBVERSION);
    }

    @Override

    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop table if exists NguoiDung ");
    }
    public Boolean insertData(String hoten,String taikhoan, String matkhau, String email,String sdt,int diem )
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("HoTen",hoten);
        contentValues.put("TaiKhoan",taikhoan);
        contentValues.put("MatKhau",matkhau);
        contentValues.put("Point",diem);
        contentValues.put("Email",email);
        contentValues.put("SDT",sdt);
        long result = myDB.insert("NguoiDung",null,contentValues);
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
        Cursor cursor = myDB.rawQuery("Select * from NguoiDung where TaiKhoan = ?", new String[]{taikhoan});
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
        Cursor cursor = myDB.rawQuery("Select * from NguoiDung where TaiKhoan = ? and MatKhau=?", new String[]{taikhoan,matkhau});
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
