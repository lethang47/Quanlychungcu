package com.example.quan_ly_chung_cu.fragment_cudan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quan_ly_chung_cu.R;

import java.util.List;

public class content_thongbaoApdater extends BaseAdapter {
    private Context context;
    private int layout;
    private List<content_thongbao> content_thongbaoList;

    public content_thongbaoApdater(Context context, int layout, List<content_thongbao> content_thongbaoList) {
        this.context = context;
        this.layout = layout;
        this.content_thongbaoList = content_thongbaoList;
    }

    @Override
    public int getCount() {
        return content_thongbaoList.size();
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

        TextView noidung = view.findViewById(R.id.id_noidung_thongbao);
        TextView ngay = view.findViewById(R.id.id_ngay_thongbao);

        content_thongbao contentThongbao= content_thongbaoList.get(i);
        noidung.setText(contentThongbao.getNoidung_thongbao());
        ngay.setText(contentThongbao.getNgay_thongbao());
        return view;
    }
}
