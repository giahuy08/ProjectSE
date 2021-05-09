package com.example.projectse.bohoctap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projectse.R;

import java.util.List;

public class BoTuVungAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<BoTuVung> boTuVungList;


    public BoTuVungAdapter(Context context, int layout, List<BoTuVung> boTuVungList) {
        this.context = context;
        this.layout = layout;
        this.boTuVungList = boTuVungList;
    }

    @Override
    public int getCount() {
        return boTuVungList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTenBo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txtTenBo = (TextView) convertView.findViewById(R.id.textviewTenBo);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        BoTuVung boTuVung = boTuVungList.get(position);
        holder.txtTenBo.setText(boTuVung.getTenBo());
        return convertView;
    }
}
