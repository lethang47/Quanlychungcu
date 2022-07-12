package com.example.quan_ly_chung_cu.fragment_quanly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quan_ly_chung_cu.R;

import java.util.List;

public class content_quanlycudan_phanhoiApdater extends BaseAdapter {
    private Context context;
    private int layout;
    private List<content_quanlycudan_phanhoi> content_quanlycudan_phanhoilist;

    public content_quanlycudan_phanhoiApdater(Context context, int layout, List<content_quanlycudan_phanhoi> content_quanlycudan_phanhoilist) {
        this.context = context;
        this.layout = layout;
        this.content_quanlycudan_phanhoilist = content_quanlycudan_phanhoilist;
    }

    @Override
    public int getCount() {
        return content_quanlycudan_phanhoilist.size();
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

        TextView noidung = view.findViewById(R.id.id_quanly_quanlycudan_phanhoi_noidung);
        TextView ngay = view.findViewById(R.id.id_quanly_quanlycudan_phanhoi_ngay);
        TextView sonha = view.findViewById(R.id.id_quanly_quanlycudan_phanhoi_sonha);

        content_quanlycudan_phanhoi contentQuanlycudanPhanhoi = content_quanlycudan_phanhoilist.get(i);
        noidung.setText(contentQuanlycudanPhanhoi.getNoidung());
        sonha.setText(contentQuanlycudanPhanhoi.getSonha());
        ngay.setText(contentQuanlycudanPhanhoi.getNgay());
        return view;
    }
}
