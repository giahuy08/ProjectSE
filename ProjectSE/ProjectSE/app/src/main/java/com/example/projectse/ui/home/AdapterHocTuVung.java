package com.example.projectse.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectse.R;

import java.util.ArrayList;

public class AdapterHocTuVung extends BaseAdapter {
    Context context;
    ArrayList<HocTuVung> listHTV;

    public AdapterHocTuVung(Context context, ArrayList<HocTuVung> listHTV) {
        this.context = context;
        this.listHTV = listHTV;
    }

    @Override
    public int getCount() {
        return listHTV.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listview_htv_row, null);
        ImageView imgHinhDaiDien = (ImageView) row.findViewById(R.id.imgHinhDaiDien);
        TextView txtidTu = (TextView) row.findViewById(R.id.txtidTu);
        TextView txtidBo = (TextView) row.findViewById(R.id.txtidBo);
        TextView txtDapan = (TextView) row.findViewById(R.id.txtdapan);
        TextView txtDichnghia = (TextView) row.findViewById(R.id.txtDichnghia);
        TextView txtLoaitu = (TextView) row.findViewById(R.id.txtLoaiTu);
        TextView audio = (TextView) row.findViewById(R.id.TxtAudio);

        HocTuVung hocTuVung = listHTV.get(position);
        txtidTu.setText(hocTuVung.idtu +"");
        txtidBo.setText(hocTuVung.idbo +"");
        txtDapan.setText(hocTuVung.dapan);
        txtDichnghia.setText(hocTuVung.dichnghia);
        txtLoaitu.setText(hocTuVung.loaitu);
        audio.setText(hocTuVung.audio);

        //Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(hocTuVung.anh, 0 , hocTuVung.anh.length);
        //imgHinhDaiDien.setImage(hocTuVung.bmHinhDaiDien);
        return row;
    }
}
