package com.example.projectse.taikhoan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectse.MainActivity;
import com.example.projectse.R;

public class LoginActivity extends AppCompatActivity {

    Button btnDangnhap;
    TextView tvDangky;
    EditText edttaikhoan, edtmatkhau;
    DatabaseAccess DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_login);
        AnhXa();
        DB = DatabaseAccess.getInstance(getApplicationContext());

        //Đăng nhập thành công chuyển sang MainActivity
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
                    DB.open();
                    //Kiểm tra username password
                    Boolean kiemtra = DB.checktaikhoanmatkhau(taikhoan,matkhau);
                    DB.close();

                    if(kiemtra == true)
                    {
//                        Intent tentaikhoan = new Intent(LoginActivity.this, ThongTinTaikhoanActivity.class);
//                        tentaikhoan.putExtra("username", taikhoan);
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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