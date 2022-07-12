package com.example.quan_ly_chung_cu.fragment_quanly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.quan_ly_chung_cu.R;

public class content_quanlycudan_Apdater extends BaseAdapter {
    private Context context;
    private int layout;
    private String[] list_sonha;

    public content_quanlycudan_Apdater(Context context, int layout, String[] list_sonha) {
        this.context = context;
        this.layout = layout;
        this.list_sonha = list_sonha;
    }

    @Override
    public int getCount() {
        return list_sonha.length;
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

        TextView textView = view.findViewById(R.id.id_quanly_tang1_sonha);

        String sonha = list_sonha[i];
        textView.setText(sonha);
        return view;
    }
}
