package com.example.projectse.hoctuvung;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectse.R;
import com.example.projectse.bohoctap.BoTuVung;
import com.example.projectse.bohoctap.BoTuVungAdapter;
import com.example.projectse.ui.home.Database;

import java.util.ArrayList;

public class HocTuVungActivity extends AppCompatActivity {

    final  String DATABASE_NAME = "HocNgonNgu.db";
    SQLiteDatabase database;

    ArrayList<BoTuVung> boTuVungs;
    BoTuVungAdapter adapter;
    ListView botuvungs;

    int idbo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoctuvung);
        botuvungs = findViewById(R.id.listviewHTV);
        boTuVungs = new ArrayList<>();
        AddArrayBTV();
        adapter = new BoTuVungAdapter(HocTuVungActivity.this,R.layout.row_botuvung,boTuVungs);
        botuvungs.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        botuvungs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idbo = boTuVungs.get(position).getIdBo();
                Intent dstv = new Intent(HocTuVungActivity.this, DSTuVungActivity.class);
                dstv.putExtra("idbo", idbo);
                startActivity(dstv);
            }
        });
    }

    private void AddArrayBTV(){
        database = Database.initDatabase(HocTuVungActivity.this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM BoCauHoi",null);
        boTuVungs.clear();

        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int idbo = cursor.getInt(0);
            int  stt = cursor.getInt(1);
            String tenbo = cursor.getString(2);
            boTuVungs.add(new BoTuVung(idbo,stt,tenbo));
        }

    }
}