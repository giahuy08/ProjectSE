package com.example.projectse.ui.home;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectse.R;

import java.util.ArrayList;

public class HocTuVungActivity extends AppCompatActivity {

    final  String DATABASE_NAME = "HocNgonNgu.db";
    SQLiteDatabase database;

    ListView listView;
    ArrayList<HocTuVung> list;
    AdapterHocTuVung adapterHocTuVung;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoctuvung);
        addControls();
        readData();
    }

    private  void addControls(){
        listView = (ListView) findViewById(R.id.listviewHTV);
        list = new ArrayList<>();
        adapterHocTuVung = new AdapterHocTuVung(this, list);
        listView.setAdapter(adapterHocTuVung);
    }
    private void readData(){
        database =Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM HocTuVung",null);
        list.clear();

        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int idtu = cursor.getInt(0);
            int idbo = cursor.getInt(1);
            String dapan = cursor.getString(2);
            String dichnghia = cursor.getString(3);
            String loaitu = cursor.getString(4);
            String audio = cursor.getString(5);
            //byte[] anh = cursor.getBlob(6);


            list.add(new HocTuVung(idtu,idbo,dapan,dichnghia,loaitu,audio,null));
        }
        adapterHocTuVung.notifyDataSetChanged();
    }
}
