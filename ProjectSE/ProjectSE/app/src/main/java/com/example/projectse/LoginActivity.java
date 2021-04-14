package com.example.projectse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button btnDangnhap;
    TextView tvDangky;
    EditText edttaikhoan, edtmatkhau;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_login);
        AnhXa();
        DB = new DBHelper(this);

        //Dang nhap thanh cong chuyen sang man` hinh` Main
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = edttaikhoan.getText().toString();
                String matkhau = edtmatkhau.getText().toString();


                if(taikhoan.equals("")||matkhau.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Kiểm tra username password
                    Boolean kiemtra = DB.checktaikhoanmatkhau(taikhoan,matkhau);

                    if(kiemtra == true)
                    {

                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        tvDangky.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,Signup.class);
                startActivity(intent);
            }
        });
    }
    private void AnhXa()
    {
        btnDangnhap=(Button) findViewById(R.id.buttonDangnhap);
        tvDangky = (TextView) findViewById(R.id.textView_register);
        edttaikhoan = (EditText) findViewById(R.id.editTextUser);
        edtmatkhau = (EditText) findViewById(R.id.editTextPass);

    }
}