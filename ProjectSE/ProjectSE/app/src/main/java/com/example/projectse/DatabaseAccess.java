package com.example.projectse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    private  static  DatabaseAccess instance;
    Cursor c= null;
    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);

    }

    public  static  DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }
    public  void close(){
        if(db!=null){
            this.db.close();
        }
    }
    public Boolean insertData(String hoten,String taikhoan, String matkhau, String email,String sdt,int diem )
    {
        db = openHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("HoTen",hoten);
        contentValues.put("TaiKhoan",taikhoan);
        contentValues.put("MatKhau",matkhau);
        contentValues.put("Point",diem);
        contentValues.put("Email",email);
        contentValues.put("SDT",sdt);
        long result = db.insert("NguoiDung",null,contentValues);
        if(result==-1) {
            return false;
        }
        else {
            return true;
        }

    }
    public Boolean checktaikhoan(String taikhoan)
    {
        db = openHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from NguoiDung where TaiKhoan = ?", new String[]{taikhoan});
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
        db = openHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from NguoiDung where TaiKhoan = ? and MatKhau=?", new String[]{taikhoan,matkhau});
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