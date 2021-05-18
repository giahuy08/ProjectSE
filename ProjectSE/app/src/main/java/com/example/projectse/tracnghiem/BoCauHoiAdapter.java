package com.example.projectse.tracnghiem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectse.R;
import com.example.projectse.bohoctap.BoHocTap;

import java.util.List;

public class BoCauHoiAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<BoHocTap> boHocTapList;

    public BoCauHoiAdapter(Context context, int layout, List<BoHocTap> boHocTapList) {
        this.context = context;
        this.layout = layout;
        this.boHocTapList = boHocTapList;
    }

    @Override
    public int getCount() {
        return boHocTapList.size();
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
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView =inflater.inflate(layout,null);
        TextView txttenbo=(TextView) convertView.findViewById(R.id.dong_bocauhoi);


        BoHocTap boHocTap = boHocTapList.get(position);
        txttenbo.setText(boHocTap.getTenBo());
        return convertView;
    }
}
