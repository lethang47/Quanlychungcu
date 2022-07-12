package com.example.quan_ly_chung_cu.fragment_cudan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quan_ly_chung_cu.R;

import java.util.List;

public class contentdichvu_beboi_Apdater extends BaseAdapter {
    private Context context;
    private int layout;
    private List<content_dichvu_beboi> content_dichvu_beboiList;

    public contentdichvu_beboi_Apdater(Context context, int layout, List<content_dichvu_beboi> content_dichvu_beboiList) {
        this.context = context;
        this.layout = layout;
        this.content_dichvu_beboiList = content_dichvu_beboiList;
    }

    @Override
    public int getCount() {
        return content_dichvu_beboiList.size();
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
        TextView textView = view.findViewById(R.id.id_date_register);
        ImageView imageView = view.findViewById(R.id.id_image_register);
        // gan gia tri
        content_dichvu_beboi contentDichvuBeboi = content_dichvu_beboiList.get(i);
        textView.setText(contentDichvuBeboi.getDate());
        imageView.setImageResource(contentDichvuBeboi.getImage());
        return view;
    }
}
