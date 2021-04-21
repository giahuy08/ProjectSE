
package com.example.projectse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    TextView tvDangNhap;
    EditText edtHoTen,edtEmail,edtSdt,edtTaiKhoan,edtMatKhau,edtXacNhan;
    Button btnSignUp;
    DatabaseAccess DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        AnhXa();

        DB =  DatabaseAccess.getInstance(getApplicationContext());
        tvDangNhap = (TextView) findViewById(R.id.textView_login);
        tvDangNhap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = edtHoTen.getText().toString();
                String email = edtEmail.getText().toString();
                String sdt = edtSdt.getText().toString();
                String taikhoan = edtTaiKhoan.getText().toString();
                String matkhau = edtMatKhau.getText().toString();
                String xacnhanmatkhau = edtXacNhan.getText().toString();

                if(hoten.equals("")||email.equals("")||sdt.equals("")||taikhoan.equals("")||matkhau.equals(""))
                {
                    Toast.makeText(Signup.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(matkhau.equals(xacnhanmatkhau)){

                        Boolean kiemtrataikhoan = DB.checktaikhoan(taikhoan);
                        if(kiemtrataikhoan == false)
                        {
                            DB.open();
                            Boolean insert = DB.insertData(hoten,taikhoan,matkhau,email,sdt,0);
                            DB.close();
                            if(insert == true)
                            {
                                btnSignUp.setText(insert.toString());
                                Toast.makeText(Signup.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(Signup.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Signup.this, "tên tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        }


                    }
                    else{
                        Toast.makeText(Signup.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                        edtMatKhau.setText("");
                        edtXacNhan.setText("");
                    }
                }
            }
        });

    }
    private void AnhXa()
    {
        tvDangNhap = (TextView) findViewById(R.id.textView_login);
        edtHoTen = (EditText) findViewById(R.id.editTextHoTen);
        edtEmail = (EditText) findViewById(R.id.editTextEmail);
        edtSdt = (EditText) findViewById(R.id.editTextSdt);
        edtTaiKhoan =(EditText) findViewById(R.id.editTextTaiKhoan);
        edtMatKhau= (EditText) findViewById(R.id.editTextMatKhau);
        edtXacNhan = (EditText) findViewById(R.id.editTextXacNhan);
        btnSignUp = (Button) findViewById(R.id.buttonSignUp);
    }
}