package com.example.projectse.taikhoan;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectse.R;
import com.example.projectse.ui.home.Database;

public class ThongTinTaikhoanActivity extends AppCompatActivity {

    final  String DATABASE_NAME = "HocNgonNgu.db";
    DatabaseAccess DB;
    SQLiteDatabase database;
    EditText tvHoten,tvEmail,tvSdt,tvMatKhau;
    TextView tvtaikhoan, tvTen,tvPoint;
    Button btnCapNhat;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_taikhoan);
        DB = DatabaseAccess.getInstance(getApplicationContext());
        AnhXa();
        LayThongTin();
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CapNhatThongTin();
            }
        });

    }
    private void AnhXa()
    {
        tvHoten = findViewById(R.id.textIntEdtHoten);
        tvEmail = findViewById(R.id.textIntEdtEmail);
        tvSdt = findViewById(R.id.textIntEdtSdt);
        tvMatKhau = findViewById(R.id.textIntEdtPass);
        tvtaikhoan = findViewById(R.id.tVusername);
        tvTen = findViewById(R.id.textViewTen);
        tvPoint = findViewById(R.id.textviewPoint);
        btnCapNhat = findViewById(R.id.buttonCapNhat);
    }
    private void CapNhatThongTin()
    {
        String hoten = tvHoten.getText().toString();
        String email = tvEmail.getText().toString();
        String sdt = tvSdt.getText().toString();
        String pass = tvMatKhau.getText().toString();
        if(hoten =="" || email =="" || sdt=="" ||pass=="")
        {
            Toast.makeText(this, "Không hợp lệ", Toast.LENGTH_SHORT).show();
        }
        else{
            Boolean checkupdate = DB.capnhatthongtin(DB.username,hoten,email,sdt,pass);
            if(checkupdate == true)
            {
                Toast.makeText(this, "Câp nhật thành công", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Thất bại", Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void LayThongTin(){
            database = Database.initDatabase(ThongTinTaikhoanActivity.this, DATABASE_NAME);
            Cursor cursor = database.rawQuery("SELECT * FROM NguoiDung WHERE TaiKhoan = ?",new String[]{String.valueOf(DB.username)});
         cursor.moveToNext();
            tvHoten.setText(cursor.getString(1));
            tvTen.setText(cursor.getString(1));
            tvtaikhoan.setText(cursor.getString(2));
            tvPoint.setText(cursor.getString(4));
            tvEmail.setText(cursor.getString(5));
            tvSdt.setText(cursor.getString(6));
            tvMatKhau.setText(cursor.getString(3));
        }



}