package com.example.projectse.ui.home;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projectse.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    ImageView imgview;
    final  String DATABASE_NAME = "HocNgonNgu.db";
    SQLiteDatabase database;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        imgview=(ImageView) root.findViewById(R.id.imageView3);
        byte[] imge=null;
        database =Database.initDatabase(getActivity(), DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM TuVung",null);

        Bitmap img4=null;

        for (int i = 0; i < 2; i++){
            cursor.moveToPosition(i);
            int idtu = cursor.getInt(0);
            int idbo = cursor.getInt(1);
            String dapan = cursor.getString(2);
            String dichnghia = cursor.getString(3);
            String loaitu = cursor.getString(4);
            String audio = cursor.getString(5);
            byte[] anh = cursor.getBlob(6);
            Bitmap img= BitmapFactory.decodeByteArray(anh,0,anh.length);
            imge=anh;
            img4=img;

        }

        textView.setText(imge+"");
        imgview.setImageBitmap(img4);
        return root;
    }
    private void readData(byte[] img1){
        database =Database.initDatabase(getActivity(), DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM TuVung",null);
        

        for (int i = 0; i < 2; i++){
            cursor.moveToPosition(i);
            int idtu = cursor.getInt(0);
            int idbo = cursor.getInt(1);
            String dapan = cursor.getString(2);
            String dichnghia = cursor.getString(3);
            String loaitu = cursor.getString(4);
            String audio = cursor.getString(5);
            byte[] anh = cursor.getBlob(6);
            Bitmap img= BitmapFactory.decodeByteArray(anh,0,anh.length);
            img1=anh;

        }
        
    }
}