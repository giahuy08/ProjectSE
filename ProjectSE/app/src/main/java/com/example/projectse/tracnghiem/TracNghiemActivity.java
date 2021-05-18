package com.example.projectse.tracnghiem;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectse.R;
import com.example.projectse.bohoctap.BoHocTap;
import com.example.projectse.ui.home.Database;

import java.util.ArrayList;

public class TracNghiemActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<BoHocTap> boHocTapArrayList;
    BoCauHoiAdapter boCauHoiAdapter;
    final  String DATABASE_NAME = "HocNgonNgu.db";
    SQLiteDatabase database;
    int idbocauhoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trac_nghiem);

        listView= findViewById(R.id.lvtracnghiem);
        boHocTapArrayList =new ArrayList<>();
        AddArrayBTN();
        boCauHoiAdapter=new BoCauHoiAdapter(TracNghiemActivity.this,R.layout.row_botracnghiem, boHocTapArrayList);
        listView.setAdapter(boCauHoiAdapter);
        boCauHoiAdapter.notifyDataSetChanged();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                database= Database.initDatabase(TracNghiemActivity.this,DATABASE_NAME);
                String a=null;
                Cursor cursor=database.rawQuery("SELECT * FROM BoCauHoi",null);
                for(int i=position;i<cursor.getCount();i++){
                    cursor.moveToPosition(i);
                    int idbo=cursor.getInt(0);
                    int stt=cursor.getInt(1);
                    String tenbo=cursor.getString(2);
                    a=tenbo;
                    idbocauhoi=idbo;
                    break;
                }
                Intent quiz= new Intent(TracNghiemActivity.this,QuizActivity.class);
                quiz.putExtra("Bo",idbocauhoi);

                startActivity(quiz);
            }
        });
    }
    private void AddArrayBTN(){
        database= Database.initDatabase(TracNghiemActivity.this,DATABASE_NAME);
        Cursor cursor=database.rawQuery("SELECT * FROM BoCauHoi",null);
        boHocTapArrayList.clear();
        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int idbo = cursor.getInt(0);
            int  stt = cursor.getInt(1);
            String tenbo = cursor.getString(2);
            boHocTapArrayList.add(new BoHocTap(idbo,stt,tenbo));

        }
    }
}