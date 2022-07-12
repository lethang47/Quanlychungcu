package com.example.quan_ly_chung_cu.fragment_cudan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quan_ly_chung_cu.R;

import java.util.ArrayList;
import java.util.List;

public class contentdichvuApdater extends BaseAdapter {
    private Context context;
    private int layout;
    private List<contentdichvu> contentdichvuList;
    public contentdichvuApdater(Context context, int layout, List<contentdichvu> contentdichvuList) {
        this.context = context;
        this.layout = layout;
        this.contentdichvuList = contentdichvuList;
    }
    @Override
    public int getCount() {
        return contentdichvuList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        // anh xa
        TextView txtView = (TextView) view.findViewById(R.id.id_ten_dichvu);
        ImageView imageView = (ImageView) view.findViewById(R.id.id_hinhanh_dichvu);
        // gan gia tri
        contentdichvu ctdichvu = contentdichvuList.get(i);
        txtView.setText(ctdichvu.getName());
        imageView.setImageResource(ctdichvu.getImage());

        return view;
    }
}
