package com.example.quan_ly_chung_cu.fragment_quanly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quan_ly_chung_cu.R;

import java.util.List;

public class content_quanlycudan_thanhvienApdater extends BaseAdapter {
    private Context context;
    int layout;
    List<content_quanlycudan_thanhvien> content_quanlycudan_thanhvienList;

    public content_quanlycudan_thanhvienApdater(Context context, int layout, List<content_quanlycudan_thanhvien> content_quanlycudan_thanhvienList) {
        this.context = context;
        this.layout = layout;
        this.content_quanlycudan_thanhvienList = content_quanlycudan_thanhvienList;
    }

    @Override
    public int getCount() {
        return content_quanlycudan_thanhvienList.size();
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

        TextView tenthanhvien = view.findViewById(R.id.id_quanlycudan_quanly_thongtin);

        content_quanlycudan_thanhvien contentQuanlycudanThanhvien = content_quanlycudan_thanhvienList.get(i);
        tenthanhvien.setText(contentQuanlycudanThanhvien.getTenthanhvien());
        return view;
    }
}
